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

import java.io.{InputStreamReader, BufferedReader, File, IOException}
import java.net.InetSocketAddress
import actors.DaemonActor
import de.sciss.osc.{Message, Client => OSCClient}

private[synth] object ConnectionLike {
   case object Ready
   case object Abort
   case object QueryServer
   final case class AddListener( l: Model.Listener )
   final case class RemoveListener( l: Model.Listener )
}

private[synth] sealed trait ConnectionLike extends ServerConnection {
   import ConnectionLike._
   import ServerConnection.{ Running => SCRunning, _ }

   val actor = new DaemonActor {
      def act() { react {
         case Abort => abortHandler( None )
         case Ready => loop {
            if( connectionAlive ) {
                  if( !c.isConnected ) c.connect()
                  c.action = p => this ! p
                  var tnotify = 0L
                  def snotify() {
                     tnotify = System.currentTimeMillis + 500
                     c ! osc.ServerNotifyMessage( onOff = true )
                  }
                  snotify()
                  loop { reactWithin( math.max( 0L, tnotify - System.currentTimeMillis) ) {
                     case actors.TIMEOUT     => snotify() // loop is retried
                     case AddListener( l )   => actAddList( l )
                     case RemoveListener( l )=> actRemoveList( l )
                     case Abort              => abortHandler( None )
                     case Message( "/done", "/notify" ) =>
                        var tstatus = 0L
                        def sstatus() {
                           tstatus = System.currentTimeMillis + 500
                           c ! osc.StatusMessage
                        }
                        sstatus()
                        loop { reactWithin( math.max( 0L, tstatus - System.currentTimeMillis) ) {
                           case actors.TIMEOUT     => sstatus() // loop is retried
                           case AddListener( l )   => actAddList( l )
                           case RemoveListener( l )=> actRemoveList( l )
                           case Abort              => abortHandler( None )
                           case cnt: osc.StatusReplyMessage =>
                              val s    = new ServerImpl( name, c, addr, config, clientConfig )
                              s.counts = cnt
                              dispatch( Preparing( s ))
                              s.initTree()
                              dispatch( SCRunning( s ))
                              createAliveThread( s )
                              loop { react {
                                 case QueryServer        => reply( s )
                                 case AddListener( l )   => actAddList( l ); actDispatch( l, SCRunning( s ))
                                 case RemoveListener( l )=> actRemoveList( l )
                                 case Abort              => abortHandler( Some( s ))
                                 case ServerConnection.Aborted =>
                                    s.serverOffline()
                                    dispatch( Aborted )
                                    loop { react {
                                       case AddListener( l )   => actAddList( l ); actDispatch( l, Aborted )
                                       case RemoveListener( l )=> actRemoveList( l )
                                       case Abort              => reply ()
                                       case QueryServer        => reply( s )
                                    }}
                              }}
                        }}
                  }}
            } else loop { react {
               case Abort  => reply ()
               case _      =>
            }}
         }
      }}

      private def abortHandler( server: Option[ ServerImpl ]) {
         handleAbort()
         val from = sender
         loop { react {
            case ServerConnection.Aborted =>
               server.foreach( _.serverOffline() )
               dispatch( ServerConnection.Aborted )
               from ! ()

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
         case e: Throwable => e.printStackTrace() // catch, but print
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

//   lazy val server : Future[ Server ] = actor !! (QueryServer, { case s: Server => s })
//   lazy val abort : Future[ Unit ] = actor !! (Abort, { case _ => ()})
   def abort() { actor ! Abort }

   def handleAbort() : Unit
   def connectionAlive : Boolean
   def c : OSCClient
   def clientConfig: Client.Config
   def createAliveThread( s: Server ) : Unit
}

private[synth] final class Connection @throws( classOf[ IOException ])
   ( val name: String, val c: OSCClient, val addr: InetSocketAddress, val config: Server.Config,
     val clientConfig: Client.Config, aliveThread: Boolean )
extends ConnectionLike {
//      import ConnectionLike._

   def start() {
      actor.start()
      actor ! ConnectionLike.Ready
   }

   override def toString = "connect<" + name + ">"

   def handleAbort() {}
   def connectionAlive = true // XXX could add a timeout?
   def createAliveThread( s: Server ) {
      if( aliveThread ) s.startAliveThread( 1.0f, 0.25f, 40 ) // allow for a luxury 10 seconds absence
   }
}

private[synth] final class Booting @throws( classOf[ IOException ])
   ( val name: String, val c: OSCClient, val addr: InetSocketAddress, val config: Server.Config,
     val clientConfig: Client.Config, aliveThread: Boolean )
extends ConnectionLike {
   import ConnectionLike._

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

   def start() {
      val inReader   = new BufferedReader( new InputStreamReader( p.getInputStream ))
      val postThread = new Thread {
         override def run() {
            var isOpen         = true
            var isBooting      = true
            try {
               while( isOpen && isBooting ) {
                  val line = inReader.readLine()
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
               case e: Throwable => isOpen = false
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
