package de.sciss.synth
package ugen

import collection.immutable.{IndexedSeq => IIdxSeq}

final case class Flatten( elem: GE )
extends GE.Lazy {
def numOutputs = sys.error( "TODO" )
   def rate = elem.rate

   def displayName = "Flatten"
   override def toString = elem.toString + ".flatten"

   def makeUGens : UGenInLike = UGenInGroup( elem.expand.flatOutputs )
}