import java.util.*;

// https://oj.leetcode.com/problems/repeated-dna-sequences/
public class RepeatedDnaSequences {
  public static Collection<String> findRepeatedDnaSequences(String s, int seqLen) {
    Set<String> allSeq = new HashSet<>();
    Set<String> repeatedSeq = new HashSet<>();
    int i = 0;
    int j = seqLen;
    while (j <= s.length()) {
      String substr = s.substring(i, j);
      if (!allSeq.add(substr)) {
        repeatedSeq .add(substr);
      }
      i++;
      j = i + seqLen;
    }
    return repeatedSeq;
  }

  public static void main(String[] args) {
    System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", 10));
  }
}

