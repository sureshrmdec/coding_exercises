import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
Flatland is a country with  cities,  of which have space stations. Its cities () are numbered from  to , where  city is referred to as .

Between each  and  (where ), there exists a bidirectional road  long.

For example, if  and cities  and  have space stations, Flatland would look like this:

hreasy(5).png

For each city, determine its distance to the nearest space station and print the maximum of these distances.

Input Format

The first line consists of two space-separated integers,  and  . 
The second line contains  space-separated integers  denoting the index of each city having a space station. These values are unordered and unique.

Constraints 



Note: There will be at least  city with a space station, and no city has more than one.

Output Format

Print an integer denoting the maximum distance that an astronaut in a Flatland city would need to travel to reach the nearest space station.

Sample Input 0:

5 2
0 4
Input Output 0:

2
Sample Input 1:

6 6
0 1 2 4 3 5
Input Output 1:

0
Explanation

Sample 0:

This sample corresponds to the example given in the problem statement above. The distance to the nearest space station for each city is listed below:

 has distance , as it contains a space station.
 has distance  to the space station in .
 has distance  to the space stations in  and .
 has distance  to the space station in .
 has distance , as it contains a space station.
We then take , and print  as our answer.

Sample 1:

In this sample,  so every city has space station and we print  as our answer.
*/


public class SpaceStations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c[] = new int[m];
        for(int c_i=0; c_i < m; c_i++){
            c[c_i] = in.nextInt();
        }
        
        long max = -1;
        for (int i=0; i<n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j=0; j<m; j++) {
                min = Math.min(Math.abs(c[j] - i), min);
            }
            if (max < 0 || max < min) {
                max = min;
            }
        }
        System.out.println(max);
    }
}

