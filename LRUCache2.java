import java.util.*;


public class LRUCache2 {
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
    static class CacheNode {
        private int key;
        private int value;
        private CacheNode next;
        private CacheNode prev;
        CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    static class CacheList {
        private CacheNode head;
        private CacheNode tail;
        private void add(CacheNode node) {
            if (head == null) {
                head = node;
            } else {
                if (tail != null) {
                    node.prev = tail;
                    tail.next = node;
                    tail = node;
                } else {
                    tail = node;
                    tail.prev = head;
                    head.next = tail;
                }
            }
        }

        private CacheNode removeHead() {
            return remove(head);
        }
        private CacheNode removeTail() {
            return remove(tail);
        }
        private CacheNode remove(CacheNode node) {
            if (node == null) {
                return null;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (node == head) {
                head = node.next;
            }
            if (node == tail) {
                tail = node.prev;
            }
            node.next = node.prev = null;
            return node;
        }
    }
    private CacheList list;
    private final int capacity;
    private Map<Integer,CacheNode> delegate;

    public LRUCache2(final int capacity) {
        this.capacity = capacity;
        this.delegate = new HashMap<Integer, CacheNode>(capacity); 
        this.list = new CacheList(); 
    }
    
    public int get(int key) {
        CacheNode node = delegate.get(key);
        if (node == null) {
            return -1;
        }
        list.remove(node);
        list.add(node);
        return node.value;
    }
    
    public void set(int key, int value) {
        CacheNode node = delegate.remove(key);
        if (node != null) {
            list.remove(node);
        } else {
            node = new CacheNode(key, value);
        }
        list.add(node);
        delegate.put(key, node); 
        if (delegate.size() > capacity) {
            CacheNode deleted = list.removeHead();
            if (deleted != null) {
                delegate.remove(deleted.key);
            }
        }
    }
    //
    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(5);
        for (int i=0; i<10; i++) {
            cache.set(i, i);
        }
        for (int i=0; i<10; i++) {
            System.out.println(cache.get(i));
        }
    }
}
