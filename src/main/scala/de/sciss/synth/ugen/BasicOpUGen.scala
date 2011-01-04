/*
 *  BasicOpUGen.scala
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

package de.sciss.synth.ugen

import de.sciss.synth.{ AnyUGenIn, AnyGE, scalar, control, audio, Constant => c, GE, Rate, RichFloat, HasSideEffect, SingleOutUGen, UGenHelper,
                        UGenIn }
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._

/**
 *    @version 0.13, 03-Jan-11
 */
object MulAdd {
   def ar( in: GE[ audio, UGenIn[ audio ]],     mul: AnyGE, add: AnyGE ) : MulAdd[ audio ]   = apply[ audio ](   audio,   in, mul, add )
   def kr( in: GE[ control, UGenIn[ control ]], mul: AnyGE, add: AnyGE ) : MulAdd[ control ] = apply[ control ]( control, in, mul, add )
   def ir( in: GE[ scalar, UGenIn[ scalar ]],   mul: AnyGE, add: AnyGE ) : MulAdd[ scalar ]  = apply[ scalar ](  scalar,  in, mul, add )
}

case class MulAdd[ R <: Rate ]( rate: R, in: GE[ R, UGenIn[ R ]], mul: AnyGE, add: AnyGE ) extends GE[ R, MulAddUGen[ R ]] {
   def expand = {
      val _in: IIdxSeq[ UGenIn[ R ]]    = in.expand
      val _mul: IIdxSeq[ AnyUGenIn ]    = mul.expand
      val _add: IIdxSeq[ AnyUGenIn ]    = add.expand
      val _sz_in  = _in.size
      val _sz_mul = _mul.size
      val _sz_add = _add.size
      val _exp_   = maxInt( _sz_in, _sz_mul, _sz_add )
      IIdxSeq.tabulate( _exp_ )( i => {
         val in0  = _in( i % _sz_in )
         val mul0 = _mul( i % _sz_mul )
         val add0 = _add( i % _sz_add )
         (mul0, add0) match {
// YYY
//            case (c(0),  _)    => add0
//            case (c(1),  c(0)) => in0
//            case (c(1),  _)    => in0 + add0
//            case (c(-1), c(0)) => -in0
//            case (_,     c(0)) => in0 * mul0
//            case (c(-1), _)    => add0 - in0
            case _             => MulAddUGen[ R ]( rate, in0, mul0, add0 )
         }
      })
   }
}

object MulAddUGen {
//   protected[synth] def make( in: GE[ AnyUGenIn ], mul: GE[ AnyUGenIn ], add: GE[ AnyUGenIn ]) : GE = {
//      for( Seq( i, m, a ) <- expand( in, mul, add )) yield make1( i, m, a )
//   }
//
//   private def make1( in: AnyUGenIn, mul: AnyUGenIn, add: AnyUGenIn ) : GE =
//      make1( Rate.highest( in.rate, mul.rate, add.rate ), in, mul, add )
//
//   private def make1( rate: Rate, in: AnyUGenIn, mul: AnyUGenIn, add: AnyUGenIn ) : GE =
//      (mul, add) match {
//         case (c(0),  _)    => add
//         case (c(1),  c(0)) => in
//         case (c(1),  _)    => in + add
//         case (c(-1), c(0)) => -in
//         case (_,     c(0)) => in * mul
//         case (c(-1), _)    => add - in
//         case _             => this( rate, in, mul, add )
//      }
}

case class MulAddUGen[ R <: Rate ]( rate: R, in: AnyUGenIn, mul: AnyUGenIn, add: AnyUGenIn )
extends SingleOutUGen[ R ]( IIdxSeq( in, mul, add )) {
   override def toString = in.toString + ".madd(" + mul + ", " + add + ")"
}

private[ugen] abstract class BasicOpUGen[ R <: Rate ]( override val specialIndex: Int, inputs: IIdxSeq[ AnyUGenIn ])
extends SingleOutUGen[ R ]( inputs )

/**
 *    Unary operations are generally constructed by calling one of the methods of <code>GEOps</code>.
 *
 *    @see  GEOps
 *    @see  BinaryOpUGen
 */
object UnaryOp {
   unop =>

   import RichFloat._

   sealed abstract class Op( val id: Int ) {
      def make[ R <: Rate ]( rate: R, a: GE[ R, UGenIn[ R ]]) = UnaryOp[ R ]( rate, this, a )
//      protected[synth] def make1( a: UGenIn ) : GE = a match {
//         case c(a)   => c( make1( a ))
//         case _      => unop.apply( a.rate, this, a )
//      }

