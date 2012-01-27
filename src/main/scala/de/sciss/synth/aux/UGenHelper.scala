/*
 *  UGenHelper.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2012 Hanns Holger Rutz. All rights reserved.
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
 */

package de.sciss.synth
package aux

import collection.breakOut
import collection.immutable.{IndexedSeq => IIdxSeq}

private[synth] object UGenHelper {
   def maxInt( is: Int* ) : Int = is.reduceLeft( math.max( _, _ ))
   def stringArg( s: String ) : IIdxSeq[ UGenIn ] = {
      val bs = s.getBytes
      Constant( bs.length ) +: (bs.map( Constant( _ ))( breakOut ): IIdxSeq[ UGenIn ])
   }

   def replaceZeroesWithSilence( ins: IIdxSeq[ UGenIn ]) : IIdxSeq[ UGenIn ] = {
      val numZeroes  = ins.foldLeft( 0 )( (sum, in) => in match {
         case Constant( 0 )   => sum + 1
         case _               => sum
      })
      if( numZeroes == 0 ) {
         ins
      } else {
         // WARNING: Silent has been removed now from scsynth !!!
//         val silent = Silent.ar( numZeroes ).outputs.iterator
//         val silent = new UGen.MultiOut( "Silent", audio, IIdxSeq.fill( numZeroes )( audio ), IIdxSeq.empty ).outputs.iterator
         val silent = new UGen.MultiOut( "DC", audio, IIdxSeq( audio ), IIdxSeq( Constant( 0 ))).outputs.head
         ins.map {
            case Constant( 0 )   => silent // .next()
            case x               => x
         }
      }
   }
}
