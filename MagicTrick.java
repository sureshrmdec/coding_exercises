/*

Problem A. Magic Trick
Confused? Read the quick-start guide.
Small input
6 points  
Solve A-small
You may try multiple times, with penalties for wrong submissions.
Note: To advance to the next rounds, you will need to score 25 points. Solving just this problem will not give you enough points.

Problem

Recently you went to a magic show. You were very impressed by one of the tricks, so you decided to try to figure out the secret behind it!

The magician starts by arranging 16 cards in a square grid: 4 rows of cards, with 4 cards in each row. Each card has a different number from 1 to 16 written on the side that is showing. Next, the magician asks a volunteer to choose a card, and to tell him which row that card is in.

Finally, the magician arranges the 16 cards in a square grid again, possibly in a different order. Once again, he asks the volunteer which row her card is in. With only the answers to these two questions, the magician then correctly determines which card the volunteer chose. Amazing, right?

You decide to write a program to help you understand the magician's technique. The program will be given the two arrangements of the cards, and the volunteer's answers to the two questions: the row number of the selected card in the first arrangement, and the row number of the selected card in the second arrangement. The rows are numbered 1 to 4 from top to bottom.

Your program should determine which card the volunteer chose; or if there is more than one card the volunteer might have chosen (the magician did a bad job); or if there's no card consistent with the volunteer's answers (the volunteer cheated).

Solving this problem

Usually, Google Code Jam problems have 1 Small input and 1 Large input. This problem has only 1 Small input. Once you have solved the Small input, you have finished solving this problem.

Input

The first line of the input gives the number of test cases, T. T test cases follow. Each test case starts with a line containing an integer: the answer to the first question. The next 4 lines represent the first arrangement of the cards: each contains 4 integers, separated by a single space. The next line contains the answer to the second question, and the following four lines contain the second arrangement in the same format.

Output

For each test case, output one line containing "Case #x: y", where x is the test case number (starting from 1).

If there is a single card the volunteer could have chosen, y should be the number on the card. If there are multiple cards the volunteer could have chosen, y should be "Bad magician!", without the quotes. If there are no cards consistent with the volunteer's answers, y should be "Volunteer cheated!", without the quotes. The text needs to be exactly right, so consider copying/pasting it from here.

Limits

1 ≤ T ≤ 100.
1 ≤ both answers ≤ 4.
Each number from 1 to 16 will appear exactly once in each arrangement.

Sample


Input 
  
Output 
 
3
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
3
1 2 5 4
3 11 6 15
9 10 7 12
13 14 8 16
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
3
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16


*/


import java.io.*;
import java.util.*;

public class MagicTrick {
  private static class Grid {
    int[] rowCards;
    int row;
    public String toString() {
      return "grid " + Arrays.toString(rowCards) + ", row " + row;
    }
  }
    
  private static void solve(int test, Grid[] grids) {
    Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
    int possible = 0;
    int last = 0;
    for (int i=0; i<4; i++) {
      for (int j=0; j<grids.length; j++) {
        Integer count = counts.get(grids[j].rowCards[i]);
        if (count == null) {
          counts.put(grids[j].rowCards[i], 1);
        } else {
          counts.put(grids[j].rowCards[i], count+1);
          possible++;
          last = grids[j].rowCards[i];
        }
      }
    }
    if (possible == 0) {
      System.out.println("Case #" + test + ": Volunteer cheated!");
    } else if (possible == 1) {
      System.out.println("Case #" + test + ": " + last);
    } else if (possible > 1) {
      System.out.println("Case #" + test + ": Bad magician!");
    } 
  }
  //
  private static Grid parse(BufferedReader in) throws IOException {
    Grid g = new Grid();
    g.row = Integer.parseInt(in.readLine().trim()) - 1;
    g.rowCards = new int[4];
    for (int i=0; i<4; i++) {
      String line = in.readLine().trim();
      String[] toks = line.split("\\D+");
      for (int j=0; j<toks.length; j++) {
        if (g.row == i) {
          g.rowCards[j] = Integer.parseInt(toks[j].trim());
        }
      }
    }
    return g;
  }


  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    int N = Integer.parseInt(in.readLine());
    for (int i=0; i<N; i++) {
      Grid grid1 = parse(in);
      Grid grid2 = parse(in);
      solve(i+1, new Grid[] {grid1, grid2});
    }

    in.close();
  }
}
