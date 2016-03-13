/*
 *  Optional.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2016 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v2+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

import language.implicitConversions

object Optional {
  implicit def some[@specialized A](a: A) = Optional(Some(a))
  implicit def wrap  [A](a: Option[A])    = Optional(a)
  implicit def unwrap[A](a: Optional[A])  = a.option
}

final case class Optional[A](option: Option[A])
