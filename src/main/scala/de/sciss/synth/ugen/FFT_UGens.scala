/*
 * FFT_UGens.scala
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
case class FFT(buf: AnyGE, in: AnyGE, hop: AnyGE = 0.5f, winType: AnyGE = 0.0f, active: AnyGE = 1.0f, winSize: AnyGE = 0.0f) extends SingleOutUGenSource[FFTUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _buf: IIdxSeq[UGenIn] = buf.expand
      val _in: IIdxSeq[UGenIn] = in.expand
      val _hop: IIdxSeq[UGenIn] = hop.expand
      val _winType: IIdxSeq[UGenIn] = winType.expand
      val _active: IIdxSeq[UGenIn] = active.expand
      val _winSize: IIdxSeq[UGenIn] = winSize.expand
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
case class FFTUGen(buf: UGenIn, in: UGenIn, hop: UGenIn, winType: UGenIn, active: UGenIn, winSize: UGenIn) extends SingleOutUGen(IIdxSeq(buf, in, hop, winType, active, winSize)) with ControlRated with WritesFFT
object IFFT {
   def kr(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply(control, chain, winType, winSize)
   def apply(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f): IFFT = apply(audio, chain, winType, winSize)
   def ar(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply(audio, chain, winType, winSize)
}
case class IFFT(rate: Rate, chain: AnyGE, winType: AnyGE, winSize: AnyGE) extends SingleOutUGenSource[IFFTUGen] {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _winType: IIdxSeq[UGenIn] = winType.expand
      val _winSize: IIdxSeq[UGenIn] = winSize.expand
      val _sz_chain = _chain.size
      val _sz_winType = _winType.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_chain, _sz_winType, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => IFFTUGen(rate, _chain(i.%(_sz_chain)), _winType(i.%(_sz_winType)), _winSize(i.%(_sz_winSize))))
   }
}
case class IFFTUGen(rate: Rate, chain: UGenIn, winType: UGenIn, winSize: UGenIn) extends SingleOutUGen(IIdxSeq(chain, winType, winSize))
/**
 * A phase vocoder UGen that takes a buffer and prepares it to be used in FFT chains,
 * without doing an actual FFT on a signal. This is useful if you want to provide
 * a buffer whose content had already been transformed into the Fourier domain.
 * 
 * @param buf             the identifier of the buffer to use
 * @param hop             the hop size for timing triggers
 * @param polar           whether the complex buffer content is given in cartesian coordinates (0) or
 *                        in polar coordinates (1)
 */
