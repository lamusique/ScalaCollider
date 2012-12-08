/*
 *  GE.scala
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

import scala.{Seq => SSeq}
import collection.breakOut
import collection.immutable.{ IndexedSeq => IIdxSeq }
import ugen.{ChannelProxy, Flatten, Poll, Impulse, LinExp, LinLin, BinaryOp, UnaryOp, MulAdd}

/**
 *    The UGen graph is constructed from interconnecting graph elements (GE).
 *    Graph elements can be decomposed into a sequence of UGenIn objects.
 *    Graph elements are ordinary UGens, UGen proxies, Control proxies,
 *    Constants, and collections of UGen inputs which result from
 *    multichannel expansion (UGenInSeq). 
 */
object GE {
   // XXX don't we expect Multi[ GE[ R ]] ?
   implicit def fromSeq( xs: SSeq[ GE ]) : GE = xs match {
      case SSeq( x ) => x
      case _ => SeqImpl( xs.toIndexedSeq )
   }

   implicit def fromIntSeq( xs: SSeq[ Int ]) : GE = xs match {
      case SSeq( single ) => single: Constant
      case _ => SeqImpl( xs.map( i => Constant( i.toFloat ))( breakOut ))
   }

   implicit def fromFloatSeq( xs: SSeq[ Float ]) : GE = xs match {
      case SSeq( x ) => x: Constant
      case _ => SeqImpl( xs.map( f => Constant( f ))( breakOut ))
   }

   implicit def fromDoubleSeq( xs: SSeq[ Double ]) : GE = xs match {
      case SSeq( x ) => x: Constant
      case _ => SeqImpl( xs.map( d => Constant( d.toFloat ))( breakOut ))
   }

   def fromUGenIns( xs: SSeq[ UGenIn ]) : GE = SeqImpl2( xs.toIndexedSeq )

//   implicit def fromSeq[ R <: Rate, G ]( x: Seq[ G ])( implicit view: G => GE[ R ]) : GE[ R ] = {
//      x match {
//         case Seq( single ) => single // Multi.Joint( single )
//         case _ => GESeq[ R ]( x.map( view )( breakOut ))
//      }
//   }

   private final case class SeqImpl( elems: IIdxSeq[ GE ]) extends GE {
def numOutputs = elems.size
      def expand : UGenInLike = UGenInGroup( elems.map( _.expand ))
      def rate = MaybeRate.reduce( elems.map( _.rate ): _* )
      override def displayName = "GE.Seq"
      override def toString = displayName + elems.mkString( "(", ",", ")" )
   }
   private final case class SeqImpl2( elems: IIdxSeq[ UGenIn ]) extends GE {
def numOutputs = elems.size
      def expand : UGenInLike = UGenInGroup( elems )
      def rate = MaybeRate.reduce( elems.map( _.rate ): _* )
      override def displayName = "GE.Seq"
      override def toString = displayName + elems.mkString( "(", ",", ")" )
   }

//   object Seq {
////      implicit def toIndexedSeq[ R <: Rate ]( g: Seq[ R ]) : IIdxSeq[ GE[ R ]] = g.elems
////      def apply[ R <: Rate ]( elems: IIdxSeq[ GE[ R ]]) : Seq[ R ] = new SeqImpl( elems )
//      def apply[ R <: Rate ]( xs: GE[ R ]* ) : Seq[ R ] = new SeqImpl( xs.toIndexedSeq )
//      def apply( xs: UGenIn* ) : Seq[ Rate ] = new SeqImpl2( xs.toIndexedSeq )
//   }
//   sealed trait Seq[ R <: Rate ] extends GE[ R ]

   /**
    * Simply a trait composed of `Lazy.Expander[UGenInLike]` and `GE`
    */
   trait Lazy extends Lazy.Expander[ UGenInLike ] with GE
   
//   implicit def ops( g: GE ) : Ops = new Ops( g )
   
   final class Ops( g: GE ) {
      def `\\`( index: Int ) = ChannelProxy( g, index )
   
      def madd( mul: GE, add: GE ) = MulAdd( g, mul, add )
   
      def flatten = Flatten( g )
   
      def poll: Poll = poll()
   
