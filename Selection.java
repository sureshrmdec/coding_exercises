class Selection {
  public static void select_numbers(Integer[] v, Integer k) {
    for (int i=0; i<k; i++) {
      int minIndex = i;
      int minValue = v[i];
      for (int j=i+1; j<v.length; j++) {
        if (v[j] < minValue) {
          minIndex = j;
          minValue = v[j];
        }
      }
      int t = v[i];
      v[i] = v[minIndex];
      v[minIndex] = t;
    }
    for (int i=0; i<k && i<v.length; i++) {
      System.out.println(v[i]);
    }
  }

  public static void main(String[] args) {
    select_numbers(new Integer[] { 9, 1, 3, 7 , 2, 2}, 3);
  }
}
