/*
 *  SynthGraph.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2012 Hanns Holger Rutz. All rights reserved.
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
 */

package de.sciss.synth

import collection.mutable.{Buffer => MBuffer, Set => MSet}
import collection.immutable.{IndexedSeq => IIdxSeq, Set => ISet}
import ugen.EnvGen

object SynthGraph {
   trait Builder {
      def addLazy( g: Lazy ) : Unit
      def addControlProxy( proxy: ControlProxyLike[ _ ]) : Unit
   //   def build : SynthGraph
   }

//   def wrapOut( thunk: => GE, fadeTime: Option[Float] = Some(0.02f) ) = SynthGraph {
//         val res1 = thunk
//         val rate = Rate.highest( res1.outputs.map( _.rate ): _* )
//         val res2 = if( (rate == audio) || (rate == control) ) {
//            val o: Option[ GE ] = fadeTime.map( fdt => makeFadeEnv( fdt ) * res1 )
//            val res2: GE = o getOrElse res1
////            val res2 = res1
//            val out = "out".kr
//            if( rate == audio ) {
//               Out( rate, out, res2 )
//            } else {
//               Out.kr( out, res2 )
//            }
//         } else
//            res1
////         Out( rate, "out".kr, fadeTime.map( t => res1 * makeFadeEnv( t )))
//      }

	def makeFadeEnv( fadeTime: Float ) : GE = {
		val dt			= "fadeTime".kr( fadeTime )
		val gate       = "gate".kr( 1 )
		val startVal	= (dt <= 0)
      // this is slightly more costly than what sclang does
      // (using non-linear shape plus an extra unary op),
      // but its fadeout is much smoother this way...
		EnvGen.kr( Env( startVal, List( Env.Seg( 1, 1, curveShape( -4 )), Env.Seg( 1, 0, sinShape )), 1 ),
         gate, timeScale = dt, doneAction = freeSelf ).squared
	}

//   error( "CURRENTLY DISABLED IN SYNTHETIC UGENS BRANCH" )
//   def replaceZeroesWithSilence( ge: GE ) : GE = {
//      val ins        = ge.outputs
//      val numZeroes  = ins.foldLeft( 0 )( (sum, in) => in match {
//         case Constant( 0 )   => sum + 1
//         case _               => sum
//      })
//      if( numZeroes == 0 ) {
//         ge
//      } else {
//         val silent = Silent.ar( numZeroes ).outputs.iterator
//         ins map (in => in match {
//            case Constant( 0 )   => silent.next
//            case _               => in
//         })
//      }
//   }

   // java.lang.ThreadLocal is around 30% faster than
   // using a synchronized map, plus we don't need
   // to look after its cleaning
   private val builders    = new ThreadLocal[ Builder ] {
      override protected def initialValue = BuilderDummy
   }
   def builder: Builder = builders.get

   def apply( thunk: => Any ) : SynthGraph = {
      val b    = new BuilderImpl
      val old  = builders.get()
      builders.set( b )
      try {
         thunk
         b.build
      } finally {
         builders.set( old ) // BuilderDummy
      }
   }

   /**
    * A boolean setting (defaults to `false`) which can help track down
    * bugs with graph elements being added outside a `SynthGraph` context.
    * When this setting is `true`, a warning message is printed to
    * `Console.err` with the graph element added and the stack trace,
    * indicating calls such as `SinOsc.ar` outside a
    * thread local `SynthGraph` builder.
    */
   var warnOutsideContext = false

   private object BuilderDummy extends Builder {
      def build : SynthGraph = sys.error( "Out of context" )
      private def warn( obj: => String ) {
         if( warnOutsideContext ) {
            Console.err.println( "Warning - adding SynthGraph element outside context: " + obj )
            val e = new Throwable()
            e.fillInStackTrace()
            val t  = e.getStackTrace
            val n  = t.length
            var i  = 0
            var go = true
            while( i < n && go ) {
               val c = t( i ).getClassName
               if( (c.startsWith( "de.sciss.synth." ) &&
                   (c.charAt( 15 ).isUpper || c.startsWith( "de.sciss.synth.ugen." ))) ||
                   c.startsWith( "scala.collection." )) {
                  i += 1
               } else {
                  go = false
               }
            }
            while( i < n ) {
               Console.err.println( "  at " + t( i ))
               i += 1
            }
         }
      }
      def addLazy( g: Lazy ) { warn( g.toString )}
      def addControlProxy( proxy: ControlProxyLike[ _ ]) { warn( proxy.toString )}
   }

   private class BuilderImpl extends Builder {
      private val lazies         = MBuffer.empty[ Lazy ]
      private var controlProxies = MSet.empty[ ControlProxyLike[ _ ]]

      override def toString = "SynthGraph.Builder@" + hashCode.toHexString

      def build = SynthGraph( lazies.toIndexedSeq, controlProxies.toSet )
      def addLazy( g: Lazy ) {
         lazies += g
      }

      def addControlProxy( proxy: ControlProxyLike[ _ ]) {
         controlProxies += proxy
      }
   }
}

@SerialVersionUID(3232544436683817667L) final case class SynthGraph( sources: IIdxSeq[ Lazy ], controlProxies: ISet[ ControlProxyLike[ _ ]]) {
   def isEmpty    = sources.isEmpty && controlProxies.isEmpty
   def nonEmpty   = !isEmpty
   def expand     = UGenGraph.expand( this )
}
