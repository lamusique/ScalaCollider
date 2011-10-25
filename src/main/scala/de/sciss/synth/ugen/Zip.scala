package de.sciss.synth
package ugen

import collection.breakOut
import collection.immutable.{IndexedSeq => IIdxSeq}

final case class Zip( elems: GE* ) extends GE.Lazy {
//def numOutputs = elems.minBy( _.numOutputs ).numOutputs
   def rate = MaybeRate.reduce( elems.map( _.rate ): _* )

   def displayName = "Zip"
   override def toString = displayName + elems.mkString( "(", ",", " )" )
   def makeUGens : UGenInLike = {
      val exp: IIdxSeq[ UGenInLike ] = elems.map( _.expand )( breakOut )
      val sz      = exp.map( _.outputs.size )   // exp.view.map ?
      val minSz   = sz.min
//      val res = UGenInGroup( IIdxSeq.tabulate( minSz )( i => UGenInGroup( exp.map( _.apply( i ))( breakOut ))))
      /* val res = */ UGenInGroup( (0 until minSz).flatMap( i => exp.map( _.unwrap( i ))))
//println( "Zip.expand = " + res )
//      res
   }
}