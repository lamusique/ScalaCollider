/*
 * TriggerUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Oct 27 17:42:00 BST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
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
   def ar(in: GE, dur: GE = 0.1f) = apply(audio, in, dur)
   /**
    * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
    *                        from non-positive to positive.
    * @param dur             the duration for which the ugens holds the value of 1 when triggered
    */
   def kr(in: GE, dur: GE = 0.1f) = apply(control, in, dur)
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
final case class Trig1(rate: Rate, in: GE, dur: GE) extends UGenSource.SingleOut("Trig1") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Trig {
   
   /**
    * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
    *                        from non-positive to positive.
    * @param dur             the duration for which the ugens holds the value of the input signal when triggered
    */
   def ar(in: GE, dur: GE = 0.1f) = apply(audio, in, dur)
   /**
    * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
    *                        from non-positive to positive.
    * @param dur             the duration for which the ugens holds the value of the input signal when triggered
    */
   def kr(in: GE, dur: GE = 0.1f) = apply(control, in, dur)
}
/**
 * @param in              the trigger. This can be any signal. A trigger happens when the signal changes
 *                        from non-positive to positive.
 * @param dur             the duration for which the ugens holds the value of the input signal when triggered
 */
final case class Trig(rate: Rate, in: GE, dur: GE) extends UGenSource.SingleOut("Trig") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def ar(trig: GE, id: GE = 0.0f, value: GE = 0.0f) = apply(audio, trig, id, value)
   /**
    * @param trig            the trigger signal causing the value to be read and sent. A trigger occurs
    *                        when passing from non-positive to positive.
    * @param id              an arbitrary integer that will be sent along with the `"/tr"` message.
    *                        This is useful to distinguish between several SendTrig instances per SynthDef.
    * @param value           a changing signal or constant that will be polled at the time of trigger,
    *                        and its value passed with the trigger message
    */
   def kr(trig: GE, id: GE = 0.0f, value: GE = 0.0f) = apply(control, trig, id, value)
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
final case class SendTrig(rate: MaybeRate, trig: GE, id: GE, value: GE) extends UGenSource.SingleOut("SendTrig") with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, value.expand, id.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args, false, true)
   }
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
   def kr(trig: GE, values: GE, msgName: String = "/reply", id: GE = 0.0f) = apply(control, trig, values, msgName, id)
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
   def ar(trig: GE, values: GE, msgName: String = "/reply", id: GE = 0.0f) = apply(audio, trig, values, msgName, id)
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
final case class SendReply(rate: MaybeRate, trig: GE, values: GE, msgName: String, id: GE) extends UGenSource.SingleOut("SendReply") with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, id.expand).++(stringArg(msgName).++(values.expand.outputs)))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args, false, true)
   }
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
   def ar(trig: GE, in: GE, label: String = "poll", trigID: GE = -1.0f) = apply(audio, trig, in, label, trigID)
   /**
    * @param trig            a non-positive to positive transition telling Poll to return a value
    * @param in              the signal you want to poll
    * @param label           a string or symbol to be printed with the polled value
    * @param trigID          if greater then 0, a `"/tr"` OSC message is sent back to the client
    *                        (similar to `SendTrig`)
    */
   def kr(trig: GE, in: GE, label: String = "poll", trigID: GE = -1.0f) = apply(control, trig, in, label, trigID)
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
final case class Poll(rate: MaybeRate, trig: GE, in: GE, label: String, trigID: GE) extends UGenSource.SingleOut("Poll") with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, in.expand, trigID.expand).++(stringArg(label)))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args, false, true)
   }
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
   def kr(trig: GE) = apply(control, trig)
   /**
    * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
    *                        changes from non-positive to positive.
    */
   def ar(trig: GE) = apply(audio, trig)
}
/**
 * A UGen that toggles like a flip-flop between zero and one upon receiving a trigger.
 * The flip-flop is initially outputing zero, so changes to one when the first trigger
 * arrives.
 * 
 * @param trig            a signal to trigger the flip-flop. a trigger occurs when the signal
 *                        changes from non-positive to positive.
 */
