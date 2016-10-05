import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BigFib {

    static int FibonacciRecursive(int n) {
      if (n == 0) return 0;
      if (n == 1) return 1;
      return FibonacciRecursive(n - 1) + FibonacciRecursive(n - 2);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        if (n == 0) {
            System.out.println(0);
        } else if (n == 1) {
            System.out.println(1);
        } else if (n < 20) {
            long prevPrev = 0; 
            long prev = 1; 
            long result = 0;     
            for (int i = 2; i <= n; i++) {    
                result = prev + prevPrev;
                prevPrev = prev;
                prev = result;
            }    
            System.out.println(result);
        } else {
            BigInteger prevPrev = new BigInteger("0");
            BigInteger prev = new BigInteger("1");
            BigInteger result = new BigInteger("0");
            for (int i = 2; i <= n; i++) {    
                result = prev.add(prevPrev);
                prevPrev = prev;
                prev = result;
            }    
            System.out.println(result);
        }   
    }
}

