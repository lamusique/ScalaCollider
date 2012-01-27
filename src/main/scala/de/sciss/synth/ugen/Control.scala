/*
 *  Control.scala
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
package ugen

import collection.immutable.{ IndexedSeq => IIdxSeq }

/**
 *    @version 0.12, 17-May-10
 */

// ---------- Control ----------

object Control {
   /**
    *    Note: we are not providing further convenience methods,
    *    as that is the task of ControlProxyFactory...
    */
   def ir( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = apply(  scalar,  values, name )
   def kr( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = apply( control, values, name )

   def ir( values: Float* ) : Control = ir( IIdxSeq( values: _* ))
   def kr( values: Float* ) : Control = kr( IIdxSeq( values: _* ))

   final class UGen private[ugen]( rate: Rate, numChannels: Int, override val specialIndex: Int )
   extends UGen.MultiOut( "Control", rate, IIdxSeq.fill( numChannels )( rate ), IIdxSeq.empty )
}
final case class Control( rate: Rate, values: IIdxSeq[ Float ], ctrlName: Option[ String ])
extends UGenSource.MultiOut( "Control" /*, values.size */) {
//def numOutputs = values.size
   protected def makeUGens : UGenInLike = makeUGen( IIdxSeq.empty )

   protected def makeUGen( args: IIdxSeq[ UGenIn ]) : UGenInLike = {
      val specialIndex = UGenGraph.builder.addControl( values, ctrlName )
      new Control.UGen( rate, values.size, specialIndex )
   }
}

final case class ControlProxy( rate: Rate, values: IIdxSeq[ Float ], name: Option[ String ])( val factory: ControlFactory )
extends AbstractControlProxy[ ControlProxy ] // IIdxSeq.fill( values.size )( rate )

final class ControlFactory( rate: Rate ) extends AbstractControlFactory[ ControlProxy ] {
   protected def makeUGen( numChannels: Int, specialIndex: Int ) : UGen = new Control.UGen( rate, numChannels, specialIndex )
}

// ---------- TrigControl ----------

object TrigControl {
   def kr( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = TrigControl( values, name )
   def kr( values: Float* ) : TrigControl = kr( IIdxSeq( values: _* ))

   final class UGen private[ugen]( numChannels: Int, override val specialIndex: Int )
   extends UGen.MultiOut( "TrigControl", control, IIdxSeq.fill( numChannels )( control ), IIdxSeq.empty )
}
final case class TrigControl( values: IIdxSeq[ Float ], ctrlName: Option[ String ])
extends UGenSource.MultiOut( "TrigControl" /*, values.size */) with ControlRated {
//def numOutputs = values.size
   protected def makeUGens : UGenInLike = makeUGen( IIdxSeq.empty )

   protected def makeUGen( args: IIdxSeq[ UGenIn ]) : UGenInLike = {
      val specialIndex = UGenGraph.builder.addControl( values, ctrlName )
      new TrigControl.UGen( values.size, specialIndex )
   }
}

final case class TrigControlProxy( values: IIdxSeq[ Float ], name: Option[ String ])
extends AbstractControlProxy[ TrigControlProxy ] /* ( IIdxSeq.fill( values.size )( control )) */ with ControlRated {
   def factory = TrigControlFactory
}

object TrigControlFactory extends AbstractControlFactory[ TrigControlProxy ] {
   protected def makeUGen( numChannels: Int, specialIndex: Int ) : UGen = new TrigControl.UGen( numChannels, specialIndex )
}

// ---------- AudioControl ----------

object AudioControl {
   def ar( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = AudioControl( values, name )
   def ar( values: Float* ) : AudioControl = ar( IIdxSeq( values: _* ))

   final class UGen private[ugen]( numChannels: Int, override val specialIndex: Int )
   extends UGen.MultiOut( "AudioControl", audio, IIdxSeq.fill( numChannels )( audio ), IIdxSeq.empty )
}
final case class AudioControl( values: IIdxSeq[ Float ], ctrlName: Option[ String ])
extends UGenSource.MultiOut( "AudioControl" /*, values.size */) with AudioRated {
//def numOutputs = values.size
   protected def makeUGens : UGenInLike = makeUGen( IIdxSeq.empty )
   protected def makeUGen( args: IIdxSeq[ UGenIn ]) : UGenInLike = {
      val specialIndex = UGenGraph.builder.addControl( values, ctrlName )
      new AudioControl.UGen( values.size, specialIndex )
   }
}


final case class AudioControlProxy( values: IIdxSeq[ Float ], name: Option[ String ])
extends AbstractControlProxy[ AudioControlProxy ] /* ( IIdxSeq.fill( values.size )( audio )) */ with AudioRated {
   def factory = AudioControlFactory
}

object AudioControlFactory extends AbstractControlFactory[ AudioControlProxy ] {
   protected def makeUGen( numChannels: Int, specialIndex: Int ) : UGen = new AudioControl.UGen( numChannels, specialIndex )
}
