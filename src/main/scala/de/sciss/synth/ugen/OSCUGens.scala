/*
 * OSCUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Mar 03 04:31:06 GMT 2011
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
final case class DegreeToKey[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, octave: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _octave = octave.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_octave = _octave.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_octave)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DegreeToKey", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _octave(i.%(_sz_octave)))))
   }
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
   def kr(index: AnyGE, in: Multi[AnyGE]) = apply[control](control, index, in)
   /**
    * @param index           an index signal into the channels of the `multi` argument. The index
    *                        is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
    *                        is truncated to its integer part (not rounded), hence using for instance an
    *                        index of `0.9` will still be interpreted as index `0`.
    * @param in              a graph element which is composed of the channels to be indexed.
    */
   def ar(index: AnyGE, in: Multi[AnyGE]) = apply[audio](audio, index, in)
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
final case class Select[R <: Rate](rate: R, index: AnyGE, in: Multi[AnyGE]) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _index = index.expand
      val _in = in.mexpand
      val _sz_index = _index.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_index, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Select", rate, IIdxSeq(_index(i.%(_sz_index))).++(_in(i.%(_sz_in)).expand)))
   }
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
final case class TWindex[R <: Rate](rate: R, trig: AnyGE, prob: AnyGE, normalize: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _trig = trig.expand
      val _prob = prob.expand
      val _normalize = normalize.expand
      val _sz_trig = _trig.size
      val _sz_prob = _prob.size
      val _sz_normalize = _normalize.size
      val _exp_ = maxInt(_sz_trig, _sz_prob, _sz_normalize)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TWindex", rate, IIdxSeq(_trig(i.%(_sz_trig)), _prob(i.%(_sz_prob)), _normalize(i.%(_sz_normalize)))))
   }
}
object Index {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class Index[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Index", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)))))
   }
}
object WrapIndex {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class WrapIndex[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("WrapIndex", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)))))
   }
}
object IndexInBetween {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class IndexInBetween[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("IndexInBetween", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)))))
   }
}
object DetectIndex {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class DetectIndex[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DetectIndex", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)))))
   }
}
object Shaper {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
final case class Shaper[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Shaper", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)))))
   }
}
object FSinOsc {
   def ar: FSinOsc[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
   def kr: FSinOsc[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
}
final case class FSinOsc[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _iphase = iphase.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FSinOsc", rate, IIdxSeq(_freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase)))))
   }
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
final case class SinOsc[R <: Rate](rate: R, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _phase = phase.expand
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("SinOsc", rate, IIdxSeq(_freq(i.%(_sz_freq)), _phase(i.%(_sz_phase)))))
   }
}
object SinOscFB {
   def ar: SinOscFB[audio] = ar()
   def ar(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply[audio](audio, freq, feedback)
   def kr: SinOscFB[control] = kr()
   def kr(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply[control](control, freq, feedback)
}
final case class SinOscFB[R <: Rate](rate: R, freq: AnyGE, feedback: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _feedback = feedback.expand
      val _sz_freq = _freq.size
      val _sz_feedback = _feedback.size
      val _exp_ = maxInt(_sz_freq, _sz_feedback)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("SinOscFB", rate, IIdxSeq(_freq(i.%(_sz_freq)), _feedback(i.%(_sz_feedback)))))
   }
}
object VOsc {
   def ar(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, bufPos, freq, phase)
   def kr(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, bufPos, freq, phase)
}
final case class VOsc[R <: Rate](rate: R, bufPos: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _bufPos = bufPos.expand
      val _freq = freq.expand
      val _phase = phase.expand
      val _sz_bufPos = _bufPos.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_bufPos, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("VOsc", rate, IIdxSeq(_bufPos(i.%(_sz_bufPos)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase)))))
   }
}
object VOsc3 {
   def ar(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply[audio](audio, bufPos, freq1, freq2, freq3)
   def kr(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply[control](control, bufPos, freq1, freq2, freq3)
}
final case class VOsc3[R <: Rate](rate: R, bufPos: AnyGE, freq1: AnyGE, freq2: AnyGE, freq3: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _bufPos = bufPos.expand
      val _freq1 = freq1.expand
      val _freq2 = freq2.expand
      val _freq3 = freq3.expand
      val _sz_bufPos = _bufPos.size
      val _sz_freq1 = _freq1.size
      val _sz_freq2 = _freq2.size
      val _sz_freq3 = _freq3.size
      val _exp_ = maxInt(_sz_bufPos, _sz_freq1, _sz_freq2, _sz_freq3)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("VOsc3", rate, IIdxSeq(_bufPos(i.%(_sz_bufPos)), _freq1(i.%(_sz_freq1)), _freq2(i.%(_sz_freq2)), _freq3(i.%(_sz_freq3)))))
   }
}
object Osc {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, buf, freq, phase)
   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, buf, freq, phase)
}
final case class Osc[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _freq = freq.expand
      val _phase = phase.expand
      val _sz_buf = _buf.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Osc", rate, IIdxSeq(_buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase)))))
   }
}
object OscN {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, buf, freq, phase)
   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, buf, freq, phase)
}
final case class OscN[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _freq = freq.expand
      val _phase = phase.expand
      val _sz_buf = _buf.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("OscN", rate, IIdxSeq(_buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase)))))
   }
}
object COsc {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, beats: AnyGE = 0.5f) = apply[audio](audio, buf, freq, beats)
}
final case class COsc[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, beats: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _freq = freq.expand
      val _beats = beats.expand
      val _sz_buf = _buf.size
      val _sz_freq = _freq.size
      val _sz_beats = _beats.size
      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_beats)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("COsc", rate, IIdxSeq(_buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _beats(i.%(_sz_beats)))))
   }
}
object Formant {
   def ar: Formant[audio] = ar()
   def ar(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply[audio](audio, fundFreq, formFreq, bw)
   def kr: Formant[control] = kr()
   def kr(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply[control](control, fundFreq, formFreq, bw)
}
final case class Formant[R <: Rate](rate: R, fundFreq: AnyGE, formFreq: AnyGE, bw: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _fundFreq = fundFreq.expand
      val _formFreq = formFreq.expand
      val _bw = bw.expand
      val _sz_fundFreq = _fundFreq.size
      val _sz_formFreq = _formFreq.size
      val _sz_bw = _bw.size
      val _exp_ = maxInt(_sz_fundFreq, _sz_formFreq, _sz_bw)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Formant", rate, IIdxSeq(_fundFreq(i.%(_sz_fundFreq)), _formFreq(i.%(_sz_formFreq)), _bw(i.%(_sz_bw)))))
   }
}
object Blip {
   def kr: Blip[control] = kr()
   def kr(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply[control](control, freq, numHarm)
   def ar: Blip[audio] = ar()
   def ar(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply[audio](audio, freq, numHarm)
}
final case class Blip[R <: Rate](rate: R, freq: AnyGE, numHarm: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _numHarm = numHarm.expand
      val _sz_freq = _freq.size
      val _sz_numHarm = _numHarm.size
      val _exp_ = maxInt(_sz_freq, _sz_numHarm)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Blip", rate, IIdxSeq(_freq(i.%(_sz_freq)), _numHarm(i.%(_sz_numHarm)))))
   }
}
object Saw {
   def kr: Saw[control] = kr()
   def kr(freq: AnyGE = 440.0f) = apply[control](control, freq)
   def ar: Saw[audio] = ar()
   def ar(freq: AnyGE = 440.0f) = apply[audio](audio, freq)
}
final case class Saw[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => new SingleOutUGen("Saw", rate, IIdxSeq(_freq(i))))
   }
}
object Pulse {
   def kr: Pulse[control] = kr()
   def kr(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply[control](control, freq, width)
   def ar: Pulse[audio] = ar()
   def ar(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply[audio](audio, freq, width)
}
final case class Pulse[R <: Rate](rate: R, freq: AnyGE, width: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _width = width.expand
      val _sz_freq = _freq.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_freq, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Pulse", rate, IIdxSeq(_freq(i.%(_sz_freq)), _width(i.%(_sz_width)))))
   }
}
object Klang {
   def ar(specs: Multi[AnyGE], freqScale: AnyGE = 1.0f, freqOffset: AnyGE = 0.0f) = apply(specs, freqScale, freqOffset)
}
final case class Klang(specs: Multi[AnyGE], freqScale: AnyGE, freqOffset: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _freqScale = freqScale.expand
      val _freqOffset = freqOffset.expand
      val _specs = specs.mexpand
      val _sz_freqScale = _freqScale.size
      val _sz_freqOffset = _freqOffset.size
      val _sz_specs = _specs.size
      val _exp_ = maxInt(_sz_freqScale, _sz_freqOffset, _sz_specs)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Klang", audio, IIdxSeq(_freqScale(i.%(_sz_freqScale)), _freqOffset(i.%(_sz_freqOffset))).++(_specs(i.%(_sz_specs)).expand)))
   }
}
object Klank {
   def ar(specs: Multi[AnyGE], in: AnyGE, freqScale: AnyGE = 1.0f, freqOffset: AnyGE = 0.0f, decayScale: AnyGE = 1.0f) = apply(specs, in, freqScale, freqOffset, decayScale)
}
final case class Klank(specs: Multi[AnyGE], in: AnyGE, freqScale: AnyGE, freqOffset: AnyGE, decayScale: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freqScale = freqScale.expand
      val _freqOffset = freqOffset.expand
      val _decayScale = decayScale.expand
      val _specs = specs.mexpand
      val _sz_in = _in.size
      val _sz_freqScale = _freqScale.size
      val _sz_freqOffset = _freqOffset.size
      val _sz_decayScale = _decayScale.size
      val _sz_specs = _specs.size
      val _exp_ = maxInt(_sz_in, _sz_freqScale, _sz_freqOffset, _sz_decayScale, _sz_specs)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Klank", audio, IIdxSeq(_in(i.%(_sz_in)), _freqScale(i.%(_sz_freqScale)), _freqOffset(i.%(_sz_freqOffset)), _decayScale(i.%(_sz_decayScale))).++(_specs(i.%(_sz_specs)).expand)))
   }
}