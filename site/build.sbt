scalaVersion  in ThisBuild := "2.10.3"

val lOSC       = RootProject(uri("git://github.com/Sciss/ScalaOSC.git#v1.1.2"))

val lAudioFile = RootProject(uri("git://github.com/Sciss/ScalaAudioFile.git#v1.4.1"))

val lUGens     = RootProject(uri("git://github.com/Sciss/ScalaColliderUGens.git#v1.7.2"))

val lMain      = RootProject(uri("git://github.com/Sciss/ScalaCollider.git#v1.10.0"))

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