      protected def make1( a: Float ) : Float

      def name = { val cn = getClass.getName
         val sz   = cn.length
         val i    = cn.indexOf( '$' ) + 1
         cn.charAt( i ).toLower + cn.substring( i + 1, if( cn.charAt( sz - 1 ) == '$' ) sz - 1 else sz )
      }
   }

   case object Neg         extends Op(  0 ) {
      protected def make1( a: Float ) = -a
   }
   case object Not         extends Op(  1 ) {
      protected def make1( a: Float ) = rf_not( a )
   }
// case object IsNil       extends Op(  2 )
// case object NotNil      extends Op(  3 )
// case object BitNot      extends Op(  4 )
   case object Abs         extends Op(  5 ) {
      protected def make1( a: Float ) = math.abs( a )
   }
// case object ToFloat     extends Op(  6 )
// case object ToInt       extends Op(  7 )
   case object Ceil        extends Op(  8 ) {
      protected def make1( a: Float ) = rf_ceil( a )
   }
   case object Floor       extends Op(  9 ) {
      protected def make1( a: Float ) = rf_floor( a )
   }
   case object Frac        extends Op( 10 ) {
      protected def make1( a: Float ) = rf_frac( a )
   }
   case object Signum      extends Op( 11 ) {
      protected def make1( a: Float ) = math.signum( a )
   }
   case object Squared     extends Op( 12 ) {
      protected def make1( a: Float ) = rf_squared( a )
   }
   case object Cubed       extends Op( 13 ) {
      protected def make1( a: Float ) = rf_cubed( a )
   }
   case object Sqrt        extends Op( 14 ) {
      protected def make1( a: Float ) = rf_sqrt( a )
   }
   case object Exp         extends Op( 15 ) {
      protected def make1( a: Float ) = rf_exp( a )
   }
   case object Reciprocal  extends Op( 16 ) {
      protected def make1( a: Float ) = rf_reciprocal( a )
   }
   case object Midicps     extends Op( 17 ) {
      protected def make1( a: Float ) = rf_midicps( a )
   }
   case object Cpsmidi     extends Op( 18 ) {
      protected def make1( a: Float ) = rf_cpsmidi( a )
   }
   case object Midiratio   extends Op( 19 ) {
      protected def make1( a: Float ) = rf_midiratio( a )
   }
   case object Ratiomidi   extends Op( 20 ) {
      protected def make1( a: Float ) = rf_ratiomidi( a )
   }
   case object Dbamp       extends Op( 21 ) {
      protected def make1( a: Float ) = rf_dbamp( a )
   }
   case object Ampdb       extends Op( 22 ) {
      protected def make1( a: Float ) = rf_ampdb( a )
   }
   case object Octcps      extends Op( 23 ) {
      protected def make1( a: Float ) = rf_octcps( a )
   }
   case object Cpsoct      extends Op( 24 ) {
      protected def make1( a: Float ) = rf_cpsoct( a )
   }
   case object Log         extends Op( 25 ) {
      protected def make1( a: Float ) = rf_log( a )
   }
   case object Log2        extends Op( 26 ) {
      protected def make1( a: Float ) = rf_log2( a )
   }
   case object Log10       extends Op( 27 ) {
      protected def make1( a: Float ) = rf_log10( a )
   }
   case object Sin         extends Op( 28 ) {
      protected def make1( a: Float ) = rf_sin( a )
   }
   case object Cos         extends Op( 29 ) {
      protected def make1( a: Float ) = rf_cos( a )
   }
   case object Tan         extends Op( 30 ) {
      protected def make1( a: Float ) = rf_tan( a )
   }
   case object Asin        extends Op( 31 ) {
      protected def make1( a: Float ) = rf_asin( a )
   }
   case object Acos        extends Op( 32 ) {
      protected def make1( a: Float ) = rf_acos( a )
   }
   case object Atan        extends Op( 33 ) {
      protected def make1( a: Float ) = rf_atan( a )
   }
   case object Sinh        extends Op( 34 ) {
      protected def make1( a: Float ) = rf_sinh( a )
   }
   case object Cosh        extends Op( 35 ) {
      protected def make1( a: Float ) = rf_cosh( a )
   }
   case object Tanh        extends Op( 36 ) {
      protected def make1( a: Float ) = rf_tanh( a )
   }
// class Rand              extends Op( 37 )
// class Rand2             extends Op( 38 )
// class Linrand           extends Op( 39 )
// class Bilinrand         extends Op( 40 )
// class Sum3rand          extends Op( 41 )
   case object Distort     extends Op( 42 ) {
      protected def make1( a: Float ) = rf_distort( a )
   }
   case object Softclip    extends Op( 43 ) {
      protected def make1( a: Float ) = rf_softclip( a )
   }
// class Coin              extends Op( 44 )
// case object DigitValue  extends Op( 45 )
// case object Silence     extends Op( 46 )
// case object Thru        extends Op( 47 )
// case object RectWindow  extends Op( 48 )
// case object HanWindow   extends Op( 49 )
// case object WelWindow   extends Op( 50 )
// case object TriWindow   extends Op( 51 )
   case object Ramp        extends Op( 52 ) {
      protected def make1( a: Float ) = rf_ramp( a )
   }
   case object Scurve      extends Op( 53 ) {
      protected def make1( a: Float ) = rf_scurve( a )
   }

//   protected[synth] def make( selector: Op, a: GE ) : GE = {
//      for( Seq( ai ) <- expand( a )) yield selector.make1( ai )
//   }
}

