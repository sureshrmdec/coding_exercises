import java.util.*;


// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
// Find all unique triplets in the array which gives the sum of zero.
public class ThreeSum {
   public static List<List<Integer>> _threeSum(int[] num) {
      //sort array
      Arrays.sort(num);

      List<List<Integer>> result = new ArrayList<List<Integer>>();
      for(int i=0; i<num.length; i++){
         if(num[i] > 0) break;

         for(int j=i+1; j<num.length; j++){
            if(num[i] + num[j] > 0 && num[j] > 0) break;

            for(int k=j+1; k<num.length; k++){
               if(num[i] + num[j] + num[k] == 0) {
                  List<Integer> each = new ArrayList<Integer>();
                  each.add(num[i]);
                  each.add(num[j]);
                  each.add(num[k]);
                  result.add(each);
               }
            }
         }
      }

      return result;
   }
   static int count = 0;
  public static List<List<Integer>> getSubsets(List<Integer> set, int index) {
    List<List<Integer>> allsubsets;
    if (set.size() == index) {
      allsubsets = new ArrayList<List<Integer>>();
      allsubsets.add(new ArrayList<Integer>()); // Empty set 
      count++;
    } else {
      allsubsets = getSubsets(set, index + 1);
      int item = set.get(index);
      ArrayList<List<Integer>> moresubsets = new ArrayList<List<Integer>>();
      for (List<Integer> subset : allsubsets) {
        List<Integer> newsubset = new ArrayList<Integer>(); 
        newsubset.addAll(subset); //
        newsubset.add(item);
        moresubsets.add(newsubset);
        count++;
      }
      allsubsets.addAll(moresubsets);
    }
    return allsubsets;
  }
   public static List<List<Integer>> threeSum(int[] num) {
      List<Integer> set = new ArrayList<>();
      for (int n : num) {
        set.add(n);
      }
      List<List<Integer>> result = new ArrayList<>();
      List<List<Integer>> subsets = getSubsets(set, 0);
      for (List<Integer> l : subsets) {
        if (l.size() == 3) {
          if (l.get(0) + l.get(1) + l.get(2) == 0) {
            result.add(l);
          }
        }
      }
      return result;
   }

   public static List<List<Integer>> threeSum2(int[] num) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();

      if (num.length < 3)
         return result;

      // sort array
      Arrays.sort(num);

      for (int i = 0; i < num.length - 2; i++) {
         // //avoid duplicate solutions
         if (i == 0 || num[i] > num[i - 1]) {

            int negate = -num[i];

            int start = i + 1;
            int end = num.length - 1;

            while (start < end) {
               //case 1
               if (num[start] + num[end] == negate) {
                  ArrayList<Integer> temp = new ArrayList<Integer>();
                  temp.add(num[i]);
                  temp.add(num[start]);
                  temp.add(num[end]);

                  result.add(temp);
                  start++;
                  end--;
                  //avoid duplicate solutions
                  while (start < end && num[end] == num[end + 1])
                     end--;

                  while (start < end && num[start] == num[start - 1])
                     start++;
                  //case 2
               } else if (num[start] + num[end] < negate) {
                  start++;
                  //case 3
               } else {
                  end--;
               }
            }

         }
      }

      return result;
   }
   public static void main(String[] args) {
      List<List<Integer>> nums = threeSum(new int[] {-1, 0, 1, 2, -1, -4});
      System.out.println(count);
      System.out.println(nums);
      nums = threeSum2(new int[] {-1, 0, 1, 2, -1, -4});
      System.out.println(nums);
   }
}
