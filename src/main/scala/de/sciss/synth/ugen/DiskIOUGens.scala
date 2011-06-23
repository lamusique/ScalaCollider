/*
 * DiskIOUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jun 24 00:20:25 BST 2011
 * ScalaCollider-UGen version: 0.12
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
/**
 * A UGen to stream in a signal from an audio file. Continuously plays a longer audio file
 * from disk. This requires a buffer to be preloaded with one buffer size of sound. If loop is
 * set to 1, the file will loop.
 * 
 * '''Note''': The buffer size must be a multiple of (2 * the server's block size). See
 * `Buffer#cue` for details.
 * 
 * @see [[de.sciss.synth.Buffer#cue]]
 * @see [[de.sciss.synth.ugen.VDiskIn]]
 * @see [[de.sciss.synth.ugen.PlayBuf]]
 */
object DiskIn {
   
   /**
    * @param numChannels     the amount of channels the file and the buffer will have. This is an Int and hence
    *                        must be pre-determined. Different SynthDefs must be created for different numbers of channels.
    * @param buf             the id of the buffer with the correct number of channels and frames
    * @param loop            whether the file should loop when its end is reached
    */
   def ar(numChannels: Int, buf: GE, loop: GE = 0.0f) = apply(numChannels, buf, loop)
}
/**
 * A UGen to stream in a signal from an audio file. Continuously plays a longer audio file
 * from disk. This requires a buffer to be preloaded with one buffer size of sound. If loop is
 * set to 1, the file will loop.
 * 
 * '''Note''': The buffer size must be a multiple of (2 * the server's block size). See
 * `Buffer#cue` for details.
 * 
 * @param numChannels     the amount of channels the file and the buffer will have. This is an Int and hence
 *                        must be pre-determined. Different SynthDefs must be created for different numbers of channels.
 * @param buf             the id of the buffer with the correct number of channels and frames
 * @param loop            whether the file should loop when its end is reached
 * 
 * @see [[de.sciss.synth.Buffer#cue]]
 * @see [[de.sciss.synth.ugen.VDiskIn]]
 * @see [[de.sciss.synth.ugen.PlayBuf]]
 */
final case class DiskIn(numChannels: Int, buf: GE, loop: GE) extends UGenSource.MultiOut("DiskIn", numChannels) with HasSideEffect with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, loop.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numOutputs)(audio), _args)
}
/**
 * A UGen which writes a signal to a soundfile on disk. To achieve this efficiently, a buffer is
 * needs to be provides which is used to buffer the incoming signal.
 * 
 * '''Note''': It might be that the buffer size must be a multiple of (2 * the server's block size).
 * We haven't currently verified this, but to be safe, you should make sure this property is met.
 * 
 * The signal output by the UGen represents the number of frames written.
 * 
 * @see [[de.sciss.synth.Buffer#write]]
 * @see [[de.sciss.synth.ugen.DiskIn]]
 * @see [[de.sciss.synth.ugen.RecordBuf]]
 */
object DiskOut {
   
   /**
    * @param buf             the buffer used internally by the UGen. this number of frames in the buffer must
    *                        be a power of two (this is currently not checked!). The buffer must have been initialized
    *                        with a `write` command whose `leaveOpen` argument is true. Note that the number of channels of
    *                        the buffer and of the input signal must be the same, otherwise `DiskOut` will fail silently
    *                        (and not write anything to the file).
    * @param in              the signal to be recorded
    */
   def ar(buf: GE, in: GE) = apply(buf, in)
}
/**
 * A UGen which writes a signal to a soundfile on disk. To achieve this efficiently, a buffer is
 * needs to be provides which is used to buffer the incoming signal.
 * 
 * '''Note''': It might be that the buffer size must be a multiple of (2 * the server's block size).
 * We haven't currently verified this, but to be safe, you should make sure this property is met.
 * 
 * The signal output by the UGen represents the number of frames written.
 * 
 * @param buf             the buffer used internally by the UGen. this number of frames in the buffer must
 *                        be a power of two (this is currently not checked!). The buffer must have been initialized
 *                        with a `write` command whose `leaveOpen` argument is true. Note that the number of channels of
 *                        the buffer and of the input signal must be the same, otherwise `DiskOut` will fail silently
 *                        (and not write anything to the file).
 * @param in              the signal to be recorded
 * 
 * @see [[de.sciss.synth.Buffer#write]]
 * @see [[de.sciss.synth.ugen.DiskIn]]
 * @see [[de.sciss.synth.ugen.RecordBuf]]
 */
