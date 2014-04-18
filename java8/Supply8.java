import java.util.function.*;

public class Supply8 {
  public static void main(String[] args) {
    Supplier<Double> random1 = Math::random;
    System.out.println(random1.get());
    //
    DoubleSupplier random2 = Math::random;
    System.out.println(random2.getAsDouble());
  }
}
