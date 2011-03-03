/*
 * DelayUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
/**
 * A UGen that reports the server's current control rate. This is equivalent to the reciprocal
 * of `ControlDur`
 * 
 * @see [[de.sciss.synth.ugen.ControlDur]]
 * @see [[de.sciss.synth.ugen.SampleRate]]
 */
object ControlRate {
   def ir = apply()
}
/**
 * A UGen that reports the server's current control rate. This is equivalent to the reciprocal
 * of `ControlDur`
 * 
 * @see [[de.sciss.synth.ugen.ControlDur]]
 * @see [[de.sciss.synth.ugen.SampleRate]]
 */
final case class ControlRate() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("ControlRate", scalar, IIdxSeq.empty))
}
/**
 * A UGen that reports the server's current (audio) sample rate. This is equivalent to the reciprocal
 * of `SampleDur`
 * 
 * @see [[de.sciss.synth.ugen.SampleDur]]
 * @see [[de.sciss.synth.ugen.ControlRate]]
 * @see [[de.sciss.synth.ugen.RadiansPerSample]]
 */
object SampleRate {
   def ir = apply()
}
/**
 * A UGen that reports the server's current (audio) sample rate. This is equivalent to the reciprocal
 * of `SampleDur`
 * 
 * @see [[de.sciss.synth.ugen.SampleDur]]
 * @see [[de.sciss.synth.ugen.ControlRate]]
 * @see [[de.sciss.synth.ugen.RadiansPerSample]]
 */
final case class SampleRate() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("SampleRate", scalar, IIdxSeq.empty))
}
/**
 * A UGen that reports the server's current (audio) sample period in seconds. This is equivalent to the reciprocal
 * of `SampleRate`
 * 
 * @see [[de.sciss.synth.ugen.SampleRate]]
 * @see [[de.sciss.synth.ugen.ControlDur]]
 */
object SampleDur {
   def ir = apply()
}
/**
 * A UGen that reports the server's current (audio) sample period in seconds. This is equivalent to the reciprocal
 * of `SampleRate`
 * 
 * @see [[de.sciss.synth.ugen.SampleRate]]
 * @see [[de.sciss.synth.ugen.ControlDur]]
 */
final case class SampleDur() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("SampleDur", scalar, IIdxSeq.empty))
}
/**
 * A UGen that reports the server's current control period in seconds. This is equivalent to the reciprocal
 * of `ControlRate`
 * 
 * @see [[de.sciss.synth.ugen.ControlRate]]
 * @see [[de.sciss.synth.ugen.SampleDur]]
 */
object ControlDur {
   def ir = apply()
}
/**
 * A UGen that reports the server's current control period in seconds. This is equivalent to the reciprocal
 * of `ControlRate`
 * 
 * @see [[de.sciss.synth.ugen.ControlRate]]
 * @see [[de.sciss.synth.ugen.SampleDur]]
 */
final case class ControlDur() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("ControlDur", scalar, IIdxSeq.empty))
}
/**
 * A UGen that reports the fractional sample offset of the current Synth from its requested scheduled start.
 * 
 * When a synth is created from a time stamped osc-bundle, it starts
 * calculation at the next possible block (normally 64 samples). Using an
 * `OffsetOut` UGen, one can delay the audio so that it matches sample
 * accurately.
 * 
 * For some synthesis methods, one even needs subsample accuracy. `SubsampleOffset`
 * provides the information where, within the current sample, the synth was
 * scheduled. It can be used to offset envelopes or resample the audio
 * output.
 * 
 * @see [[de.sciss.synth.ugen.ControlRate]]
 * @see [[de.sciss.synth.ugen.SampleDur]]
 */
object SubsampleOffset {
   def ir = apply()
}
/**
 * A UGen that reports the fractional sample offset of the current Synth from its requested scheduled start.
 * 
 * When a synth is created from a time stamped osc-bundle, it starts
 * calculation at the next possible block (normally 64 samples). Using an
 * `OffsetOut` UGen, one can delay the audio so that it matches sample
 * accurately.
 * 
 * For some synthesis methods, one even needs subsample accuracy. `SubsampleOffset`
 * provides the information where, within the current sample, the synth was
 * scheduled. It can be used to offset envelopes or resample the audio
 * output.
 * 
 * @see [[de.sciss.synth.ugen.ControlRate]]
 * @see [[de.sciss.synth.ugen.SampleDur]]
 */
