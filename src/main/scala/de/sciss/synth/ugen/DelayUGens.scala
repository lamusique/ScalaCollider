/*
 * DelayUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jan 07 00:11:13 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object ControlRate {
   def ir() = apply()
}
case class ControlRate() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object SampleRate {
   def ir() = apply()
}
case class SampleRate() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object SampleDur {
   def ir() = apply()
}
case class SampleDur() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object ControlDur {
   def ir() = apply()
}
case class ControlDur() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object SubsampleOffset {
   def ir() = apply()
}
case class SubsampleOffset() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object RadiansPerSample {
   def ir() = apply()
}
case class RadiansPerSample() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object NumInputBuses {
   def ir() = apply()
}
case class NumInputBuses() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object NumOutputBuses {
   def ir() = apply()
}
case class NumOutputBuses() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object NumAudioBuses {
   def ir() = apply()
}
case class NumAudioBuses() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object NumControlBuses {
   def ir() = apply()
}
case class NumControlBuses() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object NumBuffers {
   def ir() = apply()
}
case class NumBuffers() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object NumRunningSynths {
   def ir() = apply()
}
case class NumRunningSynths() extends SingleOutUGen[scalar](IIdxSeq.empty) with ScalarRated
object BufSampleRate {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
case class BufSampleRate[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R, BufSampleRateUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => BufSampleRateUGen(rate, _buf(i)))
   }
}
case class BufSampleRateUGen[R <: Rate](rate: R, buf: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf))
object BufRateScale {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
case class BufRateScale[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R, BufRateScaleUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => BufRateScaleUGen(rate, _buf(i)))
   }
}
case class BufRateScaleUGen[R <: Rate](rate: R, buf: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf))
object BufSamples {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
case class BufSamples[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R, BufSamplesUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => BufSamplesUGen(rate, _buf(i)))
   }
}
case class BufSamplesUGen[R <: Rate](rate: R, buf: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf))
object BufFrames {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
case class BufFrames[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R, BufFramesUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => BufFramesUGen(rate, _buf(i)))
   }
}
case class BufFramesUGen[R <: Rate](rate: R, buf: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf))
object BufChannels {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
case class BufChannels[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R, BufChannelsUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => BufChannelsUGen(rate, _buf(i)))
   }
}
case class BufChannelsUGen[R <: Rate](rate: R, buf: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf))
object BufDur {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
case class BufDur[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R, BufDurUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => BufDurUGen(rate, _buf(i)))
   }
}
case class BufDurUGen[R <: Rate](rate: R, buf: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf))
object PlayBuf {
   def kr(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, trig: AnyGE = 1.0f, startPos: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, numChannels, buf, speed, trig, startPos, loop, doneAction)
   def ar(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, trig: AnyGE = 1.0f, startPos: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, numChannels, buf, speed, trig, startPos, loop, doneAction)
}
case class PlayBuf[R <: Rate](rate: R, numChannels: Int, buf: AnyGE, speed: AnyGE, trig: AnyGE, startPos: AnyGE, loop: AnyGE, doneAction: AnyGE) extends UGenSource[PlayBufUGen[R]] with HasSideEffect with HasDoneFlag {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _speed: IIdxSeq[AnyUGenIn] = speed.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _startPos: IIdxSeq[AnyUGenIn] = startPos.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_trig = _trig.size
      val _sz_startPos = _startPos.size
      val _sz_loop = _loop.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_buf, _sz_speed, _sz_trig, _sz_startPos, _sz_loop, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => PlayBufUGen(rate, numChannels, _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _trig(i.%(_sz_trig)), _startPos(i.%(_sz_startPos)), _loop(i.%(_sz_loop)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class PlayBufUGen[R <: Rate](rate: R, numChannels: Int, buf: AnyUGenIn, speed: AnyUGenIn, trig: AnyUGenIn, startPos: AnyUGenIn, loop: AnyUGenIn, doneAction: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(buf, speed, trig, startPos, loop, doneAction)) with HasSideEffect with HasDoneFlag
object RecordBuf {
   def kr(in: Expands[AnyGE], buf: AnyGE, offset: AnyGE = 0.0f, recLevel: AnyGE = 1.0f, preLevel: AnyGE = 0.0f, run: AnyGE = 1.0f, loop: AnyGE = 1.0f, trig: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
   def ar(in: Expands[AnyGE], buf: AnyGE, offset: AnyGE = 0.0f, recLevel: AnyGE = 1.0f, preLevel: AnyGE = 0.0f, run: AnyGE = 1.0f, loop: AnyGE = 1.0f, trig: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
}
case class RecordBuf[R <: Rate](rate: R, in: Expands[AnyGE], buf: AnyGE, offset: AnyGE, recLevel: AnyGE, preLevel: AnyGE, run: AnyGE, loop: AnyGE, trig: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, RecordBufUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _offset: IIdxSeq[AnyUGenIn] = offset.expand
      val _recLevel: IIdxSeq[AnyUGenIn] = recLevel.expand
      val _preLevel: IIdxSeq[AnyUGenIn] = preLevel.expand
      val _run: IIdxSeq[AnyUGenIn] = run.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _in: IIdxSeq[AnyGE] = in.expand
      val _sz_buf = _buf.size
      val _sz_offset = _offset.size
      val _sz_recLevel = _recLevel.size
      val _sz_preLevel = _preLevel.size
      val _sz_run = _run.size
      val _sz_loop = _loop.size
      val _sz_trig = _trig.size
      val _sz_doneAction = _doneAction.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_offset, _sz_recLevel, _sz_preLevel, _sz_run, _sz_loop, _sz_trig, _sz_doneAction, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => RecordBufUGen(rate, _in(i.%(_sz_in)).expand, _buf(i.%(_sz_buf)), _offset(i.%(_sz_offset)), _recLevel(i.%(_sz_recLevel)), _preLevel(i.%(_sz_preLevel)), _run(i.%(_sz_run)), _loop(i.%(_sz_loop)), _trig(i.%(_sz_trig)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class RecordBufUGen[R <: Rate](rate: R, in: IIdxSeq[AnyUGenIn], buf: AnyUGenIn, offset: AnyUGenIn, recLevel: AnyUGenIn, preLevel: AnyUGenIn, run: AnyUGenIn, loop: AnyUGenIn, trig: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq[AnyUGenIn](buf, offset, recLevel, preLevel, run, loop, trig, doneAction).++(in)) with WritesBuffer
object BufRd {
   def kr(numChannels: Int, buf: AnyGE, phase: AnyGE = 0.0f, loop: AnyGE = 1.0f, interp: AnyGE = 2.0f) = apply[control](control, numChannels, buf, phase, loop, interp)
   def ar(numChannels: Int, buf: AnyGE, phase: AnyGE = 0.0f, loop: AnyGE = 1.0f, interp: AnyGE = 2.0f) = apply[audio](audio, numChannels, buf, phase, loop, interp)
}
case class BufRd[R <: Rate](rate: R, numChannels: Int, buf: AnyGE, phase: AnyGE, loop: AnyGE, interp: AnyGE) extends UGenSource[BufRdUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _interp: IIdxSeq[AnyUGenIn] = interp.expand
      val _sz_buf = _buf.size
      val _sz_phase = _phase.size
      val _sz_loop = _loop.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_buf, _sz_phase, _sz_loop, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => BufRdUGen(rate, numChannels, _buf(i.%(_sz_buf)), _phase(i.%(_sz_phase)), _loop(i.%(_sz_loop)), _interp(i.%(_sz_interp))))
   }
}
case class BufRdUGen[R <: Rate](rate: R, numChannels: Int, buf: AnyUGenIn, phase: AnyUGenIn, loop: AnyUGenIn, interp: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(buf, phase, loop, interp))
object BufWr {
   def kr(in: Expands[AnyGE], buf: AnyGE, phase: AnyGE = 0.0f, loop: AnyGE = 1.0f) = apply[control](control, in, buf, phase, loop)
   def ar(in: Expands[AnyGE], buf: AnyGE, phase: AnyGE = 0.0f, loop: AnyGE = 1.0f) = apply[audio](audio, in, buf, phase, loop)
}
case class BufWr[R <: Rate](rate: R, in: Expands[AnyGE], buf: AnyGE, phase: AnyGE, loop: AnyGE) extends SingleOutUGenSource[R, BufWrUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _in: IIdxSeq[AnyGE] = in.expand
      val _sz_buf = _buf.size
      val _sz_phase = _phase.size
      val _sz_loop = _loop.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_phase, _sz_loop, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => BufWrUGen(rate, _in(i.%(_sz_in)).expand, _buf(i.%(_sz_buf)), _phase(i.%(_sz_phase)), _loop(i.%(_sz_loop))))
   }
}
case class BufWrUGen[R <: Rate](rate: R, in: IIdxSeq[AnyUGenIn], buf: AnyUGenIn, phase: AnyUGenIn, loop: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq[AnyUGenIn](buf, phase, loop).++(in)) with WritesBuffer
object Pitch {
   def kr(in: AnyGE, initFreq: AnyGE = 440.0f, minFreq: AnyGE = 60.0f, maxFreq: AnyGE = 4000.0f, execFreq: AnyGE = 100.0f, binsPerOct: AnyGE = 16.0f, median: AnyGE = 1.0f, ampThresh: AnyGE = 0.01f, peakThresh: AnyGE = 0.5f, downSample: AnyGE = 1.0f) = apply[control](control, in, initFreq, minFreq, maxFreq, execFreq, binsPerOct, median, ampThresh, peakThresh, downSample)
}
case class Pitch[R <: Rate](rate: R, in: AnyGE, initFreq: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, execFreq: AnyGE, binsPerOct: AnyGE, median: AnyGE, ampThresh: AnyGE, peakThresh: AnyGE, downSample: AnyGE) extends UGenSource[PitchUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _initFreq: IIdxSeq[AnyUGenIn] = initFreq.expand
      val _minFreq: IIdxSeq[AnyUGenIn] = minFreq.expand
      val _maxFreq: IIdxSeq[AnyUGenIn] = maxFreq.expand
      val _execFreq: IIdxSeq[AnyUGenIn] = execFreq.expand
      val _binsPerOct: IIdxSeq[AnyUGenIn] = binsPerOct.expand
      val _median: IIdxSeq[AnyUGenIn] = median.expand
      val _ampThresh: IIdxSeq[AnyUGenIn] = ampThresh.expand
      val _peakThresh: IIdxSeq[AnyUGenIn] = peakThresh.expand
      val _downSample: IIdxSeq[AnyUGenIn] = downSample.expand
      val _sz_in = _in.size
      val _sz_initFreq = _initFreq.size
      val _sz_minFreq = _minFreq.size
      val _sz_maxFreq = _maxFreq.size
      val _sz_execFreq = _execFreq.size
      val _sz_binsPerOct = _binsPerOct.size
      val _sz_median = _median.size
      val _sz_ampThresh = _ampThresh.size
      val _sz_peakThresh = _peakThresh.size
      val _sz_downSample = _downSample.size
      val _exp_ = maxInt(_sz_in, _sz_initFreq, _sz_minFreq, _sz_maxFreq, _sz_execFreq, _sz_binsPerOct, _sz_median, _sz_ampThresh, _sz_peakThresh, _sz_downSample)
      IIdxSeq.tabulate(_exp_)(i => PitchUGen(rate, _in(i.%(_sz_in)), _initFreq(i.%(_sz_initFreq)), _minFreq(i.%(_sz_minFreq)), _maxFreq(i.%(_sz_maxFreq)), _execFreq(i.%(_sz_execFreq)), _binsPerOct(i.%(_sz_binsPerOct)), _median(i.%(_sz_median)), _ampThresh(i.%(_sz_ampThresh)), _peakThresh(i.%(_sz_peakThresh)), _downSample(i.%(_sz_downSample))))
   }
}
case class PitchUGen[R <: Rate](rate: R, in: AnyUGenIn, initFreq: AnyUGenIn, minFreq: AnyUGenIn, maxFreq: AnyUGenIn, execFreq: AnyUGenIn, binsPerOct: AnyUGenIn, median: AnyUGenIn, ampThresh: AnyUGenIn, peakThresh: AnyUGenIn, downSample: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, initFreq, minFreq, maxFreq, execFreq, binsPerOct, median, ampThresh, peakThresh, downSample))
object BufDelayN {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply[audio](audio, buf, in, delayTime)
}
case class BufDelayN[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R, BufDelayNUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => BufDelayNUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime))))
   }
}
case class BufDelayNUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime)) with WritesBuffer
object BufDelayL {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply[audio](audio, buf, in, delayTime)
}
case class BufDelayL[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R, BufDelayLUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => BufDelayLUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime))))
   }
}
case class BufDelayLUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime)) with WritesBuffer
object BufDelayC {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply[audio](audio, buf, in, delayTime)
}
case class BufDelayC[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R, BufDelayCUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => BufDelayCUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime))))
   }
}
case class BufDelayCUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime)) with WritesBuffer
object BufCombN {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
case class BufCombN[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, BufCombNUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => BufCombNUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class BufCombNUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
object BufCombL {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
case class BufCombL[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, BufCombLUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => BufCombLUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class BufCombLUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
object BufCombC {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
case class BufCombC[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, BufCombCUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => BufCombCUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class BufCombCUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
object BufAllpassN {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
case class BufAllpassN[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, BufAllpassNUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => BufAllpassNUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class BufAllpassNUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
object BufAllpassL {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
case class BufAllpassL[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, BufAllpassLUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => BufAllpassLUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class BufAllpassLUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
object BufAllpassC {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
case class BufAllpassC[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, BufAllpassCUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => BufAllpassCUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class BufAllpassCUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
object DelayN {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[control](control, in, maxDelayTime, delayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[audio](audio, in, maxDelayTime, delayTime)
}
case class DelayN[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R, DelayNUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => DelayNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime))))
   }
}
case class DelayNUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime))
object DelayL {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[control](control, in, maxDelayTime, delayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[audio](audio, in, maxDelayTime, delayTime)
}
case class DelayL[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R, DelayLUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => DelayLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime))))
   }
}
case class DelayLUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime))
object DelayC {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[control](control, in, maxDelayTime, delayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[audio](audio, in, maxDelayTime, delayTime)
}
case class DelayC[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R, DelayCUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => DelayCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime))))
   }
}
case class DelayCUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime))
object CombN {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
case class CombN[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, CombNUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => CombNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class CombNUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object CombL {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
case class CombL[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, CombLUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => CombLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class CombLUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object CombC {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
case class CombC[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, CombCUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => CombCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class CombCUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object AllpassN {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
}
case class AllpassN[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, AllpassNUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => AllpassNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class AllpassNUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object AllpassL {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
}
case class AllpassL[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, AllpassLUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => AllpassLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class AllpassLUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object AllpassC {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
}
case class AllpassC[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R, AllpassCUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => AllpassCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class AllpassCUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object PitchShift {
   def ar(in: AnyGE, winSize: AnyGE = 0.2f, pitchRatio: AnyGE = 1.0f, pitchDispersion: AnyGE = 0.0f, timeDispersion: AnyGE = 0.0f) = apply(in, winSize, pitchRatio, pitchDispersion, timeDispersion)
}
case class PitchShift(in: AnyGE, winSize: AnyGE, pitchRatio: AnyGE, pitchDispersion: AnyGE, timeDispersion: AnyGE) extends SingleOutUGenSource[audio, PitchShiftUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _pitchRatio: IIdxSeq[AnyUGenIn] = pitchRatio.expand
      val _pitchDispersion: IIdxSeq[AnyUGenIn] = pitchDispersion.expand
      val _timeDispersion: IIdxSeq[AnyUGenIn] = timeDispersion.expand
      val _sz_in = _in.size
      val _sz_winSize = _winSize.size
      val _sz_pitchRatio = _pitchRatio.size
      val _sz_pitchDispersion = _pitchDispersion.size
      val _sz_timeDispersion = _timeDispersion.size
      val _exp_ = maxInt(_sz_in, _sz_winSize, _sz_pitchRatio, _sz_pitchDispersion, _sz_timeDispersion)
      IIdxSeq.tabulate(_exp_)(i => PitchShiftUGen(_in(i.%(_sz_in)), _winSize(i.%(_sz_winSize)), _pitchRatio(i.%(_sz_pitchRatio)), _pitchDispersion(i.%(_sz_pitchDispersion)), _timeDispersion(i.%(_sz_timeDispersion))))
   }
}
case class PitchShiftUGen(in: AnyUGenIn, winSize: AnyUGenIn, pitchRatio: AnyUGenIn, pitchDispersion: AnyUGenIn, timeDispersion: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, winSize, pitchRatio, pitchDispersion, timeDispersion)) with AudioRated
object TGrains {
   def ar(numChannels: Int, trig: AnyGE, buf: AnyGE, speed: AnyGE = 1.0f, centerPos: AnyGE = 0.0f, dur: AnyGE = 0.1f, pan: AnyGE = 0.0f, amp: AnyGE = 0.1f, interp: AnyGE = 4.0f) = apply(numChannels, trig, buf, speed, centerPos, dur, pan, amp, interp)
}
case class TGrains(numChannels: Int, trig: AnyGE, buf: AnyGE, speed: AnyGE, centerPos: AnyGE, dur: AnyGE, pan: AnyGE, amp: AnyGE, interp: AnyGE) extends UGenSource[TGrainsUGen] with AudioRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _speed: IIdxSeq[AnyUGenIn] = speed.expand
      val _centerPos: IIdxSeq[AnyUGenIn] = centerPos.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _pan: IIdxSeq[AnyUGenIn] = pan.expand
      val _amp: IIdxSeq[AnyUGenIn] = amp.expand
      val _interp: IIdxSeq[AnyUGenIn] = interp.expand
      val _sz_trig = _trig.size
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_centerPos = _centerPos.size
      val _sz_dur = _dur.size
      val _sz_pan = _pan.size
      val _sz_amp = _amp.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_trig, _sz_buf, _sz_speed, _sz_centerPos, _sz_dur, _sz_pan, _sz_amp, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => TGrainsUGen(numChannels, _trig(i.%(_sz_trig)), _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _centerPos(i.%(_sz_centerPos)), _dur(i.%(_sz_dur)), _pan(i.%(_sz_pan)), _amp(i.%(_sz_amp)), _interp(i.%(_sz_interp))))
   }
}
case class TGrainsUGen(numChannels: Int, trig: AnyUGenIn, buf: AnyUGenIn, speed: AnyUGenIn, centerPos: AnyUGenIn, dur: AnyUGenIn, pan: AnyUGenIn, amp: AnyUGenIn, interp: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, buf, speed, centerPos, dur, pan, amp, interp)) with AudioRated
object ScopeOut {
   def ar(buf: AnyGE, in: Expands[AnyGE]) = apply[audio](audio, buf, in)
   def kr(buf: AnyGE, in: Expands[AnyGE]) = apply[control](control, buf, in)
}
case class ScopeOut[R <: Rate](rate: R, buf: AnyGE, in: Expands[AnyGE]) extends SingleOutUGenSource[R, ScopeOutUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyGE] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => ScopeOutUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)).expand))
   }
}
case class ScopeOutUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: IIdxSeq[AnyUGenIn]) extends SingleOutUGen[R](IIdxSeq[AnyUGenIn](buf).++(in)) with WritesBuffer
object Pluck {
   def ar(in: AnyGE, trig: AnyGE = 1.0f, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f, coef: AnyGE = 0.5f) = apply(in, trig, maxDelayTime, delayTime, decayTime, coef)
}
case class Pluck(in: AnyGE, trig: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE, coef: AnyGE) extends SingleOutUGenSource[audio, PluckUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _coef: IIdxSeq[AnyUGenIn] = coef.expand
      val _sz_in = _in.size
      val _sz_trig = _trig.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _sz_coef = _coef.size
      val _exp_ = maxInt(_sz_in, _sz_trig, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime, _sz_coef)
      IIdxSeq.tabulate(_exp_)(i => PluckUGen(_in(i.%(_sz_in)), _trig(i.%(_sz_trig)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)), _coef(i.%(_sz_coef))))
   }
}
case class PluckUGen(in: AnyUGenIn, trig: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn, coef: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, trig, maxDelayTime, delayTime, decayTime, coef)) with AudioRated
object DelTapWr {
   def ar(buf: AnyGE, in: AnyGE) = apply[audio](audio, buf, in)
   def kr(buf: AnyGE, in: AnyGE) = apply[control](control, buf, in)
}
case class DelTapWr[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[R, DelTapWrUGen[R]] with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DelTapWrUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
   }
}
case class DelTapWrUGen[R <: Rate](rate: R, buf: AnyUGenIn, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, in)) with WritesBuffer
object DelTapRd {
   def ar(buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE = 1.0f) = apply[audio](audio, buf, phase, delayTime, interp)
   def kr(buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE = 1.0f) = apply[control](control, buf, phase, delayTime, interp)
}
case class DelTapRd[R <: Rate](rate: R, buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE) extends SingleOutUGenSource[R, DelTapRdUGen[R]] {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _interp: IIdxSeq[AnyUGenIn] = interp.expand
      val _sz_buf = _buf.size
      val _sz_phase = _phase.size
      val _sz_delayTime = _delayTime.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_buf, _sz_phase, _sz_delayTime, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => DelTapRdUGen(rate, _buf(i.%(_sz_buf)), _phase(i.%(_sz_phase)), _delayTime(i.%(_sz_delayTime)), _interp(i.%(_sz_interp))))
   }
}
case class DelTapRdUGen[R <: Rate](rate: R, buf: AnyUGenIn, phase: AnyUGenIn, delayTime: AnyUGenIn, interp: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(buf, phase, delayTime, interp))