case class FFTTrigger(buf: AnyGE, hop: AnyGE = 0.5f, polar: AnyGE = 0.0f) extends SingleOutUGenSource[FFTTriggerUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _buf: IIdxSeq[UGenIn] = buf.expand
      val _hop: IIdxSeq[UGenIn] = hop.expand
      val _polar: IIdxSeq[UGenIn] = polar.expand
      val _sz_buf = _buf.size
      val _sz_hop = _hop.size
      val _sz_polar = _polar.size
      val _exp_ = maxInt(_sz_buf, _sz_hop, _sz_polar)
      IIdxSeq.tabulate(_exp_)(i => FFTTriggerUGen(_buf(i.%(_sz_buf)), _hop(i.%(_sz_hop)), _polar(i.%(_sz_polar))))
   }
}
case class FFTTriggerUGen(buf: UGenIn, hop: UGenIn, polar: UGenIn) extends SingleOutUGen(IIdxSeq(buf, hop, polar)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes are above a given threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold.
 */
case class PV_MagAbove(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[PV_MagAboveUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _thresh: IIdxSeq[UGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_MagAboveUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_MagAboveUGen(chain: UGenIn, thresh: UGenIn) extends SingleOutUGen(IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes are below a given threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold.
 */
case class PV_MagBelow(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[PV_MagBelowUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _thresh: IIdxSeq[UGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_MagBelowUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_MagBelowUGen(chain: UGenIn, thresh: UGenIn) extends SingleOutUGen(IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that limits (clips) the magnitude of the bins to a given threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold. Each bin's magnitude is limited to
 *                        be not greater than this threshold.
 */
case class PV_MagClip(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[PV_MagClipUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _thresh: IIdxSeq[UGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_MagClipUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_MagClipUGen(chain: UGenIn, thresh: UGenIn) extends SingleOutUGen(IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that multiplies the magnitudes of two inputs and keeps the
 * phases of the first input.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_MagMul(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_MagMulUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MagMulUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MagMulUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that divides magnitudes of two inputs and keeps the
 * phases of the first input.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 * @param zeroes          the noise floor to assume when detecting zero bins in
 *                        chainB that would cause a division by zero and hence blow up. The ugen
 *                        will use divide by this magnitude instead when zeroes are detected,
 *                        resulting in a maximum boost of `zeroes.reciprocal`.
 */
case class PV_MagDiv(chainA: AnyGE, chainB: AnyGE, zeroes: AnyGE = 1.0E-4f) extends SingleOutUGenSource[PV_MagDivUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _zeroes: IIdxSeq[UGenIn] = zeroes.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_zeroes = _zeroes.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_zeroes)
      IIdxSeq.tabulate(_exp_)(i => PV_MagDivUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _zeroes(i.%(_sz_zeroes))))
   }
}
case class PV_MagDivUGen(chainA: UGenIn, chainB: UGenIn, zeroes: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB, zeroes)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that squares the magnitudes and re-normalizes to previous peak.
 * This makes weak bins weaker.
 * 
 * @param chain           the FFT'ed buffer
 */
case class PV_MagSquared(chain: AnyGE) extends SingleOutUGenSource[PV_MagSquaredUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_MagSquaredUGen(_chain(i)))
   }
}
case class PV_MagSquaredUGen(chain: UGenIn) extends SingleOutUGen(IIdxSeq(chain)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that multiplies the magnitudes by random noise.
 * 
 * @param chain           the FFT'ed buffer
 */
case class PV_MagNoise(chain: AnyGE) extends SingleOutUGenSource[PV_MagNoiseUGen] with ControlRated with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_MagNoiseUGen(_chain(i)))
   }
}
case class PV_MagNoiseUGen(chain: UGenIn) extends SingleOutUGen(IIdxSeq(chain)) with ControlRated with WritesFFT with UsesRandSeed
/**
 * A phase vocoder UGen that copies the spectral frames from chainA
 * to chainB. This allows for parallel processing of spectral data without the
 * need for multiple FFT UGens, and to copy out data at that point in the chain
 * for other purposes. chainA and chainB must be the same size. The output
 * will carry further chainA, so you chan insert the ugen at the appropriate
 * place in the signal chain.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_Copy(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_CopyUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_CopyUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_CopyUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that combines the magnitudes of first input and phases of the second input.
 * phases of the first input.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_CopyPhase(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_CopyPhaseUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_CopyPhaseUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_CopyPhaseUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that shifts the phase of each bins by a given amount.
 * 
 * @param chain           the FFT'ed buffer
 * @param shift           phase shift in radians
 */
case class PV_PhaseShift(chain: AnyGE, shift: AnyGE) extends SingleOutUGenSource[PV_PhaseShiftUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _shift: IIdxSeq[UGenIn] = shift.expand
      val _sz_chain = _chain.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => PV_PhaseShiftUGen(_chain(i.%(_sz_chain)), _shift(i.%(_sz_shift))))
   }
}
case class PV_PhaseShiftUGen(chain: UGenIn, shift: UGenIn) extends SingleOutUGen(IIdxSeq(chain, shift)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that shift the phase of all bins by 90 degrees.
 * 
 * @param chain           the FFT'ed buffer
 */
case class PV_PhaseShift90(chain: AnyGE) extends SingleOutUGenSource[PV_PhaseShift90UGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_PhaseShift90UGen(_chain(i)))
   }
}
case class PV_PhaseShift90UGen(chain: UGenIn) extends SingleOutUGen(IIdxSeq(chain)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that shift the phase of all bins by 270 (or -90) degrees.
 * 
 * @param chain           the FFT'ed buffer
 */
case class PV_PhaseShift270(chain: AnyGE) extends SingleOutUGenSource[PV_PhaseShift270UGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_PhaseShift270UGen(_chain(i)))
   }
}
case class PV_PhaseShift270UGen(chain: UGenIn) extends SingleOutUGen(IIdxSeq(chain)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that outputs the bins with the minimum magnitude
 * of the two inputs.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_Min(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_MinUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MinUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MinUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that outputs the bins with the maximum magnitude
 * of the two inputs.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_Max(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_MaxUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MaxUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MaxUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that performs a complex multiplication of the
 * two inputs. The formula
 * is `(Re(A) * Re(B) - Im(A) * Im(B)) + i(Im(A) * Re(B) + Re(A) * Im(B))`.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_Mul(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_MulUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_MulUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_MulUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that performs a complex division of the
 * two inputs. Be careful that `chainB`, the divisor, does not
 * contain zeroes as they would obviously blow up the division.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_Div(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_DivUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_DivUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_DivUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that performs a complex addition of the
 * two inputs. The formula
 * is `(Re(A) + Re(B)) + i(Im(A) + Im(B))`.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
case class PV_Add(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[PV_AddUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => PV_AddUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB))))
   }
}
case class PV_AddUGen(chainA: UGenIn, chainB: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that makes a series of gaps in a spectrum.
 * This is done by multiplying the spectrum with a kind of
 * rectangle wave that goes from zero to nyquist. The high slope
 * of the rectangle lets the input bins pass (quasi pass-band),
 * the low slope filteres them out (quasi stop-band).
 * 
 * @param chain           the FFT'ed buffer
 * @param numTeeth        the number of periods in the rectangle wave, where
 *                        zero would mean the input signal is not affected, one means that
 *                        there is exactly one period of the wave across the spectrum,
 *                        hence one pass-band and one stop-band.
 * @param phase           the phase offset of the rectangle wave, where
 *                        1.0 is one full period. This is like the offset into the wavetable
 *                        holding the rectangle, so a value of 0.25 means we start 25%
 *                        into the basic waveform, and after 0.75 periods the next
 *                        full period (high slope) begins.
 * @param width           the pulse width between 0.0 (infinitely small
 *                        high slope, so all bins filtered out) to 0.5 (half period is
 *                        high slope, half period is low slope) to 1.0 (maximally
 *                        wide high slope, no bins filtered out).
 * 
 * @see [[de.sciss.synth.ugen.PV_RectComb2]]
 */
case class PV_RectComb(chain: AnyGE, numTeeth: AnyGE = 1.0f, phase: AnyGE = 0.0f, width: AnyGE = 0.5f) extends SingleOutUGenSource[PV_RectCombUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _numTeeth: IIdxSeq[UGenIn] = numTeeth.expand
      val _phase: IIdxSeq[UGenIn] = phase.expand
      val _width: IIdxSeq[UGenIn] = width.expand
      val _sz_chain = _chain.size
      val _sz_numTeeth = _numTeeth.size
      val _sz_phase = _phase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_chain, _sz_numTeeth, _sz_phase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => PV_RectCombUGen(_chain(i.%(_sz_chain)), _numTeeth(i.%(_sz_numTeeth)), _phase(i.%(_sz_phase)), _width(i.%(_sz_width))))
   }
}
case class PV_RectCombUGen(chain: UGenIn, numTeeth: UGenIn, phase: UGenIn, width: UGenIn) extends SingleOutUGen(IIdxSeq(chain, numTeeth, phase, width)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that switches between two input spectra
 * according to a rectangle wave.
 * This is basically identical to `PV_RectComb`, however during the
 * low slopes of the rectangle wavewave, instead of clearing out the bins,
 * it copies over the corresponding bins of the second fft input buffer.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by the output signal)
 * @param chainB          the second FFT'ed buffer
 * @param numTeeth        the number of periods in the rectangle wave, where
 *                        zero would mean the first input signal is fully passed through, one means that
 *                        there is exactly one period of the wave across the spectrum,
 *                        hence one pass-band (first signal passed through) and one stop-band
 *                        (second signal passed through).
 * @param phase           the phase offset of the rectangle wave, where
 *                        1.0 is one full period. This is like the offset into the wavetable
 *                        holding the rectangle, so a value of 0.25 means we start 25%
 *                        into the basic waveform, and after 0.75 periods the next
 *                        full period (high slope) begins.
 * @param width           the pulse width between 0.0 (infinitely small
 *                        high slope, so all bins are copied from the second input) to 0.5
 *                        (half period is high slope -- copied from first input --, half period is
 *                        low slope -- copied from second input) to 1.0 (maximally
 *                        wide high slope, so all bins passed from the first input).
 * 
 * @see [[de.sciss.synth.ugen.PV_RectComb]]
 */
case class PV_RectComb2(chainA: AnyGE, chainB: AnyGE, numTeeth: AnyGE = 1.0f, phase: AnyGE = 0.0f, width: AnyGE = 0.5f) extends SingleOutUGenSource[PV_RectComb2UGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _numTeeth: IIdxSeq[UGenIn] = numTeeth.expand
      val _phase: IIdxSeq[UGenIn] = phase.expand
      val _width: IIdxSeq[UGenIn] = width.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_numTeeth = _numTeeth.size
      val _sz_phase = _phase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_numTeeth, _sz_phase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => PV_RectComb2UGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _numTeeth(i.%(_sz_numTeeth)), _phase(i.%(_sz_phase)), _width(i.%(_sz_width))))
   }
}
case class PV_RectComb2UGen(chainA: UGenIn, chainB: UGenIn, numTeeth: UGenIn, phase: UGenIn, width: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB, numTeeth, phase, width)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that clears bins above or below a cutoff point.
 * 
 * @param chain           the FFT'ed buffer
 * @param wipe            can range between -1 and +1.
 *                        if wipe == 0 then there is no effect.
 *                        if  wipe > 0 then it acts like a high pass filter, clearing bins from the bottom up.
 *                        if  wipe < 0 then it acts like a low pass filter, clearing bins from the top down.
 */
