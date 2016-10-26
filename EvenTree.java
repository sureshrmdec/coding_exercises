import java.io.*;
import java.util.*;
import java.text.*;


// https://www.hackerrank.com/challenges/even-tree
// You are given a tree (a simple connected graph with no cycles). You have to remove as many edges from the tree as possible to obtain a forest with the condition that : Each connected component of the forest should contain an even number of vertices.
//
// Your task is to calculate the number of removed edges in such a forest.
//
// Input Format 
// The first line of input contains two integers N and M. N is the number of vertices and M is the number of edges. 
// The next M lines contain two integers ui and vi which specifies an edge of the tree. (1-based index)
//
// Output Format 
// Print the answer, a single integer.
//
// Constraints 
// 2 <= N <= 100.
//
// Note: The tree in the input will be such that it can always be decomposed into components containing even number of nodes.
//
// Sample Input
//
// 10 9
// 2 1
// 3 1
// 4 3
// 5 2
// 6 1
// 7 2
// 8 6
// 9 8
// 10 8
// Sample Output
//
// 2
// Explanation  
// On removing the edges (1, 3) and (1, 6), we can get the desired result.
//
// Original tree:
//
//
// Decomposed tree:
//
//
//
// Suggest Edits
public class EvenTree {
   private static class Vertex {
      private int val;
      private int descendants;
      private Vertex _parent;
      private Set<Vertex> _children = new HashSet<>();
      private Vertex(int val) {
         this.val = val;
      }
      public void addChild(Vertex child) {
         _children.add(child);
         child._parent = this;
      }
      public String toString() {
        return String.valueOf(val);
      }

      public int hashCode() {
         return val;
      }
      public boolean equals(Object o) {
         Vertex other = (Vertex) o;
         return val == other.val;
      } 
      public int countDescendants() {
        descendants = 1;
        if (_children.size() == 0) {
          return descendants;
        }
        for (Vertex v : _children) {
          descendants += v.countDescendants();
        }
        return descendants;
      }
   }


   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int N = in.nextInt(); // # of vertices
      int M = in.nextInt(); // # of edges  
      Vertex root = new Vertex(1);
      Map<Vertex, Vertex> vertices = new HashMap<>();
      vertices.put(root, root);
      for (int i=0; i<M; i++) {
        Vertex v = new Vertex(in.nextInt());
        Vertex w = new Vertex(in.nextInt());
        if (vertices.get(v) == null) {
          vertices.put(v, v); 
        } else {
          v = vertices.get(v);
        }
        if (vertices.get(w) == null) {
          vertices.put(w, w);
        } else {
          w = vertices.get(w);
        } 
        if (v.val <= w.val) {
          v.addChild(w);
        } else {
          w.addChild(v);
        } 
      }
      root.countDescendants(); 
      //System.out.println(children); 

      List<Vertex[]> q = new ArrayList<>();
      q.add(new Vertex[] {null, root});
      int count = 0;
      while (!q.isEmpty()) { 
        Vertex[] arr = q.remove(0);
        Vertex parent = arr[0];
        Vertex v = arr[1];
        if (parent != null && v.descendants % 2 == 0) {
          count ++;
          parent._children.remove(v);
        }
        for (Vertex child : v._children) {
          q.add(new Vertex[] {v, child});
        }
      } 
      System.out.println(count);
   }
}

