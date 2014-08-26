/*

Problem C. Minesweeper Master
Confused? Read the quick-start guide.
Small input
11 points 
Solve C-small
You may try multiple times, with penalties for wrong submissions.
Large input
24 points 
You must solve the small input first.
You have 8 minutes to solve 1 input file. (Judged after contest.)
Problem

Minesweeper is a computer game that became popular in the 1980s, and is still included in some versions of the Microsoft Windows operating system. This problem has a similar idea, but it does not assume you have played Minesweeper.

In this problem, you are playing a game on a grid of identical cells. The content of each cell is initially hidden. There are M mines hidden in M different cells of the grid. No other cells contain mines. You may click on any cell to reveal it. If the revealed cell contains a mine, then the game is over, and you lose. Otherwise, the revealed cell will contain a digit between 0 and 8, inclusive, which corresponds to the number of neighboring cells that contain mines. Two cells are neighbors if they share a corner or an edge. Additionally, if the revealed cell contains a 0, then all of the neighbors of the revealed cell are automatically revealed as well, recursively. When all the cells that don't contain mines have been revealed, the game ends, and you win.

For example, an initial configuration of the board may look like this ('*' denotes a mine, and 'c' is the first clicked cell):

*..*...**.
....*.....
..c..*....
........*.
..........
There are no mines adjacent to the clicked cell, so when it is revealed, it becomes a 0, and its 8 adjacent cells are revealed as well. This process continues, resulting in the following board:
*..*...**.
1112*.....
00012*....
00001111*.
00000001..
At this point, there are still un-revealed cells that do not contain mines (denoted by '.' characters), so the player has to click again in order to continue the game.
You want to win the game as quickly as possible. There is nothing quicker than winning in one click. Given the size of the board (R x C) and the number of hidden mines M, is it possible (however unlikely) to win in one click? You may choose where you click. If it is possible, then print any valid mine configuration and the coordinates of your click, following the specifications in the Output section. Otherwise, print "Impossible".

Input

The first line of the input gives the number of test cases, T. T lines follow. Each line contains three space-separated integers: R, C, and M.

Output

For each test case, output a line containing "Case #x:", where x is the test case number (starting from 1). On the following R lines, output the board configuration with C characters per line, using '.' to represent an empty cell, '*' to represent a cell that contains a mine, and 'c' to represent the clicked cell.

If there is no possible configuration, then instead of the grid, output a line with "Impossible" instead. If there are multiple possible configurations, output any one of them.

Limits

0 ≤ M < R * C.
Small dataset

1 ≤ T ≤ 230.
1 ≤ R, C ≤ 5.
Large dataset

1 ≤ T ≤ 140.
1 ≤ R, C ≤ 50.
Sample


Input 
  
Output 
 
5
5 5 23
3 1 1
2 2 1
4 7 3
10 10 82

Case #1:
Impossible
Case #2:
c
.
*
Case #3:
Impossible
Case #4:
......*
.c....*
.......
..*....
Case #5:
**********
**********
**********
****....**
***.....**
***.c...**
***....***
**********
**********
**********




*/


import java.io.*;
import java.util.*;

public class MinesweeperMaster {
  private static class Board {
    char[] _configuration;
    int m;
    String configRepr;
    Board() {
      _configuration = new char[R*C];
      for (int i=0; i<R*C; i++) {
        _configuration[i] = '.';
      }
    }
    public int hashCode() {
      return configRepr.hashCode();
    }
    public boolean equals(Object o) {
      Board other = (Board) o;
      return configRepr.equals(other.configRepr);
    }
    char get(int i, int j) {
      int n = i*C + j;
      if (n >= _configuration.length) throw new IllegalArgumentException("configuration of size " + _configuration.length + " cannot be accessed at n " + n + ", i " + i + ", j " + j + ", R " + R + ", C " + C);
      return _configuration[n];
    }
    void set(int i, int j, char ch) {
      int n = i*C + j;
      _configuration[n] = ch;
    }

    private void reset() {
      for (int i=0; i<_configuration.length; i++) {
        if (_configuration[i] != '*') {
          _configuration[i] = '.';
        }
      }
    }
    private boolean solve() {
      if (!isTogether()) {
        return false;
      }
      List<int[]> clickable = new ArrayList<int[]>();
      for (int i=0; i<R; i++) {
        for (int j=0; j<C; j++) {
          if (get(i, j) == '.' && safeAround(i, j)) {
            clickable.add(new int[] {i, j});
          }
        }
      }
      if (clickable.size() == 0) {
        return false;
      }
      for (int[] xy : clickable) {
        reset();
        click(xy[0], xy[1]);
        if (!hasDots()) {
          reset();
          set(xy[0], xy[1], 'c');
          buildRepr(); 
          return true;
        }
      }
      return false;
    }
    private void click(int i, int j) {
      if (i >=0 && j >=0 && i<R && j<C && get(i, j) == '.') {
        int mines = numMinesAround(i, j);
        set(i, j, (char) ('0' + mines));
        if (mines == 0) {
          click(i-1, j-1);
          click(i-1, j);
          click(i-1, j+1);
          click(i, j-1);
          click(i, j+1);
          click(i+1, j-1);
          click(i+1, j);
          click(i+1, j+1);
        }
      }
    }


