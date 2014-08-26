import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// https://www.hackerrank.com/
public class ZeroOneMultiple {
  static String zeroOne(int n) {
      StringBuilder sb = new StringBuilder();
      while (n > 0 ) {
          int bit    = n % 2 ;
          n = n / 2;
          sb.insert(0, "" + bit);
      }
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String S = in.nextLine();
    String res = zeroOne(Integer.parseInt(S));
    System.out.println(res);
  }
}

