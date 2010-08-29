/*
 *  Osc.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2010 Hanns Holger Rutz. All rights reserved.
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
 *
 *
 *  Changelog:
 */

package de.sciss.synth.ugen

import de.sciss.synth.{ audio, control, doNothing, GE, Rate, SingleOutUGen, SynthGraph, UGenIn }
import SynthGraph._

/**
 *  @version  0.11, 01-Jan-10
 */
object Osc extends UGen3Args {
  def ar( buf: GE, freq: GE = 440, phase: GE = 0 ) : GE =
    arExp( buf, freq, phase )

  def kr( buf: GE, freq: GE = 440, phase: GE = 0 ) : GE =
    krExp( buf, freq, phase )
}
case class Osc( rate: Rate, buf: UGenIn, freq: UGenIn, phase: UGenIn )
extends SingleOutUGen( buf, freq, phase )

// class Osc private ( r: Rate, val buf: UGenIn, val freq: UGenIn, val phase: UGenIn )
// extends UGen( "Osc", r, List( r ), List( bufID, freq, phase )) {
// }

object SinOsc extends UGen2Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, phase: GE = 0 ) : GE = arExp( freq, phase )
   def kr : GE = kr()
   def kr( freq: GE = 440, phase: GE = 0 ) : GE = krExp( freq, phase )
}
case class SinOsc( rate: Rate, freq: UGenIn, phase: UGenIn )
extends SingleOutUGen( freq, phase )

object SinOscFB extends UGen2Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, feedback: GE = 0 ) : GE = arExp( freq, feedback )
   def kr : GE = kr()
   def kr( freq: GE = 440, feedback: GE = 0 ) : GE = krExp( freq, feedback )
}
case class SinOscFB( rate: Rate, freq: UGenIn, feedback: UGenIn )
extends SingleOutUGen( freq, feedback )

object OscN extends UGen3Args {
  def ar( buf: GE, freq: GE = 440, phase: GE = 0 ) : GE =
    arExp( buf, freq, phase )

  def kr( buf: GE, freq: GE = 440, phase: GE = 0 ) : GE =
    krExp( buf, freq, phase )
}
case class OscN( rate: Rate, buf: UGenIn, freq: UGenIn, phase: UGenIn )
extends SingleOutUGen( buf, freq, phase ) // with SideEffectUGen

object VOsc extends UGen3Args {
  def ar( bufPos: GE, freq: GE = 440, phase: GE = 0 ) : GE =
    arExp( bufPos, freq, phase )

  def kr( bufPos: GE, freq: GE = 440, phase: GE = 0 ) : GE =
    krExp( bufPos, freq, phase )
}
case class VOsc( rate: Rate, bufPos: UGenIn, freq: UGenIn, phase: UGenIn )
extends SingleOutUGen( bufPos, freq, phase ) // with SideEffectUGen

object VOsc3 extends UGen4Args {
  def ar( bufPos: GE, freq1: GE = 110, freq2: GE = 220, freq3: GE = 440 ) : GE =
    arExp( bufPos, freq1, freq2, freq3 )

  def kr( bufPos: GE, freq1: GE = 110, freq2: GE = 220, freq3: GE = 440 ) : GE =
    krExp( bufPos, freq1, freq2, freq3 )
}
case class VOsc3( rate: Rate, bufPos: UGenIn, freq1: UGenIn, freq2: UGenIn, freq3: UGenIn )
extends SingleOutUGen( bufPos, freq1, freq2, freq3 ) // with SideEffectUGen

object COsc extends UGen3Args {
  def ar( buf: GE, freq: GE = 440, beats: GE = 0.5f ) : GE =
    arExp( buf, freq, beats )
}
case class COsc( rate: Rate, buf: UGenIn, freq: UGenIn, beats: UGenIn )
extends SingleOutUGen( buf, freq, beats ) // with SideEffectUGen

object Formant extends UGen3Args {
   def ar : GE = ar()
   def ar( fundFreq: GE = 440, formFreq: GE = 1760, bw: GE = 880 ) : GE =
      arExp( fundFreq, formFreq, bw )

   def kr : GE = kr()
   def kr( fundFreq: GE = 440, formFreq: GE = 1760, bw: GE = 880 ) : GE =
      krExp( fundFreq, formFreq, bw )
}
case class Formant( rate: Rate, fundFreq: UGenIn, formFreq: UGenIn, bw: UGenIn )
extends SingleOutUGen( fundFreq, formFreq, bw )

object LFSaw extends UGen2Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, iphase: GE = 0 ) : GE = arExp( freq, iphase )
   def kr : GE = kr()
   def kr( freq: GE = 440, iphase: GE = 0 ) : GE = krExp( freq, iphase )
}
/**
 * A sawtooth oscillator UGen. The oscillator is creating an aliased sawtooth,
 * that is it does not use band-limiting. For a band-limited version use
 * `Saw` instead. The signal range is -1 to +1.
 *
 * @param   freq     frequency in Hertz
 * @param   iphase   initial phase offset. For efficiency reasons this is a
 *    value ranging from 0 to 2. Note that a phase of zero (default) means
 *    the wave starts at 0 and rises to +1 before jumping down to -1. Use
 *    a phase of 1 to have the wave start at -1.
 *
 * @see  [[de.sciss.synth.ugen.Saw]]
 */
