/*
 * DemandUGens.scala
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
def kr(trig: AnyGE, in: Multi[AnyGE], reset: AnyGE = 0.0f) = apply(control, trig, in, reset)
/**
 * @param trig            trigger. Can be any signal. A trigger happens when the signal changes from non-positive to positive.
 * @param in              a demand-rate signal (possibly multi-channel) which is read at each trigger
 * @param reset           trigger. Resets the list of ugens (`multi`) when triggered.
 */
def ar(trig: AnyGE, in: Multi[AnyGE], reset: AnyGE = 0.0f) = apply(audio, trig, in, reset)
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
 * @param trig            trigger. Can be any signal. A trigger happens when the signal changes from non-positive to positive.
 * @param in              a demand-rate signal (possibly multi-channel) which is read at each trigger
 * @param reset           trigger. Resets the list of ugens (`multi`) when triggered.
 * 
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
case class Demand(rate: Rate, trig: AnyGE, in: Multi[AnyGE], reset: AnyGE) extends MultiOutUGenSource[DemandUGen] {
   protected def expandUGens = {
      val _trig: IIdxSeq[UGenIn] = trig.expand
      val _reset: IIdxSeq[UGenIn] = reset.expand
      val _in: IIdxSeq[AnyGE] = in.mexpand
      val _sz_trig = _trig.size
      val _sz_reset = _reset.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_trig, _sz_reset, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DemandUGen(rate, _trig(i.%(_sz_trig)), _in(i.%(_sz_in)).expand, _reset(i.%(_sz_reset))))
   }
}
case class DemandUGen(rate: Rate, trig: UGenIn, in: IIdxSeq[UGenIn], reset: UGenIn) extends MultiOutUGen(IIdxSeq.fill(in.size)(rate), IIdxSeq[UGenIn](trig, reset).++(in))
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
def kr(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE, doneAction: AnyGE = doNothing) = apply(control, dur, reset, level, doneAction)
/**
 * @param dur             the provider of time values. Can be a demand-rate ugen or any signal.
 *                        The next poll is acquired after the previous duration.
 * @param reset           a trigger which resets the dur input (if demand-rated) and the
 *                        the level input ugen. The reset input may also be a demand-rate ugen, in this case
 *                        providing a stream of reset times.
 * @param level           a demand-rate ugen providing the output values.
 * @param doneAction      a doneAction that is evaluated when the duration stream ends.
 */
