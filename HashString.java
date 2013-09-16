

import java.io.*;
class HashString {
   public static void hash_string(String s, String t) {
      int[] schars = toInt(s);
      int slen = schars.length;
      int shash = hash(schars, 0, slen);
      int[] tchars = toInt(t);
      //
      int count = 0;
      int max = tchars.length - slen;
      for (int i=0; i<=max; i++) {
         int thash = hash(tchars, i, slen);
         if (shash == thash) {
            i += slen-1;
            count++;
         }
         if (i % 1000 == 0) System.out.println("i " + i);
      }
      System.out.println(count);
   }


   private static final int[] toInt(String s) {
      int slen = s.length();
      int[] schars = new int[slen];
      for (int i=0; i<slen; i++) {
         schars[i] = s.charAt(i) - 'a';
      }
      return schars;
   }
   private static final int hash(int[] chars, int offset, int len) {
      int hash = 0;
      for (int i=0; i<len; i++) {
         int ch = chars[i+offset];
         hash = (hash + ch * pow10(len-1-i, 104677)) % 104677;
      }
      return hash;
   }


   private static int[] cachedPow = new int[200000];
   private static final int pow10(int b, int m) {
      if (cachedPow[b] != 0) {
         return cachedPow[b];
      }
      long x= 10;
      long y = b % 2 == 1 ? 10 : 1;
      long n = b / 2;
      while (n > 0) {
         x = (x * x) % m;
         if (n % 2 == 1) {
            y = y == 1 ? x : (y * x) % m;
         }
         n = n / 2;
      }
      return (cachedPow[b] = (int) y);
   }
   //

   public static void main(String[] args) throws Exception { 
      //hash_string("ab", "cab bac abab");
      //hash_string("iygagthaucgillepyihg","bbfptjtchptfrqntttiygagiygagthaucgillepyihgthaucgillepyihiygagthaucgillepyihggpblyntsuunfmmkwiygagthaucgillepyihgeiygagthaucgillepyihgaiyhbdqadkfawabfegddkuyfnfwfdlbsgwjxnkehuxastaspvggfcuqjzggtymwbbb");
      //hash_string("ezbsbihgctxp","gtarxcxpmqkcukjbqujciithezbsbihgctxpxqwzcgitlsgzyknjncoiybtiev");
      BufferedReader in = new BufferedReader(new FileReader(args[0])); 
      String[] tokens = in.readLine().replaceAll("\"", "").split(",");
      long started = System.currentTimeMillis();
      //tokens[0] = "ab";
      //tokens[1] = "cab bac abab";
      hash_string(tokens[0], tokens[1]);
      System.out.println("finished processing " + tokens[0].length() + "/" + tokens[1].length() + " in " + (System.currentTimeMillis()-started));
   }
}

