/** 
 * https://www.hackerrank.com/challenges/maxsubarray?h_r=next-challenge&h_v=zen
 * Given an array  of  elements, find the maximum possible sum of a
 *
 * Contiguous subarray
 * Non-contiguous (not necessarily contiguous) subarray.
 * Empty subarrays/subsequences should not be considered.
 *
 * Input Format
 *
 * First line of the input has an integer .  cases follow. 
 * Each test case begins with an integer . In the next line,  integers follow
 * representing the elements of array .
 *
 * Constraints
 *
 * The subarray and subsequences you consider should have at least one element.
 *
 * Output Format
 *
 * Two, space separated, integers denoting the maximum contiguous and
 * non-contiguous subarray. At least one integer should be selected and put
 * into the subarrays (this may be required in cases where all elements are
 * negative).
 *
 * Sample Input
 *
 * 2 
 * 4 
 * 1 2 3 4
 * 6
 * 2 -1 2 3 4 -5
 * Sample Output
 *
 * 10 10
 * 10 11
 * Explanation
 *
 * In the first case: 
 * The max sum for both contiguous and non-contiguous elements is the sum of
 * ALL the elements (as they are all positive).
 *
 * In the second case: 
 * [2 -1 2 3 4] --> This forms the contiguous sub-array with the maximum sum. 
 * For the max sum of a not-necessarily-contiguous group of elements, simply
 * add all the positive elements.
 * */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ContiguousSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int i=0; i<q; i++) {
            int n = in.nextInt(); 
            int[] a = new int[n];
            int sum = 0;
            int maxNum = Integer.MIN_VALUE;
            for (int j=0; j<n; j++) {
                a[j] = in.nextInt();
                if (a[j] > 0) {
                    sum += a[j];                    
                }
                maxNum = Math.max(a[j], maxNum);
            }

            int maxSofar = a[0];
            int maxEndingHere = 0;
            for (int j=0; j<n; j++) {
                maxEndingHere += a[j];
                //if (sum > 0 && maxEndingHere < 0) {
                if (maxEndingHere < 0) {
                    maxEndingHere = Math.min(maxNum, 0);
                }
                //System.out.println("maxSofar " + maxSofar + ", maxEndingHere " + maxEndingHere + ", maxNum " + maxNum);
                if (maxSofar < maxEndingHere) {
                    maxSofar = maxEndingHere;
                }
            }
            if (sum == 0) {
              sum = maxSofar;
            }
            System.out.println(maxSofar + " " + sum);
        }
    }
}
