/*
 * ChaosUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * ScalaCollider-UGens version: 1.0.1
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}

/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a - b * sqrt(abs(x[n]))
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.CuspL]]
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
 * 
 * @see [[de.sciss.synth.ugen.CuspL]]
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
 * 
 * @see [[de.sciss.synth.ugen.CuspN]]
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
 * 
 * @see [[de.sciss.synth.ugen.CuspN]]
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
 * 
 * @see [[de.sciss.synth.ugen.FBSineL]]
 * @see [[de.sciss.synth.ugen.FBSineC]]
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
 * 
 * @see [[de.sciss.synth.ugen.FBSineL]]
 * @see [[de.sciss.synth.ugen.FBSineC]]
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
 * 
 * @see [[de.sciss.synth.ugen.FBSineN]]
 * @see [[de.sciss.synth.ugen.FBSineC]]
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
 * 
 * @see [[de.sciss.synth.ugen.FBSineN]]
 * @see [[de.sciss.synth.ugen.FBSineC]]
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
 * 
 * @see [[de.sciss.synth.ugen.FBSineN]]
 * @see [[de.sciss.synth.ugen.FBSineL]]
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
 * 
 * @see [[de.sciss.synth.ugen.FBSineN]]
 * @see [[de.sciss.synth.ugen.FBSineL]]
 */
final case class FBSineC(rate: Rate, freq: GE, im: GE, fb: GE, a: GE, c: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("FBSineC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, im.expand, fb.expand, a.expand, c.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = 1 - y[n] + abs(x[n])
 * y[n+1] = x[n]
 * }}}
 * The behavior of the system is only dependent on its initial conditions.
 * Reference: Devaney, R. L. "The Gingerbreadman." Algorithm 3, 15-16, Jan. 1992.
 * 
 * @see [[de.sciss.synth.ugen.GbmanL]]
 */
object GbmanN {
   def ar: GbmanN = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, xi: GE = 1.2f, yi: GE = 2.1f) = apply(audio, freq, xi, yi)
}

/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = 1 - y[n] + abs(x[n])
 * y[n+1] = x[n]
 * }}}
 * The behavior of the system is only dependent on its initial conditions.
 * Reference: Devaney, R. L. "The Gingerbreadman." Algorithm 3, 15-16, Jan. 1992.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param yi              Initial value of y
 * 
 * @see [[de.sciss.synth.ugen.GbmanL]]
 */
final case class GbmanN(rate: Rate, freq: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("GbmanN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A linear-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = 1 - y[n] + abs(x[n])
 * y[n+1] = x[n]
 * }}}
 * The behavior of the system is only dependent on its initial conditions.
 * Reference: Devaney, R. L. "The Gingerbreadman." Algorithm 3, 15-16, Jan. 1992.
 * 
 * @see [[de.sciss.synth.ugen.GbmanN]]
 */
object GbmanL {
   def ar: GbmanL = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, xi: GE = 1.2f, yi: GE = 2.1f) = apply(audio, freq, xi, yi)
}

/**
 * A linear-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = 1 - y[n] + abs(x[n])
 * y[n+1] = x[n]
 * }}}
 * The behavior of the system is only dependent on its initial conditions.
 * Reference: Devaney, R. L. "The Gingerbreadman." Algorithm 3, 15-16, Jan. 1992.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param yi              Initial value of y
 * 
 * @see [[de.sciss.synth.ugen.GbmanN]]
 */
final case class GbmanL(rate: Rate, freq: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("GbmanL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+2] = 1 - a * pow(x[n+1], 2) + b * x[n]
 * }}}
 * This equation was discovered by French astronomer Michel Hénon while studying the orbits of stars in globular clusters.
 * 
 * @see [[de.sciss.synth.ugen.HenonL]]
 * @see [[de.sciss.synth.ugen.HenonC]]
 */
object HenonN {
   def ar: HenonN = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param x0              Initial value of x
    * @param x1              Second value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.4f, b: GE = 0.3f, x0: GE = 0.0f, x1: GE = 0.0f) = apply(audio, freq, a, b, x0, x1)
}

