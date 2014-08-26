import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//http://www.geeksforgeeks.org/
public class ShortestPalindrome {
  static int shortPalin(String S) {
    return shortPalin(S, 0, S.length()-1);
  }
  static int shortPalin(String S, int l, int h) {
    if (l > h) return Integer.MAX_VALUE;
    if (l == h) return 0;
    boolean matched = S.charAt(l) == S.charAt(h);
    if (l == h - 1) return matched ? 0 : 1;
 
    return matched ? shortPalin(S, l + 1, h - 1):
                               (Math.min(shortPalin(S, l, h - 1),
                                   shortPalin(S, l + 1, h)) + 1);
  }
  static boolean isPalin(String S) {
    if (S == null) {
      return false;
    }
    int len = S.length();
    if (len == 0) {
      return false;
    }
    for (int i=0; i<len/2; i++) {
      char ch1 = S.charAt(i);
      char ch2 = S.charAt(S.length()-i-1);
      if (ch1 != ch2) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int res;
    String _S;
    _S = in.nextLine();

    res = shortPalin(_S);
    System.out.println(res);
  }
}

