class SortedMerge {
  public static void merge_arrays(Integer[] a, Integer[] b) {
    Integer[] output = new Integer[a.length+b.length];
    int ai=0, bi=0, oi=0;
    while (ai < a.length && bi < b.length) {
      if (a[ai] < b[bi]) {
        output[oi++] = a[ai];
        ai++;
      } else if (b[bi] < a[ai]) {
        output[oi++] = b[bi];
        bi++;
      } else { // equal
        output[oi++] = a[ai];
        output[oi++] = b[bi];
        ai++;
        bi++;
      }
    }
    while (ai < a.length) {
      output[oi++] = a[ai];
      ai++;
    }
    while (bi < b.length) {
      output[oi++] = b[bi];
      bi++;
    }
    for (int i=0; i<output.length; i++) {
      System.out.println(output[i]);
    }
  }

  public static void main(String[] args) {
    merge_arrays(new Integer[] {2, 3, 7, 8, 8}, new Integer[] {7, 8, 13});
  }
}
