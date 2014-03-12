import java.io.*;
import java.util.*;

/*
   Given a completed N2xN2 Sudoku matrix, your task is to determine whether it is a valid solution. A valid solution must satisfy the following criteria:

   Each row contains each number from 1 to N2, once each.
   Each column contains each number from 1 to N2, once each.
   Divide the N2xN2 matrix into N2 non-overlapping NxN sub-matrices. Each sub-matrix contains each number from 1 to N2, once each.
   You don't need to worry about the uniqueness of the problem. Just check if the given matrix is a valid solution.

   Input

   The first line of the input gives the number of test cases, T. T test cases follow. Each test case starts with an integer N. The next N2 lines describe a completed Sudoku solution, with each line contains exactly N2 integers. All input integers are positive and less than 1000.

   Output

   For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is "Yes" (quotes for clarity only) if it is a valid solution, or "No" (quotes for clarity only) if it is invalid. Note that the judge is case-sensitive, so answers of "yes" and "no" will not be accepted.

   Limits

   1 ≤ T ≤ 100.

   Small dataset

   N = 3.

   Large dataset

   3 ≤ N ≤ 6.
   */

public class SudokuChecker {
  private int N;
  private int N2; // square of N
  private int[][] puzzle;
  public SudokuChecker(int N, int N2, int[][] puzzle) {
    this.N = N;
    this.N2 = N2;
    this.puzzle = puzzle;
  }

  private boolean checkSubmatrix(int startRow, int startColumn) {
    Map<Integer, Boolean> checked = new HashMap<>();
    for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
        Integer value = puzzle[(startRow+i)][(startColumn+j)];
        if (value < 1 || value > N2) {
          return false;
        }
        Boolean existing = checked.get(value);
        if (existing != null) {
          return false;
        }
        checked.put(value, true);
      }
    }
    return checked.size() == N2;
  }
  public boolean check() {
    for (int i=0; i<N2; i+=N) {
      for (int j=0; j<N2; j+=N) {
        checkSubmatrix(i, j);
      }
    }
    for (int i=0; i<N2; i++) {
      if (!checkRow(i)) {
        return false;
      }
    }
    for (int i=0; i<N2; i++) {
      if (!checkColumn(i)) {
        return false;
      }
    }
    return true;
  }

  private boolean checkRow(int row) {
    Map<Integer, Boolean> checked = new HashMap<>();
    for (int i=0; i<N2; i++) {
      Integer value = puzzle[row][i];
      if (value < 1 || value > N2) {
        return false;
      }
      Boolean existing = checked.get(value);
      if (existing != null) {
        return false;
      }
      checked.put(value, true);
    }
    return checked.size() == N2;
  }


  private boolean checkColumn(int column) {
    Map<Integer, Boolean> checked = new HashMap<>();
    for (int i=0; i<N2; i++) {
      Integer value = puzzle[i][column];
      if (value < 1 || value > N2) {
        return false;
      }
      Boolean existing = checked.get(value);
      if (existing != null) {
        return false;
      }
      checked.put(value, true);
    }
    return checked.size() == N2;
  }


  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    int T = Integer.parseInt(in.readLine().trim());
    for (int i=0; i<T; i++) {
      int N = Integer.parseInt(in.readLine().trim());
      int N2 = N * N; // square of N
      int[][] puzzle = new int[N2][];
      for (int j=0; j<N2; j++) {
        puzzle[j] = new int[N2];
        String line = in.readLine();
        String[] toks = line.split("\\s");
        for (int k=0; k<N2; k++) {
          puzzle[j][k] = Integer.parseInt(toks[k].trim());
        }
      }
      SudokuChecker sudokuChecker = new SudokuChecker(N, N2, puzzle);
      if (sudokuChecker.check()) {
        System.out.println("Case #" + (i+1) + ": Yes");
      } else {
        System.out.println("Case #" + (i+1) + ": No");
      }
    }
    in.close();
  }
}

