/*
 * NoiseUGens.scala
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
object WhiteNoise {
   def kr: WhiteNoise[control] = kr()
   def kr(mul: AnyGE = 1.0f) = apply[control](control, mul)
   def ar: WhiteNoise[audio] = ar()
   def ar(mul: AnyGE = 1.0f) = apply[audio](audio, mul)
}
case class WhiteNoise[ R <: Rate ](rate: R, mul: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _mul: IIdxSeq[UGenIn] = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOp.Times.make1(/*rate,*/ new SingleOutUGen("WhiteNoise", rate, IIdxSeq.empty), _mul(i)))
   }
}
//case class WhiteNoiseUGen(rate: Rate) extends SingleOutUGen(IIdxSeq.empty) with UsesRandSeed
//object GrayNoise {
//   def kr: GrayNoise = kr()
//   def kr(mul: GE[UGenIn] = 1.0f) = apply(control, mul)
//   def ar: GrayNoise = ar()
//   def ar(mul: GE[UGenIn] = 1.0f) = apply(audio, mul)
//}
//case class GrayNoise(rate: Rate, mul: GE[UGenIn]) extends SingleOutUGenSource[BinaryOpUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _mul: IIdxSeq[UGenIn] = mul.expand
//      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen(rate, BinaryOp.Times, GrayNoiseUGen(rate), _mul(i)))
//   }
//}
//case class GrayNoiseUGen(rate: Rate) extends SingleOutUGen(IIdxSeq.empty) with UsesRandSeed
//object ClipNoise {
//   def kr: ClipNoise = kr()
//   def kr(mul: GE[UGenIn] = 1.0f) = apply(control, mul)
//   def ar: ClipNoise = ar()
//   def ar(mul: GE[UGenIn] = 1.0f) = apply(audio, mul)
//}
//case class ClipNoise(rate: Rate, mul: GE[UGenIn]) extends SingleOutUGenSource[BinaryOpUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _mul: IIdxSeq[UGenIn] = mul.expand
//      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen(rate, BinaryOp.Times, ClipNoiseUGen(rate), _mul(i)))
//   }
//}
//case class ClipNoiseUGen(rate: Rate) extends SingleOutUGen(IIdxSeq.empty) with UsesRandSeed
//object PinkNoise {
//   def kr: PinkNoise = kr()
//   def kr(mul: GE[UGenIn] = 1.0f) = apply(control, mul)
//   def ar: PinkNoise = ar()
//   def ar(mul: GE[UGenIn] = 1.0f) = apply(audio, mul)
//}
//case class PinkNoise(rate: Rate, mul: GE[UGenIn]) extends SingleOutUGenSource[BinaryOpUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _mul: IIdxSeq[UGenIn] = mul.expand
//      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen(rate, BinaryOp.Times, PinkNoiseUGen(rate), _mul(i)))
//   }
//}
//case class PinkNoiseUGen(rate: Rate) extends SingleOutUGen(IIdxSeq.empty) with UsesRandSeed
//object BrownNoise {
//   def kr: BrownNoise = kr()
//   def kr(mul: GE[UGenIn] = 1.0f) = apply(control, mul)
//   def ar: BrownNoise = ar()
//   def ar(mul: GE[UGenIn] = 1.0f) = apply(audio, mul)
//}
//case class BrownNoise(rate: Rate, mul: GE[UGenIn]) extends SingleOutUGenSource[BinaryOpUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _mul: IIdxSeq[UGenIn] = mul.expand
//      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen(rate, BinaryOp.Times, BrownNoiseUGen(rate), _mul(i)))
//   }
//}
//case class BrownNoiseUGen(rate: Rate) extends SingleOutUGen(IIdxSeq.empty) with UsesRandSeed
///**
// * A UGen generating random impulses with values ranging from
// * `0` to `+1`. The pulse duration is one sample for audio-rate
// * and one block for control-rate operation.
// *
// * @see [[de.sciss.synth.ugen.Dust2]]
// * @see [[de.sciss.synth.ugen.TRand]]
// */
//object Dust {
//   def kr: Dust = kr()
///**
// * @param density         the average number of impulses per second
// */
//def kr(density: AnyGE = 1.0f) = apply(control, density)
//   def ar: Dust = ar()
///**
// * @param density         the average number of impulses per second
// */
//def ar(density: AnyGE = 1.0f) = apply(audio, density)
//}
///**
// * A UGen generating random impulses with values ranging from
// * `0` to `+1`. The pulse duration is one sample for audio-rate
// * and one block for control-rate operation.
// *
// * @param density         the average number of impulses per second
// *
// * @see [[de.sciss.synth.ugen.Dust2]]
// * @see [[de.sciss.synth.ugen.TRand]]
// */
//case class Dust(rate: Rate, density: AnyGE) extends SingleOutUGenSource[DustUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _density: IIdxSeq[UGenIn] = density.expand
//      IIdxSeq.tabulate(_density.size)(i => DustUGen(rate, _density(i)))
//   }
//}
//case class DustUGen(rate: Rate, density: UGenIn) extends SingleOutUGen(IIdxSeq(density)) with UsesRandSeed
///**
// * A UGen generating random impulses with values ranging from
// * `-1` to `+1`. The pulse duration is one sample for audio-rate
// * and one block for control-rate operation.
// *
// * @see [[de.sciss.synth.ugen.Dust]]
// * @see [[de.sciss.synth.ugen.TRand]]
// */
//object Dust2 {
//   def kr: Dust2 = kr()
///**
// * @param density         the average number of impulses per second
// */
//def kr(density: AnyGE = 1.0f) = apply(control, density)
//   def ar: Dust2 = ar()
///**
// * @param density         the average number of impulses per second
// */
//def ar(density: AnyGE = 1.0f) = apply(audio, density)
//}
///**
// * A UGen generating random impulses with values ranging from
// * `-1` to `+1`. The pulse duration is one sample for audio-rate
// * and one block for control-rate operation.
// *
// * @param density         the average number of impulses per second
// *
// * @see [[de.sciss.synth.ugen.Dust]]
// * @see [[de.sciss.synth.ugen.TRand]]
// */
//case class Dust2(rate: Rate, density: AnyGE) extends SingleOutUGenSource[Dust2UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _density: IIdxSeq[UGenIn] = density.expand
//      IIdxSeq.tabulate(_density.size)(i => Dust2UGen(rate, _density(i)))
//   }
//}
//case class Dust2UGen(rate: Rate, density: UGenIn) extends SingleOutUGen(IIdxSeq(density)) with UsesRandSeed
//object Crackle {
//   def kr: Crackle = kr()
//   def kr(chaos: AnyGE = 1.5f) = apply(control, chaos)
//   def ar: Crackle = ar()
//   def ar(chaos: AnyGE = 1.5f) = apply(audio, chaos)
//}
//case class Crackle(rate: Rate, chaos: AnyGE) extends SingleOutUGenSource[CrackleUGen] {
//   protected def expandUGens = {
//      val _chaos: IIdxSeq[UGenIn] = chaos.expand
//      IIdxSeq.tabulate(_chaos.size)(i => CrackleUGen(rate, _chaos(i)))
//   }
//}
//case class CrackleUGen(rate: Rate, chaos: UGenIn) extends SingleOutUGen(IIdxSeq(chaos))
//object Logistic {
//   def kr: Logistic = kr()
//   def kr(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply(control, chaos, freq, init)
//   def ar: Logistic = ar()
//   def ar(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply(audio, chaos, freq, init)
//}
//case class Logistic(rate: Rate, chaos: AnyGE, freq: AnyGE, init: AnyGE) extends SingleOutUGenSource[LogisticUGen] {
//   protected def expandUGens = {
//      val _chaos: IIdxSeq[UGenIn] = chaos.expand
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _init: IIdxSeq[UGenIn] = init.expand
//      val _sz_chaos = _chaos.size
//      val _sz_freq = _freq.size
//      val _sz_init = _init.size
//      val _exp_ = maxInt(_sz_chaos, _sz_freq, _sz_init)
//      IIdxSeq.tabulate(_exp_)(i => LogisticUGen(rate, _chaos(i.%(_sz_chaos)), _freq(i.%(_sz_freq)), _init(i.%(_sz_init))))
//   }
//}
//case class LogisticUGen(rate: Rate, chaos: UGenIn, freq: UGenIn, init: UGenIn) extends SingleOutUGen(IIdxSeq(chaos, freq, init))
//object Hasher {
//   def kr(in: AnyGE) = apply(control, in)
//   def ar(in: AnyGE) = apply(audio, in)
//}
//case class Hasher(rate: Rate, in: AnyGE) extends SingleOutUGenSource[HasherUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      IIdxSeq.tabulate(_in.size)(i => HasherUGen(rate, _in(i)))
//   }
//}
//case class HasherUGen(rate: Rate, in: UGenIn) extends SingleOutUGen(IIdxSeq(in))
//object MantissaMask {
//   def kr(in: AnyGE, bits: AnyGE = 3.0f) = apply(control, in, bits)
//   def ar(in: AnyGE, bits: AnyGE = 3.0f) = apply(audio, in, bits)
//}
//case class MantissaMask(rate: Rate, in: AnyGE, bits: AnyGE) extends SingleOutUGenSource[MantissaMaskUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _bits: IIdxSeq[UGenIn] = bits.expand
//      val _sz_in = _in.size
//      val _sz_bits = _bits.size
//      val _exp_ = maxInt(_sz_in, _sz_bits)
//      IIdxSeq.tabulate(_exp_)(i => MantissaMaskUGen(rate, _in(i.%(_sz_in)), _bits(i.%(_sz_bits))))
//   }
//}
//case class MantissaMaskUGen(rate: Rate, in: UGenIn, bits: UGenIn) extends SingleOutUGen(IIdxSeq(in, bits))
//object LFClipNoise {
//   def kr: LFClipNoise = kr()
//   def kr(freq: AnyGE = 500.0f) = apply(control, freq)
//   def ar: LFClipNoise = ar()
//   def ar(freq: AnyGE = 500.0f) = apply(audio, freq)
//}
//case class LFClipNoise(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[LFClipNoiseUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => LFClipNoiseUGen(rate, _freq(i)))
//   }
//}
//case class LFClipNoiseUGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed
//object LFNoise0 {
//   def kr: LFNoise0 = kr()
//   def kr(freq: AnyGE = 500.0f) = apply(control, freq)
//   def ar: LFNoise0 = ar()
//   def ar(freq: AnyGE = 500.0f) = apply(audio, freq)
//}
//case class LFNoise0(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[LFNoise0UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => LFNoise0UGen(rate, _freq(i)))
//   }
//}
//case class LFNoise0UGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed
object LFNoise1 {
   def kr: LFNoise1[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise1[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFNoise1[ R <: Rate ](rate: R, freq: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _freq: IIdxSeq[UGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => new SingleOutUGen("LFNoise1", rate, IIdxSeq( _freq(i))))
   }
}
//case class LFNoise1UGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed
//object LFNoise2 {
//   def kr: LFNoise2 = kr()
//   def kr(freq: AnyGE = 500.0f) = apply(control, freq)
//   def ar: LFNoise2 = ar()
//   def ar(freq: AnyGE = 500.0f) = apply(audio, freq)
//}
//case class LFNoise2(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[LFNoise2UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => LFNoise2UGen(rate, _freq(i)))
//   }
//}
//case class LFNoise2UGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed
case class Rand(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) extends SingleOutUGenSource[scalar] with ScalarRated with UsesRandSeed {
   protected def expandUGens = {
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Rand", scalar, IIdxSeq( _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)))))
   }
}
//case class RandUGen(lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi)) with ScalarRated with UsesRandSeed
//case class IRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f) extends SingleOutUGenSource[IRandUGen] with ScalarRated with UsesRandSeed {
//   protected def expandUGens = {
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _exp_ = maxInt(_sz_lo, _sz_hi)
//      IIdxSeq.tabulate(_exp_)(i => IRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
//   }
//}
//case class IRandUGen(lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi)) with ScalarRated with UsesRandSeed
//object TRand {
//   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply(control, lo, hi, trig)
//   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply(audio, lo, hi, trig)
//}
//case class TRand(rate: Rate, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[TRandUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_trig = _trig.size
//      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
//      IIdxSeq.tabulate(_exp_)(i => TRandUGen(rate, _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig))))
//   }
//}
//case class TRandUGen(rate: Rate, lo: UGenIn, hi: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, trig)) with UsesRandSeed
//object TExpRand {
//   def kr(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply(control, lo, hi, trig)
//   def ar(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply(audio, lo, hi, trig)
//}
//case class TExpRand(rate: Rate, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[TExpRandUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_trig = _trig.size
//      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
//      IIdxSeq.tabulate(_exp_)(i => TExpRandUGen(rate, _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig))))
//   }
//}
//case class TExpRandUGen(rate: Rate, lo: UGenIn, hi: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, trig)) with UsesRandSeed
//object TIRand {
//   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply(control, lo, hi, trig)
//   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply(audio, lo, hi, trig)
//}
//case class TIRand(rate: Rate, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[TIRandUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_trig = _trig.size
//      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
//      IIdxSeq.tabulate(_exp_)(i => TIRandUGen(rate, _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig))))
//   }
//}
//case class TIRandUGen(rate: Rate, lo: UGenIn, hi: UGenIn, trig: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, trig)) with UsesRandSeed
//case class NRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, n: AnyGE = 0.0f) extends SingleOutUGenSource[NRandUGen] with ScalarRated with UsesRandSeed {
//   protected def expandUGens = {
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _n: IIdxSeq[UGenIn] = n.expand
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_n = _n.size
//      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_n)
//      IIdxSeq.tabulate(_exp_)(i => NRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _n(i.%(_sz_n))))
//   }
//}
//case class NRandUGen(lo: UGenIn, hi: UGenIn, n: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, n)) with ScalarRated with UsesRandSeed
//case class LinRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, minMax: AnyGE = 0.0f) extends SingleOutUGenSource[LinRandUGen] with ScalarRated with UsesRandSeed {
//   protected def expandUGens = {
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _minMax: IIdxSeq[UGenIn] = minMax.expand
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_minMax = _minMax.size
//      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_minMax)
//      IIdxSeq.tabulate(_exp_)(i => LinRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _minMax(i.%(_sz_minMax))))
//   }
//}
//case class LinRandUGen(lo: UGenIn, hi: UGenIn, minMax: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, minMax)) with ScalarRated with UsesRandSeed
//case class ExpRand(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f) extends SingleOutUGenSource[ExpRandUGen] with ScalarRated with UsesRandSeed {
//   protected def expandUGens = {
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _exp_ = maxInt(_sz_lo, _sz_hi)
//      IIdxSeq.tabulate(_exp_)(i => ExpRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
//   }
//}
//case class ExpRandUGen(lo: UGenIn, hi: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi)) with ScalarRated with UsesRandSeed
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//object CoinGate {
//   def kr(in: AnyGE, prob: AnyGE = 0.5f) = apply(control, in, prob)
//   def ar(in: AnyGE, prob: AnyGE = 0.5f) = apply(audio, in, prob)
//}
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//case class CoinGate(rate: Rate, in: AnyGE, prob: AnyGE) extends SingleOutUGenSource[CoinGateUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _prob: IIdxSeq[UGenIn] = prob.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _sz_prob = _prob.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_prob, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => CoinGateUGen(rate, _in(i.%(_sz_in)), _prob(i.%(_sz_prob))))
//   }
//}
//case class CoinGateUGen(rate: Rate, in: UGenIn, prob: UGenIn) extends SingleOutUGen(IIdxSeq(prob, in)) with UsesRandSeed
//object RandSeed {
//   def ir: RandSeed = ir()
//   def ir(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply(scalar, trig, seed)
//   def kr: RandSeed = kr()
//   def kr(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply(control, trig, seed)
//}
//case class RandSeed(rate: Rate, trig: AnyGE, seed: AnyGE) extends SingleOutUGenSource[RandSeedUGen] with HasSideEffect {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _seed: IIdxSeq[UGenIn] = seed.expand
//      val _sz_trig = _trig.size
//      val _sz_seed = _seed.size
//      val _exp_ = maxInt(_sz_trig, _sz_seed)
//      IIdxSeq.tabulate(_exp_)(i => RandSeedUGen(rate, _trig(i.%(_sz_trig)), _seed(i.%(_sz_seed))))
//   }
//}
//case class RandSeedUGen(rate: Rate, trig: UGenIn, seed: UGenIn) extends SingleOutUGen(IIdxSeq(trig, seed)) with HasSideEffect
//object RandID {
//   def ir: RandID = ir()
//   def ir(id: AnyGE = 0.0f) = apply(scalar, id)
//   def kr: RandID = kr()
//   def kr(id: AnyGE = 0.0f) = apply(control, id)
//}
//case class RandID(rate: Rate, id: AnyGE) extends SingleOutUGenSource[RandIDUGen] with HasSideEffect {
//   protected def expandUGens = {
//      val _id: IIdxSeq[UGenIn] = id.expand
//      IIdxSeq.tabulate(_id.size)(i => RandIDUGen(rate, _id(i)))
//   }
//}
//case class RandIDUGen(rate: Rate, id: UGenIn) extends SingleOutUGen(IIdxSeq(id)) with HasSideEffect