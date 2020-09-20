package data_structure.bounded_queue_stack;

public class BoundedDequeNoSize<T> {

    private int head;
    private int tail;

    private T[] arr;

    public BoundedDequeNoSize(int capacity) {
        arr = (T[]) new Object[capacity + 1];
        head = 0;
        tail = 1;
    }

    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }

        head = (head + 1) == arr.length ? 0 : head + 1;
        return arr[head];
    }

    public T pollLast() {
        if (isEmpty()) {
            return null;
        }

        tail = (tail - 1) < 0 ? arr.length - 1 : tail - 1;
        return arr[tail];
    }

    public void offerFirst(T ele) {
        if (isFull()) {
            return;
        }

        arr[head] = ele;
        head = head - 1 < 0 ? arr.length - 1 : head - 1;
    }

    public void offerLast(T ele) {
        if (isFull()) {
            return;
        }

        arr[tail] = ele;
        tail = tail + 1 == arr.length ? 0 : tail + 1;
    }

    public boolean isEmpty() {
        return (head + 1) % arr.length == tail;
    }

    public boolean isFull() {
        return head == tail;
    }
}
