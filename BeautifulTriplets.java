/**
Erica wrote an increasing sequence of  numbers () in her notebook. She considers a triplet  to be beautiful if:

Given the sequence and the value of , can you help Erica count the number of beautiful triplets in the sequence?

Input Format

The first line contains  space-separated integers,  (the length of the sequence) and  (the beautiful difference), respectively. 
The second line contains  space-separated integers describing Erica's increasing sequence, .

Constraints

 for 
Output Format

Print a single line denoting the number of beautiful triplets in the sequence.

Sample Input

7 3
1 2 4 5 7 8 10
Sample Output

3
Explanation

Our input sequence is , and our beautiful difference . There are many possible triplets , but our only beautiful triplets are  ,  and . Please see the equations below:

 



Recall that a beautiful triplet satisfies the following equivalence relation:  where .
*/



import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int A[] = new int[n];
        for(int i=0; i < n; i++){
            A[i] = in.nextInt();
        }
        Map<Integer, Integer> match = new LinkedHashMap<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i == j) continue;
                if (Math.abs(A[i]-A[j]) == d) {
                    if (A[i] <= A[j]) {
                        match.put(A[i], A[j]);
                    } else {
                        match.put(A[j], A[i]);
                    }
                }
            }
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : match.entrySet()) {
            Integer first = e.getKey();
            Integer second = e.getValue();
            Integer third = match.get(second);
            if (third != null) {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