/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+2] = 1 - a * pow(x[n+1], 2) + b * x[n]
 * }}}
 * This equation was discovered by French astronomer Michel Hénon while studying the orbits of stars in globular clusters.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param x0              Initial value of x
 * @param x1              Second value of x
 * 
 * @see [[de.sciss.synth.ugen.HenonL]]
 * @see [[de.sciss.synth.ugen.HenonC]]
 */
final case class HenonN(rate: Rate, freq: GE, a: GE, b: GE, x0: GE, x1: GE) extends UGenSource.SingleOut("HenonN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+2] = 1 - a * pow(x[n+1], 2) + b * x[n]
 * }}}
 * This equation was discovered by French astronomer Michel Hénon while studying the orbits of stars in globular clusters.
 * 
 * @see [[de.sciss.synth.ugen.HenonL]]
 * @see [[de.sciss.synth.ugen.HenonC]]
 */
object HenonL {
   def ar: HenonL = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param x0              Initial value of x
    * @param x1              Second value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.4f, b: GE = 0.3f, x0: GE = 0.0f, x1: GE = 0.0f) = apply(audio, freq, a, b, x0, x1)
}

/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+2] = 1 - a * pow(x[n+1], 2) + b * x[n]
 * }}}
 * This equation was discovered by French astronomer Michel Hénon while studying the orbits of stars in globular clusters.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param x0              Initial value of x
 * @param x1              Second value of x
 * 
 * @see [[de.sciss.synth.ugen.HenonL]]
 * @see [[de.sciss.synth.ugen.HenonC]]
 */
final case class HenonL(rate: Rate, freq: GE, a: GE, b: GE, x0: GE, x1: GE) extends UGenSource.SingleOut("HenonL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A cubic-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+2] = 1 - a * pow(x[n+1], 2) + b * x[n]
 * }}}
 * This equation was discovered by French astronomer Michel Hénon while studying the orbits of stars in globular clusters.
 * 
 * @see [[de.sciss.synth.ugen.HenonL]]
 * @see [[de.sciss.synth.ugen.HenonC]]
 */
object HenonC {
   def ar: HenonC = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param x0              Initial value of x
    * @param x1              Second value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.4f, b: GE = 0.3f, x0: GE = 0.0f, x1: GE = 0.0f) = apply(audio, freq, a, b, x0, x1)
}

/**
 * A cubic-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+2] = 1 - a * pow(x[n+1], 2) + b * x[n]
 * }}}
 * This equation was discovered by French astronomer Michel Hénon while studying the orbits of stars in globular clusters.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param x0              Initial value of x
 * @param x1              Second value of x
 * 
 * @see [[de.sciss.synth.ugen.HenonL]]
 * @see [[de.sciss.synth.ugen.HenonC]]
 */
