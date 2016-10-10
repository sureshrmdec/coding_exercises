/*
https://www.hackerrank.com/challenges/funny-string
Suppose you have a String, , of length  that is indexed from  to . You also have some String, , that is the reverse of String .  is funny if the condition  is true for every character from  to .

Note: For some String ,  denotes the ASCII value of the  -indexed character in . The absolute value of an integer, , is written as .

Input Format

The first line contains an integer,  (the number of test cases). 
Each line  of the  subsequent lines contain a string, .

Constraints

Output Format

For each String  (where ), print whether it is  or  on a new line.

Sample Input

2
acxz
bcxz
Sample Output

Funny
Not Funny
Explanation

Test Case 0:  
 
 
 
As each comparison is equal, we print .

Test Case 1:  
, but  
At this point, we stop evaluating  (as ) and print 
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FunnyString {
    static String getFunny(String s) {
        for (int i=0; i<s.length()-1; i++) {
            int j = s.length() - i - 1;
            int a = Math.abs(s.charAt(i) - s.charAt(i+1));
            int b = Math.abs(s.charAt(j) - s.charAt(j-1));
            if (a != b) {
                return "Not Funny";
            }
        }
        return "Funny";
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int i=0; i<q; i++) {
            String s = in.next();
            System.out.println(getFunny(s));
        }
    }
}
