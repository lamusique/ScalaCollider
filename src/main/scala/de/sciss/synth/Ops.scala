/*
 *  Ops.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

import de.sciss.osc.Packet
import collection.immutable.{IndexedSeq => IIdxSeq}
import language.implicitConversions

object Ops {
//   implicit def nodeOps( n: Node ) : NodeOps = new NodeOps( n )

   // this allows conversions to Group so that something like Server.default.freeAll becomes possible
   implicit def groupOps[ G <% Group ]( g: G ) : GroupOps = new GroupOps( g )
//   implicit def bufferOps( b: Buffer ) : BufferOps = new BufferOps( b )
//   implicit def controlBusOps( b: ControlBus ) : ControlBusOps = new ControlBusOps( b )

  final implicit class NodeOps(val n: Node) extends AnyVal {
    import n._

    def free() {
      server ! freeMsg
    }

    /**
     * Pauses or resumes the node.
     *
     * @param flag if `true` the node is resumed, if `false` it is paused.
     */
    def run(flag: Boolean = true) {
      server ! runMsg(flag)
    }

    def set(pairs: ControlSetMap*) {
      server ! setMsg(pairs: _*)
    }

    def setn(pairs: ControlSetMap*) {
      server ! setnMsg(pairs: _*)
    }

    def trace() {
      server ! traceMsg
    }

    def release(releaseTime: Optional[Double] = None) {
      server ! releaseMsg(releaseTime)
    }

    def map(pairs: ControlKBusMap.Single*) {
      server ! mapMsg(pairs: _*)
    }

    def mapn(mappings: ControlKBusMap*) {
      server ! mapnMsg(mappings: _*)
    }

    /**
     * Creates a mapping from a mono-channel audio bus to one of the node's controls.
     *
     * Note that a mapped control acts similar to an `InFeedback` UGen in that it does not matter
     * whether the audio bus was written before the execution of the synth whose control is mapped or not.
     * If it was written before, no delay is introduced, otherwise a delay of one control block is introduced.
     *
     * @see  [[de.sciss.synth.ugen.InFeedback]]
     */
    def mapa(pairs: ControlABusMap.Single*) {
      server ! mapaMsg(pairs: _*)
    }

    /**
     * Creates a mapping from a mono- or multi-channel audio bus to one of the node's controls.
     *
     * Note that a mapped control acts similar to an `InFeedback` UGen in that it does not matter
     * whether the audio bus was written before the execution of the synth whose control is mapped or not.
     * If it was written before, no delay is introduced, otherwise a delay of one control block is introduced.
     *
     * @see  [[de.sciss.synth.ugen.InFeedback]]
     */
    def mapan(mappings: ControlABusMap*) {
      server ! mapanMsg(mappings: _*)
    }

    def fill(control: Any, numChannels: Int, value: Float) {
      server ! fillMsg(control, numChannels, value)
    }

    def fill(fillings: message.NodeFill.Info*) {
      server ! fillMsg(fillings: _*)
    }

    /**
     * Moves this node before another node
     *
     * @param   node  the node before which to move this node
     *
     * @see  [[de.sciss.synth.message.NodeBefore]]
     */
    def moveBefore(node: Node) {
      server ! moveBeforeMsg(node)
    }

    /**
     * Moves this node after another node
     *
     * @param   node  the node after which to move this node
     *
     * @see  [[de.sciss.synth.message.NodeAfter]]
     */
    def moveAfter(node: Node) {
      server ! moveAfterMsg(node)
    }

    def moveToHead(group: Group) {
      server ! moveToHeadMsg(group)
    }

    def moveToTail(group: Group) {
      server ! moveToTailMsg(group)
    }
  }

  implicit final class GroupConstructors(val g: Group.type) extends AnyVal {
    import Group._

    def play(): Group = {
      head(Server.default.defaultGroup)
    }

    def play(target: Node = Server.default.defaultGroup, addAction: AddAction = addToHead) = {
      val group = apply(target.server)
      group.server ! group.newMsg(target, addAction)
      group
    }

    def after  (target: Node):  Group = play(target, addAfter)
    def before (target: Node):  Group = play(target, addBefore)
    def head   (target: Group): Group = play(target, addToHead)
    def tail   (target: Group): Group = play(target, addToTail)
    def replace(target: Node):  Group = play(target, addReplace)
  }

  final class GroupOps(val g: Group) extends AnyVal {

    import g._

    def freeAll() {
      server ! freeAllMsg
    }

    def deepFree() {
      server ! deepFreeMsg
    }

    def dumpTree(postControls: Boolean = false) {
      server ! dumpTreeMsg(postControls)
    }
  }

  implicit final class SynthConstructors(val s: Synth.type) extends AnyVal {
    import Synth._
    def play(defName: String, args: Seq[ControlSetMap] = Nil, target: Node = Server.default.defaultGroup,
             addAction: AddAction = addToHead) = {
      val synth = apply(target.server)
      synth.server ! synth.newMsg(defName, target, args, addAction)
      synth
    }

    def after(target: Node, defName: String, args: Seq[ControlSetMap] = Nil): Synth =
      play(defName, args, target, addAfter)

    def before(target: Node, defName: String, args: Seq[ControlSetMap] = Nil): Synth =
      play(defName, args, target, addBefore)

    def head(target: Group, defName: String, args: Seq[ControlSetMap] = Nil): Synth =
      play(defName, args, target, addToHead)

    def tail(target: Group, defName: String, args: Seq[ControlSetMap] = Nil): Synth =
      play(defName, args, target, addToTail)

    def replace(target: Node, defName: String, args: Seq[ControlSetMap] = Nil): Synth =
      play(defName, args, target, addReplace)

  }

  implicit final class BufferConstructors(val b: Buffer.type) extends AnyVal {
    import Buffer._

    def alloc(server: Server = Server.default, numFrames: Int, numChannels: Int = 1,
              completion: Completion = NoCompletion): Buffer = {
      val b = apply(server)
      b.alloc(numFrames, numChannels, completion)
      b
    }

    def read(server: Server = Server.default, path: String, startFrame: Int = 0, numFrames: Int = -1,
             completion: Completion = NoCompletion): Buffer = {
      val b = apply(server)
      b.allocRead(path, startFrame, numFrames, completion)
      b
    }

    def cue(server: Server = Server.default, path: String, startFrame: Int = 0, numChannels: Int = 1,
            bufFrames: Int = 32768, completion: Completion = NoCompletion): Buffer = {
      val b = apply(server)
      b.alloc(bufFrames, numChannels, b.cueMsg(path, startFrame, completion))
      b
    }

    def readChannel(server: Server = Server.default, path: String, startFrame: Int = 0, numFrames: Int = -1,
                    channels: Seq[Int], completion: Completion = NoCompletion): Buffer = {
      val b = apply(server)
      b.allocReadChannel(path, startFrame, numFrames, channels, completion)
      b
    }
  }

  implicit final class BufferOps(val b: Buffer) extends AnyVal {

    import b._

    def alloc(numFrames: Int, numChannels: Int = 1, completion: Buffer.Completion = Buffer.NoCompletion) {
      server ! allocMsg(numFrames, numChannels, makePacket(completion))
    }

    def free(completion: Optional[Packet] = None) {
      server ! freeMsg(completion, release = true)
    }

    def close(completion: Option[Packet] = None) {
      server ! closeMsg(completion)
    }

    def allocRead(path: String, startFrame: Int = 0, numFrames: Int = -1,
                  completion: Buffer.Completion = Buffer.NoCompletion) {
      //      path = argpath;
      server ! allocReadMsg(path, startFrame, numFrames, makePacket(completion, forceQuery = true))
    }

    def allocReadChannel(path: String, startFrame: Int = 0, numFrames: Int = -1, channels: Seq[Int],
                         completion: Buffer.Completion = Buffer.NoCompletion) {
      //      path = argpath;
      server ! allocReadChannelMsg(path, startFrame, numFrames, channels, makePacket(completion, forceQuery = true))
    }

    def read(path: String, fileStartFrame: Int = 0, numFrames: Int = -1, bufStartFrame: Int = 0,
             leaveOpen: Boolean = false, completion: Buffer.Completion = Buffer.NoCompletion) {
      server ! readMsg(path, fileStartFrame, numFrames, bufStartFrame, leaveOpen, makePacket(completion))
    }

    def readChannel(path: String, fileStartFrame: Int = 0, numFrames: Int = -1, bufStartFrame: Int = 0,
                    leaveOpen: Boolean = false, channels: Seq[Int],
                    completion: Buffer.Completion = Buffer.NoCompletion) {
      server ! readChannelMsg(path, fileStartFrame, numFrames, bufStartFrame, leaveOpen,
        channels, makePacket(completion))
    }

    /**
     * Sets the contents of the buffer by replacing
     * individual sample values. An error is thrown if any of the given
     * offsets is out of range.
     *
     * @param   pairs a list of modifications to the buffer contents, each element
     *                being a sample offset and the sample value. The sample offset ranges
     *                from zero to the number of samples in the buffer (exclusive), i.e.
     *                `numChannels * numFrames`. For instance, in a stereo-buffer, the offset
     *                for the right channel's fifth frame is `(5-1) * 2 + 1 = 9`.
     */
    def set(pairs: (Int, Float)*) {
      server ! setMsg(pairs: _*)
    }

    /**
     * Sets the entire contents of the buffer.
     * An error is thrown if the number of given values does not match the number
     * of samples in the buffer.
     *
     * @param   v  the new content of the buffer. the size of the sequence must be
     *             exactly the number of samples in the buffer, i.e.
     *             `numChannels * numFrames`. Values are channel-interleaved, that is
     *             for a stereo-buffer the first element specifies the value of the
     *             first frame of the left channel, the second element specifies the value
     *             of the first frame of the right channel, followed by the second frame
     *             of the left channel, etc.
     */
    def setn(v: IIdxSeq[Float]) {
      server ! setnMsg(v)
    }

    /**
     * Sets the contents of the buffer by replacing
     * individual contiguous chunks of data. An error is thrown if any of the given
     * ranges lies outside the valid range of the entire buffer.
     *
     * @param   pairs a list of modifications to the buffer contents, each element
     *                being a sample offset and a chunk of values. The data is channel-interleaved,
     *                e.g. for a stereo-buffer, the offset for the right channel's fifth frame
     *                is `(5-1) * 2 + 1 = 9`. Accordingly, values in the float-sequences are
     *                considered channel-interleaved, i.e. for a stereo buffer and an even offset,
     *                the first element of the sequence refers to frame `offset / 2` of the
     *                left channel, the second element to frame `offset / 2` of the right channel,
     *                followed by frame `offset / 2 + 1` of the left channel, and so on.
     */
    def setn(pairs: (Int, IIdxSeq[Float])*) {
      server ! setnMsg(pairs: _*)
    }

    def zero(completion: Option[Packet] = None) {
      server ! zeroMsg(completion)
    }

    def write(path: String, fileType: io.AudioFileType = io.AudioFileType.AIFF,
              sampleFormat: io.SampleFormat = io.SampleFormat.Float, numFrames: Int = -1, startFrame: Int = 0,
              leaveOpen: Boolean = false, completion: Buffer.Completion = Buffer.NoCompletion) {
      //         path = path ?? { thisProcess.platform.recordingsDir +/+ "SC_" ++ Date.localtime.stamp ++ "." ++ headerFormat };
      server ! writeMsg(path, fileType, sampleFormat, numFrames, startFrame, leaveOpen, makePacket(completion))
    }

    // ---- utility methods ----
//    def play: Synth = play()

    def play(loop: Boolean = false, amp: Float = 1f, out: Int = 0): Synth = {
      import de.sciss.synth
      import ugen._
      synth.play(server, out) {
        // working around nasty compiler bug
        val ply = PlayBuf.ar(numChannels, id, BufRateScale.kr(id), loop = if (loop) 1 else 0)
        if (!loop) FreeSelfWhenDone.kr(ply)
        ply * "amp".kr(amp)
      }
    }
  }

  implicit final class ControlBusOps(val b: ControlBus) extends AnyVal {
    import b._

    def set(v: Float) {
      server ! setMsg(v)
    }

    def set(pairs: (Int, Float)*) {
      server ! setMsg(pairs: _*)
    }

    def setn(v: IIdxSeq[Float]) {
      server ! setnMsg(v)
    }

    def setn(pairs: (Int, IIdxSeq[Float])*) {
      server ! setnMsg(pairs: _*)
    }
  }
}