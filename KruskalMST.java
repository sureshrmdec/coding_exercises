import java.util.*;
import java.util.Queue;

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
    return "Vertex(" + id + "," + minDistance + ")";
  }
  public int compareTo(Vertex other) {
    return Double.compare(minDistance, other.minDistance);
  }
}


class Edge implements Comparable<Edge> {
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
public class KruskalMST {
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


  public static void main(String[] args) {
    final Map<Integer, Vertex> vertexLookup = new HashMap<Integer, Vertex>();
    final List<Vertex> vertexes = new ArrayList<Vertex>();
    final List<Edge> edges = new ArrayList<Edge>();
    for (int i=0; i<8; i++) {
      Vertex v = new Vertex(i);
      vertexes.add(v);
      vertexLookup.put(i, v);
    }
    edges.add(new Edge(vertexLookup.get(0), vertexLookup.get(7), 0.16));
    edges.add(new Edge(vertexLookup.get(2), vertexLookup.get(3), 0.17));
    edges.add(new Edge(vertexLookup.get(1), vertexLookup.get(7), 0.19));
    edges.add(new Edge(vertexLookup.get(0), vertexLookup.get(2), 0.26));
    edges.add(new Edge(vertexLookup.get(5), vertexLookup.get(7), 0.28));
    edges.add(new Edge(vertexLookup.get(4), vertexLookup.get(5), 0.35));
    edges.add(new Edge(vertexLookup.get(6), vertexLookup.get(2), 0.4));
    KruskalMST mst = new KruskalMST(edges, vertexes.size());
    System.out.println(mst.length);
  }
}
