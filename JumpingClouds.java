import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
Emma is playing a new mobile game involving  clouds numbered from  to . A player initially starts out on cloud , and they must jump to cloud . In each step, she can jump from any cloud  to cloud  or cloud .

There are two types of clouds, ordinary clouds and thunderclouds. The game ends if Emma jumps onto a thundercloud, but if she reaches the last cloud (i.e., ), she wins the game!

jump(1).png

Can you find the minimum number of jumps Emma must make to win the game? It is guaranteed that clouds and  are ordinary-clouds and it is always possible to win the game.

Input Format

The first line contains an integer,  (the total number of clouds). 
The second line contains  space-separated binary integers describing clouds .

If , the  cloud is an ordinary cloud.
If , the  cloud is a thundercloud.
Constraints

Output Format

Print the minimum number of jumps needed to win the game.

Sample Input 0

7
0 0 1 0 0 1 0
Sample Output 0

4
Sample Input 1

6
0 0 0 0 1 0
Sample Output 1

3
*/


public class JumpingClouds {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c[] = new int[n];
        for(int i=0; i < n; i++){
            c[i] = in.nextInt();
        }
        int count = 0;
        for (int i=1; i<n; i++) {
            if (i+1 < n && c[i+1] == 0) {
                i++;
            }
            count++;
        }
        System.out.println(count);
    }
}
