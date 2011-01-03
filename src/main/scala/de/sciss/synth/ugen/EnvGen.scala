/*
 * EnvGen.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Sun Jan 02 21:13:58 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import SynthGraph._
object Done {
   def kr(src: GE[AnyUGenIn with HasDoneFlag]) = apply(src)
}
case class Done(src: GE[AnyUGenIn with HasDoneFlag]) extends GE[DoneUGen] with ControlRated {
   def expand = {
      val _src = src.expand
      val _sz_src = _src.size
      val _exp_ = max(_sz_src)
      IIdxSeq.tabulate(_exp_)(i => DoneUGen(_src(i.%(_sz_src))))
   }
}
case class DoneUGen(src: AnyUGenIn with HasDoneFlag) extends SingleOutUGen[control](IIdxSeq(src)) with ControlRated with HasSideEffect