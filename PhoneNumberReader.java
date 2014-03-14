//https://code.google.com/codejam/contest/2924486/dashboard
/*
   Problem

   Do you know how to read the phone numbers in English? Now let me tell you.

   For example, In China, the phone numbers are 11 digits, like: 15012233444. Someone divides the numbers into 3-4-4 format, i.e. 150 1223 3444. While someone divides the numbers into 3-3-5 format, i.e. 150 122 33444. Different formats lead to different ways to read these numbers:

   150 1223 3444 reads one five zero one double two three three triple four.

   150 122 33444 reads one five zero one double two double three triple four.

   Here comes the problem:

   Given a list of phone numbers and the dividing formats, output the right ways to read these numbers.

Rules:

Single numbers just read them separately.

2 successive numbers use double.

3 successive numbers use triple.

4 successive numbers use quadruple.

5 successive numbers use quintuple.

6 successive numbers use sextuple.

7 successive numbers use septuple.

8 successive numbers use octuple.

9 successive numbers use nonuple.

10 successive numbers use decuple.

More than 10 successive numbers read them all separately.

Input

The first line of the input gives the number of test cases, T. T lines|test cases follow. Each line contains a phone number N and the dividing format F, one or more positive integers separated by dashes (-), without leading zeros and whose sum always equals the number of digits in the phone number.

Output

For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is the reading sentence in English whose words are separated by a space.

Limits

1 ≤ T ≤ 100.

Small dataset

1 ≤ length of N ≤ 10.

Large dataset

1 ≤ length of N ≤ 100.
*/


import java.io.*;
import java.util.*;

public class PhoneNumberReader {
  private final int[][] numbers;
  private final Map<Integer, String> successive = new HashMap<Integer, String>() {
    {
      put(2, "double ");
      put(3, "triple ");
      put(4, "quadruple ");
      put(5, "quintuple ");
      put(6, "sextuple ");
      put(7, "septuple ");
      put(8, "octuple ");
      put(9, "nonuple ");
      put(10, "decuple ");
    }
  };
  private final Map<Integer, String> numberSounds= new HashMap<Integer, String>() {
    {
      put(0, "zero");
      put(1, "one");
      put(2, "two");
      put(3, "three");
      put(4, "four");
      put(5, "five");
      put(6, "six");
      put(7, "seven");
      put(8, "eight");
      put(9, "nine");
    }
  };
  public PhoneNumberReader(int[][] numbers) {
    this.numbers = numbers;
  }

  public String rawNumbers() {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<numbers.length; i++) {
      for (int j=0;j<numbers[i].length; j++) {
        sb.append(String.valueOf(numbers[i][j]));
      }
      sb.append(" ");
    }
    return sb.toString();
  }
  public String formattedNumbers() {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<numbers.length; i++) {
      int lastNumber = numbers[i][0];
      int sameLastNumber = 1;
      for (int j=1;j<numbers[i].length; j++) {
        if (numbers[i][j] == lastNumber) {
          sameLastNumber++;
        } else {
          append(sb, lastNumber, sameLastNumber);
          lastNumber = numbers[i][j];
          sameLastNumber = 1;
        }
      }
      append(sb, lastNumber, sameLastNumber);
    }
    return sb.toString();
  }

  private void append(StringBuilder sb, int lastNumber, int sameLastNumber) {
    String prefix = successive.get(sameLastNumber);
    if (sb.length() > 0) {
      sb.append(" ");
    }
    if (prefix != null) {
      sb.append(prefix);
    } else if (sameLastNumber > 10) {
      for (int i=0; i<sameLastNumber - 1; i++) {
        sb.append(numberSounds.get(lastNumber) + " ");
      }
    }
    sb.append(numberSounds.get(lastNumber));
  }


  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    int N = Integer.parseInt(in.readLine().trim());
    int count = 1;
    for (int i=0; i<N; i++) {
      String line = in.readLine();
      String[] toks = line.split("\\s");
      String numbersStr = toks[0].trim();
      String[] patternsStr = toks[1].trim().split("-");
      int[][] numbers = new int[patternsStr.length][];
      int n = 0;
      for (int j=0; j<patternsStr.length; j++) {
        int size = Integer.parseInt(patternsStr[j]);
        numbers[j] = new int[size];
        for (int k=0; k<size; k++) {
          numbers[j][k] = numbersStr.charAt(n++) - '0';
        }
      }
      PhoneNumberReader reader = new PhoneNumberReader(numbers);
      //System.out.println(line);
      //System.out.println("Case #" + count + ": " + reader.rawNumbers() + " : " + reader.formattedNumbers());
      System.out.println("Case #" + count + ": " + reader.formattedNumbers());
      count++;
    }   
    in.close();
  }
}
