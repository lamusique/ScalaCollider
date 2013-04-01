/*
 *  Connection.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth
package impl

import java.io.{InputStreamReader, BufferedReader, File}
import java.net.InetSocketAddress
import de.sciss.osc.{Message, Client => OSCClient}
import de.sciss.processor.impl.ProcessorImpl
import concurrent.{Await, Promise}
import concurrent.duration._
import de.sciss.processor.Processor
import java.util.concurrent.TimeoutException
import message.{Status, StatusReply}
import de.sciss.osc
import annotation.tailrec
import de.sciss.model.impl.ModelImpl
import util.{Failure, Success}

private[synth] object ConnectionLike {
   case object Ready
   case object Abort
   case object QueryServer
   final case class AddListener( l: ServerConnection.Listener )
   final case class RemoveListener( l: ServerConnection.Listener )
}

private[synth] sealed trait ConnectionLike extends ServerConnection with ModelImpl[ServerConnection.Condition] {
  conn =>

  import ServerConnection.{Running => SCRunning, _}

  def abort() {
    Handshake.abort()
  }

  object Handshake extends ProcessorImpl[ServerImpl, Any] {
    private val beginCond = Promise[Unit]()

    def begin() {
      beginCond.trySuccess()
    }

    override def notifyAborted() {
      beginCond.failure(Processor.Aborted())
    }

    def body(): ServerImpl = {
      Await.result(beginCond.future, Duration.Inf)

      if (!connectionAlive) throw new IllegalStateException("Connection closed")
      if (!c.isConnected) c.connect()
      ping(message.ServerNotify(on = true)) {
        case Message("/done", "/notify") =>
      }
      val cnt = ping(Status) {
        case m: StatusReply => m
      }
      new ServerImpl(name, c, addr, config, clientConfig, cnt)
    }

    private def ping[A](message: Message)(reply: PartialFunction[osc.Packet, A]): A = {
      val phase = Promise[A]()
      c.action = { p =>
        if (reply.isDefinedAt(p)) phase.trySuccess(reply(p))
      }
      val result = phase.future

      @tailrec def loop(): A = try {
        checkAborted()
        c ! message
        Await.result(result, 500.milliseconds)
      } catch {
        case _: TimeoutException => loop()
      }

      loop()
    }
  }

  Handshake.addListener {
    case Processor.Result(_, Success(s)) =>
      dispatch(Preparing(s))
      s.initTree()
      dispatch(SCRunning(s))
      createAliveThread(s)
    case Processor.Result(_, Failure(e)) =>
      handleAbort()
      dispatch(Aborted)
  }

  def handleAbort(): Unit
  def connectionAlive: Boolean
  def c: OSCClient
  def clientConfig: Client.Config
  def createAliveThread(s: Server): Unit
}

private[synth] final class Connection(val name: String, val c: OSCClient, val addr: InetSocketAddress, val config: Server.Config,
                                      val clientConfig: Client.Config, aliveThread: Boolean)
  extends ConnectionLike {

  import clientConfig.executionContext

  def start() {
    Handshake.start()
    Handshake.begin()
  }

  override def toString = "connect<" + name + ">"

  def handleAbort() {}

  def connectionAlive = true

  // XXX could add a timeout?
  def createAliveThread(s: Server) {
    if (aliveThread) s.startAliveThread(1.0f, 0.25f, 40) // allow for a luxury 10 seconds absence
  }
}

private[synth] final class Booting(val name: String, val c: OSCClient, val addr: InetSocketAddress,
                                   val config: Server.Config, val clientConfig: Client.Config, aliveThread: Boolean)
  extends ConnectionLike {

  import clientConfig.executionContext

  // the actual scsynth system process
  lazy val p: Process = {
    val processArgs = config.toRealtimeArgs
    val directory   = new File(config.programPath).getParentFile
    val pb          = new ProcessBuilder(processArgs: _*)
      .directory(directory)
      .redirectErrorStream(true)
    pb.start()  // throws IOException if command not found or not executable
  }

  // an auxiliary thread that monitors the scsynth process
  lazy val processThread: Thread = new Thread {
    override def run() {
      try {
        p.waitFor()
      } catch {
        case e: InterruptedException => p.destroy()
      } finally {
        println("scsynth terminated (" + p.exitValue + ")")
        abort()
      }
    }
  }

  def start() {
    // a thread the pipes the scsynth process output to the standard console
    val postThread = new Thread {
      override def run() {
        val inReader  = new BufferedReader(new InputStreamReader(p.getInputStream))
        var isOpen    = true
        var isBooting = true
        try {
          while (isOpen && isBooting) {
            val line = inReader.readLine()
            isOpen = line != null
            if (isOpen) {
              println(line)
              // of course some sucker screwed it up and added another period in SC 3.4.4
              //                        if( line == "SuperCollider 3 server ready." ) isBooting = false
              // one more... this should allow for debug versions and supernova to be detected, too
              if (line.startsWith("Super") && line.contains(" ready")) isBooting = false
            }
          }
        } catch {
          case e: Throwable => isOpen = false
        }
        if (isOpen) { // if `false`, `processThread` will terminate and invoke `abort()`
          Handshake.begin()
        }
        while (isOpen) {
          val line = inReader.readLine
          isOpen = line != null
          if (isOpen) println(line)
        }
      }
    }

    // make sure to begin with firing up Handshake, so that an immediate
    // failure of precessThread does not call abort prematurely
    Handshake.start()
    postThread.start()
    processThread.start()
  }

  override def toString = "boot<" + name + ">"

  def handleAbort() { processThread.interrupt() }
  def connectionAlive = processThread.isAlive

  def createAliveThread(s: Server) {
    // note that we optimistically assume that if we boot the server, it
    // will not die (exhausting deathBounces). if it crashes, the boot
    // thread's process will know anyway. this way we avoid stupid
    // server offline notifications when using slow asynchronous commands
    if (aliveThread) s.startAliveThread(1.0f, 0.25f, Int.MaxValue)
  }
}
