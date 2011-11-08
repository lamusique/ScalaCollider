package de.sciss.synth
package ugen

import collection.immutable.{ IndexedSeq => IIdxSeq }

/**
 * A simple graph element that takes a function and upon UGen expansion
 * applies the multi-channel expanded version of the input argument to that function.
 *
 * Note: This may cause problems in future projects involving serialization of
 * synth graphs, due to the generic nature of the `fun` argument.
 */
final case class MapExpanded( in: GE )( fun: IIdxSeq[ GE ] => GE ) extends GE.Lazy {
   def rate = UndefinedRate
   def displayName = "MapExpanded"

   protected def makeUGens : UGenInLike = {
      val _in = in.expand
      val res = fun( _in.outputs )
      res.expand
   }
}
