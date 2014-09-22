/*
   Given a 2D matrix, print all elements of the given matrix in diagonal order. For example, consider the following 5 X 4 input matrix.

   1     2     3     4
   5     6     7     8
   9    10    11    12
   13    14    15    16
   17    18    19    20
   Diagonal printing of the above matrix is

   1
   5     2
   9     6     3
   13    10     7     4
   17    14    11     8
   18    15    12
   19    16
   20
   */
public class PrintMatrixDiagonally {
  public static void diagonalOrder(int[][] matrix) {
    int ROW = matrix.length; 
    int COL = matrix[0].length;
    for (int line=1; line<=(ROW + COL -1); line++) {
      int start_col =  Math.max(0, line-ROW);
      int count = Math.min(Math.min(line, COL-start_col), ROW);
      for (int j=0; j<count; j++)
        System.out.print(String.format("%5d", matrix[Math.min(ROW, line)-j-1][start_col+j]));
      System.out.println();
    }
  }

  public static void printMatrix(int[][] matrix) {
    for (int i=0; i< matrix.length; i++) {
      for (int j=0; j<matrix[i].length; j++)
        System.out.print(String.format("%5d", matrix[i][j]));
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int M[][] = {{1, 2, 3, 4},
      {5, 6, 7, 8},
      {9, 10, 11, 12},
      {13, 14, 15, 16},
      {17, 18, 19, 20},
    };
    printMatrix(M);
    diagonalOrder(M);
  }
}

