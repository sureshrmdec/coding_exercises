/**
Consider an array of  integers, . The distance between two indices,  and , is denoted by .

Given , find the minimum  such that  and . In other words, find the minimum distance between any pair of equal elements in the array. If no such value exists, print .

Note:  denotes the absolute value of .

Input Format

The first line contains an integer, , denoting the size of array . 
The second line contains  space-separated integers describing the respective elements in array .

Constraints

Output Format

Print a single integer denoting the minimum  in ; if no such value exists, print .

Sample Input

6
7 1 3 4 1 7
Sample Output

3
Explanation 
Here, we have two options:

 and  are both , so .
 and  are both , so .
The answer is .
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MinDistance {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int A[] = new int[n];
        for(int A_i=0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
        }
        int min = n+1;
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i=0; i<n; i++) {
            Integer j = dist.get(A[i]);
            if (j == null) {
                j = i;
            }
            if (i-j > 0) {
                min = Math.min(i-j, min);
            }
            dist.put(A[i], i);
            if (min == 1) break;
        }
        if (min == n+1) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}

