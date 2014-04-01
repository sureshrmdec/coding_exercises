/*

Unique sequence

Given an array of integer numbers

Your task is to

    write a function that prints to the standard output the length of the longest sequence of consecutive elements that doesn’t contain any duplicate numbers

Note that your function will receive the following arguments:

    numbers
        the array of integer numbers mentioned above

Data constraints

    the length of the array will not exceed 10,000

Example
Input   Output

numbers: [1, 2, 3, 3, 1, 2, 3, 6, 9, 5, 7, 7]
  

7

Explanation

The longest sequence of consecutive elements with no duplicate numbers is 1 2 3 6 9 5 7 and has length 7



*/


import java.io.*;
import java.util.*;

public class UniqueSequence {
  public static void get_longest_sequence(Integer[] numbers) {
    Map<Integer, Integer> positionsByNumber = new HashMap<Integer, Integer>();
    Map<Integer, Integer> numbersByPosition = new HashMap<Integer, Integer>();
    int max = Integer.MIN_VALUE;
    int maxStart = 0;
    int maxFinish = 0;
    int runningMax = 0;
    int start = 0;
    for (int i=0; i<numbers.length; i++) {
      Integer pos = positionsByNumber.get(numbers[i]);
      if (pos == null) {
        runningMax++;
      } else {
        if (runningMax > max) {
          max = runningMax;
          maxStart = start;
          maxFinish = i-1;
        }
        start = pos + 1;
        runningMax = i - start + 1;
        for (int j=0;j<start; j++) {
          Integer number = numbersByPosition.remove(j);
          if (number != null) {
            positionsByNumber.remove(number);
          }
        }
      }
      numbersByPosition.put(i, numbers[i]);
      positionsByNumber.put(numbers[i], i);
    }
    System.out.println(max);
  }

  private static Integer[] parse(String line) {
    String[] toks = line.split("\\D+");
    List<Integer>numbers = new ArrayList<Integer>();
    for (int i=0; i<toks.length; i++) {
      String tok = toks[i].trim();
      if (tok.length() > 0) {
        numbers.add(Integer.parseInt(tok));
      }
    }
    return numbers.toArray(new Integer[numbers.size()]);
  }
  public static void main(String[] args) throws Exception {
    //get_longest_sequence(new Integer[] {1, 2, 3, 3, 1, 2, 3, 6, 9, 5, 7, 7});
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    Integer[] numbers = parse(in.readLine());
    in.close();
    get_longest_sequence(numbers);
    //get_longest_sequence(new Integer[] {1, 2, 3, 1, 5, 2, 4, 1});
  }
}
