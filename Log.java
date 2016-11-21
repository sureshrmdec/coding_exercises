import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Log {

	static long log2(long x) {
		return log(x, 2);
	}
	static long log(long x, long base) {
		return (long) (Math.log(x) / Math.log(base));
	}

	public static long change(List<Integer> list, int max) {
		long ways[] = new long[max+1];
		ways[0] = 1;
		for (Integer n : list) {
			for (int i=n ; i<= max; i++) {
				ways[i] += ways[i - n];
			}   
		}   
		return ways[max];
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		List<Integer> list = new ArrayList<>();	
		for (int i=2; i<=n; i+=2) {
			list.add(i);
		}
    long ways = change(list, n);
    System.out.println("ways " + ways);
		System.out.println(log2(n));
	}
}