case class PV_BrickWall(chain: AnyGE, wipe: AnyGE) extends SingleOutUGenSource[PV_BrickWallUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _wipe: IIdxSeq[UGenIn] = wipe.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe)
      IIdxSeq.tabulate(_exp_)(i => PV_BrickWallUGen(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe))))
   }
}
case class PV_BrickWallUGen(chain: UGenIn, wipe: UGenIn) extends SingleOutUGen(IIdxSeq(chain, wipe)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that combine low and high bins from two inputs.
 * It does so by copiying low bins from one input and the high bins of the other,
 * thus realizes a kind of "wipe" between the two input signals.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by the output signal)
 * @param chainB          the second FFT'ed buffer
 * @param wipe            can range between -1 and +1.
 *                        if wipe == 0 then the output is the same as inA.
 *                        if  wipe > 0 then it begins replacing with bins from inB from the bottom up.
 *                        if  wipe < 0 then it begins replacing with bins from inB from the top down.
 * 
 * @see [[de.sciss.synth.ugen.PV_RandWipe]]
 */
case class PV_BinWipe(chainA: AnyGE, chainB: AnyGE, wipe: AnyGE) extends SingleOutUGenSource[PV_BinWipeUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _wipe: IIdxSeq[UGenIn] = wipe.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_wipe = _wipe.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_wipe)
      IIdxSeq.tabulate(_exp_)(i => PV_BinWipeUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _wipe(i.%(_sz_wipe))))
   }
}
case class PV_BinWipeUGen(chainA: UGenIn, chainB: UGenIn, wipe: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB, wipe)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes constitute local maxima.
 * Additionally, the given threshold is also used to filter out bins whose magnitude
 * lies below this threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold used for general filtering, prior to
 *                        the local-maximum-filtering
 */
