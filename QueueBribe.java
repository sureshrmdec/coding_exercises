import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
It's New Year's Day and everyone's in line for the Wonderland rollercoaster ride!

There are  people queued up, and each person wears a sticker indicating their initial position in the queue (i.e.:  with the first number denoting the frontmost position).

Any person in the queue can bribe the person directly in front of them to swap positions. If two people swap positions, they still wear the same sticker denoting their original place in line. One person can bribe at most two other persons.

That is to say, if  and  bribes , the queue will look like this: .

Fascinated by this chaotic queue, you decide you must know the minimum number of bribes that took place to get the queue into its current state!

Note: Each  wears sticker , meaning they were initially the  person in queue.

Input Format

The first line contains an integer, , denoting the number of test cases. 
Each test case is comprised of two lines; the first line has  (an integer indicating the number of people in the queue), and the second line has  space-separated integers describing the final state of the queue.

Constraints



Subtasks

For  score 
For  score 

Output Format

Print an integer denoting the minimum number of bribes needed to get the queue into its final state; print Too chaotic if the state is invalid (requires  to bribe more than  people).

Sample Input

2
5
2 1 5 3 4
5
2 5 1 3 4
Sample Output

3
Too chaotic
Explanation

Sample 1

The initial state:

pic1(1).png

After person  moves one position ahead by bribing person :

pic2.png

Now person  moves another position ahead by bribing person :

pic3.png

And person  moves one position ahead by bribing person :

pic5.png

So the final state is  after three bribing operations.

Sample 2

No person can afford to bribe more than two people, so its not possible to achieve the input state.



2
8
5 1 2 3 7 8 6 4
8
1 2 5 3 7 8 6 4



Too chaotic
7

*/

public class QueueBribe {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
            int q[] = new int[n];
            for(int q_i=0; q_i < n; q_i++){
                q[q_i] = in.nextInt();
            }
            
            int count = 0;
            for (int i=n-1; i>=0; i--) {
                if (q[i] - (i+1) > 2) {
                    System.out.println("Too chaotic");
                    count = 0;
                    break;
                }
                for (int j=Math.max(0, q[i]-2); j<i; j++) {
                    if (q[j] > q[i]) {
                        count++;
                    }
                }
            }
            if (count > 0) {
                System.out.println(count);            
                
            }
            
        }
    }
}
