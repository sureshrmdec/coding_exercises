import java.util.*;

public class TreeLevels {
  static class Node {
    int val;
    Node left;
    Node right;
    Node(int x) { val = x; }
  }
  public static List<List<Node>> findLevels(Node root) {
    int level = 0;
    List<List<Node>> result = new ArrayList<List<Node>>();
    List<Node> list = new ArrayList<>();
    list.add(root);
    result.add(level, list);

    while (true) {
      list = new ArrayList<>();
      for (int i=0; i<result.get(level).size(); i++) {
        Node n = result.get(level).get(i);
        if (n != null) {
          if (n.left != null) list.add(n.left);
          if (n.right != null) list.add(n.right);
        }
      }
      if (list.size() > 0) {
        result.add(level+1, list);
      } else {
        break;
      }
      level++;
    }
    return result;
  }
  public static void main(String[] args) {
  }
}
