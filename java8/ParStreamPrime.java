import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParStreamPrime {
  private static boolean isPrime(int n) {
    if (n%2==0) return false;
    for(int i=3;i*i<=n;i+=2) {
      if(n%i==0) return false;
    }
    return true;
  }
  private static long serialTest(int max) {
    long started = System.currentTimeMillis();
    IntStream.rangeClosed(1, max).forEach(num -> isPrime(num));
    return System.currentTimeMillis() - started;
  }
  private static long parallelTest(int max) {
    long started = System.currentTimeMillis();
    IntStream.rangeClosed(1, max).parallel().forEach(num -> isPrime(num));
    return System.currentTimeMillis() - started;
  }
  //
  public static void main(String[] args) {
    int max = 1000000;
    System.out.println("Serial " + serialTest(max));
    System.out.println("Parallel " + parallelTest(max));
  }
}
