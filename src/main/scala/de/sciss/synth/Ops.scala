/*
 *  Ops.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2014 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v2+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

import collection.immutable.{IndexedSeq => Vec}
import language.implicitConversions
import de.sciss.osc

/** Importing the contents of this object adds imperative (side-effect) functions to resources such as
  * synths, buses, buffers. In general these reflect the OSC messages defined for each object, and send
  * them straight to the server. For example, a `Synth` has function `newMsg` which returns an OSC message
  * to instantiate the synth of the server. After importing `Ops`, you will be able to directly launch
  * a synth using `SynthDef.play` or `Synth.play`. You will be able to directly allocate and read buffers,
  * and so forth.
  *
  * The reason why these functions are separated from the rest of the API is to allow other frameworks
  * such as SoundProcesses to avoid side-effects which they handle differently (e.g., using STM).
  */
object Ops {
  //   implicit def nodeOps( n: Node ) : NodeOps = new NodeOps( n )

  /** This allows conversions to Group so that something like Server.default.freeAll becomes possible. */
  implicit def groupOps[G <% Group](g: G): GroupOps = new GroupOps(g)

  //   implicit def bufferOps( b: Buffer ) : BufferOps = new BufferOps( b )
  //   implicit def controlBusOps( b: ControlBus ) : ControlBusOps = new ControlBusOps( b )

  final implicit class SynthDefConstructors(val d: SynthDef.type) extends AnyVal {
    import SynthDef._
    def recv(name: String, server: Server = Server.default, completion: Completion = NoCompletion)
            (thunk: => Unit): SynthDef = {
      val d = apply(name)(thunk)
      d.recv(server, completion)
      d
    }
  }

  // cannot occur inside value class at the moment
  private[this] def sendSynthDefWithAction(d: SynthDef, server: Server, msgFun: Option[osc.Packet] => osc.Message,
                                           completion: SynthDef.Completion, name: String): Unit = {
    completion.action map { action =>
      val syncMsg = server.syncMsg()
      val syncID = syncMsg.id
      val compPacket: osc.Packet = completion.message match {
        case Some(msgFun2) => osc.Bundle.now(msgFun2(d), syncMsg)
        case None => syncMsg
      }
      val p   = msgFun(Some(compPacket))
      val fut = server.!!(p) {
        case message.Synced(`syncID`) => action(d)
        //        case message.TIMEOUT => println("ERROR: " + d + "." + name + " : timeout!")
      }
      val cfg = server.clientConfig
      import cfg.executionContext
      fut.onFailure {
        case message.Timeout() => println(s"ERROR: $d.$name : timeout!")
      }

    } getOrElse {
      server ! msgFun(completion.message.map(_.apply(d)))
    }
  }

  final implicit class SynthDefOps(val d: SynthDef) extends AnyVal {
    import SynthDef.{Completion, NoCompletion, defaultDir}
    import d._

    def recv(server: Server = Server.default, completion: Completion = NoCompletion): Unit =
      sendSynthDefWithAction(d, server, recvMsg(_), completion, "recv")

    def load(server: Server = Server.default, dir: String = defaultDir,
             completion: Completion = NoCompletion): Unit = {
      write(dir)
      sendSynthDefWithAction(d, server, loadMsg(dir, _), completion, "load")
    }

    def play(target: Node = Server.default, args: Seq[ControlSetMap] = Nil, addAction: AddAction = addToHead): Synth = {
      val synth   = Synth(target.server)
      val newMsg  = synth.newMsg(name, target, args, addAction)
      target.server ! recvMsg(newMsg)
      synth
    }
  }

