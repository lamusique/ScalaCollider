/*
 *  Env.scala
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
 *    28-Jan-10   integrated IEnv
 */

package de.sciss.synth

import scala.math._
import collection.immutable.{ IndexedSeq => IIdxSeq }

/**
*    @version 0.10, 23-Apr-10
*/
//object EnvShape {
//   type Any = EnvShape[ _ <: Rate, _ <: Rate ]
//}

case object stepShape extends Env.ConstShape {
   val id = 0
   def levelAt( pos: Float, y1: Float, y2: Float ) =
      if( pos < 1f ) y1 else y2
}
case object linShape extends Env.ConstShape {
   val id = 1
   def levelAt( pos: Float, y1: Float, y2: Float ) =
      pos * (y2 - y1) + y1
}
case object expShape extends Env.ConstShape {
   val id = 2
   def levelAt( pos: Float, y1: Float, y2: Float ) = {
      val y1Lim = max( 0.0001f, y1 )
      (y1Lim * pow( y2 / y1Lim, pos )).toFloat
   }
}
case object sinShape extends Env.ConstShape {
   val id = 3
   def levelAt( pos: Float, y1: Float, y2: Float ) =
      (y1 + (y2 - y1) * (-cos( Pi * pos ) * 0.5 + 0.5)).toFloat
}
case object welchShape extends Env.ConstShape {
   val id = 4
   def levelAt( pos: Float, y1: Float, y2: Float ) = if( y1 < y2 ) {
      (y1 + (y2 - y1) * sin( Pi * 0.5 * pos )).toFloat
   } else {
      (y2 - (y2 - y1) * sin( Pi * 0.5 * (1 - pos) )).toFloat
   }
}
final case class curveShape( override val curvature: Float ) extends Env.ConstShape {
   val id = 5
   def levelAt( pos: Float, y1: Float, y2: Float ) = if( abs( curvature ) < 0.0001f ) {
      pos * (y2 - y1) + y1
   } else {
      val denom	= 1.0 - exp( curvature )
      val numer	= 1.0 - exp( pos * curvature )
      (y1 + (y2 - y1) * (numer / denom)).toFloat
   }
}
case object sqrShape extends Env.ConstShape {
   val id = 6
   def levelAt( pos: Float, y1: Float, y2: Float ) = {
      val y1Pow2	= sqrt( y1 )
      val y2Pow2	= sqrt( y2 )
      val yPow2	= pos * (y2Pow2 - y1Pow2) + y1Pow2
      (yPow2 * yPow2).toFloat
   }
}
case object cubShape extends Env.ConstShape {
   val id = 7
   def levelAt( pos: Float, y1: Float, y2: Float ) = {
      val y1Pow3	= math.pow( y1, 0.3333333 )
      val y2Pow3	= math.pow( y2, 0.3333333 )
      val yPow3	= pos * (y2Pow3 - y1Pow3) + y1Pow3
      (yPow3 * yPow3 * yPow3).toFloat
   }
}
//case class varShape[ T <: Rate, U <: Rate ]( override val idGE: GE[ T ],
//                                             override val curvatureGE: GE[ U ] = 0 ) extends EnvShape[ T, U ]
final case class varShape( override val idGE: GE, override val curvatureGE: GE = 0 ) extends Env.Shape

//object EnvSeg {
//   type Any = EnvSeg[ _ <: Rate, _ <: Rate, _ <: Rate, _ <: Rate ]
//}
//case class EnvSeg[ R <: Rate, S <: Rate, T <: Rate, U <: Rate ]( dur: GE[ R ], targetLevel: GE[ S ], shape: EnvShape[ T, U ] = linShape )

sealed trait EnvFactory[ V <: EnvLike ] {
   protected def create( startLevel: GE, segments: Env.Seg* ) : V

	// fixed duration envelopes
   def triangle : V = triangle()

	def triangle( dur: GE = 1, level: GE = 1 )
      /* ( implicit r: RateOrder[ R, scalar, R ])*/ : V =  {
	  val durH = dur * 0.5f
	  create( 0, Env.Seg( durH, level ), Env.Seg( durH, 0 ))
	}

   def sine : V = sine()

