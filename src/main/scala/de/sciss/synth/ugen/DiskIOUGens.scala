/*
 * DiskIOUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jan 07 00:11:17 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object DiskIn {
   def ar(numChannels: Int, buf: AnyGE, loop: AnyGE = 0.0f) = apply(numChannels, buf, loop)
}
case class DiskIn(numChannels: Int, buf: AnyGE, loop: AnyGE) extends UGenSource[DiskInUGen] with HasSideEffect with AudioRated {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _sz_buf = _buf.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_buf, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => DiskInUGen(numChannels, _buf(i.%(_sz_buf)), _loop(i.%(_sz_loop))))
   }
}
case class DiskInUGen(numChannels: Int, buf: AnyUGenIn, loop: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, loop)) with HasSideEffect with AudioRated
object DiskOut {
   def ar(buf: AnyGE, in: Expands[GE[audio, UGenIn[audio]]]) = apply(buf, in)
}
case class DiskOut(buf: AnyGE, in: Expands[AnyGE]) extends SingleOutUGenSource[audio, DiskOutUGen] with AudioRated with WritesBuffer {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyGE] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DiskOutUGen(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)).expand))
   }
}
case class DiskOutUGen(buf: AnyUGenIn, in: IIdxSeq[AnyUGenIn]) extends SingleOutUGen[audio](IIdxSeq[AnyUGenIn](buf).++(in)) with AudioRated with WritesBuffer
object VDiskIn {
   def ar(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, loop: AnyGE = 0.0f, sendID: AnyGE = 0.0f) = apply(numChannels, buf, speed, loop, sendID)
}
case class VDiskIn(numChannels: Int, buf: AnyGE, speed: AnyGE, loop: AnyGE, sendID: AnyGE) extends UGenSource[VDiskInUGen] with HasSideEffect with AudioRated {
   protected def expandUGens = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _speed: IIdxSeq[AnyUGenIn] = speed.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _sendID: IIdxSeq[AnyUGenIn] = sendID.expand
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_loop = _loop.size
      val _sz_sendID = _sendID.size
      val _exp_ = maxInt(_sz_buf, _sz_speed, _sz_loop, _sz_sendID)
      IIdxSeq.tabulate(_exp_)(i => VDiskInUGen(numChannels, _buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _loop(i.%(_sz_loop)), _sendID(i.%(_sz_sendID))))
   }
}
case class VDiskInUGen(numChannels: Int, buf: AnyUGenIn, speed: AnyUGenIn, loop: AnyUGenIn, sendID: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, speed, loop, sendID)) with HasSideEffect with AudioRated