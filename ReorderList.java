// Given a singly linked list L: L0→L1→ ... →Ln-1→Ln,
// reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→...
public class ReorderList {
   static class Node {
      int val;
      Node next;
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
   // Break list in the middle to two lists (use fast & slow pointers)
   // Reverse the order of the second list
   // Merge two list back together
   //
   public static void reorderList(Node head) {
      if (head != null && head.next != null) {
         Node slow = head;
         Node fast = head;
 
         while (fast != null && fast.next != null && fast.next.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
         }
 
         Node second = slow.next;
         slow.next = null;// need to close first part
 
         second = reverseOrder(second);
 
         Node p1 = head;
         Node p2 = second;
 
         while (p2 != null) {
            Node temp1 = p1.next;
            Node temp2 = p2.next;
 
            p1.next = p2;
            p2.next = temp1;            
 
            p1 = temp1;
            p2 = temp2;
         }
      }
   }
 
   public static Node reverseOrder(Node head) {
      if (head == null || head.next == null) return head;
 
      Node pre = head;
      Node curr = head.next;
 
      while (curr != null) {
         Node temp = curr.next;
         curr.next = pre;
         pre = curr;
         curr = temp;
      }
 
      head.next = null;
      return pre;
   }
 
   public static void main(String[] args) {
      Node head = new Node(1);
      head.add(2).add(3).add(4).add(5).add(6).add(7).add(8);
      System.out.println(head);
      reorderList(head);
      System.out.println(head);
   }
}
