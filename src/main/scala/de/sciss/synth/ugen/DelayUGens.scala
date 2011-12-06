/*
 * DelayUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Tue Dec 06 20:51:57 GMT 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
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
final case class ControlRate() extends UGenSource.SingleOut("ControlRate") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
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
final case class SampleRate() extends UGenSource.SingleOut("SampleRate") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
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
final case class SampleDur() extends UGenSource.SingleOut("SampleDur") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
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
final case class ControlDur() extends UGenSource.SingleOut("ControlDur") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
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
final case class SubsampleOffset() extends UGenSource.SingleOut("SubsampleOffset") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
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
final case class RadiansPerSample() extends UGenSource.SingleOut("RadiansPerSample") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
}
/**
 * Number of input busses.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
object NumInputBuses {
   def ir = apply()
}
/**
 * Number of input busses.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
final case class NumInputBuses() extends UGenSource.SingleOut("NumInputBuses") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
}
/**
 * Number of output busses.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
object NumOutputBuses {
   def ir = apply()
}
/**
 * Number of output busses.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
final case class NumOutputBuses() extends UGenSource.SingleOut("NumOutputBuses") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
}
/**
 * Number of audio busses.
 * 
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
object NumAudioBuses {
   def ir = apply()
}
/**
 * Number of audio busses.
 * 
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
final case class NumAudioBuses() extends UGenSource.SingleOut("NumAudioBuses") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
}
/**
 * Number of control busses.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
object NumControlBuses {
   def ir = apply()
}
/**
 * Number of control busses.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
final case class NumControlBuses() extends UGenSource.SingleOut("NumControlBuses") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
}
/**
 * Number of open buffers.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
object NumBuffers {
   def ir = apply()
}
/**
 * Number of open buffers.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 * @see [[de.sciss.synth.ugen.NumRunningSynths]]
 */
final case class NumBuffers() extends UGenSource.SingleOut("NumBuffers") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
}
/**
 * Number of currently running synths.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 */
object NumRunningSynths {
   def ir = apply()
}
/**
 * Number of currently running synths.
 * 
 * @see [[de.sciss.synth.ugen.NumAudioBuses]]
 * @see [[de.sciss.synth.ugen.NumControlBuses]]
 * @see [[de.sciss.synth.ugen.NumBuffers]]
 * @see [[de.sciss.synth.ugen.NumInputBuses]]
 * @see [[de.sciss.synth.ugen.NumOutputBuses]]
 */
final case class NumRunningSynths() extends UGenSource.SingleOut("NumRunningSynths") with ScalarRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, scalar, _args)
}
/**
 * Returns the buffer's current sample rate.
 * 
 * Returns the buffer's current sample rate.
 */
object BufSampleRate {
   
   /**
    * @param buf             Buffer index.
    */
   def ir(buf: GE) = apply(scalar, buf)
   /**
    * @param buf             Buffer index.
    */
   def kr(buf: GE) = apply(control, buf)
}
/**
 * Returns the buffer's current sample rate.
 * 
 * Returns the buffer's current sample rate.
 * 
 * @param buf             Buffer index.
 */
final case class BufSampleRate(rate: Rate, buf: GE) extends UGenSource.SingleOut("BufSampleRate") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Returns a ratio by which the playback of the buffer at the provided index is to be scaled relative to the current sample rate of the server.
 * {{{
 * buffer sample rate / server sample rate
 * }}}
 * 
 * Returns a ratio by which the playback of the buffer at the provided index is to be scaled relative to the current sample rate of the server.
 * {{{
 * buffer sample rate / server sample rate
 * }}}
 */
object BufRateScale {
   
   /**
    * @param buf             Buffer index.
    */
   def ir(buf: GE) = apply(scalar, buf)
   /**
    * @param buf             Buffer index.
    */
   def kr(buf: GE) = apply(control, buf)
}
/**
 * Returns a ratio by which the playback of the buffer at the provided index is to be scaled relative to the current sample rate of the server.
 * {{{
 * buffer sample rate / server sample rate
 * }}}
 * 
 * Returns a ratio by which the playback of the buffer at the provided index is to be scaled relative to the current sample rate of the server.
 * {{{
 * buffer sample rate / server sample rate
 * }}}
 * 
 * @param buf             Buffer index.
 */
final case class BufRateScale(rate: Rate, buf: GE) extends UGenSource.SingleOut("BufRateScale") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Returns the current number of allocated samples in the Buffer at the provided index. A sample is not the same as a frame (compare with [[ugen.BufFrames]] ); a frame includes the samples in each channel of the buffer. Only for a mono buffer are samples the same as frames.
 * {{{
 * samples = frames * numChannels
 * }}}
 * 
 * Returns the current number of allocated samples in the Buffer at the provided index. A sample is not the same as a frame (compare with [[ugen.BufFrames]] ); a frame includes the samples in each channel of the buffer. Only for a mono buffer are samples the same as frames.
 * {{{
 * samples = frames * numChannels
 * }}}
 */
