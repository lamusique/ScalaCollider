/*
 * KeyboardUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Jan 05 01:09:09 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object KeyState {
   def kr(keyCode: AnyGE, minVal: AnyGE = 0.0f, maxVal: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(keyCode, minVal, maxVal, lag)
}
case class KeyState(keyCode: AnyGE, minVal: AnyGE, maxVal: AnyGE, lag: AnyGE) extends GE[control, KeyStateUGen] with ControlRated {
   def expand = {
      val _keyCode: IIdxSeq[AnyUGenIn] = keyCode.expand
      val _minVal: IIdxSeq[AnyUGenIn] = minVal.expand
      val _maxVal: IIdxSeq[AnyUGenIn] = maxVal.expand
      val _lag: IIdxSeq[AnyUGenIn] = lag.expand
      val _sz_keyCode = _keyCode.size
      val _sz_minVal = _minVal.size
      val _sz_maxVal = _maxVal.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_keyCode, _sz_minVal, _sz_maxVal, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => KeyStateUGen(_keyCode(i.%(_sz_keyCode)), _minVal(i.%(_sz_minVal)), _maxVal(i.%(_sz_maxVal)), _lag(i.%(_sz_lag))))
   }
}
case class KeyStateUGen(keyCode: AnyUGenIn, minVal: AnyUGenIn, maxVal: AnyUGenIn, lag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(keyCode, minVal, maxVal, lag)) with ControlRated