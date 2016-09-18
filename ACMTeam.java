/**
You are given a list of  people who are attending ACM-ICPC World Finals. Each of them are either well versed in a topic or they are not. Find out the maximum number of topics a 2-person team can know. And also find out how many teams can know that maximum number of topics.

Note Suppose a, b, and c are three different people, then (a,b) and (b,c) are counted as two different teams.

Input Format

The first line contains two integers,  and , separated by a single space, where  represents the number of people, and  represents the number of topics.  lines follow.
Each line contains a binary string of length . If the th line's th character is , then the th person knows the th topic; otherwise, he doesn't know the topic.

Constraints 
 

Output Format

On the first line, print the maximum number of topics a 2-person team can know. 
On the second line, print the number of 2-person teams that can know the maximum number of topics.

Sample Input

4 5
10101
11100
11010
00101
Sample Output

5
2
Explanation

(1, 3) and (3, 4) know all the 5 topics. So the maximal topics a 2-person team knows is 5, and only 2 teams can achieve this.
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ACMTeam {
    static class Record {
        int firstPerson;
        int secondPerson;
        int score;
    
        Record(int first, int second, int score) {
            firstPerson = first;
            secondPerson = second;
            this.score = score;
        }
        
        @Override
        public int hashCode() {
            return firstPerson ^ secondPerson;
        }   
        @Override
        public boolean equals(Object o) {
            if (o == null || o instanceof Record == false) return false;
            Record other = (Record) o;
            return (firstPerson == other.firstPerson && secondPerson == other.secondPerson) ||
                (firstPerson == other.secondPerson && secondPerson == other.firstPerson);
        }   
        @Override
        public String toString() {
            return firstPerson + " & " + secondPerson + " = " + score;
        }   
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        BitSet  topics[] = new BitSet [n];
        boolean debug = false;
        for(int i=0; i < n; i++){
            String line = in.next();
            topics[i] = new BitSet(m);
            for (int j=0; j<m; j++) {
                topics[i].set(j, line.charAt(j) == '1');
            }
        }
        int max = Integer.MIN_VALUE;
        Set<Record> records = new HashSet<>();
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (j == i) continue;
                BitSet first = topics[i];
                BitSet second = topics[j];
                BitSet combo = new BitSet(n);
                combo.or(first);
                combo.or(second);
                int score = combo.cardinality();
                if (score >= max) {
                    max = score;
                    Record rec = new Record(i, j, score);
                    records.add(rec);
                    if (debug) System.out.println("Adding new max " + max + ", " + rec + ", first " + first + ", second " + second + ", combo " + combo);
                }
            }
        }
        int teams = 0;
        for (Record rec : records) {
            if (rec.score == max) {
                teams++;
                if (debug) System.out.println("Found max " + max + ", teams " + teams + " >>>> " + rec);
            }
        }
        System.out.println(max);
        System.out.println(teams);
    }
}

