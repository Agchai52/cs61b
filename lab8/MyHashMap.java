import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int INIT_CAPACITY = 16;
    private int n; // # of keys;
    private int m; // hash table size;
    private double lf;
    private Item<K, V>[] buckets;
    private Set<K> allKeys = new HashSet<K>();

    private class Item<K, V> {
        private Node sentinel;
        private int size;

        private class Node {
            private K key;
            private V val;
            private Node next;

            private Node(K k, V v, Node n) {
                key = k;
                val = v;
                next = n;
            }
        }

        public Item() {
            sentinel = new Node(null, null, null);
            size = 0;
        }

        public Item(K k, V v) {
            sentinel = new Node(null, null, null);
            sentinel.next = new Node(k, v, null);
            size = 1;
        }

        public void addLast(K k, V v) {
            Node p = sentinel;
            while (p.next != null) {
                if (k.equals(p.next.key)) {
                    p.next.val = v;
                    return;
                }
                p = p.next;
            }
            p.next = new Node(k, v, null);
            size += 1;
        }

        public V get(K key) {
            Node returnItem = sentinel.next;
            for (int i = 0; i < size; i++) {
                if (key.equals(returnItem.key)) {
                    return returnItem.val;
                }
                returnItem = returnItem.next;
            }
            return null;
        }

        public K get(int index) {
            if (index >= size) {
                throw new IllegalArgumentException("Overflow!");
            }
            Node p = sentinel;
            p = p.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.key;
        }

        public int size() {
            return size;
        }

    }


    public MyHashMap() {
        n = 0;
        lf = 0.75;
        m = INIT_CAPACITY;
        buckets = new Item[m];
        for (int i = 0; i < m; i++) {
            buckets[i] = new Item();
        }

    }

    public MyHashMap(int initialSize) {
        n = 0;
        lf = 0.75;
        m = initialSize;
        buckets = new Item[m];
        for (int i = 0; i < m; i++) {
            buckets[i] = new Item<K, V>();
        }
    }

    public MyHashMap(int initialSize, double loadFactor) {
        n = 0;
        lf = loadFactor;
        m = initialSize;
        buckets = new Item[m];
        for (int i = 0; i < m; i++) {
            buckets[i] = new Item<K, V>();
        }
    }



    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        n = 0;
        lf = 0.75;
        m = INIT_CAPACITY;
        buckets = new Item[m];
        for (int i = 0; i < m; i++) {
            buckets[i] = new Item<K, V>();
        }
        allKeys.clear();

    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return allKeys.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null!");
        }
        int index = Math.abs(key.hashCode()) % m;
        return buckets[index].get(key);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return n;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    private void resize(int newBSize) {
        Item<K, V>[] temps = buckets;
        int tempSize = m;
        m = newBSize;
        buckets = new Item[m];
        for (int i = 0; i < newBSize; i++) {
            buckets[i] = new Item<K, V>();
        }

        for (int i = 0; i < tempSize; i++) {
            int curSize = temps[i].size();
            if (curSize == 0) {
                continue;
            }
            for (int j = 0; j < temps[i].size(); j++) {
                K key = temps[i].get(j);
                V value = temps[i].get(key);
                put(key, value);
            }

        }

    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value cannot be null!");
        }

        if (n + 1 > (int) m * lf) {
            resize(m * 2);
        }


        int index = Math.abs(key.hashCode()) % m;

        if (!containsKey(key)) {
            n += 1;
        }

        allKeys.add(key);
        buckets[index].addLast(key, value);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return allKeys;
    }


    private class myIterator implements Iterator<K> {
        private int pos; // pos in buckets[index]
        private int index; // buckets index

        public myIterator() {
            pos = 0;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            if (index >= m) {
                return false;
            }
            return pos < buckets[index].size();
        }

        @Override
        public K next() {
            K returnItem = buckets[index].get(pos);
            if (pos+1 >= buckets[index].size()) {
                pos = 0;
                index += 1;
            } else {
                pos += 1;
            }
            return returnItem;
        }

    }

    @Override
    public Iterator<K> iterator() {
        return new myIterator();
    }




    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
