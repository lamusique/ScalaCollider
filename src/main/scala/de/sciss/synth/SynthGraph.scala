/*
 *  SynthGraph.scala
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

import java.io.DataOutputStream
import collection.breakOut
import collection.mutable.{ Buffer => MBuffer, Map => MMap, Set => MSet, Stack => MStack }
import collection.immutable.{ IndexedSeq => IIdxSeq, Set => ISet }
import ugen.{EnvGen, Out}

/**
 *    @version 0.12, 02-Aug-10
 */
case class UGenGraph( constants: IIdxSeq[ Float ], controlValues: IIdxSeq[ Float ],
                      controlNames: IIdxSeq[ (String, Int) ], ugens: IIdxSeq[ UGenGraph.RichUGen ]) {
//   override lazy val hashCode = ... TODO: figure out how case class calculates it...
   private[synth] def write( dos: DataOutputStream ) {
      // ---- constants ----
      dos.writeShort( constants.size )
      constants.foreach( dos.writeFloat( _ ))

      // ---- controls ----
      dos.writeShort( controlValues.size )
      controlValues.foreach( dos.writeFloat( _ ))

      dos.writeShort( controlNames.size )
      var count = 0
      controlNames.foreach( name => {
         writePascalString( dos, name._1 )
         dos.writeShort( name._2 )
         count += 1
      })

//      if( verbose ) println( "ugens.size = " + ugens.size )

      dos.writeShort( ugens.size )
      ugens.foreach( ru => {
         val ugen = ru.ugen
         writePascalString( dos, ugen.name )

         dos.writeByte( ugen.rate.id )
         dos.writeShort( ugen.numInputs )
         dos.writeShort( ugen.numOutputs )
         dos.writeShort( ugen.specialIndex )

         ru.inputSpecs.foreach( spec => {
            dos.writeShort( spec._1 )
            dos.writeShort( spec._2 )
         })
         ugen.outputs.foreach( in => dos.writeByte( in.rate.id ))
      })

      dos.writeShort( 0 ) // variants not supported
   }

   @inline private def writePascalString( dos: DataOutputStream, str: String ) {
      dos.writeByte( str.size )
      dos.write( str.getBytes )
   }
}

private[synth] object UGenHelper {
//   def maxInt( i: Int, is: Int* ) : Int = is.foldLeft( i )( math.max( _, _ ))
   def maxInt( is: Int* ) : Int = is.reduceLeft( math.max( _, _ ))
}

object SynthGraph {
   def wrapOut /*[ R <: Rate, S <: Rate ]*/( thunk: => AnyMulti, fadeTime: Option[Float] = Some(0.02f) )
                                      /*( implicit r: RateOrder[ control, R, S ])*/ =
      SynthGraph {
//         val res1 = thunk
//         val rate = res1.rate // r.in2 // .highest( res1.outputs.map( _.rate ): _* )
//         val res2 = if( (rate == audio) || (rate == control) ) {
//            val o: Option[ Multi[ AnyGE ]] = fadeTime.map( fdt => makeFadeEnv( fdt ) * res1 )
//            val res2: Multi[ AnyGE ] = o getOrElse res1
////            val res2 = res1
//            val out = "out".kr
////            if( rate == audio ) {
//               Out( rate, out, res2 )
////            } else {
////               Out.kr( out, res2 )
////            }
//         } else res1
      }

//	def makeFadeEnv( fadeTime: Float ) : AnyGE = {
//		val dt			= "fadeTime".kr( fadeTime )
//		val gate       = "gate".kr( 1 )
//		val startVal	= (dt <= 0)
//      // this is slightly more costly than what sclang does
//      // (using non-linear shape plus an extra unary op),
//      // but it fadeout is much smoother this way...
//		EnvGen.kr( Env( startVal, EnvSeg( 1, 1, curveShape( -4 )) :: EnvSeg( 1, 0, sinShape ) :: Nil, 1 ),
//         gate, timeScale = dt, doneAction = freeSelf ).squared
//	}

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
   private val builders    = new ThreadLocal[ SynthGraphBuilder ] {
      override protected def initialValue = BuilderDummy
   }
   def builder: SynthGraphBuilder = builders.get

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

   private object BuilderDummy extends SynthGraphBuilder {
      def build : SynthGraph = error( "Out of context" )
      def addLazy( g: Lazy ) {}
      def addControlProxy( proxy: ControlProxyLike[ _, _ ]) {}
   }

