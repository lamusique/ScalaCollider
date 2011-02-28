///*
// * UnpackFFTUGens.scala
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
//case class Unpack1FFT(chain: AnyGE, fftSize: AnyGE, bin: AnyGE, which: AnyGE = 0.0f) extends SingleOutUGenSource[Unpack1FFTUGen] with DemandRated {
//   protected def expandUGens = {
//      val _chain: IIdxSeq[UGenIn] = chain.expand
//      val _fftSize: IIdxSeq[UGenIn] = fftSize.expand
//      val _bin: IIdxSeq[UGenIn] = bin.expand
//      val _which: IIdxSeq[UGenIn] = which.expand
//      val _sz_chain = _chain.size
//      val _sz_fftSize = _fftSize.size
//      val _sz_bin = _bin.size
//      val _sz_which = _which.size
//      val _exp_ = maxInt(_sz_chain, _sz_fftSize, _sz_bin, _sz_which)
//      IIdxSeq.tabulate(_exp_)(i => Unpack1FFTUGen(_chain(i.%(_sz_chain)), _fftSize(i.%(_sz_fftSize)), _bin(i.%(_sz_bin)), _which(i.%(_sz_which))))
//   }
//}
//case class Unpack1FFTUGen(chain: UGenIn, fftSize: UGenIn, bin: UGenIn, which: UGenIn) extends SingleOutUGen(IIdxSeq(chain, fftSize, bin, which)) with DemandRated