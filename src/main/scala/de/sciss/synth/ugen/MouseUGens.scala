/*
 * MouseUGens.scala
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
object MouseX {
   def kr: MouseX = kr( )
   def kr(minVal: AnyGE = 0.0f, maxVal: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(minVal, maxVal, warp, lag)
}
case class MouseX(minVal: AnyGE, maxVal: AnyGE, warp: AnyGE, lag: AnyGE) extends GE[control, MouseXUGen] with ControlRated {
   def expand = {
      val _minVal: IIdxSeq[AnyUGenIn] = minVal.expand
      val _maxVal: IIdxSeq[AnyUGenIn] = maxVal.expand
      val _warp: IIdxSeq[AnyUGenIn] = warp.expand
      val _lag: IIdxSeq[AnyUGenIn] = lag.expand
      val _sz_minVal = _minVal.size
      val _sz_maxVal = _maxVal.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_minVal, _sz_maxVal, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseXUGen(_minVal(i.%(_sz_minVal)), _maxVal(i.%(_sz_maxVal)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag))))
   }
}
case class MouseXUGen(minVal: AnyUGenIn, maxVal: AnyUGenIn, warp: AnyUGenIn, lag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(minVal, maxVal, warp, lag)) with ControlRated
object MouseY {
   def kr: MouseY = kr( )
   def kr(minVal: AnyGE = 0.0f, maxVal: AnyGE = 1.0f, warp: AnyGE = 0.0f, lag: AnyGE = 0.2f) = apply(minVal, maxVal, warp, lag)
}
case class MouseY(minVal: AnyGE, maxVal: AnyGE, warp: AnyGE, lag: AnyGE) extends GE[control, MouseYUGen] with ControlRated {
   def expand = {
      val _minVal: IIdxSeq[AnyUGenIn] = minVal.expand
      val _maxVal: IIdxSeq[AnyUGenIn] = maxVal.expand
      val _warp: IIdxSeq[AnyUGenIn] = warp.expand
      val _lag: IIdxSeq[AnyUGenIn] = lag.expand
      val _sz_minVal = _minVal.size
      val _sz_maxVal = _maxVal.size
      val _sz_warp = _warp.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_minVal, _sz_maxVal, _sz_warp, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseYUGen(_minVal(i.%(_sz_minVal)), _maxVal(i.%(_sz_maxVal)), _warp(i.%(_sz_warp)), _lag(i.%(_sz_lag))))
   }
}
case class MouseYUGen(minVal: AnyUGenIn, maxVal: AnyUGenIn, warp: AnyUGenIn, lag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(minVal, maxVal, warp, lag)) with ControlRated
object MouseButton {
   def kr: MouseButton = kr( )
   def kr(minVal: AnyGE = 0.0f, maxVal: AnyGE = 1.0f, lag: AnyGE = 0.2f) = apply(minVal, maxVal, lag)
}
case class MouseButton(minVal: AnyGE, maxVal: AnyGE, lag: AnyGE) extends GE[control, MouseButtonUGen] with ControlRated {
   def expand = {
      val _minVal: IIdxSeq[AnyUGenIn] = minVal.expand
      val _maxVal: IIdxSeq[AnyUGenIn] = maxVal.expand
      val _lag: IIdxSeq[AnyUGenIn] = lag.expand
      val _sz_minVal = _minVal.size
      val _sz_maxVal = _maxVal.size
      val _sz_lag = _lag.size
      val _exp_ = maxInt(_sz_minVal, _sz_maxVal, _sz_lag)
      IIdxSeq.tabulate(_exp_)(i => MouseButtonUGen(_minVal(i.%(_sz_minVal)), _maxVal(i.%(_sz_maxVal)), _lag(i.%(_sz_lag))))
   }
}
case class MouseButtonUGen(minVal: AnyUGenIn, maxVal: AnyUGenIn, lag: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(minVal, maxVal, lag)) with ControlRated