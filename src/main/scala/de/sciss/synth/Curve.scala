package de.sciss.synth

import scala.math.{max, pow, cos, sin, abs, sqrt, Pi}

object Curve {
  case object step extends Curve {
    final val id = 0
  
    def levelAt(pos: Float, y1: Float, y2: Float) = if (pos < 1f) y1 else y2
  }
  
  case object linear extends Curve {
    final val id = 1
  
    def levelAt(pos: Float, y1: Float, y2: Float) = pos * (y2 - y1) + y1
  }
  val lin = linear
  
  case object exponential extends Curve {
    final val id = 2
  
    def levelAt(pos: Float, y1: Float, y2: Float) = {
      val y1Lim = max(0.0001f, y1)
      (y1Lim * pow(y2 / y1Lim, pos)).toFloat
    }
  }
  val exp = exponential
  
  case object sine extends Curve {
    final val id = 3
  
    def levelAt(pos: Float, y1: Float, y2: Float) =
      (y1 + (y2 - y1) * (-cos(Pi * pos) * 0.5 + 0.5)).toFloat
  }
  
  case object welch extends Curve {
    final val id = 4
  
    def levelAt(pos: Float, y1: Float, y2: Float) = if (y1 < y2) {
      (y1 + (y2 - y1) * sin(Pi * 0.5 * pos)).toFloat
    } else {
      (y2 - (y2 - y1) * sin(Pi * 0.5 * (1 - pos))).toFloat
    }
  }
  
  object parametric {
    final val id = 5
  }
  
  final case class parametric(/*override val */ curvature: Float) extends Curve {
    def id = parametric.id
  
    def levelAt(pos: Float, y1: Float, y2: Float) = if (abs(curvature) < 0.0001f) {
      pos * (y2 - y1) + y1
    } else {
      val denom = 1.0 - math.exp(curvature)
      val numer = 1.0 - math.exp(pos * curvature)
      (y1 + (y2 - y1) * (numer / denom)).toFloat
    }
  }
  
  case object squared extends Curve {
    final val id = 6
  
    def levelAt(pos: Float, y1: Float, y2: Float) = {
      val y1Pow2  = sqrt(y1)
      val y2Pow2  = sqrt(y2)
      val yPow2   = pos * (y2Pow2 - y1Pow2) + y1Pow2
      (yPow2 * yPow2).toFloat
    }
  }
  
  case object cubed extends Curve {
    final val id = 7
  
    def levelAt(pos: Float, y1: Float, y2: Float) = {
      val y1Pow3  = pow(y1, 0.3333333)
      val y2Pow3  = pow(y2, 0.3333333)
      val yPow3   = pos * (y2Pow3 - y1Pow3) + y1Pow3
      (yPow3 * yPow3 * yPow3).toFloat
    }
  }
}
sealed trait Curve {
  def id: Int
  def levelAt(pos: Float, y1: Float, y2: Float): Float
}