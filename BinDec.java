import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BinDec {

  public static String toBinary(int x) {
	StringBuilder sb = new StringBuilder();
	while (x != 0) {
		sb.append(x % 2 == 0 ? "0" : "1");
		x = x / 2;
	}
	return sb.toString();
  }
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int x  = scanner.nextInt();
    System.out.println(toBinary(x) + "\n");
  }
}
