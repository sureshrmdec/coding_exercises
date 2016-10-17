/**
https://www.hackerrank.com/challenges/sherlock-and-array
Watson gives Sherlock an array  of length . Then he asks him to determine if there exists an element in the array such that the sum of the elements on its left is equal to the sum of the elements on its right. If there are no elements to the left/right, then the sum is considered to be zero. 
Formally, find an , such that, 12i-1 i+1i+2N.

Input Format 
The first line contains , the number of test cases. For each test case, the first line contains , the number of elements in the array . The second line for each test case contains  space-separated integers, denoting the array .

Output Format 
For each test case print YES if there exists an element in the array, such that the sum of the elements on its left is equal to the sum of the elements on its right; otherwise print NO.

Constraints 
 
 
i  

Sample Input

2
3
1 2 3
4
1 2 3 3
Sample Output

NO
YES
Explanation 
For the first test case, no such index exists. 
For the second test case, , therefore index  satisfies the given conditions.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SherlockArray {
    static int sum(int[] a) {
        int sum = 0;
        for (int i=0; i<a.length; i++) {
            sum += a[i];
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int i=0; i<q; i++) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int j=0; j<n; j++) {
                a[j] = in.nextInt();
            }
            int leftSum = 0;
            int rightSum = sum(a);
            boolean found = false;
            for (int j=0; j<n; j++) {
                rightSum -= a[j];
                if (leftSum == rightSum) {
                    found = true;
                    break;
                }
                leftSum += a[j];
            }
            if (found) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
