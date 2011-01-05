/*
 * OSCUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Jan 05 18:34:02 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object DegreeToKey {
   def kr(buf: AnyGE, in: AnyGE, octave: AnyGE = 12.0f) = apply[control](control, buf, in, octave)
   def ar(buf: AnyGE, in: AnyGE, octave: AnyGE = 12.0f) = apply[audio](audio, buf, in, octave)
}
case class DegreeToKey[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, octave: AnyGE) extends GE[R, DegreeToKeyUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _octave: IIdxSeq[AnyUGenIn] = octave.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_octave = _octave.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_octave)
      IIdxSeq.tabulate(_exp_)(i => DegreeToKeyUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _octave(i.%(_sz_octave))))
   }
}
case class DegreeToKeyUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, octave: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, octave))
object Select {
   def kr(index: AnyGE, in: Expands[AnyGE]) = apply[control](control, index, in)
   def ar(index: AnyGE, in: Expands[AnyGE]) = apply[audio](audio, index, in)
}
case class Select[R <: Rate](rate: R, index: AnyGE, in: Expands[AnyGE]) extends GE[R, SelectUGen[R]] {
   def expand = {
      val _index: IIdxSeq[AnyUGenIn] = index.expand
      val _in: IIdxSeq[AnyGE] = in.expand
      val _sz_index = _index.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_index, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => SelectUGen(rate, _index(i.%(_sz_index)), _in(i.%(_sz_in))))
   }
}
case class SelectUGen[R <: Rate](rate: R, index: AnyUGenIn, in: AnyGE) extends SingleOutUGen[R](IIdxSeq[AnyUGenIn](index).++(in.expand))
object TWindex {
   def kr(trig: AnyGE, prob: AnyGE, normalize: AnyGE = 0.0f) = apply[control](control, trig, prob, normalize)
   def ar(trig: AnyGE, prob: AnyGE, normalize: AnyGE = 0.0f) = apply[audio](audio, trig, prob, normalize)
}
case class TWindex[R <: Rate](rate: R, trig: AnyGE, prob: AnyGE, normalize: AnyGE) extends GE[R, TWindexUGen[R]] {
   def expand = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _prob: IIdxSeq[AnyUGenIn] = prob.expand
      val _normalize: IIdxSeq[AnyUGenIn] = normalize.expand
      val _sz_trig = _trig.size
      val _sz_prob = _prob.size
      val _sz_normalize = _normalize.size
      val _exp_ = maxInt(_sz_trig, _sz_prob, _sz_normalize)
      IIdxSeq.tabulate(_exp_)(i => TWindexUGen(rate, _trig(i.%(_sz_trig)), _prob(i.%(_sz_prob)), _normalize(i.%(_sz_normalize))))
   }
}
case class TWindexUGen[R <: Rate](rate: R, trig: AnyUGenIn, prob: AnyUGenIn, normalize: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, prob, normalize))
object Index {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
case class Index[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends GE[R, IndexUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => IndexUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
   }
}
case class IndexUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in))
object WrapIndex {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
case class WrapIndex[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends GE[R, WrapIndexUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => WrapIndexUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
   }
}
case class WrapIndexUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in))
object IndexInBetween {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
case class IndexInBetween[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends GE[R, IndexInBetweenUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => IndexInBetweenUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
   }
}
case class IndexInBetweenUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in))
object DetectIndex {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
case class DetectIndex[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends GE[R, DetectIndexUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DetectIndexUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
   }
}
case class DetectIndexUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in))
object Shaper {
   def kr(buf: AnyGE, in: AnyGE = 0.0f) = apply[control](control, buf, in)
   def ar(buf: AnyGE, in: AnyGE = 0.0f) = apply[audio](audio, buf, in)
}
case class Shaper[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends GE[R, ShaperUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => ShaperUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
   }
}
case class ShaperUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in))
object FSinOsc {
   def ar: FSinOsc[audio] = ar( )
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
   def kr: FSinOsc[control] = kr( )
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
}
case class FSinOsc[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends GE[R, FSinOscUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => FSinOscUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
   }
}
case class FSinOscUGen[R <: Rate](rate: R, freq: AnyUGenIn, iphase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, iphase))
object SinOsc {
   def ar: SinOsc[audio] = ar( )
   def ar(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, freq, phase)
   def kr: SinOsc[control] = kr( )
   def kr(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, freq, phase)
}
case class SinOsc[R <: Rate](rate: R, freq: AnyGE, phase: AnyGE) extends GE[R, SinOscUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => SinOscUGen(rate, _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class SinOscUGen[R <: Rate](rate: R, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, phase))
object SinOscFB {
   def ar: SinOscFB[audio] = ar( )
   def ar(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply[audio](audio, freq, feedback)
   def kr: SinOscFB[control] = kr( )
   def kr(freq: AnyGE = 440.0f, feedback: AnyGE = 0.0f) = apply[control](control, freq, feedback)
}
case class SinOscFB[R <: Rate](rate: R, freq: AnyGE, feedback: AnyGE) extends GE[R, SinOscFBUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _feedback: IIdxSeq[AnyUGenIn] = feedback.expand
      val _sz_freq = _freq.size
      val _sz_feedback = _feedback.size
      val _exp_ = maxInt(_sz_freq, _sz_feedback)
      IIdxSeq.tabulate(_exp_)(i => SinOscFBUGen(rate, _freq(i.%(_sz_freq)), _feedback(i.%(_sz_feedback))))
   }
}
case class SinOscFBUGen[R <: Rate](rate: R, freq: AnyUGenIn, feedback: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, feedback))
object VOsc {
   def ar(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, bufPos, freq, phase)
   def kr(bufPos: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, bufPos, freq, phase)
}
case class VOsc[R <: Rate](rate: R, bufPos: AnyGE, freq: AnyGE, phase: AnyGE) extends GE[R, VOscUGen[R]] {
   def expand = {
      val _bufPos: IIdxSeq[AnyUGenIn] = bufPos.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _sz_bufPos = _bufPos.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_bufPos, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => VOscUGen(rate, _bufPos(i.%(_sz_bufPos)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class VOscUGen[R <: Rate](rate: R, bufPos: AnyUGenIn, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(bufPos, freq, phase))
object VOsc3 {
   def ar(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply[audio](audio, bufPos, freq1, freq2, freq3)
   def kr(bufPos: AnyGE, freq1: AnyGE = 110.0f, freq2: AnyGE = 220.0f, freq3: AnyGE = 440.0f) = apply[control](control, bufPos, freq1, freq2, freq3)
}
case class VOsc3[R <: Rate](rate: R, bufPos: AnyGE, freq1: AnyGE, freq2: AnyGE, freq3: AnyGE) extends GE[R, VOsc3UGen[R]] {
   def expand = {
      val _bufPos: IIdxSeq[AnyUGenIn] = bufPos.expand
      val _freq1: IIdxSeq[AnyUGenIn] = freq1.expand
      val _freq2: IIdxSeq[AnyUGenIn] = freq2.expand
      val _freq3: IIdxSeq[AnyUGenIn] = freq3.expand
      val _sz_bufPos = _bufPos.size
      val _sz_freq1 = _freq1.size
      val _sz_freq2 = _freq2.size
      val _sz_freq3 = _freq3.size
      val _exp_ = maxInt(_sz_bufPos, _sz_freq1, _sz_freq2, _sz_freq3)
      IIdxSeq.tabulate(_exp_)(i => VOsc3UGen(rate, _bufPos(i.%(_sz_bufPos)), _freq1(i.%(_sz_freq1)), _freq2(i.%(_sz_freq2)), _freq3(i.%(_sz_freq3))))
   }
}
case class VOsc3UGen[R <: Rate](rate: R, bufPos: AnyUGenIn, freq1: AnyUGenIn, freq2: AnyUGenIn, freq3: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(bufPos, freq1, freq2, freq3))
object Osc {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, buf, freq, phase)
   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, buf, freq, phase)
}
case class Osc[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends GE[R, OscUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _sz_buf = _buf.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => OscUGen(rate, _buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class OscUGen[R <: Rate](rate: R, buf: AnyUGenIn, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, freq, phase))
object OscN {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, buf, freq, phase)
   def kr(buf: AnyGE, freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, buf, freq, phase)
}
case class OscN[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, phase: AnyGE) extends GE[R, OscNUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _sz_buf = _buf.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => OscNUGen(rate, _buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class OscNUGen[R <: Rate](rate: R, buf: AnyUGenIn, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, freq, phase))
object COsc {
   def ar(buf: AnyGE, freq: AnyGE = 440.0f, beats: AnyGE = 0.5f) = apply[audio](audio, buf, freq, beats)
}
case class COsc[R <: Rate](rate: R, buf: AnyGE, freq: AnyGE, beats: AnyGE) extends GE[R, COscUGen[R]] {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _beats: IIdxSeq[AnyUGenIn] = beats.expand
      val _sz_buf = _buf.size
      val _sz_freq = _freq.size
      val _sz_beats = _beats.size
      val _exp_ = maxInt(_sz_buf, _sz_freq, _sz_beats)
      IIdxSeq.tabulate(_exp_)(i => COscUGen(rate, _buf(i.%(_sz_buf)), _freq(i.%(_sz_freq)), _beats(i.%(_sz_beats))))
   }
}
case class COscUGen[R <: Rate](rate: R, buf: AnyUGenIn, freq: AnyUGenIn, beats: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, freq, beats))
object Formant {
   def ar: Formant[audio] = ar( )
   def ar(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply[audio](audio, fundFreq, formFreq, bw)
   def kr: Formant[control] = kr( )
   def kr(fundFreq: AnyGE = 440.0f, formFreq: AnyGE = 1760.0f, bw: AnyGE = 880.0f) = apply[control](control, fundFreq, formFreq, bw)
}
case class Formant[R <: Rate](rate: R, fundFreq: AnyGE, formFreq: AnyGE, bw: AnyGE) extends GE[R, FormantUGen[R]] {
   def expand = {
      val _fundFreq: IIdxSeq[AnyUGenIn] = fundFreq.expand
      val _formFreq: IIdxSeq[AnyUGenIn] = formFreq.expand
      val _bw: IIdxSeq[AnyUGenIn] = bw.expand
      val _sz_fundFreq = _fundFreq.size
      val _sz_formFreq = _formFreq.size
      val _sz_bw = _bw.size
      val _exp_ = maxInt(_sz_fundFreq, _sz_formFreq, _sz_bw)
      IIdxSeq.tabulate(_exp_)(i => FormantUGen(rate, _fundFreq(i.%(_sz_fundFreq)), _formFreq(i.%(_sz_formFreq)), _bw(i.%(_sz_bw))))
   }
}
case class FormantUGen[R <: Rate](rate: R, fundFreq: AnyUGenIn, formFreq: AnyUGenIn, bw: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(fundFreq, formFreq, bw))
object Blip {
   def kr: Blip[control] = kr( )
   def kr(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply[control](control, freq, numHarm)
   def ar: Blip[audio] = ar( )
   def ar(freq: AnyGE = 440.0f, numHarm: AnyGE = 200.0f) = apply[audio](audio, freq, numHarm)
}
case class Blip[R <: Rate](rate: R, freq: AnyGE, numHarm: AnyGE) extends GE[R, BlipUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _numHarm: IIdxSeq[AnyUGenIn] = numHarm.expand
      val _sz_freq = _freq.size
      val _sz_numHarm = _numHarm.size
      val _exp_ = maxInt(_sz_freq, _sz_numHarm)
      IIdxSeq.tabulate(_exp_)(i => BlipUGen(rate, _freq(i.%(_sz_freq)), _numHarm(i.%(_sz_numHarm))))
   }
}
case class BlipUGen[R <: Rate](rate: R, freq: AnyUGenIn, numHarm: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, numHarm))
object Saw {
   def kr: Saw[control] = kr( )
   def kr(freq: AnyGE = 440.0f) = apply[control](control, freq)
   def ar: Saw[audio] = ar( )
   def ar(freq: AnyGE = 440.0f) = apply[audio](audio, freq)
}
case class Saw[R <: Rate](rate: R, freq: AnyGE) extends GE[R, SawUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => SawUGen(rate, _freq(i)))
   }
}
case class SawUGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq))
object Pulse {
   def kr: Pulse[control] = kr( )
   def kr(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply[control](control, freq, width)
   def ar: Pulse[audio] = ar( )
   def ar(freq: AnyGE = 440.0f, width: AnyGE = 0.5f) = apply[audio](audio, freq, width)
}
case class Pulse[R <: Rate](rate: R, freq: AnyGE, width: AnyGE) extends GE[R, PulseUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _sz_freq = _freq.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_freq, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => PulseUGen(rate, _freq(i.%(_sz_freq)), _width(i.%(_sz_width))))
   }
}
case class PulseUGen[R <: Rate](rate: R, freq: AnyUGenIn, width: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, width))