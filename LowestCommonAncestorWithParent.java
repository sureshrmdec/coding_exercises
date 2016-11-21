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


public class LowestCommonAncestorWithParent {
	public static class Node {
		int key;
		Node left;
		Node right;
		Node parent;
		public Node(int key) {
			this.key = key;
		}
	}
  private Node root, n1, n2, lca;

	// A utility function to insert a new node with
  // given key in Binary Search Tree 
	Node insert(Node node, int key) {
		/* If the tree is empty, return a new node */
		if (node == null) return new Node(key);

		/* Otherwise, recur down the tree */
		if (key < node.key) {
			node.left = insert(node.left, key);
			node.left.parent = node;
		} else if (key > node.key) {
			node.right = insert(node.right, key);
			node.right.parent = node;
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	// To find LCA of nodes n1 and n2 in Binary Tree
	Node LCA(Node n1, Node n2) {
		// Creata a map to store ancestors of n1
		Map<Node, Boolean> ancestors = new HashMap<Node, Boolean>();

		// Insert n1 and all its ancestors in map
		while (n1 != null) {
			ancestors.put(n1, Boolean.TRUE);
			n1 = n1.parent;
		}

		// Check if n2 or any of its ancestors is in
		// map.
		while (n2 != null) {
			if (ancestors.containsKey(n2) != ancestors.isEmpty())
				return n2;
			n2 = n2.parent;
		}

		return null;
	}

	// Driver method to test above functions
	public static void main(String[] args) {
		LowestCommonAncestorWithParent tree = new LowestCommonAncestorWithParent();
		tree.root = tree.insert(tree.root, 20);
		tree.root = tree.insert(tree.root, 8);
		tree.root = tree.insert(tree.root, 22);
		tree.root = tree.insert(tree.root, 4);
		tree.root = tree.insert(tree.root, 12);
		tree.root = tree.insert(tree.root, 10);
		tree.root = tree.insert(tree.root, 14);

		tree.n1 = tree.root.left.right.left;
		tree.n2 = tree.root.left;
		tree.lca = tree.LCA(tree.n1, tree.n2);

		System.out.println("LCA of " + tree.n1.key + " and " + tree.n2.key + " is " + tree.lca.key);

	}
}
