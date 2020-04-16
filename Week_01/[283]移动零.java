package com.keanu.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * 283 - easy
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        // 初始化0的位置
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
