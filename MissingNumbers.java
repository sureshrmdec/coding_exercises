/**
 * https://www.hackerrank.com/challenges/missing-numbers
 * Numeros, the Artist, had two lists  and , such that  was a permutation of
 * . Numeros was very proud of these lists. Unfortunately, while transporting
 * them from one exhibition to another, some numbers were left out of . Can you
 * find the missing numbers?
 *
 * Notes
 *
 * If a number occurs multiple times in the lists, you must ensure that the
 * frequency of that number in both lists is the same. If that is not the case,
 * then it is also a missing number.
 * You have to print all the missing numbers in ascending order.
 * Print each missing number once, even if it is missing multiple times.
 * The difference between maximum and minimum number in  is less than or equal
 * to .
 * Input Format 
 * There will be four lines of input:
 *
 *  - the size of the first list 
 *  This is followed by  space-separated integers that make up the first list. 
 *   - the size of the second list 
 *   This is followed by  space-separated integers that make up the second
 *   list.
 *
 *   Constraints
 *
 *   Output Format 
 *   Output the missing numbers in ascending order.
 *
 *   Sample Input
 *
 *   10
 *   203 204 205 206 207 208 203 204 205 206
 *   13
 *   203 204 204 205 206 207 205 208 203 206 205 206 204
 *   Sample Output
 *
 *   204 205 206
 *   Explanation
 *
 *    is present in both arrays. Its frequency in  is , while its frequency in
 *    is . Similarly,  and  occur twice in , but thrice in . So, these three
 *    numbers are our output. The rest of the numbers have thesame frequency in
 *    both lists.
 *    */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MissingNumbers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar1 = new int[n];
        for(int i=0;i<n;i++){
            ar1[i]=in.nextInt(); 
        }
        int m = in.nextInt();
        int[] ar2 = new int[m];
        for(int i=0;i<m;i++){
            ar2[i]=in.nextInt(); 
        }
        Map<Integer, Integer> counts1 = new LinkedHashMap<>();
        for (int x : ar1) {
            Integer count = counts1.get(x);
            if (count == null) {
                counts1.put(x, 1);
            } else {
                counts1.put(x, count+1);
            }
        }
        Map<Integer, Integer> counts2 = new LinkedHashMap<>();
        
        for (int x : ar2) {
            Integer count = counts2.get(x);
            if (count == null) {
                counts2.put(x, 1);
            } else {
                counts2.put(x, count+1);
            }
        }
        List<Integer> keys = new ArrayList<>(counts1.keySet());
        for (Integer x : counts2.keySet()) {
            if (!keys.contains(x)) {
                keys.add(x);
            }
        }
        Collections.sort(keys);
        for (Integer x : keys) {
            Integer a = counts1.get(x);
            Integer b = counts2.get(x);
            if (a == null || b == null || (!a.equals(b))) {
                System.out.print(x + " ");
            }
        }
        System.out.println();
    }
}
