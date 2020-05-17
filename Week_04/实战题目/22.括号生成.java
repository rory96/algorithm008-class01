import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (75.24%)
 * Likes:    995
 * Dislikes: 0
 * Total Accepted:    122.9K
 * Total Submissions: 163.1K
 * Testcase Example:  '3'
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：n = 3
 * 输出：[
 * ⁠      "((()))",
 * ⁠      "(()())",
 * ⁠      "(())()",
 * ⁠      "()(())",
 * ⁠      "()()()"
 * ⁠    ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        // 左括号和右括号不能大于n，所以递归结束的条件应该以左右括号的个数来判断
        // 而且有括号的个数一定要小于左括号的个数，括号才能合法
        List<String> result = new ArrayList<>();
        generate(0, 0, n, "", result);
        return result;
    }
    private void generate(int left, int right, int n, String s, List<String> res) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }
        if (left < n) generate(left + 1, right, n, s + "(", res);
        if (right < left) generate(left, right + 1, n, s + ")", res);
    }
}
// @lc code=end

