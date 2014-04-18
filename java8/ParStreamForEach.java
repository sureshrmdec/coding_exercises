import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParStreamForEach {
  public static void main(String[] args) {
    List<String> symbols = Arrays.asList("AAPL", "MSFT", "ORCL", "NFLX", "TSLA");
    System.out.println("unordered");
    symbols.parallelStream().forEach(System.out::println);
    System.out.println("ordered");
    symbols.parallelStream().forEachOrdered(System.out::println);
  }
}
