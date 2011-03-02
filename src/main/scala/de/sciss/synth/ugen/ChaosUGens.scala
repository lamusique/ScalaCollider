/*
 * ChaosUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import util.UGenHelper._
object CuspN {
   def ar: CuspN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, xi)
}
final case class CuspN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("CuspN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _xi(i.%(_sz_xi)))))
   }
}
object CuspL {
   def ar: CuspL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, xi)
}
final case class CuspL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("CuspL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _xi(i.%(_sz_xi)))))
   }
}
object FBSineN {
   def ar: FBSineN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
final case class FBSineN[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _im = im.expand
      val _fb = fb.expand
      val _a = a.expand
      val _c = c.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_im = _im.size
      val _sz_fb = _fb.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FBSineN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object FBSineL {
   def ar: FBSineL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
final case class FBSineL[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _im = im.expand
      val _fb = fb.expand
      val _a = a.expand
      val _c = c.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_im = _im.size
      val _sz_fb = _fb.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FBSineL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object FBSineC {
   def ar: FBSineC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
final case class FBSineC[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _im = im.expand
      val _fb = fb.expand
      val _a = a.expand
      val _c = c.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_im = _im.size
      val _sz_fb = _fb.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("FBSineC", rate, IIdxSeq(_freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object GbmanN {
   def ar: GbmanN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply[audio](audio, freq, xi, yi)
}
final case class GbmanN[R <: Rate](rate: R, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("GbmanN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object GbmanL {
   def ar: GbmanL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply[audio](audio, freq, xi, yi)
}
final case class GbmanL[R <: Rate](rate: R, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("GbmanL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object HenonN {
   def ar: HenonN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
final case class HenonN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _x0 = x0.expand
      val _x1 = x1.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_x0 = _x0.size
      val _sz_x1 = _x1.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("HenonN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1)))))
   }
}
object HenonL {
   def ar: HenonL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
final case class HenonL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _x0 = x0.expand
      val _x1 = x1.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_x0 = _x0.size
      val _sz_x1 = _x1.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("HenonL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1)))))
   }
}
object HenonC {
   def ar: HenonC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
final case class HenonC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _x0 = x0.expand
      val _x1 = x1.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_x0 = _x0.size
      val _sz_x1 = _x1.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("HenonC", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1)))))
   }
}
object LatoocarfianN {
   def ar: LatoocarfianN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _c = c.expand
      val _d = d.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_d = _d.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LatoocarfianN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object LatoocarfianL {
   def ar: LatoocarfianL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _c = c.expand
      val _d = d.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_d = _d.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LatoocarfianL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object LatoocarfianC {
   def ar: LatoocarfianC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _c = c.expand
      val _d = d.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_d = _d.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LatoocarfianC", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object LinCongN {
   def ar: LinCongN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
final case class LinCongN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _c = c.expand
      val _m = m.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_m = _m.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LinCongN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi)))))
   }
}
object LinCongL {
   def ar: LinCongL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
final case class LinCongL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _c = c.expand
      val _m = m.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_m = _m.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LinCongL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi)))))
   }
}
object LinCongC {
   def ar: LinCongC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
final case class LinCongC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _c = c.expand
      val _m = m.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _sz_m = _m.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LinCongC", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi)))))
   }
}
object LorenzL {
   def ar: LorenzL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, s: AnyGE = 10.0f, r: AnyGE = 28.0f, b: AnyGE = 2.667f, h: AnyGE = 0.05f, xi: AnyGE = 0.1f, yi: AnyGE = 0.0f, zi: AnyGE = 0.0f) = apply[audio](audio, freq, s, r, b, h, xi, yi, zi)
}
final case class LorenzL[R <: Rate](rate: R, freq: AnyGE, s: AnyGE, r: AnyGE, b: AnyGE, h: AnyGE, xi: AnyGE, yi: AnyGE, zi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _s = s.expand
      val _r = r.expand
      val _b = b.expand
      val _h = h.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _zi = zi.expand
      val _sz_freq = _freq.size
      val _sz_s = _s.size
      val _sz_r = _r.size
      val _sz_b = _b.size
      val _sz_h = _h.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _sz_zi = _zi.size
      val _exp_ = maxInt(_sz_freq, _sz_s, _sz_r, _sz_b, _sz_h, _sz_xi, _sz_yi, _sz_zi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("LorenzL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _s(i.%(_sz_s)), _r(i.%(_sz_r)), _b(i.%(_sz_b)), _h(i.%(_sz_h)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)), _zi(i.%(_sz_zi)))))
   }
}
object QuadN {
   def ar: QuadN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
final case class QuadN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _c = c.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("QuadN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)))))
   }
}
object QuadL {
   def ar: QuadL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
final case class QuadL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _c = c.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("QuadL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)))))
   }
}
object QuadC {
   def ar: QuadC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
final case class QuadC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _a = a.expand
      val _b = b.expand
      val _c = c.expand
      val _xi = xi.expand
      val _sz_freq = _freq.size
      val _sz_a = _a.size
      val _sz_b = _b.size
      val _sz_c = _c.size
      val _sz_xi = _xi.size
      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("QuadC", rate, IIdxSeq(_freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)))))
   }
}
object StandardN {
   def ar: StandardN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply[audio](audio, freq, k, xi, yi)
}
final case class StandardN[R <: Rate](rate: R, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _k = k.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_k = _k.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_k, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("StandardN", rate, IIdxSeq(_freq(i.%(_sz_freq)), _k(i.%(_sz_k)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}
object StandardL {
   def ar: StandardL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply[audio](audio, freq, k, xi, yi)
}
final case class StandardL[R <: Rate](rate: R, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _freq = freq.expand
      val _k = k.expand
      val _xi = xi.expand
      val _yi = yi.expand
      val _sz_freq = _freq.size
      val _sz_k = _k.size
      val _sz_xi = _xi.size
      val _sz_yi = _yi.size
      val _exp_ = maxInt(_sz_freq, _sz_k, _sz_xi, _sz_yi)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("StandardL", rate, IIdxSeq(_freq(i.%(_sz_freq)), _k(i.%(_sz_k)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)))))
   }
}