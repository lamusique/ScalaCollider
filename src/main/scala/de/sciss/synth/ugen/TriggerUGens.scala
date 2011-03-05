/*
 * TriggerUGens.scala
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
 * A UGen which outputs a value of 1 for a given duration when triggered.
 * 
 * When a trigger occurs at the input, a value of 1 is output for the specified duration,
 * otherwise zero is output. When a new trigger occurs while this ugens outputs 1, the
 * hold-time is reset to the duration.
 * 
 * @see [[de.sciss.synth.ugen.Trig]]
 */
object Trig1 {
   
   /**
    * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
    *                        from non-positive to positive.
    * @param dur             the duration for which the ugens holds the value of 1 when triggered
    */
   def ar(in: AnyGE, dur: AnyGE = 0.1f) = apply[audio](audio, in, dur)
   /**
    * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
    *                        from non-positive to positive.
    * @param dur             the duration for which the ugens holds the value of 1 when triggered
    */
   def kr(in: AnyGE, dur: AnyGE = 0.1f) = apply[control](control, in, dur)
}
/**
 * A UGen which outputs a value of 1 for a given duration when triggered.
 * 
 * When a trigger occurs at the input, a value of 1 is output for the specified duration,
 * otherwise zero is output. When a new trigger occurs while this ugens outputs 1, the
 * hold-time is reset to the duration.
 * 
 * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
 *                        from non-positive to positive.
 * @param dur             the duration for which the ugens holds the value of 1 when triggered
 * 
 * @see [[de.sciss.synth.ugen.Trig]]
 */
final case class Trig1[R <: Rate](rate: R, in: AnyGE, dur: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Trig1", rate, _args)
}
object Trig {
   
   /**
    * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
    *                        from non-positive to positive.
    * @param dur             the duration for which the ugens holds the value of the input signal when triggered
    */
   def ar(in: AnyGE, dur: AnyGE = 0.1f) = apply[audio](audio, in, dur)
   /**
    * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
    *                        from non-positive to positive.
    * @param dur             the duration for which the ugens holds the value of the input signal when triggered
    */
   def kr(in: AnyGE, dur: AnyGE = 0.1f) = apply[control](control, in, dur)
}
/**
 * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
 *                        from non-positive to positive.
 * @param dur             the duration for which the ugens holds the value of the input signal when triggered
 */
final case class Trig[R <: Rate](rate: R, in: AnyGE, dur: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Trig", rate, _args)
}
/**
 * A UGen that sends a value from the server to all notified clients upon receiving triggers.
 * The message sent is `OSCMessage( "/tr", <(Int) nodeID>, <(Int) trigID>, <(Float) value> )`.
 * 
 * For sending an array of values, or using an arbitrary reply command, see `SendReply`.
 * 
 * '''Warning''': The argument order is different from its sclang counterpart.
 * 
 * @see [[de.sciss.synth.ugen.SendReply]]
 */
object SendTrig {
   
   /**
    * @param trig            the trigger signal causing the value to be read and sent. A trigger occurs
    *                        when passing from non-positive to positive.
    * @param id              an arbitrary integer that will be sent along with the `"/tr"` message.
    *                        This is useful to distinguish between several SendTrig instances per SynthDef.
    * @param value           a changing signal or constant that will be polled at the time of trigger,
    *                        and its value passed with the trigger message
    */
   def ar(trig: GE[audio], id: AnyGE = 0.0f, value: AnyGE = 0.0f) = apply[audio](audio, trig, id, value)
   /**
    * @param trig            the trigger signal causing the value to be read and sent. A trigger occurs
    *                        when passing from non-positive to positive.
    * @param id              an arbitrary integer that will be sent along with the `"/tr"` message.
    *                        This is useful to distinguish between several SendTrig instances per SynthDef.
    * @param value           a changing signal or constant that will be polled at the time of trigger,
    *                        and its value passed with the trigger message
    */
   def kr(trig: GE[control], id: AnyGE = 0.0f, value: AnyGE = 0.0f) = apply[control](control, trig, id, value)
}
/**
 * A UGen that sends a value from the server to all notified clients upon receiving triggers.
 * The message sent is `OSCMessage( "/tr", <(Int) nodeID>, <(Int) trigID>, <(Float) value> )`.
 * 
 * For sending an array of values, or using an arbitrary reply command, see `SendReply`.
 * 
 * '''Warning''': The argument order is different from its sclang counterpart.
 * 
 * @param trig            the trigger signal causing the value to be read and sent. A trigger occurs
 *                        when passing from non-positive to positive.
 * @param id              an arbitrary integer that will be sent along with the `"/tr"` message.
 *                        This is useful to distinguish between several SendTrig instances per SynthDef.
 * @param value           a changing signal or constant that will be polled at the time of trigger,
 *                        and its value passed with the trigger message
 * 
 * @see [[de.sciss.synth.ugen.SendReply]]
 */
