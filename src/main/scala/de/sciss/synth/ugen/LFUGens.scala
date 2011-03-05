/*
 * LFUGens.scala
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
object Vibrato {
   def kr: Vibrato[control] = kr()
   def kr(freq: AnyGE = 440.0f, beat: AnyGE = 6.0f, depth: AnyGE = 0.02f, delay: AnyGE = 0.0f, onset: AnyGE = 0.0f, beatVar: AnyGE = 0.04f, depthVar: AnyGE = 0.1f, iphase: AnyGE = 0.0f) = apply[control](control, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
   def ar: Vibrato[audio] = ar()
   def ar(freq: AnyGE = 440.0f, beat: AnyGE = 6.0f, depth: AnyGE = 0.02f, delay: AnyGE = 0.0f, onset: AnyGE = 0.0f, beatVar: AnyGE = 0.04f, depthVar: AnyGE = 0.1f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
}
final case class Vibrato[R <: Rate](rate: R, freq: AnyGE, beat: AnyGE, depth: AnyGE, delay: AnyGE, onset: AnyGE, beatVar: AnyGE, depthVar: AnyGE, iphase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, beat.expand, depth.expand, delay.expand, onset.expand, beatVar.expand, depthVar.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Vibrato", rate, _args)
}
/**
 * A non-band-limited pulse oscillator UGen.
 * Outputs a high value of one and a low value of zero.
 * 
 * @see [[de.sciss.synth.ugen.Pulse]]
 */
object LFPulse {
   def kr: LFPulse[control] = kr()
   /**
    * @param freq            oscillator frequency in Hertz
    * @param iphase          initial phase offset in cycles ( `0..1` ). If you think
    *                        of a buffer of one cycle of the waveform, this is the starting offset
    *                        into this buffer. Hence, an `iphase` of `0.25` means that you will hear
    *                        the first impulse after `0.75` periods! If you prefer to specify the
    *                        perceived delay instead, you could use an `iphase` of `-0.25 + 1` which
    *                        is more intuitive. Note that the phase is not automatically wrapped
    *                        into the range of `0..1`, so putting an `iphase` of `-0.25` currently
    *                        results in a strange initial signal which only stabilizes to the
    *                        correct behaviour after one period!
    * @param width           pulse width duty cycle from zero to one. If you want to
    *                        specify the width rather in seconds, you can use the formula
    *                        `width = freq * dur`, e.g. for a single sample impulse use
    *                        `width = freq * SampleDur.ir`.
    */
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[control](control, freq, iphase, width)
   def ar: LFPulse[audio] = ar()
   /**
    * @param freq            oscillator frequency in Hertz
    * @param iphase          initial phase offset in cycles ( `0..1` ). If you think
    *                        of a buffer of one cycle of the waveform, this is the starting offset
    *                        into this buffer. Hence, an `iphase` of `0.25` means that you will hear
    *                        the first impulse after `0.75` periods! If you prefer to specify the
    *                        perceived delay instead, you could use an `iphase` of `-0.25 + 1` which
    *                        is more intuitive. Note that the phase is not automatically wrapped
    *                        into the range of `0..1`, so putting an `iphase` of `-0.25` currently
    *                        results in a strange initial signal which only stabilizes to the
    *                        correct behaviour after one period!
    * @param width           pulse width duty cycle from zero to one. If you want to
    *                        specify the width rather in seconds, you can use the formula
    *                        `width = freq * dur`, e.g. for a single sample impulse use
    *                        `width = freq * SampleDur.ir`.
    */
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[audio](audio, freq, iphase, width)
}
/**
 * A non-band-limited pulse oscillator UGen.
 * Outputs a high value of one and a low value of zero.
 * 
 * @param freq            oscillator frequency in Hertz
 * @param iphase          initial phase offset in cycles ( `0..1` ). If you think
 *                        of a buffer of one cycle of the waveform, this is the starting offset
 *                        into this buffer. Hence, an `iphase` of `0.25` means that you will hear
 *                        the first impulse after `0.75` periods! If you prefer to specify the
 *                        perceived delay instead, you could use an `iphase` of `-0.25 + 1` which
 *                        is more intuitive. Note that the phase is not automatically wrapped
 *                        into the range of `0..1`, so putting an `iphase` of `-0.25` currently
 *                        results in a strange initial signal which only stabilizes to the
 *                        correct behaviour after one period!
 * @param width           pulse width duty cycle from zero to one. If you want to
 *                        specify the width rather in seconds, you can use the formula
 *                        `width = freq * dur`, e.g. for a single sample impulse use
 *                        `width = freq * SampleDur.ir`.
 * 
 * @see [[de.sciss.synth.ugen.Pulse]]
 */
