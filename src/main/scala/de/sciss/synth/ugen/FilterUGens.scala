/*
 * FilterUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
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
   def kr(in: GE[control], dur: AnyGE = 0.1f) = apply[control](control, in, dur)
   /**
    * @param in              the signal to smooth out
    * @param dur             the ramp-time (seconds) which is also the interval of the sampling
    */
   def ar(in: GE[audio], dur: AnyGE = 0.1f) = apply[audio](audio, in, dur)
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
final case class Ramp[R <: Rate](rate: R, in: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _dur = dur.expand
      val _sz_in = _in.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Ramp", rate, IIdxSeq(_in(i.%(_sz_in)), _dur(i.%(_sz_dur)))))
   }
}
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
   def kr(in: GE[control], time: AnyGE = 0.1f) = apply[control](control, in, time)
   /**
    * @param in              input signal.
    * @param time            60 dB lag time in seconds.
    */
   def ar(in: GE[audio], time: AnyGE = 0.1f) = apply[audio](audio, in, time)
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
final case class Lag[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _time = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Lag", rate, IIdxSeq(_in(i.%(_sz_in)), _time(i.%(_sz_time)))))
   }
}
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
   def kr(in: GE[control], time: AnyGE = 0.1f) = apply[control](control, in, time)
   /**
    * @param in              input signal.
    * @param time            60 dB lag time in seconds.
    */
   def ar(in: GE[audio], time: AnyGE = 0.1f) = apply[audio](audio, in, time)
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
final case class Lag2[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _time = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Lag2", rate, IIdxSeq(_in(i.%(_sz_in)), _time(i.%(_sz_time)))))
   }
}
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
   def kr(in: GE[control], time: AnyGE = 0.1f) = apply[control](control, in, time)
   /**
    * @param in              input signal.
    * @param time            60 dB lag time in seconds.
    */
   def ar(in: GE[audio], time: AnyGE = 0.1f) = apply[audio](audio, in, time)
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
final case class Lag3[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _time = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Lag3", rate, IIdxSeq(_in(i.%(_sz_in)), _time(i.%(_sz_time)))))
   }
}
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
   def kr(in: GE[control], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[control](control, in, timeUp, timeDown)
   /**
    * @param in              input signal.
    * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
    * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
    */
   def ar(in: GE[audio], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[audio](audio, in, timeUp, timeDown)
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
final case class LagUD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _timeUp = timeUp.expand
      val _timeDown = timeDown.expand
      val _sz_in = _in.size
      val _sz_timeUp = _timeUp.size
      val _sz_timeDown = _timeDown.size
      val _exp_ = maxInt(_sz_in, _sz_timeUp, _sz_timeDown)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LagUD", rate, IIdxSeq(_in(i.%(_sz_in)), _timeUp(i.%(_sz_timeUp)), _timeDown(i.%(_sz_timeDown)))))
   }
}
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
   def kr(in: GE[control], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[control](control, in, timeUp, timeDown)
   /**
    * @param in              input signal.
    * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
    * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
    */
   def ar(in: GE[audio], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[audio](audio, in, timeUp, timeDown)
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
final case class Lag2UD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _timeUp = timeUp.expand
      val _timeDown = timeDown.expand
      val _sz_in = _in.size
      val _sz_timeUp = _timeUp.size
      val _sz_timeDown = _timeDown.size
      val _exp_ = maxInt(_sz_in, _sz_timeUp, _sz_timeDown)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Lag2UD", rate, IIdxSeq(_in(i.%(_sz_in)), _timeUp(i.%(_sz_timeUp)), _timeDown(i.%(_sz_timeDown)))))
   }
}
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
   def kr(in: GE[control], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[control](control, in, timeUp, timeDown)
   /**
    * @param in              input signal.
    * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
    * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
    */
   def ar(in: GE[audio], timeUp: AnyGE = 0.1f, timeDown: AnyGE = 0.1f) = apply[audio](audio, in, timeUp, timeDown)
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
final case class Lag3UD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _timeUp = timeUp.expand
      val _timeDown = timeDown.expand
      val _sz_in = _in.size
      val _sz_timeUp = _timeUp.size
      val _sz_timeDown = _timeDown.size
      val _exp_ = maxInt(_sz_in, _sz_timeUp, _sz_timeDown)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Lag3UD", rate, IIdxSeq(_in(i.%(_sz_in)), _timeUp(i.%(_sz_timeUp)), _timeDown(i.%(_sz_timeDown)))))
   }
}
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
   def kr(in: GE[control], coeff: AnyGE = 0.5f) = apply[control](control, in, coeff)
   /**
    * @param in              input signal to be processed
    * @param coeff           feedback coefficient. Should be between -1 and +1
    */
   def ar(in: GE[audio], coeff: AnyGE = 0.5f) = apply[audio](audio, in, coeff)
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
final case class OnePole[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _coeff = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("OnePole", rate, IIdxSeq(_in(i.%(_sz_in)), _coeff(i.%(_sz_coeff)))))
   }
}
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
   def kr(in: GE[control], coeff: AnyGE = 0.5f) = apply[control](control, in, coeff)
   /**
    * @param in              input signal to be processed
    * @param coeff           feed forward coefficient. +0.5 makes a two point averaging filter (see also `LPZ1`),
    *                        -0.5 makes a differentiator (see also `HPZ1`),  +1 makes a single sample delay (see also `Delay1`),
    *                        -1 makes an inverted single sample delay.
    */
   def ar(in: GE[audio], coeff: AnyGE = 0.5f) = apply[audio](audio, in, coeff)
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
final case class OneZero[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _coeff = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("OneZero", rate, IIdxSeq(_in(i.%(_sz_in)), _coeff(i.%(_sz_coeff)))))
   }
}
object TwoPole {
   
