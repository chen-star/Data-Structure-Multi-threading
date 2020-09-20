package data_structure.bounded_queue_stack;

public class BoundedQueueSized<T> {

    private int size;
    private int head;
    private int tail;

    private T[] arr;

    public BoundedQueueSized(int capacity) {
        this.arr = (T[]) new Object[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void enqueue(T ele) {
        if (isFull()) {
            return;
        }

        arr[tail] = ele;

        tail = (tail + 1) == arr.length ? 0 : tail + 1;
        size++;
    }

    public T deque() {
        if (isEmpty()) {
            return null;
        }

        T result = arr[head];

        head = (head + 1) == arr.length ? 0 : head + 1;
        size--;

        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }
}
