import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class SorensenDiceIndex {
   public static double score(String first, String second) {

      // Create two sets of character bigrams, one for each string.
      Set<String> s1 = splitIntoBigrams(first);
      Set<String> s2 = splitIntoBigrams(second);

      // Get the number of elements in each set.
      int n1 = s1.size();
      int n2 = s2.size();

      // Find the intersection, and get the number of elements in that set.
      s1.retainAll(s2);
      int nt = s1.size();


      // The coefficient is:
      // 
      //        2 ∙ | s1 ⋂ s2 |
      // D = ----------------------
      //        | s1 | + | s2 |
      // 
      return (2.0 * (double)nt) / ((double)(n1 + n2));

   }


   private static Set<String> splitIntoBigrams(String s) {
      ArrayList<String> bigrams = new ArrayList<String>();

      if (s.length() < 2) {
         bigrams.add(s);
      }
      else {
         for (int i = 1; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(i-1));
            sb.append(s.charAt(i));
            bigrams.add(sb.toString());
         }
      }
      return new TreeSet<String>(bigrams);
   }
   public static void main(String[] args) {
      String[][] pairs = {
        {"CONSERVATIONALISTS", "CONVERSATIONALISTS"},
        {"WHIRLED", "WORLD"},
        {"COMPLEMENT", "COMPLIMENT"},
        {"BAZAAR", "BIZARRE"},
        {"ACCESSARY", "ACCESSORY"},
        {"ALGORITHMS ARE FUN", "LOGARITHMS ARE NOT"},
        {"ASSISTANCE", "ASSISTANTS"},
        {"ALL TOGETHER", "ALTOGETHER"},
        {"IDENTICAL STRINGS", "IDENTICAL STRINGS"}
    };
      for (String[] pair : pairs) {
         System.out.println(pair[0] + " and " + pair[1]);
         System.out.println(score(pair[0], pair[1]));
      }
   }
}

