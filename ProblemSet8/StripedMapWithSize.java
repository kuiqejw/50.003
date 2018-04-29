package Week12;

public class StripedMap {
    //synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]

    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];

        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    public Object put(Object key, Object value) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) {
                    m.value = value;
                    return m.value;
                }
            }
            buckets[hash] = new Node(key, value, buckets[hash]);
        }
        return null;
    }

    public Object get(Object key) {
        //todo: get the item with the given key in the map
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) {
                    return m.value;
                }
            }
        }
        return null;
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            // takes lock so nobody reads outdated value or updates buckets
            // but when you delete buckets 0 to 15, somebody can read buckets 16 to 31
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }

    public int size() {
        int count = 0;
        //todo: count the number of elements in the map
        synchronized (locks) {
            for (Node n : buckets) {
                count ++;
                while (n.next != null) {
                    count ++;
                }
            }
        }
        return count;
    }

    class Node {

        Node next;
        Object key;
        Object value;

        Node(Object key, Object value, Node next) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }
}
