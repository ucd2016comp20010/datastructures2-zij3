package project20280.recursion;

public class McCarthy91 {

    static int callCount = 0;

    public static int M(int n) {
        callCount++;

        if (n > 100) {
            return n - 10;
        } else {
            return M(M(n + 11));
        }
    }

    public static void main(String[] args) {
        int n = 87;

        long start = System.nanoTime();
        int result = M(n);
        long end = System.nanoTime();

        System.out.println("M(" + n + ") = " + result);
        System.out.println("Recursive calls = " + callCount);
        System.out.println("Time taken = " + (end - start) + " ns");
    }
}