  final implicit class NodeOps(val n: Node) extends AnyVal {
    import n._

    def free(): Unit = server ! freeMsg

    /** Pauses or resumes the node.
      *
      * @param flag if `true` the node is resumed, if `false` it is paused.
      */
    def run(flag: Boolean = true): Unit = server ! runMsg(flag)

    def set(pairs: ControlSetMap*): Unit = server ! setMsg(pairs: _*)

    def setn(pairs: ControlSetMap*): Unit = server ! setnMsg(pairs: _*)

    def trace(): Unit = server ! traceMsg

    def release(releaseTime: Optional[Double] = None): Unit = server ! releaseMsg(releaseTime)

    def map(pairs: ControlKBusMap.Single*): Unit = server ! mapMsg(pairs: _*)

    def mapn(mappings: ControlKBusMap*): Unit = server ! mapnMsg(mappings: _*)

    /** Creates a mapping from a mono-channel audio bus to one of the node's controls.
      *
      * Note that a mapped control acts similar to an `InFeedback` UGen in that it does not matter
      * whether the audio bus was written before the execution of the synth whose control is mapped or not.
      * If it was written before, no delay is introduced, otherwise a delay of one control block is introduced.
      *
      * @see  [[de.sciss.synth.ugen.InFeedback]]
      */
    def mapa(pairs: ControlABusMap.Single*): Unit = server ! mapaMsg(pairs: _*)

    /** Creates a mapping from a mono- or multi-channel audio bus to one of the node's controls.
      *
      * Note that a mapped control acts similar to an `InFeedback` UGen in that it does not matter
      * whether the audio bus was written before the execution of the synth whose control is mapped or not.
      * If it was written before, no delay is introduced, otherwise a delay of one control block is introduced.
      *
      * @see  [[de.sciss.synth.ugen.InFeedback]]
      */
    def mapan(mappings: ControlABusMap*): Unit = server ! mapanMsg(mappings: _*)

    def fill(control: Any, numChannels: Int, value: Float): Unit = server ! fillMsg(control, numChannels, value)

    def fill(fillings: message.NodeFill.Info*): Unit = server ! fillMsg(fillings: _*)

    /** Moves this node before another node
      *
      * @param   node  the node before which to move this node
      *
      * @see  [[de.sciss.synth.message.NodeBefore]]
      */
    def moveBefore(node: Node): Unit = server ! moveBeforeMsg(node)

    /** Moves this node after another node
      *
      * @param   node  the node after which to move this node
      *
      * @see  [[de.sciss.synth.message.NodeAfter]]
      */
    def moveAfter(node: Node): Unit = server ! moveAfterMsg(node)

    def moveToHead(group: Group): Unit = server ! moveToHeadMsg(group)

    def moveToTail(group: Group): Unit = server ! moveToTailMsg(group)
  }

