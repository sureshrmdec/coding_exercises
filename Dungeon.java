import java.util.*;

// https://oj.leetcode.com/problems/dungeon-game/
// The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
// The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
// Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
// In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
// Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
// For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
// -2 (K)  -3  3
// -5  -10 1
// 10  30  -5 (P)
public class Dungeon {
  public static int calculateMinimumHP(int[][] dungeon) {
    List<Integer> sums = new ArrayList<>();
    calculateMinimumHP(dungeon, 0, 0, 0, sums);
    for (int i=0; i<sums.size(); i++) {
      int sum = sums.get(i);
      if (sum <= 0) {
        sum = 0 - sum + 1;
        sums.set(i, sum);
      }
    }
    //
    int min = sums.get(0);
    for (int i=1; i<sums.size(); i++) {
      min = Math.min(sums.get(i), min);
    }
    return min;
  }
  public static void calculateMinimumHP(int[][] dungeon, int i, int j, int sum, List<Integer> sums) {
    sum += dungeon[i][j];
    if (i == dungeon.length-1 && j == dungeon[0].length-1) {
      //System.out.println("i " + i + ", j " + j + ", val " + dungeon[i][j] + ", sum " + sum);
      sums.add(sum);
      return;
    }
    if (j < dungeon[i].length-1) {
      calculateMinimumHP(dungeon, i, j+1, sum, sums);
    } 
    if (i < dungeon.length-1) {
      calculateMinimumHP(dungeon, i+1, j, sum, sums);
    }
  }
  public static void main(String[] args) {
    int[][] arr = new int[][] {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
    System.out.println(calculateMinimumHP(arr));
  }
}

