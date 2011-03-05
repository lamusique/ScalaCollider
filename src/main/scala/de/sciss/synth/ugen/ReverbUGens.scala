/*
 * ReverbUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Fri Mar 04 23:36:58 GMT 2011
 * ScalaCollider-UGen version: 0.11
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object FreeVerb {
   def ar(in: GE[audio], mix: AnyGE = 0.33f, room: AnyGE = 0.5f, damp: AnyGE = 0.5f) = apply(in, mix, room, damp)
}
final case class FreeVerb(in: AnyGE, mix: AnyGE, room: AnyGE, damp: AnyGE) extends UGenSource.SingleOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, mix.expand, room.expand, damp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut("FreeVerb", audio, _args)
}
object FreeVerb2 {
   def ar(left: GE[audio], right: GE[audio], mix: AnyGE = 0.33f, room: AnyGE = 0.5f, damp: AnyGE = 0.5f) = apply(left, right, mix, room, damp)
}
final case class FreeVerb2(left: AnyGE, right: AnyGE, mix: AnyGE, room: AnyGE, damp: AnyGE) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(left.expand, right.expand, mix.expand, room.expand, damp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("FreeVerb2", audio, IIdxSeq.fill(2)(audio), _args)
}
object GVerb {
   def ar(in: GE[audio], roomSize: AnyGE = 10.0f, revTime: AnyGE = 3.0f, damping: AnyGE = 0.5f, inputBW: AnyGE = 0.5f, spread: AnyGE = 15.0f, dryLevel: AnyGE = 1.0f, earlyRefLevel: AnyGE = 0.7f, tailLevel: AnyGE = 0.5f, maxRoomSize: AnyGE = 300.0f) = apply(in, roomSize, revTime, damping, inputBW, spread, dryLevel, earlyRefLevel, tailLevel, maxRoomSize)
}
final case class GVerb(in: AnyGE, roomSize: AnyGE, revTime: AnyGE, damping: AnyGE, inputBW: AnyGE, spread: AnyGE, dryLevel: AnyGE, earlyRefLevel: AnyGE, tailLevel: AnyGE, maxRoomSize: AnyGE) extends UGenSource.MultiOut[audio] {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, roomSize.expand, revTime.expand, damping.expand, inputBW.expand, spread.expand, dryLevel.expand, earlyRefLevel.expand, tailLevel.expand, maxRoomSize.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut("GVerb", audio, IIdxSeq.fill(2)(audio), _args)
}