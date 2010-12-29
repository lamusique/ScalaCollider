///*
// * DiskIO.scala
// * (ScalaCollider-UGens)
// *
// * This is a synthetically generated file.
// * Created: Tue Dec 28 22:21:21 CET 2010
// * ScalaCollider-UGen version: 0.10
// */
//
//package de.sciss.synth
//package ugen
//import collection.immutable.{IndexedSeq => IIdxSeq}
//import SynthGraph._
//object DiskIn {
//   def ar(numChannels: Int, buf: GE[AnyUGenIn], loop: GE[AnyUGenIn] = 0.0f) = apply(numChannels, buf, loop)
//}
//case class DiskIn(numChannels: Int, buf: GE[AnyUGenIn], loop: GE[AnyUGenIn]) extends GE[DiskInUGen] {
//   def expand = {
//      val _buf = buf.expand
//      val _loop = loop.expand
//      val _sz_buf = _buf.size
//      val _sz_loop = _loop.size
//      val _exp_ = max(_sz_buf, _sz_loop)
//      IIdxSeq.tabulate(_exp_)(i => DiskInUGen(numChannels, _buf(i.%(_sz_buf)), _loop(i.%(_sz_loop))))
//   }
//}
//case class DiskInUGen(numChannels: Int, buf: AnyUGenIn, loop: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, loop)) with AudioRated with HasSideEffect
//object DiskOut {
//   def ar(buf: GE[AnyUGenIn], in: GE[GE[UGenIn[audio]]]) = apply(buf, in)
//}
//case class DiskOut(buf: GE[AnyUGenIn], in: GE[GE[UGenIn[audio]]]) extends GE[DiskOutUGen] {
//   def expand = {
//      val _buf = buf.expand
//      val _in = in.expand
//      val _sz_buf = _buf.size
//      val _sz_in = _in.size
//      val _exp_ = max(_sz_buf, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => DiskOutUGen(_buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
//   }
//}
//case class DiskOutUGen(buf: AnyUGenIn, in: GE[UGenIn[audio]]) extends SingleOutUGen[audio](in.expand.+:(buf)) with AudioRated with HasSideEffect
//object VDiskIn {
//   def ar(numChannels: Int, buf: GE[AnyUGenIn], speed: GE[AnyUGenIn] = 1.0f, loop: GE[AnyUGenIn] = 0.0f, sendID: GE[AnyUGenIn] = 0.0f) = apply(numChannels, buf, speed, loop, sendID)
//}
//case class VDiskIn(numChannels: Int, buf: GE[AnyUGenIn], speed: GE[AnyUGenIn], loop: GE[AnyUGenIn], sendID: GE[AnyUGenIn]) extends GE[VDiskInUGen] {
//   def expand = {
//      val _buf = buf.expand
//      val _speed = speed.expand
//      val _loop = loop.expand
//      val _sendID = sendID.expand
//      val _sz_buf = _buf.size
//      val _sz_speed = _speed.size
//      val _sz_loop = _loop.size
//      val _sz_sendID = _sendID.size
//      val _exp_ = max(_sz_buf, _sz_speed, _sz_loop, _sz_sendID)
//      IIdxSeq.tabulate(_exp_)(i => VDiskInUGen(numChannels, _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _loop(i.%(_sz_loop)), _sendID(i.%(_sz_sendID))))
//   }
//}
//case class VDiskInUGen(numChannels: Int, buf: AnyUGenIn, speed: AnyUGenIn, loop: AnyUGenIn, sendID: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, speed, loop, sendID)) with AudioRated with HasSideEffect