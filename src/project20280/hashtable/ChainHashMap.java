package project20280.hashtable;

import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new UnsortedTableMap[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new UnsortedTableMap<>();
        }
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
        return table[h].get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        V oldValue = table[h].put(k, v);
        if (oldValue == null) {
            n += 1;
        }
        return oldValue;
    }


    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
        V oldValue = table[h].remove(k);
        if (oldValue != null) {
            n -= 1;
        }
        return oldValue;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        /*
        for each element in (UnsortedTableMap []) table
            for each element in bucket:
                print element
        */
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (UnsortedTableMap<K, V> tm : table) {
            if (tm != null) {
                for (Entry<K, V> e : tm.entrySet()) {
                    entries.add(e);
                }
            }
        }
        return entries;
    }

    public String toString() {
        return entrySet().toString();
    }

    public static void main(String []args) throws FileNotFoundException {
        String filePath = "src/project20280/hashtable/sample_text.txt";
        if (args.length > 0) {
            filePath = args[0];
        }

        File f = new File(filePath);
        ChainHashMap<String, Integer> counter = new ChainHashMap<String, Integer>();

        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String word = scanner.next().replaceAll("[^a-z0-9']", "");
            if (word.isEmpty()) {
                continue;
            }

            Integer count = counter.get(word);
            if (count == null) {
                counter.put(word, 1);
            } else {
                counter.put(word, count + 1);
            }
        }
        scanner.close();

        ArrayList<Entry<String, Integer>> frequencies = new ArrayList<>();
        for (Entry<String, Integer> entry : counter.entrySet()) {
            frequencies.add(entry);
        }

        frequencies.sort((a, b) -> {
            int valueComparison = Integer.compare(b.getValue(), a.getValue());
            if (valueComparison != 0) {
                return valueComparison;
            }
            return a.getKey().compareTo(b.getKey());
        });

        for (Entry<String, Integer> entry : frequencies) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
