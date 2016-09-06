import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
  See https://www.nayuki.io/page/next-lexicographical-permutation-algorithm

  Given a word , rearrange the letters of  to construct another word  in such a way that  is lexicographically greater than . In case of multiple possible answers, find the lexicographically smallest one among them.

  Input Format

  The first line of input contains , the number of test cases. Each of the next  lines contains .

  Constraints

  will contain only lower-case English letters and its length will not exceed .
  Output Format

  For each testcase, output a string lexicographically bigger than  in a separate line. In case of multiple possible answers, print the lexicographically smallest one, and if no answer exists, print no answer.

  Sample Input

  5
  ab
  bb
  hefg
  dhck
  dkhc
  Sample Output

  ba
  no answer
  hegf
  dhkc
  hcdk
  Explanation

  Test case 1: 
  There exists only one string greater than ab which can be built by rearranging ab. That is ba.
  Test case 2: 
  Not possible to rearrange bb and get a lexicographically greater string.
  Test case 3: 
  hegf is the next string lexicographically greater than hefg.
  Test case 4: 
  dhkc is the next string lexicographically greater than dhck.
  Test case 5: 
  hcdk is the next string lexicographically greater than dkhc.
  */


public class Lexicographical {

  static int[] toArray(String line) {
    int[] array = new int[line.length()];
    int i=0;
    for (char ch : line.toCharArray()) {
      array[i++] = ch;
    }
    return array;
  }
  static String toString(int[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<array.length; i++) {
      sb.append((char)array[i]);
    }
    return sb.toString();
  }
  static boolean nextPermutation(int[] array) {
    // Find longest non-increasing suffix
    int i = array.length - 1;
    while (i > 0 && array[i - 1] >= array[i])
      i--;
    // Now i is the head index of the suffix

    // Are we at the last permutation already?
    if (i <= 0)
      return false;

    // Let array[i - 1] be the pivot
    // Find rightmost element that exceeds the pivot
    int j = array.length - 1;
    while (array[j] <= array[i - 1])
      j--;
    // Now the value array[j] will become the new pivot
    // Assertion: j >= i

    // Swap the pivot with j
    int temp = array[i - 1];
    array[i - 1] = array[j];
    array[j] = temp;

    // Reverse the suffix
    j = array.length - 1;
    while (i < j) {
      temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      i++;
      j--;
    }

    // Successfully computed the next permutation
    return true;
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i=0; i<n; i++) {
      String line = in.next().trim();
      int[] array = toArray(line);
      if (nextPermutation(array)) {

      }
      String resp = toString(array);
      if (line.equals(resp)) {
        System.out.println("no answer");
      } else {
        System.out.println(resp);
      }
    }
  }
}
