/*
 * FilterUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Mar 04 23:36:58 GMT 2011
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
final case class Ramp[R <: Rate](rate: R, in: AnyGE, dur: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Ramp", rate, _args)
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
final case class Lag[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Lag", rate, _args)
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
final case class Lag2[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Lag2", rate, _args)
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
final case class Lag3[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Lag3", rate, _args)
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
final case class LagUD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, timeUp.expand, timeDown.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LagUD", rate, _args)
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
final case class Lag2UD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, timeUp.expand, timeDown.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Lag2UD", rate, _args)
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
final case class Lag3UD[R <: Rate](rate: R, in: AnyGE, timeUp: AnyGE, timeDown: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, timeUp.expand, timeDown.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Lag3UD", rate, _args)
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
final case class OnePole[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("OnePole", rate, _args)
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
final case class OneZero[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("OneZero", rate, _args)
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
final case class TwoPole[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, radius.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TwoPole", rate, _args)
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
final case class TwoZero[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, radius.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TwoZero", rate, _args)
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
final case class Decay[R <: Rate](rate: R, in: AnyGE, time: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Decay", rate, _args)
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
final case class Decay2[R <: Rate](rate: R, in: AnyGE, attack: AnyGE, release: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, attack.expand, release.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Decay2", rate, _args)
}
object Delay1 {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Delay1[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Delay1", rate, _args)
}
object Delay2 {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Delay2[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Delay2", rate, _args)
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
final case class Integrator[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Integrator", rate, _args)
}
object LeakDC {
   def kr(in: GE[control], coeff: AnyGE = 0.9f) = apply[control](control, in, coeff)
   def ar(in: GE[audio], coeff: AnyGE = 0.995f) = apply[audio](audio, in, coeff)
}
final case class LeakDC[R <: Rate](rate: R, in: AnyGE, coeff: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LeakDC", rate, _args)
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
final case class LPZ1[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LPZ1", rate, _args)
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
final case class HPZ1[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("HPZ1", rate, _args)
}
object LPZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class LPZ2[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LPZ2", rate, _args)
}
object HPZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class HPZ2[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("HPZ2", rate, _args)
}
object BPZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class BPZ2[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BPZ2", rate, _args)
}
object BRZ2 {
   def kr(in: GE[control]) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class BRZ2[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BRZ2", rate, _args)
}
object APF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[control](control, in, freq, radius)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, radius: AnyGE = 0.8f) = apply[audio](audio, in, freq, radius)
}
final case class APF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, radius: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, radius.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("APF", rate, _args)
}
object LPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f) = apply[control](control, in, freq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f) = apply[audio](audio, in, freq)
}
final case class LPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LPF", rate, _args)
}
object HPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f) = apply[control](control, in, freq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f) = apply[audio](audio, in, freq)
}
final case class HPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("HPF", rate, _args)
}
object BPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class BPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BPF", rate, _args)
}
object BRF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class BRF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BRF", rate, _args)
}
object RLPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class RLPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RLPF", rate, _args)
}
object RHPF {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class RHPF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RHPF", rate, _args)
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
final case class Slew[R <: Rate](rate: R, in: AnyGE, up: AnyGE, down: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, up.expand, down.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Slew", rate, _args)
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
final case class Slope[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Slope", rate, _args)
}
object MidEQ {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply[control](control, in, freq, rq, gain)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f, gain: AnyGE = 0.0f) = apply[audio](audio, in, freq, rq, gain)
}
final case class MidEQ[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE, gain: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MidEQ", rate, _args)
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
final case class Median[R <: Rate](rate: R, in: AnyGE, length: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Median", rate, _args)
}
object Resonz {
   def kr(in: GE[control], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[control](control, in, freq, rq)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, rq: AnyGE = 1.0f) = apply[audio](audio, in, freq, rq)
}
final case class Resonz[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Resonz", rate, _args)
}
object Ringz {
   def kr(in: GE[control], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[control](control, in, freq, attack, decay)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[audio](audio, in, freq, attack, decay)
}
final case class Ringz[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, attack: AnyGE, decay: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, attack.expand, decay.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Ringz", rate, _args)
}
object Formlet {
   def kr(in: GE[control], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[control](control, in, freq, attack, decay)
   def ar(in: GE[audio], freq: AnyGE = 440.0f, attack: AnyGE = 1.0f, decay: AnyGE = 1.0f) = apply[audio](audio, in, freq, attack, decay)
}
final case class Formlet[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, attack: AnyGE, decay: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, attack.expand, decay.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Formlet", rate, _args)
}
object FOS {
   def kr(in: GE[control], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, b1: AnyGE = 0.0f) = apply[control](control, in, a0, a1, b1)
   def ar(in: GE[audio], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, b1: AnyGE = 0.0f) = apply[audio](audio, in, a0, a1, b1)
}
final case class FOS[R <: Rate](rate: R, in: AnyGE, a0: AnyGE, a1: AnyGE, b1: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, a0.expand, a1.expand, b1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FOS", rate, _args)
}
object SOS {
   def kr(in: GE[control], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, a2: AnyGE = 0.0f, b1: AnyGE = 0.0f, b2: AnyGE = 0.0f) = apply[control](control, in, a0, a1, a2, b1, b2)
   def ar(in: GE[audio], a0: AnyGE = 0.0f, a1: AnyGE = 0.0f, a2: AnyGE = 0.0f, b1: AnyGE = 0.0f, b2: AnyGE = 0.0f) = apply[audio](audio, in, a0, a1, a2, b1, b2)
}
final case class SOS[R <: Rate](rate: R, in: AnyGE, a0: AnyGE, a1: AnyGE, a2: AnyGE, b1: AnyGE, b2: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, a0.expand, a1.expand, a2.expand, b1.expand, b2.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SOS", rate, _args)
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
final case class Compander[R <: Rate](rate: R, in: AnyGE, ctrl: AnyGE, thresh: AnyGE, ratioBelow: AnyGE, ratioAbove: AnyGE, attack: AnyGE, release: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, ctrl.expand, thresh.expand, ratioBelow.expand, ratioAbove.expand, attack.expand, release.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Compander", rate, _args)
}
object Limiter {
   def kr(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[control](control, in, level, dur)
   def ar(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[audio](audio, in, level, dur)
}
final case class Limiter[R <: Rate](rate: R, in: AnyGE, level: AnyGE, dur: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, level.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Limiter", rate, _args)
}
object Normalizer {
   def kr(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[control](control, in, level, dur)
   def ar(in: AnyGE, level: AnyGE = 1.0f, dur: AnyGE = 0.01f) = apply[audio](audio, in, level, dur)
}
final case class Normalizer[R <: Rate](rate: R, in: AnyGE, level: AnyGE, dur: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, level.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Normalizer", rate, _args)
}
object Amplitude {
   def kr(in: AnyGE, attack: AnyGE = 0.01f, release: AnyGE = 0.01f) = apply[control](control, in, attack, release)
   def ar(in: AnyGE, attack: AnyGE = 0.01f, release: AnyGE = 0.01f) = apply[audio](audio, in, attack, release)
}
final case class Amplitude[R <: Rate](rate: R, in: AnyGE, attack: AnyGE, release: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, attack.expand, release.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Amplitude", rate, _args)
}
object DetectSilence {
   def kr(in: GE[control], amp: AnyGE = 1.0E-4f, dur: AnyGE = 0.1f, doneAction: AnyGE = doNothing) = apply[control](control, in, amp, dur, doneAction)
   def ar(in: GE[audio], amp: AnyGE = 1.0E-4f, dur: AnyGE = 0.1f, doneAction: AnyGE = doNothing) = apply[audio](audio, in, amp, dur, doneAction)
}
final case class DetectSilence[R <: Rate](rate: R, in: AnyGE, amp: AnyGE, dur: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, amp.expand, dur.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DetectSilence", rate, _args)
}
object Hilbert {
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Hilbert[R <: Rate](rate: R, in: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Hilbert", rate, IIdxSeq.fill(2)(rate), _args)
}
object FreqShift {
   def ar(in: AnyGE, freq: AnyGE = 0.0f, phase: AnyGE = 0.0f) = apply[audio](audio, in, freq, phase)
}
final case class FreqShift[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, phase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FreqShift", rate, _args)
}
object MoogFF {
   def kr(in: GE[control], freq: AnyGE = 200.0f, gain: AnyGE = 2.0f, reset: AnyGE = 0.0f) = apply[control](control, in, freq, gain, reset)
   def ar(in: GE[audio], freq: AnyGE = 200.0f, gain: AnyGE = 2.0f, reset: AnyGE = 0.0f) = apply[audio](audio, in, freq, gain, reset)
}
final case class MoogFF[R <: Rate](rate: R, in: AnyGE, freq: AnyGE, gain: AnyGE, reset: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, gain.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MoogFF", rate, _args)
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
final case class BLowPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BLowPass", audio, _args)
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
final case class BHiPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BHiPass", audio, _args)
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
final case class BBandPass(in: AnyGE, freq: AnyGE, bw: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, bw.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BBandPass", audio, _args)
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
final case class BBandStop(in: AnyGE, freq: AnyGE, bw: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, bw.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BBandStop", audio, _args)
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
final case class BPeakEQ(in: AnyGE, freq: AnyGE, rq: AnyGE, gain: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BPeakEQ", audio, _args)
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
final case class BAllPass(in: AnyGE, freq: AnyGE, rq: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BAllPass", audio, _args)
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
final case class BLowShelf(in: AnyGE, freq: AnyGE, rs: AnyGE, gain: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rs.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BLowShelf", audio, _args)
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
final case class BHiShelf(in: AnyGE, freq: AnyGE, rs: AnyGE, gain: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rs.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("BHiShelf", audio, _args)
}