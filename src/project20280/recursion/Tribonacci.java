package project20280.recursion;

public class Tribonacci {

    static long callCount = 0;

    public static long tribonacci(int n) {
        callCount++;

        if (n == 0) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;

        return tribonacci(n - 1)
                + tribonacci(n - 2)
                + tribonacci(n - 3);
    }

    public static void main(String[] args) {
        int n = 9;

        long start = System.nanoTime();
        long result = tribonacci(n);
        long end = System.nanoTime();

        System.out.println("Tribonacci(" + n + ") = " + result);
        System.out.println("Recursive calls: " + callCount);
        System.out.println("Time taken: " + (end - start) + " ns");
    }
}