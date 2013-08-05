// Implement a function that prints all possible combinations of the 
// characters in a string. These combinations range in length from 
// one to the length of the string. Two combinations that differ 
// only in ordering of their characters are the same combination.
// In other words, 12 and 31 are different combinations from the 
// input string 123, but 21 is the same as 12.
public class Combine {
    public void combine(String str) {
        int length = str.length();
        char[] instr = str.toCharArray();
        StringBuilder outstr = new StringBuilder();
        doCombine(instr, outstr, length, 0, 0);
    }
    private void doCombine(char[] instr, StringBuilder outstr, int length,
        int level, int start ){
        for(int i = start; i < length; i++){
            outstr.append(instr[i]);
            System.out.println(outstr);
            if(i < length - 1) {
                doCombine(instr, outstr, length, level + 1, i + 1);
            }
            outstr.setLength(outstr.length() - 1);
        }
    }

    public static void main(String[] args) {
        new Combine().combine(args[0]);
    }
}
