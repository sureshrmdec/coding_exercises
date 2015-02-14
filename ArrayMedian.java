// There are two sorted arrays A and B of size m and n respectively. Find the median 
// of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
import java.util.*;


public class ArrayMedian {
   public static double findMedianSortedArrays(int A[], int B[]) {
      int half = (A.length+B.length-1) / 2;
      int[] middleNdx = (A.length+B.length) % 2  == 0 ? new int[] {half, half+1} : new int[] {half, half};
      System.out.println("first " + Arrays.toString(A) + "second " + Arrays.toString(B) + ", middle index " + middleNdx[0] + ", " + middleNdx[1]);
      int[] middle = new int[2];
      if (middleNdx[0] < A.length) {
        middle[0] = A[middleNdx[0]];
      } else {
        middle[0] = B[middleNdx[0]-A.length];
      }
      if (middleNdx[1] < A.length) {
        middle[1] = A[middleNdx[1]];
      } else {
        middle[1] = B[middleNdx[1]-A.length];
      }
      return (middle[0] + middle[1]) / 2.0;
   }


   public static double _findMedianSortedArrays(int A[], int B[]) {
      int half = (A.length+B.length-1) / 2;
      int[] middleNdx = (A.length+B.length) % 2  == 0 ? new int[] {half, half+1} : new int[] {half, half};
      System.out.println("first " + Arrays.toString(A) + "second " + Arrays.toString(B) + ", middle index " + middleNdx[0] + ", " + middleNdx[1]);
      int[] middle = new int[2];
      for (int i=0, j=0, k=0; i<A.length || j<B.length;k++) {
         int m = i<A.length ? A[i] : Integer.MIN_VALUE;
         int n = j<B.length ? B[j] : Integer.MIN_VALUE;
         int sel = Integer.MIN_VALUE;
         if (m != Integer.MIN_VALUE && n != Integer.MIN_VALUE) {
            if (m < n) {
               sel = m;
               i++;
            } else if (m > n) {
               sel = n;
               j++;
            } else {
               sel = m;
               i++;
               j++;
            }
         } else if (m != Integer.MIN_VALUE) {
            sel = m;
            i++;
         } else {
            sel = n;
            j++;
         }
         if (k == middleNdx[0]) middle[0] = sel;
         if (k == middleNdx[1]) middle[1] = sel;
      }
      return (middle[0] + middle[1]) / 2.0;
   }

   public static void main(String[] args) {
      double m1 = findMedianSortedArrays(new int[] {1, 2, 3, 4, 5, 6}, new int[] {11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
      System.out.println(m1);
      double m2 = findMedianSortedArrays(new int[] {11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, new int[] {111, 112, 113, 114, 115, 116, 117});
      System.out.println(m2);
      double m3 = findMedianSortedArrays(new int[] {11, 12, 13, 14, 15, 16}, new int[0]);
      System.out.println(m3);
   }
}
