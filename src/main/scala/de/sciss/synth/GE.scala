/*
 *  GE.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2010 Hanns Holger Rutz. All rights reserved.
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
import ugen.{BinaryOp, UnaryOp, UnaryOpUGen, MulAdd}

trait Expands[ +R ] {
   def expand: IIdxSeq[ R ]
}

/**
 *    The UGen graph is constructed from interconnecting graph elements (GE).
 *    Graph elements can be decomposed into a sequence of UGenIn objects.
 *    Graph elements are ordinary UGens, UGen proxies, Control proxies,
 *    Constants, and collections of UGen inputs which result from
 *    multichannel expansion (UGenInSeq). 
 *
 *    @version 0.11, 26-Aug-10
 */
trait GE[ R <: Rate, +U <: UGenIn[ R ]] extends Expands[ U ] {
   ge =>

//   type Rate = R
   def rate: R // R

//   def expand: IIdxSeq[ U ]

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

//   def madd( mul: GE[ AnyUGenIn ], add: GE[ AnyUGenIn ]) : GE[ AnyUGenIn ] = {
//      Rate.highest( outputs.map( _.rate ): _* ) match {
//         case `audio`   => MulAdd.ar( this, mul, add )
//         case `control` => MulAdd.kr( this, mul, add )
//         case `scalar`  => this * mul + add
//         case r         => error( "Illegal rate " + r )
//      }
//   }

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def poll: GE = poll()

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
//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def poll( trig: GE = 10, label: String = "#auto", trigID: GE = -1 ) : GE = {
//      import SynthGraph._
//
//      val trig0 = trig match {
//         case Constant( freq ) => {
//            val res: GE = outputs.map( in => Impulse( in.rate match {
//               case `scalar`  => control
//               case _         => in.rate
//            }, freq, 0 ))
//            res
//         }
//         case _ => trig
//      }
//      val labels: IIdxSeq[ String ] = if( label == "#auto" ) {
//         ge match {
//            case seq: UGenInSeq => outputs.zipWithIndex.map( tup => "#" + tup._2 + " " + tup._1.displayName )
//            case _ => outputs.map( _.displayName )
//         }
//      } else {
//         if( numOutputs == 1 ) Vector( label ) else Vector.tabulate( numOutputs )( "#" + _ + " " + label )
//      }
//
//      for( (Seq( t, g, i ), ch) <- expand( trig0, ge, trigID ).zipWithIndex )
//         yield Poll( g.rate match {
//               case `audio`   => audio
//               case _         => g.rate
//            }, t, g, labels( ch ), i )
//   }

   import UnaryOp._

   // unary ops
   def unary_-    = Neg.make[ R ]( rate, this )
// def bitNot : GE	         = BitNot.make( this )
   def abs        = Abs.make[ R ]( rate, this )
// def toFloat : GE	         = UnOp.make( 'asFloat, this )
// def toInteger : GE	      = UnOp.make( 'asInteger, this )
   def ceil       = Ceil.make[ R ]( rate, this )
   def floor      = Floor.make[ R ]( rate, this )
   def frac       = Frac.make[ R ]( rate, this )
   def signum     = Signum.make[ R ]( rate, this )
   def squared    = Squared.make[ R ]( rate, this )
   def cubed      = Cubed.make[ R ]( rate, this )
   def sqrt       = Sqrt.make[ R ]( rate, this )
   def exp        = Exp.make[ R ]( rate, this )
   def reciprocal = Reciprocal.make[ R ]( rate, this )
   def midicps    = Midicps.make[ R ]( rate, this )
   def cpsmidi    = Cpsmidi.make[ R ]( rate, this )
   def midiratio  = Midiratio.make[ R ]( rate, this )
   def ratiomidi  = Ratiomidi.make[ R ]( rate, this )
   def dbamp      = Dbamp.make[ R ]( rate, this )
   def ampdb      = Ampdb.make[ R ]( rate, this )
   def octcps     = Octcps.make[ R ]( rate, this )
   def cpsoct     = Cpsoct.make[ R ]( rate, this )
   def log        = Log.make[ R ]( rate, this )
   def log2       = Log2.make[ R ]( rate, this )
   def log10      = Log10.make[ R ]( rate, this )
   def sin        = Sin.make[ R ]( rate, this )
   def cos        = Cos.make[ R ]( rate, this )
   def tan        = Tan.make[ R ]( rate, this )
   def asin       = Asin.make[ R ]( rate, this )
   def acos       = Acos.make[ R ]( rate, this )
   def atan       = Atan.make[ R ]( rate, this )
   def sinh       = Sinh.make[ R ]( rate, this )
   def cosh       = Cosh.make[ R ]( rate, this )
   def tanh       = Tanh.make[ R ]( rate, this )
// def rand : GE              = UnOp.make( 'rand, this )
// def rand2 : GE             = UnOp.make( 'rand2, this )
// def linrand : GE           = UnOp.make( 'linrand, this )
// def bilinrand : GE         = UnOp.make( 'bilinrand, this )
// def sum3rand : GE          = UnOp.make( 'sum3rand, this )
   def distort    = Distort.make[ R ]( rate, this )
   def softclip   = Softclip.make[ R ]( rate, this )
// def coin : GE              = UnOp.make( 'coin, this )
// def even : GE              = UnOp.make( 'even, this )
// def odd : GE               = UnOp.make( 'odd, this )
// def rectWindow : GE        = UnOp.make( 'rectWindow, this )
// def hanWindow : GE         = UnOp.make( 'hanWindow, this )
// def welWindow : GE         = UnOp.make( 'sum3rand, this )
// def triWindow : GE         = UnOp.make( 'triWindow, this )
   def ramp       = Ramp.make[ R ]( rate, this )
   def scurve     = Scurve.make[ R ]( rate, this )
// def isPositive : GE        = UnOp.make( 'isPositive, this )
// def isNegative : GE        = UnOp.make( 'isNegative, this )
// def isStrictlyPositive : GE= UnOp.make( 'isStrictlyPositive, this )
// def rho : GE               = UnOp.make( 'rho, this )
// def theta : GE             = UnOp.make( 'theta, this )

