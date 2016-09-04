/*
Given M busy-time slots of the team members in the Kindle team, can you print all the available time slots when all of them can schedule a meeting for a duration of K minutes.
The event time will be of the form HH MM (where 0 <= HH <= 23 and 0 <= MM <= 59). K will be in the form minutes.
 
Input Format:
 
M K [M number of busy time slots , K is the duration in minutes]
This is followed by M lines with 4 numbers on each line.
Each line will be of the form StartHH StartMM EndHH EndMM  [Eg: 9am-11am time slot will be given as 9 00 11 00]
An event time slot is of the form [Start Time, End Time) which means the start time is inclusive but not the end time;
So, an event of the form 10 00  11 00 => implies that the meeting starts at 10:00 and ends at 11:00. Hence, another meeting can start at 11:00.
 
Sample Input:
5 120
16 00 17 00
10 30 14 30
20 45 22 15
10 00 13 15
09 00 11 00
 
Sample Output:
00 00 09 00
17 00 20 45
 
Sample Input:
8 60
08 00 10 15
22 00 23 15
17 00 19 00
07 00 09 45
09 00 13 00
16 00 17 45
12 00 13 30
11 30 12 30
 
Sample Output:
00 00 07 00
13 30 16 00
19 00 22 00
 
Constraints :
1 <= M <= 100
 
Note: 24 00 has to be presented as 00 00.
*/


import java.io.*;
import java.util.*;

public class MeetingSchedule {
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

  int M;
  int K;
  Entry[] busyEntries;

  public MeetingSchedule(File file) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader(file));
    int[] nums = parseInt(in.readLine());
    M = nums[0];
    K = nums[1];
    busyEntries = new Entry[M];
    for (int i=0; i<M; i++) {
      int[] arr = parseInt(in.readLine());
      busyEntries[i] = new Entry(arr[0] * 60 + arr[1], arr[2] * 60 + arr[3]);
    }
    Arrays.sort(busyEntries);
  }


  public void printFreeSlots() {
    //System.out.println("Busy Entries " + Arrays.toString(busyEntries));
    List<List<Entry>> overlapping = new ArrayList<List<Entry>>();
    boolean foundDuplicate = false;
    for (int i=busyEntries.length-1; i>=0; i--) {
      foundDuplicate = false;
      Entry current = busyEntries[i];
      Entry prev = i > 0 ? busyEntries[i-1] : null;
      List<Entry> set = new ArrayList<Entry>();
      set.add(current);
      while (prev != null && current.start < prev.finish) {
        if (!set.contains(prev)) {
          set.add(prev);
        }
        i--;
        prev = i >= 0 ? busyEntries[i] : null;
        foundDuplicate = true;
        //System.out.println("---dupicate " + prev + ", i " + i + ", set " + set);
      }
      if (foundDuplicate) {
        i++;
      }
      Collections.sort(set);
      overlapping.add(set);
    }
    //
    Collections.sort(overlapping);
    Entry prev = new Entry(0, 0);
    for (List<Entry> e : overlapping) {
      System.out.println("Busy Entries " + e);
    }
    for (List<Entry> e : overlapping) {
    //System.out.println("Busy Entries " + Arrays.toString(busyEntries));
      print(prev, e.get(0));
      prev = e.get(e.size()-1);
    }
  }

  private void print(Entry from, Entry to) {
    int diff = to.finish - from.start;
    if (diff > K) {
      int startHH = from.start / 60;
      int startMM = from.start - from.start / 60;
      int endHH = to.finish / 60;
      int endMM = to.finish - to.finish / 60;
      System.out.println(String.format("%02d %02d %02d %02d", startHH, startMM, endHH, endMM));
    }
  }

  //
  private static int[] parseInt(String line) {
    String[] toks = line.split("\\D+");
    List<Integer> list = new ArrayList<Integer>();
    for (int i=0; i<toks.length; i++) {
      String tok = toks[i].trim();
      if (tok.length() > 0) {
        list.add(Integer.parseInt(tok));
      }
    }
    int[] iarr = new int[list.size()];
    for (int i=0; i<list.size(); i++) {
      iarr[i] = list.get(i);
    }
    return iarr;
  }

  public static void main(String[] args) throws Exception {
    MeetingSchedule ms = new MeetingSchedule(new File(args[0]));
    ms.printFreeSlots();
  }
}
