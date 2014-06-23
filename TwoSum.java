import java.util.*;


// Given an array of integers, find two numbers such that they add up to a specific target number.
//
public class TwoSum {
   public static int[] twoSum(int[] numbers, int target) {
      Map<Integer, Integer> map = new HashMap<Integer, Integer>();
      int[] result = new int[2];

      for (int i = 0; i < numbers.length; i++) {
         if (map.containsKey(numbers[i])) {
            int index = map.get(numbers[i]);
            result[0] = numbers[i];
            result[1] = numbers[index];
            break;
         } else {
            map.put(target - numbers[i], i);
         }
      }

      return result;
   }

   public static void main(String[] args) {
      int[] nums = twoSum(new int[] {2, 7, 11, 15}, 9);
      System.out.println(nums[0] + " + " + nums[1]);
   }
}
