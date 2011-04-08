/*
 *  GE.scala
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

import aux.Optional
import scala.{Seq => SSeq}
import collection.breakOut
import collection.immutable.{ IndexedSeq => IIdxSeq }
import ugen.{Poll, Impulse, LinExp, LinLin, BinaryOp, UnaryOp, UnaryOpUGen, MulAdd}

/**
 *    The UGen graph is constructed from interconnecting graph elements (GE).
 *    Graph elements can be decomposed into a sequence of UGenIn objects.
 *    Graph elements are ordinary UGens, UGen proxies, Control proxies,
 *    Constants, and collections of UGen inputs which result from
 *    multichannel expansion (UGenInSeq). 
 *
 *    @version 0.11, 26-Aug-10
 */
object GE {
   // XXX is the ever in effect?
//   implicit def bubbleGen[ R <: Rate, G <: GE[ R ]]( g: G ) : Multi[ /* R, */ G ] = Multi.Joint( g )
//   implicit def bubble[ G <: AnyGE ]( g: G ) : Multi[ /* R, */ G ] = Multi.Joint( g )

//   implicit def bubble[ G <: AnyGE, T <% G ]( g: T ) : Multi[ /* R, */ G ] = Multi.Joint( g )

//   implicit def bubbleGE[ R <: Rate, G <: GE[ R ]]( g: G ) : Multi[ /* R, */ G ] = Multi.Joint( g )

//   implicit def fromAnySeq( x: Seq[ AnyGE ]) : GE[ Rate ] = {
//      x match {
//         case Seq( single ) => single // Multi.Joint( single )
////         case _ => GESeq[ R, U ]( x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
//         case _ => GESeqGaga( /* rate, */ x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
//      }
//   }

   // XXX don't we expect Multi[ GE[ R ]] ?
   implicit def fromSeq( xs: SSeq[ GE ]) : GE = xs match {
      case SSeq( x ) => x
      case _ => new SeqImpl( xs.toIndexedSeq )
   }

   implicit def fromIntSeq( xs: SSeq[ Int ]) : GE = xs match {
      case SSeq( single ) => single: Constant
      case _ => new SeqImpl( xs.map( i => Constant( i.toFloat ))( breakOut ))
   }

   implicit def fromFloatSeq( xs: SSeq[ Float ]) : GE = xs match {
      case SSeq( x ) => x: Constant
      case _ => new SeqImpl( xs.map( f => Constant( f ))( breakOut ))
   }

   implicit def fromDoubleSeq( xs: SSeq[ Double ]) : GE = xs match {
      case SSeq( x ) => x: Constant
      case _ => new SeqImpl( xs.map( d => Constant( d.toFloat ))( breakOut ))
   }

   def fromUGenIns( xs: SSeq[ UGenIn ]) : GE = new SeqImpl2( xs.toIndexedSeq )

//   implicit def fromSeq[ R <: Rate, G ]( x: Seq[ G ])( implicit view: G => GE[ R ]) : GE[ R ] = {
//      x match {
//         case Seq( single ) => single // Multi.Joint( single )
//         case _ => GESeq[ R ]( x.map( view )( breakOut ))
//      }
//   }

   private class SeqImpl( elems: IIdxSeq[ GE ]) extends GE {
      def expand : UGenInLike = UGenInGroup( elems.map( _.expand ))
      def rate = MaybeRate.reduce( elems.map( _.rate ): _* )
      def displayName = "GE.Seq"
      override def toString = displayName + elems.mkString( "(", ",", ")" )
   }
   private class SeqImpl2( elems: IIdxSeq[ UGenIn ]) extends GE {
      def expand : UGenInLike = UGenInGroup( elems )
      def rate = MaybeRate.reduce( elems.map( _.rate ): _* )
      def displayName = "GE.Seq"
      override def toString = displayName + elems.mkString( "(", ",", ")" )
   }

//   object Seq {
////      implicit def toIndexedSeq[ R <: Rate ]( g: Seq[ R ]) : IIdxSeq[ GE[ R ]] = g.elems
////      def apply[ R <: Rate ]( elems: IIdxSeq[ GE[ R ]]) : Seq[ R ] = new SeqImpl( elems )
//      def apply[ R <: Rate ]( xs: GE[ R ]* ) : Seq[ R ] = new SeqImpl( xs.toIndexedSeq )
//      def apply( xs: UGenIn* ) : Seq[ Rate ] = new SeqImpl2( xs.toIndexedSeq )
//   }
//   sealed trait Seq[ R <: Rate ] extends GE[ R ]

   trait Lazy extends Lazy.Expander[ UGenInLike ] with GE
}
trait GE {
   ge =>

//   type Rate = R

   def rate: MaybeRate

//   def expand: IIdxSeq[ UGenIn ]
//   def mexpand: IIdxSeq[ GE[ R ]]

   def expand: UGenInLike

//   final def mexpand = IIdxSeq( ge )
   def displayName: String

