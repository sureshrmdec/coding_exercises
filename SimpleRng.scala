trait RNG {
   def nextInt: (Int, RNG)
   }


case class Simple(seed: Long) extends RNG {
   def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
      val nextRNG = Simple(newSeed)
         val n = (newSeed >>> 16).toInt
      (n, nextRNG)
   } 
}



object SimpleRng {
   type Rand[+A] = RNG => (A, RNG)
   def unit[A](a: A): Rand[A] = rng => (a, rng) 
      def map[A,B](s: Rand[A])(f: A => B): Rand[B] =
      rng => {
      val (a, rng2) = s(rng)
         (f(a), rng2)
   } 

   def main(args: Array[String]) = {
      println("r: " + Simple(20).nextInt) 
      val int: Rand[Int] = _.nextInt 
      println("val: " + int)
      //val ns: Rand[List[Int]] = for {
      //   x <- int
      //   y <- int
      //   xs <- ints(x)
      //} yield xs.map(_ % y)
      //println(ns)
   }
}
