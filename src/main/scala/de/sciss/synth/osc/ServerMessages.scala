/*
 *  ServerMessages.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2011 Hanns Holger Rutz. All rights reserved.
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
 *
 *
 *  Changelog:
 */

package de.sciss.synth
package osc

import java.nio.ByteBuffer
import collection.immutable.{ IndexedSeq => IIdxSeq}
import collection.mutable.ListBuffer
import java.io.PrintStream
import de.sciss.osc.{Bundle, Message, Packet, PacketCodec}

object ServerCodec extends PacketCodec {
   import Packet._

   private val superCodec = PacketCodec.Builder().scsynth().build

	private val decodeStatusReply: (String, ByteBuffer) => Message = (name, b) => {
		// ",iiiiiffdd"
		if( (b.getLong != 0x2C69696969696666L) || (b.getShort != 0x6464) ) decodeFail( name )
		skipToValues( b )

//		if( b.getInt() != 1) decodeFail  // was probably intended as a version number...
		b.getInt()
		val numUGens		= b.getInt()
		val numSynths		= b.getInt()
		val numGroups		= b.getInt()
		val numDefs			= b.getInt()
		val avgCPU			= b.getFloat()
		val peakCPU			= b.getFloat()
		val sampleRate		= b.getDouble()
		val actualSampleRate= b.getDouble()

		StatusReplyMessage( numUGens, numSynths, numGroups, numDefs,
		                       avgCPU, peakCPU, sampleRate, actualSampleRate )
	}

   private val decodeSynced: (String, ByteBuffer) => Message = (name, b) => {
      // ",i"
      if( b.getShort() != 0x2C69 ) decodeFail( name )
      skipToValues( b )

      val id = b.getInt()

      SyncedMessage( id )
   }

	private def decodeNodeChange( factory: NodeMessageFactory ) :
      (String, ByteBuffer) => Message = (name, b) => {

		// ",iiiii[ii]"
		if( (b.getInt() != 0x2C696969) || (b.getShort() != 0x6969) ) decodeFail( name )
		val extTags = b.getShort()
		if( (extTags & 0xFF) == 0x00 ) {
			skipToAlign( b )
		} else {
			skipToValues( b )
		}
		val nodeID		= b.getInt()
		val parentID	= b.getInt()
		val predID		= b.getInt()
		val succID		= b.getInt()
		val nodeType	= b.getInt()

      if( nodeType == 0 ) {
         factory.apply( nodeID, SynthInfo( parentID, predID, succID ))
      } else if( (nodeType == 1) && (extTags == 0x6969) ) {	// group
         val headID	= b.getInt()
         val tailID	= b.getInt()
         factory.apply( nodeID, GroupInfo( parentID, predID, succID, headID, tailID ))
		} else decodeFail( name )
	}

   private val decodeBufferInfo: (String, ByteBuffer) => Message = (name, b) => {
      // ",[iiif]*N"
      if( b.get() != 0x2C ) decodeFail( name )
      var cnt = 0
      var tag = b.getShort()
      while( tag != 0x0000 ) {
         if( (tag != 0x6969) || (b.getShort() != 0x6966) ) decodeFail( name )
         cnt += 1
         tag = b.getShort()
      }
      skipToAlign( b )
      var infos = new ListBuffer[ BufferInfo ]
      while( cnt > 0 ) {
         infos += BufferInfo( b.getInt(), b.getInt(), b.getInt(), b.getFloat() )
         cnt -= 1
      }
      BufferInfoMessage( infos: _* )
   }

   private val msgDecoders = Map[ String, (String, ByteBuffer) => Message ](
      "/status.reply"   -> decodeStatusReply,
      "/n_go"			   -> decodeNodeChange( NodeGoMessage ),
      "/n_end"		      -> decodeNodeChange( NodeEndMessage ),
      "/n_off"		      -> decodeNodeChange( NodeOffMessage ),
      "/n_on"			   -> decodeNodeChange( NodeOnMessage ),
      "/n_move"		   -> decodeNodeChange( NodeMoveMessage ),
      "/n_info"		   -> decodeNodeChange( NodeInfoMessage ),
      "/synced"         -> decodeSynced,
      "/b_info"         -> decodeBufferInfo,
      "status.reply"	   -> decodeStatusReply
   )

   private val superDecoder: (String, ByteBuffer ) => Message =
      (name, b) => superCodec.decodeMessage( name, b ) // super.decodeMessage( name, b )

   override /* protected */ def decodeMessage( name: String, b: ByteBuffer ) : Message = {
        msgDecoders.getOrElse( name, superDecoder ).apply( name, b )
/*		val dec = msgDecoders.get( name )
		if( dec.isDefined ) {
			dec.get.apply( name, b )
		} else {
			super.decodeMessage( name, b )
		}
*/	}

