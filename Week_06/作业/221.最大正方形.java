/*
 * @lc app=leetcode.cn id=221 lang=java
 *
 * [221] 最大正方形
 *
 * https://leetcode-cn.com/problems/maximal-square/description/
 *
 * algorithms
 * Medium (42.38%)
 * Likes:    429
 * Dislikes: 0
 * Total Accepted:    54.1K
 * Total Submissions: 127.6K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * 
 * 示例:
 * 
 * 输入: 
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * 输出: 4
 * 
 */

// @lc code=start
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int row = matrix.length, col = matrix[0].length;
        // dp[i+1][j+1]表示了以(i,j)为右下角的正方形的最大面积
        // 状态转移方程为：dp[i+1][j+1] = Math.min(Math.min(dp[i+1][j],dp[i][j+1]),dp[i][j])
        // 其中 max 为最大正方形的边长
        int[][] dp = new int[row + 1][col + 1];
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i][j]);
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
        }
        return max * max;
    }
}
// @lc code=end

