/*
 * MouseUGens.scala
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
object MouseX {
   def kr: MouseX = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
final case class MouseX(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends UGenSource.SingleOut[control] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, warp.expand, lag.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MouseX", control, _args)
}
object MouseY {
   def kr: MouseY = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
final case class MouseY(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends UGenSource.SingleOut[control] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, warp.expand, lag.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MouseY", control, _args)
}
object MouseButton {
   def kr: MouseButton = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(lo, hi, lag)
}
final case class MouseButton(lo: AnyGE, hi: AnyGE, lag: AnyGE) extends UGenSource.SingleOut[control] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, lag.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("MouseButton", control, _args)
}