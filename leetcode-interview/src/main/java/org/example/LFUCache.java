package org.example;

import java.util.*;
import java.util.Map.Entry;

class LFUCache {
    //the key value pairs
    private Map<Integer, Integer> map;
    // the keys and their frequency
    private Map<Integer, Integer> keyCountMap;
    //freq to keys
    private TreeMap<Integer, LinkedHashSet<Integer>> freqKeysMap;
    private int capacity;

    public LFUCache(int capacity) {
        map = new HashMap<>(capacity);
        keyCountMap = new HashMap<>(capacity);
        freqKeysMap = new TreeMap<>(((o1, o2) -> o1 - o2));
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = map.get(key);

        if (value == null) {
            return -1;
        } else {
            Integer frequency = keyCountMap.getOrDefault(key, 0);
            keyCountMap.put(key, frequency + 1);

            //remove the current frequency and increase by one and then add to the last node
            LinkedHashSet<Integer> keys = freqKeysMap.getOrDefault(frequency, new LinkedHashSet<>());
            if (keys.contains(key)) {
                keys.remove(key);
            }
            if (keys.size() != 0) {
                freqKeysMap.put(frequency, keys);
            } else {
                freqKeysMap.remove(frequency);
            }

            LinkedHashSet<Integer> newKeys = freqKeysMap.getOrDefault(frequency + 1, new LinkedHashSet<>());
            if (!newKeys.contains(frequency + 1)) {
                newKeys.add(key);
            } else {
                newKeys.remove(key);
                newKeys.add(key);
            }

            freqKeysMap.put(frequency + 1, newKeys);
            return value;
        }

    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            map.put(key, value);
            Integer frequency = keyCountMap.getOrDefault(key, 0);
            keyCountMap.put(key, frequency + 1);
            //remove old freq, key pairs and add new ones
            LinkedHashSet<Integer> keys = freqKeysMap.getOrDefault(frequency, new LinkedHashSet<>());
            if (keys.contains(key)) {
                keys.remove(key);
            }
            if (keys.size() != 0) {
                freqKeysMap.put(frequency, keys);
            } else {
                freqKeysMap.remove(frequency);
            }
            LinkedHashSet<Integer> newKeys = freqKeysMap.getOrDefault(frequency + 1, new LinkedHashSet<>());
            if (!newKeys.contains(frequency + 1)) {
                newKeys.add(key);
            } else {
                newKeys.remove(key);
                newKeys.add(key);
            }
            freqKeysMap.put(frequency + 1, newKeys);
        } else {
            if (map.size() >= capacity) {
                Entry<Integer, LinkedHashSet<Integer>> entry = freqKeysMap.firstEntry();
                Iterator<Integer> iterator = entry.getValue().iterator();
                Integer minFreqKey = null;
                //the first value is the MinFrequencyKey
                while (iterator.hasNext()) {
                    Integer next = iterator.next();
                    minFreqKey = next;
                    break;
                }

                LinkedHashSet<Integer> value1 = entry.getValue();
                if (value1.contains(minFreqKey)) {
                    value1.remove(minFreqKey);
                }
                //add the new key value which key is not exists
                LinkedHashSet<Integer> keys = freqKeysMap.getOrDefault(1, new LinkedHashSet<>());
                if (!keys.contains(key)) {
                    keys.add(key);
                } else {
                    keys.remove(key);
                    keys.add(key);
                }
                freqKeysMap.put(1, keys);

                map.remove(minFreqKey);
                map.put(key, value);
                keyCountMap.remove(minFreqKey);
                keyCountMap.put(key, keyCountMap.getOrDefault(key, 0) + 1);
            } else {
                map.put(key, value);
                Integer frequency = keyCountMap.getOrDefault(key, 0);
                keyCountMap.put(key, frequency + 1);
                LinkedHashSet<Integer> keys = freqKeysMap.getOrDefault(1, new LinkedHashSet<>());
                if (!keys.contains(key)) {
                    keys.add(key);
                } else {
                    keys.remove(key);
                    keys.add(key);
                }
                freqKeysMap.put(1, keys);
            }
        }
    }

}
