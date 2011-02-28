///*
// * KeyboardUGens.scala
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
//object KeyState {
//   def kr(keyCode: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(keyCode, lo, hi, lag)
//}
//case class KeyState(keyCode: AnyGE, lo: AnyGE, hi: AnyGE, lag: AnyGE) extends SingleOutUGenSource[KeyStateUGen] with ControlRated {
//   protected def expandUGens = {
//      val _keyCode: IIdxSeq[UGenIn] = keyCode.expand
//      val _lo: IIdxSeq[UGenIn] = lo.expand
//      val _hi: IIdxSeq[UGenIn] = hi.expand
//      val _lag: IIdxSeq[UGenIn] = lag.expand
//      val _sz_keyCode = _keyCode.size
//      val _sz_lo = _lo.size
//      val _sz_hi = _hi.size
//      val _sz_lag = _lag.size
//      val _exp_ = maxInt(_sz_keyCode, _sz_lo, _sz_hi, _sz_lag)
//      IIdxSeq.tabulate(_exp_)(i => KeyStateUGen(_keyCode(i.%(_sz_keyCode)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _lag(i.%(_sz_lag))))
//   }
//}
//case class KeyStateUGen(keyCode: UGenIn, lo: UGenIn, hi: UGenIn, lag: UGenIn) extends SingleOutUGen(IIdxSeq(keyCode, lo, hi, lag)) with ControlRated