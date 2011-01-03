/*
 * EnvGen.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Mon Jan 03 21:35:10 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import SynthGraph._
object Done {
   def kr(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) = apply(src)
}
case class Done(src: GE[R, UGenIn[R] with HasDoneFlag] forSome { type R <: Rate }) extends GE[control, DoneUGen] with ControlRated {
   def expand = {
      val _src: IIdxSeq[AnyUGenIn with HasDoneFlag] = src.expand
      IIdxSeq.tabulate(_src.size)(i => DoneUGen(_src(i)))
   }
}
case class DoneUGen(src: AnyUGenIn with HasDoneFlag) extends SingleOutUGen[control](IIdxSeq(src)) with ControlRated with HasSideEffect