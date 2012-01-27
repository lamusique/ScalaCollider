/*
 *  HelperElements.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2012 Hanns Holger Rutz. All rights reserved.
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
 */

package de.sciss.synth
package ugen

import collection.breakOut
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.{UGenHelper, Optional}

final case class Flatten( elem: GE ) extends GE.Lazy {
   def rate = elem.rate

   def displayName = "Flatten"
   override def toString = elem.toString + ".flatten"

   def makeUGens : UGenInLike = UGenInGroup( elem.expand.flatOutputs )
}

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


object Mix {
   /**
    * A mixing idiom that corresponds to @Seq.tabulate@ and to @Array.fill@ in sclang.
    */
	def tabulate( n: Int )( fun: (Int) => GE )/* ( implicit rate: R ) */ : Mix.Seq =
      Mix.Seq( IIdxSeq.tabulate( n )( i => fun( i )))

   /**
    * A mixing idiom that corresponds to @Seq.fill@.
    */
   def fill( n: Int )( thunk: => GE )/* ( implicit rate: R ) */ : Mix.Seq =
      Mix.Seq( IIdxSeq.fill( n )( thunk ))

   def seq( elems: IIdxSeq[ GE ])/* ( implicit rate: R ) */ = Seq( elems )

   def mono( elem: GE ) = Mono( elem )

   final case class Seq( elems: IIdxSeq[ GE ])
   extends GE.Lazy {
def numOutputs = if( elems.isEmpty ) 0 else 1 // XXX korrekt?
      def rate = MaybeRate.reduce( elems.map( _.rate ): _* )

      def displayName = "Mix.Seq"
      override def toString = displayName + elems.mkString( "(", ",", ")" )

      def makeUGens : UGenInLike = if( elems.nonEmpty ) elems.reduceLeft( _ + _ ).expand else UGenInGroup.empty
   }

   final case class Mono( elem: GE )
   extends GE.Lazy {
def numOutputs = 1
      def rate = elem.rate

      def displayName = "Mix.Mono"
      override def toString = displayName + "(" + elem + ")"

      def makeUGens : UGenInLike = {
         val flat = elem.expand.flatOutputs
         if( flat.nonEmpty ) {
            flat.reduceLeft( BinaryOp.Plus.make1( _, _ ))
         } else UGenInGroup.empty
      }
   }
}

/**
 * Mixes the channels of a signal together. Works exactly like the sclang counterpart.
 *
 * Here are some examples:
 *
 * {{{
 * Mix( SinOsc.ar( 440 :: 660 :: Nil )) --> SinOsc.ar( 440 ) + SinOsc.ar( 660 )
 * Mix( SinOsc.ar( 440 )) --> SinOsc.ar( 440 )
 * Mix( Pan2.ar( SinOsc.ar )) --> left + right
 * Mix( Pan2.ar( SinOsc.ar( 440 :: 660 :: Nil ))) --> [ left( 440 ) + left( 660 ), right( 440 ) + right( 660 )]
 * }}}
*/
final case class Mix( elem: GE )
extends UGenSource.SingleOut( "Mix" ) { // GE.Lazy
   def rate = elem.rate

   protected def makeUGens : UGenInLike = unwrap( elem.expand.outputs )

   protected def makeUGen( args: IIdxSeq[ UGenIn ]) : UGenInLike = {
      if( args.nonEmpty ) {
         args.reduceLeft( BinaryOp.Plus.make1( _, _ ))
      } else UGenInGroup.empty
   }
}

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

