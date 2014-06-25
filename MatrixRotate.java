// You are given an n x n 2D matrix representing an image.
// Rotate the image by 90 degrees (clockwise).

public class MatrixRotate {
   public void rotate(int[][] matrix) {
      if(matrix == null || matrix.length==0)
         return ;

      int m = matrix.length;

      int[][] result = new int[m][m];

      for(int i=0; i<m; i++){
         for(int j=0; j<m; j++){
            result[j][m-1-i] = matrix[i][j];
         }
      } 

      for(int i=0; i<m; i++){
         for(int j=0; j<m; j++){
            matrix[i][j] = result[i][j];
         }
      } 
   }

   public void rotateInplace(int[][] matrix) {
      int n = matrix.length;
      for (int i = 0; i < n / 2; i++) {
         for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[n-1-j][i];
            matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
            matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
            matrix[j][n-1-i] = temp;
         }
      }
   }
}
