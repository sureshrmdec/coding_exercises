/*



   A professional receives multiple service requests in a day, but not all of them can be fulfilled due to time conflicts.

   You are given a list of service requests. Each of them is defined by two timestamps representing the start time and the completion time.

   Your task is to

   write a function that prints to the standard output (stdout) the maximum number of service requests the professional can fulfill in one day without overlapping

   Note that your function will receive the following arguments:

   req_start
   array of integer numbers representing the timestamp when each service must begin
   req_end
   array of integer numbers representing the corresponding timestamp when each service must be completed

   e.g. service i must begin at req_start[i] and finish at req_end[i]

   Data constraints

   the number of requests will not exceed 50,001

   Example
   Input   Output

req_start: [4, 1, 5, 6]
req_end: [5, 4, 7, 8]


3

Explanation

The service requests above have the following start and completion times: (4,5), (1, 4), (5, 7), (6, 8).

Our professional can fulfill only two service requests without any overlaps. He can choose one of the following possibilities:

(1, 4), (4, 5), (5, 7)
(1, 4), (4, 5), (6, 8)


*/


import java.io.*;
import java.util.*;

public class ScheduleRequests {
  private static class Entry implements Comparable<Entry> {
    int start;
    int finish;
    Entry(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
    public int hashCode() {
      int prime = 31;
      int hash = 1;
      hash = prime * hash + start;
      hash = prime * hash + finish;
      return hash;
    }
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Entry other = (Entry) obj;
      return start == other.start && finish == other.finish;
    }
    public int compareTo(Entry other) {
      int cmp = start - other.start;
      if (cmp == 0) {
        cmp = finish - other.finish;
      }
      return cmp;
    }    
    public String toString() {
      return start + "=>" + finish;
    }
  } 
  public static void schedule_requests(Integer[] req_start, Integer[] req_end) {
    List<Entry> entries = new ArrayList<Entry>();
    for (int i=0; i<req_start.length; i++) {
      entries.add(new Entry(req_start[i], req_end[i]));
    }
    Collections.sort(entries);
    //System.err.println("Entries " + entries);
    List<Set<Entry>> overlapping = new ArrayList<Set<Entry>>();
    boolean foundDuplicate = false;
    for (int i=entries.size()-1; i>=0; i--) {
      foundDuplicate = false;
      Entry current = entries.get(i);
      Entry prev = i > 0 ? entries.get(i-1) : null;
      Set<Entry> set = new HashSet<Entry>();
      set.add(current);
      while (prev != null && current.start < prev.finish) {
        set.add(prev);
        i--;
        prev = entries.get(i);
        foundDuplicate = true;
        //System.err.println("---dupicate " + prev + ", i " + i + ", set " + set);
      }
      if (foundDuplicate) {
        i++;
      }
      overlapping.add(set);
    }
    //for (Set<Entry> e : overlapping) {
    //  System.err.println("---next " + e);
    //}
    System.out.println(overlapping.size());
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
    //BufferedReader in = new BufferedReader(new FileReader(args[0]));
    //StringBuilder sb = new StringBuilder();
    //Integer[] requests = parse(in.readLine());
    //Integer[] pro_start = parse(in.readLine());
    //Integer[] pro_end = parse(in.readLine());
    //
    //in.close();
    schedule_requests(new Integer[] {4, 1, 5, 6}, new Integer[] {5, 4, 7, 8});
    //schedule_requests(new Integer[] {}, new Integer[] {});
    //schedule_requests(new Integer[] {10, 45, 76, 31, 50, 68}, new Integer[] {15, 66, 157, 137, 85, 112});
  }
}
