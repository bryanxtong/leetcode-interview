package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//leetcode 3297
public class CountSubstringsThatCanBeRearrangedToContainAStringI {

    public long validSubstringCount(String word1, String word2) {
        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < word2.length(); i++) {
            targetMap.put(word2.charAt(i), targetMap.getOrDefault(word2.charAt(i), 0) + 1);
        }

        int left = 0;
        Map<Character, Integer> sourceMap = new HashMap<>();
        long result = 0;
        for (int right = 0; right < word1.length(); right++) {
            sourceMap.put(word1.charAt(right), sourceMap.getOrDefault(word1.charAt(right), 0) + 1);
            while (check(word2, targetMap, sourceMap)) {
                result += word1.length() - right;
                sourceMap.put(word1.charAt(left), sourceMap.getOrDefault(word1.charAt(left), 0) - 1);
                left++;
            }
        }

        return result;
    }

    public boolean check(String word2, Map<Character, Integer> targetMap, Map<Character, Integer> sourceMap) {
        Set<Map.Entry<Character, Integer>> entries = targetMap.entrySet();
        boolean match = true;
        for (Map.Entry<Character, Integer> entry : entries) {
            Character key = entry.getKey();
            if (sourceMap.getOrDefault(key, 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
