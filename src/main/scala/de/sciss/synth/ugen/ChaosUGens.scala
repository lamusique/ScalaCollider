/*
 * ChaosUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jan 07 00:11:12 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object CuspN {
   def ar: CuspN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, xi)
}
case class CuspN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, CuspNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => CuspNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _xi(i.%(_sz_xi))))
   }
}
case class CuspNUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, xi))
object CuspL {
   def ar: CuspL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, xi)
}
case class CuspL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, CuspLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => CuspLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _xi(i.%(_sz_xi))))
   }
}
case class CuspLUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, xi))
object FBSineN {
   def ar: FBSineN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
case class FBSineN[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, FBSineNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _im: IIdxSeq[AnyUGenIn] = im.expand
      val _fb: IIdxSeq[AnyUGenIn] = fb.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_im = _im.size
      val _sz_fb = _fb.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => FBSineNUGen(rate, _freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class FBSineNUGen[R <: Rate](rate: R, freq: AnyUGenIn, im: AnyUGenIn, fb: AnyUGenIn, a: AnyUGenIn, c: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, im, fb, a, c, xi, yi))
object FBSineL {
   def ar: FBSineL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
case class FBSineL[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, FBSineLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _im: IIdxSeq[AnyUGenIn] = im.expand
      val _fb: IIdxSeq[AnyUGenIn] = fb.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_im = _im.size
      val _sz_fb = _fb.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => FBSineLUGen(rate, _freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class FBSineLUGen[R <: Rate](rate: R, freq: AnyUGenIn, im: AnyUGenIn, fb: AnyUGenIn, a: AnyUGenIn, c: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, im, fb, a, c, xi, yi))
object FBSineC {
   def ar: FBSineC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
case class FBSineC[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, FBSineCUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _im: IIdxSeq[AnyUGenIn] = im.expand
      val _fb: IIdxSeq[AnyUGenIn] = fb.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_im = _im.size
      val _sz_fb = _fb.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => FBSineCUGen(rate, _freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class FBSineCUGen[R <: Rate](rate: R, freq: AnyUGenIn, im: AnyUGenIn, fb: AnyUGenIn, a: AnyUGenIn, c: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, im, fb, a, c, xi, yi))
object GbmanN {
   def ar: GbmanN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply[audio](audio, freq, xi, yi)
}
case class GbmanN[R <: Rate](rate: R, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, GbmanNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => GbmanNUGen(rate, _freq(i.%(_sz_freq)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class GbmanNUGen[R <: Rate](rate: R, freq: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, xi, yi))
object GbmanL {
   def ar: GbmanL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply[audio](audio, freq, xi, yi)
}
case class GbmanL[R <: Rate](rate: R, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, GbmanLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => GbmanLUGen(rate, _freq(i.%(_sz_freq)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class GbmanLUGen[R <: Rate](rate: R, freq: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, xi, yi))
object HenonN {
   def ar: HenonN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
case class HenonN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[R, HenonNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _x0: IIdxSeq[AnyUGenIn] = x0.expand
      val _x1: IIdxSeq[AnyUGenIn] = x1.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_x0 = _x0.size
      val _sz_x1 = _x1.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
      IIdxSeq.tabulate(_exp_)(i => HenonNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1))))
   }
}
case class HenonNUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, x0: AnyUGenIn, x1: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, x0, x1))
object HenonL {
   def ar: HenonL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
case class HenonL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[R, HenonLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _x0: IIdxSeq[AnyUGenIn] = x0.expand
      val _x1: IIdxSeq[AnyUGenIn] = x1.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_x0 = _x0.size
      val _sz_x1 = _x1.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
      IIdxSeq.tabulate(_exp_)(i => HenonLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1))))
   }
}
case class HenonLUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, x0: AnyUGenIn, x1: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, x0, x1))
object HenonC {
   def ar: HenonC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
case class HenonC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[R, HenonCUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _x0: IIdxSeq[AnyUGenIn] = x0.expand
      val _x1: IIdxSeq[AnyUGenIn] = x1.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_x0 = _x0.size
      val _sz_x1 = _x1.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
      IIdxSeq.tabulate(_exp_)(i => HenonCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1))))
   }
}
case class HenonCUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, x0: AnyUGenIn, x1: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, x0, x1))
object LatoocarfianN {
   def ar: LatoocarfianN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
case class LatoocarfianN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, LatoocarfianNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _d: IIdxSeq[AnyUGenIn] = d.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_d = _d.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => LatoocarfianNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class LatoocarfianNUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, c: AnyUGenIn, d: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, c, d, xi, yi))
object LatoocarfianL {
   def ar: LatoocarfianL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
case class LatoocarfianL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, LatoocarfianLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _d: IIdxSeq[AnyUGenIn] = d.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_d = _d.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => LatoocarfianLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class LatoocarfianLUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, c: AnyUGenIn, d: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, c, d, xi, yi))
object LatoocarfianC {
   def ar: LatoocarfianC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
case class LatoocarfianC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, LatoocarfianCUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _d: IIdxSeq[AnyUGenIn] = d.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_d = _d.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => LatoocarfianCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class LatoocarfianCUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, c: AnyUGenIn, d: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, c, d, xi, yi))
object LinCongN {
   def ar: LinCongN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
case class LinCongN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, LinCongNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _m: IIdxSeq[AnyUGenIn] = m.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_m = _m.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => LinCongNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi))))
   }
}
case class LinCongNUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, c: AnyUGenIn, m: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, c, m, xi))
object LinCongL {
   def ar: LinCongL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
case class LinCongL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, LinCongLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _m: IIdxSeq[AnyUGenIn] = m.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_m = _m.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => LinCongLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi))))
   }
}
case class LinCongLUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, c: AnyUGenIn, m: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, c, m, xi))
object LinCongC {
   def ar: LinCongC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
case class LinCongC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, LinCongCUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _m: IIdxSeq[AnyUGenIn] = m.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_m = _m.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => LinCongCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi))))
   }
}
case class LinCongCUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, c: AnyUGenIn, m: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, c, m, xi))
object LorenzL {
   def ar: LorenzL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, s: AnyGE = 10.0f, r: AnyGE = 28.0f, b: AnyGE = 2.667f, h: AnyGE = 0.05f, xi: AnyGE = 0.1f, yi: AnyGE = 0.0f, zi: AnyGE = 0.0f) = apply[audio](audio, freq, s, r, b, h, xi, yi, zi)
}
case class LorenzL[R <: Rate](rate: R, freq: AnyGE, s: AnyGE, r: AnyGE, b: AnyGE, h: AnyGE, xi: AnyGE, yi: AnyGE, zi: AnyGE) extends SingleOutUGenSource[R, LorenzLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _s: IIdxSeq[AnyUGenIn] = s.expand
      val _r: IIdxSeq[AnyUGenIn] = r.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _h: IIdxSeq[AnyUGenIn] = h.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _zi: IIdxSeq[AnyUGenIn] = zi.expand
      val _sz_freq = _freq.size
      val _sz_s = _s.size
      val _sz_r = _r.size
      val _sz_b = _b.size
      val _sz_h = _h.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _sz_zi = _zi.size
      val _exp_ = maxInt(_sz_freq, _sz_s, _sz_r, _sz_b, _sz_h, _sz_xi, _sz_yi, _sz_zi)
      IIdxSeq.tabulate(_exp_)(i => LorenzLUGen(rate, _freq(i.%(_sz_freq)), _s(i.%(_sz_s)), _r(i.%(_sz_r)), _b(i.%(_sz_b)), _h(i.%(_sz_h)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)), _zi(i.%(_sz_zi))))
   }
}
case class LorenzLUGen[R <: Rate](rate: R, freq: AnyUGenIn, s: AnyUGenIn, r: AnyUGenIn, b: AnyUGenIn, h: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn, zi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, s, r, b, h, xi, yi, zi))
object QuadN {
   def ar: QuadN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
case class QuadN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, QuadNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => QuadNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi))))
   }
}
case class QuadNUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, c: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, c, xi))
object QuadL {
   def ar: QuadL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
case class QuadL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, QuadLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => QuadLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi))))
   }
}
case class QuadLUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, c: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, c, xi))
object QuadC {
   def ar: QuadC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
case class QuadC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R, QuadCUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _a: IIdxSeq[AnyUGenIn] = a.expand
      val _b: IIdxSeq[AnyUGenIn] = b.expand
      val _c: IIdxSeq[AnyUGenIn] = c.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => QuadCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi))))
   }
}
case class QuadCUGen[R <: Rate](rate: R, freq: AnyUGenIn, a: AnyUGenIn, b: AnyUGenIn, c: AnyUGenIn, xi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, a, b, c, xi))
object StandardN {
   def ar: StandardN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply[audio](audio, freq, k, xi, yi)
}
case class StandardN[R <: Rate](rate: R, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, StandardNUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _k: IIdxSeq[AnyUGenIn] = k.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_k = _k.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_k, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => StandardNUGen(rate, _freq(i.%(_sz_freq)), _k(i.%(_sz_k)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class StandardNUGen[R <: Rate](rate: R, freq: AnyUGenIn, k: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, k, xi, yi))
object StandardL {
   def ar: StandardL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply[audio](audio, freq, k, xi, yi)
}
case class StandardL[R <: Rate](rate: R, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R, StandardLUGen[R]] {
   protected def expandUGens = {
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _k: IIdxSeq[AnyUGenIn] = k.expand
      val _xi: IIdxSeq[AnyUGenIn] = xi.expand
      val _yi: IIdxSeq[AnyUGenIn] = yi.expand
      val _sz_freq = _freq.size
      val _sz_k = _k.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_k, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => StandardLUGen(rate, _freq(i.%(_sz_freq)), _k(i.%(_sz_k)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
   }
}
case class StandardLUGen[R <: Rate](rate: R, freq: AnyUGenIn, k: AnyUGenIn, xi: AnyUGenIn, yi: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, k, xi, yi))