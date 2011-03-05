/*
 * NoiseUGens.scala
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
object WhiteNoise {
   def kr: WhiteNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: WhiteNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class WhiteNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends UGenSource.SingleOut[T] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("WhiteNoise", rate, _args), _args(0))
}
object GrayNoise {
   def kr: GrayNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: GrayNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class GrayNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends UGenSource.SingleOut[T] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("GrayNoise", rate, _args), _args(0))
}
object ClipNoise {
   def kr: ClipNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: ClipNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class ClipNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends UGenSource.SingleOut[T] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("ClipNoise", rate, _args), _args(0))
}
object PinkNoise {
   def kr: PinkNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: PinkNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class PinkNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends UGenSource.SingleOut[T] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(mul.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = BinaryOp.Times.make1(new UGen.SingleOut("PinkNoise", rate, _args), _args(0))
}
object BrownNoise {
   def kr: BrownNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: BrownNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class BrownNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends UGenSource.SingleOut[T] with UsesRandSeed {
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
   def kr: Dust[control] = kr()
   /**
    * @param density         the average number of impulses per second
    */
   def kr(density: AnyGE = 1.0f) = apply[control](control, density)
   def ar: Dust[audio] = ar()
   /**
    * @param density         the average number of impulses per second
    */
   def ar(density: AnyGE = 1.0f) = apply[audio](audio, density)
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
final case class Dust[R <: Rate](rate: R, density: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
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
   def kr: Dust2[control] = kr()
   /**
    * @param density         the average number of impulses per second
    */
   def kr(density: AnyGE = 1.0f) = apply[control](control, density)
   def ar: Dust2[audio] = ar()
   /**
    * @param density         the average number of impulses per second
    */
   def ar(density: AnyGE = 1.0f) = apply[audio](audio, density)
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
final case class Dust2[R <: Rate](rate: R, density: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(density.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Dust2", rate, _args)
}
object Crackle {
   def kr: Crackle[control] = kr()
   def kr(chaos: AnyGE = 1.5f) = apply[control](control, chaos)
   def ar: Crackle[audio] = ar()
   def ar(chaos: AnyGE = 1.5f) = apply[audio](audio, chaos)
}
final case class Crackle[R <: Rate](rate: R, chaos: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chaos.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Crackle", rate, _args)
}
object Logistic {
   def kr: Logistic[control] = kr()
   def kr(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply[control](control, chaos, freq, init)
   def ar: Logistic[audio] = ar()
   def ar(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply[audio](audio, chaos, freq, init)
}
final case class Logistic[R <: Rate](rate: R, chaos: AnyGE, freq: AnyGE, init: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chaos.expand, freq.expand, init.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Logistic", rate, _args)
}
object Hasher {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Hasher[R <: Rate](rate: R, in: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Hasher", rate, _args)
}
object MantissaMask {
   def kr(in: AnyGE, bits: AnyGE = 3.0f) = apply[control](control, in, bits)
   def ar(in: AnyGE, bits: AnyGE = 3.0f) = apply[audio](audio, in, bits)
}
final case class MantissaMask[R <: Rate](rate: R, in: AnyGE, bits: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, bits.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MantissaMask", rate, _args)
}
object LFClipNoise {
   def kr: LFClipNoise[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFClipNoise[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFClipNoise[R <: Rate](rate: R, freq: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFClipNoise", rate, _args)
}
object LFNoise0 {
   def kr: LFNoise0[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise0[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFNoise0[R <: Rate](rate: R, freq: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFNoise0", rate, _args)
}
object LFNoise1 {
   def kr: LFNoise1[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise1[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFNoise1[R <: Rate](rate: R, freq: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFNoise1", rate, _args)
}
object LFNoise2 {
   def kr: LFNoise2[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise2[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFNoise2[R <: Rate](rate: R, freq: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LFNoise2", rate, _args)
}
final case class Rand(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) extends UGenSource.SingleOut[scalar] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Rand", scalar, _args)
}
final case class IRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f) extends UGenSource.SingleOut[scalar] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("IRand", scalar, _args)
}
object TRand {
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
final case class TRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TRand", rate, _args)
}
object TExpRand {
   def kr(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
final case class TExpRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TExpRand", rate, _args)
}
object TIRand {
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
final case class TIRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, trig.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TIRand", rate, _args)
}
final case class NRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, n: AnyGE = 0.0f) extends UGenSource.SingleOut[scalar] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, n.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("NRand", scalar, _args)
}
final case class LinRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, minMax: AnyGE = 0.0f) extends UGenSource.SingleOut[scalar] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, minMax.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinRand", scalar, _args)
}
final case class ExpRand(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f) extends UGenSource.SingleOut[scalar] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("ExpRand", scalar, _args)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object CoinGate {
   def kr(in: AnyGE, prob: AnyGE = 0.5f) = apply[control](control, in, prob)
   def ar(in: AnyGE, prob: AnyGE = 0.5f) = apply[audio](audio, in, prob)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class CoinGate[R <: Rate](rate: R, in: AnyGE, prob: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(prob.expand, in.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("CoinGate", rate, _args)
}
object RandSeed {
   def ir: RandSeed[scalar] = ir()
   def ir(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply[scalar](scalar, trig, seed)
   def kr: RandSeed[control] = kr()
   def kr(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply[control](control, trig, seed)
}
final case class RandSeed[R <: Rate](rate: R, trig: AnyGE, seed: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, seed.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RandSeed", rate, _args)
}
object RandID {
   def ir: RandID[scalar] = ir()
   def ir(id: AnyGE = 0.0f) = apply[scalar](scalar, id)
   def kr: RandID[control] = kr()
   def kr(id: AnyGE = 0.0f) = apply[control](control, id)
}
final case class RandID[R <: Rate](rate: R, id: AnyGE) extends UGenSource.SingleOut[R] with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(id.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("RandID", rate, _args)
}