import java.util.*;

public class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode(int x) { val = x; }

   // parent node is processed before its children
   public static List<Integer> preorderTraversal(TreeNode root) {
      List<Integer> returnList = new ArrayList<Integer>();
      if(root == null) return returnList;

      Stack<TreeNode> stack = new Stack<TreeNode>();
      stack.push(root);

      while(stack.size() > 0){
         TreeNode n = stack.pop();
         returnList.add(n.val);

         if(n.right != null){
            stack.push(n.right);
         }
         if(n.left != null){
            stack.push(n.left);
         }

      }
      return returnList;
   }

   // The order of "inorder" is: left child -> parent -> right child
   public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> lst = new ArrayList<Integer>();

      if(root == null) return lst; 

      Stack<TreeNode> stack = new Stack<TreeNode>();
      TreeNode p = root;

      while(stack.size() > 0 || p != null){
         // if it is not null, push to stack
         //and go down the tree to left
         if(p != null){
            stack.push(p);
            p = p.left;
            // if no left child
            // pop stack, process the node
            // then let p point to the right
         } else{
            TreeNode t = stack.pop();
            lst.add(t.val);
            p = t.right;
         }
      }
      return lst;
   }
   // The order of "Postorder" is: left child -> right child -> parent node.
   public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> lst = new ArrayList<Integer>();
      if(root == null) return lst; 

      Stack<TreeNode> stack = new Stack<TreeNode>();
      stack.push(root);

      TreeNode prev = null;
      while(stack.size() > 0){
         TreeNode curr = stack.peek();
         if(prev == null || prev.left == curr || prev.right == curr){
            //prev == null is the situation for the root node
            if(curr.left != null){
               stack.push(curr.left);
            }else if(curr.right != null){
               stack.push(curr.right);
            }else{
               stack.pop();
               lst.add(curr.val);
            }
         }else if(curr.left == prev){
            if(curr.right != null){
               stack.push(curr.right);
            }else{
               stack.pop();
               lst.add(curr.val);
            }
         }else if(curr.right == prev){
            stack.pop();
            lst.add(curr.val);
         }

         prev = curr;
      }

      return lst;
   }
   public static boolean isValidBST(TreeNode root) {
      return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }

   private static boolean isValidBST(TreeNode root, int min, int max) {
      if (root == null) {
         return true;
      }

      // not in range
      if (root.val <= min || root.val >= max) {
         return false;
      }

      // left subtree must be < root.val && right subtree must be > root.val
      return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
   }

   // Given a binary tree, flatten it to a linked list in-place. 
   // Go down through the left, when right is not null, push right to stack. 
   //      1
   //     / \
   //    2   5
   //   / \   \
   //  3   4   6
   // 
   //   1
   //    \
   //     2
   //      \
   //       3
   //        \
   //         4
   //          \
   //           5
   //            \
   //             6
   public static void flatten(TreeNode root) {
      Stack<TreeNode> stack = new Stack<TreeNode>();
      TreeNode p = root;

      while(p != null || stack.size() > 0){
         if(p.right != null){
            stack.push(p.right);
         }
         if(p.left != null){
            p.right = p.left;
            p.left = null;
         }else if(stack.size() > 0){
            TreeNode temp = stack.pop();
            p.right=temp;
         }

         p = p.right;
      }
   }

   public boolean hasPathSum(TreeNode root, int sum) {
      if(root == null) return false;

      LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
      LinkedList<Integer> values = new LinkedList<Integer>();

      nodes.add(root);
      values.add(root.val);

      while(!nodes.isEmpty()){
         TreeNode curr = nodes.poll();
         int sumValue = values.poll();

         if(curr.left == null && curr.right == null && sumValue==sum){
            return true;
         }

         if(curr.left != null){
            nodes.add(curr.left);
            values.add(sumValue+curr.left.val);
         }

         if(curr.right != null){
            nodes.add(curr.right);
            values.add(sumValue+curr.right.val);
         }
      }

      return false;
   }
   public TreeNode sortedArrayToBST(int[] num) {
      if (num.length == 0)
         return null;

      return sortedArrayToBST(num, 0, num.length - 1);
   }

   public TreeNode sortedArrayToBST(int[] num, int start, int end) {
      if (start > end)
         return null;

      int mid = (start + end) / 2;
      TreeNode root = new TreeNode(num[mid]);
      root.left = sortedArrayToBST(num, start, mid - 1);
      root.right = sortedArrayToBST(num, mid + 1, end);

      return root;
   }

   public int minDepth(TreeNode root) {
      if(root == null) return 0;
      LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
      LinkedList<Integer> counts = new LinkedList<Integer>();

      nodes.add(root);
      counts.add(1);

      while(!nodes.isEmpty()){
         TreeNode curr = nodes.remove();
         int count = counts.remove();

         if(curr.left != null){
            nodes.add(curr.left);
            counts.add(count+1);
         }

         if(curr.right != null){
            nodes.add(curr.right);
            counts.add(count+1);
         }

         if(curr.left == null && curr.right == null){
            return count;
         }
      }

      return 0;
   }

   public int maxPathSum(TreeNode root) {
      int max[] = new int[1]; 
      max[0] = Integer.MIN_VALUE;
      calculateSum(root, max);
      return max[0];
   }

   public int calculateSum(TreeNode root, int[] max) {
      if (root == null) return 0;

      int left = calculateSum(root.left, max);
      int right = calculateSum(root.right, max);

      int current = Math.max(root.val, Math.max(root.val + left, root.val + right));

      max[0] = Math.max(max[0], Math.max(current, left + root.val + right));

      return current;
   }

   public boolean isBalanced(TreeNode root) {
      if (root == null)
         return true;

      if (getHeight(root) == -1)
         return false;

      return true;
   }

   public int getHeight(TreeNode root) {
      if (root == null)
         return 0;

      int left = getHeight(root.left);
      int right = getHeight(root.right);

      if (left == -1 || right == -1)
         return -1;

      if (Math.abs(left - right) > 1) {
         return -1;
      }

      return Math.max(left, right) + 1;

   }
}
