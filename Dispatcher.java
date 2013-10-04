/*
Dispatcher

Uber needs to connect the rider with their closest available car.

Given the city map, the positions of available cars (identified by numeric IDs) and the position of a customer

Your task is to

    write a function that prints to the standard output (stdout) the ID of the closest possible car to the customer
    if there are multiple cars at the same minimum distance print the one with the smallest ID

Note that your function will receive the following arguments:

    city_map
        which is an array of strings representing roads
        each road is represented as the two intersection IDs it connects and the length of the road, separated by commas (e.g. 3,4,12 - the road connects intersection 3 and 4 and has length 12)
        all roads are bidirectional
    cars
        which is an array of integer numbers representing the intersection IDs where each available car is positioned
        the ID of the car is given by the position it appears in the array
    customer
        which is an integer number representing the intersection ID where the customer is waiting

Data constraints

    all intersection IDs and road lengths are integer numbers in the [1, 10000] range
    the lengths of the city_map and cars arrays will not exceed 10,000

Efficiency constraints

    your function is expected to print the requested result and return in less than 2 seconds

Example
Input   Output
city_map: ["1,2,1", "2,3,2", "1,3,1", "3,4,1"]
cars: [2,1]
customer: 4     2

Explanation:

The car positioned at intersection 1 is the closest to the customer. It's ID is 2 because it is the second one from the cars array.
*/

import java.util.*;
import java.io.*;

class Vertex implements Comparable<Vertex> {
  public final int id;
  public Vertex previous;
  public Set<Edge> adjacencies = new HashSet<Edge>();
  public double minDistance = Double.POSITIVE_INFINITY;
  Vertex(int id) {
    this.id = id;
  }
  public int hashCode() {
      return id;
  }
  public boolean equals(Object o) {
      Vertex other = (Vertex) o;
      return id == other.id;
  }

  public String toString() { 
    return "" + id;
  }
  public int compareTo(Vertex other) {
    return Double.compare(minDistance, other.minDistance);
  }
}


class Edge {
  final Vertex from;
  final Vertex to;
  final double length;
  Edge(Vertex from, Vertex to, double length) {
    this.from = from;
    this.to = to;
    this.from.adjacencies.add(this);
    this.to.adjacencies.add(this);
    this.length = length;
  }
  public Vertex other(Vertex v) {
    return v.equals(from) ? to : from;
  }
}

class Graph {
  private List<Vertex> vertexes;
  private List<Edge> edges;

  public Graph(List<Vertex> vertexes, List<Edge> edges) {
    this.vertexes = vertexes;
    this.edges = edges;
  }

  public Vertex computePaths(Vertex customer, Map<Integer, Vertex> cars) {
    customer.minDistance = 0;
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(customer);
    double carDist = Double.POSITIVE_INFINITY;
    Vertex carV = null;
    while (!vertexQueue.isEmpty()) {
      Vertex u = vertexQueue.poll();
      // Visit each edge exiting u
      for (Edge e : u.adjacencies) {
        Vertex v = e.other(u);
        double distanceThroughU = u.minDistance + e.length;
        System.out.println(u + " to " + v + " is " + distanceThroughU + ", mind " + v.minDistance);
        if (distanceThroughU < v.minDistance) {
          vertexQueue.remove(v);
          v.minDistance = distanceThroughU;
          v.previous = u;
          vertexQueue.add(v);
        }
        if (u == customer && cars.get(v.id) != null) {
          if (distanceThroughU < carDist || carV == null) {
            carDist = distanceThroughU;
            carV = v;
          } else if (distanceThroughU == carDist && carV.id > v.id) {
            carV = v;
          }
        }
      }
    }
    return carV;
  }

  public List<Vertex> getShortestPathTo(Vertex target) {
    List<Vertex> path = new ArrayList<Vertex>();
    for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
      path.add(vertex);
    }
    Collections.reverse(path);
    return path;
  }
}


public class Dispatcher {
  public static void find_closest_car(String[] city_map, Integer[] cars, Integer customer) {
    Map<Integer, Vertex> vertexLookup = new HashMap<Integer, Vertex>();
    final List<Vertex> vertexes = new ArrayList<Vertex>();
    final List<Edge> edges = new ArrayList<Edge>();
    for (String road : city_map) {
        String[] toks = road.split(",");
        int fromId = Integer.parseInt(toks[0]);
        int toId = Integer.parseInt(toks[1]);
        Vertex from = vertexLookup.get(fromId);
        Vertex to = vertexLookup.get(toId);
        if (from == null) {
            from = new Vertex(fromId);
            vertexLookup.put(fromId, from);
        }
        if (to == null) {
            to = new Vertex(toId);
            vertexLookup.put(toId, to);
        }

        int len = Integer.parseInt(toks[2]);
        if (!vertexes.contains(from)) {
            vertexes.add(from);
        }
        if (!vertexes.contains(to)) {
            vertexes.add(to);
        }
        edges.add(new Edge(from, to, len));
    }
    Graph graph = new Graph(vertexes, edges);
    Map<Integer, Vertex> carsMap = new HashMap<Integer, Vertex>();
    for (int car : cars) {
        carsMap.put(car, vertexLookup.get(car));
    }
    Vertex customerV = vertexLookup.get(customer);
    Vertex match = graph.computePaths(customerV, carsMap);
    for (int i=0; i<cars.length; i++) {
        if (match.id == cars[i]) {
            System.out.println(i+1);
            break;
        }
    }
  }
  public static void main(String[] args) throws Exception {
    //find_closest_car(new String[]{"1,2,1", "2,3,2", "1,3,1", "3,4,1"}, new Integer[] {2, 1}, 4);
    find_closest_car(new String[]{"1,2,2", "6,2,2", "6,4,1", "5,4,1", "2,5,1", "2,5,1", "5,3,1", "2,3,2"}, new Integer[] {1,6}, 3);
  }
}