case class UnaryOp[ R <: Rate ]( rate: R, selector: UnaryOp.Op, a: GE[ R, UGenIn[ R ]])
extends GE[ R, UnaryOpUGen[ R ]] {
//   override def toString = a.toString + "." + selector.name
//   override def displayName = selector.name

   def expand = {
      val _a = a.expand
      IIdxSeq.tabulate( _a.size )( i => UnaryOpUGen( rate, selector, _a( i )))
   }
}

// Note: only deterministic selectors are implemented!!
case class UnaryOpUGen[ R <: Rate ]( rate: R, selector: UnaryOp.Op, a: UGenIn[ R ])
extends BasicOpUGen[ R ]( selector.id, IIdxSeq( a )) {
   override def name = "UnaryOpUGen"
   override def toString = a.toString + "." + selector.name
   override def displayName = selector.name
}

/**
 *    Binary operations are generally constructed by calling one of the methods of <code>GEOps</code>.
 *
 *    @see  GEOps
 *    @see  UnaryOpUGen
 */
object BinaryOp {
   binop =>

   sealed abstract class Op( val id: Int ) {
//      def make[ R <: Rate ]( rate: R, a: GE[ UGenIn[ R ]]) = UnaryOp[ R ]( rate, this, a )
      def make[ R <: Rate, S <: Rate, T <: Rate ]( rate: T, a: GE[ R, UGenIn[ R ]], b: GE[ S, UGenIn[ S ]]) = BinaryOp[ T ]( rate, this, a, b )
//      protected[synth] def make1( a: UGenIn, b: UGenIn ) : GE = (a, b) match {
//         case (c(a), c(b)) => c( make1( a, b ))
//         case _            => binop.apply( Rate.highest( a.rate, b.rate ), this, a, b )
//      }

      protected def make1( a: Float, b: Float ) : Float

      def name = { val cn = getClass.getName
         val sz   = cn.length
         val i    = cn.indexOf( '$' ) + 1
         cn.charAt( i ).toLower + cn.substring( i + 1, if( cn.charAt( sz - 1 ) == '$' ) sz - 1 else sz )
      }
   }

   import RichFloat._

