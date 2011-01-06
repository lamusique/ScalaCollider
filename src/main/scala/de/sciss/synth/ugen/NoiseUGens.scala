/*
 * NoiseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 06 20:58:08 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object WhiteNoise {
   def kr: WhiteNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[control, S, T]) = apply[control, S, T](rateOrder.out, mul)(rateOrder)
   def ar: WhiteNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[audio, S, T]) = apply[audio, S, T](rateOrder.out, mul)(rateOrder)
}
case class WhiteNoise[R <: Rate, S <: Rate, T <: Rate](rate: T, mul: GE[S, UGenIn[S]])( implicit rateOrder: RateOrder[R, S, T]) extends SingleOutUGenSource[T, BinaryOpUGen[T]] with UsesRandSeed {
   def expand = {
      val _mul: IIdxSeq[UGenIn[S]] = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen[T](rateOrder.out, BinaryOp.Times, WhiteNoiseUGen(rateOrder.in1), _mul(i)))
   }
}
case class WhiteNoiseUGen[R <: Rate](rate: R) extends SingleOutUGen[R](IIdxSeq.empty) with UsesRandSeed
object GrayNoise {
   def kr: GrayNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[control, S, T]) = apply[control, S, T](rateOrder.out, mul)(rateOrder)
   def ar: GrayNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[audio, S, T]) = apply[audio, S, T](rateOrder.out, mul)(rateOrder)
}
case class GrayNoise[R <: Rate, S <: Rate, T <: Rate](rate: T, mul: GE[S, UGenIn[S]])( implicit rateOrder: RateOrder[R, S, T]) extends SingleOutUGenSource[T, BinaryOpUGen[T]] with UsesRandSeed {
   def expand = {
      val _mul: IIdxSeq[UGenIn[S]] = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen[T](rateOrder.out, BinaryOp.Times, GrayNoiseUGen(rateOrder.in1), _mul(i)))
   }
}
case class GrayNoiseUGen[R <: Rate](rate: R) extends SingleOutUGen[R](IIdxSeq.empty) with UsesRandSeed
object ClipNoise {
   def kr: ClipNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[control, S, T]) = apply[control, S, T](rateOrder.out, mul)(rateOrder)
   def ar: ClipNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[audio, S, T]) = apply[audio, S, T](rateOrder.out, mul)(rateOrder)
}
case class ClipNoise[R <: Rate, S <: Rate, T <: Rate](rate: T, mul: GE[S, UGenIn[S]])( implicit rateOrder: RateOrder[R, S, T]) extends SingleOutUGenSource[T, BinaryOpUGen[T]] with UsesRandSeed {
   def expand = {
      val _mul: IIdxSeq[UGenIn[S]] = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen[T](rateOrder.out, BinaryOp.Times, ClipNoiseUGen(rateOrder.in1), _mul(i)))
   }
}
case class ClipNoiseUGen[R <: Rate](rate: R) extends SingleOutUGen[R](IIdxSeq.empty) with UsesRandSeed
object PinkNoise {
   def kr: PinkNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[control, S, T]) = apply[control, S, T](rateOrder.out, mul)(rateOrder)
   def ar: PinkNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[audio, S, T]) = apply[audio, S, T](rateOrder.out, mul)(rateOrder)
}
case class PinkNoise[R <: Rate, S <: Rate, T <: Rate](rate: T, mul: GE[S, UGenIn[S]])( implicit rateOrder: RateOrder[R, S, T]) extends SingleOutUGenSource[T, BinaryOpUGen[T]] with UsesRandSeed {
   def expand = {
      val _mul: IIdxSeq[UGenIn[S]] = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen[T](rateOrder.out, BinaryOp.Times, PinkNoiseUGen(rateOrder.in1), _mul(i)))
   }
}
case class PinkNoiseUGen[R <: Rate](rate: R) extends SingleOutUGen[R](IIdxSeq.empty) with UsesRandSeed
object BrownNoise {
   def kr: BrownNoise[control, scalar, control] = kr()
   def kr[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[control, S, T]) = apply[control, S, T](rateOrder.out, mul)(rateOrder)
   def ar: BrownNoise[audio, scalar, audio] = ar()
   def ar[S <: Rate, T <: Rate](mul: GE[S, UGenIn[S]] = 1.0f)(implicit rateOrder: RateOrder[audio, S, T]) = apply[audio, S, T](rateOrder.out, mul)(rateOrder)
}
case class BrownNoise[R <: Rate, S <: Rate, T <: Rate](rate: T, mul: GE[S, UGenIn[S]])( implicit rateOrder: RateOrder[R, S, T]) extends SingleOutUGenSource[T, BinaryOpUGen[T]] with UsesRandSeed {
   def expand = {
      val _mul: IIdxSeq[UGenIn[S]] = mul.expand
      IIdxSeq.tabulate(_mul.size)(i => BinaryOpUGen[T](rateOrder.out, BinaryOp.Times, BrownNoiseUGen(rateOrder.in1), _mul(i)))
   }
}
case class BrownNoiseUGen[R <: Rate](rate: R) extends SingleOutUGen[R](IIdxSeq.empty) with UsesRandSeed
object Dust {
   def kr: Dust[control] = kr()
   def kr(density: AnyGE = 1.0f) = apply[control](control, density)
   def ar: Dust[audio] = ar()
   def ar(density: AnyGE = 1.0f) = apply[audio](audio, density)
}
case class Dust[R <: Rate](rate: R, density: AnyGE) extends SingleOutUGenSource[R, DustUGen[R]] with UsesRandSeed {
   def expand = {
      val _density: IIdxSeq[AnyUGenIn] = density.expand
      IIdxSeq.tabulate(_density.size)(i => DustUGen(rate, _density(i)))
   }
}
case class DustUGen[R <: Rate](rate: R, density: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(density)) with UsesRandSeed
object Dust2 {
   def kr: Dust2[control] = kr()
   def kr(density: AnyGE = 1.0f) = apply[control](control, density)
   def ar: Dust2[audio] = ar()
   def ar(density: AnyGE = 1.0f) = apply[audio](audio, density)
}
case class Dust2[R <: Rate](rate: R, density: AnyGE) extends SingleOutUGenSource[R, Dust2UGen[R]] with UsesRandSeed {
   def expand = {
      val _density: IIdxSeq[AnyUGenIn] = density.expand
      IIdxSeq.tabulate(_density.size)(i => Dust2UGen(rate, _density(i)))
   }
}
case class Dust2UGen[R <: Rate](rate: R, density: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(density)) with UsesRandSeed
object Crackle {
   def kr: Crackle[control] = kr()
   def kr(chaos: AnyGE = 1.5f) = apply[control](control, chaos)
   def ar: Crackle[audio] = ar()
   def ar(chaos: AnyGE = 1.5f) = apply[audio](audio, chaos)
}
case class Crackle[R <: Rate](rate: R, chaos: AnyGE) extends SingleOutUGenSource[R, CrackleUGen[R]] {
   def expand = {
      val _chaos: IIdxSeq[AnyUGenIn] = chaos.expand
      IIdxSeq.tabulate(_chaos.size)(i => CrackleUGen(rate, _chaos(i)))
   }
}
case class CrackleUGen[R <: Rate](rate: R, chaos: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(chaos))
object Logistic {
   def kr: Logistic[control] = kr()
   def kr(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply[control](control, chaos, freq, init)
   def ar: Logistic[audio] = ar()
   def ar(chaos: AnyGE = 3.0f, freq: AnyGE = 1000.0f, init: AnyGE = 0.5f) = apply[audio](audio, chaos, freq, init)
}
case class Logistic[R <: Rate](rate: R, chaos: AnyGE, freq: AnyGE, init: AnyGE) extends SingleOutUGenSource[R, LogisticUGen[R]] {
   def expand = {
      val _chaos: IIdxSeq[AnyUGenIn] = chaos.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _init: IIdxSeq[AnyUGenIn] = init.expand
      val _sz_chaos = _chaos.size
      val _sz_freq = _freq.size
      val _sz_init = _init.size
      val _exp_ = maxInt(_sz_chaos, _sz_freq, _sz_init)
      IIdxSeq.tabulate(_exp_)(i => LogisticUGen(rate, _chaos(i.%(_sz_chaos)), _freq(i.%(_sz_freq)), _init(i.%(_sz_init))))
   }
}
case class LogisticUGen[R <: Rate](rate: R, chaos: AnyUGenIn, freq: AnyUGenIn, init: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(chaos, freq, init))
object Hasher {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: AnyGE) = apply[audio](audio, in)
}
case class Hasher[R <: Rate](rate: R, in: AnyGE) extends SingleOutUGenSource[R, HasherUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      IIdxSeq.tabulate(_in.size)(i => HasherUGen(rate, _in(i)))
   }
}
case class HasherUGen[R <: Rate](rate: R, in: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in))
object MantissaMask {
   def kr(in: AnyGE, bits: AnyGE = 3.0f) = apply[control](control, in, bits)
   def ar(in: AnyGE, bits: AnyGE = 3.0f) = apply[audio](audio, in, bits)
}
case class MantissaMask[R <: Rate](rate: R, in: AnyGE, bits: AnyGE) extends SingleOutUGenSource[R, MantissaMaskUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _bits: IIdxSeq[AnyUGenIn] = bits.expand
      val _sz_in = _in.size
      val _sz_bits = _bits.size
      val _exp_ = maxInt(_sz_in, _sz_bits)
      IIdxSeq.tabulate(_exp_)(i => MantissaMaskUGen(rate, _in(i.%(_sz_in)), _bits(i.%(_sz_bits))))
   }
}
case class MantissaMaskUGen[R <: Rate](rate: R, in: AnyUGenIn, bits: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, bits))
object LFClipNoise {
   def kr: LFClipNoise[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFClipNoise[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFClipNoise[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFClipNoiseUGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFClipNoiseUGen(rate, _freq(i)))
   }
}
case class LFClipNoiseUGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFNoise0 {
   def kr: LFNoise0[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise0[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFNoise0[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFNoise0UGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFNoise0UGen(rate, _freq(i)))
   }
}
case class LFNoise0UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFNoise1 {
   def kr: LFNoise1[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise1[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFNoise1[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFNoise1UGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFNoise1UGen(rate, _freq(i)))
   }
}
case class LFNoise1UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
object LFNoise2 {
   def kr: LFNoise2[control] = kr()
   def kr(freq: AnyGE = 500.0f) = apply[control](control, freq)
   def ar: LFNoise2[audio] = ar()
   def ar(freq: AnyGE = 500.0f) = apply[audio](audio, freq)
}
case class LFNoise2[R <: Rate](rate: R, freq: AnyGE) extends SingleOutUGenSource[R, LFNoise2UGen[R]] with UsesRandSeed {
   def expand = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      IIdxSeq.tabulate(_freq.size)(i => LFNoise2UGen(rate, _freq(i)))
   }
}
case class LFNoise2UGen[R <: Rate](rate: R, freq: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq)) with UsesRandSeed
case class Rand(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f) extends SingleOutUGenSource[scalar, RandUGen] with ScalarRated with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => RandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class RandUGen(lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[scalar](IIdxSeq(lo, hi)) with ScalarRated with UsesRandSeed
case class IRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f) extends SingleOutUGenSource[scalar, IRandUGen] with ScalarRated with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => IRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class IRandUGen(lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[scalar](IIdxSeq(lo, hi)) with ScalarRated with UsesRandSeed
object TRand {
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
case class TRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R, TRandUGen[R]] with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => TRandUGen(rate, _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig))))
   }
}
case class TRandUGen[R <: Rate](rate: R, lo: AnyUGenIn, hi: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(lo, hi, trig)) with UsesRandSeed
object TExpRand {
   def kr(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
case class TExpRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R, TExpRandUGen[R]] with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => TExpRandUGen(rate, _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig))))
   }
}
case class TExpRandUGen[R <: Rate](rate: R, lo: AnyUGenIn, hi: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(lo, hi, trig)) with UsesRandSeed
object TIRand {
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply[control](control, lo, hi, trig)
   def ar(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, trig: AnyGE) = apply[audio](audio, lo, hi, trig)
}
case class TIRand[R <: Rate](rate: R, lo: AnyGE, hi: AnyGE, trig: AnyGE) extends SingleOutUGenSource[R, TIRandUGen[R]] with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_trig = _trig.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_trig)
      IIdxSeq.tabulate(_exp_)(i => TIRandUGen(rate, _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _trig(i.%(_sz_trig))))
   }
}
case class TIRandUGen[R <: Rate](rate: R, lo: AnyUGenIn, hi: AnyUGenIn, trig: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(lo, hi, trig)) with UsesRandSeed
case class NRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, n: AnyGE = 0.0f) extends SingleOutUGenSource[scalar, NRandUGen] with ScalarRated with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _n: IIdxSeq[AnyUGenIn] = n.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_n = _n.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_n)
      IIdxSeq.tabulate(_exp_)(i => NRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _n(i.%(_sz_n))))
   }
}
case class NRandUGen(lo: AnyUGenIn, hi: AnyUGenIn, n: AnyUGenIn) extends SingleOutUGen[scalar](IIdxSeq(lo, hi, n)) with ScalarRated with UsesRandSeed
case class LinRand(lo: AnyGE = 0.0f, hi: AnyGE = 127.0f, minMax: AnyGE = 0.0f) extends SingleOutUGenSource[scalar, LinRandUGen] with ScalarRated with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _minMax: IIdxSeq[AnyUGenIn] = minMax.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_minMax = _minMax.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_minMax)
      IIdxSeq.tabulate(_exp_)(i => LinRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _minMax(i.%(_sz_minMax))))
   }
}
case class LinRandUGen(lo: AnyUGenIn, hi: AnyUGenIn, minMax: AnyUGenIn) extends SingleOutUGen[scalar](IIdxSeq(lo, hi, minMax)) with ScalarRated with UsesRandSeed
case class ExpRand(lo: AnyGE = 0.01f, hi: AnyGE = 1.0f) extends SingleOutUGenSource[scalar, ExpRandUGen] with ScalarRated with UsesRandSeed {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _exp_ = maxInt(_sz_lo, _sz_hi)
      IIdxSeq.tabulate(_exp_)(i => ExpRandUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi))))
   }
}
case class ExpRandUGen(lo: AnyUGenIn, hi: AnyUGenIn) extends SingleOutUGen[scalar](IIdxSeq(lo, hi)) with ScalarRated with UsesRandSeed
object CoinGate {
   def kr(in: AnyGE, prob: AnyGE = 0.5f) = apply[control](control, in, prob)
   def ar(in: AnyGE, prob: AnyGE = 0.5f) = apply[audio](audio, in, prob)
}
case class CoinGate[R <: Rate](rate: R, in: AnyGE, prob: AnyGE) extends SingleOutUGenSource[R, CoinGateUGen[R]] with UsesRandSeed {
   def expand = {
      val _prob: IIdxSeq[AnyUGenIn] = prob.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _sz_prob = _prob.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_prob, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => CoinGateUGen(rate, _in(i.%(_sz_in)), _prob(i.%(_sz_prob))))
   }
}
case class CoinGateUGen[R <: Rate](rate: R, in: AnyUGenIn, prob: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(prob, in)) with UsesRandSeed
object RandSeed {
   def ir: RandSeed[scalar] = ir()
   def ir(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply[scalar](scalar, trig, seed)
   def kr: RandSeed[control] = kr()
   def kr(trig: AnyGE = 1.0f, seed: AnyGE = 56789.0f) = apply[control](control, trig, seed)
}
case class RandSeed[R <: Rate](rate: R, trig: AnyGE, seed: AnyGE) extends SingleOutUGenSource[R, RandSeedUGen[R]] with HasSideEffect {
   def expand = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _seed: IIdxSeq[AnyUGenIn] = seed.expand
      val _sz_trig = _trig.size
      val _sz_seed = _seed.size
      val _exp_ = maxInt(_sz_trig, _sz_seed)
      IIdxSeq.tabulate(_exp_)(i => RandSeedUGen(rate, _trig(i.%(_sz_trig)), _seed(i.%(_sz_seed))))
   }
}
case class RandSeedUGen[R <: Rate](rate: R, trig: AnyUGenIn, seed: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(trig, seed)) with HasSideEffect
object RandID {
   def ir: RandID[scalar] = ir()
   def ir(id: AnyGE = 0.0f) = apply[scalar](scalar, id)
   def kr: RandID[control] = kr()
   def kr(id: AnyGE = 0.0f) = apply[control](control, id)
}
case class RandID[R <: Rate](rate: R, id: AnyGE) extends SingleOutUGenSource[R, RandIDUGen[R]] with HasSideEffect {
   def expand = {
      val _id: IIdxSeq[AnyUGenIn] = id.expand
      IIdxSeq.tabulate(_id.size)(i => RandIDUGen(rate, _id(i)))
   }
}
case class RandIDUGen[R <: Rate](rate: R, id: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(id)) with HasSideEffect