   /**
    * @param in              input signal to be processed
    */
   def kr(in: GE[control], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[control](control, in, freq, radius)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE[audio], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[audio](audio, in, freq, radius)
}
/**
 * @param in              input signal to be processed
 */
final case class TwoPole[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _radius = radius.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_radius = _radius.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_radius)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TwoPole", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _radius(i.%(_sz_radius)))))
   }
}
object TwoZero {
   
   /**
    * @param in              input signal to be processed
    */
   def kr(in: GE[control], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[control](control, in, freq, radius)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE[audio], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[audio](audio, in, freq, radius)
}
/**
 * @param in              input signal to be processed
 */
final case class TwoZero[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _radius = radius.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_radius = _radius.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_radius)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TwoZero", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _radius(i.%(_sz_radius)))))
   }
}
/**
 * An integrator UGen with exponential decay of past values. This is essentially the same
 * as `Integrator` except that instead of supplying the coefficient directly, it is calculated
 * from a 60 dB decay time. This is the time required for the integrator to lose 99.9 % of its
 * value or -60dB.
 * 
 * Note: This should not be confused with `Lag` which does not overshoot due to integration,
 * but asymptotically follows the input signal.
 * 
 * @see [[de.sciss.synth.ugen.Integrator]]
 * @see [[de.sciss.synth.ugen.Decay2]]
 * @see [[de.sciss.synth.ugen.Lag]]
 */
object Decay {
   
   /**
    * @param in              input signal to be processed
    */
   def kr(in: GE[control], time: AnyGE = 1.0f) = apply[control](control, in, time)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE[audio], time: AnyGE = 1.0f) = apply[audio](audio, in, time)
}
/**
 * An integrator UGen with exponential decay of past values. This is essentially the same
 * as `Integrator` except that instead of supplying the coefficient directly, it is calculated
 * from a 60 dB decay time. This is the time required for the integrator to lose 99.9 % of its
 * value or -60dB.
 * 
 * Note: This should not be confused with `Lag` which does not overshoot due to integration,
 * but asymptotically follows the input signal.
 * 
 * @param in              input signal to be processed
 * 
 * @see [[de.sciss.synth.ugen.Integrator]]
 * @see [[de.sciss.synth.ugen.Decay2]]
 * @see [[de.sciss.synth.ugen.Lag]]
 */
final case class Decay[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _time = time.expand
      val _sz_in = _in.size
      val _sz_time = _time.size
      val _exp_ = maxInt(_sz_in, _sz_time)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Decay", rate, IIdxSeq(_in(i.%(_sz_in)), _time(i.%(_sz_time)))))
   }
}
/**
 * A integrator UGen with controllable attack and release times.
 * While `Decay` has a very sharp attack and can produce clicks, `Decay2` rounds off the attack by
 * subtracting one Decay from another. It can be seen as equivalent to
 * {{{
 * Decay.ar(in, release) - Decay.ar(in, attack)
 * }}}
 * 
 * Note: This should not be confused with `LagUD` which does not overshoot due to integration,
 * but asymptotically follows the input signal.
 * 
 * @see [[de.sciss.synth.ugen.Decay]]
 * @see [[de.sciss.synth.ugen.Integrator]]
 * @see [[de.sciss.synth.ugen.LagUD]]
 */
