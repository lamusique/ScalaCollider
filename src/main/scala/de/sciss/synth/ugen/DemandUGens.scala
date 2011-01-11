/*
 * DemandUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Sun Jan 09 18:10:58 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
import Float.{PositiveInfinity => inf}
object Demand {
   def kr(trig: AnyGE, in: Multi[AnyGE], reset: AnyGE = 0.0f) = apply[control](control, trig, in, reset)
   def ar(trig: AnyGE, in: Multi[AnyGE], reset: AnyGE = 0.0f) = apply[audio](audio, trig, in, reset)
}
case class Demand[R <: Rate](rate: R, trig: AnyGE, in: Multi[AnyGE], reset: AnyGE) extends MultiOutUGenSource[R, DemandUGen[R]] {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _in: IIdxSeq[AnyGE] = in.mexpand
      val _sz_trig = _trig.size
      val _sz_reset = _reset.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_trig, _sz_reset, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DemandUGen(rate, _trig(i.%(_sz_trig)), _in(i.%(_sz_in)).expand, _reset(i.%(_sz_reset))))
   }
}
case class DemandUGen[R <: Rate](rate: R, trig: AnyUGenIn, in: IIdxSeq[AnyUGenIn], reset: AnyUGenIn) extends MultiOutUGen[R](IIdxSeq.fill(in.size)(rate), IIdxSeq[AnyUGenIn](trig, reset).++(in))
object Duty {
   def kr(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE, doneAction: AnyGE = doNothing) = apply[control](control, dur, reset, level, doneAction)
   def ar(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE, doneAction: AnyGE = doNothing) = apply[audio](audio, dur, reset, level, doneAction)
}
case class Duty[R <: Rate](rate: R, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, DutyUGen[R]] {
   protected def expandUGens = {
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _sz_dur = _dur.size
      val _sz_reset = _reset.size
      val _sz_level = _level.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_dur, _sz_reset, _sz_level, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => DutyUGen(rate, _dur(i.%(_sz_dur)), _reset(i.%(_sz_reset)), _level(i.%(_sz_level)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class DutyUGen[R <: Rate](rate: R, dur: AnyUGenIn, reset: AnyUGenIn, level: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(dur, reset, level, doneAction))
object TDuty {
   def kr: TDuty[control] = kr()
   def kr(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE = 1.0f, doneAction: AnyGE = doNothing, gapFirst: AnyGE = 0.0f) = apply[control](control, dur, reset, level, doneAction, gapFirst)
   def ar: TDuty[audio] = ar()
   def ar(dur: AnyGE = 1.0f, reset: AnyGE = 0.0f, level: AnyGE = 1.0f, doneAction: AnyGE = doNothing, gapFirst: AnyGE = 0.0f) = apply[audio](audio, dur, reset, level, doneAction, gapFirst)
}
case class TDuty[R <: Rate](rate: R, dur: AnyGE, reset: AnyGE, level: AnyGE, doneAction: AnyGE, gapFirst: AnyGE) extends SingleOutUGenSource[R, TDutyUGen[R]] {
   protected def expandUGens = {
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _gapFirst: IIdxSeq[AnyUGenIn] = gapFirst.expand
      val _sz_dur = _dur.size
      val _sz_reset = _reset.size
      val _sz_level = _level.size
      val _sz_doneAction = _doneAction.size
      val _sz_gapFirst = _gapFirst.size
      val _exp_ = maxInt(_sz_dur, _sz_reset, _sz_level, _sz_doneAction, _sz_gapFirst)
      IIdxSeq.tabulate(_exp_)(i => TDutyUGen(rate, _dur(i.%(_sz_dur)), _reset(i.%(_sz_reset)), _level(i.%(_sz_level)), _doneAction(i.%(_sz_doneAction)), _gapFirst(i.%(_sz_gapFirst))))
   }
}
case class TDutyUGen[R <: Rate](rate: R, dur: AnyUGenIn, reset: AnyUGenIn, level: AnyUGenIn, doneAction: AnyUGenIn, gapFirst: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(dur, reset, level, doneAction, gapFirst))
object DemandEnvGen {
   def ar(levels: AnyGE, durs: AnyGE, shapes: AnyGE = 1.0f, curvatures: AnyGE = 0.0f, gate: AnyGE = 1.0f, reset: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, levels, durs, shapes, curvatures, gate, reset, levelScale, levelBias, timeScale, doneAction)
}
case class DemandEnvGen[R <: Rate](rate: R, levels: AnyGE, durs: AnyGE, shapes: AnyGE, curvatures: AnyGE, gate: AnyGE, reset: AnyGE, levelScale: AnyGE, levelBias: AnyGE, timeScale: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, DemandEnvGenUGen[R]] {
   protected def expandUGens = {
      val _levels: IIdxSeq[AnyUGenIn] = levels.expand
      val _durs: IIdxSeq[AnyUGenIn] = durs.expand
      val _shapes: IIdxSeq[AnyUGenIn] = shapes.expand
      val _curvatures: IIdxSeq[AnyUGenIn] = curvatures.expand
      val _gate: IIdxSeq[AnyUGenIn] = gate.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _levelScale: IIdxSeq[AnyUGenIn] = levelScale.expand
      val _levelBias: IIdxSeq[AnyUGenIn] = levelBias.expand
      val _timeScale: IIdxSeq[AnyUGenIn] = timeScale.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
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
case class DemandEnvGenUGen[R <: Rate](rate: R, levels: AnyUGenIn, durs: AnyUGenIn, shapes: AnyUGenIn, curvatures: AnyUGenIn, gate: AnyUGenIn, reset: AnyUGenIn, levelScale: AnyUGenIn, levelBias: AnyUGenIn, timeScale: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(levels, durs, shapes, curvatures, gate, reset, levelScale, levelBias, timeScale, doneAction))
case class Dseries(start: AnyGE = 0.0f, step: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand, DseriesUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _start: IIdxSeq[AnyUGenIn] = start.expand
      val _step: IIdxSeq[AnyUGenIn] = step.expand
      val _length: IIdxSeq[AnyUGenIn] = length.expand
      val _sz_start = _start.size
      val _sz_step = _step.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_start, _sz_step, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DseriesUGen(_start(i.%(_sz_start)), _step(i.%(_sz_step)), _length(i.%(_sz_length))))
   }
}
case class DseriesUGen(start: AnyUGenIn, step: AnyUGenIn, length: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(start, step, length)) with DemandRated with IsIndividual
case class Dgeom(start: AnyGE = 1.0f, grow: AnyGE = 2.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand, DgeomUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _start: IIdxSeq[AnyUGenIn] = start.expand
      val _grow: IIdxSeq[AnyUGenIn] = grow.expand
      val _length: IIdxSeq[AnyUGenIn] = length.expand
      val _sz_start = _start.size
      val _sz_grow = _grow.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_start, _sz_grow, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DgeomUGen(_start(i.%(_sz_start)), _grow(i.%(_sz_grow)), _length(i.%(_sz_length))))
   }
}
case class DgeomUGen(start: AnyUGenIn, grow: AnyUGenIn, length: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(start, grow, length)) with DemandRated with IsIndividual
case class Dwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand, DwhiteUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _length: IIdxSeq[AnyUGenIn] = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DwhiteUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _length(i.%(_sz_length))))
   }
}
case class DwhiteUGen(lo: AnyUGenIn, hi: AnyUGenIn, length: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(lo, hi, length)) with DemandRated with UsesRandSeed
case class Dbrown(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, step: AnyGE = 0.01f, length: AnyGE = inf) extends SingleOutUGenSource[demand, DbrownUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _step: IIdxSeq[AnyUGenIn] = step.expand
      val _length: IIdxSeq[AnyUGenIn] = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_step = _step.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_step, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DbrownUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _step(i.%(_sz_step)), _length(i.%(_sz_length))))
   }
}
case class DbrownUGen(lo: AnyUGenIn, hi: AnyUGenIn, step: AnyUGenIn, length: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(lo, hi, step, length)) with DemandRated with UsesRandSeed
case class Diwhite(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, length: AnyGE = inf) extends SingleOutUGenSource[demand, DiwhiteUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _length: IIdxSeq[AnyUGenIn] = length.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_length = _length.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_length)
      IIdxSeq.tabulate(_exp_)(i => DiwhiteUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _length(i.%(_sz_length))))
   }
}
case class DiwhiteUGen(lo: AnyUGenIn, hi: AnyUGenIn, length: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(lo, hi, length)) with DemandRated with UsesRandSeed
case class Dseq(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand, DseqUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _repeats: IIdxSeq[AnyUGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DseqUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DseqUGen(seq: IIdxSeq[AnyUGenIn], repeats: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq[AnyUGenIn](repeats).++(seq)) with DemandRated with IsIndividual
case class Dser(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand, DserUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _repeats: IIdxSeq[AnyUGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DserUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DserUGen(seq: IIdxSeq[AnyUGenIn], repeats: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq[AnyUGenIn](repeats).++(seq)) with DemandRated with IsIndividual
case class Dbufrd(buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends SingleOutUGenSource[demand, DbufrdUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _index: IIdxSeq[AnyUGenIn] = index.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_buf, _sz_index, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => DbufrdUGen(_buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop))))
   }
}
case class DbufrdUGen(buf: AnyUGenIn, index: AnyUGenIn, loop: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(buf, index, loop)) with DemandRated with IsIndividual
case class Dbufwr(in: AnyGE, buf: AnyGE, index: AnyGE = 0.0f, loop: AnyGE = 1.0f) extends SingleOutUGenSource[demand, DbufwrUGen] with DemandRated with WritesBuffer {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _index: IIdxSeq[AnyUGenIn] = index.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _sz_in = _in.size
      val _sz_buf = _buf.size
      val _sz_index = _index.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_in, _sz_buf, _sz_index, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => DbufwrUGen(_in(i.%(_sz_in)), _buf(i.%(_sz_buf)), _index(i.%(_sz_index)), _loop(i.%(_sz_loop))))
   }
}
case class DbufwrUGen(in: AnyUGenIn, buf: AnyUGenIn, index: AnyUGenIn, loop: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(in, buf, index, loop)) with DemandRated with WritesBuffer
case class Drand(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand, DrandUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _repeats: IIdxSeq[AnyUGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DrandUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DrandUGen(seq: IIdxSeq[AnyUGenIn], repeats: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq[AnyUGenIn](repeats).++(seq)) with DemandRated with UsesRandSeed
case class Dxrand(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand, DxrandUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _repeats: IIdxSeq[AnyUGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DxrandUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DxrandUGen(seq: IIdxSeq[AnyUGenIn], repeats: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq[AnyUGenIn](repeats).++(seq)) with DemandRated with UsesRandSeed
case class Dshuf(seq: Multi[AnyGE], repeats: AnyGE = 1.0f) extends SingleOutUGenSource[demand, DshufUGen] with DemandRated with UsesRandSeed {
   protected def expandUGens = {
      val _repeats: IIdxSeq[AnyUGenIn] = repeats.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_repeats = _repeats.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_repeats, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DshufUGen(_seq(i.%(_sz_seq)).expand, _repeats(i.%(_sz_repeats))))
   }
}
case class DshufUGen(seq: IIdxSeq[AnyUGenIn], repeats: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq[AnyUGenIn](repeats).++(seq)) with DemandRated with UsesRandSeed
case class Dswitch1(seq: Multi[AnyGE], index: AnyGE) extends SingleOutUGenSource[demand, Dswitch1UGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _index: IIdxSeq[AnyUGenIn] = index.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_index = _index.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_index, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => Dswitch1UGen(_seq(i.%(_sz_seq)).expand, _index(i.%(_sz_index))))
   }
}
case class Dswitch1UGen(seq: IIdxSeq[AnyUGenIn], index: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq[AnyUGenIn](index).++(seq)) with DemandRated with IsIndividual
case class Dswitch(seq: Multi[AnyGE], index: AnyGE) extends SingleOutUGenSource[demand, DswitchUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _index: IIdxSeq[AnyUGenIn] = index.expand
      val _seq: IIdxSeq[AnyGE] = seq.mexpand
      val _sz_index = _index.size
      val _sz_seq = _seq.size
      val _exp_ = maxInt(_sz_index, _sz_seq)
      IIdxSeq.tabulate(_exp_)(i => DswitchUGen(_seq(i.%(_sz_seq)).expand, _index(i.%(_sz_index))))
   }
}
case class DswitchUGen(seq: IIdxSeq[AnyUGenIn], index: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq[AnyUGenIn](index).++(seq)) with DemandRated with IsIndividual
case class Dstutter(n: AnyGE, in: AnyGE) extends SingleOutUGenSource[demand, DstutterUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _n: IIdxSeq[AnyUGenIn] = n.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_n = _n.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_n, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DstutterUGen(_n(i.%(_sz_n)), _in(i.%(_sz_in))))
   }
}
case class DstutterUGen(n: AnyUGenIn, in: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(n, in)) with DemandRated with IsIndividual
case class Donce(in: AnyGE) extends SingleOutUGenSource[demand, DonceUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => DonceUGen(_in(i)))
   }
}
case class DonceUGen(in: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(in)) with DemandRated with IsIndividual
case class Dreset(in: AnyGE, reset: AnyGE) extends SingleOutUGenSource[demand, DresetUGen] with DemandRated with IsIndividual {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _reset: IIdxSeq[AnyUGenIn] = reset.expand
      val _sz_in = _in.size
      val _sz_reset = _reset.size
      val _exp_ = maxInt(_sz_in, _sz_reset)
      IIdxSeq.tabulate(_exp_)(i => DresetUGen(_in(i.%(_sz_in)), _reset(i.%(_sz_reset))))
   }
}
case class DresetUGen(in: AnyUGenIn, reset: AnyUGenIn) extends SingleOutUGen[demand](IIdxSeq(in, reset)) with DemandRated with IsIndividual