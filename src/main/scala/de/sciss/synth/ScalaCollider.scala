/*
 *  ScalaCollider.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2011 Hanns Holger Rutz. All rights reserved.
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

package de.sciss.synth
import collection.immutable.{ IndexedSeq => IIdxSeq }

object ScalaCollider {
   val name          = "ScalaCollider"
   val version       = 0.24
   val copyright     = "(C)opyright 2008-2011 Hanns Holger Rutz"

   def versionString = (version + 0.001).toString.substring( 0, 4 )

   def main( args: Array[ String ]) {
      printInfo
//      test2
      System.exit( 1 )
   }

   def printInfo {
      println( "\n" + name + " v" + versionString + "\n" + copyright +
         ". All rights reserved.\n\nThis is a library which cannot be executed directly.\n" )
   }

   def test2 {
      import de.sciss.osc._
      val so = new ServerOptionsBuilder
//      so.transport = TCP
//      so.port = 44444
      Server.test( so.build ) { s =>
         println( "Booted." )
         test( s )
      }
   }

   def test3 {
//      import ugen._
//
////      val g = SynthGraph { Out.ar( 0, SinOsc.ar( mce( 333 :: 444 :: Nil )))}
//      val g = SynthGraph { SinOsc.ar( "freq".kr, "phase".kr ) + "test".ir }
//      val u = g.expand
   }

   def test( s: Server ) {
      import ugen._

      val df = SynthDef( "AnalogBubbles" ) {
         val f1  = "freq1".kr( 0.4 )
         val f2  = "freq2".kr( 8 )
         val d   = "detune".kr( 0.90375 )
//         val f   = LFSaw.ar( f1 ).madd( 24, LFSaw.ar( GE.fromSeq( List( f2, f2 * d ))( control )).madd( 3, 80 )).midicps // glissando function
         val f   = LFSaw.ar( f1 ).madd( 24, LFSaw.ar( List( f2, f2 * d )).madd( 3, 80 )).midicps // glissando function
//         val f   = LFSaw.ar( f1 ).madd( 24, LFSaw.ar( f2 ).madd( 3, 80 )).midicps // glissando function
         val x   = CombN.ar( SinOsc.ar( f ) * 0.04, 0.2, 0.2, 4 ) // echoing sine wave
//         val exp = Out.ar( 0, x ).expand
//         println( exp )
//         exp

         Out.ar( 0, x )
      }
      df.debugDump
      df.play( s )

////implicit def gugu[ R <: Rate ]( g: GE[ R, UGenIn ]) = Multi.joint
   }

   def test3( s: Server ) {
      import ugen._

// harmonic swimming
      implicit def bubble[ R <: Rate ]( g: GE[ R, UGenIn ]) : Multi[ R, GE[ R, UGenIn ]] = Multi.Joint( g )

      val syn = play() {
          val f = 50       // fundamental frequency
          val p = 20       // number of partials per channel
          val offset = Line.kr(0, -0.02, 60, doneAction=freeSelf) // causes sound to separate and fade
//         val x: AnyMulti = Mix.tabulate(p) { i =>
          val y = Mix.fill(p) { val i = 1
              val sig = SinOsc.ar( f * (i+1) ) * // freq of partial
                  LFNoise1.kr(
                      List( Rand( 2, 10 ), Rand( 2, 10 ))).madd(  // amplitude rate
                      0.02,    // amplitude scale
                     offset    // amplitude offset
                  ).max( 0 )   // clip negative amplitudes to zero
              sig // GE.bubble( sig )
          }
//         bubble( y )
//         null: Node
         y
      }
   }

//   def test {
//      import ugen._
////      SynthGraph {
////         LinLin.kr( "thresh".kr( 1.0e-2 ), 1.0e-3, 1.0e-1, 32, 4 )
////         Out.ar( 0, DC.ar( 0 ))
////      }
//
//      SynthGraph.wrapOut {
//          var n = 1        // number of keys playing
//          Mix.fill(n) {    // mix an array of notes
//              // calculate delay based on a random note
//              val pitch  = IRand(36, 89)
//              val strike = Impulse.ar(Rand(0.1,0.5), Rand(0,2*math.Pi)) * 0.1    // random period for each key
//              val hammerEnv = Decay2.ar(strike, 0.008, 0.04)    // excitation envelope
//              Pan2.ar(
//                  // array of 3 strings per note
//                  Mix.tabulate(3)( (i) => {
//                      // detune strings, calculate delay time :
//                      val detune = Array(-0.05, 0, 0.04)(i)
//                      val delayTime = 1 / (pitch + detune).midicps
//                      // each string gets own exciter :
//                      val hammer = LFNoise2.ar(3000) * hammerEnv   // 3000 Hz was chosen by ear..
//                      CombL.ar(hammer,   // used as a string resonator
//                          delayTime,     // max delay time
//                          delayTime,     // actual delay time
//                          6)             // decay time of string
//                  }),
//                  (pitch - 36)/27 - 1    // pan position: lo notes left, hi notes right
//              )
//          }
//      }
//   }
}
