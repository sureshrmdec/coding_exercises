import java.io.*;
import java.util.*;
public class RotateBinSearch {
  public static int search(int[] a, int x) {
    return search(a, 0, a.length-1, x);
  }
  public static int search(int[] a, int l, int u, int x) {
    while (l <= u) {
      int m = l + (u-l)/2;
      System.out.println("l " + l + ", m " + m + ", u " + u);
      if (x == a[m]) {
        return m;
      } else if (a[l] <= a[m]) {
        if (x > a[m]) {
          l = m+1;
        } else if (x >= a[l]) {
          u = m-1;
        } else {
          l = m + 1;
        }
      } else if (x < a[m]) u = m-1;
      else if (x <= a[u]) l = m+1;
      else u = m - 1;
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(search(new int[] {3, 4, 5, 6, 1, 2}, 2));
  }
}

