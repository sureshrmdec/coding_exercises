/*
 * https://www.hackerrank.com/challenges/quicksort1
 * The previous challenges covered Insertion Sort, which is a simple and
 * intuitive sorting algorithm with an average case performance of . In these
 * next few challenges, we're covering a divide-and-conquer algorithm called
 * Quicksort (also known as Partition Sort).
 *
 * Step 1: Divide 
 * Choose some pivot element, , and partition your unsorted array, , into three
 * smaller arrays: , , and , where each element in , each element in , and each
 * element in .
 *
 * Challenge 
 * Given  and , partition  into , , and  using the Divide instructions above.
 * Then print each element in  followed by each element in , followed by each
 * element in  on a single line. Your output should be space-separated.
 *
 * Note: There is no need to sort the elements in-place; you can create two
 * lists and stitch them together at the end.
 *
 * Input Format
 *
 * The first line contains  (the size of ). 
 * The second line contains  space-separated integers describing  (the unsorted
 * array). The first integer (corresponding to ) is your pivot element, .
 *
 * Constraints
 *
 * All elements will be unique.
 * Multiple answer can exists for the given test case. Print any one of them.
 * Output Format
 *
 * On a single line, print the partitioned numbers (i.e.: the elements in
 * , then the elements in , and then the elements in ). Each integer should be
 * separated by a single space.
 *
 * Sample Input
 *
 * 5
 * 4 5 3 7 2
 * Sample Output
 *
 * 3 2 4 5 7
 * Explanation
 *
 *  
 *  Pivot: . 
 *  ; ; 
 *
 *  , so it's added to . 
 *  ; ; 
 *
 *  , so it's added to . 
 *  ; ; 
 *
 *  , so it's added to . 
 *  ; ; 
 *
 *  , so it's added to . 
 *  ; ; 
 *
 *  We then print the elements of , followed by , followed by , we get:
 *  3 2 4 5 7.
 *
 *  This example is only one correct answer based on the implementation shown,
 *  but it is not the only correct answer (e.g.: another valid solution would
 *  be 2 3 4 5 7).
 *  */


import java.util.*;
public class Quicksort1 {
	static void swap(int[] arr, int i, int j) {
		//System.out.println("\t swap " + i + ", " + j);
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	static void partition(int[] arr) {
		if (arr == null || arr.length == 0) return;
		quickSort(arr, 0, arr.length-1);
        printArray(arr);
	}   
	static void quickSort(int[] arr, int lowerIndex, int higherIndex) {
		int pivot = arr[lowerIndex];
        int left = lowerIndex;
        for (int i=lowerIndex; i<=higherIndex; i++) {
			if (arr[i] < pivot) {
                int t = arr[i];
                for (int j=i; j>left; j--) {
                    swap(arr, j, j-1);
                }
                arr[left] = t;
                left++;
            }
		}
	}
	static void quickSortm(int[] arr, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;
		int pivot = arr[lowerIndex + (higherIndex-lowerIndex)/2];
		// Divide into two arrays
		while (i <= j) {
			/**
			 * In each iteration, we will identify a number from left side which 
			 * is greater then the pivot value, and also we will identify a number 
			 * from right side which is less then the pivot value. Once the search 
			 * is done, then we exchange both numbers.
			 */
			while (arr[i] < pivot) {
				i++;
			}
			while (arr[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swap(arr, i, j);
				//move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		//if (lowerIndex < j)
		//    quickSort(lowerIndex, j);
		//if (i < higherIndex)
		//    quickSort(i, higherIndex);
	}

	static void printArray(int[] ar) {
		for(int n: ar){
			System.out.print(n+" ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for(int i=0;i<n;i++){
			ar[i]=in.nextInt(); 
		}
		partition(ar);
	}    
}