case class PV_LocalMax(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[PV_LocalMaxUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _thresh: IIdxSeq[UGenIn] = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => PV_LocalMaxUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh))))
   }
}
case class PV_LocalMaxUGen(chain: UGenIn, thresh: UGenIn) extends SingleOutUGen(IIdxSeq(chain, thresh)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that converts the bins into their
 * complex conjugate counterparts. The complex conjugate
 * is equal to the input, but with reversed sign of the imaginary part.
 * 
 * @param chain           the FFT'ed buffer
 */
case class PV_Conj(chain: AnyGE) extends SingleOutUGenSource[PV_ConjUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => PV_ConjUGen(_chain(i)))
   }
}
case class PV_ConjUGen(chain: UGenIn) extends SingleOutUGen(IIdxSeq(chain)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that randomizes the order of the bins.
 * The trigger will select a new random ordering.
 * 
 * @param chain           the FFT'ed buffer
 * @param wipe            the amount of bins scrambled, from 0 (none) to 1 (all bins scrambled).
 * @param width           a value from zero to one, indicating the maximum randomized distance of a bin from its
 *                        original location in the spectrum.
 * @param trig            causes a new random bin re-ordering to be made. a trigger
 *                        occurs when passing from non-positive to positive value.
 */
case class PV_BinScramble(chain: AnyGE, wipe: AnyGE = 0.5f, width: AnyGE = 0.2f, trig: AnyGE = 1.0f) extends SingleOutUGenSource[PV_BinScrambleUGen] with ControlRated with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _wipe: IIdxSeq[UGenIn] = wipe.expand
      val _width: IIdxSeq[UGenIn] = width.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _sz_width = _width.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe, _sz_width, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_BinScrambleUGen(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe)), _width(i.%(_sz_width)), _trig(i.%(_sz_trig))))
   }
}
case class PV_BinScrambleUGen(chain: UGenIn, wipe: UGenIn, width: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(chain, wipe, width, trig)) with ControlRated with WritesFFT with UsesRandSeed
/**
 * A phase vocoder UGen that averages each bin's magnitude with its neighbors.
 * 
 * @param chain           the FFT'ed buffer
 * @param bins            number of bins to average on each side of bin. As this number rises, so will CPU usage.
 */
