/*
 * LFUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 06 20:58:06 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object Vibrato {
   def kr: Vibrato[control] = kr()
   def kr(freq: AnyGE = 440.0f, beat: AnyGE = 6.0f, depth: AnyGE = 0.02f, delay: AnyGE = 0.0f, onset: AnyGE = 0.0f, beatVar: AnyGE = 0.04f, depthVar: AnyGE = 0.1f, iphase: AnyGE = 0.0f) = apply[control](control, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
   def ar: Vibrato[audio] = ar()
   def ar(freq: AnyGE = 440.0f, beat: AnyGE = 6.0f, depth: AnyGE = 0.02f, delay: AnyGE = 0.0f, onset: AnyGE = 0.0f, beatVar: AnyGE = 0.04f, depthVar: AnyGE = 0.1f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
}
case class Vibrato[R <: Rate](rate: R, freq: AnyGE, beat: AnyGE, depth: AnyGE, delay: AnyGE, onset: AnyGE, beatVar: AnyGE, depthVar: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[R, VibratoUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _beat: IIdxSeq[AnyUGenIn] = beat.expand
      val _depth: IIdxSeq[AnyUGenIn] = depth.expand
      val _delay: IIdxSeq[AnyUGenIn] = delay.expand
      val _onset: IIdxSeq[AnyUGenIn] = onset.expand
      val _beatVar: IIdxSeq[AnyUGenIn] = beatVar.expand
      val _depthVar: IIdxSeq[AnyUGenIn] = depthVar.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _sz_freq = _freq.size
      val _sz_beat = _beat.size
      val _sz_depth = _depth.size
      val _sz_delay = _delay.size
      val _sz_onset = _onset.size
      val _sz_beatVar = _beatVar.size
      val _sz_depthVar = _depthVar.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_beat, _sz_depth, _sz_delay, _sz_onset, _sz_beatVar, _sz_depthVar, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => VibratoUGen(rate, _freq(i.%(_sz_freq)), _beat(i.%(_sz_beat)), _depth(i.%(_sz_depth)), _delay(i.%(_sz_delay)), _onset(i.%(_sz_onset)), _beatVar(i.%(_sz_beatVar)), _depthVar(i.%(_sz_depthVar)), _iphase(i.%(_sz_iphase))))
   }
}
case class VibratoUGen[R <: Rate](rate: R, freq: AnyUGenIn, beat: AnyUGenIn, depth: AnyUGenIn, delay: AnyUGenIn, onset: AnyUGenIn, beatVar: AnyUGenIn, depthVar: AnyUGenIn, iphase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, beat, depth, delay, onset, beatVar, depthVar, iphase))
object LFPulse {
   def kr: LFPulse[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[control](control, freq, iphase, width)
   def ar: LFPulse[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[audio](audio, freq, iphase, width)
}
case class LFPulse[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE, width: AnyGE) extends SingleOutUGenSource[R, LFPulseUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => LFPulseUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase)), _width(i.%(_sz_width))))
   }
}
case class LFPulseUGen[R <: Rate](rate: R, freq: AnyUGenIn, iphase: AnyUGenIn, width: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, iphase, width))
object LFSaw {
   def kr: LFSaw[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFSaw[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
case class LFSaw[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[R, LFSawUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => LFSawUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
   }
}
case class LFSawUGen[R <: Rate](rate: R, freq: AnyUGenIn, iphase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, iphase))
object LFPar {
   def kr: LFPar[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFPar[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
case class LFPar[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[R, LFParUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => LFParUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
   }
}
case class LFParUGen[R <: Rate](rate: R, freq: AnyUGenIn, iphase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, iphase))
object LFCub {
   def kr: LFCub[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFCub[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
case class LFCub[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[R, LFCubUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => LFCubUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
   }
}
case class LFCubUGen[R <: Rate](rate: R, freq: AnyUGenIn, iphase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, iphase))
object LFTri {
   def kr: LFTri[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFTri[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
case class LFTri[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[R, LFTriUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => LFTriUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
   }
}
case class LFTriUGen[R <: Rate](rate: R, freq: AnyUGenIn, iphase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, iphase))
object LFGauss {
   def kr: LFGauss[control] = kr()
   def kr(dur: AnyGE = 1.0f, width: AnyGE = 0.1f, iphase: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, dur, width, iphase, loop, doneAction)
   def ar: LFGauss[audio] = ar()
   def ar(dur: AnyGE = 1.0f, width: AnyGE = 0.1f, iphase: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, dur, width, iphase, loop, doneAction)
}
case class LFGauss[R <: Rate](rate: R, dur: AnyGE, width: AnyGE, iphase: AnyGE, loop: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, LFGaussUGen[R]] {
   def expand = {
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _sz_dur = _dur.size
      val _sz_width = _width.size
      val _sz_iphase = _iphase.size
      val _sz_loop = _loop.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_dur, _sz_width, _sz_iphase, _sz_loop, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => LFGaussUGen(rate, _dur(i.%(_sz_dur)), _width(i.%(_sz_width)), _iphase(i.%(_sz_iphase)), _loop(i.%(_sz_loop)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class LFGaussUGen[R <: Rate](rate: R, dur: AnyUGenIn, width: AnyUGenIn, iphase: AnyUGenIn, loop: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(dur, width, iphase, loop, doneAction))
object Impulse {
   def kr: Impulse[control] = kr()
   def kr(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, freq, phase)
   def ar: Impulse[audio] = ar()
   def ar(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, freq, phase)
}
case class Impulse[R <: Rate](rate: R, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[R, ImpulseUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _phase: IIdxSeq[AnyUGenIn] = phase.expand
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = maxInt(_sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => ImpulseUGen(rate, _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class ImpulseUGen[R <: Rate](rate: R, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, phase))
object VarSaw {
   def kr: VarSaw[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[control](control, freq, iphase, width)
   def ar: VarSaw[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[audio](audio, freq, iphase, width)
}
case class VarSaw[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE, width: AnyGE) extends SingleOutUGenSource[R, VarSawUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _iphase: IIdxSeq[AnyUGenIn] = iphase.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _sz_width = _width.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase, _sz_width)
      IIdxSeq.tabulate(_exp_)(i => VarSawUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase)), _width(i.%(_sz_width))))
   }
}
case class VarSawUGen[R <: Rate](rate: R, freq: AnyUGenIn, iphase: AnyUGenIn, width: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, iphase, width))
object SyncSaw {
   def kr: SyncSaw[control] = kr()
   def kr(syncFreq: AnyGE = 440.0f, sawFreq: AnyGE = 440.0f) = apply[control](control, syncFreq, sawFreq)
   def ar: SyncSaw[audio] = ar()
   def ar(syncFreq: AnyGE = 440.0f, sawFreq: AnyGE = 440.0f) = apply[audio](audio, syncFreq, sawFreq)
}
case class SyncSaw[R <: Rate](rate: R, syncFreq: AnyGE, sawFreq: AnyGE) extends SingleOutUGenSource[R, SyncSawUGen[R]] {
   def expand = {
      val _syncFreq: IIdxSeq[AnyUGenIn] = syncFreq.expand
      val _sawFreq: IIdxSeq[AnyUGenIn] = sawFreq.expand
      val _sz_syncFreq = _syncFreq.size
      val _sz_sawFreq = _sawFreq.size
      val _exp_ = maxInt(_sz_syncFreq, _sz_sawFreq)
      IIdxSeq.tabulate(_exp_)(i => SyncSawUGen(rate, _syncFreq(i.%(_sz_syncFreq)), _sawFreq(i.%(_sz_sawFreq))))
   }
}
case class SyncSawUGen[R <: Rate](rate: R, syncFreq: AnyUGenIn, sawFreq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(syncFreq, sawFreq))
object K2A {
   def ar(in: AnyGE) = apply(in)
}
case class K2A(in: AnyGE) extends SingleOutUGenSource[audio, K2AUGen] with AudioRated {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => K2AUGen(_in(i)))
   }
}
case class K2AUGen(in: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in)) with AudioRated
object A2K {
   def kr(in: AnyGE) = apply(in)
}
case class A2K(in: AnyGE) extends SingleOutUGenSource[control, A2KUGen] with ControlRated {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => A2KUGen(_in(i)))
   }
}
case class A2KUGen(in: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(in)) with ControlRated
object T2K {
   def kr(in: AnyGE) = apply(in)
}
case class T2K(in: AnyGE) extends SingleOutUGenSource[control, T2KUGen] with ControlRated {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => T2KUGen(_in(i)))
   }
}
case class T2KUGen(in: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(in)) with ControlRated
object T2A {
   def ar(in: AnyGE) = apply(in)
}
case class T2A(in: AnyGE) extends SingleOutUGenSource[audio, T2AUGen] with AudioRated {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => T2AUGen(_in(i)))
   }
}
case class T2AUGen(in: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in)) with AudioRated
object DC {
   def kr(in: Expands[AnyGE]) = apply[control](control, in)
   def ar(in: Expands[AnyGE]) = apply[audio](audio, in)
}
case class DC[R <: Rate](rate: R, in: Expands[AnyGE]) extends UGenSource[DCUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyGE] = in.expand
      IIdxSeq.tabulate(_in.size)(i => DCUGen(rate, _in(i).expand))
   }
}
case class DCUGen[R <: Rate](rate: R, in: IIdxSeq[AnyUGenIn]) extends MultiOutUGen(IIdxSeq.fill(in.size)(rate), in)
object Silent {
   def ar: Silent = ar()
   def ar(numChannels: Int = 1) = apply(numChannels)
}
case class Silent(numChannels: Int) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq.empty) with AudioRated
object Line {
   def ar: Line[audio] = ar()
   def ar(start: AnyGE = 0.0f, end: AnyGE = 1.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, start, end, dur, doneAction)
   def kr: Line[control] = kr()
   def kr(start: AnyGE = 0.0f, end: AnyGE = 1.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, start, end, dur, doneAction)
}
case class Line[R <: Rate](rate: R, start: AnyGE, end: AnyGE, dur: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, LineUGen[R]] with HasSideEffect with HasDoneFlag {
   def expand = {
      val _start: IIdxSeq[AnyUGenIn] = start.expand
      val _end: IIdxSeq[AnyUGenIn] = end.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _sz_start = _start.size
      val _sz_end = _end.size
      val _sz_dur = _dur.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_start, _sz_end, _sz_dur, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => LineUGen(rate, _start(i.%(_sz_start)), _end(i.%(_sz_end)), _dur(i.%(_sz_dur)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class LineUGen[R <: Rate](rate: R, start: AnyUGenIn, end: AnyUGenIn, dur: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(start, end, dur, doneAction)) with HasSideEffect with HasDoneFlag
object XLine {
   def ar: XLine[audio] = ar()
   def ar(start: AnyGE = 1.0f, end: AnyGE = 2.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, start, end, dur, doneAction)
   def kr: XLine[control] = kr()
   def kr(start: AnyGE = 1.0f, end: AnyGE = 2.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, start, end, dur, doneAction)
}
case class XLine[R <: Rate](rate: R, start: AnyGE, end: AnyGE, dur: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, XLineUGen[R]] with HasSideEffect with HasDoneFlag {
   def expand = {
      val _start: IIdxSeq[AnyUGenIn] = start.expand
      val _end: IIdxSeq[AnyUGenIn] = end.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _sz_start = _start.size
      val _sz_end = _end.size
      val _sz_dur = _dur.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_start, _sz_end, _sz_dur, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => XLineUGen(rate, _start(i.%(_sz_start)), _end(i.%(_sz_end)), _dur(i.%(_sz_dur)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class XLineUGen[R <: Rate](rate: R, start: AnyUGenIn, end: AnyUGenIn, dur: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(start, end, dur, doneAction)) with HasSideEffect with HasDoneFlag
object Wrap {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
case class Wrap[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R, WrapUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_in = _in.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => WrapUGen(rate, _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class WrapUGen[R <: Rate](rate: R, in: AnyUGenIn, lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, lo, hi))
object Fold {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
case class Fold[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R, FoldUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_in = _in.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => FoldUGen(rate, _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class FoldUGen[R <: Rate](rate: R, in: AnyUGenIn, lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, lo, hi))
object Clip {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
case class Clip[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R, ClipUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_in = _in.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => ClipUGen(rate, _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class ClipUGen[R <: Rate](rate: R, in: AnyUGenIn, lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, lo, hi))
object AmpComp {
   def ir: AmpComp[scalar] = ir()
   def ir(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply[scalar](scalar, freq, root, expon)
   def kr: AmpComp[control] = kr()
   def kr(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply[control](control, freq, root, expon)
   def ar: AmpComp[audio] = ar()
   def ar(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply[audio](audio, freq, root, expon)
}
case class AmpComp[R <: Rate](rate: R, freq: AnyGE, root: AnyGE, expon: AnyGE) extends SingleOutUGenSource[R, AmpCompUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _root: IIdxSeq[AnyUGenIn] = root.expand
      val _expon: IIdxSeq[AnyUGenIn] = expon.expand
      val _sz_freq = _freq.size
      val _sz_root = _root.size
      val _sz_expon = _expon.size
      val _exp_ = maxInt(_sz_freq, _sz_root, _sz_expon)
      IIdxSeq.tabulate(_exp_)(i => AmpCompUGen(rate, _freq(i.%(_sz_freq)), _root(i.%(_sz_root)), _expon(i.%(_sz_expon))))
   }
}
case class AmpCompUGen[R <: Rate](rate: R, freq: AnyUGenIn, root: AnyUGenIn, expon: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, root, expon))
object AmpCompA {
   def ir: AmpCompA[scalar] = ir()
   def ir(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply[scalar](scalar, freq, root, minAmp, rootAmp)
   def kr: AmpCompA[control] = kr()
   def kr(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply[control](control, freq, root, minAmp, rootAmp)
   def ar: AmpCompA[audio] = ar()
   def ar(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply[audio](audio, freq, root, minAmp, rootAmp)
}
case class AmpCompA[R <: Rate](rate: R, freq: AnyGE, root: AnyGE, minAmp: AnyGE, rootAmp: AnyGE) extends SingleOutUGenSource[R, AmpCompAUGen[R]] {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _root: IIdxSeq[AnyUGenIn] = root.expand
      val _minAmp: IIdxSeq[AnyUGenIn] = minAmp.expand
      val _rootAmp: IIdxSeq[AnyUGenIn] = rootAmp.expand
      val _sz_freq = _freq.size
      val _sz_root = _root.size
      val _sz_minAmp = _minAmp.size
      val _sz_rootAmp = _rootAmp.size
      val _exp_ = maxInt(_sz_freq, _sz_root, _sz_minAmp, _sz_rootAmp)
      IIdxSeq.tabulate(_exp_)(i => AmpCompAUGen(rate, _freq(i.%(_sz_freq)), _root(i.%(_sz_root)), _minAmp(i.%(_sz_minAmp)), _rootAmp(i.%(_sz_rootAmp))))
   }
}
case class AmpCompAUGen[R <: Rate](rate: R, freq: AnyUGenIn, root: AnyUGenIn, minAmp: AnyUGenIn, rootAmp: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, root, minAmp, rootAmp))
object InRange {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
case class InRange[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R, InRangeUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_in = _in.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => InRangeUGen(rate, _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class InRangeUGen[R <: Rate](rate: R, in: AnyUGenIn, lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, lo, hi))
object InRect {
   def ir(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply[scalar](scalar, x, y, left, top, right, bottom)
   def kr(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply[control](control, x, y, left, top, right, bottom)
   def ar(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply[audio](audio, x, y, left, top, right, bottom)
}
case class InRect[R <: Rate](rate: R, x: AnyGE, y: AnyGE, left: AnyGE, top: AnyGE, right: AnyGE, bottom: AnyGE) extends SingleOutUGenSource[R, InRectUGen[R]] {
   def expand = {
      val _x: IIdxSeq[AnyUGenIn] = x.expand
      val _y: IIdxSeq[AnyUGenIn] = y.expand
      val _left: IIdxSeq[AnyUGenIn] = left.expand
      val _top: IIdxSeq[AnyUGenIn] = top.expand
      val _right: IIdxSeq[AnyUGenIn] = right.expand
      val _bottom: IIdxSeq[AnyUGenIn] = bottom.expand
      val _sz_x = _x.size
      val _sz_y = _y.size
      val _sz_left = _left.size
      val _sz_top = _top.size
      val _sz_right = _right.size
      val _sz_bottom = _bottom.size
      val _exp_ = maxInt(_sz_x, _sz_y, _sz_left, _sz_top, _sz_right, _sz_bottom)
      IIdxSeq.tabulate(_exp_)(i => InRectUGen(rate, _x(i.%(_sz_x)), _y(i.%(_sz_y)), _left(i.%(_sz_left)), _top(i.%(_sz_top)), _right(i.%(_sz_right)), _bottom(i.%(_sz_bottom))))
   }
}
case class InRectUGen[R <: Rate](rate: R, x: AnyUGenIn, y: AnyUGenIn, left: AnyUGenIn, top: AnyUGenIn, right: AnyUGenIn, bottom: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(x, y, left, top, right, bottom))
object LinExp {
   def ir(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[scalar](scalar, in, srcLo, srcHi, dstLo, dstHi)
   def kr(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[control](control, in, srcLo, srcHi, dstLo, dstHi)
   def ar(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[audio](audio, in, srcLo, srcHi, dstLo, dstHi)
}
case class LinExp[R <: Rate](rate: R, in: AnyGE, srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE) extends SingleOutUGenSource[R, LinExpUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _srcLo: IIdxSeq[AnyUGenIn] = srcLo.expand
      val _srcHi: IIdxSeq[AnyUGenIn] = srcHi.expand
      val _dstLo: IIdxSeq[AnyUGenIn] = dstLo.expand
      val _dstHi: IIdxSeq[AnyUGenIn] = dstHi.expand
      val _sz_in = _in.size
      val _sz_srcLo = _srcLo.size
      val _sz_srcHi = _srcHi.size
      val _sz_dstLo = _dstLo.size
      val _sz_dstHi = _dstHi.size
      val _exp_ = maxInt(_sz_in, _sz_srcLo, _sz_srcHi, _sz_dstLo, _sz_dstHi)
      IIdxSeq.tabulate(_exp_)(i => LinExpUGen(rate, _in(i.%(_sz_in)), _srcLo(i.%(_sz_srcLo)), _srcHi(i.%(_sz_srcHi)), _dstLo(i.%(_sz_dstLo)), _dstHi(i.%(_sz_dstHi))))
   }
}
case class LinExpUGen[R <: Rate](rate: R, in: AnyUGenIn, srcLo: AnyUGenIn, srcHi: AnyUGenIn, dstLo: AnyUGenIn, dstHi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, srcLo, srcHi, dstLo, dstHi))
object LinLin {
   def ir(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply[scalar](scalar, in, srcLo, srcHi, dstLo, dstHi)
   def kr(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply[control](control, in, srcLo, srcHi, dstLo, dstHi)
   def ar(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply[audio](audio, in, srcLo, srcHi, dstLo, dstHi)
}
case class LinLin[R <: Rate](rate: R, in: AnyGE, srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE) extends SingleOutUGenSource[R, LinLinUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _srcLo: IIdxSeq[AnyUGenIn] = srcLo.expand
      val _srcHi: IIdxSeq[AnyUGenIn] = srcHi.expand
      val _dstLo: IIdxSeq[AnyUGenIn] = dstLo.expand
      val _dstHi: IIdxSeq[AnyUGenIn] = dstHi.expand
      val _sz_in = _in.size
      val _sz_srcLo = _srcLo.size
      val _sz_srcHi = _srcHi.size
      val _sz_dstLo = _dstLo.size
      val _sz_dstHi = _dstHi.size
      val _exp_ = maxInt(_sz_in, _sz_srcLo, _sz_srcHi, _sz_dstLo, _sz_dstHi)
      IIdxSeq.tabulate(_exp_)(i => LinLinUGen(rate, _in(i.%(_sz_in)), _srcLo(i.%(_sz_srcLo)), _srcHi(i.%(_sz_srcHi)), _dstLo(i.%(_sz_dstLo)), _dstHi(i.%(_sz_dstHi))))
   }
}
case class LinLinUGen[R <: Rate](rate: R, in: AnyUGenIn, srcLo: AnyUGenIn, srcHi: AnyUGenIn, dstLo: AnyUGenIn, dstHi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, srcLo, srcHi, dstLo, dstHi))
object EnvGen {
   def kr(envelope: Expands[AnyGE], gate: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, envelope, gate, levelScale, levelBias, timeScale, doneAction)
   def ar(envelope: Expands[AnyGE], gate: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, envelope, gate, levelScale, levelBias, timeScale, doneAction)
}
case class EnvGen[R <: Rate](rate: R, envelope: Expands[AnyGE], gate: AnyGE, levelScale: AnyGE, levelBias: AnyGE, timeScale: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, EnvGenUGen[R]] {
   def expand = {
      val _gate: IIdxSeq[AnyUGenIn] = gate.expand
      val _levelScale: IIdxSeq[AnyUGenIn] = levelScale.expand
      val _levelBias: IIdxSeq[AnyUGenIn] = levelBias.expand
      val _timeScale: IIdxSeq[AnyUGenIn] = timeScale.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _envelope: IIdxSeq[AnyGE] = envelope.expand
      val _sz_gate = _gate.size
      val _sz_levelScale = _levelScale.size
      val _sz_levelBias = _levelBias.size
      val _sz_timeScale = _timeScale.size
      val _sz_doneAction = _doneAction.size
      val _sz_envelope = _envelope.size
      val _exp_ = maxInt(_sz_gate, _sz_levelScale, _sz_levelBias, _sz_timeScale, _sz_doneAction, _sz_envelope)
      IIdxSeq.tabulate(_exp_)(i => EnvGenUGen(rate, _envelope(i.%(_sz_envelope)).expand, _gate(i.%(_sz_gate)), _levelScale(i.%(_sz_levelScale)), _levelBias(i.%(_sz_levelBias)), _timeScale(i.%(_sz_timeScale)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class EnvGenUGen[R <: Rate](rate: R, envelope: IIdxSeq[AnyUGenIn], gate: AnyUGenIn, levelScale: AnyUGenIn, levelBias: AnyUGenIn, timeScale: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq[AnyUGenIn](gate, levelScale, levelBias, timeScale, doneAction).++(envelope))
object Linen {
   def kr: Linen[control] = kr()
   def kr(gate: AnyGE = 1.0f, attack: AnyGE = 0.01f, sustain: AnyGE = 1.0f, release: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, gate, attack, sustain, release, doneAction)
   def ar: Linen[audio] = ar()
   def ar(gate: AnyGE = 1.0f, attack: AnyGE = 0.01f, sustain: AnyGE = 1.0f, release: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, gate, attack, sustain, release, doneAction)
}
case class Linen[R <: Rate](rate: R, gate: AnyGE, attack: AnyGE, sustain: AnyGE, release: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R, LinenUGen[R]] {
   def expand = {
      val _gate: IIdxSeq[AnyUGenIn] = gate.expand
      val _attack: IIdxSeq[AnyUGenIn] = attack.expand
      val _sustain: IIdxSeq[AnyUGenIn] = sustain.expand
      val _release: IIdxSeq[AnyUGenIn] = release.expand
      val _doneAction: IIdxSeq[AnyUGenIn] = doneAction.expand
      val _sz_gate = _gate.size
      val _sz_attack = _attack.size
      val _sz_sustain = _sustain.size
      val _sz_release = _release.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_gate, _sz_attack, _sz_sustain, _sz_release, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => LinenUGen(rate, _gate(i.%(_sz_gate)), _attack(i.%(_sz_attack)), _sustain(i.%(_sz_sustain)), _release(i.%(_sz_release)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class LinenUGen[R <: Rate](rate: R, gate: AnyUGenIn, attack: AnyUGenIn, sustain: AnyUGenIn, release: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(gate, attack, sustain, release, doneAction))
object IEnvGen {
   def kr(envelope: Expands[AnyGE], index: AnyGE = 1.0f) = apply[control](control, envelope, index)
   def ar(envelope: Expands[AnyGE], index: AnyGE = 1.0f) = apply[audio](audio, envelope, index)
}
case class IEnvGen[R <: Rate](rate: R, envelope: Expands[AnyGE], index: AnyGE) extends SingleOutUGenSource[R, IEnvGenUGen[R]] {
   def expand = {
      val _index: IIdxSeq[AnyUGenIn] = index.expand
      val _envelope: IIdxSeq[AnyGE] = envelope.expand
      val _sz_index = _index.size
      val _sz_envelope = _envelope.size
      val _exp_ = maxInt(_sz_index, _sz_envelope)
      IIdxSeq.tabulate(_exp_)(i => IEnvGenUGen(rate, _envelope(i.%(_sz_envelope)).expand, _index(i.%(_sz_index))))
   }
}
case class IEnvGenUGen[R <: Rate](rate: R, envelope: IIdxSeq[AnyUGenIn], index: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq[AnyUGenIn](index).++(envelope))