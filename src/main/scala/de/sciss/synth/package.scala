/*
 *  package.scala
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

package de.sciss

import collection.breakOut
import collection.immutable.{ IndexedSeq => IIdxSeq }
import osc.{OSCPacket, OSCMessage}
import synth._
package synth {
   abstract sealed class LowPriorityImplicits {
      /**
       * This conversion is needed because while generally we
       * can rely on the numeric-widening of Int -> Float, this
       * widening is not taken into consideration when using
       * view bounds, e.g. `implicit def[ T <% GE ]( ... )`
       * will not work on Ints.
       */
      implicit def intToGE( i: Int )      = Constant( i.toFloat )
      implicit def floatToGE( f: Float )  = Constant( f )
      implicit def doubleToGE( d: Double )= Constant( d.toFloat )
   }
}

/**
 * Pay good attention to this preamble: Read this thread:
 * http://www.scala-lang.org/node/6607
 * and then this one:
 * http://www.scala-lang.org/node/7474
 * And after that, wait a bit. And then read them again, better three times.
 * And then think before changing anything in here. Pray at least once in
 * a while to the god of implicit magic (even if magic doesn't exist, since
 * "it's all the in spec"), it might help!
 *
 * @version	0.14, 28-Aug-10
 */
package object synth extends de.sciss.synth.LowPriorityImplicits /* with de.sciss.synth.RateRelations */ {
   // GEs

   type AnyUGenIn = UGenIn // [ _ <: Rate ]
   type AnyMulti  = Multi[ R, GE[ R /*, UGenIn */]] forSome { type R <: Rate }
   type AnyGE     = GE[ _ <: Rate /*, UGenIn */ ] // forSome { type R <: Rate }
//   type AnyGE   = Expands[ AnyUGenIn ]

//   type audio     = audio.type
//   type control   = control.type
//   type scalar    = scalar.type
//   type demand    = demand.type

   /**
    * This conversion is particularly important to balance priorities,
    * as the plain pair of `intToGE` and `enrichFloat` have equal
    * priorities for an Int despite being in sub/superclass relationship,
    * probably due to the numeric widening which would be needed.
    *
    * Note that we use the same name as scala.Predef.intWrapper. That
    * way the original conversion is hidden!
    */
   implicit def intWrapper( i: Int ) = new RichInt( i )
   /**
    * Note that we use the same name as scala.Predef.floatWrapper. That
    * way the original conversion is hidden!
    */
   implicit def floatWrapper( f: Float ) = new RichFloat( f )
   /**
    * Note that we use the same name as scala.Predef.doubleWrapper. That
    * way the original conversion is hidden!
    */
   implicit def doubleWrapper( d: Double ) = new RichDouble( d )

//   implicit def geOps( ge: GE ) = ge.ops

   // problem with automatic application: http://lampsvn.epfl.ch/trac/scala/ticket/3152
//   implicit def mce( x: Seq[ AnyGE ]) : GE[ /* R, */ UGenIn /*[ R ] */] = {
//      new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
////      val outputs: IIdxSeq[ UGenIn[ R ]] = x.flatMap( _.expand )( breakOut )
////      outputs match {
////         case IIdxSeq( mono ) => mono
////         case _               => new RatedUGenInSeq( rate, outputs )
//////         case _               => new RatedUGenInSeq( x.head.rate, outputs )
////      }
//   }
//
//   implicit def seqOfGEToGE[ G <% AnyGE ]( x: Seq[ G ]) : AnyGE = {
//      val outputs: IIdxSeq[ UGenIn ] = x.flatMap( _.expand )( breakOut )
//      outputs match {
//         case IIdxSeq( mono ) => mono
//         case _               => new UGenInSeq( outputs )
////         case _               => new RatedUGenInSeq( x.head.rate, outputs )
//      }
//   }
//   implicit def geSeqToGE[ R <: Rate, U <: UGenIn ]( x: Seq[ GE[ R, U ]])( implicit rate: R  ) : GE[ R, U ] = {
//      x match {
//         case Seq( single ) => single // Multi.Joint( single )
////         case _ => GESeq[ R, U ]( x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
//         case _ => GESeq[ R, U ]( rate, x.toIndexedSeq ) // Multi.Group( x.toIndexedSeq ) // new RatedUGenInSeq( Rate.highest( x.map( _.rate ): _* ), x )
//      }
////      val outputs: IIdxSeq[ UGenIn ] = x.flatMap( _.expand )( breakOut )
////      outputs match {
////         case IIdxSeq( mono ) => mono
////         case _               => new UGenInSeq( outputs )
//////         case _               => new RatedUGenInSeq( x.head.rate, outputs )
////      }
//   }
//   implicit def doneActionToGE( x: DoneAction ) = Constant( x.id )

   // or should we add a view bound to seqOfGEToGE?
//   implicit def floatSeqToGE( x: Seq[ Float ])   = GESeq[ scalar, Constant ]( x.map( Constant( _ ))( breakOut ) : IIdxSeq[ Constant ])
//   implicit def intSeqToGE( x: Seq[ Int ])       = GESeq[ scalar, Constant ]( x.map( i => Constant( i.toFloat ))( breakOut ) : IIdxSeq[ Constant ])
//   implicit def doubleSeqToGE( x: Seq[ Double ]) = GESeq[ scalar, Constant ]( x.map( d => Constant( d.toFloat ))( breakOut )  : IIdxSeq[ Constant ])

   // control mapping
//   implicit def intFloatControlSet( tup: (Int, Float) )                    = SingleControlSetMap( tup._1, tup._2 )
//   implicit def intIntControlSet( tup: (Int, Int) )                        = SingleControlSetMap( tup._1, tup._2.toFloat )
//   implicit def intDoubleControlSet( tup: (Int, Double) )                  = SingleControlSetMap( tup._1, tup._2.toFloat )
//   implicit def stringFloatControlSet( tup: (String, Float) )              = SingleControlSetMap( tup._1, tup._2 )
//   implicit def stringIntControlSet( tup: (String, Int) )                  = SingleControlSetMap( tup._1, tup._2.toFloat )
//   implicit def stringDoubleControlSet( tup: (String, Double) )            = SingleControlSetMap( tup._1, tup._2.toFloat )
//   implicit def intFloatsControlSet( tup: (Int, IIdxSeq[ Float ]))         = MultiControlSetMap( tup._1, tup._2 )
//   implicit def stringFloatsControlSet( tup: (String, IIdxSeq[ Float ]))   = MultiControlSetMap( tup._1, tup._2 )

//   implicit def intIntControlKBus( tup: (Int, Int) )                 = SingleControlKBusMap( tup._1, tup._2 )
//   implicit def stringIntControlKBus( tup: (String, Int) )           = SingleControlKBusMap( tup._1, tup._2 )
//   implicit def intIntControlABus( tup: (Int, Int) )                 = SingleControlABusMap( tup._1, tup._2 )
//   implicit def stringIntControlABus( tup: (String, Int) )           = SingleControlABusMap( tup._1, tup._2 )
////   implicit def intIntIntControlBus( tup: (Int, Int, Int) )        = MultiControlBusMap( tup._1, tup._2, tup._3 )
////   implicit def stringIntIntControlBus( tup: (String, Int, Int) )  = MultiControlBusMap( tup._1, tup._2, tup._3 )
//   implicit def intKBusControlKBus( tup: (Int, ControlBus) )         = MultiControlKBusMap( tup._1, tup._2.index, tup._2.numChannels )
//   implicit def stringKBusControlKBus( tup: (String, ControlBus) )   = MultiControlKBusMap( tup._1, tup._2.index, tup._2.numChannels )
//   implicit def intABusControlABus( tup: (Int, AudioBus) )           = MultiControlABusMap( tup._1, tup._2.index, tup._2.numChannels )
//   implicit def stringABusControlABus( tup: (String, AudioBus) )     = MultiControlABusMap( tup._1, tup._2.index, tup._2.numChannels )

   // pimping
   implicit def stringToControlProxyFactory( name: String ) = new ControlProxyFactory( name )
   implicit def thunkToGraphFunction[ /*R <: Rate, S <: Rate,*/ T ]( thunk: => T )
      ( implicit view: T => AnyMulti /*, r: RateOrder[ control, R, S ] */) = new GraphFunction( thunk )

//   // Misc
//   implicit def stringToOption( x: String ) = Some( x )

   // Buffer convenience
//   implicit def actionToCompletion( fun: Buffer => Unit ) : Buffer.Completion = Buffer.action( fun )
//   import Buffer.{ Completion => Comp }
   def message[T]( msg: => OSCPacket ) = Completion[T]( Some( _ => msg ), None )
   def message[T]( msg: T => OSCPacket ) = Completion[T]( Some( msg ), None )
   def action[T]( action: => Unit ) = Completion[T]( None, Some( _ => action ))
   def action[T]( action: T => Unit ) = Completion[T]( None, Some( action ))
   def complete[T]( msg: => OSCPacket, action: => Unit ) = Completion[T]( Some( _ => msg ), Some( _ => action ))
   def complete[T]( msg: T => OSCPacket, action: => Unit ) = Completion[T]( Some( msg ), Some( _ => action ))
   def complete[T]( msg: => OSCPacket, action: T => Unit ) = Completion[T]( Some( _ => msg ), Some( action ))
   def complete[T]( msg: T => OSCPacket, action: T => Unit ) = Completion[T]( Some( msg ), Some( action ))
//   implicit def messageToCompletion[T]( msg: OSCPacket ) = message[T]( msg )
   implicit def messageToOption( msg: OSCPacket ) = Some( msg )

   // Nodes
//   implicit def intToNode( id: Int ) : Node = new Group( Server.default, id )
//   implicit def serverToGroup( s: Server ) : Group = s.defaultGroup

//  implicit def stringToStringOrInt( x: String ) = new StringOrInt( x )
//  implicit def intToStringOrInt( x: Int ) = new StringOrInt( x )
  
   // explicit methods

// we remove the overloading as it causes problems with the GE -> Multi conversion
//   def play /*[ R <: Rate, S <: Rate ] */( thunk: => AnyMulti )/*( implicit r: RateOrder[ control, R, S ])*/ : Synth = play()( thunk )
   def play /*[ R <: Rate, S <: Rate ] */( target: Node = Server.default, outBus: Int = 0,
             fadeTime: Option[Float] = Some( 0.02f ),
             addAction: AddAction = addToHead )( thunk: => AnyMulti )/*( implicit r: RateOrder[ control, R, S ])*/ : Synth = {
      val fun = new GraphFunction( thunk )
      fun.play( target, outBus, fadeTime, addAction )
   }
}