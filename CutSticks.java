import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
You are given  sticks, where the length of each stick is a positive integer. A cut operation is performed on the sticks such that all of them are reduced by the length of the smallest stick.

Suppose we have six sticks of the following lengths:

5 4 4 2 2 8
Then, in one cut operation we make a cut of length 2 from each of the six sticks. For the next cut operation four sticks are left (of non-zero length), whose lengths are the following: 

3 2 2 6
The above step is repeated until no sticks are left.

Given the length of  sticks, print the number of sticks that are left before each subsequent cut operations.

Note: For each cut operation, you have to recalcuate the length of smallest sticks (excluding zero-length sticks).

Input Format 
The first line contains a single integer . 
The next line contains  integers: a0, a1,...aN-1 separated by space, where  represents the length of the  stick.

Output Format 
For each operation, print the number of sticks that are cut, on separate lines.

Constraints

Sample Input 0

6
5 4 4 2 2 8
Sample Output 0

6
4
2
1
Sample Input 1

8
1 2 3 4 3 3 2 1
Sample Output 1

8
6
4
1
*/
public class CutSticks {
    static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] > 0) {
                min = Math.min(min, arr[i]);
            }
        }
        return min;
    }
    static int aboveZero(int[] arr) {
        int sum = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] > 0) {
                sum++;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        while (true) {
            int sum = aboveZero(arr);
            if (sum <= 0) {
                break;
            }
            System.out.println(sum);
            int min = min(arr);
            for (int i=0; i<arr.length; i++) {
                arr[i] -= min;
            }
        }
    }
}
