import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//http://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-items-o1-extra-space/
//Rearrange array in alternating positive & negative items with O(1) extra space
//Given an array of positive and negative numbers, arrange them in an alternate fashion such that every positive number is followed by negative and vice-versa maintaining the order of appearance.
//Number of positive and negative numbers need not be equal. If there are more positive numbers they appear at the end of the array. If there are more negative numbers, they too appear in the end of the array.
//
//Example:
//
//Input:  arr[] = {1, 2, 3, -4, -1, 4}
//Output: arr[] = {-4, 1, -1, 2, 3, 4}
//
//Input:  arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
//output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0} 
//This question has been asked at many places (See this and this)
//
//The above problem can be easily solved if O(n) extra space is allowed. It becomes interesting due to the limitations that O(1) extra space and order of appearances.
//The idea is to process array from left to right. While processing, find the first out of place element in the remaining unprocessed array. An element is out of place if it is negative and at odd index, or it is positive and at even index. Once we find an out of place element, we find the first element after it with opposite sign. We right rotate the subarray between these two elements (including these two).
//
public class ArrayArrange {
  static void rearrange(int[] arr) {
     if (arr == null || arr.length == 0) {
        return;
     }
     boolean lastSign = arr[0] >= 0;
     for (int i=1; i<arr.length; i++) {
        boolean curSign = arr[i] >= 0;
        if (lastSign == curSign) {
           System.out.print("sign for last number " + arr[i-1] + " does not match " + arr[i]);
           for (int j=i+1; j<arr.length; j++) {
               boolean forwardSign = arr[j] >= 0;
               if (curSign != forwardSign) {
                  System.out.print(", swapping " + arr[i] + " with " + arr[j] + ", j " + j);
                  int t = arr[i];
                  arr[i] = arr[j];
                  arr[j] = t;
                  break;
               }
           }
           System.out.println(" ==> " + Arrays.toString(arr));
           lastSign = arr[i] >= 0;
        } else {
         lastSign = curSign;
        }
     }
  }

  public static void main(String[] args) {
    //int arr[n] = {-5, 3, 4, 5, -6, -2, 8, 9, -1, -4};
    int arr[] = {-5, -3, -4, -5, -6, 2 , 8, 9, 1 , 4};
    //int arr[] = {5, 3, 4, 2, 1, -2 , -8, -9, -1 , -4};
    //int arr[] = {-5, 3, -4, -7, -1, -2 , -8, -9, 1 , -4};
    //int arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
    System.out.println(Arrays.toString(arr));
    rearrange(arr);
    System.out.println(Arrays.toString(arr));
  }
}

