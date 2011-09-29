/*
 * GendynUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Sep 29 16:47:16 CEST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object Gendy1 {
   def ar: Gendy1 = ar()
   def ar(ampDist: GE = 1.0f, durDist: GE = 1.0f, adParam: GE = 1.0f, ddParam: GE = 1.0f, minFreq: GE = 440.0f, maxFreq: GE = 660.0f, ampScale: GE = 0.5f, durScale: GE = 0.5f, initCPs: GE = 12.0f, kNum: GE = 12.0f) = apply(audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
   def kr: Gendy1 = kr()
   def kr(ampDist: GE = 1.0f, durDist: GE = 1.0f, adParam: GE = 1.0f, ddParam: GE = 1.0f, minFreq: GE = 440.0f, maxFreq: GE = 660.0f, ampScale: GE = 0.5f, durScale: GE = 0.5f, initCPs: GE = 12.0f, kNum: GE = 12.0f) = apply(control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
}
final case class Gendy1(rate: Rate, ampDist: GE, durDist: GE, adParam: GE, ddParam: GE, minFreq: GE, maxFreq: GE, ampScale: GE, durScale: GE, initCPs: GE, kNum: GE) extends UGenSource.SingleOut("Gendy1") with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(ampDist.expand, durDist.expand, adParam.expand, ddParam.expand, minFreq.expand, maxFreq.expand, ampScale.expand, durScale.expand, initCPs.expand, kNum.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Gendy2 {
   def ar: Gendy2 = ar()
   def ar(ampDist: GE = 1.0f, durDist: GE = 1.0f, adParam: GE = 1.0f, ddParam: GE = 1.0f, minFreq: GE = 440.0f, maxFreq: GE = 660.0f, ampScale: GE = 0.5f, durScale: GE = 0.5f, initCPs: GE = 12.0f, kNum: GE = 12.0f, a: GE = 1.17f, c: GE = 0.31f) = apply(audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
   def kr: Gendy2 = kr()
   def kr(ampDist: GE = 1.0f, durDist: GE = 1.0f, adParam: GE = 1.0f, ddParam: GE = 1.0f, minFreq: GE = 440.0f, maxFreq: GE = 660.0f, ampScale: GE = 0.5f, durScale: GE = 0.5f, initCPs: GE = 12.0f, kNum: GE = 12.0f, a: GE = 1.17f, c: GE = 0.31f) = apply(control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
}
final case class Gendy2(rate: Rate, ampDist: GE, durDist: GE, adParam: GE, ddParam: GE, minFreq: GE, maxFreq: GE, ampScale: GE, durScale: GE, initCPs: GE, kNum: GE, a: GE, c: GE) extends UGenSource.SingleOut("Gendy2") with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(ampDist.expand, durDist.expand, adParam.expand, ddParam.expand, minFreq.expand, maxFreq.expand, ampScale.expand, durScale.expand, initCPs.expand, kNum.expand, a.expand, c.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object Gendy3 {
   def ar: Gendy3 = ar()
   def ar(ampDist: GE = 1.0f, durDist: GE = 1.0f, adParam: GE = 1.0f, ddParam: GE = 1.0f, freq: GE = 440.0f, ampScale: GE = 0.5f, durScale: GE = 0.5f, initCPs: GE = 12.0f, kNum: GE = 12.0f) = apply(audio, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
   def kr: Gendy3 = kr()
   def kr(ampDist: GE = 1.0f, durDist: GE = 1.0f, adParam: GE = 1.0f, ddParam: GE = 1.0f, freq: GE = 440.0f, ampScale: GE = 0.5f, durScale: GE = 0.5f, initCPs: GE = 12.0f, kNum: GE = 12.0f) = apply(control, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
}
final case class Gendy3(rate: Rate, ampDist: GE, durDist: GE, adParam: GE, ddParam: GE, freq: GE, ampScale: GE, durScale: GE, initCPs: GE, kNum: GE) extends UGenSource.SingleOut("Gendy3") with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(ampDist.expand, durDist.expand, adParam.expand, ddParam.expand, freq.expand, ampScale.expand, durScale.expand, initCPs.expand, kNum.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}