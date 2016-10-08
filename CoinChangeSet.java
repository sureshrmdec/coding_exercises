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

public class CoinChangeSet {
	static Set<Set<Integer>> getSubsets(List<Integer> set, int index) {
		Set<Set<Integer>> allsubsets;
		if (set.size() == index) { // last index
			allsubsets = new HashSet<Set<Integer>>();
			allsubsets.add(new HashSet<Integer>()); // Empty set 
		} else {
			allsubsets = getSubsets(set, index + 1);
			int item = set.get(index);
			Set<Set<Integer>> moresubsets = new HashSet<Set<Integer>>();
			for (Set<Integer> subset : allsubsets) {
				Set<Integer> newsubset = new HashSet<Integer>(); 
				newsubset.addAll(subset); //
				newsubset.add(item);
				moresubsets.add(newsubset);
			}
			//System.out.println("ndx " + index + ", item " + item + " moresubsets " + moresubsets);
			allsubsets.addAll(moresubsets);
		}
		return allsubsets;
	}

  static int sum(Collection<Integer> list) {
		int sum = 0;
		for (Integer n : list) {
			sum += n;
		}
		return sum;
	}
  static String toKey(List<Integer> list) {
    Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (Integer n : list) {
			sb.append(n + "_");
		}
		return sb.toString();
	}

	public static long makeChange(List<Integer> coins, int money) {
		Collections.sort(coins, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		Map<String, Boolean> unique = new HashMap<>();
		Set<Set<Integer>>  result = getSubsets(coins, 0);
		for (Set<Integer> next: result) {
			if (next.size() > 0) {
        List<Integer> set = new ArrayList<Integer>(next);
				int sum = sum(set);
				int coinI = 0;
				while (sum < money && coinI < coins.size()) {
					int coin = coins.get(coinI);
					if (coin + sum <= money) {
						sum += coin;
            //System.out.println("\t Adding " + coin + " to Subset " + set+ " => sum " + sum + " < money " + money + ", coinI " + coinI + " < " + coins.size());
            set.add(coin);
					} else {
						coinI++;
					}
				}
			  if (sum == money) {
          String key = toKey(set);
          if (!unique.containsKey(key)) {
					  unique.put(key, Boolean.TRUE);
            System.out.println("Subset " + set + " => sum " + sum + " < money " + money + ", coinI " + coinI + " < " + coins.size() + ", key " + key);
          }
				}	
			}
		}
		return unique.size();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		List<Integer> coins = new ArrayList<>();
		for(int coins_i=0; coins_i < m; coins_i++){
			int next = in.nextInt();
			if (next < n) {
				coins.add(next);
			}
		}
		System.out.println(makeChange(coins, n));
	}
}
