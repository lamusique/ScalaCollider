/*
 *  UGenIn.scala
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

import collection.immutable.{ IndexedSeq => IIdxSeq }

/**
 *    An element that can be used as an input to a UGen.
 *    This is after multi-channel-expansion, hence implementing
 *    classes are SingleOutUGen, UGenOutProxy, ControlOutProxy, and Constant.
 *
 *    @version 0.12, 28-Dec-10
 */
sealed trait UGenIn[ R <: Rate ] extends /* RatedGE */ GE[ R, UGenIn[ R ]] {
   type Rate = R
// YYY
//   final override def numOutputs = 1

//   final def outputs: IIdxSeq[ UGenIn ] = Vector( this )
   def rate : R
   final def expand: IIdxSeq[ UGenIn[ R ]] = IIdxSeq( this )
}

object Constant {
   @inline private def cn( f: Float )     = Constant( f )
   @inline private def cn( d: Double )    = Constant( d.toFloat )
   @inline private def cn( b: Boolean )   = Constant( if( b ) 1f else 0f )
}
/**
 *    A scalar constant used as an input to a UGen.
 *    These constants are stored in a separate table of
 *    the synth graph.
 */
case class Constant( value: Float ) extends UGenIn[scalar] with ScalarRated {
   import Constant._
   
   override def toString = value.toString
   def displayName = value.toString

//   override private[synth] def ops = new ConstantOps( this )

//   // special binop handling
//   override def +( b: GE ) : GE        = b match {
//      case Constant( bval ) => Constant( value + bval )
//      case _ => super.+( b )
//   }

   // unary ops. note: there is no sense in changing the
   // result type to Constant, as the class seen from outside
   // is always GEOps.
//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   override def unary_- : GE     = cn( -value )
//   override def abs : GE	      = cn( math.abs( value ))
//   override def ceil : GE	      = cn( math.ceil( value ))
//   override def floor : GE	      = cn( math.floor( value ))
//   override def frac : GE	      = cn( value - math.floor( value )) // according to jmc
//   override def signum : GE      = cn( math.signum( value ))
//   override def squared : GE     = cn( value * value )
//   override def cubed : GE       = cn( value * value * value )
//   override def sqrt : GE        = cn( math.sqrt( value ))
//   override def exp : GE         = cn( math.exp( value ))
//   override def reciprocal : GE  = cn( 1.0f / value )
//   override def midicps : GE     = cn( 440 * math.pow( 2, (value - 69) * 0.083333333333 ))
//   override def cpsmidi : GE     = cn( math.log( value * 0.0022727272727 ) / math.log( 2 ) * 12 + 69 )
//   override def midiratio : GE   = cn( math.pow( 2, value * 0.083333333333 ))
//   override def ratiomidi : GE   = cn( 12 * math.log( value ) / math.log( 2 ))
//   override def dbamp : GE       = cn( math.pow( 10, value * 0.05 ))
//   override def ampdb : GE       = cn( math.log10( value )* 20 )
//   override def octcps : GE      = cn( 440 * math.pow( 2, value - 4.75 ))
//   override def cpsoct : GE      = cn( math.log( value * 0.0022727272727 ) / math.log( 2 ) + 4.75 )
//   override def log : GE         = cn( math.log( value ))
//   override def log2 : GE        = cn( math.log( value ) / math.log( 2 ))
//   override def log10 : GE       = cn( math.log10( value ))
//   override def sin : GE         = cn( math.sin( value ))
//   override def cos : GE         = cn( math.cos( value ))
//   override def tan : GE         = cn( math.tan( value ))
//   override def asin : GE        = cn( math.asin( value ))
//   override def acos : GE        = cn( math.acos( value ))
//   override def atan : GE        = cn( math.atan( value ))
//   override def sinh : GE        = cn( math.sinh( value ))
//   override def cosh : GE        = cn( math.cosh( value ))
//   override def tanh : GE        = cn( math.tanh( value ))
//   override def distort : GE     = cn( value / (1 + math.abs( value )))
//   override def softclip : GE    = { val absx = math.abs( value ); cn( if( absx <= 0.5f ) value else (absx - 0.25f) / value )}
//   override def ramp : GE        = cn( if( value <= 0 ) 0 else if( value >= 1 ) 1 else value )
//   override def scurve : GE      = cn( if( value <= 0 ) 0 else if( value > 1 ) 1 else value * value * (3 - 2 * value))
}

/**
 *    A SingleOutUGen is a UGen which has exactly one output, and
 *    hence can directly function as input to another UGen without expansion.
 */
//abstract class SingleOutUGen( val inputs: UGenIn* ) extends UGen with UGenIn
abstract class SingleOutUGen[ R <: Rate ]( val inputs: IIdxSeq[ AnyUGenIn ]) extends UGen with UGenIn[ R ] {
   final def outputs = expand
   final def numOutputs = 1
}

/**
 *    A UGenOutProxy refers to a particular output of a multi-channel UGen.
 *    A sequence of these form the representation of a multi-channel-expanded
 *    UGen. 
 */
//case class UGenOutProxy( source: UGen, outputIndex: Int, rate: Rate )
//extends UGenIn with UGenProxy {
//   override def toString = source.toString + ".\\(" + outputIndex + ")"
//   def displayName = source.displayName + " \\ " + outputIndex
//}

case class UGenOutProxy[ R <: Rate ]( source: UGen, outputIndex: Int, rate: R )
extends UGenIn[ R ] with UGenProxy {
   override def toString = source.toString + ".\\(" + outputIndex + ")"
   def displayName = source.displayName + " \\ " + outputIndex
}

/**
 *    A ControlOutProxy is similar to a UGenOutProxy in that it denotes
 *    an output channel of a control UGen. However it refers to a control-proxy
 *    instead of a real control ugen, since the proxies are synthesized into
 *    actual ugens only at the end of a synth graph creation, in order to
 *    clumb several controls together. ControlOutProxy instance are typically
 *    returned from the ControlProxyFactory class, that is, using the package
 *    implicits, from calls such as "myControl".kr.
 */
case class ControlOutProxy[ R <: Rate ]( source: ControlProxyLike[ R, _ ], outputIndex: Int, rate: R )
extends UGenIn[ R ] {
   override def toString = source.toString + ".\\(" + outputIndex + ")"
   def displayName = source.displayName + " \\ " + outputIndex 
}