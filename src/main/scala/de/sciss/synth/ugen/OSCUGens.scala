/*
 * OSCUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Apr 06 02:27:48 BST 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object DegreeToKey {
   def kr(buf: GE, in: GE, octave: GE = 12.0f) = apply(control, buf, in, octave)
   def ar(buf: GE, in: GE, octave: GE = 12.0f) = apply(audio, buf, in, octave)
}
final case class DegreeToKey(rate: Rate, buf: GE, in: GE, octave: GE) extends UGenSource.SingleOut {
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
   def kr(index: GE, in: GE) = apply(control, index, in)
   /**
    * @param index           an index signal into the channels of the `multi` argument. The index
    *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
    *                        is truncated to its integer part (not rounded), hence using for instance an
    *                        index of `0.9` will still be interpreted as index `0`.
    * @param in              a graph element which is composed of the channels to be indexed.
    */
   def ar(index: GE, in: GE) = apply(audio, index, in)
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
final case class Select(rate: Rate, index: GE, in: GE) extends UGenSource.SingleOut {
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
   def kr(trig: GE, prob: GE, normalize: GE = 0.0f) = apply(control, trig, prob, normalize)
   /**
    * @param trig            the trigger used to calculate a new index. a trigger occurs when passing
    *                        from non-positive to positive
    * @param prob            a multi-channel graph element, where the output channels correspond to
    *                        to the probabilites of their respective indices being chosen.
    * @param normalize       `0` if the seq argument already sums up to 1.0 and thus doesn't need
    *                        normalization, `1` if the sum is not guaranteed to be 1.0 and thus the ugen is asked
    *                        to provide the normalization.
    */
   def ar(trig: GE, prob: GE, normalize: GE = 0.0f) = apply(audio, trig, prob, normalize)
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
final case class TWindex(rate: Rate, trig: GE, prob: GE, normalize: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, prob.expand, normalize.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TWindex", rate, _args)
}
object Index {
   def kr(buf: GE, in: GE = 0.0f) = apply(control, buf, in)
   def ar(buf: GE, in: GE = 0.0f) = apply(audio, buf, in)
}
final case class Index(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Index", rate, _args)
}
object WrapIndex {
   def kr(buf: GE, in: GE = 0.0f) = apply(control, buf, in)
   def ar(buf: GE, in: GE = 0.0f) = apply(audio, buf, in)
}
final case class WrapIndex(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("WrapIndex", rate, _args)
}
object IndexInBetween {
   def kr(buf: GE, in: GE = 0.0f) = apply(control, buf, in)
   def ar(buf: GE, in: GE = 0.0f) = apply(audio, buf, in)
}
final case class IndexInBetween(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("IndexInBetween", rate, _args)
}
object DetectIndex {
   def kr(buf: GE, in: GE = 0.0f) = apply(control, buf, in)
   def ar(buf: GE, in: GE = 0.0f) = apply(audio, buf, in)
}
final case class DetectIndex(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DetectIndex", rate, _args)
}
object Shaper {
   def kr(buf: GE, in: GE = 0.0f) = apply(control, buf, in)
   def ar(buf: GE, in: GE = 0.0f) = apply(audio, buf, in)
}
final case class Shaper(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Shaper", rate, _args)
}
object FSinOsc {
   def ar: FSinOsc = ar()
   def ar(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(audio, freq, iphase)
   def kr: FSinOsc = kr()
   def kr(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(control, freq, iphase)
}
final case class FSinOsc(rate: Rate, freq: GE, iphase: GE) extends UGenSource.SingleOut {
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
   def ar: SinOsc = ar()
   /**
    * @param freq            frequency in Hertz
    * @param phase           phase offset or modulator in radians
    */
   def ar(freq: GE = 440.0f, phase: GE = 0.0f) = apply(audio, freq, phase)
   def kr: SinOsc = kr()
   /**
    * @param freq            frequency in Hertz
    * @param phase           phase offset or modulator in radians
    */
   def kr(freq: GE = 440.0f, phase: GE = 0.0f) = apply(control, freq, phase)
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
final case class SinOsc(rate: Rate, freq: GE, phase: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SinOsc", rate, _args)
}
object SinOscFB {
   def ar: SinOscFB = ar()
   def ar(freq: GE = 440.0f, feedback: GE = 0.0f) = apply(audio, freq, feedback)
   def kr: SinOscFB = kr()
   def kr(freq: GE = 440.0f, feedback: GE = 0.0f) = apply(control, freq, feedback)
}
final case class SinOscFB(rate: Rate, freq: GE, feedback: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, feedback.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SinOscFB", rate, _args)
}
object VOsc {
   def ar(bufPos: GE, freq: GE = 440.0f, phase: GE = 0.0f) = apply(audio, bufPos, freq, phase)
   def kr(bufPos: GE, freq: GE = 440.0f, phase: GE = 0.0f) = apply(control, bufPos, freq, phase)
}
final case class VOsc(rate: Rate, bufPos: GE, freq: GE, phase: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bufPos.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("VOsc", rate, _args)
}
object VOsc3 {
   def ar(bufPos: GE, freq1: GE = 110.0f, freq2: GE = 220.0f, freq3: GE = 440.0f) = apply(audio, bufPos, freq1, freq2, freq3)
   def kr(bufPos: GE, freq1: GE = 110.0f, freq2: GE = 220.0f, freq3: GE = 440.0f) = apply(control, bufPos, freq1, freq2, freq3)
}
final case class VOsc3(rate: Rate, bufPos: GE, freq1: GE, freq2: GE, freq3: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bufPos.expand, freq1.expand, freq2.expand, freq3.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("VOsc3", rate, _args)
}
object Osc {
   def ar(buf: GE, freq: GE = 440.0f, phase: GE = 0.0f) = apply(audio, buf, freq, phase)
   def kr(buf: GE, freq: GE = 440.0f, phase: GE = 0.0f) = apply(control, buf, freq, phase)
}
final case class Osc(rate: Rate, buf: GE, freq: GE, phase: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Osc", rate, _args)
}
object OscN {
   def ar(buf: GE, freq: GE = 440.0f, phase: GE = 0.0f) = apply(audio, buf, freq, phase)
   def kr(buf: GE, freq: GE = 440.0f, phase: GE = 0.0f) = apply(control, buf, freq, phase)
}
final case class OscN(rate: Rate, buf: GE, freq: GE, phase: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("OscN", rate, _args)
}
object COsc {
   def ar(buf: GE, freq: GE = 440.0f, beats: GE = 0.5f) = apply(audio, buf, freq, beats)
}
final case class COsc(rate: Rate, buf: GE, freq: GE, beats: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, freq.expand, beats.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("COsc", rate, _args)
}
object Formant {
   def ar: Formant = ar()
   def ar(fundFreq: GE = 440.0f, formFreq: GE = 1760.0f, bw: GE = 880.0f) = apply(audio, fundFreq, formFreq, bw)
   def kr: Formant = kr()
   def kr(fundFreq: GE = 440.0f, formFreq: GE = 1760.0f, bw: GE = 880.0f) = apply(control, fundFreq, formFreq, bw)
}
final case class Formant(rate: Rate, fundFreq: GE, formFreq: GE, bw: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(fundFreq.expand, formFreq.expand, bw.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Formant", rate, _args)
}
object Blip {
   def kr: Blip = kr()
   def kr(freq: GE = 440.0f, numHarm: GE = 200.0f) = apply(control, freq, numHarm)
   def ar: Blip = ar()
   def ar(freq: GE = 440.0f, numHarm: GE = 200.0f) = apply(audio, freq, numHarm)
}
final case class Blip(rate: Rate, freq: GE, numHarm: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, numHarm.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Blip", rate, _args)
}
object Saw {
   def kr: Saw = kr()
   def kr(freq: GE = 440.0f) = apply(control, freq)
   def ar: Saw = ar()
   def ar(freq: GE = 440.0f) = apply(audio, freq)
}
final case class Saw(rate: Rate, freq: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Saw", rate, _args)
}
object Pulse {
   def kr: Pulse = kr()
   def kr(freq: GE = 440.0f, width: GE = 0.5f) = apply(control, freq, width)
   def ar: Pulse = ar()
   def ar(freq: GE = 440.0f, width: GE = 0.5f) = apply(audio, freq, width)
}
final case class Pulse(rate: Rate, freq: GE, width: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, width.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Pulse", rate, _args)
}
object Klang {
   def ar(specs: GE, freqScale: GE = 1.0f, freqOffset: GE = 0.0f) = apply(specs, freqScale, freqOffset)
}
final case class Klang(specs: GE, freqScale: GE, freqOffset: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freqScale.expand, freqOffset.expand).++(specs.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Klang", audio, _args)
}
object Klank {
   def ar(specs: GE, in: GE, freqScale: GE = 1.0f, freqOffset: GE = 0.0f, decayScale: GE = 1.0f) = apply(specs, in, freqScale, freqOffset, decayScale)
}
final case class Klank(specs: GE, in: GE, freqScale: GE, freqOffset: GE, decayScale: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freqScale.expand, freqOffset.expand, decayScale.expand).++(specs.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Klank", audio, _args)
}