final case class DiskOut(buf: GE, in: GE) extends UGenSource.SingleOut("DiskOut") with AudioRated with WritesBuffer {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
}
/**
 * A UGen to stream in a signal from an audio file with variable playback speed.
 * Continuously plays a longer audio file
 * from disk. This requires a buffer to be preloaded with one buffer size of sound. If loop is
 * set to 1, the file will loop.
 * 
 * '''Note''': The buffer size must be a multiple of (2 * the server's block size). See
 * `Buffer#cue` for details.
 * 
 * If the speed is too high, the UGen will not execute, posting a warning.
 * 
 * @see [[de.sciss.synth.Buffer#cue]]
 * @see [[de.sciss.synth.ugen.DiskIn]]
 * @see [[de.sciss.synth.ugen.DiskOut]]
 * @see [[de.sciss.synth.ugen.PlayBuf]]
 */
object VDiskIn {
   
   /**
    * @param numChannels     the amount of channels the file and the buffer will have. This is an Int and hence
    *                        must be pre-determined. Different SynthDefs must be created for different numbers of channels
    * @param buf             the id of the buffer with the correct number of channels and frames
    * @param speed           controls the speed of playback. Values below 4 are probably fine, but the higher the value,
    *                        the more disk activity there is, and the more likelihood there will be a problem.
    *                        The following must be true: `rate < bufFrames / (2 * blockSize)`, e.g with typical default
    *                        values, this will be `32768 / (2 * 64) = 256`.
    * @param loop            whether the file should loop when its end is reached
    * @param sendID          If a value other than zero is used, the UGen sends an OSC message with this id and the
    *                        file position each time it reloads the buffer: `OSCMessage( "/diskin", nodeID, sendID, frame )`
    */
   def ar(numChannels: Int, buf: GE, speed: GE = 1.0f, loop: GE = 0.0f, sendID: GE = 0.0f) = apply(numChannels, buf, speed, loop, sendID)
}
/**
 * A UGen to stream in a signal from an audio file with variable playback speed.
 * Continuously plays a longer audio file
 * from disk. This requires a buffer to be preloaded with one buffer size of sound. If loop is
 * set to 1, the file will loop.
 * 
 * '''Note''': The buffer size must be a multiple of (2 * the server's block size). See
 * `Buffer#cue` for details.
 * 
 * If the speed is too high, the UGen will not execute, posting a warning.
 * 
 * @param numChannels     the amount of channels the file and the buffer will have. This is an Int and hence
 *                        must be pre-determined. Different SynthDefs must be created for different numbers of channels
 * @param buf             the id of the buffer with the correct number of channels and frames
 * @param speed           controls the speed of playback. Values below 4 are probably fine, but the higher the value,
 *                        the more disk activity there is, and the more likelihood there will be a problem.
 *                        The following must be true: `rate < bufFrames / (2 * blockSize)`, e.g with typical default
 *                        values, this will be `32768 / (2 * 64) = 256`.
 * @param loop            whether the file should loop when its end is reached
 * @param sendID          If a value other than zero is used, the UGen sends an OSC message with this id and the
 *                        file position each time it reloads the buffer: `OSCMessage( "/diskin", nodeID, sendID, frame )`
 * 
 * @see [[de.sciss.synth.Buffer#cue]]
 * @see [[de.sciss.synth.ugen.DiskIn]]
 * @see [[de.sciss.synth.ugen.DiskOut]]
 * @see [[de.sciss.synth.ugen.PlayBuf]]
 */
final case class VDiskIn(numChannels: Int, buf: GE, speed: GE, loop: GE, sendID: GE) extends UGenSource.MultiOut("VDiskIn", numChannels) with HasSideEffect with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(buf.expand, speed.expand, loop.expand, sendID.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(numOutputs)(audio), _args)
}