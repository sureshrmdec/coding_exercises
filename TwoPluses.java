import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TwoPluses {

  static class Cell {
    int r;
    int c;

    Cell(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
          return false;
        Cell cell = (Cell) o;
        return cell.r == r && cell.c == c;
      }
  }

  static class Plus {
    int r, c;
    Set<Cell> cells = new HashSet<>();
    boolean[][] grid;

    Plus(int r, int c, boolean[][] grid) {
      this.r = r;
      this.c = c;
      this.grid = grid;
      cells.add(new Cell(r, c));
    }

    int size() {
      return cells.size();
    }

    List<Plus> grow() {
      List<Plus> pluses = new ArrayList<>();
      return grow(0, pluses, this);
    }

    List<Plus> grow(int len, List<Plus> pluses, Plus last) {
      Plus clone = new Plus(r,c,grid);
      clone.cells = new HashSet<>();
      clone.cells.addAll(last.cells);
      pluses.add(clone);
      ++len;
      if (r - len < 0
          || c - len < 0
          || r + len >= grid.length
          || c + len >= grid[0].length)
        return pluses;
      if (!(grid[r - len][c]
            && grid[r + len][c]
            && grid[r][c - len]
            && grid[r][c + len]))
        return pluses;
      Plus newP = last;
      newP.cells.add(new Cell((r - len), c));
      newP.cells.add(new Cell((r + len), c));
      newP.cells.add(new Cell(r, (c - len)));
      newP.cells.add(new Cell(r, (c + len)));
      pluses.add(newP);
      return grow(len, pluses, newP);
    }
    boolean isCrossed(Plus plus) {
      for (Cell cell : cells) {
        for (Cell c : plus.cells) {
          if (cell.equals(c)) return true;
        }
      }
      return false;
    }
  }



  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int r = scanner.nextInt();
    int c = scanner.nextInt();
    boolean[][] grid = new boolean[r][c];
    for (int i = 0; i < r; i++) {
      String s = scanner.next();
      for (int j = 0; j < c; j++) {
        grid[i][j] = s.charAt(j) == 'G';
      }
    }
    Set<Plus> allPlusesSet = new HashSet<>();
    for (int i = 1; i < r - 1; i++) {
      for (int j = 1; j < c - 1; j++) {
        Plus plus = new Plus(i, j, grid);
        List<Plus> pluses = plus.grow();
        allPlusesSet.addAll(pluses);
      }
    }
    List<Plus> allPlusesList = new ArrayList<>();
    allPlusesList.addAll(allPlusesSet);
    int max = 0;
    for (int i = 0; i < allPlusesList.size(); i++) {
      Plus plus = allPlusesList.get(i);
      for (int j = allPlusesList.size() - 1; j > i; j--) {
        Plus p = allPlusesList.get(j);
        if (plus.isCrossed(p)) continue;
        int product = plus.size() * p.size();
        if (product > max) max = product;
      }
    }
    System.out.println(max);
  }
}
