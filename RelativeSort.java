import java.util.*;
class RelativeSort {
  public static void relative_sort(Integer[] v) {
    int lastPositiveNdx = -1;
    for (int i=0; i<v.length; i++) {
      int n = v[i];
      if (n < 0) {
        if (lastPositiveNdx >= 0) {
          int last = lastPositiveNdx;
          for (int j=i; j>lastPositiveNdx; j--) {
            v[j] = v[j-1];
            last = j;
          }
          v[lastPositiveNdx] = n;
          lastPositiveNdx = last;
        }
      } else if (lastPositiveNdx < 0) {
        lastPositiveNdx = i;
      }
    }
    for (int i=0; i<v.length; i++) {
      System.out.println(v[i]);
    }
  }


  public static boolean equals(Integer[] a1, Integer[] a2) {
      if (a1.length != a2.length) {
          return false;
      }
      for (int i=0; i<a1.length; i++) {
          if (!a1[i].equals(a2[i])) {
              return false;
          }
      }
      return true;
  }

  public static void main(String[] args) {
      /*
    Integer[] v1 = new Integer[] {-5, 2, 1, -2, 3};
    relative_sort(v1);
    if (!equals(v1, new Integer[] {-5, -2, 2, 1, 3})) {
        System.out.println("bad result1");
    }
    */
    Integer[] v2 = new Integer[] {2,-5,6,-1,-4,3};
    relative_sort(v2);
    if (!equals(v2, new Integer[] {-5, -1, -4, 2, 6, 3 })) {
        System.out.println("bad result2");
    }
  }
}
