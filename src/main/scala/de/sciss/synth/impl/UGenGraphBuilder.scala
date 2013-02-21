/*
 *  UGenGraphBuilder.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
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

package de.sciss
package synth
package impl

import collection.breakOut
import collection.mutable.{Map => MMap, Buffer => MBuffer, Stack => MStack}
import collection.immutable.{IndexedSeq => IIdxSeq, Set => ISet}
import UGenGraph.RichUGen
import ugen.ControlProxyLike

object DefaultUGenGraphBuilderFactory extends UGenGraph.BuilderFactory {
  def build(graph: SynthGraph) = {
    val b = new Impl(graph)
    UGenGraph.use(b)(b.build)
  }

  private final class Impl(graph: SynthGraph) extends BasicUGenGraphBuilder {
    builder =>

    override def toString = "UGenGraph.Builder@" + hashCode.toHexString

    def build: UGenGraph = {
      var g = graph
      var controlProxies = ISet.empty[ControlProxyLike[_]]
      while (g.nonEmpty) {
        // XXX these two lines could be more efficient eventually -- using a 'clearable' SynthGraph
        controlProxies ++= g.controlProxies
        g = SynthGraph(g.sources.foreach(e => e.force(this))) // allow for further graphs being created
      }
      build(controlProxies)
    }
  }
}

object UGenGraphBuilderLike {
  // ---- IndexedUGen ----
   private final class IndexedUGen( val ugen: UGen, var index: Int, var effective: Boolean ) {
      val parents    = MBuffer.empty[ IndexedUGen ]
      var children   = MBuffer.empty[ IndexedUGen ]
      var richInputs : List[ RichUGenInBuilder ] = Nil // null

      override def toString = "IndexedUGen(" + ugen + ", " + index + ", " + effective + ") : richInputs = " + richInputs
   }

   private trait RichUGenInBuilder {
      def create : (Int, Int)
      def makeEffective() : Int
   }

   private final class RichConstant( constIdx: Int ) extends RichUGenInBuilder {
      def create = (-1, constIdx)
      def makeEffective() = 0
      override def toString = "RichConstant(" + constIdx + ")"
   }

   private final class RichUGenProxyBuilder( iu: IndexedUGen, outIdx: Int ) extends RichUGenInBuilder {
      def create = (iu.index, outIdx)
      def makeEffective() = {
         if( !iu.effective ) {
            iu.effective = true
            var numEff = 1
            iu.richInputs.foreach( numEff += _.makeEffective() )
            numEff
         } else 0
      }
      override def toString = "RichUGenProxyBuilder(" + iu + ", " + outIdx + ")"
   }
}

trait BasicUGenGraphBuilder extends UGenGraphBuilderLike {
   protected var ugens          = IIdxSeq.empty[ UGen ]
   protected var controlValues  = IIdxSeq.empty[ Float ]
   protected var controlNames   = IIdxSeq.empty[ (String, Int) ]
   protected var sourceMap      = Map.empty[ AnyRef, Any ]
}

/**
* Complete implementation of a ugen graph builder, except for the actual code that
* calls `force` on the sources of a `SynthGraph`. Implementations should call
* the `build` method passing in the control proxies for all involved synth graphs.
*/
trait UGenGraphBuilderLike extends UGenGraph.Builder {
   builder =>

   import UGenGraphBuilderLike._

   // updated during build
   protected def ugens: IIdxSeq[ UGen ]
   protected def ugens_=( seq: IIdxSeq[ UGen ]) : Unit
   protected def controlValues: IIdxSeq[ Float ]
   protected def controlValues_=( seq: IIdxSeq[ Float ]) : Unit
   protected def controlNames: IIdxSeq[ (String, Int) ]
   protected def controlNames_=( seq: IIdxSeq[ (String, Int) ]) : Unit
   protected def sourceMap: Map[ AnyRef, Any ]
   protected def sourceMap_=( map: Map[ AnyRef, Any ]) : Unit

  // this proxy function is useful because `elem.force` is package private.
  // so other projects implementing `UGenGraphBuilderLike` can use this function
  final protected def force(elem: Lazy) { elem.force(this) }

