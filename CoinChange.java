/** 
 * https://www.hackerrank.com/challenges/ctci-coin-change
 Given a number of dollars, , and a list of dollar values for  distinct coins, , find and print the number of different ways you can make change for  dollars if each coin is available in an infinite quantity.

Hints:

You can solve this problem recursively, but you must optimize your solution to eliminate overlapping subproblems using Dynamic Programming if you wish to pass all test cases. More specifically, think of ways to store the checked solutions and use the stored values to avoid repeatedly calculating the same values.
Think about the degenerate cases: 
How many ways can you make change for  dollars?
How many ways can you make change for less than  dollars if you have no coins?
If you are having trouble defining the storage for your precomputed values, then think about it in terms of the base case .
Input Format

The first line contain two space-separated integers describing the respective values of  and . 
The second line contains  space-separated integers describing the respective values of , where each integer denotes the dollar value of a distinct coin available in an infinite quantity.

Constraints

The list of coins contains  distinct integers where each integer denotes the dollar value of a coin available in an infinite quantity.
Output Format

Print a single integer denoting the number of ways we can make change for  dollars using an infinite supply of our  types of coins.

Sample Input 0

4 3
1 2 3 
Sample Output 0

4
Explanation 0 
For  and  there are four solutions:

Thus, we print  on a new line.

Sample Input 1

10 4
2 5 3 6
Sample Output 1

5
Explanation 1 
For  and  there are five solutions:

Thus, we print  on a new line.

*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CoinChange {
  public static long makeChange(List<Integer> coins, int coinI, int money) {
    if (coinI >= coins.size()) {
      return 0;
    } else if (coinI == coins.size()-1) {
      if (money % coins.get(coinI) == 0) {
        return 1;
      }
      return 0;
    }
    int current = coins.get(coinI);
    int next = coinI + 1 < coins.size() ? coins.get(coinI+1) : -1;
    long ways = 0;
    for (int i=0; i*current <= money; i++) {
      ways += makeChange(coins, coinI+1, money - (i*current));
    }
    return ways;
  }
  public static long makeChange(List<Integer> coins, int money) {
    long ways[] = new long[money+1];
    ways[0] = 1;
    for (Integer coin : coins) {
      for (int i=coin; i<= money; i++) {
        ways[i] += ways[i - coin];
        System.out.println("Aggregating coin " + coin + ", ways[" + i + "]=" + ways[i] + " from ways[" + (i-coin) + "]=" + ways[i-coin]);
      }
    }
    return ways[money];
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    List<Integer> coins = new ArrayList<>();
    for(int coins_i=0; coins_i < m; coins_i++){
      int next = in.nextInt();
      coins.add(next);
    }
    Collections.sort(coins, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
    System.out.println(makeChange(coins, n));
    //System.out.println(makeChange(coins, 0, n));
  }
}
