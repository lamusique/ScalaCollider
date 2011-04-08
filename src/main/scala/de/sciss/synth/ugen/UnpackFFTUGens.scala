/*
 * UnpackFFTUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Apr 08 04:10:01 BST 2011
 * ScalaCollider-UGen version: 0.12
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
final case class Unpack1FFT(chain: GE, fftSize: GE, bin: GE, which: GE = 0.0f) extends UGenSource.SingleOut("Unpack1FFT") with DemandRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chain.expand, fftSize.expand, bin.expand, which.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, demand, _args)
}