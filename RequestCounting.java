/*

   Request counting

   A large number of customers requested a cleaning service on Redbeacon. Each of them provided a moment in time when the service needs to be completed.

   Every professional that can provide cleaning services is available only within a certain time frame.

   Given the times for the requested services and the available time frame for each professional

   Your task is to

   write a function that computes the number of requests that each professional can access within their time frame (one per line)

   Note that your function will receive the following arguments:

   requests
   array of integer numbers representing the timestamp when the request needs to be completed
   pro_start
   array of integer numbers representing the timestamp for each professional when his availability time frame starts
   pro_end
   array of integer numbers representing the corresponding timestamp for each professional when his availability time frame ends

   e.g. the availability time frame for professional i is given by pro_start[i] and pro_end[i]

   Data constraints

   the number of requests and professionals will not exceed 50,000

   Example
   Input   Output

requests: [6, 5, 2, 3]
pro_start: [1, 4]
pro_end: [5, 6]


3
2

Explanation

The first professional is available between timestamps 1 and 5. He could access requests from timestamp 2, 3 and 5. In total 3 requests.


*/


import java.io.*;
import java.util.*;

public class RequestCounting {
  public static void count_requests(Integer[] requests, Integer[] pro_start, Integer[] pro_end) {
    count_requests(toInt(requests), toInt(pro_start), toInt(pro_end));
  }

  public static void count_requests(int[] requests, int[] pro_start, int[] pro_end) {
    Arrays.sort(requests);
    for (int i=0; i<pro_start.length; i++) {
      int from = binaryEqGt(pro_start[i], requests);
      int to = binaryEqLt(pro_end[i], requests);
      int count = from != Integer.MIN_VALUE && to != Integer.MIN_VALUE ? to - from + 1: 0;
      System.out.println(count);  
    }
  }

  private static int binaryEqGt(int key, int[] a) {
    if (key < a[0]) {
      return 0;
    }   
    if (key > a[a.length-1]) {
      return Integer.MIN_VALUE; 
    }   

    int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if      (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else {
        while (mid > 0 && a[mid-1] == key) {
          mid--;
        }   
        return mid;
      } 
    }   
    return lo; 
  }
    
  private static int binaryEqLt(int key, int[] a) {
    if (key < a[0]) {
      return Integer.MIN_VALUE;
    }   
    if (key > a[a.length-1]) {
      return a.length-1;
    }   

    int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if      (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else {
        while (mid < a.length-1 && a[mid+1] == key) {
          mid++;
        }   
        return mid;
      }   
    }   
    return hi; 
  }

  private static int[] toInt(Integer[] arr) {
    int[] iarr = new int[arr.length];
    for (int i=0; i<arr.length; i++) {
      iarr[i] = arr[i];
    }
    return iarr;
  }



  private static Integer[] parse(String line) {
    String[] toks = line.split("\\D+");
    List<Integer>numbers = new ArrayList<Integer>();
    for (int i=0; i<toks.length; i++) {
      String tok = toks[i].trim();
      if (tok.length() > 0) {
        numbers.add(Integer.parseInt(tok));
      }
    }
    return numbers.toArray(new Integer[numbers.size()]);
  }
  public static void main(String[] args) throws Exception {
    //count_requests(new int[] {6, 5, 2, 3}, new int[] {1, 4}, new int[] {5, 6});
    //count_requests(new int[] {}, new int[] {}, new int[] {});
    //count_requests(new int[] {50, 1, 50, 1, 50, 23}, new int[] {-1, 50, 54, 24, 6, 45}, new int[] {49, 50, 54, 26, 6, 55});
    //count_requests(new int[] {50, 1, 50, 1, 50, 23}, new int[] {50}, new int[] {50});
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    StringBuilder sb = new StringBuilder();
    Integer[] requests = parse(in.readLine());
    Integer[] pro_start = parse(in.readLine());
    Integer[] pro_end = parse(in.readLine());
    //
    in.close();
    count_requests(requests, pro_start, pro_end);
  }
}
