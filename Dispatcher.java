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

class Vertex {
  final String id;
  Vertex(String id) {
    this.id = id;
  }
  public int hashCode() {
      return id.hashCode();
  }
  public boolean equals(Object o) {
      Vertex other = (Vertex) o;
      return id.equals(other.id);
  }
}
class Edge {
  final Vertex from;
  final Vertex to;
  final int length;
  Edge(Vertex from, Vertex to, int length) {
    this.from = from;
    this.to = to;
    this.length = length;
  }
}

class Graph {
  final List<Vertex> vertexes = new ArrayList<Vertex>();
  final List<Edge> edges = new ArrayList<Edge>();
  final Set<Vertex> settled = new HashSet<Vertex>();
  final Set<Vertex> unSettled = new HashSet<Vertex>();
  final Map<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();
  final Map<Vertex, Integer> distance = new HashMap<Vertex, Integer>();

  public void execute(Vertex source) {
    distance.put(source, 0);
    unSettled.add(source);
    while (unSettled.size() > 0) {
      Vertex node = getMinimum(unSettled);
      settled.add(node);
      unSettled.remove(node);
      findMinimalDistances(node);
    }
  }

  private void findMinimalDistances(Vertex node) {
    List<Vertex> adjacentNodes = getNeighbors(node);
    for (Vertex target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(node)
          + getDistance(node, target)) {
        distance.put(target, getShortestDistance(node)
            + getDistance(node, target));
        predecessors.put(target, node);
        unSettled.add(target);
      }
    }
  }

  private int getDistance(Vertex node, Vertex target) {
    for (Edge edge : edges) {
      if (edge.from.equals(node)
          && edge.to.equals(target)) {
        return edge.getWeight();
      }
    }
    throw new RuntimeException("Should not happen");
  }

  private List<Vertex> getNeighbors(Vertex node) {
    List<Vertex> neighbors = new ArrayList<Vertex>();
    for (Edge edge : edges) {
      if (edge.getSource().equals(node)
          && !isSettled(edge.getDestination())) {
        neighbors.add(edge.getDestination());
      }
    }
    return neighbors;
  }

  private Vertex getMinimum(Set<Vertex> vertexes) {
    Vertex minimum = null;
    for (Vertex vertex : vertexes) {
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

  private int getShortestDistance(Vertex destination) {
    Integer d = distance.get(destination);
    if (d == null) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }

  public LinkedList<Vertex> getPath(Vertex target) {
    LinkedList<Vertex> path = new LinkedList<Vertex>();
    Vertex step = target;
    // Check if a path exists
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step);
    }
    // Put it into the correct order
    Collections.reverse(path);
    return path;
  }
}


public class Dispatcher {
  public static void find_closest_car(String[] city_map, Integer[] cars, Integer customer) {
  }
  public static void main(String[] args) throws Exception {
      /*
      typeahead(new String[] {"james", "jBlank"}, new String[] {"j", "jm", "jbl", "JB"});
      */
      BufferedReader in = new BufferedReader(new FileReader("type.dat")); 
      String[] usernames = in.readLine().replaceAll("\"", "").split(",");
      String[] queries = in.readLine().replaceAll("\"", "").split(",");
  }
}
