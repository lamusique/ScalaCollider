/*
 *  FFT.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2010 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 *
 *
 *  Changelog:
 */

package de.sciss.synth.ugen

import de.sciss.synth.{ ControlRated, GE, Rate, SingleOutUGen, UGenIn }

object FFT extends UGen6RArgs {
   def apply( buf: GE, in: GE, hop: GE = 0.5f, winType: GE = 0, active: GE = 1, winSize: GE = 0 ) : GE =
      make( buf, in, hop, winType, active, winSize )
}
case class FFT( buf: UGenIn, in: UGenIn, hop: UGenIn, winType: UGenIn, active: UGenIn, winSize: UGenIn )
extends SingleOutUGen( buf, in, hop, winType, active, winSize ) with ControlRated

object IFFT extends UGen3Args {
   def apply( chain: GE, winType: GE = 0, winSize: GE = 0 ) : GE =
      arExp( chain, winType, winSize )

   def ar( chain: GE, winType: GE = 0, winSize: GE = 0 ) : GE =
      arExp( chain, winType, winSize )

   def kr( chain: GE, winType: GE = 0, winSize: GE = 0 ) : GE =
      arExp( chain, winType, winSize )
}
case class IFFT( rate: Rate, chain: UGenIn, winType: UGenIn, winSize: UGenIn )
extends SingleOutUGen( chain, winType, winSize )

object PV_MagAbove extends UGen2RArgs {
   def apply( chain: GE, thresh: GE = 0 ) : GE = make( chain, thresh )
}
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes are above a given threshold.
 *
 * @param   chain the FFT'ed buffer
 * @param   thresh   magnitude threshold.
 */
case class PV_MagAbove( chain: UGenIn, thresh: UGenIn )
extends SingleOutUGen( chain, thresh ) with ControlRated

object PV_MagBelow extends UGen2RArgs {
   def apply( chain: GE, thresh: GE = 0 ) : GE = make( chain, thresh )
}
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes are below a given threshold.
 *
 * @param   chain the FFT'ed buffer
 * @param   thresh   magnitude threshold.
 */
case class PV_MagBelow( chain: UGenIn, thresh: UGenIn )
extends SingleOutUGen( chain, thresh ) with ControlRated

object PV_MagClip extends UGen2RArgs {
   def apply( chain: GE, thresh: GE = 0 ) : GE = make( chain, thresh )
}
/**
 * A phase vocoder UGen that limits (clips) the magnitude of the bins to a given threshold.
 *
 * @param   chain the FFT'ed buffer
 * @param   thresh   magnitude threshold. Each bin's magnitude is limited to
 *    be not greater than this threshold.
 */
case class PV_MagClip( chain: UGenIn, thresh: UGenIn )
extends SingleOutUGen( chain, thresh ) with ControlRated

object PV_LocalMax extends UGen2RArgs {
   def apply( chain: GE, thresh: GE = 0 ) : GE = make( chain, thresh )
}
/**
 * A phase vocoder UGen that passes only those bins whose magnitudes constitute local maxima.
 * Additionally, the given threshold is also used to filter out bins whose magnitude
 * lies below this threshold.
 *
 * @param   chain the FFT'ed buffer
 * @param   thresh   magnitude threshold used for general filtering, prior to
 *    the local-maximum-filtering
 */
case class PV_LocalMax( chain: UGenIn, thresh: UGenIn )
extends SingleOutUGen( chain, thresh ) with ControlRated

object PV_BinShift extends UGen3RArgs {
   def apply( chain: GE, stretch: GE = 1, shift: GE = 0 ) : GE = make( chain, stretch, shift )
}
/**
 * A phase vocoder UGen that stretches and shifts the spectrum.
 * It takes each bin, first stretches (scales) its position (bin number)
 * with a given factor, and then adds a shift to it.
 *
 * @param   chain the FFT'ed buffer
 * @param   stretch  the factor to multiply each bin position with, where 0.5 kind of
 *    transposes the signal down by an octave, and 2 transposes it up by an octove.
 * @param   shift    the translation of the spectrum, in number of bins. Since the
 *    FFT produces a linear frequency axis, the will produce harmonic distortion.
 */
