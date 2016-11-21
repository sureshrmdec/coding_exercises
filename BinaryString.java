import java.util.*;

public class BinaryString {
  static void toBinary(int n, StringBuilder sb) {
    if (n <= 1) {
      sb.insert(0, String.valueOf(n));
      return;
    }
    int rem = n % 2;
    sb.insert(0, String.valueOf(rem));
    toBinary(n >> 1, sb);
  }
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    int n = Integer.parseInt(args[0]);
    toBinary(n, sb);
    System.out.println("BIN:  " + sb);
    System.out.println("BIN2: " + Integer.toBinaryString(n));
  }
}

