/*
 *  NodeManager.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2012 Hanns Holger Rutz. All rights reserved.
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

import collection.immutable.IntMap

object NodeManager {
   abstract sealed class NodeChange { def node: Node; def info: osc.NodeInfo }
   final case class NodeGo(   node: Node, info: osc.NodeInfo ) extends NodeChange
   final case class NodeEnd(  node: Node, info: osc.NodeInfo ) extends NodeChange
   final case class NodeOn(   node: Node, info: osc.NodeInfo ) extends NodeChange
   final case class NodeOff(  node: Node, info: osc.NodeInfo ) extends NodeChange
   final case class NodeMove( node: Node, info: osc.NodeInfo ) extends NodeChange
   case object Cleared
}

final class NodeManager( server: Server ) extends Model {

   import NodeManager._
    
	private var nodes: IntMap[ Node ] = _
//	private var autoAdd  = true
   private val sync     = new AnyRef
	
	// ---- constructor ----
   clear()
//      if( server.isRunning ) {
//         val defaultGroup = server.defaultGroup
//         nodes += defaultGroup.id -> defaultGroup
//      }

	def nodeChange( e: osc.NodeChange ) { e match {
      case osc.NodeGoMessage( nodeID, info ) =>
         val node = nodes.get( nodeID ) getOrElse {
            if( /* autoAdd && */ nodes.contains( info.parentID )) {
               val created = info match {
                  case ee: osc.SynthInfo => new Synth( server, nodeID )
                  case ee: osc.GroupInfo => new Group( server, nodeID )
               }
               register( created )
               created
            } else return
         }
         dispatchBoth( NodeGo( node, info ))

      case osc.NodeEndMessage( nodeID, info ) =>
         nodes.get( nodeID ).foreach { node =>
            unregister( node )
            dispatchBoth( NodeEnd( node, info ))
         }

      case osc.NodeOffMessage( nodeID, info ) =>
         nodes.get( e.nodeID ).foreach { node =>
            dispatchBoth( NodeOff( node, info ))
         }

      case osc.NodeOnMessage( nodeID, info ) =>
         nodes.get( e.nodeID ).foreach { node =>
            dispatchBoth( NodeOn( node, info ))
         }

      case osc.NodeMoveMessage( nodeID, info ) =>
         nodes.get( e.nodeID ).foreach { node =>
            dispatchBoth( NodeMove( node, info ))
         }

      case _ =>
	}}

   private def dispatchBoth( change: NodeChange ) {
      dispatch( change )
      change.node.updated( change )
   }
	
	// eventually this should be done automatically
	// by the message dispatch management
	def register( node: Node ) {
      sync.synchronized {
   		nodes += node.id -> node
      }
	}
	
	def unregister( node: Node ) {
      sync.synchronized {
   		nodes -= node.id
      }
	}

   def getNode( id: Int ) : Option[ Node ] = sync.synchronized { nodes.get( id )}
//   def getAll : Iterable[ Node ] = sync.synchronized { nodes }

   def clear() {
      val rootNode = server.rootNode // new Group( server, 0 )
      sync.synchronized {
         nodes = IntMap( rootNode.id -> rootNode )
      }
      dispatch( Cleared )
   }
}