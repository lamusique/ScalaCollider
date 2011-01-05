/*
 * MouseUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Jan 05 02:20:59 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object MouseX {
   def kr: MouseX = kr( )
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
case class MouseX(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends GE[control, MouseXUGen] with ControlRated {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _warp: IIdxSeq[AnyUGenIn] = warp.expand
      val _lag: IIdxSeq[AnyUGenIn] = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseXUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag))))
   }
}
case class MouseXUGen(lo: AnyUGenIn, hi: AnyUGenIn, warp: AnyUGenIn, lag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(lo, hi, warp, lag)) with ControlRated
object MouseY {
   def kr: MouseY = kr( )
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(lo, hi, warp, lag)
}
case class MouseY(lo: AnyGE, hi: AnyGE, warp: AnyGE, lag: AnyGE) extends GE[control, MouseYUGen] with ControlRated {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _warp: IIdxSeq[AnyUGenIn] = warp.expand
      val _lag: IIdxSeq[AnyUGenIn] = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseYUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag))))
   }
}
case class MouseYUGen(lo: AnyUGenIn, hi: AnyUGenIn, warp: AnyUGenIn, lag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(lo, hi, warp, lag)) with ControlRated
object MouseButton {
   def kr: MouseButton = kr( )
   def kr(lo: AnyGE = 0.0f, hi: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(lo, hi, lag)
}
case class MouseButton(lo: AnyGE, hi: AnyGE, lag: AnyGE) extends GE[control, MouseButtonUGen] with ControlRated {
   def expand = {
      val _lo: IIdxSeq[AnyUGenIn] = lo.expand
      val _hi: IIdxSeq[AnyUGenIn] = hi.expand
      val _lag: IIdxSeq[AnyUGenIn] = lag.expand
      val _sz_lo = _lo.size
      val _sz_hi = _hi.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_lo, _sz_hi, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseButtonUGen(_lo(i.%(_sz_lo)), _hi(i.%(_sz_hi)), _lag(i.%(_sz_lag))))
   }
}
case class MouseButtonUGen(lo: AnyUGenIn, hi: AnyUGenIn, lag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(lo, hi, lag)) with ControlRated