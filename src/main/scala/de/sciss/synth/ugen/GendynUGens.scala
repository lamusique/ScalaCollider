/*
 * GendynUGens.scala
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
object Gendy1 {
   def ar: Gendy1[audio] = ar()
   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[audio](audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
   def kr: Gendy1[control] = kr()
   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[control](control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum)
}
final case class Gendy1[R <: Rate](rate: R, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(ampDist.expand, durDist.expand, adParam.expand, ddParam.expand, minFreq.expand, maxFreq.expand, ampScale.expand, durScale.expand, initCPs.expand, kNum.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Gendy1", rate, _args)
}
object Gendy2 {
   def ar: Gendy2[audio] = ar()
   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f, a: AnyGE = 1.17f, c: AnyGE = 0.31f) = apply[audio](audio, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
   def kr: Gendy2[control] = kr()
   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, minFreq: AnyGE = 440.0f, maxFreq: AnyGE = 660.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f, a: AnyGE = 1.17f, c: AnyGE = 0.31f) = apply[control](control, ampDist, durDist, adParam, ddParam, minFreq, maxFreq, ampScale, durScale, initCPs, kNum, a, c)
}
final case class Gendy2[R <: Rate](rate: R, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, minFreq: AnyGE, maxFreq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE, a: AnyGE, c: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(ampDist.expand, durDist.expand, adParam.expand, ddParam.expand, minFreq.expand, maxFreq.expand, ampScale.expand, durScale.expand, initCPs.expand, kNum.expand, a.expand, c.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Gendy2", rate, _args)
}
object Gendy3 {
   def ar: Gendy3[audio] = ar()
   def ar(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, freq: AnyGE = 440.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[audio](audio, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
   def kr: Gendy3[control] = kr()
   def kr(ampDist: AnyGE = 1.0f, durDist: AnyGE = 1.0f, adParam: AnyGE = 1.0f, ddParam: AnyGE = 1.0f, freq: AnyGE = 440.0f, ampScale: AnyGE = 0.5f, durScale: AnyGE = 0.5f, initCPs: AnyGE = 12.0f, kNum: AnyGE = 12.0f) = apply[control](control, ampDist, durDist, adParam, ddParam, freq, ampScale, durScale, initCPs, kNum)
}
final case class Gendy3[R <: Rate](rate: R, ampDist: AnyGE, durDist: AnyGE, adParam: AnyGE, ddParam: AnyGE, freq: AnyGE, ampScale: AnyGE, durScale: AnyGE, initCPs: AnyGE, kNum: AnyGE) extends UGenSource.SingleOut[R] with UsesRandSeed {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(ampDist.expand, durDist.expand, adParam.expand, ddParam.expand, freq.expand, ampScale.expand, durScale.expand, initCPs.expand, kNum.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Gendy3", rate, _args)
}