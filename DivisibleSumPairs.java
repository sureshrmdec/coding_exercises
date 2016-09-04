import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
You are given an array of  integers, , and a positive integer, . Find and print the number of pairs where  and  +  is evenly divisible by .

Input Format

The first line contains  space-separated integers,  and , respectively. 
The second line contains  space-separated integers describing the respective values of .

Constraints

Output Format

Print the number of  pairs where  and  +  is evenly divisible by .

Sample Input

6 3
1 3 2 6 1 2
Sample Output

 5
*/

public class DivisibleSumPairs {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int solutions = 0;
        for (int j=0; j<a.length; j++) {
            for (int i=0; i<j; i++) {
                if ((a[i]+a[j]) % k == 0) {
                    solutions++;
                }
            }
        }
        System.out.println(solutions);
    }
}
