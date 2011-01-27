/*
 * FilterUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 27 20:56:40 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
/**
 * A UGen which produces a linear lag (time smear) regarding
 * and input signal. Other than `Lag` which is a feedback
 * filter with exponential decay, `Ramp` applies a linear
 * ramp. This is achieved by sampling the input signal
 * at regular intervals given by the `lagTime` and starting
 * a new line segment after each interval.
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Sweep]]
 */
object Ramp {
   
/**
 * @param in              the signal to smooth out
 * @param dur             the ramp-time (seconds) which is also the interval of the sampling
 */
def kr(in: GE[control, UGenIn[control]], dur: AnyGE = 0.1f) = apply[control](control, in, dur)
/**
 * @param in              the signal to smooth out
 * @param dur             the ramp-time (seconds) which is also the interval of the sampling
 */
def ar(in: GE[audio, UGenIn[audio]], dur: AnyGE = 0.1f) = apply[audio](audio, in, dur)
}
/**
 * A UGen which produces a linear lag (time smear) regarding
 * and input signal. Other than `Lag` which is a feedback
 * filter with exponential decay, `Ramp` applies a linear
 * ramp. This is achieved by sampling the input signal
 * at regular intervals given by the `lagTime` and starting
 * a new line segment after each interval.
 * 
 * @param in              the signal to smooth out
 * @param dur             the ramp-time (seconds) which is also the interval of the sampling
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Sweep]]
 */
case class Ramp[R <: Rate](rate: R, in: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R, RampUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _sz_in = _in.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => RampUGen(rate, _in(i.%(_sz_in)), _dur(i.%(_sz_dur))))
   }
}
case class RampUGen[R <: Rate](rate: R, in: AnyUGenIn, dur: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, dur))
/**
 * An exponential lag UGen.
 * This is essentially the same as `OnePole` except that instead of supplying the coefficient directly,
 * it is calculated from a 60 dB lag time. This is the time required for the filter to converge to
 * within 0.01 % of a value. This is useful for smoothing out control signals.
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.LagUD]]
 * @see [[de.sciss.synth.ugen.Lag2]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
object Lag {
   
/**
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 */
def kr(in: GE[control, UGenIn[control]], time: AnyGE = 0.1f) = apply[control](control, in, time)
/**
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 */
def ar(in: GE[audio, UGenIn[audio]], time: AnyGE = 0.1f) = apply[audio](audio, in, time)
}
/**
 * An exponential lag UGen.
 * This is essentially the same as `OnePole` except that instead of supplying the coefficient directly,
 * it is calculated from a 60 dB lag time. This is the time required for the filter to converge to
 * within 0.01 % of a value. This is useful for smoothing out control signals.
 * 
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.LagUD]]
 * @see [[de.sciss.synth.ugen.Lag2]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
case class Lag[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R, LagUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _time: IIdxSeq[AnyUGenIn] = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => LagUGen(rate, _in(i.%(_sz_in)), _time(i.%(_sz_time))))
   }
}
case class LagUGen[R <: Rate](rate: R, in: AnyUGenIn, time: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, time))
/**
 * A cascaded exponential lag
 * UGen. `Lag2.kr(in, time)` is equivalent to `Lag.kr(Lag.kr(in, time), time)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factor once instead of twice.
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Lag2UD]]
 * @see [[de.sciss.synth.ugen.Lag3]]
 */
object Lag2 {
   
/**
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 */
def kr(in: GE[control, UGenIn[control]], time: AnyGE = 0.1f) = apply[control](control, in, time)
/**
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 */
def ar(in: GE[audio, UGenIn[audio]], time: AnyGE = 0.1f) = apply[audio](audio, in, time)
}
/**
 * A cascaded exponential lag
 * UGen. `Lag2.kr(in, time)` is equivalent to `Lag.kr(Lag.kr(in, time), time)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factor once instead of twice.
 * 
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Lag2UD]]
 * @see [[de.sciss.synth.ugen.Lag3]]
 */
case class Lag2[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R, Lag2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _time: IIdxSeq[AnyUGenIn] = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => Lag2UGen(rate, _in(i.%(_sz_in)), _time(i.%(_sz_time))))
   }
}
case class Lag2UGen[R <: Rate](rate: R, in: AnyUGenIn, time: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, time))
/**
 * A cascaded exponential lag
 * UGen. `Lag3.kr(in, time)` is equivalent to `Lag.kr(Lag.kr(Lag.kr(Lag.kr(in, time), time), time)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factor once instead of three times.
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Lag3UD]]
 * @see [[de.sciss.synth.ugen.Lag2]]
 */
object Lag3 {
   
/**
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 */
def kr(in: GE[control, UGenIn[control]], time: AnyGE = 0.1f) = apply[control](control, in, time)
/**
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 */
def ar(in: GE[audio, UGenIn[audio]], time: AnyGE = 0.1f) = apply[audio](audio, in, time)
}
/**
 * A cascaded exponential lag
 * UGen. `Lag3.kr(in, time)` is equivalent to `Lag.kr(Lag.kr(Lag.kr(Lag.kr(in, time), time), time)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factor once instead of three times.
 * 
 * @param in              input signal.
 * @param time            60 dB lag time in seconds.
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Lag3UD]]
 * @see [[de.sciss.synth.ugen.Lag2]]
 */
case class Lag3[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R, Lag3UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _time: IIdxSeq[AnyUGenIn] = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => Lag3UGen(rate, _in(i.%(_sz_in)), _time(i.%(_sz_time))))
   }
}
case class Lag3UGen[R <: Rate](rate: R, in: AnyUGenIn, time: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, time))
/**
 * An exponential lag UGen with separate inputs for up and down slope.
 * This is essentially the same as `Lag` except that you can supply a different 60 dB time
 * for when the signal goes up, from when the signal goes down.
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Lag2UD]]
 */
object LagUD {
   
/**
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 */
def kr(in: GE[control, UGenIn[control]], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[control](control, in, timeUp, timeDown)
/**
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 */
def ar(in: GE[audio, UGenIn[audio]], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[audio](audio, in, timeUp, timeDown)
}
/**
 * An exponential lag UGen with separate inputs for up and down slope.
 * This is essentially the same as `Lag` except that you can supply a different 60 dB time
 * for when the signal goes up, from when the signal goes down.
 * 
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 * 
 * @see [[de.sciss.synth.ugen.Lag]]
 * @see [[de.sciss.synth.ugen.Lag2UD]]
 */
case class LagUD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends SingleOutUGenSource[R, LagUDUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _timeUp: IIdxSeq[AnyUGenIn] = timeUp.expand
      val _timeDown: IIdxSeq[AnyUGenIn] = timeDown.expand
      val _sz_in = _in.size
      val _sz_timeUp = _timeUp.size
      val _sz_timeDown = _timeDown.size
      val _exp_ = maxInt(_sz_in, _sz_timeUp, _sz_timeDown)
      IIdxSeq.tabulate(_exp_)(i => LagUDUGen(rate, _in(i.%(_sz_in)), _timeUp(i.%(_sz_timeUp)), _timeDown(i.%(_sz_timeDown))))
   }
}
case class LagUDUGen[R <: Rate](rate: R, in: AnyUGenIn, timeUp: AnyUGenIn, timeDown: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, timeUp, timeDown))
/**
 * A cascaded exponential lag UGen with separate inputs for up and down
 * slope. `Lag2UD.kr(in, up, down)` is equivalent to `LagUD.kr(LagUD.kr(in, up, down), up, down)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factors once instead of twice.
 * 
 * @see [[de.sciss.synth.ugen.LagUD]]
 * @see [[de.sciss.synth.ugen.Lag2]]
 * @see [[de.sciss.synth.ugen.Lag3UD]]
 */
