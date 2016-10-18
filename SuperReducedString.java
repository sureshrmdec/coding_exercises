/**
 * https://www.hackerrank.com/challenges/reduced-string
 * Shil has a string, , consisting of  lowercase English letters. In one
 * operation, he can delete any pair of adjacent letters with same value. For
 * example, string "" would become either "" or "" after  operation.
 *
 * Shil wants to reduce  as much as possible. To do this, he will repeat the
 * above operation as many times as it can be performed. Help Shil out by
 * finding and printing 's non-reducible form!
 *
 * Note: If the final string is empty, print .
 *
 * Input Format
 *
 * A single string, .
 *
 * Constraints
 *
 * Output Format
 *
 * If the final string is empty, print ; otherwise, print the final
 * non-reducible string.
 *
 * Sample Input 0
 *
 * @abccddd
 * Sample Output 0
 *
 * abd
 * Sample Input 1
 *
 * b@b
 * Sample Output 1
 *
 * Empty String
 * Sample Input 2
 *
 * @
 * Sample Output 2
 *
 * Empty String
 * Explanation
 *
 * Sample Case 0: 
 * Shil can perform the following sequence of operations to get the final
 * string:
 *
 * Thus, we print .
 *
 * Sample Case 1: 
 * Shil can perform the following sequence of operations to get the final
 * string:
 *
 *
 * */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.next();
        while (line.length() > 0) {
            char[] chars = line.toCharArray();
            StringBuilder sb = new StringBuilder();
            sb.append(chars[0]);
            for (int i=1; i<chars.length; i++) {
                if (chars[i] == chars[i-1]) {
                    chars[i] = '_';
                    if (sb.length() > 0) {
                        sb.setLength(sb.length()-1);                        
                    }
                } else {
                    sb.append(chars[i]);
                }
            }
            if (line.length() == sb.length()) {
                break;
            }
            line = sb.toString();
        }
        System.out.println(line.length() > 0 ? line : "Empty String");
    }
}
