/*
 *  SynthDef.scala
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

import java.io.{ByteArrayOutputStream, BufferedOutputStream, DataOutputStream, File, FileOutputStream}
import java.nio.ByteBuffer
import File.{separator => sep}
import de.sciss.osc.Packet
import de.sciss.synth

object SynthDef {
  type Completion = synth.Completion[SynthDef]

  /** The default directory for writing synth defs is the
    * temporary directory given by the system property `"java.io.tmpdir"`
    */
  var defaultDir          = sys.props("java.io.tmpdir")

  /** The `SynthDef` file name extension (without leading period). */
  final val extension     = "scsyndef"

  /** Creates a new `SynthDef` with a given name and a thunk parameter that
    * creates the expanded UGen graph structure.
    */
  def apply(name: String)(thunk: => Unit)
           (implicit factory: UGenGraph.BuilderFactory = impl.DefaultUGenGraphBuilderFactory): SynthDef =
    SynthDef(name, SynthGraph(thunk).expand)

  def write(path: String, defs: Seq[SynthDef]): Unit = {
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

  def loadMsg   (path: String, completion: Optional[Packet] = None) = message.SynthDefLoad   (path, completion)
  def loadDirMsg(path: String, completion: Optional[Packet] = None) = message.SynthDefLoadDir(path, completion)
}
final case class SynthDef(name: String, graph: UGenGraph) {
  import SynthDef._

  override def toString = s"SynthDef($name)"

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

  private def write(dos: DataOutputStream): Unit = {
    writePascalString(dos, name)
    graph.write(dos)
  }

  def loadMsg: message.SynthDefLoad = loadMsg()

  def loadMsg(dir: String = defaultDir, completion: Optional[Packet] = None) =
    message.SynthDefLoad(s"$dir$sep$name.$extension", completion)

  def write(dir: String = defaultDir, overwrite: Boolean = true): Unit = {
    val file = new File(dir, s"$name.$extension")
    if (file.exists) {
      if (overwrite) file.delete() else return
    }
    SynthDef.write(file.getAbsolutePath, this :: Nil)
  }

  @inline private def writePascalString(dos: DataOutputStream, str: String): Unit = {
    dos.writeByte(str.size)
    dos.write(str.getBytes)
  }

  def hexDump(): Unit = Packet.printHexOn(toBytes, Console.out)

  private[synth] def testTopologicalSort(): Unit = {
    graph.ugens.zipWithIndex.foreach { case (ru, i) =>
      ru.inputSpecs.toList.zipWithIndex.foreach { case ((ref, _), j) =>
        if ((ref >= 0) && (ref <= i)) {
          sys.error(s"Test failed : ugen $i = ${ru.ugen} -> input $j = $ref")
        }
      }
    }
    println("Test succeeded.")
  }

  def debugDump(): Unit = {
     graph.ugens.zipWithIndex.foreach { case (ru, i) =>
       val special  = if (ru.ugen.specialIndex != 0) s"-${ru.ugen.specialIndex}" else ""
       val specs    = ru.inputSpecs.map {
         case (-1, idx) => graph.constants(idx).toString
         case (uIdx, oIdx) =>
           val ru   = graph.ugens(uIdx)
           val oStr = if (oIdx > 0) "@" + oIdx else ""
           s"#$uIdx : ${ru.ugen.name}$oStr"
       } .mkString("( ", ", ", " )")
       println(s"#$i : ${ru.ugen.name}$special$specs")
     }
   }
}