/*
 * FFT2_UGens.scala
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
/**
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
object Convolution {
   def ar(in: AnyGE, kernel: AnyGE, frameSize: AnyGE) = apply(audio, in, kernel, frameSize)
}
/**
 * @see [[de.sciss.synth.ugen.Convolution2L]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
case class Convolution(rate: Rate, in: AnyGE, kernel: AnyGE, frameSize: AnyGE) extends SingleOutUGenSource[ConvolutionUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _kernel: IIdxSeq[UGenIn] = kernel.expand
      val _frameSize: IIdxSeq[UGenIn] = frameSize.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_frameSize = _frameSize.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_frameSize)
      IIdxSeq.tabulate(_exp_)(i => ConvolutionUGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _frameSize(i.%(_sz_frameSize))))
   }
}
case class ConvolutionUGen(rate: Rate, in: UGenIn, kernel: UGenIn, frameSize: UGenIn) extends SingleOutUGen(IIdxSeq(in, kernel, frameSize))
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
def ar(in: AnyGE, kernel: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE) = apply(audio, in, kernel, trig, frameSize)
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
case class Convolution2(rate: Rate, in: AnyGE, kernel: AnyGE, trig: AnyGE, frameSize: AnyGE) extends SingleOutUGenSource[Convolution2UGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _kernel: IIdxSeq[UGenIn] = kernel.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _frameSize: IIdxSeq[UGenIn] = frameSize.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_trig = _trig.size
      val _sz_frameSize = _frameSize.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_trig, _sz_frameSize)
      IIdxSeq.tabulate(_exp_)(i => Convolution2UGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _trig(i.%(_sz_trig)), _frameSize(i.%(_sz_frameSize))))
   }
}
case class Convolution2UGen(rate: Rate, in: UGenIn, kernel: UGenIn, trig: UGenIn, frameSize: UGenIn) extends SingleOutUGen(IIdxSeq(in, kernel, trig, frameSize))
/**
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
object Convolution2L {
   def ar(in: AnyGE, kernel: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE, fadePeriods: AnyGE = 1.0f) = apply(audio, in, kernel, trig, frameSize, fadePeriods)
}
/**
 * @see [[de.sciss.synth.ugen.Convolution2]]
 * @see [[de.sciss.synth.ugen.Convolution3]]
 * @see [[de.sciss.synth.ugen.Convolution]]
 * @see [[de.sciss.synth.ugen.StereoConvolution2L]]
 */
case class Convolution2L(rate: Rate, in: AnyGE, kernel: AnyGE, trig: AnyGE, frameSize: AnyGE, fadePeriods: AnyGE) extends SingleOutUGenSource[Convolution2LUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _kernel: IIdxSeq[UGenIn] = kernel.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _frameSize: IIdxSeq[UGenIn] = frameSize.expand
      val _fadePeriods: IIdxSeq[UGenIn] = fadePeriods.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_trig = _trig.size
      val _sz_frameSize = _frameSize.size
      val _sz_fadePeriods = _fadePeriods.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_trig, _sz_frameSize, _sz_fadePeriods)
      IIdxSeq.tabulate(_exp_)(i => Convolution2LUGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _trig(i.%(_sz_trig)), _frameSize(i.%(_sz_frameSize)), _fadePeriods(i.%(_sz_fadePeriods))))
   }
}
case class Convolution2LUGen(rate: Rate, in: UGenIn, kernel: UGenIn, trig: UGenIn, frameSize: UGenIn, fadePeriods: UGenIn) extends SingleOutUGen(IIdxSeq(in, kernel, trig, frameSize, fadePeriods))
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
def ar(in: AnyGE, kernelL: AnyGE, kernelR: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE, fadePeriods: AnyGE = 1.0f) = apply(audio, in, kernelL, kernelR, trig, frameSize, fadePeriods)
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
case class StereoConvolution2L(rate: Rate, in: AnyGE, kernelL: AnyGE, kernelR: AnyGE, trig: AnyGE, frameSize: AnyGE, fadePeriods: AnyGE) extends MultiOutUGenSource[StereoConvolution2LUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _kernelL: IIdxSeq[UGenIn] = kernelL.expand
      val _kernelR: IIdxSeq[UGenIn] = kernelR.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _frameSize: IIdxSeq[UGenIn] = frameSize.expand
      val _fadePeriods: IIdxSeq[UGenIn] = fadePeriods.expand
      val _sz_in = _in.size
      val _sz_kernelL = _kernelL.size
      val _sz_kernelR = _kernelR.size
      val _sz_trig = _trig.size
      val _sz_frameSize = _frameSize.size
      val _sz_fadePeriods = _fadePeriods.size
      val _exp_ = maxInt(_sz_in, _sz_kernelL, _sz_kernelR, _sz_trig, _sz_frameSize, _sz_fadePeriods)
      IIdxSeq.tabulate(_exp_)(i => StereoConvolution2LUGen(rate, _in(i.%(_sz_in)), _kernelL(i.%(_sz_kernelL)), _kernelR(i.%(_sz_kernelR)), _trig(i.%(_sz_trig)), _frameSize(i.%(_sz_frameSize)), _fadePeriods(i.%(_sz_fadePeriods))))
   }
}
case class StereoConvolution2LUGen(rate: Rate, in: UGenIn, kernelL: UGenIn, kernelR: UGenIn, trig: UGenIn, frameSize: UGenIn, fadePeriods: UGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, kernelL, kernelR, trig, frameSize, fadePeriods))
/**
 * A UGen for triggered convolution in the time domain.
 */
