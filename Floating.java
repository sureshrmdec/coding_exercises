import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Floating {
  public static void verhulst(double R) {
    DecimalFormat f = new DecimalFormat("0.0000000000");
    int N = 100;
    double a, b, c, d, e;

    a = b = c = d = e = 0.5;

    for (int i = 1; i <= N; i++) { 
      a = (R+1)*a - R*(a*a);
      b = (R+1)*b - (R*b)*b;
      c = ((R+1) - R*c) * c;
      d = R*d + (1 - R*d)*d;
      e = e + R*(e - e*e);
      if (i % 10 != 0) continue;
      System.out.print(i + ":  ");
      System.out.println(f.format(a) + "   " +
          f.format(b) + "   " +
          f.format(c) + "   " +
          f.format(d) + "   " +
          f.format(e));
    }
  }
  public static void compoundInterest(int a, int r, int n) {
    // a amount in pennies
    // r annual interest rate in percent
    // n compounding periods per year

    // compute using double precision arithmetic
    double amount1 = a * Math.pow(1 + r / 100.0 / n, n);

    // round to nearest penny
    long amount2 = a;
    for (int i = 0; i < n; i++)
      amount2 = Math.round(amount2 * (1 + r / 100.0 / n));

    // round down to nearest penny
    long amount3 = a;
    for (int i = 0; i < n; i++)
      amount3 = (long) (amount3 * (1 + r / 100.0 / n));

    System.out.println("Exact: " + amount1);
    System.out.println("Round to nearest penny: " + amount2);
    System.out.println("Round down to nearest penny: " + amount3);
  }

  public static void financial() {
    // binary floating point arithmetic
    double a = 1.09 * 50;
    System.out.println("1.09 * 50 = " + new BigDecimal(a));
    System.out.println("rounds to   " + Math.round(a));

    double b = 1.14 * 75;
    System.out.println("1.14 * 75 = " + new BigDecimal(b));
    System.out.println("rounds to   " + Math.round(b));

    double c = 1.05 * 0.70;
    System.out.println("1.05 * 0.70 = " + new BigDecimal(c));
    System.out.println("rounds to   " + Math.round(c));

    // exact arithmetic
    BigDecimal a1 = new BigDecimal("1.09");
    BigDecimal a2 = new BigDecimal("50");
    BigDecimal a3 = a1.multiply(a2);
    System.out.println(a3 + " " + a3.setScale(0, RoundingMode.HALF_EVEN));

    BigDecimal b1 = new BigDecimal("1.14");
    BigDecimal b2 = new BigDecimal("75");
    BigDecimal b3 = b1.multiply(b2);
    System.out.println(b3 + " " + b3.setScale(0, RoundingMode.HALF_EVEN));

  }

  public static void harmonica(int N) {
    // using single precision, left-to-right
    float sum1 = 0.0f;
    for (int i = 1; i <= N; i++)
      sum1 = sum1 + 1.0f / i;
    System.out.println(sum1);

    // using single precision, right-to-left
    float sum2 = 0.0f;
    for (int i = N; i >= 1; i--)
      sum2 = sum2 + 1.0f / i;
    System.out.println(sum2);

    // using double precision, left-to-right
    double sum3 = 0.0;
    for (int i = 1; i <= N; i++)
      sum3 = sum3 + 1.0 / i;
    System.out.println(sum3);

    // using double precision, right-to-left
    double sum4 = 0.0;
    for (int i = N; i >= 1; i--)
      sum4 = sum4 + 1.0 / i;
    System.out.println(sum4);
  }
  public static void main(String[] args) { 
    //harmonica(Integer.parseInt(args[0]));
    //financial();
    //financial();
    //compoundInterest(100000, 5, 365);
    verhulst(Double.parseDouble(args[0])); 
  }
}
