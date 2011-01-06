/*
 * ReverbUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Jan 06 16:41:05 GMT 2011
 * ScalaCollider-UGen version: 0.10
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import UGenHelper._
object FreeVerb {
   def ar(in: GE[audio, UGenIn[audio]], mix: AnyGE = 0.33f, room: AnyGE = 0.5f, damp: AnyGE = 0.5f) = apply(in, mix, room, damp)
}
case class FreeVerb(in: AnyGE, mix: AnyGE, room: AnyGE, damp: AnyGE) extends GE[audio, FreeVerbUGen] with AudioRated {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _mix: IIdxSeq[AnyUGenIn] = mix.expand
      val _room: IIdxSeq[AnyUGenIn] = room.expand
      val _damp: IIdxSeq[AnyUGenIn] = damp.expand
      val _sz_in = _in.size
      val _sz_mix = _mix.size
      val _sz_room = _room.size
      val _sz_damp = _damp.size
      val _exp_ = maxInt(_sz_in, _sz_mix, _sz_room, _sz_damp)
      IIdxSeq.tabulate(_exp_)(i => FreeVerbUGen(_in(i.%(_sz_in)), _mix(i.%(_sz_mix)), _room(i.%(_sz_room)), _damp(i.%(_sz_damp))))
   }
}
case class FreeVerbUGen(in: AnyUGenIn, mix: AnyUGenIn, room: AnyUGenIn, damp: AnyUGenIn) extends SingleOutUGen[audio](IIdxSeq(in, mix, room, damp)) with AudioRated
object FreeVerb2 {
   def ar(left: GE[audio, UGenIn[audio]], right: GE[audio, UGenIn[audio]], mix: AnyGE = 0.33f, room: AnyGE = 0.5f, damp: AnyGE = 0.5f) = apply(left, right, mix, room, damp)
}
case class FreeVerb2(left: AnyGE, right: AnyGE, mix: AnyGE, room: AnyGE, damp: AnyGE) extends Expands[FreeVerb2UGen] with AudioRated {
   def expand = {
      val _left: IIdxSeq[AnyUGenIn] = left.expand
      val _right: IIdxSeq[AnyUGenIn] = right.expand
      val _mix: IIdxSeq[AnyUGenIn] = mix.expand
      val _room: IIdxSeq[AnyUGenIn] = room.expand
      val _damp: IIdxSeq[AnyUGenIn] = damp.expand
      val _sz_left = _left.size
      val _sz_right = _right.size
      val _sz_mix = _mix.size
      val _sz_room = _room.size
      val _sz_damp = _damp.size
      val _exp_ = maxInt(_sz_left, _sz_right, _sz_mix, _sz_room, _sz_damp)
      IIdxSeq.tabulate(_exp_)(i => FreeVerb2UGen(_left(i.%(_sz_left)), _right(i.%(_sz_right)), _mix(i.%(_sz_mix)), _room(i.%(_sz_room)), _damp(i.%(_sz_damp))))
   }
}
case class FreeVerb2UGen(left: AnyUGenIn, right: AnyUGenIn, mix: AnyUGenIn, room: AnyUGenIn, damp: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(audio), IIdxSeq(left, right, mix, room, damp)) with AudioRated
object GVerb {
   def ar(in: GE[audio, UGenIn[audio]], roomSize: AnyGE = 10.0f, revTime: AnyGE = 3.0f, damping: AnyGE = 0.5f, inputBW: AnyGE = 0.5f, spread: AnyGE = 15.0f, dryLevel: AnyGE = 1.0f, earlyRefLevel: AnyGE = 0.7f, tailLevel: AnyGE = 0.5f, maxRoomSize: AnyGE = 300.0f) = apply(in, roomSize, revTime, damping, inputBW, spread, dryLevel, earlyRefLevel, tailLevel, maxRoomSize)
}
case class GVerb(in: AnyGE, roomSize: AnyGE, revTime: AnyGE, damping: AnyGE, inputBW: AnyGE, spread: AnyGE, dryLevel: AnyGE, earlyRefLevel: AnyGE, tailLevel: AnyGE, maxRoomSize: AnyGE) extends Expands[GVerbUGen] with AudioRated {
   def expand = {
      val _in: IIdxSeq[AnyUGenIn] = in.expand
      val _roomSize: IIdxSeq[AnyUGenIn] = roomSize.expand
      val _revTime: IIdxSeq[AnyUGenIn] = revTime.expand
      val _damping: IIdxSeq[AnyUGenIn] = damping.expand
      val _inputBW: IIdxSeq[AnyUGenIn] = inputBW.expand
      val _spread: IIdxSeq[AnyUGenIn] = spread.expand
      val _dryLevel: IIdxSeq[AnyUGenIn] = dryLevel.expand
      val _earlyRefLevel: IIdxSeq[AnyUGenIn] = earlyRefLevel.expand
      val _tailLevel: IIdxSeq[AnyUGenIn] = tailLevel.expand
      val _maxRoomSize: IIdxSeq[AnyUGenIn] = maxRoomSize.expand
      val _sz_in = _in.size
      val _sz_roomSize = _roomSize.size
      val _sz_revTime = _revTime.size
      val _sz_damping = _damping.size
      val _sz_inputBW = _inputBW.size
      val _sz_spread = _spread.size
      val _sz_dryLevel = _dryLevel.size
      val _sz_earlyRefLevel = _earlyRefLevel.size
      val _sz_tailLevel = _tailLevel.size
      val _sz_maxRoomSize = _maxRoomSize.size
      val _exp_ = maxInt(_sz_in, _sz_roomSize, _sz_revTime, _sz_damping, _sz_inputBW, _sz_spread, _sz_dryLevel, _sz_earlyRefLevel, _sz_tailLevel, _sz_maxRoomSize)
      IIdxSeq.tabulate(_exp_)(i => GVerbUGen(_in(i.%(_sz_in)), _roomSize(i.%(_sz_roomSize)), _revTime(i.%(_sz_revTime)), _damping(i.%(_sz_damping)), _inputBW(i.%(_sz_inputBW)), _spread(i.%(_sz_spread)), _dryLevel(i.%(_sz_dryLevel)), _earlyRefLevel(i.%(_sz_earlyRefLevel)), _tailLevel(i.%(_sz_tailLevel)), _maxRoomSize(i.%(_sz_maxRoomSize))))
   }
}
case class GVerbUGen(in: AnyUGenIn, roomSize: AnyUGenIn, revTime: AnyUGenIn, damping: AnyUGenIn, inputBW: AnyUGenIn, spread: AnyUGenIn, dryLevel: AnyUGenIn, earlyRefLevel: AnyUGenIn, tailLevel: AnyUGenIn, maxRoomSize: AnyUGenIn) extends MultiOutUGen(IIdxSeq.fill(2)(audio), IIdxSeq(in, roomSize, revTime, damping, inputBW, spread, dryLevel, earlyRefLevel, tailLevel, maxRoomSize)) with AudioRated