object Convolution3 {
   def ar(in: AnyGE, kernel: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE) = apply(audio, in, kernel, trig, frameSize)
}
/**
 * A UGen for triggered convolution in the time domain.
 */
case class Convolution3(rate: Rate, in: AnyGE, kernel: AnyGE, trig: AnyGE, frameSize: AnyGE) extends SingleOutUGenSource[Convolution3UGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _kernel: IIdxSeq[UGenIn] = kernel.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _frameSize: IIdxSeq[UGenIn] = frameSize.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_trig = _trig.size
      val _sz_frameSize = _frameSize.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_trig, _sz_frameSize)
      IIdxSeq.tabulate(_exp_)(i => Convolution3UGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _trig(i.%(_sz_trig)), _frameSize(i.%(_sz_frameSize))))
   }
}
case class Convolution3UGen(rate: Rate, in: UGenIn, kernel: UGenIn, trig: UGenIn, frameSize: UGenIn) extends SingleOutUGen(IIdxSeq(in, kernel, trig, frameSize))
case class PV_ConformalMap(chain: AnyGE, real: AnyGE = 0.0f, imag: AnyGE = 0.0f) extends SingleOutUGenSource[PV_ConformalMapUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _real: IIdxSeq[UGenIn] = real.expand
      val _imag: IIdxSeq[UGenIn] = imag.expand
      val _sz_chain = _chain.size
      val _sz_real = _real.size
      val _sz_imag = _imag.size
      val _exp_ = maxInt(_sz_chain, _sz_real, _sz_imag)
      IIdxSeq.tabulate(_exp_)(i => PV_ConformalMapUGen(_chain(i.%(_sz_chain)), _real(i.%(_sz_real)), _imag(i.%(_sz_imag))))
   }
}
case class PV_ConformalMapUGen(chain: UGenIn, real: UGenIn, imag: UGenIn) extends SingleOutUGen(IIdxSeq(chain, real, imag)) with ControlRated with WritesFFT
object PV_JensenAndersen {
   def ar(chain: AnyGE, propSC: AnyGE = 0.25f, propHFE: AnyGE = 0.25f, propHFC: AnyGE = 0.25f, propSF: AnyGE = 0.25f, thresh: AnyGE = 1.0f, waitTime: AnyGE = 0.04f) = apply(audio, chain, propSC, propHFE, propHFC, propSF, thresh, waitTime)
}
case class PV_JensenAndersen(rate: Rate, chain: AnyGE, propSC: AnyGE, propHFE: AnyGE, propHFC: AnyGE, propSF: AnyGE, thresh: AnyGE, waitTime: AnyGE) extends SingleOutUGenSource[PV_JensenAndersenUGen] {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _propSC: IIdxSeq[UGenIn] = propSC.expand
      val _propHFE: IIdxSeq[UGenIn] = propHFE.expand
      val _propHFC: IIdxSeq[UGenIn] = propHFC.expand
      val _propSF: IIdxSeq[UGenIn] = propSF.expand
      val _thresh: IIdxSeq[UGenIn] = thresh.expand
      val _waitTime: IIdxSeq[UGenIn] = waitTime.expand
      val _sz_chain = _chain.size
      val _sz_propSC = _propSC.size
      val _sz_propHFE = _propHFE.size
      val _sz_propHFC = _propHFC.size
      val _sz_propSF = _propSF.size
      val _sz_thresh = _thresh.size
      val _sz_waitTime = _waitTime.size
      val _exp_ = maxInt(_sz_chain, _sz_propSC, _sz_propHFE, _sz_propHFC, _sz_propSF, _sz_thresh, _sz_waitTime)
      IIdxSeq.tabulate(_exp_)(i => PV_JensenAndersenUGen(rate, _chain(i.%(_sz_chain)), _propSC(i.%(_sz_propSC)), _propHFE(i.%(_sz_propHFE)), _propHFC(i.%(_sz_propHFC)), _propSF(i.%(_sz_propSF)), _thresh(i.%(_sz_thresh)), _waitTime(i.%(_sz_waitTime))))
   }
}
case class PV_JensenAndersenUGen(rate: Rate, chain: UGenIn, propSC: UGenIn, propHFE: UGenIn, propHFC: UGenIn, propSF: UGenIn, thresh: UGenIn, waitTime: UGenIn) extends SingleOutUGen(IIdxSeq(chain, propSC, propHFE, propHFC, propSF, thresh, waitTime))
object PV_HainsworthFoote {
   def ar(chain: AnyGE, propH: AnyGE = 0.0f, propF: AnyGE = 0.0f, thresh: AnyGE = 1.0f, waitTime: AnyGE = 0.04f) = apply(audio, chain, propH, propF, thresh, waitTime)
}
case class PV_HainsworthFoote(rate: Rate, chain: AnyGE, propH: AnyGE, propF: AnyGE, thresh: AnyGE, waitTime: AnyGE) extends SingleOutUGenSource[PV_HainsworthFooteUGen] {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _propH: IIdxSeq[UGenIn] = propH.expand
      val _propF: IIdxSeq[UGenIn] = propF.expand
      val _thresh: IIdxSeq[UGenIn] = thresh.expand
      val _waitTime: IIdxSeq[UGenIn] = waitTime.expand
      val _sz_chain = _chain.size
      val _sz_propH = _propH.size
      val _sz_propF = _propF.size
      val _sz_thresh = _thresh.size
      val _sz_waitTime = _waitTime.size
      val _exp_ = maxInt(_sz_chain, _sz_propH, _sz_propF, _sz_thresh, _sz_waitTime)
      IIdxSeq.tabulate(_exp_)(i => PV_HainsworthFooteUGen(rate, _chain(i.%(_sz_chain)), _propH(i.%(_sz_propH)), _propF(i.%(_sz_propF)), _thresh(i.%(_sz_thresh)), _waitTime(i.%(_sz_waitTime))))
   }
}
case class PV_HainsworthFooteUGen(rate: Rate, chain: UGenIn, propH: UGenIn, propF: UGenIn, thresh: UGenIn, waitTime: UGenIn) extends SingleOutUGen(IIdxSeq(chain, propH, propF, thresh, waitTime))
/**
 * A UGen calculating the running sum of an input signal over a given number of samples.
 */
