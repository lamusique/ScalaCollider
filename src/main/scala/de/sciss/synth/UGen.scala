/*
 *  UGen.scala
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
 */

package de.sciss.synth

import collection.immutable.{IndexedSeq => IIdxSeq}
import annotation.switch
import runtime.ScalaRunTime

sealed trait UGen extends Product /* with MaybeIndividual */ {
   // ---- constructor ----
   UGenGraph.builder.addUGen( this )

   def rate: Rate // YYY
   def numOutputs: Int // YYY
//   def outputs: IIdxSeq[ UGenIn ] // YYY   XXX could be UGenProxy
   def outputRates: IIdxSeq[ Rate ]

   def name : String
   def displayName = name

//   def outputRates: Seq[ Rate ]
   def inputs: IIdxSeq[ UGenIn ]
   def numInputs = inputs.size
   def source = this
   def specialIndex = 0
//   def outputIndex = 0

   override def toString: String = {
      name + "." + rate.methodName + inputs.mkString( "(", ", ", ")" )
   }

   // the full UGen spec:
   // name, rate, specialIndex, inputs, outputRates
   override final def productPrefix: String = "UGen"
   final def productArity : Int = 5
   final def productElement( n: Int ) : Any = (n: @switch) match {
      case 0 => name
      case 1 => rate
      case 2 => specialIndex
      case 3 => inputs
//      case 3 => inputs.map( _.ref )
      case 4 => outputRates
      case _ => throw new java.lang.IndexOutOfBoundsException( n.toString )
   }
   final def canEqual( x: Any ) : Boolean = x.isInstanceOf[ UGen ]
//   override def hashCode(): Int = ScalaRunTime._hashCode( this )
   override val hashCode: Int = if( isIndividual ) super.hashCode() else ScalaRunTime._hashCode( this )
   override def equals( x: Any ) : Boolean = (this eq x.asInstanceOf[ AnyRef ]) || (!isIndividual && (x match {
     case u: UGen =>
        u.name == name && u.rate == rate && u.specialIndex == specialIndex && u.inputs == inputs &&
           u.outputRates == outputRates && u.canEqual( this )
     case _ => false
   }))

   def isIndividual: Boolean
   def hasSideEffect: Boolean

//   private[synth] def ref: AnyRef = this
//   final private[synth] lazy val ref = if( isIndividual ) new AnyRef else this
}

object UGen {
   /**
    *    A SingleOutUGen is a UGen which has exactly one output, and
    *    hence can directly function as input to another UGen without expansion.
    */
   class SingleOut( val name: String, val rate: Rate, val inputs: IIdxSeq[ UGenIn ],
                    val isIndividual: Boolean = false, val hasSideEffect: Boolean = false )
   extends UGenProxy with UGen {
final def numOutputs = 1
//      final override def outputs: IIdxSeq[ UGenIn ] = IIdxSeq( this ) // increase visibility
      final def outputRates: IIdxSeq[ Rate ] = rate.toIndexedSeq // IIdxSeq( rate )

//      override def equals( x: Any ) : Boolean = {
//         super.equals( x )
//      }
//
//      override def hashCode() = {
//         super.hashCode()
//      }
      final def outputIndex = 0
   }

   object ZeroOut {
      private val outputRates: IIdxSeq[ Rate ] = IIdxSeq.empty
   }
   class ZeroOut( val name: String, val rate: Rate, val inputs: IIdxSeq[ UGenIn ], val isIndividual: Boolean = false )
   extends UGen /* with HasSideEffect */ {
      final /* override */ def numOutputs = 0
//      final def outputs = IIdxSeq.empty
      final def outputRates: IIdxSeq[ Rate ] = ZeroOut.outputRates // IIdxSeq.empty
      final def hasSideEffect = true
   }

   /**
    * A class for UGens with multiple outputs
    */
   class MultiOut( val name: String, val rate: Rate, val outputRates: IIdxSeq[ Rate ], val inputs: IIdxSeq[ UGenIn ],
                   val isIndividual: Boolean = false, val hasSideEffect: Boolean = false )
   extends UGen with UGenInGroup {
      final def numOutputs                      = outputRates.size
//      final lazy val outputs: IIdxSeq[ UGenIn ] = outputRates.zipWithIndex.map( tup => OutProxy( this, tup._2, tup._1 ))
      final def unwrap( i: Int ) : UGenInLike   = {
//         outputs( i % outputRates.size )
         OutProxy( this, i % numOutputs ) // , outputRates( i )
      }

      def outputs: IIdxSeq[ UGenIn ] = IIdxSeq.tabulate( numOutputs )( ch => OutProxy( this, ch ))

      /*
         This is important: Imagine for example `Out.ar( PanAz.ar( 4, In.ar( 0, 1 ), pos ))`.
         If `isWrapped` would report `true` here, the result would be a rewrapping into four
         `Out` UGens. Obviously we do not want this.

         This corresponds with the following check in method `MultiOutUGen: initOutputs` in sclang:
         `^if( numChannels == 1, { channels.at( 0 )}, channels )`!
       */
//      private[synth] final def isWrapped = numOutputs != 1
      private[synth] final def unbubble : UGenInLike = if( numOutputs == 1 ) outputs( 0 ) else this
   }