case class PV_BinShift( chain: UGenIn, stretch: UGenIn, shift: UGenIn )
extends SingleOutUGen( chain, stretch, shift ) with ControlRated

object PV_MagShift extends UGen3RArgs {
   def apply( chain: GE, stretch: GE = 1, shift: GE = 0 ) : GE = make( chain, stretch, shift )
}
/**
 * A phase vocoder UGen that stretches and shifts the magnitudes of the spectrum.
 * This is live `PV_BinShift` but instead of scaling and shifting the whole complex
 * bins (magnitude and phase), this only operates on the magnitudes and leaves
 * the phases in their original bins.
 *
 * @param   chain the FFT'ed buffer
 * @param   stretch  the factor to multiply each bin position with
 * @param   shift    the translation of the spectrum, in number of bins
 */
case class PV_MagShift( chain: UGenIn, stretch: UGenIn, shift: UGenIn )
extends SingleOutUGen( chain, stretch, shift ) with ControlRated

object PV_MagSquared extends UGen1RArgs {
   def apply( chain: GE ) : GE = make( chain )
}
/**
 * A phase vocoder UGen that squares the magnitudes and re-normalizes to previous peak.
 * This makes weak bins weaker.
 *
 * @param   chain the FFT'ed buffer
 */
case class PV_MagSquared( chain: UGenIn )
extends SingleOutUGen( chain ) with ControlRated

object PV_MagNoise extends UGen1RArgs {
   def apply( chain: GE ) : GE = make( chain )
}
/**
 * A phase vocoder UGen that multiplies the magnitudes by
 * random noise.
 *
 * @param   chain the FFT'ed buffer
 */
case class PV_MagNoise( chain: UGenIn )
extends SingleOutUGen( chain ) with ControlRated

object PV_PhaseShift90 extends UGen1RArgs {
   def apply( chain: GE ) : GE = make( chain )
}
/**
 * A phase vocoder UGen that shift the phase of all bins by 90 degrees.
 *
 * @param   chain the FFT'ed buffer
 */
case class PV_PhaseShift90( chain: UGenIn )
extends SingleOutUGen( chain ) with ControlRated

object PV_PhaseShift270 extends UGen1RArgs {
   def apply( chain: GE ) : GE = make( chain )
}
/**
 * A phase vocoder UGen that shift the phase of all bins by 270 (or -90) degrees.
 *
 * @param   chain the FFT'ed buffer
 */
case class PV_PhaseShift270( chain: UGenIn )
extends SingleOutUGen( chain ) with ControlRated

object PV_Conj extends UGen1RArgs {
   def apply( chain: GE ) : GE = make( chain )
}
/**
 * A phase vocoder UGen that converts the bins into their
 * complex conjugate counterparts. The complex conjugate
 * is equal to the input, but with reversed sign of the imaginary part.
 *
 * @param   chain the FFT'ed buffer
 */
case class PV_Conj( chain: UGenIn )
extends SingleOutUGen( chain ) with ControlRated

object PV_PhaseShift extends UGen2RArgs {
   def apply( chain: GE, shift: GE ) : GE = make( chain, shift )
}
/**
 * A phase vocoder UGen that shifts the phase of each bins by a given amount.
 *
 * @param   chain the FFT'ed buffer
 * @param   shift phase shift in radians
 */
case class PV_PhaseShift( chain: UGenIn, shift: UGenIn )
extends SingleOutUGen( chain, shift ) with ControlRated

object PV_BrickWall extends UGen2RArgs {
   def apply( chain: GE, wipe: GE ) : GE = make( chain, wipe )
}
/**
 * A phase vocoder UGen that clears bins above or below a cutoff point.
 *
 * @param   chain the FFT'ed buffer
 * @param   wipe  can range between -1 and +1.
 *    if wipe == 0 then there is no effect.
 *    if  wipe > 0 then it acts like a high pass filter, clearing bins from the bottom up.
 *    if  wipe < 0 then it acts like a low pass filter, clearing bins from the top down.
 */
