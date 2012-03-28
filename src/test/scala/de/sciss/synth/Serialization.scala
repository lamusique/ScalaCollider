package de.sciss.synth

object Serialization extends App {
   import java.io._
   import ugen._
   println( "Start" )
   val in = SynthGraph {
       val f1 = 0.4 // "freq1".kr(0.4)  // ControlFactory -- NotSerializableException
       val f2 = 8 // "freq2".kr(8)
       val d  = 0.90375 // "detune".kr(0.90375)
       val f = LFSaw(audio, f1, 0).madd(24, LFSaw(audio, List(f2, f2 /* *d */), 0).madd(3, 80)) // .midicps // UnaryOp broken
       val x = CombN.ar(SinOsc.ar(f) /* 0.04 */, 0.2, 0.2, 4) // BinaryOp broken
       Out.ar( 0, x )
   }
   val baos = new ByteArrayOutputStream()
   val oos = new ObjectOutputStream( baos )
   oos.writeObject( in )
   val bais = new ByteArrayInputStream( baos.toByteArray )
   val ois = new ObjectInputStream( bais )
   val out = ois.readObject.asInstanceOf[ SynthGraph ]
   println( "Ok" )
}
