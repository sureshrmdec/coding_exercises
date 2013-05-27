// autoimports
// scala.Int, scala.Boolean, java.lang, scala.Predef.require, scala.Predef.assert
class Rational(x: Int, y: Int) {
    require(y != 0, "denominator must be nonzer")
    def this(x: Int) = this(x, 1)

    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    private val g = gcd(x, y)
    def numer = x / g
    def denom = y / g

    def less(that: Rational) = numer * that.denom < that.numer * denom 

    def max(that: Rational) = if (this.less(that)) that else this
    def neg =
        new Rational(0-numer, denom)

    def sub(that: Rational) = add(that.neg)
    def add(that: Rational) =
        new Rational(
            numer * that.denom + that.numer * denom, denom * that.denom)

    override def toString = numer + "/" + denom 
}
