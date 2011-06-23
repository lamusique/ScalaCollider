/*
 * TestUGens.scala
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
   def ir(in: GE, id: GE = 0.0f, post: GE = 2.0f) = apply(scalar, in, id, post)
   /**
    * @param in              the signal to be tested
    * @param id              an identifier showing up with the values in the console
    * @param post            One of three post modes: 0 = no posting; 1 = post a line for every bad value;
    *                        2 = post a line only when the floating-point classification changes (e.g., normal -> NaN and vice versa)
    */
   def kr(in: GE, id: GE = 0.0f, post: GE = 2.0f) = apply(control, in, id, post)
   /**
    * @param in              the signal to be tested
    * @param id              an identifier showing up with the values in the console
    * @param post            One of three post modes: 0 = no posting; 1 = post a line for every bad value;
    *                        2 = post a line only when the floating-point classification changes (e.g., normal -> NaN and vice versa)
    */
   def ar(in: GE, id: GE = 0.0f, post: GE = 2.0f) = apply(audio, in, id, post)
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
final case class CheckBadValues(rate: Rate, in: GE, id: GE, post: GE) extends UGenSource.SingleOut("CheckBadValues") with HasSideEffect {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, id.expand, post.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}