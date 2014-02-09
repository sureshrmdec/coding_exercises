import java.util.*;
import java.io.*;
/*
 *
 Given a paragraph of text, write a program to find the first shortest sub-segment that contains each of the given k words at least once. A segment is said to be shorter than other if it contains less number of words.

 Ignore characters other than [a-z][A-Z] in the text. Comparison between the strings should be case-insensitive. 

 If no sub-segment is found then the program should output “NO SUBSEGMENT FOUND”.


 Input format :

 First line of the input contains the text.
 Next line contains k , the number of  words given to be searched.
 Each of the next k lines contains a word.


 Output format :

 Print first shortest sub-segment that contains given k words , ignore special characters, numbers.If no sub-segment is found it should return “NO SUBSEGMENT FOUND”

 Sample Input :

 This is a test. This is a programming test. This is a programming test in any language.
 4
 this
 a
 test
 programming

 Sample Output :

 a programming test This

Explanation :
In this test case segment "a programming test. This" contains given four words. You have to print without special characters, numbers so output is "a programming test This".  Another segment "This is a programming test." also contains given  four words but have more number of words. 

Constraint :

Total number of character in a paragraph will not be more than 200,000.
0 < k <= no. of words in paragraph.
0 < Each word length < 15
*/


public class ShortestSubsegment {
  private Map<String, List<Integer>> textMapByWord = new HashMap<String, List<Integer>>();
  private Map<Integer, String> textMapByIndex = new HashMap<Integer, String>();
  public ShortestSubsegment(String text) {
    initText(text);
  }

  private void initText(String text) {
    String[] toks = text.split("[ \\.;:]");
    for (int i=0, j=0;i<toks.length; i++) {
      String tok = toks[i].trim();
      if (tok.length() == 0) {
        continue;
      }
      String ltok = tok.toLowerCase();
      List<Integer> indexes = textMapByWord.get(ltok);
      if (indexes == null) {
        indexes = new ArrayList<Integer>();
        textMapByWord.put(ltok, indexes);
      }
      indexes.add(j);
      textMapByIndex.put(j, tok);
      j++;
    }
  }


  private void search(String[] words) {
    List<Integer> wordIndexes = new ArrayList<Integer>();
    Map<Integer, String> wordMap = new HashMap<Integer, String>();
    for (String w : words) {
      String lw = w.toLowerCase();
      List<Integer> indexes = textMapByWord.get(lw);
      if (indexes == null) {
        System.out.println("NO SUBSEGMENT FOUND");
        return;
      }
      //
      for (int n : indexes) {
        wordIndexes.add(n);
        wordMap.put(n, lw);
      }
    }
    //
    Collections.sort(wordIndexes);

    //
    int head = 0;
    int tail = 0;
    int shortest = Integer.MAX_VALUE;
    for (int i=0; i<wordIndexes.size(); i+=words.length) {
      if (i+words.length >= wordIndexes.size()) {
        break;
      }
      Map<String, Integer> counts = new HashMap<String, Integer>();
      for (int j=i; j<i+words.length; j++) {
        String lw = wordMap.get(j);
        Integer count = counts.get(lw);
        if (count == null) {
          counts.put(lw, 1);
        } else {
          counts.put(lw, count+1);
        }
      }
      if (counts.size() == words.length) {
        int first = wordIndexes.get(i);
        int last = wordIndexes.get(i+words.length-1);
        if ((last-first) < shortest) {
          head = first;
          tail = last;
          shortest = last - first;
        }
      }
    }
    System.out.println("first " + head + ", last " + tail + ", shortest " + shortest);
    for (int i=head; i<=tail; i++) {
       System.out.print(textMapByIndex.get(i));
       if (i == tail) {
         System.out.println();
       } else {
         System.out.print(' ');
       }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
    String text = in.readLine().trim();
    int k = Integer.parseInt(in.readLine().trim());
    String[] words = new String[k];
    for (int i=0; i<k; i++) {
      words[i] = in.readLine().trim();
    }
    new ShortestSubsegment(text).search(words);
  }
}


