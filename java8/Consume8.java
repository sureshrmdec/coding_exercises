import java.util.function.*;

public class Consume8 {
  public static void main(String[] args) {
    Consumer<String> consumer = s -> System.out.println(s);
    consumer.accept("hello there");
    consumer.andThen(consumer).accept("this will be printed twice");
  }
}
