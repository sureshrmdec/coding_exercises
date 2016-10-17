/**
https://www.hackerrank.com/challenges/icecream-parlor
Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together  dollars for ice cream. On any given day, the parlor offers a line of  flavors. Each flavor, , is numbered sequentially with a unique ID number from  to  and has a cost, , associated with it.

Given the value of  and the cost of each flavor for  trips to the Ice Cream Parlor, help Sunny and Johnny choose two flavors such that they spend their entire pool of money () during each visit. For each trip to the parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated integers on a new line. You must print the smaller ID first and the larger ID second.

Note: Two ice creams having unique IDs  and  may have the same cost (i.e., ).

Input Format

The first line contains an integer, , denoting the number of trips to the ice cream parlor. The  subsequent lines describe all of Sunny and Johnny's trips to the parlor; each trip is described as follows:

The first line contains .
The second line contains .
The third line contains  space-separated integers denoting the cost of each respective flavor. The  integer corresponding to the cost, , for the ice cream with ID number  (where ).
Constraints

, where 
It is guaranteed that there will always be a unique solution.
Output Format

Print two space-separated integers denoting the respective ID numbers for the flavors they choose to purchase, where the smaller ID is printed first and the larger ID is printed second. Recall that each ice cream flavor has a unique ID number in the inclusive range from  to .

Sample Input

2
4
5
1 4 5 3 2
4
4
2 2 4 3
Sample Output

1 4
1 2
Explanation

Sunny and Johnny make the following two trips to the parlor:

The first time, they pool together  dollars. There are five flavors available that day and flavors  and have a total cost of . Thus, we print 1 4 on a new line.
The second time, they pool together  dollars. There are four flavors available that day and flavors  and have a total cost of . Thus, we print 1 2 on a new line
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IceCreamParlor {

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int i=0; i<q; i++) {
            int m = in.nextInt(); // money
            int n = in.nextInt(); // flavors
            int[] a = new int[n];
            for (int j=0; j<n; j++) {
                a[j] = in.nextInt();
            }   
            boolean found = false;
            for (int j=0; j<a.length && !found; j++) {
                for (int k=0; k<a.length; k++) {
                    if (a[j] + a[k] == m && j != k) {
                        found = true;
                        //System.out.println("a[" + j + "] " + a[j] + " + a[" + k + "] " + a[k] + " === " + m);
                        System.out.println((j+1) + " " + (k+1));
                        break;
                    }
                }
            }
        }
    }
}