final case class LFPulse[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE, width: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand, width.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFPulse", rate, _args)
}
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
final case class LFSaw[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFSaw", rate, _args)
}
object LFPar {
   def kr: LFPar[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFPar[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
final case class LFPar[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFPar", rate, _args)
}
object LFCub {
   def kr: LFCub[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFCub[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
final case class LFCub[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFCub", rate, _args)
}
object LFTri {
   def kr: LFTri[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[control](control, freq, iphase)
   def ar: LFTri[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f) = apply[audio](audio, freq, iphase)
}
final case class LFTri[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFTri", rate, _args)
}
object LFGauss {
   def kr: LFGauss[control] = kr()
   def kr(dur: AnyGE = 1.0f, width: AnyGE = 0.1f, iphase: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, dur, width, iphase, loop, doneAction)
   def ar: LFGauss[audio] = ar()
   def ar(dur: AnyGE = 1.0f, width: AnyGE = 0.1f, iphase: AnyGE = 0.0f, loop: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, dur, width, iphase, loop, doneAction)
}
final case class LFGauss[R <: Rate](rate: R, dur: AnyGE, width: AnyGE, iphase: AnyGE, loop: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(dur.expand, width.expand, iphase.expand, loop.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFGauss", rate, _args)
}
object Impulse {
   def kr: Impulse[control] = kr()
   def kr(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[control](control, freq, phase)
   def ar: Impulse[audio] = ar()
   def ar(freq: AnyGE = 440.0f, phase: AnyGE = 0.0f) = apply[audio](audio, freq, phase)
}
final case class Impulse[R <: Rate](rate: R, freq: AnyGE, phase: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Impulse", rate, _args)
}
object VarSaw {
   def kr: VarSaw[control] = kr()
   def kr(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[control](control, freq, iphase, width)
   def ar: VarSaw[audio] = ar()
   def ar(freq: AnyGE = 440.0f, iphase: AnyGE = 0.0f, width: AnyGE = 0.5f) = apply[audio](audio, freq, iphase, width)
}
final case class VarSaw[R <: Rate](rate: R, freq: AnyGE, iphase: AnyGE, width: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand, width.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("VarSaw", rate, _args)
}
object SyncSaw {
   def kr: SyncSaw[control] = kr()
   def kr(syncFreq: AnyGE = 440.0f, sawFreq: AnyGE = 440.0f) = apply[control](control, syncFreq, sawFreq)
   def ar: SyncSaw[audio] = ar()
   def ar(syncFreq: AnyGE = 440.0f, sawFreq: AnyGE = 440.0f) = apply[audio](audio, syncFreq, sawFreq)
}
final case class SyncSaw[R <: Rate](rate: R, syncFreq: AnyGE, sawFreq: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(syncFreq.expand, sawFreq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("SyncSaw", rate, _args)
}
object K2A {
   def ar(in: AnyGE) = apply(in)
}
final case class K2A(in: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("K2A", audio, _args)
}
object A2K {
   def kr(in: AnyGE) = apply(in)
}
final case class A2K(in: AnyGE) extends UGenSource.SingleOut[control] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("A2K", control, _args)
}
object T2K {
   def kr(in: AnyGE) = apply(in)
}
final case class T2K(in: AnyGE) extends UGenSource.SingleOut[control] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("T2K", control, _args)
}
object T2A {
   def ar(in: AnyGE) = apply(in)
}
final case class T2A(in: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("T2A", audio, _args)
}
object DC {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class DC[R <: Rate](rate: R, in: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(in.expand.outputs)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("DC", rate, IIdxSeq.fill(_args.size)(rate), _args)
}
object Silent {
   def ar: Silent = ar()
   def ar(numChannels: Int = 1) = apply(numChannels)
}
final case class Silent(numChannels: Int) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Silent", audio, IIdxSeq.fill(numChannels)(audio), _args)
}
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
final case class Line[R <: Rate](rate: R, start: AnyGE, end: AnyGE, dur: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, end.expand, dur.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Line", rate, _args)
}
object XLine {
   def ar: XLine[audio] = ar()
   def ar(start: AnyGE = 1.0f, end: AnyGE = 2.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, start, end, dur, doneAction)
   def kr: XLine[control] = kr()
   def kr(start: AnyGE = 1.0f, end: AnyGE = 2.0f, dur: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, start, end, dur, doneAction)
}
final case class XLine[R <: Rate](rate: R, start: AnyGE, end: AnyGE, dur: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, end.expand, dur.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("XLine", rate, _args)
}
object Wrap {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
final case class Wrap[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Wrap", rate, _args)
}
object Fold {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
final case class Fold[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Fold", rate, _args)
}
object Clip {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
final case class Clip[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Clip", rate, _args)
}
object AmpComp {
   def ir: AmpComp[scalar] = ir()
   def ir(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply[scalar](scalar, freq, root, expon)
   def kr: AmpComp[control] = kr()
   def kr(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply[control](control, freq, root, expon)
   def ar: AmpComp[audio] = ar()
   def ar(freq: AnyGE = 60.midicps, root: AnyGE = 60.midicps, expon: AnyGE = 0.3333f) = apply[audio](audio, freq, root, expon)
}
final case class AmpComp[R <: Rate](rate: R, freq: AnyGE, root: AnyGE, expon: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, root.expand, expon.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("AmpComp", rate, _args)
}
object AmpCompA {
   def ir: AmpCompA[scalar] = ir()
   def ir(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply[scalar](scalar, freq, root, minAmp, rootAmp)
   def kr: AmpCompA[control] = kr()
   def kr(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply[control](control, freq, root, minAmp, rootAmp)
   def ar: AmpCompA[audio] = ar()
   def ar(freq: AnyGE = 1000.0f, root: AnyGE = 0.0f, minAmp: AnyGE = 0.32f, rootAmp: AnyGE = 1.0f) = apply[audio](audio, freq, root, minAmp, rootAmp)
}
final case class AmpCompA[R <: Rate](rate: R, freq: AnyGE, root: AnyGE, minAmp: AnyGE, rootAmp: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, root.expand, minAmp.expand, rootAmp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("AmpCompA", rate, _args)
}
object InRange {
   def ir(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[scalar](scalar, in, lo, hi)
   def kr(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[control](control, in, lo, hi)
   def ar(in: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) = apply[audio](audio, in, lo, hi)
}
final case class InRange[R <: Rate](rate: R, in: AnyGE, lo: AnyGE, hi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("InRange", rate, _args)
}
object InRect {
   def ir(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply[scalar](scalar, x, y, left, top, right, bottom)
   def kr(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply[control](control, x, y, left, top, right, bottom)
   def ar(x: AnyGE, y: AnyGE, left: AnyGE = 0.0f, top: AnyGE = 0.0f, right: AnyGE = 1.0f, bottom: AnyGE = 1.0f) = apply[audio](audio, x, y, left, top, right, bottom)
}
final case class InRect[R <: Rate](rate: R, x: AnyGE, y: AnyGE, left: AnyGE, top: AnyGE, right: AnyGE, bottom: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(x.expand, y.expand, left.expand, top.expand, right.expand, bottom.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("InRect", rate, _args)
}
object LinExp {
   def ir(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[scalar](scalar, in, srcLo, srcHi, dstLo, dstHi)
   def kr(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[control](control, in, srcLo, srcHi, dstLo, dstHi)
   def ar(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 1.0f, dstHi: AnyGE = 2.0f) = apply[audio](audio, in, srcLo, srcHi, dstLo, dstHi)
}
final case class LinExp[R <: Rate](rate: R, in: AnyGE, srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, srcLo.expand, srcHi.expand, dstLo.expand, dstHi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinExp", rate, _args)
}
object LinLin {
   def ir(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply[scalar](scalar, in, srcLo, srcHi, dstLo, dstHi)
   def kr(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply[control](control, in, srcLo, srcHi, dstLo, dstHi)
   def ar(in: AnyGE, srcLo: AnyGE = 0.0f, srcHi: AnyGE = 1.0f, dstLo: AnyGE = 0.0f, dstHi: AnyGE = 1.0f) = apply[audio](audio, in, srcLo, srcHi, dstLo, dstHi)
}
final case class LinLin[R <: Rate](rate: R, in: AnyGE, srcLo: AnyGE, srcHi: AnyGE, dstLo: AnyGE, dstHi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, srcLo.expand, srcHi.expand, dstLo.expand, dstHi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinLin", rate, _args)
}
object EnvGen {
   def kr(envelope: AnyGE, gate: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, envelope, gate, levelScale, levelBias, timeScale, doneAction)
   def ar(envelope: AnyGE, gate: AnyGE = 1.0f, levelScale: AnyGE = 1.0f, levelBias: AnyGE = 0.0f, timeScale: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, envelope, gate, levelScale, levelBias, timeScale, doneAction)
}
final case class EnvGen[R <: Rate](rate: R, envelope: AnyGE, gate: AnyGE, levelScale: AnyGE, levelBias: AnyGE, timeScale: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(gate.expand, levelScale.expand, levelBias.expand, timeScale.expand, doneAction.expand).++(envelope.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("EnvGen", rate, _args)
}
object Linen {
   def kr: Linen[control] = kr()
   def kr(gate: AnyGE = 1.0f, attack: AnyGE = 0.01f, sustain: AnyGE = 1.0f, release: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[control](control, gate, attack, sustain, release, doneAction)
   def ar: Linen[audio] = ar()
   def ar(gate: AnyGE = 1.0f, attack: AnyGE = 0.01f, sustain: AnyGE = 1.0f, release: AnyGE = 1.0f, doneAction: AnyGE = doNothing) = apply[audio](audio, gate, attack, sustain, release, doneAction)
}
final case class Linen[R <: Rate](rate: R, gate: AnyGE, attack: AnyGE, sustain: AnyGE, release: AnyGE, doneAction: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(gate.expand, attack.expand, sustain.expand, release.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Linen", rate, _args)
}
object IEnvGen {
   def kr(envelope: AnyGE, index: AnyGE = 1.0f) = apply[control](control, envelope, index)
   def ar(envelope: AnyGE, index: AnyGE = 1.0f) = apply[audio](audio, envelope, index)
}
final case class IEnvGen[R <: Rate](rate: R, envelope: AnyGE, index: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(index.expand).++(envelope.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("IEnvGen", rate, _args)
}