   /**
    * Finalizes the build process. It is assumed that the graph elements have been expanded at this
    * stage, having called into `addUGen` and `addControl`. The caller must collect all the control
    * proxies and pass them into this method.
    *
    * @param controlProxies   the control proxies participating in this graph
    *
    * @return  the completed `UGenGraph` build
    */
   final protected def build( controlProxies: Iterable[ ControlProxyLike[ _ ]]) : UGenGraph = {
//         val ctrlProxyMap        = buildControls( graph.controlProxies )
      val ctrlProxyMap        = buildControls( controlProxies )
      val (igens, constants)  = indexUGens( ctrlProxyMap )
      val indexedUGens        = sortUGens( igens )
      val richUGens : IIdxSeq[ RichUGen ] =
         indexedUGens.map( iu => RichUGen( iu.ugen, iu.richInputs.map( _.create )))( breakOut )
      UGenGraph( constants, controlValues, controlNames, richUGens )
   }

   private def indexUGens( ctrlProxyMap: Map[ ControlProxyLike[ _ ], (UGen, Int)]) :
      (IIdxSeq[ IndexedUGen ], IIdxSeq[ Float ]) = {

      val constantMap   = MMap.empty[ Float, RichConstant ]
      var constants     = IIdxSeq.empty[ Float ]
      var numIneff      = ugens.size
      val indexedUGens  = ugens.zipWithIndex.map { tup =>
         val ugen = tup._1
         val idx  = tup._2
         val eff  = ugen.hasSideEffect
         if( eff ) numIneff -= 1
         new IndexedUGen( ugen, idx, eff )
      }
//indexedUGens.foreach( iu => println( iu.ugen.ref ))
//val a0 = indexedUGens(1).ugen
//val a1 = indexedUGens(3).ugen
//val ee = a0.equals(a1)

      val ugenMap: Map[ AnyRef, IndexedUGen ] = indexedUGens.map( iu => (iu.ugen /* .ref */, iu) )( breakOut )
      indexedUGens.foreach { iu =>
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

            case up: UGenProxy =>
               val iui         = ugenMap( up.source /* .ref */)
               iu.parents     += iui
               iui.children   += iu
               new RichUGenProxyBuilder( iui, up.outputIndex )

            case ControlUGenOutProxy( proxy, outputIndex /* , _ */) =>
               val (ugen, off) = ctrlProxyMap( proxy )
               val iui         = ugenMap( ugen /* .ref */)
               iu.parents     += iui
               iui.children   += iu
               new RichUGenProxyBuilder( iui, off + outputIndex )

         })( breakOut )
         if( iu.effective ) iu.richInputs.foreach( numIneff -= _.makeEffective() )
      }
      val filtered: IIdxSeq[ IndexedUGen ] = if( numIneff == 0 ) indexedUGens else indexedUGens.collect {
         case iu if iu.effective =>
            iu.children = iu.children.filter( _.effective )
            iu
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
   private def sortUGens( indexedUGens: IIdxSeq[ IndexedUGen ]) : Array[ IndexedUGen ] = {
      indexedUGens.foreach( iu => iu.children = iu.children.sortWith( (a, b) => a.index > b.index ))
      val sorted  = new Array[ IndexedUGen ]( indexedUGens.size )
//      val avail   = MStack( indexedUGens.filter( _.parents.isEmpty ) : _* )
      val avail: MStack[ IndexedUGen ] = indexedUGens.collect({ case iu if iu.parents.isEmpty => iu })( breakOut )
      var cnt     = 0
      while( avail.nonEmpty ) {
         val iu   = avail.pop()
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

  final def visit[U](ref: AnyRef, init: => U): U = {
    sourceMap.getOrElse(ref, {
      val exp = init // .asInstanceOf[ U ]
      sourceMap += ref -> exp
      // exp.foreach( addUGen( _ ))
      exp
    }).asInstanceOf[U] // XXX hmmm, not so pretty...
  }

  final def addUGen(ugen: UGen) {
    ugens :+= ugen
  }

  final def addControl(values: IIdxSeq[Float], name: Option[String]): Int = {
    val specialIndex = controlValues.size
    controlValues ++= values
    name.foreach(n => controlNames :+= n -> specialIndex)
    specialIndex
  }

  private def buildControls(p: Iterable[ControlProxyLike[_]]): Map[ControlProxyLike[_], (UGen, Int)] = {
    import language.existentials
    // grmpfff...
    p.groupBy(_.factory).flatMap(tuple => {
      val (factory, proxies) = tuple
      factory.build(builder, proxies.toSeq.asInstanceOf[Seq[factory.Proxy /* XXX horrible */ ]]: _*)
      //            res.valuesIterator.foreach( tup => addUGen( tup._1 ))
      //            res
    })(breakOut)
  }
}