final case class SubsampleOffset() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("SubsampleOffset", scalar, IIdxSeq.empty))
}
/**
 * A UGen that delivers the conversion factor from frequency in Hertz to radians (normalized frequency).
 * The relation is `RadiansPerSample * sr = 2pi`, thus multiplying the UGen with a frequency between
 * zero and nyquist (sr/2) yields the normalized frequency between zero and pi.
 * 
 * @see [[de.sciss.synth.ugen.SampleRate]]
 */
object RadiansPerSample {
   def ir = apply()
}
/**
 * A UGen that delivers the conversion factor from frequency in Hertz to radians (normalized frequency).
 * The relation is `RadiansPerSample * sr = 2pi`, thus multiplying the UGen with a frequency between
 * zero and nyquist (sr/2) yields the normalized frequency between zero and pi.
 * 
 * @see [[de.sciss.synth.ugen.SampleRate]]
 */
final case class RadiansPerSample() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("RadiansPerSample", scalar, IIdxSeq.empty))
}
object NumInputBuses {
   def ir = apply()
}
final case class NumInputBuses() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("NumInputBuses", scalar, IIdxSeq.empty))
}
object NumOutputBuses {
   def ir = apply()
}
final case class NumOutputBuses() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("NumOutputBuses", scalar, IIdxSeq.empty))
}
object NumAudioBuses {
   def ir = apply()
}
final case class NumAudioBuses() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("NumAudioBuses", scalar, IIdxSeq.empty))
}
object NumControlBuses {
   def ir = apply()
}
final case class NumControlBuses() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("NumControlBuses", scalar, IIdxSeq.empty))
}
object NumBuffers {
   def ir = apply()
}
final case class NumBuffers() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("NumBuffers", scalar, IIdxSeq.empty))
}
object NumRunningSynths {
   def ir = apply()
}
final case class NumRunningSynths() extends SingleOutUGenSource[scalar] {
   protected def expandUGens = IIdxSeq(new SingleOutUGen("NumRunningSynths", scalar, IIdxSeq.empty))
}
object BufSampleRate {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
final case class BufSampleRate[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => new SingleOutUGen("BufSampleRate", rate, IIdxSeq(_buf(i))))
   }
}
object BufRateScale {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
final case class BufRateScale[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => new SingleOutUGen("BufRateScale", rate, IIdxSeq(_buf(i))))
   }
}
object BufSamples {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
final case class BufSamples[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => new SingleOutUGen("BufSamples", rate, IIdxSeq(_buf(i))))
   }
}
object BufFrames {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
final case class BufFrames[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => new SingleOutUGen("BufFrames", rate, IIdxSeq(_buf(i))))
   }
}
object BufChannels {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
final case class BufChannels[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => new SingleOutUGen("BufChannels", rate, IIdxSeq(_buf(i))))
   }
}
object BufDur {
   def ir(buf: AnyGE) = apply[scalar](scalar, buf)
   def kr(buf: AnyGE) = apply[control](control, buf)
}
final case class BufDur[R <: Rate](rate: R, buf: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      IIdxSeq.tabulate(_buf.size)(i => new SingleOutUGen("BufDur", rate, IIdxSeq(_buf(i))))
   }
}
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
   def kr(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, trig: AnyGE = 1.0f, startPos: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, numChannels, buf, speed, trig, startPos, loop, doneAction)
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
   def ar(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, trig: AnyGE = 1.0f, startPos: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, numChannels, buf, speed, trig, startPos, loop, doneAction)
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
final case class PlayBuf[R <: Rate](rate: R, numChannels: Int, buf: AnyGE, speed: AnyGE, trig: AnyGE, startPos: AnyGE, loop: AnyGE, doneAction: AnyGE) extends MultiOutUGenSource[R] with HasSideEffect with HasDoneFlag {
   protected def expandUGens = {
      val _buf = buf.expand
      val _speed = speed.expand
      val _trig = trig.expand
      val _startPos = startPos.expand
      val _loop = loop.expand
      val _doneAction = doneAction.expand
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_trig = _trig.size
      val _sz_startPos = _startPos.size
      val _sz_loop = _loop.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_buf, _sz_speed, _sz_trig, _sz_startPos, _sz_loop, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("PlayBuf", rate, IIdxSeq.fill(numChannels)(rate), IIdxSeq(_buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _trig(i.%(_sz_trig)), _startPos(i.%(_sz_startPos)), _loop(i.%(_sz_loop)), _doneAction(i.%(_sz_doneAction)))))
   }
}
object RecordBuf {
   def kr(in: Multi[AnyGE], buf: AnyGE, offset: AnyGE = 0.0f, recLevel: AnyGE = 1.0f, preLevel: AnyGE = 0.0f, run: AnyGE = 1.0f, loop: AnyGE = 1.0f, trig: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
   def ar(in: Multi[AnyGE], buf: AnyGE, offset: AnyGE = 0.0f, recLevel: AnyGE = 1.0f, preLevel: AnyGE = 0.0f, run: AnyGE = 1.0f, loop: AnyGE = 1.0f, trig: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
}
final case class RecordBuf[R <: Rate](rate: R, in: Multi[AnyGE], buf: AnyGE, offset: AnyGE, recLevel: AnyGE, preLevel: AnyGE, run: AnyGE, loop: AnyGE, trig: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _offset = offset.expand
      val _recLevel = recLevel.expand
      val _preLevel = preLevel.expand
      val _run = run.expand
      val _loop = loop.expand
      val _trig = trig.expand
      val _doneAction = doneAction.expand
      val _in = in.mexpand
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
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("RecordBuf", rate, IIdxSeq(_buf(i.%(_sz_buf)), _offset(i.%(_sz_offset)), _recLevel(i.%(_sz_recLevel)), _preLevel(i.%(_sz_preLevel)), _run(i.%(_sz_run)), _loop(i.%(_sz_loop)), _trig(i.%(_sz_trig)), _doneAction(i.%(_sz_doneAction))).++(_in(i.%(_sz_in)).expand)))
   }
}
/**
 * A UGen which reads the content of a buffer, using an index pointer.
 * 
 * Warning: if the supplied `bufID` refers to a buffer whose number of channels
 * differs from `numChannels`, the UGen will fail silently.
 * 
 * An alternative to `BufRd` is `PlayBuf`. While `PlayBuf` plays
 * through the buffer by itself, `BufRd` only moves its read point by the phase input
 * and therefore has no pitch input. `PlayBuf` uses cubic interplation, while
 * `BufRd` has variable interpolation. `PlayBuf` can determine the end of the buffer
 * and issue a done-action.
 * 
 * @see [[de.sciss.synth.ugen.PlayBuf]]
 * @see [[de.sciss.synth.ugen.BufWr]]
 * @see [[de.sciss.synth.ugen.Phasor]]
 * @see [[de.sciss.synth.ugen.BufFrames]]
 * @see [[de.sciss.synth.ugen.BufRateScale]]
 */