case class PV_MagSmear(chain: AnyGE, bins: AnyGE = 1.0f) extends SingleOutUGenSource[PV_MagSmearUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _bins: IIdxSeq[UGenIn] = bins.expand
      val _sz_chain = _chain.size
      val _sz_bins = _bins.size
      val _exp_ = maxInt(_sz_chain, _sz_bins)
      IIdxSeq.tabulate(_exp_)(i => PV_MagSmearUGen(_chain(i.%(_sz_chain)), _bins(i.%(_sz_bins))))
   }
}
case class PV_MagSmearUGen(chain: UGenIn, bins: UGenIn) extends SingleOutUGen(IIdxSeq(chain, bins)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that stretches and shifts the magnitudes of the spectrum.
 * This is live `PV_BinShift` but instead of scaling and shifting the whole complex
 * bins (magnitude and phase), this only operates on the magnitudes and leaves
 * the phases in their original bins.
 * 
 * @param chain           the FFT'ed buffer
 * @param stretch         the factor to multiply each bin position with
 * @param shift           the translation of the spectrum, in number of bins
 */
case class PV_MagShift(chain: AnyGE, stretch: AnyGE = 1.0f, shift: AnyGE = 0.0f) extends SingleOutUGenSource[PV_MagShiftUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _stretch: IIdxSeq[UGenIn] = stretch.expand
      val _shift: IIdxSeq[UGenIn] = shift.expand
      val _sz_chain = _chain.size
      val _sz_stretch = _stretch.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_stretch, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => PV_MagShiftUGen(_chain(i.%(_sz_chain)), _stretch(i.%(_sz_stretch)), _shift(i.%(_sz_shift))))
   }
}
case class PV_MagShiftUGen(chain: UGenIn, stretch: UGenIn, shift: UGenIn) extends SingleOutUGen(IIdxSeq(chain, stretch, shift)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that stretches and shifts the spectrum.
 * It takes each bin, first stretches (scales) its position (bin number)
 * with a given factor, and then adds a shift to it.
 * 
 * @param chain           the FFT'ed buffer
 * @param stretch         the factor to multiply each bin position with, where 0.5 kind of
 *                        transposes the signal down by an octave, and 2 transposes it up by an octave.
 * @param shift           the translation of the spectrum, in number of bins. Since the
 *                        FFT produces a linear frequency axis, the will produce harmonic distortion.
 */
case class PV_BinShift(chain: AnyGE, stretch: AnyGE = 1.0f, shift: AnyGE = 0.0f) extends SingleOutUGenSource[PV_BinShiftUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _stretch: IIdxSeq[UGenIn] = stretch.expand
      val _shift: IIdxSeq[UGenIn] = shift.expand
      val _sz_chain = _chain.size
      val _sz_stretch = _stretch.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_stretch, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => PV_BinShiftUGen(_chain(i.%(_sz_chain)), _stretch(i.%(_sz_stretch)), _shift(i.%(_sz_shift))))
   }
}
case class PV_BinShiftUGen(chain: UGenIn, stretch: UGenIn, shift: UGenIn) extends SingleOutUGen(IIdxSeq(chain, stretch, shift)) with ControlRated with WritesFFT
/**
 * A phase vocoder UGen that crossfades between two input spectra
 * by taking bins randomly from them according to a given probability.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by the output signal)
 * @param chainB          the second FFT'ed buffer
 * @param wipe            the crossfader position from 0.0 (all bins are
 *                        taken from `chainA`) to 1.0 (all bins are taken from `chainB`).
 *                        For instance, if wipe is 0.5, half of the bins are taken from
 *                        either input. The decision whether a bin is taken from A or B
 *                        is random, however remains constant between two triggers.
 * @param trig            a signal the triggers the re-newed process of
 *                        determining for each bin whether it will be taken from input
 *                        A or B. A trigger occurs when passing from non-positive to
 *                        positive value.
 * 
 * @see [[de.sciss.synth.ugen.PV_BinWipe]]
 */
