/*
 * GrainUGens.scala
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
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object GrainIn {
   def ar(in: AnyGE, numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(in, numChannels, trig, dur, pan, envBuf, maxGrains)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class GrainIn(in: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, in.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("GrainIn", audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object GrainSin {
   def ar: GrainSin = ar()
   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, freq: AnyGE = 440.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, freq, pan, envBuf, maxGrains)
}
final case class GrainSin(numChannels: Int, trig: AnyGE, dur: AnyGE, freq: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, freq.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("GrainSin", audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object GrainFM {
   def ar: GrainFM = ar()
   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, carFreq: AnyGE = 440.0f, modFreq: AnyGE = 200.0f, index: AnyGE = 1.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, carFreq, modFreq, index, pan, envBuf, maxGrains)
}
final case class GrainFM(numChannels: Int, trig: AnyGE, dur: AnyGE, carFreq: AnyGE, modFreq: AnyGE, index: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, carFreq.expand, modFreq.expand, index.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("GrainFM", audio, IIdxSeq.fill(numChannels)(audio), _args)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object GrainBuf {
   def ar(buf: AnyGE, numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, speed: AnyGE = 1.0f, pos: AnyGE = 0.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(buf, numChannels, trig, dur, speed, pos, pan, envBuf, maxGrains)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class GrainBuf(buf: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, speed: AnyGE, pos: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, buf.expand, speed.expand, pos.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("GrainBuf", audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object Warp1 {
   def ar(numChannels: Int, buf: AnyGE, pos: AnyGE = 0.0f, speed: AnyGE = 1.0f, winSize: AnyGE = 0.2f, envBuf: AnyGE = -1.0f, overlaps: AnyGE = 8.0f, winRand: AnyGE = 0.0f, interp: AnyGE = 1.0f) = apply(numChannels, buf, pos, speed, winSize, envBuf, overlaps, winRand, interp)
}
final case class Warp1(numChannels: Int, buf: AnyGE, pos: AnyGE, speed: AnyGE, winSize: AnyGE, envBuf: AnyGE, overlaps: AnyGE, winRand: AnyGE, interp: AnyGE) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, pos.expand, speed.expand, winSize.expand, envBuf.expand, overlaps.expand, winRand.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Warp1", audio, IIdxSeq.fill(numChannels)(audio), _args)
}