object Lag2UD {
   
/**
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 */
def kr(in: GE[control, UGenIn[control]], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[control](control, in, timeUp, timeDown)
/**
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 */
def ar(in: GE[audio, UGenIn[audio]], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[audio](audio, in, timeUp, timeDown)
}
/**
 * A cascaded exponential lag UGen with separate inputs for up and down
 * slope. `Lag2UD.kr(in, up, down)` is equivalent to `LagUD.kr(LagUD.kr(in, up, down), up, down)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factors once instead of twice.
 * 
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 * 
 * @see [[de.sciss.synth.ugen.LagUD]]
 * @see [[de.sciss.synth.ugen.Lag2]]
 * @see [[de.sciss.synth.ugen.Lag3UD]]
 */
case class Lag2UD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends SingleOutUGenSource[R, Lag2UDUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _timeUp: IIdxSeq[AnyUGenIn] = timeUp.expand
      val _timeDown: IIdxSeq[AnyUGenIn] = timeDown.expand
      val _sz_in = _in.size
      val _sz_timeUp = _timeUp.size
      val _sz_timeDown = _timeDown.size
      val _exp_ = maxInt(_sz_in, _sz_timeUp, _sz_timeDown)
      IIdxSeq.tabulate(_exp_)(i => Lag2UDUGen(rate, _in(i.%(_sz_in)), _timeUp(i.%(_sz_timeUp)), _timeDown(i.%(_sz_timeDown))))
   }
}
case class Lag2UDUGen[R <: Rate](rate: R, in: AnyUGenIn, timeUp: AnyUGenIn, timeDown: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, timeUp, timeDown))
/**
 * A cascaded exponential lag UGen with separate inputs for up and down
 * slope. `Lag3UD.kr(in, up, down)` is equivalent to `LagUD.kr(LagUD.kr(LagUD.kr(in, up, down), up, down), up, down)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factors once instead of three times.
 * 
 * @see [[de.sciss.synth.ugen.LagUD]]
 * @see [[de.sciss.synth.ugen.Lag3]]
 * @see [[de.sciss.synth.ugen.Lag2UD]]
 */
object Lag3UD {
   
/**
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 */
def kr(in: GE[control, UGenIn[control]], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[control](control, in, timeUp, timeDown)
/**
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 */
def ar(in: GE[audio, UGenIn[audio]], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[audio](audio, in, timeUp, timeDown)
}
/**
 * A cascaded exponential lag UGen with separate inputs for up and down
 * slope. `Lag3UD.kr(in, up, down)` is equivalent to `LagUD.kr(LagUD.kr(LagUD.kr(in, up, down), up, down), up, down)`,
 * thus resulting in a smoother transition. This saves on CPU as you only have to
 * calculate the decay factors once instead of three times.
 * 
 * @param in              input signal.
 * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
 * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
 * 
 * @see [[de.sciss.synth.ugen.LagUD]]
 * @see [[de.sciss.synth.ugen.Lag3]]
 * @see [[de.sciss.synth.ugen.Lag2UD]]
 */
case class Lag3UD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends SingleOutUGenSource[R, Lag3UDUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _timeUp: IIdxSeq[AnyUGenIn] = timeUp.expand
      val _timeDown: IIdxSeq[AnyUGenIn] = timeDown.expand
      val _sz_in = _in.size
      val _sz_timeUp = _timeUp.size
      val _sz_timeDown = _timeDown.size
      val _exp_ = maxInt(_sz_in, _sz_timeUp, _sz_timeDown)
      IIdxSeq.tabulate(_exp_)(i => Lag3UDUGen(rate, _in(i.%(_sz_in)), _timeUp(i.%(_sz_timeUp)), _timeDown(i.%(_sz_timeDown))))
   }
}
case class Lag3UDUGen[R <: Rate](rate: R, in: AnyUGenIn, timeUp: AnyUGenIn, timeDown: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, timeUp, timeDown))
/**
 * A one pole (IIR) filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = ((1 - abs(coef)) * in(i)) + (coef * out(i-1))
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.OneZero]]
 * @see [[de.sciss.synth.ugen.Lag]]
 */
object OnePole {
   
/**
 * @param in              input signal to be processed
 * @param coeff           feedback coefficient. Should be between -1 and +1
 */
def kr(in: GE[control, UGenIn[control]], coeff: AnyGE = 0.5f) = apply[control](control, in, coeff)
/**
 * @param in              input signal to be processed
 * @param coeff           feedback coefficient. Should be between -1 and +1
 */
def ar(in: GE[audio, UGenIn[audio]], coeff: AnyGE = 0.5f) = apply[audio](audio, in, coeff)
}
/**
 * A one pole (IIR) filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = ((1 - abs(coef)) * in(i)) + (coef * out(i-1))
 * }}}
 * 
 * @param in              input signal to be processed
 * @param coeff           feedback coefficient. Should be between -1 and +1
 * 
 * @see [[de.sciss.synth.ugen.OneZero]]
 * @see [[de.sciss.synth.ugen.Lag]]
 */
case class OnePole[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R, OnePoleUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _coeff: IIdxSeq[AnyUGenIn] = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => OnePoleUGen(rate, _in(i.%(_sz_in)), _coeff(i.%(_sz_coeff))))
   }
}
case class OnePoleUGen[R <: Rate](rate: R, in: AnyUGenIn, coeff: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, coeff))
/**
 * A one zero (FIR) filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = ((1 - abs(coef)) * in(i)) + (coef * in(i-1))
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.LPZ1]]
 * @see [[de.sciss.synth.ugen.HPZ1]]
 * @see [[de.sciss.synth.ugen.Delay1]]
 * @see [[de.sciss.synth.ugen.Integrator]]
 */
