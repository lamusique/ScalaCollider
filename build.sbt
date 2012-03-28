name := "scalacollider"

version := "0.33"

organization := "de.sciss"

scalaVersion := "2.9.1"

description := "A sound synthesis library for the SuperCollider server"

homepage := Some( url( "https://github.com/Sciss/ScalaCollider" ))

licenses := Seq( "GPL v2+" -> url( "http://www.gnu.org/licenses/gpl-2.0.txt" ))

libraryDependencies ++= Seq(
   "de.sciss" %% "scalaosc" % "0.33",
   "de.sciss" %% "scalaaudiofile" % "0.20"
)

retrieveManaged := true

scalacOptions ++= Seq( "-deprecation", "-unchecked" )

// ---- console ----

initialCommands in console := """import de.sciss.osc; import de.sciss.synth.{ osc => sosc, _ }; import ugen._; var s: Server = null; def boot = Server.run( s = _ )"""

// ---- publishing ----

publishMavenStyle := true

// publishTo <<= version { (v: String) =>
//    Some( "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/".+(
//       if( v.endsWith( "-SNAPSHOT")) "snapshots/" else "releases/"
//    ))
// }

publishTo <<= version { (v: String) =>
   Some( if( v.endsWith( "-SNAPSHOT" ))
      "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
   else
      "Sonatype Releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
   )
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra :=
<scm>
  <url>git@github.com:Sciss/Strugatzki.git</url>
  <connection>scm:git:git@github.com:Sciss/Strugatzki.git</connection>
</scm>
<developers>
   <developer>
      <id>sciss</id>
      <name>Hanns Holger Rutz</name>
      <url>http://www.sciss.de</url>
   </developer>
</developers>

// credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// ---- disable scaladoc generation during development phase ----

publishArtifact in (Compile, packageDoc) := false

// ---- ls.implicit.ly ----

seq( lsSettings :_* )

(LsKeys.tags in LsKeys.lsync) := Seq( "sound-synthesis", "sound", "music", "supercollider" )

(LsKeys.ghUser in LsKeys.lsync) := Some( "Sciss" )

(LsKeys.ghRepo in LsKeys.lsync) := Some( "ScalaCollider" )

// bug in ls -- doesn't find the licenses from global scope
(licenses in LsKeys.lsync) := Seq( "GPL v2+" -> url( "http://www.gnu.org/licenses/gpl-2.0.txt" ))
