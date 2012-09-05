/*
 * PhysicalModellingUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * ScalaCollider-UGens version: 1.0.0
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._

object Spring {
   def kr(in: GE, spring: GE = 1.0f, damp: GE = 0.0f) = apply(control, in, spring, damp)
   
   def ar(in: GE, spring: GE = 1.0f, damp: GE = 0.0f) = apply(audio, in, spring, damp)
}

final case class Spring(rate: Rate, in: GE, spring: GE, damp: GE) extends UGenSource.SingleOut("Spring") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, spring.expand, damp.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

object Ball {
   def kr(in: GE, g: GE = 1.0f, damp: GE = 0.0f, friction: GE = 0.01f) = apply(control, in, g, damp, friction)
   
   def ar(in: GE, g: GE = 1.0f, damp: GE = 0.0f, friction: GE = 0.01f) = apply(audio, in, g, damp, friction)
}

final case class Ball(rate: Rate, in: GE, g: GE, damp: GE, friction: GE) extends UGenSource.SingleOut("Ball") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, g.expand, damp.expand, friction.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

object TBall {
   def kr(in: GE, g: GE = 10.0f, damp: GE = 0.0f, friction: GE = 0.01f) = apply(control, in, g, damp, friction)
   
   def ar(in: GE, g: GE = 10.0f, damp: GE = 0.0f, friction: GE = 0.01f) = apply(audio, in, g, damp, friction)
}

final case class TBall(rate: Rate, in: GE, g: GE, damp: GE, friction: GE) extends UGenSource.SingleOut("TBall") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, g.expand, damp.expand, friction.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}