/*
 *  Server.scala
 *  (ScalaCollider)
 *
 *  Copyright (c) 2008-2013 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.synth

import io.{AudioFileType, SampleFormat}
import java.io.File
import java.net.{DatagramSocket, InetAddress, InetSocketAddress, ServerSocket}
import collection.mutable
import language.implicitConversions
import de.sciss.model.Model
import message.StatusReply
import de.sciss.osc
import osc.{TCP, UDP}
import concurrent.duration._
import concurrent.Future

object Server {
  @volatile private var _default: Server = null
  def default: Server = {
    if (_default == null) throw new IllegalStateException("There is no default Server yet")
    _default
  }
  def default_=(value: Server) {
    _default = value
  }

  /**
   * The default file path to `scsynth`. If the runtime (system) property `"SC_HOME"` is provided,
   * this specifies the directory of `scsynth`. Otherwise, an environment (shell) variable named
   * `"SC_HOME"` is checked. If neither exists, this returns `scsynth` in the current working directory.
   */
  def defaultProgramPath = new File(sys.props.getOrElse("SC_HOME", sys.env.getOrElse("SC_HOME", "")),
    "scsynth").getAbsolutePath

  /**
   * The base trait for `Config` and `ConfigBuilder` describes the settings used to boot scsynth in
   * realtime or non-realtime mode, as well as its server address and port.
   *
   * You obtain a `ConfigBuilder` by calling `Server.Config()`. This builder can then be mutated and
   * will be implicitly converted to an immutable `Config` when required.
   *
   * See `ConfigBuilder` for its default values.
   *
   * @see [[de.sciss.synth.Server.ConfigBuilder]]
   * @see [[de.sciss.synth.Server.Config]]
   */
  trait ConfigLike {
    /**
     * The path to `scsynth`, used when booting a server. This can be either a relative path
     * (relating to the JVM's working directory), or an absolute path.
     *
     * @see [[de.sciss.synth.Server#defaultProgramPath]]
     */
    def programPath: String

    /**
     * The maximum number of control bus channels.
     */
    def controlBusChannels: Int

    /**
     * The maximum number of audio bus channels. This includes the channels connected
     * to hardware (`outputBusChannels`) as well as all channels for internal routing.
     */
    def audioBusChannels: Int

    /**
     * The number of connected audio hardware output channels. This does not need to
     * correspond to the actual number of channels your sound card provides, but can
     * be lower or higher, although a higher value doesn't have any effect as channel
     * indices above the number of channels of the sound card will be treated as
     * internal channels.
     */
    def outputBusChannels: Int

    /**
     * The calculation block size. That is, the number of audio samples calculated en-bloc.
     * This corresponds with the control rate, such that
     * `controlRate := audioRate / blockSize`. It should be a power of two.
     */
    def blockSize: Int

    /**
     * The audio hardware sampling rate to use. A value of `0` indicates that scsynth
     * should use the current sampling rate of the audio hardware. An explicit setting
     * will make scsynth try to switch the sound card's sample rate if necessary.
     */
    def sampleRate: Int

    /**
     * The maximum number of audio buffers (for the `Buffer` class).
     */
    def audioBuffers: Int

    /**
     * The maximum number of concurrent nodes (synths and groups).
     */
    def maxNodes: Int

    /**
     * The maximum number of synth defs.
     */
    def maxSynthDefs: Int

    /**
     * The maximum number of pre-allocated realtime memory in bytes. This memory
     * is used for many UGens such as `Limiter`, `DelayN` etc. It does not
     * affect dynamically allocated memory such as audio buffers.
     */
    def memorySize: Int

    /**
     * The maximum number of concurrent connections between UGens in a single synth.
     * ScalaCollider performs a depth-first topological sorting of the synth defs,
     * so you should not worry too much about this value. It can become important
     * in very heavy channel expansions and mixdown.
     *
     * This value will be automatically increased if a more complex def is loaded
     * at startup, but it cannot be increased thereafter without rebooting.
     */
    def wireBuffers: Int

    /**
     * The number of individual random number generators allocated.
     */
    def randomSeeds: Int

    /**
     * Whether scsynth should load synthdefs stored on the harddisk when booted.
     */
    def loadSynthDefs: Boolean

    /**
     * ?
     */
    def machPortName: Option[(String, String)]

    /**
     * The verbosity level of scsynth. The standard value is `0`, while
     * `-1` suppresses informational messages, `-2` also suppresses many error messages.
     */
    def verbosity: Int

    /**
     * An explicit list of paths where DSP plugins are found. Usually this is not
     * specified, and scsynth looks for plugins in their default location.
     */
    def plugInsPaths: List[String]

    /**
     * An option to restrict access to files (e.g. for loading and saving buffers) to
     * a particular directory. This is a security measure, preventing malicious clients from
     * accessing parts of the harddisk which they shouldn't.
     */
    def restrictedPath: Option[String]

    // ---- realtime only ----

    /**
     * (Realtime) Host address of scsynth, when trying to `connect` to an already running server on the net.
     */
    def host: String

    /**
     * (Realtime) UDP or TCP port used by scsynth.
     */
    def port: Int

    /**
     * (Realtime) Open Sound Control transport used by scsynth. (Either of `UDP` and `TCP`).
     */
    def transport: osc.Transport.Net

    /**
     * (Realtime) An option to enable particular input 'streams' or 'bundles' of a sound card.
     * This is a 'binary' String made of `'0'` and `'1'` characters.
     * If the string is `"01100"`, for example, then only the second and third input streams on
     * the device will be enabled.
     */
    def inputStreamsEnabled: Option[String]

    /**
     * (Realtime) An option to enable particular output 'streams' or 'bundles' of a sound card.
     * This is a 'binary' String made of `'0'` and `'1'` characters.
     * If the string is `"01100"`, for example, then only the second and third output streams on
     * the device will be enabled.
     */
    def outputStreamsEnabled: Option[String]

    /**
     * (Realtime) An option denoting the name of the sound card to use. On systems which distinguish
     * input and output devices (OS X), this implies that both are the same. Otherwise, you can
     * use the `deviceNames` method instead.
     *
     * @see deviceNames
     */
    def deviceName: Option[String]

    /**
     * (Realtime) An option denoting the name of the input and output sound device to use. This is for
     * systems which distinguish input and output devices (OS X). If you use a single device both for
     * input and output (applies to most professional audio interfaces), you can simply use the
     * single string method `deviceName`.
     *
     * @see deviceName
     */
    def deviceNames: Option[(String, String)]

    /**
     * (Realtime) The number of connected audio hardware input channels. This does not need to
     * correspond to the actual number of channels your sound card provides, but can
     * be lower or higher, although a higher value doesn't have any effect as channel
     * indices above the number of channels of the sound card will be treated as
     * internal channels.
     */
    def inputBusChannels: Int

    /**
     * (Realtime) A value to adjust the sound card's hardware block size. Typically you will leave
     * this to `0` which means that the current block size is used. The block sizes supported depend
     * on the particular sound card. Lower values decrease latency but may increase CPU load.
     */
    def hardwareBlockSize: Int

    /**
     * (Realtime) Whether to announce scsynth's OSC service via zero conf. See
     * [[http://en.wikipedia.org/wiki/Zero_configuration_networking Wikipedia]] for more details.
     */
    def zeroConf: Boolean

    /**
     * (Realtime) The maximum number of client connections when using TCP transport.
     */
    def maxLogins: Int

    /**
     * (Realtime) A requires session password when using TCP transport. When using TCP and the password option
     * is set, each client must send the correct password as the first command to the server, otherwise it is
     * rejected.
     */
    def sessionPassword: Option[String]

    // ---- nonrealtime only ----

    /**
     * (Non-Realtime) Path to the binary OSC file.
     */
    def nrtCommandPath: String

    /**
     * (Non-Realtime) Path to the audio input file used as audio input bus supplement.
     */
    def nrtInputPath: Option[String]

    /**
     * (Non-Realtime) Path to the audio output file used as audio output bus supplement.
     */
    def nrtOutputPath: String

    /**
     * (Non-Realtime) Audio file format for writing the output.
     */
    def nrtHeaderFormat: AudioFileType

    /**
     * (Non-Realtime) Audio sample format for writing the output.
     */
    def nrtSampleFormat: SampleFormat

    /**
     * Produces a command line for booting scsynth in realtime mode.
     */
    final def toRealtimeArgs: List[String] = Config.toRealtimeArgs(this)

    /**
     * Produces a command line for booting scsynth in non-realtime mode.
     */
    final def toNonRealtimeArgs: List[String] = Config.toNonRealtimeArgs(this)

    /**
     * A utility method providing the audio bus offset for the start of
     * the internal channels. (simply the sum of `outputBusChannels` and `inputBusChannels`).
     */
    final def internalBusIndex: Int = outputBusChannels + inputBusChannels
  }

  object Config {
    /**
     * Creates a new configuration builder with default settings
     */
    def apply(): ConfigBuilder = new ConfigBuilder()

    /**
     * Implicit conversion which allows you to use a `ConfigBuilder`
     * wherever a `Config` is required.
     */
    implicit def build(cb: ConfigBuilder): Config = cb.build

    private[Server] def toNonRealtimeArgs( o: ConfigLike ): List[ String ] = {
      val b = List.newBuilder[String]

      // -N <cmd-filename> <input-filename> <output-filename> <sample-rate> <header-format> <sample-format> <...other scsynth arguments>
      b += o.programPath
      b += "-N"
      b += o.nrtCommandPath
      b += o.nrtInputPath.getOrElse("_")
      b += o.nrtOutputPath
      b += o.sampleRate.toString
      b += o.nrtHeaderFormat.id
      b += o.nrtSampleFormat.id

      addCommonArgs( o, b )
      b.result()
    }

    private[Server] def toRealtimeArgs(o: ConfigLike): List[String] = {
      val b = List.newBuilder[ String ]

      b += o.programPath
      o.transport match {
        case TCP =>
          b += "-t"
          b += o.port.toString
        case UDP =>
          b += "-u"
          b += o.port.toString
      }

      addCommonArgs(o, b)

      if (o.hardwareBlockSize != 0) {
        b += "-Z"
        b += o.hardwareBlockSize.toString
      }
      if (o.sampleRate != 0) {
        b += "-S"
        b += o.sampleRate.toString
      }
      if (o.maxLogins != 64) {
        b += "-l"
        b += o.maxLogins.toString
      }
      o.sessionPassword.foreach { pwd =>
        b += "-p"
        b += pwd
      }
      o.inputStreamsEnabled.foreach { stream =>
        b += "-I"
        b += stream
      }
      o.outputStreamsEnabled.foreach { stream =>
        b += "-O"
        b += stream
      }
      if (!o.zeroConf) {
        b += "-R"
        b += "0"
      }
      o.deviceNames.foreach { case (inDev, outDev) =>
        b += "-H"
        b += inDev
        b += outDev
      }
      o.deviceName.foreach { n =>
        b += "-H"
        b += n
      }
      o.restrictedPath.foreach { path =>
        b += "-P"
        b += path
      }

      b.result()
    }

    private[Server] def addCommonArgs( o: ConfigLike, b: mutable.Builder[ String, _ ]) {
      if (o.controlBusChannels != 4096) {
        b += "-c"
        b += o.controlBusChannels.toString
      }
      if (o.audioBusChannels != 128) {
        b += "-a"
        b += o.audioBusChannels.toString
      }
      if (o.inputBusChannels != 8) {
        b += "-i"
        b += o.inputBusChannels.toString
      }
      if (o.outputBusChannels != 8) {
        b += "-o"
        b += o.outputBusChannels.toString
      }
      if (o.blockSize != 64) {
        b += "-z"
        b += o.blockSize.toString
      }
      if (o.audioBuffers != 1024) {
        b += "-b"
        b += o.audioBuffers.toString
      }
      if (o.maxNodes != 1024) {
        b += "-n"
        b += o.maxNodes.toString
      }
      if (o.maxSynthDefs != 1024) {
        b += "-d"
        b += o.maxSynthDefs.toString
      }
      if (o.memorySize != 8192) {
        b += "-m"
        b += o.memorySize.toString
      }
      if (o.wireBuffers != 64) {
        b += "-w"
        b += o.wireBuffers.toString
      }
      if (o.randomSeeds != 64) {
        b += "-r"
        b += o.randomSeeds.toString
      }
      if (!o.loadSynthDefs) {
        b += "-D"
        b += "0"
      }
      o.machPortName.foreach {
        case (send, reply) =>
          b += "-M"
          b += send
          b += reply
      }
      if (o.verbosity != 0) {
        b += "-v"
        b += o.verbosity.toString
      }
      if (o.plugInsPaths.nonEmpty) {
        b += "-U"
        b += o.plugInsPaths.mkString(":")
      }
    }
  }

  /**
   * @see [[de.sciss.synth.Server.ConfigBuilder]]
   * @see [[de.sciss.synth.Server.ConfigLike]]
   */
  final class Config private[Server](val programPath: String,
                                     val controlBusChannels: Int,
                                     val audioBusChannels: Int,
                                     val outputBusChannels: Int,
                                     val blockSize: Int,
                                     val sampleRate: Int,
                                     val audioBuffers: Int,
                                     val maxNodes: Int,
                                     val maxSynthDefs: Int,
                                     val memorySize: Int,
                                     val wireBuffers: Int,
                                     val randomSeeds: Int,
                                     val loadSynthDefs: Boolean,
                                     val machPortName: Option[(String, String)],
                                     val verbosity: Int,
                                     val plugInsPaths: List[String],
                                     val restrictedPath: Option[String],
                                     /* val memoryLocking: Boolean, */
                                     val host: String,
                                     val port: Int,
                                     val transport: osc.Transport.Net,
                                     val inputStreamsEnabled: Option[String],
                                     val outputStreamsEnabled: Option[String],
                                     val deviceNames: Option[(String, String)],
                                     val deviceName: Option[String],
                                     val inputBusChannels: Int,
                                     val hardwareBlockSize: Int,
                                     val zeroConf: Boolean,
                                     val maxLogins: Int,
                                     val sessionPassword: Option[String],
                                     val nrtCommandPath: String,
                                     val nrtInputPath: Option[String],
                                     val nrtOutputPath: String,
                                     val nrtHeaderFormat: AudioFileType,
                                     val nrtSampleFormat: SampleFormat)
    extends ConfigLike {
    override def toString = "ServerOptions"
  }

  /**
   * @see [[de.sciss.synth.Server.Config]]
   * @see [[de.sciss.synth.Server.ConfigLike]]
   */
  final class ConfigBuilder private[Server]() extends ConfigLike {
    /**
     * The default `programPath` is read from `defaultProgramPath`
     *
     * @see [[de.sciss.synth.Server# d e f a u l t P r o g r a m P a t h]]
     */
    var programPath: String = defaultProgramPath
    /**
     * The default number of control bus channels is `4096` (scsynth default)
     */
    var controlBusChannels: Int = 4096
    /**
     * The default number of audio bus channels is `128` (scsynth default)
     */
    var audioBusChannels: Int = 128
    /**
     * The default number of output bus channels is `8` (scsynth default)
     */
    var outputBusChannels: Int = 8
    /**
     * The default calculation block size is `64` (scsynth default)
     */
    var blockSize: Int = 64
    /**
     * The default sample rate is `0` (meaning that it is adjusted to
     * the sound card's current rate; scsynth default)
     */
    var sampleRate: Int = 0
    /**
     * The default number of audio buffers is `1024` (scsynth default)
     */
    var audioBuffers: Int = 1024
    /**
     * The default maximum number of nodes is `1024` (scsynth default)
     */
    var maxNodes: Int = 1024
    /**
     * The default maximum number of synth defs is `1024` (scsynth default)
     */
    var maxSynthDefs: Int = 1024
    /**
     * The default memory size is `65536` (64 KB) (higher than scsynth's default of 8 KB)
     */
    var memorySize: Int = 65536 // 8192
    /**
     * The default number of wire buffers is `256` (higher than scsynth's default of `64`).
     */
    var wireBuffers: Int = 256 // 64
    /**
     * The default number of random number generators is `64` (scsynth default)
     */
    var randomSeeds: Int = 64
    /**
     * The default setting for loading synth defs is `true` (scsynth default)
     */
    var loadSynthDefs: Boolean = true
    /**
     * The default settings for mach port name is `None` (scsynth default)
     */
    var machPortName: Option[(String, String)] = None
    /**
     * The default verbosity level is `0` (scsynth default)
     */
    var verbosity: Int = 0
    /**
     * The default setting for plugin path redirection is `Nil`
     * (use standard paths; scsynth default)
     */
    var plugInsPaths: List[String] = Nil
    /**
     * The default setting for restricting file access is `None` (scsynth default)
     */
    var restrictedPath: Option[String] = None

    //     var memoryLocking:         Boolean                    = false

    //   // client only
    //   var clientID:              Int                        = 0
    //   var nodeIDOffset:          Int                        = 1000

    // ---- realtime only ----

    /**
     * (Realtime) The default host name is `127.0.0.1`
     */
    var host: String = "127.0.0.1"
    /**
     * (Realtime) The default port is `57110`.
     */
    var port: Int = 57110
    /**
     * (Realtime) The default transport is `UDP`.
     */
    var transport: osc.Transport.Net = UDP
    /**
     * (Realtime) The default settings for enabled input streams is `None`
     */
    var inputStreamsEnabled: Option[String] = None
    /**
     * (Realtime) The default settings for enabled output streams is `None`
     */
    var outputStreamsEnabled: Option[String] = None

    /**
     * (Realtime) The default device name is `None` (scsynth default; it will
     * use the system default sound card)
     */
    private var deviceNameVar: Option[String] = None
    /**
     * (Realtime) The default input/output device names is `None` (scsynth default; it will
     * use the system default sound card)
     */
    private var deviceNamesVar: Option[(String, String)] = None

    def deviceName: Option[String] = deviceNameVar
    def deviceName_=(value: Option[String]) {
      deviceNameVar = value
      if (value.isDefined) deviceNamesVar = None
    }

    def deviceNames: Option[(String, String)] = deviceNamesVar
    def deviceNames_=(value: Option[(String, String)]) {
      deviceNamesVar = value
      if (value.isDefined) deviceNameVar = None
    }

    /**
     * (Realtime) The default number of input bus channels is `8` (scsynth default)
     */
    var inputBusChannels: Int = 8
    /**
     * (Realtime) The default setting for hardware block size is `0` (meaning that
     * scsynth uses the hardware's current block size; scsynth default)
     */
    var hardwareBlockSize: Int = 0
    /**
     * (Realtime) The default setting for zero-conf is `false` (other than
     * scsynth's default which is `true`)
     */
    var zeroConf: Boolean = false // true
    /**
     * (Realtime) The maximum number of TCP clients is `64` (scsynth default)
     */
    var maxLogins: Int = 64
    /**
     * (Realtime) The default TCP session password is `None`
     */
    var sessionPassword: Option[String] = None

    // ---- nonrealtime only ----
    var nrtCommandPath  : String          = ""
    var nrtInputPath    : Option[String]  = None
    var nrtOutputPath   : String          = ""
    var nrtHeaderFormat : AudioFileType   = AudioFileType.AIFF
    var nrtSampleFormat : SampleFormat    = SampleFormat.Float

    /**
     * Picks and assigns a random free port for the server. This implies that
     * the server will be running on the local machine.
     *
     * As a result, this method will change this config builder's `port` value.
     * The caller must ensure that the `host` and `transport` fields have been
     * decided on before calling this method. Later changes of either of these
     * will render the result invalid.
     *
     * This method will fail with runtime exception if the host is not local.
     */
    def pickPort() {
      require(isLocal)
      transport match {
        case UDP =>
          val tmp = new DatagramSocket()
          port = tmp.getLocalPort
          tmp.close()
        case TCP =>
          val tmp = new ServerSocket(0)
          port = tmp.getLocalPort
          tmp.close()
      }
    }

    /**
     * Checks if the currently set `host` is located on the local machine.
     */
    def isLocal: Boolean = {
      val hostAddr = InetAddress.getByName(host)
      hostAddr.isLoopbackAddress || hostAddr.isSiteLocalAddress
    }

    def build : Config = new Config(
      programPath, controlBusChannels, audioBusChannels, outputBusChannels, blockSize, sampleRate, audioBuffers,
      maxNodes, maxSynthDefs, memorySize, wireBuffers, randomSeeds, loadSynthDefs, machPortName, verbosity,
      plugInsPaths, restrictedPath, /* memoryLocking, */ host, port, transport, inputStreamsEnabled, outputStreamsEnabled,
      deviceNames, deviceName, inputBusChannels, hardwareBlockSize, zeroConf, maxLogins, sessionPassword,
      nrtCommandPath,
      nrtInputPath, nrtOutputPath, nrtHeaderFormat, nrtSampleFormat)
  }

  def boot: ServerConnection = boot()()

  def boot(name: String = "localhost", config: Config = Config().build,
           clientConfig: Client.Config = Client.Config().build)
          (listener: ServerConnection.Listener = PartialFunction.empty): ServerConnection = {
    val sc = initBoot(name, config, clientConfig)
    if (!(listener eq PartialFunction.empty)) sc.addListener(listener)
    sc.start()
    sc
  }

  private def initBoot(name: String = "localhost", config: Config = Config().build,
                       clientConfig: Client.Config = Client.Config().build) = {
    val (addr, c) = prepareConnection(config, clientConfig)
    new impl.Booting(name, c, addr, config, clientConfig, true)
  }

  def connect: ServerConnection = connect()()

  def connect(name: String = "localhost", config: Config = Config().build,
              clientConfig: Client.Config = Client.Config().build)
             (listener: ServerConnection.Listener = PartialFunction.empty): ServerConnection = {
    val (addr, c) = prepareConnection(config, clientConfig)
    val sc = new impl.Connection(name, c, addr, config, clientConfig, true)
    if (!(listener eq PartialFunction.empty)) sc.addListener(listener)
    sc.start()
    sc
  }

  def run( code: Server => Unit ) { run()( code )}

  /**
   * Utility method to test code quickly with a running server. This boots a
   * server and executes the passed in code when the server is up. A shutdown
   * hook is registered to make sure the server is destroyed when the VM exits.
   */
  def run(config: Config = Config().build)(code: Server => Unit) {
    //      val b = boot( config = config )
    val sync = new AnyRef
    var s: Server = null
    val sc = initBoot(config = config)
    val li: ServerConnection.Listener = {
      case ServerConnection.Running(srv) => sync.synchronized {
        s = srv
      }; code(srv)
    }
    sc.addListener(li)
    Runtime.getRuntime.addShutdownHook(new Thread {
      override def run() {
        sync.synchronized {
          if (s != null) {
            if (s.condition != Server.Offline) s.quit()
          } else sc.abort()
        }
      }
    })
    sc.start()
  }

  /**
   * Creates an unconnected server proxy. This may be useful for creating NRT command files.
   * Any attempt to try to send messages to the server will fail.
   */
  def dummy(name: String = "dummy", config: Config = Config().build,
            clientConfig: Client.Config = Client.Config().build): Server = {
    val (addr, c) = prepareConnection(config, clientConfig)
    new impl.ServerImpl(name, c, addr, config, clientConfig, StatusReply(0, 0, 0, 0, 0f, 0f, 0.0, 0.0))
  }

  private def prepareConnection(config: Config, clientConfig: Client.Config): (InetSocketAddress, osc.Client) = {
    val addr = new InetSocketAddress(config.host, config.port)
    val clientAddr = clientConfig.addr getOrElse {
      if (addr.getAddress.isLoopbackAddress)
        new InetSocketAddress("127.0.0.1", 0)
      else
        new InetSocketAddress(InetAddress.getLocalHost, 0)
    }
    val c = createClient(config.transport, addr, clientAddr)
    (addr, c)
  }

  def allocPort(transport: osc.Transport): Int = {
    transport match {
      case TCP =>
        val ss = new ServerSocket(0)
        try {
          ss.getLocalPort
        } finally {
          ss.close()
        }

      case UDP =>
        val ds = new DatagramSocket()
        try {
          ds.getLocalPort
        } finally {
          ds.close()
        }

      case other => sys.error("Unsupported transport : " + other.name)
    }
  }

  def printError(name: String, t: Throwable) {
    println(name + " : ")
    t.printStackTrace()
  }

  implicit def defaultGroup(s: Server) = s.defaultGroup

  type Listener = Model.Listener[Update]

  sealed trait Update
  sealed trait Condition extends Update
  case object  Running   extends Condition
  case object  Offline   extends Condition
  private[synth] case object NoPending extends Condition

  final case class Counts(c: message.StatusReply) extends Update

  private def createClient(transport: osc.Transport.Net, serverAddr: InetSocketAddress,
                           clientAddr: InetSocketAddress): osc.Client = {
    val client = transport match {
      case UDP =>
        val cfg = UDP.Config()
        cfg.localSocketAddress = clientAddr
        cfg.codec = message.ServerCodec
        cfg.bufferSize = 0x10000
        UDP.Client(serverAddr, cfg)
      case TCP =>
        val cfg = TCP.Config()
        cfg.codec = message.ServerCodec
        cfg.localSocketAddress = clientAddr
        cfg.bufferSize = 0x10000
        TCP.Client(serverAddr, cfg)
    }
    client
  }
}

