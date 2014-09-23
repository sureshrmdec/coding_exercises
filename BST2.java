import java.util.*;
import java.util.Queue;


public class BST2 {
  private static class Node implements Comparable<Node> {
    private int val;
    private Node left;
    private Node right;
    public Node(int val) {
      this.val = val;
    }
    public int compareTo(Node other) {
      return val - other.val;
    }
    @Override
      public String toString() {
        return "" + val;
      }
  }

  private Node root;

  public void insert(int val) {
    root = insert(root, val);
  }


  boolean validateBST() {
    return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  boolean validateBST(Node parent, int min, int max) {
    if (parent == null) {
      return true;
    }
    if (parent.val < min) {
      return false;
    }
    if (parent.val >= max) {
      return false;
    }

    return validateBST(parent.left, min, parent.val)
      && validateBST(parent.right, parent.val, max);
  }

  boolean validateBSTViaBFS() {
    if (root == null) {
      return true;
    }
    Queue<Node> q = new PriorityQueue<>();
    Queue<Integer> minq = new PriorityQueue<>();
    Queue<Integer> maxq = new PriorityQueue<>();
    q.add(root);
    minq.add(Integer.MIN_VALUE);
    maxq.add(Integer.MAX_VALUE);
    while (!q.isEmpty()) {
      Node n = q.remove();
      Integer min = minq.remove(); 
      Integer max = maxq.remove(); 
      if (n.val < min) {
        return false;
      }
      if (n.val >= max) {
        return false;
      }
      if (n.left != null) {
        q.add(n.left);
        minq.add(min);
        maxq.add(n.val);
      }
      if (n.right != null) {
        q.add(n.right);
        minq.add(n.val);
        maxq.add(max);
      }
    }
    return true;
  }


  private Node insert(Node p, int val) {
    if (p == null) return new Node(val);
    if (val == p.val) {
      return p;
    } else if (val < p.val) {
      p.left = insert(p.left, val);
    } else {
      p.right = insert(p.right, val);
    }
    return p;
  }

  public boolean search(int val) {
    return search(root, val);
  }

  private boolean search(Node p, int val) {
    if (p == null) {
      return false;
    } else if (val == p.val) {
      return true;
    } else if (val < p.val) {
      return search(p.left, val);
    } else {
      return search(p.right, val);
    }
  }

  public void delete(int val) {
    root = delete(root, val);
  }
  private Node delete(Node p, int val) {
    if (p == null)  {
      return p;
    } else if (val < p.val) {
      p.left = delete (p.left, val);
    } else if (val > p.val) {
      p.right = delete (p.right, val);
    } else {
      if (p.left == null) {
        return p.right;
      } else if (p.right == null) {
        return p.left;
      } else {
        // get data from the rightmost node in the left subtree
        p.val = retrieveData(p.left);
        // delete the rightmost node in the left subtree
        p.left =  delete(p.left, p.val) ;
      }
    }
    return p;
  }
  private int retrieveData(Node p) {
    while (p.right != null) p = p.right;
    return p.val;
  }

  public void preOrderTraversal() {
    preOrderHelper(root);
  }


  private void preOrderHelper(Node r) {
    if (r != null) {
      System.out.print(r+" ");
      preOrderHelper(r.left);
      preOrderHelper(r.right);
    }
  }

  public void inOrderTraversal() {
    inOrderHelper(root);
  }
  private void inOrderHelper(Node r) {
    if (r != null) {
      inOrderHelper(r.left);
      System.out.print(r+" ");
      inOrderHelper(r.right);
    }
  }

  public int height() {
    return height(root);
  }
  private int height(Node p) {
    if (p == null) return 0;
    else
      return 1 + Math.max( height(p.left), height(p.right));
  }

  public int countLeaves() {
    return countLeaves(root);
  }
  private int countLeaves(Node p) {
    if(p == null) {
      return 0;
    } else if(p.left == null && p.right == null) {
      return 1;
    } else {
      return countLeaves(p.left) + countLeaves(p.right);
    }
  }
  void printPreoderWithEachLevelOnSeparateLine() {
    if (root == null) return;
    LinkedList<Node> q = new LinkedList<>();
    LinkedList<Integer> levelQ = new LinkedList<>();
    q.push(root);
    levelQ.push(1);

    while (!q.isEmpty()) {
      Node node = q.pop();
      int level = levelQ.pop();
      System.out.print(node.val);
      if (node.left != null) {
        q.add(node.left);
        levelQ.add(level+1);
      }
      if (node.right != null) {
        q.add(node.right);
        levelQ.add(level+1);
      }
      if (!levelQ.isEmpty()) {
        int plevel = levelQ.peek();
        if (level != plevel) {
          System.out.println();
        } else {
          System.out.print(" ");
        }
      } else {
        System.out.println();
      } 
    }
  }
  public static void main(String[] args) {
    //int[] a = {1,5,2,7,4};
    int[] a = {15,5, 20,3,44};
    BST2 bst = new BST2();
    for(int n : a) bst.insert(n);

    //bst.preOrderTraversal();
    //bst.root = new Node(5);
    //bst.root.left = new Node(5);
    //bst.root.right = new Node(5);
    //System.out.println(bst.validateBST());
    //System.out.println(bst.validateBSTViaBFS());
    System.out.println();
    bst.printPreoderWithEachLevelOnSeparateLine();
  }
}