   case object Plus           extends Op(  0 ) {
      override val name = "+"
      protected def make1( a: Float, b: Float ) = a + b
// YYY
//      override protected[synth] def make1( a: UGenIn, b: UGenIn ) : GE = (a, b) match {
//         case (c(0), _)       => b
//         case (_, c(0))       => a
//         case _               => super.make1( a, b )
//      }
   }
   case object Minus          extends Op(  1 ) {
      override val name = "-"
      protected def make1( a: Float, b: Float ) = a - b
// YYY
//      override protected[synth] def make1( a: UGenIn, b: UGenIn ) : GE = (a, b) match {
//         case (c(0), _)       => -b
//         case (_, c(0))       => a
//         case _               => super.make1( a, b )
//      }
   }
   case object Times          extends Op(  2 ) {
      override val name = "*"
      protected def make1( a: Float, b: Float ) = a * b
// YYY
//      override protected[synth] def make1( a: UGenIn, b: UGenIn ) : GE = (a, b) match {
//         case (c(0), _)       => a
//         case (_, c(0))       => b
//         case (c(1), _)       => b
//         case (_, c(1))       => a
//         case (c(-1), _)      => -b
//         case (_, c(-1))      => -a
//         case _               => super.make1( a, b )
//      }
   }
// case object IDiv           extends Op(  3 )
   case object Div            extends Op(  4 ) {
      override val name = "/"
      protected def make1( a: Float, b: Float ) = a / b
// YYY
//      override protected[synth] def make1( a: UGenIn, b: UGenIn ) : GE = (a, b) match {
//         case (_, c(1))       => a
//         case (_, c(-1))      => -a
//         case (_, _) if b.rate == scalar => a * b.reciprocal
//         case _               => super.make1( a, b )
//      }
   }
   case object Mod            extends Op(  5 ) {
      override val name = "%"
      protected def make1( a: Float, b: Float ) = a % b
   }
   case object Eq             extends Op(  6 ) {
      override val name = "==="
      protected def make1( a: Float, b: Float ) = if( a == b ) 1f else 0f
   }
   case object Neq            extends Op(  7 ) {
      override val name = "!=="
      protected def make1( a: Float, b: Float ) = if( a != b ) 1f else 0f
   }
   case object Lt             extends Op(  8 ) {
      override val name = "<"
      protected def make1( a: Float, b: Float ) = if( a < b ) 1f else 0f
   }
   case object Gt             extends Op(  9 ) {
      override val name = ">"
      protected def make1( a: Float, b: Float ) = if( a > b ) 1f else 0f
   }
   case object Leq            extends Op( 10 ) {
      override val name = "<="
      protected def make1( a: Float, b: Float ) = if( a <= b ) 1f else 0f
   }
   case object Geq            extends Op( 11 ) {
      override val name = ">="
      protected def make1( a: Float, b: Float ) = if( a >= b ) 1f else 0f
   }
   case object Min            extends Op( 12 ) {
      protected def make1( a: Float, b: Float ) = math.min( a, b )
   }
   case object Max            extends Op( 13 ) {
      protected def make1( a: Float, b: Float ) = math.max( a, b )
   }
   case object BitAnd         extends Op( 14 ) {
      override val name = "&"
      protected def make1( a: Float, b: Float ) = (a.toInt & b.toInt).toFloat
   }
   case object BitOr          extends Op( 15 ) {
      override val name = "|"
      protected def make1( a: Float, b: Float ) = (a.toInt | b.toInt).toFloat
   }
   case object BitXor         extends Op( 16 ) {
      override val name = "^"
      protected def make1( a: Float, b: Float ) = (a.toInt ^ b.toInt).toFloat
   }
// case object Lcm            extends Op( 17 )
// case object Gcd            extends Op( 18 )
   case object Round          extends Op( 19 ) {
      protected def make1( a: Float, b: Float ) = rf_round( a, b )
   }
   case object Roundup        extends Op( 20 ) {
      protected def make1( a: Float, b: Float ) = rf_roundup( a, b )
   }
   case object Trunc          extends Op( 21 ) {
      protected def make1( a: Float, b: Float ) = rf_trunc( a, b )
   }
   case object Atan2          extends Op( 22 ) {
      protected def make1( a: Float, b: Float ) = math.atan2( a, b ).toFloat
   }
   case object Hypot          extends Op( 23 ) {
      protected def make1( a: Float, b: Float ) = math.hypot( a, b ).toFloat
   }
   case object Hypotx         extends Op( 24 ) {
      protected def make1( a: Float, b: Float ) = rf_hypotx( a, b )
   }
   case object Pow            extends Op( 25 ) {
      protected def make1( a: Float, b: Float ) = math.pow( a, b ).toFloat
   }
// case object <<             extends Op( 26 )
// case object >>             extends Op( 27 )
// case object UnsgnRghtShft  extends Op( 28 )
// case object Fill           extends Op( 29 )
   case object Ring1          extends Op( 30 ) {
      protected def make1( a: Float, b: Float ) = rf_ring1( a, b )
   }
   case object Ring2          extends Op( 31 ) {
      protected def make1( a: Float, b: Float ) = rf_ring2( a, b )
   }
   case object Ring3          extends Op( 32 ) {
      protected def make1( a: Float, b: Float ) = rf_ring3( a, b )
   }
   case object Ring4          extends Op( 33 ) {
      protected def make1( a: Float, b: Float ) = rf_ring4( a, b )
   }
   case object Difsqr         extends Op( 34 ) {
      protected def make1( a: Float, b: Float ) = rf_difsqr( a, b )
   }
   case object Sumsqr         extends Op( 35 ) {
      protected def make1( a: Float, b: Float ) = rf_sumsqr( a, b )
   }
   case object Sqrsum         extends Op( 36 ) {
      protected def make1( a: Float, b: Float ) = rf_sqrsum( a, b )
   }
   case object Sqrdif         extends Op( 37 ) {
      protected def make1( a: Float, b: Float ) = rf_sqrdif( a, b )
   }
   case object Absdif         extends Op( 38 ) {
      protected def make1( a: Float, b: Float ) = rf_absdif( a, b )
   }
   case object Thresh         extends Op( 39 ) {
      protected def make1( a: Float, b: Float ) = rf_thresh( a, b )
   }
   case object Amclip         extends Op( 40 ) {
      protected def make1( a: Float, b: Float ) = rf_amclip( a, b )
   }
   case object Scaleneg       extends Op( 41 ) {
      protected def make1( a: Float, b: Float ) = rf_scaleneg( a, b )
   }
   case object Clip2          extends Op( 42 ) {
      protected def make1( a: Float, b: Float ) = rf_clip2( a, b )
   }
   case object Excess         extends Op( 43 ) {
      protected def make1( a: Float, b: Float ) = rf_excess( a, b )
   }
   case object Fold2          extends Op( 44 ) {
      protected def make1( a: Float, b: Float ) = rf_fold2( a, b )
   }
   case object Wrap2          extends Op( 45 ) {
      protected def make1( a: Float, b: Float ) = rf_wrap2( a, b )
   }
// YYY
//   case object Firstarg       extends Op( 46 ) {
//      override protected[synth] def make1( a: UGenIn, b: UGenIn ) : GE = (a, b) match {
//         case (c(a), c(b)) => c( make1( a, b ))
//         case _            => FirstargUGen( Rate.highest( a.rate, b.rate ), a, b )
//      }
//      protected def make1( a: Float, b: Float ) = a
//   }

// case object Rrand          extends Op( 47 )
// case object ExpRRand       extends Op( 48 )

//   protected[synth] def make( selector: Op, a: GE, b: GE ) : GE = {
//      for( Seq( ai, bi ) <- expand( a, b )) yield selector.make1( ai, bi )
//   }

/*
  private def determineRate( a: UGenIn, b: UGenIn ) : Rate = {
    if( a.rate > b.rate ) a.rate else b.rate
//    if( a.rate == 'demand ) return 'demand
//    if( b.rate == 'demand ) return 'demand
//    if( a.rate == 'audio ) return 'audio
//    if( b.rate == 'audio ) return 'audio
//    if( a.rate == 'control ) return 'control
//    if( b.rate == 'control ) return 'control
//    'scalar
  }
  */
}

