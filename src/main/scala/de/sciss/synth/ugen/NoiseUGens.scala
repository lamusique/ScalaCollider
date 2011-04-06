/*
 * NoiseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Apr 06 02:27:48 BST 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object WhiteNoise {
   def kr: WhiteNoise = kr()
   def kr(mul: GE = 1.0f) = apply(control, mul)
   def ar: WhiteNoise = ar()
   def ar(mul: GE = 1.0f) = apply(audio, mul)
}
final case class WhiteNoise(rate: Rate, mul: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("WhiteNoise", rate, _args), _args(0))
}
object GrayNoise {
   def kr: GrayNoise = kr()
   def kr(mul: GE = 1.0f) = apply(control, mul)
   def ar: GrayNoise = ar()
   def ar(mul: GE = 1.0f) = apply(audio, mul)
}
final case class GrayNoise(rate: Rate, mul: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("GrayNoise", rate, _args), _args(0))
}
object ClipNoise {
   def kr: ClipNoise = kr()
   def kr(mul: GE = 1.0f) = apply(control, mul)
   def ar: ClipNoise = ar()
   def ar(mul: GE = 1.0f) = apply(audio, mul)
}
final case class ClipNoise(rate: Rate, mul: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("ClipNoise", rate, _args), _args(0))
}
object PinkNoise {
   def kr: PinkNoise = kr()
   def kr(mul: GE = 1.0f) = apply(control, mul)
   def ar: PinkNoise = ar()
   def ar(mul: GE = 1.0f) = apply(audio, mul)
}
final case class PinkNoise(rate: Rate, mul: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("PinkNoise", rate, _args), _args(0))
}
object BrownNoise {
   def kr: BrownNoise = kr()
   def kr(mul: GE = 1.0f) = apply(control, mul)
   def ar: BrownNoise = ar()
   def ar(mul: GE = 1.0f) = apply(audio, mul)
}
final case class BrownNoise(rate: Rate, mul: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("BrownNoise", rate, _args), _args(0))
}
/**
 * A UGen generating random impulses with values ranging from
 * `0` to `+1`. The pulse duration is one sample for audio-rate
 * and one block for control-rate operation.
 * 
 * @see [[de.sciss.synth.ugen.Dust2]]
 * @see [[de.sciss.synth.ugen.TRand]]
 */
object Dust {
   def kr: Dust = kr()
   /**
    * @param density         the average number of impulses per second
    */
   def kr(density: GE = 1.0f) = apply(control, density)
   def ar: Dust = ar()
   /**
    * @param density         the average number of impulses per second
    */
   def ar(density: GE = 1.0f) = apply(audio, density)
}
/**
 * A UGen generating random impulses with values ranging from
 * `0` to `+1`. The pulse duration is one sample for audio-rate
 * and one block for control-rate operation.
 * 
 * @param density         the average number of impulses per second
 * 
 * @see [[de.sciss.synth.ugen.Dust2]]
 * @see [[de.sciss.synth.ugen.TRand]]
 */
