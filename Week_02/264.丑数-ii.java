import java.util.TreeSet;

/*
 * @lc app=leetcode.cn id=264 lang=java
 *
 * [264] 丑数 II
 *
 * https://leetcode-cn.com/problems/ugly-number-ii/description/
 *
 * algorithms
 * Medium (51.24%)
 * Likes:    263
 * Dislikes: 0
 * Total Accepted:    23.2K
 * Total Submissions: 45.2K
 * Testcase Example:  '10'
 *
 * 编写一个程序，找出第 n 个丑数。
 * 
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * 
 * 示例:
 * 
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 
 * 说明:  
 * 
 * 
 * 1 是丑数。
 * n 不超过1690。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, j = 0, k = 0, p = 1;
        while (p < n) {
            dp[p] = Math.min(Math.min(dp[i] * 2, dp[j] * 3), dp[k] * 5);
            if (dp[p] == dp[i] * 2) i++;
            if (dp[p] == dp[j] * 3) j++;
            if (dp[p] == dp[k] * 5) k++;
            p++;
        }
        return dp[n - 1];
    }

    public int nthUglyNumber1(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, j = 0, k = 0, p = 1;
        while (p < n) {
            dp[p] = Math.min(Math.min(dp[i] * 2, dp[j] * 3), dp[k] * 5);
            if (dp[p] == dp[i] * 2) i++;
            if (dp[p] == dp[j] * 3) j++;
            if (dp[p] == dp[k] * 5) k++;
            p++;
        }
        return dp[n - 1];
    }
}
// @lc code=end

