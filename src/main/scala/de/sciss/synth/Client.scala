/*
 *  Client.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2012 Hanns Holger Rutz. All rights reserved.
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
 */

package de.sciss.synth

import java.net.InetSocketAddress

object Client {
   sealed trait ConfigLike {
      def clientID:     Int
      def nodeIDOffset: Int
      def addr:         Option[ InetSocketAddress ]
   }

   object Config {
      /**
       * Creates a new configuration builder with default settings
       */
      def apply() : ConfigBuilder = new ConfigBuilder()

      /**
       * Implicit conversion which allows you to use a `ConfigBuilder`
       * wherever a `Config` is required.
       */
      implicit def build( cb: ConfigBuilder ) : Config = cb.build
   }

   final class Config private[Client]( val clientID: Int, val nodeIDOffset: Int, val addr: Option[ InetSocketAddress ])
   extends ConfigLike {
      override def toString = "ClientOptions"
   }

   final class ConfigBuilder private[Client] () extends ConfigLike {
      var clientID:     Int                        = 0
      var nodeIDOffset: Int                        = 1000
      var addr:         Option[ InetSocketAddress ]= None

      def build : Config = new Config( clientID, nodeIDOffset, addr )
   }
}