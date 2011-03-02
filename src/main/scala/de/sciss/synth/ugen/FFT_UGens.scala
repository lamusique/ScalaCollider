/*
 * FFT_UGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import util.UGenHelper._
final case class FFT(buf: AnyGE, in: AnyGE, hop: AnyGE = 0.5f, winType: AnyGE = 0.0f, active: AnyGE = 1.0f, winSize: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.expand
      val _hop = hop.expand
      val _winType = winType.expand
      val _active = active.expand
      val _winSize = winSize.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_hop = _hop.size
      val _sz_winType = _winType.size
      val _sz_active = _active.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_hop, _sz_winType, _sz_active, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FFT", control, IIdxSeq(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _hop(i.%(_sz_hop)), _winType(i.%(_sz_winType)), _active(i.%(_sz_active)), _winSize(i.%(_sz_winSize)))))
   }
}
object IFFT {
   def kr(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[control](control, chain, winType, winSize)
   def apply(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[audio](audio, chain, winType, winSize)
   def ar(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[audio](audio, chain, winType, winSize)
}
final case class IFFT[R <: Rate](rate: R, chain: AnyGE, winType: AnyGE, winSize: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _chain = chain.expand
      val _winType = winType.expand
      val _winSize = winSize.expand
      val _sz_chain = _chain.size
      val _sz_winType = _winType.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_chain, _sz_winType, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("IFFT", rate, IIdxSeq(_chain(i.%(_sz_chain)), _winType(i.%(_sz_winType)), _winSize(i.%(_sz_winSize)))))
   }
}
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
final case class FFTTrigger(buf: AnyGE, hop: AnyGE = 0.5f, polar: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _buf = buf.expand
      val _hop = hop.expand
      val _polar = polar.expand
      val _sz_buf = _buf.size
      val _sz_hop = _hop.size
      val _sz_polar = _polar.size
      val _exp_ = maxInt(_sz_buf, _sz_hop, _sz_polar)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FFTTrigger", control, IIdxSeq(_buf(i.%(_sz_buf)), _hop(i.%(_sz_hop)), _polar(i.%(_sz_polar)))))
   }
}
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes are above a given threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold.
 */
final case class PV_MagAbove(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _thresh = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagAbove", control, IIdxSeq(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh)))))
   }
}
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes are below a given threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold.
 */
final case class PV_MagBelow(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _thresh = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagBelow", control, IIdxSeq(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh)))))
   }
}
/**
 * A phase vocoder UGen that limits (clips) the magnitude of the bins to a given threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold. Each bin's magnitude is limited to
 *                        be not greater than this threshold.
 */
final case class PV_MagClip(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _thresh = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagClip", control, IIdxSeq(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh)))))
   }
}
/**
 * A phase vocoder UGen that multiplies the magnitudes of two inputs and keeps the
 * phases of the first input.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
final case class PV_MagMul(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagMul", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
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
final case class PV_MagDiv(chainA: AnyGE, chainB: AnyGE, zeroes: AnyGE = 1.0E-4f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _zeroes = zeroes.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_zeroes = _zeroes.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_zeroes)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagDiv", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _zeroes(i.%(_sz_zeroes)))))
   }
}
/**
 * A phase vocoder UGen that squares the magnitudes and re-normalizes to previous peak.
 * This makes weak bins weaker.
 * 
 * @param chain           the FFT'ed buffer
 */
final case class PV_MagSquared(chain: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => new SingleOutUGen("PV_MagSquared", control, IIdxSeq(_chain(i))))
   }
}
/**
 * A phase vocoder UGen that multiplies the magnitudes by random noise.
 * 
 * @param chain           the FFT'ed buffer
 */
final case class PV_MagNoise(chain: AnyGE) extends SingleOutUGenSource[control] with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => new SingleOutUGen("PV_MagNoise", control, IIdxSeq(_chain(i))))
   }
}
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
final case class PV_Copy(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_Copy", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
/**
 * A phase vocoder UGen that combines the magnitudes of first input and phases of the second input.
 * phases of the first input.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
final case class PV_CopyPhase(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_CopyPhase", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
/**
 * A phase vocoder UGen that shifts the phase of each bins by a given amount.
 * 
 * @param chain           the FFT'ed buffer
 * @param shift           phase shift in radians
 */
final case class PV_PhaseShift(chain: AnyGE, shift: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _shift = shift.expand
      val _sz_chain = _chain.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_PhaseShift", control, IIdxSeq(_chain(i.%(_sz_chain)), _shift(i.%(_sz_shift)))))
   }
}
/**
 * A phase vocoder UGen that shift the phase of all bins by 90 degrees.
 * 
 * @param chain           the FFT'ed buffer
 */
final case class PV_PhaseShift90(chain: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => new SingleOutUGen("PV_PhaseShift90", control, IIdxSeq(_chain(i))))
   }
}
/**
 * A phase vocoder UGen that shift the phase of all bins by 270 (or -90) degrees.
 * 
 * @param chain           the FFT'ed buffer
 */
