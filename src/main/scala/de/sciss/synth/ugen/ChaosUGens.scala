/*
 * ChaosUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Oct 27 17:42:00 BST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a - b * sqrt(abs(x[n]))
 * }}}
 */
object CuspN {
   def ar: CuspN = ar()
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 1.9f, xi: GE = 0.0f) = apply(audio, freq, a, b, xi)
}
/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a - b * sqrt(abs(x[n]))
 * }}}
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param xi              Initial value of x
 */
final case class CuspN(rate: Rate, freq: GE, a: GE, b: GE, xi: GE) extends UGenSource.SingleOut("CuspN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a - b * sqrt(abs(x[n]))
 * }}}
 */
object CuspL {
   def ar: CuspL = ar()
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 1.9f, xi: GE = 0.0f) = apply(audio, freq, a, b, xi)
}
/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a - b * sqrt(abs(x[n]))
 * }}}
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param xi              Initial value of x
 */
final case class CuspL(rate: Rate, freq: GE, a: GE, b: GE, xi: GE) extends UGenSource.SingleOut("CuspL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = sin(im * y[n] + fb * x[n])
 * y[n+1] = (a * y[n] + c) % 2pi
 * }}}
 * This uses a linear congruential function to drive the phase indexing of a sine wave. For  im = 1 ,  fb = 0 , and  a = 1 a normal sinewave results.
 */
object FBSineN {
   def ar: FBSineN = ar()
   /**
    * @param freq            Iteration frequency in Hertz
    * @param im              Index multiplier amount
    * @param fb              Feedback amount
    * @param a               Phase multiplier amount
    * @param c               Phase increment amount
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, im: GE = 1.0f, fb: GE = 0.1f, a: GE = 1.1f, c: GE = 0.5f, xi: GE = 0.1f, yi: GE = 0.1f) = apply(audio, freq, im, fb, a, c, xi, yi)
}
/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = sin(im * y[n] + fb * x[n])
 * y[n+1] = (a * y[n] + c) % 2pi
 * }}}
 * This uses a linear congruential function to drive the phase indexing of a sine wave. For  im = 1 ,  fb = 0 , and  a = 1 a normal sinewave results.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param im              Index multiplier amount
 * @param fb              Feedback amount
 * @param a               Phase multiplier amount
 * @param c               Phase increment amount
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 */
final case class FBSineN(rate: Rate, freq: GE, im: GE, fb: GE, a: GE, c: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("FBSineN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, im.expand, fb.expand, a.expand, c.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = sin(im * y[n] + fb * x[n])
 * y[n+1] = (a * y[n] + c) % 2pi
 * }}}
 * This uses a linear congruential function to drive the phase indexing of a sine wave. For  im = 1 ,  fb = 0 , and  a = 1 a normal sinewave results.
 */
object FBSineL {
   def ar: FBSineL = ar()
   /**
    * @param freq            Iteration frequency in Hertz
    * @param im              Index multiplier amount
    * @param fb              Feedback amount
    * @param a               Phase multiplier amount
    * @param c               Phase increment amount
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, im: GE = 1.0f, fb: GE = 0.1f, a: GE = 1.1f, c: GE = 0.5f, xi: GE = 0.1f, yi: GE = 0.1f) = apply(audio, freq, im, fb, a, c, xi, yi)
}
/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = sin(im * y[n] + fb * x[n])
 * y[n+1] = (a * y[n] + c) % 2pi
 * }}}
 * This uses a linear congruential function to drive the phase indexing of a sine wave. For  im = 1 ,  fb = 0 , and  a = 1 a normal sinewave results.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param im              Index multiplier amount
 * @param fb              Feedback amount
 * @param a               Phase multiplier amount
 * @param c               Phase increment amount
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 */
final case class FBSineL(rate: Rate, freq: GE, im: GE, fb: GE, a: GE, c: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("FBSineL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, im.expand, fb.expand, a.expand, c.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = sin(im * y[n] + fb * x[n])
 * y[n+1] = (a * y[n] + c) % 2pi
 * }}}
 * This uses a linear congruential function to drive the phase indexing of a sine wave. For  im = 1 ,  fb = 0 , and  a = 1 a normal sinewave results.
 */
