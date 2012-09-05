/*
 * MouseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * ScalaCollider-UGens version: 1.0.0
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._

object MouseX {
   def kr: MouseX = kr()
   
   def kr(lo: GE = 0.0f, hi: GE = 1.0f, warp: GE = 0.0f, lag: GE = 0.2f) = apply(lo, hi, warp, lag)
}

final case class MouseX(lo: GE, hi: GE, warp: GE, lag: GE) extends UGenSource.SingleOut("MouseX") with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, warp.expand, lag.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args)
}

object MouseY {
   def kr: MouseY = kr()
   
   def kr(lo: GE = 0.0f, hi: GE = 1.0f, warp: GE = 0.0f, lag: GE = 0.2f) = apply(lo, hi, warp, lag)
}

final case class MouseY(lo: GE, hi: GE, warp: GE, lag: GE) extends UGenSource.SingleOut("MouseY") with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, warp.expand, lag.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args)
}

object MouseButton {
   def kr: MouseButton = kr()
   
   def kr(lo: GE = 0.0f, hi: GE = 1.0f, lag: GE = 0.2f) = apply(lo, hi, lag)
}

final case class MouseButton(lo: GE, hi: GE, lag: GE) extends UGenSource.SingleOut("MouseButton") with ControlRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(lo.expand, hi.expand, lag.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, control, _args)
}