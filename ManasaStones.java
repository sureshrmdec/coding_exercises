/**
  Manasa is out on a hike with friends. She finds a trail of stones with numbers on them. She starts following the trail and notices that two consecutive stones have a difference of either  or . Legend has it that there is a treasure trove at the end of the trail and if Manasa can guess the value of the last stone, the treasure would be hers. Given that the number on the first stone was , find all the possible values for the number on the last stone.

Note: The numbers on the stones are in increasing order.

Input Format 
The first line contains an integer , i.e. the number of test cases.  test cases follow; each has 3 lines. The first line contains  (the number of stones). The second line contains , and the third line contains .

Output Format 
Space-separated list of numbers which are the possible values of the last stone in increasing order.

Constraints 


Sample Input

2
3 
1
2
4
10
100
Sample Output

2 3 4 
30 120 210 300 
Explanation

All possible series for the first test case are given below:

0,1,2
0,1,3
0,2,3
0,2,4
Hence the answer 2 3 4.

Series with different number of final steps for second test case are the following:

0, 10, 20, 30
0, 10, 20, 120
0, 10, 110, 120
0, 10, 110, 210
0, 100, 110, 120
0, 100, 110, 210
0, 100, 200, 210
0, 100, 200, 300
Hence the answer 30 120 210 300.

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ManasaStones {
  static boolean equals(int[] a, int[] b) {
    if (a.length != b.length) {
      return false;
    }
    for (int i=0; i<a.length; i++) {
      if (a[i] != b[i]) return false;
    }
    return true;
  }

  static int incr(int[] arr, int i, int a, int b) {
    //System.out.println("INCR INPUT i=" + i + " > " + Arrays.toString(arr));
    if (arr[i] == a) {
      arr[i] = b;
    } else if (i > 1) { // ignore index 0
      i--;
      if (arr[i] == a) {
        for (int j=i; j<arr.length; j++) {
          arr[j] = a;
        }
      }
      arr[i] = b;
    } else {
      i = arr.length - 1;
      arr[i] = b;
    }
    //System.out.println("\tINCR OUTPUT i=" + i + " > " + Arrays.toString(arr));
    return i;
  }

  static int sum(int[] a) {
    int sum = a[0];
    int last = a[0];
    for (int j=1; j<a.length; j++) {
      sum = last + a[j];
      last = sum;
    }
    return sum;
  }


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for(int a0 = 0; a0 < t; a0++){
      int n = in.nextInt(); // number of stones
      int a = in.nextInt();
      int b = in.nextInt();
      if (a == b) {
        int[] first = new int[n];
        for (int i=1; i<n; i++) {
          first[i] = a;
        }
        System.out.println(sum(first));          
      } else {
        int[] first = new int[n];
        int[] last = new int[n];
        for (int i=1; i<n; i++) {
          first[i] = a;
          last[i] = b;
        }
        int[] current = first;
        int i = n-1;
        Set<Integer> unique = new HashSet<>();
        while (!equals(current, last) && i > 0) {
          int sum = sum(current);
          unique.add(sum);
          current = current.clone();
          i = incr(current, i, a, b);
        }

        int sum = sum(current);
        unique.add(sum);
        List<Integer> list = new ArrayList<>(unique);
        Collections.sort(list);
        for (int j=0; j<n; j++) {
          if (j > 0) System.out.print(" ");
          System.out.print(list.get(j));
        }
        System.out.println();
      }
    }
  }
}