   /**
    * Decomposes the graph element into its distinct outputs. For a single-output UGen
    * or a Constant,  this will just return that UGen or Constant (wrapped in a Vector),
    * for multi-channel UGens this will return the corresponding output proxies. For
    * sequences wrapped into a UGenInSeq, this will return the underlying elements.
    *
    * @return  the sequence containing the output elements of the graph element. When
    *          being certain about the number of elements returned, pattern matching can
    *          be used to extract the different channels, e.g.
    *          `val Seq( left, right ) = Pan.ar( ... ).outputs`
    */

// BBB
//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def outputs : IIdxSeq[ UGenIn ]
//   def numOutputs : Int = outputs.size

// BBB
//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def `\\`( idx: Int ) : UGenIn = outputs( idx )

//   private[synth] def ops = new GEOps( this )

   def madd( mul: GE, add: GE ) = MulAdd( MaybeRate.max_?( rate, mul.rate, add.rate ), this, mul, add )

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
//   def poll( trig: Constant = 10, label: String = "#auto", trigID: AnyGE = -1 ) : Poll[ R ] =
//      poll( Impulse( trig.rate max freq ), label, trigID )

   def poll( trig: GE = 10, label: Optional[ String ] = None, trigID: GE = -1 ) : Poll = {
      val trig1 = trig match {
         case Constant( freq ) => Impulse( (ge.rate ?| audio) max control, freq, 0 )  // XXX good? or throw an error? should have a maxRate?
         case x => x
      }
      Poll( trig1.rate, trig1, ge, label.getOrElse( ge.displayName ), trigID  )
   }

   import UnaryOp._

   // unary ops
   def unary_- : GE   = Neg.make( this )
// def bitNot : GE	         = BitNot.make( this )
   def abs : GE       = Abs.make( this )
// def toFloat : GE	         = UnOp.make( 'asFloat, this )
// def toInteger : GE	      = UnOp.make( 'asInteger, this )
   def ceil : GE      = Ceil.make( this )
   def floor : GE     = Floor.make( this )
   def frac : GE      = Frac.make( this )
   def signum : GE    = Signum.make( this )
   def squared : GE   = Squared.make( this )
   def cubed : GE     = Cubed.make( this )
   def sqrt : GE      = Sqrt.make( this )
   def exp : GE       = Exp.make( this )
   def reciprocal : GE= Reciprocal.make( this )
   def midicps : GE   = Midicps.make( this )
   def cpsmidi : GE   = Cpsmidi.make( this )
   def midiratio : GE = Midiratio.make( this )
   def ratiomidi : GE = Ratiomidi.make( this )
   def dbamp : GE     = Dbamp.make( this )
   def ampdb : GE     = Ampdb.make( this )
   def octcps : GE    = Octcps.make( this )
   def cpsoct : GE    = Cpsoct.make( this )
   def log : GE       = Log.make( this )
   def log2 : GE      = Log2.make( this )
   def log10 : GE     = Log10.make( this )
   def sin : GE       = Sin.make( this )
   def cos : GE       = Cos.make( this )
   def tan : GE       = Tan.make( this )
   def asin : GE      = Asin.make( this )
   def acos : GE      = Acos.make( this )
   def atan : GE      = Atan.make( this )
   def sinh : GE      = Sinh.make( this )
   def cosh : GE      = Cosh.make( this )
   def tanh : GE      = Tanh.make( this )
// def rand : GE              = UnOp.make( 'rand, this )
// def rand2 : GE             = UnOp.make( 'rand2, this )
// def linrand : GE           = UnOp.make( 'linrand, this )
// def bilinrand : GE         = UnOp.make( 'bilinrand, this )
// def sum3rand : GE          = UnOp.make( 'sum3rand, this )
   def distort : GE   = Distort.make( this )
   def softclip : GE  = Softclip.make( this )
// def coin : GE              = UnOp.make( 'coin, this )
// def even : GE              = UnOp.make( 'even, this )
// def odd : GE               = UnOp.make( 'odd, this )
// def rectWindow : GE        = UnOp.make( 'rectWindow, this )
// def hanWindow : GE         = UnOp.make( 'hanWindow, this )
// def welWindow : GE         = UnOp.make( 'sum3rand, this )
// def triWindow : GE         = UnOp.make( 'triWindow, this )
   def ramp : GE      = Ramp.make( this )
   def scurve : GE    = Scurve.make( this )
// def isPositive : GE        = UnOp.make( 'isPositive, this )
// def isNegative : GE        = UnOp.make( 'isNegative, this )
// def isStrictlyPositive : GE= UnOp.make( 'isStrictlyPositive, this )
// def rho : GE               = UnOp.make( 'rho, this )
// def theta : GE             = UnOp.make( 'theta, this )

   import BinaryOp._

   // binary ops
   private def binOp( op: BinaryOp.Op, b: GE ) : GE =
      op.make( /* r.getOrElse( this.rate, b.rate ), */ this, b )

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
      LinLin( rate, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX

   def linexp( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE =
      LinExp( rate, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
}
