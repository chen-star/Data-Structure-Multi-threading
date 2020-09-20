package data_structure.bounded_queue_stack;

public class BoundedStackSized<T> {

    private int size;
    private int tail;

    private T[] arr;

    public BoundedStackSized(int capacity) {
        arr = (T[]) new Object[capacity];
        size = 0;
        tail = 0;
    }


    public void push(T ele) {
        if (isFull()) {
            return;
        }

        arr[tail] = ele;

        tail = tail + 1 == arr.length ? 0 : tail + 1;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }

        tail = tail - 1 < 0 ? arr.length - 1 : tail - 1;
        size--;

        return arr[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }
}