final case class ToggleFF(rate: Rate, trig: GE) extends UGenSource.SingleOut("ToggleFF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object SetResetFF {
   def kr(trig: GE, reset: GE) = apply(control, trig, reset)
   def ar(trig: GE, reset: GE) = apply(audio, trig, reset)
}
final case class SetResetFF(rate: Rate, trig: GE, reset: GE) extends UGenSource.SingleOut("SetResetFF") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(in: GE, trig: GE) = apply(control, in, trig)
   /**
    * @param in              the input signal
    * @param trig            the trigger. The can be any signal. A trigger happens when the signal changes from
    *                        non-positive to positive.
    */
   def ar(in: GE, trig: GE) = apply(audio, in, trig)
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
final case class Latch(rate: Rate, in: GE, trig: GE) extends UGenSource.SingleOut("Latch") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(in: GE, gate: GE) = apply(control, in, gate)
   /**
    * @param in              the input signal to gate
    * @param gate            the signal specifying whether to pass the input signal (when greater than zero) or
    *                        whether to close the gate and hold the last value (when less than or equal to zero)
    */
   def ar(in: GE, gate: GE) = apply(audio, in, gate)
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
final case class Gate(rate: Rate, in: GE, gate: GE) extends UGenSource.SingleOut("Gate") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, gate.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(control, in, lo, hi)
   /**
    * @param lo              The low threshold.
    * @param hi              The high threshold.
    */
   def ar(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(audio, in, lo, hi)
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
final case class Schmidt(rate: Rate, in: GE, lo: GE, hi: GE) extends UGenSource.SingleOut("Schmidt") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object PulseDivider {
   def kr(trig: GE, div: GE = 2.0f, start: GE = 0.0f) = apply(control, trig, div, start)
   def ar(trig: GE, div: GE = 2.0f, start: GE = 0.0f) = apply(audio, trig, div, start)
}
final case class PulseDivider(rate: Rate, trig: GE, div: GE, start: GE) extends UGenSource.SingleOut("PulseDivider") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, div.expand, start.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object PulseCount {
   def kr(trig: GE, reset: GE = 0.0f) = apply(control, trig, reset)
   def ar(trig: GE, reset: GE = 0.0f) = apply(audio, trig, reset)
}
final case class PulseCount(rate: Rate, trig: GE, reset: GE) extends UGenSource.SingleOut("PulseCount") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A pulse counting UGen. Each trigger increments a counter which is output as a signal.
 * The counter wraps inside the interval from `lo` to `hi` (inclusive). That if you
 * use a `lo` other than zero, you might want to adjust `resetVal` as well. `Stepper`
 * always starts with the value in `resetVal`, no matter what `lo` is or whether
 * the `reset` trigger is high or not.
 * 
 * @see [[de.sciss.synth.ugen.PulseCount]]
 */
object Stepper {
   
   /**
    * @param trig            The trigger signal which increments the counter. A trigger happens when the signal
    *                        changes from non-positive to positive. Note that if the UGen is created with the
    *                        trigger initially high, the counter will also be incremented immediately. Thus
    *                        a `Stepper.kr(Impulse.kr(1))` will begin by outputting `1`. If you want to avoid this,
    *                        you could their subtract `Impulse.kr(0)` from the trigger input, or set `resetVal`
    *                        to `hi`. E.g. `Stepper.kr(Impulse.kr(1), lo = 0, hi = 4, resetVal = 4)` will produce
    *                        the sequence 0, 1, 2, 4, 0, ...
    * @param reset           A trigger which resets the counter to `resetVal` immediately.
    * @param lo              The minimum value output. For a decremental `step` value, the counter jumps
    *                        to `hi` if it were to fall below `lo`.
    * @param hi              The maximum value output. For an incremental `step` value, the counter jumps
    *                        to `lo` if it were to rise beyond `hi`.
    * @param step            The amount by which the counter increases or decreases upon receiving triggers.
    *                        Note that if you use a decremental counter, still `lo` must be the minimum and
    *                        `hi` must be the maximum value output. If `lo` > `hi`, the UGen behaves wrongly.
    *                        In the case of decremental counter, set `resetVal` to `hi`. E.g. to count
    *                        from 4 down to 0, use `Stepper.kr(trig, lo = 0, hi = 4, step = -1, resetVal = 4)`, or,
    *                        if you want to ignore an initial high trigger, you could do
    *                        `Stepper.kr(Impulse.kr(1), lo = 0, hi = 4, step = -1, resetVal = 0)` --
    *                        so `resetVal` is `lo` but due to the initial trigger from `Impulse`
    *                        the `Stepper` will in fact start outputting from `4`.
    */
   def kr(trig: GE, reset: GE = 0.0f, lo: GE = 0.0f, hi: GE = 7.0f, step: GE = 1.0f, resetVal: GE = 0.0f) = apply(control, trig, reset, lo, hi, step, resetVal)
   /**
    * @param trig            The trigger signal which increments the counter. A trigger happens when the signal
    *                        changes from non-positive to positive. Note that if the UGen is created with the
    *                        trigger initially high, the counter will also be incremented immediately. Thus
    *                        a `Stepper.kr(Impulse.kr(1))` will begin by outputting `1`. If you want to avoid this,
    *                        you could their subtract `Impulse.kr(0)` from the trigger input, or set `resetVal`
    *                        to `hi`. E.g. `Stepper.kr(Impulse.kr(1), lo = 0, hi = 4, resetVal = 4)` will produce
    *                        the sequence 0, 1, 2, 4, 0, ...
    * @param reset           A trigger which resets the counter to `resetVal` immediately.
    * @param lo              The minimum value output. For a decremental `step` value, the counter jumps
    *                        to `hi` if it were to fall below `lo`.
    * @param hi              The maximum value output. For an incremental `step` value, the counter jumps
    *                        to `lo` if it were to rise beyond `hi`.
    * @param step            The amount by which the counter increases or decreases upon receiving triggers.
    *                        Note that if you use a decremental counter, still `lo` must be the minimum and
    *                        `hi` must be the maximum value output. If `lo` > `hi`, the UGen behaves wrongly.
    *                        In the case of decremental counter, set `resetVal` to `hi`. E.g. to count
    *                        from 4 down to 0, use `Stepper.kr(trig, lo = 0, hi = 4, step = -1, resetVal = 4)`, or,
    *                        if you want to ignore an initial high trigger, you could do
    *                        `Stepper.kr(Impulse.kr(1), lo = 0, hi = 4, step = -1, resetVal = 0)` --
    *                        so `resetVal` is `lo` but due to the initial trigger from `Impulse`
    *                        the `Stepper` will in fact start outputting from `4`.
    */
   def ar(trig: GE, reset: GE = 0.0f, lo: GE = 0.0f, hi: GE = 7.0f, step: GE = 1.0f, resetVal: GE = 0.0f) = apply(audio, trig, reset, lo, hi, step, resetVal)
}
/**
 * A pulse counting UGen. Each trigger increments a counter which is output as a signal.
 * The counter wraps inside the interval from `lo` to `hi` (inclusive). That if you
 * use a `lo` other than zero, you might want to adjust `resetVal` as well. `Stepper`
 * always starts with the value in `resetVal`, no matter what `lo` is or whether
 * the `reset` trigger is high or not.
 * 
 * @param trig            The trigger signal which increments the counter. A trigger happens when the signal
 *                        changes from non-positive to positive. Note that if the UGen is created with the
 *                        trigger initially high, the counter will also be incremented immediately. Thus
 *                        a `Stepper.kr(Impulse.kr(1))` will begin by outputting `1`. If you want to avoid this,
 *                        you could their subtract `Impulse.kr(0)` from the trigger input, or set `resetVal`
 *                        to `hi`. E.g. `Stepper.kr(Impulse.kr(1), lo = 0, hi = 4, resetVal = 4)` will produce
 *                        the sequence 0, 1, 2, 4, 0, ...
 * @param reset           A trigger which resets the counter to `resetVal` immediately.
 * @param lo              The minimum value output. For a decremental `step` value, the counter jumps
 *                        to `hi` if it were to fall below `lo`.
 * @param hi              The maximum value output. For an incremental `step` value, the counter jumps
 *                        to `lo` if it were to rise beyond `hi`.
 * @param step            The amount by which the counter increases or decreases upon receiving triggers.
 *                        Note that if you use a decremental counter, still `lo` must be the minimum and
 *                        `hi` must be the maximum value output. If `lo` > `hi`, the UGen behaves wrongly.
 *                        In the case of decremental counter, set `resetVal` to `hi`. E.g. to count
 *                        from 4 down to 0, use `Stepper.kr(trig, lo = 0, hi = 4, step = -1, resetVal = 4)`, or,
 *                        if you want to ignore an initial high trigger, you could do
 *                        `Stepper.kr(Impulse.kr(1), lo = 0, hi = 4, step = -1, resetVal = 0)` --
 *                        so `resetVal` is `lo` but due to the initial trigger from `Impulse`
 *                        the `Stepper` will in fact start outputting from `4`.
 * 
 * @see [[de.sciss.synth.ugen.PulseCount]]
 */
final case class Stepper(rate: Rate, trig: GE, reset: GE, lo: GE, hi: GE, step: GE, resetVal: GE) extends UGenSource.SingleOut("Stepper") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand, lo.expand, hi.expand, step.expand, resetVal.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(trig: GE, dur: GE = 0.1f) = apply(control, trig, dur)
   /**
    * @param trig            The input trigger. A trigger is recognized when the signal passes from
    *                        non-positive to positive. Note that, no matter what the amplitude of
    *                        the input trigger is, the UGen will output a delayed trigger of
    *                        amplitude 1.0.
    * @param dur             The delay time in seconds.
    */
   def ar(trig: GE, dur: GE = 0.1f) = apply(audio, trig, dur)
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
final case class TDelay(rate: Rate, trig: GE, dur: GE) extends UGenSource.SingleOut("TDelay") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object ZeroCrossing {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class ZeroCrossing(rate: Rate, in: GE) extends UGenSource.SingleOut("ZeroCrossing") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(trig: GE) = apply(control, trig)
   /**
    * @param trig            the trigger to update the output signal.
    *                        A trigger occurs when trig signal crosses from non-positive to positive.
    */
   def ar(trig: GE) = apply(audio, trig)
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
final case class Timer(rate: Rate, trig: GE) extends UGenSource.SingleOut("Timer") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(trig: GE, speed: GE) = apply(control, trig, speed)
   /**
    * @param trig            the trigger that restarts the ramp, when passing from
    *                        non-positive to positive
    * @param speed           the amount of increment of the output signal per second.
    *                        In SCLang this argument is named `rate`, while ScalaCollider uses
    *                        `speed` to avoid conflict with the UGen's calculation rate.
    */
   def ar(trig: GE, speed: GE) = apply(audio, trig, speed)
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
final case class Sweep(rate: Rate, trig: GE, speed: GE) extends UGenSource.SingleOut("Sweep") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, speed.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Phasor {
   
   /**
    * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
    *                        be ignored (you need to feed it zero first)
    */
   def kr(trig: GE, speed: GE = 1.0f, lo: GE = 0.0f, hi: GE = 1.0f, resetVal: GE = 0.0f) = apply(control, trig, speed, lo, hi, resetVal)
   /**
    * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
    *                        be ignored (you need to feed it zero first)
    */
   def ar(trig: GE, speed: GE = 1.0f, lo: GE = 0.0f, hi: GE = 1.0f, resetVal: GE = 0.0f) = apply(audio, trig, speed, lo, hi, resetVal)
}
/**
 * @param trig            Warning: SC 3.4 has a bug where an initial trig value of 1 will
 *                        be ignored (you need to feed it zero first)
 */
final case class Phasor(rate: Rate, trig: GE, speed: GE, lo: GE, hi: GE, resetVal: GE) extends UGenSource.SingleOut("Phasor") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, speed.expand, lo.expand, hi.expand, resetVal.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Peak {
   def kr(in: GE, trig: GE) = apply(control, in, trig)
   def ar(in: GE, trig: GE) = apply(audio, in, trig)
}
final case class Peak(rate: Rate, in: GE, trig: GE) extends UGenSource.SingleOut("Peak") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object RunningMin {
   def kr(in: GE, trig: GE) = apply(control, in, trig)
   def ar(in: GE, trig: GE) = apply(audio, in, trig)
}
final case class RunningMin(rate: Rate, in: GE, trig: GE) extends UGenSource.SingleOut("RunningMin") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object RunningMax {
   def kr(in: GE, trig: GE) = apply(control, in, trig)
   def ar(in: GE, trig: GE) = apply(audio, in, trig)
}
final case class RunningMax(rate: Rate, in: GE, trig: GE) extends UGenSource.SingleOut("RunningMax") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object PeakFollower {
   def kr(in: GE, decay: GE = 0.999f) = apply(control, in, decay)
   def ar(in: GE, decay: GE = 0.999f) = apply(audio, in, decay)
}
final case class PeakFollower(rate: Rate, in: GE, decay: GE) extends UGenSource.SingleOut("PeakFollower") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, decay.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object MostChange {
   def kr(a: GE, b: GE) = apply(control, a, b)
   def ar(a: GE, b: GE) = apply(audio, a, b)
}
final case class MostChange(rate: Rate, a: GE, b: GE) extends UGenSource.SingleOut("MostChange") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(a.expand, b.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LeastChange {
   def kr(a: GE, b: GE) = apply(control, a, b)
   def ar(a: GE, b: GE) = apply(audio, a, b)
}
final case class LeastChange(rate: Rate, a: GE, b: GE) extends UGenSource.SingleOut("LeastChange") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(a.expand, b.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LastValue {
   def kr(in: GE, thresh: GE = 0.01f) = apply(control, in, thresh)
   def ar(in: GE, thresh: GE = 0.01f) = apply(audio, in, thresh)
}
final case class LastValue(rate: Rate, in: GE, thresh: GE) extends UGenSource.SingleOut("LastValue") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, thresh.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(src: GE with HasDoneFlag) = apply(src)
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
final case class Done(src: GE with HasDoneFlag) extends UGenSource.SingleOut("Done") with HasSideEffect with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(src.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args, false, true)
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
   def kr(gate: GE, node: GE) = apply(gate, node)
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
final case class Pause(gate: GE, node: GE) extends UGenSource.SingleOut("Pause") with HasSideEffect with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(gate.expand, node.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args, false, true)
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
   def kr(trig: GE) = apply(trig)
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
final case class FreeSelf(trig: GE) extends UGenSource.SingleOut("FreeSelf") with HasSideEffect with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args, false, true)
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
   def kr(trig: GE) = apply(trig)
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
final case class PauseSelf(trig: GE) extends UGenSource.SingleOut("PauseSelf") with HasSideEffect with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args, false, true)
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
   def kr(trig: GE, node: GE) = apply(trig, node)
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
final case class Free(trig: GE, node: GE) extends UGenSource.SingleOut("Free") with HasSideEffect with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, node.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args, false, true)
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
   def kr(src: GE with HasDoneFlag) = apply(src)
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
final case class FreeSelfWhenDone(src: GE with HasDoneFlag) extends UGenSource.SingleOut("FreeSelfWhenDone") with HasSideEffect with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(src.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args, false, true)
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
   def kr(src: GE with HasDoneFlag) = apply(src)
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
final case class PauseSelfWhenDone(src: GE with HasDoneFlag) extends UGenSource.SingleOut("PauseSelfWhenDone") with HasSideEffect with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(src.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args, false, true)
}