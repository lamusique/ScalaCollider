/*
 *  Node.scala
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

/**
 * Add-actions are used by the server to determine where to place a node with
 * respect to other nodes. They form an enumeration of integers which are
 * represented by case objects being subclasses of this abstract class.
 *
 * @see  [[de.sciss.synth.Synth]]
 * @see  [[de.sciss.synth.Group]]
 */
sealed abstract class AddAction( final val id: Int )

/**
 * AddAction with id 0, indicating that a node should be add to the head of
 * of a target group.
 */
case object addToHead   extends AddAction( 0 )
/**
 * AddAction with id 1, indicating that a node should be add to the tail of
 * of a target group.
 */
case object addToTail   extends AddAction( 1 )
/**
 * AddAction with id 2, indicating that a node should be added to the same
 * group as the target node, right before it.
 */
case object addBefore   extends AddAction( 2 )
/**
 * AddAction with id 3, indicating that a node should be added to the same
 * group as the target node, right after it.
 */
case object addAfter    extends AddAction( 3 )
/**
 * AddAction with id 4, indicating that a node should replace an existing
 * node, that is take the target node's exact position in the tree.
 */
case object addReplace  extends AddAction( 4 )

/**
 * A representation for a node on the server's tree. A `Node` is either a `Synth` or a `Group`.
 *
 * '''Note''' that if the node is a group, all messages send to the node which are not specific to a
 * `Synth` or `Group`, i.e. all messages found in this class, will affect all child nodes of the group.
 * For example, if `release()` is called on a `Group`, the underlying `setMsg` is propagated to all
 * `Synth`s in the tree whose root is this group.
 */
abstract class Node extends Model {
   import Model._

   // ---- abstract ----
   def server: Server
   def id: Int

	def register() {
//  	NodeWatcher.register( this, assumePlaying )
       server.nodeManager.register( this )
  	}

   def onGo( thunk: => Unit ) {
      register()
      lazy val l: Listener = {
         case NodeManager.NodeGo( _, _ ) =>
            removeListener( l )
            thunk
      }
      addListener( l )
   }

   def onEnd( thunk: => Unit ) {
      register()
      lazy val l: Listener = {
         case NodeManager.NodeEnd( _, _ ) =>
            removeListener( l )
            thunk
      }
      addListener( l )
   }

   protected[synth] def updated( change: NodeManager.NodeChange ) {
      // XXX need to update isPlaying, isRunning etc.
      dispatch( change )
   }

  	def freeMsg = osc.NodeFreeMessage( id )

   /**
    * Returns an OSC message to resume the node if it was paused.
    *
    * @see [[de.sciss.synth.osc.NodeRunMessage]]
    */
  	def runMsg : osc.NodeRunMessage = runMsg( flag = true )

   /**
    * Returns an OSC message to resume the node if it was paused.
    *
    * @param flag if `true` the node is resumed, if `false` it is paused.
    *
    * @see [[de.sciss.synth.osc.NodeRunMessage]]
    */
  	def runMsg( flag: Boolean ) = osc.NodeRunMessage( id -> flag )
  
  	def setMsg( pairs: ControlSetMap* ) =
  		osc.NodeSetMessage( id, pairs: _* )

  	def setn( pairs: ControlSetMap* ) {
  		server ! setnMsg( pairs: _* )
  	}
	
  	def setnMsg( pairs: ControlSetMap* ) =
  		osc.NodeSetnMessage( id, pairs: _* )

   def traceMsg = osc.NodeTraceMessage( id )

  	def releaseMsg : osc.NodeSetMessage = releaseMsg( None )

   /**
    * A utility method which calls `setMsg` assuming a control named `gate`. The release time
    * argument is modified to correspond with the interpretation of the `gate` argument in
    * an `EnvGen` UGen. This is the case for synths created with the package method `play`.
    *
    * @param   releaseTime the optional release time in seconds within which the synth should fade out,
    *                      or `None` if the envelope should be released at its nominal release time. If the `EnvGen`
    *                      has a `doneAction` of `freeSelf`, the synth will be freed after the release phase.
    *
    * @see  [[de.sciss.synth.ugen.EnvGen]]
    * @see  [[de.sciss.synth.osc.NodeSetMessage]]
    */
  	def releaseMsg( releaseTime: Optional[ Double ]) = {
  		val value = releaseTime.map( -1.0 - _ ).getOrElse( 0.0 )
  		setMsg( "gate" -> value )
	}

   def mapMsg( pairs: ControlKBusMap.Single* ) =
      osc.NodeMapMessage( id, pairs: _* )
   
  	def mapn( mappings: ControlKBusMap* ) {
  		server ! mapnMsg( mappings: _* )
  	}
  	
  	def mapnMsg( mappings: ControlKBusMap* ) =
  		osc.NodeMapnMessage( id, mappings: _* )

   /**
    * Returns an OSC message to map from an mono-channel audio bus to one of the node's controls.
    *
    * Note that a mapped control acts similar to an `InFeedback` UGen in that it does not matter
    * whether the audio bus was written before the execution of the synth whose control is mapped or not.
    * If it was written before, no delay is introduced, otherwise a delay of one control block is introduced.
    *
    * @see  [[de.sciss.synth.ugen.InFeedback]]
    */
   def mapaMsg( pairs: ControlABusMap.Single* ) =
      osc.NodeMapaMessage( id, pairs: _* )

   /**
    * Returns an OSC message to map from an mono- or multi-channel audio bus to one of the node's controls.
    *
    * Note that a mapped control acts similar to an `InFeedback` UGen in that it does not matter
    * whether the audio bus was written before the execution of the synth whose control is mapped or not.
    * If it was written before, no delay is introduced, otherwise a delay of one control block is introduced.
    *
    * @see  [[de.sciss.synth.ugen.InFeedback]]
    */
  	def mapanMsg( mappings: ControlABusMap* ) =
  		osc.NodeMapanMessage( id, mappings: _* )

   def fillMsg( control: Any, numChannels: Int, value: Float ) =
      osc.NodeFillMessage( id, osc.NodeFillInfo( control, numChannels, value ))
   
  	def fillMsg( fillings: osc.NodeFillInfo* ) = osc.NodeFillMessage( id, fillings: _* )

   /**
    * Creates an osc. message to move this node before another node
    *
    * @param   node  the node before which to move this node
    *
    * @see  [[de.sciss.synth.osc.osc.NodeBeforeMessage]]
    */
   def moveBeforeMsg( node: Node ) = osc.NodeBeforeMessage( id -> node.id )

   /**
    * Creates an osc. message to move this node after another node
    *
    * @param   node  the node after which to move this node
    *
    * @see  [[de.sciss.synth.osc.osc.NodeAfterMessage]]
    */
   def moveAfterMsg( node: Node ) = osc.NodeAfterMessage( id -> node.id )

  	def moveToHeadMsg( group: Group ) : osc.GroupHeadMessage = group.moveNodeToHeadMsg( this )

  	def moveToTailMsg( group: Group ) : osc.GroupTailMessage = group.moveNodeToTailMsg( this )
}