object Reduce {
   /**
    * Same result as `Mix( _ )`
    */
   def +( elem: GE )    = apply( elem, BinaryOp.Plus )
   def *( elem: GE )    = apply( elem, BinaryOp.Times )
//   def all_===( elem: GE ) = error( "TODO" )
//   def all_!==( elem: GE ) = error( "TODO" )
   def min( elem: GE )  = apply( elem, BinaryOp.Min )
   def max( elem: GE )  = apply( elem, BinaryOp.Max )
   def &( elem: GE )    = apply( elem, BinaryOp.BitAnd )
   def |( elem: GE )    = apply( elem, BinaryOp.BitOr )
   def ^( elem: GE )    = apply( elem, BinaryOp.BitXor )
}
final case class Reduce( elem: GE, op: BinaryOp.Op )
extends UGenSource.SingleOut( "Reduce." + op.name ) {
   def rate = elem.rate

   protected def makeUGens : UGenInLike = unwrap( elem.expand.outputs )

   protected def makeUGen( args: IIdxSeq[ UGenIn ]) : UGenInLike = {
      if( args.nonEmpty ) {
         args.reduceLeft( op.make1( _, _ ))
      } else UGenInGroup.empty
   }
}

object WrapOut {
   private def makeFadeEnv( fadeTime: Float ) : UGenIn = {
      val cFadeTime  = new Control.UGen( control, 1, UGenGraph.builder.addControl( IIdxSeq( fadeTime ), Some( "fadeTime" ))).outputs( 0 )
      val cGate      = new Control.UGen( control, 1, UGenGraph.builder.addControl( IIdxSeq( 1 ), Some( "gate" ))).outputs( 0 )
      val startVal   = BinaryOp.Leq.make1( cFadeTime, 0 )

      // Env( startVal, List( Env.Seg( 1, 1, curveShape( -4 )), Env.Seg( 1, 0, sinShape )), 1 )
      val env        = IIdxSeq[ UGenIn ]( startVal, 2, 1, -99, 1, 1, 5, -4, 0, 1, 3, 0 )

      // this is slightly more costly than what sclang does
      // (using non-linear shape plus an extra unary op),
      // but it fadeout is much smoother this way...
      //EnvGen.kr( env, gate, timeScale = dt, doneAction = freeSelf ).squared

      new UGen.SingleOut( "EnvGen", control, IIdxSeq[ UGenIn ]( cGate, 1, 0, cFadeTime, freeSelf ) ++ env )
   }
}

/**
 * XXX TODO: This should not be a UGenSource.ZeroOut but just a LazyExpander !
 */
final case class WrapOut( in: GE, fadeTime: Optional[ Float ] = 0.02f ) extends UGenSource.ZeroOut( "WrapOut" ) with WritesBus {
   import WrapOut._
   protected def makeUGens { unwrap( in.expand.outputs )}

   protected def makeUGen( ins: IIdxSeq[ UGenIn ]) {
      if( ins.isEmpty ) return
      val rate = ins.map( _.rate ).max
      if( (rate == audio) || (rate == control) ) {
         val ins3 = fadeTime.option match {
            case Some( fdt ) =>
               val env  = makeFadeEnv( fdt )
               val ins2 = ins.map( BinaryOp.Times.make1( _, env ))
               if( rate == audio ) UGenHelper.replaceZeroesWithSilence( ins2 ) else ins2
            case None => ins
         }
         val cOut = new Control.UGen( control, 1, UGenGraph.builder.addControl( IIdxSeq( 0 ), Some( "out" ))).outputs( 0 )
         new UGen.ZeroOut( "Out", rate, cOut +: ins3 ) // with WritesBus {}
      }
   }
}


object SplayAz {
   def ar( numChannels: Int, in: GE, spread: GE = 1f, center: GE = 0f, level: GE = 1f, width: GE = 2f, orient: GE = 0f ) =
      apply( audio, numChannels, in, spread, center, level, width, orient )
}
final case class SplayAz( rate: Rate, numChannels: Int, in: GE, spread: GE, center: GE, level: GE, width: GE, orient: GE )
extends GE.Lazy {
   def numOutputs = numChannels
   def displayName = "SplayAz"

   protected def makeUGens : UGenInLike = {
      val _in  = in.expand
      val n    = _in.outputs.size
      val pf   = (0.5f * spread) / n
      val pos  = Seq.tabulate( n )( _ * pf + center )
      val mix  = Mix( PanAz( rate, numChannels, _in, pos, level, width, orient ))
      mix.expand
   }
}