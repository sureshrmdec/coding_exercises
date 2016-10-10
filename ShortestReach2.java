/**  
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 *
Given a graph consisting  nodes (labelled  to ) where a specific given node  represents the starting position  and an edge between two nodes is of a given length, which may or may not be equal to other lengths in the graph.

It is required to calculate the shortest distance from the start position (Node ) to all of the other nodes in the graph.

Note: If a node is unreachable , the distance is assumed as .

Input Format

The first line contains , denoting the number of test cases. 
First line of each test case has two integers , denoting the number of nodes in the graph and , denoting the number of edges in the graph.

The next  lines each consist of three space-separated integers   , where  and  denote the two nodes between which the undirected edge exists,  denotes the length of edge between these corresponding nodes.

The last line has an integer , denoting the starting position.

Constraints 
 
 
 
 

If there are edges between the same pair of nodes with different weights, they are to be considered as is, like multiple edges.

Output Format

For each of the  test cases, print a single line consisting  space separated integers denoting the shortest distance of  nodes other than  from starting position  in increasing order of their labels.

For unreachable nodes, print .

Sample Input

1
4 4
1 2 24
1 4 20
3 1 3
4 3 12
1
Sample Output

24 3 15
Explanation

The graph given in the test case is shown as :

Graph

The straight line is a weighted edge, denoting length of edge between the corresponding nodes.
The nodes S,B,C and D denote the obvious node 1,2,3 and 4 in the test case.
The shortest paths followed for the three nodes B,C and D are as follows :

S->B - Shortest Path Value : 

S->C - Shortest Path Value : 

S->C->D - Shortest Path Value : 
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ShortestReach2 {
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
		  Map<Edge, Integer> edges = new HashMap<>();        
			for (int j=0; j<E; j++) {
			  int u = in.nextInt() - 1; // we number from 0..N-1
			  int w = in.nextInt() - 1;
			  int weight = in.nextInt(); 
			  int source = Math.min(u, w);
			  int dest = Math.max(u, w);
				Edge edge = new Edge(graph.vertices.get(source), graph.vertices.get(dest), weight);
				Integer old = edges.get(edge);
        if (old == null || old > weight) {
				  edges.put(edge, weight);
        }
		  }
			for (Edge edge : edges.keySet()) {
				graph.edges.put(edge, edge);
				Edge edge2 = new Edge(edge.destination, edge.source, edge.weight);
				graph.edges.put(edge2, edge2);
		  }
      //System.out.println("V " + V + ", E " + E + ", non-duplicate edges " + edges.size());
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
