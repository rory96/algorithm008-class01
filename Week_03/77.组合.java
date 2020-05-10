import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (73.52%)
 * Likes:    262
 * Dislikes: 0
 * Total Accepted:    48.6K
 * Total Submissions: 66.1K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        combine(n, k, 1, stack, res);
        return res;
    }
    // 这种解法存在很多无用的递归循环
    private void combine(int n, int k, int index, Stack<Integer> stack, List<List<Integer>> res) {
        if (stack.size() == k) {
            res.add(new ArrayList<>(stack));
        }
        for (int i = index; i <= n; i++) {
            stack.push(i);
            combine(n, k, i + 1, stack, res);
            stack.pop();
        }
    }
    // 剪枝 去掉不必要的递归
    // 更好的解法
    public void combine1(int n, int k, int index, Stack<Integer> stack, List<List<Integer>> res) {
        if (stack.size() == k) {
            res.add(new ArrayList<>(stack));
            return;
        }
        // 我们需要从[i, n]区间中找到 k - stack.size() 个元素
        // i < n + 1 不一定全部都要走完的，所以i会有一个上限值
        // 如果 n=15, k=4时
        // stack.size() == 1 的时候，接下来要选择3个元素，所以i的最大值是13，最后被选择的是[13,14,15]
        // stack.size() == 2 的时候，只用选择2个元素，所以i最大值是14，最后选择的是[14,15]
        // stack.size() == 3 的时候，只用选择一个元素了，i最大值就是15，最后选择的就是[15]
        // 根据这个规律，可以算出i的最大值就是n - (k - stack.size()) + 1
        // 这样就可以减去很多不必要的递归
        for (int i = index; i <= n - (k - stack.size()) + 1; i++) {
            stack.push(i);
            combine(n, k, i + 1, stack, res);
            stack.pop();
        }
    }
}
// @lc code=end

