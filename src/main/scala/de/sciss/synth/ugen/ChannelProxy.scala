package de.sciss.synth
package ugen

final case class ChannelProxy( elem: GE, index: Int ) extends GE.Lazy {
   def rate = elem.rate

   def displayName = "ChannelProxy"
   override def toString = elem.toString + ".\\(" + index + ")"

   def makeUGens : UGenInLike = {
      val _elem   = elem.expand
      _elem.unwrap( index )
   }
}