object BufSamples {
   
   /**
    * @param buf             Buffer index.
    */
   def ir(buf: GE) = apply(scalar, buf)
   /**
    * @param buf             Buffer index.
    */
   def kr(buf: GE) = apply(control, buf)
}
/**
 * Returns the current number of allocated samples in the Buffer at the provided index. A sample is not the same as a frame (compare with [[ugen.BufFrames]] ); a frame includes the samples in each channel of the buffer. Only for a mono buffer are samples the same as frames.
 * {{{
 * samples = frames * numChannels
 * }}}
 * 
 * Returns the current number of allocated samples in the Buffer at the provided index. A sample is not the same as a frame (compare with [[ugen.BufFrames]] ); a frame includes the samples in each channel of the buffer. Only for a mono buffer are samples the same as frames.
 * {{{
 * samples = frames * numChannels
 * }}}
 * 
 * @param buf             Buffer index.
 */
final case class BufSamples(rate: Rate, buf: GE) extends UGenSource.SingleOut("BufSamples") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Returns the number of allocated frames of the buffer at the provided index.
 * 
 * Returns the number of allocated frames of the buffer at the provided index.
 */
object BufFrames {
   
   /**
    * @param buf             Buffer index.
    */
   def ir(buf: GE) = apply(scalar, buf)
   /**
    * @param buf             Buffer index.
    */
   def kr(buf: GE) = apply(control, buf)
}
/**
 * Returns the number of allocated frames of the buffer at the provided index.
 * 
 * Returns the number of allocated frames of the buffer at the provided index.
 * 
 * @param buf             Buffer index.
 */
final case class BufFrames(rate: Rate, buf: GE) extends UGenSource.SingleOut("BufFrames") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Returns the current number of channels of the buffer at the provided index.
 * 
 * Returns the current number of channels of the buffer at the provided index.
 */
object BufChannels {
   
   /**
    * @param buf             Buffer index.
    */
   def ir(buf: GE) = apply(scalar, buf)
   /**
    * @param buf             Buffer index.
    */
   def kr(buf: GE) = apply(control, buf)
}
/**
 * Returns the current number of channels of the buffer at the provided index.
 * 
 * Returns the current number of channels of the buffer at the provided index.
 * 
 * @param buf             Buffer index.
 */
final case class BufChannels(rate: Rate, buf: GE) extends UGenSource.SingleOut("BufChannels") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Returns the current duration of the buffer at the provided index.
 * 
 * Returns the current duration of the buffer at the provided index.
 */
object BufDur {
   
   /**
    * @param buf             Buffer index.
    */
   def ir(buf: GE) = apply(scalar, buf)
   /**
    * @param buf             Buffer index.
    */
   def kr(buf: GE) = apply(control, buf)
}
/**
 * Returns the current duration of the buffer at the provided index.
 * 
 * Returns the current duration of the buffer at the provided index.
 * 
 * @param buf             Buffer index.
 */
final case class BufDur(rate: Rate, buf: GE) extends UGenSource.SingleOut("BufDur") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
final case class PlayBuf(rate: Rate, numChannels: Int, buf: GE, speed: GE, trig: GE, startPos: GE, loop: GE, doneAction: GE) extends UGenSource.MultiOut("PlayBuf") with HasSideEffect with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, speed.expand, trig.expand, startPos.expand, loop.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(numChannels)(rate), _args, false, true)
}
/**
 * Records input into a Buffer.
 * If recLevel is 1.0 and preLevel is 0.0 then the new input overwrites the old data. If they are both 1.0 then the new data is added to the existing data. (Any other settings are also valid.)
 * 
 * Records input into a Buffer.
 * If recLevel is 1.0 and preLevel is 0.0 then the new input overwrites the old data. If they are both 1.0 then the new data is added to the existing data. (Any other settings are also valid.)
 */
