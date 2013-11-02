public class Parity {
 // parity of number is 1 if number of 1 bits is odd, otherwise 0
  public static short parity(long a) {
    long result = 0;
    for (; a != 0; a = a >> 1) {
        result = result ^ (a & 1);
    }
    return (short) result;
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    System.out.println(Integer.toBinaryString(n));
    System.out.println(parity(n));
  }
}


