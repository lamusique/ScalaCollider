/*
 *  GraphFunction.scala
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
 */

package de.sciss.synth
package aux

import ugen.WrapOut
import de.sciss.osc.{Bundle, Message}

object GraphFunction {
   private var uniqueIDCnt = 0
   private val uniqueSync = new AnyRef
   private def uniqueID = {
      uniqueSync.synchronized {
         uniqueIDCnt += 1
         val result = uniqueIDCnt
         result
      }
   }

   object Result {
      implicit val out = Out
      implicit def in[ T ]( implicit view: T => GE ) = In( view )
      case object Out extends Result[ UGenSource.ZeroOut ]
      final case class In[ T ]( view: T => GE ) extends Result[ T ]
   }
   sealed trait Result[ -T ]
}

final class GraphFunction[ T ]( thunk: => T )( implicit res: GraphFunction.Result[ T ]) {
   import GraphFunction._
   
   def play : Synth = play()
   def play( target: Node = Server.default.defaultGroup, outBus: Int = 0,
             fadeTime: Option[ Float ] = Some( 0.02f ),
             addAction: AddAction = addToHead ) : Synth = {

		val server = target.server
		val defName    = "temp_" + uniqueID // more clear than using hashCode
		val synthDef   = SynthDef( defName ) {
         val r = thunk
         res match {
            case Result.In( view ) => WrapOut( view( r ), fadeTime )
            case _ =>
         }
      }
		val synth      = new Synth( server )
		val bytes      = synthDef.toBytes
		val synthMsg   = synth.newMsg( synthDef.name, target, List( "i_out" -> outBus, "out" -> outBus ), addAction )
      val defFreeMsg = synthDef.freeMsg
      val compl      = Bundle.now( synthMsg, defFreeMsg )
//      synth.onEnd { server ! synthDef.freeMsg } // why would we want to accumulate the defs?
		if( bytes.remaining > (65535 / 4) ) { // "preliminary fix until full size works" (???)
			if( server.isLocal ) {
				synthDef.load( server, completion = compl )
			} else {
				println( "WARNING: synthdef may have been too large to send to remote server" )
				server ! Message( "/d_recv", bytes, compl )
			}
		} else {
			server ! Message( "/d_recv", bytes, compl )
		}
		synth
	}
}