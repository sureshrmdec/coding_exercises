import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Implement insert and delete in a tri-nary tree. Much like a 
 * binary-tree but with 3 child nodes for each parent instead of two -- 
 * with the left node being values < parent, the right node values > 
 * parent, and the middle node values == parent. 
 * For example, if I added 
 * the following nodes to the tree in this 
 * order: 5, 4, 9, 5, 7, 2, 2 -- the tree would look like this: 
 *
 *     5 
 *   / | \ 
 *  4  5  9 
 * /     / 
 * 2    7 
 * | 
 * 2 
 */
public class TrinaryTree {
   // packaged accessible inner class to represent node and value at the node 
   static class Node {
      final int val; // immutable value
      Node left;     // left.value < current.value 
      Node middle;   // middle.value == current.value 
      Node right ;   // right.value > current.value 
      Node(int val) {
         this.val = val;
      }
      @Override
      public String toString() {
         StringBuilder sb = new StringBuilder();
         toString(sb, 0);
         return sb.toString();
      }
      public void toString(StringBuilder sb, int indent) {
         for (int i=0; i<indent; i++) {
            sb.append(' ');
         }
         sb.append(String.valueOf(val) + "\n");
         if (left != null) {
            sb.append("/");
            left.toString(sb, indent+2);
         }
         if (middle != null) {
            sb.append("|");
            middle.toString(sb, indent+2);
         }
         if (right != null) {
            sb.append("\\");
            right.toString(sb, indent+2);
         }
      }
   } 

   private Node root; // root of the tree

   /**
    * The insert method adds given value such using the constraints set by TrinaryTree 
    * This code is modified version of binary search tree's implementation of insert method 
    * as described in Robert Sedgewick's Algorithm's book (pp399, 4th edition)
    * It delegates the insert method recursively so that parent can be adjusted based on
    * new values of the tree.
    * Note - trinary tree allows duplicate values and duplicate values will be added 
    *        as middle child 
    * @param  val - value to insert 
    */
   public void insert(int val) {
      root = insert(root, val);
   }

   // This private method uses variation of binary search to find the matching
   // node, where value is inserted. This method recursively finds the node where 
   // value can be inserted and set parent's pointers to middle, left or child 
   // The running time of algorithm when tree is balanced and without duplicates 
   // would be O(log n) and in worst case it would O(N), e.g. if we 
   // insert 1, 1, 1, 1 duplicate values or values in ascending order such as
   //   1
   //     2
   //       3
   // or descending order such as
   //     3
   //   2
   // 1
   //
   private Node insert(Node parent, int val) {
      if (parent == null) {
         return new Node(val);
      }
      if (val == parent.val) {
         parent.middle = insert(parent.middle, val);
      } else if (val > parent.val) {
         parent.right = insert(parent.right, val);
      } else if (val < parent.val) {
         parent.left = insert(parent.left, val);
      }
      return parent;
   }

   /**
    * This is helper methods to create a new TrinaryTree and 
    * insert array of numbers 
    * @param numbers - array of integers 
    * @return - trinary tree 
    */
   public static TrinaryTree insert(int[] numbers) {
      TrinaryTree tree = new TrinaryTree();
      for (int n : numbers) {
         tree.insert(n);
      }
      return tree;
   }

   /**
    * This delete method is variation of delete method for binary search tree 
    * as described in Robert Sedgewick's Algorithm's book (pp410, 4th edition)
    * It uses Hibbard's algorithm, which deletes node x by replacing it with its
    * successor or smallest node value in right subtree.
    * @param val - value to delete 
    */
   public void delete(int val) {
      root = delete(root, val);
   }

   // This method implements eager Hibbard deletion algorithm
   private Node delete(Node parent, int val) {
      if (parent == null) {
         return null; // nothing to do as tree is empty
      }
      if (val == parent.val) {
         if (parent.middle != null) {
            parent.middle = delete(parent.middle, val);
         } else {
            if (parent.right == null) {
               return parent.left;
            } else if (parent.left == null) {
               return parent.right;
            }
            Node t = parent;
            parent = min(t.right);
            parent.right = deleteMin(t.right);
            parent.left = t.left;
         }
      } else if (val > parent.val) {
         parent.right = delete(parent.right, val);
      } else if (val < parent.val) {
         parent.left = delete(parent.left, val);
      }
      return parent;
   }

