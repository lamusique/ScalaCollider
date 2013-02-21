/*
 *  ServerMessages.scala
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
package message

import java.nio.ByteBuffer
import collection.immutable.{IndexedSeq => IIdxSeq}
import collection.mutable.ListBuffer
import java.io.PrintStream
import de.sciss.osc.{Bundle, Message, Packet, PacketCodec}

object ServerCodec extends PacketCodec {
   import Packet._

  private final val superCodec = PacketCodec().scsynth().build

  private final val decodeStatusReply: (String, ByteBuffer) => Message = (name, b) => {
    // ",iiiiiffdd"
    if ((b.getLong != 0x2C69696969696666L) || (b.getShort != 0x6464)) decodeFail(name)
    skipToValues(b)

    //		if( b.getInt() != 1) decodeFail  // was probably intended as a version number...
    b.getInt()
    val numUGens          = b.getInt()
    val numSynths         = b.getInt()
    val numGroups         = b.getInt()
    val numDefs           = b.getInt()
    val avgCPU            = b.getFloat()
    val peakCPU           = b.getFloat()
    val sampleRate        = b.getDouble()
    val actualSampleRate  = b.getDouble()

    StatusReply(numUGens = numUGens, numSynths = numSynths, numGroups = numGroups, numDefs = numDefs,
      avgCPU = avgCPU, peakCPU = peakCPU, sampleRate = sampleRate, actualSampleRate = actualSampleRate)
  }

  private final val decodeSynced: (String, ByteBuffer) => Message = { (name, b) =>
    // ",i"
    if (b.getShort() != 0x2C69) decodeFail(name)
    skipToValues(b)

    val id = b.getInt()

    Synced(id)
  }

  private def decodeNodeChange(factory: NodeMessageFactory): (String, ByteBuffer) => Message = { (name, b) =>

    // ",iiiii[ii]"
    if ((b.getInt() != 0x2C696969) || (b.getShort() != 0x6969)) decodeFail(name)
    val extTags = b.getShort()
    if ((extTags & 0xFF) == 0x00) {
      skipToAlign(b)
    } else {
      skipToValues(b)
    }
    val nodeID    = b.getInt()
    val parentID  = b.getInt()
    val predID    = b.getInt()
    val succID    = b.getInt()
    val nodeType  = b.getInt()

    if (nodeType == 0) {
      factory.apply(nodeID, NodeInfo.SynthData(parentID, predID, succID))
    } else if ((nodeType == 1) && (extTags == 0x6969)) {
      // group
      val headID  = b.getInt()
      val tailID  = b.getInt()
      factory.apply(nodeID, NodeInfo.GroupData(parentID, predID, succID, headID, tailID))
    } else decodeFail(name)
  }

  private val decodeBufferInfo: (String, ByteBuffer) => Message = { (name, b) =>
    // ",[iiif]*N"
    if (b.get() != 0x2C) decodeFail(name)
    var cnt = 0
    var tag = b.getShort()
    while (tag != 0x0000) {
      if ((tag != 0x6969) || (b.getShort() != 0x6966)) decodeFail(name)
      cnt += 1
      tag = b.getShort()
    }
    skipToAlign(b)
    var infos = new ListBuffer[BufferInfo.Data]
    while (cnt > 0) {
      infos += BufferInfo.Data(b.getInt(), b.getInt(), b.getInt(), b.getFloat())
      cnt -= 1
    }
    BufferInfo(infos: _*)
  }

  private final val msgDecoders = Map[String, (String, ByteBuffer) => Message](
    "/status.reply" -> decodeStatusReply,
    "/n_go"         -> decodeNodeChange(NodeGo),
    "/n_end"        -> decodeNodeChange(NodeEnd),
    "/n_off"        -> decodeNodeChange(NodeOff),
    "/n_on"         -> decodeNodeChange(NodeOn),
    "/n_move"       -> decodeNodeChange(NodeMove),
    "/n_info"       -> decodeNodeChange(NodeInfo),
    "/synced"       -> decodeSynced,
    "/b_info"       -> decodeBufferInfo,
    "status.reply"  -> decodeStatusReply
  )

  private final val superDecoder: (String, ByteBuffer) => Message =
    (name, b) => superCodec.decodeMessage(name, b) // super.decodeMessage( name, b )

  override /* protected */ def decodeMessage(name: String, b: ByteBuffer): Message = {
    msgDecoders.getOrElse(name, superDecoder).apply(name, b)
    /*		val dec = msgDecoders.get( name )
        if( dec.isDefined ) {
          dec.get.apply( name, b )
        } else {
          super.decodeMessage( name, b )
        }
    */
  }

  def encodeMessage(msg: Message, b: ByteBuffer) {
    superCodec.encodeMessage(msg, b)
  }

  def encodedMessageSize(msg: Message) = superCodec.encodedMessageSize(msg)

  def encodeBundle(bndl: Bundle, b: ByteBuffer) {
    superCodec.encodeBundle(bndl, b)
  }

  def printAtom(value: Any, stream: PrintStream, nestCount: Int) {
    superCodec.printAtom(value, stream, nestCount)
  }

  final val charsetName = superCodec.charsetName

  private def decodeFail(name: String): Nothing = throw PacketCodec.MalformedPacket(name)
}
// val nodeID: Int, val parentID: Int, val predID: Int, val succID: Int, val headID: Int, val tailID: Int )

