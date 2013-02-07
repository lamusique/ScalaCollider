/*
 *  RichNumber.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
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

package de.sciss
package synth

import collection.immutable.NumericRange

object RichNumber {
   /**
    * This would be better `RichFloat.NAryOps`, but scalac can't handle the case
    * op `class RichFloat extends RichFloat.NAryOps` (says it's cyclic) -- damn...
    */
   sealed trait NAryFloatOps extends Any {
      import synth.{FloatFun => rf}

      protected def f: Float

      // -------- binary ops --------

// def +( b: Float ) : Float        = rf.+( f, b )
// def -( b: Float ) : Float        = rf.-( f, b )
// def *( b: Float ) : Float        = rf.*( f, b )
      def div( b: Float ) : Int     = rf.div( f, b )
// def /( b: Float ) : Float        = rf./( f, b )
// def %( b: Float ) : Float        = rf.%( f, b )
// def ===( b: Float ) : Int        = rf.===( f, b )
// def !==( b: Float ) : Int        = rf.!==( f, b )
// def <( b: Float ) : Float	      = rf.<( f, b )
// def >( b: Float ) : Float	      = rf.>( f, b )
// def <=( b: Float ) : Float	      = rf.<=( f, b )
// def >=( b: Float ) : Float	      = rf.>=( f, b )
      def min( b: Float ) : Float      = rf.min( f, b )
      def max( b: Float ) : Float      = rf.max( f, b )
// def &( b: Float ) : Float	      = rf.&( f, b )
// def |( b: Float ) : Float	      = rf.|( f, b )
// def ^( b: Float ) : Float	      = rf.^( f, b )
      def round( b: Float ) : Float    = rf.round( f, b )
      def roundup( b: Float ) : Float  = rf.roundup( f, b )
      def trunc( b: Float ) : Float    = rf.trunc( f, b )
      def atan2( b: Float ) : Float    = rf.atan2( f, b )
      def hypot( b: Float ) : Float    = rf.hypot( f, b )
      def hypotx( b: Float ) : Float   = rf.hypotx( f, b )
      def pow( b: Float ) : Float      = rf.pow( f, b )
      def ring1( b: Float ) : Float    = rf.ring1( f, b )
      def ring2( b: Float ) : Float    = rf.ring2( f, b )
      def ring3( b: Float ) : Float    = rf.ring3( f, b )
      def ring4( b: Float ) : Float    = rf.ring4( f, b )
      def difsqr( b: Float ) : Float   = rf.difsqr( f, b )
      def sumsqr( b: Float ) : Float   = rf.sumsqr( f, b )
      def sqrsum( b: Float ) : Float   = rf.sqrsum( f, b )
      def sqrdif( b: Float ) : Float   = rf.sqrdif( f, b )
      def absdif( b: Float ) : Float   = rf.absdif( f, b )
      def thresh( b: Float ) : Float   = rf.thresh( f, b )
      def amclip( b: Float ) : Float   = rf.amclip( f, b )
      def scaleneg( b: Float ) : Float = rf.scaleneg( f, b )
      def clip2( b: Float ) : Float    = rf.clip2( f, b )
      def excess( b: Float ) : Float   = rf.excess( f, b )
      def fold2( b: Float ) : Float    = rf.fold2( f, b )
      def wrap2( b: Float ) : Float    = rf.wrap2( f, b )
// def firstarg( b: Float ) : Float = d

      def linlin( srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float =
         rf.linlin( f, srcLo, srcHi, dstLo, dstHi )

      def linexp( srcLo: Float, srcHi: Float, dstLo: Float, dstHi: Float ) : Float =
         rf.linexp( f, srcLo, srcHi, dstLo, dstHi )
   }

   sealed trait UnaryFloatOps extends Any {
      import synth.{FloatFun => rf}

      protected def f: Float

      // unary ops
      def sqrt : Float        = rf.sqrt( f )
      def exp : Float         = rf.exp( f )
      def reciprocal : Float  = rf.reciprocal( f )
      def midicps : Float     = rf.midicps( f )
      def cpsmidi : Float     = rf.cpsmidi( f )
      def midiratio : Float   = rf.midiratio( f )
      def ratiomidi : Float   = rf.ratiomidi( f )
      def dbamp : Float       = rf.dbamp( f )
      def ampdb : Float       = rf.ampdb( f )
      def octcps : Float      = rf.octcps( f )
      def cpsoct : Float      = rf.cpsoct( f )
      def log : Float         = rf.log( f )
      def log2 : Float        = rf.log2( f )
      def log10 : Float       = rf.log10( f )
      def sin : Float         = rf.sin( f )
      def cos : Float         = rf.cos( f )
      def tan : Float         = rf.tan( f )
      def asin : Float        = rf.asin( f )
      def acos : Float        = rf.acos( f )
      def atan : Float        = rf.atan( f )
      def sinh : Float        = rf.sinh( f )
      def cosh : Float        = rf.cosh( f )
      def tanh : Float        = rf.tanh( f )
// def distort : Float     = f / (1 + math.abs( f ))
// def softclip : Float    = { val absx = math.abs( f ); if( absx <= 0.5f ) f else (absx - 0.25f) / f}
// def ramp : Float        = if( f <= 0 ) 0 else if( f >= 1 ) 1 else f
// def scurve : Float      = if( f <= 0 ) 0 else if( f > 1 ) 1 else f * f * (3 - 2 * f)
   }

   sealed trait NAryDoubleOps extends Any {
      import synth.{DoubleFun => rd}

      protected def d: Double

      // recover these from scala.runtime.RichDouble
      def until( end: Double ): Range.Partial[ Double, NumericRange[ Double ]] = new Range.Partial( until( end, _ ))
      def until( end: Double, step: Double ): NumericRange[ Double ] = Range.Double( d, end, step )
      def to( end: Double ): Range.Partial[ Double, NumericRange[ Double ]] = new Range.Partial( to( end, _ ))
      def to( end: Double, step: Double ): NumericRange[ Double ] = Range.Double.inclusive( d, end, step )

      // binary ops
      def min( b: Double ) : Double       = rd.min( d, b )
      def max( b: Double ) : Double       = rd.max( d, b )
      def round( b: Double ) : Double     = rd.round( d, b )
      def roundup( b: Double ) : Double   = rd.roundup( d, b )
      def trunc( b: Double ) : Double     = rd.trunc( d, b )
      def atan2( b: Double ) : Double     = rd.atan2( d, b )
      def hypot( b: Double ) : Double     = rd.hypot( d, b )
      def hypotx( b: Double ) : Double    = rd.hypotx( d, b )
      def pow( b: Double ) : Double       = rd.pow( d, b )
      def ring1( b: Double ) : Double     = rd.ring1( d, b )
      def ring2( b: Double ) : Double     = rd.ring2( d, b )
      def ring3( b: Double ) : Double     = rd.ring3( d, b )
      def ring4( b: Double ) : Double     = rd.ring4( d, b )
      def difsqr( b: Double ) : Double    = rd.difsqr( d, b )
      def sumsqr( b: Double ) : Double    = rd.sumsqr( d, b )
      def sqrsum( b: Double ) : Double    = rd.sqrsum( d, b )
      def sqrdif( b: Double ) : Double    = rd.sqrdif( d, b )
      def absdif( b: Double ) : Double    = rd.absdif( d, b )
      def thresh( b: Double ) : Double    = rd.thresh( d, b )
      def amclip( b: Double ) : Double    = rd.amclip( d, b )
      def scaleneg( b: Double ) : Double  = rd.scaleneg( d, b )
      def clip2( b: Double ) : Double     = rd.clip2( d, b )
      def excess( b: Double ) : Double    = rd.excess( d, b )
      def fold2( b: Double ) : Double     = rd.fold2( d, b )
      def wrap2( b: Double ) : Double     = rd.wrap2( d, b )
   // def firstarg( b: Double ) : Double  = d

      def linlin( srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double =
         rd.linlin( d, srcLo, srcHi, dstLo, dstHi )

      def linexp( srcLo: Double, srcHi: Double, dstLo: Double, dstHi: Double ) : Double =
         rd.linexp( d, srcLo, srcHi, dstLo, dstHi )
   }

//   sealed trait NAryDoubleOps2 extends NAryDoubleOps {
//      def round( b: Double ) : Double     = rd.round( d, b )
//      def roundup( b: Double ) : Double   = rd.roundup( d, b )
//      def trunc( b: Double ) : Double     = rd.trunc( d, b )
//   }

   // ---- Constant / GE ----

   sealed trait NAryGEOps extends Any {
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

final class RichInt private[synth](val i: Int ) extends AnyVal
with RichNumber.UnaryFloatOps with RichNumber.NAryFloatOps with RichNumber.NAryDoubleOps with RichNumber.NAryGEOps {
   import synth.{IntFun => ri}

   protected def f  = i.toFloat
   protected def d  = i.toDouble
   protected def cn = Constant( i.toFloat )

   // recover these from scala.runtime.RichFloat
//   def isInfinity: Boolean = java.lang.Float.isInfinite( x )
//   def isPosInfinity: Boolean = isInfinity && x > 0.0
//   def isNegInfinity: Boolean = isInfinity && x < 0.0

   // more unary ops
// def unary_- : Int       = -i
   def abs : Int	         = ri.abs( i )
//   def ceil : Float	      = math.ceil( i ).toFloat
//   def floor : Float	      = math.floor( i ).toFloat
//   def frac : Float	      = rf.frac( i )
   def signum : Int        = ri.signum( i )
   def squared : Long      = ri.squared( i )
   def cubed : Long        = ri.cubed( i )

   // more binary ops
   def min( b: Int ) : Int      = ri.min( i, b )
   def max( b: Int ) : Int      = ri.max( i, b )

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

final class RichFloat private[synth](val f: Float) extends AnyVal
with RichNumber.UnaryFloatOps with RichNumber.NAryFloatOps with RichNumber.NAryDoubleOps with RichNumber.NAryGEOps {
   import synth.{FloatFun => rf}

   protected def d  = f.toDouble
   protected def cn = Constant( f )

   // recover these from scala.runtime.RichFloat
   def isInfinity: Boolean    = java.lang.Float.isInfinite( f )
   def isPosInfinity: Boolean = isInfinity && f > 0.0
   def isNegInfinity: Boolean = isInfinity && f < 0.0

   // more unary ops
// def unary_- : Float     = -f
   def abs : Float	      = rf.abs( f )
   def ceil : Float	      = rf.ceil( f )
   def floor : Float	      = rf.floor( f )
   def frac : Float	      = rf.frac( f )
   def signum : Float      = rf.signum( f )
   def squared : Float     = rf.squared( f )
   def cubed : Float       = rf.cubed( f )

   // more binary ops
//   def round( b: Float ) : Float    = rf.round( f, b )
//   def roundup( b: Float ) : Float  = rf.roundup( f, b )
//   def trunc( b: Float ) : Float    = rf.trunc( f, b )
}

// ---------------------------- Double ----------------------------

final class RichDouble private[synth](val d: Double) extends AnyVal
with RichNumber.NAryDoubleOps with RichNumber.NAryGEOps {  
   import synth.{DoubleFun => rd}

   protected def cn = Constant( d.toFloat )

   // recover these from scala.runtime.RichDouble
   def isInfinity: Boolean    = java.lang.Double.isInfinite( d )
   def isPosInfinity: Boolean = isInfinity && d > 0.0
   def isNegInfinity: Boolean = isInfinity && d < 0.0

   // unary ops
//   def unary_- : Double    = -d
   def abs : Double	      = rd.abs( d )
   def ceil : Double	      = rd.ceil( d )
   def floor : Double	   = rd.floor( d )
   def frac : Double	      = rd.frac( d )
   def signum : Double     = rd.signum( d )
   def squared : Double    = rd.squared( d )
   def cubed : Double      = rd.cubed( d )
   def sqrt : Double       = rd.sqrt( d )
   def exp : Double        = rd.exp( d )
   def reciprocal : Double = rd.reciprocal( d )
   def midicps : Double    = rd.midicps( d )
   def cpsmidi : Double    = rd.cpsmidi( d )
   def midiratio : Double  = rd.midiratio( d )
   def ratiomidi : Double  = rd.ratiomidi( d )
   def dbamp : Double      = rd.dbamp( d )
   def ampdb : Double      = rd.ampdb( d )
   def octcps : Double     = rd.octcps( d )
   def cpsoct : Double     = rd.cpsoct( d )
   def log : Double        = rd.log( d )
   def log2 : Double       = rd.log2( d )
   def log10 : Double      = rd.log10( d )
   def sin : Double        = rd.sin( d )
   def cos : Double        = rd.cos( d )
   def tan : Double        = rd.tan( d )
   def asin : Double       = rd.asin( d )
   def acos : Double       = rd.acos( d )
   def atan : Double       = rd.atan( d )
   def sinh : Double       = rd.sinh( d )
   def cosh : Double       = rd.cosh( d )
   def tanh : Double       = rd.tanh( d )
//   def distort : Double    = rd.distort( d )
//   def softclip : Double   = rd.softclip( d )
//   def ramp : Double       = rd.ramp( d )
//   def scurve : Double     = rd.scurve( d )
}