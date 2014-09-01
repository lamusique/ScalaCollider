package de.sciss.synth

import org.scalatest.FunSpec
import ugen.{BinaryOpUGen, Constant}
import language.implicitConversions
import de.sciss.numbers.Implicits._

/*
  To run only this test:

  test-only de.sciss.synth.LocalBufSuite

 */
class LocalBufSuite extends FunSpec {
  describe("LocalBuf and MaxLocalBufs UGens") {
    it("should properly expand") {
      val df = SynthDef("test") {
        import ugen._
        val bufs = Seq.fill(2)(LocalBuf(2048, 1))
        Out.kr(0, bufs)
      }

      val out = df.graph.ugens.map { ru =>
        ru.ugen.name -> ru.ugen.inputs.map {
          case Constant(c) => c
          case _ => "U"
        }
      }

      assert(out === Vector(
        "MaxLocalBufs" -> Vector(2),
        "LocalBuf"     -> Vector(2048, 1, 0),
        "LocalBuf"     -> Vector(2048, 1, 1),
        "Out"          -> Vector(0, "U", "U")
      ))
    }
  }
}