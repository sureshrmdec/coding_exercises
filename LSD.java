// N + R running time -- sort 1 million 32-bit integers
public class LSD {
    public static void sort(String[] a, int W) {
        int R = 256; // radix R
        int N = a.length;
        String[] aux = new String[N];
        for (int d=W-1; d>=0; d--) { // do key-indexed counting from right to left
            int[] count = new int[R+1];
            for (int i=0; i<N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int r=0; r<R; r++) {
                count[r+1] += count[r];
            }
            for (int i=0; i<N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i=0; i<N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        sort(args, 3);
        for (int i=0; i<args.length; i++) {
            System.out.println(args[i]);
        }
    }
}

