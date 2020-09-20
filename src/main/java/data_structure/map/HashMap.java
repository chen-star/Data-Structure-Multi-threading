package data_structure.map;

import lombok.ToString;

public class HashMap<K, V> {

    private Node<K, V>[] arr;
    private int size;
    private static float LOAD_FACTOR = 0.75f;

    public HashMap(int capacity) {
        this.arr = new Node[capacity];
        this.size = 0;
    }

    public HashMap() {
        this(10);
    }

    public void put(K k, V v) {
        if (size > LOAD_FACTOR * arr.length) {
            resize();
        }

        int hashed = k.hashCode();
        int index = hashed % arr.length;

        Node<K, V> cur = arr[index];
        while (cur != null) {
            if (equalsKey(cur.key, k)) {
                cur.val = v;
                return;
            }
            cur = cur.next;
        }

        Node toAdd = new Node(k, v);
        toAdd.next = arr[index];
        arr[index] = toAdd;
        size++;
    }

    public V get(K k) {
        if (isEmpty()) {
            return null;
        }

        int hashed = k.hashCode();
        int index = hashed % arr.length;
        Node<K, V> cur = arr[index];
        while (cur != null) {
            if (equalsKey(cur.key, k)) {
                return cur.val;
            }
            cur = cur.next;
        }

        return null;
    }

    public void remove(K k) {
        if (isEmpty()) {
            return;
        }

        int hashed = k.hashCode();
        int index = hashed % arr.length;
        Node<K, V> prev = null;
        Node<K, V> cur = arr[index];
        while (cur != null) {
            if (equalsKey(cur.key, k)) {
                if (prev == null) {
                    arr[index] = cur.next;
                    return;
                } else {
                    prev.next = cur.next;
                    return;
                }
            }
            cur = cur.next;
        }
    }

    private void resize() {
        Node<K, V>[] resized = new Node[arr.length * 2 + 1];
        for (int i = 0; i < arr.length; i++) {
            Node cur = arr[i];
            while (cur != null) {
                Node next = cur.next;
                int newIndex = cur.key.hashCode() % resized.length;
                cur.next = resized[newIndex];
                resized[newIndex] = cur;
                cur = next;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean equalsKey(K k1, K k2) {
        if (k1 == null || k2 == null) {
            return k1 == null && k2 == null;
        }
        return k1.equals(k2);
    }

    private void print() {
        for (int i = 0; i < arr.length; i++) {
            Node cur = arr[i];
            while (cur != null) {
                System.out.print(" " + cur);
                cur = cur.next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>(2);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3,4);
        map.put(1, 10);
        map.print();

        map.remove(1);
        map.print();
    }
}

@ToString
class Node<K, V> {
    K key;
    V val;
    Node next;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
