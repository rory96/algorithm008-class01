import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (74.81%)
 * Likes:    697
 * Dislikes: 0
 * Total Accepted:    125.1K
 * Total Submissions: 164.7K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> used = new HashSet<>();
        permute(nums, len, 0, used, stack, res);
        return res;
    }
    // 和组合题差不多的思想，递归，回溯。
    // 唯一的区别就是每次递归需要遍历所有元素，过滤使用过的元素
    private void permute(int[] nums, int len, int depth, Set<Integer> used, Stack<Integer> stack, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used.contains(i)) continue;
            stack.push(nums[i]);
            used.add(i);
            permute(nums, len, depth + 1, used, stack, res);
            used.remove(i);
            stack.pop();
        }
    }
}
// @lc code=end