object FBSineC {
   def ar: FBSineC = ar()
   /**
    * @param freq            Iteration frequency in Hertz
    * @param im              Index multiplier amount
    * @param fb              Feedback amount
    * @param a               Phase multiplier amount
    * @param c               Phase increment amount
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, im: GE = 1.0f, fb: GE = 0.1f, a: GE = 1.1f, c: GE = 0.5f, xi: GE = 0.1f, yi: GE = 0.1f) = apply(audio, freq, im, fb, a, c, xi, yi)
}
/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = sin(im * y[n] + fb * x[n])
 * y[n+1] = (a * y[n] + c) % 2pi
 * }}}
 * This uses a linear congruential function to drive the phase indexing of a sine wave. For  im = 1 ,  fb = 0 , and  a = 1 a normal sinewave results.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param im              Index multiplier amount
 * @param fb              Feedback amount
 * @param a               Phase multiplier amount
 * @param c               Phase increment amount
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 */
final case class FBSineC(rate: Rate, freq: GE, im: GE, fb: GE, a: GE, c: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("FBSineC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, im.expand, fb.expand, a.expand, c.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object GbmanN {
   def ar: GbmanN = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, xi: GE = 1.2f, yi: GE = 2.1f) = apply(audio, freq, xi, yi)
}
final case class GbmanN(rate: Rate, freq: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("GbmanN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object GbmanL {
   def ar: GbmanL = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, xi: GE = 1.2f, yi: GE = 2.1f) = apply(audio, freq, xi, yi)
}
final case class GbmanL(rate: Rate, freq: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("GbmanL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object HenonN {
   def ar: HenonN = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.4f, b: GE = 0.3f, x0: GE = 0.0f, x1: GE = 0.0f) = apply(audio, freq, a, b, x0, x1)
}
final case class HenonN(rate: Rate, freq: GE, a: GE, b: GE, x0: GE, x1: GE) extends UGenSource.SingleOut("HenonN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object HenonL {
   def ar: HenonL = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.4f, b: GE = 0.3f, x0: GE = 0.0f, x1: GE = 0.0f) = apply(audio, freq, a, b, x0, x1)
}
final case class HenonL(rate: Rate, freq: GE, a: GE, b: GE, x0: GE, x1: GE) extends UGenSource.SingleOut("HenonL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object HenonC {
   def ar: HenonC = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.4f, b: GE = 0.3f, x0: GE = 0.0f, x1: GE = 0.0f) = apply(audio, freq, a, b, x0, x1)
}
final case class HenonC(rate: Rate, freq: GE, a: GE, b: GE, x0: GE, x1: GE) extends UGenSource.SingleOut("HenonC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LatoocarfianN {
   def ar: LatoocarfianN = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 3.0f, c: GE = 0.5f, d: GE = 0.5f, xi: GE = 0.5f, yi: GE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianN(rate: Rate, freq: GE, a: GE, b: GE, c: GE, d: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("LatoocarfianN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LatoocarfianL {
   def ar: LatoocarfianL = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 3.0f, c: GE = 0.5f, d: GE = 0.5f, xi: GE = 0.5f, yi: GE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianL(rate: Rate, freq: GE, a: GE, b: GE, c: GE, d: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("LatoocarfianL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LatoocarfianC {
   def ar: LatoocarfianC = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 3.0f, c: GE = 0.5f, d: GE = 0.5f, xi: GE = 0.5f, yi: GE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
}
final case class LatoocarfianC(rate: Rate, freq: GE, a: GE, b: GE, c: GE, d: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("LatoocarfianC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LinCongN {
   def ar: LinCongN = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.1f, c: GE = 0.13f, m: GE = 1.0f, xi: GE = 0.0f) = apply(audio, freq, a, c, m, xi)
}
final case class LinCongN(rate: Rate, freq: GE, a: GE, c: GE, m: GE, xi: GE) extends UGenSource.SingleOut("LinCongN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LinCongL {
   def ar: LinCongL = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.1f, c: GE = 0.13f, m: GE = 1.0f, xi: GE = 0.0f) = apply(audio, freq, a, c, m, xi)
}
final case class LinCongL(rate: Rate, freq: GE, a: GE, c: GE, m: GE, xi: GE) extends UGenSource.SingleOut("LinCongL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LinCongC {
   def ar: LinCongC = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.1f, c: GE = 0.13f, m: GE = 1.0f, xi: GE = 0.0f) = apply(audio, freq, a, c, m, xi)
}
final case class LinCongC(rate: Rate, freq: GE, a: GE, c: GE, m: GE, xi: GE) extends UGenSource.SingleOut("LinCongC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object LorenzL {
   def ar: LorenzL = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, s: GE = 10.0f, r: GE = 28.0f, b: GE = 2.667f, h: GE = 0.05f, xi: GE = 0.1f, yi: GE = 0.0f, zi: GE = 0.0f) = apply(audio, freq, s, r, b, h, xi, yi, zi)
}
final case class LorenzL(rate: Rate, freq: GE, s: GE, r: GE, b: GE, h: GE, xi: GE, yi: GE, zi: GE) extends UGenSource.SingleOut("LorenzL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, s.expand, r.expand, b.expand, h.expand, xi.expand, yi.expand, zi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object QuadN {
   def ar: QuadN = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = -1.0f, c: GE = -0.75f, xi: GE = 0.0f) = apply(audio, freq, a, b, c, xi)
}
final case class QuadN(rate: Rate, freq: GE, a: GE, b: GE, c: GE, xi: GE) extends UGenSource.SingleOut("QuadN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object QuadL {
   def ar: QuadL = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = -1.0f, c: GE = -0.75f, xi: GE = 0.0f) = apply(audio, freq, a, b, c, xi)
}
final case class QuadL(rate: Rate, freq: GE, a: GE, b: GE, c: GE, xi: GE) extends UGenSource.SingleOut("QuadL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object QuadC {
   def ar: QuadC = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = -1.0f, c: GE = -0.75f, xi: GE = 0.0f) = apply(audio, freq, a, b, c, xi)
}
final case class QuadC(rate: Rate, freq: GE, a: GE, b: GE, c: GE, xi: GE) extends UGenSource.SingleOut("QuadC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object StandardN {
   def ar: StandardN = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, k: GE = 1.0f, xi: GE = 0.5f, yi: GE = 0.0f) = apply(audio, freq, k, xi, yi)
}
final case class StandardN(rate: Rate, freq: GE, k: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("StandardN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, k.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}
object StandardL {
   def ar: StandardL = ar()
   def ar(freq: GE = SampleRate.ir * 0.5, k: GE = 1.0f, xi: GE = 0.5f, yi: GE = 0.0f) = apply(audio, freq, k, xi, yi)
}
final case class StandardL(rate: Rate, freq: GE, k: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("StandardL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, k.expand, xi.expand, yi.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}