case class PV_RandWipe(chainA: AnyGE, chainB: AnyGE, wipe: AnyGE, trig: AnyGE) extends SingleOutUGenSource[PV_RandWipeUGen] with ControlRated with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chainA: IIdxSeq[UGenIn] = chainA.expand
      val _chainB: IIdxSeq[UGenIn] = chainB.expand
      val _wipe: IIdxSeq[UGenIn] = wipe.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_wipe = _wipe.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_wipe, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_RandWipeUGen(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _wipe(i.%(_sz_wipe)), _trig(i.%(_sz_trig))))
   }
}
case class PV_RandWipeUGen(chainA: UGenIn, chainB: UGenIn, wipe: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(chainA, chainB, wipe, trig)) with ControlRated with WritesFFT with UsesRandSeed
/**
 * A phase vocoder UGen that adds a different constant random phase shift to each bin.
 * The trigger will select a new set of random phases.
 * 
 * @param chain           the FFT'ed buffer
 * @param trig            to trigger a new selection of random phases. A trigger occurs when
 *                        passing from non-positive to positive value.
 */
case class PV_Diffuser(chain: AnyGE, trig: AnyGE = 1.0f) extends SingleOutUGenSource[PV_DiffuserUGen] with ControlRated with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _sz_chain = _chain.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_DiffuserUGen(_chain(i.%(_sz_chain)), _trig(i.%(_sz_trig))))
   }
}
case class PV_DiffuserUGen(chain: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(chain, trig)) with ControlRated with WritesFFT with UsesRandSeed
/**
 * A phase vocoder UGen that randomly clears out bins of the signal.
 * Which bins are wiped out is subject to a random choice (only the amount is
 * specified) that remains constant between triggers.
 * 
 * @param chain           the FFT'ed buffer
 * @param wipe            the probability (from 0 to 1) of bins being wiped out,
 *                        hence 0 means no bins are wiped out, 1 means all bins are wiped out
 *                        (output will be silence).
 * @param trig            causes a new random bin selection to be made. a trigger
 *                        occurs when passing from non-positive to positive value.
 */
