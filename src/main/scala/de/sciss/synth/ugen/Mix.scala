/*
 *  Mix.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2011 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 *
 *
 *  Changelog:
 */

package de.sciss.synth
package ugen

import collection.immutable.{ IndexedSeq => IIdxSeq }

/**
*	@version	0.10, 09-Dec-09
*/
object Mix {
//   def apply[ R <: Rate ]( elems: Multi[ GE[ R, UGenIn[ R ]]])( implicit r: RateOrder[ R, R, R ]) : GE[ R, UGenIn[ R ]] =
//      elems.mexpand.reduceLeft( _ + _ )

//	def apply[ R <: Rate ]( in: GE[ R, UGenIn[ R ]])( implicit r: RateOrder[ R, R, R ]) : GE[ R, UGenIn[ R ]] = {
//      in.ex
//   }

   /**
    * A mixing idiom that corresponds to @Seq.tabulate@ and to @Array.fill@ in sclang.
    */
	def tabulate[ R <: Rate ]( n: Int )( fun: (Int) => Multi[ R, GE[ R, UGenIn ]])( implicit rate: R ) : Mix.Seq[ R ] =
      Mix.Seq( IIdxSeq.tabulate( n )( i => fun( i )))

//   def tabulate[ R <: Rate, G ]( n: Int )( fun: (Int) => G )( implicit rate: R, view: G => Multi[ R, GE[ R, UGenIn ]]) : Mix.Seq[ R ] =
//      Mix.Seq( IIdxSeq.tabulate( n )( i => view( fun( i ))))

   /**
    * A mixing idiom that corresponds to @Seq.fill@.
    */
   def fill[ R <: Rate ]( n: Int )( thunk: => Multi[ R, GE[ R, UGenIn /*[ R ]*/]])( implicit rate: R ) : Mix.Seq[ R ] =
      Mix.Seq( IIdxSeq.fill( n )( thunk ))

   /**
    * This is to implement explicit disjoint multi behavior so that ge's default
    * (joint) conversion to a multi is not effective.
    * Thus, any GE mixes down to a mono signal. Multi-output signals use the
    * other @apply@ method and expand to their original number of channels.
    */
   def apply[ R <: Rate ]( elem: GE[ R, UGenIn /*[ R ]*/])( implicit rate: R ) : Mix[ R ] = Mix( Multi.Disjoint( elem ))

   /**
    * This is an alias for the @apply@ method using a @GE@ input. This can be used to feed in multi-output
    * signals which would, using the @apply@ method be not split and summed.
    */
   def mono[ R <: Rate ]( elem: GE[ R, UGenIn /*[ R ]*/])( implicit rate: R ) : Mix[ R ] = apply( elem )

   def seq[ R <: Rate ]( elems: IIdxSeq[ Multi[ R, GE[ R, UGenIn /*[ R ]*/]]])( implicit rate: R ) = Seq( elems )

   case class Seq[ R <: Rate ]( elems: IIdxSeq[ Multi[ R, GE[ R, UGenIn /*[ R ]*/]]])( implicit val rate: R ) /*( implicit r: RateOrder[ R, R, R ])*/
   extends LazyExpander[ UGenIn ] with GE[ R, UGenIn /*[ R ]*/] {
//      def rate = Rate.highest( elems.map( _.rate ): _* ) // r.out

//      def force( b: UGenGraphBuilder ) { expand( b )}
//      def expand: IIdxSeq[ UGenIn /*[ R ]*/] = {
//         expand( UGenGraph.builder )
//      }
//      private def expand( b: UGenGraphBuilder ): IIdxSeq[ UGenIn /*[ R ]*/] = b.visit( this /* cache */, expandUGens )
      protected def expandUGens : IIdxSeq[ UGenIn /*[ R ]*/] = {
         val seq  = elems.map( sum( _ ))
         if( seq.isEmpty ) return IIdxSeq.empty
         val szs  = seq.map( _.size )
         val zip  = seq.zip( szs )
         val sz   = UGenHelper.maxInt( szs: _* )
         IIdxSeq.tabulate( sz )( i => zip.map( tup => tup._1.apply( i % tup._2 ))
            .reduceLeft( (a, b) => BinaryOp.Plus.make1( rate, a, b )))
      }
   }

   private def sum[ R <: Rate ]( elems: Multi[ R, GE[ R, UGenIn /*[ R ]*/]])/*( implicit r: RateOrder[ R, R, R ])*/ : IIdxSeq[ UGenIn /*[ R ]*/] = {
      val _elems     = elems.mexpand
      val _sz_elems  = _elems.size
      implicit val r = RateOrder.same( elems.rate )
      if( _sz_elems > 0 ) _elems.reduceLeft( _ + _ ).expand else IIdxSeq.empty
   }
}

/**
* Mixes the channels of a signal together. Note that, different from sclang, a multi-output UGen whose
* inputs are not expanded, will come out with its original number-of-channels, and is not mixed down
* to a mono signal (indicated below with an ! exclamation mark). If you want to enforce a mono mix of
* a multi-output UGen, you can use the @Mix.mono@ call instead.
*
* Here are some examples:
*
* {{{
* Mix( SinOsc.ar( 440 :: 660 :: Nil )) --> Line.ar( 440 ) + Line.ar( 660 )
* Mix( SinOsc.ar( 440 )) --> Line.ar( 440 )
* Mix( Pan2.ar( SinOsc.ar )) --> Pan2.ar( SinOsc.ar ) // !!
* Mix.mono( Pan2.ar( SinOsc.ar )) --> Pan2.ar( SinOsc.ar ) --> left + right // !!
* Mix( Pan2.ar( SinOsc.ar :: Saw.ar :: Nil )) --> Pan2.ar( SinOsc.ar ) + Pan2.ar( Saw.ar )
* }}}
*/
case class Mix[ R <: Rate ]( elems: Multi[ R, GE[ R, UGenIn /*[ R ]*/]])/*( implicit r: RateOrder[ R, R, R ])*/
extends LazyExpander[ UGenIn ] with GE[ R, UGenIn /*[ R ]*/] {
   import Mix._

//   def rate = r.out
   def rate = elems.rate

//   def force( b: UGenGraphBuilder ) { expand( b )}
//   def expand: IIdxSeq[ UGenIn /*[ R ]*/] = {
//      expand( UGenGraph.builder )
//   }
//   private def expand( b: UGenGraphBuilder ): IIdxSeq[ UGenIn /*[ R ]*/] = b.visit( this /* cache */, expandUGens )
   protected def expandUGens : IIdxSeq[ UGenIn /*[ R ]*/] = sum( elems )
}
