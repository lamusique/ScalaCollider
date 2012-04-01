package de.sciss.synth

import org.scalatest.FunSpec
import ugen.BinaryOp

/**
 * To run only this test:
 *
 * test-only de.sciss.synth.RichNumberSuite
 */
class RichNumberSuite extends FunSpec {
   describe( "Rich number operators" ) {
      it( "should properly expand to primitives and GE" ) {
         val intInt1 = 6.round(4)
         assert( intInt1 == 8f, "found " + intInt1 )
         assert( intInt1.isInstanceOf[ Float ], "found " + intInt1.getClass )

         val intInt2 = 6.signum
         assert( intInt2 == 1, "found " + intInt2 )
         assert( intInt2.isInstanceOf[ Int ], "found " + intInt2.getClass )

         val intFloat = 6.round(4f)
         assert( intFloat == 8f, "found " + intFloat )
         assert( intFloat.isInstanceOf[ Float ], "found " + intFloat.getClass )

         val intDouble = 6.round(4.0)
         assert( intDouble == 8.0, "found " + intDouble )
         assert( intDouble.isInstanceOf[ Double ], "found " + intDouble.getClass )

         val binOp = BinaryOp( BinaryOp.Round, Constant(6f), Constant(4f) )
         val intGE = 6.round(Constant(4f))
         assert( intGE == binOp, "found " + intGE )
//         assert( intGE.isInstanceOf[ Constant ], "found " + intGE.getClass )

         val floatInt = 6f.round(4)
         assert( floatInt == 8f, "found " + floatInt )
         assert( floatInt.isInstanceOf[ Float ], "found " + floatInt.getClass )

         val floatFloat = 6f.round(4f)
         assert( floatFloat == 8f, "found " + floatFloat )
         assert( floatFloat.isInstanceOf[ Float ], "found " + floatFloat.getClass )

         val floatDouble = 6f.round(4.0)
         assert( floatDouble == 8.0, "found " + floatDouble )
         assert( floatDouble.isInstanceOf[ Double ], "found " + floatDouble.getClass )

         val floatGE = 6f.round(Constant(4f))
         assert( floatGE == binOp, "found " + floatGE )
//         assert( floatGE.isInstanceOf[ Constant ], "found " + floatGE.getClass )

         val doubleInt = 6.0.round(4)
         assert( doubleInt == 8.0, "found " + doubleInt )
         assert( doubleInt.isInstanceOf[ Double ], "found " + doubleInt.getClass )

         val doubleFloat = 6.0.round(4f)
         assert( doubleFloat == 8.0, "found " + doubleFloat )
         assert( doubleFloat.isInstanceOf[ Double ], "found " + doubleFloat.getClass )

         val doubleDouble = 6.0.round(4.0)
         assert( doubleDouble == 8.0, "found " + doubleDouble )
         assert( doubleDouble.isInstanceOf[ Double ], "found " + doubleDouble.getClass )

         val doubleGE = 6.0.round(Constant(4f))
         assert( doubleGE == binOp, "found " + doubleGE )
//         assert( doubleGE.isInstanceOf[ Constant ], "found " + doubleGE.getClass )
      }
   }
}
