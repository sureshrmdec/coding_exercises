/**
We define a modified Fibonacci sequence using the following definition:

Given terms  and  where , term  is computed using the following relation:
For example, if term  and , term , term , term , and so on.

Given three integers, , , and , compute and print term  of a modified Fibonacci sequence.

Note: The value of  may far exceed the range of a -bit integer. Many submission languages have libraries that can handle such large results but, for those that don't (e.g., C++), you will need to be more creative in your solution to compensate for the limitations of your chosen submission language.

Input Format

A single line of three space-separated integers describing the respective values of , , and .

Constraints

 may far exceed the range of a -bit integer.
Output Format

Print a single integer denoting the value of term  in the modified Fibonacci sequence where the first two terms are  and .

Sample Input

0 1 5
Sample Output

5
Explanation

The first two terms of the sequence are  and , which gives us a modified Fibonacci sequence of . Because , we print term , which is .
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FibDP {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        int n = in.nextInt();
				BigInteger[] arr = new BigInteger[n+1];
				arr[0] = BigInteger.valueOf(t1);
				arr[1] = BigInteger.valueOf(t2);
				for (int i=2; i<=n; i++) {
          BigInteger next = arr[i-1].multiply(arr[i-1]);
					arr[i] = arr[i-2].add(next);
				}
				System.out.println(arr[n-1]);
    }
}
 

