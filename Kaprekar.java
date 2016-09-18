/**
A modified Kaprekar number is a positive whole number  with  digits, such that when we split its square into two pieces - a right hand piece  with  digits and a left hand piece  that contains the remaining  or  digits, the sum of the pieces is equal to the original number (i.e.  +  = ).

Note: r may have leading zeros.

Here's an explanation from Wikipedia about the ORIGINAL Kaprekar Number (spot the difference!): In mathematics, a Kaprekar number for a given base is a non-negative integer, the representation of whose square in that base can be split into two parts that add up to the original number again. For instance, 45 is a Kaprekar number, because 45² = 2025 and 20+25 = 45.

The Task 
You are given the two positive integers  and , where  is lower than . Write a program to determine how many Kaprekar numbers are there in the range between  and  (both inclusive) and display them all.

Input Format

There will be two lines of input: , lowest value , highest value

Constraints: 

Output Format

Output each Kaprekar number in the given range, space-separated on a single line. If no Kaprekar numbers exist in the given range, print INVALID RANGE.

Sample Input

1
100
Sample Output

1 9 45 55 99

Explanation

, , , , and  are the Kaprekar Numbers in the given range.



SAMPLE INPUT
1000
10000

SAMPLE OUTPUT
2223 2728 4950 5050 7272 7777 9999
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Kaprekar {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        int q = in.nextInt();
        List<Long> kaprekars = new ArrayList<>();
        for (long i=p; i<=q; i++) {
            String iStr = String.valueOf(i);
            long sq = (long) Math.pow(i, 2);
            String sqStr = String.valueOf(sq);
            for (int j=0; j<sqStr.length(); j++) {
                long l = j > 0 ? Long.parseLong(sqStr.substring(0, j)) : 0;
                String rStr = sqStr.substring(j);
                long r = Long.parseLong(rStr);
                if (r == 0 || rStr.length() != iStr.length()) {
                    continue;
                }
                //System.out.println("\tChecking " + i + ", l " + l + ", r " + r + ", square " + sq);
                if (l+r == i) {
                    //System.out.println("\t\tKaprekar  " + sq + ", l " + l + ", r " + rStr + " ==> " + i);
                    kaprekars.add(i);
                }
            }
        }
        if (kaprekars.size() == 0) {
            System.out.println("INVALID RANGE");
        } else {
            for (int i=0; i<kaprekars.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(kaprekars.get(i));
            }
            System.out.println();
        }
    }
}
