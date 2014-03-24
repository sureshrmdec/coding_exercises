/*
 


Write a program that reads a chess table and reports which player, if any, is in check.

Input notes:

    The input is a string of 64 symbols representing the chess table (first 8 symbols represent the first line of the chess table, the next 8 the second line and so on)
    The symbols are as follows: P = pawn, R = rook, N = knight, B = bishop, Q = queen, K = king, . = empty
    Uppercase symbols represent White's pieces, lowercase symbols Black's
    The top of the board represents Black's starting position
    It is guaranteed that there is exactly one king for each side, and that at most one player is in check
    There are no other guarantees as to the number of pieces, i.e. a table can have more than 2 queens, 4 rooks etc.

Output notes:
Please print:

    White if the white player is in check
    Black if the black player is in check
    None if none of the players are in check

Example input:

rnbqk.nr
pppp.ppp
........
..b.p..Q
..B.P...
........
PPPP.PPP
RNB.K.NR    

Example output:
None


Example input:

..k.b...
ppp.....
........
r....B..
........
......P.
.....P.P
.....RK.

Example output:
Black


Example input:

..r.....
....k...
....p...
..p.....
........
........
.....nPP
..B...RK

Example output:
White

*/


import java.io.*;
import java.util.*;

public class ChessCheck {
  public static boolean isEmptyRow(char[][] board, int column, int first, int second) {
    int start;
    int finish;
    if (first < second) {
      start = first+1;
      finish = second;
    } else {
      start = second+1;
      finish = first;
    }
    for (int i=start; i<finish; i++) {
      if (board[i][column] != '.') {
        return false;
      }
    }
    //System.out.println("empty row for column " + column + ", from " + first + ", to " + second);
    return true;
  }

  public static boolean isEmptyColumn(char[][] board, int row, int first, int second) {
    int start;
    int finish;
    if (first < second) {
      start = first+1;
      finish = second;
    } else {
      start = second+1;
      finish = first;
    }
    for (int i=start; i<finish; i++) {
      if (board[row][i] != '.') {
        return false;
      }
    }
    //System.out.println("empty column for row " + row + ", from " + first + ", to " + second);
    return true;
  }

  public static boolean isEmptyDiagonal(char[][] board, int[] first, int[] second) {
    int startx;
    int finishx;
    int starty;
    int finishy;
    if (first[0] < second[0]) {
      startx = first[0]+1;
      finishx = second[0];
    } else {
      startx = second[0]+1;
      finishx = first[0];
    }
    if (first[1] < second[1]) {
      starty = first[1]+1;
      finishy = second[1];
    } else {
      starty = second[1]+1;
      finishy = first[1];
    }
    if (finishy - starty != finishx - startx) {
      return false;
    }
    for (int i=startx, j=starty; i<finishx && j<finishy; i++, j++) {
      if (board[i][j] != '.') {
        return false;
      }
    }
    //System.out.println("empty diagonal from " + first[0] + "/" + first[1] + " to " + second[0] + "/" + second[1]);
    return true;
  }

  public static void is_check(String table) {
    int[] K = null;
    int[] k = null;
    char[][] board = new char[8][];
    int n = 0;
    for (int i=0; i<8; i++) {
      board[i] = new char[8];
      for (int j=0; j<8; j++) {
        board[i][j] = table.charAt(n++);
        if (board[i][j] == 'K') {
          K = new int[] {i, j};
        } else if (board[i][j] == 'k') {
          k = new int[] {i, j};
        }
      }
    }
    for (int i=0; i<8; i++) {
      for (int j=0; j<8; j++) {
        char piece = board[i][j];
        if (piece == 'P') {
            if (k[0] == i-1 && (k[1] == j-1 || k[1] == j+1)) {
              System.out.println("Black");
              return;
            }
        } else if (piece == 'R') {
            if ((k[0] == i && isEmptyRow(board, j, k[0], i)) ||
                (k[1] == j && isEmptyColumn(board, i, k[1], j))) {
              System.out.println("Black");
              return;
            }
        } else if (piece == 'N') {
            if (((k[0] == i-2 || k[0] == i+2) && (k[1] == j-1 || k[1] == j+1)) ||
                ((k[0] == i-1 || k[0] == i+1) && (k[1] == j-2 || k[1] == j+2))) {
              System.out.println("Black");
              return;
            }
        } else if (piece == 'B') {
            if (isEmptyDiagonal(board, k, new int[] {i, j})) {
              System.out.println("Black");
              return;
            }
        } else if (piece == 'Q') {
            if (isEmptyDiagonal(board, k, new int[] {i, j}) || ((k[0] == i && isEmptyRow(board, j, k[0], i)) ||
                (k[1] == j && isEmptyColumn(board, i, k[1], j)))) {
              System.out.println("Black");
              return;
            }
        } else if (piece == 'p') {
            if (K[0] == i+1 && (K[1] == j-1 || K[1] == j+1)) {
              System.out.println("White");
              return;
            }
        } else if (piece == 'r') {
            if ((K[0] == i && isEmptyRow(board, j, K[0], i)) ||
                (K[1] == j && isEmptyColumn(board, i, K[1], j))) {
              System.out.println("White");
              return;
            }
        } else if (piece == 'n') {
            if (((K[0] == i-2 || K[0] == i+2) && (K[1] == j-1 || K[1] == j+1)) ||
                ((K[0] == i-1 || K[0] == i+1) && (K[1] == j-2 || K[1] == j+2))) {
              System.out.println("White");
              return;
            }
        } else if (piece == 'b') {
            if (isEmptyDiagonal(board, K, new int[] {i, j})) {
              System.out.println("White");
              return;
            }
        } else if (piece == 'q') {
            if (isEmptyDiagonal(board, K, new int[] {i, j}) || ((K[0] == i && isEmptyRow(board, j, K[0], i)) ||
                (K[1] == j && isEmptyColumn(board, i, K[1], j)))) {
              System.out.println("White");
              return;
            }
        }
      }
    }
    System.out.println("None");
  }


  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line=in.readLine()) != null) {
      sb.append(line.trim());
    }
    in.close();
    ChessCheck.is_check(sb.toString());
  }
}
