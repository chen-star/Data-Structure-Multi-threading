package multithreading.producer_consumer;

/**
 * @author : alexchen
 * @created : 9/13/20, Sunday
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        final BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);
//        BlockingQueueWithMutex<Integer> blockingQueue = new BlockingQueueWithMutex<>(5);
        BlockingQueueWithSemaphore<Integer> blockingQueue = new BlockingQueueWithSemaphore<>(5);

        Thread producer1 = new Thread(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    blockingQueue.enqueue(i);
                    System.out.println("Enqueue: " + i);
                }
            } catch (InterruptedException ie) {
            }
        });

        Thread consumer1 = new Thread(() -> {
            try {
                for (int i = 0; i < 25; i++) {
                    System.out.println("Consumer 1 Deque: " + blockingQueue.deque());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer2 = new Thread(() -> {
            try {
                for (int i = 0; i < 25; i++) {
                    System.out.println("Consumer 2 Deque: " + blockingQueue.deque());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer1.start();

        Thread.sleep(1000);

        consumer1.start();
        consumer2.start();

        producer1.join();
        consumer1.join();
        consumer2.join();


    }
}
