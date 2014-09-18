import java.io.*;
import java.util.*;
public class StringUtils {
  public static class Option<T> {
    private final T value;
    private Option(T value) {
      this.value = value;
    }
    public static <T> Option<T> Some(T value) {
      return new Option(value);
    }
    public static <T> Option<T> None() {
      return new Option(null);
    }
    public String toString() {
      return "" + value;
    }
  }
  public static Option<Character> getNonrepeatingCharacter(String s) {
    Map<Character, Integer> counts = new TreeMap<>();
    for (int i=0; i<s.length(); i++) {
      char ch = s.charAt(i);
      Integer count = counts.get(ch);
      if (count == null) {
        count = 1;
      } else {
        count++;
      }
      counts.put(ch, count);
    }
    for (Map.Entry<Character, Integer> e : counts.entrySet()) {
      if (e.getValue() == 1) {
        return Option.Some(e.getKey());
      }
    }
    return Option.None();
  }

  public static void main(String[] args) {
    Option<Character> oc = getNonrepeatingCharacter("aaab");
    System.out.println(oc);
    oc = getNonrepeatingCharacter("ababcd");
    System.out.println(oc);
  }
}