object RecordBuf {
   def kr(in: GE, buf: GE, offset: GE = 0.0f, recLevel: GE = 1.0f, preLevel: GE = 0.0f, run: GE = 1.0f, loop: GE = 1.0f, trig: GE = 1.0f, doneAction: GE = doNothing) = apply(control, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
   def ar(in: GE, buf: GE, offset: GE = 0.0f, recLevel: GE = 1.0f, preLevel: GE = 0.0f, run: GE = 1.0f, loop: GE = 1.0f, trig: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, in, buf, offset, recLevel, preLevel, run, loop, trig, doneAction)
}
/**
 * Records input into a Buffer.
 * If recLevel is 1.0 and preLevel is 0.0 then the new input overwrites the old data. If they are both 1.0 then the new data is added to the existing data. (Any other settings are also valid.)
 * 
 * Records input into a Buffer.
 * If recLevel is 1.0 and preLevel is 0.0 then the new input overwrites the old data. If they are both 1.0 then the new data is added to the existing data. (Any other settings are also valid.)
 */
final case class RecordBuf(rate: Rate, in: GE, buf: GE, offset: GE, recLevel: GE, preLevel: GE, run: GE, loop: GE, trig: GE, doneAction: GE) extends UGenSource.SingleOut("RecordBuf") with WritesBuffer with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, offset.expand, recLevel.expand, preLevel.expand, run.expand, loop.expand, trig.expand, doneAction.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
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
final case class BufRd(rate: Rate, numChannels: Int, buf: GE, index: GE, loop: GE, interp: GE) extends UGenSource.MultiOut("BufRd") with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, index.expand, loop.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(numChannels)(rate), _args)
}
object BufWr {
   def kr(in: GE, buf: GE, index: GE = 0.0f, loop: GE = 1.0f) = apply(control, in, buf, index, loop)
   def ar(in: GE, buf: GE, index: GE = 0.0f, loop: GE = 1.0f) = apply(audio, in, buf, index, loop)
}
final case class BufWr(rate: Rate, in: GE, buf: GE, index: GE, loop: GE) extends UGenSource.SingleOut("BufWr") with WritesBuffer with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, index.expand, loop.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * An autocorrelation based pitch following UGen. It is more accurate than `ZeroCrossing`, but
 * more also more CPU costly. For most purposes the default settings can be used and only `in`
 * needs to be supplied.
 * 
 * The UGen has two outputs: The first output is the frequency estimate in Hertz, the second
 * output is a toggle `hasFreq`, which tells whether a pitch was found (1) or not (0). If
 * the `clarify` argument is used, `hasFreq` has more fine grained information.
 * 
 * The pitch follower executes periodically at the rate specified by `execFreq` in cps.
 * First it detects whether the input peak to peak amplitude is above the `ampThresh`.
 * If it is not then no pitch estimation is performed, the `hasFreq` output is set to zero
 * and the `freq` output is held at its previous value. Otherwise, the autocorrelation is
 * calculated, and the first peak after the peak around the lag of zero that is
 * above `peakThresh` times the amplitude of the peak at lag zero is reported.
 */
object Pitch {
   
