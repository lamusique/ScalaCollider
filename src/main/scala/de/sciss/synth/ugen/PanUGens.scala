/*
 * PanUGens.scala
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
object Pan2 {
   def kr(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, pos, level)
   def ar(in: GE[audio], pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, pos, level)
}
final case class Pan2[R <: Rate](rate: R, in: AnyGE, pos: AnyGE, level: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, pos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Pan2", rate, IIdxSeq.fill(2)(rate), _args)
}
object Pan4 {
   def kr(in: AnyGE, xpos: AnyGE = 0.0f, ypos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, xpos, ypos, level)
   def ar(in: GE[audio], xpos: AnyGE = 0.0f, ypos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, xpos, ypos, level)
}
final case class Pan4[R <: Rate](rate: R, in: AnyGE, xpos: AnyGE, ypos: AnyGE, level: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, xpos.expand, ypos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Pan4", rate, IIdxSeq.fill(4)(rate), _args)
}
object LinPan2 {
   def kr(in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, pos, level)
   def ar(in: GE[audio], pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, pos, level)
}
final case class LinPan2[R <: Rate](rate: R, in: AnyGE, pos: AnyGE, level: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, pos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("LinPan2", rate, IIdxSeq.fill(2)(rate), _args)
}
object Balance2 {
   def kr(left: AnyGE, right: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, left, right, pos, level)
   def ar(left: GE[audio], right: GE[audio], pos: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, left, right, pos, level)
}
final case class Balance2[R <: Rate](rate: R, left: AnyGE, right: AnyGE, pos: AnyGE, level: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(left.expand, right.expand, pos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Balance2", rate, IIdxSeq.fill(2)(rate), _args)
}
object Rotate2 {
   def kr(x: AnyGE, y: AnyGE, pos: AnyGE = 0.0f) = apply[control](control, x, y, pos)
   def ar(x: AnyGE, y: AnyGE, pos: AnyGE = 0.0f) = apply[audio](audio, x, y, pos)
}
final case class Rotate2[R <: Rate](rate: R, x: AnyGE, y: AnyGE, pos: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(x.expand, y.expand, pos.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("Rotate2", rate, IIdxSeq.fill(2)(rate), _args)
}
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
   def kr(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, inA, inB, pan, level)
   /**
    * @param inA             The first input signal
    * @param inB             The second input signal
    * @param pan             the xfade position from `-1` (only input A audible) to
    *                        `+1` (only input B audible)
    * @param level           An overall amplitude multiplier that is applied to the output signal
    */
   def ar(inA: GE[audio], inB: GE[audio] = K2A.ar(0), pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, inA, inB, pan, level)
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
final case class XFade2[R <: Rate](rate: R, inA: AnyGE, inB: AnyGE, pan: AnyGE, level: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(inA.expand, inB.expand, pan.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("XFade2", rate, _args)
}
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
   def kr(inA: AnyGE, inB: AnyGE = 0.0f, pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, inA, inB, pan, level)
   /**
    * @param inA             The first input signal
    * @param inB             The second input signal
    * @param pan             the xfade position from `-1` (only input A audible) to
    *                        `+1` (only input B audible)
    * @param level           An overall amplitude multiplier that is applied to the output signal
    */
   def ar(inA: GE[audio], inB: GE[audio] = K2A.ar(0), pan: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, inA, inB, pan, level)
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
final case class LinXFade2[R <: Rate](rate: R, inA: AnyGE, inB: AnyGE, pan: AnyGE, level: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(inA.expand, inB.expand, pan.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinXFade2", rate, _args)
}
object PanB {
   def kr(in: AnyGE, azimuth: AnyGE = 0.0f, elevation: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, azimuth, elevation, level)
   def ar(in: GE[audio], azimuth: AnyGE = 0.0f, elevation: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, azimuth, elevation, level)
}
final case class PanB[R <: Rate](rate: R, in: AnyGE, azimuth: AnyGE, elevation: AnyGE, level: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, azimuth.expand, elevation.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("PanB", rate, IIdxSeq.fill(4)(rate), _args)
}
object PanB2 {
   def kr(in: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, in, azimuth, level)
   def ar(in: GE[audio], azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, in, azimuth, level)
}
final case class PanB2[R <: Rate](rate: R, in: AnyGE, azimuth: AnyGE, level: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, azimuth.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("PanB2", rate, IIdxSeq.fill(3)(rate), _args)
}
object BiPanB2 {
   def kr(inA: AnyGE, inB: AnyGE, azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[control](control, inA, inB, azimuth, level)
   def ar(inA: GE[audio], inB: GE[audio], azimuth: AnyGE = 0.0f, level: AnyGE = 1.0f) = apply[audio](audio, inA, inB, azimuth, level)
}
final case class BiPanB2[R <: Rate](rate: R, inA: AnyGE, inB: AnyGE, azimuth: AnyGE, level: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(inA.expand, inB.expand, azimuth.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("BiPanB2", rate, IIdxSeq.fill(3)(rate), _args)
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
   def kr(numChannels: Int, in: AnyGE, pos: AnyGE = 0.0f, level: AnyGE = 1.0f, width: AnyGE = 2.0f, orient: AnyGE = 0.0f) = apply[control](control, numChannels, in, pos, level, width, orient)
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
   def ar(numChannels: Int, in: GE[audio], pos: AnyGE = 0.0f, level: AnyGE = 1.0f, width: AnyGE = 2.0f, orient: AnyGE = 0.0f) = apply[audio](audio, numChannels, in, pos, level, width, orient)
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
final case class PanAz[R <: Rate](rate: R, numChannels: Int, in: AnyGE, pos: AnyGE, level: AnyGE, width: AnyGE, orient: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, pos.expand, level.expand, width.expand, orient.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("PanAz", rate, IIdxSeq.fill(numChannels)(rate), _args)
}
object DecodeB2 {
   def kr(numChannels: Int, w: AnyGE, x: AnyGE, y: AnyGE, orient: AnyGE = 0.5f) = apply[control](control, numChannels, w, x, y, orient)
   def ar(numChannels: Int, w: GE[audio], x: GE[audio], y: GE[audio], orient: AnyGE = 0.5f) = apply[audio](audio, numChannels, w, x, y, orient)
}
final case class DecodeB2[R <: Rate](rate: R, numChannels: Int, w: AnyGE, x: AnyGE, y: AnyGE, orient: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(w.expand, x.expand, y.expand, orient.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("DecodeB2", rate, IIdxSeq.fill(numChannels)(rate), _args)
}