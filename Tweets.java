/*
Tweets per second

Japan Castle in the Sky airing broke a Twitter record on August 3, 2013. At one point during the classic anime movie showing, people submitted 143,199 tweets per second.

This particular spike was about 25 times greater than Twitter’s steady state.

Given an array of integers representing the number of tweets recorded every second and an integer value K

Your task is to

    write a function that prints to the standard output (stdout) the highest number of tweets recorded between each second in the array and the past K seconds before it

Note that your function will receive the following arguments:

    tps
        which is an array of integers representing the number of tweets recorded every second
    k
        which is the integer number mentioned above

Data constraints

    the length of the array above will not exceed 500,000 numbers

Efficiency constraints

    your function is expected to print the requested result and return in less than 2 seconds

Example
Input   Output

tps: 6, 9, 4, 7, 4, 1

k: 3
  

6
9
9
9
7
7
*/



import java.math.*;

class Tweets {
  public static void tweets_per_second(Integer[] tps, Integer k) {
    for (int i=0; i<tps.length; i++) {
      int max = tps[i];
      for (int j=Math.max(i-k+1, 0); j<i; j++) {
        max = Math.max(max, tps[j]);
      }
      System.out.println(max);
    }
  }
  public static void main(String[] args) throws Exception {
      tweets_per_second(new Integer[] {6, 9, 4, 7, 4, 1}, 3);
      /*
      BufferedReader in = new BufferedReader(new FileReader("type.dat")); 
      String[] usernames = in.readLine().replaceAll("\"", "").split(",");
      String[] queries = in.readLine().replaceAll("\"", "").split(",");
      typeahead(usernames, queries);
      */
  }
}