object BufRd {
   
   /**
    * @param numChannels     number of channels that the buffer will be.
    *                        Since this is an integer constant, a change in the number of channels must
    *                        be reflected by creating different SynthDefs.
    * @param buf             the identifier of the buffer to use
    * @param index           audio rate frame-index into the buffer.
    * @param loop            1 to enable looping, 0 to disable looping. this can be modulated.
    * @param interp          1 for no interpolation, 2 for linear, and 4 for cubic interpolation
    */
   def kr(numChannels: Int, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f, interp: AnyGE = 2.0f) = apply[control](control, numChannels, buf, index, loop, interp)
   /**
    * @param numChannels     number of channels that the buffer will be.
    *                        Since this is an integer constant, a change in the number of channels must
    *                        be reflected by creating different SynthDefs.
    * @param buf             the identifier of the buffer to use
    * @param index           audio rate frame-index into the buffer.
    * @param loop            1 to enable looping, 0 to disable looping. this can be modulated.
    * @param interp          1 for no interpolation, 2 for linear, and 4 for cubic interpolation
    */
   def ar(numChannels: Int, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f, interp: AnyGE = 2.0f) = apply[audio](audio, numChannels, buf, index, loop, interp)
}
/**
 * A UGen which reads the content of a buffer, using an index pointer.
 * 
 * Warning: if the supplied `bufID` refers to a buffer whose number of channels
 * differs from `numChannels`, the UGen will fail silently.
 * 
 * An alternative to `BufRd` is `PlayBuf`. While `PlayBuf` plays
 * through the buffer by itself, `BufRd` only moves its read point by the phase input
 * and therefore has no pitch input. `PlayBuf` uses cubic interplation, while
 * `BufRd` has variable interpolation. `PlayBuf` can determine the end of the buffer
 * and issue a done-action.
 * 
 * @param numChannels     number of channels that the buffer will be.
 *                        Since this is an integer constant, a change in the number of channels must
 *                        be reflected by creating different SynthDefs.
 * @param buf             the identifier of the buffer to use
 * @param index           audio rate frame-index into the buffer.
 * @param loop            1 to enable looping, 0 to disable looping. this can be modulated.
 * @param interp          1 for no interpolation, 2 for linear, and 4 for cubic interpolation
 * 
 * @see [[de.sciss.synth.ugen.PlayBuf]]
 * @see [[de.sciss.synth.ugen.BufWr]]
 * @see [[de.sciss.synth.ugen.Phasor]]
 * @see [[de.sciss.synth.ugen.BufFrames]]
 * @see [[de.sciss.synth.ugen.BufRateScale]]
 */
