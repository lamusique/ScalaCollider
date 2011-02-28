/*
 *  ControlProxyFactory.scala
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
import collection.immutable.{ IndexedSeq => IIdxSeq, Seq => ISeq }
import ugen.{ControlFactory, AudioControlProxy, ControlProxy, TrigControlProxy}

/**
 *    @version	0.14, 07-Jan-10
 */
object ControlProxyFactory {
   private val controlIrFactory = new ControlFactory[ scalar ]( scalar )
   private val controlKrFactory = new ControlFactory[ control ]( control )

//   implicit def fromString( name: String ) = new ControlProxyFactory( name )
}
class ControlProxyFactory( name: String ) {
   import ControlProxyFactory._

   def ir : ControlProxy[ scalar ] = ir( IIdxSeq( 0f ))
   def ir( value: Double, values: Double* ) : ControlProxy[ scalar ] = ir( IIdxSeq( (value.toFloat +: values.map( _.toFloat )): _* ))
   def ir( value: Float, values: Float* ) : ControlProxy[ scalar ] = ir( IIdxSeq( (value +: values): _* ))
   def kr : ControlProxy[ control ] = kr( IIdxSeq( 0f ))
   def kr( value: Double, values: Double* ) : ControlProxy[ control ] = kr( IIdxSeq( (value.toFloat +: values.map( _.toFloat )): _* ))
   def kr( value: Float, values: Float* ) : ControlProxy[ control ] = kr( IIdxSeq( (value +: values): _* ))
   def tr : TrigControlProxy = tr( IIdxSeq( 0f ))
   def tr( value: Double, values: Double* ) : TrigControlProxy = tr( IIdxSeq( (value.toFloat +: values.map( _.toFloat )): _* ))
   def tr( value: Float, values: Float* ) : TrigControlProxy = tr( IIdxSeq( (value +: values): _* ))
   def ar : AudioControlProxy = ar( IIdxSeq( 0f ))
   def ar( value: Double, values: Double* ) : AudioControlProxy = ar( IIdxSeq( (value.toFloat +: values.map( _.toFloat )): _* ))
   def ar( value: Float, values: Float* ) : AudioControlProxy = ar( IIdxSeq( (value +: values): _* ))
//   def kr[ T <% GE ]( spec: (T, Double), specs: (T, Double)* ) : GE = kr( Vector( (spec._1, spec._2.toFloat) ))
//   def kr[ T <% GE ]( spec: (T, Float), specs: (T, Float)* ) : GE = kr( Vector( spec ))

   @inline private def ir( values: IIdxSeq[ Float ]) = ControlProxy[ scalar ]( scalar, values, Some( name ))( controlIrFactory )
   @inline private def kr( values: IIdxSeq[ Float ]) = ControlProxy[ control ]( control, values, Some( name ))( controlKrFactory )
   @inline private def tr( values: IIdxSeq[ Float ]) = TrigControlProxy( values, Some( name ))
   @inline private def ar( values: IIdxSeq[ Float ]) = AudioControlProxy( values, Some( name ))
//   @inline private def kr( specs: IIdxSeq[ (GE, Float) ]) : GE = {
//
//      LagControlProxy( control, values, Some( name ))
//   }

//   def kr( values: (GE, IIdxSeq[ Float ])) : ControlProxy = {
//      val lags = values._1.outputs
//      val inits = values._2
//      val numCh = max( lags.size, inits.size )
//      new ControlProxy( Some( name ), control, wrapExtend( values._2, numCh ), Some( wrapExtend( lags, numCh )))
//   }

//   private def wrapExtend[T]( coll: IIdxSeq[T], size: Int ) : IIdxSeq[T] = {
//      if( coll.size == size ) coll
//      else if( coll.size > size ) coll.take( size )
//      else {
//         var result = coll
//         while( result.size < size ) {
//            val diff = size - result.size
//            result ++= (if( diff >= coll.size ) coll else coll.take( diff ))
//         }
//         result
//      }
//   }
}

trait ControlFactoryLike[ T ] {
   type Proxy = T // don't ask me what this is doing. some vital variance correction...
   def build( b: UGenGraphBuilder, proxies: Proxy* ) : Map[ ControlProxyLike[ _, _ ], (UGen, Int) ]
}

abstract class AbstractControlFactory[ T <: AbstractControlProxy[ _ <: Rate, T ]] extends ControlFactoryLike[ T ] {
   def build( b: UGenGraphBuilder, proxies: T* ) : Map[ ControlProxyLike[ _, _ ], (UGen, Int) ] = {
      var numChannels   = 0
      val specialIndex  = proxies.map( p => {
         numChannels += p.values.size
         b.addControl( p.values, p.name )
      }).head
      val ugen = makeUGen( numChannels, specialIndex )
      var offset = 0
      proxies.map( p => {
         val res = p -> (ugen, offset)
         offset += p.values.size
         res
      })( breakOut )
   }

   protected def makeUGen( numChannels: Int, specialIndex: Int ) : UGen
}

trait ControlProxyLike[ R <: Rate, Impl ] extends GE[ R, AnyUGenIn ] /* extends RatedGE[ U ] */ {
   def factory: ControlFactoryLike[ Impl ]
   def name: Option[ String ]
   def displayName: String // YYY
}

abstract class AbstractControlProxy[ R <: Rate, Impl ]( outputRates: IIdxSeq[ R ])
extends ControlProxyLike[ R, Impl ] {
   // ---- constructor ----
   SynthGraph.builder.addControlProxy( this )

   def name: Option[ String ]
   def values: IIdxSeq[ Float ]

//   def this( rate: Rate, numOutputs: Int ) =  this( IIdxSeq.fill( numOutputs )( rate ))

// YYY
//   final override def numOutputs = outputRates.size
//	final def outputs: IIdxSeq[ UGenIn[ R ]] = outputRates.zipWithIndex.map(
//      tup => ControlOutProxy[ R ]( this, tup._2, tup._1 ))

   final def expand: IIdxSeq[ AnyUGenIn /* [ R ] */ ] = outputRates.zipWithIndex.map(
      tup => ControlUGenOutProxy /* [ R ] */ ( this, tup._2, tup._1 ))

   final override def toString: String = {
      name.getOrElse( displayName ) + "." + rate.methodName + values.mkString( "(", ", ", ")" )
   }

   def displayName = {
      val cn = getClass.getName
      cn.substring( cn.lastIndexOf( '.' ) + 1, cn.length - 5 ) // i.e. class name without "Proxy" extension
   }
}
