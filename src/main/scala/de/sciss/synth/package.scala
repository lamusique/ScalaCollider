/*
 *  package.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss

import language.implicitConversions

package object synth /* extends de.sciss.synth.LowPriorityImplicits */ /* with de.sciss.synth.RateRelations */ {

  final val inf = Float.PositiveInfinity

  /** This conversion is particularly important to balance priorities,
    * as the plain pair of `intToGE` and `enrichFloat` have equal
    * priorities for an Int despite being in sub/superclass relationship,
    * probably due to the numeric widening which would be needed.
    *
    * Note that we use the same name as scala.Predef.intWrapper. That
    * way the original conversion is hidden!
    */
  implicit def intWrapper(i: Int): RichInt = new RichInt(i)

  /** Note that we use the same name as scala.Predef.floatWrapper. That
    * way the original conversion is hidden!
    */
  implicit def floatWrapper(f: Float): RichFloat = new RichFloat(f)

  /** Note that we use the same name as scala.Predef.doubleWrapper. That
    * way the original conversion is hidden!
    */
  implicit def doubleWrapper(d: Double): RichDouble = new RichDouble(d)

  implicit def geOps(g: GE): GEOps = new GEOps(g)

  // pimping. XXX TODO: ControlProxyFactory could be implicit class?
  implicit def stringToControlProxyFactory(name: String): ugen.ControlProxyFactory = new ugen.ControlProxyFactory(name)

  // implicit def messageToOption(msg: Packet): Option[Packet] = Some(msg)

  // explicit methods

  def play[T: GraphFunction.Result](thunk: => T): Synth = play()(thunk)

  // XXX TODO: fadeTime should be Optional[ Double ] not Option[ Float ]
  def play[T: GraphFunction.Result](target: Node = Server.default, outBus: Int = 0,
                                    fadeTime: Optional[Float] = Some(0.02f),
                                    addAction: AddAction = addToHead)(thunk: => T): Synth = {
    val fun = new GraphFunction[T](thunk)
    fun.play(target, outBus, fadeTime, addAction)
  }
}