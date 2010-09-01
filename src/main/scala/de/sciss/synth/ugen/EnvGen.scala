/*
 *  EnvGen.scala
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

import de.sciss.synth.{ audio, control, doNothing, ControlRated, Env, GE, IEnv, Rate, SideEffectUGen, SingleOutUGen,
                        SynthGraph, UGenIn }
import SynthGraph._

object Done extends UGen1RArgs {
   def kr( src: GE ) : GE = make( src )
}
/**
 * A UGen which monitors another UGen to see when it is finished.
 * Some UGens, such as `PlayBuf`, `RecordBuf`, `Line`, `XLine`, `EnvGen`, `Linen`, `BufRd`, `BufWr`, `DbufRd`,
 * and the Buffer delay UGens set a 'done' flag when they are finished playing. This UGen echoes that flag
 * as an explicit output signal when it is set to track a particular UGen. When the tracked UGen changes
 * to done, the output signal changes from zero to one.
 *
 * @param   src   the UGen to track
 *
 * @see  [[de.sciss.synth.ugen.PlayBuf]]
 * @see  [[de.sciss.synth.ugen.Line]]
 * @see  [[de.sciss.synth.ugen.EnvGen]]
 */
case class Done( src: UGenIn ) extends SingleOutUGen( src ) with ControlRated

object FreeSelf extends UGen1RArgs {
   def kr( in: GE ) : GE = make( in ) // we do not return in like sclang does
}
/**
 * A UGen that, when triggered, frees enclosing synth.
 * It frees the enclosing synth when the input signal crosses from non-positive to positive.
 *
 * This UGen outputs its input signal for convenience.
 *
 * @param   trig the input signal which will trigger the action.
 *
 * @see  [[de.sciss.synth.ugen.Free]]
 * @see  [[de.sciss.synth.ugen.PauseSelf]]
 */
case class FreeSelf( trig: UGenIn ) extends SingleOutUGen( trig ) with ControlRated with SideEffectUGen

object PauseSelf extends UGen1RArgs {
   def kr( trig: GE ) : GE = make( trig ) // we do not return in like sclang does
}
/**
 * A UGen that, when triggered, pauses enclosing synth.
 * It pauses the enclosing synth when the input signal crosses from non-positive to positive.
 *
 * This UGen outputs its input signal for convenience.
 *
 * @param   trig the input signal which will trigger the action.
 *
 * @see  [[de.sciss.synth.ugen.Pause]]
 * @see  [[de.sciss.synth.ugen.FreeSelf]]
 */
case class PauseSelf( trig: UGenIn ) extends SingleOutUGen( trig ) with ControlRated with SideEffectUGen

object FreeSelfWhenDone extends UGen1RArgs {
   def kr( src: GE ) : GE = make( src )
}
/**
 * A UGen that, when its input UGen is finished, frees enclosing synth.
 * This is essentially a shortcut for `FreeSelf.kr( Done.kr( src ))`, so instead
 * of providing a trigger signal it reads directly the done flag of an
 * appropriate ugen (such as `Line` or `PlayBuf`).
 *
 * This UGen outputs its input signal for convenience.
 *
 * @param   in the input signal which when finished will trigger the action.
 *
 * @see  [[de.sciss.synth.ugen.Free]]
 * @see  [[de.sciss.synth.ugen.FreeSelf]]
 * @see  [[de.sciss.synth.ugen.PauseSelfWhenDone]]
 * @see  [[de.sciss.synth.ugen.Done]]
 */
case class FreeSelfWhenDone( src: UGenIn ) extends SingleOutUGen( src )
with ControlRated with SideEffectUGen

object PauseSelfWhenDone extends UGen1RArgs {
   def kr( src: GE ) : GE = make( src )
}
/**
 * A UGen that, when its input UGen is finished, pauses enclosing synth.
 * This is essentially a shortcut for `PauseSelf.kr( Done.kr( src ))`, so instead
 * of providing a trigger signal it reads directly the done flag of an
 * appropriate ugen (such as `Line` or `PlayBuf`).
 *
 * This UGen outputs its input signal for convenience.
 *
 * @param   in the input signal which when finished will trigger the action.
 *
 * @see  [[de.sciss.synth.ugen.Pause]]
 * @see  [[de.sciss.synth.ugen.PauseSelf]]
 * @see  [[de.sciss.synth.ugen.FreeSelfWhenDone]]
 * @see  [[de.sciss.synth.ugen.Done]]
 */
