/*
 * MouseUGens.scala
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
object MouseX {
   def kr: MouseX = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
final case class MouseX(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends SingleOutUGenSource[control] {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _warp = warp.expand
      val _lag = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("MouseX", control, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag)))))
   }
}
object MouseY {
   def kr: MouseY = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
final case class MouseY(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends SingleOutUGenSource[control] {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _warp = warp.expand
      val _lag = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("MouseY", control, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag)))))
   }
}
object MouseButton {
   def kr: MouseButton = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(lo, hi, lag)
}
final case class MouseButton(lo: AnyGE, hi: AnyGE, lag: AnyGE) extends SingleOutUGenSource[control] {
   protected def expandUGens = {
      val _lo = lo.expand
      val _hi = hi.expand
      val _lag = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("MouseButton", control, IIdxSeq(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _lag(i.%(_sz_lag)))))
   }
}