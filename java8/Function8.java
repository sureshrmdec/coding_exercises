import java.util.function.*;


//Monoid
//  allows concatenation of data
//  concat(empty, x) == x
//  concat(x, empty) == x
//  concat(concat(x, y), z) == concat(x, concat(y, z))
//
//Monad
//  compose(f, compose(g, h) == compose(compose(f, g), h)
//  compose(f, result) == f
//  compose(result, f) == f

public class Function8 {
  public static void main(String[] args) {
    BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;
    System.out.println("sum " + adder.apply(4, 5));
    Function<Double,Double> square = x -> x * x;
    System.out.println("square " + square.apply(5.0));
  }
}