case class PauseSelfWhenDone( src: UGenIn ) extends SingleOutUGen( src )
with ControlRated with SideEffectUGen

object Pause extends UGen2RArgs {
   def kr( gate: GE, node: GE ) : GE = make( gate, node )
}
/**
 * A UGen which pauses and resumes another node.
 * Note that the UGen initially assumes the node is running, that is,
 * if `gate` is initially 1, this will '''not''' resume a paused node.
 * Instead, the gate must go to zero and back to one to resume the node.
 * Additionally, this UGen will only cause action if the gate value
 * changes, that is, if the node is paused or resumed otherwise, this
 * UGen will not interfere with that action, unless the gate value is
 * adjusted.
 *
 * This UGen outputs its gate input signal for convenience.
 *
 * @param   gate     when 0, node is paused, when 1, node is resumed
 * @param   node  	the id of the node to be paused or resumed
 *
 * @see  [[de.sciss.synth.ugen.Free]]
 * @see  [[de.sciss.synth.ugen.PauseSelf]]
 */
case class Pause( gate: UGenIn, node: UGenIn )
extends SingleOutUGen( gate, node ) with ControlRated with SideEffectUGen

object Free extends UGen2RArgs {
   def kr( trig: GE, node: GE ) : GE = make( trig, node )
}
/**
 * A UGen that, when triggered, frees a given node.
 *
 * This UGen outputs its trig input signal for convenience.
 *
 * @param   trig     the trigger to cause the action
 * @param   node     the id of the target node to free upon receiving the trigger
 *
 * @see  [[de.sciss.synth.ugen.Pause]]
 * @see  [[de.sciss.synth.ugen.FreeSelf]]
 */
case class Free( trig: UGenIn, node: UGenIn )
extends SingleOutUGen( trig, node ) with ControlRated with SideEffectUGen

object EnvGen {
   def ar( envelope: Env, gate: GE = 1, levelScale: GE = 1, levelBias: GE = 0,
           timeScale: GE = 1, doneAction: GE = doNothing ) : GE = {
      for( Seq( g, ls, lb, t, d, e @ _* ) <-
         expand( (gate +: levelScale +: levelBias +: timeScale +: doneAction +: envelope.toSeq): _* ))
            yield this( audio, g, ls, lb, t, d, e )
   }
  
   def kr( envelope: Env, gate: GE = 1, levelScale: GE = 1, levelBias: GE = 0,
           timeScale: GE = 1, doneAction: GE = doNothing ) : GE = {
      for( Seq( g, ls, lb, t, d, e @ _* ) <-
         expand( (gate +: levelScale +: levelBias +: timeScale +: doneAction +: envelope.toSeq): _* ))
            yield this( control, g, ls, lb, t, d, e )
   }
}

case class EnvGen( rate: Rate, gate: UGenIn, levelScale: UGenIn, levelBias: UGenIn,
                   timeScale: UGenIn, doneAction: UGenIn, envSeq: Seq[ UGenIn ])
extends SingleOutUGen( (gate +: levelScale +: levelBias +: timeScale +: doneAction +: envSeq): _* )
with SideEffectUGen  // side-effect: done action

object IEnvGen {
   def ar( envelope: IEnv, index: GE ) : GE = {
      for( Seq( i, e @ _* ) <- expand( (index +: envelope.toSeq): _* )) yield this( audio, i, e )
   }

   def kr( envelope: IEnv, index: GE ) : GE = {
      for( Seq( i, e @ _* ) <- expand( (index +: envelope.toSeq): _* )) yield this( control, i, e )
   }
}

case class IEnvGen( rate: Rate, index: UGenIn, ienvSeq: Seq[ UGenIn ])
extends SingleOutUGen( (index +: ienvSeq): _* )

object Linen extends UGen5Args {
   def ar : GE = ar()
	def ar( gate: GE = 1, attack: GE = 0.01f, sustain: GE = 1, release: GE = 1,
           doneAction: GE = doNothing ) : GE =
      arExp( gate, attack, sustain, release, doneAction )

   def kr : GE = ar()
	def kr( gate: GE = 1, attack: GE = 0.01f, sustain: GE = 1, release: GE = 1,
           doneAction: GE = doNothing ) : GE =
      krExp( gate, attack, sustain, release, doneAction )
}
case class Linen( rate: Rate, gate: UGenIn, attack: UGenIn, sustain: UGenIn,
                  release: UGenIn, doneAction: UGenIn )
extends SingleOutUGen( gate, attack, sustain, release, doneAction )
with SideEffectUGen // side-effect: done-action