import sbt._
import java.io.{ IOException, RandomAccessFile }

class ScalaColliderProject( info: ProjectInfo ) extends DefaultProject( info ) { 
   val dep1 = "de.sciss" %% "scalaosc" % "0.22"
   val dep2 = "de.sciss" %% "scalaaudiofile" % "0.16"
   lazy val demo = demoAction

   protected def demoAction = {
       val consoleInit = try {
           val raf = new RandomAccessFile( "console-startup.txt", "r" )
           val buf = new Array[ Byte ]( raf.length.toInt )
           raf.readFully( buf )
           raf.close()
           new String( buf, "UTF-8" )
       }
       catch { case e: IOException => { e.printStackTrace(); "" }}
       consoleTask( consoleClasspath, Nil, consoleInit ) describedAs "Starts a ScalaCollider REPL, initializing from file \"console-startup.txt\"."
   }

// NOTE: these don't have any effect. you need to edit the sbt launch script to increase JVM heap size!
//   override def javaCompileOptions = JavaCompileOption( "-Xmx1024m" ) :: JavaCompileOption( "-Xss1m" ) ::
//      JavaCompileOption( "-XX:MaxPermSize=256m" ) :: JavaCompileOption( "-XX:PermSize=64m" ) :: JavaCompileOption( "-server" ) :: Nil
}