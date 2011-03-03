/*
 * NoiseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
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
final case class WhiteNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends SingleOutUGenSource[T] with UsesRandSeed {
   protected def expandUGens = {
      val _mul = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOp.Times.make1(new SingleOutUGen("WhiteNoise", rate, IIdxSeq.empty), _mul(i)))
   }
}
object GrayNoise {
   def kr: GrayNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: GrayNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class GrayNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends SingleOutUGenSource[T] with UsesRandSeed {
   protected def expandUGens = {
      val _mul = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOp.Times.make1(new SingleOutUGen("GrayNoise", rate, IIdxSeq.empty), _mul(i)))
   }
}
object ClipNoise {
   def kr: ClipNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: ClipNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class ClipNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends SingleOutUGenSource[T] with UsesRandSeed {
   protected def expandUGens = {
      val _mul = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOp.Times.make1(new SingleOutUGen("ClipNoise", rate, IIdxSeq.empty), _mul(i)))
   }
}
object PinkNoise {
   def kr: PinkNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: PinkNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class PinkNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends SingleOutUGenSource[T] with UsesRandSeed {
   protected def expandUGens = {
      val _mul = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOp.Times.make1(new SingleOutUGen("PinkNoise", rate, IIdxSeq.empty), _mul(i)))
   }
}
object BrownNoise {
   def kr: BrownNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[control, S, T]) = apply[control, S, T](control, mul)(rateOrder)
   def ar: BrownNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S] = 1.0f)(implicit rateOrder: Rate.Ord[audio, S, T]) = apply[audio, S, T](audio, mul)(rateOrder)
}
final case class BrownNoise[R <: Rate, S <: Rate, T <: Rate](rate: R, mul: GE[S])( implicit rateOrder: Rate.Ord[R, S, T]) extends SingleOutUGenSource[T] with UsesRandSeed {
   protected def expandUGens = {
      val _mul = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOp.Times.make1(new SingleOutUGen("BrownNoise", rate, IIdxSeq.empty), _mul(i)))
   }
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
final case class Dust[R <: Rate](rate: R, density: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _density = density.expand
      IIdxSeq.tabulate(_density.size)(i => new SingleOutUGen("Dust", rate, IIdxSeq(_density(i))))
   }
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
final case class Dust2[R <: Rate](rate: R, density: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _density = density.expand
      IIdxSeq.tabulate(_density.size)(i => new SingleOutUGen("Dust2", rate, IIdxSeq(_density(i))))
   }
}
object Crackle {
   def kr: Crackle[control] = kr()
   def kr(chaos: AnyGE = 1.5f) = apply[control](control, chaos)
   def ar: Crackle[audio] = ar()
   def ar(chaos: AnyGE = 1.5f) = apply[audio](audio, chaos)
}
final case class Crackle[R <: Rate](rate: R, chaos: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _chaos = chaos.expand
      IIdxSeq.tabulate(_chaos.size)(i => new SingleOutUGen("Crackle", rate, IIdxSeq(_chaos(i))))
   }
}
object Logistic {
   def kr: Logistic[control] = kr()
   def kr(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply[control](control, chaos, freq, init)
   def ar: Logistic[audio] = ar()
   def ar(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply[audio](audio, chaos, freq, init)
}
final case class Logistic[R <: Rate](rate: R, chaos: AnyGE, freq: AnyGE, init: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _chaos = chaos.expand
      val _freq = freq.expand
      val _init = init.expand
      val _sz_chaos = _chaos.size
      val _sz_freq = _freq.size
      val _sz_init = _init.size
      val _exp_ = maxInt(_sz_chaos, _sz_freq, _sz_init)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Logistic", rate, IIdxSeq(_chaos(i.%(_sz_chaos)), _freq(i.%(_sz_freq)), _init(i.%(_sz_init)))))
   }
}
object Hasher {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
final case class Hasher[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      IIdxSeq.tabulate(_in.size)(i => new SingleOutUGen("Hasher", rate, IIdxSeq(_in(i))))
   }
}
object MantissaMask {
   def kr(in: AnyGE, bits: AnyGE = 3.0f) = apply[control](control, in, bits)
   def ar(in: AnyGE, bits: AnyGE = 3.0f) = apply[audio](audio, in, bits)
}
final case class MantissaMask[R <: Rate](rate: R, in: AnyGE, bits: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _bits = bits.expand
      val _sz_in = _in.size
      val _sz_bits = _bits.size
      val _exp_ = maxInt(_sz_in, _sz_bits)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("MantissaMask", rate, IIdxSeq(_in(i.%(_sz_in)), _bits(i.%(_sz_bits)))))
   }
}
object LFClipNoise {
   def kr: LFClipNoise[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFClipNoise[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFClipNoise[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _freq = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => new SingleOutUGen("LFClipNoise", rate, IIdxSeq(_freq(i))))
   }
}
object LFNoise0 {
   def kr: LFNoise0[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise0[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFNoise0[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _freq = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => new SingleOutUGen("LFNoise0", rate, IIdxSeq(_freq(i))))
   }
}
object LFNoise1 {
   def kr: LFNoise1[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise1[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFNoise1[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _freq = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => new SingleOutUGen("LFNoise1", rate, IIdxSeq(_freq(i))))
   }
}
object LFNoise2 {
   def kr: LFNoise2[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise2[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
final case class LFNoise2[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _freq = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => new SingleOutUGen("LFNoise2", rate, IIdxSeq(_freq(i))))
   }
}
final case class Rand(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) extends SingleOutUGenSource[scalar] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Rand", scalar, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)))))
   }
}
final case class IRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f) extends SingleOutUGenSource[scalar] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("IRand", scalar, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)))))
   }
}
object TRand {
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
final case class TRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _trig = trig.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TRand", rate, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig)))))
   }
}
object TExpRand {
   def kr(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
final case class TExpRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _trig = trig.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TExpRand", rate, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig)))))
   }
}
object TIRand {
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
final case class TIRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _trig = trig.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TIRand", rate, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig)))))
   }
}
final case class NRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, n: AnyGE = 0.0f) extends SingleOutUGenSource[scalar] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _n = n.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_n = _n.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_n)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("NRand", scalar, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _n(i.%(_sz_n)))))
   }
}
final case class LinRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, minMax: AnyGE = 0.0f) extends SingleOutUGenSource[scalar] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _minMax = minMax.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_minMax = _minMax.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_minMax)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LinRand", scalar, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _minMax(i.%(_sz_minMax)))))
   }
}
final case class ExpRand(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f) extends SingleOutUGenSource[scalar] with UsesRandSeed {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("ExpRand", scalar, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)))))
   }
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
final case class CoinGate[R <: Rate](rate: R, in: AnyGE, prob: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _prob = prob.expand
      val _in = in.expand
      val _sz_prob = _prob.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_prob, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("CoinGate", rate, IIdxSeq(_prob(i.%(_sz_prob)), _in(i.%(_sz_in)))))
   }
}
object RandSeed {
   def ir: RandSeed[scalar] = ir()
   def ir(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply[scalar](scalar, trig, seed)
   def kr: RandSeed[control] = kr()
   def kr(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply[control](control, trig, seed)
}
final case class RandSeed[R <: Rate](rate: R, trig: AnyGE, seed: AnyGE) extends SingleOutUGenSource[R] with HasSideEffect {
   protected def expandUGens = {
      val _trig = trig.expand
      val _seed = seed.expand
      val _sz_trig = _trig.size
      val _sz_seed = _seed.size
      val _exp_ = maxInt(_sz_trig, _sz_seed)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("RandSeed", rate, IIdxSeq(_trig(i.%(_sz_trig)), _seed(i.%(_sz_seed)))))
   }
}
object RandID {
   def ir: RandID[scalar] = ir()
   def ir(id: AnyGE = 0.0f) = apply[scalar](scalar, id)
   def kr: RandID[control] = kr()
   def kr(id: AnyGE = 0.0f) = apply[control](control, id)
}
final case class RandID[R <: Rate](rate: R, id: AnyGE) extends SingleOutUGenSource[R] with HasSideEffect {
   protected def expandUGens = {
      val _id = id.expand
      IIdxSeq.tabulate(_id.size)(i => new SingleOutUGen("RandID", rate, IIdxSeq(_id(i))))
   }
}