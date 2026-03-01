package project20280.recursion;

public class Reverse {

    public static void reverseArray(int[] arr, int i, int j) {

        System.out.println("reverseArray(A, " + i + ", " + j + ")");
        System.out.print("  A = {");
        for (int n : arr) {
            System.out.print(n + ", ");
        }

        System.out.print("}\n");
        if (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            reverseArray(arr, i + 1, j - 1);
        }
    }


    public static void main(String[] args) {
        int[] A = {12, 5, 19, 6, 11, 3, 9, 34, 2, 1, 15};
        reverseArray(A, 0, A.length - 1);

    }
}
