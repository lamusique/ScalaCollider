/*
 *  Rate.scala
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

/**
 *    @version 0.12, 28-Dec-10
 */
object Rate {
   def highest( rates: Rate* ) : Rate = rates.foldLeft[ Rate ]( scalar )( (a, b) => if( a.id > b.id ) a else b )
//   def highest( ge: AnyGE ) : Rate = highest( ge.expand.map( _.rate ): _* )
   def lowest( rates: Rate* ) : Rate = rates.foldLeft[ Rate ]( scalar )( (a, b) => if( a.id < b.id ) a else b )
//   def lowest( ge: AnyGE ) : Rate = lowest( ge.expand.map( _.rate ): _* )

   object > {
//   implicit val demandGtAudio:    HigherRate[ demand,  audio   ] = new Impl[ demand, audio ] // (   demand,  audio   )
//   implicit val demandGtControl:  HigherRate[ demand,  control ] = new Impl[ demand, control ] // ( demand,  control )
//   implicit val demandGtScalar:   HigherRate[ demand,  scalar  ] = new Impl[ demand, scalar ] // (  demand,  scalar  )

      implicit def allGtScalar[ R <: Rate ] : >[ R, scalar ]   = new Impl[ R, scalar ]
      implicit def demandGtAll[ R <: Rate ] : >[ demand, R ]   = new Impl[ demand, R ]
      implicit val audioGtControl: >[ audio, control ]         = new Impl[ audio, control ] // (  audio,   control )
//   implicit def same[ R <: Rate ] : HigherRate[ R, R ]               = new Impl[ R, R ]
//   implicit val audioGtScalar:    HigherRate[ audio,   scalar  ] = new Impl[ audio, scalar ] // (   audio,   scalar  )
//   implicit val controlGtScalar:  HigherRate[ control, scalar  ] = new Impl[ control, scalar ] // ( control, scalar  )

      private class Impl[ R <: Rate, S <: Rate ] /* ( val rate1: R, val rate2: S ) */ extends >[ R, S ]
   }
   sealed trait >[ -R <: Rate, S <: Rate ]

   object >= {
      implicit def allGtScalar[ R <: Rate ] : >=[ R, scalar ]  = new Impl[ R, scalar ]
      implicit def demandGtAll[ R <: Rate ] : >=[ demand, R ]  = new Impl[ demand, R ]
      implicit val audioGtControl: >=[ audio, control ]        = new Impl[ audio, control ] // (  audio,   control )
      implicit def same[ R <: Rate ] : >=[ R, R ]              = new Impl[ R, R ]
      private class Impl[ R <: Rate, S <: Rate ] /* ( val rate1: R, val rate2: S ) */ extends >=[ R, S ]
   }
   sealed trait >=[ -R <: Rate, S <: Rate ]

   sealed trait OrdLowImplicits {
//   implicit def unknown[ R <: Rate, S <: Rate ] = RateOrdUnknown[ R, S ]()
      implicit def unknown[ R <: Rate, S <: Rate ] : Ord[ R, S, Rate ] = new Impl[ R, S, Rate ] // RateOrdUnknown[ R, S ]()
      protected class Impl[ R <: Rate, S <: Rate, T <: Rate ]/*( /* val in1: R, val in2: S, */ val out: T )*/ extends Ord[ R, S, T ]
   }
   object Ord extends OrdLowImplicits {
      implicit def same[ R <: Rate ] /* ( implicit rate: R ) */ : Ord[ R, R, R ] = new Impl[ R, R, R ] // ( /* rate, rate, */ rate )
//   implicit val bothScalar = new Impl[ scalar, scalar, scalar ]
//   implicit val bothControl= new Impl[ control, control, control ]
//   implicit val bothAudio  = new Impl[ audio, audio, audio ]
//   implicit val bothDemand = new Impl[ demand, demand, demand ]
      implicit def gt[ R <: Rate, S <: Rate ]( implicit rel: >[ R, S ]) : Ord[ R, S, R ] = new Impl[ R, S, R ] // ( /* rel.rate1, rel.rate2, */ rel.rate1 )
      implicit def lt[ R <: Rate, S <: Rate ]( implicit rel: >[ S, R ]) : Ord[ R, S, S ] = new Impl[ R, S, S ] // ( /* rel.rate2, rel.rate1, */ rel.rate1 )

      private class Impl[ R <: Rate, S <: Rate, T <: Rate ] extends Ord[ R, S, T ]
   }
//sealed trait MaybeRateOrd[ -R <: Rate, -S <: Rate, -T <: Rate ]
   sealed trait Ord[ -R <: Rate, S <: Rate, T <: Rate ]
}

/**
 *    The calculation rate of a UGen or a UGen output.
 */
sealed abstract class Rate {
   val id: Int
   val methodName: String
}

sealed trait scalar  extends Rate { final val id = 0; final val methodName = "ir" }
sealed trait control extends Rate { final val id = 1; final val methodName = "kr" }
sealed trait audio   extends Rate { final val id = 2; final val methodName = "ar" }
sealed trait demand  extends Rate { final val id = 3; final val methodName = "dr" }
case object scalar  extends scalar {
   implicit val rate = scalar
}
case object control extends control {
   implicit val rate = control
}
case object audio extends audio {
   implicit val rate = audio
}
case object demand extends demand {
   implicit val rate = demand
}

//trait ScalarRated  { def rate: scalar = scalar }
//trait ControlRated { def rate: control = control }
//trait AudioRated   { def rate: audio = audio }
//trait DemandRated  { def rate: demand = demand }
