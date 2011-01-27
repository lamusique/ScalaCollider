/*
 * PhysicalModellingUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 27 23:03:33 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object Spring {
   def kr(in: AnyGE, spring: AnyGE = 1.0f, damp: AnyGE = 0.0f) = apply(control, in, spring, damp)
   def ar(in: AnyGE, spring: AnyGE = 1.0f, damp: AnyGE = 0.0f) = apply(audio, in, spring, damp)
}
case class Spring(rate: Rate, in: AnyGE, spring: AnyGE, damp: AnyGE) extends SingleOutUGenSource[SpringUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _spring: IIdxSeq[UGenIn] = spring.expand
      val _damp: IIdxSeq[UGenIn] = damp.expand
      val _sz_in = _in.size
      val _sz_spring = _spring.size
      val _sz_damp = _damp.size
      val _exp_ = maxInt(_sz_in, _sz_spring, _sz_damp)
      IIdxSeq.tabulate(_exp_)(i => SpringUGen(rate, _in(i.%(_sz_in)), _spring(i.%(_sz_spring)), _damp(i.%(_sz_damp))))
   }
}
case class SpringUGen(rate: Rate, in: UGenIn, spring: UGenIn, damp: UGenIn) extends SingleOutUGen(IIdxSeq(in, spring, damp))
object Ball {
   def kr(in: AnyGE, g: AnyGE = 1.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply(control, in, g, damp, friction)
   def ar(in: AnyGE, g: AnyGE = 1.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply(audio, in, g, damp, friction)
}
case class Ball(rate: Rate, in: AnyGE, g: AnyGE, damp: AnyGE, friction: AnyGE) extends SingleOutUGenSource[BallUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _g: IIdxSeq[UGenIn] = g.expand
      val _damp: IIdxSeq[UGenIn] = damp.expand
      val _friction: IIdxSeq[UGenIn] = friction.expand
      val _sz_in = _in.size
      val _sz_g = _g.size
      val _sz_damp = _damp.size
      val _sz_friction = _friction.size
      val _exp_ = maxInt(_sz_in, _sz_g, _sz_damp, _sz_friction)
      IIdxSeq.tabulate(_exp_)(i => BallUGen(rate, _in(i.%(_sz_in)), _g(i.%(_sz_g)), _damp(i.%(_sz_damp)), _friction(i.%(_sz_friction))))
   }
}
case class BallUGen(rate: Rate, in: UGenIn, g: UGenIn, damp: UGenIn, friction: UGenIn) extends SingleOutUGen(IIdxSeq(in, g, damp, friction))
object TBall {
   def kr(in: AnyGE, g: AnyGE = 10.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply(control, in, g, damp, friction)
   def ar(in: AnyGE, g: AnyGE = 10.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply(audio, in, g, damp, friction)
}
case class TBall(rate: Rate, in: AnyGE, g: AnyGE, damp: AnyGE, friction: AnyGE) extends SingleOutUGenSource[TBallUGen] {
   protected def expandUGens = {
      val _in: IIdxSeq[UGenIn] = in.expand
      val _g: IIdxSeq[UGenIn] = g.expand
      val _damp: IIdxSeq[UGenIn] = damp.expand
      val _friction: IIdxSeq[UGenIn] = friction.expand
      val _sz_in = _in.size
      val _sz_g = _g.size
      val _sz_damp = _damp.size
      val _sz_friction = _friction.size
      val _exp_ = maxInt(_sz_in, _sz_g, _sz_damp, _sz_friction)
      IIdxSeq.tabulate(_exp_)(i => TBallUGen(rate, _in(i.%(_sz_in)), _g(i.%(_sz_g)), _damp(i.%(_sz_damp)), _friction(i.%(_sz_friction))))
   }
}
case class TBallUGen(rate: Rate, in: UGenIn, g: UGenIn, damp: UGenIn, friction: UGenIn) extends SingleOutUGen(IIdxSeq(in, g, damp, friction))