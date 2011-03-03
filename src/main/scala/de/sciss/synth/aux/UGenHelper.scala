package de.sciss.synth.aux

private[synth] object UGenHelper {
   def maxInt( is: Int* ) : Int = is.reduceLeft( math.max( _, _ ))
}
