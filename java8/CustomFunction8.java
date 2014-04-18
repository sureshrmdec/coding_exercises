public class CustomFunction8 {
  @FunctionalInterface
  interface Command<T> {
    void execute(T obj);
  }

  private static <T> void invoke(Command<T> cmd, T arg) {
    cmd.execute(arg);
  }


  public static void main(String[] args) {
    Command<Integer> cmd = arg -> System.out.println(arg);
    invoke(cmd, 5);
  }
}
