package de.sciss.synth

import de.sciss.osc.OSCPacket

case class Completion[ T ]( message: Option[ T => OSCPacket ], action: Option[ T => Unit ])
