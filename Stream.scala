package myproj.coffee

sealed trait Stream[+A]
case object Empty extends Stream[Nothing]
case class Const[+A](h: () => A, t: () => Stream[A]) extends Stream[A]
object Stream {
   // smart constructor
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Const(() => head, () => tail)
  }
  def empty[A]: Stream[A] = Empty
  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}

// Stream(1,2,3,4).map(_ + 10).filter(_ % 2 == 0).toList
// val ones: Stream[Int] = Stream.cons(1, ones)
// ones.take(5).toList 
// ones.exists(_ % 2 != 0)
