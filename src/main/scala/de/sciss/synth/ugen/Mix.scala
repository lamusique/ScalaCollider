/*
*  Mix.scala
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
package ugen

import collection.immutable.{ IndexedSeq => IIdxSeq }

/**
*	@version	0.10, 09-Dec-09
*/
object Mix {
//   def apply[ R <: Rate ]( elems: Multi[ GE[ R, UGenIn[ R ]]])( implicit r: RateOrder[ R, R, R ]) : GE[ R, UGenIn[ R ]] =
//      elems.mexpand.reduceLeft( _ + _ )

//	def apply[ R <: Rate ]( in: GE[ R, UGenIn[ R ]])( implicit r: RateOrder[ R, R, R ]) : GE[ R, UGenIn[ R ]] = {
//      in.ex
//   }

	// support this common idiom
    // (corresponds to fill in sclang)
	def tabulate[ R <: Rate ]( n: Int )( fun: (Int) => GE[ R, UGenIn[ R ]])( implicit r: RateOrder[ R, R, R ]) = {
      implicit val rate = r.out
      Mix[ R ]( mce( IIdxSeq.tabulate( n )( fun )))
   }


   def fill[ R <: Rate ]( n: Int )( thunk: => GE[ R, UGenIn[ R ]])( implicit r: RateOrder[ R, R, R ]) = {
      implicit val rate = r.out
      Mix[ R ]( mce( IIdxSeq.fill( n )( thunk )))
   }
}

case class Mix[ R <: Rate ]( elems: Multi[ GE[ R, UGenIn[ R ]]])( implicit r: RateOrder[ R, R, R ])
extends LazyGE with GE[ R, UGenIn[ R ]] {
   def rate = r.out

   final def force( b: UGenGraphBuilder ) { expand( b )}
   def expand: IIdxSeq[ UGenIn[ R ]] = {
      expand( UGenGraph.builder )
   }
   private def expand( b: UGenGraphBuilder ): IIdxSeq[ UGenIn[ R ]] = b.visit( this /* cache */, expandUGens )
   protected def expandUGens : IIdxSeq[ UGenIn[ R ]] = {
      val _elems     = elems.mexpand
      val _sz_elems  = _elems.size
      if( _sz_elems > 0 ) {
         val summed = _elems.reduceLeft( _ + _ )
         summed.expand
      } else IIdxSeq.empty
   }
}