   private class BuilderImpl extends SynthGraphBuilder {
      private val lazies         = MBuffer.empty[ Lazy ]
      private var controlProxies = MSet.empty[ ControlProxyLike[ _, _ ]]

      def build = SynthGraph( lazies.toIndexedSeq, controlProxies.toSet )
      def addLazy( g: Lazy ) {
         lazies += g
      }

      def addControlProxy( proxy: ControlProxyLike[ _, _ ]) {
         controlProxies += proxy
      }
   }
}

case class SynthGraph( sources: IIdxSeq[ Lazy ], controlProxies: ISet[ ControlProxyLike[ _, _ ]]) {
   def expand = UGenGraph.expand( this )
}

object UGenGraph {
//   def individuate: Int = builder.individuate

   def expand( graph: SynthGraph ) : UGenGraph = {
      val b    = new BuilderImpl( graph )
      val old  = builders.get()
      builders.set( b )
      try {
         b.build
      } finally {
         builders.set( old ) // BuilderDummy
      }
   }

  // ---- rich ugen ----

   case class RichUGen( ugen: UGen, inputSpecs: Traversable[ (Int, Int) ])

   // ---- graph builder ----

   private val builders    = new ThreadLocal[ UGenGraphBuilder ] {
      override protected def initialValue = BuilderDummy
   }
   def builder: UGenGraphBuilder = builders.get

   private object BuilderDummy extends UGenGraphBuilder {
      def build : UGenGraph = outOfContext
      def addControl( values: IIdxSeq[ Float ], name: Option[ String ]) : Int = 0
//      def addControlProxy( proxy: ControlProxyLike[ _, _ ]) {}
      def addUGen( ugen: UGen ) {}
      def visit[ U <: AnyRef ]( src: Lazy, init: => U ) : U = outOfContext

      private def outOfContext : Nothing = error( "Out of context" )
   }

   private class BuilderImpl( graph: SynthGraph ) extends UGenGraphBuilder {
      builder =>

      // updated during build
      private val ugens          = MBuffer.empty[ UGen ]
      private var ugenSet        = MSet.empty[ UGen ]
      private var controlValues  = IIdxSeq.empty[ Float ]
      private var controlNames   = IIdxSeq.empty[ (String, Int) ]
//      private var controlProxies = MSet.empty[ ControlProxyLike[ _, _ ]]

      private val sourceMap      = MMap.empty[ Lazy, AnyRef ]

      def build = {
         graph.sources.foreach( _.force( builder ))
         val ctrlProxyMap        = buildControls( graph.controlProxies )
         val (igens, constants)  = indexUGens( ctrlProxyMap )
         val indexedUGens        = sortUGens( igens )
         val richUGens : IIdxSeq[ RichUGen ] =
            indexedUGens.map( iu => RichUGen( iu.ugen, iu.richInputs.map( _.create )))( breakOut )
         UGenGraph( constants, controlValues, controlNames, richUGens )
      }

      private def indexUGens( ctrlProxyMap: Map[ ControlProxyLike[ _, _ ], (UGen, Int)]) :
         (MBuffer[ IndexedUGen ], IIdxSeq[ Float ]) = {

         val constantMap   = MMap.empty[ Float, RichConstant ]
         var constants     = IIdxSeq.empty[ Float ]
         var numIneff      = ugens.size
         val indexedUGens  = ugens.zipWithIndex.map( tup => {
            val (ugen, idx) = tup
            val eff        = ugen.isInstanceOf[ HasSideEffect ]
            if( eff ) numIneff -= 1
            new IndexedUGen( ugen, idx, eff )
         })
         val ugenMap: Map[ UGen, IndexedUGen ] = indexedUGens.map( iu => (iu.ugen, iu))( breakOut )
         indexedUGens.foreach( iu => {
            // XXX Warning: match not exhaustive -- "missing combination UGenOutProxy"
            // this is clearly a nasty scala bug, as UGenProxy does catch UGenOutProxy;
            // might be http://lampsvn.epfl.ch/trac/scala/ticket/4020
            iu.richInputs = iu.ugen.inputs.map({ // don't worry -- the match _is_ exhaustive
               case Constant( value ) => constantMap.get( value ) getOrElse {
                  val rc         = new RichConstant( constants.size )
                  constantMap   += value -> rc
                  constants    :+= value
                  rc
               }
               case up: UGenProxy => {
                  val iui         = ugenMap( up.source )
                  iu.parents     += iui
                  iui.children   += iu
                  new RichUGenProxyBuilder( iui, up.outputIndex )
               }
               case ControlUGenOutProxy( proxy, outputIndex, _ ) => {
                  val (ugen, off) = ctrlProxyMap( proxy )
                  val iui         = ugenMap( ugen )
                  iu.parents     += iui
                  iui.children   += iu
                  new RichUGenProxyBuilder( iui, off + outputIndex )
               }
            })( breakOut )
            if( iu.effective ) iu.richInputs.foreach( numIneff -= _.makeEffective )
         })
         val filtered = if( numIneff == 0 ) indexedUGens else {
            val res = indexedUGens.filter( _.effective )
            res foreach { iu =>
               iu.children = iu.children.filter( _.effective )
            }
            res
         }
         (filtered, constants)
      }

