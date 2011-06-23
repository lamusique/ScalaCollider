/*
 * DynNoiseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jun 24 00:20:25 BST 2011
 * ScalaCollider-UGen version: 0.12
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
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
   def kr: LFDNoise0 = kr()
   /**
    * @param freq            rate at which to generate random values.
    */
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFDNoise0 = ar()
   /**
    * @param freq            rate at which to generate random values.
    */
   def ar(freq: GE = 500.0f) = apply(audio, freq)
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
final case class LFDNoise0(rate: Rate, freq: GE) extends UGenSource.SingleOut("LFDNoise0") with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
 * @see [[de.sciss.synth.ugen.LFNoise1]]
 * @see [[de.sciss.synth.ugen.LFDNoise0]]
 * @see [[de.sciss.synth.ugen.LFDNoise2]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
object LFDNoise1 {
   def kr: LFDNoise1 = kr()
   /**
    * @param freq            rate at which to generate random values.
    */
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFDNoise1 = ar()
   /**
    * @param freq            rate at which to generate random values.
    */
   def ar(freq: GE = 500.0f) = apply(audio, freq)
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
final case class LFDNoise1(rate: Rate, freq: GE) extends UGenSource.SingleOut("LFDNoise1") with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LFDNoise3 {
   def kr: LFDNoise3 = kr()
   /**
    * @param freq            rate at which to generate random values.
    */
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFDNoise3 = ar()
   /**
    * @param freq            rate at which to generate random values.
    */
   def ar(freq: GE = 500.0f) = apply(audio, freq)
}
/**
 * @param freq            rate at which to generate random values.
 */
final case class LFDNoise3(rate: Rate, freq: GE) extends UGenSource.SingleOut("LFDNoise3") with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LFDClipNoise {
   def kr: LFDClipNoise = kr()
   /**
    * @param freq            rate at which to generate random values.
    */
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFDClipNoise = ar()
   /**
    * @param freq            rate at which to generate random values.
    */
   def ar(freq: GE = 500.0f) = apply(audio, freq)
}
/**
 * @param freq            rate at which to generate random values.
 */
final case class LFDClipNoise(rate: Rate, freq: GE) extends UGenSource.SingleOut("LFDClipNoise") with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}