final case class HenonC(rate: Rate, freq: GE, a: GE, b: GE, x0: GE, x1: GE) extends UGenSource.SingleOut("HenonC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, x0.expand, x1.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A non-interpolating sound generator based on a function given in Clifford Pickover's book Chaos In
 * Wonderland, pg 26. The function is:
 * {{{
 * x[n+1] = sin(b * y[n]) + c * sin(b * x[n])
 * y[n+1] = sin(a * y[n]) + d * sin(a * x[n])
 * }}}
 * According to Pickover, parameters a and b should be in the range from -3 to +3, and parameters c and
 * d should be in the range from 0.5 to 1.5. The function can, depending on the parameters given, give
 * continuous chaotic output, converge to a single value (silence) or oscillate in a cycle (tone).
 * NOTE: This UGen is experimental and not optimized currently, so is rather hoggish of CPU.
 */
object LatoocarfianN {
   def ar: LatoocarfianN = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz.
    * @param a               Equation variable
    * @param b               Equation variable
    * @param c               Equation variable
    * @param d               Equation variable
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 3.0f, c: GE = 0.5f, d: GE = 0.5f, xi: GE = 0.5f, yi: GE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
}

/**
 * A non-interpolating sound generator based on a function given in Clifford Pickover's book Chaos In
 * Wonderland, pg 26. The function is:
 * {{{
 * x[n+1] = sin(b * y[n]) + c * sin(b * x[n])
 * y[n+1] = sin(a * y[n]) + d * sin(a * x[n])
 * }}}
 * According to Pickover, parameters a and b should be in the range from -3 to +3, and parameters c and
 * d should be in the range from 0.5 to 1.5. The function can, depending on the parameters given, give
 * continuous chaotic output, converge to a single value (silence) or oscillate in a cycle (tone).
 * NOTE: This UGen is experimental and not optimized currently, so is rather hoggish of CPU.
 * 
 * @param freq            Iteration frequency in Hertz.
 * @param a               Equation variable
 * @param b               Equation variable
 * @param c               Equation variable
 * @param d               Equation variable
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 */
final case class LatoocarfianN(rate: Rate, freq: GE, a: GE, b: GE, c: GE, d: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("LatoocarfianN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A linear-interpolating sound generator based on a function given in Clifford Pickover's book Chaos
 * In Wonderland, pg 26. The function is:
 * {{{
 * x[n+1] = sin(b * y[n]) + c * sin(b * x[n])
 * y[n+1] = sin(a * y[n]) + d * sin(a * x[n])
 * }}}
 * According to Pickover, parameters a and b should be in the range from -3 to +3, and parameters c and
 * d should be in the range from 0.5 to 1.5. The function can, depending on the parameters given, give
 * continuous chaotic output, converge to a single value (silence) or oscillate in a cycle (tone).
 * NOTE: This UGen is experimental and not optimized currently, so is rather hoggish of CPU.
 */
object LatoocarfianL {
   def ar: LatoocarfianL = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param c               Equation variable
    * @param d               Equation variable
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 3.0f, c: GE = 0.5f, d: GE = 0.5f, xi: GE = 0.5f, yi: GE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
}

/**
 * A linear-interpolating sound generator based on a function given in Clifford Pickover's book Chaos
 * In Wonderland, pg 26. The function is:
 * {{{
 * x[n+1] = sin(b * y[n]) + c * sin(b * x[n])
 * y[n+1] = sin(a * y[n]) + d * sin(a * x[n])
 * }}}
 * According to Pickover, parameters a and b should be in the range from -3 to +3, and parameters c and
 * d should be in the range from 0.5 to 1.5. The function can, depending on the parameters given, give
 * continuous chaotic output, converge to a single value (silence) or oscillate in a cycle (tone).
 * NOTE: This UGen is experimental and not optimized currently, so is rather hoggish of CPU.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param c               Equation variable
 * @param d               Equation variable
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 */
final case class LatoocarfianL(rate: Rate, freq: GE, a: GE, b: GE, c: GE, d: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("LatoocarfianL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A cubic-interpolating sound generator based on a function given in Clifford Pickover's book Chaos In
 * Wonderland, pg 26. The function is:
 * {{{
 * x[n+1] = sin(b * y[n]) + c * sin(b * x[n])
 * y[n+1] = sin(a * y[n]) + d * sin(a * x[n])
 * }}}
 * According to Pickover, parameters a and b should be in the range from -3 to +3, and parameters c and
 * d should be in the range from 0.5 to 1.5. The function can, depending on the parameters given, give
 * continuous chaotic output, converge to a single value (silence) or oscillate in a cycle (tone).
 * NOTE: This UGen is experimental and not optimized currently, so is rather hoggish of CPU.
 */
object LatoocarfianC {
   def ar: LatoocarfianC = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz.
    * @param a               Equation variable
    * @param b               Equation variable
    * @param c               Equation variable
    * @param d               Equation variable
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = 3.0f, c: GE = 0.5f, d: GE = 0.5f, xi: GE = 0.5f, yi: GE = 0.5f) = apply(audio, freq, a, b, c, d, xi, yi)
}

/**
 * A cubic-interpolating sound generator based on a function given in Clifford Pickover's book Chaos In
 * Wonderland, pg 26. The function is:
 * {{{
 * x[n+1] = sin(b * y[n]) + c * sin(b * x[n])
 * y[n+1] = sin(a * y[n]) + d * sin(a * x[n])
 * }}}
 * According to Pickover, parameters a and b should be in the range from -3 to +3, and parameters c and
 * d should be in the range from 0.5 to 1.5. The function can, depending on the parameters given, give
 * continuous chaotic output, converge to a single value (silence) or oscillate in a cycle (tone).
 * NOTE: This UGen is experimental and not optimized currently, so is rather hoggish of CPU.
 * 
 * @param freq            Iteration frequency in Hertz.
 * @param a               Equation variable
 * @param b               Equation variable
 * @param c               Equation variable
 * @param d               Equation variable
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 */
final case class LatoocarfianC(rate: Rate, freq: GE, a: GE, b: GE, c: GE, d: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("LatoocarfianC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, d.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = (a * x[n] + c) % m
 * }}}
 * The output signal is automatically scaled to a range of [-1, 1].
 * 
 * @see [[de.sciss.synth.ugen.LinCongL]]
 * @see [[de.sciss.synth.ugen.LinCongC]]
 */
object LinCongN {
   def ar: LinCongN = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Multiplier amount
    * @param c               Increment amount
    * @param m               Modulus amount
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.1f, c: GE = 0.13f, m: GE = 1.0f, xi: GE = 0.0f) = apply(audio, freq, a, c, m, xi)
}

/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = (a * x[n] + c) % m
 * }}}
 * The output signal is automatically scaled to a range of [-1, 1].
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Multiplier amount
 * @param c               Increment amount
 * @param m               Modulus amount
 * @param xi              Initial value of x
 * 
 * @see [[de.sciss.synth.ugen.LinCongL]]
 * @see [[de.sciss.synth.ugen.LinCongC]]
 */