final case class SendTrig[R <: Rate](rate: R, trig: AnyGE, id: AnyGE, value: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, value.expand, id.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SendTrig", rate, _args)
}
/**
 * A UGen which sends an sequence of values from the server to all notified clients upon receiving triggers.
 * The message sent is `OSCMessage( <(String) msgName>, <(Int) nodeID>, <(Int) replyID>, <(Float) values>* )`.
 * 
 * For sending a single value, `SendTrig` provides an alternative.
 * 
 * '''Warning''': The argument order is different from its sclang counterpart.
 * 
 * @see [[de.sciss.synth.ugen.SendTrig]]
 */
object SendReply {
   
   /**
    * @param trig            a non-positive to positive transition triggers a message
    * @param values          a graph element comprising the signal channels to be polled
    * @param msgName         a string specifying the OSCMessage's name. by convention, this should
    *                        start with a forward slash and contain only 7-bit ascii characters.
    * @param id              an integer identifier which is contained in the reply message. While you can
    *                        distinguish different `SendReply` instances from the same Synth by choosing different
    *                        OSCMessage names, depending on the application you may use the same message name but
    *                        different ids (similar to `SendTrig`).
    */
   def kr(trig: GE[control], values: AnyGE, msgName: String = "/reply", id: AnyGE = 0.0f) = apply[control](control, trig, values, msgName, id)
   /**
    * @param trig            a non-positive to positive transition triggers a message
    * @param values          a graph element comprising the signal channels to be polled
    * @param msgName         a string specifying the OSCMessage's name. by convention, this should
    *                        start with a forward slash and contain only 7-bit ascii characters.
    * @param id              an integer identifier which is contained in the reply message. While you can
    *                        distinguish different `SendReply` instances from the same Synth by choosing different
    *                        OSCMessage names, depending on the application you may use the same message name but
    *                        different ids (similar to `SendTrig`).
    */
   def ar(trig: GE[audio], values: AnyGE, msgName: String = "/reply", id: AnyGE = 0.0f) = apply[audio](audio, trig, values, msgName, id)
}
/**
 * A UGen which sends an sequence of values from the server to all notified clients upon receiving triggers.
 * The message sent is `OSCMessage( <(String) msgName>, <(Int) nodeID>, <(Int) replyID>, <(Float) values>* )`.
 * 
 * For sending a single value, `SendTrig` provides an alternative.
 * 
 * '''Warning''': The argument order is different from its sclang counterpart.
 * 
 * @param trig            a non-positive to positive transition triggers a message
 * @param values          a graph element comprising the signal channels to be polled
 * @param msgName         a string specifying the OSCMessage's name. by convention, this should
 *                        start with a forward slash and contain only 7-bit ascii characters.
 * @param id              an integer identifier which is contained in the reply message. While you can
 *                        distinguish different `SendReply` instances from the same Synth by choosing different
 *                        OSCMessage names, depending on the application you may use the same message name but
 *                        different ids (similar to `SendTrig`).
 * 
 * @see [[de.sciss.synth.ugen.SendTrig]]
 */
final case class SendReply[R <: Rate](rate: R, trig: AnyGE, values: AnyGE, msgName: String, id: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, id.expand).++(stringArg(msgName).++(values.expand.outputs)))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SendReply", rate, _args)
}
/**
 * A UGen for printing the current output value of its input to the console.
 * 
 * @see [[de.sciss.synth.ugen.SendTrig]]
 */
object Poll {
   
