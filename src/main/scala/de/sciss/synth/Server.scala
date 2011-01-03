/*
 *  Server.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2011 Hanns Holger Rutz. All rights reserved.
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
 *
 *
 *  Changelog:
 */

package de.sciss.synth

import de.sciss.osc.{ OSCChannel, OSCClient, OSCMessage, OSCPacket, OSCTransport, TCP, UDP }
import java.net.{ ConnectException, DatagramSocket, InetAddress, InetSocketAddress, ServerSocket, SocketAddress }
import java.io.{ BufferedReader, File, InputStreamReader, IOException }
import java.util.{ Timer, TimerTask }
import actors.{ Actor, Channel, DaemonActor, Future, InputChannel, OutputChannel, TIMEOUT }
import collection.breakOut
import collection.immutable.Queue
import concurrent.SyncVar
import osc.{ OSCBufferInfoMessage, OSCHandler, OSCNodeChange, OSCResponder, OSCServerNotifyMessage,
             OSCServerQuitMessage, OSCStatusMessage, OSCStatusReplyMessage, OSCSyncMessage, ServerCodec }

/**
 * 	@version    0.16, 03-Aug-10
 */
object Server {
   private val allSync  = new AnyRef
//   private var allVar   = Set.empty[ Server ]
   var default: Server  = null

//   def all     = allVar

   def defaultCmdPath = new File( System.getenv( "SC_HOME" ), "scsynth" ).getAbsolutePath

   @throws( classOf[ IOException ])
   def boot: ServerConnection = boot()()

   @throws( classOf[ IOException ])
   def boot( name: String = "localhost", options: ServerOptions = (new ServerOptionsBuilder).build,
             clientOptions: ClientOptions = (new ClientOptionsBuilder).build )
           ( listener: Model.Listener = Model.EmptyListener ) : ServerConnection = {
      val sc = initBoot( name, options, clientOptions )
      if( !(listener eq Model.EmptyListener) ) sc.addListener( listener )
      sc.start
      sc
   }

   private def initBoot( name: String = "localhost", options: ServerOptions = (new ServerOptionsBuilder).build,
             clientOptions: ClientOptions = (new ClientOptionsBuilder).build ) = {
      val (addr, c) = prepareConnection( options, clientOptions )
      new BootingImpl( name, c, addr, options, clientOptions, true )
   }

   @throws( classOf[ IOException ])
   def connect: ServerConnection = connect()()

   @throws( classOf[ IOException ])
   def connect( name: String = "localhost", options: ServerOptions = (new ServerOptionsBuilder).build,
                clientOptions: ClientOptions = (new ClientOptionsBuilder).build )
              ( listener: Model.Listener = Model.EmptyListener ) : ServerConnection = {
      val (addr, c) = prepareConnection( options, clientOptions )
      val sc = new ConnectionImpl( name, c, addr, options, clientOptions, true )
      if( !(listener eq Model.EmptyListener) ) sc.addListener( listener )
      sc.start
      sc
   }

   def test( code: Server => Unit ) : Unit = test()( code )
   
   /**
    *    Utility method to test code quickly with a running server. This boots a
    *    server and executes the passed in code when the server is up. A shutdown
    *    hook is registered to make sure the server is destroyed when the VM exits.
    */
   def test( options: ServerOptions = (new ServerOptionsBuilder).build )( code: Server => Unit ) {
//      val b = boot( options = options )
      val sync = new AnyRef
      var s : Server = null
      val sc = initBoot( options = options )
      sc.addListener {
         case ServerConnection.Running( srv ) => sync.synchronized { s = srv }; code( srv )
      }
      Runtime.getRuntime().addShutdownHook( new Thread { override def run = sync.synchronized {
         if( s != null ) {
            if( s.condition != Server.Offline ) s.quit
         } else sc.abort
      }})
      sc.start
   }

   @throws( classOf[ IOException ])
   def dummy: Server = dummy()

