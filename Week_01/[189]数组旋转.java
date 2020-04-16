package com.keanu.algorithm.leetcode.easy;

/**
 * 189 - easy
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        if (nums == null || nums.length <= 0 || start < 0 || end >= nums.length) return;
        for (int i = start, j = end; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