   /**
    *    A UGenOutProxy refers to a particular output of a multi-channel UGen.
    *    A sequence of these form the representation of a multi-channel-expanded
    *    UGen.
    */
   final case class OutProxy( source: UGen.MultiOut, outputIndex: Int /*, rate: Rate */)
   extends UGenIn with UGenProxy {
      override def toString = {
         if( source.numOutputs == 1 ) source.toString else source.toString + ".\\(" + outputIndex + ")"
      }
      def displayName = {
         if( source.numOutputs == 1 ) source.displayName else source.displayName + " \\ " + outputIndex
      }
      def rate : Rate = source.outputRates( outputIndex )

//      private[synth] def ref: AnyRef = this
   }
}

sealed trait UGenInLike extends GE {
//   def ungroup : IIdxSeq[ UGenInLike ]
//   def numOutputs : Int
   private[synth] def outputs : IIdxSeq[ UGenInLike ]
//   private[synth] def isWrapped : Boolean
//   private[synth] def numOutputs : Int
   private[synth] def unbubble: UGenInLike

   /**
    * Returns the UGenInLike element of index i
    * regarding the ungrouped representation. Note
    * that for efficiency reasons this method will
    * automatically wrap the index around numElements!
    */
   private[synth] def unwrap( i: Int ) : UGenInLike
   private[synth] def flatOutputs : IIdxSeq[ UGenIn ]

   // ---- GE ----
   final def expand: UGenInLike = this
}

//object UGenIn {
//   val rateOrd = Ordering.by[ UGenIn, Rate ]( _.rate )
//}
/**
 *    An element that can be used as an input to a UGen.
 *    This is after multi-channel-expansion, hence implementing
 *    classes are SingleOutUGen, UGenOutProxy, ControlOutProxy, and Constant.
 *
 *    @version 0.12, 28-Dec-10
 */
sealed trait UGenIn extends UGenInLike { // [ R <: Rate ] extends /* RatedGE */ GE[ R, UGenIn[ R ]] {
   def rate : Rate
//   private[synth] final def numOutputs: Int = 1
   private[synth] def outputs : IIdxSeq[ UGenIn ] = IIdxSeq( this )
   private[synth] final def unwrap( i: Int ) : UGenInLike = this   // don't bother about the index
   private[synth] final def flatOutputs : IIdxSeq[ UGenIn ] = IIdxSeq( this )
//   private[synth] final def isWrapped : Boolean = false
   private[synth] final def unbubble: UGenInLike = this
//   private[synth] def ref: AnyRef
}

object UGenInGroup {
   def empty : UGenInGroup = new Impl( IIdxSeq.empty )
   def apply( xs: IIdxSeq[ UGenInLike ]) : UGenInGroup = new Impl( xs )

   private final class Impl( xs: IIdxSeq[ UGenInLike ]) extends UGenInGroup {
      private[synth] def outputs : IIdxSeq[ UGenInLike ] = xs
      private[synth] def numOutputs : Int                = xs.size
      private[synth] def unwrap( i: Int ) : UGenInLike   = xs( i % xs.size )
//      private[synth] def isWrapped : Boolean             = true
      private[synth] def unbubble: UGenInLike            = this

      override def toString = displayName + xs.mkString( "(", ",", ")" )

      // ---- GE ----
      def displayName = "UGenInGroup"
      def rate : MaybeRate = MaybeRate.reduce( xs.map( _.rate ): _* )
   }
}
sealed trait UGenInGroup extends UGenInLike {
//   def numOutputs : Int
   private[synth] def outputs : IIdxSeq[ UGenInLike ]
   private[synth] def numOutputs : Int
   private[synth] final def flatOutputs : IIdxSeq[ UGenIn ] = outputs.flatMap( _.flatOutputs )
}