   // These are helper methods used by delete method and adopted from 
   // Robert Sedgewick's Algorithm's book (pp411, 4th edition)
   private Node min() {
      return min(root);
   }
   private Node min(Node parent) {
      if (parent.left == null) {
         return parent;
      }
      return min(parent.left);
   }
   private void deleteMin() {
      root = deleteMin(root);
   }
   private Node deleteMin(Node parent) {
      if (parent.left == null) {
         return parent.right;
      }
      parent.left = deleteMin(parent.left);
      return parent;
   }

   // These are helper methods for testing and verifying contents of the tree 
   private int[] preorderValuesAsPrimitiveArray() {
      List<Integer> list = preorderValues();
      int[] numbers = new int[list.size()];
      for (int i=0; i<list.size(); i++) {
         numbers[i] = list.get(i);
      }
      return numbers;
   }
   // This method traverses the tree using pre-order, i.e., it visits parent, then children
   private List<Integer> preorderValues() {
      List<Integer> list = new ArrayList<Integer>();
      if(root == null) return list;

      Stack<Node> stack = new Stack<Node>();
      stack.push(root);

      while(stack.size() > 0){
         Node n = stack.pop();
         list.add(n.val);

         if(n.right != null){
            stack.push(n.right);
         }
         if(n.middle != null){
            stack.push(n.middle);
         }
         if(n.left != null){
            stack.push(n.left);
         }
      }
      return list;
   }

   @Override
   public String toString() {
      return root.toString();
   }


   public static void Test1() { 
      TrinaryTree tree = insert(new int[] {5, 4, 9, 5, 7, 2, 2 });
      assertEquals(new int[] {5, 4, 2, 2, 5, 9, 7}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(2);
      assertEquals(new int[] {5, 4, 2, 5, 9, 7}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(5);
      assertEquals(new int[] {5, 4, 2, 9, 7}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(4);
      assertEquals(new int[] {5, 2, 9, 7}, tree.preorderValuesAsPrimitiveArray());
   } 

   public static void Test2() { 
      TrinaryTree tree = insert(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
      assertEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(4);
      assertEquals(new int[] {1, 2, 3, 5, 6, 7, 8, 9, 10}, tree.preorderValuesAsPrimitiveArray());
   } 

   public static void Test3() { 
      TrinaryTree tree = insert(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
      assertEquals(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(4);
      tree.delete(7);
      assertEquals(new int[] {10, 9, 8, 6, 5, 3, 2, 1}, tree.preorderValuesAsPrimitiveArray());
   } 

   public static void Test4() { 
      TrinaryTree tree = insert(new int[] {100, 90, 150, 70, 80, 140, 160, 150, 70});
      assertEquals(new int[] {100, 90, 70, 70, 80, 150, 140, 150, 160}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(150);
      assertEquals(new int[] {100, 90, 70, 70, 80, 150, 140, 160}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(70);
      assertEquals(new int[] {100, 90, 70, 80, 150, 140, 160}, tree.preorderValuesAsPrimitiveArray());
      tree.delete(70);
      assertEquals(new int[] {100, 90, 80, 150, 140, 160}, tree.preorderValuesAsPrimitiveArray());
   } 

   private static void assertEquals(int[] expected, int[] actual) {
      if (expected.length != actual.length) {
         throw new Error("Expected " + Arrays.toString(expected) + ", but was " + Arrays.toString(actual));
      }
      for (int i=0; i<expected.length; i++) {
         if (expected[i] != actual[i]) {
            throw new Error("Expected " + Arrays.toString(expected) + ", but was " + Arrays.toString(actual));
         }
      }
   }

   public static void main(String[] args) {
      Test1();
      Test2();
      Test3();
      Test4();
   }
}