case class LFSaw( rate: Rate, freq: UGenIn, iphase: UGenIn )
extends SingleOutUGen( freq, iphase )

object LFPar extends UGen2Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, iphase: GE = 0 ) : GE = arExp( freq, iphase )
   def kr : GE = kr()
   def kr( freq: GE = 440, iphase: GE = 0 ) : GE = krExp( freq, iphase )
}
case class LFPar( rate: Rate, freq: UGenIn, iphase: UGenIn )
extends SingleOutUGen( freq, iphase )

object LFCub extends UGen2Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, iphase: GE = 0 ) : GE = arExp( freq, iphase )
   def kr : GE = kr()
   def kr( freq: GE = 440, iphase: GE = 0 ) : GE = krExp( freq, iphase )
}
case class LFCub( rate: Rate, freq: UGenIn, iphase: UGenIn )
extends SingleOutUGen( freq, iphase )

object LFTri extends UGen2Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, iphase: GE = 0 ) : GE = arExp( freq, iphase )
   def kr : GE = kr()
   def kr( freq: GE = 440, iphase: GE = 0 ) : GE = krExp( freq, iphase )
}
case class LFTri( rate: Rate, freq: UGenIn, iphase: UGenIn )
extends SingleOutUGen( freq, iphase )

object LFGauss extends UGen5Args {
   def ar : GE = ar()
   def ar( dur: GE = 1, width: GE = 0.1f, iphase: GE = 0, loop: GE = 1,
           doneAction: GE = doNothing ) : GE =
      arExp( dur, width, iphase, loop, doneAction )

   def kr : GE = kr()
   def kr( dur: GE = 1, width: GE = 0.1f, iphase: GE = 0, loop: GE = 1,
           doneAction: GE = doNothing ) : GE =
      krExp( dur, width, iphase, loop, doneAction )
}
case class LFGauss( rate: Rate, dur: UGenIn, width: UGenIn, iphase: UGenIn,
                    loop: UGenIn, doneAction: UGenIn )
extends SingleOutUGen( dur, width, iphase, loop, doneAction )

object LFPulse extends UGen3Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, iphase: GE = 0, width: GE = 0.5f ) : GE =
      arExp( freq, iphase, width )
  
   def kr : GE = kr()
   def kr( freq: GE = 440, iphase: GE = 0, width: GE = 0.5f ) : GE =
      krExp( freq, iphase, width )
}
/**
 * A non-band-limited pulse oscillator UGen.
 * Outputs a high value of one and a low value of zero.
 *
 * @param   freq     oscillator frequency in Hertz
 * @param   iphase   initial phase offset in cycles ( `0..1` ). If you think
 *    of a buffer of one cycle of the waveform, this is the starting offset
 *    into this buffer. Hence, an `iphase` of `0.25` means that you will hear
 *    the first impulse after `0.75` periods! If you prefer to specify the
 *    perceived delay instead, you could use an `iphase` of `-0.25 + 1` which
 *    is more intuitive. Note that the phase is not automatically wrapped
 *    into the range of `0..1`, so putting an `iphase` of `-0.25` currently
 *    results in a strange initial signal which only stabilizes to the
 *    correct behaviour after one period!
 * @param   width    pulse width duty cycle from zero to one. If you want to
 *    specify the width rather in seconds, you can use the formula
 *    `width = freq * dur`, e.g. for a single sample impulse use
 *    `width = freq * SampleDur.ir`.
 *
 * @see  [[de.sciss.synth.ugen.Pulse]]
 */
case class LFPulse( rate: Rate, freq: UGenIn, iphase: UGenIn, width: UGenIn )
extends SingleOutUGen( freq, iphase, width )

object VarSaw extends UGen3Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, iphase: GE = 0, width: GE = 0.5f ) : GE =
      arExp( freq, iphase, width )

   def kr : GE = kr()
   def kr( freq: GE = 440, iphase: GE = 0, width: GE = 0.5f ) : GE =
      krExp( freq, iphase, width )
}
case class VarSaw( rate: Rate, freq: UGenIn, iphase: UGenIn, width: UGenIn )
extends SingleOutUGen( freq, iphase, width )

object Impulse extends UGen2Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, phase: GE = 0 ) : GE = arExp( freq, phase )
   def kr : GE = kr()
   def kr( freq: GE = 440, phase: GE = 0 ) : GE = krExp( freq, phase )
}
case class Impulse( rate: Rate, freq: UGenIn, phase: UGenIn )
extends SingleOutUGen( freq, phase )

