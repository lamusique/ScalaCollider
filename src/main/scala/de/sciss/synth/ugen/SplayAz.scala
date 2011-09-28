package de.sciss.synth
package ugen

import collection.immutable.{IndexedSeq => IIdxSeq}

object SplayAz {
   def ar( numChannels: Int, in: GE, spread: GE = 1f, center: GE = 0f, level: GE = 1f, width: GE = 2f, orient: GE = 0f ) =
      apply( audio, numChannels, in, spread, center, level, width, orient )
}
final case class SplayAz( rate: Rate, numChannels: Int, in: GE, spread: GE, center: GE, level: GE, width: GE, orient: GE )
extends GE.Lazy { // UGenSource.MultiOut( "SplayAz", numChannels )
   def numOutputs = numChannels
   def displayName = "SplayAz"

//   protected def makeUGens : UGenInLike = {
//      val n    = in.numOutputs
//      val pf   = (0.5f * spread) / n
//      val pos  = Seq.tabulate( n )( _ * pf + center )
//      val mix  = Mix( PanAz( rate, numChannels, in, pos, level, width, orient ))
//      mix.expand
//   }

   protected def makeUGens : UGenInLike = {
      val _in  = in.expand
      val n    = _in.outputs.size
//println( "n = " + n + "; _in.numOutputs = " + _in.numOutputs )
//      val n    = in.numOutputs
      val pf   = (0.5f * spread) / n
      val pos  = Seq.tabulate( n )( _ * pf + center )
      val mix  = Mix( PanAz( rate, numChannels, _in, pos, level, width, orient ))
      mix.expand
   }
}