/*
 *  ServerImpl.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2014 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v2+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth
package impl

import java.net.InetSocketAddress
import java.util.{TimerTask, Timer}
import java.io.IOException
import de.sciss.model.impl.ModelImpl
import message.StatusReply
import de.sciss.osc
import util.control.NonFatal
import concurrent.duration._
import concurrent.{Await, Promise, Future, TimeoutException}

private[synth] object ServerImpl {
  @volatile private var _default: Server = null
  def default: Server = {
    val res = _default
    if (res == null) throw new IllegalStateException("There is no default Server yet")
    res
  }
//  def default_=(value: Server): Unit =
//    _default = value

  private[impl] def add(s: Server): Unit =
    this.synchronized {
      if (_default == null) _default = s
    }

  private[impl] def remove(s: Server): Unit =
    this.synchronized {
      if (_default == s) _default = null
    }
}

private[synth] final class ServerImpl(val name: String, c: osc.Client, val addr: InetSocketAddress,
                                      val config: Server.Config, val clientConfig: Client.Config,
                                      var countsVar: StatusReply)
  extends Server with ModelImpl[Server.Update] {
  server =>

  import Server._
  import clientConfig.executionContext

  private var aliveThread: Option[StatusWatcher] = None
  private val condSync = new AnyRef
  @volatile private var _condition: Condition = Running
  private var pendingCondition    : Condition = NoPending

  val rootNode      = Group(this, 0)
  val defaultGroup  = Group(this, 1)
  val nodeManager   = new NodeManager  (this)
  val bufManager    = new BufferManager(this)

  private val nodeAllocator        = new NodeIDAllocator(clientConfig.clientID, clientConfig.nodeIDOffset)
  private val controlBusAllocator  = new ContiguousBlockAllocator(config.controlBusChannels)
  private val audioBusAllocator    = new ContiguousBlockAllocator(config.audioBusChannels, config.internalBusIndex)
  private val bufferAllocator      = new ContiguousBlockAllocator(config.audioBuffers)
  private var uniqueID             = 0
  private val uniqueSync           = new AnyRef

  // ---- constructor ----
  //   OSCReceiverActor.start()
  c.action = OSCReceiverActor ! _
  ServerImpl.add(server)

  def isLocal: Boolean = {
    val host = addr.getAddress
    host.isLoopbackAddress || host.isSiteLocalAddress
  }

  def isConnected = c.isConnected
  def isRunning   = _condition == Running
  def isOffline   = _condition == Offline

  def nextNodeID(): Int = nodeAllocator.alloc()

  def allocControlBus(numChannels: Int): Int = controlBusAllocator.alloc(numChannels)
  def allocAudioBus  (numChannels: Int): Int = audioBusAllocator  .alloc(numChannels)
  def allocBuffer    (numChannels: Int): Int = bufferAllocator    .alloc(numChannels)

  def freeControlBus(index: Int): Unit = controlBusAllocator.free(index)
  def freeAudioBus  (index: Int): Unit = audioBusAllocator  .free(index)
  def freeBuffer    (index: Int): Unit = bufferAllocator    .free(index)

  def nextSyncID(): Int = uniqueSync.synchronized {
    val res = uniqueID; uniqueID += 1; res
  }

  def !(p: osc.Packet): Unit = c ! p

   def !![A](p: osc.Packet, timeout: Duration)(handler: PartialFunction[osc.Message, A]): Future[A] = {
     val promise  = Promise[A]()
     val res      = promise.future
     val oh       = new OSCTimeOutHandler(handler, promise)
     OSCReceiverActor.addHandler(oh)
     server ! p // only after addHandler!
     Future {
       try {
         Await.ready(res, timeout)
       } catch {
         case _: TimeoutException => promise.tryFailure(message.Timeout())
       }
     }
     res
   }

  def counts = countsVar

  private[synth] def counts_=(newCounts: message.StatusReply): Unit = {
    countsVar = newCounts
    dispatch(Counts(newCounts))
  }

  def sampleRate = counts.sampleRate

  def dumpTree(controls: Boolean = false): Unit = {
    import Ops._
    rootNode.dumpTree(controls)
  }

  def condition = _condition

  private[synth] def condition_=(newCondition: Condition): Unit =
    condSync.synchronized {
      if (newCondition != _condition) {
        _condition = newCondition
        if (newCondition == Offline) {
          pendingCondition = Server.NoPending
          serverLost()
        }
           //            else if( newCondition == Running ) {
           //               if( pendingCondition == Booting ) {
           //                  pendingCondition = NoPending
           //                  collBootCompletion.foreach( action => try {
           //                        action.apply( this )
           //                     }
           //                     catch { case e => e.printStackTrace() }
           //                  )
           //                  collBootCompletion = Queue.empty
           //               }
           //            }
           dispatch( newCondition )
         }
      }

  def startAliveThread(delay: Float = 0.25f, period: Float = 0.25f, deathBounces: Int = 25): Unit =
    condSync.synchronized {
      if (aliveThread.isEmpty) {
        val statusWatcher = new StatusWatcher(delay, period, deathBounces)
        aliveThread = Some(statusWatcher)
        statusWatcher.start()
      }
    }

  def stopAliveThread(): Unit =
    condSync.synchronized {
      aliveThread.foreach(_.stop())
      aliveThread = None
    }

  def queryCounts(): Unit = this ! message.Status

  def dumpOSC(mode: osc.Dump, filter: osc.Packet => Boolean): Unit = {
    dumpInOSC (mode, filter)
    dumpOutOSC(mode, filter)
  }

  def dumpInOSC(mode: osc.Dump, filter: osc.Packet => Boolean): Unit =
    c.dumpIn(mode, filter = {
      case m: message.StatusReply => false
      case p => filter(p)
    })

  def dumpOutOSC(mode: osc.Dump, filter: osc.Packet => Boolean): Unit =
    c.dumpOut(mode, filter = {
      case message.Status => false
      case p => filter(p)
    })

  private def serverLost(): Unit = {
    nodeManager     .clear()
    bufManager      .clear()
    OSCReceiverActor.clear()
  }

  def serverOffline(): Unit =
    condSync.synchronized {
      stopAliveThread()
      condition = Offline
    }

  def quit(): Unit = {
    this ! quitMsg
    dispose()
  }

  def addResponder   (resp: message.Responder): Unit = OSCReceiverActor.addHandler   (resp)
  def removeResponder(resp: message.Responder): Unit = OSCReceiverActor.removeHandler(resp)

  def initTree(): Unit = {
    nodeManager.register(defaultGroup)
    server ! defaultGroup.newMsg(rootNode, addToHead)
  }

  def dispose(): Unit =
    condSync.synchronized {
      serverOffline()
      ServerImpl.remove(this)
      c.close()
      OSCReceiverActor.dispose()
    }

  def syncMsg(): message.Sync = message.Sync(nextSyncID())
  def quitMsg = message.ServerQuit


  // -------- internal class StatusWatcher --------

  private final class StatusWatcher(delay: Float, period: Float, deathBounces: Int)
    extends Runnable {
    watcher =>

    private var	alive			          = deathBounces
    private val delayMillis         = (delay  * 1000).toInt
    private val periodMillis        = (period * 1000).toInt
    //      private val	timer			   = new SwingTimer( periodMillis, this )
    private var timer               = Option.empty[Timer]
    private var callServerContacted = true
    private val sync                = new AnyRef

    //      // ---- constructor ----
    //      timer.setInitialDelay( delayMillis )

    def start(): Unit = {
      stop()
      val t = new Timer("StatusWatcher", true)
      t.schedule(new TimerTask {
        def run(): Unit = watcher.run()
      }, delayMillis, periodMillis)
      Some(t)
      timer = Some(t)
    }

    def stop(): Unit = {
      //         timer.stop
      timer.foreach { t =>
        t.cancel()
        timer = None
      }
    }

    def run(): Unit = {
      sync.synchronized {
        alive -= 1
        if (alive < 0) {
          callServerContacted = true
          condition = Offline
        }
      }
      try {
        queryCounts()
      }
      catch {
        case e: IOException => printError("Server.status", e)
      }
    }

    def statusReply(msg: message.StatusReply): Unit =
      sync.synchronized {
        alive = deathBounces
        // note: put the counts before running
        // because that way e.g. the sampleRate
        // is instantly available
        counts = msg
        if (!isRunning && callServerContacted) {
          callServerContacted = false
          //               serverContacted
          condition = Running
        }
      }
  }

  private object OSCReceiverActor /* extends DaemonActor */ {
    import concurrent._

    //    private case object Clear
    //
    //    private case object Dispose
    //
    //    private case class AddHandler(h: message.Handler)
    //
    //    private case class RemoveHandler(h: message.Handler)

    //      private case class  TimeOutHandler( h: OSCTimeOutHandler )

    private val sync = new AnyRef
    @volatile private var handlers = Set.empty[message.Handler]

    def !(p: osc.Packet): Unit = Future {
      blocking {
        p match {
          case nodeMsg        : message.NodeChange  =>
            // println(s"---- NodeChange: $nodeMsg")
            nodeManager.nodeChange(nodeMsg)
          case bufInfoMsg     : message.BufferInfo  => bufManager.bufferInfo(bufInfoMsg)
          case statusReplyMsg : message.StatusReply => aliveThread.foreach(_.statusReply(statusReplyMsg))
          case _ =>
        }
        p match {
          case m: osc.Message =>
            handlers.foreach { h =>
              if (h.handle(m)) sync.synchronized(handlers -= h)
            }
          case _ => // ignore bundles send from scsynth
        }
      }
    }

    def clear(): Unit = {
      val h = sync.synchronized {
        val res = handlers
        handlers = Set.empty
        res
      }
      h.foreach(_.removed())
    }

    def dispose(): Unit = clear()

    def addHandler(h: message.Handler): Unit =
      sync.synchronized(handlers += h)

    def removeHandler(h: message.Handler): Unit = {
      val seen = sync.synchronized {
        val res = handlers.contains(h)
        if (res) handlers -= h
        res
      }
      if (seen) h.removed()
    }
  }

  private final class OSCTimeOutHandler[A](fun: PartialFunction[osc.Message, A], promise: Promise[A])
    extends message.Handler {

    def handle(msg: osc.Message): Boolean = {
      if (promise.isCompleted) return true

      val handled = fun.isDefinedAt(msg)
      if (handled) try {
        promise.trySuccess(fun(msg))
      } catch {
        case NonFatal(e) =>
          promise.tryFailure(e)
      }
      handled
    }

    def removed() = ()
  }
}
