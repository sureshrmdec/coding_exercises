import java.util.function.*;

public class PerfStateless {
  @FunctionalInterface
  public interface Randomizer {
    long random();
  }

  public static class RandomizerImpl implements Randomizer {
    public long random() {
      return System.nanoTime();
    }
  }

  private static final int MAX = 10000000;

  private static long test(Randomizer Randomizer, int max) {
    long started = System.currentTimeMillis();

    for (int i=0; i<max; i++) {
      Randomizer.random();
    }
    return System.currentTimeMillis() - started;
  }


  private static long testDirect(int max) {
    return test(new RandomizerImpl(), max);
  }


  private static long testAnonymousClass(int max) {
    return test(new Randomizer() {
      public long random() {
        return System.nanoTime();
      }
    }, max);
  }

  private static long testLambda(int max) {
    return test(() -> System.nanoTime(), max);
  }

  public static void main(String[] args) {
    System.out.println("DIRECT " + testDirect(MAX));
    System.out.println("ANONYMOUS " + testAnonymousClass(MAX));
    System.out.println("LAMBDA " + testLambda(MAX));
  }
}
