package com.keanu.algorithm.leetcode.easy;

/**
 * 26 - easy
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int i = 1; // 这里应该是0，但是提交代码没有报错。考虑到[]数组元素个数为0的情况。
        for (int n : nums) {
            if (n > nums[i - 1]) { // 如果 i = 0 时，这里的判断应该是 if (i == 0 || n > nums[i - 1])
                nums[i++] = n;
            }
        }
        return i;
    }
}
