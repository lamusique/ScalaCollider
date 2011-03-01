/*
* TriggerUGens.scala
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
///**
// * A UGen which outputs a value of 1 for a given duration when triggered.
// *
// * When a trigger occurs at the input, a value of 1 is output for the specified duration,
// * otherwise zero is output. When a new trigger occurs while this ugens outputs 1, the
// * otherwise zero is output. When a new trigger occurs while this ugens outputs 1, the
// * hold-time is reset to the duration.
// *
// * @see [[de.sciss.synth.ugen.Trig]]
// */
//object Trig1 {
//
///**
// * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
// *                        from non-positive to positive.
// * @param dur             the duration for which the ugens holds the value of 1 when triggered
// */
//def ar(in: AnyGE, dur: AnyGE = 0.1f) = apply(audio, in, dur)
///**
// * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
// *                        from non-positive to positive.
// * @param dur             the duration for which the ugens holds the value of 1 when triggered
// */
//def kr(in: AnyGE, dur: AnyGE = 0.1f) = apply(control, in, dur)
//}
///**
// * A UGen which outputs a value of 1 for a given duration when triggered.
// *
// * When a trigger occurs at the input, a value of 1 is output for the specified duration,
// * otherwise zero is output. When a new trigger occurs while this ugens outputs 1, the
// * hold-time is reset to the duration.
// *
// * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
// *                        from non-positive to positive.
// * @param dur             the duration for which the ugens holds the value of 1 when triggered
// *
// * @see [[de.sciss.synth.ugen.Trig]]
// */
//case class Trig1(rate: Rate, in: AnyGE, dur: AnyGE) extends SingleOutUGenSource[Trig1UGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _sz_in = _in.size
//      val _sz_dur = _dur.size
//      val _exp_ = maxInt(_sz_in, _sz_dur)
//      IIdxSeq.tabulate(_exp_)(i => Trig1UGen(rate, _in(i.%(_sz_in)), _dur(i.%(_sz_dur))))
//   }
//}
//case class Trig1UGen(rate: Rate, in: UGenIn, dur: UGenIn) extends SingleOutUGen(IIdxSeq(in, dur))
//object Trig {
//
///**
// * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
// *                        from non-positive to positive.
// * @param dur             the duration for which the ugens holds the value of the input signal when triggered
// */
//def ar(in: AnyGE, dur: AnyGE = 0.1f) = apply(audio, in, dur)
///**
// * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
// *                        from non-positive to positive.
// * @param dur             the duration for which the ugens holds the value of the input signal when triggered
// */
//def kr(in: AnyGE, dur: AnyGE = 0.1f) = apply(control, in, dur)
//}
///**
// * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
// *                        from non-positive to positive.
// * @param dur             the duration for which the ugens holds the value of the input signal when triggered
// */
//case class Trig(rate: Rate, in: AnyGE, dur: AnyGE) extends SingleOutUGenSource[TrigUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _sz_in = _in.size
//      val _sz_dur = _dur.size
//      val _exp_ = maxInt(_sz_in, _sz_dur)
//      IIdxSeq.tabulate(_exp_)(i => TrigUGen(rate, _in(i.%(_sz_in)), _dur(i.%(_sz_dur))))
//   }
//}
//case class TrigUGen(rate: Rate, in: UGenIn, dur: UGenIn) extends SingleOutUGen(IIdxSeq(in, dur))
///**
// * A UGen that sends a value from the server to all notified clients upon receiving triggers.
// * The message sent is `OSCMessage( "/tr", <(Int) nodeID>, <(Int) trigID>, <(Float) value> )`.
// *
// * For sending an array of values, or using an arbitrary reply command, see `SendReply`.
// *
// * '''Warning''': We have changed the argument order. While in sclang, `id` precedes `value`,
// * we are using them in reverse order here!
// *
// * @see [[de.sciss.synth.ugen.SendReply]]
// */
//object SendTrig {
//
///**
// * @param trig            the trigger signal causing the value to be read and sent. A trigger occurs
// *                        when passing from non-positive to positive.
// * @param value           a changing signal or constant that will be polled at the time of trigger,
// *                        and its value passed with the trigger message
// * @param id              an arbitrary integer that will be sent along with the `"/tr"` message.
// *                        This is useful to distinguish between several SendTrig instances per SynthDef.
// */
//def ar(trig: AnyGE, value: AnyGE = 0.0f, id: AnyGE = 0.0f) = apply(audio, trig, value, id)
///**
// * @param trig            the trigger signal causing the value to be read and sent. A trigger occurs
// *                        when passing from non-positive to positive.
// * @param value           a changing signal or constant that will be polled at the time of trigger,
// *                        and its value passed with the trigger message
// * @param id              an arbitrary integer that will be sent along with the `"/tr"` message.
// *                        This is useful to distinguish between several SendTrig instances per SynthDef.
// */
//def kr(trig: AnyGE, value: AnyGE = 0.0f, id: AnyGE = 0.0f) = apply(control, trig, value, id)
//}
///**
// * A UGen that sends a value from the server to all notified clients upon receiving triggers.
// * The message sent is `OSCMessage( "/tr", <(Int) nodeID>, <(Int) trigID>, <(Float) value> )`.
// *
// * For sending an array of values, or using an arbitrary reply command, see `SendReply`.
// *
// * '''Warning''': We have changed the argument order. While in sclang, `id` precedes `value`,
// * we are using them in reverse order here!
// *
// * @param trig            the trigger signal causing the value to be read and sent. A trigger occurs
// *                        when passing from non-positive to positive.
// * @param value           a changing signal or constant that will be polled at the time of trigger,
// *                        and its value passed with the trigger message
// * @param id              an arbitrary integer that will be sent along with the `"/tr"` message.
// *                        This is useful to distinguish between several SendTrig instances per SynthDef.
// *
// * @see [[de.sciss.synth.ugen.SendReply]]
// */
//case class SendTrig(rate: Rate, trig: AnyGE, value: AnyGE, id: AnyGE) extends SingleOutUGenSource[SendTrigUGen] with HasSideEffect {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _value: IIdxSeq[UGenIn] = value.expand
//      val _id: IIdxSeq[UGenIn] = id.expand
//      val _sz_trig = _trig.size
//      val _sz_value = _value.size
//      val _sz_id = _id.size
//      val _exp_ = maxInt(_sz_trig, _sz_value, _sz_id)
//      IIdxSeq.tabulate(_exp_)(i => SendTrigUGen(rate, _trig(i.%(_sz_trig)), _value(i.%(_sz_value)), _id(i.%(_sz_id))))
//   }
//}
//case class SendTrigUGen(rate: Rate, trig: UGenIn, value: UGenIn, id: UGenIn) extends SingleOutUGen(IIdxSeq(trig, value, id)) with HasSideEffect
///**
// * A UGen that toggles like a flip-flop between zero and one upon receiving a trigger.
// * The flip-flop is initially outputing zero, so changes to one when the first trigger
// * arrives.
// */
//object ToggleFF {
//
///**
// * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
// *                        changes from non-positive to positive.
// */
//def kr(trig: AnyGE) = apply(control, trig)
///**
// * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
// *                        changes from non-positive to positive.
// */
//def ar(trig: AnyGE) = apply(audio, trig)
//}
///**
// * A UGen that toggles like a flip-flop between zero and one upon receiving a trigger.
// * The flip-flop is initially outputing zero, so changes to one when the first trigger
// * arrives.
// *
// * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
// *                        changes from non-positive to positive.
// */
//case class ToggleFF(rate: Rate, trig: AnyGE) extends SingleOutUGenSource[ToggleFFUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      IIdxSeq.tabulate(_trig.size)(i => ToggleFFUGen(rate, _trig(i)))
//   }
//}
//case class ToggleFFUGen(rate: Rate, trig: UGenIn) extends SingleOutUGen(IIdxSeq(trig))
//object SetResetFF {
//   def kr(trig: AnyGE, reset: AnyGE) = apply(control, trig, reset)
//   def ar(trig: AnyGE, reset: AnyGE) = apply(audio, trig, reset)
//}
//case class SetResetFF(rate: Rate, trig: AnyGE, reset: AnyGE) extends SingleOutUGenSource[SetResetFFUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _reset: IIdxSeq[UGenIn] = reset.expand
//      val _sz_trig = _trig.size
//      val _sz_reset = _reset.size
//      val _exp_ = maxInt(_sz_trig, _sz_reset)
//      IIdxSeq.tabulate(_exp_)(i => SetResetFFUGen(rate, _trig(i.%(_sz_trig)), _reset(i.%(_sz_reset))))
//   }
//}
//case class SetResetFFUGen(rate: Rate, trig: UGenIn, reset: UGenIn) extends SingleOutUGen(IIdxSeq(trig, reset))
///**
// * A sample-and-hold UGen. When triggered, a new value is taken from the input and
// * hold until the next trigger occurs.
// *
// * @see [[de.sciss.synth.ugen.Gate]]
// * @see [[de.sciss.synth.ugen.Demand]]
// */
//object Latch {
//
///**
// * @param in              the input signal
// * @param trig            the trigger. The can be any signal. A trigger happens when the signal changes from
// *                        non-positive to positive.
// */
//def kr(in: AnyGE, trig: AnyGE) = apply(control, in, trig)
///**
// * @param in              the input signal
// * @param trig            the trigger. The can be any signal. A trigger happens when the signal changes from
// *                        non-positive to positive.
// */
//def ar(in: AnyGE, trig: AnyGE) = apply(audio, in, trig)
//}
///**
// * A sample-and-hold UGen. When triggered, a new value is taken from the input and
// * hold until the next trigger occurs.
// *
// * @param in              the input signal
// * @param trig            the trigger. The can be any signal. A trigger happens when the signal changes from
// *                        non-positive to positive.
// *
// * @see [[de.sciss.synth.ugen.Gate]]
// * @see [[de.sciss.synth.ugen.Demand]]
// */
//case class Latch(rate: Rate, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[LatchUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _sz_in = _in.size
//      val _sz_trig = _trig.size
//      val _exp_ = maxInt(_sz_in, _sz_trig)
//      IIdxSeq.tabulate(_exp_)(i => LatchUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
//   }
//}
//case class LatchUGen(rate: Rate, in: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(in, trig))
///**
// * A gate or hold UGen.
// * It allows the input signal value to pass when the `gate` argument is positive,
// * otherwise it holds last value.
// *
// * @see [[de.sciss.synth.ugen.Latch]]
// */
//object Gate {
//
///**
// * @param in              the input signal to gate
// * @param gate            the signal specifying whether to pass the input signal (when greater than zero) or
// *                        whether to close the gate and hold the last value (when less than or equal to zero)
// */
//def kr(in: AnyGE, gate: AnyGE) = apply(control, in, gate)
///**
// * @param in              the input signal to gate
// * @param gate            the signal specifying whether to pass the input signal (when greater than zero) or
// *                        whether to close the gate and hold the last value (when less than or equal to zero)
// */
//def ar(in: AnyGE, gate: AnyGE) = apply(audio, in, gate)
//}
///**
// * A gate or hold UGen.
// * It allows the input signal value to pass when the `gate` argument is positive,
// * otherwise it holds last value.
// *
// * @param in              the input signal to gate
// * @param gate            the signal specifying whether to pass the input signal (when greater than zero) or
// *                        whether to close the gate and hold the last value (when less than or equal to zero)
// *
// * @see [[de.sciss.synth.ugen.Latch]]
// */
//case class Gate(rate: Rate, in: AnyGE, gate: AnyGE) extends SingleOutUGenSource[GateUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _gate: IIdxSeq[UGenIn] = gate.expand
//      val _sz_in = _in.size
//      val _sz_gate = _gate.size
//      val _exp_ = maxInt(_sz_in, _sz_gate)
//      IIdxSeq.tabulate(_exp_)(i => GateUGen(rate, _in(i.%(_sz_in)), _gate(i.%(_sz_gate))))
//   }
//}
//case class GateUGen(rate: Rate, in: UGenIn, gate: UGenIn) extends SingleOutUGen(IIdxSeq(in, gate))
///**
// * A Schmidt trigger UGen. Initially it outputs zero. When the input signal rises above `hi`,
// * its output switches to 1.0, which is hold until the signal falls below `lo`, switching the
// * output again to 0.0. The produces a kind of hysteresis behavior, preventing heavy
// * oscillations in a noisy system which might occur with a single-threshold trigger.
// */
//object Schmidt {
//
///**
// * @param lo              The low threshold.
// * @param hi              The high threshold.
// */
//def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(control, in, lo, hi)
///**
// * @param lo              The low threshold.
// * @param hi              The high threshold.
// */
//def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(audio, in, lo, hi)
//}
///**
// * A Schmidt trigger UGen. Initially it outputs zero. When the input signal rises above `hi`,
// * its output switches to 1.0, which is hold until the signal falls below `lo`, switching the
// * output again to 0.0. The produces a kind of hysteresis behavior, preventing heavy
// * oscillations in a noisy system which might occur with a single-threshold trigger.
// *
// * @param lo              The low threshold.
// * @param hi              The high threshold.
// */
//case class Schmidt(rate: Rate, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[SchmidtUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _sz_in = _in.size
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
//      IIdxSeq.tabulate(_exp_)(i => SchmidtUGen(rate, _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
//   }
//}
//case class SchmidtUGen(rate: Rate, in: UGenIn, lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(in, lo, hi))
//object PulseDivider {
//   def kr(trig: AnyGE, div: AnyGE = 2.0f, start: AnyGE = 0.0f) = apply(control, trig, div, start)
//   def ar(trig: AnyGE, div: AnyGE = 2.0f, start: AnyGE = 0.0f) = apply(audio, trig, div, start)
//}
//case class PulseDivider(rate: Rate, trig: AnyGE, div: AnyGE, start: AnyGE) extends SingleOutUGenSource[PulseDividerUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _div: IIdxSeq[UGenIn] = div.expand
//      val _start: IIdxSeq[UGenIn] = start.expand
//      val _sz_trig = _trig.size
//      val _sz_div = _div.size
//      val _sz_start = _start.size
//      val _exp_ = maxInt(_sz_trig, _sz_div, _sz_start)
//      IIdxSeq.tabulate(_exp_)(i => PulseDividerUGen(rate, _trig(i.%(_sz_trig)), _div(i.%(_sz_div)), _start(i.%(_sz_start))))
//   }
//}
//case class PulseDividerUGen(rate: Rate, trig: UGenIn, div: UGenIn, start: UGenIn) extends SingleOutUGen(IIdxSeq(trig, div, start))
//object PulseCount {
//   def kr(trig: AnyGE, reset: AnyGE = 0.0f) = apply(control, trig, reset)
//   def ar(trig: AnyGE, reset: AnyGE = 0.0f) = apply(audio, trig, reset)
//}
//case class PulseCount(rate: Rate, trig: AnyGE, reset: AnyGE) extends SingleOutUGenSource[PulseCountUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _reset: IIdxSeq[UGenIn] = reset.expand
//      val _sz_trig = _trig.size
//      val _sz_reset = _reset.size
//      val _exp_ = maxInt(_sz_trig, _sz_reset)
//      IIdxSeq.tabulate(_exp_)(i => PulseCountUGen(rate, _trig(i.%(_sz_trig)), _reset(i.%(_sz_reset))))
//   }
//}
//case class PulseCountUGen(rate: Rate, trig: UGenIn, reset: UGenIn) extends SingleOutUGen(IIdxSeq(trig, reset))
//object Stepper {
//   def kr(trig: AnyGE, reset: AnyGE = 0.0f, lo: AnyGE = 0.0f, hi: AnyGE = 7.0f, step: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply(control, trig, reset, lo, hi, step, resetVal)
//   def ar(trig: AnyGE, reset: AnyGE = 0.0f, lo: AnyGE = 0.0f, hi: AnyGE = 7.0f, step: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply(audio, trig, reset, lo, hi, step, resetVal)
//}
//case class Stepper(rate: Rate, trig: AnyGE, reset: AnyGE, lo: AnyGE, hi: AnyGE, step: AnyGE, resetVal: AnyGE) extends SingleOutUGenSource[StepperUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _reset: IIdxSeq[UGenIn] = reset.expand
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _step: IIdxSeq[UGenIn] = step.expand
//      val _resetVal: IIdxSeq[UGenIn] = resetVal.expand
//      val _sz_trig = _trig.size
//      val _sz_reset = _reset.size
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_step = _step.size
//      val _sz_resetVal = _resetVal.size
//      val _exp_ = maxInt(_sz_trig, _sz_reset, _sz_lo, _sz_hi, _sz_step, _sz_resetVal)
//      IIdxSeq.tabulate(_exp_)(i => StepperUGen(rate, _trig(i.%(_sz_trig)), _reset(i.%(_sz_reset)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _step(i.%(_sz_step)), _resetVal(i.%(_sz_resetVal))))
//   }
//}
//case class StepperUGen(rate: Rate, trig: UGenIn, reset: UGenIn, lo: UGenIn, hi: UGenIn, step: UGenIn, resetVal: UGenIn) extends SingleOutUGen(IIdxSeq(trig, reset, lo, hi, step, resetVal))
///**
// * A delay UGen for trigger signals. Other than a normal buffer delay,
// * any new trigger arriving in the time between the previous trigger
// * and the passing of the delay time is ignored.
// */
//object TDelay {
//
///**
// * @param trig            The input trigger. A trigger is recognized when the signal passes from
// *                        non-positive to positive. Note that, no matter what the amplitude of
// *                        the input trigger is, the UGen will output a delayed trigger of
// *                        amplitude 1.0.
// * @param dur             The delay time in seconds.
// */
//def kr(trig: AnyGE, dur: AnyGE = 0.1f) = apply(control, trig, dur)
///**
// * @param trig            The input trigger. A trigger is recognized when the signal passes from
// *                        non-positive to positive. Note that, no matter what the amplitude of
// *                        the input trigger is, the UGen will output a delayed trigger of
// *                        amplitude 1.0.
// * @param dur             The delay time in seconds.
// */
//def ar(trig: AnyGE, dur: AnyGE = 0.1f) = apply(audio, trig, dur)
//}
///**
// * A delay UGen for trigger signals. Other than a normal buffer delay,
// * any new trigger arriving in the time between the previous trigger
// * and the passing of the delay time is ignored.
// *
// * @param trig            The input trigger. A trigger is recognized when the signal passes from
// *                        non-positive to positive. Note that, no matter what the amplitude of
// *                        the input trigger is, the UGen will output a delayed trigger of
// *                        amplitude 1.0.
// * @param dur             The delay time in seconds.
// */
//case class TDelay(rate: Rate, trig: AnyGE, dur: AnyGE) extends SingleOutUGenSource[TDelayUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _sz_trig = _trig.size
//      val _sz_dur = _dur.size
//      val _exp_ = maxInt(_sz_trig, _sz_dur)
//      IIdxSeq.tabulate(_exp_)(i => TDelayUGen(rate, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur))))
//   }
//}
//case class TDelayUGen(rate: Rate, trig: UGenIn, dur: UGenIn) extends SingleOutUGen(IIdxSeq(trig, dur))
//object ZeroCrossing {
//   def kr(in: AnyGE) = apply(control, in)
//   def ar(in: AnyGE) = apply(audio, in)
//}
//case class ZeroCrossing(rate: Rate, in: AnyGE) extends SingleOutUGenSource[ZeroCrossingUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      IIdxSeq.tabulate(_in.size)(i => ZeroCrossingUGen(rate, _in(i)))
//   }
//}
//case class ZeroCrossingUGen(rate: Rate, in: UGenIn) extends SingleOutUGen(IIdxSeq(in))
///**
// * A UGen that returns time since last triggered.
// * The time returned is in seconds and is measured from the last received trigger.
// * Note that currently it seems the initial memory is at -1 sample, so for
// * `Impulse.ar(1)` the result (at 44.1 kHz) is 2.26757e-05, followed strangely
// * by 1.00002, and then (as expected) 1.0.
// */
//object Timer {
//
///**
// * @param trig            the trigger to update the output signal.
// *                        A trigger occurs when trig signal crosses from non-positive to positive.
// */
//def kr(trig: AnyGE) = apply(control, trig)
///**
// * @param trig            the trigger to update the output signal.
// *                        A trigger occurs when trig signal crosses from non-positive to positive.
// */
//def ar(trig: AnyGE) = apply(audio, trig)
//}
///**
// * A UGen that returns time since last triggered.
// * The time returned is in seconds and is measured from the last received trigger.
// * Note that currently it seems the initial memory is at -1 sample, so for
// * `Impulse.ar(1)` the result (at 44.1 kHz) is 2.26757e-05, followed strangely
// * by 1.00002, and then (as expected) 1.0.
// *
// * @param trig            the trigger to update the output signal.
// *                        A trigger occurs when trig signal crosses from non-positive to positive.
// */
//case class Timer(rate: Rate, trig: AnyGE) extends SingleOutUGenSource[TimerUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      IIdxSeq.tabulate(_trig.size)(i => TimerUGen(rate, _trig(i)))
//   }
//}
//case class TimerUGen(rate: Rate, trig: UGenIn) extends SingleOutUGen(IIdxSeq(trig))
///**
// * A UGen which starts a linear raise from zero each time it is
// * triggered.
// *
// * @see [[de.sciss.synth.ugen.Ramp]]
// * @see [[de.sciss.synth.ugen.Phasor]]
// * @see [[de.sciss.synth.ugen.Line]]
// */
//object Sweep {
//
///**
// * @param trig            the trigger that restarts the ramp, when passing from
// *                        non-positive to positive
// * @param speed           the amount of increment of the output signal per second.
// *                        In SCLang this argument is named `rate`, while ScalaCollider uses
// *                        `speed` to avoid conflict with the UGen's calculation rate.
// */
//def kr(trig: AnyGE, speed: AnyGE) = apply(control, trig, speed)
///**
// * @param trig            the trigger that restarts the ramp, when passing from
// *                        non-positive to positive
// * @param speed           the amount of increment of the output signal per second.
// *                        In SCLang this argument is named `rate`, while ScalaCollider uses
// *                        `speed` to avoid conflict with the UGen's calculation rate.
// */
//def ar(trig: AnyGE, speed: AnyGE) = apply(audio, trig, speed)
//}
///**
// * A UGen which starts a linear raise from zero each time it is
// * triggered.
// *
// * @param trig            the trigger that restarts the ramp, when passing from
// *                        non-positive to positive
// * @param speed           the amount of increment of the output signal per second.
// *                        In SCLang this argument is named `rate`, while ScalaCollider uses
// *                        `speed` to avoid conflict with the UGen's calculation rate.
// *
// * @see [[de.sciss.synth.ugen.Ramp]]
// * @see [[de.sciss.synth.ugen.Phasor]]
// * @see [[de.sciss.synth.ugen.Line]]
// */
//case class Sweep(rate: Rate, trig: AnyGE, speed: AnyGE) extends SingleOutUGenSource[SweepUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _speed: IIdxSeq[UGenIn] = speed.expand
//      val _sz_trig = _trig.size
//      val _sz_speed = _speed.size
//      val _exp_ = maxInt(_sz_trig, _sz_speed)
//      IIdxSeq.tabulate(_exp_)(i => SweepUGen(rate, _trig(i.%(_sz_trig)), _speed(i.%(_sz_speed))))
//   }
//}
//case class SweepUGen(rate: Rate, trig: UGenIn, speed: UGenIn) extends SingleOutUGen(IIdxSeq(trig, speed))
//object Phasor {
//
///**
// * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
// *                        be ignored (you need to feed it zero first)
// */
//def kr(trig: AnyGE, speed: AnyGE = 1.0f, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply(control, trig, speed, lo, hi, resetVal)
///**
// * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
// *                        be ignored (you need to feed it zero first)
// */
//def ar(trig: AnyGE, speed: AnyGE = 1.0f, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply(audio, trig, speed, lo, hi, resetVal)
//}
///**
// * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
// *                        be ignored (you need to feed it zero first)
// */
//case class Phasor(rate: Rate, trig: AnyGE, speed: AnyGE, lo: AnyGE, hi: AnyGE, resetVal: AnyGE) extends SingleOutUGenSource[PhasorUGen] {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _speed: IIdxSeq[UGenIn] = speed.expand
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _resetVal: IIdxSeq[UGenIn] = resetVal.expand
//      val _sz_trig = _trig.size
//      val _sz_speed = _speed.size
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_resetVal = _resetVal.size
//      val _exp_ = maxInt(_sz_trig, _sz_speed, _sz_lo, _sz_hi, _sz_resetVal)
//      IIdxSeq.tabulate(_exp_)(i => PhasorUGen(rate, _trig(i.%(_sz_trig)), _speed(i.%(_sz_speed)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _resetVal(i.%(_sz_resetVal))))
//   }
//}
//case class PhasorUGen(rate: Rate, trig: UGenIn, speed: UGenIn, lo: UGenIn, hi: UGenIn, resetVal: UGenIn) extends SingleOutUGen(IIdxSeq(trig, speed, lo, hi, resetVal))
//object Peak {
//   def kr(in: AnyGE, trig: AnyGE) = apply(control, in, trig)
//   def ar(in: AnyGE, trig: AnyGE) = apply(audio, in, trig)
//}
//case class Peak(rate: Rate, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[PeakUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _sz_in = _in.size
//      val _sz_trig = _trig.size
//      val _exp_ = maxInt(_sz_in, _sz_trig)
//      IIdxSeq.tabulate(_exp_)(i => PeakUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
//   }
//}
//case class PeakUGen(rate: Rate, in: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(in, trig))
//object RunningMin {
//   def kr(in: AnyGE, trig: AnyGE) = apply(control, in, trig)
//   def ar(in: AnyGE, trig: AnyGE) = apply(audio, in, trig)
//}
//case class RunningMin(rate: Rate, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[RunningMinUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _sz_in = _in.size
//      val _sz_trig = _trig.size
//      val _exp_ = maxInt(_sz_in, _sz_trig)
//      IIdxSeq.tabulate(_exp_)(i => RunningMinUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
//   }
//}
//case class RunningMinUGen(rate: Rate, in: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(in, trig))
//object RunningMax {
//   def kr(in: AnyGE, trig: AnyGE) = apply(control, in, trig)
//   def ar(in: AnyGE, trig: AnyGE) = apply(audio, in, trig)
//}
//case class RunningMax(rate: Rate, in: AnyGE, trig: AnyGE) extends SingleOutUGenSource[RunningMaxUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _sz_in = _in.size
//      val _sz_trig = _trig.size
//      val _exp_ = maxInt(_sz_in, _sz_trig)
//      IIdxSeq.tabulate(_exp_)(i => RunningMaxUGen(rate, _in(i.%(_sz_in)), _trig(i.%(_sz_trig))))
//   }
//}
//case class RunningMaxUGen(rate: Rate, in: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(in, trig))
//object PeakFollower {
//   def kr(in: AnyGE, decay: AnyGE = 0.999f) = apply(control, in, decay)
//   def ar(in: AnyGE, decay: AnyGE = 0.999f) = apply(audio, in, decay)
//}
//case class PeakFollower(rate: Rate, in: AnyGE, decay: AnyGE) extends SingleOutUGenSource[PeakFollowerUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _decay: IIdxSeq[UGenIn] = decay.expand
//      val _sz_in = _in.size
//      val _sz_decay = _decay.size
//      val _exp_ = maxInt(_sz_in, _sz_decay)
//      IIdxSeq.tabulate(_exp_)(i => PeakFollowerUGen(rate, _in(i.%(_sz_in)), _decay(i.%(_sz_decay))))
//   }
//}
//case class PeakFollowerUGen(rate: Rate, in: UGenIn, decay: UGenIn) extends SingleOutUGen(IIdxSeq(in, decay))
//object MostChange {
//   def kr(a: AnyGE, b: AnyGE) = apply(control, a, b)
//   def ar(a: AnyGE, b: AnyGE) = apply(audio, a, b)
//}
//case class MostChange(rate: Rate, a: AnyGE, b: AnyGE) extends SingleOutUGenSource[MostChangeUGen] {
//   protected def expandUGens = {
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _exp_ = maxInt(_sz_a, _sz_b)
//      IIdxSeq.tabulate(_exp_)(i => MostChangeUGen(rate, _a(i.%(_sz_a)), _b(i.%(_sz_b))))
//   }
//}
//case class MostChangeUGen(rate: Rate, a: UGenIn, b: UGenIn) extends SingleOutUGen(IIdxSeq(a, b))
//object LeastChange {
//   def kr(a: AnyGE, b: AnyGE) = apply(control, a, b)
//   def ar(a: AnyGE, b: AnyGE) = apply(audio, a, b)
//}
//case class LeastChange(rate: Rate, a: AnyGE, b: AnyGE) extends SingleOutUGenSource[LeastChangeUGen] {
//   protected def expandUGens = {
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _exp_ = maxInt(_sz_a, _sz_b)
//      IIdxSeq.tabulate(_exp_)(i => LeastChangeUGen(rate, _a(i.%(_sz_a)), _b(i.%(_sz_b))))
//   }
//}
//case class LeastChangeUGen(rate: Rate, a: UGenIn, b: UGenIn) extends SingleOutUGen(IIdxSeq(a, b))
//object LastValue {
//   def kr(in: AnyGE, thresh: AnyGE = 0.01f) = apply(control, in, thresh)
//   def ar(in: AnyGE, thresh: AnyGE = 0.01f) = apply(audio, in, thresh)
//}
//case class LastValue(rate: Rate, in: AnyGE, thresh: AnyGE) extends SingleOutUGenSource[LastValueUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _thresh: IIdxSeq[UGenIn] = thresh.expand
//      val _sz_in = _in.size
//      val _sz_thresh = _thresh.size
//      val _exp_ = maxInt(_sz_in, _sz_thresh)
//      IIdxSeq.tabulate(_exp_)(i => LastValueUGen(rate, _in(i.%(_sz_in)), _thresh(i.%(_sz_thresh))))
//   }
//}
//case class LastValueUGen(rate: Rate, in: UGenIn, thresh: UGenIn) extends SingleOutUGen(IIdxSeq(in, thresh))
/**
* A UGen which monitors another UGen to see when it is finished.
* Some UGens, such as `PlayBuf`, `RecordBuf`, `Line`, `XLine`, `EnvGen`, `Linen`, `BufRd`, `BufWr`, `DbufRd`,
* and the Buffer delay UGens set a 'done' flag when they are finished playing. This UGen echoes that flag
* as an explicit output signal when it is set to track a particular UGen. When the tracked UGen changes
* to done, the output signal changes from zero to one.
*
* @see [[de.sciss.synth.ugen.PlayBuf]]
* @see [[de.sciss.synth.ugen.Line]]
* @see [[de.sciss.synth.ugen.EnvGen]]
*/
object Done {

/**
* @param src             the UGen to track
*/
def kr(src: AnyGE with HasDoneFlag) = apply(src)
}
/**
* A UGen which monitors another UGen to see when it is finished.
* Some UGens, such as `PlayBuf`, `RecordBuf`, `Line`, `XLine`, `EnvGen`, `Linen`, `BufRd`, `BufWr`, `DbufRd`,
* and the Buffer delay UGens set a 'done' flag when they are finished playing. This UGen echoes that flag
* as an explicit output signal when it is set to track a particular UGen. When the tracked UGen changes
* to done, the output signal changes from zero to one.
*
* @param src             the UGen to track
*
* @see [[de.sciss.synth.ugen.PlayBuf]]
* @see [[de.sciss.synth.ugen.Line]]
* @see [[de.sciss.synth.ugen.EnvGen]]
*/
case class Done(src: AnyGE with HasDoneFlag) extends SingleOutUGenSource[control] with HasSideEffect {
   protected def expandUGens = {
      val _src: IIdxSeq[UGenIn] = src.expand
      _src.headOption match {
         case Some( _src_ch ) => IIdxSeq( new SingleOutUGen("Done", control, IIdxSeq( _src_ch )))
         case None => Console.err.println( "Warning: Done omitted due to zero-outputs input" ); IIdxSeq.empty
      }
//      IIdxSeq.tabulate(_src.size)(i => new SingleOutUGen("Done", control, IIdxSeq( _src(i))))
   }
}
//case class DoneUGen(src: UGenIn with HasDoneFlag) extends SingleOutUGen(IIdxSeq(src)) with HasSideEffect with ControlRated
///**
// * A UGen which pauses and resumes another node.
// * Note that the UGen initially assumes the node is running, that is,
// * if `gate` is initially 1, this will '''not''' resume a paused node.
// * Instead, the gate must go to zero and back to one to resume the node.
// * Additionally, this UGen will only cause action if the gate value
// * changes, that is, if the node is paused or resumed otherwise, this
// * UGen will not interfere with that action, unless the gate value is
// * adjusted.
// *
// * @see [[de.sciss.synth.ugen.Free]]
// * @see [[de.sciss.synth.ugen.PauseSelf]]
// */
//object Pause {
//
///**
// * @param gate            when 0, node is paused, when 1, node is resumed
// * @param node            the id of the node to be paused or resumed
// */
//def kr(gate: AnyGE, node: AnyGE) = apply(gate, node)
//}
///**
// * A UGen which pauses and resumes another node.
// * Note that the UGen initially assumes the node is running, that is,
// * if `gate` is initially 1, this will '''not''' resume a paused node.
// * Instead, the gate must go to zero and back to one to resume the node.
// * Additionally, this UGen will only cause action if the gate value
// * changes, that is, if the node is paused or resumed otherwise, this
// * UGen will not interfere with that action, unless the gate value is
// * adjusted.
// *
// * @param gate            when 0, node is paused, when 1, node is resumed
// * @param node            the id of the node to be paused or resumed
// *
// * @see [[de.sciss.synth.ugen.Free]]
// * @see [[de.sciss.synth.ugen.PauseSelf]]
// */
//case class Pause(gate: AnyGE, node: AnyGE) extends SingleOutUGenSource[PauseUGen] with HasSideEffect with ControlRated {
//   protected def expandUGens = {
//      val _gate: IIdxSeq[UGenIn] = gate.expand
//      val _node: IIdxSeq[UGenIn] = node.expand
//      val _sz_gate = _gate.size
//      val _sz_node = _node.size
//      val _exp_ = maxInt(_sz_gate, _sz_node)
//      IIdxSeq.tabulate(_exp_)(i => PauseUGen(_gate(i.%(_sz_gate)), _node(i.%(_sz_node))))
//   }
//}
//case class PauseUGen(gate: UGenIn, node: UGenIn) extends SingleOutUGen(IIdxSeq(gate, node)) with HasSideEffect with ControlRated
/**
* A UGen that, when triggered, frees enclosing synth.
* It frees the enclosing synth when the input signal crosses from non-positive to positive.
*
* This UGen outputs its input signal for convenience.
*
* @see [[de.sciss.synth.ugen.Free]]
* @see [[de.sciss.synth.ugen.PauseSelf]]
*/
object FreeSelf {

/**
* @param trig            the input signal which will trigger the action.
*/
def kr(trig: AnyGE) = apply(trig)
}
/**
* A UGen that, when triggered, frees enclosing synth.
* It frees the enclosing synth when the input signal crosses from non-positive to positive.
*
* This UGen outputs its input signal for convenience.
*
* @param trig            the input signal which will trigger the action.
*
* @see [[de.sciss.synth.ugen.Free]]
* @see [[de.sciss.synth.ugen.PauseSelf]]
*/
case class FreeSelf(trig: AnyGE) extends SingleOutUGenSource[control] with HasSideEffect {
   protected def expandUGens = {
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _exp_sz = _trig.size
//      if( _exp_sz == 0 ) Console.err.println( "Warning: FreeSelf omitted due to zero-outputs input" ); IIdxSeq.empty
      IIdxSeq.tabulate(_exp_sz)(i => new SingleOutUGen("FreeSelf", control, IIdxSeq(_trig(i))))
   }
}
//case class FreeSelfUGen(trig: UGenIn) extends SingleOutUGen(IIdxSeq(trig)) with HasSideEffect with ControlRated
///**
// * A UGen that, when triggered, pauses enclosing synth.
// * It pauses the enclosing synth when the input signal crosses from non-positive to positive.
// *
// * This UGen outputs its input signal for convenience.
// *
// * @see [[de.sciss.synth.ugen.Pause]]
// * @see [[de.sciss.synth.ugen.FreeSelf]]
// */
//object PauseSelf {
//
///**
// * @param trig            the input signal which will trigger the action.
// */
//def kr(trig: AnyGE) = apply(trig)
//}
///**
// * A UGen that, when triggered, pauses enclosing synth.
// * It pauses the enclosing synth when the input signal crosses from non-positive to positive.
// *
// * This UGen outputs its input signal for convenience.
// *
// * @param trig            the input signal which will trigger the action.
// *
// * @see [[de.sciss.synth.ugen.Pause]]
// * @see [[de.sciss.synth.ugen.FreeSelf]]
// */
//case class PauseSelf(trig: AnyGE) extends SingleOutUGenSource[PauseSelfUGen] with HasSideEffect with ControlRated {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      IIdxSeq.tabulate(_trig.size)(i => PauseSelfUGen(_trig(i)))
//   }
//}
//case class PauseSelfUGen(trig: UGenIn) extends SingleOutUGen(IIdxSeq(trig)) with HasSideEffect with ControlRated
///**
// * A UGen that, when triggered, frees a given node.
// *
// * This UGen outputs its trig input signal for convenience.
// *
// * @see [[de.sciss.synth.ugen.Pause]]
// * @see [[de.sciss.synth.ugen.FreeSelf]]
// */
//object Free {
//
///**
// * @param trig            the trigger to cause the action
// * @param node            the id of the target node to free upon receiving the trigger
// */
//def kr(trig: AnyGE, node: AnyGE) = apply(trig, node)
//}
///**
// * A UGen that, when triggered, frees a given node.
// *
// * This UGen outputs its trig input signal for convenience.
// *
// * @param trig            the trigger to cause the action
// * @param node            the id of the target node to free upon receiving the trigger
// *
// * @see [[de.sciss.synth.ugen.Pause]]
// * @see [[de.sciss.synth.ugen.FreeSelf]]
// */
//case class Free(trig: AnyGE, node: AnyGE) extends SingleOutUGenSource[FreeUGen] with HasSideEffect with ControlRated {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _node: IIdxSeq[UGenIn] = node.expand
//      val _sz_trig = _trig.size
//      val _sz_node = _node.size
//      val _exp_ = maxInt(_sz_trig, _sz_node)
//      IIdxSeq.tabulate(_exp_)(i => FreeUGen(_trig(i.%(_sz_trig)), _node(i.%(_sz_node))))
//   }
//}
//case class FreeUGen(trig: UGenIn, node: UGenIn) extends SingleOutUGen(IIdxSeq(trig, node)) with HasSideEffect with ControlRated
/**
* A UGen that, when its input UGen is finished, frees enclosing synth.
* This is essentially a shortcut for `FreeSelf.kr( Done.kr( src ))`, so instead
* of providing a trigger signal it reads directly the done flag of an
* appropriate ugen (such as `Line` or `PlayBuf`).
*
* This UGen outputs its input signal for convenience.
*
* @see [[de.sciss.synth.ugen.Free]]
* @see [[de.sciss.synth.ugen.FreeSelf]]
* @see [[de.sciss.synth.ugen.PauseSelfWhenDone]]
* @see [[de.sciss.synth.ugen.Done]]
*/
object FreeSelfWhenDone {

/**
* @param src             the input UGen which when finished will trigger the action.
*/
def kr(src: AnyGE with HasDoneFlag) = apply(src)
}
/**
* A UGen that, when its input UGen is finished, frees enclosing synth.
* This is essentially a shortcut for `FreeSelf.kr( Done.kr( src ))`, so instead
* of providing a trigger signal it reads directly the done flag of an
* appropriate ugen (such as `Line` or `PlayBuf`).
*
* This UGen outputs its input signal for convenience.
*
* @param src             the input UGen which when finished will trigger the action.
*
* @see [[de.sciss.synth.ugen.Free]]
* @see [[de.sciss.synth.ugen.FreeSelf]]
* @see [[de.sciss.synth.ugen.PauseSelfWhenDone]]
* @see [[de.sciss.synth.ugen.Done]]
*/
case class FreeSelfWhenDone(src: AnyGE with HasDoneFlag) extends SingleOutUGenSource[control] with HasSideEffect {
   protected def expandUGens = {
      val _src: IIdxSeq[UGenIn] = src.expand
      IIdxSeq.tabulate(_src.size)(i => new SingleOutUGen("FreeSelfWhenDone", control, IIdxSeq( _src(i))))
   }
}
//case class FreeSelfWhenDoneUGen(src: UGenProxy[ UGen with HasDoneFlag]) extends SingleOutUGen(IIdxSeq(src)) with HasSideEffect with ControlRated
///**
// * A UGen that, when its input UGen is finished, pauses enclosing synth.
// * This is essentially a shortcut for `PauseSelf.kr( Done.kr( src ))`, so instead
// * of providing a trigger signal it reads directly the done flag of an
// * appropriate ugen (such as `Line` or `PlayBuf`).
// *
// * This UGen outputs its input signal for convenience.
// *
// * @see [[de.sciss.synth.ugen.Pause]]
// * @see [[de.sciss.synth.ugen.PauseSelf]]
// * @see [[de.sciss.synth.ugen.FreeSelfWhenDone]]
// * @see [[de.sciss.synth.ugen.Done]]
// */
//object PauseSelfWhenDone {
//
///**
// * @param src             the input UGen which when finished will trigger the action.
// */
//def kr(src: GE[UGenIn with HasDoneFlag]) = apply(src)
//}
///**
// * A UGen that, when its input UGen is finished, pauses enclosing synth.
// * This is essentially a shortcut for `PauseSelf.kr( Done.kr( src ))`, so instead
// * of providing a trigger signal it reads directly the done flag of an
// * appropriate ugen (such as `Line` or `PlayBuf`).
// *
// * This UGen outputs its input signal for convenience.
// *
// * @param src             the input UGen which when finished will trigger the action.
// *
// * @see [[de.sciss.synth.ugen.Pause]]
// * @see [[de.sciss.synth.ugen.PauseSelf]]
// * @see [[de.sciss.synth.ugen.FreeSelfWhenDone]]
// * @see [[de.sciss.synth.ugen.Done]]
// */
//case class PauseSelfWhenDone(src: GE[UGenIn with HasDoneFlag]) extends SingleOutUGenSource[PauseSelfWhenDoneUGen] with HasSideEffect with ControlRated {
//   protected def expandUGens = {
//      val _src: IIdxSeq[UGenIn with HasDoneFlag] = src.expand
//      IIdxSeq.tabulate(_src.size)(i => PauseSelfWhenDoneUGen(_src(i)))
//   }
//}
//case class PauseSelfWhenDoneUGen(src: UGenIn with HasDoneFlag) extends SingleOutUGen(IIdxSeq(src)) with HasSideEffect with ControlRated