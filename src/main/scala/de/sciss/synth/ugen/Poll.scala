/*
 *  Poll.scala
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

import de.sciss.synth.{ audio, control, Constant => c, GE, Rate, SideEffectUGen, SingleOutUGen, SynthGraph, UGenIn }
import SynthGraph._

/**
 *    @version 0.10, 28-Aug-10
 */
object Poll {
   def ar( trig: GE, in: GE, label: String, trigID: GE = -1 ) : GE =
      make( audio, trig, in, label, trigID )

   def kr( trig: GE, in: GE, label: String, trigID: GE = -1 ) : GE =
      make( control, trig, in, label, trigID )

   private def make( rate: Rate, trig: GE, in: GE, label: String, trigID: GE ) : GE =
      for( Seq( t, i, d ) <- expand( trig, in, trigID )) yield this( rate, t, i, label, d )
}
/**
 * A UGen for printing the current output value of its input to the console.
 *
 * @param   trig     a non-positive to positive transition telling Poll to return a value
 * @param   in	      the signal you want to poll
 * @param   label    a string or symbol to be printed with the polled value
 * @param   trigID   if greater then 0, a `"/tr"` OSC message is sent back to the client
 *    (similar to `SendTrig`)
 *
 * @see  [[de.sciss.synth.ugen.SendTrig]]
 */
case class Poll( rate: Rate, trig: UGenIn, in: UGenIn, label: String, trigID: UGenIn )
extends SingleOutUGen( (trig +: in +: trigID +: c( label.length ) +:
   label.getBytes().map( c( _ ))): _* ) with SideEffectUGen // not: "ISO-8859-1" but platform default
