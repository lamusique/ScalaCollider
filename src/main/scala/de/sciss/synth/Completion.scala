/*
 *  Completion.scala
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

import de.sciss.osc.Packet
import language.implicitConversions

object Completion {
  implicit def fromPacket[T](p: Packet): Completion[T]        = Completion[T](Some((_: T) => p), None) // message[T]( msg )
  implicit def fromFunction[T](fun: T => Unit): Completion[T] = Completion[T](None, Some(fun))
}
final case class Completion[T](message: Option[T => Packet], action: Option[T => Unit])
