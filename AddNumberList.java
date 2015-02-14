public class AddNumberList {
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
         return val + (next == null ? "" : " " + next.toString());
         //return val + "";
      }
   }
   public static Node add(Node l1, Node l2) {
      Node result = new Node(0);
      Node l3 = result;
      int carry = 0;
      while (l1 != null || l2 != null) {
        //System.out.println("before l1 " + l1 + ", l2 " + l2 + ", l3 " + l3 + ", result " + result + ", carry " + carry);
         if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
         }
         if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
         }
         l3.next = new Node(carry % 10);
         l3 = l3.next;
         carry /= 10;
      }
      if (carry != 0) {
         l3.next = new Node(carry);
      }
      return result.next;
   }
   public static void main(String[] args) {
      Node l1 = new Node(2);
      l1.add(4).add(3).add(1);
      Node l2 = new Node(5);
      l2.add(6).add(4); 
      //
      Node result = add(l1, l2);
      //System.out.println(l1);
      //System.out.println(l2);
      System.out.println("result " + result);
   }

}
