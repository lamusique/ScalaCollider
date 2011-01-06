/*
 * MachineListening.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 06 16:41:04 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object BeatTrack {
   def kr(chain: AnyGE, lock: AnyGE = 0.0f) = apply(chain, lock)
}
case class BeatTrack(chain: AnyGE, lock: AnyGE) extends GE[control, BeatTrackUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _lock: IIdxSeq[AnyUGenIn] = lock.expand
      val _sz_chain = _chain.size
      val _sz_lock = _lock.size
      val _exp_ = maxInt(_sz_chain, _sz_lock)
      IIdxSeq.tabulate(_exp_)(i => BeatTrackUGen(_chain(i.%(_sz_chain)), _lock(i.%(_sz_lock))))
   }
}
case class BeatTrackUGen(chain: AnyUGenIn, lock: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, lock)) with ControlRated
object Loudness {
   def kr(chain: AnyGE, smask: AnyGE = 0.25f, tmask: AnyGE = 1.0f) = apply(chain, smask, tmask)
}
case class Loudness(chain: AnyGE, smask: AnyGE, tmask: AnyGE) extends GE[control, LoudnessUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _smask: IIdxSeq[AnyUGenIn] = smask.expand
      val _tmask: IIdxSeq[AnyUGenIn] = tmask.expand
      val _sz_chain = _chain.size
      val _sz_smask = _smask.size
      val _sz_tmask = _tmask.size
      val _exp_ = maxInt(_sz_chain, _sz_smask, _sz_tmask)
      IIdxSeq.tabulate(_exp_)(i => LoudnessUGen(_chain(i.%(_sz_chain)), _smask(i.%(_sz_smask)), _tmask(i.%(_sz_tmask))))
   }
}
case class LoudnessUGen(chain: AnyUGenIn, smask: AnyUGenIn, tmask: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, smask, tmask)) with ControlRated
object KeyTrack {
   def kr(chain: AnyGE, keyDecay: AnyGE = 2.0f, chromaLeak: AnyGE = 0.5f) = apply(chain, keyDecay, chromaLeak)
}
case class KeyTrack(chain: AnyGE, keyDecay: AnyGE, chromaLeak: AnyGE) extends GE[control, KeyTrackUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _keyDecay: IIdxSeq[AnyUGenIn] = keyDecay.expand
      val _chromaLeak: IIdxSeq[AnyUGenIn] = chromaLeak.expand
      val _sz_chain = _chain.size
      val _sz_keyDecay = _keyDecay.size
      val _sz_chromaLeak = _chromaLeak.size
      val _exp_ = maxInt(_sz_chain, _sz_keyDecay, _sz_chromaLeak)
      IIdxSeq.tabulate(_exp_)(i => KeyTrackUGen(_chain(i.%(_sz_chain)), _keyDecay(i.%(_sz_keyDecay)), _chromaLeak(i.%(_sz_chromaLeak))))
   }
}
case class KeyTrackUGen(chain: AnyUGenIn, keyDecay: AnyUGenIn, chromaLeak: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, keyDecay, chromaLeak)) with ControlRated
object MFCC {
   def kr(chain: AnyGE, numCoeffs: Int = 13) = apply(chain, numCoeffs)
}
case class MFCC(chain: AnyGE, numCoeffs: Int) extends Expands[MFCCUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => MFCCUGen(_chain(i), numCoeffs))
   }
}
case class MFCCUGen(chain: AnyUGenIn, numCoeffs: Int) extends MultiOutUGen(IIdxSeq.fill(numCoeffs)(control), IIdxSeq(chain)) with ControlRated
object Onsets {
   def kr(chain: AnyGE, thresh: AnyGE = 0.5f, fun: AnyGE = 3.0f, decay: AnyGE = 1.0f, noiseFloor: AnyGE = 0.1f, minGap: AnyGE = 10.0f, medianSpan: AnyGE = 11.0f, whType: AnyGE = 1.0f, raw: AnyGE = 0.0f) = apply(chain, thresh, fun, decay, noiseFloor, minGap, medianSpan, whType, raw)
}
case class Onsets(chain: AnyGE, thresh: AnyGE, fun: AnyGE, decay: AnyGE, noiseFloor: AnyGE, minGap: AnyGE, medianSpan: AnyGE, whType: AnyGE, raw: AnyGE) extends GE[control, OnsetsUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _thresh: IIdxSeq[AnyUGenIn] = thresh.expand
      val _fun: IIdxSeq[AnyUGenIn] = fun.expand
      val _decay: IIdxSeq[AnyUGenIn] = decay.expand
      val _noiseFloor: IIdxSeq[AnyUGenIn] = noiseFloor.expand
      val _minGap: IIdxSeq[AnyUGenIn] = minGap.expand
      val _medianSpan: IIdxSeq[AnyUGenIn] = medianSpan.expand
      val _whType: IIdxSeq[AnyUGenIn] = whType.expand
      val _raw: IIdxSeq[AnyUGenIn] = raw.expand
      val _sz_chain = _chain.size
      val _sz_thresh = _thresh.size
      val _sz_fun = _fun.size
      val _sz_decay = _decay.size
      val _sz_noiseFloor = _noiseFloor.size
      val _sz_minGap = _minGap.size
      val _sz_medianSpan = _medianSpan.size
      val _sz_whType = _whType.size
      val _sz_raw = _raw.size
      val _exp_ = maxInt(_sz_chain, _sz_thresh, _sz_fun, _sz_decay, _sz_noiseFloor, _sz_minGap, _sz_medianSpan, _sz_whType, _sz_raw)
      IIdxSeq.tabulate(_exp_)(i => OnsetsUGen(_chain(i.%(_sz_chain)), _thresh(i.%(_sz_thresh)), _fun(i.%(_sz_fun)), _decay(i.%(_sz_decay)), _noiseFloor(i.%(_sz_noiseFloor)), _minGap(i.%(_sz_minGap)), _medianSpan(i.%(_sz_medianSpan)), _whType(i.%(_sz_whType)), _raw(i.%(_sz_raw))))
   }
}
case class OnsetsUGen(chain: AnyUGenIn, thresh: AnyUGenIn, fun: AnyUGenIn, decay: AnyUGenIn, noiseFloor: AnyUGenIn, minGap: AnyUGenIn, medianSpan: AnyUGenIn, whType: AnyUGenIn, raw: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, thresh, fun, decay, noiseFloor, minGap, medianSpan, whType, raw)) with ControlRated
object BeatTrack2 {
   def kr(bus: AnyGE, numChannels: AnyGE, winSize: AnyGE = 2.0f, phaseSpacing: AnyGE = 0.02f, lock: AnyGE = 0.0f, weighting: AnyGE = -2.1f) = apply(bus, numChannels, winSize, phaseSpacing, lock, weighting)
}
case class BeatTrack2(bus: AnyGE, numChannels: AnyGE, winSize: AnyGE, phaseSpacing: AnyGE, lock: AnyGE, weighting: AnyGE) extends Expands[BeatTrack2UGen] with ControlRated {
   def expand = {
      val _bus: IIdxSeq[AnyUGenIn] = bus.expand
      val _numChannels: IIdxSeq[AnyUGenIn] = numChannels.expand
      val _winSize: IIdxSeq[AnyUGenIn] = winSize.expand
      val _phaseSpacing: IIdxSeq[AnyUGenIn] = phaseSpacing.expand
      val _lock: IIdxSeq[AnyUGenIn] = lock.expand
      val _weighting: IIdxSeq[AnyUGenIn] = weighting.expand
      val _sz_bus = _bus.size
      val _sz_numChannels = _numChannels.size
      val _sz_winSize = _winSize.size
      val _sz_phaseSpacing = _phaseSpacing.size
      val _sz_lock = _lock.size
      val _sz_weighting = _weighting.size
      val _exp_ = maxInt(_sz_bus, _sz_numChannels, _sz_winSize, _sz_phaseSpacing, _sz_lock, _sz_weighting)
      IIdxSeq.tabulate(_exp_)(i => BeatTrack2UGen(_bus(i.%(_sz_bus)), _numChannels(i.%(_sz_numChannels)), _winSize(i.%(_sz_winSize)), _phaseSpacing(i.%(_sz_phaseSpacing)), _lock(i.%(_sz_lock)), _weighting(i.%(_sz_weighting))))
   }
}
case class BeatTrack2UGen(bus: AnyUGenIn, numChannels: AnyUGenIn, winSize: AnyUGenIn, phaseSpacing: AnyUGenIn, lock: AnyUGenIn, weighting: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(6)(control), IIdxSeq(bus, numChannels, winSize, phaseSpacing, lock, weighting)) with ControlRated
object SpecFlatness {
   def kr(chain: AnyGE) = apply(chain)
}
case class SpecFlatness(chain: AnyGE) extends GE[control, SpecFlatnessUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => SpecFlatnessUGen(_chain(i)))
   }
}
case class SpecFlatnessUGen(chain: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain)) with ControlRated
object SpecPcile {
   def kr(chain: AnyGE, percent: AnyGE = 0.5f, interp: AnyGE = 0.0f) = apply(chain, percent, interp)
}
case class SpecPcile(chain: AnyGE, percent: AnyGE, interp: AnyGE) extends GE[control, SpecPcileUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      val _percent: IIdxSeq[AnyUGenIn] = percent.expand
      val _interp: IIdxSeq[AnyUGenIn] = interp.expand
      val _sz_chain = _chain.size
      val _sz_percent = _percent.size
      val _sz_interp = _interp.size
      val _exp_ = maxInt(_sz_chain, _sz_percent, _sz_interp)
      IIdxSeq.tabulate(_exp_)(i => SpecPcileUGen(_chain(i.%(_sz_chain)), _percent(i.%(_sz_percent)), _interp(i.%(_sz_interp))))
   }
}
case class SpecPcileUGen(chain: AnyUGenIn, percent: AnyUGenIn, interp: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain, percent, interp)) with ControlRated
object SpecCentroid {
   def kr(chain: AnyGE) = apply(chain)
}
case class SpecCentroid(chain: AnyGE) extends GE[control, SpecCentroidUGen] with ControlRated {
   def expand = {
      val _chain: IIdxSeq[AnyUGenIn] = chain.expand
      IIdxSeq.tabulate(_chain.size)(i => SpecCentroidUGen(_chain(i)))
   }
}
case class SpecCentroidUGen(chain: AnyUGenIn) extends SingleOutUGen[control](IIdxSeq(chain)) with ControlRated