object SyncSaw extends UGen2Args {
   def ar : GE = ar()
   def ar( syncFreq: GE = 440, sawFreq: GE = 440 ) : GE = arExp( syncFreq, sawFreq )
   def kr : GE = kr()
   def kr( syncFreq: GE = 440, sawFreq: GE = 440 ) : GE = krExp( syncFreq, sawFreq )
}
case class SyncSaw( rate: Rate, syncFreq: UGenIn, sawFreq: UGenIn )
extends SingleOutUGen( syncFreq, sawFreq )

object Index extends UGen2Args {
  def ar( buf: GE, in: GE = 0 ) : GE = arExp( buf, in )
  def kr( buf: GE, in: GE = 0 ) : GE = krExp( buf, in )
}
case class Index( rate: Rate, buf: UGenIn, in: UGenIn )
extends SingleOutUGen( buf, in ) // with SideEffectUGen

object WrapIndex extends UGen2Args {
  def ar( buf: GE, in: GE = 0 ) : GE = arExp( buf, in )
  def kr( buf: GE, in: GE = 0 ) : GE = krExp( buf, in )
}
case class WrapIndex( rate: Rate, buf: UGenIn, in: UGenIn )
extends SingleOutUGen( buf, in ) // with SideEffectUGen

object IndexInBetween extends UGen2Args {
  def ar( buf: GE, in: GE = 0 ) : GE = arExp( buf, in )
  def kr( buf: GE, in: GE = 0 ) : GE = krExp( buf, in )
}
case class IndexInBetween( rate: Rate, buf: UGenIn, in: UGenIn )
extends SingleOutUGen( buf, in ) // with SideEffectUGen

object DetectIndex extends UGen2Args {
  def ar( buf: GE, in: GE = 0 ) : GE = arExp( buf, in )
  def kr( buf: GE, in: GE = 0 ) : GE = krExp( buf, in )
}
case class DetectIndex( rate: Rate, buf: UGenIn, in: UGenIn )
extends SingleOutUGen( buf, in ) // with SideEffectUGen

object Shaper extends UGen2Args {
  def ar( buf: GE, in: GE = 0 ) : GE = arExp( buf, in )
  def kr( buf: GE, in: GE = 0 ) : GE = krExp( buf, in )
}
case class Shaper( rate: Rate, buf: UGenIn, in: UGenIn )
extends SingleOutUGen( buf, in )

// IndexL XXX

object DegreeToKey extends UGen3Args {
  def ar( buf: GE, in: GE, octave: GE = 12 ) : GE = arExp( buf, in, octave )
  def kr( buf: GE, in: GE, octave: GE = 12 ) : GE = krExp( buf, in, octave )
}
case class DegreeToKey( rate: Rate, buf: UGenIn, in: UGenIn, octave: UGenIn )
extends SingleOutUGen( buf, in, octave )

object Select {
   def ar( index: GE, multi: GE ) : GE = make( audio, index, multi )
   def kr( index: GE, multi: GE ) : GE = make( control, index, multi )

   private def make( rate: Rate, index: GE, multi: GE ) : GE =
      for( Seq( i, m @ _* ) <- expand( (index +: multi.outputs): _* )) yield this( rate, i, m )
}
/**
 * A UGen which selects among a sequence of inputs, according to an index signal.
 * Note that, although only one signal of the `multi` input is let through at
 * a time, sill all ugens are continuously running.
 *
 * @param   index an index signal into the channels of the `multi` argument. The index
 *    is automatically clipped to lie between `0` and `multi.numOutputs - 1`. The index
 *    is truncated to its integer part (not rounded), hence using for instance an
 *    index of `0.9` will still be interpreted as index `0`.
 * @param   multi a graph element which is composed of the channels to be indexed.
 *
 * @see  [[de.sciss.synth.ugen.TWindex]]
 */
case class Select( rate: Rate, index: UGenIn, multi: Seq[ UGenIn ])
extends SingleOutUGen( (index +: multi): _* )

object Vibrato extends UGen8Args {
   def ar : GE = ar()
   def ar( freq: GE = 440, beat: GE = 6, depth: GE = 0.02f, delay: GE = 0, onset: GE = 0,
           rateVar: GE = 0.04f, depthVar: GE = 0.1f, iphase: GE = 0 ) : GE =
      arExp( freq, beat, depth, delay, onset, rateVar, depthVar, iphase )

   def kr : GE = kr()
   def kr( freq: GE = 440, beat: GE = 6, depth: GE = 0.02f, delay: GE = 0, onset: GE = 0,
           rateVar: GE = 0.04f, depthVar: GE = 0.1f, iphase: GE = 0 ) : GE =
      krExp( freq, beat, depth, delay, onset, rateVar, depthVar, iphase )
}
case class Vibrato( rate: Rate, freq: UGenIn, beat: UGenIn, depth: UGenIn,
                    delay: UGenIn, onset: UGenIn, rateVar: UGenIn,
                    depthVar: UGenIn, iphase: UGenIn )
extends SingleOutUGen( freq, beat, depth, delay, onset, rateVar, depthVar, iphase )

// TChoose XXX
// TWChoose XXX
