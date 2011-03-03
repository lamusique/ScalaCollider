/*
 * DiskIOUGens.scala
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
   def ar(numChannels: Int, buf: AnyGE, loop: AnyGE = 0.0f) = apply(numChannels, buf, loop)
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
final case class DiskIn(numChannels: Int, buf: AnyGE, loop: AnyGE) extends MultiOutUGenSource[audio] with HasSideEffect {
   protected def expandUGens = {
      val _buf = buf.expand
      val _loop = loop.expand
      val _sz_buf = _buf.size
      val _sz_loop = _loop.size
      val _exp_ = maxInt(_sz_buf, _sz_loop)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("DiskIn", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_buf(i.%(_sz_buf)), _loop(i.%(_sz_loop)))))
   }
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
   def ar(buf: AnyGE, in: Multi[GE[audio]]) = apply(buf, in)
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
final case class DiskOut(buf: AnyGE, in: Multi[AnyGE]) extends SingleOutUGenSource[audio] with WritesBuffer {
   protected def expandUGens = {
      val _buf = buf.expand
      val _in = in.mexpand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_buf, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("DiskOut", audio, IIdxSeq(_buf(i.%(_sz_buf))).++(_in(i.%(_sz_in)).expand)))
   }
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
   def ar(numChannels: Int, buf: AnyGE, speed: AnyGE = 1.0f, loop: AnyGE = 0.0f, sendID: AnyGE = 0.0f) = apply(numChannels, buf, speed, loop, sendID)
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
final case class VDiskIn(numChannels: Int, buf: AnyGE, speed: AnyGE, loop: AnyGE, sendID: AnyGE) extends MultiOutUGenSource[audio] with HasSideEffect {
   protected def expandUGens = {
      val _buf = buf.expand
      val _speed = speed.expand
      val _loop = loop.expand
      val _sendID = sendID.expand
      val _sz_buf = _buf.size
      val _sz_speed = _speed.size
      val _sz_loop = _loop.size
      val _sz_sendID = _sendID.size
      val _exp_ = maxInt(_sz_buf, _sz_speed, _sz_loop, _sz_sendID)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("VDiskIn", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_buf(i.%(_sz_buf)), _speed(i.%(_sz_speed)), _loop(i.%(_sz_loop)), _sendID(i.%(_sz_sendID)))))
   }
}