public class Substring {
   public static void find_substr(String s, String t) {
      for (int i=0; i<t.length()-s.length()+1; i++) {
         String o = t.substring(i, i+s.length());
         if (s.equals(o)) {
            System.out.println(i+1);
            return;
         }
      }
      System.out.println(-1);
   }
   public static void count_substr(String s, String t) {
      int count = 0;
      for (int i=0; i<t.length()-s.length()+1; i++) {
         String o = t.substring(i, i+s.length());
         if (s.equals(o)) {
            count++;
            i+= s.length()-1;
         }
      }
      System.out.println(count);
   }
   //
   //
   //
   //An alphabet is a set of characters. A token is a sequence of consecutive alphabet characters delimited by one or more characters that are not part of the alphabet.
   //
   //For example if the alphabet is "abxy" then "aabby", "bxa" are considered tokens because all their characters belong to the alphabet. While "abd", "efg" are not.
   //
   //Given a string a that contains the letters of an alphabet and a string t
   //
   //Your task is to
   //
   //    write a function that prints to standard output (stdout) the number of tokens found in t
   //
   //    Note that your function will receive the following arguments:
   //
   //        a which is is a string of characters that define the alphabet t
   //        which is the string where you must search for tokens
   //        Input    Output
   //        a: "anmo"
   //        t: "anatomy"
   //        2
   //        a: "elr"
   //        t: "hello there"
   //                                  
   //        2
   //        Explanation
   //
   //      For the first example the tokens are "ana" and "om"
   //      For the second example the tokens are "ell" and "ere"
   //
   //
   public static void count_tokens(String a, String t) {
      int count = 0;
      int matched = 0;
      for (int i=0; i<t.length(); i++) {
         char ch = t.charAt(i);
         if (a.contains(ch)) {
            matched++;
         } else {
            if (matched > 0) { 
               count++;
            }
            matched = 0;
         }
      }
      if (matched > 0) {
         count++;
      }
      System.out.println(count);
   }
