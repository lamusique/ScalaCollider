/*
 * DiskIO.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Tue Jan 04 00:27:14 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object DiskIn {
   def ar(numChannels: Int, buf: AnyGE, loop: AnyGE = 0.0f) = apply(numChannels, buf, loop)
}
case class DiskIn(numChannels: Int, buf: AnyGE, loop: AnyGE) extends Expands[DiskInUGen] with AudioRated {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _loop: IIdxSeq[AnyUGenIn] = loop.expand
      val _sz_buf = _buf.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_buf, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => DiskInUGen(numChannels, _buf(i.%(_sz_buf)), _loop(i.%(_sz_loop))))
   }
}
case class DiskInUGen(numChannels: Int, buf: AnyUGenIn, loop: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, loop)) with AudioRated with HasSideEffect
object DiskOut {
   def ar(buf: AnyGE, in: Expands[GE[audio, UGenIn[audio]]]) = apply(buf, in)
}
case class DiskOut(buf: AnyGE, in: Expands[GE[audio, UGenIn[audio]]]) extends GE[audio, DiskOutUGen] with AudioRated {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[GE[audio, UGenIn[audio]]] = in.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => DiskOutUGen(_buf(i.%(_sz_buf)), _in(i.%(_sz_in))))
   }
}
case class DiskOutUGen(buf: AnyUGenIn, in: GE[audio, UGenIn[audio]]) extends SingleOutUGen[audio](in.expand.+:(buf)) with AudioRated with HasSideEffect
object VDiskIn {
   def ar(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, loop: AnyGE = 0.0f, sendID: AnyGE = 0.0f) = apply(numChannels, buf, speed, loop, sendID)
}
case class VDiskIn(numChannels: Int, buf: AnyGE, speed: AnyGE, loop: AnyGE, sendID: AnyGE) extends Expands[VDiskInUGen] with AudioRated {
   def expand = {
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
case class VDiskInUGen(numChannels: Int, buf: AnyUGenIn, speed: AnyUGenIn, loop: AnyUGenIn, sendID: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(buf, speed, loop, sendID)) with AudioRated with HasSideEffect