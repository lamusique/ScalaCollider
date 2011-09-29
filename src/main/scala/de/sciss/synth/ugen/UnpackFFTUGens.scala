/*
 * UnpackFFTUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Sep 29 16:47:16 CEST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
final case class Unpack1FFT(chain: GE, fftSize: GE, bin: GE, which: GE = 0.0f) extends UGenSource.SingleOut("Unpack1FFT") with DemandRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chain.expand, fftSize.expand, bin.expand, which.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}