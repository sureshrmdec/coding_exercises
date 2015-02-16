import java.util.*;

// https://oj.leetcode.com/problems/pascals-triangle-ii/ 
// Each number is the two numbers above it added together (except for the
// edges, which are all "1"). 
// Pascal's Triangle can show you how many ways heads and tails can combine. 
// What is the probability of getting exactly two heads with 4 coin tosses?  1+4+6+4+1
// formula "n choose k" 
// (n) = n! / 
// (k)       k!(n-k)!
//      (0)
//      (0)
//    (1)  (1)
//    (0)  (1)
//  (2)  (2)  (2)
//  (0)  (1)  (2)
public class PascalTriangle {
  public static List<Integer> getRow(int row) {
    List<Integer> result = new ArrayList<>();
    for (int n = 0; n < row; n++) {
      result.add(nCk(row, n));
    }
    return result;
  }
  //
  public static int nCk(int n, int k) {
    int numerator = fact(n);
    int denominator = fact(k) * fact(n - k);
    int result = (int) (numerator / denominator);
    return result;
  }
  //
  public static int fact(int num) {
    int result = 1;
    for (int i = 1; i <= num; i++) {
      result = result * i;
    }
    return result;
  }
  public static void main(String[] args) {
    for (int i=1; i<10; i++) {
      System.out.println(getRow(i));
    }
  }
}

