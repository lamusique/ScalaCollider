///*
// * ChaosUGens.scala
// * (ScalaCollider-UGens)
// *
// * This is a synthetically generated file.
// * Created: Thu Jan 27 23:03:33 GMT 2011
// * ScalaCollider-UGen version: 0.10
// */
//
//package de.sciss.synth
//package ugen
//import collection.immutable.{IndexedSeq => IIdxSeq}
//import UGenHelper._
//object CuspN {
//   def ar: CuspN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply(audio, freq, a, b, xi)
//}
//case class CuspN(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends SingleOutUGenSource[CuspNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => CuspNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _xi(i.%(_sz_xi))))
//   }
//}
//case class CuspNUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, xi))
//object CuspL {
//   def ar: CuspL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply(audio, freq, a, b, xi)
//}
//case class CuspL(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends SingleOutUGenSource[CuspLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => CuspLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _xi(i.%(_sz_xi))))
//   }
//}
//case class CuspLUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, xi))
//object FBSineN {
//   def ar: FBSineN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply(audio, freq, im, fb, a, c, xi, yi)
//}
//case class FBSineN(rate: Rate, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[FBSineNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _im: IIdxSeq[UGenIn] = im.expand
//      val _fb: IIdxSeq[UGenIn] = fb.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_im = _im.size
//      val _sz_fb = _fb.size
//      val _sz_a = _a.size
//      val _sz_c = _c.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => FBSineNUGen(rate, _freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class FBSineNUGen(rate: Rate, freq: UGenIn, im: UGenIn, fb: UGenIn, a: UGenIn, c: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, im, fb, a, c, xi, yi))
//object FBSineL {
//   def ar: FBSineL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply(audio, freq, im, fb, a, c, xi, yi)
//}
//case class FBSineL(rate: Rate, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[FBSineLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _im: IIdxSeq[UGenIn] = im.expand
//      val _fb: IIdxSeq[UGenIn] = fb.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_im = _im.size
//      val _sz_fb = _fb.size
//      val _sz_a = _a.size
//      val _sz_c = _c.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => FBSineLUGen(rate, _freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class FBSineLUGen(rate: Rate, freq: UGenIn, im: UGenIn, fb: UGenIn, a: UGenIn, c: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, im, fb, a, c, xi, yi))
//object FBSineC {
//   def ar: FBSineC = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply(audio, freq, im, fb, a, c, xi, yi)
//}
//case class FBSineC(rate: Rate, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[FBSineCUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _im: IIdxSeq[UGenIn] = im.expand
//      val _fb: IIdxSeq[UGenIn] = fb.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_im = _im.size
//      val _sz_fb = _fb.size
//      val _sz_a = _a.size
//      val _sz_c = _c.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_im, _sz_fb, _sz_a, _sz_c, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => FBSineCUGen(rate, _freq(i.%(_sz_freq)), _im(i.%(_sz_im)), _fb(i.%(_sz_fb)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class FBSineCUGen(rate: Rate, freq: UGenIn, im: UGenIn, fb: UGenIn, a: UGenIn, c: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, im, fb, a, c, xi, yi))
//object GbmanN {
//   def ar: GbmanN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply(audio, freq, xi, yi)
//}
//case class GbmanN(rate: Rate, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[GbmanNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => GbmanNUGen(rate, _freq(i.%(_sz_freq)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class GbmanNUGen(rate: Rate, freq: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, xi, yi))
//object GbmanL {
//   def ar: GbmanL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply(audio, freq, xi, yi)
//}
//case class GbmanL(rate: Rate, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[GbmanLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => GbmanLUGen(rate, _freq(i.%(_sz_freq)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class GbmanLUGen(rate: Rate, freq: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, xi, yi))
//object HenonN {
//   def ar: HenonN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply(audio, freq, a, b, x0, x1)
//}
//case class HenonN(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[HenonNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _x0: IIdxSeq[UGenIn] = x0.expand
//      val _x1: IIdxSeq[UGenIn] = x1.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_x0 = _x0.size
//      val _sz_x1 = _x1.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
//      IIdxSeq.tabulate(_exp_)(i => HenonNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1))))
//   }
//}
//case class HenonNUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, x0: UGenIn, x1: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, x0, x1))
//object HenonL {
//   def ar: HenonL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply(audio, freq, a, b, x0, x1)
//}
//case class HenonL(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[HenonLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _x0: IIdxSeq[UGenIn] = x0.expand
//      val _x1: IIdxSeq[UGenIn] = x1.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_x0 = _x0.size
//      val _sz_x1 = _x1.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
//      IIdxSeq.tabulate(_exp_)(i => HenonLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1))))
//   }
//}
//case class HenonLUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, x0: UGenIn, x1: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, x0, x1))
//object HenonC {
//   def ar: HenonC = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply(audio, freq, a, b, x0, x1)
//}
//case class HenonC(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends SingleOutUGenSource[HenonCUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _x0: IIdxSeq[UGenIn] = x0.expand
//      val _x1: IIdxSeq[UGenIn] = x1.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_x0 = _x0.size
//      val _sz_x1 = _x1.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_x0, _sz_x1)
//      IIdxSeq.tabulate(_exp_)(i => HenonCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _x0(i.%(_sz_x0)), _x1(i.%(_sz_x1))))
//   }
//}
//case class HenonCUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, x0: UGenIn, x1: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, x0, x1))
//object LatoocarfianN {
//   def ar: LatoocarfianN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
//}
//case class LatoocarfianN(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[LatoocarfianNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _d: IIdxSeq[UGenIn] = d.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_c = _c.size
//      val _sz_d = _d.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => LatoocarfianNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class LatoocarfianNUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, c: UGenIn, d: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, c, d, xi, yi))
//object LatoocarfianL {
//   def ar: LatoocarfianL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
//}
//case class LatoocarfianL(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[LatoocarfianLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _d: IIdxSeq[UGenIn] = d.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_c = _c.size
//      val _sz_d = _d.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => LatoocarfianLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class LatoocarfianLUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, c: UGenIn, d: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, c, d, xi, yi))
//object LatoocarfianC {
//   def ar: LatoocarfianC = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
//}
//case class LatoocarfianC(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[LatoocarfianCUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _d: IIdxSeq[UGenIn] = d.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_c = _c.size
//      val _sz_d = _d.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_d, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => LatoocarfianCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _d(i.%(_sz_d)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class LatoocarfianCUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, c: UGenIn, d: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, c, d, xi, yi))
//object LinCongN {
//   def ar: LinCongN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply(audio, freq, a, c, m, xi)
//}
//case class LinCongN(rate: Rate, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[LinCongNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _m: IIdxSeq[UGenIn] = m.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_c = _c.size
//      val _sz_m = _m.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => LinCongNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi))))
//   }
//}
//case class LinCongNUGen(rate: Rate, freq: UGenIn, a: UGenIn, c: UGenIn, m: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, c, m, xi))
//object LinCongL {
//   def ar: LinCongL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply(audio, freq, a, c, m, xi)
//}
//case class LinCongL(rate: Rate, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[LinCongLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _m: IIdxSeq[UGenIn] = m.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_c = _c.size
//      val _sz_m = _m.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => LinCongLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi))))
//   }
//}
//case class LinCongLUGen(rate: Rate, freq: UGenIn, a: UGenIn, c: UGenIn, m: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, c, m, xi))
//object LinCongC {
//   def ar: LinCongC = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply(audio, freq, a, c, m, xi)
//}
//case class LinCongC(rate: Rate, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends SingleOutUGenSource[LinCongCUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _m: IIdxSeq[UGenIn] = m.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_c = _c.size
//      val _sz_m = _m.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_c, _sz_m, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => LinCongCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _c(i.%(_sz_c)), _m(i.%(_sz_m)), _xi(i.%(_sz_xi))))
//   }
//}
//case class LinCongCUGen(rate: Rate, freq: UGenIn, a: UGenIn, c: UGenIn, m: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, c, m, xi))
//object LorenzL {
//   def ar: LorenzL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, s: AnyGE = 10.0f, r: AnyGE = 28.0f, b: AnyGE = 2.667f, h: AnyGE = 0.05f, xi: AnyGE = 0.1f, yi: AnyGE = 0.0f, zi: AnyGE = 0.0f) = apply(audio, freq, s, r, b, h, xi, yi, zi)
//}
//case class LorenzL(rate: Rate, freq: AnyGE, s: AnyGE, r: AnyGE, b: AnyGE, h: AnyGE, xi: AnyGE, yi: AnyGE, zi: AnyGE) extends SingleOutUGenSource[LorenzLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _s: IIdxSeq[UGenIn] = s.expand
//      val _r: IIdxSeq[UGenIn] = r.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _h: IIdxSeq[UGenIn] = h.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _zi: IIdxSeq[UGenIn] = zi.expand
//      val _sz_freq = _freq.size
//      val _sz_s = _s.size
//      val _sz_r = _r.size
//      val _sz_b = _b.size
//      val _sz_h = _h.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _sz_zi = _zi.size
//      val _exp_ = maxInt(_sz_freq, _sz_s, _sz_r, _sz_b, _sz_h, _sz_xi, _sz_yi, _sz_zi)
//      IIdxSeq.tabulate(_exp_)(i => LorenzLUGen(rate, _freq(i.%(_sz_freq)), _s(i.%(_sz_s)), _r(i.%(_sz_r)), _b(i.%(_sz_b)), _h(i.%(_sz_h)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi)), _zi(i.%(_sz_zi))))
//   }
//}
//case class LorenzLUGen(rate: Rate, freq: UGenIn, s: UGenIn, r: UGenIn, b: UGenIn, h: UGenIn, xi: UGenIn, yi: UGenIn, zi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, s, r, b, h, xi, yi, zi))
//object QuadN {
//   def ar: QuadN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply(audio, freq, a, b, c, xi)
//}
//case class QuadN(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[QuadNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_c = _c.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => QuadNUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi))))
//   }
//}
//case class QuadNUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, c: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, c, xi))
//object QuadL {
//   def ar: QuadL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply(audio, freq, a, b, c, xi)
//}
//case class QuadL(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[QuadLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_c = _c.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => QuadLUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi))))
//   }
//}
//case class QuadLUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, c: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, c, xi))
//object QuadC {
//   def ar: QuadC = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply(audio, freq, a, b, c, xi)
//}
//case class QuadC(rate: Rate, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends SingleOutUGenSource[QuadCUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _b: IIdxSeq[UGenIn] = b.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _sz_freq = _freq.size
//      val _sz_a = _a.size
//      val _sz_b = _b.size
//      val _sz_c = _c.size
//      val _sz_xi = _xi.size
//      val _exp_ = maxInt(_sz_freq, _sz_a, _sz_b, _sz_c, _sz_xi)
//      IIdxSeq.tabulate(_exp_)(i => QuadCUGen(rate, _freq(i.%(_sz_freq)), _a(i.%(_sz_a)), _b(i.%(_sz_b)), _c(i.%(_sz_c)), _xi(i.%(_sz_xi))))
//   }
//}
//case class QuadCUGen(rate: Rate, freq: UGenIn, a: UGenIn, b: UGenIn, c: UGenIn, xi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, a, b, c, xi))
//object StandardN {
//   def ar: StandardN = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply(audio, freq, k, xi, yi)
//}
//case class StandardN(rate: Rate, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[StandardNUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _k: IIdxSeq[UGenIn] = k.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_k = _k.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_k, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => StandardNUGen(rate, _freq(i.%(_sz_freq)), _k(i.%(_sz_k)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class StandardNUGen(rate: Rate, freq: UGenIn, k: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, k, xi, yi))
//object StandardL {
//   def ar: StandardL = ar()
//   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply(audio, freq, k, xi, yi)
//}
//case class StandardL(rate: Rate, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends SingleOutUGenSource[StandardLUGen] {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _k: IIdxSeq[UGenIn] = k.expand
//      val _xi: IIdxSeq[UGenIn] = xi.expand
//      val _yi: IIdxSeq[UGenIn] = yi.expand
//      val _sz_freq = _freq.size
//      val _sz_k = _k.size
//      val _sz_xi = _xi.size
//      val _sz_yi = _yi.size
//      val _exp_ = maxInt(_sz_freq, _sz_k, _sz_xi, _sz_yi)
//      IIdxSeq.tabulate(_exp_)(i => StandardLUGen(rate, _freq(i.%(_sz_freq)), _k(i.%(_sz_k)), _xi(i.%(_sz_xi)), _yi(i.%(_sz_yi))))
//   }
//}
//case class StandardLUGen(rate: Rate, freq: UGenIn, k: UGenIn, xi: UGenIn, yi: UGenIn) extends SingleOutUGen(IIdxSeq(freq, k, xi, yi))