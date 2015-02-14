import java.io.*;
import java.util.*;
import java.util.Queue;


public class BST2 {
  private static class Node implements Comparable<Node> {
    int val;
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

  public Node findMin() {
    Node current = root;
    while (current != null && current.left != null) {
      current = current.left;
    }
    return current;
  }

/* 
def replace_node_in_parent(self, new_value=None):
    if self.parent:
        if self == self.parent.left_child:
            self.parent.left_child = new_value
        else:
            self.parent.right_child = new_value
    if new_value:
        new_value.parent = self.parent
 
def binary_tree_delete(self, key):
    if key < self.key:
        self.left_child.binary_tree_delete(key)
    elif key > self.key:
        self.right_child.binary_tree_delete(key)
    else: # delete the key here
        if self.left_child and self.right_child: # if both children are present
            successor = self.right_child.find_min()
            self.key = successor.key
            successor.binary_tree_delete(successor.key)
        elif self.left_child:   # if the node has only a *left* child
            self.replace_node_in_parent(self.left_child)
        elif self.right_child:  # if the node has only a *right* child
            self.replace_node_in_parent(self.right_child)
        else: # this node has no children
            self.replace_node_in_parent(None)
*/


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
  //
  static class NullNode extends Node {
    NullNode() {
      super(Integer.MIN_VALUE);
    }
  }
  private static final NullNode NULL_NODE = new NullNode();

  void serialize(File file) throws IOException {
    DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
    serialize(file, root, out);
    out.close();
  }

  void serialize(File file, Node node, DataOutputStream out) throws IOException {
    if (node == null) {
      out.writeInt(NULL_NODE.val);
    } else {
      out.writeInt(node.val);
      serialize(file, node.left, out);
      serialize(file, node.right, out);
    }
  }


  static Node deserialize(File file) throws IOException {
    DataInputStream in = new DataInputStream(new FileInputStream(file));
    Node root = deserialize(in);
    return root;
  }

  static Node deserialize(DataInputStream in) throws IOException {
    Node node = null;
    try {
      int val = in.readInt();
      if (val != Integer.MIN_VALUE) {
        node = new Node(val);
        node.left = deserialize(in);
        node.right = deserialize(in);
      }
    } catch (EOFException e) {
    }
    return node;
  }

  public static void main(String[] args) throws Exception {
    //int[] a = {1,5,2,7,4};
    int[] a = {15,5, 20,3,44, 100, 10, 30, 50};
    BST2 bst = new BST2();
    for(int n : a) bst.insert(n);

    //bst.preOrderTraversal();
    //bst.root = new Node(5);
    //bst.root.left = new Node(5);
    //bst.root.right = new Node(5);
    //System.out.println(bst.validateBST());
    //System.out.println(bst.validateBSTViaBFS());
    bst.printPreoderWithEachLevelOnSeparateLine();
    System.out.println();
    bst.serialize(new File("binary.dat"));
    bst.root = deserialize(new File("binary.dat"));
    System.out.println();
    bst.printPreoderWithEachLevelOnSeparateLine();
    System.out.println();
  }
}

