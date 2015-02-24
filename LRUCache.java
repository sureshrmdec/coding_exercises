import java.util.*;


public class LRUCache {
    // https://oj.leetcode.com/problems/lru-cache/
    // Design and implement a data structure for Least Recently Used (LRU)
    // cache. It should support the following operations: get and set.
    //
    // get(key) - Get the value (will always be positive) of the key if the key
    // exists in the cache, otherwise return -1.
    // set(key, value) - Set or insert the value if the key is not already
    // present. When the cache reached its capacity, it should invalidate the
    // least recently used item before inserting a new item.
    // 
    private final int capacity;
    private LinkedHashMap<Integer,Integer> delegate;

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        int hashTableCapacity = (int)Math.ceil(capacity / 0.75F) + 1;
        this.delegate = new LinkedHashMap<Integer, Integer>(hashTableCapacity, 0.75F, true) {
            private static final long serialVersionUID = 1;
            @Override protected boolean removeEldestEntry (Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        Integer val = delegate.get(key);
        return val == null ? -1 : val.intValue();
    }
    
    public void set(int key, int value) {
        delegate.put(key, value); 
    }
    //
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        for (int i=0; i<10; i++) {
            cache.set(i, i);
        }
        for (int i=0; i<10; i++) {
            System.out.println(cache.get(i));
        }
    }
}
