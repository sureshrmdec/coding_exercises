import java.util.*;


public class IntervalMerger {
   static class Interval  implements Comparable<Interval> {
      int start;
      int end;
      Interval(int s, int e) {
         start = s;
         end = e;
      }
      public int compareTo(Interval other) {
         return start - other.start;
      }
      public String toString() {
         return start + "-" + end;
      }
   }
   //Given a collection of intervals, merge all overlapping intervals.
   public static List<Interval> merge(List<Interval> intervals) {
      if (intervals == null || intervals.size() <= 1) return intervals;

      Collections.sort(intervals);
      List<Interval> result = new ArrayList<Interval>();

      Interval prev = intervals.get(0);
      for (int i = 1; i < intervals.size(); i++) {
         Interval curr = intervals.get(i);

         if (prev.end >= curr.start) {
            Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
            prev = merged;
         } else {
            result.add(prev);
            prev = curr;
         }
      }
      result.add(prev);
      return result;
   }

   //Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
   // Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
   public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
      List<Interval> result = new ArrayList<Interval>();
      for(Interval interval: intervals){
         if (interval.end < newInterval.start){
            result.add(interval);
         } else if(interval.start > newInterval.end){
            result.add(newInterval);
            newInterval = interval;        
         } else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
            newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
         }
      }

      result.add(newInterval); 

      return result;
   }
   public static void main(String[] args) {
      System.out.println(merge(Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18))));
      System.out.println(insert(Arrays.asList(new Interval(1, 3), new Interval(6, 9)), new Interval(2, 5)));
      System.out.println(insert(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7), new Interval(8, 10), new Interval(12, 16)), new Interval(4, 9)));
   }
}

