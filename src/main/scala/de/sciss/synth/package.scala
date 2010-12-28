/*
 *  package.scala
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

package de.sciss

import de.sciss.osc.{ OSCMessage }
import collection.breakOut
import collection.immutable.{ IndexedSeq => IIdxSeq }
import synth.{ addToHead, AddAction, AudioBus, ControlBus, Completion, Constant, ControlProxyFactory, DoneAction, GE,
               GraphFunction, Group, MultiControlSetMap, MultiControlABusMap, MultiControlKBusMap, Node, Rate,
               RichDouble, RichFloat, RichInt, Server, SingleControlABusMap, SingleControlKBusMap, SingleControlSetMap,
               Synth, UGenIn, UGenInSeq }

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
package object synth extends de.sciss.synth.LowPriorityImplicits {
   // GEs

   type AnyUGenIn = UGenIn[ _ <: Rate ]

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

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   implicit def seqOfGEToGE( x: Seq[ GE ]) : GE = {
//      val outputs: IIdxSeq[ UGenIn ] = x.flatMap( _.outputs )( breakOut )
//      outputs match {
//         case IIdxSeq( mono ) => mono
//         case _               => new UGenInSeq( outputs )
//      }
//   }
   implicit def doneActionToGE( x: DoneAction ) = Constant( x.id )

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   // or should we add a view bound to seqOfGEToGE?
//   implicit def seqOfFloatToGE( x: Seq[ Float ])   = new UGenInSeq( x.map( Constant( _ ))( breakOut ))
//   implicit def seqOfIntToGE( x: Seq[ Int ])       = new UGenInSeq( x.map( i => Constant( i.toFloat ))( breakOut ))
//   implicit def seqOfDoubleToGE( x: Seq[ Double ]) = new UGenInSeq( x.map( d => Constant( d.toFloat ))( breakOut ))

   // control mapping
   implicit def intFloatControlSet( tup: (Int, Float) )                    = SingleControlSetMap( tup._1, tup._2 )
   implicit def intIntControlSet( tup: (Int, Int) )                        = SingleControlSetMap( tup._1, tup._2.toFloat )
   implicit def intDoubleControlSet( tup: (Int, Double) )                  = SingleControlSetMap( tup._1, tup._2.toFloat )
   implicit def stringFloatControlSet( tup: (String, Float) )              = SingleControlSetMap( tup._1, tup._2 )
   implicit def stringIntControlSet( tup: (String, Int) )                  = SingleControlSetMap( tup._1, tup._2.toFloat )
   implicit def stringDoubleControlSet( tup: (String, Double) )            = SingleControlSetMap( tup._1, tup._2.toFloat )
   implicit def intFloatsControlSet( tup: (Int, IIdxSeq[ Float ]))         = MultiControlSetMap( tup._1, tup._2 )
   implicit def stringFloatsControlSet( tup: (String, IIdxSeq[ Float ]))   = MultiControlSetMap( tup._1, tup._2 )

   implicit def intIntControlKBus( tup: (Int, Int) )                 = SingleControlKBusMap( tup._1, tup._2 )
   implicit def stringIntControlKBus( tup: (String, Int) )           = SingleControlKBusMap( tup._1, tup._2 )
   implicit def intIntControlABus( tup: (Int, Int) )                 = SingleControlABusMap( tup._1, tup._2 )
   implicit def stringIntControlABus( tup: (String, Int) )           = SingleControlABusMap( tup._1, tup._2 )
//   implicit def intIntIntControlBus( tup: (Int, Int, Int) )        = MultiControlBusMap( tup._1, tup._2, tup._3 )
//   implicit def stringIntIntControlBus( tup: (String, Int, Int) )  = MultiControlBusMap( tup._1, tup._2, tup._3 )
   implicit def intKBusControlKBus( tup: (Int, ControlBus) )         = MultiControlKBusMap( tup._1, tup._2.index, tup._2.numChannels )
   implicit def stringKBusControlKBus( tup: (String, ControlBus) )   = MultiControlKBusMap( tup._1, tup._2.index, tup._2.numChannels )
   implicit def intABusControlABus( tup: (Int, AudioBus) )           = MultiControlABusMap( tup._1, tup._2.index, tup._2.numChannels )
   implicit def stringABusControlABus( tup: (String, AudioBus) )     = MultiControlABusMap( tup._1, tup._2.index, tup._2.numChannels )

   // pimping
   implicit def stringToControlProxyFactory( name: String ) = new ControlProxyFactory( name )
   implicit def thunkToGraphFunction[ T <% GE[ _ ]]( thunk: => T ) = new GraphFunction( thunk )

//   // Misc
//   implicit def stringToOption( x: String ) = Some( x )

   // Buffer convenience
//   implicit def actionToCompletion( fun: Buffer => Unit ) : Buffer.Completion = Buffer.action( fun )
//   import Buffer.{ Completion => Comp }
   def message[T]( msg: => OSCMessage ) = Completion[T]( Some( _ => msg ), None )
   def message[T]( msg: T => OSCMessage ) = Completion[T]( Some( msg ), None )
   def action[T]( action: => Unit ) = Completion[T]( None, Some( _ => action ))
   def action[T]( action: T => Unit ) = Completion[T]( None, Some( action ))
   def complete[T]( msg: => OSCMessage, action: => Unit ) = Completion[T]( Some( _ => msg ), Some( _ => action ))
   def complete[T]( msg: T => OSCMessage, action: => Unit ) = Completion[T]( Some( msg ), Some( _ => action ))
   def complete[T]( msg: => OSCMessage, action: T => Unit ) = Completion[T]( Some( _ => msg ), Some( action ))
   def complete[T]( msg: T => OSCMessage, action: T => Unit ) = Completion[T]( Some( msg ), Some( action ))
   implicit def messageToCompletion[T]( msg: OSCMessage ) = message[T]( msg )
   implicit def messageToOption( msg: OSCMessage ) = Some( msg )

   // Nodes
//   implicit def intToNode( id: Int ) : Node = new Group( Server.default, id )
   implicit def serverToGroup( s: Server ) : Group = s.defaultGroup

//  implicit def stringToStringOrInt( x: String ) = new StringOrInt( x )
//  implicit def intToStringOrInt( x: Int ) = new StringOrInt( x )
  
   // explicit methods
//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def play( thunk: => GE ) : Synth = play()( thunk )
//   def play( target: Node = Server.default.defaultGroup, outBus: Int = 0,
//             fadeTime: Option[Float] = Some( 0.02f ),
//             addAction: AddAction = addToHead )( thunk: => GE ) : Synth =
//      new GraphFunction( thunk ).play( target, outBus, fadeTime, addAction )
}