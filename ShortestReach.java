/**  
 * https://www.hackerrank.com/challenges/bfsshortreach
 Consider an undirected graph consisting of  nodes where each node is labeled from  to  and the edge between any two nodes is always of length . We define node  to be the starting position for a BFS.

 Given  queries in the form of a graph and some starting node, , perform each query by calculating the shortest distance from starting node  to all the other nodes in the graph. Then print a single line of  space-separated integers listing node 's shortest distance to each of the  other nodes (ordered sequentially by node number); if  is disconnected from a node, print  as the distance to that node.

 Input Format

 The first line contains an integer, , denoting the number of queries. The subsequent lines describe each query in the following format:

 The first line contains two space-separated integers describing the respective values of  (the number of nodes) and  (the number of edges) in the graph.
 Each line  of the  subsequent lines contains two space-separated integers,  and , describing an edge connecting node  to node .
 The last line contains a single integer, , denoting the index of the starting node.
 Constraints

 Output Format

 For each of the  queries, print a single line of  space-separated integers denoting the shortest distances to each of the  other nodes from starting position . These distances should be listed sequentially by node number (i.e., ), but should not include node . If some node is unreachable from , print  as the distance to that node.

 Sample Input

 2
 4 2
 1 2
 1 3
 1
 3 1
 2 3
 2
 Sample Output

 6 6 -1
 -1 6
 Explanation

 We perform the following two queries:

 The given graph can be represented as: 
 graph1
 where our start node, , is node . The shortest distances from  to the other nodes are one edge to node , one edge to node , and an infinite distance to node  (which it's not connected to). We then print node 's distance to nodes , , and  (respectively) as a single line of space-separated integers: 6, 6, -1.
 The given graph can be represented as: 
 graph2
 where our start node, , is node . There is only one edge here, so node  is unreachable from node  and node  has one edge connecting it to node . We then print node 's distance to nodes  and  (respectively) as a single line of space-separated integers: -1 6.
Note: Recall that the actual length of each edge is , and we print  as the distance to any node that's unreachable from . 
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ShortestReach {
	static boolean debug = true;
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
				return -1;
			}
			int weight = 0;
			for (int i=1; i<path.size(); i++) {
				Edge e = new Edge(path.get(i-1), path.get(i), 0);
				Edge existing = edges.get(e);
				if (existing == null) {
					return -1;
				} else {
					weight += existing.weight;
				}
			}
			return weight;
		}


		static List<Vertex> toList(Vertex ... arr) {
			List<Vertex> list = new ArrayList<>();
			for (Vertex v : arr) {
				list.add(v);
			}
			return list;
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt(); // # of queries

		for (int i=0; i<q; i++) {
			Graph graph = new Graph();
			int V = in.nextInt(); // # of nodes
		  int E = in.nextInt(); // # of edges
			for (int j=0; j<V; j++) {
				graph.vertices.add(new Vertex(j));
			}
			for (int j=0; j<E; j++) {
			  int source = in.nextInt() - 1; // we number from 0..N-1
			  int dest = in.nextInt() - 1;
			  int weight = 6;
				Edge edge1 = new Edge(graph.vertices.get(source), graph.vertices.get(dest), weight);
				graph.edges.put(edge1, edge1);
				Edge edge2 = new Edge(graph.vertices.get(dest), graph.vertices.get(source), weight);
				graph.edges.put(edge2, edge2);
		  }
			Vertex from = graph.vertices.get(in.nextInt() - 1);
      graph.execute(from);
      boolean printed = false;
			for (int j=0; j<V; j++) {
				Vertex to = graph.vertices.get(j);
				if (from == to) continue;
				int weight = graph.getWeight(to);
        if (printed) System.out.print(" ");
				System.out.print(weight);
        printed = true;
			}
		  System.out.println();
		}
	}
}
