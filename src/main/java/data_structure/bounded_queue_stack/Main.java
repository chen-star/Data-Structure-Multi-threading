package data_structure.bounded_queue_stack;

public class Main {

    public static void main(String[] args) {
        // Queue with Size
        BoundedQueueSized<Integer> queueSized = new BoundedQueueSized<>(3);
        queueSized.enqueue(1);
        queueSized.enqueue(2);
        queueSized.enqueue(3);
        System.out.println(queueSized.deque());
        System.out.println(queueSized.deque());
        queueSized.enqueue(4);
        System.out.println(queueSized.deque());
        System.out.println(queueSized.deque());

        System.out.println();

        // Stack with Size
        BoundedStackSized<Integer> stackSized = new BoundedStackSized<>(3);
        stackSized.push(1);
        stackSized.push(2);
        stackSized.push(3);
        System.out.println(stackSized.pop());
        System.out.println(stackSized.pop());
        stackSized.push(4);
        System.out.println(stackSized.pop());
        System.out.println(stackSized.pop());

        System.out.println();

        // Queue No Size
        BoundedQueueNoSize<Integer> queueNoSize = new BoundedQueueNoSize<>(3);
        queueNoSize.enqueue(1);
        queueNoSize.enqueue(2);
        queueNoSize.enqueue(3);
        System.out.println(queueNoSize.deque());
        System.out.println(queueNoSize.deque());
        queueNoSize.enqueue(4);
        System.out.println(queueNoSize.deque());
        System.out.println(queueNoSize.deque());

        System.out.println();

        // Deque No Size
        BoundedDequeNoSize<Integer> dequeNoSize = new BoundedDequeNoSize<>(3);
        dequeNoSize.offerLast(1);
        dequeNoSize.offerLast(2);
        dequeNoSize.offerLast(3);
        System.out.println(dequeNoSize.pollLast());
        System.out.println(dequeNoSize.pollFirst());
        dequeNoSize.offerFirst(4);
        System.out.println(dequeNoSize.pollFirst());
        System.out.println(dequeNoSize.pollLast());
    }
}
