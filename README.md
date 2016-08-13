# ScalaCollider

[![Flattr this](http://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=sciss&url=https%3A%2F%2Fgithub.com%2FSciss%2FScalaCollider&title=ScalaCollider&language=Scala&tags=github&category=software)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/Sciss/ScalaCollider?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/Sciss/ScalaCollider.svg?branch=master)](https://travis-ci.org/Sciss/ScalaCollider)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.sciss/scalacollider_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.sciss/scalacollider_2.11)

## statement

ScalaCollider is a [SuperCollider](http://supercollider.sf.net) client for the Scala programming language. It is (C)opyright 2008&ndash;2016 by Hanns Holger Rutz. All rights reserved. ScalaCollider is released under the [GNU General Public License](http://github.com/Sciss/ScalaCollider/blob/master/LICENSE) and comes with absolutely no warranties. To contact the author, send an email to `contact at sciss.de`

SuperCollider is one of the most elaborate open source sound synthesis frameworks. It comes with its own language 'SCLang' that controls the sound synthesis processes on a server, 'scsynth'. ScalaCollider is an alternative to 'SCLang', giving you the (perhaps) familiar Scala language to express these sound synthesis processes, and letting you hook up any other Scala, Java or JVM-based libraries. ScalaCollider's function is more reduced than 'SCLang', focusing on UGen graphs and server-side resources such as buses and buffers. Other functionality is part of the standard Scala library, e.g. collections and GUI. Other functionality, such as plotting, MIDI, client-side sequencing (Pdefs, Routines, etc.) must be added through dedicated libraries (see section 'packages' below).

While ScalaCollider itself is in the form of a _library_ (although you can use it from the REPL with `sbt console`), you may want to have a look at the [ScalaCollider-Swing](http://github.com/Sciss/ScalaColliderSwing) project that adds an easy-to-use standalone application or mini-IDE. On the ScalaCollider-Swing page, you'll find a link to download a readily compiled binary for this standalone version.

A still experimental system on top of ScalaCollider, providing higher level abstractions, is [SoundProcesses](http://github.com/Sciss/SoundProcesses) and its graphical front-end [Mellite](http://github.com/Sciss/Mellite). Please get in touch if you intend to use these, as the documentation is still sparse, and the system and API is still a moving target.

## download and resources

The current version of ScalaCollider (the library) can be downloaded from [github.com/Sciss/ScalaCollider](http://github.com/Sciss/ScalaCollider).

More information is available from the wiki at [github.com/Sciss/ScalaCollider/wiki](http://github.com/Sciss/ScalaCollider/wiki). The API documentation is available at [sciss.github.io/ScalaCollider/latest/api](http://sciss.github.io/ScalaCollider/latest/api/).

The best way to ask questions, no matter if newbie or expert, is to use the mailing list at [groups.google.com/group/scalacollider](http://groups.google.com/group/scalacollider). To subscribe, simply send a mail to `ScalaCollider+subscribe@googlegroups.com` (you will receive a mail asking for confirmation).

The early architectural design of ScalaCollider is documented in the SuperCollider 2010 symposium proceedings: [H.H.Rutz, Rethinking the SuperCollider Client...](http://cmr.soc.plymouth.ac.uk/publications/Rutz_SuperCollider2010.pdf). However, many design decisions have been revised or refined in the meantime.

The file [ExampleCmd.sc](https://raw.githubusercontent.com/Sciss/ScalaCollider/master/ExampleCmd.sc) is a good starting point for understanding how UGen graphs are written in ScalaCollider. You can directly copy and paste these examples into the ScalaCollider-Swing application's interpreter window.

See the section 'starting a SuperCollider server' below, for another simple example of running a server (possibly from your own application code).

## building

ScalaCollider currently builds with sbt 0.13 against Scala 2.11, 2.10. It requires SuperCollider 3.5 or higher. Note that the UGens are provided by the separate [ScalaColliderUGens](http://github.com/Sciss/ScalaColliderUGens) project. A simple Swing front end is provided by the [ScalaColliderSwing](http://github.com/Sciss/ScalaColliderSwing) project.

Targets for sbt:

* `clean` &ndash; removes previous build artefacts
* `compile` &ndash; compiles classes into target/scala-version/classes
* `doc` &ndash; generates api in target/scala-version/api/index.html
* `package` &ndash; packages jar in target/scala-version
* `console` &ndash; opens a Scala REPL with ScalaCollider on the classpath

__Note__: Due to [SI-7436](https://issues.scala-lang.org/browse/SI-7436), the project must be currently compiled against Scala 2.10.0 and not 2.10.1 through 2.10.4. It can be used, however, with any Scala 2.10 version. Another implication is that you cannot build against 2.10.0 using JDK 8, because is sbt is broken here. To build for 2.10.0, you must use JDK 6 or JDK 7.

## linking

To use this project as a library, use the following artifact:

    libraryDependencies += "de.sciss" %% "scalacollider" % v

The current version `v` is `"1.18.1"`

## contributing

Please see the file [CONTRIBUTING.md](CONTRIBUTING.md)

## starting a SuperCollider server

The following short example illustrates how a server can be launched and a synth played:

```scala
import de.sciss.synth._
import ugen._
import Ops._

val cfg = Server.Config()
cfg.program = "/path/to/scsynth"
// runs a server and executes the function
// when the server is booted, with the
// server as its argument 
Server.run(cfg) { s =>
  // play is imported from package de.sciss.synth.
  // it provides a convenience method for wrapping
  // a synth graph function in an `Out` element
  // and playing it back.
  play {
    val f = LFSaw.kr(0.4).madd(24, LFSaw.kr(Seq(8.0, 7.23)).madd(3, 80)).midicps
    CombN.ar(SinOsc.ar(f) * 0.04, 0.2, 0.2, 4)
  }
}    
```

### Specifying SC_HOME

__Note__: This section is mostly irrelevant on Linux, where `scsynth` is normally found on `$PATH`, and thus no further customisation is needed.

You might omit to set the `program` of the server's configuration, as ScalaCollider will by default read the system property `SC_HOME`, and if that is not set, the environment variable `SC_HOME`. Environment variables are stored depending on your operating system. On OS X, if you use the app-bundle of ScalaCollider-Swing, you can access them from the terminal:

    $ mkdir ~/.MacOSX
    $ touch ~/.MacOSX/environment.plist
    $ open ~/.MacOSX/environment.plist

Here, `open` should launch the PropertyEditor. Otherwise you can edit this file using a text editor. The content will be like this:

    {
      "SC_HOME" = "/Applications/SuperCollider_3.6.5/SuperCollider.app/Contents/Resources/";
    }

On the other hand, if you run ScalaCollider from a Bash terminal, you edit `~/.bash_profile` instead. The entry is something like:

    export SC_HOME=/path/to/folder-of-scsynth

On linux, the environment variables probably go in `~/.profile`.

For more sound examples, see `ExampleCmd.sc`. There is also an introductory video for the [Swing frontend](http://github.com/Sciss/ScalaColliderSwing) at [www.screencast.com/t/YjUwNDZjMT](http://www.screencast.com/t/YjUwNDZjMT).

## packages

ScalaCollider's core functionality may be extended by other libraries I or other people wrote. The following two libraries are dependencies and therefore always available in ScalaCollider:

- Audio file functionality is provided by the [ScalaAudioFile](http://github.com/Sciss/ScalaAudioFile) library.
- Open Sound Control functionality is provided by the [ScalaOSC](http://github.com/Sciss/ScalaOSC) library.

Here are some examples for libraries not included:

- MIDI functionality is not included, but can be added with the [ScalaMIDI](http://github.com/Sciss/ScalaMIDI) library.
- Plotting is most easily achieved through [Scala-Chart](https://github.com/wookietreiber/scala-chart), which is conveniently included in ScalaCollider-Swing.

