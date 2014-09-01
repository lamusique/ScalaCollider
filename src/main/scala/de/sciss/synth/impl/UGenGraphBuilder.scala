/*
 *  UGenGraphBuilder.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2014 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v2+
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
import collection.immutable.{IndexedSeq => Vec, Set => ISet}
import UGenGraph.RichUGen
import de.sciss.synth.ugen.{Constant, UGenProxy, ControlUGenOutProxy, ControlProxyLike}
import scala.annotation.elidable

object DefaultUGenGraphBuilderFactory extends UGenGraph.BuilderFactory {
  def build(graph: SynthGraph) = {
    val b = new Impl(graph)
    UGenGraph.use(b)(b.build)
  }

  private final class Impl(graph: SynthGraph) extends BasicUGenGraphBuilder {
    builder =>

    override def toString = s"UGenGraph.Builder@${hashCode.toHexString}"

    def build: UGenGraph = {
      var g = graph
      var controlProxies = ISet.empty[ControlProxyLike]
      while (g.nonEmpty) {
        // XXX these two lines could be more efficient eventually -- using a 'clearable' SynthGraph
        controlProxies ++= g.controlProxies
        g = SynthGraph(g.sources.foreach(_.force(builder))) // allow for further graphs being created
      }
      build(controlProxies)
    }
  }
}

object UGenGraphBuilderLike {

  // ---- IndexedUGen ----
  private final class IndexedUGen(val ugen: UGen, var index: Int, var effective: Boolean) {
    val parents     = MBuffer.empty[IndexedUGen]
    var children    = MBuffer.empty[IndexedUGen]
    var richInputs  = List   .empty[RichUGenInBuilder]

    override def toString = s"IndexedUGen($ugen, $index, $effective) : richInputs = $richInputs"
  }

  private trait RichUGenInBuilder {
    def create: (Int, Int)
    def makeEffective(): Int
  }

  private final class RichConstant(constIdx: Int) extends RichUGenInBuilder {
    def create          = (-1, constIdx)
    def makeEffective() = 0

    override def toString = s"RichConstant($constIdx)"
  }

  private final class RichUGenProxyBuilder(iu: IndexedUGen, outIdx: Int) extends RichUGenInBuilder {
    def create = (iu.index, outIdx)

    def makeEffective() = {
      if (!iu.effective) {
        iu.effective = true
        var numEff   = 1
        iu.richInputs.foreach(numEff += _.makeEffective())
        numEff
      } else 0
    }

    override def toString = s"RichUGenProxyBuilder($iu, $outIdx)"
  }
}

trait BasicUGenGraphBuilder extends UGenGraphBuilderLike {
  protected var ugens         = Vec.empty[UGen]
  protected var controlValues = Vec.empty[Float]
  protected var controlNames  = Vec.empty[(String, Int)]
  protected var sourceMap     = Map.empty[AnyRef, Any]
}

/** Complete implementation of a ugen graph builder, except for the actual code that
  * calls `force` on the sources of a `SynthGraph`. Implementations should call
  * the `build` method passing in the control proxies for all involved synth graphs.
  */
trait UGenGraphBuilderLike extends UGenGraph.Builder {
  builder =>

  import UGenGraphBuilderLike._

  // updated during build
  protected var ugens         : Vec[UGen]
  protected var controlValues : Vec[Float]
  protected var controlNames  : Vec[(String, Int)]
  protected var sourceMap     : Map[AnyRef, Any]

  // this proxy function is useful because `elem.force` is package private.
  // so other projects implementing `UGenGraphBuilderLike` can use this function
  final protected def force(elem: Lazy): Unit = elem.force(this)

  /** Finalizes the build process. It is assumed that the graph elements have been expanded at this
    * stage, having called into `addUGen` and `addControl`. The caller must collect all the control
    * proxies and pass them into this method.
    *
    * @param controlProxies   the control proxies participating in this graph
    *
    * @return  the completed `UGenGraph` build
    */
  final protected def build(controlProxies: Iterable[ControlProxyLike]): UGenGraph = {
    //         val ctrlProxyMap        = buildControls( graph.controlProxies )
    val ctrlProxyMap        = buildControls(controlProxies)
    val (igens, constants)  = indexUGens(ctrlProxyMap)
    val indexedUGens        = sortUGens(igens)
    val richUGens: Vec[RichUGen] =
      indexedUGens.map(iu => RichUGen(iu.ugen, iu.richInputs.map(_.create)))(breakOut)
    UGenGraph(constants, controlValues, controlNames, richUGens)
  }

