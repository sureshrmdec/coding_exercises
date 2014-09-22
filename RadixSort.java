import java.util.*;

public class RadixSort {
   public void radixSort(int arr[], int maxDigits){
      int exp = 1;//10^0;
      for(int i =0; i < maxDigits; i++){
         List<Integer> bucketList[] = new ArrayList[10];
         for(int k=0; k < 10; k++){
            bucketList[k] = new ArrayList<Integer>();
         }
         for(int j =0; j < arr.length; j++){
            int number = (arr[j]/exp)%10;
            bucketList[number].add(arr[j]);
         }
         exp *= 10;
         int index =0;
         for(int k=0; k < 10; k++){
            for(int num: bucketList[k]){
               arr[index] = num;
               index++;
            }
         }
      }

      System.out.println("Sorted numbers");
      for(int i =0; i < arr.length; i++){
         System.out.print(arr[i] +", ");
      }
      System.out.println();
   }

   public static void main(String[] argv){
      int n = 5;
      int arr[] = {123,410,21,30,51,100,181};
      new RadixSort().radixSort(arr, 3);
   }
}