   /**
    * @param trig            a non-positive to positive transition telling Poll to return a value
    * @param in              the signal you want to poll
    * @param label           a string or symbol to be printed with the polled value
    * @param trigID          if greater then 0, a `"/tr"` OSC message is sent back to the client
    *                        (similar to `SendTrig`)
    */
   def ar(trig: GE[audio], in: AnyGE, label: String = "poll", trigID: AnyGE = -1.0f) = apply[audio](audio, trig, in, label, trigID)
   /**
    * @param trig            a non-positive to positive transition telling Poll to return a value
    * @param in              the signal you want to poll
    * @param label           a string or symbol to be printed with the polled value
    * @param trigID          if greater then 0, a `"/tr"` OSC message is sent back to the client
    *                        (similar to `SendTrig`)
    */
   def kr(trig: GE[control], in: AnyGE, label: String = "poll", trigID: AnyGE = -1.0f) = apply[control](control, trig, in, label, trigID)
}
/**
 * A UGen for printing the current output value of its input to the console.
 * 
 * @param trig            a non-positive to positive transition telling Poll to return a value
 * @param in              the signal you want to poll
 * @param label           a string or symbol to be printed with the polled value
 * @param trigID          if greater then 0, a `"/tr"` OSC message is sent back to the client
 *                        (similar to `SendTrig`)
 * 
 * @see [[de.sciss.synth.ugen.SendTrig]]
 */
final case class Poll[R <: Rate](rate: R, trig: AnyGE, in: AnyGE, label: String, trigID: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, in.expand, trigID.expand).++(stringArg(label)))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Poll", rate, _args)
}
/**
 * A UGen that toggles like a flip-flop between zero and one upon receiving a trigger.
 * The flip-flop is initially outputing zero, so changes to one when the first trigger
 * arrives.
 */
object ToggleFF {
   
   /**
    * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
    *                        changes from non-positive to positive.
    */
   def kr(trig: AnyGE) = apply[control](control, trig)
   /**
    * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
    *                        changes from non-positive to positive.
    */
   def ar(trig: AnyGE) = apply[audio](audio, trig)
}
/**
 * A UGen that toggles like a flip-flop between zero and one upon receiving a trigger.
 * The flip-flop is initially outputing zero, so changes to one when the first trigger
 * arrives.
 * 
 * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
 *                        changes from non-positive to positive.
 */
final case class ToggleFF[R <: Rate](rate: R, trig: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("ToggleFF", rate, _args)
}
object SetResetFF {
   def kr(trig: AnyGE, reset: AnyGE) = apply[control](control, trig, reset)
   def ar(trig: AnyGE, reset: AnyGE) = apply[audio](audio, trig, reset)
}
final case class SetResetFF[R <: Rate](rate: R, trig: AnyGE, reset: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SetResetFF", rate, _args)
}
/**
 * A sample-and-hold UGen. When triggered, a new value is taken from the input and
 * hold until the next trigger occurs.
 * 
 * @see [[de.sciss.synth.ugen.Gate]]
 * @see [[de.sciss.synth.ugen.Demand]]
 */
object Latch {
   
   /**
    * @param in              the input signal
    * @param trig            the trigger. The can be any signal. A trigger happens when the signal changes from
    *                        non-positive to positive.
    */
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   /**
    * @param in              the input signal
    * @param trig            the trigger. The can be any signal. A trigger happens when the signal changes from
    *                        non-positive to positive.
    */
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
/**
 * A sample-and-hold UGen. When triggered, a new value is taken from the input and
 * hold until the next trigger occurs.
 * 
 * @param in              the input signal
 * @param trig            the trigger. The can be any signal. A trigger happens when the signal changes from
 *                        non-positive to positive.
 * 
 * @see [[de.sciss.synth.ugen.Gate]]
 * @see [[de.sciss.synth.ugen.Demand]]
 */
final case class Latch[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Latch", rate, _args)
}
/**
 * A gate or hold UGen.
 * It allows the input signal value to pass when the `gate` argument is positive,
 * otherwise it holds last value.
 * 
 * @see [[de.sciss.synth.ugen.Latch]]
 */
object Gate {
   
