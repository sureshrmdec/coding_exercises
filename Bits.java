public class Bits {
  static boolean getBit(int n, int index) {
    return (n & (1<<index)) > 0;
  }

  static int setBit(int n, int index, boolean b) {
    if (b) {
      return n | (1 << index);
    } else {
      int mask = ~(1 << index);
      return n & mask;
    }
  }

  static void demoGetSet() {
    int n = 0;
    for (int i=0; i<32; i++) {
      n = setBit(n, i, true);
      System.out.println(i + ": " + Integer.toBinaryString(n));
    }
  }

  static void demoShift() {
    int n = ~0;
    int m = n << 1;
    System.out.println(Integer.toBinaryString(n) + " << 1 = " + Integer.toBinaryString(m));
    n = ~0;
    m = n >> 1;
    System.out.println(Integer.toBinaryString(n) + " >>1 =  " + Integer.toBinaryString(m));
  }
  static void demoOrAnd() {
    int n = 8;
    int m = 10;
    int x = n|m;
    System.out.println(Integer.toBinaryString(n) + " | " + Integer.toBinaryString(m) + " = " + Integer.toBinaryString(x));
    n = 8;
    m = 10;
    x = n&m;
    System.out.println(Integer.toBinaryString(n) + " & " + Integer.toBinaryString(m) + " = " + Integer.toBinaryString(x));
  }
  static void demoTilde() {
    int n = ~0;
    int m = ~n;
    System.out.println(Integer.toBinaryString(n) + " ~ = " + Integer.toBinaryString(m));
  }
  public static void main(String[] args) {
    demoShift(); 
    demoTilde(); 
    demoOrAnd(); 
    demoGetSet(); 
  }
}
