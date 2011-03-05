/*
 * PhysicalModellingUGens.scala
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
object Spring {
   def kr(in: AnyGE, spring: AnyGE = 1.0f, damp: AnyGE = 0.0f) = apply[control](control, in, spring, damp)
   def ar(in: AnyGE, spring: AnyGE = 1.0f, damp: AnyGE = 0.0f) = apply[audio](audio, in, spring, damp)
}
final case class Spring[R <: Rate](rate: R, in: AnyGE, spring: AnyGE, damp: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, spring.expand, damp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Spring", rate, _args)
}
object Ball {
   def kr(in: AnyGE, g: AnyGE = 1.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[control](control, in, g, damp, friction)
   def ar(in: AnyGE, g: AnyGE = 1.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[audio](audio, in, g, damp, friction)
}
final case class Ball[R <: Rate](rate: R, in: AnyGE, g: AnyGE, damp: AnyGE, friction: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, g.expand, damp.expand, friction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Ball", rate, _args)
}
object TBall {
   def kr(in: AnyGE, g: AnyGE = 10.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[control](control, in, g, damp, friction)
   def ar(in: AnyGE, g: AnyGE = 10.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[audio](audio, in, g, damp, friction)
}
final case class TBall[R <: Rate](rate: R, in: AnyGE, g: AnyGE, damp: AnyGE, friction: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, g.expand, damp.expand, friction.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("TBall", rate, _args)
}