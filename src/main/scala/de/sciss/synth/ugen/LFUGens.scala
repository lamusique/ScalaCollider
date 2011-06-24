/*
 * LFUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jun 24 13:05:41 BST 2011
 * ScalaCollider-UGens version: 0.12
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object Vibrato {
   def kr: Vibrato = kr()
   def kr(freq: GE = 440.0f, beat: GE = 6.0f, depth: GE = 0.02f, delay: GE = 0.0f, onset: GE = 0.0f, beatVar: GE = 0.04f, depthVar: GE = 0.1f, iphase: GE = 0.0f) = apply(control, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
   def ar: Vibrato = ar()
   def ar(freq: GE = 440.0f, beat: GE = 6.0f, depth: GE = 0.02f, delay: GE = 0.0f, onset: GE = 0.0f, beatVar: GE = 0.04f, depthVar: GE = 0.1f, iphase: GE = 0.0f) = apply(audio, freq, beat, depth, delay, onset, beatVar, depthVar, iphase)
}
final case class Vibrato(rate: Rate, freq: GE, beat: GE, depth: GE, delay: GE, onset: GE, beatVar: GE, depthVar: GE, iphase: GE) extends UGenSource.SingleOut("Vibrato") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, beat.expand, depth.expand, delay.expand, onset.expand, beatVar.expand, depthVar.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A non-band-limited pulse oscillator UGen.
 * Outputs a high value of one and a low value of zero.
 * 
 * @see [[de.sciss.synth.ugen.Pulse]]
 */
