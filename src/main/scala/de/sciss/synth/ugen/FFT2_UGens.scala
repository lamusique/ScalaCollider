/*
 * FFT2_UGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Sat Oct 08 23:33:09 BST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
/**
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
object Convolution {
   def ar(in: GE, kernel: GE, frameSize: GE) = apply(audio, in, kernel, frameSize)
}
/**
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
final case class Convolution(rate: Rate, in: GE, kernel: GE, frameSize: GE) extends UGenSource.SingleOut("Convolution") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, kernel.expand, frameSize.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A frequency-domain convolution UGen using a fixed kernel which can be updated
 * by a trigger signal. The delay caused by the convolution when the kernel is a dirac impulse
 * is equal to `frameSize - (controlBlockSize + 1)` (measured august 2010), so for a frameSize
 * of 2048 and a controlBlockSize of 64, this is 1983 sample frames.
 * 
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
object Convolution2 {
   
   /**
    * @param in              the realtime input to be convolved
    * @param kernel          buffer identifier for the fixed kernel, which may be modulated in combination with the trigger
    * @param trig            updates the kernel on a change from non-positive to positive (<= 0 to >0)
    * @param frameSize       size of the kernel. this must be a power of two. the FFT calculated internally
    *                        by the UGen has a size of twice this value. The maximum allowed frameSize
    *                        is 65536(?).
    */
   def ar(in: GE, kernel: GE, trig: GE = 1.0f, frameSize: GE) = apply(audio, in, kernel, trig, frameSize)
}
/**
 * A frequency-domain convolution UGen using a fixed kernel which can be updated
 * by a trigger signal. The delay caused by the convolution when the kernel is a dirac impulse
 * is equal to `frameSize - (controlBlockSize + 1)` (measured august 2010), so for a frameSize
 * of 2048 and a controlBlockSize of 64, this is 1983 sample frames.
 * 
 * @param in              the realtime input to be convolved
 * @param kernel          buffer identifier for the fixed kernel, which may be modulated in combination with the trigger
 * @param trig            updates the kernel on a change from non-positive to positive (<= 0 to >0)
 * @param frameSize       size of the kernel. this must be a power of two. the FFT calculated internally
 *                        by the UGen has a size of twice this value. The maximum allowed frameSize
 *                        is 65536(?).
 * 
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
final case class Convolution2(rate: Rate, in: GE, kernel: GE, trig: GE, frameSize: GE) extends UGenSource.SingleOut("Convolution2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, kernel.expand, trig.expand, frameSize.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
object Convolution2L {
   def ar(in: GE, kernel: GE, trig: GE = 1.0f, frameSize: GE, fadePeriods: GE = 1.0f) = apply(audio, in, kernel, trig, frameSize, fadePeriods)
}
/**
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
final case class Convolution2L(rate: Rate, in: GE, kernel: GE, trig: GE, frameSize: GE, fadePeriods: GE) extends UGenSource.SingleOut("Convolution2L") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, kernel.expand, trig.expand, frameSize.expand, fadePeriods.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A frequency domain stereo convolution UGen, capable of performing linear crossfades between kernel updates.
 * When receiving a trigger, there is a linear crossfade between the old kernel the new buffer contents.
 * It operates similar to Convolution2L, however uses two buffers and outputs a stereo signal, resulting
 * in better CPU usage than two discrete instances of Convolution2L as this way one FFT transformation per period
 * is saved.
 * 
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 */
object StereoConvolution2L {
   