   import BinaryOp._

   // binary ops
   def +[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Plus.make[ R, S, T ]( r.rate, this, b )
   
   def -[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Minus.make[ R, S, T ]( r.rate, this, b )
   
   def *[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Times.make[ R, S, T ]( r.rate, this, b )
   
// def div( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE      = IDiv.make[ R, S, T ]( r.rate, this, b )
   
   def /[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Div.make[ R, S, T ]( r.rate, this, b )
   
   def %[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Mod.make[ R, S, T ]( r.rate, this, b )
   
   def ===[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Eq.make[ R, S, T ]( r.rate, this, b )
   
   def !==[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Neq.make[ R, S, T ]( r.rate, this, b )
   
   def <[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Lt.make[ R, S, T ]( r.rate, this, b )
   
   def >[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Gt.make[ R, S, T ]( r.rate, this, b )
   
   def <=[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Leq.make[ R, S, T ]( r.rate, this, b )
   
   def >=[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Geq.make[ R, S, T ]( r.rate, this, b )
   
   def min[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Min.make[ R, S, T ]( r.rate, this, b )
   
   def max[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Max.make[ R, S, T ]( r.rate, this, b )
   
   def &[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      BitAnd.make[ R, S, T ]( r.rate, this, b )
   
   def |[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =   
      BitOr.make[ R, S, T ]( r.rate, this, b )
   
   def ^[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      BitXor.make[ R, S, T ]( r.rate, this, b )
   
// def Lcm( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE      = Lcm.make[ R, S, T ]( r.rate, this, b )
// def Gcd( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE      = Gcd.make[ R, S, T ]( r.rate, this, b )
   
   def round[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Round.make[ R, S, T ]( r.rate, this, b )
   
   def roundup[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Roundup.make[ R, S, T ]( r.rate, this, b ) // sclang uses camel case instead
   
   def trunc[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Trunc.make[ R, S, T ]( r.rate, this, b )
   
   def atan2[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Atan2.make[ R, S, T ]( r.rate, this, b )
   
   def hypot[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Hypot.make[ R, S, T ]( r.rate, this, b )
   
   def hypotx[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Hypotx.make[ R, S, T ]( r.rate, this, b )
   
   def pow[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Pow.make[ R, S, T ]( r.rate, this, b )
   
// def <<( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE       = <<.make[ R, S, T ]( r.rate, this, b )
// def >>( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE       = >>.make[ R, S, T ]( r.rate, this, b )
// def unsgnRghtShift( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE = UnsgnRghtShift.make[ R, S, T ]( r.rate, this, b )
// def fill( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE     = Fill.make[ R, S, T ]( r.rate, this, b )
   
   def ring1[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Ring1.make[ R, S, T ]( r.rate, this, b )
   
   def ring2[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Ring2.make[ R, S, T ]( r.rate, this, b )
   
   def ring3[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Ring3.make[ R, S, T ]( r.rate, this, b )
   
   def ring4[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Ring4.make[ R, S, T ]( r.rate, this, b )
   
   def difsqr[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Difsqr.make[ R, S, T ]( r.rate, this, b )
   
   def sumsqr[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
      Sumsqr.make[ R, S, T ]( r.rate, this, b )
   
   def sqrsum[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Sqrsum.make[ R, S, T ]( r.rate, this, b )
   
   def sqrdif[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Sqrdif.make[ R, S, T ]( r.rate, this, b )
   
   def absdif[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Absdif.make[ R, S, T ]( r.rate, this, b )
   
   def thresh[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Thresh.make[ R, S, T ]( r.rate, this, b )
   
   def amclip[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Amclip.make[ R, S, T ]( r.rate, this, b )
   
   def scaleneg[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Scaleneg.make[ R, S, T ]( r.rate, this, b )
   
   def clip2[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Clip2.make[ R, S, T ]( r.rate, this, b )
   
   def excess[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Excess.make[ R, S, T ]( r.rate, this, b )
   
   def fold2[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Fold2.make[ R, S, T ]( r.rate, this, b )
   
   def wrap2[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = 
      Wrap2.make[ R, S, T ]( r.rate, this, b )

// YYY
//   def firstarg[ S <: Rate, T <: Rate ]( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) =
//      Firstarg.make[ R, S, T ]( r.rate, this, b ) // sclang uses camel case instead
   
// def rrand( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE    = Rrand.make[ R, S, T ]( r.rate, this, b )
// def exprrand( b: GE[ S, UGenIn[ S ]])( implicit r: RateOrder[ R, S, T ]) = : GE = Exprrand.make[ R, S, T ]( r.rate, this, b )

   // other ugens
//   def linlin( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE = {
//      val rate = Rate.highest( a.outputs.map( _.rate ))
//      simplify( for( List( ax, sl, sh, dl, dh ) <- expand( a, srcLo, srcHi, dstLo, dstHi ))
//         yield LinLin( rate, ax, sl, sh, dl, dh ))
//   }

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def linlin( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE = Rate.highest( this ) match {
//      case `demand` => (this - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo
//      case r => LinLin.make[ R, S, T ]( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//   }
//
//   def linexp( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE = Rate.highest( this ) match {
//      case `demand` => (dstHi / dstLo).pow( (this - srcLo) / (srcHi - srcLo) ) * dstLo
//      case r => LinExp.make[ R, S, T ]( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//   }
}
