package de.sciss.synth

import ugen.DC

object RichNumberTests {
   4.round(6)  // ok

   4.round(6f)  // ok

   4.round(6.0)  // ok

   4f.round(6)  // ok

   4f.round(6f)  // ok

   4f.round(6.0)  // WRONG

   4.0.round(6)  // ok

   4.0.round(6f)  // ok

   4.0.round(6.0)  // ok

//   4.round(DC.kr(6))  // BROKEN

   4f.round(DC.kr(6))  // ok

   4.0.round(DC.kr(6))  // ok
}
