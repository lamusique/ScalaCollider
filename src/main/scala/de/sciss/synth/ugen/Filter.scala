/*
 *  Filter.scala
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

import de.sciss.synth.{ GE, Rate, SideEffectUGen, SingleOutUGen, UGenIn }

/**
 * 	@version	0.13, 16-Aug-10
 */
object Resonz extends UGen3Args {
  def ar( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = arExp( in, freq, rq )
  def kr( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = krExp( in, freq, rq )
}
case class Resonz( rate: Rate, in: UGenIn, freq: UGenIn, rq: UGenIn )
extends SingleOutUGen( in, freq, rq )

object OnePole extends UGen2Args {
	def ar( in: GE, coeff: GE = 0.5f ) : GE = arExp( in, coeff )
	def kr( in: GE, coeff: GE = 0.5f ) : GE = krExp( in, coeff )
}
/**
 * A one pole (IIR) filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = ((1 - abs(coef)) * in(i)) + (coef * out(i-1))
 * }}}
 *
 * @param   in    input signal to be processed
 * @param   coeff feedback coefficient. Should be between -1 and +1
 *
 * @see  [[de.sciss.synth.ugen.OneZero]]
 * @see  [[de.sciss.synth.ugen.Lag]]
 */
case class OnePole( rate: Rate, in: UGenIn, coeff: UGenIn )
extends SingleOutUGen( in, coeff )

object OneZero extends UGen2Args {
	def ar( in: GE, coeff: GE = 0.5f ) : GE = arExp( in, coeff )
	def kr( in: GE, coeff: GE = 0.5f ) : GE = krExp( in, coeff )
}
/**
 * A one zero (FIR) filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = ((1 - abs(coef)) * in(i)) + (coef * in(i-1))
 * }}}
 *
 * @param   in    input signal to be processed
 * @param   coeff feed forward coefficient. +0.5 makes a two point averaging filter (see also `LPZ1`),
 *    -0.5 makes a differentiator (see also `HPZ1`),  +1 makes a single sample delay (see also `Delay1`),
 *    -1 makes an inverted single sample delay.
 *
 * @see  [[de.sciss.synth.ugen.OnePole]]
 * @see  [[de.sciss.synth.ugen.LPZ1]]
 * @see  [[de.sciss.synth.ugen.HPZ1]]
 * @see  [[de.sciss.synth.ugen.Delay1]] 
 * @see  [[de.sciss.synth.ugen.Integrator]] 
 */
case class OneZero( rate: Rate, in: UGenIn, coeff: UGenIn )
extends SingleOutUGen( in, coeff )

object TwoPole extends UGen3Args {
	def ar( in: GE, freq: GE = 440, radius: GE = 0.8f ) : GE = arExp( in, freq, radius )
	def kr( in: GE, freq: GE = 440, radius: GE = 0.8f ) : GE = krExp( in, freq, radius )
}
case class TwoPole( rate: Rate, in: UGenIn, freq: UGenIn, radius: UGenIn )
extends SingleOutUGen( in, freq, radius )

object TwoZero extends UGen3Args {
	def ar( in: GE, freq: GE = 440, radius: GE = 0.8f ) : GE = arExp( in, freq, radius )
	def kr( in: GE, freq: GE = 440, radius: GE = 0.8f ) : GE = krExp( in, freq, radius )
}
case class TwoZero( rate: Rate, in: UGenIn, freq: UGenIn, radius: UGenIn )
extends SingleOutUGen( in, freq, radius )

object APF extends UGen3Args {
	def ar( in: GE, freq: GE = 440, radius: GE = 0.8f ) : GE = arExp( in, freq, radius )
	def kr( in: GE, freq: GE = 440, radius: GE = 0.8f ) : GE = krExp( in, freq, radius )
}
case class APF( rate: Rate, in: UGenIn, freq: UGenIn, radius: UGenIn )
extends SingleOutUGen( in, freq, radius )

object Integrator extends UGen2Args {
	def ar( in: GE, coeff: GE = 1 ) : GE = arExp( in, coeff )
	def kr( in: GE, coeff: GE = 1 ) : GE = krExp( in, coeff )
}
/**
 * A filter UGen to integrate an input signal with a leak.
 * Implements the formula :
 * {{{
 * out(i) = in(i) + (coef * out(i-1))
 * }}}
 *
 * @param   in    input signal to be processed
 * @param   coeff the leak coefficient. Should be between -1 and +1
 *
 * @see  [[de.sciss.synth.ugen.OnePole]]
 * @see  [[de.sciss.synth.ugen.HPZ1]]
 * @see  [[de.sciss.synth.ugen.LPZ1]]
 */
case class Integrator( rate: Rate, in: UGenIn, coeff: UGenIn )
extends SingleOutUGen( in, coeff )

object Decay extends UGen2Args {
	def ar( in: GE, time: GE = 1 ) : GE = arExp( in, time )
	def kr( in: GE, time: GE = 1 ) : GE = krExp( in, time )
}
case class Decay( rate: Rate, in: UGenIn, time: UGenIn )
extends SingleOutUGen( in, time )

object Decay2 extends UGen3Args {
	def ar( in: GE, attack: GE = 0.01f, release: GE = 1 ) : GE =
      arExp( in, attack, release )
	def kr( in: GE, attack: GE = 0.01f, release: GE = 1 ) : GE =
      krExp( in, attack, release )
}
case class Decay2( rate: Rate, in: UGenIn, attack: UGenIn, release: UGenIn )
extends SingleOutUGen( in, attack, release )

object Lag extends UGen2Args {
	def ar( in: GE, time: GE = 0.1f ) : GE = arExp( in, time )
	def kr( in: GE, time: GE = 0.1f ) : GE = krExp( in, time )
}
/**
 * An exponential lag UGen.
 * This is essentially the same as `OnePole` except that instead of supplying the coefficient directly,
 * it is calculated from a 60 dB lag time. This is the time required for the filter to converge to
 * within 0.01 % of a value. This is useful for smoothing out control signals.
 *
 * @param   in    input signal.
 * @param   time  60 dB lag time in seconds.  
 *
 * @see  [[de.sciss.synth.ugen.OnePole]]
 * @see  [[de.sciss.synth.ugen.LagUD]]
 * @see  [[de.sciss.synth.ugen.Lag2]] 
 * @see  [[de.sciss.synth.ugen.Ramp]] 
 */
case class Lag( rate: Rate, in: UGenIn, time: UGenIn )
extends SingleOutUGen( in, time )

object Lag2 extends UGen2Args {
	def ar( in: GE, time: GE = 0.1f ) : GE = arExp( in, time )
	def kr( in: GE, time: GE = 0.1f ) : GE = krExp( in, time )
}
/**
 * A cascaded exponential lag
 * UGen. `Lag2.kr(in, time)` is equivalent to `Lag.kr(Lag.kr(in, time), time)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factor once instead of twice.
 *
 * @param   in    input signal.
 * @param   time  60 dB lag time in seconds.  
 *
 * @see  [[de.sciss.synth.ugen.Lag]]
 * @see  [[de.sciss.synth.ugen.Lag2UD]]
 * @see  [[de.sciss.synth.ugen.Lag3]]
 */
case class Lag2( rate: Rate, in: UGenIn, time: UGenIn )
extends SingleOutUGen( in, time )

object Lag3 extends UGen2Args {
	def ar( in: GE, time: GE = 0.1f ) : GE = arExp( in, time )
	def kr( in: GE, time: GE = 0.1f ) : GE = krExp( in, time )
}
/**
 * A cascaded exponential lag
 * UGen. `Lag3.kr(in, time)` is equivalent to `Lag.kr(Lag.kr(Lag.kr(Lag.kr(in, time), time), time)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factor once instead of three times.
 *
 * @param   in    input signal.
 * @param   time  60 dB lag time in seconds.  
 *
 * @see  [[de.sciss.synth.ugen.Lag]]
 * @see  [[de.sciss.synth.ugen.Lag3UD]]
 * @see  [[de.sciss.synth.ugen.Lag2]]
 */
case class Lag3( rate: Rate, in: UGenIn, time: UGenIn )
extends SingleOutUGen( in, time )

object Ramp extends UGen2Args {
	def ar( in: GE, dur: GE = 0.1f ) : GE = arExp( in, dur )
	def kr( in: GE, dur: GE = 0.1f ) : GE = krExp( in, dur )
}
/**
 * A UGen which produces a linear lag (time smear) regarding
 * and input signal. Other than `Lag` which is a feedback
 * filter with exponential decay, `Ramp` applies a linear
 * ramp. This is achieved by sampling the input signal
 * at regular intervals given by the `lagTime` and starting
 * a new line segment after each interval.
 *
 * @param   in    the signal to smooth out
 * @param   dur   the ramp-time (seconds) which is also the
 *                interval of the sampling
 *
 * @see  [[de.sciss.synth.ugen.Lag]]
 * @see  [[de.sciss.synth.ugen.Sweep]]
 */
case class Ramp( rate: Rate, in: UGenIn, dur: UGenIn )
extends SingleOutUGen( in, dur )

object LagUD extends UGen3Args {
	def ar( in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f ) : GE =
      arExp( in, timeUp, timeDown )
	def kr( in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f ) : GE =
      krExp( in, timeUp, timeDown )
}
/**
 * An exponential lag UGen with separate inputs for up and down slope.
 * This is essentially the same as `Lag` except that you can supply a different 60 dB time
 * for when the signal goes up, from when the signal goes down.
 *
 * @param   in       input signal
 * @param   timeUp   60 dB lag time in seconds effective during a rising slope in the input signal
 * @param   timeDown 60 dB lag time in seconds effective during a falling slope in the input signal
 *
 * @see  [[de.sciss.synth.ugen.Lag]]
 * @see  [[de.sciss.synth.ugen.Lag2UD]]
 */
case class LagUD( rate: Rate, in: UGenIn, timeUp: UGenIn, timeDown: UGenIn )
extends SingleOutUGen( in, timeUp, timeDown )

object Lag2UD extends UGen3Args {
	def ar( in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f ) : GE =
      arExp( in, timeUp, timeDown )
	def kr( in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f ) : GE =
      krExp( in, timeUp, timeDown )
}
/**
 * A cascaded exponential lag UGen with separate inputs for up and down
 * slope. `Lag2UD.kr(in, up, down)` is equivalent to `LagUD.kr(LagUD.kr(in, up, down), up, down)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factors once instead of twice.
 *
 * @param   in    input signal
 * @param   timeUp   60 dB lag time in seconds effective during a rising slope in the input signal
 * @param   timeDown 60 dB lag time in seconds effective during a falling slope in the input signal
 *
 * @see  [[de.sciss.synth.ugen.LagUD]]
 * @see  [[de.sciss.synth.ugen.Lag2]]
 * @see  [[de.sciss.synth.ugen.Lag3UD]]
 */
case class Lag2UD( rate: Rate, in: UGenIn, timeUp: UGenIn, timeDown: UGenIn )
extends SingleOutUGen( in, timeUp, timeDown )

object Lag3UD extends UGen3Args {
	def ar( in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f ) : GE =
      arExp( in, timeUp, timeDown )
	def kr( in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f ) : GE =
      krExp( in, timeUp, timeDown )
}
/**
 * A cascaded exponential lag UGen with separate inputs for up and down
 * slope. `Lag3UD.kr(in, up, down)` is equivalent to `LagUD.kr(LagUD.kr(LagUD.kr(in, up, down), up, down), up, down)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factors once instead of three times.
 *
 * @param   in    input signal
 * @param   timeUp   60 dB lag time in seconds effective during a rising slope in the input signal
 * @param   timeDown 60 dB lag time in seconds effective during a falling slope in the input signal
 *
 * @see  [[de.sciss.synth.ugen.LagUD]]
 * @see  [[de.sciss.synth.ugen.Lag3]]
 * @see  [[de.sciss.synth.ugen.Lag2UD]]
 */
case class Lag3UD( rate: Rate, in: UGenIn, timeUp: UGenIn, timeDown: UGenIn )
extends SingleOutUGen( in, timeUp, timeDown )

object LeakDC extends UGen2Args {
	def ar( in: GE, coeff: GE = 0.995f ) : GE = arExp( in, coeff )
	def kr( in: GE, coeff: GE = 0.9f ) : GE = krExp( in, coeff )
}
case class LeakDC( rate: Rate, in: UGenIn, coeff: UGenIn )
extends SingleOutUGen( in, coeff )

object RLPF extends UGen3Args {
  def ar( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = arExp( in, freq, rq )
  def kr( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = krExp( in, freq, rq )
}
case class RLPF( rate: Rate, in: UGenIn, freq: UGenIn, rq: UGenIn )
extends SingleOutUGen( in, freq, rq )

object RHPF extends UGen3Args {
  def ar( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = arExp( in, freq, rq )
  def kr( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = krExp( in, freq, rq )
}
case class RHPF( rate: Rate, in: UGenIn, freq: UGenIn, rq: UGenIn )
extends SingleOutUGen( in, freq, rq )

object LPF extends UGen2Args {
  def ar( in: GE, freq: GE = 440 ) : GE = arExp( in, freq )
  def kr( in: GE, freq: GE = 440 ) : GE = krExp( in, freq )
}
case class LPF( rate: Rate, in: UGenIn, freq: UGenIn )
extends SingleOutUGen( in, freq )

object HPF extends UGen2Args {
  def ar( in: GE, freq: GE = 440 ) : GE = arExp( in, freq )
  def kr( in: GE, freq: GE = 440 ) : GE = krExp( in, freq )
}
case class HPF( rate: Rate, in: UGenIn, freq: UGenIn )
extends SingleOutUGen( in, freq )

object BPF extends UGen3Args {
  def ar( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = arExp( in, freq, rq )
  def kr( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = krExp( in, freq, rq )
}
case class BPF( rate: Rate, in: UGenIn, freq: UGenIn, rq: UGenIn )
extends SingleOutUGen( in, freq, rq )

object BRF extends UGen3Args {
  def ar( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = arExp( in, freq, rq )
  def kr( in: GE, freq: GE = 440, rq: GE = 1 ) : GE = krExp( in, freq, rq )
}
case class BRF( rate: Rate, in: UGenIn, freq: UGenIn, rq: UGenIn )
extends SingleOutUGen( in, freq, rq )

object MidEQ extends UGen4Args {
  def ar( in: GE, freq: GE = 440, rq: GE = 1, db: GE = 0 ) : GE =
    arExp( in, freq, rq, db )
  def kr( in: GE, freq: GE = 440, rq: GE = 1, db: GE = 0 ) : GE =
    krExp( in, freq, rq, db )
}
case class MidEQ( rate: Rate, in: UGenIn, freq: UGenIn, rq: UGenIn, db: UGenIn )
extends SingleOutUGen( in, freq, rq, db )

object LPZ1 extends UGen1Args {
	def ar( in: GE ) : GE = arExp( in )
	def kr( in: GE ) : GE = krExp( in )
}
/**
 * A two point average filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = 0.5 * (in(i) + in(i-1))
 * }}}
 *
 * @param   in    input signal to be processed
 *
 * @see  [[de.sciss.synth.ugen.OnePole]]
 * @see  [[de.sciss.synth.ugen.HPZ1]]
 * @see  [[de.sciss.synth.ugen.Integrator]]
 */
case class LPZ1( rate: Rate, in: UGenIn )
extends SingleOutUGen( in )

object HPZ1 extends UGen1Args {
	def ar( in: GE ) : GE = arExp( in )
	def kr( in: GE ) : GE = krExp( in )
}
/**
 * A two point difference filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = 0.5 * (in(i) - in(i-1))
 * }}}
 *
 * @param   in    input signal to be processed
 *
 * @see  [[de.sciss.synth.ugen.OneZero]]
 * @see  [[de.sciss.synth.ugen.LPZ1]]
 */
case class HPZ1( rate: Rate, in: UGenIn )
extends SingleOutUGen( in )

object Slope extends UGen1Args {
	def ar( in: GE ) : GE = arExp( in )
	def kr( in: GE ) : GE = krExp( in )
}
/**
 * A UGen measuring the slope of signal.
 * It calculates the rate of change per second of a signal, as given by the following formula:
 * {{{
 * out(i) = (in(i) - in(i-1)) * sampleRate
 * }}}
 * It thus equal to `HPZ1.ar(_) * 2 * SampleRate.ir`
 *
 * @param   in    input signal to be processed
 *
 * @see  [[de.sciss.synth.ugen.HPZ1]]
 */
case class Slope( rate: Rate, in: UGenIn )
extends SingleOutUGen( in )

object LPZ2 extends UGen1Args {
	def ar( in: GE ) : GE = arExp( in )
	def kr( in: GE ) : GE = krExp( in )
}
case class LPZ2( rate: Rate, in: UGenIn )
extends SingleOutUGen( in )

object HPZ2 extends UGen1Args {
	def ar( in: GE ) : GE = arExp( in )
	def kr( in: GE ) : GE = krExp( in )
}
case class HPZ2( rate: Rate, in: UGenIn )
extends SingleOutUGen( in )

object BPZ2 extends UGen1Args {
	def ar( in: GE ) : GE = arExp( in )
	def kr( in: GE ) : GE = krExp( in )
}
case class BPZ2( rate: Rate, in: UGenIn )
extends SingleOutUGen( in )

object BRZ2 extends UGen1Args {
	def ar( in: GE ) : GE = arExp( in )
	def kr( in: GE ) : GE = krExp( in )
}
case class BRZ2( rate: Rate, in: UGenIn )
extends SingleOutUGen( in )

object Median extends UGen2Args {
  def ar( length: GE = 3, in: GE ) : GE = arExp( length, in )
  def kr( length: GE = 3, in: GE ) : GE = krExp( length, in )
}
case class Median( rate: Rate, length: UGenIn, in: UGenIn )
extends SingleOutUGen( length, in )

object Slew extends UGen3Args {
  def ar( in: GE, up: GE = 1, down: GE = 1 ) : GE = arExp( in, up, down )
  def kr( in: GE, up: GE = 1, down: GE = 1 ) : GE = krExp( in, up, down )
}
/**
 * A slew rate limiter UGen.
 * Limits the slope of an input signal. The slope is expressed in units per second.
 *
 * @param   in    input signal.
 * @param   up    maximum upward slope.
 * @param   down  maximum downward slope. 
 */
case class Slew( rate: Rate, in: UGenIn, up: UGenIn, down: UGenIn )
extends SingleOutUGen( in, up, down )

object FOS extends UGen4Args {
  def ar( in: GE, a0: GE = 0, a1: GE = 0, b1: GE = 0 ) : GE =
    arExp( in, a0, a1, b1 )

  def kr( in: GE, a0: GE = 0, a1: GE = 0, b1: GE = 0 ) : GE =
    krExp( in, a0, a1, b1 )
}
case class FOS( rate: Rate, in: UGenIn, a0: UGenIn, a1: UGenIn, b1: UGenIn )
extends SingleOutUGen( in, a0, a1, b1)

object SOS extends UGen6Args {
  def ar( in: GE, a0: GE = 0, a1: GE = 0, a2: GE = 0, b1: GE = 0, b2: GE = 0 ) : GE =
    arExp( in, a0, a1, a2, b1, b2 )

  def kr( in: GE, a0: GE = 0, a1: GE = 0, a2: GE = 0, b1: GE = 0, b2: GE = 0 ) : GE =
    krExp( in, a0, a1, a2, b1, b2 )
}
case class SOS( rate: Rate, in: UGenIn, a0: UGenIn, a1: UGenIn, a2: UGenIn,
                b1: UGenIn, b2: UGenIn )
extends SingleOutUGen( in, a0, a1, a2, b1, b2 )

object Ringz extends UGen4Args {
  def ar( in: GE, freq: GE = 440, attack: GE = 1, decay: GE = 1 ) : GE =
    arExp( in, freq, attack, decay )

  def kr( in: GE, freq: GE = 440, attack: GE = 1, decay: GE = 1 ) : GE =
    krExp( in, freq, attack, decay )
}
case class Ringz( rate: Rate, in: UGenIn, freq: UGenIn, attack: UGenIn, decay: UGenIn )
extends SingleOutUGen( in, freq, attack, decay )

object Formlet extends UGen4Args {
  def ar( in: GE, freq: GE = 440, attack: GE = 1, decay: GE = 1 ) : GE =
    arExp( in, freq, attack, decay )

  def kr( in: GE, freq: GE = 440, attack: GE = 1, decay: GE = 1 ) : GE =
    krExp( in, freq, attack, decay )
}
case class Formlet( rate: Rate, in: UGenIn, freq: UGenIn, attack: UGenIn, decay: UGenIn )
extends SingleOutUGen( in, freq, attack, decay )

object DetectSilence extends UGen4Args {
  def ar( in: GE, amp: GE = 0.0001f, dur: GE = 0.1f, doneAction: GE = 0 ) : GE =
    arExp( in, amp, dur, doneAction )

  def kr( in: GE, amp: GE = 0.0001f, dur: GE = 0.1f, doneAction: GE = 0 ) : GE =
    krExp( in, amp, dur, doneAction )
}
case class DetectSilence( rate: Rate, in: UGenIn, amp: UGenIn, dur: UGenIn,
                          doneAction: UGenIn )
extends SingleOutUGen( in, amp, dur, doneAction ) with SideEffectUGen // side-effect: done-action