case class PV_RandComb(chain: AnyGE, wipe: AnyGE = 0.5f, trig: AnyGE = 1.0f) extends SingleOutUGenSource[PV_RandCombUGen] with ControlRated with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _wipe: IIdxSeq[UGenIn] = wipe.expand
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PV_RandCombUGen(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe)), _trig(i.%(_sz_trig))))
   }
}
case class PV_RandCombUGen(chain: UGenIn, wipe: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(chain, wipe, trig)) with ControlRated with WritesFFT with UsesRandSeed
/**
 * A phase vocoder UGen that freezes the magnitudes at current levels.
 * Freezing happens when the freeze input has a value of > 0.
 * 
 * @param chain           the FFT'ed buffer
 * @param freeze          whether the current levels are frozen (> 0) or not (0).
 */
case class PV_MagFreeze(chain: AnyGE, freeze: AnyGE = 1.0f) extends SingleOutUGenSource[PV_MagFreezeUGen] with ControlRated with WritesFFT {
   protected def expandUGens = {
      val _chain: IIdxSeq[UGenIn] = chain.expand
      val _freeze: IIdxSeq[UGenIn] = freeze.expand
      val _sz_chain = _chain.size
      val _sz_freeze = _freeze.size
      val _exp_ = maxInt(_sz_chain, _sz_freeze)
      IIdxSeq.tabulate(_exp_)(i => PV_MagFreezeUGen(_chain(i.%(_sz_chain)), _freeze(i.%(_sz_freeze))))
   }
}
case class PV_MagFreezeUGen(chain: UGenIn, freeze: UGenIn) extends SingleOutUGen(IIdxSeq(chain, freeze)) with ControlRated with WritesFFT
object PartConv {
   def ar(in: AnyGE, fftSize: AnyGE, buf: AnyGE) = apply(audio, in, fftSize, buf)
}
case class PartConv(rate: Rate, in: AnyGE, fftSize: AnyGE, buf: AnyGE) extends SingleOutUGenSource[PartConvUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _fftSize: IIdxSeq[UGenIn] = fftSize.expand
      val _buf: IIdxSeq[UGenIn] = buf.expand
      val _sz_in = _in.size
      val _sz_fftSize = _fftSize.size
      val _sz_buf = _buf.size
      val _exp_ = maxInt(_sz_in, _sz_fftSize, _sz_buf)
      IIdxSeq.tabulate(_exp_)(i => PartConvUGen(rate, _in(i.%(_sz_in)), _fftSize(i.%(_sz_fftSize)), _buf(i.%(_sz_buf))))
   }
}
case class PartConvUGen(rate: Rate, in: UGenIn, fftSize: UGenIn, buf: UGenIn) extends SingleOutUGen(IIdxSeq(in, fftSize, buf))