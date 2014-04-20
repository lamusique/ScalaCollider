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
  /** Allocates a new control bus.
    * If there are no more available buses, an [[AllocatorExhausted]] exception is thrown.
    *
    * @param server       the server on which the bus resides
    * @param numChannels  the number of channels to allocate
    */
  def control(server: Server = Server.default, numChannels: Int = 1) = {
    val id = server.allocControlBus(numChannels)
    if (id == -1) {
      throw AllocatorExhausted("Bus.control: failed to get a bus allocated (" +
        numChannels + " channels on " + server.name + ")")
    }
    ControlBus(server, id, numChannels)
  }

  /** Allocates a new audio bus.
    * If there are no more available buses, an [[AllocatorExhausted]] exception is thrown.
    *
    * @param server       the server on which the bus resides
    * @param numChannels  the number of channels to allocate
    */
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

  /** Frees the bus. This is a client-side only operation which makes the `index` available
    * again for re-allocation.
    */
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

  /** A convenience method that sets the control bus to one value.
    * It requires that the bus has exactly one channel, otherwise
    * an exception is thrown.
    *
    * @param v  the value to set the bus to
    */
  def setMsg(v: Float) = {
    require(numChannels == 1)
    message.ControlBusSet((index, v))
  }

  def setMsg(pairs: (Int, Float)*) = {
    require(pairs.forall(tup => tup._1 >= 0 && tup._1 < numChannels))
    message.ControlBusSet(pairs.map(tup => (tup._1 + index, tup._2)): _*)
  }

  /** A convenience method that sets the control bus to a sequence of values.
    * It requires that the bus's number of channels is equal to the argument's size, otherwise
    * an exception is thrown.
    *
    * @param xs  the vector of values to set the bus to
    */
  def setnMsg(xs: IndexedSeq[Float]) = {
    require(xs.size == numChannels)
    message.ControlBusSetn((index, xs.toIndexedSeq))
  }

  def setnMsg(pairs: (Int, IndexedSeq[Float])*) = {
    require(pairs.forall(tup => tup._1 >= 0 && (tup._1 + tup._2.size) <= numChannels))
    val ipairs = pairs.map(tup => (tup._1 + index, tup._2.toIndexedSeq))
    message.ControlBusSetn(ipairs: _*)
  }

  /** A convenience method that gets the control bus value.
    * It requires that the bus has exactly one channel, otherwise
    * an exception is thrown.
    */
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

  /** A convenience method that queries all channels of the control bus. */
  def getnMsg = {
    message.ControlBusGetn(0 -> numChannels)
  }

  def getnMsg(pairs: (Int, Int)*) = {
    require(pairs.forall(tup => tup._1 >= 0 && (tup._1 + tup._2) <= numChannels))
    message.ControlBusGetn(pairs: _*)
  }

  /** A convenience method that fills all channels of the control bus with one value.
    */
  def fillMsg(v: Float) = {
    val data = message.ControlBusFill.Data(index = 0, num = numChannels, value = v)
    message.ControlBusFill(data)
  }

  def fillMsg(data: message.ControlBusFill.Data*) = {
    require(data.forall(d => d.index >= 0 && (d.index + d.num) < numChannels))
    message.ControlBusFill(data: _*)
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