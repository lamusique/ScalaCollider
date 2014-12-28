/*
 *  NodeIDAllocator.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2015 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v2+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

final class NodeIDAllocator( user: Int, initTemp: Int ) {
   private var temp  = initTemp
   private val mask  = user << 26
//   private val perm = 2
//   private var permFreed = HashSet[ Int ]();
   private val sync = new AnyRef
    
   // equivalent to Integer:wrap (_WrapInt)
   private def wrap( x: Int, min: Int, max: Int ) : Int = {
      val width  = max - min
      val widthp = width + 1
//      val maxp   = max + 1
      val off	  = x - min
      val add    = (width - off) / widthp * widthp
      (off + add) % widthp + min
   }

   def alloc() : Int = {
      sync.synchronized {
         val x = temp
         temp = wrap( x + 1, initTemp, 0x03FFFFFF )
         x | mask
      }
   }
}