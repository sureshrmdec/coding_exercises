//Implement regular expression matching with support for '.' and '*'.


public class Regex {
   private static void assertTrue(boolean b) {
      if (!b) throw new Error("Assertion failed " + b);
   }
   private static void assertFalse(boolean b) {
      if (b) throw new Error("Assertion failed " + b);
   }


   public static boolean isMatch(String s, String p) {
      if(p.length() == 0)
         return s.length() == 0;

      //p's length 1 is special case    
      if(p.length() == 1 || p.charAt(1) != '*'){
         if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
            return false;
         return isMatch(s.substring(1), p.substring(1));    

      }else{
         int len = s.length();

         int i = -1; 
         while(i<len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
            if(isMatch(s.substring(i+1), p.substring(2)))
               return true;
            i++;
         }
         return false;
      } 
   }
   public static void main(String[] args) {
      assertFalse(isMatch("aa","a"));
      assertTrue(isMatch("aa","aa"));
      assertFalse(isMatch("aaa","aa"));
      assertTrue(isMatch("aa", "a*"));
      assertTrue(isMatch("aa", ".*"));
      assertTrue(isMatch("ab", ".*"));
      assertTrue(isMatch("aab", "c*a*b"));
   }
}
