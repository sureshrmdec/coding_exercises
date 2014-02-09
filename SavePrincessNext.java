import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
  Task

  Complete the function nextMove which takes in 4 parameters - an integerN, an integer r and c indicating the row & column position of the bot and the character array grid and output only the next move the bot makes to rescue the princess.

  Input Format

  The first line of the input is N (<100), the size of the board (NxN). The second line of the input contains two space separated integers, which is the position of the bot.

  Grid is indexed using Matrix Convention

  The position of the princess is indicated by the character ‘p’ and the position of the bot is indicated by the character ‘m’ and each cell is denoted by ‘-‘ (ascii value: 45).

  Output Format

  Output only the next move you take to rescue the princess. Valid moves are LEFT or RIGHT or UP or DOWN

  Sample Input

  5
  2 3
  -----
  -----
  p--m-
  -----
  -----
  Sample Output

  LEFT
  Scoring
  Your score for every testcase would be (NxN minus number of moves made to rescue the princess)/10 where N is the size of the grid (5x5 in the sample testcase). 
  */


public class SavePrincessNext {
  /* Head ends here */
  static void nextMove(int n, int r, int c, String [] grid){
    int[] princess = null;
    for (int i=0; i<n; i++) {
      for (int j=0;j<n; j++) {
        if (grid[i].charAt(j) == 'p') {
          princess = new int[] {i, j};
        }
        if (princess != null) {
          break;
        }
      }
      if (princess != null) {
        break;
      }
    }
    if (r != princess[0] || c != princess[1]) {
      if (r > princess[0]) {
        System.out.println("UP");
      } else if (r < princess[0]) {
        System.out.println("DOWN");
      } else {
        if (c > princess[1]) {
          System.out.println("LEFT");
        } else if (c < princess[1]) {
          System.out.println("RIGHT");
        }
      }
    }
  }
  /* Tail starts here */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n,r,c;
    n = in.nextInt();
    r = in.nextInt();
    c = in.nextInt();
    in.useDelimiter("\n");
    String grid[] = new String[n];


    for(int i = 0; i < n; i++) {
      grid[i] = in.next();
    }

    nextMove(n,r,c,grid);

  }
}
