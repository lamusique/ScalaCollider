/*
 *  AllocatorExhausted.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2015 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v2+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

final case class AllocatorExhausted(reason: String) extends RuntimeException(reason)
