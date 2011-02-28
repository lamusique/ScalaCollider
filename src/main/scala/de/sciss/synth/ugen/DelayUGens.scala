/*
* DelayUGens.scala
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
///**
// * A UGen that reports the server's current control rate. This is equivalent to the reciprocal
// * of `ControlDur`
// *
// * @see [[de.sciss.synth.ugen.ControlDur]]
// * @see [[de.sciss.synth.ugen.SampleRate]]
// */
//object ControlRate {
//   def ir = apply()
//}
///**
// * A UGen that reports the server's current control rate. This is equivalent to the reciprocal
// * of `ControlDur`
// *
// * @see [[de.sciss.synth.ugen.ControlDur]]
// * @see [[de.sciss.synth.ugen.SampleRate]]
// */
//case class ControlRate() extends SingleOutUGenSource[ControlRateUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(ControlRateUGen())
//}
//case class ControlRateUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
///**
// * A UGen that reports the server's current (audio) sample rate. This is equivalent to the reciprocal
// * of `SampleDur`
// *
// * @see [[de.sciss.synth.ugen.SampleDur]]
// * @see [[de.sciss.synth.ugen.ControlRate]]
// * @see [[de.sciss.synth.ugen.RadiansPerSample]]
// */
//object SampleRate {
//   def ir = apply()
//}
///**
// * A UGen that reports the server's current (audio) sample rate. This is equivalent to the reciprocal
// * of `SampleDur`
// *
// * @see [[de.sciss.synth.ugen.SampleDur]]
// * @see [[de.sciss.synth.ugen.ControlRate]]
// * @see [[de.sciss.synth.ugen.RadiansPerSample]]
// */
//case class SampleRate() extends SingleOutUGenSource[SampleRateUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(SampleRateUGen())
//}
//case class SampleRateUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
///**
// * A UGen that reports the server's current (audio) sample period in seconds. This is equivalent to the reciprocal
// * of `SampleRate`
// *
// * @see [[de.sciss.synth.ugen.SampleRate]]
// * @see [[de.sciss.synth.ugen.ControlDur]]
// */
//object SampleDur {
//   def ir = apply()
//}
///**
// * A UGen that reports the server's current (audio) sample period in seconds. This is equivalent to the reciprocal
// * of `SampleRate`
// *
// * @see [[de.sciss.synth.ugen.SampleRate]]
// * @see [[de.sciss.synth.ugen.ControlDur]]
// */
//case class SampleDur() extends SingleOutUGenSource[SampleDurUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(SampleDurUGen())
//}
//case class SampleDurUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
///**
// * A UGen that reports the server's current control period in seconds. This is equivalent to the reciprocal
// * of `ControlRate`
// *
// * @see [[de.sciss.synth.ugen.ControlRate]]
// * @see [[de.sciss.synth.ugen.SampleDur]]
// */
//object ControlDur {
//   def ir = apply()
//}
///**
// * A UGen that reports the server's current control period in seconds. This is equivalent to the reciprocal
// * of `ControlRate`
// *
// * @see [[de.sciss.synth.ugen.ControlRate]]
// * @see [[de.sciss.synth.ugen.SampleDur]]
// */
//case class ControlDur() extends SingleOutUGenSource[ControlDurUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(ControlDurUGen())
//}
//case class ControlDurUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
///**
// * A UGen that reports the fractional sample offset of the current Synth from its requested scheduled start.
// *
// * When a synth is created from a time stamped osc-bundle, it starts
// * calculation at the next possible block (normally 64 samples). Using an
// * `OffsetOut` UGen, one can delay the audio so that it matches sample
// * accurately.
// *
// * For some synthesis methods, one even needs subsample accuracy. `SubsampleOffset`
// * provides the information where, within the current sample, the synth was
// * scheduled. It can be used to offset envelopes or resample the audio
// * output.
// *
// * @see [[de.sciss.synth.ugen.ControlRate]]
// * @see [[de.sciss.synth.ugen.SampleDur]]
// */
//object SubsampleOffset {
//   def ir = apply()
//}
///**
// * A UGen that reports the fractional sample offset of the current Synth from its requested scheduled start.
// *
// * When a synth is created from a time stamped osc-bundle, it starts
// * calculation at the next possible block (normally 64 samples). Using an
// * `OffsetOut` UGen, one can delay the audio so that it matches sample
// * accurately.
// *
// * For some synthesis methods, one even needs subsample accuracy. `SubsampleOffset`
// * provides the information where, within the current sample, the synth was
// * scheduled. It can be used to offset envelopes or resample the audio
// * output.
// *
// * @see [[de.sciss.synth.ugen.ControlRate]]
// * @see [[de.sciss.synth.ugen.SampleDur]]
// */
//case class SubsampleOffset() extends SingleOutUGenSource[SubsampleOffsetUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(SubsampleOffsetUGen())
//}
//case class SubsampleOffsetUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
///**
// * A UGen that delivers the conversion factor from frequency in Hertz to radians (normalized frequency).
// * The relation is `RadiansPerSample * sr = 2pi`, thus multiplying the UGen with a frequency between
// * zero and nyquist (sr/2) yields the normalized frequency between zero and pi.
// *
// * @see [[de.sciss.synth.ugen.SampleRate]]
// */
//object RadiansPerSample {
//   def ir = apply()
//}
///**
// * A UGen that delivers the conversion factor from frequency in Hertz to radians (normalized frequency).
// * The relation is `RadiansPerSample * sr = 2pi`, thus multiplying the UGen with a frequency between
// * zero and nyquist (sr/2) yields the normalized frequency between zero and pi.
// *
// * @see [[de.sciss.synth.ugen.SampleRate]]
// */
//case class RadiansPerSample() extends SingleOutUGenSource[RadiansPerSampleUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(RadiansPerSampleUGen())
//}
//case class RadiansPerSampleUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
//object NumInputBuses {
//   def ir = apply()
//}
//case class NumInputBuses() extends SingleOutUGenSource[NumInputBusesUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(NumInputBusesUGen())
//}
//case class NumInputBusesUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
//object NumOutputBuses {
//   def ir = apply()
//}
//case class NumOutputBuses() extends SingleOutUGenSource[NumOutputBusesUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(NumOutputBusesUGen())
//}
//case class NumOutputBusesUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
//object NumAudioBuses {
//   def ir = apply()
//}
//case class NumAudioBuses() extends SingleOutUGenSource[NumAudioBusesUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(NumAudioBusesUGen())
//}
//case class NumAudioBusesUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
//object NumControlBuses {
//   def ir = apply()
//}
//case class NumControlBuses() extends SingleOutUGenSource[NumControlBusesUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(NumControlBusesUGen())
//}
//case class NumControlBusesUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
//object NumBuffers {
//   def ir = apply()
//}
//case class NumBuffers() extends SingleOutUGenSource[NumBuffersUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(NumBuffersUGen())
//}
//case class NumBuffersUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
//object NumRunningSynths {
//   def ir = apply()
//}
//case class NumRunningSynths() extends SingleOutUGenSource[NumRunningSynthsUGen] with ScalarRated {
//   protected def expandUGens = IIdxSeq(NumRunningSynthsUGen())
//}
//case class NumRunningSynthsUGen() extends SingleOutUGen(IIdxSeq.empty) with ScalarRated
//object BufSampleRate {
//   def ir(buf: AnyGE) = apply(scalar, buf)
//   def kr(buf: AnyGE) = apply(control, buf)
//}
//case class BufSampleRate(rate: Rate, buf: AnyGE) extends SingleOutUGenSource[BufSampleRateUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      IIdxSeq.tabulate(_buf.size)(i => BufSampleRateUGen(rate, _buf(i)))
//   }
//}
//case class BufSampleRateUGen(rate: Rate, buf: UGenIn) extends SingleOutUGen(IIdxSeq(buf))
object BufRateScale {
   def ir(buf: AnyGE) = apply(scalar, buf)
   def kr(buf: AnyGE) = apply(control, buf)
}
case class BufRateScale[ R <: Rate ](rate: R, buf: AnyGE) extends SingleOutUGenSource[R, BufRateScaleUGen] {
   protected def expandUGens = {
      val _buf: IIdxSeq[UGenIn] = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => BufRateScaleUGen(rate, _buf(i)))
   }
}
case class BufRateScaleUGen(rate: Rate, buf: AnyUGenIn) extends SingleOutUGen(IIdxSeq(buf))
//object BufSamples {
//   def ir(buf: AnyGE) = apply(scalar, buf)
//   def kr(buf: AnyGE) = apply(control, buf)
//}
//case class BufSamples(rate: Rate, buf: AnyGE) extends SingleOutUGenSource[BufSamplesUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      IIdxSeq.tabulate(_buf.size)(i => BufSamplesUGen(rate, _buf(i)))
//   }
//}
//case class BufSamplesUGen(rate: Rate, buf: UGenIn) extends SingleOutUGen(IIdxSeq(buf))
//object BufFrames {
//   def ir(buf: AnyGE) = apply(scalar, buf)
//   def kr(buf: AnyGE) = apply(control, buf)
//}
//case class BufFrames(rate: Rate, buf: AnyGE) extends SingleOutUGenSource[BufFramesUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      IIdxSeq.tabulate(_buf.size)(i => BufFramesUGen(rate, _buf(i)))
//   }
//}
//case class BufFramesUGen(rate: Rate, buf: UGenIn) extends SingleOutUGen(IIdxSeq(buf))
//object BufChannels {
//   def ir(buf: AnyGE) = apply(scalar, buf)
//   def kr(buf: AnyGE) = apply(control, buf)
//}
//case class BufChannels(rate: Rate, buf: AnyGE) extends SingleOutUGenSource[BufChannelsUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      IIdxSeq.tabulate(_buf.size)(i => BufChannelsUGen(rate, _buf(i)))
//   }
//}
//case class BufChannelsUGen(rate: Rate, buf: UGenIn) extends SingleOutUGen(IIdxSeq(buf))
//object BufDur {
//   def ir(buf: AnyGE) = apply(scalar, buf)
//   def kr(buf: AnyGE) = apply(control, buf)
//}
//case class BufDur(rate: Rate, buf: AnyGE) extends SingleOutUGenSource[BufDurUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      IIdxSeq.tabulate(_buf.size)(i => BufDurUGen(rate, _buf(i)))
//   }
//}
//case class BufDurUGen(rate: Rate, buf: UGenIn) extends SingleOutUGen(IIdxSeq(buf))
/**
* A UGen to play back samples from a buffer in memory.
*
* `PlayBuf` provides a kind of high-level interface to sample-playback, whereas `BufRd`
* represents a kind of lower-level access. While `BufRd` has a random-access-pointer
* in the form of a phase input, `PlayBuf` advances the phase automatically based on
* a given playback speed. `PlayBuf` uses cubic interpolation.
*
* @see [[de.sciss.synth.ugen.BufRd]]
* @see [[de.sciss.synth.ugen.DiskIn]]
* @see [[de.sciss.synth.ugen.RecordBuf]]
* @see [[de.sciss.synth.DoneAction]]
* @see [[de.sciss.synth.ugen.Done]]
* @see [[de.sciss.synth.ugen.BufRateScale]]
* @see [[de.sciss.synth.ugen.BufFrames]]
*/
object PlayBuf {

/**
* @param numChannels     the number of channels that the buffer will be. Since
*                        this is a constant, a change in number of channels of the underlying bus must
*                        be reflected by creating different SynthDefs. If a buffer identifier is used of a buffer
*                        that has a different numChannels then specified in the PlayBuf, it will fail silently.
* @param buf             the identifier of the buffer to use
* @param speed           1.0 advances the play head by the server's sample rate each second,
*                        so 2.0 means doubling speed (and pitch), and 0.5 means half speed (and half pitch).
*                        Negative numbers can be used for backwards playback. If the underlying buffer
*                        represents a sound at a different sample rate, the rate should be
*                        multiplied by `BufRateScale.kr( bufID )` to obtain the correct speed.
* @param trig            a trigger which causes a jump to the given startPos. A trigger occurs when a
*                        signal changes from non-positive to positive (e.g. <= 0 to > 0).
* @param startPos        sample frame to start playback. This is read when a trigger occurs.
* @param loop            1 to loop after the play head reaches the buffer end, 0 to not loop. this can be modulated.
* @param doneAction      what to do when the play head reaches the buffer end.
*/
def kr(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, trig: AnyGE = 1.0f, startPos: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(control, numChannels, buf, speed, trig, startPos, loop, doneAction)
/**
* @param numChannels     the number of channels that the buffer will be. Since
*                        this is a constant, a change in number of channels of the underlying bus must
*                        be reflected by creating different SynthDefs. If a buffer identifier is used of a buffer
*                        that has a different numChannels then specified in the PlayBuf, it will fail silently.
* @param buf             the identifier of the buffer to use
* @param speed           1.0 advances the play head by the server's sample rate each second,
*                        so 2.0 means doubling speed (and pitch), and 0.5 means half speed (and half pitch).
*                        Negative numbers can be used for backwards playback. If the underlying buffer
*                        represents a sound at a different sample rate, the rate should be
*                        multiplied by `BufRateScale.kr( bufID )` to obtain the correct speed.
* @param trig            a trigger which causes a jump to the given startPos. A trigger occurs when a
*                        signal changes from non-positive to positive (e.g. <= 0 to > 0).
* @param startPos        sample frame to start playback. This is read when a trigger occurs.
* @param loop            1 to loop after the play head reaches the buffer end, 0 to not loop. this can be modulated.
* @param doneAction      what to do when the play head reaches the buffer end.
*/
def ar(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, trig: AnyGE = 1.0f, startPos: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(audio, numChannels, buf, speed, trig, startPos, loop, doneAction)
}
/**
* A UGen to play back samples from a buffer in memory.
*
* `PlayBuf` provides a kind of high-level interface to sample-playback, whereas `BufRd`
* represents a kind of lower-level access. While `BufRd` has a random-access-pointer
* in the form of a phase input, `PlayBuf` advances the phase automatically based on
* a given playback speed. `PlayBuf` uses cubic interpolation.
*
* @param numChannels     the number of channels that the buffer will be. Since
*                        this is a constant, a change in number of channels of the underlying bus must
*                        be reflected by creating different SynthDefs. If a buffer identifier is used of a buffer
*                        that has a different numChannels then specified in the PlayBuf, it will fail silently.
* @param buf             the identifier of the buffer to use
* @param speed           1.0 advances the play head by the server's sample rate each second,
*                        so 2.0 means doubling speed (and pitch), and 0.5 means half speed (and half pitch).
*                        Negative numbers can be used for backwards playback. If the underlying buffer
*                        represents a sound at a different sample rate, the rate should be
*                        multiplied by `BufRateScale.kr( bufID )` to obtain the correct speed.
* @param trig            a trigger which causes a jump to the given startPos. A trigger occurs when a
*                        signal changes from non-positive to positive (e.g. <= 0 to > 0).
* @param startPos        sample frame to start playback. This is read when a trigger occurs.
* @param loop            1 to loop after the play head reaches the buffer end, 0 to not loop. this can be modulated.
* @param doneAction      what to do when the play head reaches the buffer end.
*
* @see [[de.sciss.synth.ugen.BufRd]]
* @see [[de.sciss.synth.ugen.DiskIn]]
* @see [[de.sciss.synth.ugen.RecordBuf]]
* @see [[de.sciss.synth.DoneAction]]
* @see [[de.sciss.synth.ugen.Done]]
* @see [[de.sciss.synth.ugen.BufRateScale]]
* @see [[de.sciss.synth.ugen.BufFrames]]
*/
case class PlayBuf[ R <: Rate ](rate: R, numChannels: Int, buf: AnyGE, speed: AnyGE, trig: AnyGE, startPos: AnyGE, loop: AnyGE, doneAction: AnyGE) extends MultiOutUGenSource[PlayBufUGen[ R ]] with HasSideEffect with HasDoneFlag {
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
      IIdxSeq.tabulate(_exp_)(i => PlayBufUGen[ R ](rate, numChannels, _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _trig(i.%(_sz_trig)), _startPos(i.%(_sz_startPos)), _loop(i.%(_sz_loop)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class PlayBufUGen[ R <: Rate ](rate: R, numChannels: Int, buf: AnyUGenIn, speed: AnyUGenIn, trig: AnyUGenIn, startPos: AnyUGenIn, loop: AnyUGenIn, doneAction: AnyUGenIn)
extends MultiOutUGen[ R ](IIdxSeq.fill(numChannels)(rate), IIdxSeq(buf, speed, trig, startPos, loop, doneAction)) with HasSideEffect with HasDoneFlag

//object RecordBuf {
//   def kr(in: Multi[AnyGE], buf: AnyGE, offset: AnyGE = 0.0f, recLevel: AnyGE = 1.0f, preLevel: AnyGE = 0.0f, run: AnyGE = 1.0f, loop: AnyGE = 1.0f, trig: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(control, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
//   def ar(in: Multi[AnyGE], buf: AnyGE, offset: AnyGE = 0.0f, recLevel: AnyGE = 1.0f, preLevel: AnyGE = 0.0f, run: AnyGE = 1.0f, loop: AnyGE = 1.0f, trig: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(audio, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
//}
//case class RecordBuf(rate: Rate, in: Multi[AnyGE], buf: AnyGE, offset: AnyGE, recLevel: AnyGE, preLevel: AnyGE, run: AnyGE, loop: AnyGE, trig: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[RecordBufUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _offset: IIdxSeq[UGenIn] = offset.expand
//      val _recLevel: IIdxSeq[UGenIn] = recLevel.expand
//      val _preLevel: IIdxSeq[UGenIn] = preLevel.expand
//      val _run: IIdxSeq[UGenIn] = run.expand
//      val _loop: IIdxSeq[UGenIn] = loop.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      val _sz_buf = _buf.size
//      val _sz_offset = _offset.size
//      val _sz_recLevel = _recLevel.size
//      val _sz_preLevel = _preLevel.size
//      val _sz_run = _run.size
//      val _sz_loop = _loop.size
//      val _sz_trig = _trig.size
//      val _sz_doneAction = _doneAction.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_offset, _sz_recLevel, _sz_preLevel, _sz_run, _sz_loop, _sz_trig, _sz_doneAction, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => RecordBufUGen(rate, _in(i.%(_sz_in)).expand, _buf(i.%(_sz_buf)), _offset(i.%(_sz_offset)), _recLevel(i.%(_sz_recLevel)), _preLevel(i.%(_sz_preLevel)), _run(i.%(_sz_run)), _loop(i.%(_sz_loop)), _trig(i.%(_sz_trig)), _doneAction(i.%(_sz_doneAction))))
//   }
//}
//case class RecordBufUGen(rate: Rate, in: IIdxSeq[UGenIn], buf: UGenIn, offset: UGenIn, recLevel: UGenIn, preLevel: UGenIn, run: UGenIn, loop: UGenIn, trig: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](buf, offset, recLevel, preLevel, run, loop, trig, doneAction).++(in)) with WritesBuffer
///**
// * A UGen which reads the content of a buffer, using an index pointer.
// *
// * Warning: if the supplied `bufID` refers to a buffer whose number of channels
// * differs from `numChannels`, the UGen will fail silently.
// *
// * An alternative to `BufRd` is `PlayBuf`. While `PlayBuf` plays
// * through the buffer by itself, `BufRd` only moves its read point by the phase input
// * and therefore has no pitch input. `PlayBuf` uses cubic interplation, while
// * `BufRd` has variable interpolation. `PlayBuf` can determine the end of the buffer
// * and issue a done-action.
// *
// * @see [[de.sciss.synth.ugen.PlayBuf]]
// * @see [[de.sciss.synth.ugen.BufWr]]
// * @see [[de.sciss.synth.ugen.Phasor]]
// * @see [[de.sciss.synth.ugen.BufFrames]]
// * @see [[de.sciss.synth.ugen.BufRateScale]]
// */
//object BufRd {
//
///**
// * @param numChannels     number of channels that the buffer will be.
// *                        Since this is an integer constant, a change in the number of channels must
// *                        be reflected by creating different SynthDefs.
// * @param buf             the identifier of the buffer to use
// * @param index           audio rate frame-index into the buffer.
// * @param loop            1 to enable looping, 0 to disable looping. this can be modulated.
// * @param interp          1 for no interpolation, 2 for linear, and 4 for cubic interpolation
// */
//def kr(numChannels: Int, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f, interp: AnyGE = 2.0f) = apply(control, numChannels, buf, index, loop, interp)
///**
// * @param numChannels     number of channels that the buffer will be.
// *                        Since this is an integer constant, a change in the number of channels must
// *                        be reflected by creating different SynthDefs.
// * @param buf             the identifier of the buffer to use
// * @param index           audio rate frame-index into the buffer.
// * @param loop            1 to enable looping, 0 to disable looping. this can be modulated.
// * @param interp          1 for no interpolation, 2 for linear, and 4 for cubic interpolation
// */
//def ar(numChannels: Int, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f, interp: AnyGE = 2.0f) = apply(audio, numChannels, buf, index, loop, interp)
//}
///**
// * A UGen which reads the content of a buffer, using an index pointer.
// *
// * Warning: if the supplied `bufID` refers to a buffer whose number of channels
// * differs from `numChannels`, the UGen will fail silently.
// *
// * An alternative to `BufRd` is `PlayBuf`. While `PlayBuf` plays
// * through the buffer by itself, `BufRd` only moves its read point by the phase input
// * and therefore has no pitch input. `PlayBuf` uses cubic interplation, while
// * `BufRd` has variable interpolation. `PlayBuf` can determine the end of the buffer
// * and issue a done-action.
// *
// * @param numChannels     number of channels that the buffer will be.
// *                        Since this is an integer constant, a change in the number of channels must
// *                        be reflected by creating different SynthDefs.
// * @param buf             the identifier of the buffer to use
// * @param index           audio rate frame-index into the buffer.
// * @param loop            1 to enable looping, 0 to disable looping. this can be modulated.
// * @param interp          1 for no interpolation, 2 for linear, and 4 for cubic interpolation
// *
// * @see [[de.sciss.synth.ugen.PlayBuf]]
// * @see [[de.sciss.synth.ugen.BufWr]]
// * @see [[de.sciss.synth.ugen.Phasor]]
// * @see [[de.sciss.synth.ugen.BufFrames]]
// * @see [[de.sciss.synth.ugen.BufRateScale]]
// */
//case class BufRd(rate: Rate, numChannels: Int, buf: AnyGE, index: AnyGE, loop: AnyGE, interp: AnyGE) extends MultiOutUGenSource[BufRdUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _index: IIdxSeq[UGenIn] = index.expand
//      val _loop: IIdxSeq[UGenIn] = loop.expand
//      val _interp: IIdxSeq[UGenIn] = interp.expand
//      val _sz_buf = _buf.size
//      val _sz_index = _index.size
//      val _sz_loop = _loop.size
//      val _sz_interp = _interp.size
//      val _exp_ = maxInt(_sz_buf, _sz_index, _sz_loop, _sz_interp)
//      IIdxSeq.tabulate(_exp_)(i => BufRdUGen(rate, numChannels, _buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop)), _interp(i.%(_sz_interp))))
//   }
//}
//case class BufRdUGen(rate: Rate, numChannels: Int, buf: UGenIn, index: UGenIn, loop: UGenIn, interp: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(buf, index, loop, interp))
//object BufWr {
//   def kr(in: Multi[AnyGE], buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) = apply(control, in, buf, index, loop)
//   def ar(in: Multi[AnyGE], buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) = apply(audio, in, buf, index, loop)
//}
//case class BufWr(rate: Rate, in: Multi[AnyGE], buf: AnyGE, index: AnyGE, loop: AnyGE) extends SingleOutUGenSource[BufWrUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _index: IIdxSeq[UGenIn] = index.expand
//      val _loop: IIdxSeq[UGenIn] = loop.expand
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      val _sz_buf = _buf.size
//      val _sz_index = _index.size
//      val _sz_loop = _loop.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_index, _sz_loop, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => BufWrUGen(rate, _in(i.%(_sz_in)).expand, _buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop))))
//   }
//}
//case class BufWrUGen(rate: Rate, in: IIdxSeq[UGenIn], buf: UGenIn, index: UGenIn, loop: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](buf, index, loop).++(in)) with WritesBuffer
//object Pitch {
//   def kr(in: AnyGE, initFreq: AnyGE = 440.0f, minFreq: AnyGE = 60.0f, maxFreq: AnyGE = 4000.0f, execFreq: AnyGE = 100.0f, binsPerOct: AnyGE = 16.0f, median: AnyGE = 1.0f, ampThresh: AnyGE = 0.01f, peakThresh: AnyGE = 0.5f, downSample: AnyGE = 1.0f) = apply(control, in, initFreq, minFreq, maxFreq, execFreq, binsPerOct, median, ampThresh, peakThresh, downSample)
//}
//case class Pitch(rate: Rate, in: AnyGE, initFreq: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, execFreq: AnyGE, binsPerOct: AnyGE, median: AnyGE, ampThresh: AnyGE, peakThresh: AnyGE, downSample: AnyGE) extends MultiOutUGenSource[PitchUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _initFreq: IIdxSeq[UGenIn] = initFreq.expand
//      val _minFreq: IIdxSeq[UGenIn] = minFreq.expand
//      val _maxFreq: IIdxSeq[UGenIn] = maxFreq.expand
//      val _execFreq: IIdxSeq[UGenIn] = execFreq.expand
//      val _binsPerOct: IIdxSeq[UGenIn] = binsPerOct.expand
//      val _median: IIdxSeq[UGenIn] = median.expand
//      val _ampThresh: IIdxSeq[UGenIn] = ampThresh.expand
//      val _peakThresh: IIdxSeq[UGenIn] = peakThresh.expand
//      val _downSample: IIdxSeq[UGenIn] = downSample.expand
//      val _sz_in = _in.size
//      val _sz_initFreq = _initFreq.size
//      val _sz_minFreq = _minFreq.size
//      val _sz_maxFreq = _maxFreq.size
//      val _sz_execFreq = _execFreq.size
//      val _sz_binsPerOct = _binsPerOct.size
//      val _sz_median = _median.size
//      val _sz_ampThresh = _ampThresh.size
//      val _sz_peakThresh = _peakThresh.size
//      val _sz_downSample = _downSample.size
//      val _exp_ = maxInt(_sz_in, _sz_initFreq, _sz_minFreq, _sz_maxFreq, _sz_execFreq, _sz_binsPerOct, _sz_median, _sz_ampThresh, _sz_peakThresh, _sz_downSample)
//      IIdxSeq.tabulate(_exp_)(i => PitchUGen(rate, _in(i.%(_sz_in)), _initFreq(i.%(_sz_initFreq)), _minFreq(i.%(_sz_minFreq)), _maxFreq(i.%(_sz_maxFreq)), _execFreq(i.%(_sz_execFreq)), _binsPerOct(i.%(_sz_binsPerOct)), _median(i.%(_sz_median)), _ampThresh(i.%(_sz_ampThresh)), _peakThresh(i.%(_sz_peakThresh)), _downSample(i.%(_sz_downSample))))
//   }
//}
//case class PitchUGen(rate: Rate, in: UGenIn, initFreq: UGenIn, minFreq: UGenIn, maxFreq: UGenIn, execFreq: UGenIn, binsPerOct: UGenIn, median: UGenIn, ampThresh: UGenIn, peakThresh: UGenIn, downSample: UGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, initFreq, minFreq, maxFreq, execFreq, binsPerOct, median, ampThresh, peakThresh, downSample))
//object BufDelayN {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply(audio, buf, in, delayTime)
//}
//case class BufDelayN(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[BufDelayNUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufDelayNUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime))))
//   }
//}
//case class BufDelayNUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime)) with WritesBuffer
//object BufDelayL {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply(audio, buf, in, delayTime)
//}
//case class BufDelayL(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[BufDelayLUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufDelayLUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime))))
//   }
//}
//case class BufDelayLUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime)) with WritesBuffer
//object BufDelayC {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply(audio, buf, in, delayTime)
//}
//case class BufDelayC(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[BufDelayCUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufDelayCUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime))))
//   }
//}
//case class BufDelayCUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime)) with WritesBuffer
//object BufCombN {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
//}
//case class BufCombN(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[BufCombNUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufCombNUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class BufCombNUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
//object BufCombL {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
//}
//case class BufCombL(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[BufCombLUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufCombLUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class BufCombLUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
//object BufCombC {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
//}
//case class BufCombC(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[BufCombCUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufCombCUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class BufCombCUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
//object BufAllpassN {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
//}
//case class BufAllpassN(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[BufAllpassNUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufAllpassNUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class BufAllpassNUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
//object BufAllpassL {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
//}
//case class BufAllpassL(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[BufAllpassLUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufAllpassLUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class BufAllpassLUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
//object BufAllpassC {
//   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
//}
//case class BufAllpassC(rate: Rate, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[BufAllpassCUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => BufAllpassCUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class BufAllpassCUGen(rate: Rate, buf: UGenIn, in: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, delayTime, decayTime)) with WritesBuffer
//object DelayN {
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
//}
//case class DelayN(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[DelayNUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
//      IIdxSeq.tabulate(_exp_)(i => DelayNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime))))
//   }
//}
//case class DelayNUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime))
//object DelayL {
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
//}
//case class DelayL(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[DelayLUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
//      IIdxSeq.tabulate(_exp_)(i => DelayLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime))))
//   }
//}
//case class DelayLUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime))
//object DelayC {
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
//}
//case class DelayC(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[DelayCUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
//      IIdxSeq.tabulate(_exp_)(i => DelayCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime))))
//   }
//}
//case class DelayCUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime))
//object CombN {
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
//}
//case class CombN(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[CombNUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => CombNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class CombNUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object CombL {
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
//}
//case class CombL(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[CombLUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => CombLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class CombLUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object CombC {
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
//}
//case class CombC(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[CombCUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => CombCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class CombCUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object AllpassN {
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
//}
//case class AllpassN(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[AllpassNUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => AllpassNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class AllpassNUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object AllpassL {
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
//}
//case class AllpassL(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[AllpassLUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => AllpassLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class AllpassLUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object AllpassC {
//   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
//   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
//}
//case class AllpassC(rate: Rate, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[AllpassCUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => AllpassCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class AllpassCUGen(rate: Rate, in: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn, decayTime: UGenIn) extends SingleOutUGen(IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object PitchShift {
//   def ar(in: AnyGE, winSize: AnyGE = 0.2f, pitchRatio: AnyGE = 1.0f, pitchDispersion: AnyGE = 0.0f, timeDispersion: AnyGE = 0.0f) = apply(in, winSize, pitchRatio, pitchDispersion, timeDispersion)
//}
//case class PitchShift(in: AnyGE, winSize: AnyGE, pitchRatio: AnyGE, pitchDispersion: AnyGE, timeDispersion: AnyGE) extends SingleOutUGenSource[PitchShiftUGen] with AudioRated {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _winSize: IIdxSeq[UGenIn] = winSize.expand
//      val _pitchRatio: IIdxSeq[UGenIn] = pitchRatio.expand
//      val _pitchDispersion: IIdxSeq[UGenIn] = pitchDispersion.expand
//      val _timeDispersion: IIdxSeq[UGenIn] = timeDispersion.expand
//      val _sz_in = _in.size
//      val _sz_winSize = _winSize.size
//      val _sz_pitchRatio = _pitchRatio.size
//      val _sz_pitchDispersion = _pitchDispersion.size
//      val _sz_timeDispersion = _timeDispersion.size
//      val _exp_ = maxInt(_sz_in, _sz_winSize, _sz_pitchRatio, _sz_pitchDispersion, _sz_timeDispersion)
//      IIdxSeq.tabulate(_exp_)(i => PitchShiftUGen(_in(i.%(_sz_in)), _winSize(i.%(_sz_winSize)), _pitchRatio(i.%(_sz_pitchRatio)), _pitchDispersion(i.%(_sz_pitchDispersion)), _timeDispersion(i.%(_sz_timeDispersion))))
//   }
//}
//case class PitchShiftUGen(in: UGenIn, winSize: UGenIn, pitchRatio: UGenIn, pitchDispersion: UGenIn, timeDispersion: UGenIn) extends SingleOutUGen(IIdxSeq(in, winSize, pitchRatio, pitchDispersion, timeDispersion)) with AudioRated
//object TGrains {
//   def ar(numChannels: Int, trig: AnyGE, buf: AnyGE, speed: AnyGE = 1.0f, centerPos: AnyGE = 0.0f, dur: AnyGE = 0.1f, pan: AnyGE = 0.0f, amp: AnyGE = 0.1f, interp: AnyGE = 4.0f) = apply(numChannels, trig, buf, speed, centerPos, dur, pan, amp, interp)
//}
//case class TGrains(numChannels: Int, trig: AnyGE, buf: AnyGE, speed: AnyGE, centerPos: AnyGE, dur: AnyGE, pan: AnyGE, amp: AnyGE, interp: AnyGE) extends MultiOutUGenSource[TGrainsUGen] with AudioRated {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _speed: IIdxSeq[UGenIn] = speed.expand
//      val _centerPos: IIdxSeq[UGenIn] = centerPos.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _pan: IIdxSeq[UGenIn] = pan.expand
//      val _amp: IIdxSeq[UGenIn] = amp.expand
//      val _interp: IIdxSeq[UGenIn] = interp.expand
//      val _sz_trig = _trig.size
//      val _sz_buf = _buf.size
//      val _sz_speed = _speed.size
//      val _sz_centerPos = _centerPos.size
//      val _sz_dur = _dur.size
//      val _sz_pan = _pan.size
//      val _sz_amp = _amp.size
//      val _sz_interp = _interp.size
//      val _exp_ = maxInt(_sz_trig, _sz_buf, _sz_speed, _sz_centerPos, _sz_dur, _sz_pan, _sz_amp, _sz_interp)
//      IIdxSeq.tabulate(_exp_)(i => TGrainsUGen(numChannels, _trig(i.%(_sz_trig)), _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _centerPos(i.%(_sz_centerPos)), _dur(i.%(_sz_dur)), _pan(i.%(_sz_pan)), _amp(i.%(_sz_amp)), _interp(i.%(_sz_interp))))
//   }
//}
//case class TGrainsUGen(numChannels: Int, trig: UGenIn, buf: UGenIn, speed: UGenIn, centerPos: UGenIn, dur: UGenIn, pan: UGenIn, amp: UGenIn, interp: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, buf, speed, centerPos, dur, pan, amp, interp)) with AudioRated
//object ScopeOut {
//   def ar(buf: AnyGE, in: Multi[AnyGE]) = apply(audio, buf, in)
//   def kr(buf: AnyGE, in: Multi[AnyGE]) = apply(control, buf, in)
//}
//case class ScopeOut(rate: Rate, buf: AnyGE, in: Multi[AnyGE]) extends SingleOutUGenSource[ScopeOutUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => ScopeOutUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in)).expand))
//   }
//}
//case class ScopeOutUGen(rate: Rate, buf: UGenIn, in: IIdxSeq[UGenIn]) extends SingleOutUGen(IIdxSeq[UGenIn](buf).++(in)) with WritesBuffer
//object Pluck {
//   def ar(in: AnyGE, trig: AnyGE = 1.0f, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f, coef: AnyGE = 0.5f) = apply(in, trig, maxDelayTime, delayTime, decayTime, coef)
//}
//case class Pluck(in: AnyGE, trig: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE, coef: AnyGE) extends SingleOutUGenSource[PluckUGen] with AudioRated {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _maxDelayTime: IIdxSeq[UGenIn] = maxDelayTime.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _decayTime: IIdxSeq[UGenIn] = decayTime.expand
//      val _coef: IIdxSeq[UGenIn] = coef.expand
//      val _sz_in = _in.size
//      val _sz_trig = _trig.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _sz_coef = _coef.size
//      val _exp_ = maxInt(_sz_in, _sz_trig, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime, _sz_coef)
//      IIdxSeq.tabulate(_exp_)(i => PluckUGen(_in(i.%(_sz_in)), _trig(i.%(_sz_trig)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)), _coef(i.%(_sz_coef))))
//   }
//}
//case class PluckUGen(in: UGenIn, trig: UGenIn, maxDelayTime: UGenIn, delayTime: UGenIn, decayTime: UGenIn, coef: UGenIn) extends SingleOutUGen(IIdxSeq(in, trig, maxDelayTime, delayTime, decayTime, coef)) with AudioRated
//object DelTapWr {
//   def ar(buf: AnyGE, in: AnyGE) = apply(audio, buf, in)
//   def kr(buf: AnyGE, in: AnyGE) = apply(control, buf, in)
//}
//case class DelTapWr(rate: Rate, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[DelTapWrUGen] with WritesBuffer {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => DelTapWrUGen(rate, _buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
//   }
//}
//case class DelTapWrUGen(rate: Rate, buf: UGenIn, in: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in)) with WritesBuffer
//object DelTapRd {
//   def ar(buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE = 1.0f) = apply(audio, buf, phase, delayTime, interp)
//   def kr(buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE = 1.0f) = apply(control, buf, phase, delayTime, interp)
//}
//case class DelTapRd(rate: Rate, buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE) extends SingleOutUGenSource[DelTapRdUGen] {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _phase: IIdxSeq[UGenIn] = phase.expand
//      val _delayTime: IIdxSeq[UGenIn] = delayTime.expand
//      val _interp: IIdxSeq[UGenIn] = interp.expand
//      val _sz_buf = _buf.size
//      val _sz_phase = _phase.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_interp = _interp.size
//      val _exp_ = maxInt(_sz_buf, _sz_phase, _sz_delayTime, _sz_interp)
//      IIdxSeq.tabulate(_exp_)(i => DelTapRdUGen(rate, _buf(i.%(_sz_buf)), _phase(i.%(_sz_phase)), _delayTime(i.%(_sz_delayTime)), _interp(i.%(_sz_interp))))
//   }
//}
//case class DelTapRdUGen(rate: Rate, buf: UGenIn, phase: UGenIn, delayTime: UGenIn, interp: UGenIn) extends SingleOutUGen(IIdxSeq(buf, phase, delayTime, interp))