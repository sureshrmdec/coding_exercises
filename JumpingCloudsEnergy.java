import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**

Aerith is playing a cloud game! In this game, there are  clouds numbered sequentially from  to . Each cloud is either an ordinary cloud or a thundercloud.

Aerith starts out on cloud  with energy level . She can use  unit of energy to make a jump of size  to cloud  until she gets back to cloud . If Aerith lands on a thundercloud, her energy () decreases by additional units. The game ends when Aerith lands back on cloud .

Given the values of , , and the configuration of the clouds, can you determine the final value of  after the game ends?

Note: Recall that  refers to the modulo operation.

Input Format

The first line contains two space-separated integers,  (the number of clouds) and  (the jump distance), respectively. 
The second line contains  space-separated integers describing the respective values of clouds . Each cloud is described as follows:

If , then cloud  is an ordinary cloud.
If , then cloud  is a thundercloud.
Constraints

Output Format

Print the final value of  on a new line.

Sample Input

8 2
0 0 1 0 0 1 1 0
Sample Output

92
Explanation

In the diagram below, red clouds are thunderclouds and purple clouds are ordinary clouds:

game board

Observe that our thunderclouds are the clouds numbered , , and . Aerith makes the following sequence of moves:

Move: , Energy: .
Move: , Energy: .
Move: , Energy: .
Move: , Energy: .
Thus, we print  as our answer.




2 1
0 1


96



8 2
0 0 1 0 0 1 1 0

92



16 2
0 0 1 0 1 1 1 1 1 1 1 0 0 0 1 1

-3 -3 -3 -3 -1 -3 -3 
80



19 19
1 1 0 1 0 1 0 1 0 1 0 1 1 0 1 1 1 1 1

97

*/


public class JumpingCloudsEnergy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int c[] = new int[n+1];
        for(int c_i=0; c_i < n; c_i++){
            c[c_i] = in.nextInt();
        }
        c[n] = c[0];
        n++;
        int E = 100;
        for (int i=0; i<n; i++) {
            if (i+k < n && c[i+k] == 0) {
                E--;
                i = i + k - 1;
            } else if (i+k < n && c[i+k] == 1) {
                E -= 3;
                i = i + k - 1;
            } else if (i+1 < n && c[i+1] == 1) {
                E -= 3;
                i++;
            } else if (i+1 < n && c[i+1] == 0) {
                E--;
                i++;
            }
        }   
        System.out.println(E);
    }
}
