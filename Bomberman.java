import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
Bomberman lives in a rectangular grid with  rows and  columns. Each cell in the grid either contains a bomb or nothing at all.

Each bomb can be planted in any cell of the grid but, once planted, it will detonate after exactly 3 seconds. Once a bomb detonates, it's destroyed — along with anything in its four neighboring cells. This means that if a bomb detonates in cell , cells  and  are cleared (note that cells at the edge of the grid have less than four neighboring cells). If there is a bomb in a neighboring cell, the neighboring bomb is destroyed without detonating (i.e., no chain reaction occurs).

Bomberman is immune to bombs, so he can move freely throughout the grid. Here's what he does:

Initially, Bomberman arbitrarily plants bombs in some of the cells.
After one second, Bomberman does nothing.
After one more second, Bomberman plants bombs in all cells without bombs, thus filling the whole grid with bombs. It is guaranteed that no bombs will detonate at this second.
After one more second, any bombs planted exactly three seconds ago will detonate. Here, Bomberman stands back and observes.
Bomberman then repeats steps 3 and 4 indefinitely.
Note that during every second Bomberman plants bombs, the bombs are planted simultaneously (i.e., at the exact same moment), and any bombs planted at the same time will detonate at the same time.

Given the initial configuration of the grid with the locations of Bomberman's first batch of planted bombs, determine the state of the grid after  seconds.

Input Format

The first line contains three space-separated integers denoting the respective values of , , and . 
Each line  of the  subsequent lines describes row  of the grid's initial state as a single string of  characters; the . character denotes an empty cell, and the O character (ascii 79) denotes a bomb.

Constraints

Subtask

 for  of the maximum score.
Output Format

Print the grid's final state. This means  lines where each line contains  characters, and each character is either a . or an O (ascii 79). This grid must represent the state of the grid after  seconds.

Sample Input

6 7 3
.......
...O...
....O..
.......
OO.....
OO.....
Sample Output

OOO.OOO
OO...OO
OOO...O
..OO.OO
...OOOO
...OOOO
Explanation

The initial state of the grid is:

.......
...O...
....O..
.......
OO.....
OO.....
Bomberman spends the first second doing nothing, so this is the state after 1 second:

.......
...O...
....O..
.......
OO.....
OO.....
Bomberman plants bombs in all the empty cells during his second second, so this is the state after 2 seconds:

OOOOOOO
OOOOOOO
OOOOOOO
OOOOOOO
OOOOOOO
OOOOOOO
In his third second, Bomberman sits back and watches all the bombs he planted 3 seconds ago detonate. This is the final state after  seconds:

OOO.OOO
OO...OO
OOO...O
..OO.OO
...OOOO
...OOOO


Assume using the following time:
N = 0 (initial board - time of bomb is 3)
N = 1 (do nothing - time of bomb is reduced to 2)
N = 2 (time of existing bombs is reduced to 1, add new bombs to empty cells with time 3)
N = 3 (bomb with time 1 will exploded, new bomb added in previous steps have time reduced to 2).
I use the following test to illustrate my algorithm.
4 3
O..O
.O..
....
I convert it to the following arrays in my solution:
3 0 0 3
0 3 0 0
0 0 0 0
(3 is bomb with time 3. 0 is empty cells i.e has no bombs).
Below are some observation:
1/ When N = 4, N = 6, ..., board is full of bombs.
2/ Board at N = 3 is the same with board at N = 7, N = 11
3/ Board at N = 5 is the same with board at N = 9, N = 13
4/ To solve board at N = 3. Calculate board at N = 2
Given example a board with N = 0 N = 2
3 0 0 3        1 3 3 1
0 3 0 0  ->  3 1 3 3
0 0 0 0        3 3 3 3
After 1 second (N = 3), cell with 1 will explode to 0 and make neighbor cells become 0. Cells with 3 become 2.
N = 3
0 0 0 0
0 0 0 0
2 0 2 2
5/ To solve board at N = 5. Use board at N = 3. N = 3 N = 5
0 0 0 0        2 2 2 2
0 0 0 0   -> 0 2 0 0
2 0 2 2         0 0 0 0
Note that cells with 0 become 2 and cells with 2 become 0. This is because
  -  cells with 0 is empty cells and it will be populated with bombs at previous step (N = 4).
  -  cells with 2 is bomb cells and it will explode to 0 and make neighbor cells become 0.


*/


public class Bomberman {
    static void explode(int[][] state, int row, int col) {
        state[row][col] = 0;
        if (row > 0) {
            state[row-1][col] = 0;
        }
        if (row < state.length-1) {
            state[row+1][col] = 0;
        }
        if (col > 0) {
            state[row][col-1] = 0;
        }
        if (col < state[row].length-1) {
            state[row][col+1] = 0;
        }
    }
    
    static void printNumbers(int[][] state) {
        for (int i=0; i<state.length; i++) {
            for (int j=0; j<state[i].length; j++) {
                System.out.print(String.format("%3d", state[i][j]));
            }
            System.out.println();
        }
    }
    
    static void print(int[][] state) {
        for (int i=0; i<state.length; i++) {
            for (int j=0; j<state[i].length; j++) {
                if (state[i][j] > 0) {
                    System.out.print("O");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
    
    static void printBombs(int R, int C) {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                System.out.print("O");
            }
            System.out.println();
        }
    }
    
    static void decrement(int[][] state, boolean reset) {
        for (int i=0; i<state.length; i++) {
            for (int j=0; j<state[i].length; j++) {
                if (state[i][j] > 0) {
                    state[i][j]--;
                } else if (reset) {
                    state[i][j] = 3;
                }
            }
        }        
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // # of rows
        int C = in.nextInt(); // # of columns
        long N = in.nextLong(); // # of seconds
        boolean debug = false;
        int[][] state = new int[R][];
        for (int i=0; i<R; i++) {
            state[i] = new int[C];
            String line = in.next().trim();
            for (int j=0; j<C; j++) {
                if (line.charAt(j) == 'O') {
                    // we will initialize to 2 instead of 3 because we will wait 1 sec
                    state[i][j] = 2; // 0 means bomb
                } else {
                    state[i][j] = 0;
                }
            }
        }
        if (debug) {
            System.out.println("Initial 1 seconds");
            printNumbers(state);            
        }
        if (N % 2 == 0) {
          printBombs(R, C);
          return;
        }
        if (N > 10) {
          int newN = -1;
          for (int i=3; i<=N; i+=4) {
            if (i == N) {
              newN = 3;
              break;
            }
          }
          if (newN == -1) {
            for (int i=5; i<=N; i+=4) {
              if (i == N) {
                newN = 3;
                break;
              }
            }
          }
        }
        for (long secs=2; secs<=N; secs++) {
            if (secs % 2 != 0) {
                List<int[]> exploding = new ArrayList<>();
                for (int i=0; i<R; i++) {
                    for (int j=0; j<C; j++) {
                        if (state[i][j] == 1) {
                            exploding.add(new int[] {i, j});
                        }
                    }
                }                
                for (int[] coords : exploding) {
                    explode(state, coords[0], coords[1]);
                }
                if (debug) {
                    System.out.println("After exploding " + secs + " seconds");
                    printNumbers(state);            
                }
                decrement(state, false);
            } else {
                decrement(state, true);                
            }
            
            
            if (debug) {
                System.out.println("After " + secs + " seconds");
                printNumbers(state);
            }
        }
        if (debug) {
            System.out.println("Final " + N + " seconds");            
        }
        print(state);
    }
}
