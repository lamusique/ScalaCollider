## ScalaCollider

### statement

ScalaCollider is a SuperCollider client for the Scala language. It is (C)opyright 2008-2011 by Hanns Holger Rutz. All rights reserved. ScalaCollider is released under the [GNU General Public License](http://github.com/Sciss/ScalaCollider/blob/master/licenses/ScalaCollider-License.txt) and comes with absolutely no warranties. To contact the author, send an email to `contact at sciss.de`

### changes

#### changes in v0.31

* `ServerOptions` became `Server.Config`. And a config builder is created via `Server.Config()`. This makes it more regular with the nomenclature of ScalaOSC. Likewise, `ClientOptions` became `Client.Options`.
* `Server.test` which was originally really meant just for quick testing, was renamed to `Server.run`, because it is generally useful for running a server without caring too much about the booting process.

#### changes in v0.30

__Note:___ There have been made several changes from 0.25 to 0.30...

* To facilitate future graph re-writing, elements are now _lazy_ which means they do not instantly (multi-channel) expand into UGens. The idea is to be able to capture the whole expression tree, which then could be used to transform it, to visualize it (before multi-channel expansion!), to persist it, etc.
* In the meantime, the UGens themselves are not represented any more by individual classes but only generic classes such as `UGen.SingleOut`. As we expect manipulations to happen at the `GE` level, this keeps the number of classes smaller.
* The SynthDef creation process is now two staged. The `GE` tree is captured as a `SynthGraph` which in turn expands into a `UGenGraph`.
* Automatic subtree omission for side-effect-free roots is currently disabled.
* the implicit conversion to `GraphFunction` has been disabled. so instead of `{ }.play`, use `play { }`
* Multi-channel nesting and expansion now behaves like in sclang.
* Recursive graph generation may require type annotations. Specifically all UGen source classes now return the UGen source type itself instead of a generic `GE`, which comes with the need to annotate `var`s in certain cases.
* Methods `outputs` and `numOutputs` are not available for the lazy graph elements. You can mostly work around this by using special graph elements for channel handling, like `Zip`, `Reduce`, `Splay`, `Mix`, `Flatten`. Method `\` (backslash) is still preserved, but it may change in a future version if it causes problems with graph re-writing. If you really need to re-arrange the channels of an element, you can currently work around this by using the `MapExpanded` element.

### requirements / installation

ScalaCollider currently builds with xsbt (sbt 0.11) against Scala 2.9.1. It requires Java 1.6 and SuperCollider 3 (it has been tested with SuperCollider 3.4 and 3.5 snapshots). It depends on ScalaOSC ([github.com/Sciss/ScalaOSC](http://github.com/Sciss/ScalaOSC)) and ScalaAudioFile ([github.com/Sciss/ScalaAudioFile](http://github.com/Sciss/ScalaAudioFile)).

Targets for xsbt:

* `clean` &ndash; removes previous build artefacts
* `compile` &ndash; compiles classes into target/scala-version/classes
* `doc` &ndash; generates api in target/scala-version/api/index.html
* `package` &ndash; packages jar in target/scala-version
* `console` &ndash; opens a Scala REPL with ScalaCollider on the classpath

Running `xsbt update` should download all the dependencies from scala-tools.org.

### creating an IntelliJ IDEA project

The IDEA project files have now been removed from the git repository, but they can be easily recreated, given that you have installed the sbt-idea plugin. If you haven't yet, create the following contents in `~/.sbt/plugins/build.sbt`:

    resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
    
    addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "0.11.0")

Then to create the IDEA project, run the following two commands from the xsbt shell:

    > set ideaProjectName := "ScalaCollider"
    > gen-idea

### starting a SuperCollider server

The following short example illustrates how a server can be launched and a synth played:

```scala
    
    import de.sciss.synth._
    import ugen._
    
    val cfg = Server.Config()
    cfg.programPath = "/path/to/scsynth"
    // runs a server and executes the function
    // when the server is booted, with the
    // server as its argument 
    Server.run( cfg ) { s =>
       // play is imported from package de.sciss.synth.
       // it provides a convenience method for wrapping
       // a synth graph function in an `Out` element
       // and playing it back.
       play {
          val f = LFSaw.kr( 0.4 ).madd( 24, LFSaw.kr( Seq( 8, 7.23 )).madd( 3, 80 )).midicps
          CombN.ar( SinOsc.ar( f ) * 0.04, 0.2, 0.2, 4 )
       }
    }
    
```

You might omit to set the `programPath` of the server's configuration, as ScalaCollider will by default read the environment variable `SC_HOME`. Environment variables are stored depending on your operating system. On OS X, if you use the app-bundle of ScalaCollider-Swing, you can access them from the terminal:

    $ touch ~/.MacOSX/environment.plist
    $ open ~/.MacOSX/environment.plist

On the other hand, if you run ScalaCollider from a Bash terminal, you instead edit `~/.bash_profile`. The entry is something like `export SC_HOME=/path/to/folder-of-scsynth`. On linux, the environment variables probably go in `~/.profile`.

For more sound examples, see `ExampleCmd.txt`. There is also an introductory video for the [Swing frontend](http://github.com/Sciss/ScalaColliderSwing) at [www.screencast.com/t/YjUwNDZjMT](http://www.screencast.com/t/YjUwNDZjMT).

### download and resources

The current version can be downloaded from [github.com/Sciss/ScalaCollider](http://github.com/Sciss/ScalaCollider).

More information is available from the wiki at [github.com/Sciss/ScalaCollider/wiki](http://github.com/Sciss/ScalaCollider/wiki).

A mailing list is available at [groups.google.com/group/scalacollider](http://groups.google.com/group/scalacollider).
