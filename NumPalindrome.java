public class NumPalindrome {
  public boolean palindrome(int x) {
    //boolean negative = x < 0;
    x = Math.abs(x);
    int y = palindrome(x, x);
    return y != -1;
  }
  
  private int palindrome(int x, int y) {
    System.out.println("   comparing " + x + " and " + y);
    if (x < 0) return -1;
    if (x == 0) return x;
    y = palindrome(x/10, y);
    if (y != -1 && (x % 10 == y % 10)) {
      return y /= 10;
    } else {
      return -1;
    }
  }
  public static void main(String[] args) {
    System.out.println(new NumPalindrome().palindrome(Integer.parseInt(args[0])));
  }
}

