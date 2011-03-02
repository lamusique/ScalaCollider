/*
 * UnpackFFTUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Mar 02 20:38:22 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import util.UGenHelper._
final case class Unpack1FFT(chain: AnyGE, fftSize: AnyGE, bin: AnyGE, which: AnyGE = 0.0f) extends SingleOutUGenSource[demand] {
   protected def expandUGens = {
      val _chain = chain.expand
      val _fftSize = fftSize.expand
      val _bin = bin.expand
      val _which = which.expand
      val _sz_chain = _chain.size
      val _sz_fftSize = _fftSize.size
      val _sz_bin = _bin.size
      val _sz_which = _which.size
      val _exp_ = maxInt(_sz_chain, _sz_fftSize, _sz_bin, _sz_which)
      IIdxSeq.tabulate(_exp_)(i => new SingleOutUGen("Unpack1FFT", demand, IIdxSeq(_chain(i.%(_sz_chain)), _fftSize(i.%(_sz_fftSize)), _bin(i.%(_sz_bin)), _which(i.%(_sz_which)))))
   }
}