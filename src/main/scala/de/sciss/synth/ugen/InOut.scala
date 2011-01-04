/*
 * InOut.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Tue Jan 04 01:35:44 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object Out {
   def ar(bus: AnyGE, in: Expands[AnyGE]) = apply[audio](audio, bus, in)
   def kr(bus: AnyGE, in: Expands[AnyGE]) = apply[control](control, bus, in)
   def ir(bus: AnyGE, in: Expands[AnyGE]) = apply[scalar](scalar, bus, in)
}
case class Out[R <: Rate](rate: R, bus: AnyGE, in: Expands[AnyGE]) extends Expands[OutUGen[R]] {
   def expand = {
      val _bus: IIdxSeq[AnyUGenIn] = bus.expand
      val _in: IIdxSeq[AnyGE] = in.expand
      val _sz_bus = _bus.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_bus, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => OutUGen(rate, _bus(i.%(_sz_bus)), _in(i.%(_sz_in))))
   }
}
case class OutUGen[R <: Rate](rate: R, bus: AnyUGenIn, in: AnyGE) extends ZeroOutUGen(IIdxSeq[AnyUGenIn](bus).++(in.expand))