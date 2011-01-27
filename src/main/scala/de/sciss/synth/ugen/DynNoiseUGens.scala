/*
 * DynNoiseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 27 20:56:40 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
/**
 * A dynamic step noise UGen. Like `LFNoise0`, it generates abruptly changing random values
 * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
 * There is no time quantization, and it there is fast recovery from low freq values.
 * 
 * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
 * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
 * to hang when freqs get very low.
 * 
 * If very high or very low freqs are not needed, or fixed freqs are used,
 * `LFNoise0` is more efficient.
 * 
 * @see [[de.sciss.synth.ugen.LFNoise0]]
 * @see [[de.sciss.synth.ugen.LFDNoise1]]
 * @see [[de.sciss.synth.ugen.LFDNoise2]]
 * @see [[de.sciss.synth.ugen.TRand]]
 */
object LFDNoise0 {
   def kr: LFDNoise0[control] = kr()
/**
 * @param freq            rate at which to generate random values.
 */
def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDNoise0[audio] = ar()
/**
 * @param freq            rate at which to generate random values.
 */
def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
/**
 * A dynamic step noise UGen. Like `LFNoise0`, it generates abruptly changing random values
 * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
 * There is no time quantization, and it there is fast recovery from low freq values.
 * 
 * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
 * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
 * to hang when freqs get very low.
 * 
 * If very high or very low freqs are not needed, or fixed freqs are used,
 * `LFNoise0` is more efficient.
 * 
 * @param freq            rate at which to generate random values.
 * 
 * @see [[de.sciss.synth.ugen.LFNoise0]]
 * @see [[de.sciss.synth.ugen.LFDNoise1]]
 * @see [[de.sciss.synth.ugen.LFDNoise2]]
 * @see [[de.sciss.synth.ugen.TRand]]
 */
case class LFDNoise0[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFDNoise0UGen[R]] with UsesRandSeed {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDNoise0UGen(rate, _freq(i)))
   }
}
case class LFDNoise0UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
/**
 * A dynamic ramp noise UGen. Like `LFNoise1`, it generates linearly interpolated random values
 * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
 * There is no time quantization, and it there is fast recovery from low freq values.
 * 
 * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
 * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
 * to hang when freqs get very low.
 * 
 * If very high or very low freqs are not needed, or fixed freqs are used,
 * `LFNoise1` is more efficient.
 * 
 * @see [[de.sciss.synth.ugen.LFNoise1]]
 * @see [[de.sciss.synth.ugen.LFDNoise0]]
 * @see [[de.sciss.synth.ugen.LFDNoise2]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
object LFDNoise1 {
   def kr: LFDNoise1[control] = kr()
/**
 * @param freq            rate at which to generate random values.
 */
def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDNoise1[audio] = ar()
/**
 * @param freq            rate at which to generate random values.
 */
def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
/**
 * A dynamic ramp noise UGen. Like `LFNoise1`, it generates linearly interpolated random values
 * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
 * There is no time quantization, and it there is fast recovery from low freq values.
 * 
 * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
 * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
 * to hang when freqs get very low.
 * 
 * If very high or very low freqs are not needed, or fixed freqs are used,
 * `LFNoise1` is more efficient.
 * 
 * @param freq            rate at which to generate random values.
 * 
 * @see [[de.sciss.synth.ugen.LFNoise1]]
 * @see [[de.sciss.synth.ugen.LFDNoise0]]
 * @see [[de.sciss.synth.ugen.LFDNoise2]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
case class LFDNoise1[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFDNoise1UGen[R]] with UsesRandSeed {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDNoise1UGen(rate, _freq(i)))
   }
}
case class LFDNoise1UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFDNoise3 {
   def kr: LFDNoise3[control] = kr()
/**
 * @param freq            rate at which to generate random values.
 */
def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDNoise3[audio] = ar()
/**
 * @param freq            rate at which to generate random values.
 */
def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
/**
 * @param freq            rate at which to generate random values.
 */
case class LFDNoise3[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFDNoise3UGen[R]] with UsesRandSeed {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDNoise3UGen(rate, _freq(i)))
   }
}
case class LFDNoise3UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFDClipNoise {
   def kr: LFDClipNoise[control] = kr()
/**
 * @param freq            rate at which to generate random values.
 */
def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFDClipNoise[audio] = ar()
/**
 * @param freq            rate at which to generate random values.
 */
def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
/**
 * @param freq            rate at which to generate random values.
 */
case class LFDClipNoise[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFDClipNoiseUGen[R]] with UsesRandSeed {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFDClipNoiseUGen(rate, _freq(i)))
   }
}
case class LFDClipNoiseUGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed