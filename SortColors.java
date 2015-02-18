import java.util.*;

// https://oj.leetcode.com/problems/sort-colors/ 
// Given an array with n objects colored red, white or blue, sort them so that
// objects of the same color are adjacent, with the colors in the order red,
// white and blue.
//
// Here, we will use the integers 0, 1, and 2 to represent the color red,
// white, and blue respectively.
public class SortColors {
  private static void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
  public static void _sortColors(int[] arr) {
    int numRed = 0;
    int numWhite = 0;
    int numBlue = 0;
    for (int n : arr) {
      if (n == 0) {
        numRed++;
      } else if (n == 1) {
        numWhite++;
      } else if (n == 2) {
        numBlue++;
      }
    }
    int ndxRed = 0;
    int ndxWhite = numRed;
    int ndxBlue = numWhite;
    for (int i=0; i<arr.length; i++) {
      if (arr[i] == 0) {
        swap(arr, i, ndxRed);
        ndxRed++;
      }
    }
    for (int i=0; i<arr.length; i++) {
      if (arr[i] == 1) {
        swap(arr, i, ndxWhite);
        ndxWhite++;
      }
    }
    // blue would be automatically sorted
  }


  public static void sortColors(int[] arr) {
    int redIndex = 0;
    int blueIndex = arr.length - 1;
    int i = 0;

    while (i <= blueIndex) {
      if (arr[i] == 0) {
        swap(arr, redIndex, i);
        i++;
        redIndex++;
      } else if (arr[i] == 1) {
        i++;
      } else {
        swap(arr, blueIndex, i);
        blueIndex--;
      }
    }
  }


  public static void main(String[] args) {
    int[] arr = new int[] {0, 1, 2, 1, 2, 1, 2, 0, 1, 2, 1, 0, 0, 1, 2};
    System.out.println("before " + Arrays.toString(arr));
    sortColors(arr);
    System.out.println("after  " + Arrays.toString(arr));
  }
}