    private boolean isDot(int i, int j) {
      if (i >=0 && j >=0 && i<R && j<C) {
        return get(i, j) == '.';
      } else {
        return true;
      }
    }
    private int isMine(int i, int j) {
      if (i >=0 && j >=0 && i<R && j<C && get(i, j) == '*') {
        return 1;
      } else {
        return 0;
      }
    }
    private boolean safeAround(int i, int j) {
      return isDot(i-1, j-1) && isDot(i-1, j) && isDot(i-1, j+1) && isDot(i, j-1) && isDot(i, j+1) && isDot(i+1, j-1) && isDot(i+1, j) && isDot(i+1, j+1);
    }
    private int numMinesAround(int i, int j) {
      return isMine(i-1, j-1) + isMine(i-1, j) + isMine(i-1, j+1) + isMine(i, j-1) + isMine(i, j+1) + isMine(i+1, j-1) + isMine(i+1, j) + isMine(i+1, j+1);
    }

    private boolean hasDots() {
      for (int i=0; i<_configuration.length; i++) {
        if (_configuration[i] == '.') {
          return true;
        }
      }
      return false;
    }

    private boolean isTogether() {
      Map<Integer, Boolean> rows = new TreeMap<Integer, Boolean>();
      Map<Integer, Boolean> columns = new TreeMap<Integer, Boolean>();
      for (int i=0; i<R; i++) {
        for (int j=0; j<C; j++) {
          if (get(i, j) == '.') {
            rows.put(i, Boolean.TRUE);
            columns.put(j, Boolean.TRUE);
          }
        }
      }
      //
      Integer prevRow = null;
      for (Integer row : rows.keySet()) {
        if (prevRow == null) {
          prevRow = row;
        } else if (row - prevRow > 1) {
          return false;
        }
        prevRow = row;
      }

      Integer prevCol = null;
      for (Integer col : columns.keySet()) {
        if (prevCol == null) {
          prevCol = col;
        } else if (col - prevCol > 1) {
          return false;
        }
        prevCol = col;
      }
      return true;
    }

    public String toString() {
      if (configRepr == null) buildRepr(); 
      return configRepr;
    }

    public void buildRepr() {
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<R; i++) {
        for (int j=0; j<C; j++) {
          if (j > 0) {
            sb.append(' ');
          }
          sb.append(get(i, j));
        }
        sb.append('\n');
      }
      configRepr = sb.toString();
    }
  }


  private static int R; // # of rows
  private static int C; // # of columns
  private static int M; // # of mines
  private List<Board> boards = new ArrayList<Board>();

  private static MinesweeperMaster parse(BufferedReader in) throws IOException {
    MinesweeperMaster mm = new MinesweeperMaster();
    String[] toks = in.readLine().split(" ");
    mm.R = Integer.parseInt(toks[0].trim());
    mm.C = Integer.parseInt(toks[1].trim());
    mm.M = Integer.parseInt(toks[2].trim());
    for (int i=0; i<mm.R; i++) {
      for (int j=0; j<mm.C; j++) {
        Board b = new Board();
        mm.boards.add(b);
      }
    }
    for (int m=0; m<mm.M; m++) {
      mm.fill(m);
    }
    
    return mm;
  }


  private void fill(int m) {
    for (int i=0; i<R*C; i++) {
      Board b = boards.get(i);
      for (int j=0; j<b._configuration.length; j++) {
        if (b.m < M && b._configuration[j] == '.') {
          b._configuration[j] = '*';
          b.m++;
        }
      }
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("R " + R + ", C " + C + ", M " + M + "\n");
    for (int i=0; i<R; i++) {
      for (int j=0; j<C; j++) {
        Board b = boards.get(i*C+j);
        sb.append(b.toString());
      }
    }
    return sb.toString();
  }

  private Board solve() {
    for (Board b : boards) {
      if (b.solve()) {
        //System.out.println("Solved " + this + "\n" + b);
        return b;
      }
    }
    return null;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    int N = Integer.parseInt(in.readLine());
    for (int i=0; i<N; i++) {
      MinesweeperMaster mm = parse(in);
      System.out.println(mm);
      /*
      Board b = mm.solve();
      if (b == null) {
        System.out.println(String.format("Case #%d:\nImpossible", i+1));
      } else {
        System.out.println(String.format("Case #%d:\n%s", i+1, b.toString()));
      }
      */
    }

    in.close();
  }
}
