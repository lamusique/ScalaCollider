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
   def lowest( rates: Rate* ) : Rate = rates.foldLeft[ Rate ]( scalar )( (a, b) => if( a.id < b.id ) a else b )
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
