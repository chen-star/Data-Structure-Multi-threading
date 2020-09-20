package data_structure.bounded_queue_stack;

import java.util.Arrays;

public class BoundedQueueNoSize<T> {

    private int head;
    private int tail;

    private T[] arr;

    public BoundedQueueNoSize(int capacity) {
        arr = (T[]) new Object[capacity + 1];
        head = 0;
        tail = 1;
    }

    public void enqueue(T ele) {
        if (isFull()) {
            return;
        }

        arr[tail] = ele;

        tail = (tail + 1) == arr.length ? 0 : tail + 1;
    }

    public T deque() {
        if (isEmpty()) {
            return null;
        }

        head = (head + 1) == arr.length ? 0 : head + 1;

        return arr[head];
    }

    public boolean isFull() {
        return head == tail;
    }

    public boolean isEmpty() {
        return (head + 1) % arr.length == tail;
    }
}