object LFPulse {
   def kr: LFPulse = kr()
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
   def kr(freq: GE = 440.0f, iphase: GE = 0.0f, width: GE = 0.5f) = apply(control, freq, iphase, width)
   def ar: LFPulse = ar()
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
   def ar(freq: GE = 440.0f, iphase: GE = 0.0f, width: GE = 0.5f) = apply(audio, freq, iphase, width)
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
final case class LFPulse(rate: Rate, freq: GE, iphase: GE, width: GE) extends UGenSource.SingleOut("LFPulse") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand, width.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A sawtooth oscillator UGen. The oscillator is creating an aliased sawtooth,
 * that is it does not use band-limiting. For a band-limited version use
 * `Saw` instead. The signal range is -1 to +1.
 * 
 * @see [[de.sciss.synth.ugen.Saw]]
 */
object LFSaw {
   def kr: LFSaw = kr()
   /**
    * @param freq            oscillator frequency in Hertz
    * @param iphase          initial phase offset. For efficiency reasons this is a
    *                        value ranging from -1 to 1 (thus equal to the initial output value).
    *                        Note that a phase of zero (default) means the wave starts at 0 and
    *                        rises to +1 before jumping down to -1. Use a phase of 1 to have the wave start at -1.
    */
   def kr(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(control, freq, iphase)
   def ar: LFSaw = ar()
   /**
    * @param freq            oscillator frequency in Hertz
    * @param iphase          initial phase offset. For efficiency reasons this is a
    *                        value ranging from -1 to 1 (thus equal to the initial output value).
    *                        Note that a phase of zero (default) means the wave starts at 0 and
    *                        rises to +1 before jumping down to -1. Use a phase of 1 to have the wave start at -1.
    */
   def ar(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(audio, freq, iphase)
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
final case class LFSaw(rate: Rate, freq: GE, iphase: GE) extends UGenSource.SingleOut("LFSaw") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LFPar {
   def kr: LFPar = kr()
   def kr(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(control, freq, iphase)
   def ar: LFPar = ar()
   def ar(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(audio, freq, iphase)
}
final case class LFPar(rate: Rate, freq: GE, iphase: GE) extends UGenSource.SingleOut("LFPar") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LFCub {
   def kr: LFCub = kr()
   def kr(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(control, freq, iphase)
   def ar: LFCub = ar()
   def ar(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(audio, freq, iphase)
}
final case class LFCub(rate: Rate, freq: GE, iphase: GE) extends UGenSource.SingleOut("LFCub") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LFTri {
   def kr: LFTri = kr()
   def kr(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(control, freq, iphase)
   def ar: LFTri = ar()
   def ar(freq: GE = 440.0f, iphase: GE = 0.0f) = apply(audio, freq, iphase)
}
final case class LFTri(rate: Rate, freq: GE, iphase: GE) extends UGenSource.SingleOut("LFTri") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LFGauss {
   def kr: LFGauss = kr()
   def kr(dur: GE = 1.0f, width: GE = 0.1f, iphase: GE = 0.0f, loop: GE = 1.0f, doneAction: GE = doNothing) = apply(control, dur, width, iphase, loop, doneAction)
   def ar: LFGauss = ar()
   def ar(dur: GE = 1.0f, width: GE = 0.1f, iphase: GE = 0.0f, loop: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, dur, width, iphase, loop, doneAction)
}
final case class LFGauss(rate: Rate, dur: GE, width: GE, iphase: GE, loop: GE, doneAction: GE) extends UGenSource.SingleOut("LFGauss") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(dur.expand, width.expand, iphase.expand, loop.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Impulse {
   def kr: Impulse = kr()
   def kr(freq: GE = 440.0f, phase: GE = 0.0f) = apply(control, freq, phase)
   def ar: Impulse = ar()
   def ar(freq: GE = 440.0f, phase: GE = 0.0f) = apply(audio, freq, phase)
}
final case class Impulse(rate: Rate, freq: GE, phase: GE) extends UGenSource.SingleOut("Impulse") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, phase.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object VarSaw {
   def kr: VarSaw = kr()
   def kr(freq: GE = 440.0f, iphase: GE = 0.0f, width: GE = 0.5f) = apply(control, freq, iphase, width)
   def ar: VarSaw = ar()
   def ar(freq: GE = 440.0f, iphase: GE = 0.0f, width: GE = 0.5f) = apply(audio, freq, iphase, width)
}
final case class VarSaw(rate: Rate, freq: GE, iphase: GE, width: GE) extends UGenSource.SingleOut("VarSaw") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, iphase.expand, width.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object SyncSaw {
   def kr: SyncSaw = kr()
   def kr(syncFreq: GE = 440.0f, sawFreq: GE = 440.0f) = apply(control, syncFreq, sawFreq)
   def ar: SyncSaw = ar()
   def ar(syncFreq: GE = 440.0f, sawFreq: GE = 440.0f) = apply(audio, syncFreq, sawFreq)
}
final case class SyncSaw(rate: Rate, syncFreq: GE, sawFreq: GE) extends UGenSource.SingleOut("SyncSaw") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(syncFreq.expand, sawFreq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object K2A {
   def ar(in: GE) = apply(in)
}
final case class K2A(in: GE) extends UGenSource.SingleOut("K2A") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
}
object A2K {
   def kr(in: GE) = apply(in)
}
final case class A2K(in: GE) extends UGenSource.SingleOut("A2K") with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args)
}
object T2K {
   def kr(in: GE) = apply(in)
}
final case class T2K(in: GE) extends UGenSource.SingleOut("T2K") with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args)
}
object T2A {
   def ar(in: GE) = apply(in)
}
final case class T2A(in: GE) extends UGenSource.SingleOut("T2A") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
}
object DC {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class DC(rate: Rate, in: GE) extends UGenSource.MultiOut("DC", in.numOutputs) {
   protected def makeUGens: UGenInLike = unwrap(in.expand.outputs)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(numOutputs)(rate), _args)
}
object Silent {
   def ar: Silent = ar()
   def ar(numChannels: Int = 1) = apply(numChannels)
}
final case class Silent(numChannels: Int) extends UGenSource.MultiOut("Silent", numChannels) with AudioRated {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numOutputs)(audio), _args)
}
/**
 * A line generator UGen that moves from a start value to the end value in a given duration.
 * 
 * @see [[de.sciss.synth.ugen.XLine]]
 * @see [[de.sciss.synth.ugen.EnvGen]]
 * @see [[de.sciss.synth.ugen.Ramp]]
 */
object Line {
   def ar: Line = ar()
   /**
    * @param start           Starting value. Note that this is read only once at initialization
    * @param end             Ending value. Note that this is read only once at initialization
    * @param dur             Duration in seconds. Note that this is read only once at initialization
    * @param doneAction      A done-action that is evaluated when the Line has reached the end value after the
    *                        given duration
    */
   def ar(start: GE = 0.0f, end: GE = 1.0f, dur: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, start, end, dur, doneAction)
   def kr: Line = kr()
   /**
    * @param start           Starting value. Note that this is read only once at initialization
    * @param end             Ending value. Note that this is read only once at initialization
    * @param dur             Duration in seconds. Note that this is read only once at initialization
    * @param doneAction      A done-action that is evaluated when the Line has reached the end value after the
    *                        given duration
    */
   def kr(start: GE = 0.0f, end: GE = 1.0f, dur: GE = 1.0f, doneAction: GE = doNothing) = apply(control, start, end, dur, doneAction)
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
final case class Line(rate: Rate, start: GE, end: GE, dur: GE, doneAction: GE) extends UGenSource.SingleOut("Line") with HasSideEffect with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, end.expand, dur.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object XLine {
   def ar: XLine = ar()
   def ar(start: GE = 1.0f, end: GE = 2.0f, dur: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, start, end, dur, doneAction)
   def kr: XLine = kr()
   def kr(start: GE = 1.0f, end: GE = 2.0f, dur: GE = 1.0f, doneAction: GE = doNothing) = apply(control, start, end, dur, doneAction)
}
final case class XLine(rate: Rate, start: GE, end: GE, dur: GE, doneAction: GE) extends UGenSource.SingleOut("XLine") with HasSideEffect with HasDoneFlag {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(start.expand, end.expand, dur.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Wrap {
   def ir(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(scalar, in, lo, hi)
   def kr(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(control, in, lo, hi)
   def ar(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(audio, in, lo, hi)
}
final case class Wrap(rate: Rate, in: GE, lo: GE, hi: GE) extends UGenSource.SingleOut("Wrap") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Fold {
   def ir(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(scalar, in, lo, hi)
   def kr(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(control, in, lo, hi)
   def ar(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(audio, in, lo, hi)
}
final case class Fold(rate: Rate, in: GE, lo: GE, hi: GE) extends UGenSource.SingleOut("Fold") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Clip {
   def ir(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(scalar, in, lo, hi)
   def kr(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(control, in, lo, hi)
   def ar(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(audio, in, lo, hi)
}
final case class Clip(rate: Rate, in: GE, lo: GE, hi: GE) extends UGenSource.SingleOut("Clip") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object AmpComp {
   def ir: AmpComp = ir()
   def ir(freq: GE = 60.midicps, root: GE = 60.midicps, expon: GE = 0.3333f) = apply(scalar, freq, root, expon)
   def kr: AmpComp = kr()
   def kr(freq: GE = 60.midicps, root: GE = 60.midicps, expon: GE = 0.3333f) = apply(control, freq, root, expon)
   def ar: AmpComp = ar()
   def ar(freq: GE = 60.midicps, root: GE = 60.midicps, expon: GE = 0.3333f) = apply(audio, freq, root, expon)
}
final case class AmpComp(rate: Rate, freq: GE, root: GE, expon: GE) extends UGenSource.SingleOut("AmpComp") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, root.expand, expon.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object AmpCompA {
   def ir: AmpCompA = ir()
   def ir(freq: GE = 1000.0f, root: GE = 0.0f, minAmp: GE = 0.32f, rootAmp: GE = 1.0f) = apply(scalar, freq, root, minAmp, rootAmp)
   def kr: AmpCompA = kr()
   def kr(freq: GE = 1000.0f, root: GE = 0.0f, minAmp: GE = 0.32f, rootAmp: GE = 1.0f) = apply(control, freq, root, minAmp, rootAmp)
   def ar: AmpCompA = ar()
   def ar(freq: GE = 1000.0f, root: GE = 0.0f, minAmp: GE = 0.32f, rootAmp: GE = 1.0f) = apply(audio, freq, root, minAmp, rootAmp)
}
final case class AmpCompA(rate: Rate, freq: GE, root: GE, minAmp: GE, rootAmp: GE) extends UGenSource.SingleOut("AmpCompA") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, root.expand, minAmp.expand, rootAmp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object InRange {
   def ir(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(scalar, in, lo, hi)
   def kr(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(control, in, lo, hi)
   def ar(in: GE, lo: GE = 0.0f, hi: GE = 1.0f) = apply(audio, in, lo, hi)
}
final case class InRange(rate: Rate, in: GE, lo: GE, hi: GE) extends UGenSource.SingleOut("InRange") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object InRect {
   def ir(x: GE, y: GE, left: GE = 0.0f, top: GE = 0.0f, right: GE = 1.0f, bottom: GE = 1.0f) = apply(scalar, x, y, left, top, right, bottom)
   def kr(x: GE, y: GE, left: GE = 0.0f, top: GE = 0.0f, right: GE = 1.0f, bottom: GE = 1.0f) = apply(control, x, y, left, top, right, bottom)
   def ar(x: GE, y: GE, left: GE = 0.0f, top: GE = 0.0f, right: GE = 1.0f, bottom: GE = 1.0f) = apply(audio, x, y, left, top, right, bottom)
}
final case class InRect(rate: Rate, x: GE, y: GE, left: GE, top: GE, right: GE, bottom: GE) extends UGenSource.SingleOut("InRect") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(x.expand, y.expand, left.expand, top.expand, right.expand, bottom.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A UGen which maps a linear range to an exponential range.
 * The equivalent formula is `(dstHi / dstLo).pow((in - srcLo) / (srcHi - srcLo)) * dstLo`.
 * 
 * '''Note''': No clipping is performed. If the input signal exceeds the input range, the output will also exceed its range.
 * 
 * @see [[de.sciss.synth.ugen.LinExp]]
 * @see [[de.sciss.synth.ugen.Clip]]
 */
object LinExp {
   def ir(in: GE, srcLo: GE = 0.0f, srcHi: GE = 1.0f, dstLo: GE = 1.0f, dstHi: GE = 2.0f) = apply(scalar, in, srcLo, srcHi, dstLo, dstHi)
   def kr(in: GE, srcLo: GE = 0.0f, srcHi: GE = 1.0f, dstLo: GE = 1.0f, dstHi: GE = 2.0f) = apply(control, in, srcLo, srcHi, dstLo, dstHi)
   def ar(in: GE, srcLo: GE = 0.0f, srcHi: GE = 1.0f, dstLo: GE = 1.0f, dstHi: GE = 2.0f) = apply(audio, in, srcLo, srcHi, dstLo, dstHi)
}
/**
 * A UGen which maps a linear range to an exponential range.
 * The equivalent formula is `(dstHi / dstLo).pow((in - srcLo) / (srcHi - srcLo)) * dstLo`.
 * 
 * '''Note''': No clipping is performed. If the input signal exceeds the input range, the output will also exceed its range.
 * 
 * @see [[de.sciss.synth.ugen.LinExp]]
 * @see [[de.sciss.synth.ugen.Clip]]
 */
final case class LinExp(rate: MaybeRate, in: GE, srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE) extends UGenSource.SingleOut("LinExp") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, srcLo.expand, srcHi.expand, dstLo.expand, dstHi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
/**
 * A UGen which maps a linear range to another linear range.
 * The equivalent formula is `(in - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo`.
 * 
 * '''Note''': No clipping is performed. If the input signal exceeds the input range, the output will also exceed its range.
 * 
 * @see [[de.sciss.synth.ugen.LinExp]]
 * @see [[de.sciss.synth.ugen.Clip]]
 */
object LinLin {
   
   /**
    * @param in              The input signal to convert.
    * @param srcLo           The lower limit of input range.
    * @param srcHi           The upper limit of input range.
    * @param dstLo           The lower limit of output range.
    * @param dstHi           The upper limit of output range.
    */
   def ir(in: GE, srcLo: GE = 0.0f, srcHi: GE = 1.0f, dstLo: GE = 0.0f, dstHi: GE = 1.0f) = apply(scalar, in, srcLo, srcHi, dstLo, dstHi)
   /**
    * @param in              The input signal to convert.
    * @param srcLo           The lower limit of input range.
    * @param srcHi           The upper limit of input range.
    * @param dstLo           The lower limit of output range.
    * @param dstHi           The upper limit of output range.
    */
   def kr(in: GE, srcLo: GE = 0.0f, srcHi: GE = 1.0f, dstLo: GE = 0.0f, dstHi: GE = 1.0f) = apply(control, in, srcLo, srcHi, dstLo, dstHi)
   /**
    * @param in              The input signal to convert.
    * @param srcLo           The lower limit of input range.
    * @param srcHi           The upper limit of input range.
    * @param dstLo           The lower limit of output range.
    * @param dstHi           The upper limit of output range.
    */
   def ar(in: GE, srcLo: GE = 0.0f, srcHi: GE = 1.0f, dstLo: GE = 0.0f, dstHi: GE = 1.0f) = apply(audio, in, srcLo, srcHi, dstLo, dstHi)
}
/**
 * A UGen which maps a linear range to another linear range.
 * The equivalent formula is `(in - srcLo) / (srcHi - srcLo) * (dstHi - dstLo) + dstLo`.
 * 
 * '''Note''': No clipping is performed. If the input signal exceeds the input range, the output will also exceed its range.
 * 
 * @param in              The input signal to convert.
 * @param srcLo           The lower limit of input range.
 * @param srcHi           The upper limit of input range.
 * @param dstLo           The lower limit of output range.
 * @param dstHi           The upper limit of output range.
 * 
 * @see [[de.sciss.synth.ugen.LinExp]]
 * @see [[de.sciss.synth.ugen.Clip]]
 */
final case class LinLin(rate: MaybeRate, in: GE, srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE) extends UGenSource.SingleOut("LinLin") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, srcLo.expand, srcHi.expand, dstLo.expand, dstHi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = {
      val _rate = rate.?|(_args(0).rate)
      new UGen.SingleOut(name, _rate, _args)
   }
}
object EnvGen {
   def kr(envelope: GE, gate: GE = 1.0f, levelScale: GE = 1.0f, levelBias: GE = 0.0f, timeScale: GE = 1.0f, doneAction: GE = doNothing) = apply(control, envelope, gate, levelScale, levelBias, timeScale, doneAction)
   def ar(envelope: GE, gate: GE = 1.0f, levelScale: GE = 1.0f, levelBias: GE = 0.0f, timeScale: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, envelope, gate, levelScale, levelBias, timeScale, doneAction)
}
final case class EnvGen(rate: Rate, envelope: GE, gate: GE, levelScale: GE, levelBias: GE, timeScale: GE, doneAction: GE) extends UGenSource.SingleOut("EnvGen") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(gate.expand, levelScale.expand, levelBias.expand, timeScale.expand, doneAction.expand).++(envelope.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Linen {
   def kr: Linen = kr()
   def kr(gate: GE = 1.0f, attack: GE = 0.01f, sustain: GE = 1.0f, release: GE = 1.0f, doneAction: GE = doNothing) = apply(control, gate, attack, sustain, release, doneAction)
   def ar: Linen = ar()
   def ar(gate: GE = 1.0f, attack: GE = 0.01f, sustain: GE = 1.0f, release: GE = 1.0f, doneAction: GE = doNothing) = apply(audio, gate, attack, sustain, release, doneAction)
}
final case class Linen(rate: Rate, gate: GE, attack: GE, sustain: GE, release: GE, doneAction: GE) extends UGenSource.SingleOut("Linen") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(gate.expand, attack.expand, sustain.expand, release.expand, doneAction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object IEnvGen {
   def kr(envelope: GE, index: GE = 1.0f) = apply(control, envelope, index)
   def ar(envelope: GE, index: GE = 1.0f) = apply(audio, envelope, index)
}
final case class IEnvGen(rate: Rate, envelope: GE, index: GE) extends UGenSource.SingleOut("IEnvGen") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(index.expand).++(envelope.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}