import java.util.*;


public class Subset {
  public static List<List<Integer>> subsets(List<Integer> set) {
    int max = 1 << set.size(); //(int) Math.pow(2, set.size());
    List<List<Integer>> subsets = new ArrayList<>();
    for (int i=0; i<max; i++) {
      List<Integer> subset = new ArrayList<>();
      for (int j=0; j<set.size(); j++) {
        if ((i & (1L << j)) != 0) {
          subset.add(set.get(j));
        }   
      }   
      subsets.add(subset);
    } 
    return subsets;
  }
  public static List<List<Integer>> getSubsets(List<Integer> set, int index) {
    List<List<Integer>> allsubsets;
    if (set.size() == index) { // last index
      allsubsets = new ArrayList<List<Integer>>();
      allsubsets.add(new ArrayList<Integer>()); // Empty set 
    } else {
      allsubsets = getSubsets(set, index + 1);
      int item = set.get(index);
      ArrayList<List<Integer>> moresubsets = new ArrayList<List<Integer>>();
      for (List<Integer> subset : allsubsets) {
        List<Integer> newsubset = new ArrayList<Integer>(); 
        newsubset.add(item);
        newsubset.addAll(subset); //
        moresubsets.add(newsubset);
      }
      //System.out.println("ndx " + index + ", item " + item + " moresubsets " + moresubsets);
      allsubsets.addAll(moresubsets);
    }
    return allsubsets;
  }
  public static void main(String[] args) {
    //List<Integer> set = Arrays.asList(1, 2, 3, 4, 5, 6);
    //List<Integer> set = Arrays.asList(1, 2, 3, 4);
    List<Integer> set = Arrays.asList(3, 2, 1);
    List<List<Integer>>  result = getSubsets(set, 0);
    System.out.println(result);
    result = subsets(set);
    System.out.println(result);
  }
}

