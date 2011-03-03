/*
 * KeyboardUGens.scala
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
object KeyState {
   def kr(keyCode: AnyGE, lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(keyCode, lo, hi, lag)
}
final case class KeyState(keyCode: AnyGE, lo: AnyGE, hi: AnyGE, lag: AnyGE) extends SingleOutUGenSource[control] {
   protected def expandUGens = {
      val _keyCode = keyCode.expand
      val _lo = lo.expand
      val _hi = hi.expand
      val _lag = lag.expand
      val _sz_keyCode = _keyCode.size
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_keyCode, _sz_lo, _sz_hi, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("KeyState", control, IIdxSeq(_keyCode(i.%(_sz_keyCode)), _lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _lag(i.%(_sz_lag)))))
   }
}