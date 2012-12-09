/*
 *  Responder.scala
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
package osc

import de.sciss.osc.Message

trait Handler {
   // returns `true` if the handler wishes to be removed
   private[synth] def handle( msg: Message ) : Boolean
   private[synth] def removed() : Unit
//   private[synth] def timedOut : Unit
}

object Responder {
   def add( server: Server = Server.default )( handler: PartialFunction[ Message, Unit ]) : Responder =
      new Impl( server, handler, false ).add()

   def once( server: Server = Server.default )( handler: PartialFunction[ Message, Unit ]) : Responder =
      new Impl( server, handler, true ).add()

   def apply( server: Server = Server.default )( handler: PartialFunction[ Message, Unit ]): Responder =
      new Impl( server, handler, false )

   private final class Impl( val server: Server, handler: PartialFunction[ Message, Unit ], once: Boolean )
   extends Responder {
      def add()      = { server.addResponder( this ); this }
      def remove()   = { server.removeResponder( this ); this }

      private[synth] def handle( msg: Message ) : Boolean = {
         val handled = handler.isDefinedAt( msg )
         if( handled ) try {
            handler( msg )
         } catch { case e: Throwable => e.printStackTrace() }
         once && handled
      }

      private[synth] def removed() {}
//      private[synth] def timedOut {}

      override def toString = "Responder(" + server + (if( once ) ", once = true" else "") + ")@" + hashCode().toHexString
   }
}

trait Responder extends Handler {
   def server: Server
   def add() : Responder
   def remove() : Responder
}