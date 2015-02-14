public class Atof {
  public static float atof(String str) {
    if (str == null) return 0;

    str = str.trim();
    if (str.length() == 0) return 0;

    boolean negative = false;
    int decimals = -1;
    double fraction = 0;
    double result = 0;

    for (int i=0; i<str.length(); i++) {
      char ch = str.charAt(i);
      if (i == 0 && ch == '+') {
        negative = false;
      } else if (i == 0 && ch == '-') {
        negative = true;
      } else if (ch == '.' && decimals == -1) {
        decimals = 0;
      } else if (ch >= '0' && ch <= '9') {
        if (decimals >= 0) {
          decimals++;
          double chf = (ch - '0');
          for (int j=0; j<decimals; j++) {
            chf /= 10.0;
          }
          fraction = fraction + chf;
        } else {
          result = result * 10 + (ch - '0');
        }
      } else {
        throw new NumberFormatException(str + " is not a valid number " + i + "/" + ch);
      }
    }

    System.out.println("fraction " + fraction + ", result " + result);
    result = result + fraction;
    if (negative) result = -result;

    if (result > Float.MAX_VALUE) return Float.MAX_VALUE;

    if (result < Float.MIN_VALUE) return Float.MIN_VALUE;

    return (float) result;
  }
  public static void main(String[] args) {
    //System.out.println("Float max " + atof(String.format("%.8f", Float.MAX_VALUE)));
    //System.out.println("Float min " + atof(String.format("%.8f", Float.MIN_VALUE)));
    //System.out.println("Double max " + atof(String.format("%.11f", Double.MAX_VALUE)));
    //System.out.println("Double min " + atof(String.format("%.11f", Double.MIN_VALUE)));

    System.out.println(atof(args[0]));
  }
}
