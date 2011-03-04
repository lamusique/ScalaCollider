package de.sciss.synth.aux

import collection.breakOut
import collection.immutable.{IndexedSeq => IIdxSeq}
import de.sciss.synth.{Constant, UGenIn}

private[synth] object UGenHelper {
   def maxInt( is: Int* ) : Int = is.reduceLeft( math.max( _, _ ))
   def stringArg( s: String ) : IIdxSeq[ UGenIn ] = {
      val bs = s.getBytes()
      Constant( bs.size ) +: (bs.map( Constant( _ ))( breakOut ): IIdxSeq[ UGenIn ])
   }
}
