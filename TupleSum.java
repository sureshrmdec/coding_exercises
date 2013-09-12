import java.util.*;

/*
 *
 *Given an array A your tastk is to print in ascending order 4 distinct 0-based indexes of elements in the array that add up to a sum S, if such indexes exist.
 * If there are multiple solutions please print the lowest one according to the lexicographical order

Example input:
A = 3 2 1 4 5 7 6 9 7 8
S = 30

Example output:
5 6 7 9

*/

class TupleSum {
  public static void tuple_sum(Integer[] a, Integer s) {
    for (int i=0; i<a.length; i++) {
      for (int j=0; j<a.length; j++) {
        for (int k=0; k<a.length; k++) {
          for (int l=0; l<a.length; l++) {
            if (i != j && i != k && i != l && j != k && j != l && k != l) {
              if (a[i] + a[j] + a[k] + a[l] == s) {
                System.out.println(i + "\n" + j + "\n" + k + "\n" + l);
                return;
              }
            }
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    tuple_sum(new Integer[] {3,2,1,4,5,7,6,9,7,8}, 30);
    //5 6 7 9
    //tuple_sum(new Integer[] {3,2,1,4,5,7,6,9,8}, 30);
    //5 6 7 8
  }
}
