package project20280.recursion;

public class Fibonacci{

    static long callCount = 0;

    public static long fibonacci(int n) {
        callCount++;
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = 13;

        long start = System.nanoTime();
        long result = fibonacci(n);
        long end = System.nanoTime();

        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Recursive calls: " + callCount);
        System.out.println("Time taken: " + (end - start) + " ns");
    }
}