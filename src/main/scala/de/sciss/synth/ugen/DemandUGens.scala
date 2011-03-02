/*
 * DemandUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import util.UGenHelper._
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
   def kr(trig: AnyGE, in: Multi[AnyGE], reset: AnyGE = 0.0f) = apply[control](control, trig, in, reset)
   /**
    * @param trig            trigger. Can be any signal. A trigger happens when the signal changes from non-positive to positive.
    * @param in              a demand-rate signal (possibly multi-channel) which is read at each trigger
    * @param reset           trigger. Resets the list of ugens (`multi`) when triggered.
    */
   def ar(trig: AnyGE, in: Multi[AnyGE], reset: AnyGE = 0.0f) = apply[audio](audio, trig, in, reset)
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
final case class Demand[R <: Rate](rate: R, trig: AnyGE, in: Multi[AnyGE], reset: AnyGE) extends MultiOutUGenSource[R] {
   protected def expandUGens = {
      val _trig = trig.expand
      val _reset = reset.expand
      val _in = in.mexpand
      val _sz_trig = _trig.size
      val _sz_reset = _reset.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_trig, _sz_reset, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => {
         val _ch_in = _in(i.%(_sz_in)).expand
         new MultiOutUGen("Demand", rate, IIdxSeq.fill(_ch_in.size)(rate), IIdxSeq(_trig(i.%(_sz_trig)), _reset(i.%(_sz_reset))).++(_ch_in))
      })
   }
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
final case class Duty[R <: Rate](rate: R, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _dur = dur.expand
      val _reset = reset.expand
      val _level = level.expand
      val _doneAction = doneAction.expand
      val _sz_dur = _dur.size
      val _sz_reset = _reset.size
      val _sz_level = _level.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_dur, _sz_reset, _sz_level, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Duty", rate, IIdxSeq(_dur(i.%(_sz_dur)), _reset(i.%(_sz_reset)), _level(i.%(_sz_level)), _doneAction(i.%(_sz_doneAction)))))
   }
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
final case class TDuty[R <: Rate](rate: R, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE, gapFirst: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _dur = dur.expand
      val _reset = reset.expand
      val _level = level.expand
      val _doneAction = doneAction.expand
      val _gapFirst = gapFirst.expand
      val _sz_dur = _dur.size
      val _sz_reset = _reset.size
      val _sz_level = _level.size
      val _sz_doneAction = _doneAction.size
      val _sz_gapFirst = _gapFirst.size
      val _exp_ = maxInt(_sz_dur, _sz_reset, _sz_level, _sz_doneAction, _sz_gapFirst)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TDuty", rate, IIdxSeq(_dur(i.%(_sz_dur)), _reset(i.%(_sz_reset)), _level(i.%(_sz_level)), _doneAction(i.%(_sz_doneAction)), _gapFirst(i.%(_sz_gapFirst)))))
   }
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
final case class DemandEnvGen[R <: Rate](rate: R, levels: AnyGE, durs: AnyGE, shapes: AnyGE, curvatures: AnyGE, gate: AnyGE, reset: AnyGE, levelScale: AnyGE, levelBias: AnyGE, timeScale: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _levels = levels.expand
      val _durs = durs.expand
      val _shapes = shapes.expand
      val _curvatures = curvatures.expand
      val _gate = gate.expand
      val _reset = reset.expand
      val _levelScale = levelScale.expand
      val _levelBias = levelBias.expand
      val _timeScale = timeScale.expand
      val _doneAction = doneAction.expand
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
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DemandEnvGen", rate, IIdxSeq(_levels(i.%(_sz_levels)), _durs(i.%(_sz_durs)), _shapes(i.%(_sz_shapes)), _curvatures(i.%(_sz_curvatures)), _gate(i.%(_sz_gate)), _reset(i.%(_sz_reset)), _levelScale(i.%(_sz_levelScale)), _levelBias(i.%(_sz_levelBias)), _timeScale(i.%(_sz_timeScale)), _doneAction(i.%(_sz_doneAction)))))
   }
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
final case class Dseries(start: AnyGE = 0.0f, step: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _start = start.expand
      val _step = step.expand
      val _length = length.expand
      val _sz_start = _start.size
      val _sz_step = _step.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_start, _sz_step, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dseries", demand, IIdxSeq(_start(i.%(_sz_start)), _step(i.%(_sz_step)), _length(i.%(_sz_length)))))
   }
}
final case class Dgeom(start: AnyGE = 1.0f, grow: AnyGE = 2.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _start = start.expand
      val _grow = grow.expand
      val _length = length.expand
      val _sz_start = _start.size
      val _sz_grow = _grow.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_start, _sz_grow, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dgeom", demand, IIdxSeq(_start(i.%(_sz_start)), _grow(i.%(_sz_grow)), _length(i.%(_sz_length)))))
   }
}
final case class Dwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _length = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dwhite", demand, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _length(i.%(_sz_length)))))
   }
}
final case class Dbrown(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, step: AnyGE = 0.01f, length: AnyGE = inf) extends SingleOutUGenSource[demand] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _step = step.expand
      val _length = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_step = _step.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_step, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dbrown", demand, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _step(i.%(_sz_step)), _length(i.%(_sz_length)))))
   }
}
final case class Diwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _length = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Diwhite", demand, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _length(i.%(_sz_length)))))
   }
}
final case class Dseq(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _repeats = repeats.expand
      val _seq = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dseq", demand, IIdxSeq(_repeats(i.%(_sz_repeats))).++(_seq(i.%(_sz_seq)).expand)))
   }
}
final case class Dser(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _repeats = repeats.expand
      val _seq = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dser", demand, IIdxSeq(_repeats(i.%(_sz_repeats))).++(_seq(i.%(_sz_seq)).expand)))
   }
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
final case class Dbufrd(buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _buf = buf.expand
      val _index = index.expand
      val _loop = loop.expand
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_buf, _sz_index, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dbufrd", demand, IIdxSeq(_buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop)))))
   }
}
final case class Dbufwr(in: AnyGE, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends SingleOutUGenSource[demand] with WritesBuffer {
   protected def expandUGens = {
      val _in = in.expand
      val _buf = buf.expand
      val _index = index.expand
      val _loop = loop.expand
      val _sz_in = _in.size
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_in, _sz_buf, _sz_index, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dbufwr", demand, IIdxSeq(_in(i.%(_sz_in)), _buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop)))))
   }
}
final case class Drand(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand] with UsesRandSeed {
   protected def expandUGens = {
      val _repeats = repeats.expand
      val _seq = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Drand", demand, IIdxSeq(_repeats(i.%(_sz_repeats))).++(_seq(i.%(_sz_seq)).expand)))
   }
}
final case class Dxrand(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand] with UsesRandSeed {
   protected def expandUGens = {
      val _repeats = repeats.expand
      val _seq = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dxrand", demand, IIdxSeq(_repeats(i.%(_sz_repeats))).++(_seq(i.%(_sz_seq)).expand)))
   }
}
final case class Dshuf(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand] with UsesRandSeed {
   protected def expandUGens = {
      val _repeats = repeats.expand
      val _seq = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dshuf", demand, IIdxSeq(_repeats(i.%(_sz_repeats))).++(_seq(i.%(_sz_seq)).expand)))
   }
}
final case class Dswitch1(seq: Multi[AnyGE], index: AnyGE) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _index = index.expand
      val _seq = seq.mexpand
      val _sz_index = _index.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_index, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dswitch1", demand, IIdxSeq(_index(i.%(_sz_index))).++(_seq(i.%(_sz_seq)).expand)))
   }
}
final case class Dswitch(seq: Multi[AnyGE], index: AnyGE) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _index = index.expand
      val _seq = seq.mexpand
      val _sz_index = _index.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_index, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dswitch", demand, IIdxSeq(_index(i.%(_sz_index))).++(_seq(i.%(_sz_seq)).expand)))
   }
}
final case class Dstutter(n: AnyGE, in: AnyGE) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _n = n.expand
      val _in = in.expand
      val _sz_n = _n.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_n, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dstutter", demand, IIdxSeq(_n(i.%(_sz_n)), _in(i.%(_sz_in)))))
   }
}
final case class Donce(in: AnyGE) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("Donce", demand, IIdxSeq(_in(i))))
   }
}
final case class Dreset(in: AnyGE, reset: AnyGE) extends SingleOutUGenSource[demand] with IsIndividual {
   protected def expandUGens = {
      val _in = in.expand
      val _reset = reset.expand
      val _sz_in = _in.size
      val _sz_reset = _reset.size
      val _exp_ = maxInt(_sz_in, _sz_reset)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Dreset", demand, IIdxSeq(_in(i.%(_sz_in)), _reset(i.%(_sz_reset)))))
   }
}