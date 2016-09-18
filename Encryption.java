/**
An English text needs to be encrypted using the following encryption scheme. 
First, the spaces are removed from the text. Let  be the length of this text. 
Then, characters are written into a grid, whose rows and columns have the following constraints:

, where  is floor function and  is ceil function
For example, the sentence if man was meant to stay on the ground god would have given us roots after removing spaces is  characters long, so it is written in the form of a grid with 7 rows and 8 columns.

ifmanwas  
meanttos          
tayonthe  
groundgo  
dwouldha  
vegivenu  
sroots
Ensure that 
If multiple grids satisfy the above conditions, choose the one with the minimum area, i.e. .
The encoded message is obtained by displaying the characters in a column, inserting a space, and then displaying the next column and inserting a space, and so on. For example, the encoded message for the above rectangle is:

imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau

You will be given a message in English with no spaces between the words. The maximum message length can be  characters. Print the encoded message.

Here are some more examples:

Sample Input:

haveaniceday
Sample Output:

hae and via ecy
Sample Input:

feedthedog    
Sample Output:

fto ehg ee dd
Sample Input:

chillout
Sample Output:

clu hlt io
*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// IN:  ifmanwasmeanttostayonthegroundgodwouldhavegivenusroots 
// OUT: imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau
// IN:  feedthedog 
// OUT: fto ehg ee dd
// IN:  chillout 
// OUT: clu hlt io
public class Encryption {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        StringBuilder text = new StringBuilder();
        for (Character ch : s.toCharArray()) {
            if (Character.isWhitespace(ch)) continue;
            text.append(ch);
        }
        s = text.toString();
        int L = s.length();
        double sq = Math.sqrt(L);
        int rows = (int) Math.floor(sq);
        int cols = (int) Math.ceil(sq);
        if (rows * cols < L) {
            rows++;
        }
        //System.out.println("L " + L + ", sq " + sq + ", rows " + rows + ", cols " + cols);
        StringBuilder[] words = new StringBuilder[cols];
        for (int i=0; i<cols; i++) {
            words[i] = new StringBuilder();
        }
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                int k = (i*cols) + j;
                if (k < L) {
                    words[j].append(s.charAt(k));
                }
            }
        }
        for (int i=0; i<words.length; i++) {
            System.out.print(words[i]);
            if (i < words.length-1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}

