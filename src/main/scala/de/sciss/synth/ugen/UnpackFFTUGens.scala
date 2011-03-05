/*
 * UnpackFFTUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Mar 04 23:36:58 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
final case class Unpack1FFT(chain: AnyGE, fftSize: AnyGE, bin: AnyGE, which: AnyGE = 0.0f) extends UGenSource.SingleOut[demand] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(chain.expand, fftSize.expand, bin.expand, which.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("Unpack1FFT", demand, _args)
}