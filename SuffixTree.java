// Given a string of N characters, find the longest repeated substring
// genome, cryptography, data compression, repitition in music
// build suffix array + sort array + then binary search suffix array  
// not good for very long substrings
// better algorithm is Manber-Myer algorithm, 
// phase 0: sort on first character using key-indexed counting sort 
// phase i: given array of suffixes sorted on first 2^i-1 characters,
// create array of suffixes sorted on first 2^i characters.
import java.util.*;
public class SuffixTree {
    public static String longestRepatedSubstring(String s) {
        int N = s.length();
        String[] suffixes = new String[N];
        for (int i=0; i<N; i++) {
            suffixes[i] = s.substring(i, N);
        }
        Arrays.sort(suffixes);
        String lrs = "";
        for (int i=0; i<N-1; i++) {
            // find LCP between adjacent suffixes
            int len = lcp(suffixes[i], suffixes[i+1]);
            if (len > lrs.length()) {
                lrs = suffixes[i].substring(0, len);
            }
        }
        return lrs;
    }
    private static int lcp(String a, String b) {
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i; //a.substring(0, i);
            }
        }
        return minLength; //a.substring(0, minLength);
    }
    public static void main(String[] args) {
        System.out.println(longestRepatedSubstring(args[0]));
    }
}

