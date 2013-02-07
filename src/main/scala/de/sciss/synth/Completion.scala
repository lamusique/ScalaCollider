package de.sciss.synth

import de.sciss.osc.Packet
import language.implicitConversions

object Completion {
  implicit def fromPacket[T](p: Packet) = Completion[T](Some((_: T) => p), None) // message[T]( msg )
}
final case class Completion[T](message: Option[T => Packet], action: Option[T => Unit])
