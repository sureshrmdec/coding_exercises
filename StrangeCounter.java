/**
https://www.hackerrank.com/challenges/strange-code
Bob has a strange counter. At the first second, , it displays the number . At each subsequent second, the number displayed by the counter decrements by .

The counter counts down in cycles. In the second after the counter counts down to , the number becomes  the initial number for that countdown cycle and then continues counting down from the new initial number in a new cycle. The diagram below shows the counter values for each time  in the first three cycles:


Given a time, , find and print the value displayed by the counter at time .

Input Format

A single integer denoting the value of .

Constraints

Subtask

 for  of the maximum score.
Output Format

Print the value displayed by the strange counter at the given time .

Sample Input

4
Sample Output

6
*/



import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long log(long x, long base) {
        return (long) (Math.log(x) / Math.log(base));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long t = in.nextLong();
        
        long cycleLength = (long) Math.pow(2, log((t + 2) / 3, 2)) * 3;
        long answer = cycleLength + cycleLength - t - 2;
        System.out.println(answer);
        /*
        long m = 3;
        long ti = 1;
        long tj = m * ti;
        long vi = 3;
        while (tj < t) {
            m *= 2;
            ti = tj + 1;
            tj = tj + m;
            vi *= 2;
        }
        for (;ti<t; ti++, vi--) {
        }
        System.out.println(vi);
        */
    }
}
