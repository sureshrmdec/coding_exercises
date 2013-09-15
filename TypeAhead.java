import java.util.*;
import java.io.*;

class TypeAhead {
  public static void typeahead(String[] usernames, String[] queries) {
    Map<String, String[]> lookup = new HashMap<String, String[]>();
    for (String u : usernames) {
      String lu = u.toLowerCase();
      for (int i=0; i<u.length(); i++) {
        String prefix = lu.substring(0, i+1);
        String[] val = lookup.get(prefix);
        if (val == null) {
          val = new String[] {u, lu};
          lookup.put(prefix, val);
        } else {
          if (val[1].compareTo(lu) > 0) {
            val[0] = u;
            val[1] = lu;
          }
        }
      }
    }
    for (String q : queries) {
      String[] matched = lookup.get(q.toLowerCase());
      if (matched == null) {
        System.out.println("-1");
      } else {
        System.out.println(matched[0]);
      }
    }
  }
  public static void main(String[] args) throws Exception {
      /*
      typeahead(new String[] {"james", "jBlank"}, new String[] {"j", "jm", "jbl", "JB"});
      */
      BufferedReader in = new BufferedReader(new FileReader("type.dat")); 
      String[] usernames = in.readLine().replaceAll("\"", "").split(",");
      String[] queries = in.readLine().replaceAll("\"", "").split(",");
      typeahead(usernames, queries);
  }
}