   /**
    * Creates an unconnected server proxy. This may be usefull for creating NRT command files.
    * Any attempt to try to send messages to the server will fail.
    */
   @throws( classOf[ IOException ])
   def dummy( name: String = "dummy", options: ServerOptions = (new ServerOptionsBuilder).build,
                clientOptions: ClientOptions = (new ClientOptionsBuilder).build ) : Server = {
      val (addr, c) = prepareConnection( options, clientOptions )
      new Server( name, c, addr, options, clientOptions )
   }

   @throws( classOf[ IOException ])
   private def prepareConnection( options: ServerOptions, clientOptions: ClientOptions ) : (InetSocketAddress, OSCClient) = {
      val addr = new InetSocketAddress( options.host, options.port )
      val clientAddr = clientOptions.addr getOrElse {
          if( addr.getAddress.isLoopbackAddress )
             new InetSocketAddress( "127.0.0.1", 0 ) else
             new InetSocketAddress( InetAddress.getLocalHost, 0 )
      }
      val c    = createClient( options.transport, addr, clientAddr )
      (addr, c)
   }
   
   def allocPort( transport: OSCTransport ) : Int = {
      transport match {
         case TCP => {
            val ss = new ServerSocket( 0 )
            try {
               ss.getLocalPort()
            } finally {
               ss.close()
            }
         }
         case UDP => {
            val ds = new DatagramSocket()
            try {
               ds.getLocalPort()
            } finally {
               ds.close()
            }
         }
         case x => error( "Unsupported transport : " + x.name )
      }
   }

   private def add( s: Server ) {
      allSync.synchronized {
//         allVar += s
         if( default == null ) default = s
      }
   }

   private def remove( s: Server ) {
      allSync.synchronized {
//         allVar -= s
         if( default == s ) default = null
      }
   }

   def printError( name: String, t: Throwable ) {
      println( name + " : " )
      t.printStackTrace()
   }

   abstract sealed class Condition
   case object Running extends Condition
//   case object Booting extends Condition
   case object Offline extends Condition
   private case object Terminating extends Condition
   private case object NoPending extends Condition

   case class Counts( c: OSCStatusReplyMessage )

   private def createClient( transport: OSCTransport, serverAddr: InetSocketAddress,
                             clientAddr: InetSocketAddress ) : OSCClient = {
//      val client        = OSCClient( transport, 0, addr.getAddress.isLoopbackAddress, ServerCodec )
      val client        = OSCClient.withAddress( transport, clientAddr, ServerCodec )
      client.bufferSize = 0x10000
      client.target     = serverAddr
      client
   }

   // -------- internal class BootThread --------

   private object ConnectionImplLike {
      case object Abort
      case object QueryServer
      case class AddListener( l: Model.Listener )
      case class RemoveListener( l: Model.Listener )
//      case object Aborted
   }

   // XXX TODO : CLEAN UP THIS MESS
   private trait ConnectionImplLike extends ServerConnection {
      import ConnectionImplLike._
      import ServerConnection.{ Running => SCRunning, _ }