final case class LinCongN(rate: Rate, freq: GE, a: GE, c: GE, m: GE, xi: GE) extends UGenSource.SingleOut("LinCongN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = (a * x[n] + c) % m
 * }}}
 * The output signal is automatically scaled to a range of [-1, 1].
 * 
 * @see [[de.sciss.synth.ugen.LinCongN]]
 * @see [[de.sciss.synth.ugen.LinCongC]]
 */
object LinCongL {
   def ar: LinCongL = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Multiplier amount
    * @param c               Increment amount
    * @param m               Modulus amount
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.1f, c: GE = 0.13f, m: GE = 1.0f, xi: GE = 0.0f) = apply(audio, freq, a, c, m, xi)
}

/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = (a * x[n] + c) % m
 * }}}
 * The output signal is automatically scaled to a range of [-1, 1].
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Multiplier amount
 * @param c               Increment amount
 * @param m               Modulus amount
 * @param xi              Initial value of x
 * 
 * @see [[de.sciss.synth.ugen.LinCongN]]
 * @see [[de.sciss.synth.ugen.LinCongC]]
 */
final case class LinCongL(rate: Rate, freq: GE, a: GE, c: GE, m: GE, xi: GE) extends UGenSource.SingleOut("LinCongL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A cubic-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = (a * x[n] + c) % m
 * }}}
 * The output signal is automatically scaled to a range of [-1, 1].
 * 
 * @see [[de.sciss.synth.ugen.LinCongN]]
 * @see [[de.sciss.synth.ugen.LinCongL]]
 */
object LinCongC {
   def ar: LinCongC = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Multiplier amount
    * @param c               Increment amount
    * @param m               Modulus amount
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.1f, c: GE = 0.13f, m: GE = 1.0f, xi: GE = 0.0f) = apply(audio, freq, a, c, m, xi)
}

