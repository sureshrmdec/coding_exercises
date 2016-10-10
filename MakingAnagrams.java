/*
https://www.hackerrank.com/challenges/ctci-making-anagrams
Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words, both strings must contain the same exact letters in the same exact frequency For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.

Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of character deletions required to make the two strings anagrams. Can you help her find this number?

Given two strings,  and , that may or may not be of the same length, determine the minimum number of character deletions required to make  and  anagrams. Any characters can be deleted from either of the strings.

This challenge is also available in the following translations:

Chinese
Russian
Input Format

The first line contains a single string, . 
The second line contains a single string, .

Constraints

It is guaranteed that  and  consist of lowercase English alphabetic letters (i.e.,  through ).
Output Format

Print a single integer denoting the number of characters you must delete to make the two strings anagrams of each other.

Sample Input

cde
abc
Sample Output

4
Explanation

We delete the following characters from our two strings to turn them into anagrams of each other:

Remove d and e from cde to get c.
Remove a and b from abc to get c.
We must delete  characters to make both strings anagrams, so we print  on a new line.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// fsqoiaidfaukvngpsugszsnseskicpejjvytviya
// ksmfgsxamduovigbasjchnoskolfwjhgetnmnkmcphqmpwnrrwtymjtwxget
// 42
public class MakingAnagrams {
	public static int numberNeeded(String first, String second) {
		Map<Character, Integer> counts = new HashMap<>();
		for (Character ch : first.toCharArray()) {
			Integer count = counts.get(ch);
			if (count == null) {
				counts.put(ch, 1);
			} else {
				counts.put(ch, count+1);
			}
		}
		for (Character ch : second.toCharArray()) {
			Integer count = counts.get(ch);
			if (count == null) {
				counts.put(ch, -1);
			} else {
				counts.put(ch, count-1);
			}
		}
		int deletions = 0;
		for (Map.Entry<Character, Integer> e : counts.entrySet()) {
			deletions += Math.abs(e.getValue());
		}
		return deletions;
	}

	public static int numberNeeded(String s) {
		int deletions = 0;
		for (int i=1; i<s.length(); i++) {
			if (s.charAt(i-1) == s.charAt(i)) {
				deletions++;
			}
		}
		return deletions;
	} 
  // Shashank likes strings in which consecutive characters are different. For example, he likes ABABA, 
  // while he doesn't like ABAA. Given a string containing characters  and  only, he wants to change it 
  // into a string he likes. To do this, he is allowed to delete the characters in the string.
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String first = in.next();
		String second = in.next();

		System.out.println(numberNeeded(first, second));
	}
}

