import java.util.*;


public class QueueUsing2Stacks<T> {
  private Stack<T>[] stacks = new Stack[2];
  private int adding = 0;
  private boolean fifoMode; // FIFO mode

  public QueueUsing2Stacks() {
    stacks[0] = new Stack<T>();
    stacks[1] = new Stack<T>();
  }

  public void enqueue(T obj) {
    if (fifoMode) {
      int other = adding == 0 ? 1 : 0;
      int size = stacks[adding].size();
      for (int i=0; i<size; i++) {
        T next = stacks[adding].pop();
        stacks[other].push(next);
      }
      stacks[other].push(obj);
      fifoMode = false;
      adding = other;
    } else {
      stacks[adding].push(obj);
    }
  }

  public T dequeue() {
    if (stacks[0].size() == 0 && stacks[1].size() == 0) {
      throw new RuntimeException("empty queue");
    }
    int other = adding == 0 ? 1 : 0;
    if (fifoMode) {
      return stacks[adding].pop();
    } else {
      int size = stacks[adding].size()-1;
      for (int i=0; i<size; i++) {
        T next = stacks[adding].pop();
        stacks[other].push(next);
      }
      T obj = stacks[adding].pop();
      adding = other;
      fifoMode = true;
      return obj;
    }
  }

  public int size() {
    return stacks[adding].size();
  }


  public static void main(String[] args) {
    QueueUsing2Stacks<Integer> q = new QueueUsing2Stacks<Integer>();
    for (int i=0; i<10; i++) {
      q.enqueue(i);
    }
    for (int i=0; i<5; i++) {
      System.out.println(q.dequeue());
    }
    for (int i=10; i<20; i++) {
      q.enqueue(i);
    }
    for (int i=0; i<8; i++) {
      System.out.println(q.dequeue());
    }
    for (int i=20; i<30; i++) {
      q.enqueue(i);
    }
    for (int i=0; i<17; i++) {
      System.out.println(q.dequeue());
    }
  }

}


