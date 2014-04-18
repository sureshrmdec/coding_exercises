import java.util.function.*;

public class Function8 {
  public static void main(String[] args) {
    BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;
    System.out.println("sum " + adder.apply(4, 5));
    Function<Double,Double> square = x -> x * x;
    System.out.println("square " + square.apply(5.0));
  }
}
