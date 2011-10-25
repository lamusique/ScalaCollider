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

object ScalaCollider {
   val name          = "ScalaCollider"
   val version       = 0.30
   val copyright     = "(C)opyright 2008-2011 Hanns Holger Rutz"
   val isSnapshot    = false

   def versionString = {
      val s = (version + 0.001).toString.substring( 0, 4 )
      if( isSnapshot ) s + "-SNAPSHOT" else s
   }

   def main( args: Array[ String ]) {
      printInfo()
//      test4()
      sys.exit( 1 )
//      test()
   }

   def printInfo() {
      println( "\n" + name + " v" + versionString + "\n" + copyright +
         ". All rights reserved.\n\nThis is a library which cannot be executed directly.\n" )
   }

//   private def test4() {
//      import ugen._
//      SynthDef( "test" ) {
//         val b = BrownNoise.ar( Seq( 0.5, 0.5 ))
//         Out.ar( 0, HPF.ar(b) )
//      }
//   }

//   private def test3() {
//      import ugen._
//      SynthDef( "simpler" ) {
//          val basic = WhiteNoise.ar(Seq(0.03,0.03))
//          Out.ar( 0, basic )
//      }
//   }

//   private def test2() {
//      import de.sciss.synth._
//      import ugen._
//      SynthDef( "zip" ) {
//         Out.ar( 0, Zip( SinOsc.ar( Seq( 1, 2 )), SinOsc.ar( Seq( 3, 4 ))))
//      }
//   }

//   private def test() {
//      import de.sciss.synth._
//      import ugen._
//
//      val g = SynthGraph {
//         val sin1 = SinOsc.ar( Seq( 111, 222 ))
//         val sin2 = SinOsc.ar( Seq( 333, 444, 555 ))
//         val dem  = Demand.ar( Impulse.ar( 1 ), Seq( sin1, sin2 ))
//         val out  = Out.ar( 0, dem )
//      }
//      val u = g.expand
//   }
}
