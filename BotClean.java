/*
   The goal of Artificial Intelligence is to create a rational agent (Artificial Intelligence 1.1.4). An agent gets input from the environment through sensors and acts on the environment with actuators. In this challenge, you will program a simple bot to perform the correct actions based on environmental input.

   Meet the bot MarkZoid. It’s a cleaning bot whose sensor is a head mounted camera and whose actuators are the wheels beneath it. It’s used to clean the floor.

   The bot here is positioned at the top left corner of a 5*5 board. Your task is to move the bot to clean all the dirty cells.

   Input Format

   The first line contains two single spaced integers which indicates the current position of the bot.
   The board is indexed using Matrix Convention
   5 lines follow representing the board. Each cell in the board is represented by any of the following 3 characters. ‘b’ (ascii value 98) indicates the bot’s current position, ‘d’ (ascii value 100) indicates a dirty cell and ‘-‘ (ascii value 45) indicates a clean cell in the board. If the bot is on a dirty cell, the cell will still have ‘d’ on it.

   Output Format

   The output is the action that is taken by the bot in the current step and it can be any of the movements in 4 directions or cleaning the cell in which it is currently located. The valid output strings are LEFT, RIGHT, UP and DOWN or CLEAN. If the bot ever reaches a dirty cell, output CLEAN to clean the dirty cell. Repeat this process until all the cells on the board are cleaned.

   Sample Input

   0 0
   b---d
   -d--d
   --dd-
   --d--
   ----d
   Sample Output

   RIGHT
   Resultant state

   -b--d
   -d--d
   --dd-
   --d--
   ----d
   Task

   Complete the function next_move that takes in 3 parameters posr, posc being the co-ordinates of the bot’s current position and board which indicates the board state to print the bot’s next move.

   The codechecker will keep calling the function next_move till the game is over or you make an invalid move

   Scoring

   Your score is (200 - number of moves the bot makes)/40. CLEAN is considered a move as well.

   Once you submit, your bot will be played on four boards with three of the board configurations unknown to you. The final score will be the sum of the scores obtained in each of the four boards.
   */



import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BotClean {

  /* Head ends here */
  static boolean next_move(int posr, int posc, String[] board){
    //add logic here
    int[] dirtyBlock = null;
    for (int i=0; i<board.length; i++) {
      for (int j=0;j<board.length; j++) {
        if (board[i].charAt(j) == 'd') {
          dirtyBlock = new int[] {i, j};
        }
        if (dirtyBlock != null) {
          break;
        }
      }
      if (dirtyBlock != null) {
        break;
      }
    }
    if (dirtyBlock == null) {
      return true;
    }
    if (posr != dirtyBlock[0] || posc != dirtyBlock[1]) {
      if (posr > dirtyBlock[0]) {
        System.out.println("UP");
      } else if (posr < dirtyBlock[0]) {
        System.out.println("DOWN");
      } else {
        if (posc > dirtyBlock[1]) {
          System.out.println("LEFT");
        } else if (posc < dirtyBlock[1]) {
          System.out.println("RIGHT");
        }
      }
    } else {
      System.out.println("CLEAN");
    }
    return false;
  }

  /* Tail starts here */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean finish = false;
    do {
      int [] pos = new int[2];
      String board[] = new String[5];
      for(int i=0;i<2;i++) pos[i] = in.nextInt();
      for(int i=0;i<5;i++) board[i] = in.next();
      finish = next_move(pos[0], pos[1], board);
    } while (!finish);
  }
}



