/*
 *  Lazy.scala
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

object Lazy {
   /**
    * A convenient implementation of the `Lazy` trait for elements which typically expand
    * to ugens. This will be typically used for elements which do not directly need to
    * generate ugens but rather spawn more graph elements. For the direct generation of
    * `UGen`s, use a subtype of `UGenSource`.
    *
    * The constructor body of this trait will call `SynthGraph.builder.addLazy` to automatically
    * register this element when instantiated.
    *
    * @tparam U   the type to which this element expands, e.g. `Unit` or `UGenInLike`
    *
    * @see [[de.sciss.synth.UGenSource]]
    */
   trait Expander[ +U ] extends Lazy /* with MaybeIndividual /* */ with Expands[ U ] */ {
//      private lazy val cache = new Cache( this )

      // this acts now as a fast unique reference
      @transient private[synth] lazy val ref = new AnyRef

      // ---- constructor ----
      SynthGraph.builder.addLazy( this )

      /**
       * A final implementation of this method which calls `visit` on the builder,
       * checking if this element has already been visited, and if not, will invoke
       * the `expand` method. Therefore it is guaranteed, that the expansion to
       * ugens is performed no more than once in the graph expansion.
       */
      final def force( b: UGenGraph.Builder ) { visit( b )}

      /**
       * A final implementation of this method which looks up the current ugen graph
       * builder and then performs the expansion just as `force`, returning the
       * expanded object
       *
       * @return  the expanded object (e.g. `Unit` for a ugen with no outputs,
       *          or a single ugen, or a group of ugens)
       */
      final def expand: U = visit( UGenGraph.builder )

      private def visit( b: UGenGraph.Builder ): U = b.visit( ref, makeUGens )

      /**
       * Abstract method which must be implemented by creating the actual `UGen`s
       * during expansion. This method is at most called once during graph
       * expansion
       *
       * @return  the expanded object (depending on the type parameter `U`)
       */
      protected def makeUGens : U
   }
}

/**
 * Elements implementing the `Lazy` trait may participate in the building of a
 * `SynthGraph` body. They can be added to the current graph by calling
 * `SynthGraph.builder.addLazy`. Then, when the graph is expanded, the
 * `force` method is called on those registered elements, allowing them
 * to either spawn new graph elements or actually expand to `UGen`s which
 * can be added to the ugen graph builder argument.
 *
 * In most cases, lazy elements will expanded to ugens, and thus the subtype
 * `Lazy.Expander` is the most convenient way to implement this trait, as it already
 * does most of the logic, and provide's for `GE`s `expand` method.
 *
 * @see [[de.sciss.synth.Lazy.Expander]]
 */
trait Lazy extends Serializable {
   /**
    * This method is invoked by the `UGenGraphBuilder` instance when a `SynthGraph`
    * is expanded.
    *
    * @param b    the ugen graph builder to which expanded `UGen`s or control proxies
    *             may be added.
    */
   def force( b: UGenGraph.Builder ) : Unit
}