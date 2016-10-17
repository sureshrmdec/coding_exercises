/**
https://www.hackerrank.com/challenges/richie-rich
Sandy likes palindromes. A palindrome is a word, phrase, number, or other sequence of characters which reads the same backward as it does forward. For example, madam is a palindrome.

On her  birthday, Sandy's uncle, Richie Rich, offered her an -digit check which she refused because the number was not a palindrome. Richie then challenged Sandy to make the number palindromic by changing no more than  digits. Sandy can only change  digit at a time, and cannot add digits to (or remove digits from) the number.

Given  and an -digit number, help Sandy determine the largest possible number she can make by changing digits.

Note: Treat the integers as numeric strings. Leading zeros are permitted and can't be ignored (So 0011 is not a palindrome, 0110 is a valid palindrome). A digit can be modified more than once.

Input Format

The first line contains two space-separated integers,  (the number of digits in the number) and  (the maximum number of digits that can be altered), respectively. 
The second line contains an -digit string of numbers that Sandy must attempt to make palindromic.

Constraints

Each character  in the number is an integer where .
Output Format

Print a single line with the largest number that can be made by changing no more than  digits; if this is not possible, print -1.

Sample Input 0

4 1
3943
Sample Output 0

3993
Sample Input 1

6 3
092282
Sample Output 1

992299
Sample Input 2

4 1
0011
Sample Output 2

-1
Explanation

Sample 0

There are two ways to make  a palindrome by changing exactly  digits:

, so we print .
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RichieRich {
   static int countNonPalindrome(char[] arr) {
      int count = 0;
      for (int i = 0; i < arr.length/2; i++) {
         if (arr[i] != arr[arr.length - 1 - i]) {
            count++;
         }   
      }   
      return count;
   } 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String number = in.next();
        BigInteger maxPalindrome = null;
        for (int m=0; m<(n/2+1); m++) {
            char[] arr = number.toCharArray();
            int[] state = new int[number.length()];
            
            int l = 0; // changes so far
            for (int j=0; j<(n/2+1) && l<k; j++) {
                if (arr[j] != arr[arr.length - 1 - j] && l < k) {
                    arr[j] = arr[arr.length-1-j] = (char) Math.max(arr[j], arr[arr.length-1-j]);
                    state[j]++;
                    l++;
                }
            }
            if (countNonPalindrome(arr) > 0) {
              continue;
            }

            for (int j=0; j<(n/2+1) && l<k; j++) {
                if (state[j] > 0 && arr[j] != '9') {
                  arr[j] = arr[arr.length-1-j] = '9';
                  l++;
                } else if (arr[j] != '9') {
                  if (k-l >= 2 && j != arr.length-1-j) {
                    arr[j] = arr[arr.length - 1 - j] = '9';
                    l += 2;
                  } else if (k-l >= 1 && j == arr.length-1-j) {
                    arr[j] = arr[arr.length - 1 - j] = '9';
                    l ++;
                  }
                }
            }
            //
            BigInteger newNum = new BigInteger(new String(arr));
            //System.out.println("FINAL l " + l + ", k " + k + ", input " + number + ", new-number " + newNum);
            if (l <= k && (maxPalindrome == null || maxPalindrome.compareTo(newNum) < 0)) {
                maxPalindrome = newNum;
                break;
            }
        }
        System.out.println(maxPalindrome == null ? "-1" : maxPalindrome);
    }
}

