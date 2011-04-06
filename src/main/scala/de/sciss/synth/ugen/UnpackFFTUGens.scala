/*
 * UnpackFFTUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Wed Apr 06 02:27:48 BST 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
final case class Unpack1FFT(chain: GE, fftSize: GE, bin: GE, which: GE = 0.0f) extends UGenSource.SingleOut {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chain.expand, fftSize.expand, bin.expand, which.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Unpack1FFT", demand, _args)
}