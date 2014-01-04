import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
Princess Peach is trapped in one of the four corners of a square grid. You are in the center of the grid and can move one step at a time in any of the four directions. Can you rescue the princess?

Input format

The first line contains an odd integer N (< 100) denoting the size of the grid. This is followed by an NxN grid. Each cell is denoted by ‘-‘ (ascii value: 45). The bot position is denoted by ‘m’ and the princess position is denoted by ‘p’.

Grid is indexed using Matrix Convention

Output format

Print out all the moves you take to rescue the princess in one go. The moves must be separated by ‘\n’, a newline. The valid outputs are LEFT or RIGHT or UP or DOWN.

Sample input

3
---
-m-
p--
Sample output

DOWN
LEFT
Task

Complete the function displayPathtoPrincess which takes in two parameters - the integer N and the character array grid. The grid will be formatted exactly as you see it in the input, so for the sample input the princess is at grid[2][0]. The function shall output moves (LEFT, RIGHT, UP or DOWN) on consecutive lines to rescue/reach the princess. The goal is to reach the princess in as few moves as possible.

The above sample input is just to help you understand the format. The princess (‘p’) can be in any one of the four corners

Scoring
Your score is calculated as follows : (NxN - moves made to rescue the princess)/10, where N is the size of the grid (3x3 in the sample testcase).
*/


public class SavePrincess {
  /* Head ends here */
  static void displayPathtoPrincess(int n, String [] grid){
    int[] bot = null;
    int[] princess = null;
    for (int i=0; i<n; i++) {
      for (int j=0;j<n; j++) {
        if (grid[i].charAt(j) == 'm') {
          bot = new int[] {i, j};
        } else  if (grid[i].charAt(j) == 'p') {
          princess = new int[] {i, j};
        }
        if (bot != null && princess != null) {
          break;
        }
      }
      if (bot != null && princess != null) {
        break;
      }
    }
    while (bot[0] != princess[0] || bot[1] != princess[1]) {
      //System.out.println("Bot " + bot[0] + "/" + bot[1] + ", princess " + princess[0] + "/" + princess[1]);
      if (bot[0] > princess[0]) {
        bot[0] = bot[0]-1;
        System.out.println("UP");
      } else if (bot[0] < princess[0]) {
        bot[0] = bot[0]+1;
        System.out.println("DOWN");
      } else {
        if (bot[1] > princess[1]) {
          bot[1] = bot[1]-1;
          System.out.println("LEFT");
        } else if (bot[1] < princess[1]) {
          bot[1] = bot[1]+1;
          System.out.println("RIGHT");
        }
      }
    }
  }


  /* Tail starts here */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int m;
    m = in.nextInt();
    String grid[] = new String[m];
    for(int i = 0; i < m; i++) {
      grid[i] = in.next();
    }

    displayPathtoPrincess(m,grid);
  }
}


