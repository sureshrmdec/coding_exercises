import java.util.*;


public class Subset {
  public static List<List<Integer>> getSubsets(List<Integer> set, int index) {
    List<List<Integer>> allsubsets;
    if (set.size() == index) {
      allsubsets = new ArrayList<List<Integer>>();
      allsubsets.add(new ArrayList<Integer>()); // Empty set
    } else {
      allsubsets = getSubsets(set, index + 1);
      int item = set.get(index);
      ArrayList<List<Integer>> moresubsets = new ArrayList<List<Integer>>();
      for (List<Integer> subset : allsubsets) {
        List<Integer> newsubset = new ArrayList<Integer>(); 
        newsubset.addAll(subset); //
        newsubset.add(item);
        moresubsets.add(newsubset);
      }
      System.out.println("ndx " + index + ", item " + item + " moresubsets " + moresubsets);
      allsubsets.addAll(moresubsets);
    }
    return allsubsets;
  }
  public static void main(String[] args) {
    //List<Integer> set = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> set = Arrays.asList(1, 2, 3);
    List<List<Integer>>  result = getSubsets(set, 0);
    System.out.println(result.size() + ", pow " + Math.pow(2, set.size()));
    System.out.println(result);
  }
}