   /**
    * @param in              The signal to be analyzed.
    * @param initFreq        The initial value of the `freq` output, until the first valid pitch is found.
    * @param minFreq         The minimum frequency in Hertz to be considered for reporting.
    *                        (This parameter is scalar only?)
    * @param maxFreq         The maximum frequency in Hertz to be considered for reporting.
    *                        (This parameter is scalar only?)
    * @param execFreq        The frequency at which the pitch is estimated. This will be
    *                        automatically clipped to be between `minFreq` and `maxFreq`.
    *                        This parameter is scalar only.
    * @param binsPerOct      A value which guides the search for the peak frequency in the first
    *                        coarse step. Its setting does *not* affect the final pitch resolution;
    *                        setting it larger will cause the coarse search to take longer, and setting
    *                        it smaller will cause the fine search to take longer.
    *                        This parameter is scalar only.
    * @param median          This specifies the length of a median filter applied to the frequency output
    *                        estimation. With the default value of `1` the filter is defeated. Median filtering
    *                        can help eliminating single spikes and jitter. This will however add latency to
    *                        the output.
    *                        This parameter is scalar only.
    * @param ampThresh       The minimum amplitude threshold above which the pitch follower
    *                        operates. An input signal below this threshold is not analyzed.
    *                        (This parameter is scalar only?)
    * @param peakThresh      This is a threshold used to find the first peak in the autocorrelation signal which
    *                        gives the reported frequency. It is a factor of the energy of the signal
    *                        (autocorrelation coefficient at zero). Set this value higher (e.g. to `1`) to
    *                        eliminate false frequencies corresponding to overtones.
    *                        (This parameter is scalar only?)
    * @param downSample      An integer factor by which the input signal is down sampled to reduce CPU overhead.
    *                        This will also reduce the pitch resolution. The default value of `1` means that
    *                        the input signal is not down sampled.
    *                        This parameter is scalar only.
    * @param clarity         If the `clarity` argument is greater than zero (it is zero by default) then the `hasFreq`
    *                        output is given additional detail. Rather than simply being 1 when a pitch is detected,
    *                        it is a "clarity" measure in the range between zero and one. (Technically, it's the height
    *                        of the autocorrelation peak normalised by the height of the zero-lag peak.) It therefore
    *                        gives a kind of measure of "purity" of the pitched signal.
    *                        This parameter is scalar only.
    */
   def kr(in: GE, initFreq: GE = 440.0f, minFreq: GE = 60.0f, maxFreq: GE = 4000.0f, execFreq: GE = 100.0f, binsPerOct: GE = 16.0f, median: GE = 1.0f, ampThresh: GE = 0.01f, peakThresh: GE = 0.5f, downSample: GE = 1.0f, clarity: GE = 0.0f) = apply(control, in, initFreq, minFreq, maxFreq, execFreq, binsPerOct, median, ampThresh, peakThresh, downSample, clarity)
}
/**
 * An autocorrelation based pitch following UGen. It is more accurate than `ZeroCrossing`, but
 * more also more CPU costly. For most purposes the default settings can be used and only `in`
 * needs to be supplied.
 * 
 * The UGen has two outputs: The first output is the frequency estimate in Hertz, the second
 * output is a toggle `hasFreq`, which tells whether a pitch was found (1) or not (0). If
 * the `clarify` argument is used, `hasFreq` has more fine grained information.
 * 
 * The pitch follower executes periodically at the rate specified by `execFreq` in cps.
 * First it detects whether the input peak to peak amplitude is above the `ampThresh`.
 * If it is not then no pitch estimation is performed, the `hasFreq` output is set to zero
 * and the `freq` output is held at its previous value. Otherwise, the autocorrelation is
 * calculated, and the first peak after the peak around the lag of zero that is
 * above `peakThresh` times the amplitude of the peak at lag zero is reported.
 * 
 * @param in              The signal to be analyzed.
 * @param initFreq        The initial value of the `freq` output, until the first valid pitch is found.
 * @param minFreq         The minimum frequency in Hertz to be considered for reporting.
 *                        (This parameter is scalar only?)
 * @param maxFreq         The maximum frequency in Hertz to be considered for reporting.
 *                        (This parameter is scalar only?)
 * @param execFreq        The frequency at which the pitch is estimated. This will be
 *                        automatically clipped to be between `minFreq` and `maxFreq`.
 *                        This parameter is scalar only.
 * @param binsPerOct      A value which guides the search for the peak frequency in the first
 *                        coarse step. Its setting does *not* affect the final pitch resolution;
 *                        setting it larger will cause the coarse search to take longer, and setting
 *                        it smaller will cause the fine search to take longer.
 *                        This parameter is scalar only.
 * @param median          This specifies the length of a median filter applied to the frequency output
 *                        estimation. With the default value of `1` the filter is defeated. Median filtering
 *                        can help eliminating single spikes and jitter. This will however add latency to
 *                        the output.
 *                        This parameter is scalar only.
 * @param ampThresh       The minimum amplitude threshold above which the pitch follower
 *                        operates. An input signal below this threshold is not analyzed.
 *                        (This parameter is scalar only?)
 * @param peakThresh      This is a threshold used to find the first peak in the autocorrelation signal which
 *                        gives the reported frequency. It is a factor of the energy of the signal
 *                        (autocorrelation coefficient at zero). Set this value higher (e.g. to `1`) to
 *                        eliminate false frequencies corresponding to overtones.
 *                        (This parameter is scalar only?)
 * @param downSample      An integer factor by which the input signal is down sampled to reduce CPU overhead.
 *                        This will also reduce the pitch resolution. The default value of `1` means that
 *                        the input signal is not down sampled.
 *                        This parameter is scalar only.
 * @param clarity         If the `clarity` argument is greater than zero (it is zero by default) then the `hasFreq`
 *                        output is given additional detail. Rather than simply being 1 when a pitch is detected,
 *                        it is a "clarity" measure in the range between zero and one. (Technically, it's the height
 *                        of the autocorrelation peak normalised by the height of the zero-lag peak.) It therefore
 *                        gives a kind of measure of "purity" of the pitched signal.
 *                        This parameter is scalar only.
 */
final case class Pitch(rate: Rate, in: GE, initFreq: GE, minFreq: GE, maxFreq: GE, execFreq: GE, binsPerOct: GE, median: GE, ampThresh: GE, peakThresh: GE, downSample: GE, clarity: GE) extends UGenSource.MultiOut("Pitch") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, initFreq.expand, minFreq.expand, maxFreq.expand, execFreq.expand, binsPerOct.expand, median.expand, ampThresh.expand, peakThresh.expand, downSample.expand, clarity.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(2)(rate), _args)
}
/**
 * Simple delay line with no interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufDelayL]]
 * @see [[de.sciss.synth.ugen.BufDelayC]]
 */
