/*
* Osc.scala
* (ScalaCollider-UGens)
*
* This is a synthetically generated file.
* Created: Tue Dec 28 22:21:21 CET 2010
* ScalaCollider-UGen version: 0.10
*/

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import SynthGraph._
object LFSaw {
   def ar: LFSaw[audio] = ar( )
   def kr: LFSaw[control] = kr( )
   def ar(freq: GE[AnyUGenIn] = 440.0f, phase: GE[AnyUGenIn] = 0.0f) = apply[audio](audio, freq, phase)
   def kr(freq: GE[AnyUGenIn] = 440.0f, phase: GE[AnyUGenIn] = 0.0f) = apply[control](control, freq, phase)
}
case class LFSaw[R <: Rate](rate: R, freq: GE[AnyUGenIn], phase: GE[AnyUGenIn]) extends GE[LFSawUGen[R]] {
   def expand = {
      val _freq = freq.expand
      val _phase = phase.expand
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = max(_sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => LFSawUGen(rate, _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class LFSawUGen[R <: Rate](rate: R, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, phase))
object SinOsc {
   def ar: SinOsc[audio] = ar( )
   def kr: SinOsc[control] = kr( )
   def ar(freq: GE[AnyUGenIn] = 440.0f, phase: GE[AnyUGenIn] = 0.0f) = apply[audio](audio, freq, phase)
   def kr(freq: GE[AnyUGenIn] = 440.0f, phase: GE[AnyUGenIn] = 0.0f) = apply[control](control, freq, phase)
}
case class SinOsc[R <: Rate](rate: R, freq: GE[AnyUGenIn], phase: GE[AnyUGenIn]) extends GE[SinOscUGen[R]] {
   def expand = {
      val _freq = freq.expand
      val _phase = phase.expand
      val _sz_freq = _freq.size
      val _sz_phase = _phase.size
      val _exp_ = max(_sz_freq, _sz_phase)
      IIdxSeq.tabulate(_exp_)(i => SinOscUGen(rate, _freq(i.%(_sz_freq)), _phase(i.%(_sz_phase))))
   }
}
case class SinOscUGen[R <: Rate](rate: R, freq: AnyUGenIn, phase: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(freq, phase))