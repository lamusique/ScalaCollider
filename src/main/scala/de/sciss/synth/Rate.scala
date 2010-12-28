/*
 *  Rate.scala
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

import ugen.MulAdd

/**
 *    @version 0.12, 28-Dec-10
 */
object Rate {
   def highest( rates: Rate* ) : Rate = rates.foldLeft[ Rate ]( scalar )( (a, b) => if( a.id > b.id ) a else b )
   def highest( ge: GE[ AnyUGenIn ]) : Rate = highest( ge.expand.map( _.rate ): _* )
   def lowest( rates: Rate* ) : Rate = rates.foldLeft[ Rate ]( scalar )( (a, b) => if( a.id < b.id ) a else b )
   def lowest( ge: GE[ AnyUGenIn ]) : Rate = lowest( ge.expand.map( _.rate ): _* )
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
case object scalar  extends scalar
case object control extends control
case object audio   extends audio
case object demand  extends demand

//trait RatedGE[ +R <: Rate, +U <: UGenIn[ R ]] extends GE[ U ] {
//   def rate : Rate // R
//   def madd( mul: RatedGE[ Rate, AnyUGenIn ], add: RatedGE[ Rate, AnyUGenIn ]) : RatedGE[ R, UGenIn[ R ]] = {
//      (rate match {
//         case `audio`   => MulAdd.ar( this, mul, add )
//         case `control` => MulAdd.kr( this, mul, add )
//         case `scalar`  => this * mul + add
//         case r         => error( "Illegal rate " + r )
//      }).asInstanceOf[ RatedGE[ R, UGenIn[ R ]]]
//   }
//}

trait ScalarRated  { def rate = scalar }
trait ControlRated { def rate = control }
trait AudioRated   { def rate = audio }

