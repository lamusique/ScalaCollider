/*
 *  Server.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2012 Hanns Holger Rutz. All rights reserved.
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

import io.{AudioFileType, SampleFormat}
import java.io.{BufferedReader, File, InputStreamReader, IOException}
import java.util.{Timer, TimerTask}
import actors.{Actor, Channel, DaemonActor, Future, OutputChannel, TIMEOUT}
import concurrent.SyncVar
import aux.{FutureActor, RevocableFuture, NodeIDAllocator, ContiguousBlockAllocator}
import sys.error
import de.sciss.osc.{Dump, Client => OSCClient, Message, Packet, Transport, TCP, UDP}
import java.net.{DatagramSocket, InetAddress, InetSocketAddress, ServerSocket}
import collection.mutable.ListBuffer

object Server {
   private val allSync  = new AnyRef
//   private var allVar   = Set.empty[ Server ]
   var default: Server  = null

   /**
    * The default file path to `scsynth`. If the runtime (system) property `"SC_HOME"` is provided,
    * this specifies the directory of `scsynth. Otherwise, an environment (shell) variable named
    * `"SC_HOME"` is checked. If neither exists, this returns `scsynth` in the current working directory.
    */
   def defaultProgramPath = new File( sys.props.getOrElse( "SC_HOME", sys.env.getOrElse( "SC_HOME", "" )),
                                      "scsynth" ).getAbsolutePath

   trait ConfigLike {
      def programPath:           String
      def controlBusChannels:    Int
      def audioBusChannels:      Int
      def outputBusChannels:     Int
      def blockSize:             Int
      def sampleRate:            Int
      def audioBuffers:          Int
      def maxNodes:              Int
      def maxSynthDefs:          Int
      def memorySize:            Int
      def wireBuffers:           Int
      def randomSeeds:           Int
      def loadSynthDefs:         Boolean
      def machPortName:          Option[ (String, String) ]
      def verbosity:             Int
      def plugInsPaths:          List[ String ]
      def restrictedPath:        Option[ String ]
      def memoryLocking:         Boolean

//   // client only
//   def clientID:              Int
//   def nodeIDOffset:          Int

      // realtime only
      def host:                  String
      def port:                  Int
      def transport:             Transport.Net
      def inputStreamsEnabled:   Option[ String ]
      def outputStreamsEnabled:  Option[ String ]
      def deviceName:            Option[ String ]
      def deviceNames:           Option[ (String, String) ]
      def inputBusChannels:      Int
      def hardwareBlockSize:     Int
      def zeroConf:              Boolean
      def maxLogins:             Int
      def sessionPassword:       Option[ String ]

      // nonrealtime only
      def nrtCommandPath:        String
      def nrtInputPath:          Option[ String ]
      def nrtOutputPath:         String
      def nrtHeaderFormat:       AudioFileType
      def nrtSampleFormat:       SampleFormat

      final def toRealtimeArgs    : List[ String ] = Config.toRealtimeArgs( this )
      final def toNonRealtimeArgs : List[ String ] = Config.toNonRealtimeArgs( this )

      final def firstPrivateBus: Int = outputBusChannels + inputBusChannels
   }

   object Config {
      /**
       * Creates a new configuration builder with default settings
       */
      def apply() : ConfigBuilder = new ConfigBuilder()

      /**
       * Implicit conversion which allows you to use a `ConfigBuilder`
       * wherever a `Config` is required.
       */
      implicit def build( cb: ConfigBuilder ) : Config = cb.build

      private[Server] def toNonRealtimeArgs( o: ConfigLike ): List[ String ] = {
       val result = new ListBuffer[ String ]()

       // -N <cmd-filename> <input-filename> <output-filename> <sample-rate> <header-format> <sample-format> <...other scsynth arguments>
       result += o.programPath
       result += "-N"
       result += o.nrtCommandPath
       result += o.nrtInputPath.getOrElse( "_" )
       result += o.nrtOutputPath
       result += o.sampleRate.toString
       result += o.nrtHeaderFormat.id
       result += o.nrtSampleFormat.id

       addCommonArgs( o, result )
       result.toList
    }

      private[Server] def toRealtimeArgs( o: ConfigLike ): List[ String ] = {
       val result = new ListBuffer[ String ]()

       result += o.programPath
       o.transport match {
          case TCP => {
             result += "-t"
             result += o.port.toString
          }
          case UDP => {
             result += "-u"
             result += o.port.toString
          }
       }

       addCommonArgs( o, result )

       if( o.hardwareBlockSize != 0 ) {
           result += "-Z"
           result += o.hardwareBlockSize.toString
       }
       if( o.sampleRate != 0 ) {
          result += "-S"
          result += o.sampleRate.toString
       }
       if( o.maxLogins != 64 ) {
          result += "-l"
          result += o.maxLogins.toString
       }
       o.sessionPassword.foreach( pwd => {
          result += "-p"
          result += pwd
       })
       o.inputStreamsEnabled.foreach( stream => {
          result += "-I"
          result += stream
       })
       o.outputStreamsEnabled.foreach( stream => {
          result += "-O"
          result += stream
       })
       if( !o.zeroConf ) {
          result += "-R"
          result += "0"
       }
       o.deviceNames.foreach( tup => {
          val (inDev, outDev) = tup
          result += "-H"
          result += tup._1
          result += tup._2
       })
       o.deviceName.foreach( n => {
          result += "-H"
          result += n
       })
       o.restrictedPath.foreach( path => {
          result += "-P"
          result += path
       })

       result.toList
    }

      private[Server] def addCommonArgs( o: ConfigLike, result: ListBuffer[ String ]) = {
       if( o.controlBusChannels != 4096 ) {
          result += "-c"
          result += o.controlBusChannels.toString
       }
       if( o.audioBusChannels != 128 ) {
          result += "-a"
          result += o.audioBusChannels.toString
       }
       if( o.inputBusChannels != 8 ) {
          result += "-i"
          result += o.inputBusChannels.toString
       }
       if( o.outputBusChannels != 8 ) {
          result += "-o"
          result += o.outputBusChannels.toString
       }
       if( o.blockSize != 64 ) {
           result += "-z"
           result += o.blockSize.toString
       }
       if( o.audioBuffers != 1024 ) {
          result += "-b"
          result += o.audioBuffers.toString
       }
       if( o.maxNodes != 1024 ) {
          result += "-n"
          result += o.maxNodes.toString
       }
       if( o.maxSynthDefs != 1024 ) {
          result += "-d"
          result += o.maxSynthDefs.toString
       }
       if( o.memorySize != 8192 ) {
          result += "-m"
          result += o.memorySize.toString
       }
       if( o.wireBuffers != 64 ) {
          result += "-w"
          result += o.wireBuffers.toString
       }
       if( o.randomSeeds != 64 ) {
          result += "-r"
          result += o.randomSeeds.toString
       }
       if( !o.loadSynthDefs ) {
          result += "-D"
          result += "0"
       }
       o.machPortName.foreach( tup => {
          result += "-M"
          result += tup._1
          result += tup._2
       })
       if( o.verbosity != 0 ) {
          result += "-v"
          result += o.verbosity.toString
       }
       if( o.plugInsPaths.nonEmpty ) {
          result += "-U"
          result += o.plugInsPaths.mkString( ":" )
       }
       if( o.memoryLocking ) {
          result += "-L"
       }
    }
   }
   final class Config private[Server]( val programPath: String, val controlBusChannels: Int, val audioBusChannels: Int,
                       val outputBusChannels: Int, val blockSize: Int, val sampleRate: Int, val audioBuffers: Int,
                       val maxNodes: Int, val maxSynthDefs: Int, val memorySize: Int, val wireBuffers: Int,
                       val randomSeeds: Int, val loadSynthDefs: Boolean, val machPortName: Option[ (String, String) ],
                       val verbosity: Int, val plugInsPaths: List[ String ], val restrictedPath: Option[ String ],
                       val memoryLocking: Boolean, val host: String, val port: Int, val transport: Transport.Net,
                       val inputStreamsEnabled: Option[ String ], val outputStreamsEnabled: Option[ String ],
                       val deviceNames: Option[ (String, String) ], val deviceName: Option[ String ],
                       val inputBusChannels: Int,
                       val hardwareBlockSize: Int, val zeroConf: Boolean, val maxLogins: Int,
                       val sessionPassword: Option[ String ], val nrtCommandPath: String,
                       val nrtInputPath: Option[ String ],
                       val nrtOutputPath: String, val nrtHeaderFormat: AudioFileType,
                       val nrtSampleFormat: SampleFormat )
   extends ConfigLike {
      override def toString = "ServerOptions"
   }

  final class ConfigBuilder private[Server] () extends ConfigLike {
     var programPath:           String                     = defaultProgramPath
     var controlBusChannels:    Int                        = 4096
     var audioBusChannels:      Int                        = 128
     var outputBusChannels:     Int                        = 8
     var blockSize:             Int                        = 64
     var sampleRate:            Int                        = 0
     var audioBuffers:          Int                        = 1024
     var maxNodes:              Int                        = 1024
     var maxSynthDefs:          Int                        = 1024
     var memorySize:            Int                        = 8192
     var wireBuffers:           Int                        = 64
     var randomSeeds:           Int                        = 64
     var loadSynthDefs:         Boolean                    = true
     var machPortName:          Option[ (String, String) ] = None
     var verbosity:             Int                        = 0
     var plugInsPaths:          List[ String ]             = Nil
     var restrictedPath:        Option[ String ]           = None
     var memoryLocking:         Boolean                    = false

//   // client only
//   var clientID:              Int                        = 0
//   var nodeIDOffset:          Int                        = 1000

     // realtime only
     var host:                  String                     = "127.0.0.1"
     var port:                  Int                        = 57110
     var transport:             Transport.Net              = UDP
     var inputStreamsEnabled:   Option[ String ]           = None
     var outputStreamsEnabled:  Option[ String ]           = None

     private var deviceNameVar:  Option[ String ] = None
     private var deviceNamesVar: Option[ (String, String) ] = None

     def deviceName:            Option[ String ]           = deviceNameVar
     def deviceNames:           Option[ (String, String) ] = deviceNamesVar
     def deviceName_=( value: Option[ String ]) {
        deviceNameVar = value
        if( value.isDefined ) deviceNamesVar = None
     }
     def deviceNames_=( value: Option[ (String, String) ]) {
        deviceNamesVar = value
        if( value.isDefined ) deviceNameVar = None
     }

     var inputBusChannels:      Int                        = 8
     var hardwareBlockSize:     Int                        = 0
     var zeroConf:              Boolean                    = true
     var maxLogins:             Int                        = 64
     var sessionPassword:       Option[ String ]           = None

     // nonrealtime only
     var nrtCommandPath:        String                     = ""
     var nrtInputPath:          Option[ String ]           = None
     var nrtOutputPath:         String                     = ""
     var nrtHeaderFormat:       AudioFileType              = AudioFileType.AIFF
     var nrtSampleFormat:       SampleFormat               = SampleFormat.Float

     /**
      * Picks and assigns a random free port for the server. This implies that
      * the server will be running on the local machine.
      *
      * As a result, this method will change this config builder's `port` value.
      * The caller must ensure that the `host` and `transport` fields have been
      * decided on before calling this method. Later changes of either of these
      * will render the result invalid.
      *
      * This method will fail with runtime exception if the host is not local.
      */
     def pickPort() {
        require( isLocal )
        transport match {
           case UDP =>
              val tmp = new DatagramSocket()
              port = tmp.getLocalPort
              tmp.close()
           case TCP =>
              val tmp = new ServerSocket( 0 )
              port = tmp.getLocalPort
              tmp.close()
        }
     }

     /**
      * Checks if the currently set `host` is located on the local machine.
      */
     def isLocal : Boolean = {
        val hostAddr = InetAddress.getByName( host )
        hostAddr.isLoopbackAddress || hostAddr.isSiteLocalAddress
     }

     def build : Config = new Config(
        programPath, controlBusChannels, audioBusChannels, outputBusChannels, blockSize, sampleRate, audioBuffers,
        maxNodes, maxSynthDefs, memorySize, wireBuffers, randomSeeds, loadSynthDefs, machPortName, verbosity,
        plugInsPaths, restrictedPath, memoryLocking, host, port, transport, inputStreamsEnabled, outputStreamsEnabled,
        deviceNames, deviceName, inputBusChannels, hardwareBlockSize, zeroConf, maxLogins, sessionPassword,
        nrtCommandPath,
        nrtInputPath, nrtOutputPath, nrtHeaderFormat, nrtSampleFormat )
  }

   @throws( classOf[ IOException ])
   def boot: ServerConnection = boot()()

   @throws( classOf[ IOException ])
   def boot( name: String = "localhost", config: Config = Config().build,
             clientConfig: Client.Config = Client.Config().build )
           ( listener: Model.Listener = Model.EmptyListener ) : ServerConnection = {
      val sc = initBoot( name, config, clientConfig )
      if( !(listener eq Model.EmptyListener) ) sc.addListener( listener )
      sc.start()
      sc
   }

   private def initBoot( name: String = "localhost", config: Config = Config().build,
             clientConfig: Client.Config = Client.Config().build ) = {
      val (addr, c) = prepareConnection( config, clientConfig )
//c.dump()
      new BootingImpl( name, c, addr, config, clientConfig, true )
   }

   @throws( classOf[ IOException ])
   def connect: ServerConnection = connect()()

   @throws( classOf[ IOException ])
   def connect( name: String = "localhost", config: Config = Config().build,
                clientConfig: Client.Config = Client.Config().build )
              ( listener: Model.Listener = Model.EmptyListener ) : ServerConnection = {
      val (addr, c) = prepareConnection( config, clientConfig )
      val sc = new ConnectionImpl( name, c, addr, config, clientConfig, true )
      if( !(listener eq Model.EmptyListener) ) sc.addListener( listener )
      sc.start()
      sc
   }

   def run( code: Server => Unit ) { run()( code )}
   
   /**
    *    Utility method to test code quickly with a running server. This boots a
    *    server and executes the passed in code when the server is up. A shutdown
    *    hook is registered to make sure the server is destroyed when the VM exits.
    */
   def run( config: Config = Config().build )( code: Server => Unit ) {
//      val b = boot( config = config )
      val sync = new AnyRef
      var s : Server = null
      val sc = initBoot( config = config )
      sc.addListener {
         case ServerConnection.Running( srv ) => sync.synchronized { s = srv }; code( srv )
      }
      Runtime.getRuntime.addShutdownHook( new Thread { override def run() { sync.synchronized {
         if( s != null ) {
            if( s.condition != Server.Offline ) s.quit
         } else sc.abort
      }}})
      sc.start()
   }

   @throws( classOf[ IOException ])
   def dummy: Server = dummy()

   /**
    * Creates an unconnected server proxy. This may be usefull for creating NRT command files.
    * Any attempt to try to send messages to the server will fail.
    */
   @throws( classOf[ IOException ])
   def dummy( name: String = "dummy", config: Config = Config().build,
                clientConfig: Client.Config = Client.Config().build ) : Server = {
      val (addr, c) = prepareConnection( config, clientConfig )
      new Server( name, c, addr, config, clientConfig )
   }

   @throws( classOf[ IOException ])
   private def prepareConnection( config: Config, clientConfig: Client.Config ) : (InetSocketAddress, OSCClient) = {
      val addr = new InetSocketAddress( config.host, config.port )
      val clientAddr = clientConfig.addr getOrElse {
          if( addr.getAddress.isLoopbackAddress )
             new InetSocketAddress( "127.0.0.1", 0 ) else
             new InetSocketAddress( InetAddress.getLocalHost, 0 )
      }
      val c    = createClient( config.transport, addr, clientAddr )
      (addr, c)
   }
   
   def allocPort( transport: Transport ) : Int = {
      transport match {
         case TCP => {
            val ss = new ServerSocket( 0 )
            try {
               ss.getLocalPort
            } finally {
               ss.close()
            }
         }
         case UDP => {
            val ds = new DatagramSocket()
            try {
               ds.getLocalPort
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

   implicit def defaultGroup( s: Server ) = s.defaultGroup

   abstract sealed class Condition
   case object Running extends Condition
//   case object Booting extends Condition
   case object Offline extends Condition
//   private case object Terminating extends Condition
   private case object NoPending extends Condition

   case class Counts( c: osc.StatusReplyMessage )

   private def createClient( transport: Transport.Net, serverAddr: InetSocketAddress,
                             clientAddr: InetSocketAddress ) : OSCClient = {
//      val client        = OSCClient( transport, 0, addr.getAddress.isLoopbackAddress, osc.ServerCodec )
//println( "transport = " + transport + " ; server = " + serverAddr + " ; client = " + clientAddr )
      val client        = transport match {
         case UDP =>
            val cfg                 = UDP.Config()
            cfg.localSocketAddress  = clientAddr
            cfg.codec               = osc.ServerCodec
            cfg.bufferSize          = 0x10000
            UDP.Client( serverAddr, cfg )
         case TCP =>
            val cfg                 = TCP.Config()
            cfg.codec               = osc.ServerCodec
            cfg.localSocketAddress  = clientAddr
            cfg.bufferSize          = 0x10000
            TCP.Client( serverAddr, cfg )
      }
//      client.connect()
      client
   }

   // -------- internal class BootThread --------

   private object ConnectionImplLike {
      case object Ready
      case object Abort
      case object QueryServer
      final case class AddListener( l: Model.Listener )
      final case class RemoveListener( l: Model.Listener )
//      case object Aborted
   }

   // XXX TODO : CLEAN UP THIS MESS
   private trait ConnectionImplLike extends ServerConnection {
      import ConnectionImplLike._
      import ServerConnection.{ Running => SCRunning, _ }

      val actor = new DaemonActor {
//         var state: Condition = Connecting
         def act() { react {
//            dispatch( Connecting )
            case Abort => abortHandler( None )
            case Ready => loop {
               if( connectionAlive ) {
//                  def retryConnect() {
//                     val tretry  = System.currentTimeMillis + 500
//                     var looping = true
//                     loopWhile( looping ) { reactWithin( math.max( 0L, tretry - System.currentTimeMillis) ) {
//                        case TIMEOUT            => looping = false
//                        case AddListener( l )   => actAddList( l )
//                        case RemoveListener( l )=> actRemoveList( l )
//                        case Abort              => abortHandler( None )
//                     }}
//                  }
//                  try {
//println( "?? Connect")
//                     c.start
//println( "isConnected? " + c.isConnected + " ; isOpen? " + c.isOpen )
                     if( !c.isConnected ) c.connect()
//println( "!! Connect")
//c.dumpOSC()
                     c.action = p => this ! p
                     var tnotify = 0L
                     def snotify() {
                        tnotify = System.currentTimeMillis + 500
//println( ">>> NOT" )
//try {
                        c ! osc.ServerNotifyMessage( true )
//} catch {
//   case n: PortUnreachableException => println( "caught : " + n )
//}
                     }
                     snotify()
                     loop { reactWithin( math.max( 0L, tnotify - System.currentTimeMillis) ) {
                        case TIMEOUT            => snotify() // loop is retried
                        case AddListener( l )   => actAddList( l )
                        case RemoveListener( l )=> actRemoveList( l )
                        case Abort              => abortHandler( None )
                        case Message( "/done", "/notify" ) => {
//println( "<<< NOT" )
                           var tstatus = 0L
                           def sstatus() {
                              tstatus = System.currentTimeMillis + 500
//println( ">>> STAT" )
//try {
   c ! osc.StatusMessage
//} catch {
//   case n: PortUnreachableException => println( "caught : " + n )
//}
                           }
                           sstatus()
                           loop { reactWithin( math.max( 0L, tstatus - System.currentTimeMillis) ) {
                              case TIMEOUT            => sstatus() // loop is retried
                              case AddListener( l )   => actAddList( l )
                              case RemoveListener( l )=> actRemoveList( l )
                              case Abort              => abortHandler( None )
                              case counts: osc.StatusReplyMessage => {
//println( "<<< STAT" )
                                 val s = new Server( name, c, addr, config, clientConfig )
                                 s.counts = counts
                                 dispatch( Preparing( s ))
                                 s.initTree()
                                 dispatch( SCRunning( s ))
                                 createAliveThread( s )
                                 loop { react {
                                    case QueryServer        => reply( s )
                                    case AddListener( l )   => actAddList( l ); actDispatch( l, SCRunning( s ))
                                    case RemoveListener( l )=> actRemoveList( l )
                                    case Abort              => abortHandler( Some( s ))
                                    case ServerConnection.Aborted => {
                                       s.serverOffline()
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
//                  }
//                  catch {
//                     case _: ConnectException => retryConnect()   // thrown when TCP server not available
//                     case _: PortUnreachableException => retryConnect() // thrown when server sets up UDP
//                     case e: ClosedChannelException =>
//println( "CAUGHT:" )
//e.printStackTrace( Console.out )
//println( "IS OPEN? " + c.isOpen() )
//                        retryConnect() // thrown when in TCP mode and socket not yet available
////println( "!= Connect")
//                  }
               } else loop { react {
                  case Abort  => reply ()
                  case _      =>
               }}
            }
         }}

         private def abortHandler( server: Option[ Server ]) {
            handleAbort()
            val from = sender
            loop { react {
               case ServerConnection.Aborted => {
                  server.foreach( _.serverOffline() )
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

      def handleAbort() : Unit
      def connectionAlive : Boolean
      def c : OSCClient
      def clientConfig: Client.Config
      def createAliveThread( s: Server ) : Unit
   }

   private class ConnectionImpl @throws( classOf[ IOException ])
      ( val name: String, val c: OSCClient, val addr: InetSocketAddress, val config: Config,
        val clientConfig: Client.Config, aliveThread: Boolean )
   extends ConnectionImplLike {
//      import ConnectionImplLike._

      def start() {
         actor.start()
      }

      override def toString = "connect<" + name + ">"

      def handleAbort() {}
      def connectionAlive = true // XXX could add a timeout?
      def createAliveThread( s: Server ) {
         if( aliveThread ) s.startAliveThread( 1.0f, 0.25f, 40 ) // allow for a luxury 10 seconds absence
      }
   }

//   private object BootingImpl {
//      final case class Booted( open: Boolean )
//   }
   private class BootingImpl @throws( classOf[ IOException ])
      ( val name: String, val c: OSCClient, val addr: InetSocketAddress, val config: Config,
        val clientConfig: Client.Config, aliveThread: Boolean )
   extends ConnectionImplLike {
      import ConnectionImplLike._

      lazy val p = {
         val processArgs   = config.toRealtimeArgs
         val directory     = new File( config.programPath ).getParentFile
         val pb            = new ProcessBuilder( processArgs: _* )
            .directory( directory )
            .redirectErrorStream( true )
         pb.start    // throws IOException if command not found or not executable
      }

      lazy val processThread = new Thread {
         override def run() { try {
            p.waitFor()
         } catch { case e: InterruptedException =>
            p.destroy()
         } finally {
            println( "scsynth terminated (" + p.exitValue +")" )
            actor ! ServerConnection.Aborted
         }}
      }

//      @volatile private var isOpen      = false
//      @volatile private var isBooting   = false
//
      def start() {
         val inReader   = new BufferedReader( new InputStreamReader( p.getInputStream ))
         val postThread = new Thread {
            override def run() {
               var isOpen         = true
               var isBooting      = true
               try {
                  while( isOpen && isBooting ) {
                     val line = inReader.readLine
                     isOpen = line != null
                     if( isOpen ) {
                        println( line )
// of course some sucker screwed it up and added another period in SC 3.4.4
//                        if( line == "SuperCollider 3 server ready." ) isBooting = false
// one more... this should allow for debug versions and supernova to be detected, too
if( line.startsWith( "Super" ) && line.contains( " ready" )) isBooting = false
                     }
                  }
               } catch {
                  case e => isOpen = false
               }
               actor ! (if( isOpen ) Ready else Abort)
               while( isOpen ) {
                  val line = inReader.readLine
                  isOpen = line != null
                  if( isOpen ) println( line )
               }
            }
         }

         // ...and go
         postThread.start()
         processThread.start()
         actor.start()
      }

      override def toString = "boot<" + name + ">"

      def handleAbort() { processThread.interrupt() }
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

sealed trait ServerLike extends Model {
   def name: String
   def config: Server.Config
   def addr: InetSocketAddress
}

object ServerConnection {
   sealed abstract class Condition
//   case object Connecting extends Condition
   case class Preparing( server: Server ) extends Condition
   case class Running( server: Server ) extends Condition
   case object Aborted extends Condition
}
sealed trait ServerConnection extends ServerLike {
//   def start : Unit
   def server : Future[ Server ]
   def abort : Future[ Unit ]
}

//abstract class Server extends Model {}
final class Server private( val name: String, c: OSCClient, val addr: InetSocketAddress, val config: Server.Config,
                      val clientConfig: Client.Config )
extends ServerLike {
   server =>

   import Server._

   private var aliveThread: Option[StatusWatcher]	= None
//   private var bootThread: Option[BootThread]		= None
   private var countsVar							      = new osc.StatusReplyMessage( 0, 0, 0, 0, 0f, 0f, 0.0, 0.0 )
//   private var collBootCompletion					   = Queue.empty[ (Server) => Unit ]
   private val condSync                            = new AnyRef
   private var conditionVar: Condition 			   = Running // Offline
   private var pendingCondition: Condition      	= NoPending
//   private var bufferAllocatorVar: ContiguousBlockAllocator = null
//   private val host                                = InetAddress.getByName( config.host.value )

//   val addr                                        = new InetSocketAddress( host, config.port.value )
   val rootNode                                    = new Group( this, 0 )
   val defaultGroup                                = new Group( this, 1 )
   val nodeMgr                                     = new NodeManager( this )
   val bufMgr                                      = new BufferManager( this )
//   var latency                                     = 0.2f

   // ---- constructor ----
   OSCReceiverActor.start()
   c.action = OSCReceiverActor.messageReceived
   add( server )

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
      private val allocator = new NodeIDAllocator( clientConfig.clientID, clientConfig.nodeIDOffset )

      def nextID = allocator.alloc
   }

   object busses {
      private val controlAllocator = new ContiguousBlockAllocator( config.controlBusChannels )
      private val audioAllocator = new ContiguousBlockAllocator( config.audioBusChannels, config.firstPrivateBus )

      def allocControl( numChannels: Int ) = controlAllocator.alloc( numChannels )
      def allocAudio( numChannels: Int ) = audioAllocator.alloc( numChannels )
      def freeControl( index: Int ) { controlAllocator.free( index )}
      def freeAudio( index: Int ) { audioAllocator.free( index )}
   }

   object buffers {
      private val allocator = new ContiguousBlockAllocator( config.audioBuffers )

      def alloc( numChannels: Int ) = allocator.alloc( numChannels )
      def free( index: Int ) { allocator.free( index )}
   }

   private object uniqueID {
      private var id = 0
      def nextID = this.synchronized { val res = id; id += 1; res }
   }

   def !( p: Packet ) { c ! p }

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
   def !![ A ]( p: Packet, handler: PartialFunction[ Message, A ]) : RevocableFuture[ A ] = {
      val c    = new Channel[ A ]( Actor.self )
      val a = new FutureActor[ A ]( c ) {
         val sync    = new AnyRef
         var revoked = false
         var oh: Option[ osc.Handler ] = None

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
         def revoke() { sync.synchronized {
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
   def !?( timeOut: Long, p: Packet, handler: PartialFunction[ Any, Unit ]) {
      val a = new DaemonActor {
         def act() {
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
   private[synth] def counts_=( newCounts: osc.StatusReplyMessage ) {
      countsVar = newCounts
      dispatch( Counts( newCounts ))
   }

   def sampleRate = counts.sampleRate
  
   def dumpTree { dumpTree( false )}

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

   def startAliveThread( delay: Float = 0.25f, period: Float = 0.25f, deathBounces: Int = 25 ) {
      condSync.synchronized {
         if( aliveThread.isEmpty ) {
            val statusWatcher = new StatusWatcher( delay, period, deathBounces )
            aliveThread = Some( statusWatcher )
            statusWatcher.start()
         }
      }
   }

   def stopAliveThread() {
      condSync.synchronized {
         aliveThread.foreach( _.stop() )
         aliveThread = None
      }
  }

   def queryCounts() {
      this ! osc.StatusMessage
   }

   def syncMsg : osc.SyncMessage = syncMsg()
   def syncMsg( id: Int = uniqueID.nextID ) = osc.SyncMessage( id )

   def dumpOSC( mode: Dump = Dump.Text ) {
      c.dumpIn( mode, filter = {
         case m: osc.StatusReplyMessage => false
         case _ => true
      })
      c.dumpOut( mode, filter = {
         case osc.StatusMessage => false
         case _ => true
      })
   }

   private def serverLost() {
      nodeMgr.clear()
      bufMgr.clear()
      OSCReceiverActor.clear()
   }

   private def serverOffline() {
      condSync.synchronized {
//         bootThread = None
         stopAliveThread()
         condition = Offline
      }
   }

   def quit {
      this ! quitMsg
//      cleanUpAfterQuit()
      dispose
   }

   def quitMsg = osc.ServerQuitMessage

//   private def cleanUpAfterQuit() {
//      try {
//         condSync.synchronized {
//            stopAliveThread()
//            pendingCondition = Terminating
//         }
//      }
//      catch { case e: IOException => printError( "Server.cleanUpAfterQuit", e )}
//   }

   private[synth] def addResponder( resp: osc.Responder ) {
      OSCReceiverActor.addHandler( resp )
   }

   private[synth] def removeResponder( resp: osc.Responder ) {
      OSCReceiverActor.removeHandler( resp )
   }

   private[synth] def initTree() {
      nodeMgr.register( defaultGroup )
      server ! defaultGroup.newMsg( rootNode, addToHead )
   }

   def dispose {
      condSync.synchronized {
         serverOffline()
         remove( this )
//         c.dispose // = (msg: Message, sender: SocketAddress, time: Long) => ()
         c.close()
         OSCReceiverActor.dispose()
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
         timer.foreach( t => {
            t.cancel()
            timer = None
         })
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

      def statusReply( msg: osc.StatusReplyMessage ) {
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
      private case class  AddHandler( h: osc.Handler )
      private case class  RemoveHandler( h: osc.Handler )
      private case class  TimeOutHandler( h: OSCTimeOutHandler )

      def clear() {
         this ! Clear
      }

      def dispose() {
         clear()
         this ! Dispose
      }

      def addHandler( handler: osc.Handler ) {
         this ! AddHandler( handler )
      }

      def removeHandler( handler: osc.Handler ) {
         this ! RemoveHandler( handler )
      }

      def timeOutHandler( handler: OSCTimeOutHandler ) {
         this ! TimeOutHandler( handler )
      }

      // ------------ OSCListener interface ------------

      def messageReceived( p: Packet ) {
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : ! : " + msg )
         this ! p
      }

      def act() {
         var running    = true
         var handlers   = Set.empty[ osc.Handler ]
//         while( running )( receive { })
         loopWhile( running )( react {
            case msg: Message => debug( msg ) {
//            case ReceivedMessage( msg, sender, time ) => debug( msg ) {
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : received : " + msg )
               msg match {
                  case nodeMsg:        osc.NodeChange           => nodeMgr.nodeChange( nodeMsg )
                  case bufInfoMsg:     osc.BufferInfoMessage    => bufMgr.bufferInfo( bufInfoMsg )
                  case statusReplyMsg: osc.StatusReplyMessage   => aliveThread.foreach( _.statusReply( statusReplyMsg ))
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
         case e => println( "" + new java.util.Date() + " OOOPS : msg " + msg + " produced " + e )
      }
      val t2 = System.currentTimeMillis
      if( (t2 - t1) > 2000 ) println( "" + new java.util.Date() + " WOW this took long (" + (t2-t1) + "): " + msg )
   }

   // -------- internal osc.Handler implementations --------

   private class OSCInfHandler[ A ]( fun: PartialFunction[ Message, A ], ch: OutputChannel[ A ])
   extends osc.Handler {
      def handle( msg: Message ) : Boolean = {
         val handled = fun.isDefinedAt( msg )
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : inf handled : " + msg + " ? " + handled )
         if( handled ) try {
            ch ! fun.apply( msg )
         } catch { case e => e.printStackTrace() }
         handled
      }
      def removed() {}
   }

   private class OSCTimeOutHandler( fun: PartialFunction[ Any, Unit ], ch: OutputChannel[ Any ])
   extends osc.Handler {
      def handle( msg: Message ) : Boolean = {
         val handled = fun.isDefinedAt( msg )
//if( msg.name == "/synced" ) println( "" + new java.aux.Date() + " : to handled : " + msg + " ? " + handled )
         if( handled ) try {
            ch ! fun.apply( msg )
         } catch { case e => e.printStackTrace() }
         handled
      }
      def removed() {}
      def timedOut() {
         if( fun.isDefinedAt( TIMEOUT )) try {
            fun.apply( TIMEOUT )
         } catch { case e => e.printStackTrace() }
      }
   }
}