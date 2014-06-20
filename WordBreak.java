// http://www.programcreek.com/
// Given a string s and a dictionary of words dict, determine if s can be segmented 
// into a space-separated sequence of one or more dictionary words.
public class WordBreak {
   // O(N^2)
   public boolean wordBreakNaive(String s, Set<String> dict) {
      return wordBreakHelper(s, dict, 0);
   }
   // O(len-of-string+ size-of-dict)
   public boolean wordBreakDP(String s, Set<String> dict) {
      boolean[] t = new boolean[s.length()+1];
      t[0] = true;

      for(int i=0; i<s.length(); i++){
         if(!t[i]) continue;

         for(String a: dict){
            int len = a.length();
            int end = i + len;
            if(end > s.length()) continue;
            if(t[end]) continue;
            if(s.substring(i, end).equals(a)){
               t[end] = true;
            }
         }
      }

      return t[s.length()];
   } 
   private boolean wordBreakHelper(String s, Set<String> dict, int start){
      if(start == s.length()) return true;

      for(String a: dict){
         int len = a.length();
         int end = start+len;
         if(end > s.length()) continue;

         if(s.substring(start, start+len).equals(a))
            if(wordBreakHelper(s, dict, start+len)) return true;
      }

      return false;
   }
}
