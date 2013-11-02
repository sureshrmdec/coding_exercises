public class ReverseString {
  public static void invertString(char[] input, int offset, int len) {
      int n = 0;
      for (int i=0; i<len/2; i++) {
          char t = input[offset+i];
          input[offset+i] = input[offset+len-i-1];
          input[offset+len-i-1] = t;
          n++;
      }
  }
  
  public static void reverseWords(char[] input) {
      int len = input.length;
      invertString(input, 0, len);

      int start = 0;
      while (start < len) {
          int end = start;
          while (end < len && input[end] != ' ') {
              end++;
          }
          invertString(input, start, end-start);
          start = end + 1;
      }
  }

  public static void main(String[] args) {
      char[] input = args[0].toCharArray();
      reverseWords(input);
      System.out.println(new String(input));
  }
}
