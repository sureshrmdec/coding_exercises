import java.util.function.*;

public class SupplyLog {
  private static boolean debugEnabled; 
  public static void debug(Supplier<String> msg) {
    if (debugEnabled) {
      System.out.println(msg.get());
    }
  }
  public static void main(String[] args) {
    debug(() -> "this will not be printed");
    debugEnabled = true;
    debug(() -> "this will be printed");
  }
}
