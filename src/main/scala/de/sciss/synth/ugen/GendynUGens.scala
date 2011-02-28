///*
// * GendynUGens.scala
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
//object Gendy1 {
//   def ar: Gendy1 = ar()
//   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply(audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
//   def kr: Gendy1 = kr()
//   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply(control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
//}
//case class Gendy1(rate: Rate, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE) extends SingleOutUGenSource[Gendy1UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _ampDist: IIdxSeq[UGenIn] = ampDist.expand
//      val _durDist: IIdxSeq[UGenIn] = durDist.expand
//      val _adParam: IIdxSeq[UGenIn] = adParam.expand
//      val _ddParam: IIdxSeq[UGenIn] = ddParam.expand
//      val _minFreq: IIdxSeq[UGenIn] = minFreq.expand
//      val _maxFreq: IIdxSeq[UGenIn] = maxFreq.expand
//      val _ampScale: IIdxSeq[UGenIn] = ampScale.expand
//      val _durScale: IIdxSeq[UGenIn] = durScale.expand
//      val _initCPs: IIdxSeq[UGenIn] = initCPs.expand
//      val _kNum: IIdxSeq[UGenIn] = kNum.expand
//      val _sz_ampDist = _ampDist.size
//      val _sz_durDist = _durDist.size
//      val _sz_adParam = _adParam.size
//      val _sz_ddParam = _ddParam.size
//      val _sz_minFreq = _minFreq.size
//      val _sz_maxFreq = _maxFreq.size
//      val _sz_ampScale = _ampScale.size
//      val _sz_durScale = _durScale.size
//      val _sz_initCPs = _initCPs.size
//      val _sz_kNum = _kNum.size
//      val _exp_ = maxInt(_sz_ampDist, _sz_durDist, _sz_adParam, _sz_ddParam, _sz_minFreq, _sz_maxFreq, _sz_ampScale, _sz_durScale, _sz_initCPs, _sz_kNum)
//      IIdxSeq.tabulate(_exp_)(i => Gendy1UGen(rate, _ampDist(i.%(_sz_ampDist)), _durDist(i.%(_sz_durDist)), _adParam(i.%(_sz_adParam)), _ddParam(i.%(_sz_ddParam)), _minFreq(i.%(_sz_minFreq)), _maxFreq(i.%(_sz_maxFreq)), _ampScale(i.%(_sz_ampScale)), _durScale(i.%(_sz_durScale)), _initCPs(i.%(_sz_initCPs)), _kNum(i.%(_sz_kNum))))
//   }
//}
//case class Gendy1UGen(rate: Rate, ampDist: UGenIn, durDist: UGenIn, adParam: UGenIn, ddParam: UGenIn, minFreq: UGenIn, maxFreq: UGenIn, ampScale: UGenIn, durScale: UGenIn, initCPs: UGenIn, kNum: UGenIn) extends SingleOutUGen(IIdxSeq(ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)) with UsesRandSeed
//object Gendy2 {
//   def ar: Gendy2 = ar()
//   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f, a: AnyGE = 1.17f, c: AnyGE = 0.31f) = apply(audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
//   def kr: Gendy2 = kr()
//   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f, a: AnyGE = 1.17f, c: AnyGE = 0.31f) = apply(control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
//}
//case class Gendy2(rate: Rate, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE, a: AnyGE, c: AnyGE) extends SingleOutUGenSource[Gendy2UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _ampDist: IIdxSeq[UGenIn] = ampDist.expand
//      val _durDist: IIdxSeq[UGenIn] = durDist.expand
//      val _adParam: IIdxSeq[UGenIn] = adParam.expand
//      val _ddParam: IIdxSeq[UGenIn] = ddParam.expand
//      val _minFreq: IIdxSeq[UGenIn] = minFreq.expand
//      val _maxFreq: IIdxSeq[UGenIn] = maxFreq.expand
//      val _ampScale: IIdxSeq[UGenIn] = ampScale.expand
//      val _durScale: IIdxSeq[UGenIn] = durScale.expand
//      val _initCPs: IIdxSeq[UGenIn] = initCPs.expand
//      val _kNum: IIdxSeq[UGenIn] = kNum.expand
//      val _a: IIdxSeq[UGenIn] = a.expand
//      val _c: IIdxSeq[UGenIn] = c.expand
//      val _sz_ampDist = _ampDist.size
//      val _sz_durDist = _durDist.size
//      val _sz_adParam = _adParam.size
//      val _sz_ddParam = _ddParam.size
//      val _sz_minFreq = _minFreq.size
//      val _sz_maxFreq = _maxFreq.size
//      val _sz_ampScale = _ampScale.size
//      val _sz_durScale = _durScale.size
//      val _sz_initCPs = _initCPs.size
//      val _sz_kNum = _kNum.size
//      val _sz_a = _a.size
//      val _sz_c = _c.size
//      val _exp_ = maxInt(_sz_ampDist, _sz_durDist, _sz_adParam, _sz_ddParam, _sz_minFreq, _sz_maxFreq, _sz_ampScale, _sz_durScale, _sz_initCPs, _sz_kNum, _sz_a, _sz_c)
//      IIdxSeq.tabulate(_exp_)(i => Gendy2UGen(rate, _ampDist(i.%(_sz_ampDist)), _durDist(i.%(_sz_durDist)), _adParam(i.%(_sz_adParam)), _ddParam(i.%(_sz_ddParam)), _minFreq(i.%(_sz_minFreq)), _maxFreq(i.%(_sz_maxFreq)), _ampScale(i.%(_sz_ampScale)), _durScale(i.%(_sz_durScale)), _initCPs(i.%(_sz_initCPs)), _kNum(i.%(_sz_kNum)), _a(i.%(_sz_a)), _c(i.%(_sz_c))))
//   }
//}
//case class Gendy2UGen(rate: Rate, ampDist: UGenIn, durDist: UGenIn, adParam: UGenIn, ddParam: UGenIn, minFreq: UGenIn, maxFreq: UGenIn, ampScale: UGenIn, durScale: UGenIn, initCPs: UGenIn, kNum: UGenIn, a: UGenIn, c: UGenIn) extends SingleOutUGen(IIdxSeq(ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)) with UsesRandSeed
//object Gendy3 {
//   def ar: Gendy3 = ar()
//   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, freq: AnyGE = 440.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply(audio, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
//   def kr: Gendy3 = kr()
//   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, freq: AnyGE = 440.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply(control, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
//}
//case class Gendy3(rate: Rate, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, freq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE) extends SingleOutUGenSource[Gendy3UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _ampDist: IIdxSeq[UGenIn] = ampDist.expand
//      val _durDist: IIdxSeq[UGenIn] = durDist.expand
//      val _adParam: IIdxSeq[UGenIn] = adParam.expand
//      val _ddParam: IIdxSeq[UGenIn] = ddParam.expand
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _ampScale: IIdxSeq[UGenIn] = ampScale.expand
//      val _durScale: IIdxSeq[UGenIn] = durScale.expand
//      val _initCPs: IIdxSeq[UGenIn] = initCPs.expand
//      val _kNum: IIdxSeq[UGenIn] = kNum.expand
//      val _sz_ampDist = _ampDist.size
//      val _sz_durDist = _durDist.size
//      val _sz_adParam = _adParam.size
//      val _sz_ddParam = _ddParam.size
//      val _sz_freq = _freq.size
//      val _sz_ampScale = _ampScale.size
//      val _sz_durScale = _durScale.size
//      val _sz_initCPs = _initCPs.size
//      val _sz_kNum = _kNum.size
//      val _exp_ = maxInt(_sz_ampDist, _sz_durDist, _sz_adParam, _sz_ddParam, _sz_freq, _sz_ampScale, _sz_durScale, _sz_initCPs, _sz_kNum)
//      IIdxSeq.tabulate(_exp_)(i => Gendy3UGen(rate, _ampDist(i.%(_sz_ampDist)), _durDist(i.%(_sz_durDist)), _adParam(i.%(_sz_adParam)), _ddParam(i.%(_sz_ddParam)), _freq(i.%(_sz_freq)), _ampScale(i.%(_sz_ampScale)), _durScale(i.%(_sz_durScale)), _initCPs(i.%(_sz_initCPs)), _kNum(i.%(_sz_kNum))))
//   }
//}
//case class Gendy3UGen(rate: Rate, ampDist: UGenIn, durDist: UGenIn, adParam: UGenIn, ddParam: UGenIn, freq: UGenIn, ampScale: UGenIn, durScale: UGenIn, initCPs: UGenIn, kNum: UGenIn) extends SingleOutUGen(IIdxSeq(ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)) with UsesRandSeed