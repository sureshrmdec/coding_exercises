/**
You are given an integer . Print the factorial of this number.

Input 
Input consists of a single integer , where .

Output 
Print the factorial of .

Example 
For an input of , you would print .

Note: Factorials of  can't be stored even in a  long long variable. Big integers must be used for such calculations. Languages like Java, Python, Ruby etc. can handle big integers, but we need to write additional code in C/C++ to handle huge values.

We recommend solving this challenge using BigIntegers.
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ExtraLongFactorials {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        if (n <= 1) {
            System.out.println(1);
        } else if (n < 20) {
            long sum = 1;
            while (n > 1) {
                sum *= n; 
                n--;
            }
            System.out.println(sum);
        } else {
            BigInteger sum = new BigInteger("1");
            while (n > 1) {
                sum = sum.multiply(new BigInteger(String.valueOf(n)));
                n--;
            }
            System.out.println(sum);
        }   
    }
}

