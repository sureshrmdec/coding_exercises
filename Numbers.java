import java.io.*;


public class Numbers {
  public static void create(File file) throws IOException {
    DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
    for (int i=Integer.MIN_VALUE; i<=Integer.MAX_VALUE; i+=(Integer.MAX_VALUE/1000)) {
      out.write(i);
    }
    out.close();
  }
  private static void printMissing(File file) throws IOException {
    byte[] bitfield = new byte[0xFFFFFFFF/8]; // create bit vector of 4B
    DataInputStream in = new DataInputStream(new FileInputStream(file));
    int n;
    while ((n=in.read()) != -1) {
      bitfield[n/8] |= (1 << (n % 8)); // or nth bit of byte -- 2 to the power of n mod 8
    }
    for (int i=0; i<bitfield.length; i++) {
      for (int j=0; j<8; j++) {
        if ((bitfield[i] & (1 << j)) == 0) { // 1 << j is 2 to the power of j
          System.out.println(i*8+j);
          return;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    //create(new File("data/integers.data"));
    //printMissing(new File("data/integers.data"));
  }
}
