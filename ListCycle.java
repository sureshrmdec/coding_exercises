public class ListCycle {
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
   public static boolean hasCycle(Node head) {
      Node fast = head;
      Node slow = head;

      if(head == null) return false;

      if(head.next == null) return false;

      while(fast != null && fast.next != null){
         slow = slow.next;
         fast = fast.next.next;

         if(slow == fast) return true;
      }

      return false;
   }

   public static void main(String[] args) {
      Node head = new Node(1);
      Node tail = head.add(2).add(3).add(4).add(5).add(6).add(7).add(8);
      System.out.println(hasCycle(head));
      tail.next = head;
      System.out.println(hasCycle(head));
   }
}