   /**
    * @param in              the input signal to gate
    * @param gate            the signal specifying whether to pass the input signal (when greater than zero) or
    *                        whether to close the gate and hold the last value (when less than or equal to zero)
    */
   def kr(in: AnyGE, gate: AnyGE) = apply[control](control, in, gate)
   /**
    * @param in              the input signal to gate
    * @param gate            the signal specifying whether to pass the input signal (when greater than zero) or
    *                        whether to close the gate and hold the last value (when less than or equal to zero)
    */
   def ar(in: AnyGE, gate: AnyGE) = apply[audio](audio, in, gate)
}
/**
 * A gate or hold UGen.
 * It allows the input signal value to pass when the `gate` argument is positive,
 * otherwise it holds last value.
 * 
 * @param in              the input signal to gate
 * @param gate            the signal specifying whether to pass the input signal (when greater than zero) or
 *                        whether to close the gate and hold the last value (when less than or equal to zero)
 * 
 * @see [[de.sciss.synth.ugen.Latch]]
 */
final case class Gate[R <: Rate](rate: R, in: AnyGE, gate: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, gate.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Gate", rate, _args)
}
/**
 * A Schmidt trigger UGen. Initially it outputs zero. When the input signal rises above `hi`,
 * its output switches to 1.0, which is hold until the signal falls below `lo`, switching the
 * output again to 0.0. The produces a kind of hysteresis behavior, preventing heavy
 * oscillations in a noisy system which might occur with a single-threshold trigger.
 */
object Schmidt {
   
