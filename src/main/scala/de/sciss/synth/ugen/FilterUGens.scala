/*
 * FilterUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Mon Oct 31 11:22:31 GMT 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
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
   def kr(in: GE, dur: GE = 0.1f) = apply(control, in, dur)
   /**
    * @param in              the signal to smooth out
    * @param dur             the ramp-time (seconds) which is also the interval of the sampling
    */
   def ar(in: GE, dur: GE = 0.1f) = apply(audio, in, dur)
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
final case class Ramp(rate: MaybeRate, in: GE, dur: GE) extends UGenSource.SingleOut("Ramp") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, time: GE = 0.1f) = apply(control, in, time)
   /**
    * @param in              input signal.
    * @param time            60 dB lag time in seconds.
    */
   def ar(in: GE, time: GE = 0.1f) = apply(audio, in, time)
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
final case class Lag(rate: MaybeRate, in: GE, time: GE) extends UGenSource.SingleOut("Lag") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, time: GE = 0.1f) = apply(control, in, time)
   /**
    * @param in              input signal.
    * @param time            60 dB lag time in seconds.
    */
   def ar(in: GE, time: GE = 0.1f) = apply(audio, in, time)
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
final case class Lag2(rate: MaybeRate, in: GE, time: GE) extends UGenSource.SingleOut("Lag2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, time: GE = 0.1f) = apply(control, in, time)
   /**
    * @param in              input signal.
    * @param time            60 dB lag time in seconds.
    */
   def ar(in: GE, time: GE = 0.1f) = apply(audio, in, time)
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
final case class Lag3(rate: MaybeRate, in: GE, time: GE) extends UGenSource.SingleOut("Lag3") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f) = apply(control, in, timeUp, timeDown)
   /**
    * @param in              input signal.
    * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
    * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
    */
   def ar(in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f) = apply(audio, in, timeUp, timeDown)
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
final case class LagUD(rate: MaybeRate, in: GE, timeUp: GE, timeDown: GE) extends UGenSource.SingleOut("LagUD") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, timeUp.expand, timeDown.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f) = apply(control, in, timeUp, timeDown)
   /**
    * @param in              input signal.
    * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
    * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
    */
   def ar(in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f) = apply(audio, in, timeUp, timeDown)
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
final case class Lag2UD(rate: MaybeRate, in: GE, timeUp: GE, timeDown: GE) extends UGenSource.SingleOut("Lag2UD") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, timeUp.expand, timeDown.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f) = apply(control, in, timeUp, timeDown)
   /**
    * @param in              input signal.
    * @param timeUp          60 dB lag time in seconds effective during a rising slope in the input signal
    * @param timeDown        60 dB lag time in seconds effective during a falling slope in the input signal
    */
   def ar(in: GE, timeUp: GE = 0.1f, timeDown: GE = 0.1f) = apply(audio, in, timeUp, timeDown)
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
final case class Lag3UD(rate: MaybeRate, in: GE, timeUp: GE, timeDown: GE) extends UGenSource.SingleOut("Lag3UD") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, timeUp.expand, timeDown.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, coeff: GE = 0.5f) = apply(control, in, coeff)
   /**
    * @param in              input signal to be processed
    * @param coeff           feedback coefficient. Should be between -1 and +1
    */
   def ar(in: GE, coeff: GE = 0.5f) = apply(audio, in, coeff)
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
final case class OnePole(rate: MaybeRate, in: GE, coeff: GE) extends UGenSource.SingleOut("OnePole") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, coeff: GE = 0.5f) = apply(control, in, coeff)
   /**
    * @param in              input signal to be processed
    * @param coeff           feed forward coefficient. +0.5 makes a two point averaging filter (see also `LPZ1`),
    *                        -0.5 makes a differentiator (see also `HPZ1`),  +1 makes a single sample delay (see also `Delay1`),
    *                        -1 makes an inverted single sample delay.
    */
   def ar(in: GE, coeff: GE = 0.5f) = apply(audio, in, coeff)
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
final case class OneZero(rate: MaybeRate, in: GE, coeff: GE) extends UGenSource.SingleOut("OneZero") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object TwoPole {
   
   /**
    * @param in              input signal to be processed
    */
   def kr(in: GE, freq: GE = 440.0f, radius: GE = 0.8f) = apply(control, in, freq, radius)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE, freq: GE = 440.0f, radius: GE = 0.8f) = apply(audio, in, freq, radius)
}
/**
 * @param in              input signal to be processed
 */
final case class TwoPole(rate: MaybeRate, in: GE, freq: GE, radius: GE) extends UGenSource.SingleOut("TwoPole") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, radius.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object TwoZero {
   
   /**
    * @param in              input signal to be processed
    */
   def kr(in: GE, freq: GE = 440.0f, radius: GE = 0.8f) = apply(control, in, freq, radius)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE, freq: GE = 440.0f, radius: GE = 0.8f) = apply(audio, in, freq, radius)
}
/**
 * @param in              input signal to be processed
 */
