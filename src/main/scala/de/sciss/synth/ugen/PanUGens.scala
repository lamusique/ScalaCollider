/*
 * PanUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 27 23:03:33 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object Pan2 {
   def kr(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, in, pos, level)
   def ar(in: GE[UGenIn], pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, in, pos, level)
}
case class Pan2(rate: Rate, in: AnyGE, pos: AnyGE, level: AnyGE) extends MultiOutUGenSource[Pan2UGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _pos: IIdxSeq[UGenIn] = pos.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_pos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => Pan2UGen(rate, _in(i.%(_sz_in)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level))))
   }
}
case class Pan2UGen(rate: Rate, in: UGenIn, pos: UGenIn, level: UGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, pos, level))
object Pan4 {
   def kr(in: AnyGE, xpos: AnyGE = 0.0f, ypos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, in, xpos, ypos, level)
   def ar(in: GE[UGenIn], xpos: AnyGE = 0.0f, ypos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, in, xpos, ypos, level)
}
case class Pan4(rate: Rate, in: AnyGE, xpos: AnyGE, ypos: AnyGE, level: AnyGE) extends MultiOutUGenSource[Pan4UGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _xpos: IIdxSeq[UGenIn] = xpos.expand
      val _ypos: IIdxSeq[UGenIn] = ypos.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_xpos = _xpos.size
      val _sz_ypos = _ypos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_xpos, _sz_ypos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => Pan4UGen(rate, _in(i.%(_sz_in)), _xpos(i.%(_sz_xpos)), _ypos(i.%(_sz_ypos)), _level(i.%(_sz_level))))
   }
}
case class Pan4UGen(rate: Rate, in: UGenIn, xpos: UGenIn, ypos: UGenIn, level: UGenIn) extends MultiOutUGen(IIdxSeq.fill(4)(rate), IIdxSeq(in, xpos, ypos, level))
object LinPan2 {
   def kr(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, in, pos, level)
   def ar(in: GE[UGenIn], pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, in, pos, level)
}
case class LinPan2(rate: Rate, in: AnyGE, pos: AnyGE, level: AnyGE) extends MultiOutUGenSource[LinPan2UGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _pos: IIdxSeq[UGenIn] = pos.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_pos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => LinPan2UGen(rate, _in(i.%(_sz_in)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level))))
   }
}
case class LinPan2UGen(rate: Rate, in: UGenIn, pos: UGenIn, level: UGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(in, pos, level))
object Balance2 {
   def kr(left: AnyGE, right: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, left, right, pos, level)
   def ar(left: GE[UGenIn], right: GE[UGenIn], pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, left, right, pos, level)
}
case class Balance2(rate: Rate, left: AnyGE, right: AnyGE, pos: AnyGE, level: AnyGE) extends MultiOutUGenSource[Balance2UGen] {
   protected def expandUGens = {
      val _left: IIdxSeq[UGenIn] = left.expand
      val _right: IIdxSeq[UGenIn] = right.expand
      val _pos: IIdxSeq[UGenIn] = pos.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_left = _left.size
      val _sz_right = _right.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_left, _sz_right, _sz_pos, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => Balance2UGen(rate, _left(i.%(_sz_left)), _right(i.%(_sz_right)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level))))
   }
}
case class Balance2UGen(rate: Rate, left: UGenIn, right: UGenIn, pos: UGenIn, level: UGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(left, right, pos, level))
object Rotate2 {
   def kr(x: AnyGE, y: AnyGE, pos: AnyGE = 0.0f) = apply(control, x, y, pos)
   def ar(x: AnyGE, y: AnyGE, pos: AnyGE = 0.0f) = apply(audio, x, y, pos)
}
case class Rotate2(rate: Rate, x: AnyGE, y: AnyGE, pos: AnyGE) extends MultiOutUGenSource[Rotate2UGen] {
   protected def expandUGens = {
      val _x: IIdxSeq[UGenIn] = x.expand
      val _y: IIdxSeq[UGenIn] = y.expand
      val _pos: IIdxSeq[UGenIn] = pos.expand
      val _sz_x = _x.size
      val _sz_y = _y.size
      val _sz_pos = _pos.size
      val _exp_ = maxInt(_sz_x, _sz_y, _sz_pos)
      IIdxSeq.tabulate(_exp_)(i => Rotate2UGen(rate, _x(i.%(_sz_x)), _y(i.%(_sz_y)), _pos(i.%(_sz_pos))))
   }
}
case class Rotate2UGen(rate: Rate, x: UGenIn, y: UGenIn, pos: UGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(rate), IIdxSeq(x, y, pos))
/**
 * An equal power two channel cross fading UGen.
 * 
 * @see [[de.sciss.synth.ugen.LinXFade2]]
 */
