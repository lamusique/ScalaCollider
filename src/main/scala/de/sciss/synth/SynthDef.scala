/*
 *  SynthDef.scala
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

import java.io.{ ByteArrayOutputStream, BufferedOutputStream, DataOutputStream, File, FileOutputStream }
import java.nio.ByteBuffer
import de.sciss.synth.{ Completion => Comp }
import osc.{OSCSyncedMessage, OSCSynthDefFreeMessage, OSCSynthDefLoadMessage, OSCSynthDefRecvMessage}
import File.{ separator => sep }
import actors.TIMEOUT
import de.sciss.osc.{OSCBundle, OSCMessage, OSCPacket}

/**
 *    @version 0.19, 24-Aug-10
 *    @todo    should add load and loadDir to companion object
 */
case class SynthDef( name: String, graph: UGenGraph ) {
   syndef =>

   import SynthDef._

   override def toString = "SynthDef(" + name + ")"

   def freeMsg = OSCSynthDefFreeMessage( name )

   def recv : Unit = recv()
   def recv( server: Server = Server.default, completion: Completion = NoCompletion ) {
      sendWithAction( server, recvMsg( _ ), completion, "recv" )
      this
   }

   private def sendWithAction( server: Server, msgFun: Option[ OSCPacket ] => OSCMessage, completion: Completion,
                               name: String ) {
      completion.action map { action =>
         val syncMsg    = server.syncMsg
         val syncID     = syncMsg.id
         val compPacket: OSCPacket = completion.message match {
            case Some( msgFun2 ) => OSCBundle( msgFun2( syndef ), syncMsg )
            case None            => syncMsg
         }
         server !? (6000L, msgFun( Some( compPacket )), { // XXX timeout kind of arbitrary
            case OSCSyncedMessage( syncID ) => action( syndef )
            case TIMEOUT => println( "ERROR: " + syndef + "." + name + " : timeout!" )
         })
      } getOrElse {
         server ! msgFun( completion.message.map( _.apply( syndef )))
      }
   }

//   private def sendWithAction( msg: OSCMessage, syncID: Int, action: SynthDef => Unit, name: String ) {
//      server !? (6000L, msg, {
//         case OSCSyncedMessage( syncID ) => action( syndef )
//         case TIMEOUT => println( "ERROR: " + syndef + "." + name + " : timeout!" )
//      })
//   }
  
   def recvMsg: OSCSynthDefRecvMessage = recvMsg( None )
   def recvMsg( completion: Option[ OSCPacket ]) = OSCSynthDefRecvMessage( toBytes, completion )
  
  	def toBytes : ByteBuffer = {
    	val baos	= new ByteArrayOutputStream
    	val dos	= new DataOutputStream( baos )

    	dos.writeInt( 0x53436766 )	// magic cookie 'SCgf'
    	dos.writeInt( 1 )			   // version
    	dos.writeShort( 1 ) 		   // number of defs in file.
    	write( dos )
    	dos.flush
    	dos.close

    	ByteBuffer.wrap( baos.toByteArray ).asReadOnlyBuffer()
   }

   private def write( dos: DataOutputStream ) {
      writePascalString( dos, name )
      graph.write( dos )
   }

   def load : Unit = load()
   def load( server: Server = Server.default, dir: String = defaultDir,
             completion: Completion = NoCompletion ) {
      writeDefFile( dir )
      sendWithAction( server, loadMsg( dir, _ ), completion, "load" )
      this
   }
  
   def loadMsg : OSCSynthDefLoadMessage = loadMsg()
  
   def loadMsg( dir: String = defaultDir, completion: Option[ OSCPacket ] = None ) =
	   OSCSynthDefLoadMessage( dir + sep + name + ".scsyndef", completion )

   def play: Synth = play()
   def play( target: Node = Server.default, args: Seq[ ControlSetMap ] = Nil, addAction: AddAction = addToHead ) : Synth = {
      val synth   = new Synth( target.server )
		val newMsg  = synth.newMsg( name, target, args, addAction )
		target.server ! recvMsg( newMsg )
		synth
   }
    
   def writeDefFile : Unit = writeDefFile()
   def writeDefFile( dir: String = defaultDir, overwrite: Boolean = true ) {
      var file = new File( dir, name + ".scsyndef" )
      val exists = file.exists
      if( overwrite ) {
         if( exists ) file.delete
         SynthDef.writeDefFile( file.getAbsolutePath, List( this ))
      } else if( !exists ) {
         SynthDef.writeDefFile( file.getAbsolutePath, List( this ))
      }
   }
  
   @inline private def writePascalString( dos: DataOutputStream, str: String ) {
      dos.writeByte( str.size )
      dos.write( str.getBytes )
   }

   def hexDump {
      OSCPacket.printHexOn( Console.out, toBytes )
   }

   def testTopoSort {
      var i = 0
      graph.ugens.foreach( ru => {
         var j = 0
         ru.inputSpecs.foreach( spec => {
            if( (spec._1 >= 0) && (spec._1 <= i) ) {
               error( "Test failed : ugen " + i + " = " + ru.ugen + " -> input " + j + " = " + spec )
            }
            j += 1
         })
         i += 1
      })
      println( "Test succeeded." )
   }

   def debugDump {
      var i = 0
      graph.ugens.foreach( ru => {
         println( "#" + i + " : " + ru.ugen.name +
            (if( ru.ugen.specialIndex != 0 ) "-" + ru.ugen.specialIndex else "") + ru.inputSpecs.map({
               case (-1, idx)    => graph.constants( idx ).toString
               case (uidx, oidx) => { val ru = graph.ugens( uidx ); "#" + uidx + " : " + ru.ugen.name +
                  (if( oidx > 0 ) "@" + oidx else "") }
            }).mkString( "( ", ", ", " )" ))
         i += 1
      })
   }
}

object SynthDef {
   type Completion   = Comp[ SynthDef ]
   val NoCompletion  = Comp[ SynthDef ]( None, None )

   var defaultDir    = System.getProperty( "java.io.tmpdir" )

   def apply( name: String )( thunk: => Unit ) : SynthDef = SynthDef( name, SynthGraph( thunk ).expand )

   def recv( server: Server = Server.default, name: String, completion: Completion = NoCompletion )
           ( thunk: => Unit ) : SynthDef = {
      val d = apply( name )( thunk )
      d.recv( server, completion )
      d
   }

   def writeDefFile( path: String, defs: Seq[ SynthDef ]) {
      val os	= new FileOutputStream( path )
	   val dos	= new DataOutputStream( new BufferedOutputStream( os ))

      try {
         dos.writeInt( 0x53436766 ) 		// magic cookie
         dos.writeInt( 1 ) 				   // version
         dos.writeShort( defs.size ) 		// number of defs in file.
         defs.foreach( _.write( dos ))
      }
      finally {
         dos.close
      }
   }
}