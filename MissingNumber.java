import java.util.*;

public class MissingNumber {
  public static void find_missing_number(Integer[] v) {
    BitSet found = new BitSet(v.length);
    for (int n : v) {
      found.set(n);
    }
    for (int i=1; i<=v.length+1; i++) {
      if (!found.get(i)) System.out.println(i);
    }
  }
  public static void main(String[] args) {
    find_missing_number(new Integer[] {5, 4, 1, 2});
  }

}

