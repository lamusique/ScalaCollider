/*
 *  Control.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2011 Hanns Holger Rutz. All rights reserved.
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

package de.sciss.synth.ugen

import collection.immutable.{ IndexedSeq => IIdxSeq }
import collection.breakOut
import de.sciss.synth._

/**
 *    @version 0.12, 17-May-10
 */

// ---------- Control ----------

object Control {
   /**
    *    Note: we are not providing further convenience methods,
    *    as that is the task of ControlProxyFactory...
    */
   def ir( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = apply[ scalar ](  scalar,  values, name )
   def kr( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = apply[ control ]( control, values, name )

   def ir( values: Float* ) : Control[ scalar ] = ir( IIdxSeq( values: _* ))
   def kr( values: Float* ) : Control[ control ] = kr( IIdxSeq( values: _* ))
}
case class Control[ R <: Rate ]( rate: R, values: IIdxSeq[ Float ], name: Option[ String ])
extends MultiOutUGenSource[ R, ControlUGen[ R ]] {
   protected def expandUGens = {
      val specialIndex = UGenGraph.builder.addControl( values, name )
      IIdxSeq( ControlUGen( rate, values.size, specialIndex ))
   }
}

case class ControlUGen[ R <: Rate ]( rate: R, numChannels: Int, override val specialIndex: Int )
extends MultiOutUGen[ R ]( IIdxSeq.fill( numChannels )( rate ), IIdxSeq.empty ) with HasSideEffect

case class ControlProxy[ R <: Rate ]( rate: R, values: IIdxSeq[ Float ], name: Option[ String ])( val factory: ControlFactory[ R ])
extends AbstractControlProxy[ R, ControlProxy[ R ]]( IIdxSeq.fill( values.size )( rate ))

class ControlFactory[ R <: Rate ]( rate: R ) extends AbstractControlFactory[ ControlProxy[ R ]] {
   protected def makeUGen( numChannels: Int, specialIndex: Int ) : UGen = ControlUGen( rate, numChannels, specialIndex )
}

// ---------- TrigControl ----------

object TrigControl {
   def kr( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = TrigControl( values, name )
   def kr( values: Float* ) : TrigControl = kr( IIdxSeq( values: _* ))
}
case class TrigControl( values: IIdxSeq[ Float ], name: Option[ String ]) extends MultiOutUGenSource[ control, TrigControlUGen ] with ControlRated {
   protected def expandUGens = {
      val specialIndex = UGenGraph.builder.addControl( values, name )
      IIdxSeq( TrigControlUGen( values.size, specialIndex ))
   }
}

case class TrigControlUGen private[ugen]( numChannels: Int, override val specialIndex: Int )
extends MultiOutUGen[ control ]( IIdxSeq.fill( numChannels )( control ), IIdxSeq.empty ) with ControlRated with HasSideEffect

case class TrigControlProxy( values: IIdxSeq[ Float ], name: Option[ String ])
extends AbstractControlProxy[ control, TrigControlProxy ]( IIdxSeq.fill( values.size )( control )) with ControlRated {
   def factory = TrigControlFactory
}

object TrigControlFactory extends AbstractControlFactory[ TrigControlProxy ] {
   protected def makeUGen( numChannels: Int, specialIndex: Int ) : UGen = TrigControlUGen( numChannels, specialIndex )
}

// ---------- AudioControl ----------

object AudioControl {
   def ar( values: IIdxSeq[ Float ], name: Option[ String ] = None ) = AudioControl( values, name )
   def ar( values: Float* ) : AudioControl = ar( IIdxSeq( values: _* ))
}
case class AudioControl( values: IIdxSeq[ Float ], name: Option[ String ]) extends MultiOutUGenSource[ audio, AudioControlUGen ] with AudioRated {
   protected def expandUGens = {
      val specialIndex = UGenGraph.builder.addControl( values, name )
      IIdxSeq( AudioControlUGen( values.size, specialIndex ))
   }
}

case class AudioControlUGen private[ugen]( numChannels: Int, override val specialIndex: Int )
extends MultiOutUGen[ audio ]( IIdxSeq.fill( numChannels )( audio ), IIdxSeq.empty ) with AudioRated with HasSideEffect

case class AudioControlProxy( values: IIdxSeq[ Float ], name: Option[ String ])
extends AbstractControlProxy[ audio, AudioControlProxy ]( IIdxSeq.fill( values.size )( audio )) with AudioRated {
   def factory = AudioControlFactory
}

object AudioControlFactory extends AbstractControlFactory[ AudioControlProxy ] {
   protected def makeUGen( numChannels: Int, specialIndex: Int ) : UGen = AudioControlUGen( numChannels, specialIndex )
}
