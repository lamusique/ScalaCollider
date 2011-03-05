/*
 * OSCUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Mar 04 23:36:58 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object DegreeToKey {
   def kr(buf: AnyGE, in: AnyGE, octave: AnyGE = 12.0f) = apply[control](control, buf, in, octave)
   def ar(buf: AnyGE, in: AnyGE, octave: AnyGE = 12.0f) = apply[audio](audio, buf, in, octave)
}
final case class DegreeToKey[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, octave: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, octave.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DegreeToKey", rate, _args)
}
/**
 * A UGen which selects among a sequence of inputs, according to an index signal.
 * Note that, although only one signal of the `multi` input is let through at
 * a time, sill all ugens are continuously running.
 * 
 * @see [[de.sciss.synth.ugen.TWindex]]
 */
object Select {
   
   /**
    * @param index           an index signal into the channels of the `multi` argument. The index
    *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
    *                        is truncated to its integer part (not rounded), hence using for instance an
    *                        index of `0.9` will still be interpreted as index `0`.
    * @param in              a graph element which is composed of the channels to be indexed.
    */
   def kr(index: AnyGE, in: AnyGE) = apply[control](control, index, in)
   /**
    * @param index           an index signal into the channels of the `multi` argument. The index
    *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
    *                        is truncated to its integer part (not rounded), hence using for instance an
    *                        index of `0.9` will still be interpreted as index `0`.
    * @param in              a graph element which is composed of the channels to be indexed.
    */
   def ar(index: AnyGE, in: AnyGE) = apply[audio](audio, index, in)
}
/**
 * A UGen which selects among a sequence of inputs, according to an index signal.
 * Note that, although only one signal of the `multi` input is let through at
 * a time, sill all ugens are continuously running.
 * 
 * @param index           an index signal into the channels of the `multi` argument. The index
 *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
 *                        is truncated to its integer part (not rounded), hence using for instance an
 *                        index of `0.9` will still be interpreted as index `0`.
 * @param in              a graph element which is composed of the channels to be indexed.
 * 
 * @see [[de.sciss.synth.ugen.TWindex]]
 */
