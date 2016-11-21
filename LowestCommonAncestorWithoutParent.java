/* PROBLEM STATEMENT:

   The lowest common ancestor (LCA) of two nodes is the deepest node
   common to the lineage of both nodes. For example, in this tree...

   R
   / \
   /   \ 
   A     B
   / \     \
   X    Y     Z
   C  D  L M   G  H


   ... the LCA of nodes A and B is the root R.
   ... the LCA of nodes X and Y is the node A.
   ... the LCA of nodes A and Z is the root R.

   Please implement a method that returns the lowest common ancestor
   of two nodes given.

   You may not assume any organization for the tree, i.e. the tree 
   is _not_ a BST. However, the nodes do have a parent reference.

*/

import java.util.*;


public class LowestCommonAncestorWithoutParent {
   public static class Node {
      int val;
      Node left;
      Node right;
      public Node(int val) {
         this.val = val;
      }
   }
	 // Finds the path from root node to given root of the tree.
	 // Stores the path in a list path[], returns true if path 
 	 // exists otherwise false
   public static boolean findPath(Node root, List<Node> path, int k) {
		if (root == null) return false;
 
    // Store this node is path vector. The node will be
    // removed if not in path from root to k
    path.add(root);
 
    // See if the k is same as root's key
    if (root.val == k) return true;
 
    // Check if k is found in left or right sub-tree
    if ((root.left != null && findPath(root.left, path, k)) ||
            (root.right!= null && findPath(root.right, path, k))) {
        return true;
		}
 
    // If not present in subtree rooted with root, remove
    // root from path and return False
		if (path.size() > 0) path.remove(path.size()-1);
    return false;
	}
 
	// Returns LCA if node n1 , n2 are present in the given
	// binary tre otherwise return -1
	public static int findLCA(Node root, int n1, int n2) {
    // To store paths to n1 and n2 fromthe root
    List<Node> path1 = new ArrayList<>();
    List<Node> path2 = new ArrayList<>();
 
    // Find paths from root to n1 and root to n2.
    // If either n1 or n2 is not present , return -1 
    if (!findPath(root, path1, n1) || !findPath(root, path2, n2)) {
        return -1;
		} 
    // Compare the paths to get the first different value
    int i = 0;
		for (i = 0; i < path1.size() && i < path2.size() ; i++) {
        if (path1.get(i) != path2.get(i)) break;
		}
    if (path1.size() >= i && i > 0) {
      return path1.get(i-1).val;
    }
    return -1;
  } 

  public static void main(String[] args) { 
		// Driver program to test above function
		// Let's create the Binary Tree shown in above diagram
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		System.out.println("LCA[4, 5) " + findLCA(root, 4, 5)); 
		System.out.println("LCA[4, 6) " + findLCA(root, 4, 6)); 
		System.out.println("LCA[3, 4) " + findLCA(root, 3, 4)); 
		System.out.println("LCA[2, 4) " + findLCA(root, 2, 4)); 
  }
} 