object RunningSum {
   
/**
 * @param in              the input signal to sum up
 * @param winSize         the length of the sliding window over the input signal.
 *                        these are the number of audio sample-frames for audio-rate calculation,
 *                        or the number of blocks for control-rate calculation summed up.
 */
def kr(in: GE[UGenIn], winSize: AnyGE = 440.0f) = apply(control, in, winSize)
/**
 * @param in              the input signal to sum up
 * @param winSize         the length of the sliding window over the input signal.
 *                        these are the number of audio sample-frames for audio-rate calculation,
 *                        or the number of blocks for control-rate calculation summed up.
 */
def ar(in: GE[UGenIn], winSize: AnyGE = 440.0f) = apply(audio, in, winSize)
}
/**
 * A UGen calculating the running sum of an input signal over a given number of samples.
 * 
 * @param in              the input signal to sum up
 * @param winSize         the length of the sliding window over the input signal.
 *                        these are the number of audio sample-frames for audio-rate calculation,
 *                        or the number of blocks for control-rate calculation summed up.
 */
case class RunningSum(rate: Rate, in: AnyGE, winSize: AnyGE) extends SingleOutUGenSource[RunningSumUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _winSize: IIdxSeq[UGenIn] = winSize.expand
      val _sz_in = _in.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_in, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => RunningSumUGen(rate, _in(i.%(_sz_in)), _winSize(i.%(_sz_winSize))))
   }
}
case class RunningSumUGen(rate: Rate, in: UGenIn, winSize: UGenIn) extends SingleOutUGen(IIdxSeq(in, winSize))