import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



/**
Given a set, , of  distinct integers, print the size of a maximal subset, , of  where the sum of any  numbers in  are not evenly divisible by .

Input Format

The first line contains  space-separated integers,  and , respectively. 
The second line contains  space-separated integers (we'll refer to the  value as ) describing the unique values of the set.

Constraints

All of the given numbers are distinct.
Output Format

Print the size of the largest possible subset ().

Sample Input

4 3
1 7 2 4
Sample Output

3


This initially appears difficult to solve in reasonable time complexity. After further thought, I think this can be done on O(n) with a few considerations. This is supposed to be an "easy" problem, so I'll provide some algorithm guidance here.
For any number K, the sum of 2 values (A & B) is evenly divisible by K if the sum of the remainders of A/K + B/K is K. (There is also a special case where both A & B are evenly divisible, giving a sum of 0.)
For any such remainder, there is 1 and only 1 other remainder value which will make a sum divisible by K.
Example: with K of 5, remainder pairs are 1+4 & 2+3. Given the numbers with a remainder of 1, they can't be paired with ANY of the numbers with remainder 4 (and vice versa). So, for the number of values in the resultant set, choose the larger of values with remainder 1 vs. values with remainder 4. Choose the larger set of remainder 2 vs remainder 3.
For the special case where remainder is 0, given the set of all values which are individually divisible by K, they can't be paired with any others. So, at most 1 value which is evenly divisible by K can be added to the result set.
For even values of K, the equal remainder is simular to the 0 case. For K = 6, pairs are 1+5, 2+4, 3+3. For values with remainder 3, at most one value can be added to the result set.
*/


public class NondisibleSubset {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int i=0; i < n; i++){
            a[i] = in.nextInt();
        }
        int[] counts = new int[k];
        for (int i=0; i<a.length; i++) {
          int j = a[i] % k;
          counts[j]++;
        }
        int count = Math.min(counts[0], 1);
        for (int i=1; i<=k/2; i++) {
          if (i != k - i) {
            count += Math.max(counts[i], counts[k-i]);
          }
        }
        if (k % 2 == 0) {
          count++;
        }
        System.out.println(count);
    }
}
