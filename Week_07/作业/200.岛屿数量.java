/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿数量
 *
 * https://leetcode-cn.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (49.20%)
 * Likes:    546
 * Dislikes: 0
 * Total Accepted:    100.2K
 * Total Submissions: 203.3K
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 
 * 此外，你可以假设该网格的四条边均被水包围。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        int numOfIsland = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    numOfIsland++;
                    dfs(grid, i, j);
                }
            }
        }
        return numOfIsland;
    }
    private void dfs(char[][] grid, int m, int n) {
        if (!inArea(grid, m, n)) {
            return;
        }
        grid[m][n] = '0';
        dfs(grid, m - 1, n);
        dfs(grid, m + 1, n);
        dfs(grid, m, n - 1);
        dfs(grid, m, n + 1);
    }
    private boolean inArea(char[][] grid, int m, int n) {
        return m >= 0 && n >= 0 && m < grid.length && n < grid[0].length && grid[m][n] == '1';
    }
}
// @lc code=end