	def sine /*[ R <: Rate, S <: Rate ]*/( dur: GE = 1, level: GE = 1 )
      /* ( implicit r: RateOrder[ R, scalar, R ])*/ : V = {
	  val durH = dur * 0.5f
	  create( 0, Env.Seg( durH, level, sinShape ), Env.Seg( durH, 0, sinShape ))
	}

   def perc : V = perc()

	def perc( attack: GE = 0.01, release: GE = 1, level: GE = 1,
             shape: Env.Shape /*.Any*/ = curveShape( -4 )) : V =
      create( 0, Env.Seg( attack, level, shape ), Env.Seg( release, 0, shape ))

   def linen : V = linen()

	def linen( attack: GE = 0.01f, sustain: GE = 1, release: GE = 1,
              level: GE = 1, shape: Env.Shape /*.Any*/ = linShape ) : V =
		create( 0, Env.Seg( attack, level, shape ), Env.Seg( sustain, level, shape ),
                 Env.Seg( release, 0, shape ))
}

object Env extends EnvFactory[ Env ] {
   sealed abstract class Shape /*[ T <: Rate, U <: Rate ] */{
      def idGE: GE // GE[ T ]
      def curvatureGE: GE // GE[ U ]
   }

   final case class Seg( dur: GE, targetLevel: GE, shape: Shape = linShape )

   sealed abstract class ConstShape extends Shape /*[ scalar, scalar ]*/ {
      val id: Int
      val curvature: Float = 0f
      def idGE: GE = id
      val curvatureGE: GE = curvature

      def levelAt( pos: Float, y1: Float, y2: Float ) : Float
   }

   protected def create( startLevel: GE, segments: Seg* /*.Any*/ ) =
      new Env( startLevel, segments )

	// envelopes with sustain
	def cutoff( release: GE = 0.1f, level: GE = 1, shape: Shape /*.Any*/ = linShape ) : Env = {
      val releaseLevel: GE = shape match {
         case `expShape` => 1e-05f // dbamp( -100 )
         case _ => 0
      }
		new Env( level, List( Env.Seg( release, releaseLevel, shape )), 0 )
	}

//   def dadsr /*[ S <: Rate, P <: Rate, B <: Rate, PB <: Rate, PS <: Rate, PSB <: Rate ]*/
//      ( delay: GE = 0.1f, attack: GE = 0.01f, decay: GE = 0.3f,
//        sustainLevel: GE[ /* S,*/ UGenIn /*[ S ]*/] = 0.5f, release: GE = 1,
//          peakLevel: GE[ /* P,*/ UGenIn /*[ P ]*/] = 1, shape: EnvShape /*.Any*/ = curveShape( -4 ),
//        bias: GE[ /* B,*/ UGenIn /*[ B ]*/ ] = 0 )
//      /* ( implicit rpb: RateOrder[ P, B, PB ], rps: RateOrder[ P, S, PS ], rpsb: RateOrder[ PS, B, PSB ]) */ =
//      new Env( bias, List( Sg( delay,   bias, shape ),
//                           Sg( attack,  peakLevel + bias, shape ),
//                           Sg( decay,   peakLevel * sustainLevel + bias, shape ),
//                           Sg( release, bias, shape )), 3 )

	def dadsr /*[ S <: Rate, P <: Rate, B <: Rate, PB <: Rate, PS <: Rate, PSB <: Rate ]*/
      ( delay: GE = 0.1f, attack: GE = 0.01f, decay: GE = 0.3f,
        sustainLevel: GE = 0.5f, release: GE = 1,
  		  peakLevel: GE = 1, shape: Shape /*.Any*/ = curveShape( -4 ),
        bias: GE = 0 )
      /* ( implicit rpb: RateOrder[ P, B, PB ], rps: RateOrder[ P, S, PS ], rpsb: RateOrder[ PS, B, PSB ]) */ =
      new Env( bias, List( Seg( delay,   bias, shape ),
                           Seg( attack,  peakLevel + bias, shape ),
                           Seg( decay,   peakLevel * sustainLevel + bias, shape ),
                           Seg( release, bias, shape )), 3 )

//   def adsr /*[ S <: Rate, P <: Rate, B <: Rate, PS <: Rate, PSB <: Rate ]*/
//      ( attack: GE = 0.01f, decay: GE = 0.3f, sustainLevel: GE[ /* S,*/ UGenIn /*[ S ]*/] = 0.5f,
//        release: GE = 1, peakLevel: GE[ /*P,*/ UGenIn /*[ P ]*/ ] = 1, shape: EnvShape /*.Any*/ = curveShape( -4 ),
//        bias: GE[ /* B,*/ UGenIn /*[ B ]*/] = 0 ) /* ( implicit rpb: RateOrder[ P, S, PS ], rpsb: RateOrder[ PS, B, PSB ]) */ =
//      new Env( bias, List( Sg( attack, bias, shape ),
//                           Sg( decay, peakLevel * sustainLevel + bias, shape ),
//                           Sg( release, bias, shape )), 2 )

