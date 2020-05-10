import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (58.12%)
 * Likes:    287
 * Dislikes: 0
 * Total Accepted:    56.7K
 * Total Submissions: 97K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) return res;
        Deque<Integer> queue = new ArrayDeque<>(len);
        Set<String> usedStr = new HashSet<>();
        Set<Integer> used = new HashSet<>(len);
        permute(nums, len, 0, usedStr, used, queue, res);
        return res;
    }
    // 比较费时？目前只能想出这种方法
    // 对全排列进行改造
    private void permute(int[] nums, int len, int depth, Set<String> usedStr, Set<Integer> used, Deque<Integer> queue, List<List<Integer>> res) {
        if (depth == len) {
            String str = encode(queue);
            if (!usedStr.contains(str)) {
                res.add(new ArrayList<>(queue));
                usedStr.add(str);
            }
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used.contains(i)) continue;
            queue.add(nums[i]);
            used.add(i);
            permute(nums, len, depth + 1, usedStr, used, queue, res);
            used.remove(i);
            queue.removeLast();
        }
    }
    private String encode(Deque<Integer> queue) {
        StringBuffer sb = new StringBuffer();
        for (int num : queue) {
            sb.append(num);
        }
        return sb.toString();
    }
}
// @lc code=end

