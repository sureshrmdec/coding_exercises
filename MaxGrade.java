/* 
 Given an array with all final grades for a course

Your task is to

    write a function that finds the highest grade and prints this grade to standard output (stdout)

Note that your function will receive the following arguments:

    grades
        which is the list of grades, represented as integer numbers

Data constraints

    the length of the array given as input will not exceed 1000 elements

*/

import java.util.*;
class MaxGrade {
    public static void max_grade(Integer[] grades) {
        Arrays.sort(grades);
        System.out.println(grades[grades.length-1]);
        
    }
}

