// A linked list is given such that each node contains an additional random pointer 
// which could point to any node in the list or null.
// Return a deep copy of the list.
//
import java.util.*;

public class CopyList {
   static class Node {
      int val;
      Node next;
      Node random;
      Node(int x) {
         val = x;
         next = null;
      }
      Node add(int x) {
         next = new Node(x);
         return next;
      }
      public String toString() {
         return val + (next == null ? "" : "->" + next.toString());
      }
   }
   public static Node copyRandomList(Node head) {
      if (head == null) return null;
      Map<Node, Node> map = new HashMap<Node, Node>();
      Node newHead = new Node(head.val);

      Node p = head;
      Node q = newHead;
      map.put(head, newHead);

      p = p.next;
      while (p != null) {
         Node temp = new Node(p.val);
         map.put(p, temp);
         q.next = temp;
         q = temp;
         p = p.next;
      }

      p = head;
      q = newHead;
      while (p != null) {
         if (p.random != null)
            q.random = map.get(p.random);
         else
            q.random = null;

         p = p.next;
         q = q.next;
      }

      return newHead;
   }
}