case class BinaryOp[ R <: Rate ]( rate: R, selector: BinaryOp.Op, a: AnyGE, b: AnyGE )
extends GE[ R, BinaryOpUGen[ R ]] {
//   override def toString = a.toString + "." + selector.name
//   override def displayName = selector.name

   def expand = {
      val _a: IIdxSeq[ AnyUGenIn ]  = a.expand
      val _b: IIdxSeq[ AnyUGenIn ]  = b.expand
      val _sz_a   = _a.size
      val _sz_b   = _b.size
      val _exp_   = math.max( _sz_a, _sz_b )
      // YYY optimize
      IIdxSeq.tabulate( _exp_ )( i => BinaryOpUGen( rate, selector, _a( i % _sz_a ), _b( i % _sz_b )))
   }
}

// Note: only deterministic selectors are implemented!!
case class BinaryOpUGen[ R <: Rate ]( rate: R, selector: BinaryOp.Op, a: AnyUGenIn, b: AnyUGenIn )
extends BasicOpUGen[ R ]( selector.id, IIdxSeq( a, b )) {
   override def name = "BinaryOpUGen"
   override def toString = if( (selector.id <= 11) || ((selector.id >=14) && (selector.id <= 16)) ) {
      "(" + a + " " + selector.name + " " + b + ")"
   } else {
      a.toString + "." + selector.name + "(" + b + ")"
   }
   override def displayName = selector.name
}

// Special case since it should not be erased. Might be that we
// better transform BinaryOpUGen from case class to regular class with extractor?

// YYY
//case class FirstargUGen( rate: Rate, a: UGenIn, b: UGenIn )
//extends BasicOpUGen( BinaryOpUGen.Firstarg.id, a, b ) with HasSideEffect {
//   override def name = "BinaryOpUGen"
//   override def displayName = "firstarg"
//   override def toString = a.toString + "." + displayName + "(" + b + ")"
//}