/**
 *    Identifies messages received or sent by the
 *    SuperCollider server
 */
sealed trait ServerMessage

/**
 * Identifies messages sent to the SuperCollider server
 */
sealed trait Send extends ServerMessage {
  def isSynchronous: Boolean
}
/**
 * Identifies messages sent to the server which are
 * executed synchronously
 */
sealed trait SyncSend extends Send {
  final def isSynchronous = true
}
/**
 * Identifies command messages sent to the server which are
 * executed synchronously and do not return a message
 */
trait SyncCmd extends SyncSend
/**
 * Identifies query messages sent to the server which are
 * executed synchronously and produce a reply message
 */
trait SyncQuery extends SyncSend
/**
 * Identifies messages sent to the server which are
 * executed asynchronously and reply with a form of
 * done-message.
 */
sealed trait AsyncSend extends Send {
  final def isSynchronous = false
}
/**
 * Identifies messages returned by SuperCollider server
 */
trait Receive extends ServerMessage

/**
 * Represents a `/synced` message, a reply from the server acknowledging that
 * all asynchronous operations up to the corresponding `/sync` message (i.e. with
 * the same id) have been completed
 */
final case class Synced(id: Int) extends Message("/synced", id) with Receive

/**
 * Represents a `/sync` message, which is queued with the asynchronous messages
 * on the server, and which, when executed, triggers a corresponding `/synced` reply
 * message (i.e. with the same id)
 *
 * @param   id    an arbitrary identifier which can be used to match the corresponding
 *                reply message. typically the id is incremented by 1 for each
 *                `/sync` message sent out.
 */
final case class Sync(id: Int) extends Message("/sync", id) with AsyncSend

final case class StatusReply(numUGens: Int, numSynths: Int, numGroups: Int, numDefs: Int, avgCPU: Float,
                             peakCPU: Float, sampleRate: Double, actualSampleRate: Double)
  extends Message("/status.reply", 1, numUGens, numSynths, numGroups, numDefs, avgCPU, peakCPU,
                                      sampleRate, actualSampleRate)
  with Receive

case object Status extends Message("/status") with SyncQuery

//trait NodeChange {
//	def name: String // aka command (/n_go, /n_end, /n_off, /n_on, /n_move, /n_info)
//	def nodeID:   Int
//	def parentID: Int
//	def predID:   Int
//	def succID:   Int
//}

sealed trait NodeChange extends Receive {
  def nodeID: Int
  def info: NodeInfo.Data
}

private[synth] sealed trait NodeMessageFactory {
  def apply(nodeID: Int, info: NodeInfo.Data): Message
}

object NodeGo extends NodeMessageFactory
final case class NodeGo(nodeID: Int, info: NodeInfo.Data)
  extends Message("/n_go", info.toList(nodeID): _*) with NodeChange

