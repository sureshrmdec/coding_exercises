import java.util.*;

// https://oj.leetcode.com/problems/factorial-trailing-zeroes/ 
// http://www.purplemath.com/modules/factzero.htm
public class FactorialTrailingZeroes {
  public static int trailingZeroes(int n) {
    int count = 0;
    int pow = 1;
    while (true) {
      int div = (int) Math.pow(5, pow);
      if (div > n) {
        break;
      }
      count += (n / div);
      pow++;
    }
    return count;
  }

  public static void main(String[] args) {
    assert 24 == trailingZeroes(101);
    assert 249 == trailingZeroes(1000);
    assert 1151 == trailingZeroes(4617);
  }
}

