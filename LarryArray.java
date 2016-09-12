/** 
 *
 * See
 * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
 *
 Larry has a permutation of  numbers, , whose unique elements range from  to  (i.e.: ). He wants  to be sorted, so he delegates the task of doing so to his robot. The robot can perform the following operation as many times as it wants:

 Choose any  consecutive indices and rotate their elements in such a way that  rotates to , which rotates to , which rotates back to .
 For example: if  and the robot rotates ,  becomes .

 On a new line for each test case, print  if the robot can fully sort ; otherwise, print .

 Input Format

 The first line contains an integer, , the number of test cases. 
 The  subsequent lines each describe a test case over  lines:

 An integer, , denoting the size of .
 space-separated integers describing , where the  value describes element .
 Constraints

 Output Format

 On a new line for each test case, print  if the robot can fully sort ; otherwise, print .

 Sample Input

 3
 3
 3 1 2
 4
 1 3 4 2
 5
 1 2 3 5 4
 Sample Output

 YES
 YES
 NO
 Explanation

 In the explanation below, the subscript of  denotes the number of operations performed.

 Test Case 0: 

 is now sorted, so we print  on a new line.

 Test Case 1: 
 . 
 . 
 is now sorted, so we print  on a new line.

 Test Case 2:
 No sequence of rotations will result in a sorted . Thus, we print  on a new line.


 3
 4
 1 3 4 2
 5
 1 2 3 5 4
 4
 4 1 3 2

 YES
 NO
 YES
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LarryArray {
  static boolean isSorted(int[] arr) {
    for (int i=1; i<arr.length; i++) {
      if (arr[i-1] > arr[i]) return false;
    }
    return true;
  }

  // rotate until first element is smallest
  static boolean rotate(int[] arr, int n) {
    if (arr[n] <= arr[n+1] && arr[n] <= arr[n+2]) return false;

    int t = arr[n];
    arr[n] = arr[n+1];
    arr[n+1] = arr[n+2];
    arr[n+2] = t;
    return true;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    boolean debug = false;
    for(int a0 = 0; a0 < t; a0++){
      int n = in.nextInt();                                                                                                   int[] arr = new int[n];
      for (int j=0; j<n; j++) {
        arr[j] = in.nextInt();
      }
      Boolean sorted = null;
      for (int i=0; i<=n-3 && sorted == null; i++) {
        if (debug) System.out.println("Checking i " + i + " ==> " + Arrays.toString(arr));
        for (int j=0; j<=n-3 && sorted == null; j++) {
          while (rotate(arr, j)) {
          }
          if (isSorted(arr)) {
            if (debug) System.out.println("\tSorted " + Arrays.toString(arr));
            sorted = true;
            break;
          }
        }

        //
        if (isSorted(arr)) {
          if (debug) System.out.println("\tSorted " + Arrays.toString(arr));
          sorted = true;
          break;
        }
      }


      //
      if (sorted == null) {
        sorted = isSorted(arr);
      }
      if (sorted) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }
}
