package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 3306 using sliding window
 */
public class CountOfSubstringsContainingEveryVowelAndKConsonantsII {

    public long countOfSubstrings(String word, int k) {
        return f(word, k) - f(word, k+1);
    }

    public long f(String word, int k) {
        char[] charArray = word.toCharArray();
        long count = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        for (int right = 0; right < charArray.length; right++) {
            char rightChar = charArray[right];
            if (checkvowel(rightChar)) {
                map.put(charArray[right], map.getOrDefault(charArray[right], 0) + 1);
            } else {
                map.put('b', map.getOrDefault('b', 0) + 1);
            }
            while (map.getOrDefault('a', 0) >= 1
                    && map.getOrDefault('e', 0) >= 1
                    && map.getOrDefault('i', 0) >= 1
                    && map.getOrDefault('o', 0) >= 1
                    && map.getOrDefault('u', 0) >= 1
                    && map.getOrDefault('b', 0) >= k) {
                count++;
                //count the right chars
                count += word.length() - right - 1;
                char leftChar = charArray[left];
                if (checkvowel(leftChar)) {
                    map.put(charArray[left], map.getOrDefault(charArray[left], 0) - 1);
                } else {
                    map.put('b', map.getOrDefault('b', 0) - 1);
                }
                left++;
            }
        }

        return count;
    }

    public boolean checkvowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) {
        CountOfSubstringsContainingEveryVowelAndKConsonantsII c = new CountOfSubstringsContainingEveryVowelAndKConsonantsII();
        c.countOfSubstrings("ieaouqqieaouqq", 1);
    }

}