final case class PV_PhaseShift270(chain: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => new SingleOutUGen("PV_PhaseShift270", control, IIdxSeq(_chain(i))))
   }
}
/**
 * A phase vocoder UGen that outputs the bins with the minimum magnitude
 * of the two inputs.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
final case class PV_Min(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_Min", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
/**
 * A phase vocoder UGen that outputs the bins with the maximum magnitude
 * of the two inputs.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
final case class PV_Max(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_Max", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
/**
 * A phase vocoder UGen that performs a complex multiplication of the
 * two inputs. The formula
 * is `(Re(A) * Re(B) - Im(A) * Im(B)) + i(Im(A) * Re(B) + Re(A) * Im(B))`.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
final case class PV_Mul(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_Mul", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
/**
 * A phase vocoder UGen that performs a complex division of the
 * two inputs. Be careful that `chainB`, the divisor, does not
 * contain zeroes as they would obviously blow up the division.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
final case class PV_Div(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_Div", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
/**
 * A phase vocoder UGen that performs a complex addition of the
 * two inputs. The formula
 * is `(Re(A) + Re(B)) + i(Im(A) + Im(B))`.
 * 
 * @param chainA          the first FFT'ed buffer (this gets replaced by
 *                        the output signal)
 * @param chainB          the second FFT'ed buffer
 */
final case class PV_Add(chainA: AnyGE, chainB: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_Add", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)))))
   }
}
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
final case class PV_RectComb(chain: AnyGE, numTeeth: AnyGE = 1.0f, phase: AnyGE = 0.0f, width: AnyGE = 0.5f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _numTeeth = numTeeth.expand
      val _phase = phase.expand
      val _width = width.expand
      val _sz_chain = _chain.size
      val _sz_numTeeth = _numTeeth.size
      val _sz_phase = _phase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_chain, _sz_numTeeth, _sz_phase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_RectComb", control, IIdxSeq(_chain(i.%(_sz_chain)), _numTeeth(i.%(_sz_numTeeth)), _phase(i.%(_sz_phase)), _width(i.%(_sz_width)))))
   }
}
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
final case class PV_RectComb2(chainA: AnyGE, chainB: AnyGE, numTeeth: AnyGE = 1.0f, phase: AnyGE = 0.0f, width: AnyGE = 0.5f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _numTeeth = numTeeth.expand
      val _phase = phase.expand
      val _width = width.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_numTeeth = _numTeeth.size
      val _sz_phase = _phase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_numTeeth, _sz_phase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_RectComb2", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _numTeeth(i.%(_sz_numTeeth)), _phase(i.%(_sz_phase)), _width(i.%(_sz_width)))))
   }
}
/**
 * A phase vocoder UGen that clears bins above or below a cutoff point.
 * 
 * @param chain           the FFT'ed buffer
 * @param wipe            can range between -1 and +1.
 *                        if wipe == 0 then there is no effect.
 *                        if  wipe > 0 then it acts like a high pass filter, clearing bins from the bottom up.
 *                        if  wipe < 0 then it acts like a low pass filter, clearing bins from the top down.
 */
final case class PV_BrickWall(chain: AnyGE, wipe: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _wipe = wipe.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_BrickWall", control, IIdxSeq(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe)))))
   }
}
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
final case class PV_BinWipe(chainA: AnyGE, chainB: AnyGE, wipe: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _wipe = wipe.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_wipe = _wipe.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_wipe)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_BinWipe", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _wipe(i.%(_sz_wipe)))))
   }
}
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes constitute local maxima.
 * Additionally, the given threshold is also used to filter out bins whose magnitude
 * lies below this threshold.
 * 
 * @param chain           the FFT'ed buffer
 * @param thresh          magnitude threshold used for general filtering, prior to
 *                        the local-maximum-filtering
 */
final case class PV_LocalMax(chain: AnyGE, thresh: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _thresh = thresh.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_LocalMax", control, IIdxSeq(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh)))))
   }
}
/**
 * A phase vocoder UGen that converts the bins into their
 * complex conjugate counterparts. The complex conjugate
 * is equal to the input, but with reversed sign of the imaginary part.
 * 
 * @param chain           the FFT'ed buffer
 */
final case class PV_Conj(chain: AnyGE) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => new SingleOutUGen("PV_Conj", control, IIdxSeq(_chain(i))))
   }
}
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
final case class PV_BinScramble(chain: AnyGE, wipe: AnyGE = 0.5f, width: AnyGE = 0.2f, trig: AnyGE = 1.0f) extends SingleOutUGenSource[control] with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain = chain.expand
      val _wipe = wipe.expand
      val _width = width.expand
      val _trig = trig.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _sz_width = _width.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe, _sz_width, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_BinScramble", control, IIdxSeq(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe)), _width(i.%(_sz_width)), _trig(i.%(_sz_trig)))))
   }
}
/**
 * A phase vocoder UGen that averages each bin's magnitude with its neighbors.
 * 
 * @param chain           the FFT'ed buffer
 * @param bins            number of bins to average on each side of bin. As this number rises, so will CPU usage.
 */
