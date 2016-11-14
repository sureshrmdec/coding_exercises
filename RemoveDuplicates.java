import java.util.*;
// Given a sorted array, remove the duplicates in place such that each element appear only once and return 
// the new length. Do not allocate extra space for another array, you must do this in place with constant memory.
//
// For example, given input array A = [1,1,2], your function should return length = 2, and A is now [1,2].
public class RemoveDuplicates {
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
   public static Node deleteDuplicates(Node head) {
      if(head == null || head.next == null)
         return head;

      Node prev = head;    
      Node p = head.next;

      while(p != null){
         if(p.val == prev.val){
            prev.next = p.next;
            //no change prev
         }else{
            prev = p;
         }
         p = p.next; 
      }

      return head;
   }


   public static String removeDuplicatesInPlace(String s) {
     if (s == null) return s;
     char[] str = s.toCharArray();
     int len = str.length;
     if (len < 2) return s;

     int tail = 1;

     for (int i=1; i<len; i++) {
       int j;
       for (j=0; j<tail; j++) {
         if (str[i] == str[j]) break;
       }
       if (j == tail) {
         str[tail] = str[i];
         System.out.println("\t\tSetting str[" + tail + "] to str[" + i + "], original " + s);
         ++tail;
       }
     }
     for (int i=tail; i<len; i++) {
      str[i] = 0;
    }
    return new String(str);
   }

   public static int[] removeDuplicates(int[] A) {
      if (A.length < 2)
         return A;

      int j = 0;
      int i = 1;

      while (i < A.length) {
         if (A[i] == A[j]) {
            i++;
         } else {
            j++;
            A[j] = A[i];
            i++;
         }
      }

      int[] B = Arrays.copyOf(A, j + 1);

      return B;
   }

   public static void main(String[] args) {
      System.out.println(removeDuplicatesInPlace("abcd"));
      System.out.println(removeDuplicatesInPlace("aaaa"));
      System.out.println(removeDuplicatesInPlace(null));
      System.out.println(removeDuplicatesInPlace("aaabbb"));
      //int[] arr = { 1, 2, 2, 3, 3 };
      //arr = removeDuplicates(arr);
      //System.out.println(arr.length);
      //for (int i=0; i<arr.length; i++) {
      //   System.err.println("\t" + arr[i]);
      //}
   }
}
