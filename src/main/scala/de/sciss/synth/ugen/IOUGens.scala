/*
 * IOUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object OffsetOut {
   def ar(bus: AnyGE, in: Multi[GE[audio]]) = apply(bus, in)
}
final case class OffsetOut(bus: AnyGE, in: Multi[AnyGE]) extends ZeroOutUGenSource[audio] with WritesBus {
   protected def expandUGens = {
      val _bus = bus.expand
      val _in = in.mexpand
      val _sz_bus = _bus.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_bus, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new ZeroOutUGen("OffsetOut", audio, IIdxSeq(_bus(i.%(_sz_bus))).++(_in(i.%(_sz_in)).expand)))
   }
}
object LocalIn {
   def kr: LocalIn[control] = kr()
   def kr(numChannels: Int = 1) = apply[control](control, numChannels)
   def ar: LocalIn[audio] = ar()
   def ar(numChannels: Int = 1) = apply[audio](audio, numChannels)
}
final case class LocalIn[R <: Rate](rate: R, numChannels: Int) extends MultiOutUGenSource[R] {
   protected def expandUGens = IIdxSeq(new MultiOutUGen("LocalIn", rate, IIdxSeq.fill(numChannels)(rate), IIdxSeq.empty))
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object XOut {
   def ar(bus: AnyGE, in: Multi[GE[audio]], xfade: AnyGE) = apply[audio](audio, bus, in, xfade)
   def kr(bus: AnyGE, in: Multi[AnyGE], xfade: AnyGE) = apply[control](control, bus, in, xfade)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class XOut[R <: Rate](rate: R, bus: AnyGE, in: Multi[AnyGE], xfade: AnyGE) extends ZeroOutUGenSource[R] with WritesBus {
   protected def expandUGens = {
      val _bus = bus.expand
      val _xfade = xfade.expand
      val _in = in.mexpand
      val _sz_bus = _bus.size
      val _sz_xfade = _xfade.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_bus, _sz_xfade, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new ZeroOutUGen("XOut", rate, IIdxSeq(_bus(i.%(_sz_bus)), _xfade(i.%(_sz_xfade))).++(_in(i.%(_sz_in)).expand)))
   }
}
object ReplaceOut {
   def ar(bus: AnyGE, in: Multi[GE[audio]]) = apply(bus, in)
}
final case class ReplaceOut(bus: AnyGE, in: Multi[AnyGE]) extends ZeroOutUGenSource[audio] with WritesBus {
   protected def expandUGens = {
      val _bus = bus.expand
      val _in = in.mexpand
      val _sz_bus = _bus.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_bus, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new ZeroOutUGen("ReplaceOut", audio, IIdxSeq(_bus(i.%(_sz_bus))).++(_in(i.%(_sz_in)).expand)))
   }
}
object Out {
//   def ar(bus: AnyGE, in: Multi[GE[audio]])( implicit rateCons: RateCons[ audio, audio ]) = apply[audio, audio](audio, bus, in)
//   def kr[ S <: Rate ](bus: AnyGE, in: Multi[GE[S]])( implicit rateCons: RateCons[ control, S ]) = apply[control, S](control, bus, in)
//   def ir[ S <: Rate ](bus: AnyGE, in: Multi[GE[S]])( implicit rateCons: RateCons[ scalar, S ]) = apply[scalar, S](scalar, bus, in)

//   def ar(bus: AnyGE, in: Multi[GE[audio]]) = apply[audio, audio](audio, bus, in)( RateCons.ar )
//   def kr[ S <: Rate ](bus: AnyGE, in: Multi[GE[S]]) = apply[control, S](control, bus, in)( RateCons.kr[ S ])
//   def ir[ S <: Rate ](bus: AnyGE, in: Multi[GE[S]]) = apply[scalar, S](scalar, bus, in)( RateCons.ir[ S ])

   object RateCons {
      implicit val ar: RateCons[ audio, audio ]             = new RateCons[ audio, audio ]
      implicit def kr[ S <: Rate ]: RateCons[ control, S ]  = new RateCons[ control, S ]
      implicit def ir[ S <: Rate ]: RateCons[ scalar, S ]   = new RateCons[ scalar, S ]
   }
   sealed class RateCons[ R <: Rate, S <: Rate ]

   def ar(bus: AnyGE, in: Multi[GE[audio]]) = apply[audio, audio](audio, bus, in)
   def kr[ S <: Rate ](bus: AnyGE, in: Multi[GE[S]]) = apply[control, S](control, bus, in)
   def ir[ S <: Rate ](bus: AnyGE, in: Multi[GE[S]]) = apply[scalar, S](scalar, bus, in)
}
final case class Out[R <: Rate, S <: Rate](rate: R, bus: AnyGE, in: Multi[GE[S]])( implicit rateCons: Out.RateCons[ R, S ])
extends ZeroOutUGenSource[R] with WritesBus {
   protected def expandUGens = {
      val _bus = bus.expand
      val _in = in.mexpand
      val _sz_bus = _bus.size
      val _sz_in = _in.size
      val _exp_ = maxInt(_sz_bus, _sz_in)
      IIdxSeq.tabulate(_exp_)(i => new ZeroOutUGen("Out", rate, IIdxSeq(_bus(i.%(_sz_bus))).++(_in(i.%(_sz_in)).expand)))
   }
}
object LocalOut {
   def kr(in: Multi[AnyGE]) = apply[control](control, in)
   def ar(in: Multi[GE[audio]]) = apply[audio](audio, in)
}
final case class LocalOut[R <: Rate](rate: R, in: Multi[AnyGE]) extends ZeroOutUGenSource[R] {
   protected def expandUGens = {
      val _in = in.mexpand
      IIdxSeq.tabulate(_in.size)(i => new ZeroOutUGen("LocalOut", rate, _in(i).expand))
   }
}
object In {
   def ir(bus: AnyGE, numChannels: Int = 1) = apply[scalar](scalar, bus, numChannels)
   def kr(bus: AnyGE, numChannels: Int = 1) = apply[control](control, bus, numChannels)
   def ar(bus: AnyGE, numChannels: Int = 1) = apply[audio](audio, bus, numChannels)
}
final case class In[R <: Rate](rate: R, bus: AnyGE, numChannels: Int) extends MultiOutUGenSource[R] {
   protected def expandUGens = {
      val _bus = bus.expand
      IIdxSeq.tabulate(_bus.size)(i => new MultiOutUGen("In", rate, IIdxSeq.fill(numChannels)(rate), IIdxSeq(_bus(i))))
   }
}
object LagIn {
   def kr(bus: AnyGE, numChannels: Int = 1, lag: AnyGE = 0.1f) = apply[control](control, bus, numChannels, lag)
}
final case class LagIn[R <: Rate](rate: R, bus: AnyGE, numChannels: Int, lag: AnyGE) extends MultiOutUGenSource[R] {
   protected def expandUGens = {
      val _bus = bus.expand
      val _lag = lag.expand
      val _sz_bus = _bus.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_bus, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => new MultiOutUGen("LagIn", rate, IIdxSeq.fill(numChannels)(rate), IIdxSeq(_bus(i.%(_sz_bus)), _lag(i.%(_sz_lag)))))
   }
}
/**
 * A UGen which reads a signal from an audio bus with a current or one cycle old timestamp
 * 
 * Audio buses adhere to the concept of a cycle timestamp, which increases for each audio block
 * calculated. When the various output ugens (`Out`, `OffsetOut`, `XOut`) write data to a bus,
 * they mix it with any data from the current cycle, but overwrite any data from the previous cycle.
 * (`ReplaceOut` overwrites all data regardless.) Thus depending on node order and what synths are
 * writing to the bus, the data on a given bus may be from the current cycle or be one cycle old at
 * the time of reading.
 * 
 * `In.ar` checks the timestamp of any data it reads in and zeros any data from the previous
 * cycle (for use within that node; the data remains on the bus). This is fine for audio data,
 * as it avoids feedback, but for control data it is useful to be able to read data from any place
 * in the node order. For this reason `In.kr` also reads data that is older than the current cycle.
 * 
 * In some cases one might also want to read audio from a node later in the current node order.
 * This can be achieved with `InFeedback`. It reads from the previous cycle, and hence introduces
 * a '''delay''' of one block size, which by default is 64 sample frames (equal to about 1.45 ms
 * at 44.1 kHz sample rate).
 * 
 * @see [[de.sciss.synth.ugen.In]]
 * @see [[de.sciss.synth.ugen.LocalIn]]
 * @see [[de.sciss.synth.ugen.ControlDur]]
 */
