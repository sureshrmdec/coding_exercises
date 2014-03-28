/*



 plane ticket contains a departure location and a destination. Locations are identified by unique integer numbers.

Given a set of plane tickets used by a traveler

Your task is to

    write a function that reconstructs the traveler’s itinerary and prints to the standard output (stdout) the IDs of each visited location in the order they were visited (one per line)
    please note that no location was visited twice in the traveler's journey

Note that your function will receive the following arguments:

    departure_ids
        an array of integer numbers representing the IDs of the departure locations for all given plane tickets
    destination_ids
        which is an array of integers representing the corresponding IDs of the destinations for all given plane tickets

e.g. departure_ids[i] and destination_ids[i] represent the locations on ticket i

Data constraints

    the length of the arrays above will not exceed 20,000 entries

Example
Input   Output

departure_ids: [1, 4, 5]
destination_ids: [2, 1, 4]
  

5 4 1 2

Explanation

The plane tickets are (1,2) (4,1) (5,4) and the journey was 5 -> 4 -> 1 -> 2



*/


import java.io.*;
import java.util.*;

public class PlaneTickets {
  private static class Entry implements Comparable<Entry> {
    int id;
    Entry prev;
    Entry next;
    Entry(int id) {
      this.id = id;
    }
    public int hashCode() {
      int prime = 31;
      int hash = 1;
      hash = prime * hash + id;
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
      return id == other.id;
    }
    public int compareTo(Entry other) {
      return id - other.id;
    }    
    public String toString() {
      String s = String.valueOf(id);
      Entry e = this.next;
      while (e != null) {
        s += "->" + e.id;
        e = e.next;
      }
      return s;
    }
    public Entry setNext(Entry e) {
      this.next = e;
      e.prev = this;
      return e;
    }
  } 

  public static void get_journey(Integer[] departure_ids, Integer[] destination_ids) {
    Map<Integer, Entry> entries = new HashMap<>();
    Entry last = null;
    for (int i=0; i<departure_ids.length; i++) {
      Entry current = entries.get(departure_ids[i]);
      if (current == null) {
        current = new Entry(departure_ids[i]);
        entries.put(departure_ids[i], current);
      } else {
        last = current;
      }
      Entry dest = entries.get(destination_ids[i]);
      if (dest == null) {
        dest = new Entry(destination_ids[i]);
        entries.put(destination_ids[i], dest);
      } else if (last == null) {
        last = dest;
      }
      current.setNext(dest);
    }
    // 
    Entry head = last;
    while (head.prev != null) {
      head = head.prev;
    }
    boolean first = true;
    while (head != null) {
      if (!first) System.out.print(" ");
      System.out.print(head.id);
      first = false;
      head = head.next;
    }
    System.out.println();
  }
  public static void main(String[] args) throws Exception {
    //get_journey(new Integer[] {1, 4, 5}, new Integer[] {2, 1, 4}); // 5 4 1 2 
    get_journey(new Integer[] {1, 3, 4, 2}, new Integer[] {4, 0, 2, 3}); // 1 4 2 3 0
  }
}
