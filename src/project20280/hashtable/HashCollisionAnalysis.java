package project20280.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class HashCollisionAnalysis {

    private static List<String> readWords(String filePath) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        scanner.close();
        return words;
    }

    private static int hashPoly(String s, int a) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = h * a + s.charAt(i);
        }
        return h;
    }

    private static int hashCyclic(String s, int shift) {
        int h = 0;
        for (int i = 0; i < s.length(); ++i) {
            h = (h << shift) | (h >>> (32 - shift));
            h += s.charAt(i);
        }
        return h;
    }

    private static int oldJavaHashCode(String s) {
        int hash = 0;
        int skip = Math.max(1, s.length() / 8);
        for (int i = 0; i < s.length(); i += skip) {
            hash = (hash * 37) + s.charAt(i);
        }
        return hash;
    }

    private static int countCollisions(List<String> words, ToIntFunction<String> hashFunction) {
        HashSet<Integer> seenHashes = new HashSet<>();
        int collisions = 0;

        for (String word : words) {
            int hash = hashFunction.applyAsInt(word);
            if (!seenHashes.add(hash)) {
                collisions++;
            }
        }

        return collisions;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "src/project20280/hashtable/words.txt";

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Could not find word list: " + file.getPath());
            return;
        }

        List<String> words = readWords(filePath);

        int poly41Collisions = countCollisions(words, word -> hashPoly(word, 41));
        int poly17Collisions = countCollisions(words, word -> hashPoly(word, 17));
        int cyclic7Collisions = countCollisions(words, word -> hashCyclic(word, 7));
        int oldJavaCollisions = countCollisions(words, HashCollisionAnalysis::oldJavaHashCode);

        System.out.println("(a) Polynomial accumulation, a = 41: " + poly41Collisions);
        System.out.println("(b) Polynomial accumulation, a = 17: " + poly17Collisions);
        System.out.println("(c) Cyclic shift, shift = 7: " + cyclic7Collisions);

        int bestShift = -1;
        int bestCollisions = Integer.MAX_VALUE;
        System.out.println("(d) Cyclic shift collisions for shift values 0 to 31:");
        for (int shift = 0; shift <= 31; shift++) {
            final int currentShift = shift;
            int collisions = countCollisions(words, word -> hashCyclic(word, currentShift));
            System.out.println("shift " + shift + ": " + collisions);
            if (collisions < bestCollisions) {
                bestCollisions = collisions;
                bestShift = shift;
            }
        }
        System.out.println("Best shift: " + bestShift + " with " + bestCollisions + " collisions");

        System.out.println("(e) Old Java hashCode: " + oldJavaCollisions);
    }
}
