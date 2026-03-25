package project20280.hashtable;

import project20280.interfaces.Entry;

import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new MapEntry[capacity];
    }

    int findSlot(int h, K k) {
        int avail = -1;
        int i = h;
        do {
            if (table[i] == null) {
                if (avail < 0) {
                    avail = i;
                }
                return -(avail + 1);
            }
            if (table[i] == DEFUNCT) {
                if (avail == -1) {
                    avail = i;
                }
            } else if (table[i].getKey().equals(k)) {
                return i;
            }
            i = (i + 1) % table.length;
        } while (i != h);
        if (avail >= 0) {
            return -(avail + 1);
        }
        return -(table.length + 1);
    }

    @Override
    protected V bucketGet(int h, K k) {
        int i = findSlot(h, k);
        if (i < 0) return null;
        return table[i].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        int i = findSlot(h, k);
        if (i >= 0) {
            return table[i].setValue(v);
        }

        int availableSlot = -(i + 1);
        if (availableSlot >= table.length) {
            throw new IllegalStateException("Hash table is full");
        }

        table[availableSlot] = new MapEntry<>(k, v);
        n += 1;
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int i = findSlot(h, k);
        if (i < 0) {
            return null;
        }

        V value = table[i].getValue();
        table[i] = DEFUNCT;
        n -= 1;
        return value;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null && entry != DEFUNCT) {
                entries.add(entry);
            }
        }
        return entries;
    }
}
