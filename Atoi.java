public class Atoi {
  public static int atoi(String str) {
    if (str == null) return 0;

    str = str.trim();
    if (str.length() == 0) return 0;

    boolean negative = false;

    long result = 0;

    for (int i=0; i<str.length(); i++) {
      char ch = str.charAt(i);
      if (i == 0 && ch == '+') {
        negative = false;
      } else if (i == 0 && ch == '-') {
        negative = true;
      } else if (ch >= '0' && ch <= '9') {
        result = result * 10 + (ch - '0');
      } else {
        throw new NumberFormatException(str + " is not a valid number");
      }
    }

    if (negative) result = -result;

    if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;

    if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;

    return (int) result;
  }
  public static void main(String[] args) {
    System.out.println("Integer max " + atoi(Integer.MAX_VALUE + ""));
    System.out.println("Integer min " + atoi(Integer.MIN_VALUE + ""));
    System.out.println("Long max " + atoi(Long.MAX_VALUE + ""));
    System.out.println("Long min " + atoi(Long.MIN_VALUE + ""));
    System.out.println(atoi(args[0]));
  }
}
