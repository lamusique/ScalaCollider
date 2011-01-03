/*
 * InOut.scala
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
object Out {
   def ar(bus: GE[AnyUGenIn], in: Expands[GE[AnyUGenIn]]) = apply[audio](audio, bus, in)
   def kr(bus: GE[AnyUGenIn], in: Expands[GE[AnyUGenIn]]) = apply[control](control, bus, in)
   def ir(bus: GE[AnyUGenIn], in: Expands[GE[AnyUGenIn]]) = apply[scalar](scalar, bus, in)
}
case class Out[R <: Rate](rate: R, bus: GE[AnyUGenIn], in: Expands[GE[AnyUGenIn]]) extends Expands[OutUGen[R]] {
   def expand = {
      val _bus = bus.expand
      val _in = in.expand
      val _sz_bus = _bus.size
      val _sz_in = _in.size
      val _exp_ = max(_sz_bus, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => OutUGen(rate, _bus(i.%(_sz_bus)), _in(i.%(_sz_in))))
   }
}
case class OutUGen[R <: Rate](rate: R, bus: AnyUGenIn, in: GE[AnyUGenIn]) extends ZeroOutUGen(in.expand.+:(bus))