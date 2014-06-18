package myproj.coffee

case class Employee(name: String, department: String) {

}
object Employee {
  def lookupByName(name: String): Option[Employee] = None

  def Try[A](a: => A): Option[A] =
    try Some(a)
    catch { case e: Exception => None }

  def parseInsuranceRateQuote(
    age: String,
    numberOfSpeedingTickets: String): Option[Double] = {
    val optAge: Option[Int] = Try { age.toInt }
    val optTickets: Option[Int] = Try { numberOfSpeedingTickets.toInt }
    map2(optAge, optTickets)(insuranceRateQuote)
  }
  def insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double = 0

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    a flatMap (aa =>
      b map (bb =>
        f(aa, bb)))

  def map3[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    for {
      aa <- a
      bb <- b
    } yield f(aa, bb)

  def main(args: Array[String]): Unit = {
    val d: Option[String] =
      lookupByName("Joe").map(_.department)
    println(d)
    val dept: String =
      lookupByName("Joe").
        map(_.department).
        filter(_ != "Accounting").
        getOrElse("Default Dept")
    println(dept)

  }
}