object InFeedback {
   
   /**
    * @param bus             the index of the audio bus to read in from.
    * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
    *                        this is a constant, a change in number of channels of the underlying bus must
    *                        be reflected by creating different SynthDefs.
    */
   def ar(bus: AnyGE, numChannels: Int = 1) = apply(bus, numChannels)
}
/**
 * A UGen which reads a signal from an audio bus with a current or one cycle old timestamp
 * 
 * Audio buses adhere to the concept of a cycle timestamp, which increases for each audio block
 * calculated. When the various output ugens (`Out`, `OffsetOut`, `XOut`) write data to a bus,
 * they mix it with any data from the current cycle, but overwrite any data from the previous cycle.
 * (`ReplaceOut` overwrites all data regardless.) Thus depending on node order and what synths are
 * writing to the bus, the data on a given bus may be from the current cycle or be one cycle old at
 * the time of reading.
 * 
 * `In.ar` checks the timestamp of any data it reads in and zeros any data from the previous
 * cycle (for use within that node; the data remains on the bus). This is fine for audio data,
 * as it avoids feedback, but for control data it is useful to be able to read data from any place
 * in the node order. For this reason `In.kr` also reads data that is older than the current cycle.
 * 
 * In some cases one might also want to read audio from a node later in the current node order.
 * This can be achieved with `InFeedback`. It reads from the previous cycle, and hence introduces
 * a '''delay''' of one block size, which by default is 64 sample frames (equal to about 1.45 ms
 * at 44.1 kHz sample rate).
 * 
 * @param bus             the index of the audio bus to read in from.
 * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
 *                        this is a constant, a change in number of channels of the underlying bus must
 *                        be reflected by creating different SynthDefs.
 * 
 * @see [[de.sciss.synth.ugen.In]]
 * @see [[de.sciss.synth.ugen.LocalIn]]
 * @see [[de.sciss.synth.ugen.ControlDur]]
 */
