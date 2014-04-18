public class Run8 {
  public static void main(String[] args) {
    Runnable r = () -> System.out.println("hello there");
    r.run();
  }
}
