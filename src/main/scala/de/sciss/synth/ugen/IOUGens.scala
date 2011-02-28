/*
 * IOUGens.scala
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
//object OffsetOut {
//   def ar(bus: AnyGE, in: Multi[GE[UGenIn]]) = apply(bus, in)
//}
//case class OffsetOut(bus: AnyGE, in: Multi[AnyGE]) extends ZeroOutUGenSource[OffsetOutUGen] with AudioRated with WritesBus {
//   protected def expandUGens = {
//      val _bus: IIdxSeq[UGenIn] = bus.expand
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      val _sz_bus = _bus.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_bus, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => OffsetOutUGen(_bus(i.%(_sz_bus)), _in(i.%(_sz_in)).expand))
//   }
//}
//case class OffsetOutUGen(bus: UGenIn, in: IIdxSeq[UGenIn]) extends ZeroOutUGen(IIdxSeq[UGenIn](bus).++(in)) with AudioRated with WritesBus
//object LocalIn {
//   def kr: LocalIn = kr()
//   def kr(numChannels: Int = 1) = apply(control, numChannels)
//   def ar: LocalIn = ar()
//   def ar(numChannels: Int = 1) = apply(audio, numChannels)
//}
//case class LocalIn(rate: Rate, numChannels: Int) extends MultiOutUGenSource[LocalInUGen] {
//   protected def expandUGens = IIdxSeq(LocalInUGen(rate, numChannels))
//}
//case class LocalInUGen(rate: Rate, numChannels: Int) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq.empty)
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//object XOut {
//   def ar(bus: AnyGE, in: Multi[GE[UGenIn]], xfade: AnyGE) = apply(audio, bus, in, xfade)
//   def kr(bus: AnyGE, in: Multi[AnyGE], xfade: AnyGE) = apply(control, bus, in, xfade)
//}
///**
// * '''Warning''': The argument order is different from its sclang counterpart.
// */
//case class XOut(rate: Rate, bus: AnyGE, in: Multi[AnyGE], xfade: AnyGE) extends ZeroOutUGenSource[XOutUGen] with WritesBus {
//   protected def expandUGens = {
//      val _bus: IIdxSeq[UGenIn] = bus.expand
//      val _xfade: IIdxSeq[UGenIn] = xfade.expand
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      val _sz_bus = _bus.size
//      val _sz_xfade = _xfade.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_bus, _sz_xfade, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => XOutUGen(rate, _bus(i.%(_sz_bus)), _in(i.%(_sz_in)).expand, _xfade(i.%(_sz_xfade))))
//   }
//}
//case class XOutUGen(rate: Rate, bus: UGenIn, in: IIdxSeq[UGenIn], xfade: UGenIn) extends ZeroOutUGen(IIdxSeq[UGenIn](bus, xfade).++(in)) with WritesBus
//object ReplaceOut {
//   def ar(bus: AnyGE, in: Multi[GE[UGenIn]]) = apply(bus, in)
//}
//case class ReplaceOut(bus: AnyGE, in: Multi[AnyGE]) extends ZeroOutUGenSource[ReplaceOutUGen] with AudioRated with WritesBus {
//   protected def expandUGens = {
//      val _bus: IIdxSeq[UGenIn] = bus.expand
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      val _sz_bus = _bus.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_bus, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => ReplaceOutUGen(_bus(i.%(_sz_bus)), _in(i.%(_sz_in)).expand))
//   }
//}
//case class ReplaceOutUGen(bus: UGenIn, in: IIdxSeq[UGenIn]) extends ZeroOutUGen(IIdxSeq[UGenIn](bus).++(in)) with AudioRated with WritesBus
object Out {
   def ar(bus: AnyGE, in: Multi[audio, GE[audio, UGenIn]]) = apply(audio, bus, in)
   def kr(bus: AnyGE, in: AnyMulti) = apply(control, bus, in)
   def ir(bus: AnyGE, in: AnyMulti) = apply(scalar, bus, in)
}
case class Out[ R <: Rate ](rate: R, bus: AnyGE, in: AnyMulti ) extends ZeroOutUGenSource[OutUGen] with WritesBus {
   protected def expandUGens = {
      val _bus: IIdxSeq[UGenIn] = bus.expand
      val _in: IIdxSeq[AnyGE] = in.mexpand
      val _sz_bus = _bus.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_bus, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => OutUGen(rate, _bus(i.%(_sz_bus)), _in(i.%(_sz_in)).expand))
   }
}
case class OutUGen(rate: Rate, bus: UGenIn, in: IIdxSeq[UGenIn]) extends ZeroOutUGen(IIdxSeq[UGenIn](bus).++(in)) with WritesBus
//object LocalOut {
//   def kr(in: Multi[AnyGE]) = apply(control, in)
//   def ar(in: Multi[GE[UGenIn]]) = apply(audio, in)
//}
//case class LocalOut(rate: Rate, in: Multi[AnyGE]) extends ZeroOutUGenSource[LocalOutUGen] {
//   protected def expandUGens = {
//      val _in: IIdxSeq[AnyGE] = in.mexpand
//      IIdxSeq.tabulate(_in.size)(i => LocalOutUGen(rate, _in(i).expand))
//   }
//}
//case class LocalOutUGen(rate: Rate, in: IIdxSeq[UGenIn]) extends ZeroOutUGen(in)
object In {
   def ir(bus: AnyGE, numChannels: Int = 1) = apply[scalar](scalar, bus, numChannels)
   def kr(bus: AnyGE, numChannels: Int = 1) = apply[control](control, bus, numChannels)
   def ar(bus: AnyGE, numChannels: Int = 1) = apply[audio](audio, bus, numChannels)
}
case class In[ R <: Rate ](rate: R, bus: AnyGE, numChannels: Int) extends MultiOutUGenSource[R, InUGen[ R ]] {
   protected def expandUGens = {
      val _bus: IIdxSeq[UGenIn] = bus.expand
      IIdxSeq.tabulate(_bus.size)(i => InUGen[ R ](rate, _bus(i), numChannels))
   }
}
case class InUGen[ R <: Rate ](rate: R, bus: UGenIn, numChannels: Int) extends MultiOutUGen[ R ](IIdxSeq.fill(numChannels)(rate), IIdxSeq(bus))
//object LagIn {
//   def kr(bus: AnyGE, numChannels: Int = 1, lag: AnyGE = 0.1f) = apply(control, bus, numChannels, lag)
//}
//case class LagIn(rate: Rate, bus: AnyGE, numChannels: Int, lag: AnyGE) extends MultiOutUGenSource[LagInUGen] {
//   protected def expandUGens = {
//      val _bus: IIdxSeq[UGenIn] = bus.expand
//      val _lag: IIdxSeq[UGenIn] = lag.expand
//      val _sz_bus = _bus.size
//      val _sz_lag = _lag.size
//      val _exp_ = maxInt(_sz_bus, _sz_lag)
//      IIdxSeq.tabulate(_exp_)(i => LagInUGen(rate, _bus(i.%(_sz_bus)), numChannels, _lag(i.%(_sz_lag))))
//   }
//}
//case class LagInUGen(rate: Rate, bus: UGenIn, numChannels: Int, lag: UGenIn) extends MultiOutUGen(IIdxSeq.fill(numChannels)(rate), IIdxSeq(bus, lag))
///**
// * A UGen which reads a signal from an audio bus with a current or one cycle old timestamp
// *
// * Audio buses adhere to the concept of a cycle timestamp, which increases for each audio block
// * calculated. When the various output ugens (`Out`, `OffsetOut`, `XOut`) write data to a bus,
// * they mix it with any data from the current cycle, but overwrite any data from the previous cycle.
// * (`ReplaceOut` overwrites all data regardless.) Thus depending on node order and what synths are
// * writing to the bus, the data on a given bus may be from the current cycle or be one cycle old at
// * the time of reading.
// *
// * `In.ar` checks the timestamp of any data it reads in and zeros any data from the previous
// * cycle (for use within that node; the data remains on the bus). This is fine for audio data,
// * as it avoids feedback, but for control data it is useful to be able to read data from any place
// * in the node order. For this reason `In.kr` also reads data that is older than the current cycle.
// *
// * In some cases one might also want to read audio from a node later in the current node order.
// * This can be achieved with `InFeedback`. It reads from the previous cycle, and hence introduces
// * a '''delay''' of one block size, which by default is 64 sample frames (equal to about 1.45 ms
// * at 44.1 kHz sample rate).
// *
// * @see [[de.sciss.synth.ugen.In]]
// * @see [[de.sciss.synth.ugen.LocalIn]]
// * @see [[de.sciss.synth.ugen.ControlDur]]
// */
//object InFeedback {
//
///**
// * @param bus             the index of the audio bus to read in from.
// * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
// *                        this is a constant, a change in number of channels of the underlying bus must
// *                        be reflected by creating different SynthDefs.
// */
//def ar(bus: AnyGE, numChannels: Int = 1) = apply(bus, numChannels)
//}
///**
// * A UGen which reads a signal from an audio bus with a current or one cycle old timestamp
// *
// * Audio buses adhere to the concept of a cycle timestamp, which increases for each audio block
// * calculated. When the various output ugens (`Out`, `OffsetOut`, `XOut`) write data to a bus,
// * they mix it with any data from the current cycle, but overwrite any data from the previous cycle.
// * (`ReplaceOut` overwrites all data regardless.) Thus depending on node order and what synths are
// * writing to the bus, the data on a given bus may be from the current cycle or be one cycle old at
// * the time of reading.
// *
// * `In.ar` checks the timestamp of any data it reads in and zeros any data from the previous
// * cycle (for use within that node; the data remains on the bus). This is fine for audio data,
// * as it avoids feedback, but for control data it is useful to be able to read data from any place
// * in the node order. For this reason `In.kr` also reads data that is older than the current cycle.
// *
// * In some cases one might also want to read audio from a node later in the current node order.
// * This can be achieved with `InFeedback`. It reads from the previous cycle, and hence introduces
// * a '''delay''' of one block size, which by default is 64 sample frames (equal to about 1.45 ms
// * at 44.1 kHz sample rate).
// *
// * @param bus             the index of the audio bus to read in from.
// * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
// *                        this is a constant, a change in number of channels of the underlying bus must
// *                        be reflected by creating different SynthDefs.
// *
// * @see [[de.sciss.synth.ugen.In]]
// * @see [[de.sciss.synth.ugen.LocalIn]]
// * @see [[de.sciss.synth.ugen.ControlDur]]
// */
//case class InFeedback(bus: AnyGE, numChannels: Int) extends MultiOutUGenSource[InFeedbackUGen] with AudioRated {
//   protected def expandUGens = {
//      val _bus: IIdxSeq[UGenIn] = bus.expand
//      IIdxSeq.tabulate(_bus.size)(i => InFeedbackUGen(_bus(i), numChannels))
//   }
//}
//case class InFeedbackUGen(bus: UGenIn, numChannels: Int) extends MultiOutUGen(IIdxSeq.fill(numChannels)(audio), IIdxSeq(bus)) with AudioRated
///**
// * A UGen which generates a trigger anytime a control bus is set.
// *
// * Any time the bus is "touched" i.e. has its value set (using `"/c_set"` etc.), a single
// * impulse trigger will be generated. Its amplitude is the value that the bus was set to.
// * Note that if a signal is continuously written to that bus, for instance using
// * `Out.kr`, only one initial trigger is generated once that ugen starts writing, but
// * no successive triggers are generated.
// *
// * @see [[de.sciss.synth.ugen.In]]
// */
//object InTrig {
//
///**
// * @param bus             the index of the control bus to read in from.
// * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
// *                        this is a constant, a change in number of channels of the underlying bus must
// *                        be reflected by creating different SynthDefs.
// */
//def kr(bus: AnyGE, numChannels: Int = 1) = apply(bus, numChannels)
//}
///**
// * A UGen which generates a trigger anytime a control bus is set.
// *
// * Any time the bus is "touched" i.e. has its value set (using `"/c_set"` etc.), a single
// * impulse trigger will be generated. Its amplitude is the value that the bus was set to.
// * Note that if a signal is continuously written to that bus, for instance using
// * `Out.kr`, only one initial trigger is generated once that ugen starts writing, but
// * no successive triggers are generated.
// *
// * @param bus             the index of the control bus to read in from.
// * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
// *                        this is a constant, a change in number of channels of the underlying bus must
// *                        be reflected by creating different SynthDefs.
// *
// * @see [[de.sciss.synth.ugen.In]]
// */
//case class InTrig(bus: AnyGE, numChannels: Int) extends MultiOutUGenSource[InTrigUGen] with ControlRated {
//   protected def expandUGens = {
//      val _bus: IIdxSeq[UGenIn] = bus.expand
//      IIdxSeq.tabulate(_bus.size)(i => InTrigUGen(_bus(i), numChannels))
//   }
//}
//case class InTrigUGen(bus: UGenIn, numChannels: Int) extends MultiOutUGen(IIdxSeq.fill(numChannels)(control), IIdxSeq(bus)) with ControlRated