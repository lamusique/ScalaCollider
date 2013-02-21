package de.sciss.synth

final case class AllocatorExhausted(reason: String) extends RuntimeException(reason)
