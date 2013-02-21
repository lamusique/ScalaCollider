/*
 *  Group.scala
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

object Group {
  def apply(server: Server): Group = apply(server, server.nextNodeID())

  def apply(): Group = apply(Server.default)
}

final case class Group(server: Server, id: Int)
  extends Node {

//  def this(server: Server) = this(server, server.nextNodeID())
//
//  def this() = this(Server.default)

  def newMsg(target: Node, addAction: AddAction) =
    message.GroupNew(message.GroupNew.Info(id, addAction.id, target.id))

  def dumpTreeMsg: message.GroupDumpTree = dumpTreeMsg(postControls = false)

  def dumpTreeMsg(postControls: Boolean) = message.GroupDumpTree(id -> postControls)

  def queryTreeMsg(postControls: Boolean) = message.GroupQueryTree(id -> postControls)

  def freeAllMsg = message.GroupFreeAll(id)

  def deepFreeMsg = message.GroupDeepFree(id)

  def moveNodeToHeadMsg(node: Node) = message.GroupHead(id -> node.id)

  def moveNodeToTailMsg(node: Node) = message.GroupTail(id -> node.id)
}