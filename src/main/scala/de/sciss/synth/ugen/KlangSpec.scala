package de.sciss.synth
package ugen

import collection.immutable.{IndexedSeq => IIdxSeq}

object KlangSpec {
   def fill( n: Int )( thunk: => (AnyGE, AnyGE, AnyGE) ) : IIdxSeq[ KlangSpec ] = {
      IIdxSeq.fill[ (AnyGE, AnyGE, AnyGE) ]( n )( thunk ).map( (tup) => KlangSpec( tup._1, tup._2, tup._3 ))
   }

   def tabulate( n: Int )( func: (Int) => (AnyGE, AnyGE, AnyGE) ) : IIdxSeq[ KlangSpec ] = {
      IIdxSeq.tabulate[ (AnyGE, AnyGE, AnyGE) ]( n )( func ).map( (tup) => KlangSpec( tup._1, tup._2, tup._3 ))
   }

   implicit def toGE( spec: KlangSpec ) : Multi[ AnyGE ] = geSeqToGE( IIdxSeq( spec.freq, spec.amp, spec.decay ))
}
case class KlangSpec( freq: AnyGE, amp: AnyGE = 1, decay: AnyGE = 0 )
//{
//   def toSeq: IIdxSeq[ GE ] = Vector( freq, amp, decay )
//}