final case class Select[R <: Rate](rate: R, index: AnyGE, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(index.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Select", rate, _args)
}
/**
 * A UGen providing a probability-weighted index into a sequence upon receiving a trigger.
 * 
 * When triggered, returns a random index value based the values of the channels of the
 * `prob` argument functioning as probabilities. The index is zero based, hence goes from
 * `0` to `prob.numOutputs - 1`.
 * 
 * By default the sequence of probabilities should sum to 1.0, however for convenience, this
 * can be achieved by the ugen when the `normalize` flag is set to 1 (less efficient).
 * 
 * @see [[de.sciss.synth.ugen.Select]]
 */
object TWindex {
   
   /**
    * @param trig            the trigger used to calculate a new index. a trigger occurs when passing
    *                        from non-positive to positive
    * @param prob            a multi-channel graph element, where the output channels correspond to
    *                        to the probabilites of their respective indices being chosen.
    * @param normalize       `0` if the seq argument already sums up to 1.0 and thus doesn't need
    *                        normalization, `1` if the sum is not guaranteed to be 1.0 and thus the ugen is asked
    *                        to provide the normalization.
    */
   def kr(trig: AnyGE, prob: AnyGE, normalize: AnyGE = 0.0f) = apply[control](control, trig, prob, normalize)
   /**
    * @param trig            the trigger used to calculate a new index. a trigger occurs when passing
    *                        from non-positive to positive
    * @param prob            a multi-channel graph element, where the output channels correspond to
    *                        to the probabilites of their respective indices being chosen.
    * @param normalize       `0` if the seq argument already sums up to 1.0 and thus doesn't need
    *                        normalization, `1` if the sum is not guaranteed to be 1.0 and thus the ugen is asked
    *                        to provide the normalization.
    */
   def ar(trig: AnyGE, prob: AnyGE, normalize: AnyGE = 0.0f) = apply[audio](audio, trig, prob, normalize)
}
/**
 * A UGen providing a probability-weighted index into a sequence upon receiving a trigger.
 * 
 * When triggered, returns a random index value based the values of the channels of the
 * `prob` argument functioning as probabilities. The index is zero based, hence goes from
 * `0` to `prob.numOutputs - 1`.
 * 
 * By default the sequence of probabilities should sum to 1.0, however for convenience, this
 * can be achieved by the ugen when the `normalize` flag is set to 1 (less efficient).
 * 
 * @param trig            the trigger used to calculate a new index. a trigger occurs when passing
 *                        from non-positive to positive
 * @param prob            a multi-channel graph element, where the output channels correspond to
 *                        to the probabilites of their respective indices being chosen.
 * @param normalize       `0` if the seq argument already sums up to 1.0 and thus doesn't need
 *                        normalization, `1` if the sum is not guaranteed to be 1.0 and thus the ugen is asked
 *                        to provide the normalization.
 * 
 * @see [[de.sciss.synth.ugen.Select]]
 */
final case class TWindex[R <: Rate](rate: R, trig: AnyGE, prob: AnyGE, normalize: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, prob.expand, normalize.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TWindex", rate, _args)
}
object Index {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class Index[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Index", rate, _args)
}
object WrapIndex {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class WrapIndex[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("WrapIndex", rate, _args)
}
object IndexInBetween {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class IndexInBetween[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("IndexInBetween", rate, _args)
}
object DetectIndex {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class DetectIndex[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DetectIndex", rate, _args)
}
object Shaper {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class Shaper[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Shaper", rate, _args)
}
object FSinOsc {
   def ar: FSinOsc[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
   def kr: FSinOsc[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
}
final case class FSinOsc[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FSinOsc", rate, _args)
}
/**
 * A Sinusoidal (sine tone) oscillator UGen.
 * This is the same as `Osc` except that it uses a built-in interpolating sine table of 8192 entries.
 * 
 * @see [[de.sciss.synth.ugen.Osc]]
 * @see [[de.sciss.synth.ugen.FSinOsc]]
 */
object SinOsc {
   def ar: SinOsc[audio] = ar()
   /**
    * @param freq            frequency in Hertz
    * @param phase           phase offset or modulator in radians
    */
   def ar(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, freq, phase)
   def kr: SinOsc[control] = kr()
   /**
    * @param freq            frequency in Hertz
    * @param phase           phase offset or modulator in radians
    */
   def kr(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, freq, phase)
}
/**
 * A Sinusoidal (sine tone) oscillator UGen.
 * This is the same as `Osc` except that it uses a built-in interpolating sine table of 8192 entries.
 * 
 * @param freq            frequency in Hertz
 * @param phase           phase offset or modulator in radians
 * 
 * @see [[de.sciss.synth.ugen.Osc]]
 * @see [[de.sciss.synth.ugen.FSinOsc]]
 */
final case class SinOsc[R <: Rate](rate: R, freq: AnyGE, phase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SinOsc", rate, _args)
}
object SinOscFB {
   def ar: SinOscFB[audio] = ar()
   def ar(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply[audio](audio, freq, feedback)
   def kr: SinOscFB[control] = kr()
   def kr(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply[control](control, freq, feedback)
}
final case class SinOscFB[R <: Rate](rate: R, freq: AnyGE, feedback: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, feedback.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SinOscFB", rate, _args)
}
object VOsc {
   def ar(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, bufPos, freq, phase)
   def kr(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, bufPos, freq, phase)
}
final case class VOsc[R <: Rate](rate: R, bufPos: AnyGE, freq: AnyGE, phase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bufPos.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("VOsc", rate, _args)
}
object VOsc3 {
   def ar(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply[audio](audio, bufPos, freq1, freq2, freq3)
   def kr(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply[control](control, bufPos, freq1, freq2, freq3)
}
final case class VOsc3[R <: Rate](rate: R, bufPos: AnyGE, freq1: AnyGE, freq2: AnyGE, freq3: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bufPos.expand, freq1.expand, freq2.expand, freq3.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("VOsc3", rate, _args)
}
object Osc {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, buf, freq, phase)
   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, buf, freq, phase)
}
final case class Osc[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Osc", rate, _args)
}
object OscN {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, buf, freq, phase)
   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, buf, freq, phase)
}
final case class OscN[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("OscN", rate, _args)
}
object COsc {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, beats: AnyGE = 0.5f) = apply[audio](audio, buf, freq, beats)
}
final case class COsc[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, beats: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, freq.expand, beats.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("COsc", rate, _args)
}
object Formant {
   def ar: Formant[audio] = ar()
   def ar(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply[audio](audio, fundFreq, formFreq, bw)
   def kr: Formant[control] = kr()
   def kr(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply[control](control, fundFreq, formFreq, bw)
}
final case class Formant[R <: Rate](rate: R, fundFreq: AnyGE, formFreq: AnyGE, bw: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(fundFreq.expand, formFreq.expand, bw.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Formant", rate, _args)
}
object Blip {
   def kr: Blip[control] = kr()
   def kr(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply[control](control, freq, numHarm)
   def ar: Blip[audio] = ar()
   def ar(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply[audio](audio, freq, numHarm)
}
final case class Blip[R <: Rate](rate: R, freq: AnyGE, numHarm: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, numHarm.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Blip", rate, _args)
}
object Saw {
   def kr: Saw[control] = kr()
   def kr(freq: AnyGE = 440.0f) = apply[control](control, freq)
   def ar: Saw[audio] = ar()
   def ar(freq: AnyGE = 440.0f) = apply[audio](audio, freq)
}
final case class Saw[R <: Rate](rate: R, freq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Saw", rate, _args)
}
object Pulse {
   def kr: Pulse[control] = kr()
   def kr(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply[control](control, freq, width)
   def ar: Pulse[audio] = ar()
   def ar(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply[audio](audio, freq, width)
}
final case class Pulse[R <: Rate](rate: R, freq: AnyGE, width: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, width.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Pulse", rate, _args)
}
object Klang {
   def ar(specs: AnyGE, freqScale: AnyGE = 1.0f, freqOffset: AnyGE = 0.0f) = apply(specs, freqScale, freqOffset)
}
final case class Klang(specs: AnyGE, freqScale: AnyGE, freqOffset: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freqScale.expand, freqOffset.expand).++(specs.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Klang", audio, _args)
}
object Klank {
   def ar(specs: AnyGE, in: AnyGE, freqScale: AnyGE = 1.0f, freqOffset: AnyGE = 0.0f, decayScale: AnyGE = 1.0f) = apply(specs, in, freqScale, freqOffset, decayScale)
}
final case class Klank(specs: AnyGE, in: AnyGE, freqScale: AnyGE, freqOffset: AnyGE, decayScale: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freqScale.expand, freqOffset.expand, decayScale.expand).++(specs.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Klank", audio, _args)
}