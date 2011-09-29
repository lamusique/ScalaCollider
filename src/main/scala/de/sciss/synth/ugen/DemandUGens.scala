/*
 * DemandUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Sep 29 16:47:16 CEST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
import Float.{PositiveInfinity => inf}
/**
 * A UGen which polls results from demand-rate ugens when receiving a trigger.
 * When there is a trigger at the `trig` input, a value is demanded from each ugen in the `in` input
 * and output. The unit generators in the list should be demand-rate.
 * When there is a trigger at the reset input, the demand rate ugens in the list are reset.
 * 
 * Note: By design, a reset trigger only resets the demand ugens; it does not reset the value at Demand's output.
 * Demand continues to hold its value until the next value is demanded, at which point its output value will
 * be the first expected item in the `in` argument.
 * 
 * Note: One demand-rate ugen represents a single stream of values, so that embedding the same ugen twice
 * calls this stream twice per demand, possibly yielding different values. To embed the same sequence
 * twice, either make sure the ugen is demanded only once, or create two instances of the ugen.
 * 
 * '''Warning''': Demand currently seems to have problems with infinite sequences. As a workaround
 * use a very large length instead. E.g. instead of `Dbrown( 0, 1, inf )` use `Dbrown( 0, 1, 0xFFFFFFFF )`!
 * 
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object Demand {
   
   /**
    * @param trig            trigger. Can be any signal. A trigger happens when the signal changes from non-positive to positive.
    * @param in              a demand-rate signal (possibly multi-channel) which is read at each trigger
    * @param reset           trigger. Resets the list of ugens (`multi`) when triggered.
    */
   def kr(trig: GE, in: GE, reset: GE = 0.0f) = apply(control, trig, in, reset)
   /**
    * @param trig            trigger. Can be any signal. A trigger happens when the signal changes from non-positive to positive.
    * @param in              a demand-rate signal (possibly multi-channel) which is read at each trigger
    * @param reset           trigger. Resets the list of ugens (`multi`) when triggered.
    */
   def ar(trig: GE, in: GE, reset: GE = 0.0f) = apply(audio, trig, in, reset)
}
/**
 * A UGen which polls results from demand-rate ugens when receiving a trigger.
 * When there is a trigger at the `trig` input, a value is demanded from each ugen in the `in` input
 * and output. The unit generators in the list should be demand-rate.
 * When there is a trigger at the reset input, the demand rate ugens in the list are reset.
 * 
 * Note: By design, a reset trigger only resets the demand ugens; it does not reset the value at Demand's output.
 * Demand continues to hold its value until the next value is demanded, at which point its output value will
 * be the first expected item in the `in` argument.
 * 
 * Note: One demand-rate ugen represents a single stream of values, so that embedding the same ugen twice
 * calls this stream twice per demand, possibly yielding different values. To embed the same sequence
 * twice, either make sure the ugen is demanded only once, or create two instances of the ugen.
 * 
 * '''Warning''': Demand currently seems to have problems with infinite sequences. As a workaround
 * use a very large length instead. E.g. instead of `Dbrown( 0, 1, inf )` use `Dbrown( 0, 1, 0xFFFFFFFF )`!
 * 
 * '''Warning''': The argument order is different from its sclang counterpart.
 * 
 * @param trig            trigger. Can be any signal. A trigger happens when the signal changes from non-positive to positive.
 * @param in              a demand-rate signal (possibly multi-channel) which is read at each trigger
 * @param reset           trigger. Resets the list of ugens (`multi`) when triggered.
 */
final case class Demand(rate: Rate, trig: GE, in: GE, reset: GE) extends UGenSource.MultiOut("Demand") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(_args.size.-(2))(rate), _args)
}
/**
 * A UGen which polls results from demand-rate ugens in intervals specified by a durational input.
 * A value from the `level` ugen is demanded and output according to a stream
 * of duration values. When there is a trigger at the reset input, the `level`
 * and the `dur` input are reset.
 * 
 * @see [[de.sciss.synth.ugen.TDuty]]
 * @see [[de.sciss.synth.ugen.Demand]]
 * @see [[de.sciss.synth.DoneAction]]
 */
object Duty {
   
