/*
 * DynNoiseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Jan 05 01:09:04 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object LFDNoise0 {
   def kr: LFDNoise0[control] = kr( )
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDNoise0[audio] = ar( )
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFDNoise0[R <: Rate](rate: R, freq: AnyGE) extends GE[R, LFDNoise0UGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDNoise0UGen(rate, _freq(i)))
   }
}
case class LFDNoise0UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFDNoise1 {
   def kr: LFDNoise1[control] = kr( )
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDNoise1[audio] = ar( )
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFDNoise1[R <: Rate](rate: R, freq: AnyGE) extends GE[R, LFDNoise1UGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDNoise1UGen(rate, _freq(i)))
   }
}
case class LFDNoise1UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFDNoise3 {
   def kr: LFDNoise3[control] = kr( )
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDNoise3[audio] = ar( )
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFDNoise3[R <: Rate](rate: R, freq: AnyGE) extends GE[R, LFDNoise3UGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDNoise3UGen(rate, _freq(i)))
   }
}
case class LFDNoise3UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFDClipNoise {
   def kr: LFDClipNoise[control] = kr( )
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDClipNoise[audio] = ar( )
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFDClipNoise[R <: Rate](rate: R, freq: AnyGE) extends GE[R, LFDClipNoiseUGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDClipNoiseUGen(rate, _freq(i)))
   }
}
case class LFDClipNoiseUGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed