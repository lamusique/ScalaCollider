/*
 * LFUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 27 23:03:33 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import util.UGenHelper._
//object Vibrato {
//   def kr: Vibrato = kr()
//   def kr(freq: AnyGE = 440.0f, beat: AnyGE = 6.0f, depth: AnyGE = 0.02f, delay: AnyGE = 0.0f, onset: AnyGE = 0.0f, beatVar: AnyGE = 0.04f, depthVar: AnyGE = 0.1f, iphase: AnyGE = 0.0f) = apply(control, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
//   def ar: Vibrato = ar()
//   def ar(freq: AnyGE = 440.0f, beat: AnyGE = 6.0f, depth: AnyGE = 0.02f, delay: AnyGE = 0.0f, onset: AnyGE = 0.0f, beatVar: AnyGE = 0.04f, depthVar: AnyGE = 0.1f, iphase: AnyGE = 0.0f) = apply(audio, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
//}
//case class Vibrato(rate: Rate, freq: AnyGE, beat: AnyGE, depth: AnyGE, delay: AnyGE, onset: AnyGE, beatVar: AnyGE, depthVar: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[VibratoUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _beat: IIdxSeq[UGenIn] = beat.expand
//      val _depth: IIdxSeq[UGenIn] = depth.expand
//      val _delay: IIdxSeq[UGenIn] = delay.expand
//      val _onset: IIdxSeq[UGenIn] = onset.expand
//      val _beatVar: IIdxSeq[UGenIn] = beatVar.expand
//      val _depthVar: IIdxSeq[UGenIn] = depthVar.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _sz_freq = _freq.size
//      val _sz_beat = _beat.size
//      val _sz_depth = _depth.size
//      val _sz_delay = _delay.size
//      val _sz_onset = _onset.size
//      val _sz_beatVar = _beatVar.size
//      val _sz_depthVar = _depthVar.size
//      val _sz_iphase = _iphase.size
//      val _exp_ = maxInt(_sz_freq, _sz_beat, _sz_depth, _sz_delay, _sz_onset, _sz_beatVar, _sz_depthVar, _sz_iphase)
//      IIdxSeq.tabulate(_exp_)(i => VibratoUGen(rate, _freq(i.%(_sz_freq)), _beat(i.%(_sz_beat)), _depth(i.%(_sz_depth)), _delay(i.%(_sz_delay)), _onset(i.%(_sz_onset)), _beatVar(i.%(_sz_beatVar)), _depthVar(i.%(_sz_depthVar)), _iphase(i.%(_sz_iphase))))
//   }
//}
//case class VibratoUGen(rate: Rate, freq: UGenIn, beat: UGenIn, depth: UGenIn, delay: UGenIn, onset: UGenIn, beatVar: UGenIn, depthVar: UGenIn, iphase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, beat, depth, delay, onset, beatVar, depthVar, iphase))
///**
// * A non-band-limited pulse oscillator UGen.
// * Outputs a high value of one and a low value of zero.
// *
// * @see [[de.sciss.synth.ugen.Pulse]]
// */
//object LFPulse {
//   def kr: LFPulse = kr()
///**
// * @param freq            oscillator frequency in Hertz
// * @param iphase          initial phase offset in cycles ( `0..1` ). If you think
// *                        of a buffer of one cycle of the waveform, this is the starting offset
// *                        into this buffer. Hence, an `iphase` of `0.25` means that you will hear
// *                        the first impulse after `0.75` periods! If you prefer to specify the
// *                        perceived delay instead, you could use an `iphase` of `-0.25 + 1` which
// *                        is more intuitive. Note that the phase is not automatically wrapped
// *                        into the range of `0..1`, so putting an `iphase` of `-0.25` currently
// *                        results in a strange initial signal which only stabilizes to the
// *                        correct behaviour after one period!
// * @param width           pulse width duty cycle from zero to one. If you want to
// *                        specify the width rather in seconds, you can use the formula
// *                        `width = freq * dur`, e.g. for a single sample impulse use
// *                        `width = freq * SampleDur.ir`.
// */
//def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply(control, freq, iphase, width)
//   def ar: LFPulse = ar()
///**
// * @param freq            oscillator frequency in Hertz
// * @param iphase          initial phase offset in cycles ( `0..1` ). If you think
// *                        of a buffer of one cycle of the waveform, this is the starting offset
// *                        into this buffer. Hence, an `iphase` of `0.25` means that you will hear
// *                        the first impulse after `0.75` periods! If you prefer to specify the
// *                        perceived delay instead, you could use an `iphase` of `-0.25 + 1` which
// *                        is more intuitive. Note that the phase is not automatically wrapped
// *                        into the range of `0..1`, so putting an `iphase` of `-0.25` currently
// *                        results in a strange initial signal which only stabilizes to the
// *                        correct behaviour after one period!
// * @param width           pulse width duty cycle from zero to one. If you want to
// *                        specify the width rather in seconds, you can use the formula
// *                        `width = freq * dur`, e.g. for a single sample impulse use
// *                        `width = freq * SampleDur.ir`.
// */
//def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply(audio, freq, iphase, width)
//}
///**
// * A non-band-limited pulse oscillator UGen.
// * Outputs a high value of one and a low value of zero.
// *
// * @param freq            oscillator frequency in Hertz
// * @param iphase          initial phase offset in cycles ( `0..1` ). If you think
// *                        of a buffer of one cycle of the waveform, this is the starting offset
// *                        into this buffer. Hence, an `iphase` of `0.25` means that you will hear
// *                        the first impulse after `0.75` periods! If you prefer to specify the
// *                        perceived delay instead, you could use an `iphase` of `-0.25 + 1` which
// *                        is more intuitive. Note that the phase is not automatically wrapped
// *                        into the range of `0..1`, so putting an `iphase` of `-0.25` currently
// *                        results in a strange initial signal which only stabilizes to the
// *                        correct behaviour after one period!
// * @param width           pulse width duty cycle from zero to one. If you want to
// *                        specify the width rather in seconds, you can use the formula
// *                        `width = freq * dur`, e.g. for a single sample impulse use
// *                        `width = freq * SampleDur.ir`.
// *
// * @see [[de.sciss.synth.ugen.Pulse]]
// */
//case class LFPulse(rate: Rate, freq: AnyGE, iphase: AnyGE, width: AnyGE) extends SingleOutUGenSource[LFPulseUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _width: IIdxSeq[UGenIn] = width.expand
//      val _sz_freq = _freq.size
//      val _sz_iphase = _iphase.size
//      val _sz_width = _width.size
//      val _exp_ = maxInt(_sz_freq, _sz_iphase, _sz_width)
//      IIdxSeq.tabulate(_exp_)(i => LFPulseUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase)), _width(i.%(_sz_width))))
//   }
//}
//case class LFPulseUGen(rate: Rate, freq: UGenIn, iphase: UGenIn, width: UGenIn) extends SingleOutUGen(IIdxSeq(freq, iphase, width))
/**
* A sawtooth oscillator UGen. The oscillator is creating an aliased sawtooth,
* that is it does not use band-limiting. For a band-limited version use
* `Saw` instead. The signal range is -1 to +1.
*
* @see [[de.sciss.synth.ugen.Saw]]
*/
object LFSaw {
   def kr: LFSaw[control] = kr()
/**
* @param freq            oscillator frequency in Hertz
* @param iphase          initial phase offset. For efficiency reasons this is a
*                        value ranging from -1 to 1 (thus equal to the initial output value).
*                        Note that a phase of zero (default) means the wave starts at 0 and
*                        rises to +1 before jumping down to -1. Use a phase of 1 to have the wave start at -1.
*/
def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFSaw[audio] = ar()
/**
* @param freq            oscillator frequency in Hertz
* @param iphase          initial phase offset. For efficiency reasons this is a
*                        value ranging from -1 to 1 (thus equal to the initial output value).
*                        Note that a phase of zero (default) means the wave starts at 0 and
*                        rises to +1 before jumping down to -1. Use a phase of 1 to have the wave start at -1.
*/
def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
/**
* A sawtooth oscillator UGen. The oscillator is creating an aliased sawtooth,
* that is it does not use band-limiting. For a band-limited version use
* `Saw` instead. The signal range is -1 to +1.
*
* @param freq            oscillator frequency in Hertz
* @param iphase          initial phase offset. For efficiency reasons this is a
*                        value ranging from -1 to 1 (thus equal to the initial output value).
*                        Note that a phase of zero (default) means the wave starts at 0 and
*                        rises to +1 before jumping down to -1. Use a phase of 1 to have the wave start at -1.
*
* @see [[de.sciss.synth.ugen.Saw]]
*/
case class LFSaw[ R <: Rate ](rate: R, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq: IIdxSeq[UGenIn] = freq.expand
      val _iphase: IIdxSeq[UGenIn] = iphase.expand
      val _sz_freq = _freq.size
      val _sz_iphase = _iphase.size
      val _exp_ = maxInt(_sz_freq, _sz_iphase)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LFSaw", rate, IIdxSeq( _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase)))))
   }
}
//case class LFSawUGen(rate: Rate, freq: UGenIn, iphase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, iphase))
//object LFPar {
//   def kr: LFPar = kr()
//   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(control, freq, iphase)
//   def ar: LFPar = ar()
//   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(audio, freq, iphase)
//}
//case class LFPar(rate: Rate, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[LFParUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _sz_freq = _freq.size
//      val _sz_iphase = _iphase.size
//      val _exp_ = maxInt(_sz_freq, _sz_iphase)
//      IIdxSeq.tabulate(_exp_)(i => LFParUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
//   }
//}
//case class LFParUGen(rate: Rate, freq: UGenIn, iphase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, iphase))
//object LFCub {
//   def kr: LFCub = kr()
//   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(control, freq, iphase)
//   def ar: LFCub = ar()
//   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(audio, freq, iphase)
//}
//case class LFCub(rate: Rate, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[LFCubUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _sz_freq = _freq.size
//      val _sz_iphase = _iphase.size
//      val _exp_ = maxInt(_sz_freq, _sz_iphase)
//      IIdxSeq.tabulate(_exp_)(i => LFCubUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
//   }
//}
//case class LFCubUGen(rate: Rate, freq: UGenIn, iphase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, iphase))
//object LFTri {
//   def kr: LFTri = kr()
//   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(control, freq, iphase)
//   def ar: LFTri = ar()
//   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply(audio, freq, iphase)
//}
//case class LFTri(rate: Rate, freq: AnyGE, iphase: AnyGE) extends SingleOutUGenSource[LFTriUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _sz_freq = _freq.size
//      val _sz_iphase = _iphase.size
//      val _exp_ = maxInt(_sz_freq, _sz_iphase)
//      IIdxSeq.tabulate(_exp_)(i => LFTriUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase))))
//   }
//}
//case class LFTriUGen(rate: Rate, freq: UGenIn, iphase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, iphase))
//object LFGauss {
//   def kr: LFGauss = kr()
//   def kr(dur: AnyGE = 1.0f, width: AnyGE = 0.1f, iphase: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(control, dur, width, iphase, loop, doneAction)
//   def ar: LFGauss = ar()
//   def ar(dur: AnyGE = 1.0f, width: AnyGE = 0.1f, iphase: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(audio, dur, width, iphase, loop, doneAction)
//}
//case class LFGauss(rate: Rate, dur: AnyGE, width: AnyGE, iphase: AnyGE, loop: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[LFGaussUGen] {
//   protected def expandUGens = {
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _width: IIdxSeq[UGenIn] = width.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _loop: IIdxSeq[UGenIn] = loop.expand
//      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
//      val _sz_dur = _dur.size
//      val _sz_width = _width.size
//      val _sz_iphase = _iphase.size
//      val _sz_loop = _loop.size
//      val _sz_doneAction = _doneAction.size
//      val _exp_ = maxInt(_sz_dur, _sz_width, _sz_iphase, _sz_loop, _sz_doneAction)
//      IIdxSeq.tabulate(_exp_)(i => LFGaussUGen(rate, _dur(i.%(_sz_dur)), _width(i.%(_sz_width)), _iphase(i.%(_sz_iphase)), _loop(i.%(_sz_loop)), _doneAction(i.%(_sz_doneAction))))
//   }
//}
//case class LFGaussUGen(rate: Rate, dur: UGenIn, width: UGenIn, iphase: UGenIn, loop: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq(dur, width, iphase, loop, doneAction))
//object Impulse {
//   def kr: Impulse = kr()
//   def kr(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(control, freq, phase)
//   def ar: Impulse = ar()
//   def ar(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply(audio, freq, phase)
//}
//case class Impulse(rate: Rate, freq: AnyGE, phase: AnyGE) extends SingleOutUGenSource[ImpulseUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _phase: IIdxSeq[UGenIn] = phase.expand
//      val _sz_freq = _freq.size
//      val _sz_phase = _phase.size
//      val _exp_ = maxInt(_sz_freq, _sz_phase)
//      IIdxSeq.tabulate(_exp_)(i => ImpulseUGen(rate, _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
//   }
//}
//case class ImpulseUGen(rate: Rate, freq: UGenIn, phase: UGenIn) extends SingleOutUGen(IIdxSeq(freq, phase))
//object VarSaw {
//   def kr: VarSaw = kr()
//   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply(control, freq, iphase, width)
//   def ar: VarSaw = ar()
//   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply(audio, freq, iphase, width)
//}
//case class VarSaw(rate: Rate, freq: AnyGE, iphase: AnyGE, width: AnyGE) extends SingleOutUGenSource[VarSawUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _iphase: IIdxSeq[UGenIn] = iphase.expand
//      val _width: IIdxSeq[UGenIn] = width.expand
//      val _sz_freq = _freq.size
//      val _sz_iphase = _iphase.size
//      val _sz_width = _width.size
//      val _exp_ = maxInt(_sz_freq, _sz_iphase, _sz_width)
//      IIdxSeq.tabulate(_exp_)(i => VarSawUGen(rate, _freq(i.%(_sz_freq)), _iphase(i.%(_sz_iphase)), _width(i.%(_sz_width))))
//   }
//}
//case class VarSawUGen(rate: Rate, freq: UGenIn, iphase: UGenIn, width: UGenIn) extends SingleOutUGen(IIdxSeq(freq, iphase, width))
//object SyncSaw {
//   def kr: SyncSaw = kr()
//   def kr(syncFreq: AnyGE = 440.0f, sawFreq: AnyGE = 440.0f) = apply(control, syncFreq, sawFreq)
//   def ar: SyncSaw = ar()
//   def ar(syncFreq: AnyGE = 440.0f, sawFreq: AnyGE = 440.0f) = apply(audio, syncFreq, sawFreq)
//}
//case class SyncSaw(rate: Rate, syncFreq: AnyGE, sawFreq: AnyGE) extends SingleOutUGenSource[SyncSawUGen] {
//   protected def expandUGens = {
//      val _syncFreq: IIdxSeq[UGenIn] = syncFreq.expand
//      val _sawFreq: IIdxSeq[UGenIn] = sawFreq.expand
//      val _sz_syncFreq = _syncFreq.size
//      val _sz_sawFreq = _sawFreq.size
//      val _exp_ = maxInt(_sz_syncFreq, _sz_sawFreq)
//      IIdxSeq.tabulate(_exp_)(i => SyncSawUGen(rate, _syncFreq(i.%(_sz_syncFreq)), _sawFreq(i.%(_sz_sawFreq))))
//   }
//}
//case class SyncSawUGen(rate: Rate, syncFreq: UGenIn, sawFreq: UGenIn) extends SingleOutUGen(IIdxSeq(syncFreq, sawFreq))
object K2A {
   def ar(in: AnyGE) = apply(in)
}
case class K2A(in: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("K2A", audio, IIdxSeq( _in(i))))
   }
}
//case class K2AUGen(in: UGenIn) extends SingleOutUGen(IIdxSeq(in)) with AudioRated
object A2K {
   def kr(in: AnyGE) = apply(in)
}
case class A2K(in: AnyGE) extends SingleOutUGenSource[control] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("A2K", control, IIdxSeq( _in(i))))
   }
}
//case class A2KUGen(in: UGenIn) extends SingleOutUGen(IIdxSeq(in)) with ControlRated
object T2K {
   def kr(in: AnyGE) = apply(in)
}
case class T2K(in: AnyGE) extends SingleOutUGenSource[control] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("T2K", control, IIdxSeq( _in(i))))
   }
}
//case class T2KUGen(in: UGenIn) extends SingleOutUGen(IIdxSeq(in)) with ControlRated
object T2A {
   def ar(in: AnyGE) = apply(in)
}
case class T2A(in: AnyGE) extends SingleOutUGenSource[audio] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("T2A", audio, IIdxSeq( _in(i))))
   }
}
//case class T2AUGen(in: UGenIn) extends SingleOutUGen(IIdxSeq(in)) with AudioRated
object DC {
   def kr(in: AnyMulti ) = apply(control, in)
   def ar(in: AnyMulti ) = apply(audio, in)
}
case class DC[ R <: Rate ](rate: R, in: AnyMulti) extends MultiOutUGenSource[R] {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyGE] = in.mexpand
//      IIdxSeq.tabulate(_in.size)(i => DCUGen[ R ](rate, _in(i).expand))
      IIdxSeq.tabulate(_in.size)(i => { val _exp_in = _in(i).expand; new MultiOutUGen[ R ]("DC", rate, IIdxSeq.fill(_exp_in.size)(rate), _exp_in)})
   }
}
//case class DCUGen[ R <: Rate ](rate: R, in: IIdxSeq[UGenIn]) extends MultiOutUGen[ R ](IIdxSeq.fill(in.size)(rate), in)
object Silent {
   def ar: Silent = ar()
   def ar(numChannels: Int = 1) = apply(numChannels)
}
case class Silent(numChannels: Int) extends MultiOutUGenSource[audio] {
//   protected def expandUGens = IIdxSeq(SilentUGen(numChannels))
   protected def expandUGens = IIdxSeq(new MultiOutUGen[audio]("Silent", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq.empty))
}
//case class SilentUGen(numChannels: Int) extends MultiOutUGen[audio](IIdxSeq.fill(numChannels)(audio), IIdxSeq.empty) with AudioRated
/**
 * A line generator UGen that moves from a start value to the end value in a given duration.
 * 
 * @see [[de.sciss.synth.ugen.XLine]]
 * @see [[de.sciss.synth.ugen.EnvGen]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
object Line {
   def ar: Line[audio] = ar()
/**
 * @param start           Starting value. Note that this is read only once at initialization
 * @param end             Ending value. Note that this is read only once at initialization
 * @param dur             Duration in seconds. Note that this is read only once at initialization
 * @param doneAction      A done-action that is evaluated when the Line has reached the end value after the
 *                        given duration
 */
def ar(start: AnyGE = 0.0f, end: AnyGE = 1.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, start, end, dur, doneAction)
   def kr: Line[control] = kr()
/**
 * @param start           Starting value. Note that this is read only once at initialization
 * @param end             Ending value. Note that this is read only once at initialization
 * @param dur             Duration in seconds. Note that this is read only once at initialization
 * @param doneAction      A done-action that is evaluated when the Line has reached the end value after the
 *                        given duration
 */
def kr(start: AnyGE = 0.0f, end: AnyGE = 1.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, start, end, dur, doneAction)
}
/**
 * A line generator UGen that moves from a start value to the end value in a given duration.
 * 
 * @param start           Starting value. Note that this is read only once at initialization
 * @param end             Ending value. Note that this is read only once at initialization
 * @param dur             Duration in seconds. Note that this is read only once at initialization
 * @param doneAction      A done-action that is evaluated when the Line has reached the end value after the
 *                        given duration
 * 
 * @see [[de.sciss.synth.ugen.XLine]]
 * @see [[de.sciss.synth.ugen.EnvGen]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
case class Line[ R <: Rate ](rate: R, start: AnyGE, end: AnyGE, dur: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R] with HasSideEffect with HasDoneFlag {
   protected def expandUGens = {
      val _start: IIdxSeq[UGenIn] = start.expand
      val _end: IIdxSeq[UGenIn] = end.expand
      val _dur: IIdxSeq[UGenIn] = dur.expand
      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
      val _sz_start = _start.size
      val _sz_end = _end.size
      val _sz_dur = _dur.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = maxInt(_sz_start, _sz_end, _sz_dur, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Line", rate, IIdxSeq( _start(i.%(_sz_start)), _end(i.%(_sz_end)), _dur(i.%(_sz_dur)), _doneAction(i.%(_sz_doneAction)))))
   }
}
//case class LineUGen(rate: Rate, start: UGenIn, end: UGenIn, dur: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq(start, end, dur, doneAction)) with HasSideEffect with HasDoneFlag
//object XLine {
//   def ar: XLine = ar()
//   def ar(start: AnyGE = 1.0f, end: AnyGE = 2.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(audio, start, end, dur, doneAction)
//   def kr: XLine = kr()
//   def kr(start: AnyGE = 1.0f, end: AnyGE = 2.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(control, start, end, dur, doneAction)
//}
//case class XLine(rate: Rate, start: AnyGE, end: AnyGE, dur: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[XLineUGen] with HasSideEffect with HasDoneFlag {
//   protected def expandUGens = {
//      val _start: IIdxSeq[UGenIn] = start.expand
//      val _end: IIdxSeq[UGenIn] = end.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
//      val _sz_start = _start.size
//      val _sz_end = _end.size
//      val _sz_dur = _dur.size
//      val _sz_doneAction = _doneAction.size
//      val _exp_ = maxInt(_sz_start, _sz_end, _sz_dur, _sz_doneAction)
//      IIdxSeq.tabulate(_exp_)(i => XLineUGen(rate, _start(i.%(_sz_start)), _end(i.%(_sz_end)), _dur(i.%(_sz_dur)), _doneAction(i.%(_sz_doneAction))))
//   }
//}
//case class XLineUGen(rate: Rate, start: UGenIn, end: UGenIn, dur: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq(start, end, dur, doneAction)) with HasSideEffect with HasDoneFlag
//object Wrap {
//   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(scalar, in, lo, hi)
//   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(control, in, lo, hi)
//   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(audio, in, lo, hi)
//}
case class Wrap[ R <: Rate ](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _sz_in = _in.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Wrap", rate, IIdxSeq( _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)))))
   }
}
//case class WrapUGen(rate: Rate, in: UGenIn, lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(in, lo, hi))
object Fold {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(audio, in, lo, hi)
}
case class Fold[ R <: Rate ](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _sz_in = _in.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Fold", rate, IIdxSeq( _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)))))
   }
}
//case class FoldUGen(rate: Rate, in: UGenIn, lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(in, lo, hi))
object Clip {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(audio, in, lo, hi)
}
case class Clip[ R <: Rate ](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _sz_in = _in.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Clip", rate, IIdxSeq( _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)))))
   }
}
//case class ClipUGen(rate: Rate, in: UGenIn, lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(in, lo, hi))
//object AmpComp {
//   def ir: AmpComp = ir()
//   def ir(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply(scalar, freq, root, expon)
//   def kr: AmpComp = kr()
//   def kr(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply(control, freq, root, expon)
//   def ar: AmpComp = ar()
//   def ar(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply(audio, freq, root, expon)
//}
//case class AmpComp(rate: Rate, freq: AnyGE, root: AnyGE, expon: AnyGE) extends SingleOutUGenSource[AmpCompUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _root: IIdxSeq[UGenIn] = root.expand
//      val _expon: IIdxSeq[UGenIn] = expon.expand
//      val _sz_freq = _freq.size
//      val _sz_root = _root.size
//      val _sz_expon = _expon.size
//      val _exp_ = maxInt(_sz_freq, _sz_root, _sz_expon)
//      IIdxSeq.tabulate(_exp_)(i => AmpCompUGen(rate, _freq(i.%(_sz_freq)), _root(i.%(_sz_root)), _expon(i.%(_sz_expon))))
//   }
//}
//case class AmpCompUGen(rate: Rate, freq: UGenIn, root: UGenIn, expon: UGenIn) extends SingleOutUGen(IIdxSeq(freq, root, expon))
//object AmpCompA {
//   def ir: AmpCompA = ir()
//   def ir(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply(scalar, freq, root, minAmp, rootAmp)
//   def kr: AmpCompA = kr()
//   def kr(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply(control, freq, root, minAmp, rootAmp)
//   def ar: AmpCompA = ar()
//   def ar(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply(audio, freq, root, minAmp, rootAmp)
//}
//case class AmpCompA(rate: Rate, freq: AnyGE, root: AnyGE, minAmp: AnyGE, rootAmp: AnyGE) extends SingleOutUGenSource[AmpCompAUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _root: IIdxSeq[UGenIn] = root.expand
//      val _minAmp: IIdxSeq[UGenIn] = minAmp.expand
//      val _rootAmp: IIdxSeq[UGenIn] = rootAmp.expand
//      val _sz_freq = _freq.size
//      val _sz_root = _root.size
//      val _sz_minAmp = _minAmp.size
//      val _sz_rootAmp = _rootAmp.size
//      val _exp_ = maxInt(_sz_freq, _sz_root, _sz_minAmp, _sz_rootAmp)
//      IIdxSeq.tabulate(_exp_)(i => AmpCompAUGen(rate, _freq(i.%(_sz_freq)), _root(i.%(_sz_root)), _minAmp(i.%(_sz_minAmp)), _rootAmp(i.%(_sz_rootAmp))))
//   }
//}
//case class AmpCompAUGen(rate: Rate, freq: UGenIn, root: UGenIn, minAmp: UGenIn, rootAmp: UGenIn) extends SingleOutUGen(IIdxSeq(freq, root, minAmp, rootAmp))
//object InRange {
//   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(scalar, in, lo, hi)
//   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(control, in, lo, hi)
//   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply(audio, in, lo, hi)
//}
//case class InRange(rate: Rate, in: AnyGE, lo: AnyGE, hi: AnyGE) extends SingleOutUGenSource[InRangeUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _sz_in = _in.size
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _exp_ = maxInt(_sz_in, _sz_lo, _sz_hi)
//      IIdxSeq.tabulate(_exp_)(i => InRangeUGen(rate, _in(i.%(_sz_in)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
//   }
//}
//case class InRangeUGen(rate: Rate, in: UGenIn, lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(in, lo, hi))
//object InRect {
//   def ir(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply(scalar, x, y, left, top, right, bottom)
//   def kr(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply(control, x, y, left, top, right, bottom)
//   def ar(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply(audio, x, y, left, top, right, bottom)
//}
//case class InRect(rate: Rate, x: AnyGE, y: AnyGE, left: AnyGE, top: AnyGE, right: AnyGE, bottom: AnyGE) extends SingleOutUGenSource[InRectUGen] {
//   protected def expandUGens = {
//      val _x: IIdxSeq[UGenIn] = x.expand
//      val _y: IIdxSeq[UGenIn] = y.expand
//      val _left: IIdxSeq[UGenIn] = left.expand
//      val _top: IIdxSeq[UGenIn] = top.expand
//      val _right: IIdxSeq[UGenIn] = right.expand
//      val _bottom: IIdxSeq[UGenIn] = bottom.expand
//      val _sz_x = _x.size
//      val _sz_y = _y.size
//      val _sz_left = _left.size
//      val _sz_top = _top.size
//      val _sz_right = _right.size
//      val _sz_bottom = _bottom.size
//      val _exp_ = maxInt(_sz_x, _sz_y, _sz_left, _sz_top, _sz_right, _sz_bottom)
//      IIdxSeq.tabulate(_exp_)(i => InRectUGen(rate, _x(i.%(_sz_x)), _y(i.%(_sz_y)), _left(i.%(_sz_left)), _top(i.%(_sz_top)), _right(i.%(_sz_right)), _bottom(i.%(_sz_bottom))))
//   }
//}
//case class InRectUGen(rate: Rate, x: UGenIn, y: UGenIn, left: UGenIn, top: UGenIn, right: UGenIn, bottom: UGenIn) extends SingleOutUGen(IIdxSeq(x, y, left, top, right, bottom))
object LinExp {
   def ir(in: GE[ scalar ], srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[scalar](scalar, in, srcLo, srcHi, dstLo, dstHi)
   def kr(in: GE[ control ], srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[control](control, in, srcLo, srcHi, dstLo, dstHi)
   def ar(in: GE[ audio ], srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[audio](audio, in, srcLo, srcHi, dstLo, dstHi)
}
case class LinExp[ R <: Rate ](rate: R, in: GE[ R ], srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _srcLo: IIdxSeq[UGenIn] = srcLo.expand
      val _srcHi: IIdxSeq[UGenIn] = srcHi.expand
      val _dstLo: IIdxSeq[UGenIn] = dstLo.expand
      val _dstHi: IIdxSeq[UGenIn] = dstHi.expand
      val _sz_in = _in.size
      val _sz_srcLo = _srcLo.size
      val _sz_srcHi = _srcHi.size
      val _sz_dstLo = _dstLo.size
      val _sz_dstHi = _dstHi.size
      val _exp_ = maxInt(_sz_in, _sz_srcLo, _sz_srcHi, _sz_dstLo, _sz_dstHi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LinExp", rate, IIdxSeq( _in(i.%(_sz_in)), _srcLo(i.%(_sz_srcLo)), _srcHi(i.%(_sz_srcHi)), _dstLo(i.%(_sz_dstLo)), _dstHi(i.%(_sz_dstHi)))))
   }
}
//case class LinExpUGen(rate: Rate, in: UGenIn, srcLo: UGenIn, srcHi: UGenIn, dstLo: UGenIn, dstHi: UGenIn) extends SingleOutUGen(IIdxSeq(in, srcLo, srcHi, dstLo, dstHi))
object LinLin {
   def ir(in: GE[ scalar ], srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply(scalar, in, srcLo, srcHi, dstLo, dstHi)
   def kr(in: GE[ control ], srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply(control, in, srcLo, srcHi, dstLo, dstHi)
   def ar(in: GE[ audio ], srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply(audio, in, srcLo, srcHi, dstLo, dstHi)
}
case class LinLin[ R <: Rate ](rate: R, in: GE[ R ], srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _srcLo: IIdxSeq[UGenIn] = srcLo.expand
      val _srcHi: IIdxSeq[UGenIn] = srcHi.expand
      val _dstLo: IIdxSeq[UGenIn] = dstLo.expand
      val _dstHi: IIdxSeq[UGenIn] = dstHi.expand
      val _sz_in = _in.size
      val _sz_srcLo = _srcLo.size
      val _sz_srcHi = _srcHi.size
      val _sz_dstLo = _dstLo.size
      val _sz_dstHi = _dstHi.size
      val _exp_ = maxInt(_sz_in, _sz_srcLo, _sz_srcHi, _sz_dstLo, _sz_dstHi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LinLin", rate, IIdxSeq( _in(i.%(_sz_in)), _srcLo(i.%(_sz_srcLo)), _srcHi(i.%(_sz_srcHi)), _dstLo(i.%(_sz_dstLo)), _dstHi(i.%(_sz_dstHi)))))
   }
}
//case class LinLinUGen(rate: Rate, in: UGenIn, srcLo: UGenIn, srcHi: UGenIn, dstLo: UGenIn, dstHi: UGenIn) extends SingleOutUGen(IIdxSeq(in, srcLo, srcHi, dstLo, dstHi))
object EnvGen {
   def kr(envelope: AnyMulti, gate: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, envelope, gate, levelScale, levelBias, timeScale, doneAction)
   def ar(envelope: AnyMulti, gate: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, envelope, gate, levelScale, levelBias, timeScale, doneAction)
}
case class EnvGen[ R <: Rate ](rate: R, envelope: AnyMulti, gate: AnyGE, levelScale: AnyGE, levelBias: AnyGE, timeScale: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _gate: IIdxSeq[UGenIn] = gate.expand
      val _levelScale: IIdxSeq[UGenIn] = levelScale.expand
      val _levelBias: IIdxSeq[UGenIn] = levelBias.expand
      val _timeScale: IIdxSeq[UGenIn] = timeScale.expand
      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
      val _envelope: IIdxSeq[AnyGE] = envelope.mexpand
      val _sz_gate = _gate.size
      val _sz_levelScale = _levelScale.size
      val _sz_levelBias = _levelBias.size
      val _sz_timeScale = _timeScale.size
      val _sz_doneAction = _doneAction.size
      val _sz_envelope = _envelope.size
      val _exp_ = maxInt(_sz_gate, _sz_levelScale, _sz_levelBias, _sz_timeScale, _sz_doneAction, _sz_envelope)
//      IIdxSeq.tabulate(_exp_)(i => EnvGenUGen(rate, _envelope(i.%(_sz_envelope)).expand, _gate(i.%(_sz_gate)), _levelScale(i.%(_sz_levelScale)), _levelBias(i.%(_sz_levelBias)), _timeScale(i.%(_sz_timeScale)), _doneAction(i.%(_sz_doneAction))))
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("EnvGen", rate, IIdxSeq( _gate(i.%(_sz_gate)), _levelScale(i.%(_sz_levelScale)), _levelBias(i.%(_sz_levelBias)), _timeScale(i.%(_sz_timeScale)), _doneAction(i.%(_sz_doneAction))) ++ _envelope(i.%(_sz_envelope)).expand ))
   }
}
//case class EnvGenUGen(rate: Rate, envelope: IIdxSeq[UGenIn], gate: UGenIn, levelScale: UGenIn, levelBias: UGenIn, timeScale: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](gate, levelScale, levelBias, timeScale, doneAction).++(envelope))
//object Linen {
//   def kr: Linen = kr()
//   def kr(gate: AnyGE = 1.0f, attack: AnyGE = 0.01f, sustain: AnyGE = 1.0f, release: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(control, gate, attack, sustain, release, doneAction)
//   def ar: Linen = ar()
//   def ar(gate: AnyGE = 1.0f, attack: AnyGE = 0.01f, sustain: AnyGE = 1.0f, release: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply(audio, gate, attack, sustain, release, doneAction)
//}
//case class Linen(rate: Rate, gate: AnyGE, attack: AnyGE, sustain: AnyGE, release: AnyGE, doneAction: AnyGE) extends SingleOutUGenSource[LinenUGen] {
//   protected def expandUGens = {
//      val _gate: IIdxSeq[UGenIn] = gate.expand
//      val _attack: IIdxSeq[UGenIn] = attack.expand
//      val _sustain: IIdxSeq[UGenIn] = sustain.expand
//      val _release: IIdxSeq[UGenIn] = release.expand
//      val _doneAction: IIdxSeq[UGenIn] = doneAction.expand
//      val _sz_gate = _gate.size
//      val _sz_attack = _attack.size
//      val _sz_sustain = _sustain.size
//      val _sz_release = _release.size
//      val _sz_doneAction = _doneAction.size
//      val _exp_ = maxInt(_sz_gate, _sz_attack, _sz_sustain, _sz_release, _sz_doneAction)
//      IIdxSeq.tabulate(_exp_)(i => LinenUGen(rate, _gate(i.%(_sz_gate)), _attack(i.%(_sz_attack)), _sustain(i.%(_sz_sustain)), _release(i.%(_sz_release)), _doneAction(i.%(_sz_doneAction))))
//   }
//}
//case class LinenUGen(rate: Rate, gate: UGenIn, attack: UGenIn, sustain: UGenIn, release: UGenIn, doneAction: UGenIn) extends SingleOutUGen(IIdxSeq(gate, attack, sustain, release, doneAction))
//object IEnvGen {
//   def kr(envelope: Multi[AnyGE], index: AnyGE = 1.0f) = apply(control, envelope, index)
//   def ar(envelope: Multi[AnyGE], index: AnyGE = 1.0f) = apply(audio, envelope, index)
//}
//case class IEnvGen(rate: Rate, envelope: Multi[AnyGE], index: AnyGE) extends SingleOutUGenSource[IEnvGenUGen] {
//   protected def expandUGens = {
//      val _index: IIdxSeq[UGenIn] = index.expand
//      val _envelope: IIdxSeq[AnyGE] = envelope.mexpand
//      val _sz_index = _index.size
//      val _sz_envelope = _envelope.size
//      val _exp_ = maxInt(_sz_index, _sz_envelope)
//      IIdxSeq.tabulate(_exp_)(i => IEnvGenUGen(rate, _envelope(i.%(_sz_envelope)).expand, _index(i.%(_sz_index))))
//   }
//}
//case class IEnvGenUGen(rate: Rate, envelope: IIdxSeq[UGenIn], index: UGenIn) extends SingleOutUGen(IIdxSeq[UGenIn](index).++(envelope))
