/*
 * FFT2_UGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jan 07 14:02:32 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object Convolution {
   def ar(in: AnyGE, kernel: AnyGE, frameSize: AnyGE) = apply[audio](audio, in, kernel, frameSize)
}
case class Convolution[R <: Rate](rate: R, in: AnyGE, kernel: AnyGE, frameSize: AnyGE) extends SingleOutUGenSource[R, ConvolutionUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _kernel: IIdxSeq[AnyUGenIn] = kernel.expand
      val _frameSize: IIdxSeq[AnyUGenIn] = frameSize.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_frameSize = _frameSize.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_frameSize)
      IIdxSeq.tabulate(_exp_)(i => ConvolutionUGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _frameSize(i.%(_sz_frameSize))))
   }
}
case class ConvolutionUGen[R <: Rate](rate: R, in: AnyUGenIn, kernel: AnyUGenIn, frameSize: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, kernel, frameSize))
object Convolution2 {
   def ar(in: AnyGE, kernel: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE) = apply[audio](audio, in, kernel, trig, frameSize)
}
case class Convolution2[R <: Rate](rate: R, in: AnyGE, kernel: AnyGE, trig: AnyGE, frameSize: AnyGE) extends SingleOutUGenSource[R, Convolution2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _kernel: IIdxSeq[AnyUGenIn] = kernel.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _frameSize: IIdxSeq[AnyUGenIn] = frameSize.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_trig = _trig.size
      val _sz_frameSize = _frameSize.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_trig, _sz_frameSize)
      IIdxSeq.tabulate(_exp_)(i => Convolution2UGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _trig(i.%(_sz_trig)), _frameSize(i.%(_sz_frameSize))))
   }
}
case class Convolution2UGen[R <: Rate](rate: R, in: AnyUGenIn, kernel: AnyUGenIn, trig: AnyUGenIn, frameSize: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, kernel, trig, frameSize))
object Convolution2L {
   def ar(in: AnyGE, kernel: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE, fadePeriods: AnyGE = 1.0f) = apply[audio](audio, in, kernel, trig, frameSize, fadePeriods)
}
case class Convolution2L[R <: Rate](rate: R, in: AnyGE, kernel: AnyGE, trig: AnyGE, frameSize: AnyGE, fadePeriods: AnyGE) extends SingleOutUGenSource[R, Convolution2LUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _kernel: IIdxSeq[AnyUGenIn] = kernel.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _frameSize: IIdxSeq[AnyUGenIn] = frameSize.expand
      val _fadePeriods: IIdxSeq[AnyUGenIn] = fadePeriods.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_trig = _trig.size
      val _sz_frameSize = _frameSize.size
      val _sz_fadePeriods = _fadePeriods.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_trig, _sz_frameSize, _sz_fadePeriods)
      IIdxSeq.tabulate(_exp_)(i => Convolution2LUGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _trig(i.%(_sz_trig)), _frameSize(i.%(_sz_frameSize)), _fadePeriods(i.%(_sz_fadePeriods))))
   }
}
case class Convolution2LUGen[R <: Rate](rate: R, in: AnyUGenIn, kernel: AnyUGenIn, trig: AnyUGenIn, frameSize: AnyUGenIn, fadePeriods: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, kernel, trig, frameSize, fadePeriods))
object StereoConvolution2L {
   def ar(in: AnyGE, kernelL: AnyGE, kernelR: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE, fadePeriods: AnyGE = 1.0f) = apply[audio](audio, in, kernelL, kernelR, trig, frameSize, fadePeriods)
}
case class StereoConvolution2L[R <: Rate](rate: R, in: AnyGE, kernelL: AnyGE, kernelR: AnyGE, trig: AnyGE, frameSize: AnyGE, fadePeriods: AnyGE) extends UGenSource[StereoConvolution2LUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _kernelL: IIdxSeq[AnyUGenIn] = kernelL.expand
      val _kernelR: IIdxSeq[AnyUGenIn] = kernelR.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _frameSize: IIdxSeq[AnyUGenIn] = frameSize.expand
      val _fadePeriods: IIdxSeq[AnyUGenIn] = fadePeriods.expand
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
case class StereoConvolution2LUGen[R <: Rate](rate: R, in: AnyUGenIn, kernelL: AnyUGenIn, kernelR: AnyUGenIn, trig: AnyUGenIn, frameSize: AnyUGenIn, fadePeriods: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, kernelL, kernelR, trig, frameSize, fadePeriods))
object Convolution3 {
   def ar(in: AnyGE, kernel: AnyGE, trig: AnyGE = 1.0f, frameSize: AnyGE) = apply[audio](audio, in, kernel, trig, frameSize)
}
case class Convolution3[R <: Rate](rate: R, in: AnyGE, kernel: AnyGE, trig: AnyGE, frameSize: AnyGE) extends SingleOutUGenSource[R, Convolution3UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _kernel: IIdxSeq[AnyUGenIn] = kernel.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _frameSize: IIdxSeq[AnyUGenIn] = frameSize.expand
      val _sz_in = _in.size
      val _sz_kernel = _kernel.size
      val _sz_trig = _trig.size
      val _sz_frameSize = _frameSize.size
      val _exp_ = maxInt(_sz_in, _sz_kernel, _sz_trig, _sz_frameSize)
      IIdxSeq.tabulate(_exp_)(i => Convolution3UGen(rate, _in(i.%(_sz_in)), _kernel(i.%(_sz_kernel)), _trig(i.%(_sz_trig)), _frameSize(i.%(_sz_frameSize))))
   }
}
case class Convolution3UGen[R <: Rate](rate: R, in: AnyUGenIn, kernel: AnyUGenIn, trig: AnyUGenIn, frameSize: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, kernel, trig, frameSize))
case class PV_ConformalMap(chain: AnyGE, real: AnyGE = 0.0f, imag: AnyGE = 0.0f) extends SingleOutUGenSource[control, PV_ConformalMapUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _real: IIdxSeq[AnyUGenIn] = real.expand
      val _imag: IIdxSeq[AnyUGenIn] = imag.expand
      val _sz_chain = _chain.size
      val _sz_real = _real.size
      val _sz_imag = _imag.size
      val _exp_ = maxInt(_sz_chain, _sz_real, _sz_imag)
      IIdxSeq.tabulate(_exp_)(i => PV_ConformalMapUGen(_chain(i.%(_sz_chain)), _real(i.%(_sz_real)), _imag(i.%(_sz_imag))))
   }
}
case class PV_ConformalMapUGen(chain: AnyUGenIn, real: AnyUGenIn, imag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, real, imag)) with ControlRated with WritesFFT
object PV_JensenAndersen {
   def ar(chain: AnyGE, propSC: AnyGE = 0.25f, propHFE: AnyGE = 0.25f, propHFC: AnyGE = 0.25f, propSF: AnyGE = 0.25f, thresh: AnyGE = 1.0f, waitTime: AnyGE = 0.04f) = apply[audio](audio, chain, propSC, propHFE, propHFC, propSF, thresh, waitTime)
}
case class PV_JensenAndersen[R <: Rate](rate: R, chain: AnyGE, propSC: AnyGE, propHFE: AnyGE, propHFC: AnyGE, propSF: AnyGE, thresh: AnyGE, waitTime: AnyGE) extends SingleOutUGenSource[R, PV_JensenAndersenUGen[R]] {
   protected def expandUGens = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _propSC: IIdxSeq[AnyUGenIn] = propSC.expand
      val _propHFE: IIdxSeq[AnyUGenIn] = propHFE.expand
      val _propHFC: IIdxSeq[AnyUGenIn] = propHFC.expand
      val _propSF: IIdxSeq[AnyUGenIn] = propSF.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _waitTime: IIdxSeq[AnyUGenIn] = waitTime.expand
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
case class PV_JensenAndersenUGen[R <: Rate](rate: R, chain: AnyUGenIn, propSC: AnyUGenIn, propHFE: AnyUGenIn, propHFC: AnyUGenIn, propSF: AnyUGenIn, thresh: AnyUGenIn, waitTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(chain, propSC, propHFE, propHFC, propSF, thresh, waitTime))
object PV_HainsworthFoote {
   def ar(chain: AnyGE, propH: AnyGE = 0.0f, propF: AnyGE = 0.0f, thresh: AnyGE = 1.0f, waitTime: AnyGE = 0.04f) = apply[audio](audio, chain, propH, propF, thresh, waitTime)
}
case class PV_HainsworthFoote[R <: Rate](rate: R, chain: AnyGE, propH: AnyGE, propF: AnyGE, thresh: AnyGE, waitTime: AnyGE) extends SingleOutUGenSource[R, PV_HainsworthFooteUGen[R]] {
   protected def expandUGens = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _propH: IIdxSeq[AnyUGenIn] = propH.expand
      val _propF: IIdxSeq[AnyUGenIn] = propF.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _waitTime: IIdxSeq[AnyUGenIn] = waitTime.expand
      val _sz_chain = _chain.size
      val _sz_propH = _propH.size
      val _sz_propF = _propF.size
      val _sz_thresh = _thresh.size
      val _sz_waitTime = _waitTime.size
      val _exp_ = maxInt(_sz_chain, _sz_propH, _sz_propF, _sz_thresh, _sz_waitTime)
      IIdxSeq.tabulate(_exp_)(i => PV_HainsworthFooteUGen(rate, _chain(i.%(_sz_chain)), _propH(i.%(_sz_propH)), _propF(i.%(_sz_propF)), _thresh(i.%(_sz_thresh)), _waitTime(i.%(_sz_waitTime))))
   }
}
case class PV_HainsworthFooteUGen[R <: Rate](rate: R, chain: AnyUGenIn, propH: AnyUGenIn, propF: AnyUGenIn, thresh: AnyUGenIn, waitTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(chain, propH, propF, thresh, waitTime))
object RunningSum {
   def kr(in: GE[control, UGenIn[control]], winSize: AnyGE = 440.0f) = apply[control](control, in, winSize)
   def ar(in: GE[audio, UGenIn[audio]], winSize: AnyGE = 440.0f) = apply[audio](audio, in, winSize)
}
case class RunningSum[R <: Rate](rate: R, in: AnyGE, winSize: AnyGE) extends SingleOutUGenSource[R, RunningSumUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _sz_in = _in.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_in, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => RunningSumUGen(rate, _in(i.%(_sz_in)), _winSize(i.%(_sz_winSize))))
   }
}
case class RunningSumUGen[R <: Rate](rate: R, in: AnyUGenIn, winSize: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, winSize))