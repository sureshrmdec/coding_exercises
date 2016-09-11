/**
Given an array with  elements, can you sort this array in ascending order using only one of the following operations?

Swap two elements.
Reverse one sub-segment.
Input Format 
The first line contains a single integer, , which indicates the size of the array. 
The next line contains  integers separated by spaces.

n  
d1 d2 ... dn  
Constraints 
 
  
All  are distinct.

Output Format 
1. If the array is already sorted, output yes on the first line. You do not need to output anything else.

If you can sort this array using one single operation (from the two permitted operations) then output yes on the first line and then:

a. If you can sort the array by swapping  and , output swap l r in the second line.  and  are the indices of the elements to be swapped, assuming that the array is indexed from  to .

b. Else if it is possible to sort the array by reversing the segment , output reverse l r in the second line. and  are the indices of the first and last elements of the subsequence to be reversed, assuming that the array is indexed from  to .

 represents the sub-sequence of the array, beginning at index  and ending at index , both inclusive.

If an array can be sorted by either swapping or reversing, stick to the swap-based method.

If you cannot sort the array in either of the above ways, output no in the first line.

Sample Input #1

2  
4 2  
Sample Output #1

yes  
swap 1 2
Sample Input #2

3
3 1 2
Sample Output #2

no
Sample Input #3

6
1 5 4 3 2 6
Sample Output #3

yes
reverse 2 5
Explanation 
For #1, you can both swap(1, 2) and reverse(1, 2), but if you can sort the array using swap, output swap only. 
For #2, it is impossible to sort by one single operation (among those permitted). 
For #3, you can reverse the sub-array d[2...5] = "5 4 3 2", then the array becomes sorted.



(1)input the array in vector
(2)run through the vector from index 1 to len-2 ( leaving the first and last elements)
(3)At each of these indices check whether it forms an inversion or a reverse inversion. 
Inversion is if curr > prev and curr > next. Similarly find out reverse inversions, 
curr < prev and curr < next. I call inversions as dips, and reverse inversions as ups.
(4)for the first and last elements you can check only the next and prev respectively as they are at the boundary.
(5)Once you have collected data of these inversions, if you analyze you will see 
that if reverse has to form a soln, you will have only one dip and one up.
(6)And if swapping can be soln then there will be 2 dips and 2 ups.
(7)If you get more than 2 dips and 2ups, it means it can't be solved.
(8)There are some edge cases which you need to take care of though.


If both reverse and swap can be a solution, then i give preference to swap.


*/



import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] A = new int[N];
        boolean debug = false;
        for (int i=0; i<N; i++) {
          A[i] = in.nextInt();
          if (debug) {
            //System.out.println("Input A[" + i + "] = " + A[i]);
          }
        }
        boolean reversing = false;
        int revFrom = -1;
        int revTo = -1;
        boolean swap = false;
        for (int i=1; i<N; i++) {
            // current > prev and current > next and prev < next
            if (i < N-1 && A[i] > A[i-1] && A[i] > A[i+1] && A[i-1] < A[i+1]) {
               if (!reversing) {
                  if (debug) {
                      System.out.println("Detected start A[" + i + "] " + A[i]);
                  }
                  revFrom = i+1;
                  reversing = true;
                  // 95929 108831 894947 125082 137123
                  if (i < N-2 && A[i+1] < A[i+2]) {
                      swap = true;
                  }

               } else {
                  System.out.println("no");
                  return;
               }
                // current < prev
            } else if (A[i] < A[i-1]) {
                if (reversing) {
                    revTo = i+1;
                } else if (i == N-1) {
                    reversing = true;
                    revFrom = i;
                    revTo = i+1;
                } else {
                  System.out.println("no");
                  return;
                }
            } else if (reversing) {
               if (debug) {
                   System.out.println("Unknown state A[" + i + "] " + A[i] + ", reversing " + reversing + ", revFrom " + revFrom + ", revTo " + revTo);
               }
            }
        }
        
        if (debug) {
            System.out.println("reversing " + reversing + ", revFrom " + revFrom + ", revTo " + revTo);
        }
        System.out.println("yes");
        if (reversing) {
            if (revTo-revFrom == 1 || swap) {
                System.out.println("swap " + revFrom + " " + revTo);
            } else {
                System.out.println("reverse " + revFrom + " " + revTo);
            }
        }
    }
}
