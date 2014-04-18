import java.util.HashMap;
import java.util.Map;

public class Fib {
  private final static Map<Integer,Long> cache = new HashMap<Integer, Long>() {{
    put(0,0L);
    put(1,1L);
  }};
  public static long fib(int x) {
    return cache.computeIfAbsent(x, n -> fib(n-1) + fib(n-2));
  }

  public static void main(String[] args) {
    System.out.println(fib(10));
  }
}
