/*
 * TestUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 27 20:56:40 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
/**
 * A UGen to test for infinity, not-a-number (NaN), and denormals.
 * Its output is as follows: 0 = a normal float, 1 = NaN, 2 = infinity, and 3 = a denormal.
 * According to the post settings it will print the information to the console along
 * with a given identifier.
 */
object CheckBadValues {
   
/**
 * @param in              the signal to be tested
 * @param id              an identifier showing up with the values in the console
 * @param post            One of three post modes: 0 = no posting; 1 = post a line for every bad value;
 *                        2 = post a line only when the floating-point classification changes (e.g., normal -> NaN and vice versa)
 */
def ir(in: AnyGE, id: AnyGE = 0.0f, post: AnyGE = 2.0f) = apply[scalar](scalar, in, id, post)
/**
 * @param in              the signal to be tested
 * @param id              an identifier showing up with the values in the console
 * @param post            One of three post modes: 0 = no posting; 1 = post a line for every bad value;
 *                        2 = post a line only when the floating-point classification changes (e.g., normal -> NaN and vice versa)
 */
def kr(in: AnyGE, id: AnyGE = 0.0f, post: AnyGE = 2.0f) = apply[control](control, in, id, post)
/**
 * @param in              the signal to be tested
 * @param id              an identifier showing up with the values in the console
 * @param post            One of three post modes: 0 = no posting; 1 = post a line for every bad value;
 *                        2 = post a line only when the floating-point classification changes (e.g., normal -> NaN and vice versa)
 */
def ar(in: AnyGE, id: AnyGE = 0.0f, post: AnyGE = 2.0f) = apply[audio](audio, in, id, post)
}
/**
 * A UGen to test for infinity, not-a-number (NaN), and denormals.
 * Its output is as follows: 0 = a normal float, 1 = NaN, 2 = infinity, and 3 = a denormal.
 * According to the post settings it will print the information to the console along
 * with a given identifier.
 * 
 * @param in              the signal to be tested
 * @param id              an identifier showing up with the values in the console
 * @param post            One of three post modes: 0 = no posting; 1 = post a line for every bad value;
 *                        2 = post a line only when the floating-point classification changes (e.g., normal -> NaN and vice versa)
 */
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