object OneZero {
   
/**
 * @param in              input signal to be processed
 * @param coeff           feed forward coefficient. +0.5 makes a two point averaging filter (see also `LPZ1`),
 *                        -0.5 makes a differentiator (see also `HPZ1`),  +1 makes a single sample delay (see also `Delay1`),
 *                        -1 makes an inverted single sample delay.
 */
def kr(in: GE[control, UGenIn[control]], coeff: AnyGE = 0.5f) = apply[control](control, in, coeff)
/**
 * @param in              input signal to be processed
 * @param coeff           feed forward coefficient. +0.5 makes a two point averaging filter (see also `LPZ1`),
 *                        -0.5 makes a differentiator (see also `HPZ1`),  +1 makes a single sample delay (see also `Delay1`),
 *                        -1 makes an inverted single sample delay.
 */
def ar(in: GE[audio, UGenIn[audio]], coeff: AnyGE = 0.5f) = apply[audio](audio, in, coeff)
}
/**
 * A one zero (FIR) filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = ((1 - abs(coef)) * in(i)) + (coef * in(i-1))
 * }}}
 * 
 * @param in              input signal to be processed
 * @param coeff           feed forward coefficient. +0.5 makes a two point averaging filter (see also `LPZ1`),
 *                        -0.5 makes a differentiator (see also `HPZ1`),  +1 makes a single sample delay (see also `Delay1`),
 *                        -1 makes an inverted single sample delay.
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.LPZ1]]
 * @see [[de.sciss.synth.ugen.HPZ1]]
 * @see [[de.sciss.synth.ugen.Delay1]]
 * @see [[de.sciss.synth.ugen.Integrator]]
 */
case class OneZero[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R, OneZeroUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _coeff: IIdxSeq[AnyUGenIn] = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => OneZeroUGen(rate, _in(i.%(_sz_in)), _coeff(i.%(_sz_coeff))))
   }
}
case class OneZeroUGen[R <: Rate](rate: R, in: AnyUGenIn, coeff: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, coeff))
object TwoPole {
   
/**
 * @param in              input signal to be processed
 */
def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[control](control, in, freq, radius)
/**
 * @param in              input signal to be processed
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[audio](audio, in, freq, radius)
}
/**
 * @param in              input signal to be processed
 */
case class TwoPole[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends SingleOutUGenSource[R, TwoPoleUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _radius: IIdxSeq[AnyUGenIn] = radius.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_radius = _radius.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_radius)
      IIdxSeq.tabulate(_exp_)(i => TwoPoleUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _radius(i.%(_sz_radius))))
   }
}
case class TwoPoleUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, radius: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, radius))
object TwoZero {
   
/**
 * @param in              input signal to be processed
 */
def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[control](control, in, freq, radius)
/**
 * @param in              input signal to be processed
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[audio](audio, in, freq, radius)
}
/**
 * @param in              input signal to be processed
 */
case class TwoZero[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends SingleOutUGenSource[R, TwoZeroUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _radius: IIdxSeq[AnyUGenIn] = radius.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_radius = _radius.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_radius)
      IIdxSeq.tabulate(_exp_)(i => TwoZeroUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _radius(i.%(_sz_radius))))
   }
}
case class TwoZeroUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, radius: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, radius))
object Decay {
   
/**
 * @param in              input signal to be processed
 */
def kr(in: GE[control, UGenIn[control]], time: AnyGE = 1.0f) = apply[control](control, in, time)
/**
 * @param in              input signal to be processed
 */
def ar(in: GE[audio, UGenIn[audio]], time: AnyGE = 1.0f) = apply[audio](audio, in, time)
}
/**
 * @param in              input signal to be processed
 */
case class Decay[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R, DecayUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _time: IIdxSeq[AnyUGenIn] = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => DecayUGen(rate, _in(i.%(_sz_in)), _time(i.%(_sz_time))))
   }
}
case class DecayUGen[R <: Rate](rate: R, in: AnyUGenIn, time: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, time))
object Decay2 {
   
/**
 * @param in              input signal to be processed
 */
def kr(in: GE[control, UGenIn[control]], attack: AnyGE = 0.01f, release: AnyGE = 1.0f) = apply[control](control, in, attack, release)
/**
 * @param in              input signal to be processed
 */
def ar(in: GE[audio, UGenIn[audio]], attack: AnyGE = 0.01f, release: AnyGE = 1.0f) = apply[audio](audio, in, attack, release)
}
/**
 * @param in              input signal to be processed
 */
case class Decay2[R <: Rate](rate: R, in: AnyGE, attack: AnyGE, release: AnyGE) extends SingleOutUGenSource[R, Decay2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _attack: IIdxSeq[AnyUGenIn] = attack.expand
      val _release: IIdxSeq[AnyUGenIn] = release.expand
      val _sz_in = _in.size
      val _sz_attack = _attack.size
      val _sz_release = _release.size
      val _exp_ = maxInt(_sz_in, _sz_attack, _sz_release)
      IIdxSeq.tabulate(_exp_)(i => Decay2UGen(rate, _in(i.%(_sz_in)), _attack(i.%(_sz_attack)), _release(i.%(_sz_release))))
   }
}
case class Decay2UGen[R <: Rate](rate: R, in: AnyUGenIn, attack: AnyUGenIn, release: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, attack, release))
object Delay1 {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
case class Delay1[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, Delay1UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => Delay1UGen(rate, _in(i)))
   }
}
case class Delay1UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object Delay2 {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
case class Delay2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, Delay2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => Delay2UGen(rate, _in(i)))
   }
}
case class Delay2UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
/**
 * A filter UGen to integrate an input signal with a leak.
 * Implements the formula :
 * {{{
 * out(i) = in(i) + (coef * out(i-1))
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.HPZ1]]
 * @see [[de.sciss.synth.ugen.LPZ1]]
 */
object Integrator {
   
/**
 * @param in              input signal to be processed
 * @param coeff           the leak coefficient. Should be between -1 and +1
 */
def kr(in: GE[control, UGenIn[control]], coeff: AnyGE = 1.0f) = apply[control](control, in, coeff)
/**
 * @param in              input signal to be processed
 * @param coeff           the leak coefficient. Should be between -1 and +1
 */
def ar(in: GE[audio, UGenIn[audio]], coeff: AnyGE = 1.0f) = apply[audio](audio, in, coeff)
}
/**
 * A filter UGen to integrate an input signal with a leak.
 * Implements the formula :
 * {{{
 * out(i) = in(i) + (coef * out(i-1))
 * }}}
 * 
 * @param in              input signal to be processed
 * @param coeff           the leak coefficient. Should be between -1 and +1
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.HPZ1]]
 * @see [[de.sciss.synth.ugen.LPZ1]]
 */
case class Integrator[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R, IntegratorUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _coeff: IIdxSeq[AnyUGenIn] = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => IntegratorUGen(rate, _in(i.%(_sz_in)), _coeff(i.%(_sz_coeff))))
   }
}
case class IntegratorUGen[R <: Rate](rate: R, in: AnyUGenIn, coeff: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, coeff))
object LeakDC {
   def kr(in: GE[control, UGenIn[control]], coeff: AnyGE = 0.9f) = apply[control](control, in, coeff)
   def ar(in: GE[audio, UGenIn[audio]], coeff: AnyGE = 0.995f) = apply[audio](audio, in, coeff)
}
case class LeakDC[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R, LeakDCUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _coeff: IIdxSeq[AnyUGenIn] = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => LeakDCUGen(rate, _in(i.%(_sz_in)), _coeff(i.%(_sz_coeff))))
   }
}
case class LeakDCUGen[R <: Rate](rate: R, in: AnyUGenIn, coeff: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, coeff))
/**
 * two point average filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = 0.5 * (in(i) + in(i-1))
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.HPZ1]]
 * @see [[de.sciss.synth.ugen.Integrator]]
 */
