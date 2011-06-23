/*
 * KeyboardUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Jun 24 00:20:25 BST 2011
 * ScalaCollider-UGen version: 0.12
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