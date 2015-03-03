import java.io.*;
import java.util.*;


public class TransformTree {
    private static class Node {
        private String val;
        private int count;
        private List<Node> children = new ArrayList<>(); 
        //
        public Node(String val, int count) {
            this.val = val;
            this.count = count;
        }
        public Node(Node other) {
            this.val = other.val;
            this.count = other.count;
        }
        public void add(String val, int count) {
            Node node = new Node(val, count);
            add(node);
        }

        public boolean isEmpty() {
            return children.size() == 0;
        }

        public void add(Node node) {
            if (!children.contains(node)) {
                children.add(node);
            }
        }

        public void print() {
            print(0);
        }

        public void print(int indent) {
            for (int i=0; i<indent; i++) System.out.print(" ");
            System.out.println(val + " (" + count + ")");
            for (Node c : children) {
                c.print(indent+2);
            }
        }

        @Override
        public String toString() {
            return val + " (" + count + ")";
        }
        @Override
        public boolean equals(Object o) {
            if (o == null || o instanceof Node == false) return false;
            Node other = (Node) o;
            return val.equals(other.val) && count == other.count;
        }
    }

    private Node root = new Node("root", 420);


    public void transform(int[] order) {
        Node newRoot = new Node("newRoot", 420);
        Node[] history = new Node[order.length];
        for (Node child : root.children) {
            transform(child, newRoot, history, 0, order);
        }
        this.root = newRoot;
    }
    private void transform(Node node, Node newRoot, Node[] history, int level, int[] order) {
        history[level] = new Node(node);
        if (node.isEmpty()) {
            Node parent = newRoot;
            for (int n : order) {
                Node child = history[n];
                parent.add(child);
                parent = child;
            }
        } else {
            for (Node child : new ArrayList<Node>(node.children)) {
                transform(child, newRoot, history, level+1, order);
            }
        }
    }

    // Let tree store count for given combination
    // US 210 -> M 80 -> Teen 10
    // US 210 -> M 80 -> GenX 30
    // US 210 -> M 80 -> Mil 50
    // US 210 -> F 120 -> Teen 20
    // US 210 -> F 120 -> GenX 40
    // US 210 -> F 120 -> Mil 60
    // CA 210 -> M 60 -> Teen 10
    // CA 210 -> M 60 -> GenX 20
    // CA 210 -> M 60 -> Mil 30
    // CA 210 -> F 150 -> Teen 40
    // CA 210 -> F 150 -> GenX 50
    // CA 210 -> F 150 -> Mil 60
    public static void main(String[] args) throws Exception {
        TransformTree tree = new TransformTree();
        Node us = new Node("US", 210);
        tree.root.add(us);
        Node usM = new Node("M", 80);
        us.add(usM);
        usM.add(new Node("Teen", 10));
        usM.add(new Node("GenX", 30));
        usM.add(new Node("Mil", 50));
        Node usF = new Node("F", 120);
        us.add(usF);
        usF.add(new Node("Teen", 20));
        usF.add(new Node("GenX", 40));
        usF.add(new Node("Mil", 60));
        //
        Node ca = new Node("CA", 210);
        tree.root.add(ca);
        Node caM = new Node("M", 60);
        ca.add(caM);
        caM.add(new Node("Teen", 10));
        caM.add(new Node("GenX", 20));
        caM.add(new Node("Mil", 30));
        Node caF = new Node("F", 150);
        ca.add(caF);
        caF.add(new Node("Teen", 40));
        caF.add(new Node("GenX", 50));
        caF.add(new Node("Mil", 60));

        tree.root.print();
        tree.transform(new int[] {2, 1, 0});
        tree.root.print();
    }
}

