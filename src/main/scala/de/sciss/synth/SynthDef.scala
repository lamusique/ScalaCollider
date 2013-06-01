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


object SynthDef {
  type Completion         = Comp[SynthDef]
  final val NoCompletion  = Comp[SynthDef](None, None)

  var defaultDir          = sys.props("java.io.tmpdir")

  def apply(name: String)(thunk: => Unit)
           (implicit factory: UGenGraph.BuilderFactory = impl.DefaultUGenGraphBuilderFactory): SynthDef =
    SynthDef(name, SynthGraph(thunk).expand)

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
/**
 * @todo    should add load and loadDir to companion object
 */
final case class SynthDef(name: String, graph: UGenGraph) {
  syndef =>

  import SynthDef._

  override def toString = "SynthDef(" + name + ")"

  def freeMsg = message.SynthDefFree(name)

  def recvMsg: message.SynthDefRecv = recvMsg(None)

  def recvMsg(completion: Optional[Packet]) = message.SynthDefRecv(toBytes, completion)

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

  def loadMsg: message.SynthDefLoad = loadMsg()

  def loadMsg(dir: String = defaultDir, completion: Optional[Packet] = None) =
    message.SynthDefLoad(dir + sep + name + ".scsyndef", completion)

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
