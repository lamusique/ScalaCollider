// import com.typesafe.sbt.SbtGit.{GitKeys => git}

val commonSettings = Seq(
  organization := "de.sciss",
  version      := "0.1-SNAPSHOT",
  scalaVersion := "2.10.3"
)

retrieveManaged in ThisBuild := true

val scalaOSC           = RootProject(uri("git://github.com/Sciss/ScalaOSC.git#v1.1.2"))

val scalaAudioFile     = RootProject(uri("git://github.com/Sciss/ScalaAudioFile.git#v1.4.1"))

val scalaColliderUGens = RootProject(uri("git://github.com/Sciss/ScalaColliderUGens.git#v1.7.2"))

val scalaCollider      = RootProject(uri("git://github.com/Sciss/ScalaCollider.git#v1.10.0"))

val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(unidocSettings: _*)
  .settings(site.settings ++ ghpages.settings: _*)
  .settings(
    name := "ScalaCollider",
    site.addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), "latest/api"),
    git.remoteRepo := "git@github.com:Sciss/ScalaCollider.git",
    scalacOptions in (Compile, doc) ++= Seq("-skip-packages", "de.sciss.osc.impl")
  )
  .aggregate(scalaOSC, scalaAudioFile, scalaColliderUGens, scalaCollider)

