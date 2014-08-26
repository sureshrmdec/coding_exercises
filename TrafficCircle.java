//https://code.google.com/codejam/contest/2437491/dashboard
/*
   Problem

   Before graduating from Awesome Programmer University, students traditionally perform certain "graduation requirements". One of these is driving around a traffic circle backwards. For most people, this is crazy enough, but as an extra challenge, you want to see if you can go backwards around the traffic circle multiple times without stopping.

   The traffic circle consists of N intersections, spaced evenly around the circle. A car would normally enter the traffic circle at one intersection, and then every second, it will move to the next counter-clockwise intersection, until eventually it reaches its destination and leaves.


   You have been watching cars enter and leave the traffic circle for X seconds. For each car, you record the time it enters the circle, as well as the intersections it enters and leaves at. All cars are moving counter-clockwise at the rate of 1 intersection per second. Each car you watched exited the circle before coming back to the intersection it entered at. There are multiple lanes on the traffic circle, so multiple cars can occupy the same position at the same time.

   If you had planned it just right, how long could you have driven clockwise in the traffic circle during this time? You must enter the circle at some integer time >= 0, leave at time <= X, and once you leave, you are not allowed to come back. When in the traffic circle, you must travel clockwise at the rate of 1 intersection per second. You want to play it safe (well, as safe as driving backwards on a traffic circle can be), so you must never touch or pass by another car. In particular, you cannot leave the circle at an intersection at which another car is entering at the same moment, and you cannot enter the circle at an intersection at which another car is leaving at the same moment. You can choose when and where to enter and leave the circle.

   Input

   The first line of the input gives the number of test cases, T. T test cases follow. The first line of any test case describes the number C of cars you observed. The second line contains two integers, X and N — the time (in seconds) for which you observed the circle, and the number of intersections on the circle. Next C lines describe the cars you have seen. Each of those lines contains three integers si, ei and ti — the intersection at which the car entered the circle, the intersection on which it left and the time at which it entered. The intersections are numbered from 1 to N, counterclockwise (that is, the intersection number 2 is the next intersection counterclockwise from number 1).

   Output

   For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is the maximum number of seconds you can travel on the circle. Note that y can be zero both in the case where you cannot enter the circle at all and in the case when you can enter it, but can't travel even one intersection.

   Remember that you are required to enter the circle at a time expressed as an integer number of seconds — you must enter at an integer time, and thus arrive at each intersection at an integer time.

   Limits

   1 ≤ T ≤ 100
   1 ≤ si, ei ≤ N
   si ≠ ei 
   0 ≤ ti
   Each observed car leaves the circle at time X or earlier.
   Small dataset

   3 ≤ N ≤ 10
   1 ≤ X ≤ 10
   0 ≤ C ≤ 10
   Large dataset

   3 ≤ N ≤ 1010
   1 ≤ X ≤ 1010
   0 ≤ C ≤ 1000
   Sample


   Input 

   Output 

   5
   1
   3 4
   1 4 0
   6
   3 5
   5 2 0
   5 1 2
   1 3 0
   1 2 2
   2 3 0
   3 4 0
   3
   2 3
   1 3 0
   2 1 0
   3 2 0
   0
   6 4
   1
   2 3
   1 3 0
   Case #1: 1
   Case #2: 2
   Case #3: 0
Case #4: 6
Case #5: 0
In the first sample case, we have one car, going as in the picture in the statement. There are a number of ways allowing us to travel backwards for one second — for instance, we can enter at intersection 1 at time 1 (we can't enter at time zero, because the other car is there), and travel to intersection 4 (we can't go on to intersection 3, as we would pass the other car which will be going from 3 to 4). Another option is to enter at intersection 4 at time 0, and travel to intersection 3 (and then exit).


In the second sample case, we can travel for two seconds by entering at intersection 5 at time 1, and traveling backwards to intersection 3. In the third sample case, we can't even enter the circle - there are cars at all intersections at every full second. In the fourth case there are no cars, so we can just enter the circle at any polong at time 0 and travel round and round till time 6. In the fifth case we can enter the circle, but since there are only three intersections, we will always collide with the other car if we try to move to the next one.

Note: Driving against the direction of the traffic on a traffic circle is typically not a wise thing to do and may cause harm to you or other people. Google (and Google Code Jam in particular) encourages you not to try this.
*
*/


import java.io.*;
import java.util.*;

public class TrafficCircle {
  private static class LongWrapper {
    private long value;
    private void increment() {
      ++value;
    }
    public String toString() {
      return String.valueOf(value);
    }
  }