final case class Dust(rate: Rate, density: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(density.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dust", rate, _args)
}
/**
 * A UGen generating random impulses with values ranging from
 * `-1` to `+1`. The pulse duration is one sample for audio-rate
 * and one block for control-rate operation.
 * 
 * @see [[de.sciss.synth.ugen.Dust]]
 * @see [[de.sciss.synth.ugen.TRand]]
 */
object Dust2 {
   def kr: Dust2 = kr()
   /**
    * @param density         the average number of impulses per second
    */
   def kr(density: GE = 1.0f) = apply(control, density)
   def ar: Dust2 = ar()
   /**
    * @param density         the average number of impulses per second
    */
   def ar(density: GE = 1.0f) = apply(audio, density)
}
/**
 * A UGen generating random impulses with values ranging from
 * `-1` to `+1`. The pulse duration is one sample for audio-rate
 * and one block for control-rate operation.
 * 
 * @param density         the average number of impulses per second
 * 
 * @see [[de.sciss.synth.ugen.Dust]]
 * @see [[de.sciss.synth.ugen.TRand]]
 */
final case class Dust2(rate: Rate, density: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(density.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dust2", rate, _args)
}
object Crackle {
   def kr: Crackle = kr()
   def kr(chaos: GE = 1.5f) = apply(control, chaos)
   def ar: Crackle = ar()
   def ar(chaos: GE = 1.5f) = apply(audio, chaos)
}
final case class Crackle(rate: Rate, chaos: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chaos.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Crackle", rate, _args)
}
object Logistic {
   def kr: Logistic = kr()
   def kr(chaos: GE = 3.0f, freq: GE = 1000.0f, init: GE = 0.5f) = apply(control, chaos, freq, init)
   def ar: Logistic = ar()
   def ar(chaos: GE = 3.0f, freq: GE = 1000.0f, init: GE = 0.5f) = apply(audio, chaos, freq, init)
}
final case class Logistic(rate: Rate, chaos: GE, freq: GE, init: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chaos.expand, freq.expand, init.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Logistic", rate, _args)
}
object Hasher {
   def kr(in: GE) = apply(control, in)
   def ar(in: GE) = apply(audio, in)
}
final case class Hasher(rate: Rate, in: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Hasher", rate, _args)
}
object MantissaMask {
   def kr(in: GE, bits: GE = 3.0f) = apply(control, in, bits)
   def ar(in: GE, bits: GE = 3.0f) = apply(audio, in, bits)
}
final case class MantissaMask(rate: Rate, in: GE, bits: GE) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, bits.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MantissaMask", rate, _args)
}
object LFClipNoise {
   def kr: LFClipNoise = kr()
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFClipNoise = ar()
   def ar(freq: GE = 500.0f) = apply(audio, freq)
}
final case class LFClipNoise(rate: Rate, freq: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFClipNoise", rate, _args)
}
object LFNoise0 {
   def kr: LFNoise0 = kr()
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFNoise0 = ar()
   def ar(freq: GE = 500.0f) = apply(audio, freq)
}
final case class LFNoise0(rate: Rate, freq: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFNoise0", rate, _args)
}
object LFNoise1 {
   def kr: LFNoise1 = kr()
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFNoise1 = ar()
   def ar(freq: GE = 500.0f) = apply(audio, freq)
}
final case class LFNoise1(rate: Rate, freq: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFNoise1", rate, _args)
}
object LFNoise2 {
   def kr: LFNoise2 = kr()
   def kr(freq: GE = 500.0f) = apply(control, freq)
   def ar: LFNoise2 = ar()
   def ar(freq: GE = 500.0f) = apply(audio, freq)
}
final case class LFNoise2(rate: Rate, freq: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFNoise2", rate, _args)
}
final case class Rand(lo: GE = 0.0f, hi: GE = 1.0f) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Rand", scalar, _args)
}
final case class IRand(lo: GE = 0.0f, hi: GE = 127.0f) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("IRand", scalar, _args)
}
object TRand {
   def kr(lo: GE = 0.0f, hi: GE = 1.0f, trig: GE) = apply(control, lo, hi, trig)
   def ar(lo: GE = 0.0f, hi: GE = 1.0f, trig: GE) = apply(audio, lo, hi, trig)
}
final case class TRand(rate: Rate, lo: GE, hi: GE, trig: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TRand", rate, _args)
}
object TExpRand {
   def kr(lo: GE = 0.01f, hi: GE = 1.0f, trig: GE) = apply(control, lo, hi, trig)
   def ar(lo: GE = 0.01f, hi: GE = 1.0f, trig: GE) = apply(audio, lo, hi, trig)
}
final case class TExpRand(rate: Rate, lo: GE, hi: GE, trig: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TExpRand", rate, _args)
}
object TIRand {
   def kr(lo: GE = 0.0f, hi: GE = 127.0f, trig: GE) = apply(control, lo, hi, trig)
   def ar(lo: GE = 0.0f, hi: GE = 127.0f, trig: GE) = apply(audio, lo, hi, trig)
}
final case class TIRand(rate: Rate, lo: GE, hi: GE, trig: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TIRand", rate, _args)
}
final case class NRand(lo: GE = 0.0f, hi: GE = 127.0f, n: GE = 0.0f) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, n.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NRand", scalar, _args)
}
final case class LinRand(lo: GE = 0.0f, hi: GE = 127.0f, minMax: GE = 0.0f) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, minMax.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinRand", scalar, _args)
}
final case class ExpRand(lo: GE = 0.01f, hi: GE = 1.0f) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("ExpRand", scalar, _args)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object CoinGate {
   def kr(in: GE, prob: GE = 0.5f) = apply(control, in, prob)
   def ar(in: GE, prob: GE = 0.5f) = apply(audio, in, prob)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class CoinGate(rate: Rate, in: GE, prob: GE) extends UGenSource.SingleOut with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(prob.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("CoinGate", rate, _args)
}
object RandSeed {
   def ir: RandSeed = ir()
   def ir(trig: GE = 1.0f, seed: GE = 56789.0f) = apply(scalar, trig, seed)
   def kr: RandSeed = kr()
   def kr(trig: GE = 1.0f, seed: GE = 56789.0f) = apply(control, trig, seed)
}
final case class RandSeed(rate: Rate, trig: GE, seed: GE) extends UGenSource.SingleOut with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, seed.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RandSeed", rate, _args)
}
object RandID {
   def ir: RandID = ir()
   def ir(id: GE = 0.0f) = apply(scalar, id)
   def kr: RandID = kr()
   def kr(id: GE = 0.0f) = apply(control, id)
}
final case class RandID(rate: Rate, id: GE) extends UGenSource.SingleOut with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(id.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RandID", rate, _args)
}