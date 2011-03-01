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

import collection.breakOut
import collection.immutable.{ IndexedSeq => IIdxSeq }
import ugen.{LinExp, LinLin, BinaryOp, UnaryOp, UnaryOpUGen, MulAdd}
import util.Expands

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
   implicit def bubble[ G <: AnyGE ]( g: G ) : Multi[ /* R, */ G ] = Multi.Joint( g )
//   implicit def bubbleGE[ R <: Rate, G <: GE[ R ]]( g: G ) : Multi[ /* R, */ G ] = Multi.Joint( g )

//   implicit def fromAnySeq( x: Seq[ AnyGE ]) : GE[ Rate ] = {
//      x match {
//         case Seq( single ) => single // Multi.Joint( single )
////         case _ => GESeq[ R, U ]( x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
//         case _ => GESeqGaga( /* rate, */ x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
//      }
//   }

   // XXX is the ever in effect?
   implicit def fromSeq[ R <: Rate ]( x: Seq[ GE[ R ]])/* ( implicit rate: R ) */ : GE[ R ] = {
      x match {
         case Seq( single ) => single // Multi.Joint( single )
//         case _ => GESeq[ R, U ]( x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
         case _ => GESeq[ R ]( /* rate, */ x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
      }
//      val outputs: IIdxSeq[ UGenIn ] = x.flatMap( _.expand )( breakOut )
//      outputs match {
//         case IIdxSeq( mono ) => mono
//         case _               => new UGenInSeq( outputs )
////         case _               => new RatedUGenInSeq( x.head.rate, outputs )
//      }
   }
}
trait GE[ +R <: Rate /*, +U <: UGenIn */ ] extends Expands[ UGenIn /* U */] /* with Multi[ GE[ R, U ]] */ {
   ge =>

//   type Rate = R

//   def rate: R

//   def expand: IIdxSeq[ U ]
//   def expand: IIdxSeq[ U ]
//   final def mexpand = IIdxSeq( ge )

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

//   def outputs : IIdxSeq[ UGenIn ]
//   def numOutputs : Int = outputs.size

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def `\\`( idx: Int ) : UGenIn = outputs( idx )

//   private[synth] def ops = new GEOps( this )

   def madd[ S <: Rate, T <: Rate ]( mul: GE[ S ], add: GE[ T ])( implicit r1: Rate.>=[ R, S ], r2: Rate.>=[ R, T ]) =
      MulAdd[ R, S, T ]( this, mul, add )

// BBB
//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def poll: AnyGE = poll()
//
//   /**
//    * Polls the output values of this graph element, and prints the result to the console.
//    * This is a convenient method for wrapping this graph element in a `Poll` UGen.
//    *
//    * @param   trig     a signal to trigger the printing. If this is a constant, it is
//    *    interpreted as a frequency value and an `Impulse` generator of that frequency
//    *    is used instead.
//    * @param   label    a string to print along with the values, in order to identify
//    *    different polls. Using the special label `"#auto"` (default) will generated
//    *    automatic useful labels using information from the polled graph element
//    * @param   trigID   if greater then 0, a `"/tr"` OSC message is sent back to the client
//    *    (similar to `SendTrig`)
//    *
//    * @see  [[de.sciss.synth.ugen.Poll]]
//    */
//   def poll( trig: AnyGE = 10, label: String = "#auto", trigID: AnyGE = -1 ) : AnyGE = {
//      import SynthGraph._
//
//      val trig0 = trig match {
//         case Constant( freq ) => {
////            val res: GE = outputs.map( in => Impulse( in.rate match {
////               case `scalar`  => control
////               case _         => in.rate
////            }, freq, 0 ))
////            res
//            Impulse( rate, freq, 0 )
//         }
//         case _ => trig
//      }
////      val labels: IIdxSeq[ String ] =
//////         if( label == "#auto" ) {
//////         ge match {
//////            case seq: UGenInSeq => outputs.zipWithIndex.map( tup => "#" + tup._2 + " " + tup._1.displayName )
//////            case _ => outputs.map( _.displayName )
//////         }
//////      } else {
////         if( numOutputs == 1 ) IIdxSeq( label ) else IIdxSeq.tabulate( numOutputs )( "#" + _ + " " + label )
//////      }
//
//      Poll( rate, trig, ge, tridID  )
////      for( (Seq( t, g, i ), ch) <- expand( trig0, ge, trigID ).zipWithIndex )
////         yield Poll( g.rate match {
////               case `audio`   => audio
////               case _         => g.rate
////            }, t, g, labels( ch ), i )
//   }