      /**
       * Polls the output values of this graph element, and prints the result to the console.
       * This is a convenient method for wrapping this graph element in a `Poll` UGen.
       *
       * @param   trig     a signal to trigger the printing. If this is a constant, it is
       *    interpreted as a frequency value and an `Impulse` generator of that frequency
       *    is used instead.
       * @param   label    a string to print along with the values, in order to identify
       *    different polls. Using the special label `"#auto"` (default) will generated
       *    automatic useful labels using information from the polled graph element
       * @param   trigID   if greater then 0, a `"/tr"` OSC message is sent back to the client
       *    (similar to `SendTrig`)
       *
       * @see  [[de.sciss.synth.ugen.Poll]]
       */
      def poll( trig: GE = 10, label: Optional[ String ] = None, trigID: GE = -1 ) : Poll = {
         val trig1 = trig match {
            case Constant( freq ) => Impulse( (g.rate ?| audio) max control, freq, 0 )  // XXX good? or throw an error? should have a maxRate?
            case other => other
         }
         Poll( trig1.rate, trig1, g, label.getOrElse( g.displayName ), trigID  )
      }
   
      import UnaryOp._
   
      // unary ops
      def unary_- : GE   = Neg.make( g )
   // def bitNot : GE	         = BitNot.make( g )
      def abs : GE       = Abs.make( g )
   // def toFloat : GE	         = UnOp.make( 'asFloat, this )
   // def toInteger : GE	      = UnOp.make( 'asInteger, this )
      def ceil : GE      = Ceil.make( g )
      def floor : GE     = Floor.make( g )
      def frac : GE      = Frac.make( g )
      def signum : GE    = Signum.make( g )
      def squared : GE   = Squared.make( g )
      def cubed : GE     = Cubed.make( g )
      def sqrt : GE      = Sqrt.make( g )
      def exp : GE       = Exp.make( g )
      def reciprocal : GE= Reciprocal.make( g )
      def midicps : GE   = Midicps.make( g )
      def cpsmidi : GE   = Cpsmidi.make( g )
      def midiratio : GE = Midiratio.make( g )
      def ratiomidi : GE = Ratiomidi.make( g )
      def dbamp : GE     = Dbamp.make( g )
      def ampdb : GE     = Ampdb.make( g )
      def octcps : GE    = Octcps.make( g )
      def cpsoct : GE    = Cpsoct.make( g )
      def log : GE       = Log.make( g )
      def log2 : GE      = Log2.make( g )
      def log10 : GE     = Log10.make( g )
      def sin : GE       = Sin.make( g )
      def cos : GE       = Cos.make( g )
      def tan : GE       = Tan.make( g )
      def asin : GE      = Asin.make( g )
      def acos : GE      = Acos.make( g )
      def atan : GE      = Atan.make( g )
      def sinh : GE      = Sinh.make( g )
      def cosh : GE      = Cosh.make( g )
      def tanh : GE      = Tanh.make( g )
   // def rand : GE              = UnOp.make( 'rand, this )
   // def rand2 : GE             = UnOp.make( 'rand2, this )
   // def linrand : GE           = UnOp.make( 'linrand, this )
   // def bilinrand : GE         = UnOp.make( 'bilinrand, this )
   // def sum3rand : GE          = UnOp.make( 'sum3rand, this )
      def distort : GE   = Distort.make( g )
      def softclip : GE  = Softclip.make( g )
   // def coin : GE              = UnOp.make( 'coin, this )
   // def even : GE              = UnOp.make( 'even, this )
   // def odd : GE               = UnOp.make( 'odd, this )
   // def rectWindow : GE        = UnOp.make( 'rectWindow, this )
   // def hanWindow : GE         = UnOp.make( 'hanWindow, this )
   // def welWindow : GE         = UnOp.make( 'sum3rand, this )
   // def triWindow : GE         = UnOp.make( 'triWindow, this )
      def ramp : GE      = Ramp.make( g )
      def scurve : GE    = Scurve.make( g )
   // def isPositive : GE        = UnOp.make( 'isPositive, this )
   // def isNegative : GE        = UnOp.make( 'isNegative, this )
   // def isStrictlyPositive : GE= UnOp.make( 'isStrictlyPositive, this )
   // def rho : GE               = UnOp.make( 'rho, this )
   // def theta : GE             = UnOp.make( 'theta, this )
   
      import BinaryOp._
   
