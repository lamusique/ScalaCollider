package de.sciss.synth.osc

import de.sciss.osc.Message
import de.sciss.synth.Server

trait OSCHandler {
   private[synth] def handle( msg: Message ) : Boolean
   private[synth] def removed : Unit
//   private[synth] def timedOut : Unit
}

object OSCResponder {
   def add( handler: PartialFunction[ Message, Unit ], server: Server = Server.default ) : OSCResponder =
      new Impl( server, handler, false ).add

   def once( handler: PartialFunction[ Message, Unit ], server: Server = Server.default ) : OSCResponder =
      new Impl( server, handler, true ).add

   def apply( handler: PartialFunction[ Message, Unit ], server: Server = Server.default ): OSCResponder =
      new Impl( server, handler, false )

   private class Impl( val server: Server, handler: PartialFunction[ Message, Unit ], once: Boolean )
   extends OSCResponder {
      def add     = { server.addResponder( this ); this }
      def remove  = { server.removeResponder( this ); this }

      private[synth] def handle( msg: Message ) : Boolean = {
         val handled = handler.isDefinedAt( msg )
         if( handled ) try {
            handler( msg )
         } catch { case e => e.printStackTrace() }
         once
      }

      private[synth] def removed {}
//      private[synth] def timedOut {}
   }
}

trait OSCResponder extends OSCHandler {
   def server: Server
   def add : OSCResponder
   def remove : OSCResponder
}