object LPZ1 {
   
/**
 * @param in              input signal to be processed
 */
def kr(in: GE[control, UGenIn[control]]) = apply[control](control, in)
/**
 * @param in              input signal to be processed
 */
def ar(in: GE[audio, UGenIn[audio]]) = apply[audio](audio, in)
}
/**
 * two point average filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = 0.5 * (in(i) + in(i-1))
 * }}}
 * 
 * @param in              input signal to be processed
 * 
 * @see [[de.sciss.synth.ugen.OnePole]]
 * @see [[de.sciss.synth.ugen.HPZ1]]
 * @see [[de.sciss.synth.ugen.Integrator]]
 */
case class LPZ1[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, LPZ1UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => LPZ1UGen(rate, _in(i)))
   }
}
case class LPZ1UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
/**
 * A two point difference filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = 0.5 * (in(i) - in(i-1))
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.OneZero]]
 * @see [[de.sciss.synth.ugen.LPZ1]]
 */
object HPZ1 {
   
/**
 * @param in              input signal to be processed
 */
def kr(in: GE[control, UGenIn[control]]) = apply[control](control, in)
/**
 * @param in              input signal to be processed
 */
def ar(in: GE[audio, UGenIn[audio]]) = apply[audio](audio, in)
}
/**
 * A two point difference filter UGen.
 * Implements the formula :
 * {{{
 * out(i) = 0.5 * (in(i) - in(i-1))
 * }}}
 * 
 * @param in              input signal to be processed
 * 
 * @see [[de.sciss.synth.ugen.OneZero]]
 * @see [[de.sciss.synth.ugen.LPZ1]]
 */
case class HPZ1[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, HPZ1UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => HPZ1UGen(rate, _in(i)))
   }
}
case class HPZ1UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object LPZ2 {
   def kr(in: GE[control, UGenIn[control]]) = apply[control](control, in)
   def ar(in: GE[audio, UGenIn[audio]]) = apply[audio](audio, in)
}
case class LPZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, LPZ2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => LPZ2UGen(rate, _in(i)))
   }
}
case class LPZ2UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object HPZ2 {
   def kr(in: GE[control, UGenIn[control]]) = apply[control](control, in)
   def ar(in: GE[audio, UGenIn[audio]]) = apply[audio](audio, in)
}
case class HPZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, HPZ2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => HPZ2UGen(rate, _in(i)))
   }
}
case class HPZ2UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object BPZ2 {
   def kr(in: GE[control, UGenIn[control]]) = apply[control](control, in)
   def ar(in: GE[audio, UGenIn[audio]]) = apply[audio](audio, in)
}
case class BPZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, BPZ2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => BPZ2UGen(rate, _in(i)))
   }
}
case class BPZ2UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object BRZ2 {
   def kr(in: GE[control, UGenIn[control]]) = apply[control](control, in)
   def ar(in: GE[audio, UGenIn[audio]]) = apply[audio](audio, in)
}
case class BRZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, BRZ2UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => BRZ2UGen(rate, _in(i)))
   }
}
case class BRZ2UGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object APF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[control](control, in, freq, radius)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[audio](audio, in, freq, radius)
}
case class APF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends SingleOutUGenSource[R, APFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _radius: IIdxSeq[AnyUGenIn] = radius.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_radius = _radius.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_radius)
      IIdxSeq.tabulate(_exp_)(i => APFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _radius(i.%(_sz_radius))))
   }
}
case class APFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, radius: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, radius))
object LPF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f) = apply[control](control, in, freq)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f) = apply[audio](audio, in, freq)
}
case class LPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE) extends SingleOutUGenSource[R, LPFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _exp_ = maxInt(_sz_in, _sz_freq)
      IIdxSeq.tabulate(_exp_)(i => LPFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq))))
   }
}
case class LPFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq))
object HPF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f) = apply[control](control, in, freq)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f) = apply[audio](audio, in, freq)
}
case class HPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE) extends SingleOutUGenSource[R, HPFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _exp_ = maxInt(_sz_in, _sz_freq)
      IIdxSeq.tabulate(_exp_)(i => HPFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq))))
   }
}
case class HPFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq))
object BPF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
case class BPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R, BPFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => BPFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class BPFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, rq))
object BRF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
case class BRF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R, BRFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => BRFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class BRFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, rq))
object RLPF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
case class RLPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R, RLPFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => RLPFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class RLPFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, rq))
object RHPF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
case class RHPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R, RHPFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => RHPFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class RHPFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, rq))
/**
 * A slew rate limiter UGen.
 * Limits the slope of an input signal. The slope is expressed in units per second.
 */
object Slew {
   
/**
 * @param in              input signal
 * @param up              maximum upward slope.
 * @param down            maximum downward slope.
 */
def kr(in: GE[control, UGenIn[control]], up: AnyGE = 1.0f, down: AnyGE = 1.0f) = apply[control](control, in, up, down)
/**
 * @param in              input signal
 * @param up              maximum upward slope.
 * @param down            maximum downward slope.
 */
def ar(in: GE[audio, UGenIn[audio]], up: AnyGE = 1.0f, down: AnyGE = 1.0f) = apply[audio](audio, in, up, down)
}
/**
 * A slew rate limiter UGen.
 * Limits the slope of an input signal. The slope is expressed in units per second.
 * 
 * @param in              input signal
 * @param up              maximum upward slope.
 * @param down            maximum downward slope.
 */
case class Slew[R <: Rate](rate: R, in: AnyGE, up: AnyGE, down: AnyGE) extends SingleOutUGenSource[R, SlewUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _up: IIdxSeq[AnyUGenIn] = up.expand
      val _down: IIdxSeq[AnyUGenIn] = down.expand
      val _sz_in = _in.size
      val _sz_up = _up.size
      val _sz_down = _down.size
      val _exp_ = maxInt(_sz_in, _sz_up, _sz_down)
      IIdxSeq.tabulate(_exp_)(i => SlewUGen(rate, _in(i.%(_sz_in)), _up(i.%(_sz_up)), _down(i.%(_sz_down))))
   }
}
case class SlewUGen[R <: Rate](rate: R, in: AnyUGenIn, up: AnyUGenIn, down: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, up, down))
/**
 * A UGen measuring the slope of signal.
 * It calculates the rate of change per second of a signal, as given by the following formula:
 * {{{
 * out(i) = (in(i) - in(i-1)) * sampleRate
 * }}}
 * It thus equal to `HPZ1.ar(_) * 2 * SampleRate.ir`
 */