      // binary ops
      private def binOp( op: BinaryOp.Op, b: GE ) : GE =
         op.make( /* r.getOrElse( this.rate, b.rate ), */ g, b )
   
      def +( b: GE) = binOp( Plus, b )
   //      Plus.make /*[ R, S, T ]*/( r.getOrElse( this.rate, b.rate ), this, b )
      
      def -( b: GE) = binOp( Minus, b )
   
      def *( b: GE) = binOp( Times, b )
   
   // def div( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE      = IDiv.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   
      def / ( b: GE) = binOp( Div, b )
   
      def % ( b: GE) = binOp( Mod, b )
   
      def === ( b: GE) = binOp( Eq, b )
   
      def !== ( b: GE) = binOp( Neq, b )
   
      def < ( b: GE) = binOp( Lt, b )
   
      def > ( b: GE) = binOp( Gt, b )
   
      def <= ( b: GE) = binOp( Leq, b )
   
      def >= ( b: GE) = binOp( Geq, b )
   
      def min ( b: GE) = binOp( Min, b )
   
      def max( b: GE) = binOp( Max, b )
   
      def & ( b: GE) = binOp( BitAnd, b )
   
      def | ( b: GE) = binOp( BitOr, b )
   
      def ^ ( b: GE) = binOp( BitXor, b )
   
   // def Lcm( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE      = Lcm.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   // def Gcd( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE      = Gcd.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   
      def round ( b: GE) = binOp( Round, b )
   
      def roundup ( b: GE) = binOp( Roundup, b )
   
      def trunc ( b: GE) = binOp( Trunc, b )
   
      def atan2 ( b: GE) = binOp( Atan2, b )
   
      def hypot ( b: GE) = binOp( Hypot, b )
   
      def hypotx ( b: GE) = binOp( Hypotx, b )
   
      def pow ( b: GE) = binOp( Pow, b )
   
   // def <<( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE       = <<.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   // def >>( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE       = >>.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   // def unsgnRghtShift( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE = UnsgnRghtShift.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   // def fill( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE     = Fill.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   
      def ring1 ( b: GE) = binOp( Ring1, b )
   
      def ring2 ( b: GE) = binOp( Ring2, b )
   
      def ring3 ( b: GE) = binOp( Ring3, b )
   
      def ring4 ( b: GE) = binOp( Ring4, b )
   
      def difsqr ( b: GE) = binOp( Difsqr, b )
   
      def sumsqr ( b: GE) = binOp( Sumsqr, b )
   
      def sqrsum ( b: GE) = binOp( Sqrsum, b )
   
      def sqrdif ( b: GE) = binOp( Sqrdif, b )
   
      def absdif ( b: GE) = binOp( Absdif, b )
   
      def thresh ( b: GE) = binOp( Thresh, b )
   
      def amclip ( b: GE) = binOp( Amclip, b )
   
      def scaleneg ( b: GE) = binOp( Scaleneg, b )
   
      def clip2 ( b: GE) = binOp( Clip2, b )
   
      def excess ( b: GE) = binOp( Excess, b )
   
      def fold2 ( b: GE) = binOp( Fold2, b )
   
      def wrap2 ( b: GE) = binOp( Wrap2, b )
   
      def firstarg ( b: GE) = binOp( Firstarg, b )
   
   // def rrand( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE    = Rrand.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   // def exprrand( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/**/ = : GE = Exprrand.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
   
      def linlin( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE =
         LinLin( /* rate, */ g, srcLo, srcHi, dstLo, dstHi )
   
      def linexp( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE =
         LinExp( g.rate, g, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
   }
}
/**
 * The main trait used in synthesis graph, a graph element, abbreviated as `GE`.
 *
 * Graph elements are characterized by having a calculation rate (possibly unknown),
 * and they embody future UGens, which are created by invoking the `expand` method.
 * For each ugen in SuperCollider, there is a corresponding graph element defined
 * in the `ugen` package, and these elements take again graph elements as arguments.
 * Multi-channel expansion is thus deferred to the transition from `SynthGraph` to `UGenGraph`.
 *
 * Currently, also a lot of unary and binary operations are directly defined on the `GE` trait,
 * although they might go into a separate `GEOps` implicit class in future versions.
 *
 * @see [[de.sciss.synth.SynthGraph]]
 */
trait GE {
   def rate: MaybeRate
   def expand: UGenInLike
   def displayName: String
}