sealed trait ServerLike {
  def name: String
  def config: Server.Config
  def addr: InetSocketAddress
}

object ServerConnection {
  type Listener = Model.Listener[Condition]

  sealed abstract class Condition
  case class Preparing(server: Server) extends Condition
  case class Running(server: Server) extends Condition
  case object Aborted extends Condition
}

trait ServerConnection extends ServerLike with Model[ServerConnection.Condition] {
  //   def server : Future[ Server ]
  def abort(): Unit // : Future[ Unit ]
}

trait Server extends ServerLike with Model[Server.Update] {
   server =>

   import Server._

   def clientConfig : Client.Config

   def rootNode     : Group
   def defaultGroup : Group
   def nodeManager  : NodeManager
   def bufManager   : BufferManager

   def isLocal      : Boolean
   def isConnected  : Boolean
   def isRunning    : Boolean
   def isOffline    : Boolean

   def nextNodeID(): Int
   def nextSyncID(): Int

  def allocControlBus(numChannels: Int): Int
  def allocAudioBus  (numChannels: Int): Int

  def freeControlBus(index: Int): Unit
  def freeAudioBus  (index: Int): Unit

  def allocBuffer(numChannels: Int): Int
  def freeBuffer (index      : Int): Unit

  def !(p: osc.Packet): Unit

//   /**
//    * Sends out an OSC packet that generates some kind of reply, and
//    * returns immediately a `RevocableFuture` representing the parsed reply.
//    * This parsing is done by a handler which is registered.
//    * The handler is tested for each incoming OSC message (using its
//    * `isDefinedAt` method) and invoked and removed in case of a
//    * match. Note that the caller is responsible for timing out
//    * the handler after a reasonable time. To do this, the
//    * method `revoke` on the returned future must be called, which
//    * will silently unregister the handler.
//    *
//    * '''Warning''': It is crucial that the Future is awaited
//    * only within a dedicated actor thread. In particular you must
//    * be careful and aware of the fact that the handler is executed
//    * on the OSC receiver actor's body, and that you must not
//    * try to await the future from ''any'' handler function
//    * registered with OSC reception, because it would not be
//    * possible to pull the reply message of the OSC receiver's
//    * mailbox while the actor body blocks.
//    *
//    * @param   p        the packet to send out
//    * @param   handler  the handler to match against incoming messages
//    *    or timeout
//    * @return  the future representing the parsed reply, and providing
//    *    a `revoke` method to issue a timeout.
//    *
//    * @see  [[scala.actors.Futures]]
//    */
//   def !![ A ]( p: Packet, handler: PartialFunction[ Message, A ]) : RevocableFuture[ A ]

