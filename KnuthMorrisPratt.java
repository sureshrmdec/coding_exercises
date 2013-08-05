
public class KnuthMorrisPratt {
	public int[] prekmp(String pattern) {
		int[] next = new int[pattern.length()];
		int i=0, j=-1;
		next[0]=-1;
		while (i<pattern.length()-1) {
			while (j>=0 && pattern.charAt(i)!=pattern.charAt(j))
				j = next[j];
			i++; 
			j++;
			next[i] = j;
		}
		return next;
	}
	
	public int kmp(String text, String pattern) {
		int[] next = prekmp(pattern);
		int i=0, j=0;
		while (i<text.length()) { 
			while (j>=0 && text.charAt(i)!=pattern.charAt(j))
				j = next[j];
			i++; j++;
			if (j==pattern.length()) return i-pattern.length();
		}
		return -1;
	}

    public static void main(String[] args) {
        KnuthMorrisPratt k = new KnuthMorrisPratt();
        int ndx = k.kmp(args[0], args[1]);
        System.out.println(ndx);
    }
}
