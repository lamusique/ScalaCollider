/*
 * PanUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Jan 05 15:41:21 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object Pan2 {
   def kr(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, pos, level)
   def ar(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, pos, level)
}
case class Pan2[R <: Rate](rate: R, in: AnyGE, pos: AnyGE, level: AnyGE) extends Expands[Pan2UGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _pos: IIdxSeq[AnyUGenIn] = pos.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_pos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => Pan2UGen(rate, _in(i.%(_sz_in)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level))))
   }
}
case class Pan2UGen[R <: Rate](rate: R, in: AnyUGenIn, pos: AnyUGenIn, level: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, pos, level))
object Pan4 {
   def kr(in: AnyGE, xpos: AnyGE = 0.0f, ypos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, xpos, ypos, level)
   def ar(in: AnyGE, xpos: AnyGE = 0.0f, ypos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, xpos, ypos, level)
}
case class Pan4[R <: Rate](rate: R, in: AnyGE, xpos: AnyGE, ypos: AnyGE, level: AnyGE) extends Expands[Pan4UGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _xpos: IIdxSeq[AnyUGenIn] = xpos.expand
      val _ypos: IIdxSeq[AnyUGenIn] = ypos.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_xpos = _xpos.size
      val _sz_ypos = _ypos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_xpos, _sz_ypos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => Pan4UGen(rate, _in(i.%(_sz_in)), _xpos(i.%(_sz_xpos)), _ypos(i.%(_sz_ypos)), _level(i.%(_sz_level))))
   }
}
case class Pan4UGen[R <: Rate](rate: R, in: AnyUGenIn, xpos: AnyUGenIn, ypos: AnyUGenIn, level: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(4)(rate), IIdxSeq(in, xpos, ypos, level))
object LinPan2 {
   def kr(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, pos, level)
   def ar(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, pos, level)
}
case class LinPan2[R <: Rate](rate: R, in: AnyGE, pos: AnyGE, level: AnyGE) extends Expands[LinPan2UGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _pos: IIdxSeq[AnyUGenIn] = pos.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_pos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => LinPan2UGen(rate, _in(i.%(_sz_in)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level))))
   }
}
case class LinPan2UGen[R <: Rate](rate: R, in: AnyUGenIn, pos: AnyUGenIn, level: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, pos, level))
object Balance2 {
   def kr(left: AnyGE, right: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, left, right, pos, level)
   def ar(left: AnyGE, right: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, left, right, pos, level)
}
case class Balance2[R <: Rate](rate: R, left: AnyGE, right: AnyGE, pos: AnyGE, level: AnyGE) extends Expands[Balance2UGen[R]] {
   def expand = {
      val _left: IIdxSeq[AnyUGenIn] = left.expand
      val _right: IIdxSeq[AnyUGenIn] = right.expand
      val _pos: IIdxSeq[AnyUGenIn] = pos.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_left = _left.size
      val _sz_right = _right.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_left, _sz_right, _sz_pos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => Balance2UGen(rate, _left(i.%(_sz_left)), _right(i.%(_sz_right)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level))))
   }
}
case class Balance2UGen[R <: Rate](rate: R, left: AnyUGenIn, right: AnyUGenIn, pos: AnyUGenIn, level: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(left, right, pos, level))
object Rotate2 {
   def kr(x: AnyGE, y: AnyGE, pos: AnyGE = 0.0f) = apply[control](control, x, y, pos)
   def ar(x: AnyGE, y: AnyGE, pos: AnyGE = 0.0f) = apply[audio](audio, x, y, pos)
}
case class Rotate2[R <: Rate](rate: R, x: AnyGE, y: AnyGE, pos: AnyGE) extends Expands[Rotate2UGen[R]] {
   def expand = {
      val _x: IIdxSeq[AnyUGenIn] = x.expand
      val _y: IIdxSeq[AnyUGenIn] = y.expand
      val _pos: IIdxSeq[AnyUGenIn] = pos.expand
      val _sz_x = _x.size
      val _sz_y = _y.size
      val _sz_pos = _pos.size
      val _exp_ = maxInt(_sz_x, _sz_y, _sz_pos)
      IIdxSeq.tabulate(_exp_)(i => Rotate2UGen(rate, _x(i.%(_sz_x)), _y(i.%(_sz_y)), _pos(i.%(_sz_pos))))
   }
}
case class Rotate2UGen[R <: Rate](rate: R, x: AnyUGenIn, y: AnyUGenIn, pos: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(x, y, pos))
object XFade2 {
   def kr(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, inA, inB, pan, level)
   def ar(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, inA, inB, pan, level)
}
case class XFade2[R <: Rate](rate: R, inA: AnyGE, inB: AnyGE, pan: AnyGE, level: AnyGE) extends GE[R, XFade2UGen[R]] {
   def expand = {
      val _inA: IIdxSeq[AnyUGenIn] = inA.expand
      val _inB: IIdxSeq[AnyUGenIn] = inB.expand
      val _pan: IIdxSeq[AnyUGenIn] = pan.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_inA = _inA.size
      val _sz_inB = _inB.size
      val _sz_pan = _pan.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_inA, _sz_inB, _sz_pan, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => XFade2UGen(rate, _inA(i.%(_sz_inA)), _inB(i.%(_sz_inB)), _pan(i.%(_sz_pan)), _level(i.%(_sz_level))))
   }
}
case class XFade2UGen[R <: Rate](rate: R, inA: AnyUGenIn, inB: AnyUGenIn, pan: AnyUGenIn, level: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(inA, inB, pan, level))
object LinXFade2 {
   def kr(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, inA, inB, pan, level)
   def ar(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, inA, inB, pan, level)
}
case class LinXFade2[R <: Rate](rate: R, inA: AnyGE, inB: AnyGE, pan: AnyGE, level: AnyGE) extends GE[R, LinXFade2UGen[R]] {
   def expand = {
      val _inA: IIdxSeq[AnyUGenIn] = inA.expand
      val _inB: IIdxSeq[AnyUGenIn] = inB.expand
      val _pan: IIdxSeq[AnyUGenIn] = pan.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_inA = _inA.size
      val _sz_inB = _inB.size
      val _sz_pan = _pan.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_inA, _sz_inB, _sz_pan, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => LinXFade2UGen(rate, _inA(i.%(_sz_inA)), _inB(i.%(_sz_inB)), _pan(i.%(_sz_pan)), _level(i.%(_sz_level))))
   }
}
case class LinXFade2UGen[R <: Rate](rate: R, inA: AnyUGenIn, inB: AnyUGenIn, pan: AnyUGenIn, level: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(inA, inB, pan, level))
object PanB {
   def kr(in: AnyGE, azimuth: AnyGE = 0.0f, elevation: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, azimuth, elevation, level)
   def ar(in: AnyGE, azimuth: AnyGE = 0.0f, elevation: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, azimuth, elevation, level)
}
case class PanB[R <: Rate](rate: R, in: AnyGE, azimuth: AnyGE, elevation: AnyGE, level: AnyGE) extends Expands[PanBUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _azimuth: IIdxSeq[AnyUGenIn] = azimuth.expand
      val _elevation: IIdxSeq[AnyUGenIn] = elevation.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_azimuth = _azimuth.size
      val _sz_elevation = _elevation.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_azimuth, _sz_elevation, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => PanBUGen(rate, _in(i.%(_sz_in)), _azimuth(i.%(_sz_azimuth)), _elevation(i.%(_sz_elevation)), _level(i.%(_sz_level))))
   }
}
case class PanBUGen[R <: Rate](rate: R, in: AnyUGenIn, azimuth: AnyUGenIn, elevation: AnyUGenIn, level: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(4)(rate), IIdxSeq(in, azimuth, elevation, level))
object PanB2 {
   def kr(in: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, azimuth, level)
   def ar(in: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, azimuth, level)
}
case class PanB2[R <: Rate](rate: R, in: AnyGE, azimuth: AnyGE, level: AnyGE) extends Expands[PanB2UGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _azimuth: IIdxSeq[AnyUGenIn] = azimuth.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_azimuth = _azimuth.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_azimuth, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => PanB2UGen(rate, _in(i.%(_sz_in)), _azimuth(i.%(_sz_azimuth)), _level(i.%(_sz_level))))
   }
}
case class PanB2UGen[R <: Rate](rate: R, in: AnyUGenIn, azimuth: AnyUGenIn, level: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(3)(rate), IIdxSeq(in, azimuth, level))
object BiPanB2 {
   def kr(inA: AnyGE, inB: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, inA, inB, azimuth, level)
   def ar(inA: AnyGE, inB: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, inA, inB, azimuth, level)
}
case class BiPanB2[R <: Rate](rate: R, inA: AnyGE, inB: AnyGE, azimuth: AnyGE, level: AnyGE) extends Expands[BiPanB2UGen[R]] {
   def expand = {
      val _inA: IIdxSeq[AnyUGenIn] = inA.expand
      val _inB: IIdxSeq[AnyUGenIn] = inB.expand
      val _azimuth: IIdxSeq[AnyUGenIn] = azimuth.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _sz_inA = _inA.size
      val _sz_inB = _inB.size
      val _sz_azimuth = _azimuth.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_inA, _sz_inB, _sz_azimuth, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => BiPanB2UGen(rate, _inA(i.%(_sz_inA)), _inB(i.%(_sz_inB)), _azimuth(i.%(_sz_azimuth)), _level(i.%(_sz_level))))
   }
}
case class BiPanB2UGen[R <: Rate](rate: R, inA: AnyUGenIn, inB: AnyUGenIn, azimuth: AnyUGenIn, level: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(3)(rate), IIdxSeq(inA, inB, azimuth, level))
object PanAz {
   def kr(numChannels: Int, in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f, width: AnyGE = 2.0f, orient: AnyGE = 0.0f) = apply[control](control, numChannels, in, pos, level, width, orient)
   def ar(numChannels: Int, in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f, width: AnyGE = 2.0f, orient: AnyGE = 0.0f) = apply[audio](audio, numChannels, in, pos, level, width, orient)
}
case class PanAz[R <: Rate](rate: R, numChannels: Int, in: AnyGE, pos: AnyGE, level: AnyGE, width: AnyGE, orient: AnyGE) extends Expands[PanAzUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _pos: IIdxSeq[AnyUGenIn] = pos.expand
      val _level: IIdxSeq[AnyUGenIn] = level.expand
      val _width: IIdxSeq[AnyUGenIn] = width.expand
      val _orient: IIdxSeq[AnyUGenIn] = orient.expand
      val _sz_in = _in.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _sz_width = _width.size
      val _sz_orient = _orient.size
      val _exp_ = maxInt(_sz_in, _sz_pos, _sz_level, _sz_width, _sz_orient)
      IIdxSeq.tabulate(_exp_)(i => PanAzUGen(rate, numChannels, _in(i.%(_sz_in)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level)), _width(i.%(_sz_width)), _orient(i.%(_sz_orient))))
   }
}
case class PanAzUGen[R <: Rate](rate: R, numChannels: Int, in: AnyUGenIn, pos: AnyUGenIn, level: AnyUGenIn, width: AnyUGenIn, orient: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(in, pos, level, width, orient))
object DecodeB2 {
   def kr(numChannels: Int, w: AnyGE, x: AnyGE, y: AnyGE, orient: AnyGE = 0.5f) = apply[control](control, numChannels, w, x, y, orient)
   def ar(numChannels: Int, w: AnyGE, x: AnyGE, y: AnyGE, orient: AnyGE = 0.5f) = apply[audio](audio, numChannels, w, x, y, orient)
}
case class DecodeB2[R <: Rate](rate: R, numChannels: Int, w: AnyGE, x: AnyGE, y: AnyGE, orient: AnyGE) extends Expands[DecodeB2UGen[R]] {
   def expand = {
      val _w: IIdxSeq[AnyUGenIn] = w.expand
      val _x: IIdxSeq[AnyUGenIn] = x.expand
      val _y: IIdxSeq[AnyUGenIn] = y.expand
      val _orient: IIdxSeq[AnyUGenIn] = orient.expand
      val _sz_w = _w.size
      val _sz_x = _x.size
      val _sz_y = _y.size
      val _sz_orient = _orient.size
      val _exp_ = maxInt(_sz_w, _sz_x, _sz_y, _sz_orient)
      IIdxSeq.tabulate(_exp_)(i => DecodeB2UGen(rate, numChannels, _w(i.%(_sz_w)), _x(i.%(_sz_x)), _y(i.%(_sz_y)), _orient(i.%(_sz_orient))))
   }
}
case class DecodeB2UGen[R <: Rate](rate: R, numChannels: Int, w: AnyUGenIn, x: AnyUGenIn, y: AnyUGenIn, orient: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(w, x, y, orient))