  private final int C; // number of cars you observe 
  private final long X; // the time (in seconds) for which you observed the circle
  private final long N;  // the number of intersections on the circle 
  private final List<List<LongWrapper>> numberOfCarsAtIntersectionByTime = new ArrayList<List<LongWrapper>>();

  private static long next(long x, long max) {
    x++;
    if (x >= max) {
      x = x % max;
    }   
    return x;
  }


  private static long add(long x, long n, long max) {
    x = x + n;
    if (x >= max) {
      x = x % max;
    }   
    return x;
  }


  private static long prev(long x, long max) {
    x--;
    if (x < 0) {
      x = max - 1;
    }                                                                                                                return x;
  }

  public static TrafficCircle create(BufferedReader in, boolean debug) throws Exception {
    int C = Integer.parseInt(in.readLine().trim()); // number of cars you observe 
    String line = in.readLine().trim();
    String[] toks = line.split("\\s");
    long X = Long.parseLong(toks[0].trim()); // the time (in seconds) for which you observed the circle
    long N = Long.parseLong(toks[1].trim()); // the number of intersections on the circle
    long[] s = new long[C];
    long[] e = new long[C];
    long[] t = new long[C];
    for (int j=0; j<C; j++) {
      toks = in.readLine().trim().split("\\s");
      // we will store intersection numbers as 0 -- N-1 as opposed to 1..N
      s[j] = Long.parseLong(toks[0].trim()) - 1; // the intersection at which the car entered the circle.
      // The intersections are numbered from 1 to N, counterclockwise (that is, the intersection number 2 is the next intersection counterclockwise from number 1).
      e[j] = Long.parseLong(toks[1].trim()) - 1; // the intersection on which it left
      t[j] = Long.parseLong(toks[2].trim()); // the time at which it entered. 
    }
    //
    long maxLimit = X * N;
    final List<List<LongWrapper>> numberOfCarsAtIntersectionByTime = new ArrayList<List<LongWrapper>>();
    for (long i=0; i<maxLimit; i++) {
      List<LongWrapper> intersectionList = new ArrayList<LongWrapper>();
      numberOfCarsAtIntersectionByTime.add(intersectionList);
      for (long j=0; j<N; j++) {
        intersectionList.add(new LongWrapper());
      }
    }

    TrafficCircle tc = new TrafficCircle (C, X, N, numberOfCarsAtIntersectionByTime);
    for (int i=0; i<C; i++) { // for all cars 
      long limit = s[i] < e[i] ? e[i]-s[i] : e[i]+N-s[i];
      for (int j=0; j<=limit; j++) {
        long x = add(s[i], j, N);
        List<LongWrapper> intersections = numberOfCarsAtIntersectionByTime.get(i);
        intersections.get(x).increment();
        if (j < limit) {
          long xx = add(s[i], j+1, N); // opposite side 
          intersections.get(xx).increment();
        }
      }
    }
    if (debug) {
      System.out.println("\tobserve time " + X + ", # of intersections " + N + ", number of cars " + s.length + "\n");
      for (int i=0; i<C; i++) {
        System.out.println("\t\t@" + t[i] + ":" + s[i] + "->" + e[i]);
      }
    }
    return new TrafficCircle (C, X, N, numberOfCarsAtIntersectionByTime); 
  }

  public TrafficCircle (final int C, final long X, final long N, final List<List<LongWrapper>> numberOfCarsAtIntersectionByTime) {
    this.C = C;
    this.X = X;
    this.N = N;
    this.numberOfCarsAtIntersectionByTime  = numberOfCarsAtIntersectionByTime;
  }

  public String getProblemDescription() {
    StringBuilder sb = new StringBuilder("observe time " + X + ", # of intersections " + N + ", number of cars " + C + "\n");
    long timeSize = numberOfCarsAtIntersectionByTime.size();
    for (long i=0;i<timeSize; i++) {
      long[] time = numberOfCarsAtIntersectionByTime.get(i);
      long intersectionSize = time.length;
      for (long j=0; j<intersectionSize; j++) {
        sb.append("\n\t@" + i + ", intersection " + j + " has " + time[j] + " cars");
      }
    }
    return sb.toString();
  }

  private final long maxSecondsTravelOnCircle() {
    return 0;
  }



  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    long T = Long.parseLong(in.readLine().trim()); // number of test cases
    long count = 1;
    for (long i=0; i<T; i++) {
      if (count > 5) break;
      TrafficCircle tc = TrafficCircle.create(in, true);

      //if (count == 26 || count == 28) {
      //  System.out.println("Case #" + count + ": " + tc.getProblemDescription());
      //}
      System.out.println("Case #" + count + ": " + tc.maxSecondsTravelOnCircle());
      count++;
    }   
    in.close();
  }
}