/**
 * A cubic-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = (a * x[n] + c) % m
 * }}}
 * The output signal is automatically scaled to a range of [-1, 1].
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Multiplier amount
 * @param c               Increment amount
 * @param m               Modulus amount
 * @param xi              Initial value of x
 * 
 * @see [[de.sciss.synth.ugen.LinCongN]]
 * @see [[de.sciss.synth.ugen.LinCongL]]
 */
final case class LinCongC(rate: Rate, freq: GE, a: GE, c: GE, m: GE, xi: GE) extends UGenSource.SingleOut("LinCongC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, c.expand, m.expand, xi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A strange attractor discovered by Edward N. Lorenz while studying mathematical models of the
 * atmosphere. The system is composed of three ordinary differential equations:
 * {{{
 * x' = s * (y - x)
 * y' = x * (r - z) - y
 * z' = x * y - b * z
 * }}}
 * The time step amount h determines the rate at which the ODE is evaluated. Higher values will
 * increase the rate, but cause more instability. A safe choice is the default amount of 0.05.
 */
object LorenzL {
   def ar: LorenzL = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param s               Equation variable
    * @param r               Equation variable
    * @param b               Equation variable
    * @param h               Integration time step
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    * @param zi              Initial value of z
    */
   def ar(freq: GE = SampleRate.ir * 0.5, s: GE = 10.0f, r: GE = 28.0f, b: GE = 2.667f, h: GE = 0.05f, xi: GE = 0.1f, yi: GE = 0.0f, zi: GE = 0.0f) = apply(audio, freq, s, r, b, h, xi, yi, zi)
}

/**
 * A strange attractor discovered by Edward N. Lorenz while studying mathematical models of the
 * atmosphere. The system is composed of three ordinary differential equations:
 * {{{
 * x' = s * (y - x)
 * y' = x * (r - z) - y
 * z' = x * y - b * z
 * }}}
 * The time step amount h determines the rate at which the ODE is evaluated. Higher values will
 * increase the rate, but cause more instability. A safe choice is the default amount of 0.05.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param s               Equation variable
 * @param r               Equation variable
 * @param b               Equation variable
 * @param h               Integration time step
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 * @param zi              Initial value of z
 */
final case class LorenzL(rate: Rate, freq: GE, s: GE, r: GE, b: GE, h: GE, xi: GE, yi: GE, zi: GE) extends UGenSource.SingleOut("LorenzL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, s.expand, r.expand, b.expand, h.expand, xi.expand, yi.expand, zi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a * pow(x[n], 2) + b * x[n] + c
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.QuadL]]
 * @see [[de.sciss.synth.ugen.QuadC]]
 */
object QuadN {
   def ar: QuadN = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param c               Equation variable
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = -1.0f, c: GE = -0.75f, xi: GE = 0.0f) = apply(audio, freq, a, b, c, xi)
}

/**
 * A non-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a * pow(x[n], 2) + b * x[n] + c
 * }}}
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param c               Equation variable
 * @param xi              Initial value of x
 * 
 * @see [[de.sciss.synth.ugen.QuadL]]
 * @see [[de.sciss.synth.ugen.QuadC]]
 */
final case class QuadN(rate: Rate, freq: GE, a: GE, b: GE, c: GE, xi: GE) extends UGenSource.SingleOut("QuadN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a * pow(x[n], 2) + b * x[n] + c
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.QuadN]]
 * @see [[de.sciss.synth.ugen.QuadC]]
 */
object QuadL {
   def ar: QuadL = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param c               Equation variable
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = -1.0f, c: GE = -0.75f, xi: GE = 0.0f) = apply(audio, freq, a, b, c, xi)
}

/**
 * A linear-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a * pow(x[n], 2) + b * x[n] + c
 * }}}
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param c               Equation variable
 * @param xi              Initial value of x
 * 
 * @see [[de.sciss.synth.ugen.QuadN]]
 * @see [[de.sciss.synth.ugen.QuadC]]
 */
