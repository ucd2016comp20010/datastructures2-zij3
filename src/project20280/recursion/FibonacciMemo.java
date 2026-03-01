package project20280.recursion;

import java.util.Arrays;

public class FibonacciMemo {

    static long callCount = 0;

    public static long fibonacci(int n, long[] memo) {
        callCount++;

        if (n <= 1) return n;

        if (memo[n] != -1) return memo[n];

        memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 90;

        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);

        long start = System.nanoTime();
        long result = fibonacci(n, memo);
        long end = System.nanoTime();

        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Recursive calls: " + callCount);
        System.out.println("Time taken: " + (end - start) + " ns");
    }
}