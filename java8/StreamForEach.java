import java.util.stream.Stream;

public class StreamForEach {
  public static void main(String[] args) {
    Stream<String> symbols = Stream.of("AAPL", "MSFT", "ORCL", "NFLX", "TSLA");
    symbols.forEach(System.out::println);
  }
}
