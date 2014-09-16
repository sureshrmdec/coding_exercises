public class SubTree {
  private class Node {
    private int data;
    private Node left;
    private Node right;
    public Node(int data) {
      this.data = data;
    }
  }

  boolean containsTree(Node t1, Node t2) {
    if (t2 == null) return true; // The empty tree is always a subtree 
    else return subTree(t1, t2);
  }

  boolean subTree(Node r1, Node r2) { 
    if (r1 == null) return false; // big tree empty & subtree still not found. 
    if (r1.data == r2.data) {
      if (matchTree(r1,r2)) return true; 
    }
    return (subTree(r1.left, r2) || subTree(r1.right, r2)); 
  }

  boolean matchTree(Node r1, Node r2) { 
    if (r2 == null && r1 == null) return true; // nothing left in the subtree 
    if (r1 == null || r2 == null) return false; // big tree empty & subtree still not found 
    if (r1.data != r2.data) return false; // data doesn’t match 
    return (matchTree(r1.left, r2.left) &&
        matchTree(r1.right, r2.right));
  } 
}

