package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode 5
 * can refer to official for a better way
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        List<String> ans = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            String palindrome = isPalindrome(s, i, i);
            String palindrome1 = isPalindrome(s, i, i + 1);
            ans.add(palindrome);
            ans.add(palindrome1);
        }
        Collections.sort(ans, Comparator.comparingInt(String::length));
        return ans.get(ans.size() - 1);

    }

    private String isPalindrome(String s, int m, int n) {

        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            if (m >= 0 && n <= s.length() - 1
                    && s.charAt(m) == s.charAt(n)) {
                ans = s.substring(m, n + 1);
                m--;
                n++;
            } else {
                break;
            }
        }
        return ans;
    }
}
