package de.sciss.synth
package ugen

////object WrapOut {
////
////}
//final case class WrapOut[R <: Rate](rate: R, bus: AnyGE, in: Multi[AnyGE]) extends ZeroOutUGenSource[R] with WritesBus {
//   protected def expandUGens = {
//      val _bus = bus.expand
//      val _in = in.mexpand
//      val _sz_bus = _bus.size
//      val _sz_in = _in.size
//      val _exp_ = maxInt(_sz_bus, _sz_in)
//      IIdxSeq.tabulate(_exp_)(i => new ZeroOutUGen("Out", rate, IIdxSeq(_bus(i.%(_sz_bus))).++(_in(i.%(_sz_in)).expand)))
//   }
//}

//object WrapType {
//   implicit def aGE( g: AnyGE ) : WrapType
//   implicit def aMulti( g: AnyMulti ) : WrapType
//   implicit def aLazy( g: Lazy ) : WrapType
//
//   private class
//}
//sealed trait WrapType
//
//object Hirzel {
//   def wrapOut[ O <: Option[ Float ], G ]( g: G, fadeTime : O = Some( 0.02f ))( implicit cons: WrapType[ G, O ]) {
//
//   }
//}

//private[synth] trait CanWrapOutLowImplicits {
//
//}

object CanWrapOut {
//   implicit def ge( g: AnyGE ) : CanWrapOut = new GEImpl( g )
   implicit def lazyElem( lz: => Lazy ) : CanWrapOut = new LazyImpl( lz )
   implicit def unit( lz: => Unit ) : CanWrapOut = new LazyImpl( lz )

   implicit def ar[ G <% Multi[ GE[ audio ]]]( g: G ) : CanWrapOut = new GEImpl[ audio ]( fdo =>
      Out.ar( "out".kr, fdo.map[ Multi[ GE[ audio ]]]( t => Multi.Joint( Multi.flatten( g ) * SynthGraph.makeFadeEnv( t ))).getOrElse( g: Multi[ GE[ audio ]]))
   )

   implicit def kr[ G <% Multi[ GE[ control ]]]( g: G ) : CanWrapOut = new GEImpl[ control ]( fdo =>
      Out.kr( "out".kr, fdo.map[ Multi[ GE[ control ]]]( t => Multi.Joint( Multi.flatten( g ) * SynthGraph.makeFadeEnv( t ))).getOrElse( g: Multi[ GE[ control ]]))
   )

//   implicit def kr( g: Multi[ GE[ control ]]) : CanWrapOut = new GEImpl( g )
//   implicit def ir( g: Multi[ GE[ scalar  ]]) : CanWrapOut = new GEImpl( g )

   private class GEImpl[ R <: Rate ]( g: Option[ Float ] => Unit ) extends CanWrapOut {
      def wrapOut( fadeTime: Option[ Float ]) = SynthGraph { g( fadeTime )}
   }

   private class LazyImpl( lz: => Unit ) extends CanWrapOut {
      def wrapOut( fadeTime: Option[ Float ]) = SynthGraph { lz }
   }
}
sealed trait CanWrapOut { def wrapOut( fadeTime: Option[ Float ] = Some( 0.02f )) : SynthGraph }