/*
 *  ControlMappings.scala
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

import collection.immutable.{ IndexedSeq => IIdxSeq }

/**
 *    @version 0.11, 01-Jul-10
 */
object ControlSetMap extends SingleControlSetMapImplicits with MultiControlSetMapImplicits

sealed trait ControlSetMap {
   def toSetSeq: IIdxSeq[ Any ]
   def toSetnSeq: IIdxSeq[ Any ]
}

sealed trait SingleControlSetMapImplicits {
   implicit def intFloatControlSet( tup: (Int, Float) )                    = SingleControlSetMap( tup._1, tup._2 )
   implicit def intIntControlSet( tup: (Int, Int) )                        = SingleControlSetMap( tup._1, tup._2.toFloat )
   implicit def intDoubleControlSet( tup: (Int, Double) )                  = SingleControlSetMap( tup._1, tup._2.toFloat )
   implicit def stringFloatControlSet( tup: (String, Float) )              = SingleControlSetMap( tup._1, tup._2 )
   implicit def stringIntControlSet( tup: (String, Int) )                  = SingleControlSetMap( tup._1, tup._2.toFloat )
   implicit def stringDoubleControlSet( tup: (String, Double) )            = SingleControlSetMap( tup._1, tup._2.toFloat )
}
object SingleControlSetMap extends SingleControlSetMapImplicits
case class SingleControlSetMap protected[synth]( key: Any, value: Float )
extends ControlSetMap {
   def toSetSeq: IIdxSeq[ Any ]  = Vector( key, value )
   def toSetnSeq: IIdxSeq[ Any ] = Vector( key, 1, value )
}

sealed trait MultiControlSetMapImplicits {
   implicit def intFloatsControlSet( tup: (Int, IIdxSeq[ Float ]))         = MultiControlSetMap( tup._1, tup._2 )
   implicit def stringFloatsControlSet( tup: (String, IIdxSeq[ Float ]))   = MultiControlSetMap( tup._1, tup._2 )
}
object MultiControlSetMap extends MultiControlSetMapImplicits
case class MultiControlSetMap protected[synth]( key: Any, values: IIdxSeq[ Float ])
extends ControlSetMap {
   def toSetSeq: IIdxSeq[ Any ]  = error( "Not yet supported" )
   def toSetnSeq: IIdxSeq[ Any ] = key +: values.size +: values
}

sealed trait ControlKBusMap {
//   def toMapSeq: IIdxSeq[ Any ]
   def toMapnSeq: IIdxSeq[ Any ]
}

object SingleControlKBusMap {
   implicit def intIntControlKBus( tup: (Int, Int) )                 = SingleControlKBusMap( tup._1, tup._2 )
   implicit def stringIntControlKBus( tup: (String, Int) )           = SingleControlKBusMap( tup._1, tup._2 )
}
case class SingleControlKBusMap protected[synth]( key: Any, index: Int )
extends ControlKBusMap {
   def toMapSeq: IIdxSeq[ Any ]  = Vector( key, index )
   def toMapnSeq: IIdxSeq[ Any ] = Vector( key, index, 1 )
}

object MultiControlKBusMap {
   implicit def intKBusControlKBus( tup: (Int, ControlBus) )         = MultiControlKBusMap( tup._1, tup._2.index, tup._2.numChannels )
   implicit def stringKBusControlKBus( tup: (String, ControlBus) )   = MultiControlKBusMap( tup._1, tup._2.index, tup._2.numChannels )
}
case class MultiControlKBusMap protected[synth]( key: Any, index: Int, numChannels: Int )
extends ControlKBusMap {
   def toMapnSeq: IIdxSeq[ Any ] = Vector( key, index, numChannels )
}

sealed trait ControlABusMap {
   def toMapanSeq: IIdxSeq[ Any ]
}

object SingleControlABusMap {
   implicit def intIntControlABus( tup: (Int, Int) )                 = SingleControlABusMap( tup._1, tup._2 )
   implicit def stringIntControlABus( tup: (String, Int) )           = SingleControlABusMap( tup._1, tup._2 )
}
case class SingleControlABusMap protected[synth]( key: Any, index: Int )
extends ControlABusMap {
   def toMapaSeq: IIdxSeq[ Any ]  = Vector( key, index )
   def toMapanSeq: IIdxSeq[ Any ] = Vector( key, index, 1 )
}

object MultiControlABusMap {
   implicit def intABusControlABus( tup: (Int, AudioBus) )           = MultiControlABusMap( tup._1, tup._2.index, tup._2.numChannels )
   implicit def stringABusControlABus( tup: (String, AudioBus) )     = MultiControlABusMap( tup._1, tup._2.index, tup._2.numChannels )
}
case class MultiControlABusMap protected[synth]( key: Any, index: Int, numChannels: Int )
extends ControlABusMap {
   def toMapanSeq: IIdxSeq[ Any ] = Vector( key, index, numChannels )
}
