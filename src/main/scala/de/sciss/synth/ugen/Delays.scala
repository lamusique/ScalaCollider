/*
 * Delays.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Mon Jan 03 21:16:33 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import SynthGraph._
object CombN {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
case class CombN[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends GE[R, CombNUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => CombNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class CombNUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object CombL {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
case class CombL[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends GE[R, CombLUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => CombLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class CombLUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
object CombC {
   def ar(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
   def kr(in: AnyGE, maxDelayTime: AnyGE = 0.2f, delayTime: AnyGE = 0.2f, decayTime: AnyGE = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
}
case class CombC[R <: Rate](rate: R, in: AnyGE, maxDelayTime: AnyGE, delayTime: AnyGE, decayTime: AnyGE) extends GE[R, CombCUGen[R]] {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _maxDelayTime: IIdxSeq[AnyUGenIn] = maxDelayTime.expand
      val _delayTime: IIdxSeq[AnyUGenIn] = delayTime.expand
      val _decayTime: IIdxSeq[AnyUGenIn] = decayTime.expand
      val _sz_in = _in.size
      val _sz_maxDelayTime = _maxDelayTime.size
      val _sz_delayTime = _delayTime.size
      val _sz_decayTime = _decayTime.size
      val _exp_ = maxInt(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
      IIdxSeq.tabulate(_exp_)(i => CombCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
   }
}
case class CombCUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))