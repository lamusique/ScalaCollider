package de.sciss.synth

object SplayAzTest extends App {
  import Ops._; import ugen._

  Server.run { s =>
    play {
      val sig = WhiteNoise.ar(LFPulse.ar(0.5, Seq(0.0, -0.3333 + 1, -0.66666 + 1), 0.1)) * 0.1
      SplayAz.ar(2, sig)
      // Mix(PanAz.ar(2, sig, Seq(0.0, 2.0 * 1 / 3, 2.0 * 2 / 3)))
    }
  }
}
