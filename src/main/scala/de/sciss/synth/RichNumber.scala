/*
 *  RichNumber.scala
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

import collection.immutable.NumericRange

object RichNumber {
   private[synth] val LOG2 = math.log( 2 )

   /**
    * This would be better `RichFloat.NAryOps`, but scalac can't handle the case
    * op `class RichFloat extends RichFloat.NAryOps` (says it's cyclic) -- damn...
    */
   sealed trait NAryFloatOps {
      import RichFloat._

      protected def f: Float

      // -------- binary ops --------

// def +( b: Float ) : Float        = rf_+( f, b )
// def -( b: Float ) : Float        = rf_-( f, b )
// def *( b: Float ) : Float        = rf_*( f, b )
      def div( b: Float ) : Int     = rf_div( f, b )
// def /( b: Float ) : Float        = rf_/( f, b )
// def %( b: Float ) : Float        = rf_%( f, b )
// def ===( b: Float ) : Int        = rf_===( f, b )
// def !==( b: Float ) : Int        = rf_!==( f, b )
// def <( b: Float ) : Float	      = rf_<( f, b )
// def >( b: Float ) : Float	      = rf_>( f, b )
// def <=( b: Float ) : Float	      = rf_<=( f, b )
// def >=( b: Float ) : Float	      = rf_>=( f, b )
      def min( b: Float ) : Float      = rf_min( f, b )
      def max( b: Float ) : Float      = rf_max( f, b )
// def &( b: Float ) : Float	      = rf_&( f, b )
// def |( b: Float ) : Float	      = rf_|( f, b )
// def ^( b: Float ) : Float	      = rf_^( f, b )
      def round( b: Float ) : Float    = rf_round( f, b )
      def roundup( b: Float ) : Float  = rf_roundup( f, b )
      def trunc( b: Float ) : Float    = rf_trunc( f, b )
      def atan2( b: Float ) : Float    = rf_atan2( f, b )
      def hypot( b: Float ) : Float    = rf_hypot( f, b )
      def hypotx( b: Float ) : Float   = rf_hypotx( f, b )
      def pow( b: Float ) : Float      = rf_pow( f, b )
      def ring1( b: Float ) : Float    = rf_ring1( f, b )
      def ring2( b: Float ) : Float    = rf_ring2( f, b )
      def ring3( b: Float ) : Float    = rf_ring3( f, b )
      def ring4( b: Float ) : Float    = rf_ring4( f, b )
      def difsqr( b: Float ) : Float   = rf_difsqr( f, b )
      def sumsqr( b: Float ) : Float   = rf_sumsqr( f, b )
      def sqrsum( b: Float ) : Float   = rf_sqrsum( f, b )
      def sqrdif( b: Float ) : Float   = rf_sqrdif( f, b )
      def absdif( b: Float ) : Float   = rf_absdif( f, b )
      def thresh( b: Float ) : Float   = rf_thresh( f, b )
      def amclip( b: Float ) : Float   = rf_amclip( f, b )
      def scaleneg( b: Float ) : Float = rf_scaleneg( f, b )
      def clip2( b: Float ) : Float    = rf_clip2( f, b )
      def excess( b: Float ) : Float   = rf_excess( f, b )
      def fold2( b: Float ) : Float    = rf_fold2( f, b )
      def wrap2( b: Float ) : Float    = rf_wrap2( f, b )
// def firstarg( b: Float ) : Float = d

      def linlin( srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float =
         rf_linlin( f, srcLo, srcHi, dstLo, dstHi )

      def linexp( srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float =
         rf_linexp( f, srcLo, srcHi, dstLo, dstHi )
   }

   sealed trait UnaryFloatOps {
      import RichFloat._

      protected def f: Float

      // unary ops
      def sqrt : Float        = rf_sqrt( f )
      def exp : Float         = rf_exp( f )
      def reciprocal : Float  = rf_reciprocal( f )
      def midicps : Float     = rf_midicps( f )
      def cpsmidi : Float     = rf_cpsmidi( f )
      def midiratio : Float   = rf_midiratio( f )
      def ratiomidi : Float   = rf_ratiomidi( f )
      def dbamp : Float       = rf_dbamp( f )
      def ampdb : Float       = rf_ampdb( f )
      def octcps : Float      = rf_octcps( f )
      def cpsoct : Float      = rf_cpsoct( f )
      def log : Float         = rf_log( f )
      def log2 : Float        = rf_log2( f )
      def log10 : Float       = rf_log10( f )
      def sin : Float         = rf_sin( f )
      def cos : Float         = rf_cos( f )
      def tan : Float         = rf_tan( f )
      def asin : Float        = rf_asin( f )
      def acos : Float        = rf_acos( f )
      def atan : Float        = rf_atan( f )
      def sinh : Float        = rf_sinh( f )
      def cosh : Float        = rf_cosh( f )
      def tanh : Float        = rf_tanh( f )
// def distort : Float     = f / (1 + math.abs( f ))
// def softclip : Float    = { val absx = math.abs( f ); if( absx <= 0.5f ) f else (absx - 0.25f) / f}
// def ramp : Float        = if( f <= 0 ) 0 else if( f >= 1 ) 1 else f
// def scurve : Float      = if( f <= 0 ) 0 else if( f > 1 ) 1 else f * f * (3 - 2 * f)
   }

   // ---- Double ----

   @inline def rd_fold( in: Double, lo: Double, hi: Double ) : Double = {
      val x = in - lo
      // avoid the divide if possible
      if( in >= hi ) {
         val f = hi + hi - in
         if (f >= lo) return f
      } else if( in < lo ) {
         val f = lo + lo - in
         if( f < hi ) return f
      } else return in

      if( hi == lo ) return lo
      // ok do the divide
      val range   = hi - lo
      val range2  = range + range
      val c       = x - range2 * math.floor( x / range2 )
      lo + (if( c >= range ) range2 - c else c)
   }

   @inline def rd_wrap( in: Double, lo: Double, hi: Double ) : Double = {
      // avoid the divide if possible
      if( in >= hi ) {
         val range   = hi - lo
         val in2     = in - range;
         if( in2 < hi ) in2 else if( hi == lo ) lo else {
            in2 - range * math.floor( (in2 - lo) / range )
         }
      } else if( in < lo ) {
         val range   = hi - lo
         val in2     = in + range
         if( in2 >= lo ) in2 else if( hi == lo ) lo else {
            in2 - range * math.floor( (in2 - lo) / range )
         }
      } else in
   }

   // ---- binary ops ----

   @inline def rd_+( a: Double, b: Double ) : Double     = a + b
   @inline def rd_-( a: Double, b: Double ) : Double     = a - b
   @inline def rd_*( a: Double, b: Double ) : Double     = a * b
   @inline def rd_div( a: Double, b: Double ) : Int      = (a / b).toInt
   @inline def rd_/( a: Double, b: Double ) : Double     = a / b
   @inline def rd_%( a: Double, b: Double ) : Double     = a % b
//   @inline def rd_===
//   @inline def rd_!==
   @inline def rd_<( a: Double, b: Double ) : Boolean    = a < b
   @inline def rd_>( a: Double, b: Double ) : Boolean    = a > b
   @inline def rd_<=( a: Double, b: Double ) : Boolean   = a <= b
   @inline def rd_>=( a: Double, b: Double ) : Boolean   = a >= b
   @inline def rd_min( a: Double, b: Double ) : Double   = math.min( a, b )
   @inline def rd_max( a: Double, b: Double ) : Double   = math.max( a, b )

   @inline def rd_round( a: Double, b: Double ) =
      if( b == 0 ) a else math.floor( a / b + 0.5f ) * b

   @inline def rd_roundup( a: Double, b: Double ) =
      if( b == 0 ) a else math.ceil( a / b ) * b

   @inline def rd_trunc( a: Double, b: Double ) =
      if( b == 0 ) a else math.floor( a / b ) * b

   @inline def rd_atan2( a: Double, b: Double ) : Double = math.atan2( a, b )
   @inline def rd_hypot( a: Double, b: Double ) : Double = math.hypot( a, b )

   @inline def rd_hypotx( a: Double, b: Double ) = {
      val minab = math.min( math.abs( a ), math.abs( b ))
      a + b - (math.sqrt(2) - 1) * minab
   }

   @inline def rd_pow( a: Double, b: Double ) : Double = math.pow( a, b )

// <<
// >>
// UnsgnRghtShft
// fill

   @inline def rd_ring1( a: Double, b: Double ) =
      a * b + a

   @inline def rd_ring2( a: Double, b: Double ) =
      a * b + a + b

   @inline def rd_ring3( a: Double, b: Double ) =
      a * a * b

   @inline def rd_ring4( a: Double, b: Double ) = {
      val ab = a * b; a * ab - b * ab
   }

   @inline def rd_difsqr( a: Double, b: Double ) =
      a * a - b * b

   @inline def rd_sumsqr( a: Double, b: Double ) =
      a * a + b * b

   @inline def rd_sqrsum( a: Double, b: Double ) = {
      val z = a + b; z * z
   }

   @inline def rd_sqrdif( a: Double, b: Double ) = {
      val z = a - b; z * z
   }

   @inline def rd_absdif( a: Double, b: Double ) = math.abs( a - b )

   @inline def rd_thresh( a: Double, b: Double ) =
      if( a < b ) 0 else a

   @inline def rd_amclip( a: Double, b: Double ) =
      a * 0.5 * (b + math.abs( a ))

   @inline def rd_scaleneg( a: Double, b: Double ) =
      (math.abs( a ) - a) * (0.5 * b + 0.5) + a

   @inline def rd_clip2( a: Double, b: Double ) =
      math.max( math.min( a, b ), -b )

   @inline def rd_excess( a: Double, b: Double ) =
      a - math.max( math.min( a, b ), -b )

   @inline def rd_fold2( a: Double, b: Double ) = rd_fold( a, -b, b )

   @inline def rd_wrap2( a: Double, b: Double ) = rd_wrap( a, -b, b )

   @inline def rd_linlin( in: Double, srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double = {
      (in - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo
   }

   @inline def rd_linexp( in: Double, srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double = {
      math.pow( dstHi / dstLo, (in - srcLo) / (srcHi - srcLo)) * dstLo
   }

   sealed trait NAryDoubleOps {
      protected def d: Double

      // recover these from scala.runtime.RichDouble
      def until( end: Double ): Range.Partial[ Double, NumericRange[ Double ]] = new Range.Partial( until( end, _ ))
      def until( end: Double, step: Double ): NumericRange[ Double ] = Range.Double( d, end, step )
      def to( end: Double ): Range.Partial[ Double, NumericRange[ Double ]] = new Range.Partial( to( end, _ ))
      def to( end: Double, step: Double ): NumericRange[ Double ] = Range.Double.inclusive( d, end, step )

      // binary ops
      def min( b: Double ) : Double       = rd_min( d, b )
      def max( b: Double ) : Double       = rd_max( d, b )
      def round( b: Double ) : Double     = rd_round( d, b )
      def roundup( b: Double ) : Double   = rd_roundup( d, b )
      def trunc( b: Double ) : Double     = rd_trunc( d, b )
      def atan2( b: Double ) : Double     = rd_atan2( d, b )
      def hypot( b: Double ) : Double     = rd_hypot( d, b )
      def hypotx( b: Double ) : Double    = rd_hypotx( d, b )
      def pow( b: Double ) : Double       = rd_pow( d, b )
      def ring1( b: Double ) : Double     = rd_ring1( d, b )
      def ring2( b: Double ) : Double     = rd_ring2( d, b )
      def ring3( b: Double ) : Double     = rd_ring3( d, b )
      def ring4( b: Double ) : Double     = rd_ring4( d, b )
      def difsqr( b: Double ) : Double    = rd_difsqr( d, b )
      def sumsqr( b: Double ) : Double    = rd_sumsqr( d, b )
      def sqrsum( b: Double ) : Double    = rd_sqrsum( d, b )
      def sqrdif( b: Double ) : Double    = rd_sqrdif( d, b )
      def absdif( b: Double ) : Double    = rd_absdif( d, b )
      def thresh( b: Double ) : Double    = rd_thresh( d, b )
      def amclip( b: Double ) : Double    = rd_amclip( d, b )
      def scaleneg( b: Double ) : Double  = rd_scaleneg( d, b )
      def clip2( b: Double ) : Double     = rd_clip2( d, b )
      def excess( b: Double ) : Double    = rd_excess( d, b )
      def fold2( b: Double ) : Double     = rd_fold2( d, b )
      def wrap2( b: Double ) : Double     = rd_wrap2( d, b )
   // def firstarg( b: Double ) : Double  = d

      def linlin( srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double =
         rd_linlin( d, srcLo, srcHi, dstLo, dstHi )

      def linexp( srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double =
         rd_linexp( d, srcLo, srcHi, dstLo, dstHi )
   }

//   sealed trait NAryDoubleOps2 extends NAryDoubleOps {
//      def round( b: Double ) : Double     = rd_round( d, b )
//      def roundup( b: Double ) : Double   = rd_roundup( d, b )
//      def trunc( b: Double ) : Double     = rd_trunc( d, b )
//   }

   // ---- Constant / GE ----

   sealed trait NAryGEOps {
//      import RichDouble._

      protected def cn: Constant

   //   private def binOp[ S <: Rate, T <: Rate ]( op: BinaryOp.Op, b: GE[ S ])
   //                                            ( implicit r: MaybeRateOrder[ R, S, T ]) : GE[ T ] =
   //      op.make[ R, S, T ]( /* r.getOrElse( this.rate, b.rate ), */ this, b )
   //
   //   def +[ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: MaybeRateOrder[ R, S, T ]) = binOp( Plus, b )

      // binary ops
      def -( b: GE ) : GE          = cn.-( b )
      def *( b: GE ) : GE          = cn.*( b )
      def /( b: GE ) : GE          = cn./( b )
      def %( b: GE ) : GE          = cn.%( b )
      def ===( b: GE ) : GE        = cn.===( b )
      def !==( b: GE ) : GE        = cn.!==( b )
      def <( b: GE ) : GE          = cn.<( b )
      def >( b: GE ) : GE          = cn.>( b )
      def <=( b: GE ) : GE         = cn.<=( b )
      def >=( b: GE ) : GE         = cn.>=( b )
      def &( b: GE ) : GE          = cn.&( b )
      def |( b: GE ) : GE          = cn.|( b )
      def ^( b: GE ) : GE          = cn.^( b )
      def round( b: GE ) : GE      = cn.round( b )
      def roundup( b: GE ) : GE    = cn.roundup( b )
      def trunc( b: GE ) : GE      = cn.trunc( b )
      def atan2( b: GE ) : GE      = cn.atan2( b )
      def hypot( b: GE ) : GE      = cn.hypot( b )
      def hypotx( b: GE ) : GE     = cn.hypotx( b )
      def pow( b: GE ) : GE        = cn.pow( b )
      def ring1( b: GE ) : GE      = cn.ring1( b )
      def ring2( b: GE ) : GE      = cn.ring2( b )
      def ring3( b: GE ) : GE      = cn.ring3( b )
      def ring4( b: GE ) : GE      = cn.ring4( b )
      def difsqr( b: GE ) : GE     = cn.difsqr( b )
      def sumsqr( b: GE ) : GE     = cn.sumsqr( b )
      def sqrsum( b: GE ) : GE     = cn.sqrsum( b )
      def sqrdif( b: GE ) : GE     = cn.sqrdif( b )
      def absdif( b: GE ) : GE     = cn.absdif( b )
      def thresh( b: GE ) : GE     = cn.thresh( b )
      def amclip( b: GE ) : GE     = cn.amclip( b )
      def scaleneg( b: GE ) : GE   = cn.scaleneg( b )
      def clip2( b: GE ) : GE      = cn.clip2( b )
      def excess( b: GE ) : GE     = cn.excess( b )
      def fold2( b: GE ) : GE      = cn.fold2( b )
      def wrap2( b: GE ) : GE      = cn.wrap2( b )
   //   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
   //   def firstarg( b: GE ) : GE          = cn.firstarg( b )

      def linlin( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) =
         cn.linlin( srcLo, srcHi, dstLo, dstHi )

      def linexp( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) =
         cn.linexp( srcLo, srcHi, dstLo, dstHi )
   }

//   sealed trait NAryGEOps2 extends NAryGEOps {
//      def round( b: GE ) : GE   = cn.round( b )
//      def roundup( b: GE ) : GE = cn.roundup( b )
//      def trunc( b: GE ) : GE   = cn.trunc( b )
//   }
}

// ---------------------------- Int ----------------------------

object RichInt {
   // ---- unary ops ----

   @inline def ri_abs( a: Int ) : Int        = math.abs( a )
   @inline def ri_signum( a: Int ) : Int     = math.signum( a )
   @inline def ri_squared( a: Int ) : Long   = { val n = a.toLong; n * n }
   @inline def ri_cubed( a: Int ) : Long     = { val n = a.toLong; n * n * n }

   // ---- binary ops ----
   @inline def ri_min( a: Int, b: Int ) : Int   = math.min( a, b )
   @inline def ri_max( a: Int, b: Int ) : Int   = math.max( a, b )
}
final case class RichInt private[synth]( i: Int )
extends RichNumber.UnaryFloatOps with RichNumber.NAryFloatOps with RichNumber.NAryDoubleOps with RichNumber.NAryGEOps {
   import RichInt._

   protected def f  = i.toFloat
   protected def d  = i.toDouble
   protected def cn = Constant( i.toFloat )

   // recover these from scala.runtime.RichFloat
//   def isInfinity: Boolean = java.lang.Float.isInfinite( x )
//   def isPosInfinity: Boolean = isInfinity && x > 0.0
//   def isNegInfinity: Boolean = isInfinity && x < 0.0

   // more unary ops
// def unary_- : Int       = -i
   def abs : Int	         = ri_abs( i )
//   def ceil : Float	      = math.ceil( i ).toFloat
//   def floor : Float	      = math.floor( i ).toFloat
//   def frac : Float	      = rf_frac( i )
   def signum : Int        = ri_signum( i )
   def squared : Long      = ri_squared( i )
   def cubed : Long        = ri_cubed( i )

   // more binary ops
   def min( b: Int ) : Int      = ri_min( i, b )
   def max( b: Int ) : Int      = ri_max( i, b )

   // recover these from scala.runtime.RichInt
   def until( end: Int ): Range                    = Range( i, end )
   def until( end: Int, step: Int ): Range         = Range( i, end, step )
   def to( end: Int ): Range.Inclusive             = Range.inclusive( i, end )
	def to( end: Int, step: Int ): Range.Inclusive  = Range.inclusive( i, end, step )

   def toBinaryString: String = java.lang.Integer.toBinaryString( i )
	def toHexString: String    = java.lang.Integer.toHexString( i )
	def toOctalString: String  = java.lang.Integer.toOctalString( i )
}

// ---------------------------- Float ----------------------------

object RichFloat {
   import RichNumber.LOG2

   // -------- unary ops --------

   @inline def rf_not( f: Float ) : Float          = if( f > 0f ) 0f else 1f
   @inline def rf_neg( f: Float ) : Float          = -f
   @inline def rf_abs( f: Float ) : Float          = math.abs( f )
   @inline def rf_ceil( f: Float ) : Float         = math.ceil( f ).toFloat
   @inline def rf_floor( f: Float ) : Float        = math.floor( f ).toFloat
   @inline def rf_frac( f: Float ) : Float         = (f - math.floor( f )).toFloat // according to jmc
   @inline def rf_signum( f: Float ) : Float       = math.signum( f )
   @inline def rf_squared( f: Float ) : Float      = f * f
   @inline def rf_cubed( f: Float ) : Float        = f * f * f
   @inline def rf_sqrt( f: Float ) : Float         = math.sqrt( f ).toFloat
   @inline def rf_exp( f: Float ) : Float          = math.exp( f ).toFloat
   @inline def rf_reciprocal( f: Float ) : Float   = 1.0f / f
   @inline def rf_midicps( f: Float ) : Float      = (440 * math.pow( 2, (f - 69) / 12 )).toFloat
   @inline def rf_cpsmidi( f: Float ) : Float      = (math.log( f / 440 ) / LOG2 * 12 + 69).toFloat
   @inline def rf_midiratio( f: Float ) : Float    = (math.pow( 2, f / 12 )).toFloat
   @inline def rf_ratiomidi( f: Float ) : Float    = (12 * math.log( f ) / LOG2).toFloat
   @inline def rf_dbamp( f: Float ) : Float        = (math.pow( 10, f * 0.05 )).toFloat
   @inline def rf_ampdb( f: Float ) : Float        = (math.log10( f )* 20).toFloat
   @inline def rf_octcps( f: Float ) : Float       = (440 * math.pow( 2, f - 4.75 )).toFloat
   @inline def rf_cpsoct( f: Float ) : Float       = (math.log( f / 440 ) / LOG2 + 4.75).toFloat
   @inline def rf_log( f: Float ) : Float          = math.log( f ).toFloat
   @inline def rf_log2( f: Float ) : Float         = (math.log( f ) / LOG2).toFloat
   @inline def rf_log10( f: Float ) : Float        = math.log10( f ).toFloat
   @inline def rf_sin( f: Float ) : Float          = math.sin( f ).toFloat
   @inline def rf_cos( f: Float ) : Float          = math.cos( f ).toFloat
   @inline def rf_tan( f: Float ) : Float          = math.tan( f ).toFloat
   @inline def rf_asin( f: Float ) : Float         = math.asin( f ).toFloat
   @inline def rf_acos( f: Float ) : Float         = math.acos( f ).toFloat
   @inline def rf_atan( f: Float ) : Float         = math.atan( f ).toFloat
   @inline def rf_sinh( f: Float ) : Float         = math.sinh( f ).toFloat
   @inline def rf_cosh( f: Float ) : Float         = math.cosh( f ).toFloat
   @inline def rf_tanh( f: Float ) : Float         = math.tanh( f ).toFloat

   @inline def rf_distort( f: Float ) : Float      = f / (1 + math.abs( f ))
   @inline def rf_softclip( f: Float ) : Float = {
      val absx = math.abs( f )
      if( absx <= 0.5f ) f else (absx - 0.25f) / f
   }
   @inline def rf_ramp( f: Float ) : Float         = if( f <= 0 ) 0 else if( f >= 1 ) 1 else f
   @inline def rf_scurve( f: Float ) : Float       = if( f <= 0 ) 0 else if( f > 1 ) 1 else f * f * (3 - 2 * f)

   // -------- binary ops --------

   @inline def rf_+( a: Float, b: Float ) : Float     = a + b
   @inline def rf_-( a: Float, b: Float ) : Float     = a - b
   @inline def rf_*( a: Float, b: Float ) : Float     = a * b
   @inline def rf_div( a: Float, b: Float ) : Int     = (a / b).toInt
   @inline def rf_/( a: Float, b: Float ) : Float     = a / b
   @inline def rf_%( a: Float, b: Float ) : Float     = a % b
   @inline def rf_===( a: Float, b: Float ) : Int     = if( a == b ) 1 else 0
   @inline def rf_!==( a: Float, b: Float ) : Int     = if( a != b ) 1 else 0
   @inline def rf_<( a: Float, b: Float ) : Boolean   = a < b
   @inline def rf_>( a: Float, b: Float ) : Boolean   = a > b
   @inline def rf_<=( a: Float, b: Float ) : Boolean  = a <= b
   @inline def rf_>=( a: Float, b: Float ) : Boolean  = a >= b
   @inline def rf_min( a: Float, b: Float ) : Float   = math.min( a, b )
   @inline def rf_max( a: Float, b: Float ) : Float   = math.max( a, b )
   @inline def rf_&( a: Float, b: Float ) : Float	   = a.toInt & b.toInt
   @inline def rf_|( a: Float, b: Float ) : Float	   = a.toInt | b.toInt
   @inline def rf_^( a: Float, b: Float ) : Float	   = a.toInt ^ b.toInt

   // lcm
   // gcd

   @inline def rf_round( a: Float, b: Float ) =
      if( b == 0 ) a else (math.floor( a / b + 0.5f ) * b).toFloat

   @inline def rf_roundup( a: Float, b: Float ) =
      if( b == 0 ) a else (math.ceil( a / b ) * b).toFloat

   @inline def rf_trunc( a: Float, b: Float ) =
      if( b == 0 ) a else (math.floor( a / b ) * b).toFloat

   @inline def rf_atan2( a: Float, b: Float ) : Float = math.atan2( a, b ).toFloat
   @inline def rf_hypot( a: Float, b: Float ) : Float = math.hypot( a, b ).toFloat

   @inline def rf_hypotx( a: Float, b: Float ) = {
      val minab = math.min( math.abs( a ), math.abs( b ))
      (a + b - (math.sqrt(2) - 1) * minab).toFloat
   }

   @inline def rf_pow( a: Float, b: Float ) : Float = math.pow( a, b ).toFloat

// <<
// >>
// UnsgnRghtShft
// fill

   @inline def rf_ring1( a: Float, b: Float ) =
      a * b + a

   @inline def rf_ring2( a: Float, b: Float ) =
      a * b + a + b

   @inline def rf_ring3( a: Float, b: Float ) =
      a * a * b

   @inline def rf_ring4( a: Float, b: Float ) = {
      val ab = a * b; a * ab - b * ab
   }

   @inline def rf_difsqr( a: Float, b: Float ) =
      a * a - b * b

   @inline def rf_sumsqr( a: Float, b: Float ) =
      a * a + b * b

   @inline def rf_sqrsum( a: Float, b: Float ) = {
      val z = a + b; z * z
   }

   @inline def rf_sqrdif( a: Float, b: Float ) = {
      val z = a - b; z * z
   }

   @inline def rf_absdif( a: Float, b: Float ) = math.abs( a - b )

   @inline def rf_thresh( a: Float, b: Float ) =
      if( a < b ) 0 else a

   @inline def rf_amclip( a: Float, b: Float ) =
      a * 0.5f * (b + math.abs( a ))

   @inline def rf_scaleneg( a: Float, b: Float ) =
      (math.abs( a ) - a) * (0.5f * b + 0.5f) + a

   @inline def rf_clip2( a: Float, b: Float ) =
      math.max( math.min( a, b ), -b )

   @inline def rf_excess( a: Float, b: Float ) =
      a - math.max( math.min( a, b ), -b )

   @inline def rf_fold2( a: Float, b: Float ) = rf_fold( a, -b, b )

   @inline def rf_wrap2( a: Float, b: Float ) = rf_wrap( a, -b, b )

   // -------- n-ary ops --------

   @inline def rf_fold( in: Float, lo: Float, hi: Float ) : Float = {
      val x = in - lo
      // avoid the divide if possible
      if( in >= hi ) {
         val f = hi + hi - in
         if (f >= lo) return f
      } else if( in < lo ) {
         val f = lo + lo - in
         if( f < hi ) return f
      } else return in

      if( hi == lo ) return lo
      // ok do the divide
      val range   = hi - lo
      val range2  = range + range
      val c       = x - range2 * math.floor( x / range2 ).toFloat
      lo + (if( c >= range ) range2 - c else c)
   }

   @inline def rf_wrap( in: Float, lo: Float, hi: Float ) : Float = {
      // avoid the divide if possible
      if( in >= hi ) {
         val range   = hi - lo
         val in2     = in - range;
         if( in2 < hi ) in2 else if( hi == lo ) lo else {
            in2 - range * math.floor( (in2 - lo) / range ).toFloat
         }
      } else if( in < lo ) {
         val range   = hi - lo
         val in2     = in + range
         if( in2 >= lo ) in2 else if( hi == lo ) lo else {
            in2 - range * math.floor( (in2 - lo) / range ).toFloat
         }
      } else in
   }

   @inline def rf_linlin( in: Float, srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float = {
      (in - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo
   }

   @inline def rf_linexp( in: Float, srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float = {
      math.pow( dstHi / dstLo, (in - srcLo) / (srcHi - srcLo)).toFloat * dstLo
   }
}
final case class RichFloat private[synth]( protected val f: Float )
extends RichNumber.UnaryFloatOps with RichNumber.NAryFloatOps with RichNumber.NAryDoubleOps with RichNumber.NAryGEOps {
   import RichFloat._

   protected def d  = f.toDouble
   protected def cn = Constant( f )

   // recover these from scala.runtime.RichFloat
   def isInfinity: Boolean    = java.lang.Float.isInfinite( f )
   def isPosInfinity: Boolean = isInfinity && f > 0.0
   def isNegInfinity: Boolean = isInfinity && f < 0.0

   // more unary ops
// def unary_- : Float     = -f
   def abs : Float	      = rf_abs( f )
   def ceil : Float	      = rf_ceil( f )
   def floor : Float	      = rf_floor( f )
   def frac : Float	      = rf_frac( f )
   def signum : Float      = rf_signum( f )
   def squared : Float     = rf_squared( f )
   def cubed : Float       = rf_cubed( f )

   // more binary ops
//   def round( b: Float ) : Float    = rf_round( f, b )
//   def roundup( b: Float ) : Float  = rf_roundup( f, b )
//   def trunc( b: Float ) : Float    = rf_trunc( f, b )
}

// ---------------------------- Double ----------------------------

object RichDouble {
   import RichNumber.LOG2

   // -------- unary ops --------

   @inline def rd_neg( d: Double ) : Double	      = -d
   @inline def rd_abs( d: Double ) : Double	      = math.abs( d )
   @inline def rd_ceil( d: Double ) : Double       = math.ceil( d )
   @inline def rd_floor( d: Double ) : Double	   = math.floor( d )
   @inline def rd_frac( d: Double ) : Double	      = (d - math.floor( d )) // according to jmc
   @inline def rd_signum( d: Double ) : Double     = math.signum( d )
   @inline def rd_squared( d: Double ) : Double    = d * d
   @inline def rd_cubed( d: Double ) : Double      = d * d * d
   @inline def rd_sqrt( d: Double ) : Double       = math.sqrt( d )
   @inline def rd_exp( d: Double ) : Double        = math.exp( d )
   @inline def rd_reciprocal( d: Double ) : Double = 1.0 / d
   @inline def rd_midicps( d: Double ) : Double    = (440 * math.pow( 2, (d - 69) / 12 ))
   @inline def rd_cpsmidi( d: Double ) : Double    = (math.log( d / 440 ) / LOG2 * 12 + 69)
   @inline def rd_midiratio( d: Double ) : Double  = (math.pow( 2, d / 12 ))
   @inline def rd_ratiomidi( d: Double ) : Double  = (12 * math.log( d ) / LOG2)
   @inline def rd_dbamp( d: Double ) : Double      = (math.pow( 10, d * 0.05 ))
   @inline def rd_ampdb( d: Double ) : Double      = (math.log10( d ) * 20)
   @inline def rd_octcps( d: Double ) : Double     = (440 * math.pow( 2, d - 4.75 ))
   @inline def rd_cpsoct( d: Double ) : Double     = (math.log( d / 440 ) / LOG2 + 4.75)
   @inline def rd_log( d: Double ) : Double        = math.log( d )
   @inline def rd_log2( d: Double ) : Double       = (math.log( d ) / LOG2)
   @inline def rd_log10( d: Double ) : Double      = math.log10( d )
   @inline def rd_sin( d: Double ) : Double        = math.sin( d )
   @inline def rd_cos( d: Double ) : Double        = math.cos( d )
   @inline def rd_tan( d: Double ) : Double        = math.tan( d )
   @inline def rd_asin( d: Double ) : Double       = math.asin( d )
   @inline def rd_acos( d: Double ) : Double       = math.acos( d )
   @inline def rd_atan( d: Double ) : Double       = math.atan( d )
   @inline def rd_sinh( d: Double ) : Double       = math.sinh( d )
   @inline def rd_cosh( d: Double ) : Double       = math.cosh( d )
   @inline def rd_tanh( d: Double ) : Double       = math.tanh( d )
//   @inline def rd_distort( d: Double ) : Double    = d / (1 + math.abs( d ))
//   @inline def rd_softclip( d: Double ) : Double   = { val absx = math.abs( d ); if( absx <= 0.5 ) d else (absx - 0.25) / d}
//   @inline def rd_ramp( d: Double ) : Double       = if( d <= 0 ) 0 else if( d >= 1 ) 1 else d
//   @inline def rd_scurve( d: Double ) : Double     = if( d <= 0 ) 0 else if( d > 1 ) 1 else d * d * (3 - 2 * d)
}
final class RichDouble private[synth]( protected val d: Double )
extends RichNumber.NAryDoubleOps with RichNumber.NAryGEOps {
   import RichDouble._

   protected def cn = Constant( d.toFloat )

   // recover these from scala.runtime.RichDouble
   def isInfinity: Boolean    = java.lang.Double.isInfinite( d )
   def isPosInfinity: Boolean = isInfinity && d > 0.0
   def isNegInfinity: Boolean = isInfinity && d < 0.0

   // unary ops
//   def unary_- : Double    = -d
   def abs : Double	      = rd_abs( d )
   def ceil : Double	      = rd_ceil( d )
   def floor : Double	   = rd_floor( d )
   def frac : Double	      = rd_frac( d )
   def signum : Double     = rd_signum( d )
   def squared : Double    = rd_squared( d )
   def cubed : Double      = rd_cubed( d )
   def sqrt : Double       = rd_sqrt( d )
   def exp : Double        = rd_exp( d )
   def reciprocal : Double = rd_reciprocal( d )
   def midicps : Double    = rd_midicps( d )
   def cpsmidi : Double    = rd_cpsmidi( d )
   def midiratio : Double  = rd_midiratio( d )
   def ratiomidi : Double  = rd_ratiomidi( d )
   def dbamp : Double      = rd_dbamp( d )
   def ampdb : Double      = rd_ampdb( d )
   def octcps : Double     = rd_octcps( d )
   def cpsoct : Double     = rd_cpsoct( d )
   def log : Double        = rd_log( d )
   def log2 : Double       = rd_log2( d )
   def log10 : Double      = rd_log10( d )
   def sin : Double        = rd_sin( d )
   def cos : Double        = rd_cos( d )
   def tan : Double        = rd_tan( d )
   def asin : Double       = rd_asin( d )
   def acos : Double       = rd_acos( d )
   def atan : Double       = rd_atan( d )
   def sinh : Double       = rd_sinh( d )
   def cosh : Double       = rd_cosh( d )
   def tanh : Double       = rd_tanh( d )
//   def distort : Double    = rd_distort( d )
//   def softclip : Double   = rd_softclip( d )
//   def ramp : Double       = rd_ramp( d )
//   def scurve : Double     = rd_scurve( d )
}