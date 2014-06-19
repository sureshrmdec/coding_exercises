package myproj.coffee

object Lazy {
  def if2[A](cond: Boolean, onTrue: () => A, onFalse: () => A): A =
    if (cond) onTrue() else onFalse()

  def if3[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
    if (cond) onTrue else onFalse

  def maybeTwice2(b: Boolean, i: => Int) = {
    lazy val j = i
    if (b) j + j else 0
  }
  def maybeTwice(b: Boolean, i: => Int) = if (b) i + i else 0
  def main(args: Array[String]): Unit = {
    if2(3 < 22,
      () => println("a"),
      () => println("b"))

    if3(false, sys.error("fail"), 3)
    println("---")
    println(maybeTwice(true, { println("hi"); 1 + 51 }))

    println(maybeTwice2(true, { println("hi"); 1 + 41 }))
  }

}
