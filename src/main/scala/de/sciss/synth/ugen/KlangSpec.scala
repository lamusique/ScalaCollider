package de.sciss.synth
package ugen

import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._

object KlangSpec {
   def fill( n: Int )( thunk: => (AnyGE, AnyGE, AnyGE) ) : Seq = {
      new SeqImpl( IIdxSeq.fill[ (AnyGE, AnyGE, AnyGE) ]( n )( thunk ).map( (tup) => KlangSpec( tup._1, tup._2, tup._3 )))
   }

   def tabulate( n: Int )( func: (Int) => (AnyGE, AnyGE, AnyGE) ) : Seq = {
      new SeqImpl( IIdxSeq.tabulate[ (AnyGE, AnyGE, AnyGE) ]( n )( func ).map( (tup) => KlangSpec( tup._1, tup._2, tup._3 )))
   }

//   implicit def toGE( spec: KlangSpec ) : Multi[ AnyGE ] = IIdxSeq( spec.freq, spec.amp, spec.decay ) : AnyGE
//   implicit def toGE( spec: KlangSpec ) : Multi[ AnyGE ] = new SeqImpl( IIdxSeq( spec ))

   private class SeqImpl( val elems: IIdxSeq[ KlangSpec ]) extends Seq {
      def mexpand: IIdxSeq[ AnyGE ] = {
//       we want something like this:
//         (f1, a1, d1) -- (f2, a2, d2) -- (f3, a3, d3)
//         (f1', a1', d1') -- (f2', a2', d2') -- (f3', a3', d3')
         val _elems     = elems.map( _.mexpand )
         val _sz_elems  = _elems.map( _.size )
         val _exp_      = maxInt( _sz_elems: _* )
         IIdxSeq.tabulate( _exp_ )( i => GE.fromSeq[ Rate ]( _elems.map( mspec => mspec( i % mspec.size ))))
      }
   }

   object Seq {
//      implicit def toIndexedSeq[ R <: Rate ]( g: Seq[ R ]) : IIdxSeq[ GE[ R ]] = g.elems
//      def apply[ R <: Rate ]( elems: IIdxSeq[ GE[ R ]]) : Seq[ R ] = new SeqImpl( elems )
   }
   sealed trait Seq extends Multi[ AnyGE ] {
//      def expand : IIdxSeq[ UGenIn ] = elems.flatMap( _.expand )
//      def elems : IIdxSeq[ KlangSpec ]
   }
}
case class KlangSpec( freq: AnyGE, amp: AnyGE = 1, decay: AnyGE = 0 ) extends Multi[ AnyGE ] {
//   def toSeq: IIdxSeq[ GE ] = Vector( freq, amp, decay )
   def mexpand: IIdxSeq[ AnyGE ] = {
      val _freq      = freq.expand
      val _amp       = amp.expand
      val _decay     = decay.expand
      val _sz_freq   = _freq.size
      val _sz_amp    = _amp.size
      val _sz_decay  = _decay.size
      val _exp_      = maxInt(_sz_freq, _sz_amp, _sz_decay)
      IIdxSeq.tabulate(_exp_)(i => GE.fromUGenIns(IIdxSeq( _freq(i.%(_sz_freq)), _amp(i.%(_sz_amp)), _decay(i.%(_sz_decay)))))
   }
}