object Slope {
   
/**
 * @param in              input signal to be measured
 */
def kr(in: GE[control, UGenIn[control]]) = apply[control](control, in)
/**
 * @param in              input signal to be measured
 */
def ar(in: GE[audio, UGenIn[audio]]) = apply[audio](audio, in)
}
/**
 * A UGen measuring the slope of signal.
 * It calculates the rate of change per second of a signal, as given by the following formula:
 * {{{
 * out(i) = (in(i) - in(i-1)) * sampleRate
 * }}}
 * It thus equal to `HPZ1.ar(_) * 2 * SampleRate.ir`
 * 
 * @param in              input signal to be measured
 */
case class Slope[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, SlopeUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => SlopeUGen(rate, _in(i)))
   }
}
case class SlopeUGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object MidEQ {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply[control](control, in, freq, rq, gain)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply[audio](audio, in, freq, rq, gain)
}
case class MidEQ[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE, gain: AnyGE) extends SingleOutUGenSource[R, MidEQUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _gain: IIdxSeq[AnyUGenIn] = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => MidEQUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)), _gain(i.%(_sz_gain))))
   }
}
case class MidEQUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn, gain: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, rq, gain))
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object Median {
   def kr(in: GE[control, UGenIn[control]], length: AnyGE = 3.0f) = apply[control](control, in, length)
   def ar(in: GE[audio, UGenIn[audio]], length: AnyGE = 3.0f) = apply[audio](audio, in, length)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
case class Median[R <: Rate](rate: R, in: AnyGE, length: AnyGE) extends SingleOutUGenSource[R, MedianUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _length: IIdxSeq[AnyUGenIn] = length.expand
      val _sz_in = _in.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_in, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => MedianUGen(rate, _in(i.%(_sz_in)), _length(i.%(_sz_length))))
   }
}
case class MedianUGen[R <: Rate](rate: R, in: AnyUGenIn, length: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, length))
object Resonz {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
case class Resonz[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R, ResonzUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => ResonzUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class ResonzUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, rq))
object Ringz {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[control](control, in, freq, attack, decay)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[audio](audio, in, freq, attack, decay)
}
case class Ringz[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, attack: AnyGE, decay: AnyGE) extends SingleOutUGenSource[R, RingzUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _attack: IIdxSeq[AnyUGenIn] = attack.expand
      val _decay: IIdxSeq[AnyUGenIn] = decay.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_attack = _attack.size
      val _sz_decay = _decay.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_attack, _sz_decay)
      IIdxSeq.tabulate(_exp_)(i => RingzUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _attack(i.%(_sz_attack)), _decay(i.%(_sz_decay))))
   }
}
case class RingzUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, attack: AnyUGenIn, decay: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, attack, decay))
object Formlet {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[control](control, in, freq, attack, decay)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[audio](audio, in, freq, attack, decay)
}
case class Formlet[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, attack: AnyGE, decay: AnyGE) extends SingleOutUGenSource[R, FormletUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _attack: IIdxSeq[AnyUGenIn] = attack.expand
      val _decay: IIdxSeq[AnyUGenIn] = decay.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_attack = _attack.size
      val _sz_decay = _decay.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_attack, _sz_decay)
      IIdxSeq.tabulate(_exp_)(i => FormletUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _attack(i.%(_sz_attack)), _decay(i.%(_sz_decay))))
   }
}
case class FormletUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, attack: AnyUGenIn, decay: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, attack, decay))
object FOS {
   def kr(in: GE[control, UGenIn[control]], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, b1: AnyGE = 0.0f) = apply[control](control, in, a0, a1, b1)
   def ar(in: GE[audio, UGenIn[audio]], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, b1: AnyGE = 0.0f) = apply[audio](audio, in, a0, a1, b1)
}
case class FOS[R <: Rate](rate: R, in: AnyGE, a0: AnyGE, a1: AnyGE, b1: AnyGE) extends SingleOutUGenSource[R, FOSUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _a0: IIdxSeq[AnyUGenIn] = a0.expand
      val _a1: IIdxSeq[AnyUGenIn] = a1.expand
      val _b1: IIdxSeq[AnyUGenIn] = b1.expand
      val _sz_in = _in.size
      val _sz_a0 = _a0.size
      val _sz_a1 = _a1.size
      val _sz_b1 = _b1.size
      val _exp_ = maxInt(_sz_in, _sz_a0, _sz_a1, _sz_b1)
      IIdxSeq.tabulate(_exp_)(i => FOSUGen(rate, _in(i.%(_sz_in)), _a0(i.%(_sz_a0)), _a1(i.%(_sz_a1)), _b1(i.%(_sz_b1))))
   }
}
case class FOSUGen[R <: Rate](rate: R, in: AnyUGenIn, a0: AnyUGenIn, a1: AnyUGenIn, b1: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, a0, a1, b1))
object SOS {
   def kr(in: GE[control, UGenIn[control]], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, a2: AnyGE = 0.0f, b1: AnyGE = 0.0f, b2: AnyGE = 0.0f) = apply[control](control, in, a0, a1, a2, b1, b2)
   def ar(in: GE[audio, UGenIn[audio]], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, a2: AnyGE = 0.0f, b1: AnyGE = 0.0f, b2: AnyGE = 0.0f) = apply[audio](audio, in, a0, a1, a2, b1, b2)
}
case class SOS[R <: Rate](rate: R, in: AnyGE, a0: AnyGE, a1: AnyGE, a2: AnyGE, b1: AnyGE, b2: AnyGE) extends SingleOutUGenSource[R, SOSUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _a0: IIdxSeq[AnyUGenIn] = a0.expand
      val _a1: IIdxSeq[AnyUGenIn] = a1.expand
      val _a2: IIdxSeq[AnyUGenIn] = a2.expand
      val _b1: IIdxSeq[AnyUGenIn] = b1.expand
      val _b2: IIdxSeq[AnyUGenIn] = b2.expand
      val _sz_in = _in.size
      val _sz_a0 = _a0.size
      val _sz_a1 = _a1.size
      val _sz_a2 = _a2.size
      val _sz_b1 = _b1.size
      val _sz_b2 = _b2.size
      val _exp_ = maxInt(_sz_in, _sz_a0, _sz_a1, _sz_a2, _sz_b1, _sz_b2)
      IIdxSeq.tabulate(_exp_)(i => SOSUGen(rate, _in(i.%(_sz_in)), _a0(i.%(_sz_a0)), _a1(i.%(_sz_a1)), _a2(i.%(_sz_a2)), _b1(i.%(_sz_b1)), _b2(i.%(_sz_b2))))
   }
}
case class SOSUGen[R <: Rate](rate: R, in: AnyUGenIn, a0: AnyUGenIn, a1: AnyUGenIn, a2: AnyUGenIn, b1: AnyUGenIn, b2: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, a0, a1, a2, b1, b2))
/**
 * A compressor, expander, limiter, gate and ducking UGen. This dynamic processor uses a
 * hard-knee characteristic. All of the thresholds and ratios are given as direct
 * values, not in decibels!
 * 
 * @see [[de.sciss.synth.ugen.Limiter]]
 * @see [[de.sciss.synth.ugen.Normalizer]]
 */
