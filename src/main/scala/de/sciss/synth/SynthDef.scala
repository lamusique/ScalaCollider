/*
 *  SynthDef.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
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

import java.io.{ByteArrayOutputStream, BufferedOutputStream, DataOutputStream, File, FileOutputStream}
import java.nio.ByteBuffer
import de.sciss.synth.{Completion => Comp}
import File.{separator => sep}
import de.sciss.osc.{Bundle, Message, Packet}

/**
 * @todo    should add load and loadDir to companion object
 */
final case class SynthDef(name: String, graph: UGenGraph) {
  syndef =>

  import SynthDef._

  override def toString = "SynthDef(" + name + ")"

  def freeMsg = message.SynthDefFree(name)

  def recv(server: Server = Server.default, completion: Completion = NoCompletion) {
    sendWithAction(server, recvMsg(_), completion, "recv")
  }

  private def sendWithAction(server: Server, msgFun: Option[Packet] => Message, completion: Completion,
                             name: String) {
    completion.action map { action =>
      val syncMsg = server.syncMsg
      val syncID = syncMsg.id
      val compPacket: Packet = completion.message match {
        case Some(msgFun2) => Bundle.now(msgFun2(syndef), syncMsg)
        case None => syncMsg
      }
      server.!?(msgFun(Some(compPacket))) {
        // XXX timeout kind of arbitrary
        case message.Synced(`syncID`) => action(syndef)
        case message.TIMEOUT => println("ERROR: " + syndef + "." + name + " : timeout!")
      }
    } getOrElse {
      server ! msgFun(completion.message.map(_.apply(syndef)))
    }
  }

  def recvMsg: message.SynthDefRecv = recvMsg(None)

  def recvMsg(completion: Option[Packet]) = message.SynthDefRecv(toBytes, completion)

  def toBytes: ByteBuffer = {
    val baos  = new ByteArrayOutputStream
    val dos   = new DataOutputStream(baos)

    dos.writeInt(0x53436766)  // magic cookie 'SCgf'
    dos.writeInt(1)           // version
    dos.writeShort(1)         // number of defs in file.
    write(dos)
    dos.flush()
    dos.close()

    ByteBuffer.wrap(baos.toByteArray).asReadOnlyBuffer()
  }

  private def write(dos: DataOutputStream) {
    writePascalString(dos, name)
    graph.write(dos)
  }

  def load(server: Server = Server.default, dir: String = defaultDir,
           completion: Completion = NoCompletion) {
    write(dir)
    sendWithAction(server, loadMsg(dir, _), completion, "load")
  }

  def loadMsg: message.SynthDefLoad = loadMsg()

  def loadMsg(dir: String = defaultDir, completion: Option[Packet] = None) =
    message.SynthDefLoad(dir + sep + name + ".scsyndef", completion)

  def play(target: Node = Server.default, args: Seq[ControlSetMap] = Nil, addAction: AddAction = addToHead): Synth = {
    val synth   = Synth(target.server)
    val newMsg  = synth.newMsg(name, target, args, addAction)
    target.server ! recvMsg(newMsg)
    synth
  }

  def write(dir: String = defaultDir, overwrite: Boolean = true) {
    val file = new File(dir, name + ".scsyndef")
    if (file.exists) {
      if (overwrite) file.delete() else return
    }
    SynthDef.write(file.getAbsolutePath, this :: Nil)
  }

  @inline private def writePascalString(dos: DataOutputStream, str: String) {
    dos.writeByte(str.size)
    dos.write(str.getBytes)
  }

  def hexDump() {
    Packet.printHexOn(toBytes, Console.out)
  }

  def testTopoSort() {
    graph.ugens.zipWithIndex.foreach { case (ru, i) =>
      ru.inputSpecs.toList.zipWithIndex.foreach { case ((ref, _), j) =>
        if ((ref >= 0) && (ref <= i)) {
          sys.error("Test failed : ugen " + i + " = " + ru.ugen + " -> input " + j + " = " + ref)
        }
      }
    }
    println("Test succeeded.")
  }

  def debugDump() {
     graph.ugens.zipWithIndex.foreach { case (ru, i) =>
       println("#" + i + " : " + ru.ugen.name +
         (if (ru.ugen.specialIndex != 0) "-" + ru.ugen.specialIndex else "") + ru.inputSpecs.map({
         case (-1, idx) => graph.constants(idx).toString
         case (uidx, oidx) =>
           val ru = graph.ugens(uidx);
           "#" + uidx + " : " + ru.ugen.name +
             (if (oidx > 0) "@" + oidx else "")
       }).mkString("( ", ", ", " )"))
     }
   }
}

object SynthDef {
  type Completion         = Comp[SynthDef]
  final val NoCompletion  = Comp[SynthDef](None, None)

  var defaultDir          = sys.props("java.io.tmpdir")

  def apply(name: String)(thunk: => Unit)
           (implicit factory: UGenGraph.BuilderFactory = impl.DefaultUGenGraphBuilderFactory): SynthDef =
    SynthDef(name, SynthGraph(thunk).expand)

  def recv(name: String, server: Server = Server.default, completion: Completion = NoCompletion)
          (thunk: => Unit): SynthDef = {
    val d = apply(name)(thunk)
    d.recv(server, completion)
    d
  }

  def write(path: String, defs: Seq[SynthDef]) {
    val os  = new FileOutputStream(path)
    val dos = new DataOutputStream(new BufferedOutputStream(os))

    try {
      dos.writeInt(0x53436766)    // magic cookie
      dos.writeInt(1)             // version
      dos.writeShort(defs.size)   // number of defs in file.
      defs.foreach(_.write(dos))
    }
    finally {
      dos.close()
    }
  }
}