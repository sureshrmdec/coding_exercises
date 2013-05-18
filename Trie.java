public class Trie<T> {
    private static final int R = 256;
    private static class Node<T> {
        private T value;
        private Node[] next = new Node[R];
        public String toString() {
            return value.toString();
        }
    }
    private Node<T> root = new Node<T>();

    public void put(String key, T value) {
        root = put(root, key, value, 0);
    }

    private Node<T> put(Node<T> x, String key, T val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.value = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public T get(String key) {
        Node<T> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    private Node<T> get(Node<T> x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }


    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<Integer>();
        trie.put("One", 1);
        trie.put("Two", 2);
        trie.put("Three", 3);
        System.out.println(trie.get("One"));
        System.out.println(trie.get("Two"));
        System.out.println(trie.get("Three"));
    }
}

