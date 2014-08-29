public class BST {
   private class Node {
      private int val;
      private Node left;
      private Node right;
      public Node(int val) {
         this.val = val;
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
      if (p == null) return -1;
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



   public static void main(String[] args) {
      int[] a = {1,5,2,7,4};
      BST bst = new BST();
      for(int n : a) bst.insert(n);

      bst.preOrderTraversal();
      System.out.println();
   }
}
