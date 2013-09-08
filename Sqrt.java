// uses Newton's method
public class Sqrt {
    public static void compute_sqrt(Integer n) {
      System.out.println((int) sqrt(1, n));
    }

    private static double sqrt(double guess, double x) {
        if (isGoodEnough(guess, x)) return guess; 
        else return sqrt(improve(guess, x), x); 
    }
    private static boolean isGoodEnough(double guess, double x) {
      System.out.println("Comparing " + guess + " with " + x);
      return Math.abs(guess * guess - x) / x < 0.0001; // note we divide by x so that it works for both large and small numbers
    }
    private static double improve(double guess, double x) {
      return (guess + x / guess) / 2;
    }
    public static void main(String[] args) {
      compute_sqrt(17);
    }
}