final case class QuadL(rate: Rate, freq: GE, a: GE, b: GE, c: GE, xi: GE) extends UGenSource.SingleOut("QuadL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A cubic-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a * pow(x[n], 2) + b * x[n] + c
 * }}}
 * 
 * @see [[de.sciss.synth.ugen.QuadN]]
 * @see [[de.sciss.synth.ugen.QuadL]]
 */
object QuadC {
   def ar: QuadC = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param a               Equation variable
    * @param b               Equation variable
    * @param c               Equation variable
    * @param xi              Initial value of x
    */
   def ar(freq: GE = SampleRate.ir * 0.5, a: GE = 1.0f, b: GE = -1.0f, c: GE = -0.75f, xi: GE = 0.0f) = apply(audio, freq, a, b, c, xi)
}

/**
 * A cubic-interpolating sound generator based on the difference equation:
 * {{{
 * x[n+1] = a * pow(x[n], 2) + b * x[n] + c
 * }}}
 * 
 * @param freq            Iteration frequency in Hertz
 * @param a               Equation variable
 * @param b               Equation variable
 * @param c               Equation variable
 * @param xi              Initial value of x
 * 
 * @see [[de.sciss.synth.ugen.QuadN]]
 * @see [[de.sciss.synth.ugen.QuadL]]
 */
final case class QuadC(rate: Rate, freq: GE, a: GE, b: GE, c: GE, xi: GE) extends UGenSource.SingleOut("QuadC") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, a.expand, b.expand, c.expand, xi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = (x[n] + y[n+1]) % 2pi
 * y[n+1] = (y[n] + k * sin(x[n])) % 2pi
 * }}}
 * The standard map is an area preserving map of a cylinder discovered by the plasma physicist Boris
 * Chirikov.
 * 
 * @see [[de.sciss.synth.ugen.StandardL]]
 */
object StandardN {
   def ar: StandardN = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param k               Perturbation amount
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, k: GE = 1.0f, xi: GE = 0.5f, yi: GE = 0.0f) = apply(audio, freq, k, xi, yi)
}

/**
 * A non-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = (x[n] + y[n+1]) % 2pi
 * y[n+1] = (y[n] + k * sin(x[n])) % 2pi
 * }}}
 * The standard map is an area preserving map of a cylinder discovered by the plasma physicist Boris
 * Chirikov.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param k               Perturbation amount
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 * 
 * @see [[de.sciss.synth.ugen.StandardL]]
 */
final case class StandardN(rate: Rate, freq: GE, k: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("StandardN") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, k.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}

/**
 * A linear-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = (x[n] + y[n+1]) % 2pi
 * y[n+1] = (y[n] + k * sin(x[n])) % 2pi
 * }}}
 * The standard map is an area preserving map of a cylinder discovered by the plasma physicist Boris
 * Chirikov.
 * 
 * @see [[de.sciss.synth.ugen.StandardN]]
 */
object StandardL {
   def ar: StandardL = ar()
   
   /**
    * @param freq            Iteration frequency in Hertz
    * @param k               Perturbation amount
    * @param xi              Initial value of x
    * @param yi              Initial value of y
    */
   def ar(freq: GE = SampleRate.ir * 0.5, k: GE = 1.0f, xi: GE = 0.5f, yi: GE = 0.0f) = apply(audio, freq, k, xi, yi)
}

/**
 * A linear-interpolating sound generator based on the difference equations:
 * {{{
 * x[n+1] = (x[n] + y[n+1]) % 2pi
 * y[n+1] = (y[n] + k * sin(x[n])) % 2pi
 * }}}
 * The standard map is an area preserving map of a cylinder discovered by the plasma physicist Boris
 * Chirikov.
 * 
 * @param freq            Iteration frequency in Hertz
 * @param k               Perturbation amount
 * @param xi              Initial value of x
 * @param yi              Initial value of y
 * 
 * @see [[de.sciss.synth.ugen.StandardN]]
 */
final case class StandardL(rate: Rate, freq: GE, k: GE, xi: GE, yi: GE) extends UGenSource.SingleOut("StandardL") {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(freq.expand, k.expand, xi.expand, yi.expand))
   
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, rate, _args)
}