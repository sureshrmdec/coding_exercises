import java.util.*;


public class ConsecutiveSequence {
   public static int longestConsecutive(int[] num) {
    if (num.length == 0) {
      return -1;
    } else if (num.length == 1) {
      return num[0];
    }
    System.out.println(Arrays.toString(num));
    int prev = num[0];
    int maxCount = 1;
    int consec = 1;
    int max = num[0];
    for (int i=1; i<num.length; i++) {
      if (prev == num[i]) {
        consec++;
        if (consec > maxCount) {
System.out.println(" <i " + i + ", prev " + prev + ", max " + max + ", count " + consec + ", num[i] " + num[i]);
          max = prev;
          maxCount = consec;
        }
      } else {
        //System.out.println("      i " + i + ", prev " + prev + ", max " + max + ", count " + consec + ", num[i] " + num[i]);
        consec = 1;
        prev = num[i];
      }
    }
    return max;
  }
   public static int _longestConsecutive(int[] num) {
      Set<Integer> set = new HashSet<Integer>();
      int max = 1;

      for (int e : num)
         set.add(e);

      for (int e : num) {
         int left = e - 1;
         int right = e + 1;
         int count = 1;

         while (set.contains(left)) {
            count++;
            set.remove(left);
            left--;
         }

         while (set.contains(right)) {
            count++;
            set.remove(right);
            right++;
         }

         max = Math.max(count, max);
      }

      return max;
   }
   public static void main(String[] args) {
    int n = longestConsecutive(new int[] {1, 2, 2, 3, 3, 5, 4, 4, 3, 6, 5, 5, 6, 6, 6, 6});
    System.out.println(n);
    n = _longestConsecutive(new int[] {1, 2, 2, 3, 3, 5, 4, 4, 3, 6, 5, 5, 6, 6, 6, 6});
    System.out.println(n);
  }
}
