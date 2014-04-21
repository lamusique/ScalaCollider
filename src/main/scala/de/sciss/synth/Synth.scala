/*
 *  Synth.scala
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

object Synth {
  def apply(server: Server = Server.default): Synth = apply(server, server.nextNodeID())
}
final case class Synth(server: Server, id: Int)
  extends Node {

  private var defNameVar = ""

//  def this(server: Server) = this(server, server.nextNodeID())
//
//  def this() = this(Server.default)

  def newMsg(defName: String, target: Node = server.defaultGroup, args: Seq[ControlSet] = Nil,
             addAction: AddAction = addToHead) = {
    defNameVar = defName
    message.SynthNew(defName, id, addAction.id, target.id, args: _*)
  }

  def defName = defNameVar

  override def toString = "Synth(" + server + "," + id +
    (if (defNameVar != "") ") : <" + defNameVar + ">" else ")")
}