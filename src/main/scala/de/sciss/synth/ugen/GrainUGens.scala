/*
 * GrainUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
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
final case class GrainIn(in: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[audio] {
   protected def expandUGens = {
      val _trig = trig.expand
      val _dur = dur.expand
      val _in = in.expand
      val _pan = pan.expand
      val _envBuf = envBuf.expand
      val _maxGrains = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_in = _in.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_in, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("GrainIn", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _in(i.%(_sz_in)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains)))))
   }
}
object GrainSin {
   def ar: GrainSin = ar()
   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, freq: AnyGE = 440.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, freq, pan, envBuf, maxGrains)
}
final case class GrainSin(numChannels: Int, trig: AnyGE, dur: AnyGE, freq: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[audio] {
   protected def expandUGens = {
      val _trig = trig.expand
      val _dur = dur.expand
      val _freq = freq.expand
      val _pan = pan.expand
      val _envBuf = envBuf.expand
      val _maxGrains = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_freq = _freq.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_freq, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("GrainSin", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _freq(i.%(_sz_freq)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains)))))
   }
}
object GrainFM {
   def ar: GrainFM = ar()
   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, carFreq: AnyGE = 440.0f, modFreq: AnyGE = 200.0f, index: AnyGE = 1.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, carFreq, modFreq, index, pan, envBuf, maxGrains)
}
final case class GrainFM(numChannels: Int, trig: AnyGE, dur: AnyGE, carFreq: AnyGE, modFreq: AnyGE, index: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[audio] {
   protected def expandUGens = {
      val _trig = trig.expand
      val _dur = dur.expand
      val _carFreq = carFreq.expand
      val _modFreq = modFreq.expand
      val _index = index.expand
      val _pan = pan.expand
      val _envBuf = envBuf.expand
      val _maxGrains = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_carFreq = _carFreq.size
      val _sz_modFreq = _modFreq.size
      val _sz_index = _index.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_carFreq, _sz_modFreq, _sz_index, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("GrainFM", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _carFreq(i.%(_sz_carFreq)), _modFreq(i.%(_sz_modFreq)), _index(i.%(_sz_index)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains)))))
   }
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
final case class GrainBuf(buf: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, speed: AnyGE, pos: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[audio] {
   protected def expandUGens = {
      val _trig = trig.expand
      val _dur = dur.expand
      val _buf = buf.expand
      val _speed = speed.expand
      val _pos = pos.expand
      val _pan = pan.expand
      val _envBuf = envBuf.expand
      val _maxGrains = maxGrains.expand
      val _sz_trig = _trig.size
      val _sz_dur = _dur.size
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_pos = _pos.size
      val _sz_pan = _pan.size
      val _sz_envBuf = _envBuf.size
      val _sz_maxGrains = _maxGrains.size
      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_buf, _sz_speed, _sz_pos, _sz_pan, _sz_envBuf, _sz_maxGrains)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("GrainBuf", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _pos(i.%(_sz_pos)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains)))))
   }
}
object Warp1 {
   def ar(numChannels: Int, buf: AnyGE, pos: AnyGE = 0.0f, speed: AnyGE = 1.0f, winSize: AnyGE = 0.2f, envBuf: AnyGE = -1.0f, overlaps: AnyGE = 8.0f, winRand: AnyGE = 0.0f, interp: AnyGE = 1.0f) = apply(numChannels, buf, pos, speed, winSize, envBuf, overlaps, winRand, interp)
}
final case class Warp1(numChannels: Int, buf: AnyGE, pos: AnyGE, speed: AnyGE, winSize: AnyGE, envBuf: AnyGE, overlaps: AnyGE, winRand: AnyGE, interp: AnyGE) extends MultiOutUGenSource[audio] {
   protected def expandUGens = {
      val _buf = buf.expand
      val _pos = pos.expand
      val _speed = speed.expand
      val _winSize = winSize.expand
      val _envBuf = envBuf.expand
      val _overlaps = overlaps.expand
      val _winRand = winRand.expand
      val _interp = interp.expand
      val _sz_buf = _buf.size
      val _sz_pos = _pos.size
      val _sz_speed = _speed.size
      val _sz_winSize = _winSize.size
      val _sz_envBuf = _envBuf.size
      val _sz_overlaps = _overlaps.size
      val _sz_winRand = _winRand.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_buf, _sz_pos, _sz_speed, _sz_winSize, _sz_envBuf, _sz_overlaps, _sz_winRand, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("Warp1", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_buf(i.%(_sz_buf)), _pos(i.%(_sz_pos)), _speed(i.%(_sz_speed)), _winSize(i.%(_sz_winSize)), _envBuf(i.%(_sz_envBuf)), _overlaps(i.%(_sz_overlaps)), _winRand(i.%(_sz_winRand)), _interp(i.%(_sz_interp)))))
   }
}