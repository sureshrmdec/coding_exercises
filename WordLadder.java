import java.util.*;
// http://www.programcreek.com/
// Given two words (start and end), and a dictionary, find the 
// length of shortest transformation sequence from start to end, such that:
//
// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary
// For example,
//
// Given:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]
// As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.
//
// Note:
// Return 0 if there is no such transformation sequence.
// All words have the same length.
public class WordLadder {
   // buggy
   public static int naiveLadderLength(String start, String end, Set<String> dict) {
      int len=0;
      Set<String> visited = new HashSet<String>();
      int startLen = start.length();
      for(int i=0; i<startLen; i++){
         char[] startArr = start.toCharArray();
         for(char c='a'; c<='z'; c++){
            if(c==start.toCharArray()[i]){
               continue;
            }

            startArr[i] = c;
            String temp = new String(startArr);
            if(dict.contains(temp)){
               System.out.println(temp);
               len++;
               start = temp;
               if(temp.equals(end)){
                  System.out.println("----");
                  return len;
               }
            }
         }
      }

      return len;
   }


   public static int shortestPathLadderLength(String start, String end, Set<String> dict) {
      if (dict.size() == 0)  return 0; 

      LinkedList<String> wordQueue = new LinkedList<String>();
      LinkedList<Integer> distanceQueue = new LinkedList<Integer>();

      wordQueue.add(start);
      distanceQueue.add(1);


      while(!wordQueue.isEmpty()){
         String currWord = wordQueue.pop();
         Integer currDistance = distanceQueue.pop();

         for(int i=0; i<currWord.length(); i++){
            char[] currCharArr = currWord.toCharArray();
            for(char c='a'; c<='z'; c++){
               currCharArr[i] = c;

               String newWord = new String(currCharArr);
               if(newWord.equals(end)){
                  System.out.println(">>>---" + newWord + ": " + (currDistance+1));
                  return currDistance+1;
               }
               if(dict.contains(newWord)){
                  System.out.println(newWord);
                  wordQueue.add(newWord);
                  distanceQueue.add(currDistance+1);
                  dict.remove(newWord);
               }
            }
         }
      }
      return 0;
   }


   public static void main(String[] args) {
      String start = "hit";
      String end = "cog";
      Set<String> dict = new HashSet<String>();
      dict.add("hot");
      dict.add("dot");
      dict.add("dog");
      dict.add("lot");
      dict.add("log");
      //System.out.println(naiveLadderLength(start, end, dict));
      System.out.println(shortestPathLadderLength(start, end, dict));
   }
}
