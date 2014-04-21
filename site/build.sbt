scalaVersion  in ThisBuild := "2.11.0"

val lOSC       = RootProject(uri("git://github.com/Sciss/ScalaOSC.git#v1.1.3"))

val lAudioFile = RootProject(uri("git://github.com/Sciss/ScalaAudioFile.git#v1.4.2"))

val lUGens     = RootProject(uri("git://github.com/Sciss/ScalaColliderUGens.git#v1.9.0"))

val lMain      = RootProject(uri("git://github.com/Sciss/ScalaCollider.git#v1.12.0"))

git.gitCurrentBranch in ThisBuild := "master"

val root = (project in file("."))
  .settings(unidocSettings: _*)
  .settings(site.settings ++ ghpages.settings: _*)
  .settings(
    site.addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), "latest/api"),
    git.remoteRepo := s"git@github.com:Sciss/ScalaCollider.git",
    scalacOptions in (Compile, doc) ++= Seq("-skip-packages", "de.sciss.osc.impl")
  )
  .aggregate(lOSC, lAudioFile, lUGens, lMain)
