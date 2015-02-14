public class QuickUnion {
  private int[] id;
  private int[] sz;
  public QuickUnion(int N) {
    id = new int[N];
    sz = new int[N];
    for (int i=0; i<N; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }
  public int root(int i) {
      while (i != id[i]) {
        // compress
        // id[i] = id[id[i]]; // flatten tree so that child points to root
        i = id[i];
      }
      return i;
  }
  // O(N)
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }
  // O(N)
  public void union(int p, int q) {
    int i = root(p);
    int j = root(q);
    id[i] = j;
  }
  // O(log N)
  public void weightedUnion(int p, int q) {
    int i = root(p);
    int j = root(q);
    if (i == j) return;
    if (sz[i] < sz[j]) {
      id[i] = j;
      sz[j] += sz[i];
    } else {
      id[j] = i;
      sz[i] += sz[j];
    }
  }
}
