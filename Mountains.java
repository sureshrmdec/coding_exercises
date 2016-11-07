/**
https://www.hackerrank.com/contests/walmart-codesprint-algo/challenges/popsicle-stick-mountains
Victor loves building structures using popsicle sticks. Each time he goes to Walmart, he purchases  identical popsicle sticks. When he gets home, he places the sticks on a sheet of paper in such a way that they form mountains like the ones in the following diagram:

1.jpg

Victor builds mountains according to the following rules:

The two bottom endpoints (i.e., the base) of the mountain always touch the ground.
None of the intermediate sticks between the mountain's left and right endpoints can be below ground level.
Sticks are only placed at  or  angles with the ground.
No stick is ever placed inside the walls of a mountain.
The diagram below demonstrates structures that violate these rules and are not allowed:

2.jpg

Victor is curious to know how many different types of mountains he can build following the rules above; two mountains are considered to be different if they look different. Given the value of  for  trips to Walmart, print the number of mountains he can build using up to  sticks on a new line. As this value can be quite large, print each answer modulo .

Input Format

The first line contains an integer, , denoting the number of trips to Walmart. 
Each of the  subsequent lines contains an integer, , denoting the number of sticks Victor purchased during that trip to Walmart.

Constraints

It is guaranteed that  is always an even number.
Output Format

For each trip to Walmart, print the number of structures Victor can build using up to  sticks, modulo , on a new line.

Sample Input

3
2
6
20
Sample Output

1
8
23713
Explanation

Victor makes  trips to Walmart to puchase popsicle sticks:

During the first trip, Victor can build one mountain with his  sticks:

3.jpg
Thus, we print  on a new line.

During the second trip, Victor can build eight mountains with his  sticks:

4.jpg
Thus, we print  on a new line.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Mountains {
  static Map<Integer, Long> counts = new HashMap<>();
  //
  public static long mountains(int n) {
    if (n <= 0) return 0;
    if (n == 2) return 1;
    Long oldCount = counts.get(n);
    if (oldCount != null) {
      return oldCount;
    }
    int max = (int) Math.pow(2, n);
    long count = 0;
    for (int i=0; i<=max; i++) {
      int ground = 0;
      for (int j=0; j<n; j++) {
        if ((i & (1L << j)) != 0) {
          ground++;
        } else {
          ground--;
          if (ground < 0) {
            break;
          }
        }
      } 
      if (ground == 0) {
        count++;
      }
    }
    counts.put(n, count);
    return count;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i=0; i<n; i++) {
      int m = in.nextInt();
      long count = 0;
      for (int j=2; j<=m; j++) {
        count += mountains(j);
      }
      System.out.println(count);
    }
  }
}