object Compander {
   
/**
 * @param in              The signal to be compressed / expanded / gated.
 * @param ctrl            The signal whose amplitude controls the processor. Often the same as in, but one may wish
 *                        to apply equalization or delay to it to change the compressor character (side-chaining), or even feed
 *                        a completely different signal, for instance in a ducking application.
 * @param thresh          Control signal amplitude threshold, which determines the break point between slopeBelow
 *                        and slopeAbove. Usually 0..1. The control signal amplitude is calculated using RMS.
 * @param ratioBelow      Slope of the amplitude curve below the threshold. If this slope > 1.0, the amplitude
 *                        will drop off more quickly the softer the control signal gets; when the control signal is close to 0
 *                        amplitude, the output should be exactly zero -- hence, noise gating. Values < 1.0 are possible,
 *                        but it means that a very low-level control signal will cause the input signal to be amplified,
 *                        which would raise the noise floor.
 * @param ratioAbove      Slope of the amplitude curve above the threshold. Values < 1.0 achieve compression
 *                        (louder signals are attenuated); > 1.0, you get expansion (louder signals are made even louder).
 *                        For 3:1 compression, you would use a value of 1/3 here.
 * @param attack          The amount of time it takes for the amplitude adjustment to kick in fully. This is
 *                        usually pretty small, not much more than 10 milliseconds (the default value). I often set it as low as
 *                        2 milliseconds (0.002).
 * @param release         The amount of time for the amplitude adjustment to be released. Usually a bit longer
 *                        than attack; if both times are too short, you can get some (possibly unwanted) artifacts.
 */
def kr(in: AnyGE, ctrl: AnyGE, thresh: AnyGE = 0.5f, ratioBelow: AnyGE = 1.0f, ratioAbove: AnyGE = 1.0f, attack: AnyGE = 0.01f, release: AnyGE = 0.1f) = apply[control](control, in, ctrl, thresh, ratioBelow, ratioAbove, attack, release)
/**
 * @param in              The signal to be compressed / expanded / gated.
 * @param ctrl            The signal whose amplitude controls the processor. Often the same as in, but one may wish
 *                        to apply equalization or delay to it to change the compressor character (side-chaining), or even feed
 *                        a completely different signal, for instance in a ducking application.
 * @param thresh          Control signal amplitude threshold, which determines the break point between slopeBelow
 *                        and slopeAbove. Usually 0..1. The control signal amplitude is calculated using RMS.
 * @param ratioBelow      Slope of the amplitude curve below the threshold. If this slope > 1.0, the amplitude
 *                        will drop off more quickly the softer the control signal gets; when the control signal is close to 0
 *                        amplitude, the output should be exactly zero -- hence, noise gating. Values < 1.0 are possible,
 *                        but it means that a very low-level control signal will cause the input signal to be amplified,
 *                        which would raise the noise floor.
 * @param ratioAbove      Slope of the amplitude curve above the threshold. Values < 1.0 achieve compression
 *                        (louder signals are attenuated); > 1.0, you get expansion (louder signals are made even louder).
 *                        For 3:1 compression, you would use a value of 1/3 here.
 * @param attack          The amount of time it takes for the amplitude adjustment to kick in fully. This is
 *                        usually pretty small, not much more than 10 milliseconds (the default value). I often set it as low as
 *                        2 milliseconds (0.002).
 * @param release         The amount of time for the amplitude adjustment to be released. Usually a bit longer
 *                        than attack; if both times are too short, you can get some (possibly unwanted) artifacts.
 */
def ar(in: AnyGE, ctrl: AnyGE, thresh: AnyGE = 0.5f, ratioBelow: AnyGE = 1.0f, ratioAbove: AnyGE = 1.0f, attack: AnyGE = 0.01f, release: AnyGE = 0.1f) = apply[audio](audio, in, ctrl, thresh, ratioBelow, ratioAbove, attack, release)
}
/**
 * A compressor, expander, limiter, gate and ducking UGen. This dynamic processor uses a
 * hard-knee characteristic. All of the thresholds and ratios are given as direct
 * values, not in decibels!
 * 
 * @param in              The signal to be compressed / expanded / gated.
 * @param ctrl            The signal whose amplitude controls the processor. Often the same as in, but one may wish
 *                        to apply equalization or delay to it to change the compressor character (side-chaining), or even feed
 *                        a completely different signal, for instance in a ducking application.
 * @param thresh          Control signal amplitude threshold, which determines the break point between slopeBelow
 *                        and slopeAbove. Usually 0..1. The control signal amplitude is calculated using RMS.
 * @param ratioBelow      Slope of the amplitude curve below the threshold. If this slope > 1.0, the amplitude
 *                        will drop off more quickly the softer the control signal gets; when the control signal is close to 0
 *                        amplitude, the output should be exactly zero -- hence, noise gating. Values < 1.0 are possible,
 *                        but it means that a very low-level control signal will cause the input signal to be amplified,
 *                        which would raise the noise floor.
 * @param ratioAbove      Slope of the amplitude curve above the threshold. Values < 1.0 achieve compression
 *                        (louder signals are attenuated); > 1.0, you get expansion (louder signals are made even louder).
 *                        For 3:1 compression, you would use a value of 1/3 here.
 * @param attack          The amount of time it takes for the amplitude adjustment to kick in fully. This is
 *                        usually pretty small, not much more than 10 milliseconds (the default value). I often set it as low as
 *                        2 milliseconds (0.002).
 * @param release         The amount of time for the amplitude adjustment to be released. Usually a bit longer
 *                        than attack; if both times are too short, you can get some (possibly unwanted) artifacts.
 * 
 * @see [[de.sciss.synth.ugen.Limiter]]
 * @see [[de.sciss.synth.ugen.Normalizer]]
 */
