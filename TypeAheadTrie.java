import java.util.*;
import java.io.*;

class TypeAheadTrie {
  public static void typeahead(String[] usernames, String[] queries) {
    long started = System.currentTimeMillis();
    Dictionary dic = new Dictionary();
    for (String u : usernames) {
      dic.insert(u);
    }
    System.out.println("inserted in " + (System.currentTimeMillis()-started));
    started = System.currentTimeMillis();
    for (String q : queries) {
      //System.out.println("Searching " + q);
      String matched = dic.search(q);
      if (matched == null) {
        System.out.println("-1");
      } else {
        System.out.println(matched);
      }
    }
    System.out.println("queried in " + (System.currentTimeMillis()-started));
  }
  static class Dictionary {
    private HashMap<Character,Node> roots = new HashMap<Character,Node>();

    public void insert(String username) {
      char[] lcUsername = username.toLowerCase().toCharArray();
      Node node = roots.get(lcUsername[0]);
      if (node == null) {
        node = new Node();
        roots.put(lcUsername[0], node);
      }

      insertWord(lcUsername, 1, node, username);
    }

    private void insertWord(char[] string, int ndx, Node node, String username) {
      Node nextChild = node.children.get(string[ndx]);
      if (nextChild == null) {
        nextChild = new Node();
        node.children.put(string[ndx], nextChild);
      }

      if (ndx == string.length -1) {
        nextChild.usernames.add(username);
        nextChild.lcUsernames.add(new String(string));
      } else {
        insertWord(string, ndx+1,nextChild, username);
      }
    }

    public String search(String string) {
      char[] query = string.toLowerCase().toCharArray();
      Node node = roots.get(query[0]);
      if (node != null) {
        String[] result = new String[2];
        searchFor(query, 1, node, result);
        return result[0];
      } else {
        return null;
      } 
    }


    private void searchFor(char[] query, int ndx, Node node, String[] result) {
      if (ndx >= query.length) {
        addMatchingUsers(node, result);
        return;
      }

      Node childNode = node.children.get(query[ndx]);
      if (childNode != null) {
        searchFor(query, ndx+1, childNode, result);
      }
    }
    //
    private void addMatchingUsers(Node node, String[] result) {
      for (int i=0; i<node.usernames.size(); i++) {
        String u = node.usernames.get(i);
        String lu = node.lcUsernames.get(i);
        if (result[0] == null || result[1].compareTo(lu) > 0) {
            result[0] = u;
            result[1] = lu;
        }
      }
      for (Node child : node.children.values()) {
        addMatchingUsers(child, result);
      }
    }
  }
  static class Node {
    public List<String> usernames = new ArrayList<String>();
    public List<String> lcUsernames = new ArrayList<String>();
    public HashMap<Character,Node> children = new HashMap<Character,Node>();
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
