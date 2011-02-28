/*
 * OSCUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 27 23:03:33 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
//object DegreeToKey {
//   def kr(buf: AnyGE, in: AnyGE, octave: AnyGE = 12.0f) = apply(control, buf, in, octave)
//   def ar(buf: AnyGE, in: AnyGE, octave: AnyGE = 12.0f) = apply(audio, buf, in, octave)
//}
//case class DegreeToKey(rate: Rate, buf: AnyGE, in: AnyGE, octave: AnyGE) extends SingleOutUGenSource[DegreeToKeyUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _octave: IIdxSeq[UGenIn] = octave.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_octave = _octave.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_octave)
//      IIdxSeq.tabulate(_exp_)(i => DegreeToKeyUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _octave(i.%(_sz_octave))))
//   }
//}
//case class DegreeToKeyUGen(rate: Rate, buf: UGenIn, in: UGenIn, octave: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, octave))
///**
// * A UGen which selects among a sequence of inputs, according to an index signal.
// * Note that, although only one signal of the `multi` input is let through at
// * a time, sill all ugens are continuously running.
// *
// * @see [[de.sciss.synth.ugen.TWindex]]
// */
//object Select {
//
///**
// * @param index           an index signal into the channels of the `multi` argument. The index
// *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
// *                        is truncated to its integer part (not rounded), hence using for instance an
// *                        index of `0.9` will still be interpreted as index `0`.
// * @param in              a graph element which is composed of the channels to be indexed.
// */
//def kr(index: AnyGE, in: Multi[AnyGE]) = apply(control, index, in)
///**
// * @param index           an index signal into the channels of the `multi` argument. The index
// *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
// *                        is truncated to its integer part (not rounded), hence using for instance an
// *                        index of `0.9` will still be interpreted as index `0`.
// * @param in              a graph element which is composed of the channels to be indexed.
// */
//def ar(index: AnyGE, in: Multi[AnyGE]) = apply(audio, index, in)
//}
///**
// * A UGen which selects among a sequence of inputs, according to an index signal.
// * Note that, although only one signal of the `multi` input is let through at
// * a time, sill all ugens are continuously running.
// *
// * @param index           an index signal into the channels of the `multi` argument. The index
// *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
// *                        is truncated to its integer part (not rounded), hence using for instance an
// *                        index of `0.9` will still be interpreted as index `0`.
// * @param in              a graph element which is composed of the channels to be indexed.
// *
// * @see [[de.sciss.synth.ugen.TWindex]]
// */
//case class Select(rate: Rate, index: AnyGE, in: Multi[AnyGE]) extends SingleOutUGenSource[SelectUGen] {
//   protected def expandUGens = {
//      val _index: IIdxSeq[UGenIn] = index.expand
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      val _sz_index = _index.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_index, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => SelectUGen(rate, _index(i.%(_sz_index)), _in(i.%(_sz_in)).expand))
//   }
//}
//case class SelectUGen(rate: Rate, index: UGenIn, in: IIdxSeq[UGenIn]) extends SingleOutUGen(IIdxSeq[UGenIn](index).++(in))
///**
// * A UGen providing a probability-weighted index into a sequence upon receiving a trigger.
// *
// * When triggered, returns a random index value based the values of the channels of the
// * `prob` argument functioning as probabilities. The index is zero based, hence goes from
// * `0` to `prob.numOutputs - 1`.
// *
// * By default the sequence of probabilities should sum to 1.0, however for convenience, this
// * can be achieved by the ugen when the `normalize` flag is set to 1 (less efficient).
// *
// * @see [[de.sciss.synth.ugen.Select]]
// */
//object TWindex {
//
///**
// * @param trig            the trigger used to calculate a new index. a trigger occurs when passing
// *                        from non-positive to positive
// * @param prob            a multi-channel graph element, where the output channels correspond to
// *                        to the probabilites of their respective indices being chosen.
// * @param normalize       `0` if the seq argument already sums up to 1.0 and thus doesn't need
// *                        normalization, `1` if the sum is not guaranteed to be 1.0 and thus the ugen is asked
// *                        to provide the normalization.
// */
//def kr(trig: AnyGE, prob: AnyGE, normalize: AnyGE = 0.0f) = apply(control, trig, prob, normalize)
///**
// * @param trig            the trigger used to calculate a new index. a trigger occurs when passing
// *                        from non-positive to positive
// * @param prob            a multi-channel graph element, where the output channels correspond to
// *                        to the probabilites of their respective indices being chosen.
// * @param normalize       `0` if the seq argument already sums up to 1.0 and thus doesn't need
// *                        normalization, `1` if the sum is not guaranteed to be 1.0 and thus the ugen is asked
// *                        to provide the normalization.
// */
//def ar(trig: AnyGE, prob: AnyGE, normalize: AnyGE = 0.0f) = apply(audio, trig, prob, normalize)
//}
///**
// * A UGen providing a probability-weighted index into a sequence upon receiving a trigger.
// *
// * When triggered, returns a random index value based the values of the channels of the
// * `prob` argument functioning as probabilities. The index is zero based, hence goes from
// * `0` to `prob.numOutputs - 1`.
// *
// * By default the sequence of probabilities should sum to 1.0, however for convenience, this
// * can be achieved by the ugen when the `normalize` flag is set to 1 (less efficient).
// *
// * @param trig            the trigger used to calculate a new index. a trigger occurs when passing
// *                        from non-positive to positive
// * @param prob            a multi-channel graph element, where the output channels correspond to
// *                        to the probabilites of their respective indices being chosen.
// * @param normalize       `0` if the seq argument already sums up to 1.0 and thus doesn't need
// *                        normalization, `1` if the sum is not guaranteed to be 1.0 and thus the ugen is asked
// *                        to provide the normalization.
// *
// * @see [[de.sciss.synth.ugen.Select]]
// */
//case class TWindex(rate: Rate, trig: AnyGE, prob: AnyGE, normalize: AnyGE) extends SingleOutUGenSource[TWindexUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _prob: IIdxSeq[UGenIn] = prob.expand
//      val _normalize: IIdxSeq[UGenIn] = normalize.expand
//      val _sz_trig = _trig.size
//      val _sz_prob = _prob.size
//      val _sz_normalize = _normalize.size
//      val _exp_ = maxInt(_sz_trig, _sz_prob, _sz_normalize)
//      IIdxSeq.tabulate(_exp_)(i => TWindexUGen(rate, _trig(i.%(_sz_trig)), _prob(i.%(_sz_prob)), _normalize(i.%(_sz_normalize))))
//   }
//}
//case class TWindexUGen(rate: Rate, trig: UGenIn, prob: UGenIn, normalize: UGenIn) extends SingleOutUGen(IIdxSeq(trig, prob, normalize))
//object Index {
//   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply(control, buf, in)
//   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply(audio, buf, in)
//}
//case class Index(rate: Rate, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[IndexUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => IndexUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
//   }
//}
//case class IndexUGen(rate: Rate, buf: UGenIn, in: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in))
//object WrapIndex {
//   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply(control, buf, in)
//   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply(audio, buf, in)
//}
//case class WrapIndex(rate: Rate, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[WrapIndexUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => WrapIndexUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
//   }
//}
//case class WrapIndexUGen(rate: Rate, buf: UGenIn, in: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in))
//object IndexInBetween {
//   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply(control, buf, in)
//   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply(audio, buf, in)
//}
//case class IndexInBetween(rate: Rate, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[IndexInBetweenUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => IndexInBetweenUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
//   }
//}
//case class IndexInBetweenUGen(rate: Rate, buf: UGenIn, in: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in))
//object DetectIndex {
//   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply(control, buf, in)
//   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply(audio, buf, in)
//}
//case class DetectIndex(rate: Rate, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[DetectIndexUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => DetectIndexUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
//   }
//}
//case class DetectIndexUGen(rate: Rate, buf: UGenIn, in: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in))
//object Shaper {
//   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply(control, buf, in)
//   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply(audio, buf, in)
//}
//case class Shaper(rate: Rate, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[ShaperUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => ShaperUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
//   }
//}
//case class ShaperUGen(rate: Rate, buf: UGenIn, in: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in))
//object FSinOsc {
//   def ar: FSinOsc = ar()
//   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(audio, freq, iphase)
//   def kr: FSinOsc = kr()
//   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(control, freq, iphase)
//}
//case class FSinOsc(rate: Rate, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[FSinOscUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _sz_freq = _freq.size
//      val _sz_iphase = _iphase.size
//      val _exp_ = maxInt(_sz_freq, _sz_iphase)
//      IIdxSeq.tabulate(_exp_)(i => FSinOscUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
//   }
//}
//case class FSinOscUGen(rate: Rate, freq: UGenIn, iphase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, iphase))
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
def ar(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(audio, freq, phase)
   def kr: SinOsc[control] = kr()
/**
 * @param freq            frequency in Hertz
 * @param phase           phase offset or modulator in radians
 */
def kr(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(control, freq, phase)
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
case class SinOsc[ R <: Rate ](rate: R, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R, SinOscUGen] {
   protected def expandUGens = {
      val _freq: IIdxSeq[UGenIn] = freq.expand
      val _phase: IIdxSeq[UGenIn] = phase.expand
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => SinOscUGen(rate, _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class SinOscUGen(rate: Rate, freq: UGenIn, phase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, phase))
//object SinOscFB {
//   def ar: SinOscFB = ar()
//   def ar(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply(audio, freq, feedback)
//   def kr: SinOscFB = kr()
//   def kr(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply(control, freq, feedback)
//}
//case class SinOscFB(rate: Rate, freq: AnyGE, feedback: AnyGE) extends SingleOutUGenSource[SinOscFBUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _feedback: IIdxSeq[UGenIn] = feedback.expand
//      val _sz_freq = _freq.size
//      val _sz_feedback = _feedback.size
//      val _exp_ = maxInt(_sz_freq, _sz_feedback)
//      IIdxSeq.tabulate(_exp_)(i => SinOscFBUGen(rate, _freq(i.%(_sz_freq)), _feedback(i.%(_sz_feedback))))
//   }
//}
//case class SinOscFBUGen(rate: Rate, freq: UGenIn, feedback: UGenIn) extends SingleOutUGen(IIdxSeq(freq, feedback))
//object VOsc {
//   def ar(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(audio, bufPos, freq, phase)
//   def kr(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(control, bufPos, freq, phase)
//}
//case class VOsc(rate: Rate, bufPos: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[VOscUGen] {
//   protected def expandUGens = {
//      val _bufPos: IIdxSeq[UGenIn] = bufPos.expand
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _phase: IIdxSeq[UGenIn] = phase.expand
//      val _sz_bufPos = _bufPos.size
//      val _sz_freq = _freq.size
//      val _sz_phase = _phase.size
//      val _exp_ = maxInt(_sz_bufPos, _sz_freq, _sz_phase)
//      IIdxSeq.tabulate(_exp_)(i => VOscUGen(rate, _bufPos(i.%(_sz_bufPos)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
//   }
//}
//case class VOscUGen(rate: Rate, bufPos: UGenIn, freq: UGenIn, phase: UGenIn) extends SingleOutUGen(IIdxSeq(bufPos, freq, phase))
//object VOsc3 {
//   def ar(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply(audio, bufPos, freq1, freq2, freq3)
//   def kr(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply(control, bufPos, freq1, freq2, freq3)
//}
//case class VOsc3(rate: Rate, bufPos: AnyGE, freq1: AnyGE, freq2: AnyGE, freq3: AnyGE) extends SingleOutUGenSource[VOsc3UGen] {
//   protected def expandUGens = {
//      val _bufPos: IIdxSeq[UGenIn] = bufPos.expand
//      val _freq1: IIdxSeq[UGenIn] = freq1.expand
//      val _freq2: IIdxSeq[UGenIn] = freq2.expand
//      val _freq3: IIdxSeq[UGenIn] = freq3.expand
//      val _sz_bufPos = _bufPos.size
//      val _sz_freq1 = _freq1.size
//      val _sz_freq2 = _freq2.size
//      val _sz_freq3 = _freq3.size
//      val _exp_ = maxInt(_sz_bufPos, _sz_freq1, _sz_freq2, _sz_freq3)
//      IIdxSeq.tabulate(_exp_)(i => VOsc3UGen(rate, _bufPos(i.%(_sz_bufPos)), _freq1(i.%(_sz_freq1)), _freq2(i.%(_sz_freq2)), _freq3(i.%(_sz_freq3))))
//   }
//}
//case class VOsc3UGen(rate: Rate, bufPos: UGenIn, freq1: UGenIn, freq2: UGenIn, freq3: UGenIn) extends SingleOutUGen(IIdxSeq(bufPos, freq1, freq2, freq3))
//object Osc {
//   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(audio, buf, freq, phase)
//   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(control, buf, freq, phase)
//}
//case class Osc(rate: Rate, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[OscUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _phase: IIdxSeq[UGenIn] = phase.expand
//      val _sz_buf = _buf.size
//      val _sz_freq = _freq.size
//      val _sz_phase = _phase.size
//      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_phase)
//      IIdxSeq.tabulate(_exp_)(i => OscUGen(rate, _buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
//   }
//}
//case class OscUGen(rate: Rate, buf: UGenIn, freq: UGenIn, phase: UGenIn) extends SingleOutUGen(IIdxSeq(buf, freq, phase))
//object OscN {
//   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(audio, buf, freq, phase)
//   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(control, buf, freq, phase)
//}
//case class OscN(rate: Rate, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[OscNUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _phase: IIdxSeq[UGenIn] = phase.expand
//      val _sz_buf = _buf.size
//      val _sz_freq = _freq.size
//      val _sz_phase = _phase.size
//      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_phase)
//      IIdxSeq.tabulate(_exp_)(i => OscNUGen(rate, _buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
//   }
//}
//case class OscNUGen(rate: Rate, buf: UGenIn, freq: UGenIn, phase: UGenIn) extends SingleOutUGen(IIdxSeq(buf, freq, phase))
//object COsc {
//   def ar(buf: AnyGE, freq: AnyGE = 440.0f, beats: AnyGE = 0.5f) = apply(audio, buf, freq, beats)
//}
//case class COsc(rate: Rate, buf: AnyGE, freq: AnyGE, beats: AnyGE) extends SingleOutUGenSource[COscUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _beats: IIdxSeq[UGenIn] = beats.expand
//      val _sz_buf = _buf.size
//      val _sz_freq = _freq.size
//      val _sz_beats = _beats.size
//      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_beats)
//      IIdxSeq.tabulate(_exp_)(i => COscUGen(rate, _buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _beats(i.%(_sz_beats))))
//   }
//}
//case class COscUGen(rate: Rate, buf: UGenIn, freq: UGenIn, beats: UGenIn) extends SingleOutUGen(IIdxSeq(buf, freq, beats))
//object Formant {
//   def ar: Formant = ar()
//   def ar(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply(audio, fundFreq, formFreq, bw)
//   def kr: Formant = kr()
//   def kr(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply(control, fundFreq, formFreq, bw)
//}
//case class Formant(rate: Rate, fundFreq: AnyGE, formFreq: AnyGE, bw: AnyGE) extends SingleOutUGenSource[FormantUGen] {
//   protected def expandUGens = {
//      val _fundFreq: IIdxSeq[UGenIn] = fundFreq.expand
//      val _formFreq: IIdxSeq[UGenIn] = formFreq.expand
//      val _bw: IIdxSeq[UGenIn] = bw.expand
//      val _sz_fundFreq = _fundFreq.size
//      val _sz_formFreq = _formFreq.size
//      val _sz_bw = _bw.size
//      val _exp_ = maxInt(_sz_fundFreq, _sz_formFreq, _sz_bw)
//      IIdxSeq.tabulate(_exp_)(i => FormantUGen(rate, _fundFreq(i.%(_sz_fundFreq)), _formFreq(i.%(_sz_formFreq)), _bw(i.%(_sz_bw))))
//   }
//}
//case class FormantUGen(rate: Rate, fundFreq: UGenIn, formFreq: UGenIn, bw: UGenIn) extends SingleOutUGen(IIdxSeq(fundFreq, formFreq, bw))
//object Blip {
//   def kr: Blip = kr()
//   def kr(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply(control, freq, numHarm)
//   def ar: Blip = ar()
//   def ar(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply(audio, freq, numHarm)
//}
//case class Blip(rate: Rate, freq: AnyGE, numHarm: AnyGE) extends SingleOutUGenSource[BlipUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _numHarm: IIdxSeq[UGenIn] = numHarm.expand
//      val _sz_freq = _freq.size
//      val _sz_numHarm = _numHarm.size
//      val _exp_ = maxInt(_sz_freq, _sz_numHarm)
//      IIdxSeq.tabulate(_exp_)(i => BlipUGen(rate, _freq(i.%(_sz_freq)), _numHarm(i.%(_sz_numHarm))))
//   }
//}
//case class BlipUGen(rate: Rate, freq: UGenIn, numHarm: UGenIn) extends SingleOutUGen(IIdxSeq(freq, numHarm))
//object Saw {
//   def kr: Saw = kr()
//   def kr(freq: AnyGE = 440.0f) = apply(control, freq)
//   def ar: Saw = ar()
//   def ar(freq: AnyGE = 440.0f) = apply(audio, freq)
//}
//case class Saw(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[SawUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => SawUGen(rate, _freq(i)))
//   }
//}
//case class SawUGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq))
//object Pulse {
//   def kr: Pulse = kr()
//   def kr(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply(control, freq, width)
//   def ar: Pulse = ar()
//   def ar(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply(audio, freq, width)
//}
//case class Pulse(rate: Rate, freq: AnyGE, width: AnyGE) extends SingleOutUGenSource[PulseUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _width: IIdxSeq[UGenIn] = width.expand
//      val _sz_freq = _freq.size
//      val _sz_width = _width.size
//      val _exp_ = maxInt(_sz_freq, _sz_width)
//      IIdxSeq.tabulate(_exp_)(i => PulseUGen(rate, _freq(i.%(_sz_freq)), _width(i.%(_sz_width))))
//   }
//}
//case class PulseUGen(rate: Rate, freq: UGenIn, width: UGenIn) extends SingleOutUGen(IIdxSeq(freq, width))