package de.sciss.synth

import de.sciss.osc.Packet
import language.implicitConversions

object Completion {
  implicit def fromPacket[T](p: Packet): Completion[T]        = Completion[T](Some((_: T) => p), None) // message[T]( msg )
  implicit def fromFunction[T](fun: T => Unit): Completion[T] = Completion[T](None, Some(fun))
}
final case class Completion[T](message: Option[T => Packet], action: Option[T => Unit])
