/**
We define  to be a permutation of the first  natural numbers in the range . Let  denote the position of  in permutation  (please use -based indexing).

 is considered to be an absolute permutation if  holds true for every .

Given  and , print the lexicographically smallest absolute permutation, ; if no absolute permutation exists, print -1.

Input Format

The first line of input contains a single integer, , denoting the number of test cases. 
Each of the  subsequent lines contains  space-separated integers describing the respective  and  values for a test case.

Constraints

Output Format

On a new line for each test case, print the lexicographically smallest absolute permutation; if no absolute permutation exists, print -1.

Sample Input

3
2 1
3 0
3 2
Sample Output

2 1
1 2 3
-1
Explanation

Test Case 0:

perm.png

Test Case 1:

perm(1).png

Test Case 2: 
No absolute permutation exists, so we print -1 on a new line.
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static boolean isAbsolute(int[] original, int[] working, int k) {
      for (int i=0; i<original.length; i++) {
          if (Math.abs(original[i] - working[i]) != k) {
              return false;
          }
      }
      return true;
  }
  static void print(int[] array) {
      for (int i=0; i<array.length; i++) {
          if (i > 0) System.out.print(" ");
          System.out.print(array[i]);
      }
      System.out.println();
  }
  static boolean nextPermutation(int[] array) {                                                                                          
    // Find longest non-increasing suffix
    int i = array.length - 1;
    while (i > 0 && array[i - 1] >= array[i])
      i--;
    // Now i is the head index of the suffix

    // Are we at the last permutation already?
    if (i <= 0)
      return false;

    // Let array[i - 1] be the pivot
    // Find rightmost element that exceeds the pivot
    int j = array.length - 1;
    while (array[j] <= array[i - 1])
      j--;
    // Now the value array[j] will become the new pivot
    // Assertion: j >= i

    // Swap the pivot with j
    int temp = array[i - 1];
    array[i - 1] = array[j];
    array[j] = temp;

    // Reverse the suffix
    j = array.length - 1;
    while (i < j) {
      temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      i++;
      j--;
    }

    // Successfully computed the next permutation
    return true;
  }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int k = in.nextInt();
            
            int[] arr = new int[n];
            for (int i=0; i<n; i++) {
                arr[i] = i+1;
            }
            
            if (k ==0) {
                print(arr);
            } else if ((k<=n/2)&&(n%k==0)&&(n/k%2 ==0)) {
                for(int i=0;i<n;i++) {
                   if((i/k)%2 == 0){
                        arr[i] = i + k+1;
                   } else{
                        arr[i] = i - k+1;
                   }
               }
                print(arr);
            } else {
                System.out.println("-1");
            }
    
    /*
            boolean matched = false;
            while (k == 0 || (n % k == 0 && k > 0)) {
                if (k == 0 || isAbsolute(original, working, k)) {
                    matched = true;
                    print(working);
                    break;
                }
                if (!nextPermutation(working)) {
                    break;
                }
            }
            if (!matched) {
                System.out.println("-1");
            }
        */
        }
    }
}


