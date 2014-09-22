import java.util.*;


public class Maze {
  enum Color {
    WHITE, GRAY, BLACK
  }
  private EdgeWeightedGraph G; 
  private Edge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
  private int[] dist;
  private int[] d;      // discovered counter when vertex v is found
  private int[] f;      // finished counter when vertex v is found
  private int[] pred;   // predecessor vertex that can be used to recover a path from source vertex s to vertex v
  private Color[] color;
  int counter;

  public Maze(EdgeWeightedGraph G) {
    this.G = G;
    edgeTo = new Edge[G.V()];
    d = new int[G.V()];
    f = new int[G.V()];
    pred = new int[G.V()];
    dist = new int[G.V()];
    color = new Color[G.V()];
    for (int v = 0; v < G.V(); v++) {
      d[v] = f[v] = pred[v] = -1;
      dist[v] = Integer.MAX_VALUE;
      color[v] = Color.WHITE;
    }
  }
  public List<Integer> getPathUsingBfs(int s, int t) {
    color[s] = Color.GRAY;
    dist[s] = 0;
    Queue<Integer> q = new Queue<>();
    q.enqueue(s);
    while (!q.isEmpty()) {
      int u = q.dequeue();
      for (Edge e : G.adj(u)) {
        int v = e.other(u);
        if (color[v] == Color.WHITE) {
          System.out.println("u " + u + ", v " + v);
          dist[v] = dist[u] + 1;
          pred[v] = u;
          color[v] = Color.GRAY;
          q.enqueue(v);
        }
      }
      color[u] = Color.BLACK;
    }
    return predToPath(s, t);
  }
  private List<Integer> predToPath(int s, int t) {
    List<Integer> path = new ArrayList<>();
    int p = pred[t];
    path.add(0, t);
    while (p != 1 && p != -1) {
      path.add(0, p);
      p = pred[p];
    }
    path.add(0, s);
    return path;
  }

  public List<Integer> getPathUsingDfs(int s, int t) {
    dfs(s);
    for (int v = 0; v < G.V(); v++) {
      if (color[v] == Color.WHITE) { // disconnected
        dfs(v);
      }
    }
    return predToPath(s, t);
  }

  private void dfs(int u) {
    color[u] = Color.GRAY;
    d[u] = ++counter;
    for (Edge e : G.adj(u)) {
      int v = e.other(u);
      if (color[v] == Color.WHITE) {
        //System.out.println("u " + u + ", v " + v);
        pred[v] = u;
        dfs(v);
      }
    }
    color[u] = Color.BLACK;
    f[u] = ++counter;
  }

  // javac Maze.java ;java -cp . Maze data/mediumEWG.txt 
  // javac Maze.java ;java -cp . Maze data/tinyEWG.txt 
  public static void main(String[] args) {
    In in = new In(args[0]);
    EdgeWeightedGraph G = new EdgeWeightedGraph(in);
    Maze m = new Maze(G);
    //System.out.println(m.getPathUsingDfs(1, 4));
    //System.out.println(m.getPathUsingBfs(1, 4));
    System.out.println(m.getPathUsingDfs(1, 7));
    //System.out.println(m.getPathUsingBfs(1, 7));
  }

}

