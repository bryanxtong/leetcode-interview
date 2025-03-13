package org.example;

/**
 * Leetcode 2202, using prefix Max and suffix Min
 */
public class SumOfBeautyInTheArray {
    public int sumOfBeauties(int[] nums) {

        int ans = 0;
        int prefixMax[] = new int[nums.length];
        prefixMax[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            prefixMax[i] = Math.max(nums[i - 1], prefixMax[i - 1]);
        }

        int suffixMin[] = new int[nums.length];
        suffixMin[nums.length - 1] = Integer.MAX_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i + 1], suffixMin[i + 1]);
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > prefixMax[i] && nums[i] < suffixMin[i]) {
                ans += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new SumOfBeautyInTheArray().sumOfBeauties(new int[]{3, 2, 1});
    }
}