      /*
       *    Note that in Scala like probably in most other languages,
       *    the UGens _can only_ be added in right topological order,
       *    as that is the only way they can refer to their inputs.
       *    However, the Synth-Definition-File-Format help documents
       *    states that depth-first order is preferable performance-
       *    wise. Truth is, performance is probably the same,
       *    mNumWireBufs might be different, so it's a space not a
       *    time issue.
       */
      private def sortUGens( indexedUGens: MBuffer[ IndexedUGen ]) : Array[ IndexedUGen ] = {
         indexedUGens.foreach( iu => iu.children = iu.children.sortWith( (a, b) => a.index > b.index ))
         val sorted  = new Array[ IndexedUGen ]( indexedUGens.size )
         val avail   = MStack( indexedUGens.filter( _.parents.isEmpty ) : _* )
         var cnt     = 0
         while( avail.nonEmpty ) {
            val iu   = avail.pop
            iu.index = cnt
            sorted( cnt ) = iu
            cnt     += 1
            iu.children foreach { iuc =>
               iuc.parents.remove( iuc.parents.indexOf( iu ))
               if( iuc.parents.isEmpty ) /* avail =*/ avail.push( iuc )
            }
         }
         sorted
      }

      def visit[ U <: AnyRef ]( src: Lazy, init: => U ) : U = {
         sourceMap.getOrElse( src, {
            val exp = init
            sourceMap += src -> exp
//            exp.foreach( addUGen( _ ))
            exp
         }).asInstanceOf[ U ] // XXX hmmm, not so pretty...
      }

      def addUGen( ugen: UGen ) {
         if( ugenSet.add( ugen )) ugens += ugen
      }

      def addControl( values: IIdxSeq[ Float ], name: Option[ String ]) : Int = {
         val specialIndex = controlValues.size
         controlValues ++= values
         name.foreach( n => controlNames :+= n -> specialIndex )
         specialIndex
      }

//      def addControlProxy( proxy: ControlProxyLike[ _, _ ]) {
//         controlProxies += proxy
//      }

      /*
       *    Manita, how simple things can get as soon as you
       *    clean up the sclang mess...
       */
      private def buildControls( p: Traversable[ ControlProxyLike[ _, _ ]]): Map[ ControlProxyLike[ _, _ ], (UGen, Int) ] = {
         p.groupBy( _.factory ).flatMap( tuple => {
            val (factory, proxies) = tuple
            factory.build( builder, proxies.toSeq: _* )
//            res.valuesIterator.foreach( tup => addUGen( tup._1 ))
//            res
         })( breakOut )
      }

      // ---- IndexedUGen ----
      private class IndexedUGen( val ugen: UGen, var index: Int, var effective: Boolean ) {
         val parents    = MBuffer.empty[ IndexedUGen ]
         var children   = MBuffer.empty[ IndexedUGen ]
         var richInputs : List[ RichUGenInBuilder ] = null

         override def toString = "IndexedUGen(" + ugen + ", " + index + ", " + effective + ") : richInputs = " + richInputs
      }

      private trait RichUGenInBuilder {
         def create : (Int, Int)
         def makeEffective : Int
      }

      private class RichConstant( constIdx: Int ) extends RichUGenInBuilder {
         def create = (-1, constIdx)
         def makeEffective = 0
         override def toString = "RichConstant(" + constIdx + ")"
      }

      private class RichUGenProxyBuilder( iu: IndexedUGen, outIdx: Int ) extends RichUGenInBuilder {
         def create = (iu.index, outIdx)
         def makeEffective = {
            if( !iu.effective ) {
               iu.effective = true
               var numEff = 1
               iu.richInputs.foreach( numEff += _.makeEffective )
               numEff
            } else 0
         }
         override def toString = "RichUGenProxyBuilder(" + iu + ", " + outIdx + ")"
      }
   }
}