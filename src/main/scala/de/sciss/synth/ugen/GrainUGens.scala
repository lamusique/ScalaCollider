/*
 * GrainUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Apr 08 04:10:01 BST 2011
 * ScalaCollider-UGen version: 0.12
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object GrainIn {
   def ar(in: GE, numChannels: Int = 1, trig: GE = 0.0f, dur: GE = 1.0f, pan: GE = 0.0f, envBuf: GE = -1.0f, maxGrains: GE = 512.0f) = apply(in, numChannels, trig, dur, pan, envBuf, maxGrains)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class GrainIn(in: GE, numChannels: Int, trig: GE, dur: GE, pan: GE, envBuf: GE, maxGrains: GE) extends UGenSource.MultiOut("GrainIn") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, in.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object GrainSin {
   def ar: GrainSin = ar()
   def ar(numChannels: Int = 1, trig: GE = 0.0f, dur: GE = 1.0f, freq: GE = 440.0f, pan: GE = 0.0f, envBuf: GE = -1.0f, maxGrains: GE = 512.0f) = apply(numChannels, trig, dur, freq, pan, envBuf, maxGrains)
}
final case class GrainSin(numChannels: Int, trig: GE, dur: GE, freq: GE, pan: GE, envBuf: GE, maxGrains: GE) extends UGenSource.MultiOut("GrainSin") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, freq.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object GrainFM {
   def ar: GrainFM = ar()
   def ar(numChannels: Int = 1, trig: GE = 0.0f, dur: GE = 1.0f, carFreq: GE = 440.0f, modFreq: GE = 200.0f, index: GE = 1.0f, pan: GE = 0.0f, envBuf: GE = -1.0f, maxGrains: GE = 512.0f) = apply(numChannels, trig, dur, carFreq, modFreq, index, pan, envBuf, maxGrains)
}
final case class GrainFM(numChannels: Int, trig: GE, dur: GE, carFreq: GE, modFreq: GE, index: GE, pan: GE, envBuf: GE, maxGrains: GE) extends UGenSource.MultiOut("GrainFM") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, carFreq.expand, modFreq.expand, index.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numChannels)(audio), _args)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object GrainBuf {
   def ar(buf: GE, numChannels: Int = 1, trig: GE = 0.0f, dur: GE = 1.0f, speed: GE = 1.0f, pos: GE = 0.0f, pan: GE = 0.0f, envBuf: GE = -1.0f, maxGrains: GE = 512.0f) = apply(buf, numChannels, trig, dur, speed, pos, pan, envBuf, maxGrains)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class GrainBuf(buf: GE, numChannels: Int, trig: GE, dur: GE, speed: GE, pos: GE, pan: GE, envBuf: GE, maxGrains: GE) extends UGenSource.MultiOut("GrainBuf") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(trig.expand, dur.expand, buf.expand, speed.expand, pos.expand, pan.expand, envBuf.expand, maxGrains.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numChannels)(audio), _args)
}
object Warp1 {
   def ar(numChannels: Int, buf: GE, pos: GE = 0.0f, speed: GE = 1.0f, winSize: GE = 0.2f, envBuf: GE = -1.0f, overlaps: GE = 8.0f, winRand: GE = 0.0f, interp: GE = 1.0f) = apply(numChannels, buf, pos, speed, winSize, envBuf, overlaps, winRand, interp)
}
final case class Warp1(numChannels: Int, buf: GE, pos: GE, speed: GE, winSize: GE, envBuf: GE, overlaps: GE, winRand: GE, interp: GE) extends UGenSource.MultiOut("Warp1") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, pos.expand, speed.expand, winSize.expand, envBuf.expand, overlaps.expand, winRand.expand, interp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numChannels)(audio), _args)
}