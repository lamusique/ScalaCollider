package de.sciss.synth.util

private[synth] object UGenHelper {
   def maxInt( is: Int* ) : Int = is.reduceLeft( math.max( _, _ ))
}
