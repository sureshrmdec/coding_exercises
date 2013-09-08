/*
Majority number

Given an array of integer numbers your task is to print to the standard output (stdout) the majority number.

One number is considered majority if it occurs more than N / 2 times in an array of size N.

Note: If no number is majority then print "None"

Expected complexity: O(N) time, O(1) memory

Example input:
1 1 2 3 4 1 6 1 7 1 1

Example output:
1

Example input:
1 2 2 3

Example output:
None
*/


import java.util.*;

class Majority {
    public static void majority(Integer[] v) {
      Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
      int max = -1;
      for (int i=0; i<v.length; i++) {
        Integer count = counts.get(v[i]);
        if (count == null) {
          count = 1;
        } else {
          count++;
        }
        counts.put(v[i], count);

        if (count > v.length/2) {
          max = v[i];
        }
      }
      if (max != -1) {
        System.out.println(max);
      }  else {
          System.out.println("None");
      }
    }

  public static void main(String[] args) {
    //majority(new Integer[] { 1, 1, 2, 3, 4, 1, 6, 1, 7, 1, 1});
    // majority(new Integer[] { 1, 2, 2, 3});
    majority(new Integer[] { 870,18,18,18,117,385,18,18 });
  }
}

