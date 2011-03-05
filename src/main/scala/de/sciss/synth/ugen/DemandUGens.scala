/*
 * DemandUGens.scala
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
   def kr(trig: AnyGE, in: AnyGE, reset: AnyGE = 0.0f) = apply[control](control, trig, in, reset)
   /**
    * @param trig            trigger. Can be any signal. A trigger happens when the signal changes from non-positive to positive.
    * @param in              a demand-rate signal (possibly multi-channel) which is read at each trigger
    * @param reset           trigger. Resets the list of ugens (`multi`) when triggered.
    */
   def ar(trig: AnyGE, in: AnyGE, reset: AnyGE = 0.0f) = apply[audio](audio, trig, in, reset)
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
final case class Demand[R <: Rate](rate: R, trig: AnyGE, in: AnyGE, reset: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, reset.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Demand", rate, IIdxSeq.fill(_args.size.-(2))(rate), _args)
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
   def kr(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE, doneAction: AnyGE = doNothing) = apply[control](control, dur, reset, level, doneAction)
   /**
    * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
    *                        The next poll is acquired after the previous duration.
    * @param reset           a trigger which resets the dur input (if demand-rated) and the
    *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
    *                        providing a stream of reset times.
    * @param level           a demand-rate ugen providing the output values.
    * @param doneAction      a doneAction that is evaluated when the duration stream ends.
    */
   def ar(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE, doneAction: AnyGE = doNothing) = apply[audio](audio, dur, reset, level, doneAction)
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
final case class Duty[R <: Rate](rate: R, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(dur.expand, reset.expand, level.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Duty", rate, _args)
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
   def kr: TDuty[control] = kr()
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
   def kr(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE = 1.0f, doneAction: AnyGE = doNothing, gapFirst: AnyGE = 0.0f) = apply[control](control, dur, reset, level, doneAction, gapFirst)
   def ar: TDuty[audio] = ar()
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
   def ar(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE = 1.0f, doneAction: AnyGE = doNothing, gapFirst: AnyGE = 0.0f) = apply[audio](audio, dur, reset, level, doneAction, gapFirst)
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
final case class TDuty[R <: Rate](rate: R, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE, gapFirst: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(dur.expand, reset.expand, level.expand, doneAction.expand, gapFirst.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TDuty", rate, _args)
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
   def ar(levels: AnyGE, durs: AnyGE, shapes: AnyGE = 1.0f, curvatures: AnyGE = 0.0f, gate: AnyGE = 1.0f, reset: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, levels, durs, shapes, curvatures, gate, reset, levelScale, levelBias, timeScale, doneAction)
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
final case class DemandEnvGen[R <: Rate](rate: R, levels: AnyGE, durs: AnyGE, shapes: AnyGE, curvatures: AnyGE, gate: AnyGE, reset: AnyGE, levelScale: AnyGE, levelBias: AnyGE, timeScale: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(levels.expand, durs.expand, shapes.expand, curvatures.expand, gate.expand, reset.expand, levelScale.expand, levelBias.expand, timeScale.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("DemandEnvGen", rate, _args)
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
final case class Dseries(start: AnyGE = 0.0f, step: AnyGE = 1.0f, length: AnyGE = inf) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, step.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dseries", demand, _args)
}
final case class Dgeom(start: AnyGE = 1.0f, grow: AnyGE = 2.0f, length: AnyGE = inf) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, grow.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dgeom", demand, _args)
}
final case class Dwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends UGenSource.SingleOut[demand] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dwhite", demand, _args)
}
final case class Dbrown(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, step: AnyGE = 0.01f, length: AnyGE = inf) extends UGenSource.SingleOut[demand] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, step.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dbrown", demand, _args)
}
final case class Diwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends UGenSource.SingleOut[demand] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, length.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Diwhite", demand, _args)
}
final case class Dseq(seq: AnyGE, repeats: AnyGE = 1.0f) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dseq", demand, _args)
}
final case class Dser(seq: AnyGE, repeats: AnyGE = 1.0f) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dser", demand, _args)
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
final case class Dbufrd(buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, index.expand, loop.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dbufrd", demand, _args)
}
final case class Dbufwr(in: AnyGE, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends UGenSource.SingleOut[demand] with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, buf.expand, index.expand, loop.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dbufwr", demand, _args)
}
final case class Drand(seq: AnyGE, repeats: AnyGE = 1.0f) extends UGenSource.SingleOut[demand] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Drand", demand, _args)
}
final case class Dxrand(seq: AnyGE, repeats: AnyGE = 1.0f) extends UGenSource.SingleOut[demand] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dxrand", demand, _args)
}
final case class Dshuf(seq: AnyGE, repeats: AnyGE = 1.0f) extends UGenSource.SingleOut[demand] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(repeats.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dshuf", demand, _args)
}
final case class Dswitch1(seq: AnyGE, index: AnyGE) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(index.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dswitch1", demand, _args)
}
final case class Dswitch(seq: AnyGE, index: AnyGE) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(index.expand).++(seq.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dswitch", demand, _args)
}
final case class Dstutter(n: AnyGE, in: AnyGE) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(n.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dstutter", demand, _args)
}
final case class Donce(in: AnyGE) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Donce", demand, _args)
}
final case class Dreset(in: AnyGE, reset: AnyGE) extends UGenSource.SingleOut[demand] with IsIndividual {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, reset.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dreset", demand, _args)
}