/*
 * MouseUGens.scala
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
object MouseX {
   def kr: MouseX = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
case class MouseX(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends SingleOutUGenSource[MouseXUGen] with ControlRated {
   protected def expandUGens = {
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _warp: IIdxSeq[UGenIn] = warp.expand
      val _lag: IIdxSeq[UGenIn] = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseXUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag))))
   }
}
case class MouseXUGen(lo: UGenIn, hi: UGenIn, warp: UGenIn, lag: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, warp, lag)) with ControlRated
object MouseY {
   def kr: MouseY = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
case class MouseY(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends SingleOutUGenSource[MouseYUGen] with ControlRated {
   protected def expandUGens = {
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _warp: IIdxSeq[UGenIn] = warp.expand
      val _lag: IIdxSeq[UGenIn] = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseYUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag))))
   }
}
case class MouseYUGen(lo: UGenIn, hi: UGenIn, warp: UGenIn, lag: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, warp, lag)) with ControlRated
object MouseButton {
   def kr: MouseButton = kr()
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(lo, hi, lag)
}
case class MouseButton(lo: AnyGE, hi: AnyGE, lag: AnyGE) extends SingleOutUGenSource[MouseButtonUGen] with ControlRated {
   protected def expandUGens = {
      val _lo: IIdxSeq[UGenIn] = lo.expand
      val _hi: IIdxSeq[UGenIn] = hi.expand
      val _lag: IIdxSeq[UGenIn] = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseButtonUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _lag(i.%(_sz_lag))))
   }
}
case class MouseButtonUGen(lo: UGenIn, hi: UGenIn, lag: UGenIn) extends SingleOutUGen(IIdxSeq(lo, hi, lag)) with ControlRated