   /**
    * @param in              the realtime input to be convolved
    * @param kernelL         buffer identifier for the left channel's fixed kernel, which may be modulated in combination
    *                        with the trigger
    * @param kernelR         buffer identifier for the right channel's fixed kernel, which may be modulated in combination
    *                        with the trigger
    * @param trig            updates the kernel on a change from non-positive to positive (<= 0 to >0), and starts a new
    *                        crossfade from the previous kernel to the new one over the given amount of periods.
    * @param frameSize       size of each kernel. this must be a power of two. the FFT calculated internally
    *                        by the UGen has a size of twice this value. The maximum allowed frameSize
    *                        is 65536(?).
    * @param fadePeriods     The number of periods over which a crossfade is performed. This must be an integer
    */
   def ar(in: GE, kernelL: GE, kernelR: GE, trig: GE = 1.0f, frameSize: GE, fadePeriods: GE = 1.0f) = apply(audio, in, kernelL, kernelR, trig, frameSize, fadePeriods)
}
/**
 * A frequency domain stereo convolution UGen, capable of performing linear crossfades between kernel updates.
 * When receiving a trigger, there is a linear crossfade between the old kernel the new buffer contents.
 * It operates similar to Convolution2L, however uses two buffers and outputs a stereo signal, resulting
 * in better CPU usage than two discrete instances of Convolution2L as this way one FFT transformation per period
 * is saved.
 * 
 * @param in              the realtime input to be convolved
 * @param kernelL         buffer identifier for the left channel's fixed kernel, which may be modulated in combination
 *                        with the trigger
 * @param kernelR         buffer identifier for the right channel's fixed kernel, which may be modulated in combination
 *                        with the trigger
 * @param trig            updates the kernel on a change from non-positive to positive (<= 0 to >0), and starts a new
 *                        crossfade from the previous kernel to the new one over the given amount of periods.
 * @param frameSize       size of each kernel. this must be a power of two. the FFT calculated internally
 *                        by the UGen has a size of twice this value. The maximum allowed frameSize
 *                        is 65536(?).
 * @param fadePeriods     The number of periods over which a crossfade is performed. This must be an integer
 * 
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 */
final case class StereoConvolution2L(rate: Rate, in: GE, kernelL: GE, kernelR: GE, trig: GE, frameSize: GE, fadePeriods: GE) extends UGenSource.MultiOut("StereoConvolution2L") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, kernelL.expand, kernelR.expand, trig.expand, frameSize.expand, fadePeriods.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(2)(rate), _args)
}
/**
 * A UGen for triggered convolution in the time domain.
 */
object Convolution3 {
   def ar(in: GE, kernel: GE, trig: GE = 1.0f, frameSize: GE) = apply(audio, in, kernel, trig, frameSize)
}
/**
 * A UGen for triggered convolution in the time domain.
 */
final case class Convolution3(rate: Rate, in: GE, kernel: GE, trig: GE, frameSize: GE) extends UGenSource.SingleOut("Convolution3") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, kernel.expand, trig.expand, frameSize.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
final case class PV_ConformalMap(chain: GE, real: GE = 0.0f, imag: GE = 0.0f) extends UGenSource.SingleOut("PV_ConformalMap") with ControlRated with WritesFFT {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chain.expand, real.expand, imag.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args)
}
object PV_JensenAndersen {
   def ar(chain: GE, propSC: GE = 0.25f, propHFE: GE = 0.25f, propHFC: GE = 0.25f, propSF: GE = 0.25f, thresh: GE = 1.0f, waitTime: GE = 0.04f) = apply(audio, chain, propSC, propHFE, propHFC, propSF, thresh, waitTime)
}
final case class PV_JensenAndersen(rate: Rate, chain: GE, propSC: GE, propHFE: GE, propHFC: GE, propSF: GE, thresh: GE, waitTime: GE) extends UGenSource.SingleOut("PV_JensenAndersen") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chain.expand, propSC.expand, propHFE.expand, propHFC.expand, propSF.expand, thresh.expand, waitTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object PV_HainsworthFoote {
   def ar(chain: GE, propH: GE = 0.0f, propF: GE = 0.0f, thresh: GE = 1.0f, waitTime: GE = 0.04f) = apply(audio, chain, propH, propF, thresh, waitTime)
}
final case class PV_HainsworthFoote(rate: Rate, chain: GE, propH: GE, propF: GE, thresh: GE, waitTime: GE) extends UGenSource.SingleOut("PV_HainsworthFoote") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chain.expand, propH.expand, propF.expand, thresh.expand, waitTime.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A UGen calculating the running sum of an input signal over a given number of samples.
 */
object RunningSum {
   
   /**
    * @param in              the input signal to sum up
    * @param length          the length of the sliding window over the input signal.
    *                        these are the number of audio sample-frames for audio-rate calculation,
    *                        or the number of blocks for control-rate calculation summed up.
    */
   def kr(in: GE, length: GE = 440.0f) = apply(control, in, length)
   /**
    * @param in              the input signal to sum up
    * @param length          the length of the sliding window over the input signal.
    *                        these are the number of audio sample-frames for audio-rate calculation,
    *                        or the number of blocks for control-rate calculation summed up.
    */
   def ar(in: GE, length: GE = 440.0f) = apply(audio, in, length)
}
/**
 * A UGen calculating the running sum of an input signal over a given number of samples.
 * 
 * @param in              the input signal to sum up
 * @param length          the length of the sliding window over the input signal.
 *                        these are the number of audio sample-frames for audio-rate calculation,
 *                        or the number of blocks for control-rate calculation summed up.
 */
final case class RunningSum(rate: MaybeRate, in: GE, length: GE) extends UGenSource.SingleOut("RunningSum") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}