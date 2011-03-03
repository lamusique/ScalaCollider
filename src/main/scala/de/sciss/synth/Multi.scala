/*
 *  Multi.scala
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

import collection.immutable.{IndexedSeq => IIdxSeq}

object Multi {
   implicit def flatten[ R <: Rate ]( m: Multi[ /* R, */ GE[ R ]]) : GE[ R ] = Flatten( m )
   implicit def joint[ G <: AnyGE ]( g: G ) : Multi[ /* R, */ G ] = Joint( g )

//   implicit def bubbleGen[ R <: Rate, G <% GE[ R ]]( g: G ) : Multi[ /* R, */ G ] = Multi.Joint( g )
//   implicit def bubble[ R <: Rate ]( g: GE[ R ]) : Multi[ /* R, */ GE[ R ]] = Multi.Joint( g )

//   def joint[ G <: AnyGE ]( g: G ) = Multi.Joint( g )
//   def disjoint /*[ R <: Rate ]*/( g: GE[ /* R,*/ UGenIn /*[ R ]*/]) = Multi.Disjoint( g )
//   def group( elems: IIdxSeq[ AnyGE ]) = Multi.Group( elems )

   final case class Flatten[ R <: Rate ]( m: Multi[ /* R, */ GE[ R ]]) extends GE[ R ] {
//      def rate = m.rate
      override def toString = "Multi.flatten(" + m + ")"
      def expand : IIdxSeq[ UGenIn ] = {
         m.mexpand.flatMap( _.expand )
      }
   }

   final case class Joint[ /* R <: Rate, */ G <: AnyGE ]( g: G ) extends Multi[ /* R, */ G ] {
//      def rate = g.rate
      def mexpand : IIdxSeq[ G ] = IIdxSeq( g )
   }

   final case class Disjoint[ R <: Rate ]( g: GE[ R ]) extends Multi[ /* R, */ GE[ R ]] {
//      def rate = g.rate
      def mexpand : IIdxSeq[ GE[ R ]] = {
//         implicit val r = g.rate
         g.expand.map( new WrapUGenIn[ R ]( _ ))
      }
   }

   private class WrapUGenIn[ R <: Rate ]( in: UGenIn )/*( implicit val rate: R )*/ extends GE[ R ] {
      def expand: IIdxSeq[ UGenIn ] = IIdxSeq( in )
   }

//   case class Group( elems: IIdxSeq[ AnyGE ]) extends AnyGE {
//
//   }
}

trait Multi[ /* R <: Rate, */ +G <: AnyGE /* GE[ R ] */] {
   def mexpand: IIdxSeq[ G ]
//   def rate: R
}
