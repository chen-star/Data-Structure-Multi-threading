package multithreading.producer_consumer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : alexchen
 * @created : 9/13/20, Sunday
 **/
public class BlockingQueueWithMutex<T> {

    T[] arr;
    int size;
    int head;
    int tail;

    Lock lock = new ReentrantLock();

    public BlockingQueueWithMutex(int capacity) {
        arr = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        while (size == arr.length) {
            lock.unlock();

            lock.lock();
        }

        if (tail == arr.length) {
            tail = 0;
        }

        arr[tail++] = item;
        size++;

        lock.unlock();
    }

    public T deque() throws InterruptedException {
        lock.lock();
        while (size == 0) {
            lock.unlock();

            lock.lock();
        }

        if (head == arr.length) {
            head = 0;
        }

        T result = arr[head++];
        size--;

        lock.unlock();
        return result;
    }
}
