import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class UtopianTree {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = Integer.parseInt(in.nextLine());
    int[] tests = new int[N];
    for (int i=0; i<N; i++) {
      tests[i] = Integer.parseInt(in.nextLine());
    }
    for (int i=0; i<N; i++) {
      int height = 1;
      for (int j=0; j<tests[i]; j++) {
        if (j % 2 == 0) {
          height *= 2;
        } else {
          height++;
        }
      }
      System.out.println(height);
    }
  }
}

