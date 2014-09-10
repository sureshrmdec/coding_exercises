import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.Stack;

/*
   Topological Sorting
   Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
   For example, a topological sorting of the following graph is “5 4 2 3 1 0″. There can be more than one topological sorting for a graph. For example, another topological sorting of the following graph is “4 5 2 3 1 0″. The first vertex in topological sorting is always a vertex with in-degree as 0 (a vertex with no in-coming edges).

   graph

   Topological Sorting vs Depth First Traversal (DFS):
   In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. In topological sorting, we need to print a vertex before its adjacent vertices. For example, in the given graph, the vertex ’5′ should be printed before vertex ’0′, but unlike DFS, the vertex ’4′ should also be printed before vertex ’0′. So Topological sorting is different from DFS. For example, a DFS of the above graph is “5 2 3 1 0 4″, but it is not a topological sorting

   Algorithm to find Topological Sorting:
   We recommend to first see implementation of DFS here. We can modify DFS to find Topological Sorting of a graph. In DFS, we start from a vertex, we first print it and then recursively call DFS for its adjacent vertices. In topological sorting, we use a temporary stack. We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, then push it to a stack. Finally, print contents of stack. Note that a vertex is pushed to stack only when all of its adjacent vertices (and their adjacent vertices and so on) are already in stack.

*/

public class TopologicalSorting {
   private List<Integer>[] adjacents;
   TopologicalSorting(int v) {
      adjacents = (List<Integer>[]) new ArrayList[v];
      for (int i=0; i<v; i++) {
         adjacents[i] = new ArrayList<>();
      }
   }


   public void addEdge(int v, int w) {
      adjacents[v].add(w);
   }


   public void topologicalSort() {
      Stack<Integer> stack = new Stack<>();
      boolean[] visited = new boolean[adjacents.length];
      for (int i = 0; i < adjacents.length; i++) {
         visited[i] = false;
      }

      for (int i = 0; i < adjacents.length; i++) {
         if (!visited[i]) {
            topologicalSortUtil(i, visited, stack);
         }
      }
      while (!stack.empty()) {
         System.out.println(stack.pop());
      }
   }


   private void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
      visited[v] = true;
      for (int w : adjacents[v]) {
         if (!visited[w]) {
            topologicalSortUtil(w, visited, stack);
         }
      }

      stack.push(v);
   }

   public static void main(String[] args) {
      TopologicalSorting g = new TopologicalSorting(6);
      g.addEdge(5, 2);
      g.addEdge(5, 0);
      g.addEdge(4, 0);
      g.addEdge(4, 1);
      g.addEdge(2, 3);
      g.addEdge(3, 1);

      g.topologicalSort();
   }
}

