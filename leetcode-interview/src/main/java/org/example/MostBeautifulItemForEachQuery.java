package org.example;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
//leetcode 2070， refer to official for a better way
public class MostBeautifulItemForEachQuery {
    public int[] maximumBeauty(int[][] items, int[] queries) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < items.length; i++) {
            if (!map.containsKey(items[i][0])) {
                map.put(items[i][0], items[i][1]);
            } else {
                if (items[i][1] >= map.getOrDefault(items[i][0], 0)) {
                    map.put(items[i][0], items[i][1]);
                }
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        TreeMap<Integer, Integer> targetMap = new TreeMap<>();
        int max = -1;
        for (Map.Entry<Integer, Integer> entry : entries) {
            max = Math.max(max, entry.getValue());
            targetMap.put(entry.getKey(), max);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            Integer q = queries[i];
            while (!targetMap.containsKey(q)) {
                SortedMap<Integer, Integer> integerIntegerSortedMap = targetMap.headMap(q);
                if (!integerIntegerSortedMap.isEmpty()) {
                    q = integerIntegerSortedMap.lastKey();
                } else {
                    break;
                }
            }
            if (q != null && targetMap.containsKey(q)) {
                ans[i] = targetMap.get(q);
            }
        }
        return ans;
    }
}
