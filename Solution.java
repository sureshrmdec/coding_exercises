import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int T = in.nextInt();
    for(int a0 = 0; a0 < T; a0++){
      int n = in.nextInt();
      int q[] = new int[n];
      for(int q_i=0; q_i < n; q_i++){
        q[q_i] = in.nextInt();
      }
      //
      int count = 0;
      Map<Integer, Integer> swaps = new HashMap<>();

      for (int i=0; i<n-1; i++) {
        if (q[i] > q[i+1] || q[i] != (i+1)) {
          int diff = Math.abs(q[i] - (i+1));
          //System.out.println("Comparing " + q[i] + " " + q[i+1]);
          Integer swp = swaps.get(q[i]);
          if (swp != null && swp >= 2) {
            //System.out.println("----swap " + q[i] + "---" + swp + ", i " + i);
            count = 0;
            break;
          }
          if (swp == null) {
            swaps.put(q[i], 1);
          } else {
            swaps.put(q[i], swp+1);
          }
          if (q[i] > q[i+1]) {
            int t = q[i];
            q[i] = q[i+1];
            q[i+1] = t;
            diff = Math.abs(q[i] - q[i+1]);
            System.out.println("----Comparing " + q[i] + " is more than 2 offset from " + q[i+1] + ", diff " + diff);
            count += Math.max(1, diff/2);
          } else if (diff >= 2) {
            System.out.println("Comparing " + q[i] + " is more than 2 offset from " + (i+1) + ", diff " + diff);
            count += Math.max(1, diff/2);
          } else {
            count++;
          }

        }
      }
      if (count == 0) {
        System.out.println("Too chaotic");
      } else {
        System.out.println(count);
      }
    }
  }
}