sealed trait UGenProxy extends UGenIn {
   def source : UGen
   def outputIndex : Int
}

object Constant {
   @inline private def cn( f: Float )     = Constant( f )
   @inline private def cn( d: Double )    = Constant( d.toFloat )
//   @inline private def cn( b: Boolean )   = Constant( if( b ) 1f else 0f )
}

//case class ConstantUGenIn( value: Float ) extends UGenIn {
//   def rate: Rate = scalar
//}

/**
 *    A scalar constant used as an input to a UGen.
 *    These constants are stored in a separate table of
 *    the synth graph.
 */
final case class Constant( value: Float ) extends GE with UGenIn {
   import Constant._

   override def toString = value.toString
   def displayName = value.toString
   def rate: Rate = scalar

//   private[synth] final def ref: AnyRef = this

//   def expand : UGenInLike = this

//   def expand: IIdxSeq[ Constant ] = IIdxSeq( this ) // ConstantUGenIn( value )

//   override private[synth] def ops = new ConstantOps( this )

//   // special binop handling
//   override def +( b: GE ) : GE        = b match {
//      case Constant( bval ) => Constant( value + bval )
//      case _ => super.+( b )
//   }

   // unary ops. note: there is no sense in changing the
   // result type to Constant, as the class seen from outside
   // is always GEOps.
   override def unary_-      = cn( -value )
   override def abs 	        = cn( math.abs( value ))
   override def ceil 	     = cn( math.ceil( value ))
   override def floor 	     = cn( math.floor( value ))
   override def frac 	     = cn( value - math.floor( value )) // according to jmc
   override def signum       = cn( math.signum( value ))
   override def squared      = cn( value * value )
   override def cubed        = cn( value * value * value )
   override def sqrt         = cn( math.sqrt( value ))
   override def exp          = cn( math.exp( value ))
   override def reciprocal   = cn( 1.0f / value )
   override def midicps      = cn( 440 * math.pow( 2, (value - 69) * 0.083333333333 ))
   override def cpsmidi      = cn( math.log( value * 0.0022727272727 ) / math.log( 2 ) * 12 + 69 )
   override def midiratio    = cn( math.pow( 2, value * 0.083333333333 ))
   override def ratiomidi    = cn( 12 * math.log( value ) / math.log( 2 ))
   override def dbamp        = cn( math.pow( 10, value * 0.05 ))
   override def ampdb        = cn( math.log10( value )* 20 )
   override def octcps       = cn( 440 * math.pow( 2, value - 4.75 ))
   override def cpsoct       = cn( math.log( value * 0.0022727272727 ) / math.log( 2 ) + 4.75 )
   override def log          = cn( math.log( value ))
   override def log2         = cn( math.log( value ) / math.log( 2 ))
   override def log10        = cn( math.log10( value ))
   override def sin          = cn( math.sin( value ))
   override def cos          = cn( math.cos( value ))
   override def tan          = cn( math.tan( value ))
   override def asin         = cn( math.asin( value ))
   override def acos         = cn( math.acos( value ))
   override def atan         = cn( math.atan( value ))
   override def sinh         = cn( math.sinh( value ))
   override def cosh         = cn( math.cosh( value ))
   override def tanh         = cn( math.tanh( value ))
   override def distort      = cn( value / (1 + math.abs( value )))
   override def softclip     = { val absx = math.abs( value ); cn( if( absx <= 0.5f ) value else (absx - 0.25f) / value )}
   override def ramp         = cn( if( value <= 0 ) 0 else if( value >= 1 ) 1 else value )
   override def scurve       = cn( if( value <= 0 ) 0 else if( value > 1 ) 1 else value * value * (3 - 2 * value))
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
//case class ControlOutProxy[ R <: Rate ]( source: ControlProxyLike[ R, _ ], outputIndex: Int, rate: R )
//extends GE[ R, UGenIn ] with UGenIn { // UGenIn[ R ] {
//   override def toString = source.toString + ".\\(" + outputIndex + ")"
//   def displayName = source.displayName + " \\ " + outputIndex
//
//   def expand: IIdxSeq[ UGenIn ] = IIdxSeq( this )
//}

final case class ControlUGenOutProxy( source: ControlProxyLike[ _ ], outputIndex: Int /*, rate: Rate */)
extends UGenIn { // UGenIn[ R ] {
   def rate = source.rate
   override def toString = source.toString + ".\\(" + outputIndex + ")"
   def displayName = source.displayName + " \\ " + outputIndex
//   private[synth] def ref: AnyRef = this
}
