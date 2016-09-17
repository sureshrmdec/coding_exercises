/**
our local library needs your help! Given the expected and actual return dates for a library book, create a program that calculates the fine (if any). The fee structure is as follows:

If the book is returned on or before the expected return date, no fine will be charged (i.e.: .
If the book is returned after the expected return day but still within the same calendar month and year as the expected return date, .
If the book is returned after the expected return month but still within the same calendar year as the expected return date, the .
If the book is returned after the calendar year in which it was expected, there is a fixed fine of .
Input Format

The first line contains  space-separated integers denoting the respective , , and  on which the book was actually returned. 
The second line contains  space-separated integers denoting the respective , , and  on which the book was expected to be returned (due date).

Constraints

Output Format

Print a single integer denoting the library fine for the book received as input.

Sample Input

9 6 2015
6 6 2015
Sample Output

45
Explanation

Given the following return dates: 
Actual:  
Expected: 

Because , we know it is less than a year late. 
Because , we know it's less than a month late. 
Because , we know that it was returned late (but still within the same month and year).

Per the library's fee structure, we know that our fine will be . We then print the result of  as our output.

*/



import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LibraryFine {                                                                       
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int returnedDay = in.nextInt();
        int returnedMonth = in.nextInt();
        int returnedYear = in.nextInt();
        GregorianCalendar returnedDate = new GregorianCalendar(returnedYear, returnedMonth-1, returnedDay);
        int dueDay = in.nextInt();
        int dueMonth = in.nextInt();
        int dueYear = in.nextInt();
        GregorianCalendar dueDate = new GregorianCalendar(dueYear, dueMonth-1, dueDay);
        
        if (returnedDate.getTimeInMillis () <= dueDate.getTimeInMillis()) {
            System.out.println(0);
        } else if (returnedYear <= dueYear && returnedMonth <= dueMonth) {
            System.out.println(15 * (returnedDay - dueDay));
        } else if (returnedYear <= dueYear) {
            System.out.println(500 * (returnedMonth - dueMonth));
        } else {
            System.out.println(10000);
        }
    }
}