object BufDelayN {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f) = apply(audio, buf, in, delayTime)
}
/**
 * Simple delay line with no interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.BufDelayL]]
 * @see [[de.sciss.synth.ugen.BufDelayC]]
 */
final case class BufDelayN(rate: Rate, buf: GE, in: GE, delayTime: GE) extends UGenSource.SingleOut("BufDelayN") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * Simple delay line with linear interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufDelayN]]
 * @see [[de.sciss.synth.ugen.BufDelayC]]
 */
object BufDelayL {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f) = apply(audio, buf, in, delayTime)
}
/**
 * Simple delay line with linear interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.BufDelayN]]
 * @see [[de.sciss.synth.ugen.BufDelayC]]
 */
final case class BufDelayL(rate: Rate, buf: GE, in: GE, delayTime: GE) extends UGenSource.SingleOut("BufDelayL") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * Simple delay line with cubic interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufDelayN]]
 * @see [[de.sciss.synth.ugen.BufDelayL]]
 */
object BufDelayC {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f) = apply(audio, buf, in, delayTime)
}
/**
 * Simple delay line with cubic interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.BufDelayN]]
 * @see [[de.sciss.synth.ugen.BufDelayL]]
 */
final case class BufDelayC(rate: Rate, buf: GE, in: GE, delayTime: GE) extends UGenSource.SingleOut("BufDelayC") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * Comb delay line with no interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufCombL]]
 * @see [[de.sciss.synth.ugen.BufCombC]]
 */
object BufCombN {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
/**
 * Comb delay line with no interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.BufCombL]]
 * @see [[de.sciss.synth.ugen.BufCombC]]
 */
final case class BufCombN(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("BufCombN") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * Comb delay line with linear interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufCombN]]
 * @see [[de.sciss.synth.ugen.BufCombC]]
 */
object BufCombL {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
/**
 * Comb delay line with linear interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.BufCombN]]
 * @see [[de.sciss.synth.ugen.BufCombC]]
 */
final case class BufCombL(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("BufCombL") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * Comb delay line with cubic interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufCombN]]
 * @see [[de.sciss.synth.ugen.BufCombL]]
 */
object BufCombC {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
/**
 * Comb delay line with cubic interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.BufCombN]]
 * @see [[de.sciss.synth.ugen.BufCombL]]
 */
final case class BufCombC(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("BufCombC") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * All pass delay line with no interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufAllpassL]]
 * @see [[de.sciss.synth.ugen.BufAllpassC]]
 */
object BufAllpassN {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
/**
 * All pass delay line with no interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.BufAllpassL]]
 * @see [[de.sciss.synth.ugen.BufAllpassC]]
 */
final case class BufAllpassN(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("BufAllpassN") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * All pass delay line with linear interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufAllpassN]]
 * @see [[de.sciss.synth.ugen.BufAllpassC]]
 */
