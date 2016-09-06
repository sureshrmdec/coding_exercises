import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
Lisa just got a new math workbook. A workbook contains exercise problems, grouped into chapters.

There are  chapters in Lisa's workbook, numbered from  to .
The -th chapter has  problems, numbered from  to .
Each page can hold up to  problems. There are no empty pages or unnecessary spaces, so only the last page of a chapter may contain fewer than  problems.
Each new chapter starts on a new page, so a page will never contain problems from more than one chapter.
The page number indexing starts at .
Lisa believes a problem to be special if its index (within a chapter) is the same as the page number where it's located. Given the details for Lisa's workbook, can you count its number of special problems?

Note: See the diagram in the Explanation section for more details.

Input Format

The first line contains two integers  and  — the number of chapters and the maximum number of problems per page respectively. 
The second line contains  integers , where  denotes the number of problems in the -th chapter.

Constraints

Output Format

Print the number of special problems in Lisa's workbook.

Sample Input

5 3  
4 2 6 1 10
Sample Output

4
*/


public class Workbook {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // number of chapters
        int k = in.nextInt(); // maximum problems per chapter
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = in.nextInt();
        }
        int count = 0;
        int page = 1;
        for (int i=0; i<n; i++) {
            int kp = arr[i] / k;
            if (arr[i] % k != 0) {
                kp++;
            }
            for (int j=0; j<kp; j++) {
                int problemi = j*k + 1;
                int problemj = Math.min(arr[i], problemi + k - 1);
                //System.out.println("Chapter " + (i+1) + " has " + arr[i] + " chapters, problems " + problemi + "-" + problemj + ", page " + page);
                if (page >= problemi && page <= problemj) {
                    count++;
                    //System.out.println("\t\tIncrementing special " + count);                    
                }
                page++;
            }
        }
        System.out.println(count);
    }
}