   def encodeMessage( msg: Message, b: ByteBuffer ) { superCodec.encodeMessage( msg, b )}
   def getEncodedMessageSize( msg: Message ) = superCodec.getEncodedMessageSize( msg )
   def encodeBundle( bndl: Bundle, b: ByteBuffer ) { superCodec.encodeBundle( bndl, b )}
   def printAtom( value: Any, stream: PrintStream, nestCount: Int ) { superCodec.printAtom( value, stream, nestCount )}
   val charsetName = superCodec.charsetName

   private def decodeFail( name : String ) : Nothing = throw PacketCodec.MalformedPacket( name )
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
   def isSynchronous : Boolean
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
final case class SyncedMessage( id: Int ) extends Message( "/synced", id )
with Receive

/**
 * Represents a `/sync` message, which is queued with the asynchronous messages
 * on the server, and which, when executed, triggers a corresponding `/synced` reply
 * message (i.e. with the same id)
 *
 * @param   id    an arbitrary identifier which can be used to match the corresponding
 *                reply message. typically the id is incremented by 1 for each
 *                `/sync` message sent out.
 */
final case class SyncMessage( id: Int ) extends Message( "/sync", id )
with AsyncSend

final case class StatusReplyMessage( numUGens: Int, numSynths: Int, numGroups: Int,
                                  numDefs: Int, avgCPU: Float, peakCPU: Float,
                                  sampleRate: Double, actualSampleRate: Double )
extends Message( "/status.reply", 1, numUGens, numSynths, numGroups, numDefs, avgCPU, peakCPU,
                    sampleRate, actualSampleRate )
with Receive

case object StatusMessage extends Message( "/status" )
with SyncQuery

//trait NodeChange {
//	def name: String // aka command (/n_go, /n_end, /n_off, /n_on, /n_move, /n_info)
//	def nodeID:   Int
//	def parentID: Int
//	def predID:   Int
//	def succID:   Int
//}

abstract sealed class NodeInfo {
   def parentID: Int
   def predID:   Int
   def succID:   Int
   def toList( nodeID: Int ): List[ Any ]
}
final case class SynthInfo( parentID: Int, predID: Int, succID: Int ) extends NodeInfo {
   def toList( nodeID: Int ) = List( nodeID, parentID, predID, succID, 0 )
}
final case class GroupInfo( parentID: Int, predID: Int, succID: Int, headID: Int, tailID: Int ) extends NodeInfo {
   def toList( nodeID: Int ) = List( nodeID, parentID, predID, succID, 1, headID, tailID )
}

sealed trait NodeChange extends Receive {
   def nodeID: Int
   def info:   NodeInfo
}

protected[synth] sealed trait NodeMessageFactory {
   def apply( nodeID: Int, info: NodeInfo ) : Message
}

object NodeGoMessage extends NodeMessageFactory
final case class NodeGoMessage( nodeID: Int, info: NodeInfo )
extends Message( "/n_go", info.toList( nodeID ): _* ) with NodeChange

object NodeEndMessage extends NodeMessageFactory
final case class NodeEndMessage( nodeID: Int, info: NodeInfo )
extends Message( "/n_end", info.toList( nodeID ): _* ) with NodeChange

object NodeOnMessage extends NodeMessageFactory
final case class NodeOnMessage( nodeID: Int, info: NodeInfo )
extends Message( "/n_on", info.toList( nodeID ): _* ) with NodeChange

object NodeOffMessage extends NodeMessageFactory
final case class NodeOffMessage( nodeID: Int, info: NodeInfo )
extends Message( "/n_off", info.toList( nodeID ): _* ) with NodeChange

object NodeMoveMessage extends NodeMessageFactory
final case class NodeMoveMessage( nodeID: Int, info: NodeInfo )
extends Message( "/n_move", info.toList( nodeID ): _* ) with NodeChange

object NodeInfoMessage extends NodeMessageFactory
final case class NodeInfoMessage( nodeID: Int, info: NodeInfo )
extends Message( "/n_info", info.toList( nodeID ): _* ) with NodeChange

final case class BufferInfo( bufID: Int, numFrames: Int, numChannels: Int, sampleRate: Float )

// we need List[ Any ] as scala would otherwise expand to List[ Float ]!
final case class BufferInfoMessage( infos: BufferInfo* )
extends Message( "/b_info", infos.flatMap( info =>
   List[ Any ]( info.bufID, info.numFrames, info.numChannels, info.sampleRate )): _* )
with Receive

// ---- messages to the server ----
final case class ServerNotifyMessage( onOff: Boolean )
extends Message( "/notify", if( onOff ) 1 else 0 )
with AsyncSend

case object ServerQuitMessage extends Message( "/quit" )
with AsyncSend

final case class BufferQueryMessage( ids: Int* ) extends Message( "/b_query", ids: _* )
with SyncQuery

final case class BufferFreeMessage( id: Int, completion: Option[ Packet ])
extends Message( "/b_free", (completion.map( m => List( id, m )) getOrElse List( id )): _* )
with AsyncSend

final case class BufferCloseMessage( id: Int, completion: Option[ Packet ])
extends Message( "/b_close", (completion.map( m => List( id, m )) getOrElse List( id )): _* )
with AsyncSend

final case class BufferAllocMessage( id: Int, numFrames: Int, numChannels: Int, completion: Option[ Packet ])
extends Message( "/b_alloc", (completion.map( m => List( id, numFrames, numChannels, m ))
                                                   getOrElse List( id, numFrames, numChannels )): _* )
with AsyncSend

final case class BufferAllocReadMessage( id: Int, path: String, startFrame: Int, numFrames: Int,
                                      completion: Option[ Packet ])
extends Message( "/b_allocRead", (completion.map( m => List( id, path, startFrame, numFrames, m ))
                                                       getOrElse List( id, path, startFrame, numFrames )): _* )
with AsyncSend

final case class BufferAllocReadChannelMessage( id: Int, path: String, startFrame: Int, numFrames: Int,
                                             channels: List[ Int ], completion: Option[ Packet ])
extends Message( "/b_allocReadChannel", (List( id, path, startFrame, numFrames ) ::: channels
   ::: completion.map( msg => List( msg )).getOrElse( Nil )): _* )
with AsyncSend

final case class BufferReadMessage( id: Int, path: String, fileStartFrame: Int, numFrames: Int, bufStartFrame: Int,
                                 leaveOpen: Boolean, completion: Option[ Packet ])
extends Message( "/b_read", (completion.map(
   m =>      List( id, path, fileStartFrame, numFrames, bufStartFrame, if( leaveOpen ) 1 else 0, m ))
   getOrElse List( id, path, fileStartFrame, numFrames, bufStartFrame, if( leaveOpen ) 1 else 0 )): _* )
with AsyncSend

final case class BufferReadChannelMessage( id: Int, path: String, fileStartFrame: Int, numFrames: Int,
                                        bufStartFrame: Int, leaveOpen: Boolean, channels: List[ Int ],
                                        completion: Option[ Packet ])
extends Message( "/b_readChannel", (List( id, path, fileStartFrame, numFrames, bufStartFrame,
   if( leaveOpen ) 1 else 0 ) ::: channels ::: completion.map( msg => List( msg )).getOrElse( Nil )): _* )
with AsyncSend

final case class BufferZeroMessage( id: Int, completion: Option[ Packet ])
extends Message( "/b_zero", (completion.map( m => List( id, m )) getOrElse List( id )): _* )
with AsyncSend

final case class BufferWriteMessage( id: Int, path: String, fileType: io.AudioFileType, sampleFormat: io.SampleFormat,
                                  numFrames: Int, startFrame: Int, leaveOpen: Boolean,
                                  completion: Option[ Packet])
extends Message( "/b_write", (List( id, path, fileType.id, sampleFormat.id, numFrames, startFrame,
   if( leaveOpen ) 1 else 0 ) ::: completion.map( msg => List( msg )).getOrElse( Nil )): _* )
with AsyncSend

final case class BufferSetMessage( id: Int, indicesAndValues: (Int, Float)* )
extends Message( "/b_set", (id +: indicesAndValues.flatMap( iv => List[ Any ]( iv._1, iv._2 ))): _* )
with SyncCmd

final case class BufferSetnMessage( id: Int, indicesAndValues: (Int, IIdxSeq[ Float ])* )
extends Message( "/b_setn", (id +: indicesAndValues.flatMap( iv => Vector( iv._1, iv._2.size ) ++ iv._2 )): _* )
with SyncCmd

//case class BusValuePair( index: Int, value: Float )
final case class ControlBusSetMessage( indicesAndValues: (Int, Float)* )
extends Message( "/c_set", indicesAndValues.flatMap( iv => List[ Any ]( iv._1, iv._2 )): _* )
with SyncCmd

//case class BusValuesPair( index: Int, values: IIdxSeq[ Float ])
final case class ControlBusSetnMessage( indicesAndValues: (Int, IIdxSeq[ Float ])* )
extends Message( "/c_setn", indicesAndValues.flatMap( iv => Vector( iv._1, iv._2.size ) ++ iv._2 ): _* )
with SyncCmd

final case class ControlBusGetMessage( index: Int* ) // fucking hell: indices is defined for SeqLike
extends Message( "/c_get", index: _* )
with SyncQuery

final case class GroupNewInfo( groupID: Int, addAction: Int, targetID: Int )
final case class GroupNewMessage( groups: GroupNewInfo* )
extends Message( "/g_new", groups.flatMap( g => List( g.groupID, g.addAction, g.targetID )): _* )
with SyncCmd

//case class NodeFlagPair( id: Int, flag: Boolean )
final case class GroupDumpTreeMessage( groups: (Int, Boolean)* )
extends Message( "/g_dumpTree", groups.flatMap( g => List( g._1, if( g._2 ) 1 else 0 )): _* )
with SyncCmd

final case class GroupQueryTreeMessage( groups: (Int, Boolean)* )
extends Message( "/g_queryTree", groups.flatMap( g => List( g._1, if( g._2 ) 1 else 0 )): _* )
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
final case class GroupHeadMessage( groups: (Int, Int)* )
extends Message( "/g_head", groups.flatMap( g => List( g._1, g._2 )): _* )
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
final case class GroupTailMessage( groups: (Int, Int)* )
extends Message( "/g_tail", groups.flatMap( g => List( g._1, g._2 )): _* )
with SyncCmd

final case class GroupFreeAllMessage( ids: Int* )
extends Message( "/g_freeAll", ids: _* )
with SyncCmd

final case class GroupDeepFreeMessage( ids: Int* )
extends Message( "/g_deepFree", ids: _* )
with SyncCmd

final case class SynthNewMessage( defName: String, id: Int, addAction: Int, targetID: Int, controls: ControlSetMap* )
extends Message( "/s_new",
   (Vector( defName, id, addAction, targetID ) ++ controls.flatMap( _.toSetSeq )): _* )
with SyncCmd

final case class NodeRunMessage( nodes: (Int, Boolean)* )
extends Message( "/n_run", nodes.flatMap( n => List( n._1, if( n._2 ) 1 else 0 )): _* )
with SyncCmd

final case class NodeSetMessage( id: Int, pairs: ControlSetMap* )
extends Message( "/n_set", (id +: pairs.flatMap( _.toSetSeq )): _* )
with SyncCmd

final case class NodeSetnMessage( id: Int, pairs: ControlSetMap* )
extends Message( "/n_setn", (id +: pairs.flatMap( _.toSetnSeq )): _* )
with SyncCmd

final case class NodeTraceMessage( ids: Int* )
extends Message( "/n_trace", ids: _* )
with SyncCmd

final case class NodeNoIDMessage( ids: Int* )
extends Message( "/n_noid", ids: _* )
with SyncCmd

final case class NodeFreeMessage( ids: Int* )
extends Message( "/n_free", ids: _* )
with SyncCmd

final case class NodeMapMessage( id: Int, mappings: ControlKBusMap.Single* )
extends Message( "/n_map", (id +: mappings.flatMap( _.toMapSeq )): _* )
with SyncCmd

final case class NodeMapnMessage( id: Int, mappings: ControlKBusMap* )
extends Message( "/n_mapn", (id +: mappings.flatMap( _.toMapnSeq )): _* )
with SyncCmd

final case class NodeMapaMessage( id: Int, mappings: ControlABusMap.Single* )
extends Message( "/n_mapa", (id +: mappings.flatMap( _.toMapaSeq )): _* )
with SyncCmd

final case class NodeMapanMessage( id: Int, mappings: ControlABusMap* )
extends Message( "/n_mapan", (id +: mappings.flatMap( _.toMapanSeq )): _* )
with SyncCmd

final case class NodeFillInfo( control: Any, numChannels: Int, value: Float )

final case class NodeFillMessage( id: Int, fillings: NodeFillInfo* )
extends Message( "/n_fill", (id +: fillings.flatMap( f => Vector( f.control, f.numChannels, f.value ))): _* )
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
final case class NodeBeforeMessage( groups: (Int, Int)* )
extends Message( "/n_before", groups.flatMap( g => List( g._1, g._2 )): _* )
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
final case class NodeAfterMessage( groups: (Int, Int)* )
extends Message( "/n_after", groups.flatMap( g => List( g._1, g._2 )): _* )
with SyncCmd

final case class SynthDefRecvMessage( bytes: ByteBuffer, completion: Option[ Packet ])
extends Message( "/d_recv", (bytes :: (completion.map( List( _ )) getOrElse Nil)): _* )
with AsyncSend

final case class SynthDefFreeMessage( names: String* )
extends Message( "/d_free", names: _* )
with SyncCmd

final case class SynthDefLoadMessage( path: String, completion: Option[ Packet ])
extends Message( "/d_load", (path :: (completion.map( List( _ )) getOrElse Nil)): _* )
with AsyncSend

final case class SynthDefLoadDirMessage( path: String, completion: Option[ Packet ])
extends Message( "/d_loadDir", (path :: (completion.map( List( _ )) getOrElse Nil)): _* )
with AsyncSend
