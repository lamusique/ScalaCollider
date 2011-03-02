package de.sciss.synth.util

import concurrent.SyncVar
import actors.{ Channel, DaemonActor, Future, InputChannel }

trait RevocableFuture[ T ] extends Future[ T ] {
   def revoke : Unit
}

/*
 *    FutureActor in scala.actors is not very accessible...
 *    We need our own implementation of Future is seems
 */
abstract class FutureActor[ T ]( channel: Channel[ T ])
extends RevocableFuture[ T ] with DaemonActor {
   @volatile private var v: Option[T] = None

   private case object Eval

   def isSet = !v.isEmpty

   def apply(): T = {
      if( v.isEmpty ) error( "Thread-based operations not supported" )
      v.get
   }

   def respond( k: T => Unit ) {
      v.map( k( _ )) getOrElse {
         val fut = this !! Eval
         fut.inputChannel.react {
            case _ => k( v.get )
         }
      }
   }

   def inputChannel: InputChannel[ T ] = channel

   protected def body( v: SyncVar[ T ]) 

   def act() {
      val syncVar = new SyncVar[ T ]

      { body( syncVar )} andThen {
         val syncVal = syncVar.get
         v = Some( syncVal )
         channel ! syncVal
         loop { react {
            case Eval => reply()
         }}
      }
   }
}
