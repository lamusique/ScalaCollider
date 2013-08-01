# ScalaCollider

## statement

ScalaCollider is a [SuperCollider](http://supercollider.sf.net) client for the Scala programming language. It is (C)opyright 2008-2013 by Hanns Holger Rutz. All rights reserved. ScalaCollider is released under the [GNU General Public License](http://github.com/Sciss/ScalaCollider/blob/master/LICENSE) and comes with absolutely no warranties. To contact the author, send an email to `contact at sciss.de`

## building

ScalaCollider currently builds with sbt 0.12 against Scala 2.10. It requires SuperCollider 3.5 or higher. Note that the UGens are provided by the separate [ScalaColliderUGens](http://github.com/Sciss/ScalaColliderUGens) project. A simple Swing front end is provided by the [ScalaColliderSwing](http://github.com/Sciss/ScalaColliderSwing) project.

Targets for sbt:

* `clean` &ndash; removes previous build artefacts
* `compile` &ndash; compiles classes into target/scala-version/classes
* `doc` &ndash; generates api in target/scala-version/api/index.html
* `package` &ndash; packages jar in target/scala-version
* `console` &ndash; opens a Scala REPL with ScalaCollider on the classpath

__Note__: Due to [SI-7268](https://issues.scala-lang.org/browse/SI-7268), the project must be currently compiled against Scala 2.10.0 and not 2.10.1. It can be used, however, with any Scala 2.10 version.

## linking

To use this project as a library, use the following artifact:

    libraryDependencies += "de.sciss" %% "scalacollider" % v

The current version `v` is `1.9.+`

## starting a SuperCollider server

The following short example illustrates how a server can be launched and a synth played:

```scala
    
    import de.sciss.synth._
    import ugen._
    
    val cfg = Server.Config()
    cfg.programPath = "/path/to/scsynth"
    // runs a server and executes the function
    // when the server is booted, with the
    // server as its argument 
    Server.run(cfg) { s =>
      // play is imported from package de.sciss.synth.
      // it provides a convenience method for wrapping
      // a synth graph function in an `Out` element
      // and playing it back.
      play {
        val f = LFSaw.kr(0.4).madd(24, LFSaw.kr(Seq(8, 7.23)).madd(3, 80)).midicps
        CombN.ar(SinOsc.ar(f) * 0.04, 0.2, 0.2, 4)
      }
    }
    
```

You might omit to set the `programPath` of the server's configuration, as ScalaCollider will by default read the system property `SC_HOME`, and if that is not set, the environment variable `SC_HOME`. Environment variables are stored depending on your operating system. On OS X, if you use the app-bundle of ScalaCollider-Swing, you can access them from the terminal:

    $ touch ~/.MacOSX/environment.plist
    $ open ~/.MacOSX/environment.plist

On the other hand, if you run ScalaCollider from a Bash terminal, you instead edit `~/.bash_profile`. The entry is something like `export SC_HOME=/path/to/folder-of-scsynth`. On linux, the environment variables probably go in `~/.profile`.

For more sound examples, see `ExampleCmd.txt`. There is also an introductory video for the [Swing frontend](http://github.com/Sciss/ScalaColliderSwing) at [www.screencast.com/t/YjUwNDZjMT](http://www.screencast.com/t/YjUwNDZjMT).

## packages

- Audio file functionality is provided by the [ScalaAudioFile](http://github.com/Sciss/ScalaAudioFile) library.
- Open Sound Control functionality is provided by the [ScalaOSC](http://github.com/Sciss/ScalaOSC) library.
- MIDI functionality is not included, but can be added with the [ScalaMIDI](http://github.com/Sciss/ScalaMIDI) library.

## download and resources

The current version can be downloaded from [github.com/Sciss/ScalaCollider](http://github.com/Sciss/ScalaCollider).

More information is available from the wiki at [github.com/Sciss/ScalaCollider/wiki](http://github.com/Sciss/ScalaCollider/wiki).

A mailing list is available at [groups.google.com/group/scalacollider](http://groups.google.com/group/scalacollider).
