// Implement a function that prints all possible combinations of 
// the characters in a string. These combinations range in length from one 
// to the length of the string. Two combinations that differ only in 
// ordering of their characters are the same combination.
// In other words, 12 and 31 are different combinations from the 
// input string 123, but 21 is the same as 12.
public class PhoneWords {
    private static final int PHONE_NUMBER_LENGTH = 7;
    public static void printTelephoneWords(int[] phoneNum) {
        char[] result = new char[ PHONE_NUMBER_LENGTH ];
        doPrintTelephoneWords( phoneNum, 0, result );
    }
    private static char getCharKey(int key, int place) {
        char[][] keys = {
            {},
            {'A', 'B', 'C'},
            {'D', 'E', 'F'},
            {'G', 'H', 'I'},
            {'J', 'K', 'L'},
            {'M', 'N', 'O'},
            {'P', 'R', 'S'},
            {'T', 'U', 'V'},
        };
        try {
            return keys[key-1][place-1];
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Key " + key + ", place " + place);
        }
    }


    private static void doPrintTelephoneWords(int[] phoneNum, int curDigit,
        char[] result ){
        if (curDigit == PHONE_NUMBER_LENGTH) {
            System.out.println(new String(result));
            return;
        }
        for( int i = 1; i <= 3; i++ ){
            result[curDigit] = getCharKey( phoneNum[curDigit], i);
            doPrintTelephoneWords(phoneNum, curDigit + 1, result);
            if( phoneNum[curDigit] == 0 || phoneNum[curDigit] == 1) return;
        }
    }
    public static void main(String[] args) {
        printTelephoneWords(new int[] {2, 3, 4, 5, 6, 7, 8});
    }
}
