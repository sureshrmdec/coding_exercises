import java.util.*;



//When typing on touch screen, occasionally the wrong key is registered. 
//For example, when typing Hello, G might be registered instead of H. 
//Write a function that given a string return all nearby words. 
//You are given helper function to return nearby_chars(char ch) and is_word(string)
public class NearbySpelling {
  interface WordLookup {
    boolean isWord(String w);
  }

  public static List<String> nearbyWords(String word) {
    if (word == null || word.length() == 0) {
      return Arrays.asList("");
    }
    return nearbyPerms(word, 0, 
        new WordLookup(){ 
          public boolean isWord(String w) {
            return true;
          }
    });
  }
  //
  private static List<String> nearbyPerms(String word, int n, WordLookup wordLookup) {
    if (n >= word.length()) {
      return Arrays.asList("");
    }
    List<String> subwords = nearbyPerms(word, n+1, wordLookup);
    char[] nearby = nearbyLetters(word.charAt(n));
    List<String> perms = new ArrayList<>();
    System.out.println(word.charAt(n) + ": " + nearby.length + ", " + Math.pow(nearby.length, word.length()));
    for (String w : subwords) {
      for (char c : nearby) {
        String newWord = c + w;
        if (wordLookup.isWord(newWord)) {
          perms.add(newWord);
        }
      }
    }
    return perms;
  }

  private static char[] nearbyLetters(char c) {
    c = Character.toLowerCase(c);
    String[] keyboard = {"qwertyuiop","asdfghjkl","zxcvbnm"};
    int row = -1;
    int col = -1;
    for (int i=0; i<keyboard.length; i++) {
      col = keyboard[i].indexOf(c);
      if (col != -1) {
        row = i;
        break;
      }
    }
    if (row == -1) {
      return new char[0];
    }
    int[][] neighbors = {{row,col-1}, {row,col+1}, {row-1,col-1}, {row-1,col}, {row-1,col+1}, {row+1,col-1}, {row+1,col}, {row+1,col+1}};
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<neighbors.length; i++) {
      if (neighbors[i][0] >= 0 && neighbors[i][0] < keyboard.length && neighbors[i][0] < keyboard.length &&
        neighbors[i][1] >= 0 && neighbors[i][1] < keyboard[neighbors[i][0]].length()) {
        sb.append(keyboard[neighbors[i][0]].charAt(neighbors[i][1]));
      }
    }
    return sb.toString().toCharArray();
  }

  public static void main(String[] args) {
    List<String> nearby = nearbyWords(args[0]);
    System.out.println(nearby.size() + ": " + nearby);
  }
}
