public class Trace {
  public static void main(String[] args) {
    Runnable r = () -> {throw new RuntimeException("error");};
    r.run();
  }
}