object BufAllpassL {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
/**
 * All pass delay line with linear interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.BufAllpassN]]
 * @see [[de.sciss.synth.ugen.BufAllpassC]]
 */
final case class BufAllpassL(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("BufAllpassL") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * All pass delay line with cubic interpolation which uses a buffer for its internal memory.
 * 
 * @see [[de.sciss.synth.ugen.BufAllpassN]]
 * @see [[de.sciss.synth.ugen.BufAllpassL]]
 */
object BufAllpassC {
   
   /**
    * @param buf             Buffer index.
    * @param in              The input signal.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(buf: GE, in: GE, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, buf, in, delayTime, decayTime)
}
/**
 * All pass delay line with cubic interpolation which uses a buffer for its internal memory.
 * 
 * @param buf             Buffer index.
 * @param in              The input signal.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.BufAllpassN]]
 * @see [[de.sciss.synth.ugen.BufAllpassL]]
 */
final case class BufAllpassC(rate: Rate, buf: GE, in: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("BufAllpassC") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * Simple delay line with no interpolation.
 * 
 * @see [[de.sciss.synth.ugen.DelayL]]
 * @see [[de.sciss.synth.ugen.DelayC]]
 */
object DelayN {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
}
/**
 * Simple delay line with no interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.DelayL]]
 * @see [[de.sciss.synth.ugen.DelayC]]
 */
final case class DelayN(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE) extends UGenSource.SingleOut("DelayN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Simple delay line with linear interpolation.
 * 
 * @see [[de.sciss.synth.ugen.DelayN]]
 * @see [[de.sciss.synth.ugen.DelayC]]
 */
object DelayL {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
}
/**
 * Simple delay line with linear interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.DelayN]]
 * @see [[de.sciss.synth.ugen.DelayC]]
 */
final case class DelayL(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE) extends UGenSource.SingleOut("DelayL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Simple delay line with cubic interpolation.
 * 
 * @see [[de.sciss.synth.ugen.DelayN]]
 * @see [[de.sciss.synth.ugen.DelayL]]
 */
object DelayC {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(control, in, maxDelayTime, delayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f) = apply(audio, in, maxDelayTime, delayTime)
}
/**
 * Simple delay line with cubic interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.DelayN]]
 * @see [[de.sciss.synth.ugen.DelayL]]
 */
final case class DelayC(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE) extends UGenSource.SingleOut("DelayC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Comb delay line with no interpolation.
 * 
 * @see [[de.sciss.synth.ugen.CombL]]
 * @see [[de.sciss.synth.ugen.CombC]]
 */
object CombN {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
/**
 * Comb delay line with no interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.CombL]]
 * @see [[de.sciss.synth.ugen.CombC]]
 */
final case class CombN(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("CombN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Comb delay line with linear interpolation.
 * 
 * @see [[de.sciss.synth.ugen.CombN]]
 * @see [[de.sciss.synth.ugen.CombC]]
 */
object CombL {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
/**
 * Comb delay line with linear interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.CombN]]
 * @see [[de.sciss.synth.ugen.CombC]]
 */
final case class CombL(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("CombL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * Comb delay line with cubic interpolation.
 * 
 * @see [[de.sciss.synth.ugen.CombN]]
 * @see [[de.sciss.synth.ugen.CombL]]
 */
object CombC {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
/**
 * Comb delay line with cubic interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.CombN]]
 * @see [[de.sciss.synth.ugen.CombL]]
 */
final case class CombC(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("CombC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * All pass delay line with no interpolation.
 * 
 * @see [[de.sciss.synth.ugen.AllpassL]]
 * @see [[de.sciss.synth.ugen.AllpassC]]
 */
object AllpassN {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
/**
 * All pass delay line with no interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.AllpassL]]
 * @see [[de.sciss.synth.ugen.AllpassC]]
 */
final case class AllpassN(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("AllpassN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * All pass delay line with linear interpolation.
 * 
 * @see [[de.sciss.synth.ugen.AllpassN]]
 * @see [[de.sciss.synth.ugen.AllpassC]]
 */
object AllpassL {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
/**
 * All pass delay line with linear interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.AllpassN]]
 * @see [[de.sciss.synth.ugen.AllpassC]]
 */
final case class AllpassL(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("AllpassL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * All pass delay line with cubic interpolation.
 * 
 * @see [[de.sciss.synth.ugen.AllpassN]]
 * @see [[de.sciss.synth.ugen.AllpassL]]
 */
object AllpassC {
   
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def ar(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(audio, in, maxDelayTime, delayTime, decayTime)
   /**
    * @param in              The input signal.
    * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
    *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
    */
   def kr(in: GE, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f) = apply(control, in, maxDelayTime, delayTime, decayTime)
}
/**
 * All pass delay line with cubic interpolation.
 * 
 * @param in              The input signal.
 * @param maxDelayTime    The maximum delay time in seconds. used to initialize the delay buffer size.
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. If this time is negative then the feedback
 *                        coefficient will be negative, thus emphasizing only odd harmonics at an octave lower.
 * 
 * @see [[de.sciss.synth.ugen.AllpassN]]
 * @see [[de.sciss.synth.ugen.AllpassL]]
 */
final case class AllpassC(rate: Rate, in: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE) extends UGenSource.SingleOut("AllpassC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A time domain granular pitch shifter. Grains have a triangular amplitude
 * envelope and an overlap of 4:1.
 */
object PitchShift {
   
   /**
    * @param in              The input signal.
    * @param winSize         The size of the grain window in seconds. This value cannot be modulated.
    * @param pitchRatio      The ratio of the pitch shift. Must be from 0 to 4.
    * @param pitchDispersio  The maximum random deviation of the pitch from the pitchRatio .
    * @param timeDispersion  A random offset of from zero to timeDispersion seconds is added to the delay of each grain.
    *                        Use of some dispersion can alleviate a hard comb filter effect due to uniform grain placement.
    *                        It can also be an effect in itself. timeDispersion can be no larger than windowSize .
    */
   def ar(in: GE, winSize: GE = 0.2f, pitchRatio: GE = 1.0f, pitchDispersion: GE = 0.0f, timeDispersion: GE = 0.0f) = apply(in, winSize, pitchRatio, pitchDispersion, timeDispersion)
}
/**
 * A time domain granular pitch shifter. Grains have a triangular amplitude
 * envelope and an overlap of 4:1.
 * 
 * @param in              The input signal.
 * @param winSize         The size of the grain window in seconds. This value cannot be modulated.
 * @param pitchRatio      The ratio of the pitch shift. Must be from 0 to 4.
 * @param pitchDispersio  The maximum random deviation of the pitch from the pitchRatio .
 * @param timeDispersion  A random offset of from zero to timeDispersion seconds is added to the delay of each grain.
 *                        Use of some dispersion can alleviate a hard comb filter effect due to uniform grain placement.
 *                        It can also be an effect in itself. timeDispersion can be no larger than windowSize .
 */
final case class PitchShift(in: GE, winSize: GE, pitchRatio: GE, pitchDispersion: GE, timeDispersion: GE) extends UGenSource.SingleOut("PitchShift") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, winSize.expand, pitchRatio.expand, pitchDispersion.expand, timeDispersion.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
}
/**
 * Triggers generate grains from a buffer. Each grain has a Hanning envelope
 * {{{
 * (sin2(x) for x from 0 to π)
 * }}}
 * and is panned between two channels of multiple outputs.
 */
object TGrains {
   
   /**
    * @param numChannels     Number of output channels.
    * @param trig            At each trigger, the following arguments are sampled and used as the arguments of a new grain.
    *                        A trigger occurs when a signal changes from non-positive to positive value.
    *                        If the trigger is audio rate then the grains will start with sample accuracy.
    * @param buf             The buffer index. It must be a one channel (mono) buffer.
    * @param speed           1.0 is normal, 2.0 is one octave up, 0.5 is one octave down -1.0 is backwards normal rate… etc.
    * @param centerPos       The position in the buffer in seconds at which the grain envelope will reach maximum amplitude.
    * @param dur             Duration of the grain in seconds.
    * @param pan             A value from -1 to 1. Determines where to pan the output in the same manner as [[ugen.PanAz]].
    * @param amp             Amplitude of the grain.
    * @param interp          1, 2, or 4. Determines whether the grain uses (1) no interpolation, (2) linear interpolation,
    *                        or (4) cubic interpolation.
    */
   def ar(numChannels: Int, trig: GE, buf: GE, speed: GE = 1.0f, centerPos: GE = 0.0f, dur: GE = 0.1f, pan: GE = 0.0f, amp: GE = 0.1f, interp: GE = 4.0f) = apply(numChannels, trig, buf, speed, centerPos, dur, pan, amp, interp)
}
/**
 * Triggers generate grains from a buffer. Each grain has a Hanning envelope
 * {{{
 * (sin2(x) for x from 0 to π)
 * }}}
 * and is panned between two channels of multiple outputs.
 * 
 * @param numChannels     Number of output channels.
 * @param trig            At each trigger, the following arguments are sampled and used as the arguments of a new grain.
 *                        A trigger occurs when a signal changes from non-positive to positive value.
 *                        If the trigger is audio rate then the grains will start with sample accuracy.
 * @param buf             The buffer index. It must be a one channel (mono) buffer.
 * @param speed           1.0 is normal, 2.0 is one octave up, 0.5 is one octave down -1.0 is backwards normal rate… etc.
 * @param centerPos       The position in the buffer in seconds at which the grain envelope will reach maximum amplitude.
 * @param dur             Duration of the grain in seconds.
 * @param pan             A value from -1 to 1. Determines where to pan the output in the same manner as [[ugen.PanAz]].
 * @param amp             Amplitude of the grain.
 * @param interp          1, 2, or 4. Determines whether the grain uses (1) no interpolation, (2) linear interpolation,
 *                        or (4) cubic interpolation.
 */
final case class TGrains(numChannels: Int, trig: GE, buf: GE, speed: GE, centerPos: GE, dur: GE, pan: GE, amp: GE, interp: GE) extends UGenSource.MultiOut("TGrains") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, buf.expand, speed.expand, centerPos.expand, dur.expand, pan.expand, amp.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object ScopeOut {
   def ar(buf: GE, in: GE) = apply(audio, buf, in)
   def kr(buf: GE, in: GE) = apply(control, buf, in)
}
final case class ScopeOut(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut("ScopeOut") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * A Karplus-Strong UGen.
 */
object Pluck {
   
   /**
    * @param in              An excitation signal.
    * @param trig            Upon a negative to positive transition, the excitation signal will be fed into the delay line.
    * @param maxDelayTime    Maximum delay time in seconds (initializes the internal delay buffer).
    * @param delayTime       Delay time in seconds.
    * @param decayTime       Time for the echoes to decay by 60 decibels. Negative times emphasize odd partials.
    * @param coef            the coef of the internal OnePole filter. Values should be between -1 and +1
    *                        (larger values will be unstable... so be careful!).
    */
   def ar(in: GE, trig: GE = 1.0f, maxDelayTime: GE = 0.2f, delayTime: GE = 0.2f, decayTime: GE = 1.0f, coef: GE = 0.5f) = apply(in, trig, maxDelayTime, delayTime, decayTime, coef)
}
/**
 * A Karplus-Strong UGen.
 * 
 * @param in              An excitation signal.
 * @param trig            Upon a negative to positive transition, the excitation signal will be fed into the delay line.
 * @param maxDelayTime    Maximum delay time in seconds (initializes the internal delay buffer).
 * @param delayTime       Delay time in seconds.
 * @param decayTime       Time for the echoes to decay by 60 decibels. Negative times emphasize odd partials.
 * @param coef            the coef of the internal OnePole filter. Values should be between -1 and +1
 *                        (larger values will be unstable... so be careful!).
 */
final case class Pluck(in: GE, trig: GE, maxDelayTime: GE, delayTime: GE, decayTime: GE, coef: GE) extends UGenSource.SingleOut("Pluck") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand, maxDelayTime.expand, delayTime.expand, decayTime.expand, coef.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
}
/**
 * Write to a buffer for a `DelTapRd` UGen
 * 
 * @see [[de.sciss.synth.ugen.DelTapRd]]
 */
object DelTapWr {
   
   /**
    * @param buf             The buffer to write signal into. Max delay time is based on buffer size.
    *                        @return phase - `DelTapWr outputs its current sample value for use in the phase`
    *                        argument in DelTapRd
    * @param in              The input signal.
    */
   def ar(buf: GE, in: GE) = apply(audio, buf, in)
   /**
    * @param buf             The buffer to write signal into. Max delay time is based on buffer size.
    *                        @return phase - `DelTapWr outputs its current sample value for use in the phase`
    *                        argument in DelTapRd
    * @param in              The input signal.
    */
   def kr(buf: GE, in: GE) = apply(control, buf, in)
}
/**
 * Write to a buffer for a `DelTapRd` UGen
 * 
 * @param buf             The buffer to write signal into. Max delay time is based on buffer size.
 *                        @return phase - `DelTapWr outputs its current sample value for use in the phase`
 *                        argument in DelTapRd
 * @param in              The input signal.
 * 
 * @see [[de.sciss.synth.ugen.DelTapRd]]
 */
final case class DelTapWr(rate: Rate, buf: GE, in: GE) extends UGenSource.SingleOut("DelTapWr") with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args, true, true)
}
/**
 * Tap a delay line from a `DelTapWr` UGen.
 * 
 * @see [[de.sciss.synth.ugen.DelTapWr]]
 */
object DelTapRd {
   
   /**
    * @param buf             Buffer where DelTapWr has written signal. Max delay time is based on buffer size.
    * @param phase           the current phase of the DelTapWr UGen. This is the output of DelTapWr.
    * @param delayTime       Delay time in seconds.
    * @param interp          The kind of interpolation to be used. 1 is none, 2 is linear, 4 is cubic..
    */
   def ar(buf: GE, phase: GE, delayTime: GE, interp: GE = 1.0f) = apply(audio, buf, phase, delayTime, interp)
   /**
    * @param buf             Buffer where DelTapWr has written signal. Max delay time is based on buffer size.
    * @param phase           the current phase of the DelTapWr UGen. This is the output of DelTapWr.
    * @param delayTime       Delay time in seconds.
    * @param interp          The kind of interpolation to be used. 1 is none, 2 is linear, 4 is cubic..
    */
   def kr(buf: GE, phase: GE, delayTime: GE, interp: GE = 1.0f) = apply(control, buf, phase, delayTime, interp)
}
/**
 * Tap a delay line from a `DelTapWr` UGen.
 * 
 * @param buf             Buffer where DelTapWr has written signal. Max delay time is based on buffer size.
 * @param phase           the current phase of the DelTapWr UGen. This is the output of DelTapWr.
 * @param delayTime       Delay time in seconds.
 * @param interp          The kind of interpolation to be used. 1 is none, 2 is linear, 4 is cubic..
 * 
 * @see [[de.sciss.synth.ugen.DelTapWr]]
 */
final case class DelTapRd(rate: Rate, buf: GE, phase: GE, delayTime: GE, interp: GE) extends UGenSource.SingleOut("DelTapRd") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, phase.expand, delayTime.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}