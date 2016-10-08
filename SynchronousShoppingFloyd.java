/** 
 * https://www.hackerrank.com/challenges/synchronous-shopping
  Bitville is a seaside city that has  shopping centers connected via  bidirectional roads. Each road connects exactly two distinct shopping centers and has a travel time associated with it.

  There are  different types of fish sold in Bitville. Historically, any shopping center has a fishmonger selling certain types of fish. Buying any amount of fish from any fishmonger takes no time.

  Our heroes, Big Cat and Little Cat, are standing at Bitville shopping center number . They have a list of the types of fish sold at each fishmonger, and they want to collectively purchase all  types of fish in a minimal amount of time. To do this, they decide to split the shopping between themselves in the following way:

  Both cats choose their own paths, starting at shopping center  and ending at shopping center . It should be noted that Little Cat's path is not necessarily the same as Big Cat's.
  While traveling their respective paths, each cat will buy certain types of fish at certain shops.
  When the cats reach shopping center , they must have collectively purchased all  types of fish in a minimal amount of time.
  If one cat finishes shopping before the other, he waits at shopping center  for his partner to finish; this means that the total shopping time is the maximum of Little and Big Cats' respective shopping times.
  It is to be noted that any of the cats can visit the shopping center  in between, but they both have to finish their paths at the shopping center .

  Given the layout for Bitville and the list of fish types sold at each fishmonger, what is the minimum amount of time it will take for Big and Little Cat to purchase all  types of fish and meet up at shopping center ?

  Input Format

  The first line contains  space-separated integers:  (the number of shopping centers),  (the number of roads), and  (the number of types of fish sold in Bitville), respectively.

  Each line  of the  subsequent lines () describes a shopping center as a line of space-separated integers. Each line takes the following form:

  The first integer, , denotes the number of types of fish that are sold by the fishmonger at the  shopping center.
  Each of the  subsequent integers on the line describes a type of fish sold by that fishmonger. Which is denoted by .
  Each line  of the  subsequent lines () contains  space-separated integers describing a road. The first two integers,  and , describe the two shopping centers it connects. The third integer, , denotes the amount of time it takes to travel the road (i.e., travel time).

  Constraints

  All  are different for every fixed .
  Each road connectes  distinct shopping centers (i.e., no road connects a shopping center to itself).
  Each pair of shopping centers is directly connected by no more than  road.
  It is possible to get to any shopping center from any other shopping center.
  Each type of fish is always sold by at least one fishmonger.
  Output Format

  Print the minimum amount of time it will take for the cats to collectively purchase all  fish and meet up at shopping center .

  Sample Input

  5 5 5
  1 1
  1 2
  1 3
  1 4
  1 5
  1 2 10
  1 3 10
  2 4 10
  3 5 10
  4 5 10
  Sample Output

  30
  Explanation

  Big Cat can choose the following route: , and buy fish at all of the shopping centers on his way.

  Little Cat can choose the following route: , and buy fish from the fishmonger at the  shopping center only.
  */


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SynchronousShoppingFloyd {
  static boolean debug = true;
  static class Vertex {
    int id;
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

  static class ShoppingCenter extends Vertex {
    int numberOfTypes;
    int[] typesOfFish;

    @Override
    boolean hasAnyTypes(Set<Integer> missingTypes) {
      for (int type: typesOfFish) {
        if (missingTypes.contains(type)) return true;
      }
      return false;
    }
    @Override 
      public String toString() {
        return String.valueOf(id); // + ": " + Arrays.toString(typesOfFish);
      }
  }

  static class Edge {
    Vertex v;
    Vertex w;
    int weight;
    Edge(Vertex v, Vertex w, int weight) {
      this.v = v;
      this.w = w;
      this.weight = weight;
    }
    @Override 
      public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) return false;
        Edge other = (Edge) o;
        return v == other.v && w == other.w;
      }
    @Override
      public int hashCode() {
        return v.id ^ w.id;
      }
    @Override
      public String toString() {
        return v + "->" + w + " = " + weight;
      }
    private String toKey() {
      return v.id + ":" + w.id;
    }
  }


  static class Graph {
    List<Vertex> vertices = new ArrayList<>();
    Map<Edge, Edge> edges = new HashMap<>();        
    int V;
    int E;
    Edge[][] adj;

    Set<Vertex> getNodesWithAnyTypes(Set<Integer> missingTypes) {
      Set<Vertex> nodes = new HashSet<>();
      for (Vertex v : vertices) {
        if (v.hasAnyTypes(missingTypes)) {
          nodes.add(v);
        }
      }
      return nodes;
    }

    class AdjIterator implements Iterator<Edge>, Iterable<Edge> {
      private int v, w = 0;
      public AdjIterator(int v) { this.v = v; }

      public Iterator<Edge> iterator() { return this; }

      public boolean hasNext() {
        while (w < V) {
          if (adj[v][w] != null) return true;
          w++;
        }   
        return false;
      }   

      public Edge next() {
        if (hasNext()) { return adj[v][w++];                 }   
        else           { throw new NoSuchElementException(); }
      }   

      public void remove()  { throw new UnsupportedOperationException();  }
    }

    Graph(int V) {
      if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
      this.V = V;
      this.E = 0;
      this.adj = new Edge[V][V];
      for (int i=0; i<V; i++) {
        this.adj[i] = new Edge[V];
      }
    }

    void addEdge(Edge e) {
      int v = e.v.id;
      int w = e.w.id;
      if (adj[v][w] == null) {
        edges.put(e, e);
        E++;
        adj[v][w] = e;
      }
    }

    public Iterable<Edge> adj(int v) {
      return new AdjIterator(v);
    }
    public Set<Vertex> adjVertices(int v) {
      //List<Edge > list= new ArrayList<>();
      Set<Vertex> set = new HashSet<>();
      for (Edge e : adj(v)) {
        if (e.v.id == v) {
          set.add(e.w);
        } else if (e.w.id == v) {
          set.add(e.v);
        }
        //list.add(e);
      }
      //System.out.println("Adj(" + v + ") " + list + "\n\t" + set);
      return set;
    }

    private int getWeight(List<Vertex> path) {
      if (path.size() < 2) {
        return Integer.MAX_VALUE;
      }
      int weight = 0;
      for (int i=1; i<path.size(); i++) {
        Edge e = new Edge(path.get(i-1), path.get(i), 0);
        Edge existing = edges.get(e);
        weight += existing.weight;
      }
      return weight;
    }

    int breadthFirst(List<Vertex> visited, Set<Integer> missingTypes, Vertex endNode) {
      if (visited.size() == 0) return Integer.MAX_VALUE;
      Vertex last = visited.get(visited.size()-1);
      Collection<Vertex> nodes = adjVertices(last.id); 
      int minWeight = Integer.MAX_VALUE;
      for (Vertex node : nodes) {
        if (visited.contains(node)) {
          continue;
        }
        if (node.equals(endNode)) {
          visited.add(node);
          long weight = getWeight(visited);
          Set<Integer> foundTypes = getFoundTypes(visited);
          System.out.println(">>>(" + node + ")>>>>> new path " + visited + ", weight " + weight + ", found " + foundTypes + ", missing " + missingTypes);
          if (foundTypes.containsAll(missingTypes)) {
            minWeight = Math.min(minWeight, getWeight(visited));
          }
          visited.remove(visited.size()-1);
          break;
        }
      }

      for (Vertex node : nodes) { 
        if(visited.contains(node) || node.equals(endNode)) {
          continue;
        }
        visited.add(node);
        minWeight = Math.min(minWeight, breadthFirst(visited, missingTypes, endNode));
        visited.remove(visited.size()-1);
      }
      return minWeight;
    }    

  }


  static class FloydWarshall {
    private boolean hasNegativeCycle;  // is there a negative cycle?
    private long[][] distTo;  // distTo[v][w] = length of shortest v->w path
    private Edge[][] edgeTo;  // edgeTo[v][w] = last edge on shortest v->w path

    FloydWarshall(Graph G) {
      int V = G.V;
      distTo = new long[V][V];
      edgeTo = new Edge[V][V];

      // initialize distances to infinity
      for (int v = 0; v < V; v++) {
        for (int w = 0; w < V; w++) {
          distTo[v][w] = Integer.MAX_VALUE;
        }
      }

      // initialize distances using edge-weighted digraph's
      for (int v = 0; v < G.V; v++) {
        for (Edge e : G.adj(v)) {
          distTo[e.v.id][e.w.id] = e.weight;
          edgeTo[e.v.id][e.w.id] = e;
        }
        // in case of self-loops
        if (distTo[v][v] >= 0) {
          distTo[v][v] = 0;
          edgeTo[v][v] = null;
        }
      }

      // Floyd-Warshall updates
      for (int i = 0; i < V; i++) {
        // compute shortest paths using only 0, 1, ..., i as intermediate vertices
        for (int v = 0; v < V; v++) {
          if (edgeTo[v][i] == null) continue;  // optimization
          for (int w = 0; w < V; w++) {
            if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
              distTo[v][w] = distTo[v][i] + distTo[i][w];
              edgeTo[v][w] = edgeTo[i][w];
            }
          }
          // check for negative cycle
          if (distTo[v][v] < 0.0) {
            hasNegativeCycle = true;
            return;
          }
        }
      }
    }

    public boolean hasPath(Vertex s, Vertex t) {
      return distTo[s.id][t.id] < Integer.MAX_VALUE;
    }

    public long dist(Vertex s, Vertex t) {
      if (hasNegativeCycle)
        throw new UnsupportedOperationException("Negative cost cycle exists");
      return distTo[s.id][t.id];
    }

    public List<Edge> pathEdges(Vertex s, Vertex t) {
      if (hasNegativeCycle)
        throw new UnsupportedOperationException("Negative cost cycle exists");
      if (!hasPath(s, t)) return null;
      List<Edge> path = new ArrayList<>();
      for (Edge e = edgeTo[s.id][t.id]; e != null; e = edgeTo[s.id][e.v.id]) {
        path.add(0, e);
      }
      return path;
    }


    public List<Vertex> path(Vertex s, Vertex t) {
      if (hasNegativeCycle)
        throw new UnsupportedOperationException("Negative cost cycle exists");
      if (!hasPath(s, t)) return null;
      List<Vertex> path = new ArrayList<>();
      for (Edge e = edgeTo[s.id][t.id]; e != null; e = edgeTo[s.id][e.v.id]) {
        if (path.size() == 0) {
          path.add(0, e.w);
          path.add(0, e.v);
        } else {
          path.add(0, e.v);
        }
      }
      return path;
    }
  }

  static Set<Integer> getFoundTypes(Collection<Vertex> path) {
    Set<Integer> foundTypes = new HashSet<>();
    for (Vertex v : path) {
      ShoppingCenter shoppingCenter = (ShoppingCenter) v;
      for (int i=0; i<shoppingCenter.numberOfTypes; i++) {
        foundTypes.add(shoppingCenter.typesOfFish[i]);
      }
      //if (debug) System.out.println(v);
    }
    return foundTypes; 
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt(); // # of shopping centers
    int M = in.nextInt(); // # of roads
    int K = in.nextInt(); // # of types of fish sold
    Set<Integer> allTypes = new HashSet<>();

    Graph G = new Graph(N);
    for (int i=0; i<N; i++) {
      ShoppingCenter shoppingCenter = new ShoppingCenter();
      shoppingCenter.id = i;
      shoppingCenter.numberOfTypes = in.nextInt();
      shoppingCenter.typesOfFish = new int[shoppingCenter.numberOfTypes];
      for (int j=0; j<shoppingCenter.numberOfTypes; j++) {
        shoppingCenter.typesOfFish[j] = in.nextInt();
        allTypes.add(shoppingCenter.typesOfFish[j]);
      }
      G.vertices.add(shoppingCenter);
    }


    for (int i=0; i<M; i++) {
      int v = in.nextInt() - 1; // we number from 0..N-1
      int w = in.nextInt() - 1;
      int weight = in.nextInt();
      Edge edge1 = new Edge(G.vertices.get(v), G.vertices.get(w), weight);
      G.addEdge(edge1);
      Edge edge2 = new Edge(G.vertices.get(w), G.vertices.get(v), weight);
      G.addEdge(edge2);
    }

    FloydWarshall spt = new FloydWarshall(G); 
    // CHECKING MIN WEIGHT
    Vertex from = G.vertices.get(0);
    Vertex to = G.vertices.get(N-1);

    if (spt.hasPath(from, to)) {
      long weight = spt.dist(from, to);
      for (Edge e : spt.pathEdges(from, to)) {
        //if (debug) System.out.println("\t" + e);
      }
      List<Vertex> path = spt.path(from, to);
      Set<Integer> foundTypes = getFoundTypes(path);
      Set<Integer> missingTypes = new HashSet<>(allTypes);
      missingTypes.removeAll(foundTypes);    

      if (debug) System.out.println("Best weight " + weight + ", foundTypes " + foundTypes + ", missing " + missingTypes + ", path " + path.size());
      if (missingTypes.size() > 0) {
        //List<Vertex> visited = new ArrayList<>();
        //visited.add(from);
        //weight = G.breadthFirst(visited, missingTypes, to);
        Set<Vertex> neighbors = new HashSet<>();
        for (Vertex v : path) {
          neighbors.addAll(G.adjVertices(v.id)); 
        }
        Graph g = new Graph(N);
        long mweight = Integer.MAX_VALUE;
        Set<Vertex> nodesWithMissingTypes = G.getNodesWithAnyTypes(missingTypes); 
        for (Edge edge : G.edges.keySet()) {
          Edge medge = new Edge(edge.v, edge.w, edge.weight);
          if (neighbors.contains(medge.v) || neighbors.contains(medge.w)) {
            g.addEdge(medge);
            //if (path.contains(medge.v) || path.contains(medge.w)) {
            //  medge.weight*=2;
            //} else if (nodesWithMissingTypes.contains(medge.v) || nodesWithMissingTypes.contains(medge.w)) {
            //  medge.weight = 0;
          }
        }
        int n = 0;
        for (Vertex node : neighbors) {
          for (Edge medge : g.edges.keySet()) {
            if (nodesWithMissingTypes.contains(medge.v) || nodesWithMissingTypes.contains(medge.w)) {
              medge.weight = 0;
            } else if (node == medge.v || node == medge.w) {
              medge.weight = g.edges.size()/10;
            } else {
              medge.weight = g.edges.size();
            }
          }

          FloydWarshall mspt = new FloydWarshall(g); 
          List<Vertex> mpath = mspt.path(from, to);
          Set<Integer> mfoundTypes = getFoundTypes(mpath);
          Set<Integer> mmissingTypes = new HashSet<>(allTypes);
          mmissingTypes.removeAll(mfoundTypes);    
          long mmweight = mspt.dist(from, to);


          if (debug) System.out.println(n + "/" + neighbors.size() + ": Next weight " + mmweight + ", foundTypes " + foundTypes + ", missing " + mmissingTypes + ", path " + mpath.size());
          if (mmissingTypes.size() == 0 && mmweight < mweight) {
            mweight = mmweight;
          }
          n++;
        }
        weight = mweight;
        }
        System.out.println(weight);
      }
  }
}
