package de.sciss.synth
package ugen

final case class Flatten( elem: GE ) extends GE.Lazy {
   def rate = elem.rate

   def displayName = "Flatten"
   override def toString = elem.toString + ".flatten"

   def makeUGens : UGenInLike = UGenInGroup( elem.expand.flatOutputs )
}