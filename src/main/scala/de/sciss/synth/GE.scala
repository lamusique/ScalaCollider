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
import ugen.{ BinaryOpUGen, UnaryOpUGen }

/**
 *    The UGen graph is constructed from interconnecting graph elements (GE).
 *    Graph elements can be decomposed into a sequence of UGenIn objects.
 *    Graph elements are ordinary UGens, UGen proxies, Control proxies,
 *    Constants, and collections of UGen inputs which result from
 *    multichannel expansion (UGenInSeq). 
 *
 *    @version 0.11, 26-Aug-10
 */
trait GE {
   ge =>

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
   def outputs : IIdxSeq[ UGenIn ]
   def numOutputs : Int = outputs.size
   def `\\`( idx: Int ) : UGenIn = outputs( idx )

//   private[synth] def ops = new GEOps( this )

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def madd( mul: GE, add: GE ) : GE = {
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

   import UnaryOpUGen._

   // unary ops
   def unary_- : GE           = Neg.make( this )
// def bitNot : GE	         = BitNot.make( this )
   def abs : GE	            = Abs.make( this )
// def toFloat : GE	         = UnOp.make( 'asFloat, this )
// def toInteger : GE	      = UnOp.make( 'asInteger, this )
   def ceil : GE	            = Ceil.make( this )
   def floor : GE	            = Floor.make( this )
   def frac : GE	            = Frac.make( this )
   def signum : GE	         = Signum.make( this )
   def squared : GE           = Squared.make( this )
   def cubed : GE             = Cubed.make( this )
   def sqrt : GE              = Sqrt.make( this )
   def exp : GE               = Exp.make( this )
   def reciprocal : GE        = Reciprocal.make( this )
   def midicps : GE           = Midicps.make( this )
   def cpsmidi : GE           = Cpsmidi.make( this )
   def midiratio : GE         = Midiratio.make( this )
   def ratiomidi : GE         = Ratiomidi.make( this )
   def dbamp : GE             = Dbamp.make( this )
   def ampdb : GE             = Ampdb.make( this )
   def octcps : GE            = Octcps.make( this )
   def cpsoct : GE            = Cpsoct.make( this )
   def log : GE               = Log.make( this )
   def log2 : GE              = Log2.make( this )
   def log10 : GE             = Log10.make( this )
   def sin : GE               = Sin.make( this )
   def cos : GE               = Cos.make( this )
   def tan : GE               = Tan.make( this )
   def asin : GE              = Asin.make( this )
   def acos : GE              = Acos.make( this )
   def atan : GE              = Atan.make( this )
   def sinh : GE              = Sinh.make( this )
   def cosh : GE              = Cosh.make( this )
   def tanh : GE              = Tanh.make( this )
// def rand : GE              = UnOp.make( 'rand, this )
// def rand2 : GE             = UnOp.make( 'rand2, this )
// def linrand : GE           = UnOp.make( 'linrand, this )
// def bilinrand : GE         = UnOp.make( 'bilinrand, this )
// def sum3rand : GE          = UnOp.make( 'sum3rand, this )
   def distort : GE           = Distort.make( this )
   def softclip : GE          = Softclip.make( this )
// def coin : GE              = UnOp.make( 'coin, this )
// def even : GE              = UnOp.make( 'even, this )
// def odd : GE               = UnOp.make( 'odd, this )
// def rectWindow : GE        = UnOp.make( 'rectWindow, this )
// def hanWindow : GE         = UnOp.make( 'hanWindow, this )
// def welWindow : GE         = UnOp.make( 'sum3rand, this )
// def triWindow : GE         = UnOp.make( 'triWindow, this )
   def ramp : GE              = Ramp.make( this )
   def scurve : GE            = Scurve.make( this )
// def isPositive : GE        = UnOp.make( 'isPositive, this )
// def isNegative : GE        = UnOp.make( 'isNegative, this )
// def isStrictlyPositive : GE= UnOp.make( 'isStrictlyPositive, this )
// def rho : GE               = UnOp.make( 'rho, this )
// def theta : GE             = UnOp.make( 'theta, this )

   import BinaryOpUGen._

   // binary ops
   def +( b: GE ) : GE        = Plus.make( this, b )
   def -( b: GE ) : GE        = Minus.make( this, b )
   def *( b: GE ) : GE        = Times.make( this, b )
// def div( b: GE ) : GE      = IDiv.make( this, b )
   def /( b: GE ) : GE        = Div.make( this, b )
   def %( b: GE ) : GE        = Mod.make( this, b )
   def ===( b: GE ) : GE      = Eq.make( this, b )
   def !==( b: GE ) : GE      = Neq.make( this, b )
   def <( b: GE ) : GE	      = Lt.make( this, b )
   def >( b: GE ) : GE	      = Gt.make( this, b )
   def <=( b: GE ) : GE	      = Leq.make( this, b )
   def >=( b: GE ) : GE	      = Geq.make( this, b )
   def min( b: GE ) : GE      = Min.make( this, b )
   def max( b: GE ) : GE      = Max.make( this, b )
   def &( b: GE ) : GE	      = BitAnd.make( this, b )
   def |( b: GE ) : GE	      = BitOr.make( this, b )
   def ^( b: GE ) : GE	      = BitXor.make( this, b )
// def Lcm( b: GE ) : GE      = Lcm.make( this, b )
// def Gcd( b: GE ) : GE      = Gcd.make( this, b )
   def round( b: GE ) : GE    = Round.make( this, b )
   def roundup( b: GE ) : GE  = Roundup.make( this, b ) // sclang uses camel case instead
   def trunc( b: GE ) : GE    = Trunc.make( this, b )
   def atan2( b: GE ) : GE    = Atan2.make( this, b )
   def hypot( b: GE ) : GE    = Hypot.make( this, b )
   def hypotx( b: GE ) : GE   = Hypotx.make( this, b )
   def pow( b: GE ) : GE      = Pow.make( this, b )
// def <<( b: GE ) : GE       = <<.make( this, b )
// def >>( b: GE ) : GE       = >>.make( this, b )
// def unsgnRghtShift( b: GE ) : GE = UnsgnRghtShift.make( this, b )
// def fill( b: GE ) : GE     = Fill.make( this, b )
   def ring1( b: GE ) : GE    = Ring1.make( this, b )
   def ring2( b: GE ) : GE    = Ring2.make( this, b )
   def ring3( b: GE ) : GE    = Ring3.make( this, b )
   def ring4( b: GE ) : GE    = Ring4.make( this, b )
   def difsqr( b: GE ) : GE   = Difsqr.make( this, b )
   def sumsqr( b: GE ) : GE   = Sumsqr.make( this, b )
   def sqrsum( b: GE ) : GE   = Sqrsum.make( this, b )
   def sqrdif( b: GE ) : GE   = Sqrdif.make( this, b )
   def absdif( b: GE ) : GE   = Absdif.make( this, b )
   def thresh( b: GE ) : GE   = Thresh.make( this, b )
   def amclip( b: GE ) : GE   = Amclip.make( this, b )
   def scaleneg( b: GE ) : GE = Scaleneg.make( this, b )
   def clip2( b: GE ) : GE    = Clip2.make( this, b )
   def excess( b: GE ) : GE   = Excess.make( this, b )
   def fold2( b: GE ) : GE    = Fold2.make( this, b )
   def wrap2( b: GE ) : GE    = Wrap2.make( this, b )
   def firstarg( b: GE ) : GE = Firstarg.make( this, b ) // sclang uses camel case instead
// def rrand( b: GE ) : GE    = Rrand.make( this, b )
// def exprrand( b: GE ) : GE = Exprrand.make( this, b )

   // other ugens
//   def linlin( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE = {
//      val rate = Rate.highest( a.outputs.map( _.rate ))
//      simplify( for( List( ax, sl, sh, dl, dh ) <- expand( a, srcLo, srcHi, dstLo, dstHi ))
//         yield LinLin( rate, ax, sl, sh, dl, dh ))
//   }

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def linlin( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE = Rate.highest( this ) match {
//      case `demand` => (this - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo
//      case r => LinLin.make( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//   }
//
//   def linexp( srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE ) : GE = Rate.highest( this ) match {
//      case `demand` => (dstHi / dstLo).pow( (this - srcLo) / (srcHi - srcLo) ) * dstLo
//      case r => LinExp.make( r, this, srcLo, srcHi, dstLo, dstHi ) // should be highest rate of all inputs? XXX
//   }
}
