package de.sciss.synth
package ugen

import collection.breakOut
import collection.immutable.{IndexedSeq => IIdxSeq}

final case class Zip( elems: GE* ) extends GE.Lazy {
def numOutputs = elems.minBy( _.numOutputs ).numOutputs
   def rate = MaybeRate.reduce( elems.map( _.rate ): _* )

   def displayName = "Zip"
   override def toString = displayName + elems.mkString( "(", ", ", " )" )
   def makeUGens : UGenInLike = {
      val exp     = elems.map( _.expand.outputs )
      val minSz   = exp.minBy( _.size ).size
      UGenInGroup( IIdxSeq.tabulate( minSz )( i => UGenInGroup( exp.map( _.apply( i ))( breakOut ))))
   }
}