case class Compander[R <: Rate](rate: R, in: AnyGE, ctrl: AnyGE, thresh: AnyGE, ratioBelow: AnyGE, ratioAbove: AnyGE, attack: AnyGE, release: AnyGE) extends SingleOutUGenSource[R, CompanderUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _ctrl: IIdxSeq[AnyUGenIn] = ctrl.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _ratioBelow: IIdxSeq[AnyUGenIn] = ratioBelow.expand
      val _ratioAbove: IIdxSeq[AnyUGenIn] = ratioAbove.expand
      val _attack: IIdxSeq[AnyUGenIn] = attack.expand
      val _release: IIdxSeq[AnyUGenIn] = release.expand
      val _sz_in = _in.size
      val _sz_ctrl = _ctrl.size
      val _sz_thresh = _thresh.size
      val _sz_ratioBelow = _ratioBelow.size
      val _sz_ratioAbove = _ratioAbove.size
      val _sz_attack = _attack.size
      val _sz_release = _release.size
      val _exp_ = maxInt(_sz_in, _sz_ctrl, _sz_thresh, _sz_ratioBelow, _sz_ratioAbove, _sz_attack, _sz_release)
      IIdxSeq.tabulate(_exp_)(i => CompanderUGen(rate, _in(i.%(_sz_in)), _ctrl(i.%(_sz_ctrl)), _thresh(i.%(_sz_thresh)), _ratioBelow(i.%(_sz_ratioBelow)), _ratioAbove(i.%(_sz_ratioAbove)), _attack(i.%(_sz_attack)), _release(i.%(_sz_release))))
   }
}
case class CompanderUGen[R <: Rate](rate: R, in: AnyUGenIn, ctrl: AnyUGenIn, thresh: AnyUGenIn, ratioBelow: AnyUGenIn, ratioAbove: AnyUGenIn, attack: AnyUGenIn, release: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, ctrl, thresh, ratioBelow, ratioAbove, attack, release))
object Limiter {
   def kr(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[control](control, in, level, dur)
   def ar(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[audio](audio, in, level, dur)
}
case class Limiter[R <: Rate](rate: R, in: AnyGE, level: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R, LimiterUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _sz_in = _in.size
      val _sz_level = _level.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_level, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => LimiterUGen(rate, _in(i.%(_sz_in)), _level(i.%(_sz_level)), _dur(i.%(_sz_dur))))
   }
}
case class LimiterUGen[R <: Rate](rate: R, in: AnyUGenIn, level: AnyUGenIn, dur: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, level, dur))
object Normalizer {
   def kr(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[control](control, in, level, dur)
   def ar(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[audio](audio, in, level, dur)
}
case class Normalizer[R <: Rate](rate: R, in: AnyGE, level: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R, NormalizerUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _sz_in = _in.size
      val _sz_level = _level.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_level, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => NormalizerUGen(rate, _in(i.%(_sz_in)), _level(i.%(_sz_level)), _dur(i.%(_sz_dur))))
   }
}
case class NormalizerUGen[R <: Rate](rate: R, in: AnyUGenIn, level: AnyUGenIn, dur: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, level, dur))
object Amplitude {
   def kr(in: AnyGE, attack: AnyGE = 0.01f, release: AnyGE = 0.01f) = apply[control](control, in, attack, release)
   def ar(in: AnyGE, attack: AnyGE = 0.01f, release: AnyGE = 0.01f) = apply[audio](audio, in, attack, release)
}
case class Amplitude[R <: Rate](rate: R, in: AnyGE, attack: AnyGE, release: AnyGE) extends SingleOutUGenSource[R, AmplitudeUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _attack: IIdxSeq[AnyUGenIn] = attack.expand
      val _release: IIdxSeq[AnyUGenIn] = release.expand
      val _sz_in = _in.size
      val _sz_attack = _attack.size
      val _sz_release = _release.size
      val _exp_ = maxInt(_sz_in, _sz_attack, _sz_release)
      IIdxSeq.tabulate(_exp_)(i => AmplitudeUGen(rate, _in(i.%(_sz_in)), _attack(i.%(_sz_attack)), _release(i.%(_sz_release))))
   }
}
case class AmplitudeUGen[R <: Rate](rate: R, in: AnyUGenIn, attack: AnyUGenIn, release: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, attack, release))
object DetectSilence {
   def kr(in: GE[control, UGenIn[control]], amp: AnyGE = 1.0E-4f, dur: AnyGE = 0.1f, doneAction: AnyGE = doNothing) = apply[control](control, in, amp, dur, doneAction)
   def ar(in: GE[audio, UGenIn[audio]], amp: AnyGE = 1.0E-4f, dur: AnyGE = 0.1f, doneAction: AnyGE = doNothing) = apply[audio](audio, in, amp, dur, doneAction)
}
case class DetectSilence[R <: Rate](rate: R, in: AnyGE, amp: AnyGE, dur: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, DetectSilenceUGen[R]] with HasSideEffect {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _amp: IIdxSeq[AnyUGenIn] = amp.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _sz_in = _in.size
      val _sz_amp = _amp.size
      val _sz_dur = _dur.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_in, _sz_amp, _sz_dur, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => DetectSilenceUGen(rate, _in(i.%(_sz_in)), _amp(i.%(_sz_amp)), _dur(i.%(_sz_dur)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class DetectSilenceUGen[R <: Rate](rate: R, in: AnyUGenIn, amp: AnyUGenIn, dur: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, amp, dur, doneAction)) with HasSideEffect
object Hilbert {
   def ar(in: AnyGE) = apply[audio](audio, in)
}
case class Hilbert[R <: Rate](rate: R, in: AnyGE) extends MultiOutUGenSource[R, HilbertUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => HilbertUGen(rate, _in(i)))
   }
}
case class HilbertUGen[R <: Rate](rate: R, in: AnyUGenIn) extends MultiOutUGen[R](IIdxSeq.fill(2)(rate), IIdxSeq(in))
object FreqShift {
   def ar(in: AnyGE, freq: AnyGE = 0.0f, phase: AnyGE = 0.0f) = apply[audio](audio, in, freq, phase)
}
case class FreqShift[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R, FreqShiftUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => FreqShiftUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class FreqShiftUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, phase))
object MoogFF {
   def kr(in: GE[control, UGenIn[control]], freq: AnyGE = 200.0f, gain: AnyGE = 2.0f, reset: AnyGE = 0.0f) = apply[control](control, in, freq, gain, reset)
   def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 200.0f, gain: AnyGE = 2.0f, reset: AnyGE = 0.0f) = apply[audio](audio, in, freq, gain, reset)
}
case class MoogFF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, gain: AnyGE, reset: AnyGE) extends SingleOutUGenSource[R, MoogFFUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _gain: IIdxSeq[AnyUGenIn] = gain.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_gain = _gain.size
      val _sz_reset = _reset.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_gain, _sz_reset)
      IIdxSeq.tabulate(_exp_)(i => MoogFFUGen(rate, _in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _gain(i.%(_sz_gain)), _reset(i.%(_sz_reset))))
   }
}
case class MoogFFUGen[R <: Rate](rate: R, in: AnyUGenIn, freq: AnyUGenIn, gain: AnyUGenIn, reset: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, freq, gain, reset))
/**
 * A 2nd order (12db per oct rolloff) resonant low pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BLowPass {
   
/**
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply(in, freq, rq)
}
/**
 * A 2nd order (12db per oct rolloff) resonant low pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 */
case class BLowPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[audio, BLowPassUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => BLowPassUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class BLowPassUGen(in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, rq)) with AudioRated
/**
 * A 2nd order (12db per oct rolloff) resonant high pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BHiPass {
   
/**
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply(in, freq, rq)
}
/**
 * A 2nd order (12db per oct rolloff) resonant high pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 */
