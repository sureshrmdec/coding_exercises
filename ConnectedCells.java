/**
https://www.hackerrank.com/challenges/connected-cell-in-a-grid
Consider a matrix with  rows and  columns, where each cell contains either a  or a  and any cell containing a  is called a filled cell. Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally; in other words, cell  is connected to cells , , , , , , , and , provided that the location exists in the matrix for that .

If one or more filled cells are also connected, they form a region. Note that each cell in a region is connected to at least one other cell in the region but is not necessarily directly connected to all the other cells in the region.

Task 
Given an  matrix, find and print the number of cells in the largest region in the matrix. Note that there may be more than one region in the matrix.

Input Format

The first line contains an integer, , denoting the number of rows in the matrix. 
The second line contains an integer, , denoting the number of columns in the matrix. 
Each line  of the  subsequent lines contains  space-separated integers describing the respective values filling each row in the matrix.

Constraints

Output Format

Print the number of cells in the largest region in the given matrix.

Sample Input

4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0
Sample Output

5
Explanation

The diagram below depicts two regions of the matrix; for each region, the component cells forming the region are marked with an X:

X X 0 0     1 1 0 0
0 X X 0     0 1 1 0
0 0 X 0     0 0 1 0
1 0 0 0     X 0 0 0
The first region has five cells and the second region has one cell. Because we want to print the number of cells in the largest region of the matrix, we print .

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ConnectedCells {
    static void checkConnection(int[][] state, int r, int c, int i, int j, int region, Map<Integer, Set<Integer>> connections) {
        Set<Integer> regions = connections.get(region);
        if (regions == null) {
            regions = new HashSet<>();
            connections.put(region, regions);
        }
        if (i > 0 && state[i-1][j] != -1 && state[i-1][j] != region) { // upper
            regions.add(state[i-1][j]);
        } else if (i > 0 && j > 0 && state[i-1][j-1] != -1 && state[i-1][j-1] != region) { // upper-left
            regions.add(state[i-1][j-1]);
        } else if (i > 0 && j < c-1 && state[i-1][j+1] != -1 && state[i-1][j+1] != region) { // upper-right
            regions.add(state[i-1][j+1]);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);                                                                                                      
        int r = in.nextInt();
        int c = in.nextInt();
        int[][] matrix = new int[r][];
        int[][] state = new int[r][];
        for(int i=0;i<r;i++) {
            matrix[i] = new int[c];
            state[i] = new int[c];
            for (int j=0; j<c; j++) {
                matrix[i][j] = in.nextInt();
                state[i][j] = -1;
            }
        }
        int nextRegion = 0;
        Map<Integer, Set<Integer>> connections = new HashMap<>();
        for(int i=0;i<r;i++) {
            for (int j=0; j<c; j++) {
                if (state[i][j] == -1 && matrix[i][j] == 1) {
                    int region = -1;
                    if (i > 0 && state[i-1][j] != -1) { // upper-row
                        region = state[i-1][j];
                    } else if (i > 0 && j > 0 && state[i-1][j-1] != -1) { // upper-left
                        region = state[i-1][j-1];
                    } else if (i > 0 && j < c-1 && state[i-1][j+1] != -1) { // upper-right
                        region = state[i-1][j+1];
                    } else if (j > 0 && state[i][j-1] != -1) { // left-cell
                        region = state[i][j-1];
                    } else {
                        region = nextRegion;
                        nextRegion++;
                    }
                    state[i][j] = region;
                    checkConnection(state, r, c, i, j, region, connections);
                }
            }
        } 

        Map<Integer, Integer> counts = new HashMap<>();
        for(int i=0;i<r;i++) {
            for (int j=0; j<c; j++) {
                if (state[i][j] != -1) {
                    Integer count = counts.get(state[i][j]);
                    if (count == null) {
                        count = 1;
                    } else {
                        count = count + 1;
                    }
                    counts.put(state[i][j], count);
                }
            }
        } 
        for (Integer region : new ArrayList<Integer>(counts.keySet())) {
            Set<Integer> connection = connections.get(region);
            if (connection != null) {
                Integer count = counts.get(region);
                int smallestRegion = region;
                for (Integer regCon : connection) {
                    if (!region.equals(regCon)) {
                        count += counts.get(regCon);
                        smallestRegion = Math.min(smallestRegion, regCon);
                    }
                }
                if (smallestRegion == region) {
                    counts.put(smallestRegion, count);
                }
            }            
        }
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> region : counts.entrySet()) {
            if (region.getValue() > max) {
                max = region.getValue();
            }
        }

        System.out.println(max);
    }
}
