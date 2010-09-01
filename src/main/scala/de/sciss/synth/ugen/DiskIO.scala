/*
 *  DiskIO.scala
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

import de.sciss.synth.{ audio, AudioRated, GE, MultiOutUGen, SideEffectUGen, SingleOutUGen, SynthGraph, UGenIn }
import SynthGraph._

object DiskOut {
   def ar( buf: GE, multi: GE ) : GE =
      for( Seq( b, m @ _* ) <- expand( (buf +: multi.outputs): _* )) yield this( b, m )
}
/**
 * A UGen which writes a signal to a soundfile on disk. To achieve this efficiently, a buffer is
 * needs to be provides which is used to buffer the incoming signal.
 *
 * '''Note''': It might be that the buffer size must be a multiple of (2 * the server's block size).
 * We haven't currently verified this, but to be safe, you should make sure this property is met. 
 *
 * @param   bufID the buffer used internally by the UGen. this number of frames in the buffer must
 *    be a power of two (this is currently not checked!). The buffer must have been initialized
 *    with a `write` command whose `leaveOpen` argument is true. Note that the number of channels of
 *    the buffer and of the input signal must be the same, otherwise `DiskOut` will fail silently
 *    (and not write anything to the file).
 * @param   multi the signal to be recorded.
 *
 * @see  [[de.sciss.synth.Buffer#write]]
 * @see  [[de.sciss.synth.ugen.DiskIn]]
 * @see  [[de.sciss.synth.ugen.RecordBuf]]
 */
// @see  [[de.sciss.synth.Buffer#write( String, AudioFileType, SampleFormat, Int, Int, Boolean, Completion )]]
case class DiskOut( buf: UGenIn, multi: Seq[ UGenIn ])
extends SingleOutUGen( (buf +: multi): _* ) with AudioRated with SideEffectUGen

object DiskIn {
   def ar( numChannels: Int, buf: GE, loop: GE = 0 ) =
      for( Seq( b, l ) <- expand( buf, loop )) yield this( numChannels, b, l )
}

/**
 * A UGen to stream in a signal from an audio file.
 * Continuously plays a longer audio file from disk. This requires a buffer to be preloaded with one buffer size of
 * sound. If loop is set to 1, the file will loop.
 *
 * '''Note''': The buffer size must be a multiple of (2 * the server's block size). See `Buffer#cue` for
 * details.
 *
 * @param   numChannels    the amount of channels the file and the buffer will have. This is an Int and hence
 *    must be pre-determined. Different SynthDefs must be created for different numbers of channels
 * @param   buf            the id of the buffer with the correct number of channels and frames (see above)
 * @param   loop           whether the file should loop when its end is reached
 *
 * @see  [[de.sciss.synth.Buffer]]
 * @see  [[de.sciss.synth.ugen.DiskOut]]
 * @see  [[de.sciss.synth.ugen.PlayBuf]]
 */
case class DiskIn( numChannels: Int, buf: UGenIn, loop: UGenIn )
extends MultiOutUGen( audio, numChannels, buf, loop ) with AudioRated with SideEffectUGen // side-effect: advancing sf offset

object VDiskIn {
   // note: argument 'rate' renamed to 'speed'
   def ar( numChannels: Int, buf: GE, speed: GE = 1, loop: GE = 0, sendID: GE = 0 ) =
      for( Seq( b, s, l, i ) <- expand( buf, speed, loop, sendID ))
         yield this( numChannels, b, s, l, i )
}
case class VDiskIn( numChannels: Int, buf: UGenIn, speed: UGenIn,
                    loop: UGenIn, sendID: UGenIn )
extends MultiOutUGen( audio, numChannels, buf, speed, loop, sendID )
with AudioRated with SideEffectUGen
 