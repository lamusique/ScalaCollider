/*
 *  UGen.scala
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

package de.sciss.synth

import collection.immutable.{ IndexedSeq => IIdxSeq, Seq => ISeq }

/**
 *    @version 0.15, 21-May-10
 */
trait UGenProxy {
   def source : UGen
   def outputIndex : Int
}

//trait ExclusiveUGen     // marker trait: UGen can only occur once in a synthdef

/**
 *    Marks a ugen which has side effects
 *    such as writing to a bus or buffer,
 *    communicating back to the client, etc.
 *    Only side-effect ugens are valid roots
 *    of the ugen graph, that way other
 *    orphaned non-side-effect ugens are
 *    automatically removed from the graph.
 */
trait HasSideEffect
/**
 *    Marks a ugen which sets a special done
 *    flag that can be read by ugens such
 *    as `Done`.
 */
trait HasDoneFlag
/**
 *    Marks a ugen which is individual, that
 *    is two instances of that ugen are different
 *    even if all inputs are the same. This is
 *    the case for example for all ugens that
 *    depend on the random seed (as indicated
 *    by the sub-type `HasRandSeed`) or which
 *    mix onto buses or buffers (e.g. `RecordBuf`).
 *
 *    Note that for example `BufWr` could be
 *    considered not individual as two identically
 *    parametrized BufWr instances produce exactly the same
 *    behaviour as one of them. However,
 *    they are in certain spots of the UGen
 *    graph in which they could be behave differently,
 *    for example if the computation order is
 *    `BufWr` -> `BufRd` -> `BufRd`. We thus
 *    defensively mark every ugen as individual
 *    which writes to a Buffer or Bus.
 */
trait IsIndividual {
   override def equals( x: Any ) : Boolean = super.equals( x )
   override def hashCode() = super.hashCode()
}
trait UsesRandSeed extends IsIndividual
trait WritesBuffer extends HasSideEffect with IsIndividual  // XXX eventually: WritesBuffer[T] { def buf: T }
trait WritesFFT extends HasSideEffect with IsIndividual
trait WritesBus extends HasSideEffect with IsIndividual     // XXX eventually: WritesBus[T] { def bus: T }

abstract class UGen
extends /* YYY RatedGE with */ UGenProxy {
//   // ---- constructor ----
//   {
//      SynthGraph.builder.addUGen( this )
//   }

   def rate: Rate // YYY
   def numOutputs: Int // YYY
   def outputs: IIdxSeq[ AnyUGenIn ] // YYY

//   def name = { val cn = getClass.getName; cn.substring( cn.lastIndexOf( '.' ) + 1 )}
   def name = {
      val cn = getClass.getName
      // account for non-expandable ugens such as SampleRate.ir which doesn't have both
      // SampleRate and SampleRateUGen ...
      cn.substring( cn.lastIndexOf( '.' ) + 1, if( cn.endsWith( "UGen" )) cn.length - 4 else cn.length )
   }
   def displayName = name
//   def outputRates: Seq[ Rate ]
   def inputs: Seq[ AnyUGenIn ]
   def numInputs = inputs.size
   def source = this
   def specialIndex = 0
   def outputIndex = 0

//   def checkInputs : Option[String] = {
//      // checkValidInputs
//      None
//   }
   
//   def optimizeGraph {
//      // nothing
//   }

   override def toString: String = {
      name + "." + rate.methodName + inputs.mkString( "(", ", ", ")" )
   }
}

// a class for UGens with multiple outputs
abstract class MultiOutUGen[ R <: Rate ]( outputRates: IIdxSeq[ R ], val inputs: IIdxSeq[ AnyUGenIn ])
extends UGen with GE[R, UGenIn[ R ]]{
// YYY
//   // most multi out ugens use the same rate for all outputs,
//   // therefore we have a simpler constructor
//   def this( rate: Rate, numOutputs: Int, inputs: UGenIn* ) = this( Vector.fill( numOutputs )( rate ), inputs: _* )
   
   final override def numOutputs = outputRates.size
//	final def outputs: IIdxSeq[ UGenIn ] = outputRates.zipWithIndex.map(
//      tup => UGenOutProxy( this, tup._2, tup._1 ))

   final def expand: IIdxSeq[ UGenIn[ R ]] = outputRates.zipWithIndex.map( tup => UGenOutProxy( this, tup._2, tup._1 ))
   final def outputs: IIdxSeq[ UGenIn[ R ]] = expand
}

abstract class ZeroOutUGen( val inputs: IIdxSeq[ AnyUGenIn ]) extends UGen with HasSideEffect {
   final override def numOutputs = 0
   final def outputs = IIdxSeq.empty
}

trait UGenSource[ +U <: UGen ] extends Expands[ U ] {
   // ---- constructor ----
   SynthGraph.builder.addUGenSource( this )
}

trait SingleOutUGenSource[ R <: Rate, +U <: SingleOutUGen[ R ]] extends UGenSource[ U ] with GE[ R, U ]