	def adsr /*[ S <: Rate, P <: Rate, B <: Rate, PS <: Rate, PSB <: Rate ]*/
      ( attack: GE = 0.01f, decay: GE = 0.3f, sustainLevel: GE = 0.5f,
        release: GE = 1, peakLevel: GE = 1, shape: Shape /*.Any*/ = curveShape( -4 ),
        bias: GE = 0 ) /* ( implicit rpb: RateOrder[ P, S, PS ], rpsb: RateOrder[ PS, B, PSB ]) */ =
		new Env( bias, List( Seg( attack, bias, shape ),
                           Seg( decay, peakLevel * sustainLevel + bias, shape ),
                           Seg( release, bias, shape )), 2 )

	def asr( attack: GE = 0.01f, level: GE = 1, release: GE = 1,
            shape: Shape = curveShape( -4 )) =
		new Env( 0, List( Seg( attack, level, shape ), Seg( release, 0, shape )), 1 )
}

object EnvLike {
   implicit def toGE( env: EnvLike ) : GE = env.toGE
}

sealed trait EnvLike {
   val startLevel: GE
   val segments: Seq[ Env.Seg ]
   def isSustained : Boolean
// note: we do not define toSeq because the format is
// significantly different so there is little sense in doing so
//	def toSeq : Seq[ GE ] ...
   def toGE : GE
}

final case class Env( startLevel: GE, segments: Seq[ Env.Seg ],
                releaseNode: GE = -99, loopNode: GE = -99 )
extends EnvLike {

	def toGE : GE = {
      val segmIdx    = segments.toIndexedSeq
      val sizeGE: GE = segmIdx.size
      val res: IIdxSeq[ GE ] = startLevel +: sizeGE +: releaseNode +: loopNode +: segmIdx.flatMap( seg =>
         IIdxSeq[ GE ]( seg.targetLevel, seg.dur, seg.shape.idGE, seg.shape.curvatureGE ))
//      error( "NOT YET" )
//      type X <: Rate
//      GE.fromSeq[ Rate ]( res )
      // res1// : AnyMulti // geSeqToGE[ UGenIn ]( res )
//      GE.fromSeq( res.toSeq )
      GE.fromSeq( res )
   }

   def isSustained = releaseNode != Constant( -99 )
}

object IEnv extends EnvFactory[ IEnv ] {
   protected def create( startLevel: GE, segments: Env.Seg* ) =
      new IEnv( startLevel, segments )
}

final case class IEnv( startLevel: GE, segments: Seq[ Env.Seg ], offset: GE = 0 )
extends EnvLike {
	def toGE : GE = {
      val segmIdx    = segments.toIndexedSeq
      val sizeGE: GE = segmIdx.size
      val totalDur   = segmIdx.foldLeft[ GE ]( 0 )( (sum, next) => sum + next.dur )
      val res: IIdxSeq[ GE ] = offset +: startLevel +: sizeGE +: totalDur +: segmIdx.flatMap( seg =>
         IIdxSeq[ GE ]( seg.dur, seg.shape.idGE, seg.shape.curvatureGE, seg.targetLevel ))
      //geSeqToGE[ UGenIn ]( res )
//      (res: GE) : AnyMulti

//      GE.bubbleGE( res ) // GE.fromAnySeq( res )
//      Multi.bubbleGen( res )
//      GE.fromSeq[ Rate ]( res )
//      GE.fromSeq( res )
      GE.fromSeq( res )
   }

   def isSustained = false
}