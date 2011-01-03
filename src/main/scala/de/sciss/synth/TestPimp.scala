package de.sciss.synth

import ugen.MulAdd

object TestPimp {
//   implicit def enrichAudioGE(   ge: GE[ UGenIn[ audio ]])   = new RichAudioGE(   ge )
//   implicit def enrichControlGE( ge: GE[ UGenIn[ control ]]) = new RichControlGE( ge )
//
//   class RichAudioGE( ge: GE[ UGenIn[ audio ]]) {
//      def madd( mul: GE[ AnyUGenIn ], add: GE[ AnyUGenIn ]) = MulAdd.ar( ge, mul, add )
//   }
//
//   class RichControlGE( ge: GE[ UGenIn[ control ]]) {
//      def madd( mul: GE[ AnyUGenIn ], add: GE[ AnyUGenIn ]) = MulAdd.kr( ge, mul, add )
//   }

   implicit val audioImp   = audio
   implicit val controlImp = control
   implicit val scalarImp  = scalar
   implicit val demandImp  = demand

   implicit def enrichGE[ R <: Rate ]( ge: GE[ R, UGenIn[ R ]])( implicit rate: R ) = new RichGE( rate, ge )

   class RichGE[ R <: Rate ]( rate: R, ge: GE[ R, UGenIn[ R ]]) {
      def madd( mul: AnyGE, add: AnyGE ) = MulAdd[ R ]( rate, ge, mul, add )
   }

//   class RichGE[ R <: Rate ]( rate: R, ge: GE[ UGenIn[ R ]]) {
//      def madd( mul: GE[ AnyUGenIn ], add: GE[ AnyUGenIn ]) = rate match {
//         case `audio`   => MulAdd[ R ]( rate, ge, mul, add )
//         case `control` => MulAdd[ R ]( rate, ge, mul, add )
//         case _ => error( "Illegal rate " + r )
//      }
//   }

//   def madd( mul: RatedGE[ Rate, AnyUGenIn ], add: RatedGE[ Rate, AnyUGenIn ]) : RatedGE[ R, UGenIn[ R ]] = {
//      (rate match {
//         case `audio`   => MulAdd.ar( this, mul, add )
//         case `control` => MulAdd.kr( this, mul, add )
//         case `scalar`  => this * mul + add
//         case r         => error( "Illegal rate " + r )
//      }).asInstanceOf[ RatedGE[ R, UGenIn[ R ]]]
//   }
}