/*
 * DelayUGens.scala
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
final case class ControlRate() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("ControlRate", scalar, _args)
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
final case class SampleRate() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SampleRate", scalar, _args)
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
final case class SampleDur() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SampleDur", scalar, _args)
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
final case class ControlDur() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("ControlDur", scalar, _args)
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
final case class SubsampleOffset() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SubsampleOffset", scalar, _args)
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
final case class RadiansPerSample() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RadiansPerSample", scalar, _args)
}
object NumInputBuses {
   def ir = apply()
}
final case class NumInputBuses() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NumInputBuses", scalar, _args)
}
object NumOutputBuses {
   def ir = apply()
}
final case class NumOutputBuses() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NumOutputBuses", scalar, _args)
}
object NumAudioBuses {
   def ir = apply()
}
final case class NumAudioBuses() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NumAudioBuses", scalar, _args)
}
object NumControlBuses {
   def ir = apply()
}
final case class NumControlBuses() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NumControlBuses", scalar, _args)
}
object NumBuffers {
   def ir = apply()
}
final case class NumBuffers() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NumBuffers", scalar, _args)
}
object NumRunningSynths {
   def ir = apply()
}
final case class NumRunningSynths() extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NumRunningSynths", scalar, _args)
}
object BufSampleRate {
   def ir(buf: GE) = apply(scalar, buf)
   def kr(buf: GE) = apply(control, buf)
}
final case class BufSampleRate(rate: Rate, buf: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufSampleRate", rate, _args)
}
object BufRateScale {
   def ir(buf: GE) = apply(scalar, buf)
   def kr(buf: GE) = apply(control, buf)
}
final case class BufRateScale(rate: Rate, buf: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufRateScale", rate, _args)
}
object BufSamples {
   def ir(buf: GE) = apply(scalar, buf)
   def kr(buf: GE) = apply(control, buf)
}
final case class BufSamples(rate: Rate, buf: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufSamples", rate, _args)
}
object BufFrames {
   def ir(buf: GE) = apply(scalar, buf)
   def kr(buf: GE) = apply(control, buf)
}
final case class BufFrames(rate: Rate, buf: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufFrames", rate, _args)
}
object BufChannels {
   def ir(buf: GE) = apply(scalar, buf)
   def kr(buf: GE) = apply(control, buf)
}
final case class BufChannels(rate: Rate, buf: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufChannels", rate, _args)
}
object BufDur {
   def ir(buf: GE) = apply(scalar, buf)
   def kr(buf: GE) = apply(control, buf)
}
final case class BufDur(rate: Rate, buf: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufDur", rate, _args)
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
   def kr(numChannels: Int, buf: GE, speed: GE = 1.0f, trig: GE = 1.0f, startPos: GE = 0.0f, loop: GE = 1.0f, doneAction: GE = doNothing) = apply(control, numChannels, buf, speed, trig, startPos, loop, doneAction)
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
   def ar(numChannels: Int, buf: GE, speed: GE = 1.0f, trig: GE = 1.0f, startPos: GE = 0.0f, loop: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, numChannels, buf, speed, trig, startPos, loop, doneAction)
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
final case class PlayBuf(rate: Rate, numChannels: Int, buf: GE, speed: GE, trig: GE, startPos: GE, loop: GE, doneAction: GE) extends UGenSource.MultiOut with HasSideEffect with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, speed.expand, trig.expand, startPos.expand, loop.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("PlayBuf", rate, IIdxSeq.fill(numChannels)(rate), _args)
}
object RecordBuf {
   def kr(in: GE, buf: GE, offset: GE = 0.0f, recLevel: GE = 1.0f, preLevel: GE = 0.0f, run: GE = 1.0f, loop: GE = 1.0f, trig: GE = 1.0f, doneAction: GE = doNothing) = apply(control, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
   def ar(in: GE, buf: GE, offset: GE = 0.0f, recLevel: GE = 1.0f, preLevel: GE = 0.0f, run: GE = 1.0f, loop: GE = 1.0f, trig: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
}
final case class RecordBuf(rate: Rate, in: GE, buf: GE, offset: GE, recLevel: GE, preLevel: GE, run: GE, loop: GE, trig: GE, doneAction: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, offset.expand, recLevel.expand, preLevel.expand, run.expand, loop.expand, trig.expand, doneAction.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RecordBuf", rate, _args)
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
   def kr(numChannels: Int, buf: GE, index: GE = 0.0f, loop: GE = 1.0f, interp: GE = 2.0f) = apply(control, numChannels, buf, index, loop, interp)
   /**
    * @param numChannels     number of channels that the buffer will be.
    *                        Since this is an integer constant, a change in the number of channels must
    *                        be reflected by creating different SynthDefs.
    * @param buf             the identifier of the buffer to use
    * @param index           audio rate frame-index into the buffer.
    * @param loop            1 to enable looping, 0 to disable looping. this can be modulated.
    * @param interp          1 for no interpolation, 2 for linear, and 4 for cubic interpolation
    */
   def ar(numChannels: Int, buf: GE, index: GE = 0.0f, loop: GE = 1.0f, interp: GE = 2.0f) = apply(audio, numChannels, buf, index, loop, interp)
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
final case class BufRd(rate: Rate, numChannels: Int, buf: GE, index: GE, loop: GE, interp: GE) extends UGenSource.MultiOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, index.expand, loop.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("BufRd", rate, IIdxSeq.fill(numChannels)(rate), _args)
}
object BufWr {
   def kr(in: GE, buf: GE, index: GE = 0.0f, loop: GE = 1.0f) = apply(control, in, buf, index, loop)
   def ar(in: GE, buf: GE, index: GE = 0.0f, loop: GE = 1.0f) = apply(audio, in, buf, index, loop)
}
final case class BufWr(rate: Rate, in: GE, buf: GE, index: GE, loop: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, index.expand, loop.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufWr", rate, _args)
}
object Pitch {
   def kr(in: GE, initFreq: GE = 440.0f, minFreq: GE = 60.0f, maxFreq: GE = 4000.0f, execFreq: GE = 100.0f, binsPerOct: GE = 16.0f, median: GE = 1.0f, ampThresh: GE = 0.01f, peakThresh: GE = 0.5f, downSample: GE = 1.0f) = apply(control, in, initFreq, minFreq, maxFreq, execFreq, binsPerOct, median, ampThresh, peakThresh, downSample)
}
final case class Pitch(rate: Rate, in: GE, initFreq: GE, minFreq: GE, maxFreq: GE, execFreq: GE, binsPerOct: GE, median: GE, ampThresh: GE, peakThresh: GE, downSample: GE) extends UGenSource.MultiOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, initFreq.expand, minFreq.expand, maxFreq.expand, execFreq.expand, binsPerOct.expand, median.expand, ampThresh.expand, peakThresh.expand, downSample.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Pitch", rate, IIdxSeq.fill(2)(rate), _args)
}
object BufDelayN {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f) = apply(audio, buf, in, delayTime)
}
final case class BufDelayN(rate: Rate, buf: GE, in: GE, delayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufDelayN", rate, _args)
}
object BufDelayL {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f) = apply(audio, buf, in, delayTime)
}
final case class BufDelayL(rate: Rate, buf: GE, in: GE, delayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufDelayL", rate, _args)
}
object BufDelayC {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f) = apply(audio, buf, in, delayTime)
}
final case class BufDelayC(rate: Rate, buf: GE, in: GE, delayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufDelayC", rate, _args)
}
object BufCombN {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
final case class BufCombN(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufCombN", rate, _args)
}
object BufCombL {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
final case class BufCombL(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufCombL", rate, _args)
}
object BufCombC {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
final case class BufCombC(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufCombC", rate, _args)
}
object BufAllpassN {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
final case class BufAllpassN(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufAllpassN", rate, _args)
}
object BufAllpassL {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
final case class BufAllpassL(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufAllpassL", rate, _args)
}
object BufAllpassC {
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
final case class BufAllpassC(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BufAllpassC", rate, _args)
}
object DelayN {
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
}
final case class DelayN(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DelayN", rate, _args)
}
object DelayL {
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
}
final case class DelayL(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DelayL", rate, _args)
}
object DelayC {
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
}
final case class DelayC(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DelayC", rate, _args)
}
object CombN {
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
final case class CombN(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("CombN", rate, _args)
}
object CombL {
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
final case class CombL(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("CombL", rate, _args)
}
object CombC {
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
final case class CombC(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("CombC", rate, _args)
}
object AllpassN {
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
}
final case class AllpassN(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("AllpassN", rate, _args)
}
object AllpassL {
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
}
final case class AllpassL(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("AllpassL", rate, _args)
}
object AllpassC {
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
}
final case class AllpassC(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("AllpassC", rate, _args)
}
object PitchShift {
   def ar(in: GE, winSize: GE = 0.2f, pitchRatio: GE = 1.0f, pitchDispersion: GE = 0.0f, timeDispersion: GE = 0.0f) = apply(in, winSize, pitchRatio, pitchDispersion, timeDispersion)
}
final case class PitchShift(in: GE, winSize: GE, pitchRatio: GE, pitchDispersion: GE, timeDispersion: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, winSize.expand, pitchRatio.expand, pitchDispersion.expand, timeDispersion.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("PitchShift", audio, _args)
}
object TGrains {
   def ar(numChannels: Int, trig: GE, buf: GE, speed: GE = 1.0f, centerPos: GE = 0.0f, dur: GE = 0.1f, pan: GE = 0.0f, amp: GE = 0.1f, interp: GE = 4.0f) = apply(numChannels, trig, buf, speed, centerPos, dur, pan, amp, interp)
}
final case class TGrains(numChannels: Int, trig: GE, buf: GE, speed: GE, centerPos: GE, dur: GE, pan: GE, amp: GE, interp: GE) extends UGenSource.MultiOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, buf.expand, speed.expand, centerPos.expand, dur.expand, pan.expand, amp.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("TGrains", audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object ScopeOut {
   def ar(buf: GE, in: GE) = apply(audio, buf, in)
   def kr(buf: GE, in: GE) = apply(control, buf, in)
}
final case class ScopeOut(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("ScopeOut", rate, _args)
}
object Pluck {
   def ar(in: GE, trig: GE = 1.0f, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f, coef: GE = 0.5f) = apply(in, trig, maxDelayTime, delayTime, decayTime, coef)
}
final case class Pluck(in: GE, trig: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE, coef: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand, coef.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Pluck", audio, _args)
}
object DelTapWr {
   def ar(buf: GE, in: GE) = apply(audio, buf, in)
   def kr(buf: GE, in: GE) = apply(control, buf, in)
}
final case class DelTapWr(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DelTapWr", rate, _args)
}
object DelTapRd {
   def ar(buf: GE, phase: GE, delayTime: GE, interp: GE = 1.0f) = apply(audio, buf, phase, delayTime, interp)
   def kr(buf: GE, phase: GE, delayTime: GE, interp: GE = 1.0f) = apply(control, buf, phase, delayTime, interp)
}
final case class DelTapRd(rate: Rate, buf: GE, phase: GE, delayTime: GE, interp: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, phase.expand, delayTime.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DelTapRd", rate, _args)
}