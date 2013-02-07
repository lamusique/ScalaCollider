package de.sciss.synth

import language.implicitConversions

object Optional {
  implicit def some[@specialized A](a: A) = Optional(Some(a))
  implicit def wrap  [A](a: Option[A])    = Optional(a)
  implicit def unwrap[A](a: Optional[A])  = a.option
}

final case class Optional[A](option: Option[A])
