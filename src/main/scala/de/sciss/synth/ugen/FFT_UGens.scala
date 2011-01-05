/*
 * FFT_UGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Jan 05 15:41:14 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
case class FFT(buf: AnyGE, in: AnyGE, hop: AnyGE = 0.5f, winType: AnyGE = 0.0f, active: AnyGE = 1.0f, winSize: AnyGE = 0.0f) extends GE[control, FFTUGen] with ControlRated with WritesFFT {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _hop: IIdxSeq[AnyUGenIn] = hop.expand
      val _winType: IIdxSeq[AnyUGenIn] = winType.expand
      val _active: IIdxSeq[AnyUGenIn] = active.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _sz_buf = _buf.size
      val _sz_in = _in.size
      val _sz_hop = _hop.size
      val _sz_winType = _winType.size
      val _sz_active = _active.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_buf, _sz_in, _sz_hop, _sz_winType, _sz_active, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => FFTUGen(_buf(i.%(_sz_buf)), _in(i.%(_sz_in)), _hop(i.%(_sz_hop)), _winType(i.%(_sz_winType)), _active(i.%(_sz_active)), _winSize(i.%(_sz_winSize))))
   }
}
case class FFTUGen(buf: AnyUGenIn, in: AnyUGenIn, hop: AnyUGenIn, winType: AnyUGenIn, active: AnyUGenIn, winSize: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(buf, in, hop, winType, active, winSize)) with ControlRated with WritesFFT
object IFFT {
   def kr(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[control](control, chain, winType, winSize)
   def apply(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[audio](audio, chain, winType, winSize)
   def ar(chain: AnyGE, winType: AnyGE = 0.0f, winSize: AnyGE = 0.0f) = apply[audio](audio, chain, winType, winSize)
}
case class IFFT[R <: Rate](rate: R, chain: AnyGE, winType: AnyGE, winSize: AnyGE) extends GE[R, IFFTUGen[R]] {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _winType: IIdxSeq[AnyUGenIn] = winType.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _sz_chain = _chain.size
      val _sz_winType = _winType.size
      val _sz_winSize = _winSize.size
      val _exp_ = maxInt(_sz_chain, _sz_winType, _sz_winSize)
      IIdxSeq.tabulate(_exp_)(i => IFFTUGen(rate, _chain(i.%(_sz_chain)), _winType(i.%(_sz_winType)), _winSize(i.%(_sz_winSize))))
   }
}
case class IFFTUGen[R <: Rate](rate: R, chain: AnyUGenIn, winType: AnyUGenIn, winSize: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(chain, winType, winSize))
case class FFTTrigger(buf: AnyGE, hop: AnyGE = 0.5f, polar: AnyGE = 0.0f) extends GE[control, FFTTriggerUGen] with ControlRated with WritesFFT {
   def expand = {
      val _buf: IIdxSeq[AnyUGenIn] = buf.expand
      val _hop: IIdxSeq[AnyUGenIn] = hop.expand
      val _polar: IIdxSeq[AnyUGenIn] = polar.expand
      val _sz_buf = _buf.size
      val _sz_hop = _hop.size
      val _sz_polar = _polar.size
      val _exp_ = maxInt(_sz_buf, _sz_hop, _sz_polar)
      IIdxSeq.tabulate(_exp_)(i => FFTTriggerUGen(_buf(i.%(_sz_buf)), _hop(i.%(_sz_hop)), _polar(i.%(_sz_polar))))
   }
}
case class FFTTriggerUGen(buf: AnyUGenIn, hop: AnyUGenIn, polar: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(buf, hop, polar)) with ControlRated with WritesFFT