object XFade2 {
   
/**
 * @param inA             The first input signal
 * @param inB             The second input signal
 * @param pan             the xfade position from `-1` (only input A audible) to
 *                        `+1` (only input B audible)
 * @param level           An overall amplitude multiplier that is applied to the output signal
 */
def kr(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, inA, inB, pan, level)
/**
 * @param inA             The first input signal
 * @param inB             The second input signal
 * @param pan             the xfade position from `-1` (only input A audible) to
 *                        `+1` (only input B audible)
 * @param level           An overall amplitude multiplier that is applied to the output signal
 */
def ar(inA: GE[UGenIn], inB: GE[UGenIn] = K2A.ar(0), pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, inA, inB, pan, level)
}
/**
 * An equal power two channel cross fading UGen.
 * 
 * @param inA             The first input signal
 * @param inB             The second input signal
 * @param pan             the xfade position from `-1` (only input A audible) to
 *                        `+1` (only input B audible)
 * @param level           An overall amplitude multiplier that is applied to the output signal
 * 
 * @see [[de.sciss.synth.ugen.LinXFade2]]
 */
case class XFade2(rate: Rate, inA: AnyGE, inB: AnyGE, pan: AnyGE, level: AnyGE) extends SingleOutUGenSource[XFade2UGen] {
   protected def expandUGens = {
      val _inA: IIdxSeq[UGenIn] = inA.expand
      val _inB: IIdxSeq[UGenIn] = inB.expand
      val _pan: IIdxSeq[UGenIn] = pan.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_inA = _inA.size
      val _sz_inB = _inB.size
      val _sz_pan = _pan.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_inA, _sz_inB, _sz_pan, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => XFade2UGen(rate, _inA(i.%(_sz_inA)), _inB(i.%(_sz_inB)), _pan(i.%(_sz_pan)), _level(i.%(_sz_level))))
   }
}
case class XFade2UGen(rate: Rate, inA: UGenIn, inB: UGenIn, pan: UGenIn, level: UGenIn) extends SingleOutUGen(IIdxSeq(inA, inB, pan, level))
/**
 * An linear two channel cross fading UGen.
 * 
 * @see [[de.sciss.synth.ugen.LinXFade2]]
 */
object LinXFade2 {
   
/**
 * @param inA             The first input signal
 * @param inB             The second input signal
 * @param pan             the xfade position from `-1` (only input A audible) to
 *                        `+1` (only input B audible)
 * @param level           An overall amplitude multiplier that is applied to the output signal
 */
def kr(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, inA, inB, pan, level)
/**
 * @param inA             The first input signal
 * @param inB             The second input signal
 * @param pan             the xfade position from `-1` (only input A audible) to
 *                        `+1` (only input B audible)
 * @param level           An overall amplitude multiplier that is applied to the output signal
 */
def ar(inA: GE[UGenIn], inB: GE[UGenIn] = K2A.ar(0), pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, inA, inB, pan, level)
}
/**
 * An linear two channel cross fading UGen.
 * 
 * @param inA             The first input signal
 * @param inB             The second input signal
 * @param pan             the xfade position from `-1` (only input A audible) to
 *                        `+1` (only input B audible)
 * @param level           An overall amplitude multiplier that is applied to the output signal
 * 
 * @see [[de.sciss.synth.ugen.LinXFade2]]
 */
