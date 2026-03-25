package project20280.exercises;

import project20280.priorityqueue.HeapPriorityQueue;

import java.util.Random;

public class PQSort {

    public static void pqSort(Integer[] a) {
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(a, a);

        for (int i = 0; i < a.length; i++) {
            a[i] = pq.removeMin().getKey();
        }
    }

    public static void main(String[] args) {

        // Sizes from 1,000 up to 1,000,000
        int[] sizes = {1000, 10000, 50000, 100000, 500000, 1000000};
        Random rand = new Random();

        System.out.printf("%-10s | %-15s%n", "Size (n)", "Time (ms)");
        System.out.println("----------------------------");

        for (int n : sizes) {
            Integer[] data = new Integer[n];
            for (int i = 0; i < n; i++) {
                data[i] = rand.nextInt(n * 10); // Random integers
            }

            // Measure start time
            long startTime = System.nanoTime();

            pqSort(data);

            // Measure end time
            long endTime = System.nanoTime();

            // Convert nanoseconds to milliseconds
            double durationMs = (endTime - startTime) / 1_000_000.0;

            System.out.printf("%-10d | %-15.2f%n", n, durationMs);
        }
    }
}
