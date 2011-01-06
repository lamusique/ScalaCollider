/*
 * FFT_UGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 06 16:40:56 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
case class FFT(buf: AnyGE, in: AnyGE, hop: AnyGE = 0.5f, winType: AnyGE = 0.0f, active: AnyGE = 1.0f, winSize: AnyGE = 0.0f) extends GE[control, FFTUGen] with ControlRated with WritesFFT {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _hop: IIdxSeq[AnyUGenIn] = hop.expand
      val _winType: IIdxSeq[AnyUGenIn] = winType.expand
      val _active: IIdxSeq[AnyUGenIn] = active.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_hop = _hop.size
      val _sz_winType = _winType.size
      val _sz_active = _active.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_hop, _sz_winType, _sz_active, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => FFTUGen(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _hop(i.%(_sz_hop)), _winType(i.%(_sz_winType)), _active(i.%(_sz_active)), _winSize(i.%(_sz_winSize))))
   }
}
case class FFTUGen(buf: AnyUGenIn, in: AnyUGenIn, hop: AnyUGenIn, winType: AnyUGenIn, active: AnyUGenIn, winSize: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(buf, in, hop, winType, active, winSize)) with ControlRated with WritesFFT
object IFFT {
   def kr(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[control](control, chain, winType, winSize)
   def apply(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[audio](audio, chain, winType, winSize)
   def ar(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[audio](audio, chain, winType, winSize)
}
case class IFFT[R <: Rate](rate: R, chain: AnyGE, winType: AnyGE, winSize: AnyGE) extends GE[R, IFFTUGen[R]] {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _winType: IIdxSeq[AnyUGenIn] = winType.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _sz_chain = _chain.size
      val _sz_winType = _winType.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_chain, _sz_winType, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => IFFTUGen(rate, _chain(i.%(_sz_chain)), _winType(i.%(_sz_winType)), _winSize(i.%(_sz_winSize))))
   }
}
case class IFFTUGen[R <: Rate](rate: R, chain: AnyUGenIn, winType: AnyUGenIn, winSize: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(chain, winType, winSize))
case class FFTTrigger(buf: AnyGE, hop: AnyGE = 0.5f, polar: AnyGE = 0.0f) extends GE[control, FFTTriggerUGen] with ControlRated with WritesFFT {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _hop: IIdxSeq[AnyUGenIn] = hop.expand
      val _polar: IIdxSeq[AnyUGenIn] = polar.expand
      val _sz_buf = _buf.size
      val _sz_hop = _hop.size
      val _sz_polar = _polar.size
      val _exp_ = maxInt(_sz_buf, _sz_hop, _sz_polar)
      IIdxSeq.tabulate(_exp_)(i => FFTTriggerUGen(_buf(i.%(_sz_buf)), _hop(i.%(_sz_hop)), _polar(i.%(_sz_polar))))
   }
}
case class FFTTriggerUGen(buf: AnyUGenIn, hop: AnyUGenIn, polar: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(buf, hop, polar)) with ControlRated with WritesFFT
case class PV_MagAbove(chain: AnyGE, thresh: AnyGE = 0.0f) extends GE[control, PV_MagAboveUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_MagAboveUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_MagAboveUGen(chain: AnyUGenIn, thresh: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
case class PV_MagBelow(chain: AnyGE, thresh: AnyGE = 0.0f) extends GE[control, PV_MagBelowUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_MagBelowUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_MagBelowUGen(chain: AnyUGenIn, thresh: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
case class PV_MagClip(chain: AnyGE, thresh: AnyGE = 0.0f) extends GE[control, PV_MagClipUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_MagClipUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_MagClipUGen(chain: AnyUGenIn, thresh: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
case class PV_MagMul(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_MagMulUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MagMulUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MagMulUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_MagDiv(chainA: AnyGE, chainB: AnyGE, zeroes: AnyGE = 1.0E-4f) extends GE[control, PV_MagDivUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _zeroes: IIdxSeq[AnyUGenIn] = zeroes.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_zeroes = _zeroes.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_zeroes)
      IIdxSeq.tabulate(_exp_)(i => PV_MagDivUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _zeroes(i.%(_sz_zeroes))))
   }
}
case class PV_MagDivUGen(chainA: AnyUGenIn, chainB: AnyUGenIn, zeroes: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB, zeroes)) with ControlRated with WritesFFT
case class PV_MagSquared(chain: AnyGE) extends GE[control, PV_MagSquaredUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_MagSquaredUGen(_chain(i)))
   }
}
case class PV_MagSquaredUGen(chain: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain)) with ControlRated with WritesFFT
case class PV_MagNoise(chain: AnyGE) extends GE[control, PV_MagNoiseUGen] with ControlRated with WritesFFT with UsesRandSeed {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_MagNoiseUGen(_chain(i)))
   }
}
case class PV_MagNoiseUGen(chain: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain)) with ControlRated with WritesFFT with UsesRandSeed
case class PV_Copy(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_CopyUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_CopyUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_CopyUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_CopyPhase(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_CopyPhaseUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_CopyPhaseUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_CopyPhaseUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_PhaseShift(chain: AnyGE, shift: AnyGE) extends GE[control, PV_PhaseShiftUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _shift: IIdxSeq[AnyUGenIn] = shift.expand
      val _sz_chain = _chain.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => PV_PhaseShiftUGen(_chain(i.%(_sz_chain)), _shift(i.%(_sz_shift))))
   }
}
case class PV_PhaseShiftUGen(chain: AnyUGenIn, shift: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, shift)) with ControlRated with WritesFFT
case class PV_PhaseShift90(chain: AnyGE) extends GE[control, PV_PhaseShift90UGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_PhaseShift90UGen(_chain(i)))
   }
}
case class PV_PhaseShift90UGen(chain: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain)) with ControlRated with WritesFFT
case class PV_PhaseShift270(chain: AnyGE) extends GE[control, PV_PhaseShift270UGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_PhaseShift270UGen(_chain(i)))
   }
}
case class PV_PhaseShift270UGen(chain: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain)) with ControlRated with WritesFFT
case class PV_Min(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_MinUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MinUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MinUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_Max(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_MaxUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MaxUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MaxUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_Mul(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_MulUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MulUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MulUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_Div(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_DivUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_DivUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_DivUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_Add(chainA: AnyGE, chainB: AnyGE) extends GE[control, PV_AddUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_AddUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_AddUGen(chainA: AnyUGenIn, chainB: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
case class PV_RectComb(chain: AnyGE, numTeeth: AnyGE = 1.0f, phase: AnyGE = 0.0f, width: AnyGE = 0.5f) extends GE[control, PV_RectCombUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _numTeeth: IIdxSeq[AnyUGenIn] = numTeeth.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _sz_chain = _chain.size
      val _sz_numTeeth = _numTeeth.size
      val _sz_phase = _phase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_chain, _sz_numTeeth, _sz_phase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => PV_RectCombUGen(_chain(i.%(_sz_chain)), _numTeeth(i.%(_sz_numTeeth)), _phase(i.%(_sz_phase)), _width(i.%(_sz_width))))
   }
}
case class PV_RectCombUGen(chain: AnyUGenIn, numTeeth: AnyUGenIn, phase: AnyUGenIn, width: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, numTeeth, phase, width)) with ControlRated with WritesFFT
case class PV_RectComb2(chainA: AnyGE, chainB: AnyGE, numTeeth: AnyGE = 1.0f, phase: AnyGE = 0.0f, width: AnyGE = 0.5f) extends GE[control, PV_RectComb2UGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _numTeeth: IIdxSeq[AnyUGenIn] = numTeeth.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_numTeeth = _numTeeth.size
      val _sz_phase = _phase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_numTeeth, _sz_phase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => PV_RectComb2UGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _numTeeth(i.%(_sz_numTeeth)), _phase(i.%(_sz_phase)), _width(i.%(_sz_width))))
   }
}
case class PV_RectComb2UGen(chainA: AnyUGenIn, chainB: AnyUGenIn, numTeeth: AnyUGenIn, phase: AnyUGenIn, width: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB, numTeeth, phase, width)) with ControlRated with WritesFFT
case class PV_BrickWall(chain: AnyGE, wipe: AnyGE) extends GE[control, PV_BrickWallUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _wipe: IIdxSeq[AnyUGenIn] = wipe.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe)
      IIdxSeq.tabulate(_exp_)(i => PV_BrickWallUGen(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe))))
   }
}
case class PV_BrickWallUGen(chain: AnyUGenIn, wipe: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, wipe)) with ControlRated with WritesFFT
case class PV_BinWipe(chainA: AnyGE, chainB: AnyGE, wipe: AnyGE) extends GE[control, PV_BinWipeUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _wipe: IIdxSeq[AnyUGenIn] = wipe.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_wipe = _wipe.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_wipe)
      IIdxSeq.tabulate(_exp_)(i => PV_BinWipeUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _wipe(i.%(_sz_wipe))))
   }
}
case class PV_BinWipeUGen(chainA: AnyUGenIn, chainB: AnyUGenIn, wipe: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB, wipe)) with ControlRated with WritesFFT
case class PV_LocalMax(chain: AnyGE, thresh: AnyGE = 0.0f) extends GE[control, PV_LocalMaxUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_LocalMaxUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_LocalMaxUGen(chain: AnyUGenIn, thresh: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
case class PV_Conj(chain: AnyGE) extends GE[control, PV_ConjUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_ConjUGen(_chain(i)))
   }
}
case class PV_ConjUGen(chain: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain)) with ControlRated with WritesFFT
case class PV_BinScramble(chain: AnyGE, wipe: AnyGE = 0.5f, width: AnyGE = 0.2f, trig: AnyGE = 1.0f) extends GE[control, PV_BinScrambleUGen] with ControlRated with WritesFFT with UsesRandSeed {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _wipe: IIdxSeq[AnyUGenIn] = wipe.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _sz_width = _width.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe, _sz_width, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_BinScrambleUGen(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe)), _width(i.%(_sz_width)), _trig(i.%(_sz_trig))))
   }
}
case class PV_BinScrambleUGen(chain: AnyUGenIn, wipe: AnyUGenIn, width: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, wipe, width, trig)) with ControlRated with WritesFFT with UsesRandSeed
case class PV_MagSmear(chain: AnyGE, bins: AnyGE = 1.0f) extends GE[control, PV_MagSmearUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _bins: IIdxSeq[AnyUGenIn] = bins.expand
      val _sz_chain = _chain.size
      val _sz_bins = _bins.size
      val _exp_ = maxInt(_sz_chain, _sz_bins)
      IIdxSeq.tabulate(_exp_)(i => PV_MagSmearUGen(_chain(i.%(_sz_chain)), _bins(i.%(_sz_bins))))
   }
}
case class PV_MagSmearUGen(chain: AnyUGenIn, bins: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, bins)) with ControlRated with WritesFFT
case class PV_MagShift(chain: AnyGE, stretch: AnyGE = 1.0f, shift: AnyGE = 0.0f) extends GE[control, PV_MagShiftUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _stretch: IIdxSeq[AnyUGenIn] = stretch.expand
      val _shift: IIdxSeq[AnyUGenIn] = shift.expand
      val _sz_chain = _chain.size
      val _sz_stretch = _stretch.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_stretch, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => PV_MagShiftUGen(_chain(i.%(_sz_chain)), _stretch(i.%(_sz_stretch)), _shift(i.%(_sz_shift))))
   }
}
case class PV_MagShiftUGen(chain: AnyUGenIn, stretch: AnyUGenIn, shift: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, stretch, shift)) with ControlRated with WritesFFT
case class PV_BinShift(chain: AnyGE, stretch: AnyGE = 1.0f, shift: AnyGE = 0.0f) extends GE[control, PV_BinShiftUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _stretch: IIdxSeq[AnyUGenIn] = stretch.expand
      val _shift: IIdxSeq[AnyUGenIn] = shift.expand
      val _sz_chain = _chain.size
      val _sz_stretch = _stretch.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_stretch, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => PV_BinShiftUGen(_chain(i.%(_sz_chain)), _stretch(i.%(_sz_stretch)), _shift(i.%(_sz_shift))))
   }
}
case class PV_BinShiftUGen(chain: AnyUGenIn, stretch: AnyUGenIn, shift: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, stretch, shift)) with ControlRated with WritesFFT
case class PV_RandWipe(chainA: AnyGE, chainB: AnyGE, wipe: AnyGE, trig: AnyGE) extends GE[control, PV_RandWipeUGen] with ControlRated with WritesFFT with UsesRandSeed {
   def expand = {
      val _chainA: IIdxSeq[AnyUGenIn] = chainA.expand
      val _chainB: IIdxSeq[AnyUGenIn] = chainB.expand
      val _wipe: IIdxSeq[AnyUGenIn] = wipe.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_wipe = _wipe.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_wipe, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_RandWipeUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _wipe(i.%(_sz_wipe)), _trig(i.%(_sz_trig))))
   }
}
case class PV_RandWipeUGen(chainA: AnyUGenIn, chainB: AnyUGenIn, wipe: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chainA, chainB, wipe, trig)) with ControlRated with WritesFFT with UsesRandSeed
case class PV_Diffuser(chain: AnyGE, trig: AnyGE = 1.0f) extends GE[control, PV_DiffuserUGen] with ControlRated with WritesFFT with UsesRandSeed {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_chain = _chain.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_DiffuserUGen(_chain(i.%(_sz_chain)), _trig(i.%(_sz_trig))))
   }
}
case class PV_DiffuserUGen(chain: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, trig)) with ControlRated with WritesFFT with UsesRandSeed
case class PV_RandComb(chain: AnyGE, wipe: AnyGE = 0.5f, trig: AnyGE = 1.0f) extends GE[control, PV_RandCombUGen] with ControlRated with WritesFFT with UsesRandSeed {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _wipe: IIdxSeq[AnyUGenIn] = wipe.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_RandCombUGen(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe)), _trig(i.%(_sz_trig))))
   }
}
case class PV_RandCombUGen(chain: AnyUGenIn, wipe: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, wipe, trig)) with ControlRated with WritesFFT with UsesRandSeed
case class PV_MagFreeze(chain: AnyGE, freeze: AnyGE = 1.0f) extends GE[control, PV_MagFreezeUGen] with ControlRated with WritesFFT {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _freeze: IIdxSeq[AnyUGenIn] = freeze.expand
      val _sz_chain = _chain.size
      val _sz_freeze = _freeze.size
      val _exp_ = maxInt(_sz_chain, _sz_freeze)
      IIdxSeq.tabulate(_exp_)(i => PV_MagFreezeUGen(_chain(i.%(_sz_chain)), _freeze(i.%(_sz_freeze))))
   }
}
case class PV_MagFreezeUGen(chain: AnyUGenIn, freeze: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, freeze)) with ControlRated with WritesFFT
object PartConv {
   def ar(in: AnyGE, fftSize: AnyGE, buf: AnyGE) = apply[audio](audio, in, fftSize, buf)
}
case class PartConv[R <: Rate](rate: R, in: AnyGE, fftSize: AnyGE, buf: AnyGE) extends GE[R, PartConvUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _fftSize: IIdxSeq[AnyUGenIn] = fftSize.expand
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _sz_in = _in.size
      val _sz_fftSize = _fftSize.size
      val _sz_buf = _buf.size
      val _exp_ = maxInt(_sz_in, _sz_fftSize, _sz_buf)
      IIdxSeq.tabulate(_exp_)(i => PartConvUGen(rate, _in(i.%(_sz_in)), _fftSize(i.%(_sz_fftSize)), _buf(i.%(_sz_buf))))
   }
}
case class PartConvUGen[R <: Rate](rate: R, in: AnyUGenIn, fftSize: AnyUGenIn, buf: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, fftSize, buf))