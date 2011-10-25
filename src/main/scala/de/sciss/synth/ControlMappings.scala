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
import sys.error

object ControlSetMap extends SingleControlSetMapImplicits with MultiControlSetMapImplicits {
   object Single extends SingleControlSetMapImplicits
   case class Single( key: Any, value: Float )
   extends ControlSetMap {
      def toSetSeq: IIdxSeq[ Any ]  = IIdxSeq( key, value )
      def toSetnSeq: IIdxSeq[ Any ] = IIdxSeq( key, 1, value )
   }

   object Multi extends MultiControlSetMapImplicits
   case class Multi( key: Any, values: IIdxSeq[ Float ])
   extends ControlSetMap {
      def toSetSeq: IIdxSeq[ Any ]  = error( "Not yet supported" )
      def toSetnSeq: IIdxSeq[ Any ] = key +: values.size +: values
   }
}

sealed trait ControlSetMap {
   def toSetSeq: IIdxSeq[ Any ]
   def toSetnSeq: IIdxSeq[ Any ]
}

private[synth] sealed trait SingleControlSetMapImplicits {
   implicit def intFloatControlSet( tup: (Int, Float) )                    = ControlSetMap.Single( tup._1, tup._2 )
   implicit def intIntControlSet( tup: (Int, Int) )                        = ControlSetMap.Single( tup._1, tup._2.toFloat )
   implicit def intDoubleControlSet( tup: (Int, Double) )                  = ControlSetMap.Single( tup._1, tup._2.toFloat )
   implicit def stringFloatControlSet( tup: (String, Float) )              = ControlSetMap.Single( tup._1, tup._2 )
   implicit def stringIntControlSet( tup: (String, Int) )                  = ControlSetMap.Single( tup._1, tup._2.toFloat )
   implicit def stringDoubleControlSet( tup: (String, Double) )            = ControlSetMap.Single( tup._1, tup._2.toFloat )
}

private[synth] sealed trait MultiControlSetMapImplicits {
   implicit def intFloatsControlSet( tup: (Int, IIdxSeq[ Float ]))         = ControlSetMap.Multi( tup._1, tup._2 )
   implicit def stringFloatsControlSet( tup: (String, IIdxSeq[ Float ]))   = ControlSetMap.Multi( tup._1, tup._2 )
}

object ControlKBusMap {
//   object Single {
      implicit def intIntControlKBus( tup: (Int, Int) )                 = Single( tup._1, tup._2 )
      implicit def stringIntControlKBus( tup: (String, Int) )           = Single( tup._1, tup._2 )
//   }
   final case class Single( key: Any, index: Int )
   extends ControlKBusMap {
      def toMapSeq: IIdxSeq[ Any ]  = IIdxSeq( key, index )
      def toMapnSeq: IIdxSeq[ Any ] = IIdxSeq( key, index, 1 )
   }

//   object Multi {
      implicit def intKBusControlKBus( tup: (Int, ControlBus) )         = Multi( tup._1, tup._2.index, tup._2.numChannels )
      implicit def stringKBusControlKBus( tup: (String, ControlBus) )   = Multi( tup._1, tup._2.index, tup._2.numChannels )
//   }
   final case class Multi( key: Any, index: Int, numChannels: Int )
   extends ControlKBusMap {
      def toMapnSeq: IIdxSeq[ Any ] = IIdxSeq( key, index, numChannels )
   }

}
sealed trait ControlKBusMap {
//   def toMapSeq: IIdxSeq[ Any ]
   def toMapnSeq: IIdxSeq[ Any ]
}

object ControlABusMap {
//   object Single {
      implicit def intIntControlABus( tup: (Int, Int) )                 = Single( tup._1, tup._2 )
      implicit def stringIntControlABus( tup: (String, Int) )           = Single( tup._1, tup._2 )
//   }
   final case class Single( key: Any, index: Int )
   extends ControlABusMap {
      def toMapaSeq: IIdxSeq[ Any ]  = IIdxSeq( key, index )
      def toMapanSeq: IIdxSeq[ Any ] = IIdxSeq( key, index, 1 )
   }

//   object Multi {
      implicit def intABusControlABus( tup: (Int, AudioBus) )           = Multi( tup._1, tup._2.index, tup._2.numChannels )
      implicit def stringABusControlABus( tup: (String, AudioBus) )     = Multi( tup._1, tup._2.index, tup._2.numChannels )
//   }
   final case class Multi( key: Any, index: Int, numChannels: Int )
   extends ControlABusMap {
      def toMapanSeq: IIdxSeq[ Any ] = IIdxSeq( key, index, numChannels )
   }
}
sealed trait ControlABusMap {
   def toMapanSeq: IIdxSeq[ Any ]
}
