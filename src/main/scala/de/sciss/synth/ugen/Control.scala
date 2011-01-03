/*
 *  Control.scala
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

package de.sciss.synth.ugen

import collection.immutable.{ IndexedSeq => IIdxSeq, Seq => ISeq }
import collection.breakOut
import de.sciss.synth.{ scalar, control, audio, AbstractControlProxy, AudioRated, ControlFactoryLike,
                        ControlProxyLike, ControlRated, MultiOutUGen, Rate, HasSideEffect, SynthGraph, UGen }

/**
 *    @version 0.12, 17-May-10
 */

// ---------- Control ----------

object Control {
   /**
    *    Note: we are not providing further convenience methods,
    *    as that is the task of ControlProxyFactory...
    */
   def ir( values: IIdxSeq[ Float ], name: Option[ String ] = None ) : ControlUGen = make( scalar, values, name )
   def kr( values: IIdxSeq[ Float ], name: Option[ String ] = None ) : ControlUGen = make( control, values, name )

   def ir( values: Float* ) : ControlUGen = ir( Vector( values: _* ))
   def kr( values: Float* ) : ControlUGen = kr( Vector( values: _* ))

   private def make( rate: Rate, values: IIdxSeq[ Float ], name: Option[ String ]) : ControlUGen = {
      val specialIndex = SynthGraph.builder.addControl( values, name )
      ControlUGen( rate, values.size, specialIndex )
   }
}
case class ControlUGen private[ugen]( rate: Rate, numChannels: Int, override val specialIndex: Int )
extends MultiOutUGen( IIdxSeq.fill( numChannels )( rate ), IIdxSeq.empty ) with HasSideEffect

case class ControlProxy[ R <: Rate ]( rate: R, values: IIdxSeq[ Float ], name: Option[ String ])
extends AbstractControlProxy[ R, ControlProxy[ R ]]( IIdxSeq.fill( values.size )( rate )) {
//   def factory = ControlFactory
   def factory : ControlFactoryLike[ ControlProxy[ R ]] = ControlFactory

   override def toString: String = {
      name.getOrElse( displayName ) + "." + rate.methodName + values.mkString( "(", ", ", ")" )
   }

   def displayName = "Control"

   private object ControlFactory extends ControlFactoryLike[ ControlProxy[ R ]] {
      // XXX eventually we should try to factor this out for all controlfactories...
      def build( proxies: ControlProxy[ R ]* ) : Map[ ControlProxyLike[ _, _ ], (UGen, Int) ] = {
         val b = SynthGraph.builder
         // XXX the 'force' is a remainder from a bug with the scala 2.8.0 release candidates,
         // which exhibited a problem of not forcing varargs into being strict.
         // this has probably been fixed, hence we should eventually check if
         // the force is still necessary or could be removed!
         // -- DONE
         proxies.groupBy( _.rate ).flatMap( group => {
            val (rate, ps)    = group
            var numChannels   = 0
            val specialIndex  = ps.map( p => {
               numChannels += p.values.size
               b.addControl( p.values, p.name )
            }).head
            val ugen: UGen = ControlUGen( rate, numChannels, specialIndex )
            var offset = 0
            ps.map( p => {
               val res = p -> (ugen, offset)
               offset += p.values.size
               res
            })
         })( breakOut )
      }
   }
}

//object ControlFactory extends ControlFactoryLike[ ControlProxy[ _ ]] {
//   // XXX eventually we should try to factor this out for all controlfactories...
//   def build( proxies: ControlProxy[ _ ]* ) : Map[ ControlProxyLike[ _, _ ], (UGen, Int) ] = {
//      val b = SynthGraph.builder
//      // XXX the 'force' is a remainder from a bug with the scala 2.8.0 release candidates,
//      // which exhibited a problem of not forcing varargs into being strict.
//      // this has probably been fixed, hence we should eventually check if
//      // the force is still necessary or could be removed!
//      // -- DONE
//      proxies.groupBy( _.rate ).flatMap( group => {
//         val (rate, ps)    = group
//         var numChannels   = 0
//         val specialIndex  = ps.map( p => {
//            numChannels += p.values.size
//            b.addControl( p.values, p.name )
//         }).head
//         val ugen: UGen = ControlUGen( rate, numChannels, specialIndex )
//         var offset = 0
//         ps.map( p => {
//            val res = p -> (ugen, offset)
//            offset += p.values.size
//            res
//         })
//      })( breakOut )
//   }
//}

