import java.util.*;


public class Permutations {
  public static List<String> getPerms(String s) {
    List<String> permutations = new ArrayList<String>(); 
    if (s == null) { // error case
      return null;
    } else if (s.length() == 0) { // base case
      permutations.add(""); 
      return permutations;
    }
    char first = s.charAt(0); // get the first character
    String remainder = s.substring(1); // remove the first character 
    List<String> words = getPerms(remainder);
    for (String word : words) {
      for (int j = 0; j <= word.length(); j++) {
        permutations.add(insertCharAt(word, first, j)); 
      }
    }
    System.out.println("s " + s + ", first " + first + ", perms " + permutations);
    return permutations; 
  }
  public static String insertCharAt(String word, char c, int i) { 
    String start = word.substring(0, i);
    String end = word.substring(i);
    String str = start + c + end;
    //System.out.println("start '" + start + "', end '" + end + "', c " + c + ", i " + i + ", str '" + str + "'");
    return str;
  }
  public static void main(String[] args) {
    System.out.println(getPerms(args[0]));
  }
}
