package multithreading.producer_consumer;

/**
 * @author : alexchen
 * @created : 9/13/20, Sunday
 **/
public class BlockingQueue<T> {

    T[] arr;
    int size;
    int head;
    int tail;

    public BlockingQueue(int capacity) {
        arr = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (size == arr.length) {
            wait();
        }

        if (tail == arr.length) {
            tail = 0;
        }

        arr[tail++] = item;
        size++;

        notifyAll();
    }

    public synchronized T deque() throws InterruptedException {
        while (size == 0) {
            wait();
        }

        if (head == arr.length) {
            head = 0;
        }

        T result = arr[head++];
        size--;

        notifyAll();

        return result;
    }
}
