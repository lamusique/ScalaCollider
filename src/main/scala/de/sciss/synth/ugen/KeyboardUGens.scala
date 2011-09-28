/*
 * KeyboardUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Sep 28 23:54:52 CEST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object KeyState {
   def kr(keyCode: GE, lo: GE = 0.0f, hi: GE = 1.0f, lag: GE = 0.2f) = apply(keyCode, lo, hi, lag)
}
final case class KeyState(keyCode: GE, lo: GE, hi: GE, lag: GE) extends UGenSource.SingleOut("KeyState") with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(keyCode.expand, lo.expand, hi.expand, lag.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args)
}