final case class InFeedback(bus: AnyGE, numChannels: Int) extends MultiOutUGenSource[audio] {
   protected def expandUGens = {
      val _bus = bus.expand
      IIdxSeq.tabulate(_bus.size)(i => new MultiOutUGen("InFeedback", audio, IIdxSeq.fill(numChannels)(audio), IIdxSeq(_bus(i))))
   }
}
/**
 * A UGen which generates a trigger anytime a control bus is set.
 * 
 * Any time the bus is "touched" i.e. has its value set (using `"/c_set"` etc.), a single
 * impulse trigger will be generated. Its amplitude is the value that the bus was set to.
 * Note that if a signal is continuously written to that bus, for instance using
 * `Out.kr`, only one initial trigger is generated once that ugen starts writing, but
 * no successive triggers are generated.
 * 
 * @see [[de.sciss.synth.ugen.In]]
 */
object InTrig {
   
   /**
    * @param bus             the index of the control bus to read in from.
    * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
    *                        this is a constant, a change in number of channels of the underlying bus must
    *                        be reflected by creating different SynthDefs.
    */
   def kr(bus: AnyGE, numChannels: Int = 1) = apply(bus, numChannels)
}
/**
 * A UGen which generates a trigger anytime a control bus is set.
 * 
 * Any time the bus is "touched" i.e. has its value set (using `"/c_set"` etc.), a single
 * impulse trigger will be generated. Its amplitude is the value that the bus was set to.
 * Note that if a signal is continuously written to that bus, for instance using
 * `Out.kr`, only one initial trigger is generated once that ugen starts writing, but
 * no successive triggers are generated.
 * 
 * @param bus             the index of the control bus to read in from.
 * @param numChannels     the number of channels (i.e. adjacent buses) to read in. Since
 *                        this is a constant, a change in number of channels of the underlying bus must
 *                        be reflected by creating different SynthDefs.
 * 
 * @see [[de.sciss.synth.ugen.In]]
 */
final case class InTrig(bus: AnyGE, numChannels: Int) extends MultiOutUGenSource[control] {
   protected def expandUGens = {
      val _bus = bus.expand
      IIdxSeq.tabulate(_bus.size)(i => new MultiOutUGen("InTrig", control, IIdxSeq.fill(numChannels)(control), IIdxSeq(_bus(i))))
   }
}