      val actor = new DaemonActor {
//         var state: Condition = Connecting
         def act {
//            dispatch( Connecting )
            loop {
               if( connectionAlive ) {
                  try {
                     c.start
                     c.action = (msg, addr, when) => this ! msg
                     var tnotify = 0L
                     def snotify {
                        tnotify = System.currentTimeMillis + 5000
                        c ! OSCServerNotifyMessage( true )
                     }
                     snotify
                     loop { reactWithin( math.max( 0L, tnotify - System.currentTimeMillis) ) {
                        case TIMEOUT            => snotify // loop is retried
                        case AddListener( l )   => actAddList( l )
                        case RemoveListener( l )=> actRemoveList( l )
                        case Abort              => abortHandler( None )
                        case OSCMessage( "/done", "/notify" ) => {
                           var tstatus = 0L
                           def sstatus {
                              tstatus = System.currentTimeMillis + 500
                              c ! OSCStatusMessage
                           }
                           sstatus
                           loop { reactWithin( math.max( 0L, tstatus - System.currentTimeMillis) ) {
                              case TIMEOUT            => sstatus // loop is retried
                              case AddListener( l )   => actAddList( l )
                              case RemoveListener( l )=> actRemoveList( l )
                              case Abort              => abortHandler( None )
                              case counts: OSCStatusReplyMessage => {
                                 val s = new Server( name, c, addr, options, clientOptions )
                                 s.counts = counts
                                 dispatch( Preparing( s ))
                                 s.initTree
                                 dispatch( SCRunning( s ))
                                 createAliveThread( s )
                                 loop { react {
                                    case QueryServer        => reply( s )
                                    case AddListener( l )   => actAddList( l ); actDispatch( l, SCRunning( s ))
                                    case RemoveListener( l )=> actRemoveList( l )
                                    case Abort              => abortHandler( Some( s ))
                                    case ServerConnection.Aborted => {
                                       s.serverOffline
                                       dispatch( Aborted )
                                       loop { react {
                                          case AddListener( l )   => actAddList( l ); actDispatch( l, Aborted )
                                          case RemoveListener( l )=> actRemoveList( l )
                                          case Abort              => reply ()
                                          case QueryServer        => reply( s )
                                       }}
                                    }
                                 }}
                              }
                           }}
                        }
                     }}
                  }
                  catch { case e: ConnectException => // thrown when in TCP mode and socket not yet available
                     val tretry  = System.currentTimeMillis + 500
                     var looping = true
                     loopWhile( looping ) { reactWithin( math.max( 0L, tretry - System.currentTimeMillis) ) {
                        case TIMEOUT            => looping = false
                        case AddListener( l )   => actAddList( l )
                        case RemoveListener( l )=> actRemoveList( l )
                        case Abort              => abortHandler( None )
                     }}
                  }
               } else loop { react {
                  case Abort  => reply ()
                  case _      =>
               }}
            }
         }

         private def abortHandler( server: Option[ Server ]) {
            handleAbort
            val from = sender
            loop { react {
               case ServerConnection.Aborted => {
                  server.foreach( _.serverOffline )
                  dispatch( ServerConnection.Aborted )
                  from ! ()
               }
               case AddListener( l )   => actAddList( l )
               case RemoveListener( l )=> actRemoveList( l )
               case _                  => // XXX ?
            }}
         }
      }

      private def actDispatch( l: Model.Listener, change: AnyRef ) {
         try {
            if( l.isDefinedAt( change )) l( change )
         } catch {
            case e => e.printStackTrace() // catch, but print
         }
      }

      private def actAddList( l: Model.Listener ) {
         super.addListener( l )
      }

      private def actRemoveList( l: Model.Listener ) {
         super.removeListener( l )
      }

      override def addListener( l: Model.Listener ) : Model.Listener = {
         actor ! AddListener( l )
         l
      }

      override def removeListener( l: Model.Listener ) : Model.Listener = {
         actor ! RemoveListener( l )
         l
      }

//      def start { actor ! Start }
      lazy val server : Future[ Server ] = actor !! (QueryServer, { case s: Server => s })
      lazy val abort : Future[ Unit ] = actor !! (Abort, { case _ => ()})

      def handleAbort : Unit
      def connectionAlive : Boolean
      def c : OSCClient
      def clientOptions : ClientOptions
      def createAliveThread( s: Server ) : Unit
   }

   private class ConnectionImpl @throws( classOf[ IOException ])
      ( val name: String, val c: OSCClient, val addr: InetSocketAddress, val options: ServerOptions,
        val clientOptions: ClientOptions, aliveThread: Boolean )
   extends ConnectionImplLike {
      import ConnectionImplLike._

      def start { actor.start }

      override def toString = "connect<" + name + ">"

      def handleAbort {}
      def connectionAlive = true // XXX could add a timeout?
      def createAliveThread( s: Server ) {
         if( aliveThread ) s.startAliveThread( 1.0f, 0.25f, 40 ) // allow for a luxury 10 seconds absence
      }
   }

