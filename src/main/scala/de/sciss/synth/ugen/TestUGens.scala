/*
 * TestUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Sun Jan 09 18:05:58 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object CheckBadValues {
   def ir(in: AnyGE, id: AnyGE = 0.0f, post: AnyGE = 2.0f) = apply[scalar](scalar, in, id, post)
   def kr(in: AnyGE, id: AnyGE = 0.0f, post: AnyGE = 2.0f) = apply[control](control, in, id, post)
   def ar(in: AnyGE, id: AnyGE = 0.0f, post: AnyGE = 2.0f) = apply[audio](audio, in, id, post)
}
case class CheckBadValues[R <: Rate](rate: R, in: AnyGE, id: AnyGE, post: AnyGE) extends SingleOutUGenSource[R, CheckBadValuesUGen[R]] with HasSideEffect {
   protected def expandUGens = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _id: IIdxSeq[AnyUGenIn] = id.expand
      val _post: IIdxSeq[AnyUGenIn] = post.expand
      val _sz_in = _in.size
      val _sz_id = _id.size
      val _sz_post = _post.size
      val _exp_ = maxInt(_sz_in, _sz_id, _sz_post)
      IIdxSeq.tabulate(_exp_)(i => CheckBadValuesUGen(rate, _in(i.%(_sz_in)), _id(i.%(_sz_id)), _post(i.%(_sz_post))))
   }
}
case class CheckBadValuesUGen[R <: Rate](rate: R, in: AnyUGenIn, id: AnyUGenIn, post: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, id, post)) with HasSideEffect