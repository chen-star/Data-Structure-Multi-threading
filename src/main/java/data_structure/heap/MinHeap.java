package data_structure.heap;

import java.util.Arrays;

public class MinHeap {

    private int[] arr;
    private int size;

    public MinHeap(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
        heapify();
    }

    public MinHeap(int capacity) {
        arr = new int[capacity];
        size = 0;
    }

    public void offer(int ele) {
        if (isFull()) {
            return;
        }

        arr[size++] = ele;
        percolateUp(size - 1);
    }

    public int poll() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int result = arr[0];
        percolateDown(0);
        size--;
        return result;
    }

    public int update(int index, int ele) {
        int origin = arr[index];
        arr[index] = ele;

        if (origin < ele) {
            percolateDown(index);
        } else if (origin > ele) {
            percolateUp(index);
        }

        return origin;
    }

    private void heapify() {
        for (int i = arr.length - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateUp(int index) {
        int parent = index;
        int cur = index;
        while (cur >= 1) {
            parent = (cur - 1) / 2;
            if (arr[parent] <= arr[cur]) {
                break;
            }
            swap(cur, parent);
            cur = parent;
        }
    }

    private void percolateDown(int index) {
        while (index <= size / 2 - 1) {
            int leftIdx = index * 2 + 1;
            int rightIdx = index * 2 + 2;
            int leftVal = arr[leftIdx];
            int rightVal = rightIdx >= size ? Integer.MAX_VALUE : arr[rightIdx];

            int swapIndex = leftVal <= rightVal ? leftIdx : rightIdx;

            if (arr[swapIndex] < arr[index]) {
                swap(swapIndex, index);
            } else {
                break;
            }

            index = swapIndex;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void print() {
        for (int len = 1, i = 0; i < size; len <<= 1) {
            for (int k = 0; k < len && i < size; k++) {
                System.out.print(" " + arr[i++]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(new int[]{2,4,5,3,13,6,7,8,11});
        minHeap.print();

        System.out.println();
        MinHeap minHeap1 = new MinHeap(16);
        minHeap1.offer(2);
        minHeap1.offer(3);
        minHeap1.offer(19);
        minHeap1.offer(13);
        minHeap1.offer(16);
        minHeap1.offer(21);
        minHeap1.print();

        System.out.println();
        minHeap1.update(0, 14);
        minHeap1.print();

        System.out.println();
        minHeap1.update(1, 0);
        minHeap1.print();
    }
}
