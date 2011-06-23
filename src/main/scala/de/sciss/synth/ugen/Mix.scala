/*
 *  Mix.scala
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
package ugen

import collection.immutable.{ IndexedSeq => IIdxSeq, Stack => IStack }
import collection.mutable.{ Queue => MQueue, Stack => MStack }
import aux.UGenHelper

object Mix {
   /**
    * A mixing idiom that corresponds to @Seq.tabulate@ and to @Array.fill@ in sclang.
    */
	def tabulate( n: Int )( fun: (Int) => GE )/* ( implicit rate: R ) */ : Mix.Seq =
      Mix.Seq( IIdxSeq.tabulate( n )( i => fun( i )))

   /**
    * A mixing idiom that corresponds to @Seq.fill@.
    */
   def fill( n: Int )( thunk: => GE )/* ( implicit rate: R ) */ : Mix.Seq =
      Mix.Seq( IIdxSeq.fill( n )( thunk ))

   def seq( elems: IIdxSeq[ GE ])/* ( implicit rate: R ) */ = Seq( elems )

   def mono( elem: GE ) = Mono( elem )

   final case class Seq( elems: IIdxSeq[ GE ])
   extends GE.Lazy {
      def rate = MaybeRate.reduce( elems.map( _.rate ): _* )

      def displayName = "Mix.Seq"
      override def toString = displayName + elems.mkString( "(", ",", ")" )

      def makeUGens : UGenInLike = if( elems.nonEmpty ) elems.reduceLeft( _ + _ ).expand else UGenInGroup.empty
   }

   final case class Mono( elem: GE )
   extends GE.Lazy {
      def rate = elem.rate

      def displayName = "Mix.Mono"
      override def toString = displayName + "(" + elem + ")"

      def makeUGens : UGenInLike = {
         val flat = elem.expand.flatOutputs
         if( flat.nonEmpty ) {
            flat.reduceLeft( BinaryOp.Plus.make1( _, _ ))
         } else UGenInGroup.empty
      }
   }
}

/**
 * Mixes the channels of a signal together. Works exactly like the sclang counterpart.
 *
 * Here are some examples:
 *
 * {{{
 * Mix( SinOsc.ar( 440 :: 660 :: Nil )) --> SinOsc.ar( 440 ) + SinOsc.ar( 660 )
 * Mix( SinOsc.ar( 440 )) --> SinOsc.ar( 440 )
 * Mix( Pan2.ar( SinOsc.ar )) --> left + right
 * Mix( Pan2.ar( SinOsc.ar( 440 :: 660 :: Nil ))) --> [ left( 440 ) + left( 660 ), right( 440 ) + right( 660 )]
 * }}}
*/
final case class Mix( elem: GE )
extends UGenSource.SingleOut( "Mix" ) { // GE.Lazy
   def rate = elem.rate

   protected def makeUGens : UGenInLike = unwrap( elem.expand.outputs )

   protected def makeUGen( args: IIdxSeq[ UGenIn ]) : UGenInLike = {
      if( args.nonEmpty ) {
         args.reduceLeft( BinaryOp.Plus.make1( _, _ ))
      } else UGenInGroup.empty
   }
}
