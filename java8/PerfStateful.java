import java.util.function.*;
import java.util.concurrent.atomic.*;

public class PerfStateful {
  @FunctionalInterface
  public interface Incrementor {
    long increment();
  }

  public static class IncrementorImpl implements Incrementor {
    private AtomicLong counter = new AtomicLong(0);
    public long increment() {
      return counter.incrementAndGet();
    }
  }

  private static final int MAX = 10000000;

  private static long test(Incrementor incrementor, int max) {
    long started = System.currentTimeMillis();

    for (int i=0; i<max; i++) {
      incrementor.increment();
    }
    return System.currentTimeMillis() - started;
  }


  private static long testDirect(int max) {
    return test(new IncrementorImpl(), max);
  }


  private static long testAnonymousClass(int max) {
    return test(new Incrementor() {
      private AtomicLong counter = new AtomicLong(0);
      public long increment() {
        return counter.incrementAndGet();
      }
    }, max);
  }

  private static long testLambda(int max) {
    AtomicLong counter = new AtomicLong(0);
    return test(() -> counter.incrementAndGet(), max);
  }

  public static void main(String[] args) {
    System.out.println("DIRECT " + testDirect(MAX));
    System.out.println("ANONYMOUS " + testAnonymousClass(MAX));
    System.out.println("LAMBDA " + testLambda(MAX));
  }
}
