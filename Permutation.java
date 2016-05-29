// Permutations = N!
// N Pick K = nPk (5 people sit on 3 chairs) = N! / (N-K)!  
// e.g. 3 letters in alphabets (26) = 26 ** 3
// e.g. 3 letters in alphabets if we need different letters = 26! / (26-3)!
// color code game - blue/yello/white/red/orange/green, pick 4 colors if colors
// cannot be repeated: 6!/(6-4)!
// combinations: How many ways to choose combinations (divide by # of ways you
// can arrange k things)
// n Choose k  = nCk = n!/((n-k)! * k!) -- binomial coefficient -- order doesn't matter
// e.g. handshakes with 4 people = 4C2 = ( 4 2 ) = 4! / ((4-2)! * 2!) = 6
// card game using 36 cards, 4 suits, hand is collection of 9, how many
// hands possible = ( 36 9) = 36! / ((36 - 9)! * 9!) = 

public class Permutation { 
    static void permute( String str ){
        int length = str.length();
        boolean[] used = new boolean[ length ];
        StringBuffer out = new StringBuffer();
        char[] in = str.toCharArray();
        doPermute( in, out, used, length, 0 );
    }
    static void doPermute( char[] in, StringBuffer out,
        boolean[] used, int length, int level ){
        if( level == length ){
            System.out.println( out.toString() );
            return;
        }
        for( int i = 0; i < length; ++i ){
            if( used[i] ) continue;
            out.append( in[i] );
            used[i] = true;
            doPermute( in, out, used, length, level + 1 );
            used[i] = false;
            out.setLength( out.length() - 1 );
        }
    }
    public static void main(String[] args) {
      permute(args[0]);
    }
}