case class PV_BrickWall( chain: UGenIn, wipe: UGenIn )
extends SingleOutUGen( chain, wipe ) with ControlRated

object PV_BinWipe extends UGen3RArgs {
   def apply( chainA: GE, chainB: GE, wipe: GE ) : GE = make( chainA, chainB, wipe )
}
/**
 * A phase vocoder UGen that combine low and high bins from two inputs.
 * It does so by copiying low bins from one input and the high bins of the other,
 * thus realizes a kind of "wipe" between the two input signals.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 * @param   wipe     can range between -1 and +1.
 *    if wipe == 0 then the output is the same as inA.
 *    if  wipe > 0 then it begins replacing with bins from inB from the bottom up.
 *    if  wipe < 0 then it begins replacing with bins from inB from the top down.
 *
 * @see  [[de.sciss.synth.ugen.PV_RandWipe]]
 */
case class PV_BinWipe( chainA: UGenIn, chainB: UGenIn, wipe: UGenIn )
extends SingleOutUGen( chainA, chainB, wipe ) with ControlRated

object PV_MagMul extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that multiplies the magnitudes of two inputs and keeps the
 * phases of the first input.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_MagMul( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_CopyPhase extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that combines the magnitudes of first input and phases of the second input.
 * phases of the first input.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_CopyPhase( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_Copy extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that copies the spectral frames from chainA
 * to chainB. This allows for parallel processing of spectral data without the
 * need for multiple FFT UGens, and to copy out data at that point in the chain
 * for other purposes. chainA and chainB must be the same size. The output
 * will carry further chainA, so you chan insert the ugen at the appropriate
 * place in the signal chain.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_Copy( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_Max extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that outputs the bins with the maximum magnitude
 * of the two inputs.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_Max( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_Min extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that outputs the bins with the minimum magnitude
 * of the two inputs.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_Min( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_Mul extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that performs a complex multiplication of the
 * two inputs. The formula
 * is `(Re(A) * Re(B) - Im(A) * Im(B)) + i(Im(A) * Re(B) + Re(A) * Im(B))`.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_Mul( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_Div extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that performs a complex division of the
 * two inputs. Be careful that `chainB`, the divisor, does not
 * contain zeroes as they would obviously blow up the division.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_Div( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_Add extends UGen2RArgs {
   def apply( chainA: GE, chainB: GE ) : GE = make( chainA, chainB )
}
/**
 * A phase vocoder UGen that performs a complex addition of the
 * two inputs. The formula
 * is `(Re(A) + Re(B)) + i(Im(A) + Im(B))`.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 */
case class PV_Add( chainA: UGenIn, chainB: UGenIn )
extends SingleOutUGen( chainA, chainB ) with ControlRated

object PV_MagDiv extends UGen3RArgs {
   def apply( chainA: GE, chainB: GE, zeroes: GE = 0.0001f ) : GE = make( chainA, chainB, zeroes )
}
/**
 * A phase vocoder UGen that divides magnitudes of two inputs and keeps the
 * phases of the first input.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 * @param   zeroes   the noise floor to assume when detecting zero bins in
 *    chainB that would cause a division by zero and hence blow up. The ugen
 *    will use divide by this magnitude instead when zeroes are detected,
 *    resulting in a maximum boost of `zeroes.reciprocal`.
 */
case class PV_MagDiv( chainA: UGenIn, chainB: UGenIn, zeroes: UGenIn )
extends SingleOutUGen( chainA, chainB, zeroes ) with ControlRated

object PV_RandComb extends UGen3RArgs {
   def apply( chain: GE, wipe: GE = 0.5f, trig: GE = 1 ) : GE = make( chain, wipe, trig )
}
/**
 * A phase vocoder UGen that randomly clears out bins of the signal.
 * Which bins are wiped out is subject to a random choice (only the amount is
 * specified) that remains constant between triggers.
 *
 * @param   chain the FFT'ed buffer
 * @param   wipe  the probability (from 0 to 1) of bins being wiped out,
 *    hence 0 means no bins are wiped out, 1 means all bins are wiped out
 *    (output will be silence).
 * @param   trig    causes a new random bin selection to be made. a trigger
 *    occurs when passing from non-positive to positive value.
 */
case class PV_RandComb( chain: UGenIn, wipe: UGenIn, trig: UGenIn )
extends SingleOutUGen( chain, wipe, trig ) with ControlRated

object PV_RectComb extends UGen4RArgs {
   def apply( chain: GE, numTeeth: GE = 1, phase: GE = 0, width: GE = 0.5 ) : GE = make( chain, numTeeth, phase, width )
}
/**
 * A phase vocoder UGen that makes a series of gaps in a spectrum.
 * This is done by multiplying the spectrum with a kind of
 * rectangle wave that goes from zero to nyquist. The high slope
 * of the rectangle lets the input bins pass (quasi pass-band),
 * the low slope filteres them out (quasi stop-band).
 *
 * @param   chain    the FFT'ed buffer
 * @param   numTeeth the number of periods in the rectangle wave, where
 *    zero would mean the input signal is not affected, one means that
 *    there is exactly one period of the wave across the spectrum,
 *    hence one pass-band and one stop-band.
 * @param   phase    the phase offset of the rectangle wave, where
 *    1.0 is one full period. This is like the offset into the wavetable
 *    holding the rectangle, so a value of 0.25 means we start 25%
 *    into the basic waveform, and after 0.75 periods the next
 *    full period (high slope) begins.
 * @param   width    the pulse width between 0.0 (infinitely small
 *    high slope, so all bins filtered out) to 0.5 (half period is
 *    high slope, half period is low slope) to 1.0 (maximally
 *    wide high slope, no bins filtered out).
 *
 * @see  [[de.sciss.synth.ugen.PV_RectComb2]]
 */
case class PV_RectComb( chain: UGenIn, numTeeth: UGenIn, phase: UGenIn, width: UGenIn )
extends SingleOutUGen( chain, numTeeth, phase, width ) with ControlRated

object PV_RectComb2 extends UGen5RArgs {
   def apply( chainA: GE, chainB: GE, numTeeth: GE = 1, phase: GE = 0, width: GE = 0.5f ) : GE =
      make( chainA, chainB, numTeeth, phase, width )
}
/**
 * A phase vocoder UGen that switches between two input spectra
 * according to a rectangle wave.
 * This is basically identical to `PV_RectComb`, however during the
 * low slopes of the rectangle wavewave, instead of clearing out the bins,
 * it copies over the corresponding bins of the second fft input buffer.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 * @param   numTeeth the number of periods in the rectangle wave, where
 *    zero would mean the first input signal is fully passed through, one means that
 *    there is exactly one period of the wave across the spectrum,
 *    hence one pass-band (first signal passed through) and one stop-band
 *    (second signal passed through).
 * @param   phase    the phase offset of the rectangle wave, where
 *    1.0 is one full period. This is like the offset into the wavetable
 *    holding the rectangle, so a value of 0.25 means we start 25%
 *    into the basic waveform, and after 0.75 periods the next
 *    full period (high slope) begins.
 * @param   width    the pulse width between 0.0 (infinitely small
 *    high slope, so all bins are copied from the second input) to 0.5
 *    (half period is high slope -- copied from first input --, half period is
 *    low slope -- copied from second input) to 1.0 (maximally
 *    wide high slope, so all bins passed from the first input).
 *
 * @see  [[de.sciss.synth.ugen.PV_RectComb]]
 */
case class PV_RectComb2( chainA: UGenIn, chainB: UGenIn, numTeeth: UGenIn, phase: UGenIn, width: UGenIn )
extends SingleOutUGen( chainA, chainB, numTeeth, phase, width ) with ControlRated

object PV_RandWipe extends UGen4RArgs {
   def apply( chainA: GE, chainB: GE, wipe: GE = 0.5f, trig: GE = 1 ) : GE =
      make( chainA, chainB, wipe, trig )
}
/**
 * A phase vocoder UGen that crossfades between two input spectra
 * by taking bins randomly from them according to a given probability.
 *
 * @param   chainA   the first FFT'ed buffer (this gets replaced by
 *    the output signal)
 * @param   chainB   the second FFT'ed buffer
 * @param   wipe     the crossfader position from 0.0 (all bins are
 *    taken from `chainA`) to 1.0 (all bins are taken from `chainB`).
 *    For instance, if wipe is 0.5, half of the bins are taken from
 *    either input. The decision whether a bin is taken from A or B
 *    is random, however remains constant between two triggers.
 * @param   trig    a signal the triggers the re-newed process of
 *    determining for each bin whether it will be taken from input
 *    A or B. A trigger occurs when passing from non-positive to
 *    positive value.
 *
 * @see  [[de.sciss.synth.ugen.PV_BinWipe]]
 */
case class PV_RandWipe( chainA: UGenIn, chainB: UGenIn, wipe: UGenIn, trig: UGenIn )
extends SingleOutUGen( chainA, chainB, wipe, trig ) with ControlRated

object PV_Diffuser extends UGen2RArgs {
   def apply( chain: GE, thresh: GE = 1 ) : GE = make( chain, thresh )
}
/**
 * A phase vocoder UGen that adds a different constant random phase shift to each bin.
 * The trigger will select a new set of random phases.
 *
 * @param   chain the FFT'ed buffer
 * @param   trig  to trigger a new selection of random phases. A trigger occurs when
 *    passing from non-positive to positive value.
 */
case class PV_Diffuser( chain: UGenIn, thresh: UGenIn )
extends SingleOutUGen( chain, thresh ) with ControlRated

object PV_MagFreeze extends UGen2RArgs {
   def apply( chain: GE, freeze: GE = 1 ) : GE = make( chain, freeze )
}
/**
 * A phase vocoder UGen that freezes the magnitudes at current levels.
 * Freezing happens when the freeze input has a value of > 0.
 *
 * @param   chain    the FFT'ed buffer
 * @param   freeze   whether the current levels are frozen (> 0) or not (0).
 */
case class PV_MagFreeze( chain: UGenIn, freeze: UGenIn )
extends SingleOutUGen( chain, freeze ) with ControlRated

object PV_BinScramble extends UGen4RArgs {
   def apply( chain: GE, wipe: GE = 0.5f, width: GE = 0.2f, trig: GE = 1 ) : GE = make( chain, wipe, width, trig )
}
/**
 * A phase vocoder UGen that randomizes the order of the bins.
 * The trigger will select a new random ordering.
 *
 * @param   chain the FFT'ed buffer
 * @param   wipe  the amount of bins scrambled, from 0 (none) to 1 (all bins scrambled).
 * @param   width  a value from zero to one, indicating the maximum randomized distance of a bin from its
 *    original location in the spectrum.
 * @param   trig    causes a new random bin re-ordering to be made. a trigger
 *    occurs when passing from non-positive to positive value.
 */
case class PV_BinScramble( chain: UGenIn, wipe: UGenIn, width: UGenIn, trig: UGenIn )
extends SingleOutUGen( chain, wipe, width, trig ) with ControlRated

object FFTTrigger extends UGen3RArgs {
   def apply( buf: GE, hop: GE = 0.5f, polar: GE = 0 ) : GE = make( buf, hop, polar )
}
/**
 * A phase vocoder UGen that takes a buffer and prepares it to be used in FFT chains,
 * without doing an actual FFT on a signal. This is useful if you want to provide
 * a buffer whose content had already been transformed into the Fourier domain.
 *
 * @param   buf   the identifier of the buffer to use
 * @param   hop   the hop size for timing triggers
 * @param   polar whether the complex buffer content is given in cartesian coordinates (0) or
 *    in polar coordinates (1)
 */
case class FFTTrigger( buf: UGenIn, hop: UGenIn, polar: UGenIn )
extends SingleOutUGen( buf, hop, polar ) with ControlRated