case class LinXFade2(rate: Rate, inA: AnyGE, inB: AnyGE, pan: AnyGE, level: AnyGE) extends SingleOutUGenSource[LinXFade2UGen] {
   protected def expandUGens = {
      val _inA: IIdxSeq[UGenIn] = inA.expand
      val _inB: IIdxSeq[UGenIn] = inB.expand
      val _pan: IIdxSeq[UGenIn] = pan.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_inA = _inA.size
      val _sz_inB = _inB.size
      val _sz_pan = _pan.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_inA, _sz_inB, _sz_pan, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => LinXFade2UGen(rate, _inA(i.%(_sz_inA)), _inB(i.%(_sz_inB)), _pan(i.%(_sz_pan)), _level(i.%(_sz_level))))
   }
}
case class LinXFade2UGen(rate: Rate, inA: UGenIn, inB: UGenIn, pan: UGenIn, level: UGenIn) extends SingleOutUGen(IIdxSeq(inA, inB, pan, level))
object PanB {
   def kr(in: AnyGE, azimuth: AnyGE = 0.0f, elevation: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, in, azimuth, elevation, level)
   def ar(in: GE[UGenIn], azimuth: AnyGE = 0.0f, elevation: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, in, azimuth, elevation, level)
}
case class PanB(rate: Rate, in: AnyGE, azimuth: AnyGE, elevation: AnyGE, level: AnyGE) extends MultiOutUGenSource[PanBUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _azimuth: IIdxSeq[UGenIn] = azimuth.expand
      val _elevation: IIdxSeq[UGenIn] = elevation.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_azimuth = _azimuth.size
      val _sz_elevation = _elevation.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_azimuth, _sz_elevation, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => PanBUGen(rate, _in(i.%(_sz_in)), _azimuth(i.%(_sz_azimuth)), _elevation(i.%(_sz_elevation)), _level(i.%(_sz_level))))
   }
}
case class PanBUGen(rate: Rate, in: UGenIn, azimuth: UGenIn, elevation: UGenIn, level: UGenIn) extends MultiOutUGen(IIdxSeq.fill(4)(rate), IIdxSeq(in, azimuth, elevation, level))
object PanB2 {
   def kr(in: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, in, azimuth, level)
   def ar(in: GE[UGenIn], azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, in, azimuth, level)
}
case class PanB2(rate: Rate, in: AnyGE, azimuth: AnyGE, level: AnyGE) extends MultiOutUGenSource[PanB2UGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _azimuth: IIdxSeq[UGenIn] = azimuth.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_in = _in.size
      val _sz_azimuth = _azimuth.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_in, _sz_azimuth, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => PanB2UGen(rate, _in(i.%(_sz_in)), _azimuth(i.%(_sz_azimuth)), _level(i.%(_sz_level))))
   }
}
case class PanB2UGen(rate: Rate, in: UGenIn, azimuth: UGenIn, level: UGenIn) extends MultiOutUGen(IIdxSeq.fill(3)(rate), IIdxSeq(in, azimuth, level))
object BiPanB2 {
   def kr(inA: AnyGE, inB: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(control, inA, inB, azimuth, level)
   def ar(inA: GE[UGenIn], inB: GE[UGenIn], azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply(audio, inA, inB, azimuth, level)
}
case class BiPanB2(rate: Rate, inA: AnyGE, inB: AnyGE, azimuth: AnyGE, level: AnyGE) extends MultiOutUGenSource[BiPanB2UGen] {
   protected def expandUGens = {
      val _inA: IIdxSeq[UGenIn] = inA.expand
      val _inB: IIdxSeq[UGenIn] = inB.expand
      val _azimuth: IIdxSeq[UGenIn] = azimuth.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _sz_inA = _inA.size
      val _sz_inB = _inB.size
      val _sz_azimuth = _azimuth.size
      val _sz_level = _level.size
      val _exp_ = maxInt(_sz_inA, _sz_inB, _sz_azimuth, _sz_level)
      IIdxSeq.tabulate(_exp_)(i => BiPanB2UGen(rate, _inA(i.%(_sz_inA)), _inB(i.%(_sz_inB)), _azimuth(i.%(_sz_azimuth)), _level(i.%(_sz_level))))
   }
}
case class BiPanB2UGen(rate: Rate, inA: UGenIn, inB: UGenIn, azimuth: UGenIn, level: UGenIn) extends MultiOutUGen(IIdxSeq.fill(3)(rate), IIdxSeq(inA, inB, azimuth, level))
/**
 * An azimuth-based panorama UGen. It uses vector-based-amplitude panning where
 * the arbitrary number of speakers is supposed to be distributed in a circle
 * with even spacing between them. It uses an equal-power-curve to transition
 * between adjectant speakers. '''Note''' the different default value for
 * the `orient` argument!
 * 
 * Use case: To spread an multi-channel input signal across an output bus
 * with a different number of channels, such that the first input channel is played on the
 * first output channel (no spread to adjectant channels) and the last input channel is played
 * to the last output channel (no spread to adjectant channels), you would create a dedicated `PanAz` per
 * input channel where the pan position
 * is `inChanIdx * 2f / (inChannels - 1) * (outChannels - 1) / outChannels`.
 */
object PanAz {
   
/**
 * @param numChannels     the number of output channels
 * @param in              the input signal
 * @param pos             the pan position. Channels are evenly spaced over a cyclic period of 2.0.
 *                        the output channel position is `pos / 2 * numChannels + orient`. Thus, assuming an `orient`
 *                        of `0.0`, and `numChannels` being for example `3`, a `pos` of `0*2.0/3 == 0.0` corresponds to the first
 *                        output channel, a `pos` of `1*2.0/3` corresponds to the second output channel,
 *                        a `pos` of `2*2.0/3=4.0/3` corresponds to the third and last output channel, and
 *                        a `pos` of `3*2.0/3=2.0` completes the circle and wraps again to the first channel.
 *                        Using a bipolar pan position, such as a sawtooth that ranges from -1 to +1, all channels will be
 *                        cyclically panned through.
 * @param level           a control rate level input (linear multiplier).
 * @param width           the width of the panning envelope. The default of 2.0 pans between pairs
 *                        of adjacent speakers. Width values greater than two will spread the pan over greater numbers
 *                        of speakers. Width values less than one will leave silent gaps between speakers.
 * @param orient          the offset in the output channels regarding a pan position of zero.
 *                        Note that ScalaCollider uses a default of zero which means that a pan pos of zero outputs
 *                        the signal exactly on the first output channel. This is different in sclang where the default is
 *                        0.5 which means that a pan position of zero will output the signal inbetween the first and second
 *                        speaker. Accordingly, an `orient` of `1.0` would result in a channel offset of one, where a
 *                        pan position of zero would output the signal exactly on the second output channel, and so forth.
 */
def kr(numChannels: Int, in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f, width: AnyGE = 2.0f, orient: AnyGE = 0.0f) = apply(control, numChannels, in, pos, level, width, orient)
/**
 * @param numChannels     the number of output channels
 * @param in              the input signal
 * @param pos             the pan position. Channels are evenly spaced over a cyclic period of 2.0.
 *                        the output channel position is `pos / 2 * numChannels + orient`. Thus, assuming an `orient`
 *                        of `0.0`, and `numChannels` being for example `3`, a `pos` of `0*2.0/3 == 0.0` corresponds to the first
 *                        output channel, a `pos` of `1*2.0/3` corresponds to the second output channel,
 *                        a `pos` of `2*2.0/3=4.0/3` corresponds to the third and last output channel, and
 *                        a `pos` of `3*2.0/3=2.0` completes the circle and wraps again to the first channel.
 *                        Using a bipolar pan position, such as a sawtooth that ranges from -1 to +1, all channels will be
 *                        cyclically panned through.
 * @param level           a control rate level input (linear multiplier).
 * @param width           the width of the panning envelope. The default of 2.0 pans between pairs
 *                        of adjacent speakers. Width values greater than two will spread the pan over greater numbers
 *                        of speakers. Width values less than one will leave silent gaps between speakers.
 * @param orient          the offset in the output channels regarding a pan position of zero.
 *                        Note that ScalaCollider uses a default of zero which means that a pan pos of zero outputs
 *                        the signal exactly on the first output channel. This is different in sclang where the default is
 *                        0.5 which means that a pan position of zero will output the signal inbetween the first and second
 *                        speaker. Accordingly, an `orient` of `1.0` would result in a channel offset of one, where a
 *                        pan position of zero would output the signal exactly on the second output channel, and so forth.
 */
def ar(numChannels: Int, in: GE[UGenIn], pos: AnyGE = 0.0f, level: AnyGE = 1.0f, width: AnyGE = 2.0f, orient: AnyGE = 0.0f) = apply(audio, numChannels, in, pos, level, width, orient)
}
/**
 * An azimuth-based panorama UGen. It uses vector-based-amplitude panning where
 * the arbitrary number of speakers is supposed to be distributed in a circle
 * with even spacing between them. It uses an equal-power-curve to transition
 * between adjectant speakers. '''Note''' the different default value for
 * the `orient` argument!
 * 
 * Use case: To spread an multi-channel input signal across an output bus
 * with a different number of channels, such that the first input channel is played on the
 * first output channel (no spread to adjectant channels) and the last input channel is played
 * to the last output channel (no spread to adjectant channels), you would create a dedicated `PanAz` per
 * input channel where the pan position
 * is `inChanIdx * 2f / (inChannels - 1) * (outChannels - 1) / outChannels`.
 * 
 * @param numChannels     the number of output channels
 * @param in              the input signal
 * @param pos             the pan position. Channels are evenly spaced over a cyclic period of 2.0.
 *                        the output channel position is `pos / 2 * numChannels + orient`. Thus, assuming an `orient`
 *                        of `0.0`, and `numChannels` being for example `3`, a `pos` of `0*2.0/3 == 0.0` corresponds to the first
 *                        output channel, a `pos` of `1*2.0/3` corresponds to the second output channel,
 *                        a `pos` of `2*2.0/3=4.0/3` corresponds to the third and last output channel, and
 *                        a `pos` of `3*2.0/3=2.0` completes the circle and wraps again to the first channel.
 *                        Using a bipolar pan position, such as a sawtooth that ranges from -1 to +1, all channels will be
 *                        cyclically panned through.
 * @param level           a control rate level input (linear multiplier).
 * @param width           the width of the panning envelope. The default of 2.0 pans between pairs
 *                        of adjacent speakers. Width values greater than two will spread the pan over greater numbers
 *                        of speakers. Width values less than one will leave silent gaps between speakers.
 * @param orient          the offset in the output channels regarding a pan position of zero.
 *                        Note that ScalaCollider uses a default of zero which means that a pan pos of zero outputs
 *                        the signal exactly on the first output channel. This is different in sclang where the default is
 *                        0.5 which means that a pan position of zero will output the signal inbetween the first and second
 *                        speaker. Accordingly, an `orient` of `1.0` would result in a channel offset of one, where a
 *                        pan position of zero would output the signal exactly on the second output channel, and so forth.
 */
case class PanAz(rate: Rate, numChannels: Int, in: AnyGE, pos: AnyGE, level: AnyGE, width: AnyGE, orient: AnyGE) extends MultiOutUGenSource[PanAzUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _pos: IIdxSeq[UGenIn] = pos.expand
      val _level: IIdxSeq[UGenIn] = level.expand
      val _width: IIdxSeq[UGenIn] = width.expand
      val _orient: IIdxSeq[UGenIn] = orient.expand
      val _sz_in = _in.size
      val _sz_pos = _pos.size
      val _sz_level = _level.size
      val _sz_width = _width.size
      val _sz_orient = _orient.size
      val _exp_ = maxInt(_sz_in, _sz_pos, _sz_level, _sz_width, _sz_orient)
      IIdxSeq.tabulate(_exp_)(i => PanAzUGen(rate, numChannels, _in(i.%(_sz_in)), _pos(i.%(_sz_pos)), _level(i.%(_sz_level)), _width(i.%(_sz_width)), _orient(i.%(_sz_orient))))
   }
}
case class PanAzUGen(rate: Rate, numChannels: Int, in: UGenIn, pos: UGenIn, level: UGenIn, width: UGenIn, orient: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(in, pos, level, width, orient))
object DecodeB2 {
   def kr(numChannels: Int, w: AnyGE, x: AnyGE, y: AnyGE, orient: AnyGE = 0.5f) = apply(control, numChannels, w, x, y, orient)
   def ar(numChannels: Int, w: GE[UGenIn], x: GE[UGenIn], y: GE[UGenIn], orient: AnyGE = 0.5f) = apply(audio, numChannels, w, x, y, orient)
}
case class DecodeB2(rate: Rate, numChannels: Int, w: AnyGE, x: AnyGE, y: AnyGE, orient: AnyGE) extends MultiOutUGenSource[DecodeB2UGen] {
   protected def expandUGens = {
      val _w: IIdxSeq[UGenIn] = w.expand
      val _x: IIdxSeq[UGenIn] = x.expand
      val _y: IIdxSeq[UGenIn] = y.expand
      val _orient: IIdxSeq[UGenIn] = orient.expand
      val _sz_w = _w.size
      val _sz_x = _x.size
      val _sz_y = _y.size
      val _sz_orient = _orient.size
      val _exp_ = maxInt(_sz_w, _sz_x, _sz_y, _sz_orient)
      IIdxSeq.tabulate(_exp_)(i => DecodeB2UGen(rate, numChannels, _w(i.%(_sz_w)), _x(i.%(_sz_x)), _y(i.%(_sz_y)), _orient(i.%(_sz_orient))))
   }
}
case class DecodeB2UGen(rate: Rate, numChannels: Int, w: UGenIn, x: UGenIn, y: UGenIn, orient: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(w, x, y, orient))