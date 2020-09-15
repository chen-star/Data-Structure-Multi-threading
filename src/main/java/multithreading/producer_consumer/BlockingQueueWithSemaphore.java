package multithreading.producer_consumer;

/**
 * @author : alexchen
 * @created : 9/14/20, Monday
 **/
public class BlockingQueueWithSemaphore<T> {

    private T[] arr;
    private int size;
    private int head;
    private int tail;

    private CountingSemaphore producerSem;
    private CountingSemaphore consumerSem;
    private CountingSemaphore lockSem;

    public BlockingQueueWithSemaphore(int capacity) {
        arr = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;

        producerSem = new CountingSemaphore(capacity, capacity);
        consumerSem = new CountingSemaphore(capacity, 0);
        lockSem = new CountingSemaphore(1, 1);
    }

    public void enqueue(T item) throws InterruptedException {
        producerSem.acquire();
        lockSem.acquire();

        if (tail == arr.length) {
            tail = 0;
        }

        arr[tail++] = item;
        size++;

        lockSem.release();
        consumerSem.release();
    }

    public T deque() throws InterruptedException {
        consumerSem.acquire();
        lockSem.acquire();

        if (head == arr.length) {
            head = 0;
        }

        T result = arr[head++];
        size--;

        lockSem.release();
        producerSem.release();

        return result;
    }
}


class CountingSemaphore {
    private int totalCount;
    private int used;

    public CountingSemaphore(int totalCount, int currentAvailable) {
        this.totalCount = totalCount;
        this.used = totalCount - currentAvailable;
    }

    public synchronized void acquire() throws InterruptedException {
        while (used == totalCount) {
            wait();
        }

        notify();
        used++;
    }

    public synchronized void release() throws InterruptedException {
        while (used == 0) {
            wait();
        }

        notify();
        used--;
    }
}
