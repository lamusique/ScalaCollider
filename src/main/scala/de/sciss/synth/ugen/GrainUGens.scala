///*
// * GrainUGens.scala
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
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//object GrainIn {
//   def ar(in: AnyGE, numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(in, numChannels, trig, dur, pan, envBuf, maxGrains)
//}
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//case class GrainIn(in: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[GrainInUGen] with AudioRated {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _in: IIdxSeq[UGenIn] = in.expand
//      val _pan: IIdxSeq[UGenIn] = pan.expand
//      val _envBuf: IIdxSeq[UGenIn] = envBuf.expand
//      val _maxGrains: IIdxSeq[UGenIn] = maxGrains.expand
//      val _sz_trig = _trig.size
//      val _sz_dur = _dur.size
//      val _sz_in = _in.size
//      val _sz_pan = _pan.size
//      val _sz_envBuf = _envBuf.size
//      val _sz_maxGrains = _maxGrains.size
//      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_in, _sz_pan, _sz_envBuf, _sz_maxGrains)
//      IIdxSeq.tabulate(_exp_)(i => GrainInUGen(_in(i.%(_sz_in)), numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
//   }
//}
//case class GrainInUGen(in: UGenIn, numChannels: Int, trig: UGenIn, dur: UGenIn, pan: UGenIn, envBuf: UGenIn, maxGrains: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, in, pan, envBuf, maxGrains)) with AudioRated
//object GrainSin {
//   def ar: GrainSin = ar()
//   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, freq: AnyGE = 440.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, freq, pan, envBuf, maxGrains)
//}
//case class GrainSin(numChannels: Int, trig: AnyGE, dur: AnyGE, freq: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[GrainSinUGen] with AudioRated {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      val _pan: IIdxSeq[UGenIn] = pan.expand
//      val _envBuf: IIdxSeq[UGenIn] = envBuf.expand
//      val _maxGrains: IIdxSeq[UGenIn] = maxGrains.expand
//      val _sz_trig = _trig.size
//      val _sz_dur = _dur.size
//      val _sz_freq = _freq.size
//      val _sz_pan = _pan.size
//      val _sz_envBuf = _envBuf.size
//      val _sz_maxGrains = _maxGrains.size
//      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_freq, _sz_pan, _sz_envBuf, _sz_maxGrains)
//      IIdxSeq.tabulate(_exp_)(i => GrainSinUGen(numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _freq(i.%(_sz_freq)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
//   }
//}
//case class GrainSinUGen(numChannels: Int, trig: UGenIn, dur: UGenIn, freq: UGenIn, pan: UGenIn, envBuf: UGenIn, maxGrains: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, freq, pan, envBuf, maxGrains)) with AudioRated
//object GrainFM {
//   def ar: GrainFM = ar()
//   def ar(numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, carFreq: AnyGE = 440.0f, modFreq: AnyGE = 200.0f, index: AnyGE = 1.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(numChannels, trig, dur, carFreq, modFreq, index, pan, envBuf, maxGrains)
//}
//case class GrainFM(numChannels: Int, trig: AnyGE, dur: AnyGE, carFreq: AnyGE, modFreq: AnyGE, index: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[GrainFMUGen] with AudioRated {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _carFreq: IIdxSeq[UGenIn] = carFreq.expand
//      val _modFreq: IIdxSeq[UGenIn] = modFreq.expand
//      val _index: IIdxSeq[UGenIn] = index.expand
//      val _pan: IIdxSeq[UGenIn] = pan.expand
//      val _envBuf: IIdxSeq[UGenIn] = envBuf.expand
//      val _maxGrains: IIdxSeq[UGenIn] = maxGrains.expand
//      val _sz_trig = _trig.size
//      val _sz_dur = _dur.size
//      val _sz_carFreq = _carFreq.size
//      val _sz_modFreq = _modFreq.size
//      val _sz_index = _index.size
//      val _sz_pan = _pan.size
//      val _sz_envBuf = _envBuf.size
//      val _sz_maxGrains = _maxGrains.size
//      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_carFreq, _sz_modFreq, _sz_index, _sz_pan, _sz_envBuf, _sz_maxGrains)
//      IIdxSeq.tabulate(_exp_)(i => GrainFMUGen(numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _carFreq(i.%(_sz_carFreq)), _modFreq(i.%(_sz_modFreq)), _index(i.%(_sz_index)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
//   }
//}
//case class GrainFMUGen(numChannels: Int, trig: UGenIn, dur: UGenIn, carFreq: UGenIn, modFreq: UGenIn, index: UGenIn, pan: UGenIn, envBuf: UGenIn, maxGrains: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, carFreq, modFreq, index, pan, envBuf, maxGrains)) with AudioRated
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//object GrainBuf {
//   def ar(buf: AnyGE, numChannels: Int = 1, trig: AnyGE = 0.0f, dur: AnyGE = 1.0f, speed: AnyGE = 1.0f, pos: AnyGE = 0.0f, pan: AnyGE = 0.0f, envBuf: AnyGE = -1.0f, maxGrains: AnyGE = 512.0f) = apply(buf, numChannels, trig, dur, speed, pos, pan, envBuf, maxGrains)
//}
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//case class GrainBuf(buf: AnyGE, numChannels: Int, trig: AnyGE, dur: AnyGE, speed: AnyGE, pos: AnyGE, pan: AnyGE, envBuf: AnyGE, maxGrains: AnyGE) extends MultiOutUGenSource[GrainBufUGen] with AudioRated {
//   protected def expandUGens = {
//      val _trig: IIdxSeq[UGenIn] = trig.expand
//      val _dur: IIdxSeq[UGenIn] = dur.expand
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _speed: IIdxSeq[UGenIn] = speed.expand
//      val _pos: IIdxSeq[UGenIn] = pos.expand
//      val _pan: IIdxSeq[UGenIn] = pan.expand
//      val _envBuf: IIdxSeq[UGenIn] = envBuf.expand
//      val _maxGrains: IIdxSeq[UGenIn] = maxGrains.expand
//      val _sz_trig = _trig.size
//      val _sz_dur = _dur.size
//      val _sz_buf = _buf.size
//      val _sz_speed = _speed.size
//      val _sz_pos = _pos.size
//      val _sz_pan = _pan.size
//      val _sz_envBuf = _envBuf.size
//      val _sz_maxGrains = _maxGrains.size
//      val _exp_ = maxInt(_sz_trig, _sz_dur, _sz_buf, _sz_speed, _sz_pos, _sz_pan, _sz_envBuf, _sz_maxGrains)
//      IIdxSeq.tabulate(_exp_)(i => GrainBufUGen(_buf(i.%(_sz_buf)), numChannels, _trig(i.%(_sz_trig)), _dur(i.%(_sz_dur)), _speed(i.%(_sz_speed)), _pos(i.%(_sz_pos)), _pan(i.%(_sz_pan)), _envBuf(i.%(_sz_envBuf)), _maxGrains(i.%(_sz_maxGrains))))
//   }
//}
//case class GrainBufUGen(buf: UGenIn, numChannels: Int, trig: UGenIn, dur: UGenIn, speed: UGenIn, pos: UGenIn, pan: UGenIn, envBuf: UGenIn, maxGrains: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(trig, dur, buf, speed, pos, pan, envBuf, maxGrains)) with AudioRated
//object Warp1 {
//   def ar(numChannels: Int, buf: AnyGE, pos: AnyGE = 0.0f, speed: AnyGE = 1.0f, winSize: AnyGE = 0.2f, envBuf: AnyGE = -1.0f, overlaps: AnyGE = 8.0f, winRand: AnyGE = 0.0f, interp: AnyGE = 1.0f) = apply(numChannels, buf, pos, speed, winSize, envBuf, overlaps, winRand, interp)
//}
//case class Warp1(numChannels: Int, buf: AnyGE, pos: AnyGE, speed: AnyGE, winSize: AnyGE, envBuf: AnyGE, overlaps: AnyGE, winRand: AnyGE, interp: AnyGE) extends MultiOutUGenSource[Warp1UGen] with AudioRated {
//   protected def expandUGens = {
//      val _buf: IIdxSeq[UGenIn] = buf.expand
//      val _pos: IIdxSeq[UGenIn] = pos.expand
//      val _speed: IIdxSeq[UGenIn] = speed.expand
//      val _winSize: IIdxSeq[UGenIn] = winSize.expand
//      val _envBuf: IIdxSeq[UGenIn] = envBuf.expand
//      val _overlaps: IIdxSeq[UGenIn] = overlaps.expand
//      val _winRand: IIdxSeq[UGenIn] = winRand.expand
//      val _interp: IIdxSeq[UGenIn] = interp.expand
//      val _sz_buf = _buf.size
//      val _sz_pos = _pos.size
//      val _sz_speed = _speed.size
//      val _sz_winSize = _winSize.size
//      val _sz_envBuf = _envBuf.size
//      val _sz_overlaps = _overlaps.size
//      val _sz_winRand = _winRand.size
//      val _sz_interp = _interp.size
//      val _exp_ = maxInt(_sz_buf, _sz_pos, _sz_speed, _sz_winSize, _sz_envBuf, _sz_overlaps, _sz_winRand, _sz_interp)
//      IIdxSeq.tabulate(_exp_)(i => Warp1UGen(numChannels, _buf(i.%(_sz_buf)), _pos(i.%(_sz_pos)), _speed(i.%(_sz_speed)), _winSize(i.%(_sz_winSize)), _envBuf(i.%(_sz_envBuf)), _overlaps(i.%(_sz_overlaps)), _winRand(i.%(_sz_winRand)), _interp(i.%(_sz_interp))))
//   }
//}
//case class Warp1UGen(numChannels: Int, buf: UGenIn, pos: UGenIn, speed: UGenIn, winSize: UGenIn, envBuf: UGenIn, overlaps: UGenIn, winRand: UGenIn, interp: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, pos, speed, winSize, envBuf, overlaps, winRand, interp)) with AudioRated