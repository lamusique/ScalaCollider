/*
 * KeyboardUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Mar 04 23:36:58 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object KeyState {
   def kr(keyCode: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(keyCode, lo, hi, lag)
}
final case class KeyState(keyCode: AnyGE, lo: AnyGE, hi: AnyGE, lag: AnyGE) extends UGenSource.SingleOut[control] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(keyCode.expand, lo.expand, hi.expand, lag.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("KeyState", control, _args)
}