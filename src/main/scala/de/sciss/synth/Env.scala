///*
// *  Env.scala
// *  (ScalaCollider)
// *
// *  Copyright (c) 2008-2011 Hanns Holger Rutz. All rights reserved.
// *
// *  This software is free software; you can redistribute it and/or
// *  modify it under the terms of the GNU General Public License
// *  as published by the Free Software Foundation; either
// *  version 2, june 1991 of the License, or (at your option) any later version.
// *
// *  This software is distributed in the hope that it will be useful,
// *  but WITHOUT ANY WARRANTY; without even the implied warranty of
// *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// *  General Public License for more details.
// *
// *  You should have received a copy of the GNU General Public
// *  License (gpl.txt) along with this software; if not, write to the Free Software
// *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
// *
// *
// *  For further information, please contact Hanns Holger Rutz at
// *  contact@sciss.de
// *
// *
// *  Changelog:
// *    28-Jan-10   integrated IEnv
// */
//
//package de.sciss.synth
//
//import scala.math._
//import collection.immutable.{ IndexedSeq => IIdxSeq }
//
///**
//*    @version 0.10, 23-Apr-10
//*/
////object EnvShape {
////   type Any = EnvShape[ _ <: Rate, _ <: Rate ]
////}
//
//sealed abstract class EnvShape /*[ T <: Rate, U <: Rate ]*/ {
//   def idGE: GE[ /*T,*/ UGenIn /*[ T ]*/]          // AnyGE
//   def curvatureGE: GE[ /*U,*/ UGenIn /*[ U ]*/ ]   // AnyGE
//}
//
//sealed abstract class ConstEnvShape extends EnvShape /*[ scalar, scalar ]*/ {
//   val id: Int
//   val curvature: Float = 0f
//   def idGE: GE[ /*scalar,*/ UGenIn /*[ scalar ]*/] = id
//   val curvatureGE: GE[ /*scalar,*/ UGenIn /*[ scalar ]*/] = curvature
//
//   def levelAt( pos: Float, y1: Float, y2: Float ) : Float
//}
//case object stepShape extends ConstEnvShape {
//   val id = 0
//   def levelAt( pos: Float, y1: Float, y2: Float ) =
//      if( pos < 1f ) y1 else y2
//}
//case object linShape extends ConstEnvShape {
//   val id = 1
//   def levelAt( pos: Float, y1: Float, y2: Float ) =
//      pos * (y2 - y1) + y1
//}
//case object expShape extends ConstEnvShape {
//   val id = 2
//   def levelAt( pos: Float, y1: Float, y2: Float ) = {
//      val y1Lim = max( 0.0001f, y1 )
//      (y1Lim * pow( y2 / y1Lim, pos )).toFloat
//   }
//}
//case object sinShape extends ConstEnvShape {
//   val id = 3
//   def levelAt( pos: Float, y1: Float, y2: Float ) =
//      (y1 + (y2 - y1) * (-cos( Pi * pos ) * 0.5 + 0.5)).toFloat
//}
//case object welchShape extends ConstEnvShape {
//   val id = 4
//   def levelAt( pos: Float, y1: Float, y2: Float ) = if( y1 < y2 ) {
//      (y1 + (y2 - y1) * sin( Pi * 0.5 * pos )).toFloat
//   } else {
//      (y2 - (y2 - y1) * sin( Pi * 0.5 * (1 - pos) )).toFloat
//   }
//}
//case class curveShape( override val curvature: Float ) extends ConstEnvShape {
//   val id = 5
//   def levelAt( pos: Float, y1: Float, y2: Float ) = if( abs( curvature ) < 0.0001f ) {
//      pos * (y2 - y1) + y1
//   } else {
//      val denom	= 1.0 - exp( curvature )
//      val numer	= 1.0 - exp( pos * curvature )
//      (y1 + (y2 - y1) * (numer / denom)).toFloat
//   }
//}
//case object sqrShape extends ConstEnvShape {
//   val id = 6
//   def levelAt( pos: Float, y1: Float, y2: Float ) = {
//      val y1Pow2	= sqrt( y1 )
//      val y2Pow2	= sqrt( y2 )
//      val yPow2	= pos * (y2Pow2 - y1Pow2) + y1Pow2
//      (yPow2 * yPow2).toFloat
//   }
//}
//case object cubShape extends ConstEnvShape {
//   val id = 7
//   def levelAt( pos: Float, y1: Float, y2: Float ) = {
//      val y1Pow3	= math.pow( y1, 0.3333333 )
//      val y2Pow3	= math.pow( y2, 0.3333333 )
//      val yPow3	= pos * (y2Pow3 - y1Pow3) + y1Pow3
//      (yPow3 * yPow3 * yPow3).toFloat
//   }
//}
//case class varShape /*[ T <: Rate, U <: Rate ]*/( override val idGE: GE[ /*T,*/ UGenIn /*[ T ]*/],
//                                             override val curvatureGE: GE[ /*U,*/ UGenIn /*[ U ]*/] = 0 ) extends EnvShape /*[ T, U ] */
//
////object EnvSeg {
////   type Any = EnvSeg[ _ <: Rate, _ <: Rate, _ <: Rate, _ <: Rate ]
////}
//case class EnvSeg /*[ R <: Rate, S <: Rate, T <: Rate, U <: Rate ]*/(
//   dur: GE[ /*R,*/ UGenIn /*[ R ]*/], targetLevel: GE[ /*S,*/ UGenIn /*[ S ]*/], shape: EnvShape /*[ T, U ]*/ = linShape )
//
//import de.sciss.synth.{ EnvSeg => Sg }
//
//trait AbstractEnvFactory[ V <: AbstractEnv ] {
//   protected def create( startLevel: AnyGE, segments: Sg* /*.Any*/ ) : V
//
//	// fixed duration envelopes
//   def triangle : V = triangle()
//
//	def triangle /*[ R <: Rate, S <: Rate ]*/( dur: GE[ /* R,*/ UGenIn /*[ R ] */] = 1, level: GE[ /* S,*/ UGenIn /*[ S ]*/] = 1 )
//      /* ( implicit r: RateOrder[ R, scalar, R ])*/ : V =  {
//	  val durH = dur * 0.5f
//	  create( 0, Sg( durH, level ), Sg( durH, 0 ))
//	}
//
//   def sine : V = sine()
//
//	def sine /*[ R <: Rate, S <: Rate ]*/( dur: GE[ /* R,*/ UGenIn /*[ R ]*/] = 1, level: GE[ /* S,*/ UGenIn /*[ S ]*/] = 1 )
//      /* ( implicit r: RateOrder[ R, scalar, R ])*/ : V = {
//	  val durH = dur * 0.5f
//	  create( 0, Sg( durH, level, sinShape ), Sg( durH, 0, sinShape ))
//	}
//
//   def perc : V = perc()
//
//	def perc( attack: AnyGE = 0.01, release: AnyGE = 1, level: AnyGE = 1,
//             shape: EnvShape /*.Any*/ = curveShape( -4 )) : V =
//      create( 0, Sg( attack, level, shape ), Sg( release, 0, shape ))
//
//   def linen : V = linen()
//
//	def linen( attack: AnyGE = 0.01f, sustain: AnyGE = 1, release: AnyGE = 1,
//              level: AnyGE = 1, shape: EnvShape /*.Any*/ = linShape ) : V =
//		create( 0, Sg( attack, level, shape ), Sg( sustain, level, shape ),
//                 Sg( release, 0, shape ))
//}
//
//object Env extends AbstractEnvFactory[ Env ] {
//   protected def create( startLevel: AnyGE, segments: Sg* /*.Any*/ ) =
//      new Env( startLevel, segments )
//
//	// envelopes with sustain
//	def cutoff( release: AnyGE = 0.1f, level: AnyGE = 1, shape: EnvShape /*.Any*/ = linShape ) : Env = {
//      val releaseLevel: AnyGE = shape match {
//         case `expShape` => 1e-05f // dbamp( -100 )
//         case _ => 0
//      }
//		new Env( level, List( Sg( release, releaseLevel, shape )), 0 )
//	}
//
//	def dadsr /*[ S <: Rate, P <: Rate, B <: Rate, PB <: Rate, PS <: Rate, PSB <: Rate ]*/
//      ( delay: AnyGE = 0.1f, attack: AnyGE = 0.01f, decay: AnyGE = 0.3f,
//        sustainLevel: GE[ /* S,*/ UGenIn /*[ S ]*/] = 0.5f, release: AnyGE = 1,
//  		  peakLevel: GE[ /* P,*/ UGenIn /*[ P ]*/] = 1, shape: EnvShape /*.Any*/ = curveShape( -4 ),
//        bias: GE[ /* B,*/ UGenIn /*[ B ]*/ ] = 0 )
//      /* ( implicit rpb: RateOrder[ P, B, PB ], rps: RateOrder[ P, S, PS ], rpsb: RateOrder[ PS, B, PSB ]) */ =
//      new Env( bias, List( Sg( delay,   bias, shape ),
//                           Sg( attack,  peakLevel + bias, shape ),
//                           Sg( decay,   peakLevel * sustainLevel + bias, shape ),
//                           Sg( release, bias, shape )), 3 )
//
//	def adsr /*[ S <: Rate, P <: Rate, B <: Rate, PS <: Rate, PSB <: Rate ]*/
//      ( attack: AnyGE = 0.01f, decay: AnyGE = 0.3f, sustainLevel: GE[ /* S,*/ UGenIn /*[ S ]*/] = 0.5f,
//        release: AnyGE = 1, peakLevel: GE[ /*P,*/ UGenIn /*[ P ]*/ ] = 1, shape: EnvShape /*.Any*/ = curveShape( -4 ),
//        bias: GE[ /* B,*/ UGenIn /*[ B ]*/] = 0 ) /* ( implicit rpb: RateOrder[ P, S, PS ], rpsb: RateOrder[ PS, B, PSB ]) */ =
//		new Env( bias, List( Sg( attack, bias, shape ),
//                           Sg( decay, peakLevel * sustainLevel + bias, shape ),
//                           Sg( release, bias, shape )), 2 )
//
//	def asr( attack: AnyGE = 0.01f, level: AnyGE = 1, release: AnyGE = 1,
//            shape: EnvShape /*.Any*/ = curveShape( -4 )) =
//		new Env( 0, List( Sg( attack, level, shape ), Sg( release, 0, shape )), 1 )
//}
//
//object AbstractEnv {
//   implicit def toGE( env: AbstractEnv ) : Multi[ AnyGE ] = env.toGE
//}
//
//trait AbstractEnv {
//   val startLevel: AnyGE
//   val segments: Seq[ EnvSeg /*.Any*/ ]
//   def isSustained : Boolean
//// note: we do not define toSeq because the format is
//// significantly different so there is little sense in doing so
////	def toSeq : Seq[ GE ] ...
//   def toGE : Multi[ AnyGE ]
//}
//
//case class Env( startLevel: AnyGE, segments: Seq[ EnvSeg /*.Any*/ ],
//                releaseNode: AnyGE = -99, loopNode: AnyGE = -99 )
//extends AbstractEnv {
//
//	def toGE : Multi[ AnyGE ] = {
//      val segmIdx    = segments.toIndexedSeq
//      val sizeGE: AnyGE = segmIdx.size
//      val res: IIdxSeq[ AnyGE ] = startLevel +: sizeGE +: releaseNode +: loopNode +: segmIdx.flatMap( seg =>
//         IIdxSeq[ AnyGE ]( seg.targetLevel, seg.dur, seg.shape.idGE, seg.shape.curvatureGE ))
////      error( "NOT YET" )
//      geSeqToGE[ UGenIn ]( res )
//   }
//
//   def isSustained = releaseNode != Constant( -99 )
//}
//
//object IEnv extends AbstractEnvFactory[ IEnv ] {
//   protected def create( startLevel: AnyGE, segments: Sg* ) =
//      new IEnv( startLevel, segments )
//}
//
//case class IEnv( startLevel: AnyGE, segments: Seq[ EnvSeg ], offset: AnyGE = 0 )
//extends AbstractEnv {
//	def toGE : Multi[ AnyGE ] = {
//      val segmIdx    = segments.toIndexedSeq
//      val sizeGE: AnyGE = segmIdx.size
//      val totalDur   = segmIdx.foldLeft[ AnyGE ]( 0 )( (sum, next) => sum + next.dur )
//      val res = offset +: startLevel +: sizeGE +: totalDur +: segmIdx.flatMap( seg =>
//         IIdxSeq( seg.dur, seg.shape.idGE, seg.shape.curvatureGE, seg.targetLevel ))
//      geSeqToGE[ UGenIn ]( res )
//   }
//
//   def isSustained = false
//}