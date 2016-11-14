import java.util.*;


public class SumList {
  static class Node {
    int data;
    Node next;
    Node(int data) {
      this.data = data;
    }
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(String.valueOf(data));
      Node cur = next;
      while (cur != null) {
        sb.append("->" + String.valueOf(cur.data));
        cur = cur.next;
      }
      return sb.toString();
    }
  }
  static Node addList(Node l1, Node l2, int carry) {
    Node result = new Node(carry);
    int value = carry;
    if (l1 != null) value += l1.data;
    if (l2 != null) value += l2.data;
    if (carry == 0 && value == 0) {
      return null;
    }
    result.data = value % 10;
    System.out.println("\tresult " + result.data + ", value " + value + ", carry " + carry);
    if (l1 != null || l2 != null || value >= 10) {
      Node more = addList(
          l1 == null ? null : l1.next,
          l2 == null ? null : l2.next,
          value >= 10 ? 1 : 0);
      result.next = more;
    }
    return result;
  }

  public static void main(String[] args) {
    Node l1 = new Node(3);
    l1.next = new Node(1);
    l1.next.next = new Node(5);

    Node l2 = new Node(5);
    l2.next = new Node(9);
    l2.next.next = new Node(2);

    System.out.println(addList(l1, l2, 0));
  }
}
