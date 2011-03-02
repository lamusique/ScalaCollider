/*
 * GendynUGens.scala
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
object Gendy1 {
   def ar: Gendy1[audio] = ar()
   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[audio](audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
   def kr: Gendy1[control] = kr()
   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[control](control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
}
final case class Gendy1[R <: Rate](rate: R, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _ampDist = ampDist.expand
      val _durDist = durDist.expand
      val _adParam = adParam.expand
      val _ddParam = ddParam.expand
      val _minFreq = minFreq.expand
      val _maxFreq = maxFreq.expand
      val _ampScale = ampScale.expand
      val _durScale = durScale.expand
      val _initCPs = initCPs.expand
      val _kNum = kNum.expand
      val _sz_ampDist = _ampDist.size
      val _sz_durDist = _durDist.size
      val _sz_adParam = _adParam.size
      val _sz_ddParam = _ddParam.size
      val _sz_minFreq = _minFreq.size
      val _sz_maxFreq = _maxFreq.size
      val _sz_ampScale = _ampScale.size
      val _sz_durScale = _durScale.size
      val _sz_initCPs = _initCPs.size
      val _sz_kNum = _kNum.size
      val _exp_ = maxInt(_sz_ampDist, _sz_durDist, _sz_adParam, _sz_ddParam, _sz_minFreq, _sz_maxFreq, _sz_ampScale, _sz_durScale, _sz_initCPs, _sz_kNum)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Gendy1", rate, IIdxSeq(_ampDist(i.%(_sz_ampDist)), _durDist(i.%(_sz_durDist)), _adParam(i.%(_sz_adParam)), _ddParam(i.%(_sz_ddParam)), _minFreq(i.%(_sz_minFreq)), _maxFreq(i.%(_sz_maxFreq)), _ampScale(i.%(_sz_ampScale)), _durScale(i.%(_sz_durScale)), _initCPs(i.%(_sz_initCPs)), _kNum(i.%(_sz_kNum)))))
   }
}
object Gendy2 {
   def ar: Gendy2[audio] = ar()
   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f, a: AnyGE = 1.17f, c: AnyGE = 0.31f) = apply[audio](audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
   def kr: Gendy2[control] = kr()
   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f, a: AnyGE = 1.17f, c: AnyGE = 0.31f) = apply[control](control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
}
final case class Gendy2[R <: Rate](rate: R, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE, a: AnyGE, c: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _ampDist = ampDist.expand
      val _durDist = durDist.expand
      val _adParam = adParam.expand
      val _ddParam = ddParam.expand
      val _minFreq = minFreq.expand
      val _maxFreq = maxFreq.expand
      val _ampScale = ampScale.expand
      val _durScale = durScale.expand
      val _initCPs = initCPs.expand
      val _kNum = kNum.expand
      val _a = a.expand
      val _c = c.expand
      val _sz_ampDist = _ampDist.size
      val _sz_durDist = _durDist.size
      val _sz_adParam = _adParam.size
      val _sz_ddParam = _ddParam.size
      val _sz_minFreq = _minFreq.size
      val _sz_maxFreq = _maxFreq.size
      val _sz_ampScale = _ampScale.size
      val _sz_durScale = _durScale.size
      val _sz_initCPs = _initCPs.size
      val _sz_kNum = _kNum.size
      val _sz_a = _a.size
      val _sz_c = _c.size
      val _exp_ = maxInt(_sz_ampDist, _sz_durDist, _sz_adParam, _sz_ddParam, _sz_minFreq, _sz_maxFreq, _sz_ampScale, _sz_durScale, _sz_initCPs, _sz_kNum, _sz_a, _sz_c)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Gendy2", rate, IIdxSeq(_ampDist(i.%(_sz_ampDist)), _durDist(i.%(_sz_durDist)), _adParam(i.%(_sz_adParam)), _ddParam(i.%(_sz_ddParam)), _minFreq(i.%(_sz_minFreq)), _maxFreq(i.%(_sz_maxFreq)), _ampScale(i.%(_sz_ampScale)), _durScale(i.%(_sz_durScale)), _initCPs(i.%(_sz_initCPs)), _kNum(i.%(_sz_kNum)), _a(i.%(_sz_a)), _c(i.%(_sz_c)))))
   }
}
object Gendy3 {
   def ar: Gendy3[audio] = ar()
   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, freq: AnyGE = 440.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[audio](audio, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
   def kr: Gendy3[control] = kr()
   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, freq: AnyGE = 440.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[control](control, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
}
final case class Gendy3[R <: Rate](rate: R, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, freq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE) extends SingleOutUGenSource[R] with UsesRandSeed {
   protected def expandUGens = {
      val _ampDist = ampDist.expand
      val _durDist = durDist.expand
      val _adParam = adParam.expand
      val _ddParam = ddParam.expand
      val _freq = freq.expand
      val _ampScale = ampScale.expand
      val _durScale = durScale.expand
      val _initCPs = initCPs.expand
      val _kNum = kNum.expand
      val _sz_ampDist = _ampDist.size
      val _sz_durDist = _durDist.size
      val _sz_adParam = _adParam.size
      val _sz_ddParam = _ddParam.size
      val _sz_freq = _freq.size
      val _sz_ampScale = _ampScale.size
      val _sz_durScale = _durScale.size
      val _sz_initCPs = _initCPs.size
      val _sz_kNum = _kNum.size
      val _exp_ = maxInt(_sz_ampDist, _sz_durDist, _sz_adParam, _sz_ddParam, _sz_freq, _sz_ampScale, _sz_durScale, _sz_initCPs, _sz_kNum)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Gendy3", rate, IIdxSeq(_ampDist(i.%(_sz_ampDist)), _durDist(i.%(_sz_durDist)), _adParam(i.%(_sz_adParam)), _ddParam(i.%(_sz_ddParam)), _freq(i.%(_sz_freq)), _ampScale(i.%(_sz_ampScale)), _durScale(i.%(_sz_durScale)), _initCPs(i.%(_sz_initCPs)), _kNum(i.%(_sz_kNum)))))
   }
}