   import UnaryOp._

   // unary ops
   def unary_- : GE[ R ]   = Neg.make[ R ]( this )
// def bitNot : GE	         = BitNot.make( this )
   def abs : GE[ R ]       = Abs.make [ R ](  this )
// def toFloat : GE	         = UnOp.make( 'asFloat, this )
// def toInteger : GE	      = UnOp.make( 'asInteger, this )
   def ceil : GE[ R ]      = Ceil.make[ R ]( this )
   def floor : GE[ R ]     = Floor.make[ R ]( this )
   def frac : GE[ R ]      = Frac.make[ R ]( this )
   def signum : GE[ R ]    = Signum.make[ R ]( this )
   def squared : GE[ R ]   = Squared.make[ R ]( this )
   def cubed : GE[ R ]     = Cubed.make[ R ]( this )
   def sqrt : GE[ R ]      = Sqrt.make[ R ]( this )
   def exp : GE[ R ]       = Exp.make[ R ]( this )
   def reciprocal : GE[ R ]= Reciprocal.make[ R ]( this )
   def midicps : GE[ R ]   = Midicps.make[ R ]( this )
   def cpsmidi : GE[ R ]   = Cpsmidi.make[ R ]( this )
   def midiratio : GE[ R ] = Midiratio.make[ R ]( this )
   def ratiomidi : GE[ R ] = Ratiomidi.make[ R ]( this )
   def dbamp : GE[ R ]     = Dbamp.make[ R ]( this )
   def ampdb : GE[ R ]     = Ampdb.make[ R ]( this )
   def octcps : GE[ R ]    = Octcps.make[ R ]( this )
   def cpsoct : GE[ R ]    = Cpsoct.make[ R ]( this )
   def log : GE[ R ]       = Log.make[ R ]( this )
   def log2 : GE[ R ]      = Log2.make[ R ]( this )
   def log10 : GE[ R ]     = Log10.make[ R ]( this )
   def sin : GE[ R ]       = Sin.make[ R ]( this )
   def cos : GE[ R ]       = Cos.make[ R ]( this )
   def tan : GE[ R ]       = Tan.make[ R ]( this )
   def asin : GE[ R ]      = Asin.make[ R ]( this )
   def acos : GE[ R ]      = Acos.make[ R ]( this )
   def atan : GE[ R ]      = Atan.make[ R ]( this )
   def sinh : GE[ R ]      = Sinh.make[ R ]( this )
   def cosh : GE[ R ]      = Cosh.make[ R ]( this )
   def tanh : GE[ R ]      = Tanh.make[ R ]( this )
// def rand : GE              = UnOp.make( 'rand, this )
// def rand2 : GE             = UnOp.make( 'rand2, this )
// def linrand : GE           = UnOp.make( 'linrand, this )
// def bilinrand : GE         = UnOp.make( 'bilinrand, this )
// def sum3rand : GE          = UnOp.make( 'sum3rand, this )
   def distort : GE[ R ]   = Distort.make[ R ]( this )
   def softclip : GE[ R ]  = Softclip.make[ R ]( this )
// def coin : GE              = UnOp.make( 'coin, this )
// def even : GE              = UnOp.make( 'even, this )
// def odd : GE               = UnOp.make( 'odd, this )
// def rectWindow : GE        = UnOp.make( 'rectWindow, this )
// def hanWindow : GE         = UnOp.make( 'hanWindow, this )
// def welWindow : GE         = UnOp.make( 'sum3rand, this )
// def triWindow : GE         = UnOp.make( 'triWindow, this )
   def ramp : GE[ R ]      = Ramp.make[ R ]( this )
   def scurve : GE[ R ]    = Scurve.make[ R ]( this )
// def isPositive : GE        = UnOp.make( 'isPositive, this )
// def isNegative : GE        = UnOp.make( 'isNegative, this )
// def isStrictlyPositive : GE= UnOp.make( 'isStrictlyPositive, this )
// def rho : GE               = UnOp.make( 'rho, this )
// def theta : GE             = UnOp.make( 'theta, this )

