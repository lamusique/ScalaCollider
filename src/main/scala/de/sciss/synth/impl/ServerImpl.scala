/*
 *  ServerImpl.scala
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

import java.net.InetSocketAddress
import java.util.{TimerTask, Timer}
import java.io.IOException
import de.sciss.model.impl.ModelImpl
import message.StatusReply
import de.sciss.osc
import util.control.NonFatal
import concurrent.duration._
import concurrent.{Await, future, Promise, Future, TimeoutException}

private[synth] object ServerImpl {
   def add( s: Server ) {
      this.synchronized {
         if( Server.default == null ) Server.default = s
      }
   }

   def remove( s: Server ) {
      this.synchronized {
         if( Server.default == s ) Server.default = null
      }
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
  val nodeManager   = new NodeManager(this)
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

  def nextNodeID() = nodeAllocator.alloc()

  def allocControlBus(numChannels: Int) = controlBusAllocator.alloc(numChannels)
  def allocAudioBus  (numChannels: Int) = audioBusAllocator  .alloc(numChannels)
  def allocBuffer    (numChannels: Int) = bufferAllocator    .alloc(numChannels)

  def freeControlBus(index: Int) { controlBusAllocator.free(index) }
  def freeAudioBus  (index: Int) { audioBusAllocator  .free(index) }
  def freeBuffer    (index: Int) { bufferAllocator    .free(index) }

  def nextSyncID(): Int = uniqueSync.synchronized {
    val res = uniqueID; uniqueID += 1; res
  }

  def !(p: osc.Packet) { c ! p }

   def !![A](p: osc.Packet, timeout: Duration)(handler: PartialFunction[osc.Message, A]): Future[A] = {
     val promise  = Promise[A]()
     val res      = promise.future
     val oh       = new OSCTimeOutHandler(handler, promise)
     OSCReceiverActor.addHandler(oh)
     future {
       try {
         Await.ready(res, timeout)
       } catch {
         case _: TimeoutException => promise.tryFailure(message.Timeout())
       }
     }
     server ! p // only after addHandler!
     res
   }

  def counts = countsVar

  private[synth] def counts_=(newCounts: message.StatusReply) {
    countsVar = newCounts
    dispatch(Counts(newCounts))
  }

  def sampleRate = counts.sampleRate

  def dumpTree(controls: Boolean = false) {
    import Ops._
    rootNode.dumpTree(controls)
  }

  def condition = _condition

  private[synth] def condition_=(newCondition: Condition) {
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
   }

  def startAliveThread(delay: Float = 0.25f, period: Float = 0.25f, deathBounces: Int = 25) {
    condSync.synchronized {
      if (aliveThread.isEmpty) {
        val statusWatcher = new StatusWatcher(delay, period, deathBounces)
        aliveThread = Some(statusWatcher)
        statusWatcher.start()
      }
    }
  }

  def stopAliveThread() {
    condSync.synchronized {
      aliveThread.foreach(_.stop())
      aliveThread = None
    }
  }

  def queryCounts() {
    this ! message.Status
  }

  def dumpOSC(mode: osc.Dump) {
    c.dumpIn(mode, filter = {
      case m: message.StatusReply => false
      case _ => true
    })
    c.dumpOut(mode, filter = {
      case message.Status => false
      case _ => true
    })
  }

  private def serverLost() {
    nodeManager.clear()
    bufManager.clear()
    OSCReceiverActor.clear()
  }

  def serverOffline() {
    condSync.synchronized {
      stopAliveThread()
      condition = Offline
    }
  }

  def quit() {
    this ! quitMsg
    dispose()
  }

  def addResponder(resp: message.Responder) {
    OSCReceiverActor.addHandler(resp)
  }

  def removeResponder(resp: message.Responder) {
    OSCReceiverActor.removeHandler(resp)
  }

  def initTree() {
    nodeManager.register(defaultGroup)
    server ! defaultGroup.newMsg(rootNode, addToHead)
  }

  def dispose() {
    condSync.synchronized {
      serverOffline()
      ServerImpl.remove(this)
      c.close()
      OSCReceiverActor.dispose()
    }
  }

  // -------- internal class StatusWatcher --------

  private class StatusWatcher(delay: Float, period: Float, deathBounces: Int)
    extends Runnable {
    watcher =>

    private var	alive			   = deathBounces
    private val delayMillis = (delay * 1000).toInt
    private val periodMillis = (period * 1000).toInt
    //      private val	timer			   = new SwingTimer( periodMillis, this )
    private var timer: Option[Timer] = None
    private var callServerContacted = true
    private val sync = new AnyRef

    //      // ---- constructor ----
    //      timer.setInitialDelay( delayMillis )

    def start() {
      stop()
      val t = new Timer("StatusWatcher", true)
      t.schedule(new TimerTask {
        def run() {
          watcher.run()
        } // invokeOnMainThread( watcher )
      }, delayMillis, periodMillis)
      Some(t)
      timer = Some(t)
    }

    def stop() {
      //         timer.stop
      timer.foreach { t =>
        t.cancel()
        timer = None
      }
    }

    def run() {
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

    def statusReply( msg: message.StatusReply ) {
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

    def !(p: osc.Packet) {
      future { blocking {
        p match {
          case nodeMsg        : message.NodeChange  => nodeManager.nodeChange(nodeMsg)
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
      }}
    }

    def clear() {
      val h = sync.synchronized {
        val res = handlers
        handlers = Set.empty
        res
      }
      h.foreach(_.removed())
    }

    def dispose() {
      clear()
      // running = false
    }

    def addHandler(h: message.Handler) {
      sync.synchronized(handlers += h)
    }

    def removeHandler(h: message.Handler) {
      val seen = sync.synchronized {
        val res = handlers.contains(h)
        if (res) handlers -= h
        res
      }
      if (seen) h.removed()
    }

    //      def timeOutHandler( handler: OSCTimeOutHandler ) {
//         this ! TimeOutHandler( handler )
//      }

      // ------------ OSCListener interface ------------

//      def messageReceived( p: Packet ) {
////if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : ! : " + msg )
//         this ! p
//      }

//      def act() {
//         var running    = true
//         var handlers   = Set.empty[ message.Handler ]
////         while( running )( receive { })
//         loopWhile( running )( react {
//            case msg: Message => debug( msg ) {
////            case ReceivedMessage( msg, sender, time ) => debug( msg ) {
////if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : received : " + msg )
//               msg match {
//                  case nodeMsg:        message.NodeChange           => nodeManager.nodeChange( nodeMsg )
//                  case bufInfoMsg:     message.BufferInfo    => bufManager.bufferInfo( bufInfoMsg )
//                  case statusReplyMsg: message.StatusReply   => aliveThread.foreach( _.statusReply( statusReplyMsg ))
//                  case _ =>
//               }
////if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : handlers" )
//               handlers.foreach( h => if( h.handle( msg )) handlers -= h )
//            }
//            case AddHandler( h )    => handlers += h
//            case RemoveHandler( h ) => if( handlers.contains( h )) { handlers -= h; h.removed() }
//            case TimeOutHandler( h )=> if( handlers.contains( h )) { handlers -= h; h.timedOut() }
//            case Clear              => handlers.foreach( _.removed() ); handlers = Set.empty
//            case Dispose            => running = false
//            case m                  => println( "Received illegal message " + m )
//         })
//      }
   }

//   private def debug( msg: AnyRef )( code: => Unit ) {
//      val t1 = System.currentTimeMillis
//      try {
//         code
//      } catch {
//         case e: Throwable => println( "" + new java.util.Date() + " OOOPS : msg " + msg + " produced " + e )
//      }
//      val t2 = System.currentTimeMillis
//      if( (t2 - t1) > 2000 ) println( "" + new java.util.Date() + " WOW this took long (" + (t2-t1) + "): " + msg )
//   }

   // -------- internal osc.Handler implementations --------

//   private class OSCInfHandler[ A ]( fun: PartialFunction[ Message, A ], ch: OutputChannel[ A ])
//   extends message.Handler {
//      def handle( msg: Message ) : Boolean = {
//         val handled = fun.isDefinedAt( msg )
////if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : inf handled : " + msg + " ? " + handled )
//         if( handled ) try {
//            ch ! fun.apply( msg )
//         } catch { case e: Throwable => e.printStackTrace() }
//         handled
//      }
//      def removed() {}
//   }
//

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

    def removed() {}

//    def timedOut() {
//      if (fun.isDefinedAt(message.TIMEOUT)) try {
//        fun.apply(message.TIMEOUT)
//      } catch {
//        case e: Throwable => e.printStackTrace()
//      }
//    }
  }
}
