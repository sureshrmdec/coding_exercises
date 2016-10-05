/**
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
  See http://algs4.cs.princeton.edu/41graph/BreadthFirstPaths.java.html
  */


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SynchronousShoppingDijkstra {
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
        //return String.valueOf(id); // + ": " + Arrays.toString(typesOfFish);
        return String.valueOf(id) + "(" + Arrays.toString(typesOfFish) + ") ";
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
    private String toKey() {
      return source.id + ":" + destination.id;
    }
  }

  static class Graph {
    List<Vertex> vertices = new ArrayList<>();
    Map<Edge, Edge> edges = new HashMap<>();        
    Set<Vertex> settledNodes;
    Set<Vertex> unSettledNodes;
    Map<Vertex, Vertex> predecessors;
    Map<Vertex, Integer> distance;

    Set<Vertex> getNodesWithAnyTypes(Set<Integer> missingTypes) {
      Set<Vertex> nodes = new HashSet<>();
      for (Vertex v : vertices) {
        if (v.hasAnyTypes(missingTypes)) {
          nodes.add(v);
        }
      }
      return nodes;
    }

    public void execute(Vertex source) {
      settledNodes = new HashSet<>();
      unSettledNodes = new HashSet<>();
      predecessors = new HashMap<>();
      distance = new HashMap<>();
      distance.put(source, 0);
      unSettledNodes.add(source);
      while (unSettledNodes.size() > 0) {
        Vertex node = getMinimum(unSettledNodes);
        settledNodes.add(node);
        unSettledNodes.remove(node);
        findMinimalDistances(node);
      }
    }

    private void findMinimalDistances(Vertex node) {
      List<Vertex> adjacentNodes = getNeighbors(node);
      for (Vertex target : adjacentNodes) {
        int d = getShortestDistance(node) + getDistance(node, target);
        if (getShortestDistance(target) > d) {
          distance.put(target, d);
          predecessors.put(target, node);
          unSettledNodes.add(target);
        }
      }
    }
    private int getDistance(Vertex node, Vertex target) {
      Edge e = new Edge(node, target, 0);
      Edge existing = edges.get(e);
      if (existing != null && existing.weight == 0) {
      }
      if (existing != null) {
        return existing.weight;
      }
      throw new RuntimeException("Failed to find distance between " + node + " and " + target);
    }

    private List<Vertex> getNeighbors(Vertex node) {
      List<Vertex> neighbors = new ArrayList<>();
      for (Edge edge : edges.keySet()) {
        if (edge.source == node && !isSettled(edge.destination)) {
          neighbors.add(edge.destination);
        }
      }
      return neighbors;
    }

    private List<Vertex> getAdjacentNeighbors(Vertex node) {
      List<Vertex> neighbors = new ArrayList<>();
      for (Edge edge : edges.keySet()) {
        if (edge.source == node) {
          neighbors.add(edge.destination);
        }
      }
      return neighbors;
    }

    private Vertex getMinimum(Collection<Vertex> vertices) {
      Vertex minimum = null;
      for (Vertex vertex : vertices) {
        if (minimum == null) {
          minimum = vertex;
        } else {
          if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
            minimum = vertex;                        
          }
        }
      }
      return minimum;
    }

    private boolean isSettled(Vertex vertex) {
      return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
      Integer d = distance.get(destination);
      if (d == null) {
        d = Integer.MAX_VALUE;
      }
      return d;
    }

    private List<Vertex> getPath(Vertex target) {
      List<Vertex> path = new ArrayList<>();
      Vertex step = target;
      if (predecessors.get(step) == null) {
        return path;
      }
      path.add(step);
      while ((step = predecessors.get(step)) != null) {
        path.add(step);
      }
      Collections.reverse(path);
      return path;
    }

    private int getWeight(Vertex target) {
      List<Vertex> path = getPath(target);
      return getWeight(path);
    }

    private int getWeight(List<Vertex> path) {
      if (path.size() < 2) {
        return Integer.MAX_VALUE;
      }
      int weight = 0;
      for (int i=1; i<path.size(); i++) {
        Edge e = new Edge(path.get(i-1), path.get(i), 0);
        Edge existing = edges.get(e);
        if (existing != null) {
          weight += existing.weight;
        }
      }
      return weight;
    }


    int breadthFirst(List<Vertex> visited, Set<Integer> missingTypes, Vertex endNode) {
      if (visited.size() == 0) return Integer.MAX_VALUE;
      Vertex last = visited.get(visited.size()-1);
      List<Vertex> nodes = getAdjacentNeighbors(last); 
      int minWeight = Integer.MAX_VALUE;
      for (Vertex node : nodes) {
        if (visited.contains(node)) {
          continue;
        }
        if (node.equals(endNode)) {
          visited.add(node);
          Set<Integer> foundTypes = getFoundTypes(visited);
          if (foundTypes.containsAll(missingTypes)) {
            int weight = getWeight(visited);
            minWeight = Math.min(minWeight, weight);
            if (debug) System.out.println(">>>FOUND (" + node + ")>>>>> weight " + weight + ", found " + foundTypes + ", missing " + missingTypes);
          } else {
            int weight = getWeight(visited);
            if (debug) System.out.println(">>>NOT FOUND(" + node + ")>>>>> weight " + weight + ", found " + foundTypes + ", missing " + missingTypes);
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

    static List<Vertex> toList(Vertex ... arr) {
      List<Vertex> list = new ArrayList<>();
      for (Vertex v : arr) {
        list.add(v);
      }
      return list;
    }


    int bfs(Set<Integer> missingTypes, Vertex from, Vertex to) {
      int minWeight = Integer.MAX_VALUE;
      List<List<Vertex>> q = new ArrayList<>();
      q.add(toList(from));
      //
      while (!q.isEmpty()) {
        List<Vertex> path = q.remove(0);
        Vertex node = path.get(path.size()-1);

        if (node.equals(to)) {
          Set<Integer> foundTypes = getFoundTypes(path);
          if (foundTypes.containsAll(missingTypes)) {
            int weight = getWeight(path);
            minWeight = Math.min(minWeight, weight);
            if (debug) System.out.println(">>>Got all types(" + node + ")>>>>> weight " + weight + ", found " + foundTypes + ", missing " + missingTypes);
          } else {
            int weight = getWeight(path);
            if (debug) System.out.println(">>>Could not find all types (" + node + ")>>>>> weight " + weight + ", found " + foundTypes + ", missing " + missingTypes);
          }
          //continue;
        }
        //
        List<Vertex> nodes = getAdjacentNeighbors(node); 
        for (Vertex w : nodes) {
          if (path.contains(w)) continue;
          List<Vertex> newPath = new ArrayList<Vertex>(path);
          newPath.add(w);
          q.add(newPath);
        }
      }
      return minWeight;
    }  



    int getSecondMinPath(List<Vertex> bestPath, Set<Integer> missingTypes) {
      int minWeight = Integer.MAX_VALUE;
      //
      Vertex from = bestPath.get(0);
      Vertex to = bestPath.get(bestPath.size()-1);
      for (int i=1; i<bestPath.size()-1; i++) {
        Graph graph = new Graph();
        graph.vertices = new ArrayList<Vertex>(vertices);
        graph.edges = new HashMap<Edge, Edge>(edges);
        ShoppingCenter skip = (ShoppingCenter) bestPath.get(i);
        graph.vertices.remove(skip);
        for (Edge e : new ArrayList<Edge>(edges.keySet())) {
          if (e.source.equals(skip) || e.destination.equals(skip)) {
            edges.remove(e);
          }
        }
        graph.execute(from);
        //
        List<Vertex> path = graph.getPath(to);
        if (path.size() <= 2) continue;
        Set<Integer> foundTypes = getFoundTypes(path);
        int weight = graph.getWeight(to);
        if (foundTypes.containsAll(missingTypes)) {
          minWeight = Math.min(minWeight, weight);
          if (debug) System.out.println("Found (" + skip + ") Next path " + path + ", weight " + weight + ", found types " + foundTypes);
        }
      }
      return minWeight;
    }    
  }

  static Set<Integer> getFoundTypes(Collection<Vertex> path) {
    Set<Integer> foundTypes = new HashSet<>();
    for (Vertex v : path) {
      ShoppingCenter shoppingCenter = (ShoppingCenter) v;
      for (int i=0; i<shoppingCenter.numberOfTypes; i++) {
        foundTypes.add(shoppingCenter.typesOfFish[i]);
      }
      //if (debug) System.out.println("\t" + v);
    }
    return foundTypes; 
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt(); // # of shopping centers
    int M = in.nextInt(); // # of roads
    int K = in.nextInt(); // # of types of fish sold
    Set<Integer> allTypes = new HashSet<>();

    Graph graph = new Graph();
    for (int i=0; i<N; i++) {
      ShoppingCenter shoppingCenter = new ShoppingCenter();
      shoppingCenter.id = i;
      shoppingCenter.numberOfTypes = in.nextInt();
      shoppingCenter.typesOfFish = new int[shoppingCenter.numberOfTypes];
      for (int j=0; j<shoppingCenter.numberOfTypes; j++) {
        shoppingCenter.typesOfFish[j] = in.nextInt();
        allTypes.add(shoppingCenter.typesOfFish[j]);
      }
      graph.vertices.add(shoppingCenter);
    }


    for (int i=0; i<M; i++) {
      int source = in.nextInt() - 1; // we number from 0..N-1
      int dest = in.nextInt() - 1;
      int weight = in.nextInt();
      Edge edge1 = new Edge(graph.vertices.get(source), graph.vertices.get(dest), weight);
      graph.edges.put(edge1, edge1);
      Edge edge2 = new Edge(graph.vertices.get(dest), graph.vertices.get(source), weight);
      graph.edges.put(edge2, edge2);
    }

    // CHECKING MIN WEIGHT
    Vertex from = graph.vertices.get(0);
    Vertex to = graph.vertices.get(N-1);
    graph.execute(from);

    List<Vertex> path = graph.getPath(to);
    Set<Integer> foundTypes = getFoundTypes(path);
    int weight = graph.getWeight(to);

    Set<Integer> missingTypes = new HashSet<>(allTypes);
    missingTypes.removeAll(foundTypes);    

    if (debug) System.out.println("Best weight " + weight + ", foundTypes " + foundTypes + ", missing " + missingTypes + ", edges " + graph.edges.size() + ", path " + path.size());


    if (missingTypes.size() > 0) {
      //
      //List<Vertex> visited = new ArrayList<>();
      //visited.add(from);
      //int mweight = graph.breadthFirst(visited, missingTypes, to);
      int mweight = graph.bfs(missingTypes, from, to); 
      //weight = graph.getSecondMinPath(path, missingTypes); 
      /*
         Set<Vertex> nodesWithMissingTypes = graph.getNodesWithAnyTypes(missingTypes); 
         int mweight = Integer.MAX_VALUE;
         for (Vertex v : nodesWithMissingTypes) {
      //if (v == from || v == to) continue;
      Graph mgraph = new Graph();
      for (Edge edge : graph.edges.keySet()) {
      //if (edge.source == v || edge.destination == v) continue;
      Edge medge1 = new Edge(edge.source, edge.destination, edge.weight);
      mgraph.edges.put(medge1, medge1);
      Edge medge2 = new Edge(edge.destination, edge.source, edge.weight);
      mgraph.edges.put(medge2, medge2);
      }

      for (Edge medge : mgraph.edges.keySet()) {
      if (v == medge.source || v == medge.destination) {
      medge.weight = 0;
      } else {
      medge.weight = mgraph.edges.size() * 10000;
      }
      }
      mgraph.execute(from);
      //
      List<Vertex>  mpath = mgraph.getPath(to);
      foundTypes = getFoundTypes(mpath);
      Set<Integer> mmissingTypes = new HashSet<>(allTypes);
      mmissingTypes.removeAll(foundTypes);
      int mmweight = mgraph.getWeight(mpath);
      if (debug) System.out.println("Next weight " + mmweight + ", foundTypes " + foundTypes + ", missing " + mmissingTypes + ", edges " + mgraph.edges.size() + ", path " + mpath.size());
      if (mmissingTypes.size() == 0 && mmweight < mweight) {
      mweight = mmweight;
      }
         }
         */
      weight = mweight;
    }
    System.out.println(weight);
  }
}
