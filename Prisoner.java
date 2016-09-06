import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
A jail has  prisoners, and each prisoner has a unique id number, , ranging from  to . There are  sweets that must be distributed to the prisoners.

The jailer decides the fairest way to do this is by sitting the prisoners down in a circle (ordered by ascending ), and then, starting with some random , distribute one candy at a time to each sequentially numbered prisoner until all  candies are distributed. For example, if the jailer picks prisoner , then his distribution order would be  until all  sweets are distributed.

But wait—there's a catch—the very last sweet is poisoned! Can you find and print the ID number of the last prisoner to receive a sweet so he can be warned?

Input Format

The first line contains an integer, , denoting the number of test cases. 
The  subsequent lines each contain  space-separated integers: 
 (the number of prisoners),  (the number of sweets), and  (the prisoner ID), respectively.

Constraints

Output Format

For each test case, print the ID number of the prisoner who receives the poisoned sweet on a new line.

Sample Input

1 
5 2 1
Sample Output

2
Explanation

There are  prisoners and  sweets. Distribution starts at ID number , so prisoner  gets the first sweet and prisoner  gets the second (last) sweet. Thus, we must warn prisoner  about the poison, so we print on a new line.
*/


public class Prisoner {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i=0; i<T; i++) {
            int N = in.nextInt(); // the number of prisoners
            int M = in.nextInt(); // the number of sweets
            int S = in.nextInt(); // the prisoner ID
            //
            int total = S + M;
            int s = total % N - 1;
            
            if (s == 0) {
                s = N;
            }
            System.out.println(s);
        }
    }
}
