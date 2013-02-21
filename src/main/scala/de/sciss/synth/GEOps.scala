/*
 *  GE.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

import ugen.{ChannelProxy, Flatten, Poll, Impulse, LinExp, LinLin, BinaryOp, UnaryOp, MulAdd}

final class GEOps(val g: GE ) extends AnyVal {
  def `\\`(index: Int) = ChannelProxy(g, index)

  def madd(mul: GE, add: GE) = MulAdd(g, mul, add)

  def flatten = Flatten(g)

  def poll: Poll = poll()

  /**
   * Polls the output values of this graph element, and prints the result to the console.
   * This is a convenient method for wrapping this graph element in a `Poll` UGen.
   *
   * @param   trig     a signal to trigger the printing. If this is a constant, it is
   *                   interpreted as a frequency value and an `Impulse` generator of that frequency
   *                   is used instead.
   * @param   label    a string to print along with the values, in order to identify
   *                   different polls. Using the special label `"#auto"` (default) will generated
   *                   automatic useful labels using information from the polled graph element
   * @param   trigID   if greater then 0, a `"/tr"` OSC message is sent back to the client
   *                   (similar to `SendTrig`)
   *
   * @see  [[de.sciss.synth.ugen.Poll]]
   */
  def poll(trig: GE = 10, label: Optional[String] = None, trigID: GE = -1): Poll = {
    val trig1 = trig match {
      case Constant(freq) => Impulse((g.rate ?| audio) max control, freq, 0) // XXX good? or throw an error? should have a maxRate?
      case other          => other
    }
    Poll(trig1.rate, trig1, g, label.getOrElse {
      val str = g.toString
      val i   = str.indexOf('(')
      if (i >= 0) str.substring(0, i)
      else {
        val j = str.indexOf('@')
        if (j >= 0) str.substring(0, j)
        else str
      }
    }, trigID)
  }

  import UnaryOp._

  // unary ops
  def unary_- : GE    = Neg.make(g)
  // def bitNot: GE = ...
  def abs: GE         = Abs.make(g)
  // def toFloat: GE = ...
  // def toInteger: GE = ...
  def ceil: GE        = Ceil.make(g)
  def floor: GE       = Floor.make(g)
  def frac: GE        = Frac.make(g)
  def signum: GE      = Signum.make(g)
  def squared: GE     = Squared.make(g)
  def cubed: GE       = Cubed.make(g)
  def sqrt: GE        = Sqrt.make(g)
  def exp: GE         = Exp.make(g)
  def reciprocal: GE  = Reciprocal.make(g)
  def midicps: GE     = Midicps.make(g)
  def cpsmidi: GE     = Cpsmidi.make(g)
  def midiratio: GE   = Midiratio.make(g)
  def ratiomidi: GE   = Ratiomidi.make(g)
  def dbamp: GE       = Dbamp.make(g)
  def ampdb: GE       = Ampdb.make(g)
  def octcps: GE      = Octcps.make(g)
  def cpsoct: GE      = Cpsoct.make(g)
  def log: GE         = Log.make(g)
  def log2: GE        = Log2.make(g)
  def log10: GE       = Log10.make(g)
  def sin: GE         = Sin.make(g)
  def cos: GE         = Cos.make(g)
  def tan: GE         = Tan.make(g)
  def asin: GE        = Asin.make(g)
  def acos: GE        = Acos.make(g)
  def atan: GE        = Atan.make(g)
  def sinh: GE        = Sinh.make(g)
  def cosh: GE        = Cosh.make(g)
  def tanh: GE        = Tanh.make(g)
  // def rand : GE              = UnOp.make( 'rand, this )
  // def rand2 : GE             = UnOp.make( 'rand2, this )
  // def linrand : GE           = UnOp.make( 'linrand, this )
  // def bilinrand : GE         = UnOp.make( 'bilinrand, this )
  // def sum3rand : GE          = UnOp.make( 'sum3rand, this )
  def distort: GE     = Distort.make(g)

  def softclip: GE    = Softclip.make(g)

  // def coin : GE              = UnOp.make( 'coin, this )
  // def even : GE              = UnOp.make( 'even, this )
  // def odd : GE               = UnOp.make( 'odd, this )
  // def rectWindow : GE        = UnOp.make( 'rectWindow, this )
  // def hanWindow : GE         = UnOp.make( 'hanWindow, this )
  // def welWindow : GE         = UnOp.make( 'sum3rand, this )
  // def triWindow : GE         = UnOp.make( 'triWindow, this )
  def ramp: GE        = Ramp.make(g)

  def scurve: GE      = Scurve.make(g)

  // def isPositive : GE        = UnOp.make( 'isPositive, this )
  // def isNegative : GE        = UnOp.make( 'isNegative, this )
  // def isStrictlyPositive : GE= UnOp.make( 'isStrictlyPositive, this )
  // def rho : GE               = UnOp.make( 'rho, this )
  // def theta : GE             = UnOp.make( 'theta, this )

  import BinaryOp._

  // binary ops
  private def binOp(op: BinaryOp.Op, b: GE): GE = op.make(g, b)

  def +       (b: GE): GE = binOp(Plus, b)
  def -       (b: GE): GE = binOp(Minus, b)
  def *       (b: GE): GE = binOp(Times, b)
  // def div(b: GE): GE = ...
  def /       (b: GE): GE = binOp(Div, b)
  def %       (b: GE): GE = binOp(Mod, b)
  def ===     (b: GE): GE = binOp(Eq, b)
  def !==     (b: GE): GE = binOp(Neq, b)
  def <       (b: GE): GE = binOp(Lt, b)
  def >       (b: GE): GE = binOp(Gt, b)
  def <=      (b: GE): GE = binOp(Leq, b)
  def >=      (b: GE): GE = binOp(Geq, b)
  def min     (b: GE): GE = binOp(Min, b)
  def max     (b: GE): GE = binOp(Max, b)
  def &       (b: GE): GE = binOp(BitAnd, b)
  def |       (b: GE): GE = binOp(BitOr, b)
  def ^       (b: GE): GE = binOp(BitXor, b)
  // def lcm(b: GE): GE = ...
  // def gcd(b: GE): GE = ...

  def round   (b: GE): GE = binOp(Round, b)
  def roundup (b: GE): GE = binOp(Roundup, b)
  def trunc   (b: GE): GE = binOp(Trunc, b)
  def atan2   (b: GE): GE = binOp(Atan2, b)
  def hypot   (b: GE): GE = binOp(Hypot, b)
  def hypotx  (b: GE): GE = binOp(Hypotx, b)
  def pow     (b: GE): GE = binOp(Pow, b)

  // def <<(b: GE): GE = ...
  // def >>(b: GE): GE = ...
  // def unsgnRghtShift(b: GE): GE = ...
  // def fill(b: GE): GE = ...

  def ring1   (b: GE): GE = binOp(Ring1, b)
  def ring2   (b: GE): GE = binOp(Ring2, b)
  def ring3   (b: GE): GE = binOp(Ring3, b)
  def ring4   (b: GE): GE = binOp(Ring4, b)
  def difsqr  (b: GE): GE = binOp(Difsqr, b)
  def sumsqr  (b: GE): GE = binOp(Sumsqr, b)
  def sqrsum  (b: GE): GE = binOp(Sqrsum, b)
  def sqrdif  (b: GE): GE = binOp(Sqrdif, b)
  def absdif  (b: GE): GE = binOp(Absdif, b)
  def thresh  (b: GE): GE = binOp(Thresh, b)
  def amclip  (b: GE): GE = binOp(Amclip, b)
  def scaleneg(b: GE): GE = binOp(Scaleneg, b)
  def clip2   (b: GE): GE = binOp(Clip2, b)
  def excess  (b: GE): GE = binOp(Excess, b)
  def fold2   (b: GE): GE = binOp(Fold2, b)
  def wrap2   (b: GE): GE = binOp(Wrap2, b)
  def firstarg(b: GE): GE = binOp(Firstarg, b)

// def rrand(b: GE): GE    = ...
// def exprrand(b: GE): GE = ...

  def linlin(srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE): GE =
    LinLin(/* rate, */ g, srcLo, srcHi, dstLo, dstHi)

  def linexp(srcLo: GE, srcHi: GE, dstLo: GE, dstHi: GE): GE =
    LinExp(g.rate, g, srcLo, srcHi, dstLo, dstHi) // should be highest rate of all inputs? XXX
}