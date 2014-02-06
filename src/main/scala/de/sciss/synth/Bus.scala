/*
 *  Bus.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2014 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v2+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

object Bus {
  def control(server: Server = Server.default, numChannels: Int = 1) = {
    val id = server.allocControlBus(numChannels)
    if (id == -1) {
      throw AllocatorExhausted("Bus.control: failed to get a bus allocated (" +
        numChannels + " channels on " + server.name + ")")
    }
    ControlBus(server, id, numChannels)
  }

  def audio(server: Server = Server.default, numChannels: Int = 1) = {
    val id = server.allocAudioBus(numChannels)
    if (id == -1) {
      throw AllocatorExhausted("Bus.audio: failed to get a bus allocated (" +
        numChannels + " channels on " + server.name + ")")
    }
    AudioBus(server, id, numChannels)
  }
}

sealed trait Bus {
  /** The rate at which the bus operates. Either of `control` or `audio`. */
  def rate: Rate
  /** The logical index of the bus in the server architecture. */
  def index: Int
  /** The number of channels for this bus. */
  def numChannels: Int
  /** The server to which this bus belongs. */
  def server: Server

  /** Frees the bus. This is a client-side only operation which makes the `index` available again for re-allocation. */
  def free(): Unit

  protected final var released  = false
  protected final val sync      = new AnyRef
}

final case class ControlBus(server: Server, index: Int, numChannels: Int) extends Bus {
  /** Control buses always run at `control` rate. */
  def rate: Rate = control

  def free(): Unit = sync.synchronized {
    if (released) sys.error(s"$this : has already been freed")
    server.freeControlBus(index)
    released = true
  }

  def setMsg(v: Float) = {
    require(numChannels == 1)
    message.ControlBusSet((index, v))
  }

  def setMsg(pairs: (Int, Float)*) = {
    require(pairs.forall(tup => tup._1 >= 0 && tup._1 < numChannels))
    message.ControlBusSet(pairs.map(tup => (tup._1 + index, tup._2)): _*)
  }

  def setnMsg(v: IndexedSeq[Float]) = {
    require(v.size == numChannels)
    message.ControlBusSetn((index, v.toIndexedSeq))
  }

  def setnMsg(pairs: (Int, IndexedSeq[Float])*) = {
    require(pairs.forall(tup => tup._1 >= 0 && (tup._1 + tup._2.size) <= numChannels))
    val ipairs = pairs.map(tup => (tup._1 + index, tup._2.toIndexedSeq))
    message.ControlBusSetn(ipairs: _*)
  }

  def getMsg = {
    require(numChannels == 1)
    message.ControlBusGet(index)
  }

  def getMsg(offset: Int = 0) = {
    require(offset >= 0 && offset < numChannels)
    message.ControlBusGet(offset + index)
  }

  def getMsg(offsets: Int*) = {
    require(offsets.forall(o => o >= 0 && o < numChannels))
    message.ControlBusGet(offsets.map(_ + index): _*)
  }
}

final case class AudioBus(server: Server, index: Int, numChannels: Int) extends Bus {

  /** Audio buses always run at `audio` rate. */
  def rate: Rate = audio

  def free(): Unit = sync.synchronized {
    if (released) sys.error(s"$this : has already been freed")
    server.freeAudioBus(index)
    released = true
  }
}