  private def indexUGens(ctrlProxyMap: Map[ControlProxyLike, (UGen, Int)]): (Vec[IndexedUGen], Vec[Float]) = {
    val constantMap   = MMap.empty[Float, RichConstant]
    val constants     = Vector.newBuilder[Float]
    var numConstants  = 0
    var numIneff      = ugens.size
    val indexedUGens  = ugens.zipWithIndex.map { case (ugen, idx) =>
      val eff = ugen.hasSideEffect
      if (eff) numIneff -= 1
      new IndexedUGen(ugen, idx, eff)
    }
    //indexedUGens.foreach( iu => println( iu.ugen.ref ))
    //val a0 = indexedUGens(1).ugen
    //val a1 = indexedUGens(3).ugen
    //val ee = a0.equals(a1)

    val ugenMap: Map[AnyRef, IndexedUGen] = indexedUGens.map(iu => (iu.ugen /* .ref */ , iu))(breakOut)
    indexedUGens.foreach { iu =>
      // XXX Warning: match not exhaustive -- "missing combination UGenOutProxy"
      // this is clearly a nasty scala bug, as UGenProxy does catch UGenOutProxy;
      // might be http://lampsvn.epfl.ch/trac/scala/ticket/4020
      iu.richInputs = iu.ugen.inputs.map {
        // don't worry -- the match _is_ exhaustive
        case Constant(value) => constantMap.getOrElse(value, {
          val rc        = new RichConstant(numConstants)
          constantMap  += value -> rc
          constants    += value
          numConstants += 1
          rc
        })

        case up: UGenProxy =>
          val iui       = ugenMap(up.source /* .ref */)
          iu.parents   += iui
          iui.children += iu
          new RichUGenProxyBuilder(iui, up.outputIndex)

        case ControlUGenOutProxy(proxy, outputIndex /* , _ */) =>
          val (ugen, off) = ctrlProxyMap(proxy)
          val iui         = ugenMap(ugen /* .ref */)
          iu.parents     += iui
          iui.children   += iu
          new RichUGenProxyBuilder(iui, off + outputIndex)

      } (breakOut)
      if (iu.effective) iu.richInputs.foreach(numIneff -= _.makeEffective())
    }
    val filtered: Vec[IndexedUGen] = if (numIneff == 0) indexedUGens
    else indexedUGens.collect {
      case iu if iu.effective =>
        iu.children = iu.children.filter(_.effective)
        iu
    }
    (filtered, constants.result())
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
  private def sortUGens(indexedUGens: Vec[IndexedUGen]): Array[IndexedUGen] = {
    indexedUGens.foreach(iu => iu.children = iu.children.sortWith((a, b) => a.index > b.index))
    val sorted = new Array[IndexedUGen](indexedUGens.size)
    //      val avail   = MStack( indexedUGens.filter( _.parents.isEmpty ) : _* )
    val avail: MStack[IndexedUGen] = indexedUGens.collect({
      case iu if iu.parents.isEmpty => iu
    })(breakOut)
    var cnt = 0
    while (avail.nonEmpty) {
      val iu      = avail.pop()
      iu.index    = cnt
      sorted(cnt) = iu
      cnt        += 1
      iu.children foreach { iuc =>
        iuc.parents.remove(iuc.parents.indexOf(iu))
        if (iuc.parents.isEmpty) /* avail =*/ avail.push(iuc)
      }
    }
    sorted
  }

  private def printSmart(x: Any): String = x match {
    case u: UGen  => u.name
    case _        => x.toString
  }

  final def visit[U](ref: AnyRef, init: => U): U = {
    log(s"visit  ${ref.hashCode.toHexString}")
    sourceMap.getOrElse(ref, {
      log(s"expand ${ref.hashCode.toHexString}...")
      val exp    = init
      log(s"...${ref.hashCode.toHexString} -> ${exp.hashCode.toHexString} ${printSmart(exp)}")
      sourceMap += ref -> exp
      exp
    }).asInstanceOf[U] // not so pretty...
  }

  var showLog = false

  @elidable(elidable.CONFIG) private def log(what: => String): Unit =
    if (showLog) println(s"<ugen-graph> $what")

  final def addUGen(ugen: UGen): Unit = {
    ugens :+= ugen
    log(s"addUGen ${ugen.name} @ ${ugen.hashCode.toHexString} ${if (ugen.isIndividual) "indiv" else ""}")
  }

  final def prependUGen(ugen: UGen): Unit = {
    ugens +:= ugen
    log(s"prependUGen ${ugen.name} @ ${ugen.hashCode.toHexString} ${if (ugen.isIndividual) "indiv" else ""}")
  }

  final def addControl(values: Vec[Float], name: Option[String]): Int = {
    val specialIndex = controlValues.size
    controlValues  ++= values
    name.foreach(n => controlNames :+= n -> specialIndex)
    log(s"addControl ${name.getOrElse("<unnamed>")} num = ${values.size}, idx = $specialIndex")
    specialIndex
  }

  private def buildControls(p: Iterable[ControlProxyLike]): Map[ControlProxyLike, (UGen, Int)] = {
    p.groupBy(_.factory).flatMap { case (factory, proxies) =>
      factory.build(builder, proxies.toIndexedSeq)
    } (breakOut)
  }
}
