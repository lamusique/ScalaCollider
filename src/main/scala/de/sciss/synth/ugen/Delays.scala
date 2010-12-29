///*
// * Delays.scala
// * (ScalaCollider-UGens)
// *
// * This is a synthetically generated file.
// * Created: Tue Dec 28 22:21:21 CET 2010
// * ScalaCollider-UGen version: 0.10
// */
//
//package de.sciss.synth
//package ugen
//import collection.immutable.{IndexedSeq => IIdxSeq}
//import SynthGraph._
//object CombN {
//   def ar(in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn] = 0.2f, delayTime: GE[AnyUGenIn] = 0.2f, decayTime: GE[AnyUGenIn] = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
//   def kr(in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn] = 0.2f, delayTime: GE[AnyUGenIn] = 0.2f, decayTime: GE[AnyUGenIn] = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
//}
//case class CombN[R <: Rate](rate: R, in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn], delayTime: GE[AnyUGenIn], decayTime: GE[AnyUGenIn]) extends GE[CombNUGen[R]] {
//   def expand = {
//      val _in = in.expand
//      val _maxDelayTime = maxDelayTime.expand
//      val _delayTime = delayTime.expand
//      val _decayTime = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = max(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => CombNUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class CombNUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object CombL {
//   def ar(in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn] = 0.2f, delayTime: GE[AnyUGenIn] = 0.2f, decayTime: GE[AnyUGenIn] = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
//   def kr(in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn] = 0.2f, delayTime: GE[AnyUGenIn] = 0.2f, decayTime: GE[AnyUGenIn] = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
//}
//case class CombL[R <: Rate](rate: R, in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn], delayTime: GE[AnyUGenIn], decayTime: GE[AnyUGenIn]) extends GE[CombLUGen[R]] {
//   def expand = {
//      val _in = in.expand
//      val _maxDelayTime = maxDelayTime.expand
//      val _delayTime = delayTime.expand
//      val _decayTime = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = max(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => CombLUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class CombLUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))
//object CombC {
//   def ar(in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn] = 0.2f, delayTime: GE[AnyUGenIn] = 0.2f, decayTime: GE[AnyUGenIn] = 1.0f) = apply[audio](audio, in, maxDelayTime, delayTime, decayTime)
//   def kr(in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn] = 0.2f, delayTime: GE[AnyUGenIn] = 0.2f, decayTime: GE[AnyUGenIn] = 1.0f) = apply[control](control, in, maxDelayTime, delayTime, decayTime)
//}
//case class CombC[R <: Rate](rate: R, in: GE[AnyUGenIn], maxDelayTime: GE[AnyUGenIn], delayTime: GE[AnyUGenIn], decayTime: GE[AnyUGenIn]) extends GE[CombCUGen[R]] {
//   def expand = {
//      val _in = in.expand
//      val _maxDelayTime = maxDelayTime.expand
//      val _delayTime = delayTime.expand
//      val _decayTime = decayTime.expand
//      val _sz_in = _in.size
//      val _sz_maxDelayTime = _maxDelayTime.size
//      val _sz_delayTime = _delayTime.size
//      val _sz_decayTime = _decayTime.size
//      val _exp_ = max(_sz_in, _sz_maxDelayTime, _sz_delayTime, _sz_decayTime)
//      IIdxSeq.tabulate(_exp_)(i => CombCUGen(rate, _in(i.%(_sz_in)), _maxDelayTime(i.%(_sz_maxDelayTime)), _delayTime(i.%(_sz_delayTime)), _decayTime(i.%(_sz_decayTime))))
//   }
//}
//case class CombCUGen[R <: Rate](rate: R, in: AnyUGenIn, maxDelayTime: AnyUGenIn, delayTime: AnyUGenIn, decayTime: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(in, maxDelayTime, delayTime, decayTime))