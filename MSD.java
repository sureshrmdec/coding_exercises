public class MSD {
    public static void sort(String[] a) {
        String[] aux = new String[a.length];
        sort(a, aux, 0, a.length-1, 0);
    }

    private static void sort(String[] a, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) return;
        int R = 256; // radix R

        int[] count = new int[R+2];
        for (int i=lo; i<=hi; i++) {
            int ch = a[i].charAt(d);
            count[ch + 2]++;
        }
        for (int r=0; r<R+1; r++) {
            count[r+1] += count[r];
        }
        for (int i=lo; i<=hi; i++) {
            aux[count[a[i].charAt(d) + 1]++] = a[i];
        }
        for (int i=lo; i<=hi; i++) {
            a[i] = aux[i-lo];
        }
        int RR = 3;
        for (int r=0; r<RR; r++) {
            sort(a, aux, lo+count[r], lo+count[r+1]-1, d+1);
        }
    }

    public static void main(String[] args) {
        sort(args);
        for (int i=0; i<args.length; i++) {
            System.out.println(args[i]);
        }
    }
}

