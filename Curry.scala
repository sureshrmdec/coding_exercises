object Curry {
    def sum(f: Int => Int): (Int, Int) => Int = {
        def sumF(a: Int, b: Int): Int =
        if (a > b) 0 else f(a) + sumF(a+1, b)
        sumF
    }
    def product(f: Int => Int): (Int, Int) => Int = {
        def productF(a: Int, b: Int): Int =
        if (a > b) 1 else f(a) * productF(a+1, b)
        productF
    }
    def sum2(f: Int => Int)(a: Int, b: Int): Int =
        if (a > b) 0 else f(a) + sum2(f)(a+1, b)
    def product2(f: Int => Int)(a: Int, b: Int): Int =
        if (a > b) 1 else f(a) * product2(f)(a+1, b)

    def sumCubes = sum(x => x * x * x)
    // sum(cube)(1, 10)

    def sumCubes2(a: Int, b:Int) = sum2(x => x * x * x)(a, b)
    def fact(n:Int) = product2(x => x)(1, n)

    // map reduce
    def mapReduce(f: Int => Int, combine:(Int, Int) => Int, zero: Int)(a: Int, b:Int): Int =
        if (a > b) zero 
        else combine(f(a), mapReduce(f, combine, zero)(a+1, b))


    def product3(f: Int => Int)(a: Int, b:Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
}

