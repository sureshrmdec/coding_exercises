import java.io.*;
import java.util.*;
public class InorderString {
  // target string should be inorder compared to s2, e.g.
  // s1=abc, s2=123, target=abc123 or a1b2c3 or ab1c23
  // s1=ab, s2=bc, target=abbc or abcb
  static boolean isInorder(String s1, String s2, String target) {
    if (target.length() == 0) {
      if (s1.length() == 0 && s2.length() == 0) {
        return true;
      }
      return false;
    }
    if (target.length() != s1.length() + s2.length()) {
      return false;
    }
    char tch = target.charAt(0);
    if (s1.length() > 0 && s2.length() > 0) {
      char s1ch = s1.charAt(0);
      char s2ch = s2.charAt(0);
      if (tch == s1ch && tch == s2ch) {
        return isInorder(s1.substring(1), s2, target.substring(1)) || isInorder(s1, s2.substring(1), target.substring(1));
      } else if (tch == s1ch) {
        return isInorder(s1.substring(1), s2, target.substring(1));
      } else if (tch == s2ch) {
        return isInorder(s1, s2.substring(1), target.substring(1));
      } else {
        return false;
      }
    } else if (s1.length() > 0 && tch == s1.charAt(0)) {
      return isInorder(s1.substring(1), s2, target.substring(1));
    } else if (s2.length() > 0 && tch == s2.charAt(0)) {
      return isInorder(s1, s2.substring(1), target.substring(1));
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    System.out.println(isInorder("abc", "123", "ab1c23"));
    System.out.println(isInorder("ab", "bc", "abcb"));
    System.out.println(isInorder("ab", "bc", "acbb"));
  }
}

