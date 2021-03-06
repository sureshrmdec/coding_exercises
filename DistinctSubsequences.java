//Given a string S and a string T, count the number of distinct subsequences of T in S.
// A subsequence of a string is a new string which is formed from the original string by deleting some 
// of the characters without disturbing the relative positions of the remaining characters. 
// (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
// Here is an example:
// S = "rabbbit", T = "rabbit"
// Return 3.
import java.util.*;


public class DistinctSubsequences {
   public static int numDistincts(String S, String T) {
      int[][] table = new int[S.length() + 1][T.length() + 1];

      for (int i = 0; i < S.length(); i++)
         table[i][0] = 1;
       for (int k=0; k<table.length ; k++) {
          System.out.println("before table " + k + ": " + Arrays.toString(table[k]));
       }

      for (int i = 1; i <= S.length(); i++) {
         for (int j = 1; j <= T.length(); j++) {
            if (S.charAt(i - 1) == T.charAt(j - 1)) {
               table[i][j] += table[i - 1][j] + table[i - 1][j - 1];
               System.err.println("S.charAt(" + (i-1) + ") " + S.charAt(i - 1)  + " == T.charAt(" + (j-1) + ") " + T.charAt(j-1) + ", table[i][j] " + table[i][j]);
               //for (int k=0; k<table.length ; k++) {
               //   System.out.println("table " + k + ": " + Arrays.toString(table[k]));
               //}
               System.out.println();
            } else {
               table[i][j] += table[i - 1][j];
            }
         }
      }
       for (int k=0; k<table.length ; k++) {
          System.out.println("after table " + k + ": " + Arrays.toString(table[k]));
       }
      return table[S.length()][T.length()];
   }
   public static void main(String[] args) {
      if (args.length == 2) {
         System.out.println(numDistincts(args[0], args[1]));
      } else {
         System.out.println(numDistincts("rabbbity", "rabbit"));
      }
   }
}
