### changes

#### changes in v0.34

* SynthGraph is serializable
* bit of clean up in rich numbers

#### changes in v0.33

* fix for `LinLin` UGen removed from SuperCollider.

#### changes in v0.32

* OSC array support (e.g. setting multi-channel controls directly in synth instantiation).
* fix for `SendTrig` argument names
* elimination of functionally equivalent UGens and removal of no-op subtrees re-enabled
* fix for `Silent` UGen removed from SuperCollider.

#### changes in v0.31

* `ServerOptions` became `Server.Config`. And a config builder is created via `Server.Config()`. This makes it more regular with the nomenclature of ScalaOSC. Likewise, `ClientOptions` became `Client.Config`.
* `Server.test` which was originally really meant just for quick testing, was renamed to `Server.run`, because it is generally useful for running a server without caring too much about the booting process.

#### changes in v0.30

__Note:__ There have been made several changes from 0.25 to 0.30...

* To facilitate future graph re-writing, elements are now _lazy_ which means they do not instantly (multi-channel) expand into UGens. The idea is to be able to capture the whole expression tree, which then could be used to transform it, to visualize it (before multi-channel expansion!), to persist it, etc.
* In the meantime, the UGens themselves are not represented any more by individual classes but only generic classes such as `UGen.SingleOut`. As we expect manipulations to happen at the `GE` level, this keeps the number of classes smaller.
* The SynthDef creation process is now two staged. The `GE` tree is captured as a `SynthGraph` which in turn expands into a `UGenGraph`.
* Automatic subtree omission for side-effect-free roots is currently disabled.
* the implicit conversion to `GraphFunction` has been disabled. so instead of `{ }.play`, use `play { }`
* Multi-channel nesting and expansion now behaves like in sclang.
* Recursive graph generation may require type annotations. Specifically all UGen source classes now return the UGen source type itself instead of a generic `GE`, which comes with the need to annotate `var`s in certain cases.
* Methods `outputs` and `numOutputs` are not available for the lazy graph elements. You can mostly work around this by using special graph elements for channel handling, like `Zip`, `Reduce`, `Splay`, `Mix`, `Flatten`. Method `\` (backslash) is still preserved, but it may change in a future version if it causes problems with graph re-writing. If you really need to re-arrange the channels of an element, you can currently work around this by using the `MapExpanded` element.
