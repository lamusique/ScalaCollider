/*
 * GrainUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jan 07 00:11:24 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object GrainIn {
   def ar(in: AnyGE, numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(in, numChannels, trig, dur, pan, envBuf, maxGrains)
}
case class GrainIn(in: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource[GrainInUGen] with AudioRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _pan: IIdxSeq[AnyUGenIn] = pan.expand
      val _envBuf: IIdxSeq[AnyUGenIn] = envBuf.expand
      val _maxGrains: IIdxSeq[AnyUGenIn] = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_in = _in.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_in, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => GrainInUGen(_in(i.%(_sz_in)), numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
   }
}
case class GrainInUGen(in: AnyUGenIn, numChannels: Int, trig: AnyUGenIn, dur: AnyUGenIn, pan: AnyUGenIn, envBuf: AnyUGenIn, maxGrains: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, in, pan, envBuf, maxGrains)) with AudioRated
object GrainSin {
   def ar: GrainSin = ar()
   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, freq: AnyGE = 440.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, freq, pan, envBuf, maxGrains)
}
case class GrainSin(numChannels: Int, trig: AnyGE, dur: AnyGE, freq: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource[GrainSinUGen] with AudioRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _freq: IIdxSeq[AnyUGenIn] = freq.expand
      val _pan: IIdxSeq[AnyUGenIn] = pan.expand
      val _envBuf: IIdxSeq[AnyUGenIn] = envBuf.expand
      val _maxGrains: IIdxSeq[AnyUGenIn] = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_freq = _freq.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_freq, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => GrainSinUGen(numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _freq(i.%(_sz_freq)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
   }
}
case class GrainSinUGen(numChannels: Int, trig: AnyUGenIn, dur: AnyUGenIn, freq: AnyUGenIn, pan: AnyUGenIn, envBuf: AnyUGenIn, maxGrains: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, freq, pan, envBuf, maxGrains)) with AudioRated
object GrainFM {
   def ar: GrainFM = ar()
   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, carFreq: AnyGE = 440.0f, modFreq: AnyGE = 200.0f, index: AnyGE = 1.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, carFreq, modFreq, index, pan, envBuf, maxGrains)
}
case class GrainFM(numChannels: Int, trig: AnyGE, dur: AnyGE, carFreq: AnyGE, modFreq: AnyGE, index: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource[GrainFMUGen] with AudioRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _carFreq: IIdxSeq[AnyUGenIn] = carFreq.expand
      val _modFreq: IIdxSeq[AnyUGenIn] = modFreq.expand
      val _index: IIdxSeq[AnyUGenIn] = index.expand
      val _pan: IIdxSeq[AnyUGenIn] = pan.expand
      val _envBuf: IIdxSeq[AnyUGenIn] = envBuf.expand
      val _maxGrains: IIdxSeq[AnyUGenIn] = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_carFreq = _carFreq.size
      val _sz_modFreq = _modFreq.size
      val _sz_index = _index.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_carFreq, _sz_modFreq, _sz_index, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => GrainFMUGen(numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _carFreq(i.%(_sz_carFreq)), _modFreq(i.%(_sz_modFreq)), _index(i.%(_sz_index)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
   }
}
case class GrainFMUGen(numChannels: Int, trig: AnyUGenIn, dur: AnyUGenIn, carFreq: AnyUGenIn, modFreq: AnyUGenIn, index: AnyUGenIn, pan: AnyUGenIn, envBuf: AnyUGenIn, maxGrains: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, carFreq, modFreq, index, pan, envBuf, maxGrains)) with AudioRated
object GrainBuf {
   def ar(buf: AnyGE, numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, speed: AnyGE = 1.0f, pos: AnyGE = 0.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(buf, numChannels, trig, dur, speed, pos, pan, envBuf, maxGrains)
}
case class GrainBuf(buf: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, speed: AnyGE, pos: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends UGenSource[GrainBufUGen] with AudioRated {
   protected def expandUGens = {
      val _trig: IIdxSeq[AnyUGenIn] = trig.expand
      val _dur: IIdxSeq[AnyUGenIn] = dur.expand
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _speed: IIdxSeq[AnyUGenIn] = speed.expand
      val _pos: IIdxSeq[AnyUGenIn] = pos.expand
      val _pan: IIdxSeq[AnyUGenIn] = pan.expand
      val _envBuf: IIdxSeq[AnyUGenIn] = envBuf.expand
      val _maxGrains: IIdxSeq[AnyUGenIn] = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_pos = _pos.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_buf, _sz_speed, _sz_pos, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => GrainBufUGen(_buf(i.%(_sz_buf)), numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _speed(i.%(_sz_speed)), _pos(i.%(_sz_pos)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
   }
}
case class GrainBufUGen(buf: AnyUGenIn, numChannels: Int, trig: AnyUGenIn, dur: AnyUGenIn, speed: AnyUGenIn, pos: AnyUGenIn, pan: AnyUGenIn, envBuf: AnyUGenIn, maxGrains: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, buf, speed, pos, pan, envBuf, maxGrains)) with AudioRated
object Warp1 {
   def ar(numChannels: Int, buf: AnyGE, pos: AnyGE = 0.0f, speed: AnyGE = 1.0f, winSize: AnyGE = 0.2f, envBuf: AnyGE = -1.0f, overlaps: AnyGE = 8.0f, winRand: AnyGE = 0.0f, interp: AnyGE = 1.0f) = apply(numChannels, buf, pos, speed, winSize, envBuf, overlaps, winRand, interp)
}
case class Warp1(numChannels: Int, buf: AnyGE, pos: AnyGE, speed: AnyGE, winSize: AnyGE, envBuf: AnyGE, overlaps: AnyGE, winRand: AnyGE, interp: AnyGE) extends UGenSource[Warp1UGen] with AudioRated {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _pos: IIdxSeq[AnyUGenIn] = pos.expand
      val _speed: IIdxSeq[AnyUGenIn] = speed.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _envBuf: IIdxSeq[AnyUGenIn] = envBuf.expand
      val _overlaps: IIdxSeq[AnyUGenIn] = overlaps.expand
      val _winRand: IIdxSeq[AnyUGenIn] = winRand.expand
      val _interp: IIdxSeq[AnyUGenIn] = interp.expand
      val _sz_buf = _buf.size
      val _sz_pos = _pos.size
      val _sz_speed = _speed.size
      val _sz_winSize = _winSize.size
      val _sz_envBuf = _envBuf.size
      val _sz_overlaps = _overlaps.size
      val _sz_winRand = _winRand.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_buf, _sz_pos, _sz_speed, _sz_winSize, _sz_envBuf, _sz_overlaps, _sz_winRand, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => Warp1UGen(numChannels, _buf(i.%(_sz_buf)), _pos(i.%(_sz_pos)), _speed(i.%(_sz_speed)), _winSize(i.%(_sz_winSize)), _envBuf(i.%(_sz_envBuf)), _overlaps(i.%(_sz_overlaps)), _winRand(i.%(_sz_winRand)), _interp(i.%(_sz_interp))))
   }
}
case class Warp1UGen(numChannels: Int, buf: AnyUGenIn, pos: AnyUGenIn, speed: AnyUGenIn, winSize: AnyUGenIn, envBuf: AnyUGenIn, overlaps: AnyUGenIn, winRand: AnyUGenIn, interp: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, pos, speed, winSize, envBuf, overlaps, winRand, interp)) with AudioRated