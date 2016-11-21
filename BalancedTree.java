import java.util.*;

public class BalancedTree {
  static class Node {
    int val;
    Node left;
    Node right;
    Node(int x) { val = x; }
  }
  public static int maxDepth(Node root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }
  public static int minDepth(Node root) {
    if (root == null) return 0;
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
  }
}
