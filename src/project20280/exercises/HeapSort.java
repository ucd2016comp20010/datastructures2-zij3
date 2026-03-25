package project20280.exercises;

import java.util.Arrays;
import java.util.Random;

import static project20280.exercises.PQSort.pqSort;

public class HeapSort {
    public static void inPlaceHeapSort(Integer[] a) {
        int size = a.length;


        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(a, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            Integer temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            siftDown(a, i, 0);
        }
    }

    /**
     * Helper method to restore the Max-Heap property.
     *
     * @param a The array representing the heap
     * @param n The current size of the heap bounds
     * @param i The index to sift down
     */
    private static void siftDown(Integer[] a, int n, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && a[left].compareTo(a[largest]) > 0) {
                largest = left;
            }

            if (right < n && a[right].compareTo(a[largest]) > 0) {
                largest = right;
            }

            if (largest != i) {
                Integer temp = a[i];
                a[i] = a[largest];
                a[largest] = temp;
                i = largest;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 50000, 100000, 500000, 1000000};
        Random rand = new Random();

        System.out.printf("%-10s | %-15s | %-20s | %-10s%n",
                "Size (n)", "pqSort (ms)", "inPlaceHeapSort (ms)", "Speedup");
        System.out.println("-------------------------------------------------------------------");

        for (int n : sizes) {
            // 1. Generate random data
            Integer[] original = new Integer[n];
            for (int i = 0; i < n; i++) {
                original[i] = rand.nextInt(n * 10);
            }

            // 2. Make exact copies for a fair comparison
            Integer[] arrayForPqSort = Arrays.copyOf(original, n);
            Integer[] arrayForInPlace = Arrays.copyOf(original, n);

            // 3. Measure pqSort (Out-of-place)
            long startPq = System.nanoTime();
            // Assuming pqSort is in the same class, or call it via its class name
            pqSort(arrayForPqSort);
            long endPq = System.nanoTime();
            double timePq = (endPq - startPq) / 1_000_000.0;

            // 4. Measure inPlaceHeapSort
            long startInPlace = System.nanoTime();
            inPlaceHeapSort(arrayForInPlace);
            long endInPlace = System.nanoTime();
            double timeInPlace = (endInPlace - startInPlace) / 1_000_000.0;

            // 5. Calculate how many times faster the in-place version is
            double speedup = timePq / timeInPlace;

            // 6. Print the row
            System.out.printf("%-10d | %-15.2f | %-20.2f | %.1fx%n",
                    n, timePq, timeInPlace, speedup);
        }
    }
}