   /**
    * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
    *                        The next poll is acquired after the previous duration.
    * @param reset           a trigger which resets the dur input (if demand-rated) and the
    *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
    *                        providing a stream of reset times.
    * @param level           a demand-rate ugen providing the output values.
    * @param doneAction      a doneAction that is evaluated when the duration stream ends.
    */
   def kr(dur: GE = 1.0f, reset: GE = 0.0f, level: GE, doneAction: GE = doNothing) = apply(control, dur, reset, level, doneAction)
   /**
    * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
    *                        The next poll is acquired after the previous duration.
    * @param reset           a trigger which resets the dur input (if demand-rated) and the
    *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
    *                        providing a stream of reset times.
    * @param level           a demand-rate ugen providing the output values.
    * @param doneAction      a doneAction that is evaluated when the duration stream ends.
    */
   def ar(dur: GE = 1.0f, reset: GE = 0.0f, level: GE, doneAction: GE = doNothing) = apply(audio, dur, reset, level, doneAction)
}
/**
 * A UGen which polls results from demand-rate ugens in intervals specified by a durational input.
 * A value from the `level` ugen is demanded and output according to a stream
 * of duration values. When there is a trigger at the reset input, the `level`
 * and the `dur` input are reset.
 * 
 * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
 *                        The next poll is acquired after the previous duration.
 * @param reset           a trigger which resets the dur input (if demand-rated) and the
 *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
 *                        providing a stream of reset times.
 * @param level           a demand-rate ugen providing the output values.
 * @param doneAction      a doneAction that is evaluated when the duration stream ends.
 * 
 * @see [[de.sciss.synth.ugen.TDuty]]
 * @see [[de.sciss.synth.ugen.Demand]]
 * @see [[de.sciss.synth.DoneAction]]
 */
