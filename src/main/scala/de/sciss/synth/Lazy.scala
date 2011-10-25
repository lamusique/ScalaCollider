/*
 *  Lazy.scala
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

object Lazy {
   trait Expander[ +U ] extends Lazy /* with MaybeIndividual /* */ with Expands[ U ] */ {
//      private lazy val cache = new Cache( this )

      // this acts now as a fast unique reference
      private[synth] lazy val ref = new AnyRef

      // ---- constructor ----
      SynthGraph.builder.addLazy( this )

      final def force( b: UGenGraphBuilder ) { visit( b )}
      final def expand: U = visit( UGenGraph.builder )
      private def visit( b: UGenGraphBuilder ): U = b.visit( ref, makeUGens )
      protected def makeUGens : U
   }

//   final class Cache[ +T <: Lazy ]( val self: T ) extends Proxy with Lazy {
//      override val hashCode: Int = self.hashCode
//      def force( b: UGenGraphBuilder ) { self.force( b )}
//   }
}

trait Lazy {
   def force( b: UGenGraphBuilder ) : Unit
}