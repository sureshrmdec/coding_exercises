/**
 * https://www.hackerrank.com/challenges/primsmstsub
 Given a graph which consists of several edges connecting the  nodes in it. 
 It is required to find a subgraph of the given graph with the following properties:

 The subgraph contains all the nodes present in the original graph.
 The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
 It is also required that there is exactly one, exclusive path between any two nodes of the subgraph.
 One specific node  is fixed as the starting point of finding the subgraph. 
 Find the total weight of such a subgraph (sum of all edges in the subgraph)

 Input Format

 First line has two integers , denoting the number of nodes in the graph and , denoting the number of edges in the graph.

 The next  lines each consist of three space separated integers   , where  and  denote the two nodes between which the undirected edge exists,  denotes the length of edge between the corresponding nodes.

 The last line has an integer , denoting the starting node.

 Constraints 




 If there are edges between the same pair of nodes with different weights, they are to be considered as is, like multiple edges.

 Output Format

 Print a single integer denoting the total weight of tree so obtained (sum of weight of edges).

 Sample Input

 5 6
 1 2 3
 1 3 4
 4 2 6
 5 2 2
 2 3 5
 3 5 7
 1
 Sample Output

 15
 Explanation

 The graph given in the test case is shown as :

 Graph

 The nodes A,B,C,D and E denote the obvious 1,2,3,4 and 5 node numbers.

 The starting node is A or 1 (in the given test case)

 Applying the Prim's algorithm, edge choices available at first are :

 A->B (WT. 3) and A->C (WT. 4) , out of which A->B is chosen (smaller weight of edge).

 Now the available choices are :

 A->C (WT. 4) , B->C (WT. 5) , B->E (WT. 2) and B->D (WT. 6) , out of which B->E is chosen by the algorithm.

 Following the same method of the algorithm, the next chosen edges , sequentially are :

 A->C and B->D.

 Hence the overall sequence of edges picked up by prims are:

 A->B : B->E : A->C : B->D

 and Total weight of the hence formed MST is : 15 
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PrimSpecialSubtree {
  static boolean debug = true;
  static class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int NMAX;        // maximum number of elements on PQ
    private int N;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    public IndexMinPQ(int NMAX) {
      if (NMAX < 0) throw new IllegalArgumentException();
      this.NMAX = NMAX;
      keys = (Key[]) new Comparable[NMAX + 1];    // make this of length NMAX??
      pq   = new int[NMAX + 1];
      qp   = new int[NMAX + 1];                   // make this of length NMAX??
      for (int i = 0; i <= NMAX; i++) qp[i] = -1;
    }

    public boolean isEmpty() { return N == 0; }

    public boolean contains(int i) {
        if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    public int size() {
      return N;
    }

    public void insert(int i, Key key) {
      if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
      if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
      N++;
      qp[i] = N;
      pq[N] = i;
      keys[i] = key;
      swim(N);
    }

    public int minIndex() { 
      if (N == 0) throw new NoSuchElementException("Priority queue underflow");
      return pq[1];        
    }

    public Key minKey() { 
      if (N == 0) throw new NoSuchElementException("Priority queue underflow");
      return keys[pq[1]];        
    }

    public int delMin() { 
      if (N == 0) throw new NoSuchElementException("Priority queue underflow");
      int min = pq[1];        
      exch(1, N--); 
      sink(1);
      qp[min] = -1;            // delete
      keys[pq[N+1]] = null;    // to help with garbage collection
      pq[N+1] = -1;            // not needed
      return min; 
    }

    public Key keyOf(int i) {
      if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      else return keys[i];
    }

    public void changeKey(int i, Key key) {
      if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      keys[i] = key;
      swim(qp[i]);
      sink(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
      if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      if (keys[i].compareTo(key) <= 0) throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
      keys[i] = key;
      swim(qp[i]);
    }

    public void increaseKey(int i, Key key) {
      if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      if (keys[i].compareTo(key) >= 0) throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
      keys[i] = key;
      sink(qp[i]);
    }

    public void delete(int i) {
      if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      int index = qp[i];
      exch(index, N--);
      swim(index);
      sink(index);
      keys[i] = null;
      qp[i] = -1;
    }

    /**************************************************************
     * General helper functions
     **************************************************************/
    private boolean greater(int i, int j) {
      return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
      int swap = pq[i]; pq[i] = pq[j]; pq[j] = swap;
      qp[pq[i]] = i; qp[pq[j]] = j;
    }


    /**************************************************************
     * Heap helper functions
     **************************************************************/
    private void swim(int k)  {
      while (k > 1 && greater(k/2, k)) {
        exch(k, k/2);
        k = k/2;
      }
    }

    private void sink(int k) {
      while (2*k <= N) {
        int j = 2*k;
        if (j < N && greater(j, j+1)) j++;
        if (!greater(k, j)) break;
        exch(k, j);
        k = j;
      }
    }

    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
      // create a new pq
      private IndexMinPQ<Key> copy;

      // add all elements to copy of heap
      // takes linear time since already in heap order so no keys move
      public HeapIterator() {
        copy = new IndexMinPQ<Key>(pq.length - 1);
        for (int i = 1; i <= N; i++)
          copy.insert(pq[i], keys[pq[i]]);
      }

      public boolean hasNext()  { return !copy.isEmpty();                     }
      public void remove()      { throw new UnsupportedOperationException();  }

      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return copy.delMin();
      }
    }
  }


  static class Vertex {
    int id;
    Vertex(int id) {
      this.id = id;
    }

    @Override 
    public boolean equals(Object o) {
      if (o == null || o.getClass() != getClass()) return false;
      Vertex other = (Vertex) o;
      return id == other.id;
    }
    @Override
    public int hashCode() {
      return id;
    }
    @Override
    public String toString() {
      return String.valueOf(id);
    }
    boolean hasAnyTypes(Set<Integer> missingTypes) {
      return false;
    }
  }
  static class Edge {
    Vertex source;
    Vertex destination;
    int weight;
    Edge(Vertex source, Vertex destination, int weight) {
      this.source = source;
      this.destination = destination;
      this.weight = weight;
    }
    @Override 
    public boolean equals(Object o) {
      if (o == null || o.getClass() != getClass()) return false;
      Edge other = (Edge) o;
      return source == other.source && destination == other.destination;
    }
    @Override
    public int hashCode() {
      return source.id ^ destination.id;
    }
    @Override
    public String toString() {
      return source + " => " + destination + " = " + weight;
    }
    public int other(int vertex) {
      if (vertex == source.id) return destination.id;
      else if (vertex == destination.id) return source.id;
      else throw new IllegalArgumentException("Illegal endpoint");
    }
    private String toKey() {
      return source.id + ":" + destination.id;
    }
  }
  static class Graph {
    private final int V;
    private int E;
    private List<Edge>[] adj;

    /**
     * Create an empty edge-weighted graph with V vertices.
     */
    public Graph(int V) {
      if (V < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
      this.V = V;
      this.E = 0;
      adj = (List<Edge>[]) new List[V];
      for (int v = 0; v < V; v++) {
        adj[v] = new ArrayList<Edge>();
      }
    }

    public int V() {
      return V;
    }

    public int E() {
      return E;
    }

    /**
     * Add the undirected edge e to this graph.
     */
    public void addEdge(Edge e) {
      int v = e.source.id;
      int w = e.destination.id;
      adj[v].add(e);
      adj[w].add(e);
      E++;
    }


    public Iterable<Edge> adj(int v) {
      return adj[v];
    }

    public Iterable<Edge> edges() {
      List<Edge> list = new ArrayList<Edge>();
      for (int v = 0; v < V; v++) {
        int selfLoops = 0;
        for (Edge e : adj(v)) {
          if (e.other(v) > v) {
            list.add(e);
          }
          // only add one copy of each self loop
          else if (e.other(v) == v) {
            if (selfLoops % 2 == 0) list.add(e);
            selfLoops++;
          }
        }
      }
      return list;
    }



    /**
     * Return a string representation of this graph.
     */
    public String toString() {
      String NEWLINE = System.getProperty("line.separator");
      StringBuilder s = new StringBuilder();
      s.append(V + " " + E + NEWLINE);
      for (int v = 0; v < V; v++) {
        s.append(v + ": ");
        for (Edge e : adj[v]) {
          s.append(e + "  ");
        }
        s.append(NEWLINE);
      }
      return s.toString();
    }

  }

  private Edge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
  private int[] distTo;      // distTo[v] = weight of shortest such edge
  private boolean[] marked;     // marked[v] = true if v on tree, false otherwise
  private IndexMinPQ<Integer> pq;

  /**
   * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
   * @param G the edge-weighted graph
   */
  public PrimSpecialSubtree(Graph G) {
    edgeTo = new Edge[G.V()];
    distTo = new int[G.V()];
    marked = new boolean[G.V()];
    pq = new IndexMinPQ<Integer>(G.V());
    for (int v = 0; v < G.V(); v++) distTo[v] = Integer.MAX_VALUE;

    for (int v = 0; v < G.V(); v++)      // run from each vertex to find
      if (!marked[v]) prim(G, v);      // minimum spanning forest
  }

  // run Prim's algorithm in graph G, starting from vertex s
  private void prim(Graph G, int s) {
    distTo[s] = 0;
    pq.insert(s, distTo[s]);
    while (!pq.isEmpty()) {
      int v = pq.delMin();
      scan(G, v);
    }
  }

  // scan vertex v
  private void scan(Graph G, int v) {
    marked[v] = true;
    for (Edge e : G.adj(v)) {
      int w = e.other(v);
      if (marked[w]) continue;         // v-w is obsolete edge
      if (e.weight < distTo[w]) {
        distTo[w] = e.weight;
        edgeTo[w] = e;
        if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
        else                pq.insert(w, distTo[w]);
      }
    }
  }

  public Iterable<Edge> edges() {
    List<Edge> mst = new ArrayList<Edge>();
    for (int v = 0; v < edgeTo.length; v++) {
      Edge e = edgeTo[v];
      if (e != null) {
        mst.add(e);
      }
    }
    return mst;
  }

  public int weight() {
    int weight = 0;
    for (Edge e : edges())
      weight += e.weight;
    return weight;
  }

  // javac PrimMST.java ;java -cp . PrimMST data/mediumEWG.txt 
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt(); // # of nodes
    int M = in.nextInt(); // # of edges 

    Graph graph = new Graph(N+1);
    for (int i=0; i<M; i++) {
      Vertex x = new Vertex(in.nextInt());
      Vertex y = new Vertex(in.nextInt());
      Edge e = new Edge(x, y, in.nextInt());
      graph.addEdge(e);
    }
    int start = in.nextInt();
    PrimSpecialSubtree mst = new PrimSpecialSubtree(graph);
    System.out.println(mst.weight());
  }
}


