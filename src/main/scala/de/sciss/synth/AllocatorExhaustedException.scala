package de.sciss.synth

final case class AllocatorExhaustedException( reason: String ) extends RuntimeException( reason )
