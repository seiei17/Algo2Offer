package algo;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    @Test
    public void test() {
        LRU<Character, Integer> lru = new LRU<>(3);
        lru.put('M', 1);
        lru.put('Z', 2);
        lru.put('C', 3);
        lru.print();
        lru.put('X', 4);
        lru.print();
        lru.get('Z');
        lru.put('Y', 5);
        lru.print();
    }
}

class LRU<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRU(int initialCapacity) {
        super(initialCapacity, 0.75f, true);
        this.capacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > this.capacity;
    }

    public void print() {
        for (K key: this.keySet()) System.out.print(key + " ");
        System.out.println();
    }
}