final case class PV_MagSmear(chain: AnyGE, bins: AnyGE = 1.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _bins = bins.expand
      val _sz_chain = _chain.size
      val _sz_bins = _bins.size
      val _exp_ = maxInt(_sz_chain, _sz_bins)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagSmear", control, IIdxSeq(_chain(i.%(_sz_chain)), _bins(i.%(_sz_bins)))))
   }
}
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
final case class PV_MagShift(chain: AnyGE, stretch: AnyGE = 1.0f, shift: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _stretch = stretch.expand
      val _shift = shift.expand
      val _sz_chain = _chain.size
      val _sz_stretch = _stretch.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_stretch, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagShift", control, IIdxSeq(_chain(i.%(_sz_chain)), _stretch(i.%(_sz_stretch)), _shift(i.%(_sz_shift)))))
   }
}
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
final case class PV_BinShift(chain: AnyGE, stretch: AnyGE = 1.0f, shift: AnyGE = 0.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _stretch = stretch.expand
      val _shift = shift.expand
      val _sz_chain = _chain.size
      val _sz_stretch = _stretch.size
      val _sz_shift = _shift.size
      val _exp_ = maxInt(_sz_chain, _sz_stretch, _sz_shift)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_BinShift", control, IIdxSeq(_chain(i.%(_sz_chain)), _stretch(i.%(_sz_stretch)), _shift(i.%(_sz_shift)))))
   }
}
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
final case class PV_RandWipe(chainA: AnyGE, chainB: AnyGE, wipe: AnyGE, trig: AnyGE) extends SingleOutUGenSource[control] with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chainA = chainA.expand
      val _chainB = chainB.expand
      val _wipe = wipe.expand
      val _trig = trig.expand
      val _sz_chainA = _chainA.size
      val _sz_chainB = _chainB.size
      val _sz_wipe = _wipe.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chainA, _sz_chainB, _sz_wipe, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_RandWipe", control, IIdxSeq(_chainA(i.%(_sz_chainA)), _chainB(i.%(_sz_chainB)), _wipe(i.%(_sz_wipe)), _trig(i.%(_sz_trig)))))
   }
}
/**
 * A phase vocoder UGen that adds a different constant random phase shift to each bin.
 * The trigger will select a new set of random phases.
 * 
 * @param chain           the FFT'ed buffer
 * @param trig            to trigger a new selection of random phases. A trigger occurs when
 *                        passing from non-positive to positive value.
 */
final case class PV_Diffuser(chain: AnyGE, trig: AnyGE = 1.0f) extends SingleOutUGenSource[control] with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain = chain.expand
      val _trig = trig.expand
      val _sz_chain = _chain.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_Diffuser", control, IIdxSeq(_chain(i.%(_sz_chain)), _trig(i.%(_sz_trig)))))
   }
}
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
final case class PV_RandComb(chain: AnyGE, wipe: AnyGE = 0.5f, trig: AnyGE = 1.0f) extends SingleOutUGenSource[control] with WritesFFT with UsesRandSeed {
   protected def expandUGens = {
      val _chain = chain.expand
      val _wipe = wipe.expand
      val _trig = trig.expand
      val _sz_chain = _chain.size
      val _sz_wipe = _wipe.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_chain, _sz_wipe, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_RandComb", control, IIdxSeq(_chain(i.%(_sz_chain)), _wipe(i.%(_sz_wipe)), _trig(i.%(_sz_trig)))))
   }
}
/**
 * A phase vocoder UGen that freezes the magnitudes at current levels.
 * Freezing happens when the freeze input has a value of > 0.
 * 
 * @param chain           the FFT'ed buffer
 * @param freeze          whether the current levels are frozen (> 0) or not (0).
 */
final case class PV_MagFreeze(chain: AnyGE, freeze: AnyGE = 1.0f) extends SingleOutUGenSource[control] with WritesFFT {
   protected def expandUGens = {
      val _chain = chain.expand
      val _freeze = freeze.expand
      val _sz_chain = _chain.size
      val _sz_freeze = _freeze.size
      val _exp_ = maxInt(_sz_chain, _sz_freeze)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PV_MagFreeze", control, IIdxSeq(_chain(i.%(_sz_chain)), _freeze(i.%(_sz_freeze)))))
   }
}
object PartConv {
   def ar(in: AnyGE, fftSize: AnyGE, buf: AnyGE) = apply[audio](audio, in, fftSize, buf)
}
final case class PartConv[R <: Rate](rate: R, in: AnyGE, fftSize: AnyGE, buf: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _fftSize = fftSize.expand
      val _buf = buf.expand
      val _sz_in = _in.size
      val _sz_fftSize = _fftSize.size
      val _sz_buf = _buf.size
      val _exp_ = maxInt(_sz_in, _sz_fftSize, _sz_buf)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("PartConv", rate, IIdxSeq(_in(i.%(_sz_in)), _fftSize(i.%(_sz_fftSize)), _buf(i.%(_sz_buf)))))
   }
}