  /**
   * Sends out an OSC packet that generates some kind of reply, and
   * returns immediately. It registers a handler to parse that reply.
   * The handler is tested for each incoming OSC message (using its
   * `isDefinedAt` method) and invoked and removed in case of a
   * match, completing the returned future.
   *
   * If the handler doesn't match in the given timeout period,
   * the future fails with a `Timeout` exception, and the handler is removed.
   *
   * @param   packet    the packet to send out
   * @param   timeout   the timeout in milliseconds
   * @param   handler   the handler to match against incoming messages
   * @return   a future of the successfully completed handler or timeout exception
   *
   * @see  [[de.sciss.synth.message.Timeout]]
   */
  def !![A](packet: osc.Packet, timeout: Duration = 6.seconds)(handler: PartialFunction[osc.Message, A]): Future[A]

  def counts: StatusReply

  def sampleRate: Double

  def dumpTree(controls: Boolean = false): Unit

  def condition: Condition

  def startAliveThread(delay: Float = 0.25f, period: Float = 0.25f, deathBounces: Int = 25): Unit

  def stopAliveThread(): Unit

  def queryCounts(): Unit

  final def syncMsg(): message.Sync = message.Sync(nextSyncID())

  def dumpOSC(mode: osc.Dump = osc.Dump.Text): Unit

  def quit(): Unit

  final def quitMsg = message.ServerQuit

  def dispose(): Unit

  private[synth] def addResponder   (resp: message.Responder): Unit
  private[synth] def removeResponder(resp: message.Responder): Unit

  override def toString = "<" + name + ">"
}