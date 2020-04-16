package com.keanu.algorithm.leetcode.easy;

/**
 * 66 - easy - plus one
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 0;
        carry = (digits[digits.length - 1] + 1) / 10;
        digits[digits.length - 1] = (digits[digits.length - 1] + 1) % 10;
        int i = digits.length - 2;
        while (carry > 0 && i >= 0) {
            int tmp = digits[i] + carry;
            carry = tmp / 10;
            digits[i--] = tmp % 10;
        }
        if (carry > 0) {
            int[] res = new int[digits.length + 1];
            res[0] = carry;
            System.arraycopy(digits, 0, res, 1, res.length - 1);
            return res;
        }
        return digits;
    }
}
