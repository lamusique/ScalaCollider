package de.sciss.synth

import ugen.MulAdd

object TestPimp {
   implicit def enrichAudioGE( ge: GE[ UGenIn[ audio ]]) = new RichAudioGE( ge )

   class RichAudioGE( ge: GE[ UGenIn[ audio ]]) {
      def madd( mul: GE[ AnyUGenIn ], add: GE[ AnyUGenIn ]) = MulAdd.ar( ge, mul, add )
   }
//   def madd( mul: RatedGE[ Rate, AnyUGenIn ], add: RatedGE[ Rate, AnyUGenIn ]) : RatedGE[ R, UGenIn[ R ]] = {
//      (rate match {
//         case `audio`   => MulAdd.ar( this, mul, add )
//         case `control` => MulAdd.kr( this, mul, add )
//         case `scalar`  => this * mul + add
//         case r         => error( "Illegal rate " + r )
//      }).asInstanceOf[ RatedGE[ R, UGenIn[ R ]]]
//   }
}