final case class BufRd[R <: Rate](rate: R, numChannels: Int, buf: AnyGE, index: AnyGE, loop: AnyGE, interp: AnyGE) extends MultiOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _index = index.expand
      val _loop = loop.expand
      val _interp = interp.expand
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_buf, _sz_index, _sz_loop, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("BufRd", rate, IIdxSeq.fill(numChannels)(rate), IIdxSeq(_buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop)), _interp(i.%(_sz_interp)))))
   }
}
object BufWr {
   def kr(in: Multi[AnyGE], buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) = apply[control](control, in, buf, index, loop)
   def ar(in: Multi[AnyGE], buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) = apply[audio](audio, in, buf, index, loop)
}
final case class BufWr[R <: Rate](rate: R, in: Multi[AnyGE], buf: AnyGE, index: AnyGE, loop: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _index = index.expand
      val _loop = loop.expand
      val _in = in.mexpand
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_index, _sz_loop, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufWr", rate, IIdxSeq(_buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop))).++(_in(i.%(_sz_in)).expand)))
   }
}
object Pitch {
   def kr(in: AnyGE, initFreq: AnyGE = 440.0f, minFreq: AnyGE = 60.0f, maxFreq: AnyGE = 4000.0f, execFreq: AnyGE = 100.0f, binsPerOct: AnyGE = 16.0f, median: AnyGE = 1.0f, ampThresh: AnyGE = 0.01f, peakThresh: AnyGE = 0.5f, downSample: AnyGE = 1.0f) = apply[control](control, in, initFreq, minFreq, maxFreq, execFreq, binsPerOct, median, ampThresh, peakThresh, downSample)
}
final case class Pitch[R <: Rate](rate: R, in: AnyGE, initFreq: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, execFreq: AnyGE, binsPerOct: AnyGE, median: AnyGE, ampThresh: AnyGE, peakThresh: AnyGE, downSample: AnyGE) extends MultiOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _initFreq = initFreq.expand
      val _minFreq = minFreq.expand
      val _maxFreq = maxFreq.expand
      val _execFreq = execFreq.expand
      val _binsPerOct = binsPerOct.expand
      val _median = median.expand
      val _ampThresh = ampThresh.expand
      val _peakThresh = peakThresh.expand
      val _downSample = downSample.expand
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
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("Pitch", rate, IIdxSeq.fill(2)(rate), IIdxSeq(_in(i.%(_sz_in)), _initFreq(i.%(_sz_initFreq)), _minFreq(i.%(_sz_minFreq)), _maxFreq(i.%(_sz_maxFreq)), _execFreq(i.%(_sz_execFreq)), _binsPerOct(i.%(_sz_binsPerOct)), _median(i.%(_sz_median)), _ampThresh(i.%(_sz_ampThresh)), _peakThresh(i.%(_sz_peakThresh)), _downSample(i.%(_sz_downSample)))))
   }
}
object BufDelayN {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply[audio](audio, buf, in, delayTime)
}
final case class BufDelayN[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufDelayN", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)))))
   }
}
object BufDelayL {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply[audio](audio, buf, in, delayTime)
}
final case class BufDelayL[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufDelayL", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)))))
   }
}
object BufDelayC {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f) = apply[audio](audio, buf, in, delayTime)
}
final case class BufDelayC[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufDelayC", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)))))
   }
}
object BufCombN {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
final case class BufCombN[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufCombN", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object BufCombL {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
final case class BufCombL[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufCombL", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object BufCombC {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
final case class BufCombC[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufCombC", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object BufAllpassN {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
final case class BufAllpassN[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufAllpassN", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object BufAllpassL {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
final case class BufAllpassL[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufAllpassL", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object BufAllpassC {
   def ar(buf: AnyGE, in: AnyGE, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, buf, in, delayTime, decayTime)
}
final case class BufAllpassC[R <: Rate](rate: R, buf: AnyGE, in: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BufAllpassC", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object DelayN {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[control](control, in, maxDelayTime, delayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[audio](audio, in, maxDelayTime, delayTime)
}
final case class DelayN[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DelayN", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)))))
   }
}
object DelayL {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[control](control, in, maxDelayTime, delayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[audio](audio, in, maxDelayTime, delayTime)
}
final case class DelayL[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DelayL", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)))))
   }
}
object DelayC {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[control](control, in, maxDelayTime, delayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f) = apply[audio](audio, in, maxDelayTime, delayTime)
}
final case class DelayC[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DelayC", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)))))
   }
}
object CombN {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
final case class CombN[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("CombN", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object CombL {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
final case class CombL[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("CombL", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object CombC {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
final case class CombC[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("CombC", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object AllpassN {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
}
final case class AllpassN[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("AllpassN", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object AllpassL {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
}
final case class AllpassL[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("AllpassL", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object AllpassC {
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
}
final case class AllpassC[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("AllpassC", rate, IIdxSeq(_in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)))))
   }
}
object PitchShift {
   def ar(in: AnyGE, winSize: AnyGE = 0.2f, pitchRatio: AnyGE = 1.0f, pitchDispersion: AnyGE = 0.0f, timeDispersion: AnyGE = 0.0f) = apply(in, winSize, pitchRatio, pitchDispersion, timeDispersion)
}
final case class PitchShift(in: AnyGE, winSize: AnyGE, pitchRatio: AnyGE, pitchDispersion: AnyGE, timeDispersion: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _winSize = winSize.expand
      val _pitchRatio = pitchRatio.expand
      val _pitchDispersion = pitchDispersion.expand
      val _timeDispersion = timeDispersion.expand
      val _sz_in = _in.size
      val _sz_winSize = _winSize.size
      val _sz_pitchRatio = _pitchRatio.size
      val _sz_pitchDispersion = _pitchDispersion.size
      val _sz_timeDispersion = _timeDispersion.size
      val _exp_ = maxInt(_sz_in, _sz_winSize, _sz_pitchRatio, _sz_pitchDispersion, _sz_timeDispersion)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PitchShift", audio, IIdxSeq(_in(i.%(_sz_in)), _winSize(i.%(_sz_winSize)), _pitchRatio(i.%(_sz_pitchRatio)), _pitchDispersion(i.%(_sz_pitchDispersion)), _timeDispersion(i.%(_sz_timeDispersion)))))
   }
}
object TGrains {
   def ar(numChannels: Int, trig: AnyGE, buf: AnyGE, speed: AnyGE = 1.0f, centerPos: AnyGE = 0.0f, dur: AnyGE = 0.1f, pan: AnyGE = 0.0f, amp: AnyGE = 0.1f, interp: AnyGE = 4.0f) = apply(numChannels, trig, buf, speed, centerPos, dur, pan, amp, interp)
}
final case class TGrains(numChannels: Int, trig: AnyGE, buf: AnyGE, speed: AnyGE, centerPos: AnyGE, dur: AnyGE, pan: AnyGE, amp: AnyGE, interp: AnyGE) extends MultiOutUGenSource[audio] {
   protected def expandUGens = {
      val _trig = trig.expand
      val _buf = buf.expand
      val _speed = speed.expand
      val _centerPos = centerPos.expand
      val _dur = dur.expand
      val _pan = pan.expand
      val _amp = amp.expand
      val _interp = interp.expand
      val _sz_trig = _trig.size
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_centerPos = _centerPos.size
      val _sz_dur = _dur.size
      val _sz_pan = _pan.size
      val _sz_amp = _amp.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_trig, _sz_buf, _sz_speed, _sz_centerPos, _sz_dur, _sz_pan, _sz_amp, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("TGrains", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_trig(i.%(_sz_trig)), _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _centerPos(i.%(_sz_centerPos)), _dur(i.%(_sz_dur)), _pan(i.%(_sz_pan)), _amp(i.%(_sz_amp)), _interp(i.%(_sz_interp)))))
   }
}
object ScopeOut {
   def ar(buf: AnyGE, in: Multi[AnyGE]) = apply[audio](audio, buf, in)
   def kr(buf: AnyGE, in: Multi[AnyGE]) = apply[control](control, buf, in)
}
final case class ScopeOut[R <: Rate](rate: R, buf: AnyGE, in: Multi[AnyGE]) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.mexpand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("ScopeOut", rate, IIdxSeq(_buf(i.%(_sz_buf))).++(_in(i.%(_sz_in)).expand)))
   }
}
object Pluck {
   def ar(in: AnyGE, trig: AnyGE = 1.0f, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f, coef: AnyGE = 0.5f) = apply(in, trig, maxDelayTime, delayTime, decayTime, coef)
}
final case class Pluck(in: AnyGE, trig: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE, coef: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _trig = trig.expand
      val _maxDelayTime = maxDelayTime.expand
      val _delayTime = delayTime.expand
      val _decayTime = decayTime.expand
      val _coef = coef.expand
      val _sz_in = _in.size
      val _sz_trig = _trig.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _sz_coef = _coef.size
      val _exp_ = maxInt(_sz_in, _sz_trig, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime, _sz_coef)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Pluck", audio, IIdxSeq(_in(i.%(_sz_in)), _trig(i.%(_sz_trig)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime)), _coef(i.%(_sz_coef)))))
   }
}
object DelTapWr {
   def ar(buf: AnyGE, in: AnyGE) = apply[audio](audio, buf, in)
   def kr(buf: AnyGE, in: AnyGE) = apply[control](control, buf, in)
}
final case class DelTapWr[R <: Rate](rate: R, buf: AnyGE, in: AnyGE) extends SingleOutUGenSource[R] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DelTapWr", rate, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)))))
   }
}
object DelTapRd {
   def ar(buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE = 1.0f) = apply[audio](audio, buf, phase, delayTime, interp)
   def kr(buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE = 1.0f) = apply[control](control, buf, phase, delayTime, interp)
}
final case class DelTapRd[R <: Rate](rate: R, buf: AnyGE, phase: AnyGE, delayTime: AnyGE, interp: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _phase = phase.expand
      val _delayTime = delayTime.expand
      val _interp = interp.expand
      val _sz_buf = _buf.size
      val _sz_phase = _phase.size
      val _sz_delayTime = _delayTime.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_buf, _sz_phase, _sz_delayTime, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DelTapRd", rate, IIdxSeq(_buf(i.%(_sz_buf)), _phase(i.%(_sz_phase)), _delayTime(i.%(_sz_delayTime)), _interp(i.%(_sz_interp)))))
   }
}