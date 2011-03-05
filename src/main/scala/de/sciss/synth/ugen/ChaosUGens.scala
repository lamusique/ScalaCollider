/*
 * ChaosUGens.scala
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
object CuspN {
   def ar: CuspN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, xi)
}
final case class CuspN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("CuspN", rate, _args)
}
object CuspL {
   def ar: CuspL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 1.9f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, xi)
}
final case class CuspL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("CuspL", rate, _args)
}
object FBSineN {
   def ar: FBSineN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
final case class FBSineN[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, im.expand, fb.expand, a.expand, c.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FBSineN", rate, _args)
}
object FBSineL {
   def ar: FBSineL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
final case class FBSineL[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, im.expand, fb.expand, a.expand, c.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FBSineL", rate, _args)
}
object FBSineC {
   def ar: FBSineC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, im: AnyGE = 1.0f, fb: AnyGE = 0.1f, a: AnyGE = 1.1f, c: AnyGE = 0.5f, xi: AnyGE = 0.1f, yi: AnyGE = 0.1f) = apply[audio](audio, freq, im, fb, a, c, xi, yi)
}
final case class FBSineC[R <: Rate](rate: R, freq: AnyGE, im: AnyGE, fb: AnyGE, a: AnyGE, c: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, im.expand, fb.expand, a.expand, c.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FBSineC", rate, _args)
}
object GbmanN {
   def ar: GbmanN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply[audio](audio, freq, xi, yi)
}
final case class GbmanN[R <: Rate](rate: R, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("GbmanN", rate, _args)
}
object GbmanL {
   def ar: GbmanL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, xi: AnyGE = 1.2f, yi: AnyGE = 2.1f) = apply[audio](audio, freq, xi, yi)
}
final case class GbmanL[R <: Rate](rate: R, freq: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("GbmanL", rate, _args)
}
object HenonN {
   def ar: HenonN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
final case class HenonN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("HenonN", rate, _args)
}
object HenonL {
   def ar: HenonL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
final case class HenonL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("HenonL", rate, _args)
}
object HenonC {
   def ar: HenonC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.4f, b: AnyGE = 0.3f, x0: AnyGE = 0.0f, x1: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, x0, x1)
}
final case class HenonC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, x0: AnyGE, x1: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("HenonC", rate, _args)
}
object LatoocarfianN {
   def ar: LatoocarfianN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LatoocarfianN", rate, _args)
}
object LatoocarfianL {
   def ar: LatoocarfianL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LatoocarfianL", rate, _args)
}
object LatoocarfianC {
   def ar: LatoocarfianC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = 3.0f, c: AnyGE = 0.5f, d: AnyGE = 0.5f, xi: AnyGE = 0.5f, yi: AnyGE = 0.5f) = apply[audio](audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, d: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LatoocarfianC", rate, _args)
}
object LinCongN {
   def ar: LinCongN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
final case class LinCongN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinCongN", rate, _args)
}
object LinCongL {
   def ar: LinCongL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
final case class LinCongL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinCongL", rate, _args)
}
object LinCongC {
   def ar: LinCongC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.1f, c: AnyGE = 0.13f, m: AnyGE = 1.0f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, c, m, xi)
}
final case class LinCongC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, c: AnyGE, m: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LinCongC", rate, _args)
}
object LorenzL {
   def ar: LorenzL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, s: AnyGE = 10.0f, r: AnyGE = 28.0f, b: AnyGE = 2.667f, h: AnyGE = 0.05f, xi: AnyGE = 0.1f, yi: AnyGE = 0.0f, zi: AnyGE = 0.0f) = apply[audio](audio, freq, s, r, b, h, xi, yi, zi)
}
final case class LorenzL[R <: Rate](rate: R, freq: AnyGE, s: AnyGE, r: AnyGE, b: AnyGE, h: AnyGE, xi: AnyGE, yi: AnyGE, zi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, s.expand, r.expand, b.expand, h.expand, xi.expand, yi.expand, zi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("LorenzL", rate, _args)
}
object QuadN {
   def ar: QuadN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
final case class QuadN[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("QuadN", rate, _args)
}
object QuadL {
   def ar: QuadL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
final case class QuadL[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("QuadL", rate, _args)
}
object QuadC {
   def ar: QuadC[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, a: AnyGE = 1.0f, b: AnyGE = -1.0f, c: AnyGE = -0.75f, xi: AnyGE = 0.0f) = apply[audio](audio, freq, a, b, c, xi)
}
final case class QuadC[R <: Rate](rate: R, freq: AnyGE, a: AnyGE, b: AnyGE, c: AnyGE, xi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("QuadC", rate, _args)
}
object StandardN {
   def ar: StandardN[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply[audio](audio, freq, k, xi, yi)
}
final case class StandardN[R <: Rate](rate: R, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, k.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("StandardN", rate, _args)
}
object StandardL {
   def ar: StandardL[audio] = ar()
   def ar(freq: AnyGE = SampleRate.ir * 0.5, k: AnyGE = 1.0f, xi: AnyGE = 0.5f, yi: AnyGE = 0.0f) = apply[audio](audio, freq, k, xi, yi)
}
final case class StandardL[R <: Rate](rate: R, freq: AnyGE, k: AnyGE, xi: AnyGE, yi: AnyGE) extends UGenSource.SingleOut[R] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, k.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("StandardL", rate, _args)
}