package de.sciss.synth

object BufferGetDataTest extends App {
  import Ops._

  Server.run() { s =>
    import s.clientConfig.executionContext
    val path  = "/usr/share/SuperCollider/sounds/a11wlk01-44_1.aiff"
    val b     = Buffer(s)
    // s.dumpOSC()
    for {
      _ <- b.allocRead(path)
      _ <- b.getData()
    } {
      println("Data transfer OK.")
      s.quit()
    }
  }
}
