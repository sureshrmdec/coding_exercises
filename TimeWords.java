/**
 * Write a program which prints the time in words for the input given in the
 * format mentioned above.
 *
 * Input Format
 *
 * There will be two lines of input:
 * , representing the hours
 * , representing the minutes
 *
 * Constraints
 *
 *
 * Output Format
 *
 * Display the time in words.
 *
 * Sample Input
 *
 * 5  
 * 47  
 * Sample Output
 *
 * thirteen minutes to six
 * */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TimeWords {        
   static String[] numbers = new String[] {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", 
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", 
            "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
            "twenty six", "twenty seven", "twenty eight", "twenty nine"};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int m = in.nextInt();
        if (m == 0) {
            System.out.println(numbers[h] + " o' clock");
        } else if (m == 15) {
            System.out.println("quarter past " + numbers[h]);
        } else if (m == 30) {
            System.out.println("half past " + numbers[h]);
        } else if (m == 45) {
            System.out.println("quarter to " + numbers[h+1]);
        } else if (m < 30) {
            System.out.println(numbers[m] + " minutes past " + numbers[h]);
        } else {
            System.out.println(numbers[60-m] + " minutes to " + numbers[h+1]);
        }
    }
}