def ar(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE, doneAction: AnyGE = doNothing) = apply(audio, dur, reset, level, doneAction)
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
case class Duty(rate: Rate, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[DutyUGen] {
   protected def expandUGens = {
      val _dur: IIdxSeq[UGenIn] = dur.expand
      val _reset: IIdxSeq[UGenIn] = reset.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
      val _sz_dur = _dur.size
      val _sz_reset = _reset.size
      val _sz_level = _level.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_dur, _sz_reset, _sz_level, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => DutyUGen(rate, _dur(i.%(_sz_dur)), _reset(i.%(_sz_reset)), _level(i.%(_sz_level)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class DutyUGen(rate: Rate, dur: UGenIn, reset: UGenIn, level: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq(dur, reset, level, doneAction))
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
def kr(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE = 1.0f, doneAction: AnyGE = doNothing, gapFirst: AnyGE = 0.0f) = apply(control, dur, reset, level, doneAction, gapFirst)
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
def ar(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE = 1.0f, doneAction: AnyGE = doNothing, gapFirst: AnyGE = 0.0f) = apply(audio, dur, reset, level, doneAction, gapFirst)
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
case class TDuty(rate: Rate, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE, gapFirst: AnyGE) extends SingleOutUGenSource[TDutyUGen] {
   protected def expandUGens = {
      val _dur: IIdxSeq[UGenIn] = dur.expand
      val _reset: IIdxSeq[UGenIn] = reset.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
      val _gapFirst: IIdxSeq[UGenIn] = gapFirst.expand
      val _sz_dur = _dur.size
      val _sz_reset = _reset.size
      val _sz_level = _level.size
      val _sz_doneAction = _doneAction.size
      val _sz_gapFirst = _gapFirst.size
      val _exp_ = maxInt(_sz_dur, _sz_reset, _sz_level, _sz_doneAction, _sz_gapFirst)
      IIdxSeq.tabulate(_exp_)(i => TDutyUGen(rate, _dur(i.%(_sz_dur)), _reset(i.%(_sz_reset)), _level(i.%(_sz_level)), _doneAction(i.%(_sz_doneAction)), _gapFirst(i.%(_sz_gapFirst))))
   }
}
case class TDutyUGen(rate: Rate, dur: UGenIn, reset: UGenIn, level: UGenIn, doneAction: UGenIn, gapFirst: UGenIn) extends SingleOutUGen(IIdxSeq(dur, reset, level, doneAction, gapFirst))
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
def ar(levels: AnyGE, durs: AnyGE, shapes: AnyGE = 1.0f, curvatures: AnyGE = 0.0f, gate: AnyGE = 1.0f, reset: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(audio, levels, durs, shapes, curvatures, gate, reset, levelScale, levelBias, timeScale, doneAction)
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
case class DemandEnvGen(rate: Rate, levels: AnyGE, durs: AnyGE, shapes: AnyGE, curvatures: AnyGE, gate: AnyGE, reset: AnyGE, levelScale: AnyGE, levelBias: AnyGE, timeScale: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[DemandEnvGenUGen] {
   protected def expandUGens = {
      val _levels: IIdxSeq[UGenIn] = levels.expand
      val _durs: IIdxSeq[UGenIn] = durs.expand
      val _shapes: IIdxSeq[UGenIn] = shapes.expand
      val _curvatures: IIdxSeq[UGenIn] = curvatures.expand
      val _gate: IIdxSeq[UGenIn] = gate.expand
      val _reset: IIdxSeq[UGenIn] = reset.expand
      val _levelScale: IIdxSeq[UGenIn] = levelScale.expand
      val _levelBias: IIdxSeq[UGenIn] = levelBias.expand
      val _timeScale: IIdxSeq[UGenIn] = timeScale.expand
      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
      val _sz_levels = _levels.size
      val _sz_durs = _durs.size
      val _sz_shapes = _shapes.size
      val _sz_curvatures = _curvatures.size
      val _sz_gate = _gate.size
      val _sz_reset = _reset.size
      val _sz_levelScale = _levelScale.size
      val _sz_levelBias = _levelBias.size
      val _sz_timeScale = _timeScale.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_levels, _sz_durs, _sz_shapes, _sz_curvatures, _sz_gate, _sz_reset, _sz_levelScale, _sz_levelBias, _sz_timeScale, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => DemandEnvGenUGen(rate, _levels(i.%(_sz_levels)), _durs(i.%(_sz_durs)), _shapes(i.%(_sz_shapes)), _curvatures(i.%(_sz_curvatures)), _gate(i.%(_sz_gate)), _reset(i.%(_sz_reset)), _levelScale(i.%(_sz_levelScale)), _levelBias(i.%(_sz_levelBias)), _timeScale(i.%(_sz_timeScale)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class DemandEnvGenUGen(rate: Rate, levels: UGenIn, durs: UGenIn, shapes: UGenIn, curvatures: UGenIn, gate: UGenIn, reset: UGenIn, levelScale: UGenIn, levelBias: UGenIn, timeScale: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq(levels, durs, shapes, curvatures, gate, reset, levelScale, levelBias, timeScale, doneAction))
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
case class Dseries(start: AnyGE = 0.0f, step: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[DseriesUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _start: IIdxSeq[UGenIn] = start.expand
      val _step: IIdxSeq[UGenIn] = step.expand
      val _length: IIdxSeq[UGenIn] = length.expand
      val _sz_start = _start.size
      val _sz_step = _step.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_start, _sz_step, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DseriesUGen(_start(i.%(_sz_start)), _step(i.%(_sz_step)), _length(i.%(_sz_length))))
   }
}
case class DseriesUGen(start: UGenIn, step: UGenIn, length: UGenIn) extends SingleOutUGen(IIdxSeq(start, step, length)) with DemandRated with IsIndividual
case class Dgeom(start: AnyGE = 1.0f, grow: AnyGE = 2.0f, length: AnyGE = inf) extends SingleOutUGenSource[DgeomUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _start: IIdxSeq[UGenIn] = start.expand
      val _grow: IIdxSeq[UGenIn] = grow.expand
      val _length: IIdxSeq[UGenIn] = length.expand
      val _sz_start = _start.size
      val _sz_grow = _grow.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_start, _sz_grow, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DgeomUGen(_start(i.%(_sz_start)), _grow(i.%(_sz_grow)), _length(i.%(_sz_length))))
   }
}
case class DgeomUGen(start: UGenIn, grow: UGenIn, length: UGenIn) extends SingleOutUGen(IIdxSeq(start, grow, length)) with DemandRated with IsIndividual
case class Dwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[DwhiteUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _length: IIdxSeq[UGenIn] = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DwhiteUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _length(i.%(_sz_length))))
   }
}
case class DwhiteUGen(lo: UGenIn, hi: UGenIn, length: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, length)) with DemandRated with UsesRandSeed
case class Dbrown(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, step: AnyGE = 0.01f, length: AnyGE = inf) extends SingleOutUGenSource[DbrownUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _step: IIdxSeq[UGenIn] = step.expand
      val _length: IIdxSeq[UGenIn] = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_step = _step.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_step, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DbrownUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _step(i.%(_sz_step)), _length(i.%(_sz_length))))
   }
}
case class DbrownUGen(lo: UGenIn, hi: UGenIn, step: UGenIn, length: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, step, length)) with DemandRated with UsesRandSeed
case class Diwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[DiwhiteUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _length: IIdxSeq[UGenIn] = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DiwhiteUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _length(i.%(_sz_length))))
   }
}
case class DiwhiteUGen(lo: UGenIn, hi: UGenIn, length: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, length)) with DemandRated with UsesRandSeed
case class Dseq(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[DseqUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _repeats: IIdxSeq[UGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DseqUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DseqUGen(seq: IIdxSeq[UGenIn], repeats: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](repeats).++(seq)) with DemandRated with IsIndividual
case class Dser(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[DserUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _repeats: IIdxSeq[UGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DserUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DserUGen(seq: IIdxSeq[UGenIn], repeats: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](repeats).++(seq)) with DemandRated with IsIndividual
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
case class Dbufrd(buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends SingleOutUGenSource[DbufrdUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _buf: IIdxSeq[UGenIn] = buf.expand
      val _index: IIdxSeq[UGenIn] = index.expand
      val _loop: IIdxSeq[UGenIn] = loop.expand
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_buf, _sz_index, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => DbufrdUGen(_buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop))))
   }
}
case class DbufrdUGen(buf: UGenIn, index: UGenIn, loop: UGenIn) extends SingleOutUGen(IIdxSeq(buf, index, loop)) with DemandRated with IsIndividual
case class Dbufwr(in: AnyGE, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends SingleOutUGenSource[DbufwrUGen] with DemandRated with WritesBuffer {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _buf: IIdxSeq[UGenIn] = buf.expand
      val _index: IIdxSeq[UGenIn] = index.expand
      val _loop: IIdxSeq[UGenIn] = loop.expand
      val _sz_in = _in.size
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_in, _sz_buf, _sz_index, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => DbufwrUGen(_in(i.%(_sz_in)), _buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop))))
   }
}
case class DbufwrUGen(in: UGenIn, buf: UGenIn, index: UGenIn, loop: UGenIn) extends SingleOutUGen(IIdxSeq(in, buf, index, loop)) with DemandRated with WritesBuffer
case class Drand(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[DrandUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _repeats: IIdxSeq[UGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DrandUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DrandUGen(seq: IIdxSeq[UGenIn], repeats: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](repeats).++(seq)) with DemandRated with UsesRandSeed
case class Dxrand(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[DxrandUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _repeats: IIdxSeq[UGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DxrandUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DxrandUGen(seq: IIdxSeq[UGenIn], repeats: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](repeats).++(seq)) with DemandRated with UsesRandSeed
case class Dshuf(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[DshufUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _repeats: IIdxSeq[UGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DshufUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DshufUGen(seq: IIdxSeq[UGenIn], repeats: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](repeats).++(seq)) with DemandRated with UsesRandSeed
case class Dswitch1(seq: Multi[AnyGE], index: AnyGE) extends SingleOutUGenSource[Dswitch1UGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _index: IIdxSeq[UGenIn] = index.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_index = _index.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_index, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => Dswitch1UGen(_seq(i.%(_sz_seq)).expand, _index(i.%(_sz_index))))
   }
}
case class Dswitch1UGen(seq: IIdxSeq[UGenIn], index: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](index).++(seq)) with DemandRated with IsIndividual
case class Dswitch(seq: Multi[AnyGE], index: AnyGE) extends SingleOutUGenSource[DswitchUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _index: IIdxSeq[UGenIn] = index.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_index = _index.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_index, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DswitchUGen(_seq(i.%(_sz_seq)).expand, _index(i.%(_sz_index))))
   }
}
case class DswitchUGen(seq: IIdxSeq[UGenIn], index: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](index).++(seq)) with DemandRated with IsIndividual
case class Dstutter(n: AnyGE, in: AnyGE) extends SingleOutUGenSource[DstutterUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _n: IIdxSeq[UGenIn] = n.expand
      val _in: IIdxSeq[UGenIn] = in.expand
      val _sz_n = _n.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_n, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DstutterUGen(_n(i.%(_sz_n)), _in(i.%(_sz_in))))
   }
}
case class DstutterUGen(n: UGenIn, in: UGenIn) extends SingleOutUGen(IIdxSeq(n, in)) with DemandRated with IsIndividual
case class Donce(in: AnyGE) extends SingleOutUGenSource[DonceUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => DonceUGen(_in(i)))
   }
}
case class DonceUGen(in: UGenIn) extends SingleOutUGen(IIdxSeq(in)) with DemandRated with IsIndividual
case class Dreset(in: AnyGE, reset: AnyGE) extends SingleOutUGenSource[DresetUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _reset: IIdxSeq[UGenIn] = reset.expand
      val _sz_in = _in.size
      val _sz_reset = _reset.size
      val _exp_ = maxInt(_sz_in, _sz_reset)
      IIdxSeq.tabulate(_exp_)(i => DresetUGen(_in(i.%(_sz_in)), _reset(i.%(_sz_reset))))
   }
}
case class DresetUGen(in: UGenIn, reset: UGenIn) extends SingleOutUGen(IIdxSeq(in, reset)) with DemandRated with IsIndividual