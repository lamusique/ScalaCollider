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

/**
 * A graph element which maps a linear range to another linear range.
 * The equivalent formula is `(in - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo`.
 *
 * '''Note''': No clipping is performed. If the input signal exceeds the input range, the output will also exceed its range.
 *
 * @param in              The input signal to convert.
 * @param srcLo           The lower limit of input range.
 * @param srcHi           The upper limit of input range.
 * @param dstLo           The lower limit of output range.
 * @param dstHi           The upper limit of output range.
 *
 * @see [[de.sciss.synth.ugen.LinExp]]
 * @see [[de.sciss.synth.ugen.Clip]]
 */
final case class LinLin( /* rate: MaybeRate, */ in: GE, srcLo: GE = 0f, srcHi: GE = 1f, dstLo: GE = 0f, dstHi: GE = 1f )
extends GE.Lazy {
   def displayName = "LinLin"

   def rate: MaybeRate = in.rate // XXX correct?

   protected def makeUGens : UGenInLike = {
      val scale  = (dstHi - dstLo) / (srcHi - srcLo)
      val offset = dstLo - (scale * srcLo)
      MulAdd( in, scale, offset ).expand
   }
}

object Silent {
   def ar: Silent = ar()
   def ar( numChannels: Int = 1 ) = apply( numChannels )
}
final case class Silent( numChannels: Int ) extends GE.Lazy with AudioRated {
   def displayName = "Silent"

   protected def makeUGens: UGenInLike = {
      val dc = DC.ar( 0 )
      val ge: GE = if( numChannels == 1 ) dc else Seq.fill( numChannels )( dc )
      ge.expand
   }
}

/**
 * A graph element which reads from a connected sound driver input. This is a convenience
 * element for accessing physical input signals, e.g. from a microphone connected to your
 * audio interface. It expands to a regular `In` UGen offset by `NumOutputBuses.ir`.
 */
object PhysicalIn {
   /**
    * Short cut for reading a mono signal from the first physical input
    */
   def ar : PhysicalIn = ar()
   /**
    * @param bus           the physical index to read from (beginning at zero which corresponds to
                           the first channel of the audio interface or sound driver)
    * @param numChannels   the number of consecutive channels to read
    */
   def ar( bus: GE = 0, numChannels: Int = 1 ) : PhysicalIn = apply( Seq( (bus, numChannels) ))
//   def apply( index: GE, moreIndices: GE* ) : PhysicalIn = apply( (index +: moreIndices).map( (_, 1) ): _* )
}
/**
 * A graph element which reads from a connected sound driver input. This is a convenience
 * element for accessing physical input signals, e.g. from a microphone connected to your
 * audio interface. It expands to a regular `In` UGen offset by `NumOutputBuses.ir`.
 *
 * @param pairs   pairs of channel indices and number of channels which will be concatenated. As with the `ar`
 *                method, indices start at zero which corresponds to the first channel of the audio interface.
 *                For example, consider an audio interface with channels 1 to 8 being analog line inputs,
 *                channels 9 and 10 being AES/EBU and channels 11 to 18 being ADAT inputs. To read a combination
 *                of the analog and ADAT inputs, the `pairs` would be the `Seq( (0, 8), (10, 8) )`.
 */
final case class PhysicalIn( pairs: Seq[ (GE, Int) ]) extends GE.Lazy with AudioRated {
   def displayName = "PhyiscalIn"

   protected def makeUGens: UGenInLike = {
      val offset = NumOutputBuses.ir
      Flatten( pairs.map { case (index, numChannels) => In.ar( index + offset, numChannels )}).expand
   }
}

object PhysicalOut {
   /**
    * @param bus           the physical index to write to (beginning at zero which corresponds to
                           the first channel of the audio interface or sound driver)
    * @param in            the signal to write
    */
   def ar( bus: GE = 0, in: GE ) : PhysicalOut = apply( Seq( bus ), in )
}
final case class PhysicalOut( indices: Seq[ GE ], in: GE ) extends UGenSource.ZeroOut( "PhyiscalOut" ) with AudioRated {
//   def displayName = "PhyiscalOut"

   protected def makeUGens {
      val chans = in.expand.outputs
      indices.dropRight( 1 ).zip( chans ).foreach { case (index, sig) =>
         Out.ar( index, sig )
      }
      (indices.lastOption, chans.drop( indices.size - 1 )) match {
         case (Some( index ), sig) if( sig.nonEmpty ) =>
            Out.ar( index, sig )
         case _ =>
      }
   }

   protected def makeUGen( args: IIdxSeq[ UGenIn ]) {}   // XXX not used, ugly
}
