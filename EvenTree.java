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
      private Vertex _parent;
      private Set<Vertex> _children = new HashSet<>();
      private Vertex _savedParent;
      private Set<Vertex> _savedChildren = new HashSet<>();
      private Vertex(int val) {
         this.val = val;
      }
      public void addChild(Vertex child) {
         _children.add(child);
         _savedChildren.add(child);
         child._parent = this;
         child._savedParent = this;
      }
      public void addTempChild(Vertex child) {
         _children.add(child);
         child._parent = this;
      }
      public void clear() {
         _children.clear();
         _parent = null;
      }
      public void reset() {
         _children.clear();
         _children.addAll(_savedChildren);
         _parent = _savedParent;
      }

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
         for (Vertex v : _children) {
            v.toString(sb, indent+2);
         } 
      }
      public int hashCode() {
         return val;
      }
      public boolean equals(Object o) {
         Vertex other = (Vertex) o;
         return val == other.val;
      }
   }
   private static class Edge {
      final Vertex from;
      final Vertex to;
      Edge(Vertex from, Vertex to) {
         this.from = from;
         this.to = to;
      }
      public Vertex other(Vertex v) {
         return v.equals(from) ? to : from;
      }
      public String toString() {
         return from.val + "->" + to.val;
      }
   }
   private static Vertex get(Map<Integer, Vertex> cache, int n) {
      Vertex v = cache.get(n);
      if (v == null) {
         v = new Vertex(n);
         cache.put(n, v);
      }
      return v;
   }
   private static Vertex buildTree(List<Edge> edges) {
      for (Edge e : edges) {
         e.from.reset();
         e.to.clear();
         e.from.addTempChild(e.to);
      }
      Vertex head = null;
      for (Edge e : edges) {
         if (e.from._parent == null) {
            head = e.from;
            break;
         }
         if (e.to._parent == null) {
            head = e.to;
            break;
         }
      }
      return head;
   }
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      String[] toks = in.nextLine().split("\\s+");
      int N = Integer.parseInt(toks[0]); // N is the number of vertices 
      int M = Integer.parseInt(toks[1]); // M is the number of edges.
      List<Edge> edges = new ArrayList<>();
      Map<Integer, Vertex> cache = new HashMap<>();
      for (int i=0; i<M; i++) {
         toks = in.nextLine().split("\\s+");
         int to = Integer.parseInt(toks[0]);
         int from = Integer.parseInt(toks[1]);
         Vertex vto = get(cache, to);
         Vertex vfrom = get(cache, from);
         vfrom.addChild(vto);
         edges.add(new Edge(vfrom, vto));
      }
      System.out.println(edges);
      Vertex head = buildTree(edges);
      System.out.println(head);
   }
}

