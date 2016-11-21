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


public class LowestCommonAncestor {
	public static class Node {
		int val;
		Node left;
		Node right;
		public Node(int val) {
			this.val = val;
		}
    public String toString() {
      return String.valueOf(val);
    }
	}


	public static Node findLCA(Node root, Node p, Node q) {
		// both p and q are on left
		if (covers(root.left, p) && covers(root.left, q)) {
			return findLCA(root.left, p, q);
		}
		// both p and q are on right
		if (covers(root.right, p) && covers(root.right, q)) {
			return findLCA(root.right, p, q);
		}
		return root;
	}

	static boolean covers(Node root, Node p) {
		if (root == null) return false;
		if (root == p) return true;
		return covers(root.left, p) || covers(root.right, p);
	}
	public static void main(String[] args) { 
		// Driver program to test above function
		// Let's create the Binary Tree shown in above diagram
		Node root = new Node(1);
		Node n2 = root.left = new Node(2);
		Node n3 = root.right = new Node(3);
		Node n4 = root.left.left = new Node(4);
		Node n5 = root.left.right = new Node(5);
		Node n6 = root.right.left = new Node(6);
		root.right.right = new Node(7);
		System.out.println("LCA[4, 5) " + findLCA(root, n4, n5)); 
		System.out.println("LCA[4, 6) " + findLCA(root, n4, n6)); 
		System.out.println("LCA[3, 4) " + findLCA(root, n3, n4)); 
		System.out.println("LCA[2, 4) " + findLCA(root, n2, n4)); 
	}
}