   import BinaryOp._

   // binary ops
   private def binOp[ S <: Rate, T <: Rate ]( op: BinaryOp.Op, b: GE[ S ])
                                            ( implicit r: Rate.Order[ R, S, T ]) : GE[ T ] =
      op.make[ R, S, T ]( /* r.getOrElse( this.rate, b.rate ), */ this, b )

   def +[ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Plus, b )
//      Plus.make /*[ R, S, T ]*/( r.getOrElse( this.rate, b.rate ), this, b )
   
   def -[ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Minus, b )

   def *[ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Times, b )

// def div( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE      = IDiv.make /*[ R, S, T ]*/( /* r.out,*/ this, b )

   def / [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Div, b )

   def % [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Mod, b )

   def === [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Eq, b )

   def !== [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Neq, b )

   def < [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Lt, b )

   def > [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Gt, b )

   def <= [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Leq, b )

   def >= [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Geq, b )

   def min [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Min, b )

   def max[ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Max, b )

   def & [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( BitAnd, b )

   def | [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( BitOr, b )

   def ^ [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( BitXor, b )

// def Lcm( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE      = Lcm.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
// def Gcd( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE      = Gcd.make /*[ R, S, T ]*/( /* r.out,*/ this, b )

   def round [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Round, b )

   def roundup [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Roundup, b )

   def trunc [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Trunc, b )

   def atan2 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Atan2, b )

   def hypot [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Hypot, b )

   def hypotx [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Hypotx, b )

   def pow [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Pow, b )

// def <<( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE       = <<.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
// def >>( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE       = >>.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
// def unsgnRghtShift( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE = UnsgnRghtShift.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
// def fill( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE     = Fill.make /*[ R, S, T ]*/( /* r.out,*/ this, b )

   def ring1 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Ring1, b )

   def ring2 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Ring2, b )

   def ring3 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Ring3, b )

   def ring4 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Ring4, b )

   def difsqr [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Difsqr, b )

   def sumsqr [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Sumsqr, b )

   def sqrsum [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Sqrsum, b )

   def sqrdif [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Sqrdif, b )

   def absdif [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Absdif, b )

   def thresh [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Thresh, b )

   def amclip [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Amclip, b )

   def scaleneg [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Scaleneg, b )

   def clip2 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Clip2, b )

   def excess [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Excess, b )

   def fold2 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Fold2, b )

   def wrap2 [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Wrap2, b )

   def firstarg [ S <: Rate, T <: Rate ]( b: GE[ S ])( implicit r: Rate.Order[ R, S, T ]) = binOp( Firstarg, b )

// def rrand( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE    = Rrand.make /*[ R, S, T ]*/( /* r.out,*/ this, b )
// def exprrand( b: GE[ /*S,*/ UGenIn /*[ S ]*/])/*( implicit r: Rate.Order[ R, S, T ])*/ = : GE = Exprrand.make /*[ R, S, T ]*/( /* r.out,*/ this, b )

// BBB
//   def linlin( srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE ) : AnyGE = Rate.highest( this ) match {
//      case `demand` => (this - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo
////      case r => LinLin.make /*[ R, S, T ]*/( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//      case r => LinLin( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//   }
//
//   def linexp( srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE ) : AnyGE = Rate.highest( this ) match {
//      case `demand` => (dstHi / dstLo).pow( (this - srcLo) / (srcHi - srcLo) ) * dstLo
////      case r => LinExp.make /*[ R, S, T ]*/( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//      case r => LinExp( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//   }
}