// ---------- TrigControl ----------

object TrigControl {
   def kr( values: IIdxSeq[ Float ], name: Option[ String ] = None ) : TrigControlUGen = {
      val specialIndex = SynthGraph.builder.addControl( values, name )
      TrigControlUGen( values.size, specialIndex )
   }
   def kr( values: Float* ) : TrigControlUGen = kr( Vector( values: _* ))
}
case class TrigControlUGen private[ugen]( numChannels: Int, override val specialIndex: Int )
extends MultiOutUGen( IIdxSeq.fill( numChannels )( control ), IIdxSeq.empty ) with ControlRated with HasSideEffect

case class TrigControlProxy( values: IIdxSeq[ Float ], name: Option[ String ])
extends AbstractControlProxy[ control, TrigControlProxy ]( IIdxSeq.fill( values.size )( control )) with ControlRated {
   def factory = TrigControlFactory

   override def toString: String = {
      name.getOrElse( displayName ) + "." + rate.methodName + values.mkString( "(", ", ", ")" )
   }

   def displayName = "TrigControl"
}

object TrigControlFactory extends ControlFactoryLike[ TrigControlProxy ] {
   def build( proxies: TrigControlProxy* ) : Map[ ControlProxyLike[ _, _ ], (UGen, Int) ] = {
      val b = SynthGraph.builder
      var numChannels   = 0
      val specialIndex  = proxies.map( p => {
         numChannels += p.values.size
         b.addControl( p.values, p.name )
      }).head
      val ugen: UGen = TrigControlUGen( numChannels, specialIndex )
      var offset = 0
      proxies.map( p => {
         val res = p -> (ugen, offset)
         offset += p.values.size
         res
      })( breakOut )
   }
}

// ---------- AudioControl ----------

object AudioControl {
   def ar( values: IIdxSeq[ Float ], name: Option[ String ] = None ) : AudioControlUGen = {
      val specialIndex = SynthGraph.builder.addControl( values, name )
      AudioControlUGen( values.size, specialIndex )
   }
   def ar( values: Float* ) : AudioControlUGen = ar( Vector( values: _* ))
}
case class AudioControlUGen private[ugen]( numChannels: Int, override val specialIndex: Int )
extends MultiOutUGen( IIdxSeq.fill( numChannels )( audio ), IIdxSeq.empty ) with AudioRated with HasSideEffect

case class AudioControlProxy( values: IIdxSeq[ Float ], name: Option[ String ])
extends AbstractControlProxy[ audio, AudioControlProxy ]( IIdxSeq.fill( values.size )( audio )) with AudioRated {
   def factory = AudioControlFactory

   override def toString: String = {
      name.getOrElse( displayName ) + "." + rate.methodName + values.mkString( "(", ", ", ")" )
   }

   def displayName = "AudioControl"
}

object AudioControlFactory extends ControlFactoryLike[ AudioControlProxy ] {
   def build( proxies: AudioControlProxy* ) : Map[ ControlProxyLike[ _, _ ], (UGen, Int) ] = {
      val b = SynthGraph.builder
      var numChannels   = 0
      val specialIndex  = proxies.map( p => {
         numChannels += p.values.size
         b.addControl( p.values, p.name )
      }).head
      val ugen: UGen = AudioControlUGen( numChannels, specialIndex )
      var offset = 0
      proxies.map( p => {
         val res = p -> (ugen, offset)
         offset += p.values.size
         res
      })( breakOut )
   }
}