final case class TwoZero(rate: MaybeRate, in: GE, freq: GE, radius: GE) extends UGenSource.SingleOut("TwoZero") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, radius.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, time: GE = 1.0f) = apply(control, in, time)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE, time: GE = 1.0f) = apply(audio, in, time)
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
final case class Decay(rate: MaybeRate, in: GE, time: GE) extends UGenSource.SingleOut("Decay") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, time.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, attack: GE = 0.01f, release: GE = 1.0f) = apply(control, in, attack, release)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE, attack: GE = 0.01f, release: GE = 1.0f) = apply(audio, in, attack, release)
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
final case class Decay2(rate: MaybeRate, in: GE, attack: GE, release: GE) extends UGenSource.SingleOut("Decay2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, attack.expand, release.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object Delay1 {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class Delay1(rate: Rate, in: GE) extends UGenSource.SingleOut("Delay1") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Delay2 {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class Delay2(rate: Rate, in: GE) extends UGenSource.SingleOut("Delay2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(in: GE, coeff: GE = 1.0f) = apply(control, in, coeff)
   /**
    * @param in              input signal to be processed
    * @param coeff           the leak coefficient. Should be between -1 and +1
    */
   def ar(in: GE, coeff: GE = 1.0f) = apply(audio, in, coeff)
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
final case class Integrator(rate: MaybeRate, in: GE, coeff: GE) extends UGenSource.SingleOut("Integrator") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object LeakDC {
   def kr(in: GE, coeff: GE = 0.9f) = apply(control, in, coeff)
   def ar(in: GE, coeff: GE = 0.995f) = apply(audio, in, coeff)
}
final case class LeakDC(rate: MaybeRate, in: GE, coeff: GE) extends UGenSource.SingleOut("LeakDC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, coeff.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE) = apply(control, in)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE) = apply(audio, in)
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
final case class LPZ1(rate: MaybeRate, in: GE) extends UGenSource.SingleOut("LPZ1") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE) = apply(control, in)
   /**
    * @param in              input signal to be processed
    */
   def ar(in: GE) = apply(audio, in)
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
final case class HPZ1(rate: MaybeRate, in: GE) extends UGenSource.SingleOut("HPZ1") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object LPZ2 {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class LPZ2(rate: MaybeRate, in: GE) extends UGenSource.SingleOut("LPZ2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object HPZ2 {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class HPZ2(rate: MaybeRate, in: GE) extends UGenSource.SingleOut("HPZ2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object BPZ2 {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class BPZ2(rate: MaybeRate, in: GE) extends UGenSource.SingleOut("BPZ2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object BRZ2 {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class BRZ2(rate: MaybeRate, in: GE) extends UGenSource.SingleOut("BRZ2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object APF {
   def kr(in: GE, freq: GE = 440.0f, radius: GE = 0.8f) = apply(control, in, freq, radius)
   def ar(in: GE, freq: GE = 440.0f, radius: GE = 0.8f) = apply(audio, in, freq, radius)
}
final case class APF(rate: MaybeRate, in: GE, freq: GE, radius: GE) extends UGenSource.SingleOut("APF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, radius.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object LPF {
   def kr(in: GE, freq: GE = 440.0f) = apply(control, in, freq)
   def ar(in: GE, freq: GE = 440.0f) = apply(audio, in, freq)
}
final case class LPF(rate: MaybeRate, in: GE, freq: GE) extends UGenSource.SingleOut("LPF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object HPF {
   def kr(in: GE, freq: GE = 440.0f) = apply(control, in, freq)
   def ar(in: GE, freq: GE = 440.0f) = apply(audio, in, freq)
}
final case class HPF(rate: MaybeRate, in: GE, freq: GE) extends UGenSource.SingleOut("HPF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object BPF {
   def kr(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(control, in, freq, rq)
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(audio, in, freq, rq)
}
final case class BPF(rate: MaybeRate, in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("BPF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object BRF {
   def kr(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(control, in, freq, rq)
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(audio, in, freq, rq)
}
final case class BRF(rate: MaybeRate, in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("BRF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object RLPF {
   def kr(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(control, in, freq, rq)
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(audio, in, freq, rq)
}
final case class RLPF(rate: MaybeRate, in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("RLPF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object RHPF {
   def kr(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(control, in, freq, rq)
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(audio, in, freq, rq)
}
final case class RHPF(rate: MaybeRate, in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("RHPF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, up: GE = 1.0f, down: GE = 1.0f) = apply(control, in, up, down)
   /**
    * @param in              input signal
    * @param up              maximum upward slope.
    * @param down            maximum downward slope.
    */
   def ar(in: GE, up: GE = 1.0f, down: GE = 1.0f) = apply(audio, in, up, down)
}
/**
 * A slew rate limiter UGen.
 * Limits the slope of an input signal. The slope is expressed in units per second.
 * 
 * @param in              input signal
 * @param up              maximum upward slope.
 * @param down            maximum downward slope.
 */
final case class Slew(rate: MaybeRate, in: GE, up: GE, down: GE) extends UGenSource.SingleOut("Slew") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, up.expand, down.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE) = apply(control, in)
   /**
    * @param in              input signal to be measured
    */
   def ar(in: GE) = apply(audio, in)
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
final case class Slope(rate: MaybeRate, in: GE) extends UGenSource.SingleOut("Slope") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object MidEQ {
   def kr(in: GE, freq: GE = 440.0f, rq: GE = 1.0f, gain: GE = 0.0f) = apply(control, in, freq, rq, gain)
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f, gain: GE = 0.0f) = apply(audio, in, freq, rq, gain)
}
final case class MidEQ(rate: MaybeRate, in: GE, freq: GE, rq: GE, gain: GE) extends UGenSource.SingleOut("MidEQ") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object Median {
   def kr(in: GE, length: GE = 3.0f) = apply(control, in, length)
   def ar(in: GE, length: GE = 3.0f) = apply(audio, in, length)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class Median(rate: MaybeRate, in: GE, length: GE) extends UGenSource.SingleOut("Median") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object Resonz {
   def kr(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(control, in, freq, rq)
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(audio, in, freq, rq)
}
final case class Resonz(rate: MaybeRate, in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("Resonz") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object Ringz {
   def kr(in: GE, freq: GE = 440.0f, attack: GE = 1.0f, decay: GE = 1.0f) = apply(control, in, freq, attack, decay)
   def ar(in: GE, freq: GE = 440.0f, attack: GE = 1.0f, decay: GE = 1.0f) = apply(audio, in, freq, attack, decay)
}
final case class Ringz(rate: MaybeRate, in: GE, freq: GE, attack: GE, decay: GE) extends UGenSource.SingleOut("Ringz") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, attack.expand, decay.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object Formlet {
   def kr(in: GE, freq: GE = 440.0f, attack: GE = 1.0f, decay: GE = 1.0f) = apply(control, in, freq, attack, decay)
   def ar(in: GE, freq: GE = 440.0f, attack: GE = 1.0f, decay: GE = 1.0f) = apply(audio, in, freq, attack, decay)
}
final case class Formlet(rate: MaybeRate, in: GE, freq: GE, attack: GE, decay: GE) extends UGenSource.SingleOut("Formlet") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, attack.expand, decay.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object FOS {
   def kr(in: GE, a0: GE = 0.0f, a1: GE = 0.0f, b1: GE = 0.0f) = apply(control, in, a0, a1, b1)
   def ar(in: GE, a0: GE = 0.0f, a1: GE = 0.0f, b1: GE = 0.0f) = apply(audio, in, a0, a1, b1)
}
final case class FOS(rate: MaybeRate, in: GE, a0: GE, a1: GE, b1: GE) extends UGenSource.SingleOut("FOS") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, a0.expand, a1.expand, b1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object SOS {
   def kr(in: GE, a0: GE = 0.0f, a1: GE = 0.0f, a2: GE = 0.0f, b1: GE = 0.0f, b2: GE = 0.0f) = apply(control, in, a0, a1, a2, b1, b2)
   def ar(in: GE, a0: GE = 0.0f, a1: GE = 0.0f, a2: GE = 0.0f, b1: GE = 0.0f, b2: GE = 0.0f) = apply(audio, in, a0, a1, a2, b1, b2)
}
final case class SOS(rate: MaybeRate, in: GE, a0: GE, a1: GE, a2: GE, b1: GE, b2: GE) extends UGenSource.SingleOut("SOS") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, a0.expand, a1.expand, a2.expand, b1.expand, b2.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def kr(in: GE, ctrl: GE, thresh: GE = 0.5f, ratioBelow: GE = 1.0f, ratioAbove: GE = 1.0f, attack: GE = 0.01f, release: GE = 0.1f) = apply(control, in, ctrl, thresh, ratioBelow, ratioAbove, attack, release)
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
   def ar(in: GE, ctrl: GE, thresh: GE = 0.5f, ratioBelow: GE = 1.0f, ratioAbove: GE = 1.0f, attack: GE = 0.01f, release: GE = 0.1f) = apply(audio, in, ctrl, thresh, ratioBelow, ratioAbove, attack, release)
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
final case class Compander(rate: Rate, in: GE, ctrl: GE, thresh: GE, ratioBelow: GE, ratioAbove: GE, attack: GE, release: GE) extends UGenSource.SingleOut("Compander") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, ctrl.expand, thresh.expand, ratioBelow.expand, ratioAbove.expand, attack.expand, release.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Limiter {
   def kr(in: GE, level: GE = 1.0f, dur: GE = 0.01f) = apply(control, in, level, dur)
   def ar(in: GE, level: GE = 1.0f, dur: GE = 0.01f) = apply(audio, in, level, dur)
}
final case class Limiter(rate: Rate, in: GE, level: GE, dur: GE) extends UGenSource.SingleOut("Limiter") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, level.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Normalizer {
   def kr(in: GE, level: GE = 1.0f, dur: GE = 0.01f) = apply(control, in, level, dur)
   def ar(in: GE, level: GE = 1.0f, dur: GE = 0.01f) = apply(audio, in, level, dur)
}
final case class Normalizer(rate: Rate, in: GE, level: GE, dur: GE) extends UGenSource.SingleOut("Normalizer") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, level.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Amplitude {
   def kr(in: GE, attack: GE = 0.01f, release: GE = 0.01f) = apply(control, in, attack, release)
   def ar(in: GE, attack: GE = 0.01f, release: GE = 0.01f) = apply(audio, in, attack, release)
}
final case class Amplitude(rate: Rate, in: GE, attack: GE, release: GE) extends UGenSource.SingleOut("Amplitude") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, attack.expand, release.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object DetectSilence {
   def kr(in: GE, amp: GE = 1.0E-4f, dur: GE = 0.1f, doneAction: GE = doNothing) = apply(control, in, amp, dur, doneAction)
   def ar(in: GE, amp: GE = 1.0E-4f, dur: GE = 0.1f, doneAction: GE = doNothing) = apply(audio, in, amp, dur, doneAction)
}
final case class DetectSilence(rate: MaybeRate, in: GE, amp: GE, dur: GE, doneAction: GE) extends UGenSource.SingleOut("DetectSilence") with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, amp.expand, dur.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args, false, true)
   }
}
object Hilbert {
   def ar(in: GE) = apply(audio, in)
}
final case class Hilbert(rate: Rate, in: GE) extends UGenSource.MultiOut("Hilbert") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(2)(rate), _args)
}
object FreqShift {
   def ar(in: GE, freq: GE = 0.0f, phase: GE = 0.0f) = apply(audio, in, freq, phase)
}
final case class FreqShift(rate: Rate, in: GE, freq: GE, phase: GE) extends UGenSource.SingleOut("FreqShift") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object MoogFF {
   def kr(in: GE, freq: GE = 200.0f, gain: GE = 2.0f, reset: GE = 0.0f) = apply(control, in, freq, gain, reset)
   def ar(in: GE, freq: GE = 200.0f, gain: GE = 2.0f, reset: GE = 0.0f) = apply(audio, in, freq, gain, reset)
}
final case class MoogFF(rate: MaybeRate, in: GE, freq: GE, gain: GE, reset: GE) extends UGenSource.SingleOut("MoogFF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, gain.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
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
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(in, freq, rq)
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
final case class BLowPass(in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("BLowPass") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
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
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(in, freq, rq)
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
final case class BHiPass(in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("BHiPass") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
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
   def ar(in: GE, freq: GE = 440.0f, bw: GE = 1.0f) = apply(in, freq, bw)
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
final case class BBandPass(in: GE, freq: GE, bw: GE) extends UGenSource.SingleOut("BBandPass") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, bw.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
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
   def ar(in: GE, freq: GE = 440.0f, bw: GE = 1.0f) = apply(in, freq, bw)
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
final case class BBandStop(in: GE, freq: GE, bw: GE) extends UGenSource.SingleOut("BBandStop") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, bw.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
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
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f, gain: GE = 0.0f) = apply(in, freq, rq, gain)
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
final case class BPeakEQ(in: GE, freq: GE, rq: GE, gain: GE) extends UGenSource.SingleOut("BPeakEQ") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
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
   def ar(in: GE, freq: GE = 440.0f, rq: GE = 1.0f) = apply(in, freq, rq)
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
final case class BAllPass(in: GE, freq: GE, rq: GE) extends UGenSource.SingleOut("BAllPass") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
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
   def ar(in: GE, freq: GE = 440.0f, rs: GE = 1.0f, gain: GE = 0.0f) = apply(in, freq, rs, gain)
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
final case class BLowShelf(in: GE, freq: GE, rs: GE, gain: GE) extends UGenSource.SingleOut("BLowShelf") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rs.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
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
   def ar(in: GE, freq: GE = 440.0f, rs: GE = 1.0f, gain: GE = 0.0f) = apply(in, freq, rs, gain)
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
final case class BHiShelf(in: GE, freq: GE, rs: GE, gain: GE) extends UGenSource.SingleOut("BHiShelf") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, freq.expand, rs.expand, gain.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
}