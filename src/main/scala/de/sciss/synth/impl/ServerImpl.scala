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
import de.sciss.osc.{Client => OSCClient, Dump, Message, Packet}
import java.util.{TimerTask, Timer}
import java.io.IOException
import scala.Some
import actors.{OutputChannel, DaemonActor, Actor, Channel}

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
private[synth] final class ServerImpl( val name: String, c: OSCClient, val addr: InetSocketAddress,
                                       val config: Server.Config, val clientConfig: Client.Config )
extends Server {
   server =>

   import Server._

   private var aliveThread: Option[StatusWatcher]	= None
   private var countsVar							            = new message.StatusReply(0, 0, 0, 0, 0f, 0f, 0.0, 0.0)
   private val condSync                           = new AnyRef
   private var conditionVar: Condition 			      = Running // Offline
   private var pendingCondition: Condition      	= NoPending

   val rootNode                                    = Group(this, 0)
   val defaultGroup                                = Group(this, 1)
   val nodeManager                                 = new NodeManager(this)
   val bufManager                                  = new BufferManager(this)

   private val nodeAllocator        = new NodeIDAllocator( clientConfig.clientID, clientConfig.nodeIDOffset )
   private val controlBusAllocator  = new ContiguousBlockAllocator( config.controlBusChannels )
   private val audioBusAllocator    = new ContiguousBlockAllocator( config.audioBusChannels, config.internalBusIndex )
   private val bufferAllocator      = new ContiguousBlockAllocator( config.audioBuffers )
   private var uniqueID             = 0
   private val uniqueSync           = new AnyRef

   // ---- constructor ----
   OSCReceiverActor.start()
   c.action = OSCReceiverActor ! _
   ServerImpl.add( server )

   def isLocal : Boolean = {
      val host = addr.getAddress
      host.isLoopbackAddress || host.isSiteLocalAddress
   }

   def isConnected = c.isConnected
   def isRunning = condSync.synchronized { conditionVar == Running }
   def isOffline = condSync.synchronized { conditionVar == Offline }

   def nextNodeID() = nodeAllocator.alloc()
   def allocControlBus( numChannels: Int ) = controlBusAllocator.alloc( numChannels )
   def allocAudioBus( numChannels: Int ) = audioBusAllocator.alloc( numChannels )
   def freeControlBus( index: Int ) { controlBusAllocator.free( index )}
   def freeAudioBus( index: Int ) { audioBusAllocator.free( index )}
   def allocBuffer( numChannels: Int ) = bufferAllocator.alloc( numChannels )
   def freeBuffer( index: Int ) { bufferAllocator.free( index )}
   def nextSyncID() : Int = uniqueSync.synchronized { val res = uniqueID; uniqueID += 1; res }

   def !( p: Packet ) { c ! p }

//   /**
//    * Sends out an OSC packet that generates some kind of reply, and
//    * returns immediately a `RevocableFuture` representing the parsed reply.
//    * This parsing is done by a handler which is registered.
//    * The handler is tested for each incoming OSC message (using its
//    * `isDefinedAt` method) and invoked and removed in case of a
//    * match. Note that the caller is responsible for timing out
//    * the handler after a reasonable time. To do this, the
//    * method `revoke` on the returned future must be called, which
//    * will silently unregister the handler.
//    *
//    * '''Warning''': It is crucial that the Future is awaited
//    * only within a dedicated actor thread. In particular you must
//    * be careful and aware of the fact that the handler is executed
//    * on the OSC receiver actor's body, and that you must not
//    * try to await the future from ''any'' handler function
//    * registered with OSC reception, because it would not be
//    * possible to pull the reply message of the OSC receiver's
//    * mailbox while the actor body blocks.
//    *
//    * @param   p        the packet to send out
//    * @param   handler  the handler to match against incoming messages
//    *    or timeout
//    * @return  the future representing the parsed reply, and providing
//    *    a `revoke` method to issue a timeout.
//    *
//    * @see  [[scala.actors.Futures]]
//    */
//   def !![ A ]( p: Packet, handler: PartialFunction[ Message, A ]) : RevocableFuture[ A ] = {
//      val c    = new Channel[ A ]( Actor.self )
//      val a = new FutureActor[ A ]( c ) {
//         val sync    = new AnyRef
//         var revoked = false
//         var oh: Option[ osc.Handler ] = None
//
//         def body( res: SyncVar[ A ]) {
//            val futCh   = new Channel[ A ]( Actor.self )
//            sync.synchronized { if( !revoked ) {
//               val h = new OSCInfHandler( handler, futCh )
//               oh = Some( h )
//               OSCReceiverActor.addHandler( h )
//               server ! p // only after addHandler!
//            }}
//            futCh.react { case r => res.set( r )}
//         }
//         def revoke() { sync.synchronized {
//            revoked = true
//            oh.foreach( OSCReceiverActor.removeHandler( _ ))
//            oh = None
//         }}
//      }
//      a.start()
//// NOTE: race condition, addHandler might take longer than
//// the /done, notify!
////      this ! p
//      a
//   }

   /**
    * Sends out an OSC packet that generates some kind of reply, and
    * returns immediately. It registers a handler to parse that reply.
    * The handler is tested for each incoming OSC message (using its
    * `isDefinedAt` method) and invoked and removed in case of a
    * match. If the handler doesn't match in the given timeout period,
    * it is invoked with message `TIMEOUT` and removed. If the handler
    * wishes not to do anything particular in the case of a timeout,
    * it simply should not add a case for `TIMEOUT`.
    *
    * @param   timeOut  the timeout in milliseconds
    * @param   p        the packet to send out
    * @param   handler  the handler to match against incoming messages
    *    or timeout
    *
    * @see  [[de.sciss.synth.message.TIMEOUT]]
    */
   def !?(p: Packet, timeOut: Long)(handler: PartialFunction[Any, Unit]) {
     val a = new DaemonActor {
       def act() {
         val futCh = new Channel[Any](Actor.self)
         val oh = new OSCTimeOutHandler(handler, futCh)
         OSCReceiverActor.addHandler(oh)
         server ! p // only after addHandler!
         futCh.reactWithin(timeOut) {
           case actors.TIMEOUT => OSCReceiverActor.timeOutHandler(oh)
           case r =>
         }
       }
     }
     a.start()
     // NOTE: race condition, addHandler might take longer than
     // the /done, notify!
     // this ! p
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

  def condition = condSync.synchronized { conditionVar }
   private[synth] def condition_=( newCondition: Condition ) {
      condSync.synchronized {
         if( newCondition != conditionVar ) {
            conditionVar = newCondition
            if( newCondition == Offline ) {
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

  def dumpOSC(mode: Dump = Dump.Text) {
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

   private class StatusWatcher( delay: Float, period: Float, deathBounces: Int )
   extends Runnable {
      watcher =>

      private var	alive			   = deathBounces
      private val	delayMillis		= (delay * 1000).toInt
      private val	periodMillis	= (period * 1000).toInt
//      private val	timer			   = new SwingTimer( periodMillis, this )
      private var timer: Option[ Timer ] = None
      private var callServerContacted  = true
      private val sync           = new AnyRef

//      // ---- constructor ----
//      timer.setInitialDelay( delayMillis )

      def start() {
         stop()
         timer = {
            val t = new Timer( "StatusWatcher", true )
            t.schedule( new TimerTask {
               def run() { watcher.run() } // invokeOnMainThread( watcher )
            }, delayMillis, periodMillis )
            Some( t )
         }
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
            if( alive < 0 ) {
               callServerContacted = true
               condition = Offline
            }
         }
         try {
            queryCounts()
         }
         catch { case e: IOException => printError( "Server.status", e )}
      }

      def statusReply( msg: message.StatusReply ) {
         sync.synchronized {
            alive = deathBounces
            // note: put the counts before running
            // because that way e.g. the sampleRate
            // is instantly available
            counts = msg
            if( !isRunning && callServerContacted ) {
               callServerContacted = false
//               serverContacted
               condition = Running
            }
         }
      }
   }

   private object OSCReceiverActor extends DaemonActor {
      private case object Clear
      private case object Dispose
//      private case class  ReceivedMessage( msg: Message, sender: SocketAddress, time: Long )
      private case class  AddHandler( h: message.Handler )
      private case class  RemoveHandler( h: message.Handler )
      private case class  TimeOutHandler( h: OSCTimeOutHandler )

      def clear() {
         this ! Clear
      }

      def dispose() {
         clear()
         this ! Dispose
      }

      def addHandler( handler: message.Handler ) {
         this ! AddHandler( handler )
      }

      def removeHandler( handler: message.Handler ) {
         this ! RemoveHandler( handler )
      }

      def timeOutHandler( handler: OSCTimeOutHandler ) {
         this ! TimeOutHandler( handler )
      }

      // ------------ OSCListener interface ------------

//      def messageReceived( p: Packet ) {
////if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : ! : " + msg )
//         this ! p
//      }

      def act() {
         var running    = true
         var handlers   = Set.empty[ message.Handler ]
//         while( running )( receive { })
         loopWhile( running )( react {
            case msg: Message => debug( msg ) {
//            case ReceivedMessage( msg, sender, time ) => debug( msg ) {
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : received : " + msg )
               msg match {
                  case nodeMsg:        message.NodeChange           => nodeManager.nodeChange( nodeMsg )
                  case bufInfoMsg:     message.BufferInfo    => bufManager.bufferInfo( bufInfoMsg )
                  case statusReplyMsg: message.StatusReply   => aliveThread.foreach( _.statusReply( statusReplyMsg ))
                  case _ =>
               }
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : handlers" )
               handlers.foreach( h => if( h.handle( msg )) handlers -= h )
            }
            case AddHandler( h )    => handlers += h
            case RemoveHandler( h ) => if( handlers.contains( h )) { handlers -= h; h.removed() }
            case TimeOutHandler( h )=> if( handlers.contains( h )) { handlers -= h; h.timedOut() }
            case Clear              => handlers.foreach( _.removed() ); handlers = Set.empty
            case Dispose            => running = false
            case m                  => println( "Received illegal message " + m )
         })
      }
   }

   private def debug( msg: AnyRef )( code: => Unit ) {
      val t1 = System.currentTimeMillis
      try {
         code
      } catch {
         case e: Throwable => println( "" + new java.util.Date() + " OOOPS : msg " + msg + " produced " + e )
      }
      val t2 = System.currentTimeMillis
      if( (t2 - t1) > 2000 ) println( "" + new java.util.Date() + " WOW this took long (" + (t2-t1) + "): " + msg )
   }

   // -------- internal osc.Handler implementations --------

   private class OSCInfHandler[ A ]( fun: PartialFunction[ Message, A ], ch: OutputChannel[ A ])
   extends message.Handler {
      def handle( msg: Message ) : Boolean = {
         val handled = fun.isDefinedAt( msg )
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : inf handled : " + msg + " ? " + handled )
         if( handled ) try {
            ch ! fun.apply( msg )
         } catch { case e: Throwable => e.printStackTrace() }
         handled
      }
      def removed() {}
   }

   private class OSCTimeOutHandler( fun: PartialFunction[ Any, Unit ], ch: OutputChannel[ Any ])
   extends message.Handler {
      def handle( msg: Message ) : Boolean = {
         val handled = fun.isDefinedAt( msg )
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : to handled : " + msg + " ? " + handled )
         if( handled ) try {
            ch ! fun.apply( msg )
         } catch { case e: Throwable => e.printStackTrace() }
         handled
      }
      def removed() {}
      def timedOut() {
         if( fun.isDefinedAt( message.TIMEOUT )) try {
            fun.apply( message.TIMEOUT )
         } catch { case e: Throwable => e.printStackTrace() }
      }
   }
}
