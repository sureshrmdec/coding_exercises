object Mat {
    // uses Euclid's method
    def gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b) 
    // this is not tail recursive
    def factorial(n: Int): Int =
        if (n == 0) 1 else n * factorial(n-1)
    // this is tail recursive
    def factorialt(n: Int): Int = {
        def loop(acc: Int, n: Int): Int =
            if (n == 0) acc else loop(acc*n, n-1)
        loop(1, n)
    }

    def sum(f: Int => Int, a: Int, b: Int): Int = {
        println("a " + a + ", b "  + b)
        if (a > b) 0 else f(a) + sum(f, a+1, b)
    }

    def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)
    def sumCubest(a: Int, b: Int) = sumt(x => x * x * x, a, b)
    def sumt(f: Int => Int, a: Int, b: Int): Int = {
        def loop(a: Int, acc: Int): Int = {
            println("a " + a + ", acc "  + acc)
            if (a > b) acc else loop(a+1, acc+f(a)) 
        }
        loop(a, 0)
    }
}

