/*
 * ReverbUGens.scala
 * (ScalaCollider-UGens)
 *
 * This is a synthetically generated file.
 * Created: Thu Oct 27 17:42:00 BST 2011
 * ScalaCollider-UGens version: 0.14-SNAPSHOT
 */

package de.sciss.synth
package ugen
import collection.immutable.{IndexedSeq => IIdxSeq}
import aux.UGenHelper._
object FreeVerb {
   def ar(in: GE, mix: GE = 0.33f, room: GE = 0.5f, damp: GE = 0.5f) = apply(in, mix, room, damp)
}
final case class FreeVerb(in: GE, mix: GE, room: GE, damp: GE) extends UGenSource.SingleOut("FreeVerb") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, mix.expand, room.expand, damp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.SingleOut(name, audio, _args)
}
object FreeVerb2 {
   def ar(left: GE, right: GE, mix: GE = 0.33f, room: GE = 0.5f, damp: GE = 0.5f) = apply(left, right, mix, room, damp)
}
final case class FreeVerb2(left: GE, right: GE, mix: GE, room: GE, damp: GE) extends UGenSource.MultiOut("FreeVerb2") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(left.expand, right.expand, mix.expand, room.expand, damp.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(2)(audio), _args)
}
object GVerb {
   def ar(in: GE, roomSize: GE = 10.0f, revTime: GE = 3.0f, damping: GE = 0.5f, inputBW: GE = 0.5f, spread: GE = 15.0f, dryLevel: GE = 1.0f, earlyRefLevel: GE = 0.7f, tailLevel: GE = 0.5f, maxRoomSize: GE = 300.0f) = apply(in, roomSize, revTime, damping, inputBW, spread, dryLevel, earlyRefLevel, tailLevel, maxRoomSize)
}
final case class GVerb(in: GE, roomSize: GE, revTime: GE, damping: GE, inputBW: GE, spread: GE, dryLevel: GE, earlyRefLevel: GE, tailLevel: GE, maxRoomSize: GE) extends UGenSource.MultiOut("GVerb") with AudioRated {
   protected def makeUGens: UGenInLike = unwrap(IIdxSeq(in.expand, roomSize.expand, revTime.expand, damping.expand, inputBW.expand, spread.expand, dryLevel.expand, earlyRefLevel.expand, tailLevel.expand, maxRoomSize.expand))
   protected def makeUGen(_args: IIdxSeq[UGenIn]): UGenInLike = new UGen.MultiOut(name, audio, IIdxSeq.fill(2)(audio), _args)
}