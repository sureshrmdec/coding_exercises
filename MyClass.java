import java.io.*;
class MyClass {
    public static void hash_string(String s, String t) {
      long started = System.currentTimeMillis();
        char[] schars = s.toCharArray(); 
        int slen = schars.length;
        long shash = hash(schars, 0, slen);
        char[] tchars = t.toCharArray();
        int count = 0;
        int max = tchars.length - slen;
        for (int i=0; i<=max; i++) {
          if (schars[0] != tchars[i] || schars[slen-1] != tchars[i+slen-1]) continue;
          long thash = hash(tchars, i, slen);
          if (shash == thash) {
            i += slen-1;
            count++; 
          } else if (s.equals(new String(tchars, i, slen))) {
             System.out.println("matched at ooo " + i + " hash " + shash + "/" + thash);
          }
        }
        System.out.println(count);
    }
    private static long hash(char[] chars, int offset, int len) {
      long hash = 0;
      for (int i=0; i<len; i++) {
         int ch = chars[i+offset];
         long sum = (ch * exp10(len-1-i, 104677)) % 104677;
         hash = (hash + sum) % 104677;
      }
      return hash;
    }

    private static long[] cacheExp = new long[200000];
    private static long exp10(int b, int m) {
       if (cacheExp[b] != 0) {
          return cacheExp[b];
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
       cacheExp[b] = y;
       return y;
   }
   public static void main(String[] args) throws Exception { 
      //hash_string("ab", "cab bac abab");
      // hash_string("iygagthaucgillepyihg","bbfptjtchptfrqntttiygagiygagthaucgillepyihgthaucgillepyihiygagthaucgillepyihggpblyntsuunfmmkwiygagthaucgillepyihgeiygagthaucgillepyihgaiyhbdqadkfawabfegddkuyfnfwfdlbsgwjxnkehuxastaspvggfcuqjzggtymwbbb");
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

