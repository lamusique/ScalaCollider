/*
 * PanUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Tue Oct 25 17:24:22 BST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object Pan2 {
   def kr(in: GE, pos: GE = 0.0f, level: GE = 1.0f) = apply(control, in, pos, level)
   def ar(in: GE, pos: GE = 0.0f, level: GE = 1.0f) = apply(audio, in, pos, level)
}
final case class Pan2(rate: Rate, in: GE, pos: GE, level: GE) extends UGenSource.MultiOut("Pan2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, pos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(2)(rate), _args)
}
object Pan4 {
   def kr(in: GE, xpos: GE = 0.0f, ypos: GE = 0.0f, level: GE = 1.0f) = apply(control, in, xpos, ypos, level)
   def ar(in: GE, xpos: GE = 0.0f, ypos: GE = 0.0f, level: GE = 1.0f) = apply(audio, in, xpos, ypos, level)
}
final case class Pan4(rate: Rate, in: GE, xpos: GE, ypos: GE, level: GE) extends UGenSource.MultiOut("Pan4") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, xpos.expand, ypos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(4)(rate), _args)
}
object LinPan2 {
   def kr(in: GE, pos: GE = 0.0f, level: GE = 1.0f) = apply(control, in, pos, level)
   def ar(in: GE, pos: GE = 0.0f, level: GE = 1.0f) = apply(audio, in, pos, level)
}
final case class LinPan2(rate: Rate, in: GE, pos: GE, level: GE) extends UGenSource.MultiOut("LinPan2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, pos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(2)(rate), _args)
}
object Balance2 {
   def kr(left: GE, right: GE, pos: GE = 0.0f, level: GE = 1.0f) = apply(control, left, right, pos, level)
   def ar(left: GE, right: GE, pos: GE = 0.0f, level: GE = 1.0f) = apply(audio, left, right, pos, level)
}
final case class Balance2(rate: Rate, left: GE, right: GE, pos: GE, level: GE) extends UGenSource.MultiOut("Balance2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(left.expand, right.expand, pos.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(2)(rate), _args)
}
object Rotate2 {
   def kr(x: GE, y: GE, pos: GE = 0.0f) = apply(control, x, y, pos)
   def ar(x: GE, y: GE, pos: GE = 0.0f) = apply(audio, x, y, pos)
}
final case class Rotate2(rate: Rate, x: GE, y: GE, pos: GE) extends UGenSource.MultiOut("Rotate2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(x.expand, y.expand, pos.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(2)(rate), _args)
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
   def kr(inA: GE, inB: GE = 0.0f, pan: GE = 0.0f, level: GE = 1.0f) = apply(control, inA, inB, pan, level)
   /**
    * @param inA             The first input signal
    * @param inB             The second input signal
    * @param pan             the xfade position from `-1` (only input A audible) to
    *                        `+1` (only input B audible)
    * @param level           An overall amplitude multiplier that is applied to the output signal
    */
   def ar(inA: GE, inB: GE = K2A.ar(0), pan: GE = 0.0f, level: GE = 1.0f) = apply(audio, inA, inB, pan, level)
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
final case class XFade2(rate: Rate, inA: GE, inB: GE, pan: GE, level: GE) extends UGenSource.SingleOut("XFade2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(inA.expand, inB.expand, pan.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
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
   def kr(inA: GE, inB: GE = 0.0f, pan: GE = 0.0f, level: GE = 1.0f) = apply(control, inA, inB, pan, level)
   /**
    * @param inA             The first input signal
    * @param inB             The second input signal
    * @param pan             the xfade position from `-1` (only input A audible) to
    *                        `+1` (only input B audible)
    * @param level           An overall amplitude multiplier that is applied to the output signal
    */
   def ar(inA: GE, inB: GE = K2A.ar(0), pan: GE = 0.0f, level: GE = 1.0f) = apply(audio, inA, inB, pan, level)
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
final case class LinXFade2(rate: Rate, inA: GE, inB: GE, pan: GE, level: GE) extends UGenSource.SingleOut("LinXFade2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(inA.expand, inB.expand, pan.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object PanB {
   def kr(in: GE, azimuth: GE = 0.0f, elevation: GE = 0.0f, level: GE = 1.0f) = apply(control, in, azimuth, elevation, level)
   def ar(in: GE, azimuth: GE = 0.0f, elevation: GE = 0.0f, level: GE = 1.0f) = apply(audio, in, azimuth, elevation, level)
}
final case class PanB(rate: Rate, in: GE, azimuth: GE, elevation: GE, level: GE) extends UGenSource.MultiOut("PanB") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, azimuth.expand, elevation.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(4)(rate), _args)
}
object PanB2 {
   def kr(in: GE, azimuth: GE = 0.0f, level: GE = 1.0f) = apply(control, in, azimuth, level)
   def ar(in: GE, azimuth: GE = 0.0f, level: GE = 1.0f) = apply(audio, in, azimuth, level)
}
final case class PanB2(rate: Rate, in: GE, azimuth: GE, level: GE) extends UGenSource.MultiOut("PanB2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, azimuth.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(3)(rate), _args)
}
object BiPanB2 {
   def kr(inA: GE, inB: GE, azimuth: GE = 0.0f, level: GE = 1.0f) = apply(control, inA, inB, azimuth, level)
   def ar(inA: GE, inB: GE, azimuth: GE = 0.0f, level: GE = 1.0f) = apply(audio, inA, inB, azimuth, level)
}
final case class BiPanB2(rate: Rate, inA: GE, inB: GE, azimuth: GE, level: GE) extends UGenSource.MultiOut("BiPanB2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(inA.expand, inB.expand, azimuth.expand, level.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(3)(rate), _args)
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
   def kr(numChannels: Int, in: GE, pos: GE = 0.0f, level: GE = 1.0f, width: GE = 2.0f, orient: GE = 0.0f) = apply(control, numChannels, in, pos, level, width, orient)
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
   def ar(numChannels: Int, in: GE, pos: GE = 0.0f, level: GE = 1.0f, width: GE = 2.0f, orient: GE = 0.0f) = apply(audio, numChannels, in, pos, level, width, orient)
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
final case class PanAz(rate: Rate, numChannels: Int, in: GE, pos: GE, level: GE, width: GE, orient: GE) extends UGenSource.MultiOut("PanAz") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, pos.expand, level.expand, width.expand, orient.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(numChannels)(rate), _args)
}
object DecodeB2 {
   def kr(numChannels: Int, w: GE, x: GE, y: GE, orient: GE = 0.5f) = apply(control, numChannels, w, x, y, orient)
   def ar(numChannels: Int, w: GE, x: GE, y: GE, orient: GE = 0.5f) = apply(audio, numChannels, w, x, y, orient)
}
final case class DecodeB2(rate: Rate, numChannels: Int, w: GE, x: GE, y: GE, orient: GE) extends UGenSource.MultiOut("DecodeB2") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(w.expand, x.expand, y.expand, orient.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, rate, IIdxSeq.fill(numChannels)(rate), _args)
}