   private class BootingImpl @throws( classOf[ IOException ])
      ( val name: String, val c: OSCClient, val addr: InetSocketAddress, val options: ServerOptions,
        val clientOptions: ClientOptions, aliveThread: Boolean )
   extends ConnectionImplLike {
      import ConnectionImplLike._

      lazy val p = {
         val processArgs   = options.toRealtimeArgs
         val directory     = new File( options.programPath ).getParentFile
         val pb            = new ProcessBuilder( processArgs: _* )
            .directory( directory )
            .redirectErrorStream( true )
         pb.start    // throws IOException if command not found or not executable
      }

      lazy val processThread = new Thread {
         override def run = try {
            p.waitFor()
         } catch { case e: InterruptedException =>
            p.destroy()
         } finally {
            println( "scsynth terminated (" + p.exitValue +")" )
            actor ! ServerConnection.Aborted
         }
      }

      def start {
         val inReader   = new BufferedReader( new InputStreamReader( p.getInputStream ))
         val postActor  = new DaemonActor {
            def act {
               var isOpen = true
               loopWhile( isOpen ) {
                  val line = inReader.readLine
                  isOpen = line != null
                  if( isOpen ) println( line )
               }
            }
         }

         // ...and go
         postActor.start
         processThread.start
         actor.start
      }

      override def toString = "boot<" + name + ">"

      def handleAbort { processThread.interrupt() }
      def connectionAlive = processThread.isAlive
      def createAliveThread( s: Server ) {
         // note that we optimistically assume that if we boot the server, it
         // will not die (exhausting deathBounces). if it crashes, the boot
         // thread's process will know anyway. this way we avoid stupid
         // server offline notifications when using slow asynchronous commands
         if( aliveThread ) s.startAliveThread( 1.0f, 0.25f, Int.MaxValue )
      }
   }
}

trait ServerLike extends Model {
   def name: String
   def options: ServerOptions
   def addr: InetSocketAddress
}

object ServerConnection {
   sealed abstract class Condition
//   case object Connecting extends Condition
   case class Preparing( server: Server ) extends Condition
   case class Running( server: Server ) extends Condition
   case object Aborted extends Condition
}
trait ServerConnection extends ServerLike {
//   def start : Unit
   def server : Future[ Server ]
   def abort : Future[ Unit ]
}

//abstract class Server extends Model {}
class Server private( val name: String, c: OSCClient, val addr: InetSocketAddress, val options: ServerOptions,
                      val clientOptions: ClientOptions )
