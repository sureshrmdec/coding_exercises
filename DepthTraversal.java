/*

 Given a binary tree, your task is to do a depth-first traversal and print its node values in order.

The binary tree has a string representation as following:

    Each character represents a tree node. For each tree node we assign an index computed as the character position in the string, where positions are indexed starting with 1.
    The root of the tree is the first character. Its index is 1.
    The left child of a node having index N will have index 2 * N.
    The right child of a node having index N will have index 2 * N + 1
    If a node index is higher than the string length then it doesn't exist.
    If the character representing a node is "*", then the node is empty.

Example input:
tree: "w snulnmrmunandsi oatsitit tdKoegi onata u;id so tni fi l.***w***e***k*w*g***o*t*i***f*i* ***o*** ***p*t*g***i*a*r***s*a**"

Example output:
Knowledge is knowing a tomato is a fruit; wisdom is not putting it in a fruit salad.

Expected complexity: O(N)


*/


import java.io.*;
import java.util.*;

public class DepthTraversal {
  static class Node {
    final char letter;
    Node left;
    Node right;
    Node(final char letter) {
      this.letter = letter;
    }
  }

  public static void depth_first(String tree) {
    printInorder(tree, 1);
    System.out.println();
  }
  private static void printInorder(String tree, int N) {
    if (N > tree.length()) return;
    char ch = tree.charAt(N-1);
    if (ch != '*') {
      printInorder(tree, 2 * N);
      System.out.print(ch);
      printInorder(tree, (2 * N) + 1);
    }
  }

  public static void _depth_first(String tree) {
    if (tree == null || tree.length() == 0) return;
    System.out.println("BEFORE " + tree);
    Map<Integer, Node> nodes = new HashMap<Integer, Node>();
    for (int i=0; i<tree.length(); i++) {
      int N = i+1;
      Node node = getNode(nodes, tree, N);
      if (node != null) {
        node.left = getNode(nodes, tree, 2 * N);
        node.right = getNode(nodes, tree, (2 * N) + 1);
      }
    }
    print(nodes.get(1));
    System.out.println();
  }

  private static Node getNode(Map<Integer, Node> nodes, String tree, int N) {
    if (N > tree.length()) {
      return null;
    }
    char ch = tree.charAt(N-1);
    if (ch == '*') return null;
    Node node = nodes.get(N);
    if (node == null) {
      node = new Node(ch);
      nodes.put(N, node);
    }
    return node;
  }


  private static void print(Node node) {
    if (node == null) return;
    print(node.left);
    System.out.print(node.letter);
    print(node.right);
  }


  public static void main(String[] args) throws Exception {
    //BufferedReader in = new BufferedReader(new FileReader(args[0]));
    //String tree = parse(in.readLine());
    //in.close();
    //depth_first(tree);
    //depth_first("puvetnarynapl ieu issklnl a eetNv,nra rmtc,ae eigi d@teot m g.***e* *d***n*c*c***a*e* *** *s*e*** *l*a*** *x*i***n*h*s***n*h**");
    //Never, under any circumstances, take a sleeping pill and a laxative on
    //the same night.
    depth_first("w snulnmrmunandsi oatsitit tdKoegi onata u;id so tni fi l.***w***e***k*w*g***o*t*i***f*i* ***o*** ***p*t*g***i*a*r***s*a**");
    // Knowledge is knowing a tomato is a fruit; wisdom is not putting it in a fruit salad. 
  }
}