object Decay2 {
   
   /**
    * @param in              input signal to be processed
    */
   def kr(in: GE[control], attack: AnyGE = 0.01f, release: AnyGE = 1.0f) = apply[control](control, in, attack, release)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE[audio], attack: AnyGE = 0.01f, release: AnyGE = 1.0f) = apply[audio](audio, in, attack, release)
}
/**
 * A integrator UGen with controllable attack and release times.
 * While `Decay` has a very sharp attack and can produce clicks, `Decay2` rounds off the attack by
 * subtracting one Decay from another. It can be seen as equivalent to
 * {{{
 * Decay.ar(in, release) - Decay.ar(in, attack)
 * }}}
 * 
 * Note: This should not be confused with `LagUD` which does not overshoot due to integration,
 * but asymptotically follows the input signal.
 * 
 * @param in              input signal to be processed
 * 
 * @see [[de.sciss.synth.ugen.Decay]]
 * @see [[de.sciss.synth.ugen.Integrator]]
 * @see [[de.sciss.synth.ugen.LagUD]]
 */
final case class Decay2[R <: Rate](rate: R, in: AnyGE, attack: AnyGE, release: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _attack = attack.expand
      val _release = release.expand
      val _sz_in = _in.size
      val _sz_attack = _attack.size
      val _sz_release = _release.size
      val _exp_ = maxInt(_sz_in, _sz_attack, _sz_release)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Decay2", rate, IIdxSeq(_in(i.%(_sz_in)), _attack(i.%(_sz_attack)), _release(i.%(_sz_release)))))
   }
}
object Delay1 {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Delay1[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("Delay1", rate, IIdxSeq(_in(i))))
   }
}
object Delay2 {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Delay2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("Delay2", rate, IIdxSeq(_in(i))))
   }
}
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
   def kr(in: GE[control], coeff: AnyGE = 1.0f) = apply[control](control, in, coeff)
   /**
    * @param in              input signal to be processed
    * @param coeff           the leak coefficient. Should be between -1 and +1
    */
   def ar(in: GE[audio], coeff: AnyGE = 1.0f) = apply[audio](audio, in, coeff)
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
final case class Integrator[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _coeff = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Integrator", rate, IIdxSeq(_in(i.%(_sz_in)), _coeff(i.%(_sz_coeff)))))
   }
}
object LeakDC {
   def kr(in: GE[control], coeff: AnyGE = 0.9f) = apply[control](control, in, coeff)
   def ar(in: GE[audio], coeff: AnyGE = 0.995f) = apply[audio](audio, in, coeff)
}
final case class LeakDC[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _coeff = coeff.expand
      val _sz_in = _in.size
      val _sz_coeff = _coeff.size
      val _exp_ = maxInt(_sz_in, _sz_coeff)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LeakDC", rate, IIdxSeq(_in(i.%(_sz_in)), _coeff(i.%(_sz_coeff)))))
   }
}
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
   def kr(in: GE[control]) = apply[control](control, in)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE[audio]) = apply[audio](audio, in)
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
final case class LPZ1[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("LPZ1", rate, IIdxSeq(_in(i))))
   }
}
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
   def kr(in: GE[control]) = apply[control](control, in)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE[audio]) = apply[audio](audio, in)
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
final case class HPZ1[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("HPZ1", rate, IIdxSeq(_in(i))))
   }
}
object LPZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class LPZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("LPZ2", rate, IIdxSeq(_in(i))))
   }
}
object HPZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class HPZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("HPZ2", rate, IIdxSeq(_in(i))))
   }
}
object BPZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class BPZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("BPZ2", rate, IIdxSeq(_in(i))))
   }
}
object BRZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class BRZ2[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("BRZ2", rate, IIdxSeq(_in(i))))
   }
}
object APF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[control](control, in, freq, radius)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[audio](audio, in, freq, radius)
}
final case class APF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _radius = radius.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_radius = _radius.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_radius)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("APF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _radius(i.%(_sz_radius)))))
   }
}
object LPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f) = apply[control](control, in, freq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f) = apply[audio](audio, in, freq)
}
final case class LPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _exp_ = maxInt(_sz_in, _sz_freq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LPF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)))))
   }
}
object HPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f) = apply[control](control, in, freq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f) = apply[audio](audio, in, freq)
}
final case class HPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _exp_ = maxInt(_sz_in, _sz_freq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("HPF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)))))
   }
}
object BPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class BPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BPF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
object BRF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class BRF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BRF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
object RLPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class RLPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("RLPF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
object RHPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class RHPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("RHPF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
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
   def kr(in: GE[control], up: AnyGE = 1.0f, down: AnyGE = 1.0f) = apply[control](control, in, up, down)
   /**
    * @param in              input signal
    * @param up              maximum upward slope.
    * @param down            maximum downward slope.
    */
   def ar(in: GE[audio], up: AnyGE = 1.0f, down: AnyGE = 1.0f) = apply[audio](audio, in, up, down)
}
/**
 * A slew rate limiter UGen.
 * Limits the slope of an input signal. The slope is expressed in units per second.
 * 
 * @param in              input signal
 * @param up              maximum upward slope.
 * @param down            maximum downward slope.
 */
final case class Slew[R <: Rate](rate: R, in: AnyGE, up: AnyGE, down: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _up = up.expand
      val _down = down.expand
      val _sz_in = _in.size
      val _sz_up = _up.size
      val _sz_down = _down.size
      val _exp_ = maxInt(_sz_in, _sz_up, _sz_down)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Slew", rate, IIdxSeq(_in(i.%(_sz_in)), _up(i.%(_sz_up)), _down(i.%(_sz_down)))))
   }
}
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
   def kr(in: GE[control]) = apply[control](control, in)
   /**
    * @param in              input signal to be measured
    */
   def ar(in: GE[audio]) = apply[audio](audio, in)
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
final case class Slope[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("Slope", rate, IIdxSeq(_in(i))))
   }
}
object MidEQ {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply[control](control, in, freq, rq, gain)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply[audio](audio, in, freq, rq, gain)
}
final case class MidEQ[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE, gain: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _gain = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("MidEQ", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)), _gain(i.%(_sz_gain)))))
   }
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object Median {
   def kr(in: GE[control], length: AnyGE = 3.0f) = apply[control](control, in, length)
   def ar(in: GE[audio], length: AnyGE = 3.0f) = apply[audio](audio, in, length)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class Median[R <: Rate](rate: R, in: AnyGE, length: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _length = length.expand
      val _sz_in = _in.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_in, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Median", rate, IIdxSeq(_in(i.%(_sz_in)), _length(i.%(_sz_length)))))
   }
}
object Resonz {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class Resonz[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Resonz", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
object Ringz {
   def kr(in: GE[control], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[control](control, in, freq, attack, decay)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[audio](audio, in, freq, attack, decay)
}
final case class Ringz[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, attack: AnyGE, decay: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _attack = attack.expand
      val _decay = decay.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_attack = _attack.size
      val _sz_decay = _decay.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_attack, _sz_decay)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Ringz", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _attack(i.%(_sz_attack)), _decay(i.%(_sz_decay)))))
   }
}
object Formlet {
   def kr(in: GE[control], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[control](control, in, freq, attack, decay)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[audio](audio, in, freq, attack, decay)
}
final case class Formlet[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, attack: AnyGE, decay: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _attack = attack.expand
      val _decay = decay.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_attack = _attack.size
      val _sz_decay = _decay.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_attack, _sz_decay)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Formlet", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _attack(i.%(_sz_attack)), _decay(i.%(_sz_decay)))))
   }
}
object FOS {
   def kr(in: GE[control], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, b1: AnyGE = 0.0f) = apply[control](control, in, a0, a1, b1)
   def ar(in: GE[audio], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, b1: AnyGE = 0.0f) = apply[audio](audio, in, a0, a1, b1)
}
final case class FOS[R <: Rate](rate: R, in: AnyGE, a0: AnyGE, a1: AnyGE, b1: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _a0 = a0.expand
      val _a1 = a1.expand
      val _b1 = b1.expand
      val _sz_in = _in.size
      val _sz_a0 = _a0.size
      val _sz_a1 = _a1.size
      val _sz_b1 = _b1.size
      val _exp_ = maxInt(_sz_in, _sz_a0, _sz_a1, _sz_b1)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FOS", rate, IIdxSeq(_in(i.%(_sz_in)), _a0(i.%(_sz_a0)), _a1(i.%(_sz_a1)), _b1(i.%(_sz_b1)))))
   }
}
object SOS {
   def kr(in: GE[control], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, a2: AnyGE = 0.0f, b1: AnyGE = 0.0f, b2: AnyGE = 0.0f) = apply[control](control, in, a0, a1, a2, b1, b2)
   def ar(in: GE[audio], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, a2: AnyGE = 0.0f, b1: AnyGE = 0.0f, b2: AnyGE = 0.0f) = apply[audio](audio, in, a0, a1, a2, b1, b2)
}
final case class SOS[R <: Rate](rate: R, in: AnyGE, a0: AnyGE, a1: AnyGE, a2: AnyGE, b1: AnyGE, b2: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _a0 = a0.expand
      val _a1 = a1.expand
      val _a2 = a2.expand
      val _b1 = b1.expand
      val _b2 = b2.expand
      val _sz_in = _in.size
      val _sz_a0 = _a0.size
      val _sz_a1 = _a1.size
      val _sz_a2 = _a2.size
      val _sz_b1 = _b1.size
      val _sz_b2 = _b2.size
      val _exp_ = maxInt(_sz_in, _sz_a0, _sz_a1, _sz_a2, _sz_b1, _sz_b2)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("SOS", rate, IIdxSeq(_in(i.%(_sz_in)), _a0(i.%(_sz_a0)), _a1(i.%(_sz_a1)), _a2(i.%(_sz_a2)), _b1(i.%(_sz_b1)), _b2(i.%(_sz_b2)))))
   }
}
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
final case class Compander[R <: Rate](rate: R, in: AnyGE, ctrl: AnyGE, thresh: AnyGE, ratioBelow: AnyGE, ratioAbove: AnyGE, attack: AnyGE, release: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _ctrl = ctrl.expand
      val _thresh = thresh.expand
      val _ratioBelow = ratioBelow.expand
      val _ratioAbove = ratioAbove.expand
      val _attack = attack.expand
      val _release = release.expand
      val _sz_in = _in.size
      val _sz_ctrl = _ctrl.size
      val _sz_thresh = _thresh.size
      val _sz_ratioBelow = _ratioBelow.size
      val _sz_ratioAbove = _ratioAbove.size
      val _sz_attack = _attack.size
      val _sz_release = _release.size
      val _exp_ = maxInt(_sz_in, _sz_ctrl, _sz_thresh, _sz_ratioBelow, _sz_ratioAbove, _sz_attack, _sz_release)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Compander", rate, IIdxSeq(_in(i.%(_sz_in)), _ctrl(i.%(_sz_ctrl)), _thresh(i.%(_sz_thresh)), _ratioBelow(i.%(_sz_ratioBelow)), _ratioAbove(i.%(_sz_ratioAbove)), _attack(i.%(_sz_attack)), _release(i.%(_sz_release)))))
   }
}
object Limiter {
   def kr(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[control](control, in, level, dur)
   def ar(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[audio](audio, in, level, dur)
}
final case class Limiter[R <: Rate](rate: R, in: AnyGE, level: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _level = level.expand
      val _dur = dur.expand
      val _sz_in = _in.size
      val _sz_level = _level.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_level, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Limiter", rate, IIdxSeq(_in(i.%(_sz_in)), _level(i.%(_sz_level)), _dur(i.%(_sz_dur)))))
   }
}
object Normalizer {
   def kr(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[control](control, in, level, dur)
   def ar(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[audio](audio, in, level, dur)
}
final case class Normalizer[R <: Rate](rate: R, in: AnyGE, level: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _level = level.expand
      val _dur = dur.expand
      val _sz_in = _in.size
      val _sz_level = _level.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_level, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Normalizer", rate, IIdxSeq(_in(i.%(_sz_in)), _level(i.%(_sz_level)), _dur(i.%(_sz_dur)))))
   }
}
object Amplitude {
   def kr(in: AnyGE, attack: AnyGE = 0.01f, release: AnyGE = 0.01f) = apply[control](control, in, attack, release)
   def ar(in: AnyGE, attack: AnyGE = 0.01f, release: AnyGE = 0.01f) = apply[audio](audio, in, attack, release)
}
final case class Amplitude[R <: Rate](rate: R, in: AnyGE, attack: AnyGE, release: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _attack = attack.expand
      val _release = release.expand
      val _sz_in = _in.size
      val _sz_attack = _attack.size
      val _sz_release = _release.size
      val _exp_ = maxInt(_sz_in, _sz_attack, _sz_release)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Amplitude", rate, IIdxSeq(_in(i.%(_sz_in)), _attack(i.%(_sz_attack)), _release(i.%(_sz_release)))))
   }
}
object DetectSilence {
   def kr(in: GE[control], amp: AnyGE = 1.0E-4f, dur: AnyGE = 0.1f, doneAction: AnyGE = doNothing) = apply[control](control, in, amp, dur, doneAction)
   def ar(in: GE[audio], amp: AnyGE = 1.0E-4f, dur: AnyGE = 0.1f, doneAction: AnyGE = doNothing) = apply[audio](audio, in, amp, dur, doneAction)
}
final case class DetectSilence[R <: Rate](rate: R, in: AnyGE, amp: AnyGE, dur: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R] with HasSideEffect {
   protected def expandUGens = {
      val _in = in.expand
      val _amp = amp.expand
      val _dur = dur.expand
      val _doneAction = doneAction.expand
      val _sz_in = _in.size
      val _sz_amp = _amp.size
      val _sz_dur = _dur.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_in, _sz_amp, _sz_dur, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DetectSilence", rate, IIdxSeq(_in(i.%(_sz_in)), _amp(i.%(_sz_amp)), _dur(i.%(_sz_dur)), _doneAction(i.%(_sz_doneAction)))))
   }
}
object Hilbert {
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Hilbert[R <: Rate](rate: R, in: AnyGE) extends MultiOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new MultiOutUGen("Hilbert", rate, IIdxSeq.fill(2)(rate), IIdxSeq(_in(i))))
   }
}
object FreqShift {
   def ar(in: AnyGE, freq: AnyGE = 0.0f, phase: AnyGE = 0.0f) = apply[audio](audio, in, freq, phase)
}
final case class FreqShift[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _phase = phase.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FreqShift", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase)))))
   }
}
object MoogFF {
   def kr(in: GE[control], freq: AnyGE = 200.0f, gain: AnyGE = 2.0f, reset: AnyGE = 0.0f) = apply[control](control, in, freq, gain, reset)
   def ar(in: GE[audio], freq: AnyGE = 200.0f, gain: AnyGE = 2.0f, reset: AnyGE = 0.0f) = apply[audio](audio, in, freq, gain, reset)
}
final case class MoogFF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, gain: AnyGE, reset: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _gain = gain.expand
      val _reset = reset.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_gain = _gain.size
      val _sz_reset = _reset.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_gain, _sz_reset)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("MoogFF", rate, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _gain(i.%(_sz_gain)), _reset(i.%(_sz_reset)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply(in, freq, rq)
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
final case class BLowPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BLowPass", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply(in, freq, rq)
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
final case class BHiPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BHiPass", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, bw: AnyGE = 1.0f) = apply(in, freq, bw)
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
final case class BBandPass(in: AnyGE, freq: AnyGE, bw: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _bw = bw.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_bw = _bw.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_bw)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BBandPass", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _bw(i.%(_sz_bw)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, bw: AnyGE = 1.0f) = apply(in, freq, bw)
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
final case class BBandStop(in: AnyGE, freq: AnyGE, bw: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _bw = bw.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_bw = _bw.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_bw)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BBandStop", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _bw(i.%(_sz_bw)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply(in, freq, rq, gain)
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
final case class BPeakEQ(in: AnyGE, freq: AnyGE, rq: AnyGE, gain: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _gain = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BPeakEQ", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)), _gain(i.%(_sz_gain)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply(in, freq, rq)
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
final case class BAllPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rq = rq.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rq = _rq.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BAllPass", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rq(i.%(_sz_rq)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rs: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply(in, freq, rs, gain)
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
final case class BLowShelf(in: AnyGE, freq: AnyGE, rs: AnyGE, gain: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rs = rs.expand
      val _gain = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rs = _rs.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rs, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BLowShelf", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rs(i.%(_sz_rs)), _gain(i.%(_sz_gain)))))
   }
}
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
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rs: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply(in, freq, rs, gain)
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
final case class BHiShelf(in: AnyGE, freq: AnyGE, rs: AnyGE, gain: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in = in.expand
      val _freq = freq.expand
      val _rs = rs.expand
      val _gain = gain.expand
      val _sz_in = _in.size
      val _sz_freq = _freq.size
      val _sz_rs = _rs.size
      val _sz_gain = _gain.size
      val _exp_ = maxInt(_sz_in, _sz_freq, _sz_rs, _sz_gain)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("BHiShelf", audio, IIdxSeq(_in(i.%(_sz_in)), _freq(i.%(_sz_freq)), _rs(i.%(_sz_rs)), _gain(i.%(_sz_gain)))))
   }
}