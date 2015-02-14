import java.util.*;

// https://oj.leetcode.com/problems/largest-number/
public class LargestNumber {
  public static String largetstNumber(int[] num) {
    StringBuilder sb = new StringBuilder();
    List<String> sorted = new ArrayList<>();
    for (int n : num) {
      sorted.add(String.valueOf(n));
    }
    Collections.sort(sorted);
    for (int i=sorted.size()-1; i>=0; i--) {
      sb.append(sorted.get(i));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(largetstNumber(new int[]{9, 3, 30, 34, 5}));
  }
}

