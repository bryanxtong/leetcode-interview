package org.example;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LRUCache_MAP {

    private int size;
    private LinkedHashMap<Integer, Integer> map;

    public LRUCache_MAP(int capacity) {
        this.size = capacity;
        map = new LinkedHashMap<>(10, 0.75f, true);

    }

    public Integer put(Integer key, Integer value) {
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            if (map.size() >= size) {
                Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
                Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
                int eldestKey = 0;
                if (iterator.hasNext()) {
                    eldestKey = iterator.next().getKey();
                }
                map.remove(eldestKey);
            }
            map.put(key, value);
        }
        return value;
    }

    public Integer get(Integer key) {
        return map.getOrDefault(key, -1);
    }

    public static void main(String[] args) {
        LRUCache_MAP lRUCache = new LRUCache_MAP(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        lRUCache.get(1);
        lRUCache.put(3, 3);
        lRUCache.get(2);
        lRUCache.put(4, 4);
        lRUCache.get(1);
        lRUCache.get(3);
        lRUCache.get(4);
    }


}
