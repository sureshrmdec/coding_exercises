import java.util.*;


public class SubTree {
  private class Node {
    private int data;
    private Node next;
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

  public Node nthToLast(Node head, int n) { 
    if (head == null || n < 1) {
      return null;
    }
    Node p1 = head;
    Node p2 = head;
    for (int j = 0; j < n - 1; ++j) { // skip n-1 steps ahead
      if (p2 == null) return null; // not found since list size < n 
      p2 = p2.next; 
    }

    while (p2.next != null) { 
      p1 = p1.next;
      p2 = p2.next; 
    }
    return p1; 
  }
  public Node addList(Node l1, Node l2, int carry) {
    if (l1 == null && l2 == null) return null;
    Node result = new Node(carry);
    int value = carry;
    if (l1 != null) value += l1.data;
    if (l2 != null) value += l2.data;
    result.data = value % 10;
    Node more = addList(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value > 10 ? 1 : 1);
    result.next = more;
    return result;
  }

  void findSum(Node head, int sum, ArrayList<Integer> buffer,
      int level) {
    if (head == null) return;
    int tmp = sum;
    buffer.add(head.data);
    for (int i = level;i >- 1; i--){
      tmp -= buffer.get(i);
      if (tmp == 0) print(buffer, i, level);
    }
    ArrayList<Integer> c1 = (ArrayList<Integer>) buffer.clone();
    ArrayList<Integer> c2 = (ArrayList<Integer>) buffer.clone();
    findSum(head.left, sum, c1, level + 1);
    findSum(head.right, sum, c2, level + 1);
  }
  void print(ArrayList<Integer> buffer, int level, int i2) {
    for (int i = level; i <= i2; i++) {
      System.out.print(buffer.get(i) + " ");
    }
    System.out.println();
  }
}

