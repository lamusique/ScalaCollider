/*
 * IOUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Mar 04 23:36:58 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object OffsetOut {
   def ar(bus: AnyGE, in: GE[audio]) = apply(bus, in)
}
final case class OffsetOut(bus: AnyGE, in: AnyGE) extends UGenSource.ZeroOut[audio] with WritesBus {
   protected def makeUGens: Unit = unwrap(IIdxSeq(bus.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): Unit = new UGen.ZeroOut("OffsetOut", audio, _args)
}
object LocalIn {
   def kr: LocalIn[control] = kr()
   def kr(numChannels: Int = 1) = apply[control](control, numChannels)
   def ar: LocalIn[audio] = ar()
   def ar(numChannels: Int = 1) = apply[audio](audio, numChannels)
}
final case class LocalIn[R <: Rate](rate: R, numChannels: Int) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = makeUGen(IIdxSeq.empty)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("LocalIn", rate, IIdxSeq.fill(numChannels)(rate), _args)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
object XOut {
   def ar(bus: AnyGE, in: GE[audio], xfade: AnyGE) = apply[audio](audio, bus, in, xfade)
   def kr(bus: AnyGE, in: AnyGE, xfade: AnyGE) = apply[control](control, bus, in, xfade)
}
/**
 * '''Warning''': The argument order is different from its sclang counterpart.
 */
final case class XOut[R <: Rate](rate: R, bus: AnyGE, in: AnyGE, xfade: AnyGE) extends UGenSource.ZeroOut[R] with WritesBus {
   protected def makeUGens: Unit = unwrap(IIdxSeq(bus.expand, xfade.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): Unit = new UGen.ZeroOut("XOut", rate, _args)
}
object ReplaceOut {
   def ar(bus: AnyGE, in: GE[audio]) = apply(bus, in)
}
final case class ReplaceOut(bus: AnyGE, in: AnyGE) extends UGenSource.ZeroOut[audio] with WritesBus {
   protected def makeUGens: Unit = unwrap(IIdxSeq(bus.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): Unit = new UGen.ZeroOut("ReplaceOut", audio, _args)
}
object Out {
   def ar(bus: AnyGE, in: GE[audio]) = apply[audio](audio, bus, in)
   def kr(bus: AnyGE, in: AnyGE) = apply[control](control, bus, in)
   def ir(bus: AnyGE, in: AnyGE) = apply[scalar](scalar, bus, in)
}
final case class Out[R <: Rate](rate: R, bus: AnyGE, in: AnyGE) extends UGenSource.ZeroOut[R] with WritesBus {
   protected def makeUGens: Unit = unwrap(IIdxSeq(bus.expand).++(in.expand.outputs))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): Unit = new UGen.ZeroOut("Out", rate, _args)
}
object LocalOut {
   def kr(in: AnyGE) = apply[control](control, in)
   def ar(in: GE[audio]) = apply[audio](audio, in)
}
final case class LocalOut[R <: Rate](rate: R, in: AnyGE) extends UGenSource.ZeroOut[R] {
   protected def makeUGens: Unit = unwrap(in.expand.outputs)
   protected def makeUGen(_args: IIdxSeq[UGenIn]): Unit = new UGen.ZeroOut("LocalOut", rate, _args)
}
object In {
   def ir(bus: AnyGE, numChannels: Int = 1) = apply[scalar](scalar, bus, numChannels)
   def kr(bus: AnyGE, numChannels: Int = 1) = apply[control](control, bus, numChannels)
   def ar(bus: AnyGE, numChannels: Int = 1) = apply[audio](audio, bus, numChannels)
}
final case class In[R <: Rate](rate: R, bus: AnyGE, numChannels: Int) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bus.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("In", rate, IIdxSeq.fill(numChannels)(rate), _args)
}
object LagIn {
   def kr(bus: AnyGE, numChannels: Int = 1, lag: AnyGE = 0.1f) = apply[control](control, bus, numChannels, lag)
}
final case class LagIn[R <: Rate](rate: R, bus: AnyGE, numChannels: Int, lag: AnyGE) extends UGenSource.MultiOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bus.expand, lag.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("LagIn", rate, IIdxSeq.fill(numChannels)(rate), _args)
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
final case class InFeedback(bus: AnyGE, numChannels: Int) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bus.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("InFeedback", audio, IIdxSeq.fill(numChannels)(audio), _args)
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
final case class InTrig(bus: AnyGE, numChannels: Int) extends UGenSource.MultiOut[control] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(bus.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("InTrig", control, IIdxSeq.fill(numChannels)(control), _args)
}