final case class Duty(rate: Rate, dur: GE, reset: GE, level: GE, doneAction: GE) extends UGenSource.SingleOut("Duty") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(dur.expand, reset.expand, level.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A UGen which polls results from demand-rate ugens in intervals specified by a durational input,
 * and outputs them as trigger values.
 * A value from the `level` ugen is demanded and output for one sample (when
 * running at audio-rate) or one block (when running at control-rate) according to a stream
 * of duration values. When there is a trigger at the reset input, the `level` and
 * the `dur` input are reset.
 * 
 * @see [[de.sciss.synth.ugen.Duty]]
 * @see [[de.sciss.synth.ugen.Demand]]
 * @see [[de.sciss.synth.DoneAction]]
 */
object TDuty {
   def kr: TDuty = kr()
   /**
    * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
    *                        The next poll is acquired after the previous duration.
    * @param reset           a trigger which resets the dur input (if demand-rated) and the
    *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
    *                        providing a stream of reset times.
    * @param level           a demand-rate ugen providing the output values.
    * @param doneAction      a doneAction that is evaluated when the duration stream ends.
    * @param gapFirst        when 0 (default), the UGen does the first level poll immediately and then
    *                        waits for the first durational value. When this is 1, the UGen initially polls the first
    *                        durational value, waits for that duration, and then polls the first level
    *                        (along with polling the next durational value).
    */
   def kr(dur: GE = 1.0f, reset: GE = 0.0f, level: GE = 1.0f, doneAction: GE = doNothing, gapFirst: GE = 0.0f) = apply(control, dur, reset, level, doneAction, gapFirst)
   def ar: TDuty = ar()
   /**
    * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
    *                        The next poll is acquired after the previous duration.
    * @param reset           a trigger which resets the dur input (if demand-rated) and the
    *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
    *                        providing a stream of reset times.
    * @param level           a demand-rate ugen providing the output values.
    * @param doneAction      a doneAction that is evaluated when the duration stream ends.
    * @param gapFirst        when 0 (default), the UGen does the first level poll immediately and then
    *                        waits for the first durational value. When this is 1, the UGen initially polls the first
    *                        durational value, waits for that duration, and then polls the first level
    *                        (along with polling the next durational value).
    */
   def ar(dur: GE = 1.0f, reset: GE = 0.0f, level: GE = 1.0f, doneAction: GE = doNothing, gapFirst: GE = 0.0f) = apply(audio, dur, reset, level, doneAction, gapFirst)
}
/**
 * A UGen which polls results from demand-rate ugens in intervals specified by a durational input,
 * and outputs them as trigger values.
 * A value from the `level` ugen is demanded and output for one sample (when
 * running at audio-rate) or one block (when running at control-rate) according to a stream
 * of duration values. When there is a trigger at the reset input, the `level` and
 * the `dur` input are reset.
 * 
 * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
 *                        The next poll is acquired after the previous duration.
 * @param reset           a trigger which resets the dur input (if demand-rated) and the
 *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
 *                        providing a stream of reset times.
 * @param level           a demand-rate ugen providing the output values.
 * @param doneAction      a doneAction that is evaluated when the duration stream ends.
 * @param gapFirst        when 0 (default), the UGen does the first level poll immediately and then
 *                        waits for the first durational value. When this is 1, the UGen initially polls the first
 *                        durational value, waits for that duration, and then polls the first level
 *                        (along with polling the next durational value).
 * 
 * @see [[de.sciss.synth.ugen.Duty]]
 * @see [[de.sciss.synth.ugen.Demand]]
 * @see [[de.sciss.synth.DoneAction]]
 */
final case class TDuty(rate: Rate, dur: GE, reset: GE, level: GE, doneAction: GE, gapFirst: GE) extends UGenSource.SingleOut("TDuty") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(dur.expand, reset.expand, level.expand, doneAction.expand, gapFirst.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * An envelope generator UGen using demand-rate inputs for the envelope segments.
 * For each parameter of the envelope (levels, durations and shapes), values are polled
 * every time a new segment starts.
 * 
 * @see [[de.sciss.synth.ugen.EnvGen]]
 * @see [[de.sciss.synth.EnvShape]]
 * @see [[de.sciss.synth.DoneAction]]
 */
object DemandEnvGen {
   
   /**
    * @param levels          demand-rate ugen (or other ugen) returning level values
    * @param durs            demand-rate ugen (or other ugen) returning durational values
    * @param shapes          demand-rate ugen (or other ugen) returning shape number for the envelope segment.
    * @param curvatures      demand-rate ugen (or other ugen) returning curvature values. these are
    *                        used for curveShape segments (shape number 5) and should be zero for other shapes.
    * @param gate            a control rate gate: if gate is x >= 1, the ugen runs.
    *                        if gate is 0 > x > 1, the ugen is released at the next level (according to doneAction).
    *                        if gate is x <= 0, the ugen is sampled end held.
    * @param reset           a trigger signal. a trigger occurs when passing from non-positive to positive.
    *                        when the trigger amplitude is < 1, the input ugens (those that are demand-rated)
    *                        are reset when the current segment ends. if the trigger amplitude is > 1,
    *                        the reset is performed immediately.
    * @param levelScale      demand-rate ugen returning level scaling values
    * @param levelBias       demand-rate ugen returning level offset values
    * @param timeScale       demand-rate ugen returning time scaling values
    * @param doneAction      a done action performed when one of the demand-rated series ends
    */
   def ar(levels: GE, durs: GE, shapes: GE = 1.0f, curvatures: GE = 0.0f, gate: GE = 1.0f, reset: GE = 1.0f, levelScale: GE = 1.0f, levelBias: GE = 0.0f, timeScale: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, levels, durs, shapes, curvatures, gate, reset, levelScale, levelBias, timeScale, doneAction)
}
/**
 * An envelope generator UGen using demand-rate inputs for the envelope segments.
 * For each parameter of the envelope (levels, durations and shapes), values are polled
 * every time a new segment starts.
 * 
 * @param levels          demand-rate ugen (or other ugen) returning level values
 * @param durs            demand-rate ugen (or other ugen) returning durational values
 * @param shapes          demand-rate ugen (or other ugen) returning shape number for the envelope segment.
 * @param curvatures      demand-rate ugen (or other ugen) returning curvature values. these are
 *                        used for curveShape segments (shape number 5) and should be zero for other shapes.
 * @param gate            a control rate gate: if gate is x >= 1, the ugen runs.
 *                        if gate is 0 > x > 1, the ugen is released at the next level (according to doneAction).
 *                        if gate is x <= 0, the ugen is sampled end held.
 * @param reset           a trigger signal. a trigger occurs when passing from non-positive to positive.
 *                        when the trigger amplitude is < 1, the input ugens (those that are demand-rated)
 *                        are reset when the current segment ends. if the trigger amplitude is > 1,
 *                        the reset is performed immediately.
 * @param levelScale      demand-rate ugen returning level scaling values
 * @param levelBias       demand-rate ugen returning level offset values
 * @param timeScale       demand-rate ugen returning time scaling values
 * @param doneAction      a done action performed when one of the demand-rated series ends
 * 
 * @see [[de.sciss.synth.ugen.EnvGen]]
 * @see [[de.sciss.synth.EnvShape]]
 * @see [[de.sciss.synth.DoneAction]]
 */
final case class DemandEnvGen(rate: Rate, levels: GE, durs: GE, shapes: GE, curvatures: GE, gate: GE, reset: GE, levelScale: GE, levelBias: GE, timeScale: GE, doneAction: GE) extends UGenSource.SingleOut("DemandEnvGen") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(levels.expand, durs.expand, shapes.expand, curvatures.expand, gate.expand, reset.expand, levelScale.expand, levelBias.expand, timeScale.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A demand-rate UGen which produces an arithmetic (linear) series.
 * 
 * The arguments can be constant or any other ugens.
 * 
 * @param start           the start value of the series
 * @param step            the incremental step by which the series changes. the step is
 *                        added to the previous value on each demand.
 * @param length          the number of elements to produces (maybe be infinite)
 * 
 * @see [[de.sciss.synth.ugen.Dgeom]]
 * @see [[de.sciss.synth.ugen.Dseq]]
 */
final case class Dseries(start: GE = 0.0f, step: GE = 1.0f, length: GE = inf) extends UGenSource.SingleOut("Dseries") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, step.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dgeom(start: GE = 1.0f, grow: GE = 2.0f, length: GE = inf) extends UGenSource.SingleOut("Dgeom") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, grow.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dwhite(lo: GE = 0.0f, hi: GE = 1.0f, length: GE = inf) extends UGenSource.SingleOut("Dwhite") with DemandRated with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dbrown(lo: GE = 0.0f, hi: GE = 1.0f, step: GE = 0.01f, length: GE = inf) extends UGenSource.SingleOut("Dbrown") with DemandRated with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, step.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Diwhite(lo: GE = 0.0f, hi: GE = 1.0f, length: GE = inf) extends UGenSource.SingleOut("Diwhite") with DemandRated with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dseq(seq: GE, repeats: GE = 1.0f) extends UGenSource.SingleOut("Dseq") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dser(seq: GE, repeats: GE = 1.0f) extends UGenSource.SingleOut("Dser") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
/**
 * A demand-rate UGen that reads out a buffer. All inputs can be either demand ugen or any other ugen.
 * 
 * @param buf             the identifier of the buffer to read out
 * @param index           the frame index into the buffer
 * @param loop            whether to wrap an exceeding phase around the buffer length (1) or not (0)
 * 
 * @see [[de.sciss.synth.ugen.BufRd]]
 * @see [[de.sciss.synth.ugen.Dbufwr]]
 */
final case class Dbufrd(buf: GE, index: GE = 0.0f, loop: GE = 1.0f) extends UGenSource.SingleOut("Dbufrd") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, index.expand, loop.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dbufwr(in: GE, buf: GE, index: GE = 0.0f, loop: GE = 1.0f) extends UGenSource.SingleOut("Dbufwr") with DemandRated with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, buf.expand, index.expand, loop.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Drand(seq: GE, repeats: GE = 1.0f) extends UGenSource.SingleOut("Drand") with DemandRated with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dxrand(seq: GE, repeats: GE = 1.0f) extends UGenSource.SingleOut("Dxrand") with DemandRated with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dshuf(seq: GE, repeats: GE = 1.0f) extends UGenSource.SingleOut("Dshuf") with DemandRated with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dswitch1(seq: GE, index: GE) extends UGenSource.SingleOut("Dswitch1") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(index.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dswitch(seq: GE, index: GE) extends UGenSource.SingleOut("Dswitch") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(index.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dstutter(n: GE, in: GE) extends UGenSource.SingleOut("Dstutter") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(n.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Donce(in: GE) extends UGenSource.SingleOut("Donce") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
final case class Dreset(in: GE, reset: GE) extends UGenSource.SingleOut("Dreset") with DemandRated with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}
/**
 * A demand rate UGen printing the current output value of its input to the console when polled.
 * 
 * @param in              the signal you want to poll
 * @param label           a string or symbol to be printed with the polled value
 * @param run             if 1 the polling is active, if 0 it is inactive.
 * 
 * @see [[de.sciss.synth.ugen.SendTrig]]
 * @see [[de.sciss.synth.ugen.Poll]]
 */
final case class Dpoll(rate: Rate, in: GE, label: String, run: GE, trigID: GE) extends UGenSource.SingleOut("Dpoll") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, trigID.expand, run.expand).++(stringArg(label)))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}