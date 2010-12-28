/*
* Line.scala
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
object Line {
   def ar: Line[audio] = ar( )
   def kr: Line[control] = kr( )
   def ar(start: GE[AnyUGenIn] = 0.0f, end: GE[AnyUGenIn] = 1.0f, dur: GE[AnyUGenIn] = 1.0f, doneAction: GE[AnyUGenIn] = doNothing) = apply[audio](audio, start, end, dur, doneAction)
   def kr(start: GE[AnyUGenIn] = 0.0f, end: GE[AnyUGenIn] = 1.0f, dur: GE[AnyUGenIn] = 1.0f, doneAction: GE[AnyUGenIn] = doNothing) = apply[control](control, start, end, dur, doneAction)
}
case class Line[R <: Rate](rate: R, start: GE[AnyUGenIn], end: GE[AnyUGenIn], dur: GE[AnyUGenIn], doneAction: GE[AnyUGenIn]) extends GE[LineUGen[R]] {
   def expand = {
      val _start = start.expand
      val _end = end.expand
      val _dur = dur.expand
      val _doneAction = doneAction.expand
      val _sz_start = _start.size
      val _sz_end = _end.size
      val _sz_dur = _dur.size
      val _sz_doneAction = _doneAction.size
      val _exp_ = max(_sz_start, _sz_end, _sz_dur, _sz_doneAction)
      IIdxSeq.tabulate(_exp_)(i => LineUGen(rate, _start(i.%(_sz_start)), _end(i.%(_sz_end)), _dur(i.%(_sz_dur)), _doneAction(i.%(_sz_doneAction))))
   }
}
case class LineUGen[R <: Rate](rate: R, start: AnyUGenIn, end: AnyUGenIn, dur: AnyUGenIn, doneAction: AnyUGenIn) extends SingleOutUGen[R](IIdxSeq(start, end, dur, doneAction)) with HasSideEffect with HasDoneFlag