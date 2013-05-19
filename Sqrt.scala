// uses Newton's method
object Sqrt {
    def abs(x: Double) = if (x >= 0) x else -x

    def sqrt(x: Double) = {
        def sqrtIter(guess: Double): Double =
            if (isGoodEnough(guess)) guess 
            else sqrtIter(improve(guess)) 
        def isGoodEnough(guess: Double) =
            abs(guess * guess - x) / x < 0.0001 // note we divide by x so that it works for both large and small numbers

        def improve(guess: Double) =
            (guess + x / guess) / 2
        sqrtIter(1.0)
    }

}