extends ServerLike {
   server =>

   import Server._

   private var aliveThread: Option[StatusWatcher]	= None
//   private var bootThread: Option[BootThread]		= None
   private var countsVar							      = new OSCStatusReplyMessage( 0, 0, 0, 0, 0f, 0f, 0.0, 0.0 )
//   private var collBootCompletion					   = Queue.empty[ (Server) => Unit ]
   private val condSync                            = new AnyRef
   private var conditionVar: Condition 			   = Running // Offline
   private var pendingCondition: Condition      	= NoPending
//   private var bufferAllocatorVar: ContiguousBlockAllocator = null
//   private val host                                = InetAddress.getByName( options.host.value )

//   val addr                                        = new InetSocketAddress( host, options.port.value )
   val rootNode                                    = new Group( this, 0 )
   val defaultGroup                                = new Group( this, 1 )
   val nodeMgr                                     = new NodeManager( this )
   val bufMgr                                      = new BufferManager( this )
//   var latency                                     = 0.2f

   // ---- constructor ----
   {
      OSCReceiverActor.start
      c.action = OSCReceiverActor.messageReceived
      add( server )
   }

   def isLocal : Boolean = {
      val host = addr.getAddress
      host.isLoopbackAddress || host.isSiteLocalAddress
   }
   
   def isConnected = c.isConnected
   def isRunning = condSync.synchronized { conditionVar == Running }
//   def isBooting = condSync.synchronized { conditionVar == Booting }
   def isOffline = condSync.synchronized { conditionVar == Offline }
//   def bufferAllocator = bufferAllocatorVar

   object nodes {
      private var allocator = new NodeIDAllocator( clientOptions.clientID, clientOptions.nodeIDOffset )

      def nextID = allocator.alloc
   }

   object busses {
      private var controlAllocator = new ContiguousBlockAllocator( options.controlBusChannels )
      private var audioAllocator = new ContiguousBlockAllocator( options.audioBusChannels, options.firstPrivateBus )

      def allocControl( numChannels: Int ) = controlAllocator.alloc( numChannels )
      def allocAudio( numChannels: Int ) = audioAllocator.alloc( numChannels )
      def freeControl( index: Int ) = controlAllocator.free( index )
      def freeAudio( index: Int ) = audioAllocator.free( index )
   }

   object buffers {
      private var allocator = new ContiguousBlockAllocator( options.audioBuffers )

      def alloc( numChannels: Int ) = allocator.alloc( numChannels )
      def free( index: Int ) = allocator.free( index )
   }

   private object uniqueID {
      private var id = 0
      def nextID = this.synchronized { val res = id; id += 1; res }
   }

   def !( p: OSCPacket ) { c ! p }

   /**
    * Sends out an OSC packet that generates some kind of reply, and
    * returns immediately a `RevocableFuture` representing the parsed reply.
    * This parsing is done by a handler which is registered.
    * The handler is tested for each incoming OSC message (using its
    * `isDefinedAt` method) and invoked and removed in case of a
    * match. Note that the caller is responsible for timing out
    * the handler after a reasonable time. To do this, the
    * method `revoke` on the returned future must be called, which
    * will silently unregister the handler.
    *
    * '''Warning''': It is crucial that the Future is awaited
    * only within a dedicated actor thread. In particular you must
    * be careful and aware of the fact that the handler is executed
    * on the OSC receiver actor's body, and that you must not
    * try to await the future from ''any'' handler function
    * registered with OSC reception, because it would not be
    * possible to pull the reply message of the OSC receiver's
    * mailbox while the actor body blocks. 
    *
    * @param   p        the packet to send out
    * @param   handler  the handler to match against incoming messages
    *    or timeout
    * @return  the future representing the parsed reply, and providing
    *    a `revoke` method to issue a timeout.
    *
    * @see  [[scala.actors.Futures]]
    */
   def !![ A ]( p: OSCPacket, handler: PartialFunction[ OSCMessage, A ]) : RevocableFuture[ A ] = {
      val c    = new Channel[ A ]( Actor.self )
      val a = new FutureActor[ A ]( c ) {
         val sync    = new AnyRef
         var revoked = false
         var oh: Option[ OSCHandler ] = None

         def body( res: SyncVar[ A ]) {
            val futCh   = new Channel[ A ]( Actor.self )
            sync.synchronized { if( !revoked ) {
               val h = new OSCInfHandler( handler, futCh )
               oh = Some( h )
               OSCReceiverActor.addHandler( h )
               server ! p // only after addHandler!
            }}
            futCh.react { case r => res.set( r )}
         }
         def revoke { sync.synchronized {
            revoked = true
            oh.foreach( OSCReceiverActor.removeHandler( _ ))
            oh = None
         }}
      }
      a.start()
// NOTE: race condition, addHandler might take longer than
// the /done, notify!
//      this ! p
      a
   }

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
    * @see  [[scala.actors.TIMEOUT]]
    */
   def !?( timeOut: Long, p: OSCPacket, handler: PartialFunction[ Any, Unit ]) {
      val a = new DaemonActor {
         def act {
            val futCh   = new Channel[ Any ]( Actor.self )
            val oh      = new OSCTimeOutHandler( handler, futCh )
            OSCReceiverActor.addHandler( oh )
            server ! p // only after addHandler!
            futCh.reactWithin( timeOut ) {
               case TIMEOUT   => OSCReceiverActor.timeOutHandler( oh )
               case r         =>
            }
         }
      }
      a.start()
// NOTE: race condition, addHandler might take longer than
// the /done, notify!
//      this ! p
      a
   }

   def counts = countsVar
   private[synth] def counts_=( newCounts: OSCStatusReplyMessage ) {
      countsVar = newCounts
      dispatch( Counts( newCounts ))
   }

   def sampleRate = counts.sampleRate
  
   def dumpTree : Unit = dumpTree( false )

   def dumpTree( controls: Boolean ) {
      rootNode.dumpTree( controls )
   }
  
   def condition = condSync.synchronized { conditionVar }
   private[synth] def condition_=( newCondition: Condition ) {
      condSync.synchronized {
         if( newCondition != conditionVar ) {
            conditionVar = newCondition
            if( newCondition == Offline ) {
               pendingCondition = NoPending
               serverLost
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

   def startAliveThread( delay: Float = 0.25f, period: Float = 0.25f, deathBounces: Int = 25 ) {
      condSync.synchronized {
         if( aliveThread.isEmpty ) {
            val statusWatcher = new StatusWatcher( delay, period, deathBounces )
            aliveThread = Some( statusWatcher )
            statusWatcher.start
         }
      }
   }

   def stopAliveThread {
      condSync.synchronized {
         aliveThread.foreach( _.stop )
         aliveThread = None
      }
  }

   def queryCounts {
      this ! OSCStatusMessage
   }

   def syncMsg : OSCSyncMessage = syncMsg()
   def syncMsg( id: Int = uniqueID.nextID ) = OSCSyncMessage( id )

   def dumpOSC( mode: Int = OSCChannel.DUMP_TEXT ) {
      c.dumpIncomingOSC( mode, filter = {
         case m: OSCStatusReplyMessage => false
         case _ => true
      })
      c.dumpOutgoingOSC( mode, filter = {
         case OSCStatusMessage => false
         case _ => true
      })
   }

   private def serverLost {
      nodeMgr.clear
      bufMgr.clear
      OSCReceiverActor.clear
   }

   private def serverOffline {
      condSync.synchronized {
//         bootThread = None
         stopAliveThread
         condition = Offline
      }
   }

   def quit {
      this ! quitMsg
      cleanUpAfterQuit
   }

   def quitMsg = OSCServerQuitMessage

   private def cleanUpAfterQuit {
      try {
         condSync.synchronized {
            stopAliveThread
            pendingCondition = Terminating
         }
      }
      catch { case e: IOException => printError( "Server.cleanUpAfterQuit", e )}
   }

   private[synth] def addResponder( resp: OSCResponder ) {
      OSCReceiverActor.addHandler( resp )
   }

   private[synth] def removeResponder( resp: OSCResponder ) {
      OSCReceiverActor.removeHandler( resp )
   }

   private[synth] def initTree {
      nodeMgr.register( defaultGroup )
      server ! defaultGroup.newMsg( rootNode, addToHead )
   }

   def dispose {
      condSync.synchronized {
         serverOffline
         remove( this )
         c.dispose // = (msg: OSCMessage, sender: SocketAddress, time: Long) => ()
         OSCReceiverActor.dispose
//         c.dispose
      }
   }

   override def toString = "<" + name + ">"

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

      def start {
         stop
         timer = {
            val t = new Timer( "StatusWatcher", true )
            t.schedule( new TimerTask {
               def run = watcher.run // invokeOnMainThread( watcher )
            }, delayMillis, periodMillis )
            Some( t )
         }
      }

      def stop {
//         timer.stop
         timer.foreach( t => {
            t.cancel
            timer = None
         })
      }

      def run {
         sync.synchronized {
            alive -= 1
            if( alive < 0 ) {
               callServerContacted = true
               condition = Offline
            }
         }
         try {
            queryCounts
         }
         catch { case e: IOException => printError( "Server.status", e )}
      }

      def statusReply( msg: OSCStatusReplyMessage ) {
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
      private case class  ReceivedMessage( msg: OSCMessage, sender: SocketAddress, time: Long )
      private case class  AddHandler( h: OSCHandler )
      private case class  RemoveHandler( h: OSCHandler )
      private case class  TimeOutHandler( h: OSCTimeOutHandler )

      def clear {
         this ! Clear
      }

      def dispose {
         clear
         this ! Dispose
      }

      def addHandler( handler: OSCHandler ) {
         this ! AddHandler( handler )
      }

      def removeHandler( handler: OSCHandler ) {
         this ! RemoveHandler( handler )
      }

      def timeOutHandler( handler: OSCTimeOutHandler ) {
         this ! TimeOutHandler( handler )
      }

      // ------------ OSCListener interface ------------

      def messageReceived( msg: OSCMessage, sender: SocketAddress, time: Long ) {
//if( msg.name == "/synced" ) println( "" + new java.util.Date() + " : ! : " + msg )
         this ! ReceivedMessage( msg, sender, time )
      }

      def act {
         var running    = true
         var handlers   = Set.empty[ OSCHandler ]
//         while( running )( receive { })
         loopWhile( running )( react {
            case ReceivedMessage( msg, sender, time ) => debug( msg ) {
//if( msg.name == "/synced" ) println( "" + new java.util.Date() + " : received : " + msg )
               msg match {
                  case nodeMsg:        OSCNodeChange           => nodeMgr.nodeChange( nodeMsg )
                  case bufInfoMsg:     OSCBufferInfoMessage    => bufMgr.bufferInfo( bufInfoMsg )
                  case statusReplyMsg: OSCStatusReplyMessage   => aliveThread.foreach( _.statusReply( statusReplyMsg ))
                  case _ =>
               }
//if( msg.name == "/synced" ) println( "" + new java.util.Date() + " : handlers" )
               handlers.foreach( h => if( h.handle( msg )) handlers -= h )
            }
            case AddHandler( h )    => handlers += h
            case RemoveHandler( h ) => if( handlers.contains( h )) { handlers -= h; h.removed }
            case TimeOutHandler( h )=> if( handlers.contains( h )) { handlers -= h; h.timedOut }
            case Clear              => handlers.foreach( _.removed ); handlers = Set.empty
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
         case e => println( "" + new java.util.Date() + " OOOPS : msg " + msg + " produced " + e )
      }
      val t2 = System.currentTimeMillis
      if( (t2 - t1) > 2000 ) println( "" + new java.util.Date() + " WOW this took long (" + (t2-t1) + "): " + msg )
   }

   // -------- internal OSCHandler implementations --------

   private class OSCInfHandler[ A ]( fun: PartialFunction[ OSCMessage, A ], ch: OutputChannel[ A ])
   extends OSCHandler {
      def handle( msg: OSCMessage ) : Boolean = {
         val handled = fun.isDefinedAt( msg )
//if( msg.name == "/synced" ) println( "" + new java.util.Date() + " : inf handled : " + msg + " ? " + handled )
         if( handled ) try {
            ch ! fun.apply( msg )
         } catch { case e => e.printStackTrace() }
         handled
      }
      def removed {}
   }

   private class OSCTimeOutHandler( fun: PartialFunction[ Any, Unit ], ch: OutputChannel[ Any ])
   extends OSCHandler {
      def handle( msg: OSCMessage ) : Boolean = {
         val handled = fun.isDefinedAt( msg )
//if( msg.name == "/synced" ) println( "" + new java.util.Date() + " : to handled : " + msg + " ? " + handled )
         if( handled ) try {
            ch ! fun.apply( msg )
         } catch { case e => e.printStackTrace() }
         handled
      }
      def removed {}
      def timedOut {
         if( fun.isDefinedAt( TIMEOUT )) try {
            fun.apply( TIMEOUT )
         } catch { case e => e.printStackTrace() }
      }
   }
}