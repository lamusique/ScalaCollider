///*
// * DynNoiseUGens.scala
// * (ScalaCollider-UGens)
// *
// * This is a synthetically generated file.
// * Created: Thu Jan 27 23:03:33 GMT 2011
// * ScalaCollider-UGen version: 0.10
// */
//
//package de.sciss.synth
//package ugen
//import collection.immutable.{IndexedSeq => IIdxSeq}
//import UGenHelper._
///**
// * A dynamic step noise UGen. Like `LFNoise0`, it generates abruptly changing random values
// * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
// * There is no time quantization, and it there is fast recovery from low freq values.
// *
// * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
// * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
// * to hang when freqs get very low.
// *
// * If very high or very low freqs are not needed, or fixed freqs are used,
// * `LFNoise0` is more efficient.
// *
// * @see [[de.sciss.synth.ugen.LFNoise0]]
// * @see [[de.sciss.synth.ugen.LFDNoise1]]
// * @see [[de.sciss.synth.ugen.LFDNoise2]]
// * @see [[de.sciss.synth.ugen.TRand]]
// */
//object LFDNoise0 {
//   def kr: LFDNoise0 = kr()
///**
// * @param freq            rate at which to generate random values.
// */
//def kr(freq: AnyGE = 500.0f) = apply(control, freq)
//   def ar: LFDNoise0 = ar()
///**
// * @param freq            rate at which to generate random values.
// */
//def ar(freq: AnyGE = 500.0f) = apply(audio, freq)
//}
///**
// * A dynamic step noise UGen. Like `LFNoise0`, it generates abruptly changing random values
// * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
// * There is no time quantization, and it there is fast recovery from low freq values.
// *
// * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
// * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
// * to hang when freqs get very low.
// *
// * If very high or very low freqs are not needed, or fixed freqs are used,
// * `LFNoise0` is more efficient.
// *
// * @param freq            rate at which to generate random values.
// *
// * @see [[de.sciss.synth.ugen.LFNoise0]]
// * @see [[de.sciss.synth.ugen.LFDNoise1]]
// * @see [[de.sciss.synth.ugen.LFDNoise2]]
// * @see [[de.sciss.synth.ugen.TRand]]
// */
//case class LFDNoise0(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[LFDNoise0UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => LFDNoise0UGen(rate, _freq(i)))
//   }
//}
//case class LFDNoise0UGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed
///**
// * A dynamic ramp noise UGen. Like `LFNoise1`, it generates linearly interpolated random values
// * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
// * There is no time quantization, and it there is fast recovery from low freq values.
// *
// * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
// * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
// * to hang when freqs get very low.
// *
// * If very high or very low freqs are not needed, or fixed freqs are used,
// * `LFNoise1` is more efficient.
// *
// * @see [[de.sciss.synth.ugen.LFNoise1]]
// * @see [[de.sciss.synth.ugen.LFDNoise0]]
// * @see [[de.sciss.synth.ugen.LFDNoise2]]
// * @see [[de.sciss.synth.ugen.Ramp]]
// */
//object LFDNoise1 {
//   def kr: LFDNoise1 = kr()
///**
// * @param freq            rate at which to generate random values.
// */
//def kr(freq: AnyGE = 500.0f) = apply(control, freq)
//   def ar: LFDNoise1 = ar()
///**
// * @param freq            rate at which to generate random values.
// */
//def ar(freq: AnyGE = 500.0f) = apply(audio, freq)
//}
///**
// * A dynamic ramp noise UGen. Like `LFNoise1`, it generates linearly interpolated random values
// * between `-1` and `+1` at a rate given by the `freq` argument, with two differences:
// * There is no time quantization, and it there is fast recovery from low freq values.
// *
// * In contrast, `LFNoise0`, `LFNoise1`, and `LFNoise2` quantize to the nearest integer division
// * of the samplerate, and they poll the freq argument only when scheduled, and thus seem
// * to hang when freqs get very low.
// *
// * If very high or very low freqs are not needed, or fixed freqs are used,
// * `LFNoise1` is more efficient.
// *
// * @param freq            rate at which to generate random values.
// *
// * @see [[de.sciss.synth.ugen.LFNoise1]]
// * @see [[de.sciss.synth.ugen.LFDNoise0]]
// * @see [[de.sciss.synth.ugen.LFDNoise2]]
// * @see [[de.sciss.synth.ugen.Ramp]]
// */
//case class LFDNoise1(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[LFDNoise1UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => LFDNoise1UGen(rate, _freq(i)))
//   }
//}
//case class LFDNoise1UGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed
//object LFDNoise3 {
//   def kr: LFDNoise3 = kr()
///**
// * @param freq            rate at which to generate random values.
// */
//def kr(freq: AnyGE = 500.0f) = apply(control, freq)
//   def ar: LFDNoise3 = ar()
///**
// * @param freq            rate at which to generate random values.
// */
//def ar(freq: AnyGE = 500.0f) = apply(audio, freq)
//}
///**
// * @param freq            rate at which to generate random values.
// */
//case class LFDNoise3(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[LFDNoise3UGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => LFDNoise3UGen(rate, _freq(i)))
//   }
//}
//case class LFDNoise3UGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed
//object LFDClipNoise {
//   def kr: LFDClipNoise = kr()
///**
// * @param freq            rate at which to generate random values.
// */
//def kr(freq: AnyGE = 500.0f) = apply(control, freq)
//   def ar: LFDClipNoise = ar()
///**
// * @param freq            rate at which to generate random values.
// */
//def ar(freq: AnyGE = 500.0f) = apply(audio, freq)
//}
///**
// * @param freq            rate at which to generate random values.
// */
//case class LFDClipNoise(rate: Rate, freq: AnyGE) extends SingleOutUGenSource[LFDClipNoiseUGen] with UsesRandSeed {
//   protected def expandUGens = {
//      val _freq: IIdxSeq[UGenIn] = freq.expand
//      IIdxSeq.tabulate(_freq.size)(i => LFDClipNoiseUGen(rate, _freq(i)))
//   }
//}
//case class LFDClipNoiseUGen(rate: Rate, freq: UGenIn) extends SingleOutUGen(IIdxSeq(freq)) with UsesRandSeed