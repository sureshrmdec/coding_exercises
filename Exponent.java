public class Exponent {
  // Let’s say we wanted to find xn and for the sake of this problem we want
  // the last four digits of that number. Note that 2674 mod 10000 = (2673
  // mod 10000) * 267 mod 10000. This is important because it means we can
  // take the last 4 digits in each step instead of having to compute the giant
  // exponent and then taking the last 4 digits. We can do this problem very
  // easily by using a simple loop, but we can do better by using recursion. By
  // definition of exponents, xa * xa = x2a
  // . Using this we can see that if n is
  // divisible by 2, then xn = xn/2 * xn/2
  // .
  // For example 54 = 52 * 52
  // But let’s take a look at xn/2
  // . If n is even we can do the exact same thing!
  // x
  // n/2 = xn/4 * xn/4
  // . We are solving a reduced version of the same problem
  // so we have a recurrence relation. The base case is simple: x1 = x.
  // But what if n is odd and not 1? Then we have xn = xn/2 * xn/2 * x.
  // Thus our recursive function has 3 different cases: the base case, even case
  // and odd case.
  public static int exponent(int b, int n) {
    if(n == 1)return b;
    if(n % 2 == 0){
      int x = exponent(b, n / 2);
      return (x * x) % 10000;
    }
    else{
      int x = exponent(b, n / 2);
      return (x * x * b) % 10000;
    }
  }
  public static void main(String[] args) {
    System.out.println(exponent(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
  }
}
