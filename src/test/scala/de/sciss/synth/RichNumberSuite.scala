package de.sciss.synth

import org.scalatest.FunSpec
import ugen.BinaryOp

/**
 * To run only this test:
 *
 * test-only de.sciss.synth.RichNumberSuite
 */
class RichNumberSuite extends FunSpec {
   // === is occupied by GEOps
   implicit private def toEqual2(left: Any) : Equal2 = new Equal2(left)
   final private class Equal2(left: Any) {
      def ====(right: Any) : Option[String] = new Equalizer(left).===(right)
   }

   describe( "Rich number operators" ) {
      it( "should properly expand to primitives and GE" ) {
         val intInt1 = 6.round(4)
         assert( intInt1 ==== 8f )
         assert( intInt1.isInstanceOf[ Float ], "found " + intInt1.getClass )

         val intInt2 = 6.signum
         assert( intInt2 ==== 1 )
         assert( intInt2.isInstanceOf[ Int ], "found " + intInt2.getClass )

         val intFloat = 6.round(4f)
         assert( intFloat ==== 8f )
         assert( intFloat.isInstanceOf[ Float ], "found " + intFloat.getClass )

         val intDouble = 6.round(4.0)
         assert( intDouble ==== 8.0 )
         assert( intDouble.isInstanceOf[ Double ], "found " + intDouble.getClass )

         val c4      = Constant(4f)
         val binOp   = BinaryOp( BinaryOp.Round, Constant(6f), c4 )
         val intGE   = 6.round(c4)
//         assert( intGE ==== binOp )
         assert( intGE != binOp )
//         assert( intGE.isInstanceOf[ Constant ], "found " + intGE.getClass )

         val floatInt = 6f.round(4)
         assert( floatInt ==== 8f )
         assert( floatInt.isInstanceOf[ Float ], "found " + floatInt.getClass )

         val floatFloat = 6f.round(4f)
         assert( floatFloat ==== 8f )
         assert( floatFloat.isInstanceOf[ Float ], "found " + floatFloat.getClass )

         val floatDouble = 6f.round(4.0)
         assert( floatDouble ==== 8.0 )
         assert( floatDouble.isInstanceOf[ Double ], "found " + floatDouble.getClass )

         val floatGE = 6f.round(c4)
//         assert( floatGE ==== binOp )
         assert( floatGE != binOp )
//         assert( floatGE.isInstanceOf[ Constant ], "found " + floatGE.getClass )

         val doubleInt = 6.0.round(4)
         assert( doubleInt ==== 8.0)
         assert( doubleInt.isInstanceOf[ Double ], "found " + doubleInt.getClass )

         val doubleFloat = 6.0.round(4f)
         assert( doubleFloat ==== 8.0 )
         assert( doubleFloat.isInstanceOf[ Double ], "found " + doubleFloat.getClass )

         val doubleDouble = 6.0.round(4.0)
         assert( doubleDouble ==== 8.0 )
         assert( doubleDouble.isInstanceOf[ Double ], "found " + doubleDouble.getClass )

         val doubleGE = 6.0.round(c4)
//         assert( doubleGE ==== binOp )
         assert( doubleGE != binOp )
//         assert( doubleGE.isInstanceOf[ Constant ], "found " + doubleGE.getClass )
      }
   }
}
