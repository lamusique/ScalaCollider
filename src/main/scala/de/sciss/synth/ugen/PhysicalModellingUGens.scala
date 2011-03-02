/*
 * PhysicalModellingUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import util.UGenHelper._
object Spring {
   def kr(in: AnyGE, spring: AnyGE = 1.0f, damp: AnyGE = 0.0f) = apply[control](control, in, spring, damp)
   def ar(in: AnyGE, spring: AnyGE = 1.0f, damp: AnyGE = 0.0f) = apply[audio](audio, in, spring, damp)
}
final case class Spring[R <: Rate](rate: R, in: AnyGE, spring: AnyGE, damp: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _spring = spring.expand
      val _damp = damp.expand
      val _sz_in = _in.size
      val _sz_spring = _spring.size
      val _sz_damp = _damp.size
      val _exp_ = maxInt(_sz_in, _sz_spring, _sz_damp)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Spring", rate, IIdxSeq(_in(i.%(_sz_in)), _spring(i.%(_sz_spring)), _damp(i.%(_sz_damp)))))
   }
}
object Ball {
   def kr(in: AnyGE, g: AnyGE = 1.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[control](control, in, g, damp, friction)
   def ar(in: AnyGE, g: AnyGE = 1.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[audio](audio, in, g, damp, friction)
}
final case class Ball[R <: Rate](rate: R, in: AnyGE, g: AnyGE, damp: AnyGE, friction: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _g = g.expand
      val _damp = damp.expand
      val _friction = friction.expand
      val _sz_in = _in.size
      val _sz_g = _g.size
      val _sz_damp = _damp.size
      val _sz_friction = _friction.size
      val _exp_ = maxInt(_sz_in, _sz_g, _sz_damp, _sz_friction)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Ball", rate, IIdxSeq(_in(i.%(_sz_in)), _g(i.%(_sz_g)), _damp(i.%(_sz_damp)), _friction(i.%(_sz_friction)))))
   }
}
object TBall {
   def kr(in: AnyGE, g: AnyGE = 10.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[control](control, in, g, damp, friction)
   def ar(in: AnyGE, g: AnyGE = 10.0f, damp: AnyGE = 0.0f, friction: AnyGE = 0.01f) = apply[audio](audio, in, g, damp, friction)
}
final case class TBall[R <: Rate](rate: R, in: AnyGE, g: AnyGE, damp: AnyGE, friction: AnyGE) extends SingleOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.expand
      val _g = g.expand
      val _damp = damp.expand
      val _friction = friction.expand
      val _sz_in = _in.size
      val _sz_g = _g.size
      val _sz_damp = _damp.size
      val _sz_friction = _friction.size
      val _exp_ = maxInt(_sz_in, _sz_g, _sz_damp, _sz_friction)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("TBall", rate, IIdxSeq(_in(i.%(_sz_in)), _g(i.%(_sz_g)), _damp(i.%(_sz_damp)), _friction(i.%(_sz_friction)))))
   }
}