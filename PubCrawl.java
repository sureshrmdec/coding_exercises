
/*
Pub crawl

How to visit as many pubs as possible in one night while spending as little money as possible on Uber rides?

Given a list of roads connecting pubs

Your task is to

    write a function that prints to the standard output (stdout) the minimum possible median cost for a tour (two decimal precision)
    a tour can start at any pub in the city, but must end at the same one where it started
    the median cost of a tour is defined as the sum of all roads lengths you travelled through divided by the number of pubs you visited

Note that your function will receive the following arguments:

    city_map
        which is an array of strings representing roads
        each road is represented as the two pub IDs it connects and the length of the road, separated by commas (e.g. “3,4,12” - the road connects pub 3 to pub 4 and has length 12)
        all roads are unidirectional - they connect the first pub to the second one only

Data constraints

    the length of the array above will not exceed 15000 entries
    all pub IDs will be smaller than 1000

Efficiency constraints

    your function is expected to print the requested result and return in less than 2 seconds

Example
Input   Output
city_map: ["1,2,1","2,3,1","1,3,1","3,4,2","4,1,3"]   1.75

*/

import java.util.*;
import java.io.*;
import java.util.Queue;

class Vertex {
  public final int id;
  public Vertex previous;
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
    return "Vertex(" + id + ")";
  }
}


class Edge implements Comparable<Edge> {
  final Vertex from;
  final Vertex to;
  final double length;
  Edge(Vertex from, Vertex to, double length) {
    this.from = from;
    this.to = to;
    this.length = length;
  }
  public Vertex other(Vertex v) {
    return v.equals(from) ? to : from;
  }
  public int compareTo(Edge that) {
    if (this.length < that.length) return -1;
    else if (this.length > that.length) return +1;
    else return  0;
  }
}

class UF {
  public int[] id;     // id[i] = parent of i
  public byte[] rank;  // rank[i] = rank of subtree rooted at i (cannot be more than 31)
  public int count;    // number of components

  public UF(int N) {
    if (N < 0) throw new IllegalArgumentException();
    count = N;
    id = new int[N];
    rank = new byte[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      rank[i] = 0;
    }
  }
  public int find(int p) {
    if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
    while (p != id[p]) {
      id[p] = id[id[p]];    // path compression by halving
      p = id[p];
    }
    return p;
  }
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
  public void union(int p, int q) {
    int i = find(p);
    int j = find(q);
    if (i == j) return;
    // make root of smaller rank point to root of larger rank
    if      (rank[i] < rank[j]) id[i] = j;
    else if (rank[i] > rank[j]) id[j] = i;
    else {
      id[j] = i;
      rank[i]++;
    }
    count--;
  }
}
class KruskalMST {
  public double length;  // length of MST
  public final Queue<Edge> mst = new PriorityQueue<Edge>();  // edges in MST
  public KruskalMST(Collection<Edge> edges, int V) {
    PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    for (Edge e : edges) {
      pq.add(e);
    }

    UF uf = new UF(V);
    while (!pq.isEmpty() && mst.size() < V - 1) {
      Edge e = pq.remove();
      if (!uf.connected(e.from.id, e.to.id)) { // v-w does not create a cycle
        uf.union(e.from.id, e.to.id);  // merge v and w components
        mst.add(e);  // add edge e to mst
        length += e.length;
      }
    }

    // check optimality conditions
    assert check(edges, V);
  }

  private boolean check(Collection<Edge> edges, int V) {
    // check total length
    double total = 0.0;
    for (Edge e : edges) {
      total += e.length;
    }
    double EPSILON = 1E-12;
    if (Math.abs(total - length) > EPSILON) {
      System.err.printf("length of edges does not equal length: %f vs. %f\n", total, length);
      return false;
    }

    // check that it is acyclic
    UF uf = new UF(V);
    for (Edge e : edges) {
      if (uf.connected(e.from.id, e.to.id)) {
        System.err.println("Not a forest");
        return false;
      }
      uf.union(e.from.id, e.to.id);
    }

    // check that it is a spanning forest
    for (Edge e : edges) {
      if (!uf.connected(e.from.id, e.to.id)) {
        System.err.println("Not a spanning forest");
        return false;
      }
    }

    // check that it is a minimal spanning forest (cut optimality conditions)
    for (Edge e : edges) {
      // all edges in MST except e
      uf = new UF(V);
      for (Edge f : mst) {
        if (f != e) uf.union(f.from.id, f.to.id);
      }

      // check that e is min length edge in crossing cut
      for (Edge f : edges) {
        if (!uf.connected(f.from.id, f.to.id)) {
          if (f.length < e.length) {
            System.err.println("Edge " + f + " violates cut optimality conditions");
            return false;
          }
        }
      }

    }
    return true;
  }
}
public class PubCrawl {
  public static void get_best_tour(String[] city_map) {
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
    KruskalMST mst = new KruskalMST(edges, vertexes.size()+1);
    Vertex first = null;
    Vertex last = null;
    for (Edge e : mst.mst) {
      if (last == null) {
        last = e.from;
      }
      first = e.to;
      System.out.println("\tfrom " + e.from + " to " + e.to + ", len " + e.length + ", mst " + mst.length);
    }
    Edge back = null;
    for (Edge e : edges) {
      if (e.from == first && e.to == last) {
        back = e;
        break;
      }
    }
    System.out.println((mst.length+back.length) / (mst.mst.size()+1));
  }

  public static void main(String[] args) throws Exception {
    //get_best_tour(new String[] {"1,2,1","2,3,1","1,3,1","3,4,2","4,1,3"}); //1.75
    get_best_tour(new String[] {"10,9,1","9,2,2","2,10,4","2,7,2","7,9,4","7,6,2","6,2,3","7,10,13","4,5,16","5,10,16","5,4,14","8,8,14","3,5,14","7,3,12","5,2,15","3,6,12","7,4,12","3,1,15","4,2,12","3,8,15","3,7,12","9,8,14","8,4,16","9,3,13","9,9,16","3,3,15","5,9,16","1,4,16","4,6,13","1,10,15","6,5,14","4,7,13","8,5,15","5,6,12","10,2,12","6,7,14","2,8,12"}); // 2.33
  }
}
