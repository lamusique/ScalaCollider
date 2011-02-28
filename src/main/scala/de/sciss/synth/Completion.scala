package de.sciss.synth

import de.sciss.osc.OSCPacket

object Completion {
   implicit def fromPacket[T]( msg: OSCPacket ) = Completion[T]( Some( (_: T) => msg ), None ) // message[T]( msg )
}
case class Completion[ T ]( message: Option[ T => OSCPacket ], action: Option[ T => Unit ])