case class BHiPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[audio, BHiPassUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => BHiPassUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class BHiPassUGen(in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, rq)) with AudioRated
/**
 * An band pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BBandPass {
   
/**
 * @param in              input signal to be processed.
 * @param freq            center frequency.
 * @param bw              the bandwidth '''in octaves''' between -3 dB frequencies
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, bw: AnyGE = 1.0f) = apply(in, freq, bw)
}
/**
 * An band pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            center frequency.
 * @param bw              the bandwidth '''in octaves''' between -3 dB frequencies
 */
case class BBandPass(in: AnyGE, freq: AnyGE, bw: AnyGE) extends SingleOutUGenSource[audio, BBandPassUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _bw: IIdxSeq[AnyUGenIn] = bw.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_bw = _bw.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_bw)
      IIdxSeq.tabulate(_exp_)(i => BBandPassUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _bw(i.%(_sz_bw))))
   }
}
case class BBandPassUGen(in: AnyUGenIn, freq: AnyUGenIn, bw: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, bw)) with AudioRated
/**
 * An band stop (reject) filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BBandStop {
   
/**
 * @param in              input signal to be processed.
 * @param freq            center frequency.
 * @param bw              the bandwidth '''in octaves''' between -3 dB frequencies
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, bw: AnyGE = 1.0f) = apply(in, freq, bw)
}
/**
 * An band stop (reject) filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            center frequency.
 * @param bw              the bandwidth '''in octaves''' between -3 dB frequencies
 */
case class BBandStop(in: AnyGE, freq: AnyGE, bw: AnyGE) extends SingleOutUGenSource[audio, BBandStopUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _bw: IIdxSeq[AnyUGenIn] = bw.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_bw = _bw.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_bw)
      IIdxSeq.tabulate(_exp_)(i => BBandStopUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _bw(i.%(_sz_bw))))
   }
}
case class BBandStopUGen(in: AnyUGenIn, freq: AnyUGenIn, bw: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, bw)) with AudioRated
/**
 * An parametric equalizer UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BPeakEQ {
   
/**
 * @param in              input signal to be processed.
 * @param freq            center frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 * @param gain            boost/cut at the center frequency (in decibels).
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply(in, freq, rq, gain)
}
/**
 * An parametric equalizer UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            center frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 * @param gain            boost/cut at the center frequency (in decibels).
 */
case class BPeakEQ(in: AnyGE, freq: AnyGE, rq: AnyGE, gain: AnyGE) extends SingleOutUGenSource[audio, BPeakEQUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _gain: IIdxSeq[AnyUGenIn] = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => BPeakEQUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)), _gain(i.%(_sz_gain))))
   }
}
case class BPeakEQUGen(in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn, gain: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, rq, gain)) with AudioRated
/**
 * An all pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BAllPass {
   
/**
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply(in, freq, rq)
}
/**
 * An all pass filter UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rq              the reciprocal of Q, hence bandwidth / cutoffFreq.
 */
case class BAllPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[audio, BAllPassUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rq: IIdxSeq[AnyUGenIn] = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => BAllPassUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq))))
   }
}
case class BAllPassUGen(in: AnyUGenIn, freq: AnyUGenIn, rq: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, rq)) with AudioRated
/**
 * A low shelf equalizer UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BLowShelf {
   
/**
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rs              the reciprocal of the slope S (Shell boost/cut slope).
 *                        When `S = 1`, the shelf slope is as steep as it can be and remain monotonically increasing
 *                        or decreasing gain with frequency.  The shelf slope, in dB/octave, remains proportional to
 *                        S for all other values for a fixed freq/sample-rate and `gain`.
 * @param gain            boost/cut at the cutoff frequency (in decibels).
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rs: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply(in, freq, rs, gain)
}
/**
 * A low shelf equalizer UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rs              the reciprocal of the slope S (Shell boost/cut slope).
 *                        When `S = 1`, the shelf slope is as steep as it can be and remain monotonically increasing
 *                        or decreasing gain with frequency.  The shelf slope, in dB/octave, remains proportional to
 *                        S for all other values for a fixed freq/sample-rate and `gain`.
 * @param gain            boost/cut at the cutoff frequency (in decibels).
 */
case class BLowShelf(in: AnyGE, freq: AnyGE, rs: AnyGE, gain: AnyGE) extends SingleOutUGenSource[audio, BLowShelfUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rs: IIdxSeq[AnyUGenIn] = rs.expand
      val _gain: IIdxSeq[AnyUGenIn] = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rs = _rs.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rs, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => BLowShelfUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rs(i.%(_sz_rs)), _gain(i.%(_sz_gain))))
   }
}
case class BLowShelfUGen(in: AnyUGenIn, freq: AnyUGenIn, rs: AnyUGenIn, gain: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, rs, gain)) with AudioRated
/**
 * A high shelf equalizer UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 */
object BHiShelf {
   
/**
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rs              the reciprocal of the slope S (Shell boost/cut slope).
 *                        When `S = 1`, the shelf slope is as steep as it can be and remain monotonically increasing
 *                        or decreasing gain with frequency.  The shelf slope, in dB/octave, remains proportional to
 *                        S for all other values for a fixed freq/sample-rate and `gain`.
 * @param gain            boost/cut at the cutoff frequency (in decibels).
 */
def ar(in: GE[audio, UGenIn[audio]], freq: AnyGE = 440.0f, rs: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply(in, freq, rs, gain)
}
/**
 * A high shelf equalizer UGen.
 * The B equalization suite is based on the Second Order Section (SOS) biquad UGen.
 * 
 * Note: Biquad coefficient calculations imply certain amount of CPU overhead. These
 * plugin UGens contain optimizations such that the coefficients get updated only when
 * there has been a change to one of the filter's parameters. This can cause spikes in
 * CPU performance and should be considered when using several of these units.
 * 
 * @param in              input signal to be processed.
 * @param freq            cutoff frequency.
 * @param rs              the reciprocal of the slope S (Shell boost/cut slope).
 *                        When `S = 1`, the shelf slope is as steep as it can be and remain monotonically increasing
 *                        or decreasing gain with frequency.  The shelf slope, in dB/octave, remains proportional to
 *                        S for all other values for a fixed freq/sample-rate and `gain`.
 * @param gain            boost/cut at the cutoff frequency (in decibels).
 */
case class BHiShelf(in: AnyGE, freq: AnyGE, rs: AnyGE, gain: AnyGE) extends SingleOutUGenSource[audio, BHiShelfUGen] with AudioRated {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _rs: IIdxSeq[AnyUGenIn] = rs.expand
      val _gain: IIdxSeq[AnyUGenIn] = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rs = _rs.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rs, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => BHiShelfUGen(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rs(i.%(_sz_rs)), _gain(i.%(_sz_gain))))
   }
}
case class BHiShelfUGen(in: AnyUGenIn, freq: AnyUGenIn, rs: AnyUGenIn, gain: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, freq, rs, gain)) with AudioRated