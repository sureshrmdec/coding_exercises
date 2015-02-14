import java.util.*;

// https://oj.leetcode.com/problems/compare-version-numbers/
public class VersionCompare {
  private static String normalize(String version) {
    String[] toks = version.split("\\.");
    StringBuilder sb = new StringBuilder();
    for (String tok : toks) {
      sb.append(String.format("%010d", Integer.parseInt(tok)));
    }
    return sb.toString();
  }
  public static int compareVersion(String version1, String version2) {
    String v1 = normalize(version1);
    String v2 = normalize(version2);
    System.out.println(v1 + "," + v2);
    return v1.compareTo(v2);
  }

  public static void main(String[] args) {
    System.out.println(compareVersion("0.1", "1.1"));
    System.out.println(compareVersion("1.1", "1.2"));
    System.out.println(compareVersion("1.1", "13.37"));
  }
}

