/*
 * TriggerUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jan 07 14:02:40 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object Trig1 {
   def ar(in: AnyGE, dur: AnyGE = 0.1f) = apply[audio](audio, in, dur)
   def kr(in: AnyGE, dur: AnyGE = 0.1f) = apply[control](control, in, dur)
}
case class Trig1[R <: Rate](rate: R, in: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R, Trig1UGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _sz_in = _in.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => Trig1UGen(rate, _in(i.%(_sz_in)), _dur(i.%(_sz_dur))))
   }
}
case class Trig1UGen[R <: Rate](rate: R, in: AnyUGenIn, dur: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, dur))
object Trig {
   def ar(in: AnyGE, dur: AnyGE = 0.1f) = apply[audio](audio, in, dur)
   def kr(in: AnyGE, dur: AnyGE = 0.1f) = apply[control](control, in, dur)
}
case class Trig[R <: Rate](rate: R, in: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R, TrigUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _sz_in = _in.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_in, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => TrigUGen(rate, _in(i.%(_sz_in)), _dur(i.%(_sz_dur))))
   }
}
case class TrigUGen[R <: Rate](rate: R, in: AnyUGenIn, dur: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, dur))
object SendTrig {
   def ar(trig: AnyGE, value: AnyGE = 0.0f, id: AnyGE = 0.0f) = apply[audio](audio, trig, value, id)
   def kr(trig: AnyGE, value: AnyGE = 0.0f, id: AnyGE = 0.0f) = apply[control](control, trig, value, id)
}
case class SendTrig[R <: Rate](rate: R, trig: AnyGE, value: AnyGE, id: AnyGE) extends SingleOutUGenSource[R, SendTrigUGen[R]] with HasSideEffect {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _value: IIdxSeq[AnyUGenIn] = value.expand
      val _id: IIdxSeq[AnyUGenIn] = id.expand
      val _sz_trig = _trig.size
      val _sz_value = _value.size
      val _sz_id = _id.size
      val _exp_ = maxInt(_sz_trig, _sz_value, _sz_id)
      IIdxSeq.tabulate(_exp_)(i => SendTrigUGen(rate, _trig(i.%(_sz_trig)), _value(i.%(_sz_value)), _id(i.%(_sz_id))))
   }
}
case class SendTrigUGen[R <: Rate](rate: R, trig: AnyUGenIn, value: AnyUGenIn, id: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, value, id)) with HasSideEffect
object ToggleFF {
   def kr(trig: AnyGE) = apply[control](control, trig)
   def ar(trig: AnyGE) = apply[audio](audio, trig)
}
case class ToggleFF[R <: Rate](rate: R, trig: AnyGE) extends SingleOutUGenSource[R, ToggleFFUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      IIdxSeq.tabulate(_trig.size)(i => ToggleFFUGen(rate, _trig(i)))
   }
}
case class ToggleFFUGen[R <: Rate](rate: R, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig))
object SetResetFF {
   def kr(trig: AnyGE, reset: AnyGE) = apply[control](control, trig, reset)
   def ar(trig: AnyGE, reset: AnyGE) = apply[audio](audio, trig, reset)
}
case class SetResetFF[R <: Rate](rate: R, trig: AnyGE, reset: AnyGE) extends SingleOutUGenSource[R, SetResetFFUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _sz_trig = _trig.size
      val _sz_reset = _reset.size
      val _exp_ = maxInt(_sz_trig, _sz_reset)
      IIdxSeq.tabulate(_exp_)(i => SetResetFFUGen(rate, _trig(i.%(_sz_trig)), _reset(i.%(_sz_reset))))
   }
}
case class SetResetFFUGen[R <: Rate](rate: R, trig: AnyUGenIn, reset: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, reset))
object Latch {
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
case class Latch[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R, LatchUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_in = _in.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_in, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => LatchUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
   }
}
case class LatchUGen[R <: Rate](rate: R, in: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, trig))
object Gate {
   def kr(in: AnyGE, gate: AnyGE) = apply[control](control, in, gate)
   def ar(in: AnyGE, gate: AnyGE) = apply[audio](audio, in, gate)
}
case class Gate[R <: Rate](rate: R, in: AnyGE, gate: AnyGE) extends SingleOutUGenSource[R, GateUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _gate: IIdxSeq[AnyUGenIn] = gate.expand
      val _sz_in = _in.size
      val _sz_gate = _gate.size
      val _exp_ = maxInt(_sz_in, _sz_gate)
      IIdxSeq.tabulate(_exp_)(i => GateUGen(rate, _in(i.%(_sz_in)), _gate(i.%(_sz_gate))))
   }
}
case class GateUGen[R <: Rate](rate: R, in: AnyUGenIn, gate: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, gate))
object Schmidt {
   def kr(trig: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, trig, lo, hi)
   def ar(trig: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, trig, lo, hi)
}
case class Schmidt[R <: Rate](rate: R, trig: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R, SchmidtUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_trig = _trig.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_trig, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => SchmidtUGen(rate, _trig(i.%(_sz_trig)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class SchmidtUGen[R <: Rate](rate: R, trig: AnyUGenIn, lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, lo, hi))
object PulseDivider {
   def kr(trig: AnyGE, div: AnyGE = 2.0f, start: AnyGE = 0.0f) = apply[control](control, trig, div, start)
   def ar(trig: AnyGE, div: AnyGE = 2.0f, start: AnyGE = 0.0f) = apply[audio](audio, trig, div, start)
}
case class PulseDivider[R <: Rate](rate: R, trig: AnyGE, div: AnyGE, start: AnyGE) extends SingleOutUGenSource[R, PulseDividerUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _div: IIdxSeq[AnyUGenIn] = div.expand
      val _start: IIdxSeq[AnyUGenIn] = start.expand
      val _sz_trig = _trig.size
      val _sz_div = _div.size
      val _sz_start = _start.size
      val _exp_ = maxInt(_sz_trig, _sz_div, _sz_start)
      IIdxSeq.tabulate(_exp_)(i => PulseDividerUGen(rate, _trig(i.%(_sz_trig)), _div(i.%(_sz_div)), _start(i.%(_sz_start))))
   }
}
case class PulseDividerUGen[R <: Rate](rate: R, trig: AnyUGenIn, div: AnyUGenIn, start: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, div, start))
object PulseCount {
   def kr(trig: AnyGE, reset: AnyGE = 0.0f) = apply[control](control, trig, reset)
   def ar(trig: AnyGE, reset: AnyGE = 0.0f) = apply[audio](audio, trig, reset)
}
case class PulseCount[R <: Rate](rate: R, trig: AnyGE, reset: AnyGE) extends SingleOutUGenSource[R, PulseCountUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _sz_trig = _trig.size
      val _sz_reset = _reset.size
      val _exp_ = maxInt(_sz_trig, _sz_reset)
      IIdxSeq.tabulate(_exp_)(i => PulseCountUGen(rate, _trig(i.%(_sz_trig)), _reset(i.%(_sz_reset))))
   }
}
case class PulseCountUGen[R <: Rate](rate: R, trig: AnyUGenIn, reset: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, reset))
object Stepper {
   def kr(trig: AnyGE, reset: AnyGE = 0.0f, lo: AnyGE = 0.0f, hi: AnyGE = 7.0f, step: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[control](control, trig, reset, lo, hi, step, resetVal)
   def ar(trig: AnyGE, reset: AnyGE = 0.0f, lo: AnyGE = 0.0f, hi: AnyGE = 7.0f, step: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[audio](audio, trig, reset, lo, hi, step, resetVal)
}
case class Stepper[R <: Rate](rate: R, trig: AnyGE, reset: AnyGE, lo: AnyGE, hi: AnyGE, step: AnyGE, resetVal: AnyGE) extends SingleOutUGenSource[R, StepperUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _step: IIdxSeq[AnyUGenIn] = step.expand
      val _resetVal: IIdxSeq[AnyUGenIn] = resetVal.expand
      val _sz_trig = _trig.size
      val _sz_reset = _reset.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_step = _step.size
      val _sz_resetVal = _resetVal.size
      val _exp_ = maxInt(_sz_trig, _sz_reset, _sz_lo, _sz_hi, _sz_step, _sz_resetVal)
      IIdxSeq.tabulate(_exp_)(i => StepperUGen(rate, _trig(i.%(_sz_trig)), _reset(i.%(_sz_reset)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _step(i.%(_sz_step)), _resetVal(i.%(_sz_resetVal))))
   }
}
case class StepperUGen[R <: Rate](rate: R, trig: AnyUGenIn, reset: AnyUGenIn, lo: AnyUGenIn, hi: AnyUGenIn, step: AnyUGenIn, resetVal: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, reset, lo, hi, step, resetVal))
object TDelay {
   def kr(trig: AnyGE, dur: AnyGE = 0.1f) = apply[control](control, trig, dur)
   def ar(trig: AnyGE, dur: AnyGE = 0.1f) = apply[audio](audio, trig, dur)
}
case class TDelay[R <: Rate](rate: R, trig: AnyGE, dur: AnyGE) extends SingleOutUGenSource[R, TDelayUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _exp_ = maxInt(_sz_trig, _sz_dur)
      IIdxSeq.tabulate(_exp_)(i => TDelayUGen(rate, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur))))
   }
}
case class TDelayUGen[R <: Rate](rate: R, trig: AnyUGenIn, dur: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, dur))
object ZeroCrossing {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
case class ZeroCrossing[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, ZeroCrossingUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => ZeroCrossingUGen(rate, _in(i)))
   }
}
case class ZeroCrossingUGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object Timer {
   def kr(trig: AnyGE) = apply[control](control, trig)
   def ar(trig: AnyGE) = apply[audio](audio, trig)
}
case class Timer[R <: Rate](rate: R, trig: AnyGE) extends SingleOutUGenSource[R, TimerUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      IIdxSeq.tabulate(_trig.size)(i => TimerUGen(rate, _trig(i)))
   }
}
case class TimerUGen[R <: Rate](rate: R, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig))
object Sweep {
   def kr(trig: AnyGE, speed: AnyGE) = apply[control](control, trig, speed)
   def ar(trig: AnyGE, speed: AnyGE) = apply[audio](audio, trig, speed)
}
case class Sweep[R <: Rate](rate: R, trig: AnyGE, speed: AnyGE) extends SingleOutUGenSource[R, SweepUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _speed: IIdxSeq[AnyUGenIn] = speed.expand
      val _sz_trig = _trig.size
      val _sz_speed = _speed.size
      val _exp_ = maxInt(_sz_trig, _sz_speed)
      IIdxSeq.tabulate(_exp_)(i => SweepUGen(rate, _trig(i.%(_sz_trig)), _speed(i.%(_sz_speed))))
   }
}
case class SweepUGen[R <: Rate](rate: R, trig: AnyUGenIn, speed: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, speed))
object Phasor {
   def kr(trig: AnyGE, speed: AnyGE = 1.0f, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[control](control, trig, speed, lo, hi, resetVal)
   def ar(trig: AnyGE, speed: AnyGE = 1.0f, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[audio](audio, trig, speed, lo, hi, resetVal)
}
case class Phasor[R <: Rate](rate: R, trig: AnyGE, speed: AnyGE, lo: AnyGE, hi: AnyGE, resetVal: AnyGE) extends SingleOutUGenSource[R, PhasorUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _speed: IIdxSeq[AnyUGenIn] = speed.expand
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _resetVal: IIdxSeq[AnyUGenIn] = resetVal.expand
      val _sz_trig = _trig.size
      val _sz_speed = _speed.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_resetVal = _resetVal.size
      val _exp_ = maxInt(_sz_trig, _sz_speed, _sz_lo, _sz_hi, _sz_resetVal)
      IIdxSeq.tabulate(_exp_)(i => PhasorUGen(rate, _trig(i.%(_sz_trig)), _speed(i.%(_sz_speed)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _resetVal(i.%(_sz_resetVal))))
   }
}
case class PhasorUGen[R <: Rate](rate: R, trig: AnyUGenIn, speed: AnyUGenIn, lo: AnyUGenIn, hi: AnyUGenIn, resetVal: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, speed, lo, hi, resetVal))
object Peak {
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
case class Peak[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R, PeakUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_in = _in.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_in, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => PeakUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
   }
}
case class PeakUGen[R <: Rate](rate: R, in: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, trig))
object RunningMin {
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
case class RunningMin[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R, RunningMinUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_in = _in.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_in, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => RunningMinUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
   }
}
case class RunningMinUGen[R <: Rate](rate: R, in: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, trig))
object RunningMax {
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
case class RunningMax[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R, RunningMaxUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_in = _in.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_in, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => RunningMaxUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
   }
}
case class RunningMaxUGen[R <: Rate](rate: R, in: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, trig))
object PeakFollower {
   def kr(in: AnyGE, decay: AnyGE = 0.999f) = apply[control](control, in, decay)
   def ar(in: AnyGE, decay: AnyGE = 0.999f) = apply[audio](audio, in, decay)
}
case class PeakFollower[R <: Rate](rate: R, in: AnyGE, decay: AnyGE) extends SingleOutUGenSource[R, PeakFollowerUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _decay: IIdxSeq[AnyUGenIn] = decay.expand
      val _sz_in = _in.size
      val _sz_decay = _decay.size
      val _exp_ = maxInt(_sz_in, _sz_decay)
      IIdxSeq.tabulate(_exp_)(i => PeakFollowerUGen(rate, _in(i.%(_sz_in)), _decay(i.%(_sz_decay))))
   }
}
case class PeakFollowerUGen[R <: Rate](rate: R, in: AnyUGenIn, decay: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, decay))
object MostChange {
   def kr(a: AnyGE, b: AnyGE) = apply[control](control, a, b)
   def ar(a: AnyGE, b: AnyGE) = apply[audio](audio, a, b)
}
case class MostChange[R <: Rate](rate: R, a: AnyGE, b: AnyGE) extends SingleOutUGenSource[R, MostChangeUGen[R]] {
   protected def expandUGens = {
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _exp_ = maxInt(_sz_a, _sz_b)
      IIdxSeq.tabulate(_exp_)(i => MostChangeUGen(rate, _a(i.%(_sz_a)), _b(i.%(_sz_b))))
   }
}
case class MostChangeUGen[R <: Rate](rate: R, a: AnyUGenIn, b: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(a, b))
object LeastChange {
   def kr(a: AnyGE, b: AnyGE) = apply[control](control, a, b)
   def ar(a: AnyGE, b: AnyGE) = apply[audio](audio, a, b)
}
case class LeastChange[R <: Rate](rate: R, a: AnyGE, b: AnyGE) extends SingleOutUGenSource[R, LeastChangeUGen[R]] {
   protected def expandUGens = {
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _exp_ = maxInt(_sz_a, _sz_b)
      IIdxSeq.tabulate(_exp_)(i => LeastChangeUGen(rate, _a(i.%(_sz_a)), _b(i.%(_sz_b))))
   }
}
case class LeastChangeUGen[R <: Rate](rate: R, a: AnyUGenIn, b: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(a, b))
object LastValue {
   def kr(in: AnyGE, thresh: AnyGE = 0.01f) = apply[control](control, in, thresh)
   def ar(in: AnyGE, thresh: AnyGE = 0.01f) = apply[audio](audio, in, thresh)
}
case class LastValue[R <: Rate](rate: R, in: AnyGE, thresh: AnyGE) extends SingleOutUGenSource[R, LastValueUGen[R]] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _sz_in = _in.size
      val _sz_thresh = _thresh.size
      val _exp_ = maxInt(_sz_in, _sz_thresh)
      IIdxSeq.tabulate(_exp_)(i => LastValueUGen(rate, _in(i.%(_sz_in)), _thresh(i.%(_sz_thresh))))
   }
}
case class LastValueUGen[R <: Rate](rate: R, in: AnyUGenIn, thresh: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, thresh))
object Done {
   def kr(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) = apply(src)
}
case class Done(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) extends SingleOutUGenSource[control, DoneUGen] with HasSideEffect with ControlRated {
   protected def expandUGens = {
      val _src: IIdxSeq[AnyUGenIn with HasDoneFlag] = src.expand
      IIdxSeq.tabulate(_src.size)(i => DoneUGen(_src(i)))
   }
}
case class DoneUGen(src: AnyUGenIn with HasDoneFlag) extends SingleOutUGen[control](IIdxSeq(src)) with HasSideEffect with ControlRated
object Pause {
   def kr(gate: AnyGE, node: AnyGE) = apply(gate, node)
}
case class Pause(gate: AnyGE, node: AnyGE) extends SingleOutUGenSource[control, PauseUGen] with HasSideEffect with ControlRated {
   protected def expandUGens = {
      val _gate: IIdxSeq[AnyUGenIn] = gate.expand
      val _node: IIdxSeq[AnyUGenIn] = node.expand
      val _sz_gate = _gate.size
      val _sz_node = _node.size
      val _exp_ = maxInt(_sz_gate, _sz_node)
      IIdxSeq.tabulate(_exp_)(i => PauseUGen(_gate(i.%(_sz_gate)), _node(i.%(_sz_node))))
   }
}
case class PauseUGen(gate: AnyUGenIn, node: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(gate, node)) with HasSideEffect with ControlRated
object FreeSelf {
   def kr(trig: AnyGE) = apply(trig)
}
case class FreeSelf(trig: AnyGE) extends SingleOutUGenSource[control, FreeSelfUGen] with HasSideEffect with ControlRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      IIdxSeq.tabulate(_trig.size)(i => FreeSelfUGen(_trig(i)))
   }
}
case class FreeSelfUGen(trig: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(trig)) with HasSideEffect with ControlRated
object PauseSelf {
   def kr(trig: AnyGE) = apply(trig)
}
case class PauseSelf(trig: AnyGE) extends SingleOutUGenSource[control, PauseSelfUGen] with HasSideEffect with ControlRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      IIdxSeq.tabulate(_trig.size)(i => PauseSelfUGen(_trig(i)))
   }
}
case class PauseSelfUGen(trig: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(trig)) with HasSideEffect with ControlRated
object Free {
   def kr(trig: AnyGE, node: AnyGE) = apply(trig, node)
}
case class Free(trig: AnyGE, node: AnyGE) extends SingleOutUGenSource[control, FreeUGen] with HasSideEffect with ControlRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _node: IIdxSeq[AnyUGenIn] = node.expand
      val _sz_trig = _trig.size
      val _sz_node = _node.size
      val _exp_ = maxInt(_sz_trig, _sz_node)
      IIdxSeq.tabulate(_exp_)(i => FreeUGen(_trig(i.%(_sz_trig)), _node(i.%(_sz_node))))
   }
}
case class FreeUGen(trig: AnyUGenIn, node: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(trig, node)) with HasSideEffect with ControlRated
object FreeSelfWhenDone {
   def kr(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) = apply(src)
}
case class FreeSelfWhenDone(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) extends SingleOutUGenSource[control, FreeSelfWhenDoneUGen] with HasSideEffect with ControlRated {
   protected def expandUGens = {
      val _src: IIdxSeq[AnyUGenIn with HasDoneFlag] = src.expand
      IIdxSeq.tabulate(_src.size)(i => FreeSelfWhenDoneUGen(_src(i)))
   }
}
case class FreeSelfWhenDoneUGen(src: AnyUGenIn with HasDoneFlag) extends SingleOutUGen[control](IIdxSeq(src)) with HasSideEffect with ControlRated
object PauseSelfWhenDone {
   def kr(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) = apply(src)
}
case class PauseSelfWhenDone(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) extends SingleOutUGenSource[control, PauseSelfWhenDoneUGen] with HasSideEffect with ControlRated {
   protected def expandUGens = {
      val _src: IIdxSeq[AnyUGenIn with HasDoneFlag] = src.expand
      IIdxSeq.tabulate(_src.size)(i => PauseSelfWhenDoneUGen(_src(i)))
   }
}
case class PauseSelfWhenDoneUGen(src: AnyUGenIn with HasDoneFlag) extends SingleOutUGen[control](IIdxSeq(src)) with HasSideEffect with ControlRated