package de.sciss.synth
package ugen

import collection.immutable.{IndexedSeq => IIdxSeq}

object Reduce {
   /**
    * Same result as `Mix( _ )`
    */
   def +( elem: GE )    = apply( elem, BinaryOp.Plus )
   def *( elem: GE )    = apply( elem, BinaryOp.Times )
//   def all_===( elem: GE ) = error( "TODO" )
//   def all_!==( elem: GE ) = error( "TODO" )
   def min( elem: GE )  = apply( elem, BinaryOp.Min )
   def max( elem: GE )  = apply( elem, BinaryOp.Max )
   def &( elem: GE )    = apply( elem, BinaryOp.BitAnd )
   def |( elem: GE )    = apply( elem, BinaryOp.BitOr )
   def ^( elem: GE )    = apply( elem, BinaryOp.BitXor )
}
final case class Reduce( elem: GE, op: BinaryOp.Op )
extends UGenSource.SingleOut( "Reduce." + op.name ) {
   def rate = elem.rate

   protected def makeUGens : UGenInLike = unwrap( elem.expand.outputs )

   protected def makeUGen( args: IIdxSeq[ UGenIn ]) : UGenInLike = {
      if( args.nonEmpty ) {
         args.reduceLeft( op.make1( _, _ ))
      } else UGenInGroup.empty
   }
}