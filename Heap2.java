import java.util.*;

public class Heap2 {
  private static final int CAPACITY = 2;
  private int size; 
  private int[] heap;

  public Heap2() {
    size = 0;
    heap = new int[CAPACITY];
  }
  public void heapify(int arr[]){
    this.heap = arr;
    int limit = Math.floor(arr.length/2.0);
    for(int i=0;i<limit;i++){
      int idx = i;
      while(idx<size){
        int left = idx*2+1;
        int right = idx*2+2;
        if(left<size && arr[left]>arr[idx]){
          int swap = arr[left];
          arr[left] = arr[idx];
          arr[idx] = swap;
          idx = left;
        }else if(right<size && arr[right]>arr[idx]){
          int swap = arr[right];
          arr[right]=arr[idx];
          arr[idx] = swap;
          idx = right;
        }else {
          break;
        }
      }
    }
  }

  public void push(int x){
    if(size>=this.heap.length){
      resize();
    }
    heap[size] = x;
    size++;
    //Make sure parent is > child from the last element
    int idx = size-1;
    int parent = (idx-1)/2;
    while(idx>0 && arr[parent]<arr[idx]){
      int swap = arr[parent];
      arr[parent] = arr[idx];
      arr[idx] = swap;
      idx = parent;
      parent = (idx-1)/2;
    }
  }


  public int pop(){
    if(size==0)return 0;
    int ret = arr[0];
    arr[0] = arr[size-1];
    size--;
    int idx = 0;
    while(idx<size){
      int left = idx*2+1;
      int right = idx*2+2;
      if(left<size && arr[left]>arr[idx]){
        int swap = arr[left];
        arr[left] = arr[idx];
        arr[idx] = swap;
        idx = left;
      }else if(right<size && arr[right]>arr[idx]){
        int swap = arr[right];
        arr[right]=arr[idx];
        arr[idx] = swap;
        idx = right;
      }else {
        break;
      }
    }
  }


  public void resize(){
    int[] newArr = new int[this.heap.length*2];
    for(int i=0;i<size;i++){
      newArr[i] = this.heap[i];
    }
    this.heap = newArr;
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
    int[] a = {4,7,7,7,5,0,2,3,5,1};
    Heap tmp = new Heap();
    tmp.heapSort(a);
    System.out.println(Arrays.toString(a));
  }
}