   /**
    * @param lo              The low threshold.
    * @param hi              The high threshold.
    */
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   /**
    * @param lo              The low threshold.
    * @param hi              The high threshold.
    */
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
/**
 * A Schmidt trigger UGen. Initially it outputs zero. When the input signal rises above `hi`,
 * its output switches to 1.0, which is hold until the signal falls below `lo`, switching the
 * output again to 0.0. The produces a kind of hysteresis behavior, preventing heavy
 * oscillations in a noisy system which might occur with a single-threshold trigger.
 * 
 * @param lo              The low threshold.
 * @param hi              The high threshold.
 */
final case class Schmidt[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Schmidt", rate, _args)
}
object PulseDivider {
   def kr(trig: AnyGE, div: AnyGE = 2.0f, start: AnyGE = 0.0f) = apply[control](control, trig, div, start)
   def ar(trig: AnyGE, div: AnyGE = 2.0f, start: AnyGE = 0.0f) = apply[audio](audio, trig, div, start)
}
final case class PulseDivider[R <: Rate](rate: R, trig: AnyGE, div: AnyGE, start: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, div.expand, start.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("PulseDivider", rate, _args)
}
object PulseCount {
   def kr(trig: AnyGE, reset: AnyGE = 0.0f) = apply[control](control, trig, reset)
   def ar(trig: AnyGE, reset: AnyGE = 0.0f) = apply[audio](audio, trig, reset)
}
final case class PulseCount[R <: Rate](rate: R, trig: AnyGE, reset: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("PulseCount", rate, _args)
}
object Stepper {
   def kr(trig: AnyGE, reset: AnyGE = 0.0f, lo: AnyGE = 0.0f, hi: AnyGE = 7.0f, step: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[control](control, trig, reset, lo, hi, step, resetVal)
   def ar(trig: AnyGE, reset: AnyGE = 0.0f, lo: AnyGE = 0.0f, hi: AnyGE = 7.0f, step: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[audio](audio, trig, reset, lo, hi, step, resetVal)
}
final case class Stepper[R <: Rate](rate: R, trig: AnyGE, reset: AnyGE, lo: AnyGE, hi: AnyGE, step: AnyGE, resetVal: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand, lo.expand, hi.expand, step.expand, resetVal.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Stepper", rate, _args)
}
/**
 * A delay UGen for trigger signals. Other than a normal buffer delay,
 * any new trigger arriving in the time between the previous trigger
 * and the passing of the delay time is ignored.
 */
object TDelay {
   
   /**
    * @param trig            The input trigger. A trigger is recognized when the signal passes from
    *                        non-positive to positive. Note that, no matter what the amplitude of
    *                        the input trigger is, the UGen will output a delayed trigger of
    *                        amplitude 1.0.
    * @param dur             The delay time in seconds.
    */
   def kr(trig: AnyGE, dur: AnyGE = 0.1f) = apply[control](control, trig, dur)
   /**
    * @param trig            The input trigger. A trigger is recognized when the signal passes from
    *                        non-positive to positive. Note that, no matter what the amplitude of
    *                        the input trigger is, the UGen will output a delayed trigger of
    *                        amplitude 1.0.
    * @param dur             The delay time in seconds.
    */
   def ar(trig: AnyGE, dur: AnyGE = 0.1f) = apply[audio](audio, trig, dur)
}
/**
 * A delay UGen for trigger signals. Other than a normal buffer delay,
 * any new trigger arriving in the time between the previous trigger
 * and the passing of the delay time is ignored.
 * 
 * @param trig            The input trigger. A trigger is recognized when the signal passes from
 *                        non-positive to positive. Note that, no matter what the amplitude of
 *                        the input trigger is, the UGen will output a delayed trigger of
 *                        amplitude 1.0.
 * @param dur             The delay time in seconds.
 */
final case class TDelay[R <: Rate](rate: R, trig: AnyGE, dur: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TDelay", rate, _args)
}
object ZeroCrossing {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class ZeroCrossing[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("ZeroCrossing", rate, _args)
}
/**
 * A UGen that returns time since last triggered.
 * The time returned is in seconds and is measured from the last received trigger.
 * Note that currently it seems the initial memory is at -1 sample, so for
 * `Impulse.ar(1)` the result (at 44.1 kHz) is 2.26757e-05, followed strangely
 * by 1.00002, and then (as expected) 1.0.
 */
object Timer {
   
   /**
    * @param trig            the trigger to update the output signal.
    *                        A trigger occurs when trig signal crosses from non-positive to positive.
    */
   def kr(trig: AnyGE) = apply[control](control, trig)
   /**
    * @param trig            the trigger to update the output signal.
    *                        A trigger occurs when trig signal crosses from non-positive to positive.
    */
   def ar(trig: AnyGE) = apply[audio](audio, trig)
}
/**
 * A UGen that returns time since last triggered.
 * The time returned is in seconds and is measured from the last received trigger.
 * Note that currently it seems the initial memory is at -1 sample, so for
 * `Impulse.ar(1)` the result (at 44.1 kHz) is 2.26757e-05, followed strangely
 * by 1.00002, and then (as expected) 1.0.
 * 
 * @param trig            the trigger to update the output signal.
 *                        A trigger occurs when trig signal crosses from non-positive to positive.
 */
final case class Timer[R <: Rate](rate: R, trig: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Timer", rate, _args)
}
/**
 * A UGen which starts a linear raise from zero each time it is
 * triggered.
 * 
 * @see [[de.sciss.synth.ugen.Ramp]]
 * @see [[de.sciss.synth.ugen.Phasor]]
 * @see [[de.sciss.synth.ugen.Line]]
 */
object Sweep {
   
   /**
    * @param trig            the trigger that restarts the ramp, when passing from
    *                        non-positive to positive
    * @param speed           the amount of increment of the output signal per second.
    *                        In SCLang this argument is named `rate`, while ScalaCollider uses
    *                        `speed` to avoid conflict with the UGen's calculation rate.
    */
   def kr(trig: AnyGE, speed: AnyGE) = apply[control](control, trig, speed)
   /**
    * @param trig            the trigger that restarts the ramp, when passing from
    *                        non-positive to positive
    * @param speed           the amount of increment of the output signal per second.
    *                        In SCLang this argument is named `rate`, while ScalaCollider uses
    *                        `speed` to avoid conflict with the UGen's calculation rate.
    */
   def ar(trig: AnyGE, speed: AnyGE) = apply[audio](audio, trig, speed)
}
/**
 * A UGen which starts a linear raise from zero each time it is
 * triggered.
 * 
 * @param trig            the trigger that restarts the ramp, when passing from
 *                        non-positive to positive
 * @param speed           the amount of increment of the output signal per second.
 *                        In SCLang this argument is named `rate`, while ScalaCollider uses
 *                        `speed` to avoid conflict with the UGen's calculation rate.
 * 
 * @see [[de.sciss.synth.ugen.Ramp]]
 * @see [[de.sciss.synth.ugen.Phasor]]
 * @see [[de.sciss.synth.ugen.Line]]
 */
final case class Sweep[R <: Rate](rate: R, trig: AnyGE, speed: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, speed.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Sweep", rate, _args)
}
object Phasor {
   
   /**
    * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
    *                        be ignored (you need to feed it zero first)
    */
   def kr(trig: AnyGE, speed: AnyGE = 1.0f, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[control](control, trig, speed, lo, hi, resetVal)
   /**
    * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
    *                        be ignored (you need to feed it zero first)
    */
   def ar(trig: AnyGE, speed: AnyGE = 1.0f, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, resetVal: AnyGE = 0.0f) = apply[audio](audio, trig, speed, lo, hi, resetVal)
}
/**
 * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
 *                        be ignored (you need to feed it zero first)
 */
final case class Phasor[R <: Rate](rate: R, trig: AnyGE, speed: AnyGE, lo: AnyGE, hi: AnyGE, resetVal: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, speed.expand, lo.expand, hi.expand, resetVal.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Phasor", rate, _args)
}
object Peak {
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
final case class Peak[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Peak", rate, _args)
}
object RunningMin {
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
final case class RunningMin[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RunningMin", rate, _args)
}
object RunningMax {
   def kr(in: AnyGE, trig: AnyGE) = apply[control](control, in, trig)
   def ar(in: AnyGE, trig: AnyGE) = apply[audio](audio, in, trig)
}
final case class RunningMax[R <: Rate](rate: R, in: AnyGE, trig: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RunningMax", rate, _args)
}
object PeakFollower {
   def kr(in: AnyGE, decay: AnyGE = 0.999f) = apply[control](control, in, decay)
   def ar(in: AnyGE, decay: AnyGE = 0.999f) = apply[audio](audio, in, decay)
}
final case class PeakFollower[R <: Rate](rate: R, in: AnyGE, decay: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, decay.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("PeakFollower", rate, _args)
}
object MostChange {
   def kr(a: AnyGE, b: AnyGE) = apply[control](control, a, b)
   def ar(a: AnyGE, b: AnyGE) = apply[audio](audio, a, b)
}
final case class MostChange[R <: Rate](rate: R, a: AnyGE, b: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(a.expand, b.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MostChange", rate, _args)
}
object LeastChange {
   def kr(a: AnyGE, b: AnyGE) = apply[control](control, a, b)
   def ar(a: AnyGE, b: AnyGE) = apply[audio](audio, a, b)
}
final case class LeastChange[R <: Rate](rate: R, a: AnyGE, b: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(a.expand, b.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LeastChange", rate, _args)
}
object LastValue {
   def kr(in: AnyGE, thresh: AnyGE = 0.01f) = apply[control](control, in, thresh)
   def ar(in: AnyGE, thresh: AnyGE = 0.01f) = apply[audio](audio, in, thresh)
}
final case class LastValue[R <: Rate](rate: R, in: AnyGE, thresh: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, thresh.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LastValue", rate, _args)
}
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
final case class Done(src: AnyGE with HasDoneFlag) extends UGenSource.SingleOut[control] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(src.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Done", control, _args)
}
/**
 * A UGen which pauses and resumes another node.
 * Note that the UGen initially assumes the node is running, that is,
 * if `gate` is initially 1, this will '''not''' resume a paused node.
 * Instead, the gate must go to zero and back to one to resume the node.
 * Additionally, this UGen will only cause action if the gate value
 * changes, that is, if the node is paused or resumed otherwise, this
 * UGen will not interfere with that action, unless the gate value is
 * adjusted.
 * 
 * @see [[de.sciss.synth.ugen.Free]]
 * @see [[de.sciss.synth.ugen.PauseSelf]]
 */
object Pause {
   
   /**
    * @param gate            when 0, node is paused, when 1, node is resumed
    * @param node            the id of the node to be paused or resumed
    */
   def kr(gate: AnyGE, node: AnyGE) = apply(gate, node)
}
/**
 * A UGen which pauses and resumes another node.
 * Note that the UGen initially assumes the node is running, that is,
 * if `gate` is initially 1, this will '''not''' resume a paused node.
 * Instead, the gate must go to zero and back to one to resume the node.
 * Additionally, this UGen will only cause action if the gate value
 * changes, that is, if the node is paused or resumed otherwise, this
 * UGen will not interfere with that action, unless the gate value is
 * adjusted.
 * 
 * @param gate            when 0, node is paused, when 1, node is resumed
 * @param node            the id of the node to be paused or resumed
 * 
 * @see [[de.sciss.synth.ugen.Free]]
 * @see [[de.sciss.synth.ugen.PauseSelf]]
 */
final case class Pause(gate: AnyGE, node: AnyGE) extends UGenSource.SingleOut[control] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(gate.expand, node.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Pause", control, _args)
}
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
final case class FreeSelf(trig: AnyGE) extends UGenSource.SingleOut[control] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FreeSelf", control, _args)
}
/**
 * A UGen that, when triggered, pauses enclosing synth.
 * It pauses the enclosing synth when the input signal crosses from non-positive to positive.
 * 
 * This UGen outputs its input signal for convenience.
 * 
 * @see [[de.sciss.synth.ugen.Pause]]
 * @see [[de.sciss.synth.ugen.FreeSelf]]
 */
object PauseSelf {
   
   /**
    * @param trig            the input signal which will trigger the action.
    */
   def kr(trig: AnyGE) = apply(trig)
}
/**
 * A UGen that, when triggered, pauses enclosing synth.
 * It pauses the enclosing synth when the input signal crosses from non-positive to positive.
 * 
 * This UGen outputs its input signal for convenience.
 * 
 * @param trig            the input signal which will trigger the action.
 * 
 * @see [[de.sciss.synth.ugen.Pause]]
 * @see [[de.sciss.synth.ugen.FreeSelf]]
 */
final case class PauseSelf(trig: AnyGE) extends UGenSource.SingleOut[control] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("PauseSelf", control, _args)
}
/**
 * A UGen that, when triggered, frees a given node.
 * 
 * This UGen outputs its trig input signal for convenience.
 * 
 * @see [[de.sciss.synth.ugen.Pause]]
 * @see [[de.sciss.synth.ugen.FreeSelf]]
 */
object Free {
   
   /**
    * @param trig            the trigger to cause the action
    * @param node            the id of the target node to free upon receiving the trigger
    */
   def kr(trig: AnyGE, node: AnyGE) = apply(trig, node)
}
/**
 * A UGen that, when triggered, frees a given node.
 * 
 * This UGen outputs its trig input signal for convenience.
 * 
 * @param trig            the trigger to cause the action
 * @param node            the id of the target node to free upon receiving the trigger
 * 
 * @see [[de.sciss.synth.ugen.Pause]]
 * @see [[de.sciss.synth.ugen.FreeSelf]]
 */
final case class Free(trig: AnyGE, node: AnyGE) extends UGenSource.SingleOut[control] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, node.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Free", control, _args)
}
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
final case class FreeSelfWhenDone(src: AnyGE with HasDoneFlag) extends UGenSource.SingleOut[control] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(src.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FreeSelfWhenDone", control, _args)
}
/**
 * A UGen that, when its input UGen is finished, pauses enclosing synth.
 * This is essentially a shortcut for `PauseSelf.kr( Done.kr( src ))`, so instead
 * of providing a trigger signal it reads directly the done flag of an
 * appropriate ugen (such as `Line` or `PlayBuf`).
 * 
 * This UGen outputs its input signal for convenience.
 * 
 * @see [[de.sciss.synth.ugen.Pause]]
 * @see [[de.sciss.synth.ugen.PauseSelf]]
 * @see [[de.sciss.synth.ugen.FreeSelfWhenDone]]
 * @see [[de.sciss.synth.ugen.Done]]
 */
object PauseSelfWhenDone {
   
   /**
    * @param src             the input UGen which when finished will trigger the action.
    */
   def kr(src: AnyGE with HasDoneFlag) = apply(src)
}
/**
 * A UGen that, when its input UGen is finished, pauses enclosing synth.
 * This is essentially a shortcut for `PauseSelf.kr( Done.kr( src ))`, so instead
 * of providing a trigger signal it reads directly the done flag of an
 * appropriate ugen (such as `Line` or `PlayBuf`).
 * 
 * This UGen outputs its input signal for convenience.
 * 
 * @param src             the input UGen which when finished will trigger the action.
 * 
 * @see [[de.sciss.synth.ugen.Pause]]
 * @see [[de.sciss.synth.ugen.PauseSelf]]
 * @see [[de.sciss.synth.ugen.FreeSelfWhenDone]]
 * @see [[de.sciss.synth.ugen.Done]]
 */
final case class PauseSelfWhenDone(src: AnyGE with HasDoneFlag) extends UGenSource.SingleOut[control] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(src.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("PauseSelfWhenDone", control, _args)
}