object NodeEnd extends NodeMessageFactory
final case class NodeEnd(nodeID: Int, info: NodeInfo.Data)
  extends Message("/n_end", info.toList(nodeID): _*) with NodeChange

object NodeOn extends NodeMessageFactory
final case class NodeOn(nodeID: Int, info: NodeInfo.Data)
  extends Message("/n_on", info.toList(nodeID): _*) with NodeChange

object NodeOff extends NodeMessageFactory
final case class NodeOff(nodeID: Int, info: NodeInfo.Data)
  extends Message("/n_off", info.toList(nodeID): _*) with NodeChange

object NodeMove extends NodeMessageFactory
final case class NodeMove(nodeID: Int, info: NodeInfo.Data)
  extends Message("/n_move", info.toList(nodeID): _*) with NodeChange

object NodeInfo extends NodeMessageFactory {
  abstract sealed class Data {
    def parentID: Int
    def predID: Int
    def succID: Int

    def toList(nodeID: Int): List[Any]
  }

  final case class SynthData(parentID: Int, predID: Int, succID: Int) extends Data {
    def toList(nodeID: Int): List[Any] = List(nodeID, parentID, predID, succID, 0)
  }

  final case class GroupData(parentID: Int, predID: Int, succID: Int, headID: Int, tailID: Int) extends Data {
    def toList(nodeID: Int): List[Any] = List(nodeID, parentID, predID, succID, 1, headID, tailID)
  }

}
final case class NodeInfo(nodeID: Int, info: NodeInfo.Data)
  extends Message("/n_info", info.toList(nodeID): _*) with NodeChange

// we need List[Any] as scala would otherwise expand to List[Float]!
object BufferInfo {
  final case class Data(bufID: Int, numFrames: Int, numChannels: Int, sampleRate: Float)
}

final case class BufferInfo(infos: BufferInfo.Data*)
  extends Message("/b_info", infos.flatMap(info =>
    List[Any](info.bufID, info.numFrames, info.numChannels, info.sampleRate)): _*)
  with Receive

// ---- messages to the server ----

final case class ServerNotify(onOff: Boolean)
  extends Message("/notify", onOff)
  with AsyncSend

case object ServerQuit extends Message("/quit") with AsyncSend

sealed trait HasCompletion extends AsyncSend {
  def completion: Option[Packet]
  def updateCompletion(completion: Option[Packet]): Message with AsyncSend with HasCompletion
}

final case class BufferQuery(ids: Int*) extends Message("/b_query", ids: _*) with SyncQuery

