/*
https://www.hackerrank.com/challenges/palindrome-index?h_r=next-challenge&h_v=zen
Given a string, , of lowercase letters, determine the index of the character whose removal will make  a palindrome. If  is already a palindrome or no such character exists, then print . There will always be a valid solution, and any correct answer is acceptable. For example, if  "bcbc", we can either remove 'b' at index  or 'c' at index .

Input Format

The first line contains an integer, , denoting the number of test cases. 
Each line  of the  subsequent lines (where ) describes a test case in the form of a single string, .

Constraints

All characters are lowercase English letters.
Output Format

Print an integer denoting the zero-indexed position of the character that makes  not a palindrome; if  is already a palindrome or no such character exists, print .

Sample Input

3
aaab
baa
aaa
Sample Output

3
0
-1
Explanation

Test Case 1: "aaab" 
Removing 'b' at index  results in a palindrome, so we print  on a new line.

Test Case 2: "baa" 
Removing 'b' at index  results in a palindrome, so we print  on a new line.

Test Case 3: "aaa" 
This string is already a palindrome, so we print ; however, , , and  are also all acceptable answers, as the string will still be a palindrome if any one of the characters at those indices are removed.

Note: The custom checker logic for this challenge is available here.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PalindromeIndex {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            String s = in.next();
            int len = s.length();
            int len2 = len/2;
            int ndx = -1;
            for (int i=0; i<len2; i++) {
                char a = s.charAt(i);
                char b = s.charAt(len-i-1);
                if (a != b) {
                    if (i+1 < len && b == s.charAt(i+1)) {
                        ndx = i;
                        //System.out.println("a) i=" + i + ", a=" + a + ", b=" + b + ", last " + (len-i-1));
                        break;
                    } else if (len-i-2 > 0 && a == s.charAt(len-i-2)) {
                        ndx = len-i-1;
                        //System.out.println("b) Ndx=" + ndx + ", i=" + i + ", a=" + a + ", b=" + b); 
                        break;
                    } else {
                        ndx = i;
                        break;
                    }
                }
            }
            System.out.println(ndx);
        } 
    }
}

