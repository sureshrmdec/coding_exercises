import java.util.*;

public class Heap {
   private static final int CAPACITY = 2;
   private int size; 
   private int[] heap;

   public Heap() {
      size = 0;
      heap = new int[CAPACITY];
   }

   public Heap(int[] array) {
      size = array.length;
      heap = new int[array.length+1];
      System.arraycopy(array, 0, heap, 1, array.length);//we do not use 0 index
      buildHeap();
   }
   private void buildHeap() {
      for (int k = size/2; k > 0; k--) {
         percolatingDown(k);
      }
   }
   private void percolatingDown(int k) {
      int tmp = heap[k];
      int child;

      for(; 2*k <= size; k = child) {
         child = 2*k;

         if(child != size && heap[child] > heap[child + 1]) child++;
         if(tmp > heap[child])  heap[k] = heap[child];
         else break;
      }
      heap[k] = tmp;
   }

   public void heapSort(int[] array) {
      size = array.length;
      heap = new int[size+1];
      System.arraycopy(array, 0, heap, 1, size);
      buildHeap();

      for (int i = size; i > 0; i--) {
         int tmp = heap[i];
         heap[i] = heap[1];
         heap[1] = tmp;
         size--;
         percolatingDown(1);
      }
      for(int k = 0; k < heap.length-1; k++)
         array[k] = heap[heap.length - 1 - k];
   }

   public int deleteMin() throws RuntimeException {
      if (size == 0) throw new RuntimeException();
      int min = heap[1];
      heap[1] = heap[size--];
      percolatingDown(1);
      return min;
  }

   public void insert(int x) {
      if(size == heap.length - 1) doubleSize();
      int pos = ++size;

      //Percolate up
      for(; pos > 1 && x < heap[pos/2]; pos = pos/2 )
         heap[pos] = heap[pos/2];

      heap[pos] = x;
   }
   private void doubleSize()
   {
      int[] old = heap;
      heap = new int[heap.length * 2];
      System.arraycopy(old, 1, heap, 1, size);
   }

   public String toString() {
      String out = "";
      for(int k = 1; k <= size; k++) out += heap[k]+" ";
      return out;
   }

   public static void main(String[] args) {
      Heap h = new Heap();
      h.insert(10);
      h.insert(11);
      h.insert(4);
      h.insert(8);
      System.out.println(h);
      h.deleteMin();
      System.out.println(h);


      Heap tmp = new Heap();
      int[] a = {4,7,7,7,5,0,2,3,5,1};
      tmp.heapSort(a);
      System.out.println(Arrays.toString(a));
   }
}