  implicit final class GroupConstructors(val g: Group.type) extends AnyVal {
    import Group._

    def play(): Group = head(Server.default.defaultGroup)

    def play(target: Node = Server.default.defaultGroup, addAction: AddAction = addToHead): Group = {
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

    def freeAll(): Unit = server ! freeAllMsg

    def deepFree(): Unit = server ! deepFreeMsg

    def dumpTree(postControls: Boolean = false): Unit = server ! dumpTreeMsg(postControls)
  }

  implicit final class SynthConstructors(val s: Synth.type) extends AnyVal {
    import Synth._
    def play(defName: String, args: Seq[ControlSetMap] = Nil, target: Node = Server.default.defaultGroup,
             addAction: AddAction = addToHead): Synth = {
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

    def alloc(numFrames: Int, numChannels: Int = 1, completion: Buffer.Completion = Buffer.NoCompletion): Unit =
      server ! allocMsg(numFrames, numChannels, makePacket(completion))

    def free(completion: Optional[osc.Packet] = None): Unit = server ! freeMsg(completion, release = true)

    def close(completion: Optional[osc.Packet] = None): Unit = server ! closeMsg(completion)

    def allocRead(path: String, startFrame: Int = 0, numFrames: Int = -1,
                  completion: Buffer.Completion = Buffer.NoCompletion): Unit =
      server ! allocReadMsg(path, startFrame, numFrames, makePacket(completion, forceQuery = true))

    def allocReadChannel(path: String, startFrame: Int = 0, numFrames: Int = -1, channels: Seq[Int],
                         completion: Buffer.Completion = Buffer.NoCompletion): Unit =
      server ! allocReadChannelMsg(path, startFrame, numFrames, channels, makePacket(completion, forceQuery = true))

    def read(path: String, fileStartFrame: Int = 0, numFrames: Int = -1, bufStartFrame: Int = 0,
             leaveOpen: Boolean = false, completion: Buffer.Completion = Buffer.NoCompletion): Unit =
      server ! readMsg(path, fileStartFrame, numFrames, bufStartFrame, leaveOpen, makePacket(completion))

    def readChannel(path: String, fileStartFrame: Int = 0, numFrames: Int = -1, bufStartFrame: Int = 0,
                    leaveOpen: Boolean = false, channels: Seq[Int],
                    completion: Buffer.Completion = Buffer.NoCompletion): Unit =
      server ! readChannelMsg(path, fileStartFrame, numFrames, bufStartFrame, leaveOpen,
        channels, makePacket(completion))

    /** Sets the contents of the buffer by replacing
      * individual sample values. An error is thrown if any of the given
      * offsets is out of range.
      *
      * @param   pairs a list of modifications to the buffer contents, each element
      *                being a sample offset and the sample value. The sample offset ranges
      *                from zero to the number of samples in the buffer (exclusive), i.e.
      *                `numChannels * numFrames`. For instance, in a stereo-buffer, the offset
      *                for the right channel's fifth frame is `(5-1) * 2 + 1 = 9`.
      */
    def set(pairs: (Int, Float)*): Unit = server ! setMsg(pairs: _*)

    /** Sets the entire contents of the buffer.
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
    def setn(v: Vec[Float]): Unit = server ! setnMsg(v)

    /** Sets the contents of the buffer by replacing
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
    def setn(pairs: (Int, Vec[Float])*): Unit = server ! setnMsg(pairs: _*)

    def fill(index: Int, num: Int, value: Float): Unit = server ! fillMsg(index, num, value)

    def fill(infos: message.BufferFill.Info*): Unit = server ! fillMsg(infos: _*)

    def zero(completion: Optional[osc.Packet] = None): Unit = server ! zeroMsg(completion)

    def write(path: String, fileType: io.AudioFileType = io.AudioFileType.AIFF,
              sampleFormat: io.SampleFormat = io.SampleFormat.Float, numFrames: Int = -1, startFrame: Int = 0,
              leaveOpen: Boolean = false, completion: Buffer.Completion = Buffer.NoCompletion): Unit =
      server ! writeMsg(path, fileType, sampleFormat, numFrames, startFrame, leaveOpen, makePacket(completion))

    // ---- utility methods ----
    //    def play: Synth = play()

    def play(loop: Boolean = false, amp: Float = 1f, out: Int = 0): Synth = {
      import de.sciss.synth
      import ugen._
      synth.play(server, out) {
        // working around nasty compiler bug
        val ply = PlayBuf.ar(numChannels, id, BufRateScale.kr(id), loop = if (loop) 1 else 0)
        if (!loop) FreeSelfWhenDone.kr(ply)
        val ampCtl = "amp".kr(amp) // XXX TODO: scalac 2.10.0 inference bug
        ply * ampCtl
      }
    }
  }

  implicit final class ControlBusOps(val b: ControlBus) extends AnyVal {
    import b._

    def set(v: Float): Unit = server ! setMsg(v)

    def set(pairs: (Int, Float)*): Unit = server ! setMsg(pairs: _*)

    def setn(v: Vec[Float]): Unit = server ! setnMsg(v)

    def setn(pairs: (Int, Vec[Float])*): Unit = server ! setnMsg(pairs: _*)
  }
}