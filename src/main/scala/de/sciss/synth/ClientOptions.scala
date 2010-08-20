/*
 *  ClientOptions.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2010 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 *
 *
 *  Changelog:
 */

package de.sciss.synth

import java.net.InetSocketAddress

/**
 * @version 0.11, 20-Aug-10
 */
trait ClientOptionsLike {
   def clientID:     Int
   def nodeIDOffset: Int
   def addr:         Option[ InetSocketAddress ]
}

abstract class ClientOptions extends ClientOptionsLike

class ClientOptionsBuilder extends ClientOptionsLike {
   var clientID:     Int                        = 0
   var nodeIDOffset: Int                        = 1000
   var addr:         Option[ InetSocketAddress ]= None

   def build : ClientOptions = new Impl( clientID, nodeIDOffset, addr )

   private class Impl( val clientID: Int, val nodeIDOffset: Int, val addr: Option[ InetSocketAddress ])
   extends ClientOptions
}