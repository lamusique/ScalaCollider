/*
 *  RichNumber.scala
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

import collection.immutable.NumericRange

// ---------------------------- Int ----------------------------

final case class RichInt private[synth]( i: Int )
extends Proxy with Ordered[ Int ] with UnaryFloatOps with NAryFloatOps with NAryDoubleOps with NAryGEOps {
   protected def f  = i.toFloat
   protected def d  = i.toDouble
   protected def cn = Constant( i.toFloat )

   // Proxy
   def self: Any = i

   // Ordered
   def compare( that: Int ): Int = if( i < that ) -1 else if( i > that ) 1 else 0

   // recover these from scala.runtime.RichFloat
//   def isInfinity: Boolean = java.lang.Float.isInfinite( x )
//   def isPosInfinity: Boolean = isInfinity && x > 0.0
//   def isNegInfinity: Boolean = isInfinity && x < 0.0

   // more unary ops
// def unary_- : Int       = -i
   def abs : Int	         = math.abs( i )
//   def ceil : Float	      = math.ceil( i ).toFloat
//   def floor : Float	      = math.floor( i ).toFloat
//   def frac : Float	      = rf_frac( i )
   def signum : Int        = math.signum( i )
//   def squared : Float     = i * i // or should we use Float to avoid overflow?
//   def cubed : Float       = i * i * i // or should we use Float to avoid overflow?

   // more binary ops
   def min( b: Int ) : Int      = if( i < b ) i else b // math.min( f, b )
   def max( b: Int ) : Int      = if( i > b ) i else b // math.max( f, b )

   // recover these from scala.runtime.RichInt
   def until( end: Int ): Range = Range( i, end )
   def until( end: Int, step: Int ): Range = Range( i, end, step )
   def to( end: Int ): Range.Inclusive = Range.inclusive( i, end )
	def to( end: Int, step: Int ): Range.Inclusive = Range.inclusive( i, end, step )
	
   def toBinaryString: String = java.lang.Integer.toBinaryString( i )
	def toHexString: String = java.lang.Integer.toHexString( i )
	def toOctalString: String = java.lang.Integer.toOctalString( i )
}

// ---------------------------- Float ----------------------------

object RichFloat {
   import RichDouble.LOG2

   @inline private[synth] def rf_fold( in: Float, lo: Float, hi: Float ) : Float = {
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

   @inline private[synth] def rf_wrap( in: Float, lo: Float, hi: Float ) : Float = {
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

   @inline private[synth] def rf_not( f: Float ) = if( f > 0f ) 0f else 1f
   @inline private[synth] def rf_ceil( f: Float ) = math.ceil( f ).toFloat
   @inline private[synth] def rf_floor( f: Float ) = math.floor( f ).toFloat
   @inline private[synth] def rf_frac( f: Float ) = (f - math.floor( f )).toFloat // according to jmc
   @inline private[synth] def rf_squared( f: Float ) = f * f
   @inline private[synth] def rf_cubed( f: Float ) = f * f * f
   @inline private[synth] def rf_sqrt( f: Float ) = math.sqrt( f ).toFloat
   @inline private[synth] def rf_exp( f: Float ) = math.exp( f ).toFloat
   @inline private[synth] def rf_reciprocal( f: Float ) = 1.0f / f
   @inline private[synth] def rf_midicps( f: Float ) = (440 * math.pow( 2, (f - 69) / 12 )).toFloat
   @inline private[synth] def rf_cpsmidi( f: Float ) = (math.log( f / 440 ) / LOG2 * 12 + 69).toFloat
   @inline private[synth] def rf_midiratio( f: Float ) = (math.pow( 2, f / 12 )).toFloat
   @inline private[synth] def rf_ratiomidi( f: Float ) = (12 * math.log( f ) / LOG2).toFloat
   @inline private[synth] def rf_dbamp( f: Float ) = (math.pow( 10, f * 0.05 )).toFloat
   @inline private[synth] def rf_ampdb( f: Float ) = (math.log10( f )* 20).toFloat
   @inline private[synth] def rf_octcps( f: Float ) = (440 * math.pow( 2, f - 4.75 )).toFloat
   @inline private[synth] def rf_cpsoct( f: Float ) = (math.log( f / 440 ) / LOG2 + 4.75).toFloat
   @inline private[synth] def rf_log( f: Float ) = math.log( f ).toFloat
   @inline private[synth] def rf_log2( f: Float ) = (math.log( f ) / LOG2).toFloat
   @inline private[synth] def rf_log10( f: Float ) = math.log10( f ).toFloat
   @inline private[synth] def rf_sin( f: Float ) = math.sin( f ).toFloat
   @inline private[synth] def rf_cos( f: Float ) = math.cos( f ).toFloat
   @inline private[synth] def rf_tan( f: Float ) = math.tan( f ).toFloat
   @inline private[synth] def rf_asin( f: Float ) = math.asin( f ).toFloat
   @inline private[synth] def rf_acos( f: Float ) = math.acos( f ).toFloat
   @inline private[synth] def rf_atan( f: Float ) = math.atan( f ).toFloat
   @inline private[synth] def rf_sinh( f: Float ) = math.sinh( f ).toFloat
   @inline private[synth] def rf_cosh( f: Float ) = math.cosh( f ).toFloat
   @inline private[synth] def rf_tanh( f: Float ) = math.tanh( f ).toFloat

   @inline private[synth] def rf_distort( f: Float ) = f / (1 + math.abs( f ))
   @inline private[synth] def rf_softclip( f: Float ) = { val absx = math.abs( f ); if( absx <= 0.5f ) f else (absx - 0.25f) / f}
   @inline private[synth] def rf_ramp( f: Float ) = if( f <= 0 ) 0 else if( f >= 1 ) 1 else f
   @inline private[synth] def rf_scurve( f: Float ) = if( f <= 0 ) 0 else if( f > 1 ) 1 else f * f * (3 - 2 * f)

   @inline private[synth] def rf_round( a: Float, b: Float ) =
      if( b == 0 ) a else (math.floor( a / b + 0.5f ) * b).toFloat

   @inline private[synth] def rf_roundup( a: Float, b: Float ) =
      if( b == 0 ) a else (math.ceil( a / b ) * b).toFloat

   @inline private[synth] def rf_trunc( a: Float, b: Float ) =
      if( b == 0 ) a else (math.floor( a / b ) * b).toFloat

   @inline private[synth] def rf_hypotx( a: Float, b: Float ) = {
      val minab = math.min( math.abs( a ), math.abs( b ))
      (a + b - (math.sqrt(2) - 1) * minab).toFloat
   }

   @inline private[synth] def rf_ring1( a: Float, b: Float ) =
      a * b + a

   @inline private[synth] def rf_ring2( a: Float, b: Float ) =
      a * b + a + b

   @inline private[synth] def rf_ring3( a: Float, b: Float ) =
      a * a * b

   @inline private[synth] def rf_ring4( a: Float, b: Float ) = {
      val ab = a * b; a * ab - b * ab
   }

   @inline private[synth] def rf_difsqr( a: Float, b: Float ) =
      a * a - b * b

   @inline private[synth] def rf_sumsqr( a: Float, b: Float ) =
      a * a + b * b

   @inline private[synth] def rf_sqrsum( a: Float, b: Float ) = {
      val z = a + b; z * z
   }

   @inline private[synth] def rf_sqrdif( a: Float, b: Float ) = {
      val z = a - b; z * z
   }

   @inline private[synth] def rf_absdif( a: Float, b: Float ) = math.abs( a - b )

   @inline private[synth] def rf_thresh( a: Float, b: Float ) =
      if( a < b ) 0 else a

   @inline private[synth] def rf_amclip( a: Float, b: Float ) =
      a * 0.5f * (b + math.abs( a ))

   @inline private[synth] def rf_scaleneg( a: Float, b: Float ) =
      (math.abs( a ) - a) * (0.5f * b + 0.5f) + a

   @inline private[synth] def rf_clip2( a: Float, b: Float ) =
      math.max( math.min( a, b ), -b )

   @inline private[synth] def rf_excess( a: Float, b: Float ) =
      a - math.max( math.min( a, b ), -b )

   @inline private[synth] def rf_fold2( a: Float, b: Float ) = rf_fold( a, -b, b )

   @inline private[synth] def rf_wrap2( a: Float, b: Float ) = rf_wrap( a, -b, b )

   @inline private[synth] def rf_linlin( in: Float, srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float = {
      (in - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo
   }

   @inline private[synth] def rf_linexp( in: Float, srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float = {
      math.pow( dstHi / dstLo, (in - srcLo) / (srcHi - srcLo)).toFloat * dstLo
   }
}

sealed trait NAryFloatOps {
   import RichFloat._
   
   protected def f: Float

   // binary ops
// def +( b: Float ) : Float        = f + b.f
// def -( b: Float ) : Float        = f - b.f
// def *( b: Float ) : Float        = f * b.f
// def /( b: Float ) : Float        = f / b.f
// def %( b: Float ) : Float        = f % b.f
// def ===( b: Float ) : Int        = if( f == b ) 1 else 0
// def !==( b: Float ) : Int        = if( f != b ) 1 else 0
// def <( b: Float ) : Float	      = f < b.f
// def >( b: Float ) : Float	      = f > b.f
// def <=( b: Float ) : Float	      = f <= b.f
// def >=( b: Float ) : Float	      = f >= b.f
   def min( b: Float ) : Float      = math.min( f, b )
   def max( b: Float ) : Float      = math.max( f, b )
// def &( b: Float ) : Float	      = f.toInt & b.f.toInt
// def |( b: Float ) : Float	      = f.toInt | b.f.toInt
// def ^( b: Float ) : Float	      = f.toInt ^ b.f.toInt
   def atan2( b: Float ) : Float    = math.atan2( f, b ).toFloat
   def hypot( b: Float ) : Float    = math.hypot( f, b ).toFloat
   def hypotx( b: Float ) : Float   = rf_hypotx( f, b )
   def pow( b: Float ) : Float      = math.pow( f, b ).toFloat
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

final case class RichFloat private[synth]( protected val f: Float )
extends Proxy with Ordered[ Float ] with UnaryFloatOps with NAryFloatOps with NAryDoubleOps2 with NAryGEOps2 {
   import RichFloat._

   protected def d  = f.toDouble
   protected def cn = Constant( f )

   // Proxy
   def self: Any = f

   // Ordered
   def compare( b: Float ): Int = java.lang.Float.compare( f, b )

   // recover these from scala.runtime.RichFloat
   def isInfinity: Boolean = java.lang.Float.isInfinite( f )
   def isPosInfinity: Boolean = isInfinity && f > 0.0
   def isNegInfinity: Boolean = isInfinity && f < 0.0

   // more unary ops
// def unary_- : Float     = -f
   def abs : Float	      = math.abs( f )
   def ceil : Float	      = math.ceil( f ).toFloat
   def floor : Float	      = math.floor( f ).toFloat
   def frac : Float	      = rf_frac( f )
   def signum : Float      = math.signum( f )
   def squared : Float     = rf_squared( f )
   def cubed : Float       = rf_cubed( f )

   // more binary ops
   def round( b: Float ) : Float    = rf_round( f, b )
   def roundup( b: Float ) : Float  = rf_roundup( f, b )
   def trunc( b: Float ) : Float    = rf_trunc( f, b )
}

// ---------------------------- Double ----------------------------

object RichDouble {
   private[synth] val LOG2 = math.log( 2 ) 

   @inline private[synth] def rd_fold( in: Double, lo: Double, hi: Double ) : Double = {
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

   @inline private[synth] def rd_wrap( in: Double, lo: Double, hi: Double ) : Double = {
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

   @inline private[synth] def rd_round( a: Double, b: Double ) =
      if( b == 0 ) a else math.floor( a / b + 0.5f ) * b

   @inline private[synth] def rd_roundup( a: Double, b: Double ) =
      if( b == 0 ) a else math.ceil( a / b ) * b

   @inline private[synth] def rd_trunc( a: Double, b: Double ) =
      if( b == 0 ) a else math.floor( a / b ) * b

   @inline private[synth] def rd_hypotx( a: Double, b: Double ) = {
      val minab = math.min( math.abs( a ), math.abs( b ))
      a + b - (math.sqrt(2) - 1) * minab
   }

   @inline private[synth] def rd_ring1( a: Double, b: Double ) =
      a * b + a

   @inline private[synth] def rd_ring2( a: Double, b: Double ) =
      a * b + a + b

   @inline private[synth] def rd_ring3( a: Double, b: Double ) =
      a * a * b

   @inline private[synth] def rd_ring4( a: Double, b: Double ) = {
      val ab = a * b; a * ab - b * ab
   }

   @inline private[synth] def rd_difsqr( a: Double, b: Double ) =
      a * a - b * b

   @inline private[synth] def rd_sumsqr( a: Double, b: Double ) =
      a * a + b * b

   @inline private[synth] def rd_sqrsum( a: Double, b: Double ) = {
      val z = a + b; z * z
   }

   @inline private[synth] def rd_sqrdif( a: Double, b: Double ) = {
      val z = a - b; z * z
   }

   @inline private[synth] def rd_absdif( a: Double, b: Double ) = math.abs( a - b )

   @inline private[synth] def rd_thresh( a: Double, b: Double ) =
      if( a < b ) 0 else a

   @inline private[synth] def rd_amclip( a: Double, b: Double ) =
      a * 0.5 * (b + math.abs( a ))

   @inline private[synth] def rd_scaleneg( a: Double, b: Double ) =
      (math.abs( a ) - a) * (0.5 * b + 0.5) + a

   @inline private[synth] def rd_clip2( a: Double, b: Double ) =
      math.max( math.min( a, b ), -b )

   @inline private[synth] def rd_excess( a: Double, b: Double ) =
      a - math.max( math.min( a, b ), -b )

   @inline private[synth] def rd_fold2( a: Double, b: Double ) = rd_fold( a, -b, b )

   @inline private[synth] def rd_wrap2( a: Double, b: Double ) = rd_wrap( a, -b, b )

   @inline private[synth] def rd_linlin( in: Double, srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double = {
      (in - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo
   }

   @inline private[synth] def rd_linexp( in: Double, srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double = {
      math.pow( dstHi / dstLo, (in - srcLo) / (srcHi - srcLo)) * dstLo
   }
}

sealed trait NAryDoubleOps {
   import RichDouble._

   protected def d: Double

   // recover these from scala.runtime.RichDouble
   def until( end: Double ): Range.Partial[ Double, NumericRange[ Double ]] = new Range.Partial( until( end, _ ))
   def until( end: Double, step: Double ): NumericRange[ Double ] = Range.Double( d, end, step )
	def to( end: Double ): Range.Partial[ Double, NumericRange[ Double ]] = new Range.Partial( to( end, _ ))
	def to( end: Double, step: Double ): NumericRange[ Double ] = Range.Double.inclusive( d, end, step )
   
   // binary ops
   def min( b: Double ) : Double       = math.min( d, b )
   def max( b: Double ) : Double       = math.max( d, b )
   def atan2( b: Double ) : Double     = math.atan2( d, b )
   def hypot( b: Double ) : Double     = math.hypot( d, b )
   def hypotx( b: Double ) : Double    = rd_hypotx( d, b )
   def pow( b: Double ) : Double       = math.pow( d, b )
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

sealed trait NAryDoubleOps2 extends NAryDoubleOps {
   import RichDouble._

   def round( b: Double ) : Double     = rd_round( d, b )
   def roundup( b: Double ) : Double   = rd_roundup( d, b )
   def trunc( b: Double ) : Double     = rd_trunc( d, b )
}

sealed trait NAryGEOps {
   import RichDouble._

   protected def cn: Constant

   // binary ops
   def -( b: AnyGE )                 = cn.-( b )
   def *( b: AnyGE )                 = cn.*( b )
   def /( b: AnyGE )                 = cn./( b )
   def %( b: AnyGE )                 = cn.%( b )
   def ===( b: AnyGE )               = cn.===( b )
   def !==( b: AnyGE )               = cn.!==( b )
   def <( b: AnyGE )                 = cn.<( b )
   def >( b: AnyGE )                 = cn.>( b )
   def <=( b: AnyGE )                = cn.<=( b )
   def >=( b: AnyGE )                = cn.>=( b )
   def &( b: AnyGE )                 = cn.&( b )
   def |( b: AnyGE )                 = cn.|( b )
   def ^( b: AnyGE )                 = cn.^( b )
   def atan2( b: AnyGE )             = cn.atan2( b )
   def hypot( b: AnyGE )             = cn.hypot( b )
   def hypotx( b: AnyGE )            = cn.hypotx( b )
   def pow( b: AnyGE )               = cn.pow( b )
   def ring1( b: AnyGE )             = cn.ring1( b )
   def ring2( b: AnyGE )             = cn.ring2( b )
   def ring3( b: AnyGE )             = cn.ring3( b )
   def ring4( b: AnyGE )             = cn.ring4( b )
   def difsqr( b: AnyGE )            = cn.difsqr( b )
   def sumsqr( b: AnyGE )            = cn.sumsqr( b )
   def sqrsum( b: AnyGE )            = cn.sqrsum( b )
   def sqrdif( b: AnyGE )            = cn.sqrdif( b )
   def absdif( b: AnyGE )            = cn.absdif( b )
   def thresh( b: AnyGE )            = cn.thresh( b )
   def amclip( b: AnyGE )            = cn.amclip( b )
   def scaleneg( b: AnyGE )          = cn.scaleneg( b )
   def clip2( b: AnyGE )             = cn.clip2( b )
   def excess( b: AnyGE )            = cn.excess( b )
   def fold2( b: AnyGE )             = cn.fold2( b )
   def wrap2( b: AnyGE )             = cn.wrap2( b )
//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def firstarg( b: AnyGE )          = cn.firstarg( b )

   def linlin( srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE ) =
      cn.linlin( srcLo, srcHi, dstLo, dstHi )

   def linexp( srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE ) =
      cn.linexp( srcLo, srcHi, dstLo, dstHi )
}

sealed trait NAryGEOps2 extends NAryGEOps {
   def round( b: AnyGE )             = cn.round( b )
   def roundup( b: AnyGE )           = cn.roundup( b )
   def trunc( b: AnyGE )             = cn.trunc( b )
}

final class RichDouble private[synth]( protected val d: Double )
extends Proxy with Ordered[ Double ] with NAryDoubleOps2 with NAryGEOps2 {
   import RichDouble.LOG2

   protected def cn = Constant( d.toFloat )
   
   // Proxy
   def self: Any = d

   // Ordered
   def compare( b: Double ): Int = java.lang.Double.compare( d, b )

   // recover these from scala.runtime.RichDouble
   def isInfinity: Boolean = java.lang.Double.isInfinite( d )
   def isPosInfinity: Boolean = isInfinity && d > 0.0
   def isNegInfinity: Boolean = isInfinity && d < 0.0

   // unary ops
//   def unary_- : Double    = -d
   def abs : Double	      = math.abs( d )
   def ceil : Double	      = math.ceil( d )
   def floor : Double	   = math.floor( d )
   def frac : Double	      = (d - math.floor( d )) // according to jmc
   def signum : Double     = math.signum( d )
   def squared : Double    = d * d
   def cubed : Double      = d * d * d
   def sqrt : Double       = math.sqrt( d )
   def exp : Double        = math.exp( d )
   def reciprocal : Double = 1.0 / d
   def midicps : Double    = (440 * math.pow( 2, (d - 69) / 12 ))
   def cpsmidi : Double    = (math.log( d / 440 ) / LOG2 * 12 + 69)
   def midiratio : Double  = (math.pow( 2, d / 12 ))
   def ratiomidi : Double  = (12 * math.log( d ) / LOG2)
   def dbamp : Double      = (math.pow( 10, d * 0.05 ))
   def ampdb : Double      = (math.log10( d ) * 20)
   def octcps : Double     = (440 * math.pow( 2, d - 4.75 ))
   def cpsoct : Double     = (math.log( d / 440 ) / LOG2 + 4.75)
   def log : Double        = math.log( d )
   def log2 : Double       = (math.log( d ) / LOG2)
   def log10 : Double      = math.log10( d )
   def sin : Double        = math.sin( d )
   def cos : Double        = math.cos( d )
   def tan : Double        = math.tan( d )
   def asin : Double       = math.asin( d )
   def acos : Double       = math.acos( d )
   def atan : Double       = math.atan( d )
   def sinh : Double       = math.sinh( d )
   def cosh : Double       = math.cosh( d )
   def tanh : Double       = math.tanh( d )
//   def distort : Double    = d / (1 + math.abs( d ))
//   def softclip : Double   = { val absx = math.abs( d ); if( absx <= 0.5 ) d else (absx - 0.25) / d}
//   def ramp : Double       = if( d <= 0 ) 0 else if( d >= 1 ) 1 else d
//   def scurve : Double     = if( d <= 0 ) 0 else if( d > 1 ) 1 else d * d * (3 - 2 * d)
}