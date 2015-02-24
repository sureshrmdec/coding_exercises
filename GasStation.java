import java.util.*;


public class GasStation {
    // https://oj.leetcode.com/problems/gas-station/
    // There are N gas stations along a circular route, where the amount of gas
    // at station i is gas[i].
    //
    // You have a car with an unlimited gas tank and it costs cost[i] of gas to
    // travel from station i to its next station (i+1). You begin the journey
    // with an empty tank at one of the gas stations.
    //
    // Return the starting gas station's index if you can travel around the
    // circuit once, otherwise return -1.
    //
    // Note:
    // The solution is guaranteed to be unique.
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalCost = gas[0] - cost[0];
        int min = totalCost;
        int ndx = 0;
        for (int i=1; i<gas.length; i++) {
            totalCost = gas[i] - cost[i];
            if (totalCost < min) {
                min = totalCost;
                ndx = i;
            }
        }
        return totalCost >= 0 ? ndx : -1;
    }
    //
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {0, 1, 2, 3, 4, 7, 5}));
        System.out.println(canCompleteCircuit(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 3, 4, 5, 7}));
    }
}
