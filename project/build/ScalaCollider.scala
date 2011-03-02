import sbt._
import java.io.{ IOException, RandomAccessFile }

class ScalaColliderProject( info: ProjectInfo ) extends DefaultProject( info ) {
   // ---- dependancies ----

   val dep1 = "de.sciss" %% "scalaosc" % "0.22"
   val dep2 = "de.sciss" %% "scalaaudiofile" % "0.16"
   lazy val demo = demoAction

   // ---- publishing ----

   override def managedStyle  = ManagedStyle.Maven
   val publishTo              = "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/"

   override def packageDocsJar= defaultJarPath( "-javadoc.jar" )
   override def packageSrcJar = defaultJarPath( "-sources.jar" )
   val sourceArtifact         = Artifact.sources( artifactID )
   val docsArtifact           = Artifact.javadoc( artifactID )
   override def packageToPublishActions = super.packageToPublishActions ++ Seq( packageDocs, packageSrc )

   override def pomExtra =
      <licenses>
        <license>
          <name>GPL v2+</name>
          <url>http://www.gnu.org/licenses/gpl-2.0.txt</url>
          <distribution>repo</distribution>
        </license>
      </licenses>

   Credentials( Path.userHome / ".ivy2" / ".credentials", log )

   // ---- actions ----

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