final case class BufferFree(id: Int, completion: Option[Packet])
  extends Message("/b_free", (completion.map(m => List(id, m)) getOrElse List(id)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferClose(id: Int, completion: Option[Packet])
  extends Message("/b_close", (completion.map(m => List(id, m)) getOrElse List(id)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferAlloc(id: Int, numFrames: Int, numChannels: Int, completion: Option[Packet])
  extends Message("/b_alloc", (completion.map(m => List(id, numFrames, numChannels, m))
    getOrElse List(id, numFrames, numChannels)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferAllocRead(id: Int, path: String, startFrame: Int, numFrames: Int, completion: Option[Packet])
  extends Message("/b_allocRead", (completion.map(m => List(id, path, startFrame, numFrames, m))
    getOrElse List(id, path, startFrame, numFrames)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferAllocReadChannel(id: Int, path: String, startFrame: Int, numFrames: Int,
                                        channels: List[Int], completion: Option[Packet])
  extends Message("/b_allocReadChannel", (List(id, path, startFrame, numFrames) ::: channels
    ::: completion.map(msg => List(msg)).getOrElse(Nil)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferRead(id: Int, path: String, fileStartFrame: Int, numFrames: Int, bufStartFrame: Int,
                            leaveOpen: Boolean, completion: Option[Packet])
  extends Message("/b_read", (completion.map(
    m => List(id, path, fileStartFrame, numFrames, bufStartFrame, leaveOpen, m))
    getOrElse List(id, path, fileStartFrame, numFrames, bufStartFrame, leaveOpen)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferReadChannel(id: Int, path: String, fileStartFrame: Int, numFrames: Int,
                                   bufStartFrame: Int, leaveOpen: Boolean, channels: List[Int],
                                   completion: Option[Packet])
  extends Message("/b_readChannel", (List(id, path, fileStartFrame, numFrames, bufStartFrame, leaveOpen) :::
    channels ::: completion.map(msg => List(msg)).getOrElse(Nil)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferZero(id: Int, completion: Option[Packet])
  extends Message("/b_zero", (completion.map(m => List(id, m)) getOrElse List(id)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferWrite(id: Int, path: String, fileType: io.AudioFileType, sampleFormat: io.SampleFormat,
                             numFrames: Int, startFrame: Int, leaveOpen: Boolean,
                             completion: Option[Packet])
  extends Message("/b_write", (List(id, path, fileType.id, sampleFormat.id, numFrames, startFrame, leaveOpen) :::
    completion.map(msg => List(msg)).getOrElse(Nil)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class BufferSet(id: Int, indicesAndValues: (Int, Float)*)
  extends Message("/b_set", (id +: indicesAndValues.flatMap(iv => List[Any](iv._1, iv._2))): _*)
  with SyncCmd

final case class BufferSetn(id: Int, indicesAndValues: (Int, IIdxSeq[Float])*)
  extends Message("/b_setn", (id +: indicesAndValues.flatMap(iv => Vector(iv._1, iv._2.size) ++ iv._2)): _*)
  with SyncCmd

final case class ControlBusSet(indicesAndValues: (Int, Float)*)
  extends Message("/c_set", indicesAndValues.flatMap(iv => List[Any](iv._1, iv._2)): _*)
  with SyncCmd

//case class BusValuesPair( index: Int, values: IIdxSeq[ Float ])
final case class ControlBusSetn(indicesAndValues: (Int, IIdxSeq[Float])*)
  extends Message("/c_setn", indicesAndValues.flatMap(iv => Vector(iv._1, iv._2.size) ++ iv._2): _*)
  with SyncCmd

final case class ControlBusGet(index: Int*) // fucking hell: indices is defined for SeqLike
  extends Message("/c_get", index: _*)
  with SyncQuery

object GroupNew {
  final case class Info(groupID: Int, addAction: Int, targetID: Int)
}
final case class GroupNew(groups: GroupNew.Info*)
  extends Message("/g_new", groups.flatMap(g => List(g.groupID, g.addAction, g.targetID)): _*)
  with SyncCmd

final case class GroupDumpTree(groups: (Int, Boolean)*)
  extends Message("/g_dumpTree", groups.flatMap(g => List(g._1, g._2)): _*)
  with SyncCmd

final case class GroupQueryTree(groups: (Int, Boolean)*)
  extends Message("/g_queryTree", groups.flatMap(g => List(g._1, g._2)): _*)
  with SyncQuery

/**
 * Represents an `/g_head` message, which pair-wise places nodes at the head
 * of groups.
 * {{{
 * /g_head
 *   [
 *     Int - the ID of the group at which head a node is to be placed (B)
 *     int - the ID of the node to place (A)
 *   ] * N
 * }}}
 * So that for each pair, node A is moved to the head of group B.
 */
final case class GroupHead(groups: (Int, Int)*)
  extends Message("/g_head", groups.flatMap(g => List(g._1, g._2)): _*)
  with SyncCmd

/**
 * Represents an `/g_tail` message, which pair-wise places nodes at the tail
 * of groups.
 * {{{
 * /g_tail
 *   [
 *     Int - the ID of the group at which tail a node is to be placed (B)
 *     int - the ID of the node to place (A)
 *   ] * N
 * }}}
 * So that for each pair, node A is moved to the tail of group B.
 */
final case class GroupTail(groups: (Int, Int)*)
  extends Message("/g_tail", groups.flatMap(g => List(g._1, g._2)): _*)
  with SyncCmd

final case class GroupFreeAll(ids: Int*)
  extends Message("/g_freeAll", ids: _*)
  with SyncCmd

final case class GroupDeepFree(ids: Int*)
  extends Message("/g_deepFree", ids: _*)
  with SyncCmd

final case class SynthNew(defName: String, id: Int, addAction: Int, targetID: Int, controls: ControlSetMap*)
  extends Message("/s_new",
    (Vector(defName, id, addAction, targetID) ++ controls.flatMap(_.toSetSeq)): _*)
  with SyncCmd

final case class NodeRun(nodes: (Int, Boolean)*)
  extends Message("/n_run", nodes.flatMap(n => List(n._1, n._2)): _*)
  with SyncCmd

final case class NodeSet(id: Int, pairs: ControlSetMap*)
  extends Message("/n_set", (id +: pairs.flatMap(_.toSetSeq)): _*)
  with SyncCmd

final case class NodeSetn(id: Int, pairs: ControlSetMap*)
  extends Message("/n_setn", (id +: pairs.flatMap(_.toSetnSeq)): _*)
  with SyncCmd

final case class NodeTrace(ids: Int*)
  extends Message("/n_trace", ids: _*)
  with SyncCmd

final case class NodeNoID(ids: Int*)
  extends Message("/n_noid", ids: _*)
  with SyncCmd

final case class NodeFree(ids: Int*)
  extends Message("/n_free", ids: _*)
  with SyncCmd

final case class NodeMap(id: Int, mappings: ControlKBusMap.Single*)
  extends Message("/n_map", (id +: mappings.flatMap(_.toMapSeq)): _*)
  with SyncCmd

final case class NodeMapn(id: Int, mappings: ControlKBusMap*)
  extends Message("/n_mapn", (id +: mappings.flatMap(_.toMapnSeq)): _*)
  with SyncCmd

final case class NodeMapa(id: Int, mappings: ControlABusMap.Single*)
  extends Message("/n_mapa", (id +: mappings.flatMap(_.toMapaSeq)): _*)
  with SyncCmd

final case class NodeMapan(id: Int, mappings: ControlABusMap*)
  extends Message("/n_mapan", (id +: mappings.flatMap(_.toMapanSeq)): _*)
  with SyncCmd

object NodeFill {
  final case class Info(control: Any, numChannels: Int, value: Float)
}
final case class NodeFill(id: Int, fillings: NodeFill.Info*)
  extends Message("/n_fill", (id +: fillings.flatMap(f => Vector(f.control, f.numChannels, f.value))): _*)
  with SyncCmd

/**
 * Represents an `/n_before` message, which pair-wise places nodes before
 * other nodes.
 * {{{
 * /n_before
 *   [
 *     Int - the ID of the node to place (A)
 *     int - the ID of the node before which the above is placed (B)
 *   ] * N
 * }}}
 * So that for each pair, node A in the same group as node B, to execute immediately before node B.
 */
final case class NodeBefore(groups: (Int, Int)*)
  extends Message("/n_before", groups.flatMap(g => List(g._1, g._2)): _*)
  with SyncCmd

/**
 * Represents an `/n_after` message, which pair-wise places nodes after
 * other nodes.
 * {{{
 * /n_after
 *   [
 *     Int - the ID of the node to place (A)
 *     int - the ID of the node after which the above is placed (B)
 *   ] * N
 * }}}
 * So that for each pair, node A in the same group as node B, to execute immediately after node B.
 */
final case class NodeAfter(groups: (Int, Int)*)
  extends Message("/n_after", groups.flatMap(g => List(g._1, g._2)): _*)
  with SyncCmd

final case class SynthDefRecv(bytes: ByteBuffer, completion: Option[Packet])
  extends Message("/d_recv", (bytes :: (completion.map(List(_)) getOrElse Nil)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class SynthDefFree(names: String*)
  extends Message("/d_free", names: _*)
  with SyncCmd

final case class SynthDefLoad(path: String, completion: Option[Packet])
  extends Message("/d_load", (path :: (completion.map(List(_)) getOrElse Nil)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}

final case class SynthDefLoadDir(path: String, completion: Option[Packet])
  extends Message("/d_loadDir", (path :: (completion.map(List(_)) getOrElse Nil)): _*)
  with HasCompletion {

  def updateCompletion(completion: Option[Packet]) = copy(completion = completion)
}
