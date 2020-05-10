import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (68.88%)
 * Likes:    395
 * Dislikes: 0
 * Total Accepted:    38.9K
 * Total Submissions: 56.1K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步，可进可退。（引用自
 * 百度百科 - 皇后 ）
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> solveNQueens(int n) {
        // 左对角线，右对角线
        Set<Integer> diag1 = new HashSet<>(), diag2 = new HashSet<>();
        // 列
        boolean[] cols = new boolean[n];
        // 棋盘，记录Queen的位置
        char[][] board = new char[n][n];
        // 初始化棋盘，Quenn未摆上棋盘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(n, 0, board, diag1, diag2, cols, res);
        return res;
    }
    private void dfs(int n, int row, char[][] board, Set<Integer> diag1, Set<Integer> diag2, boolean[] cols, List<List<String>> res) {
        if (row == n) {
            res.add(transfer(board, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 判断当前列，两个对角线是否能被攻击到。
            // 左对角线 row + i(行数+列数)是常数，右对角线row-i(行数-列数)是一个常数
            // 所以如果要判断对角线是否能被攻击到，则将该位置的row+i和row-i记录下来
            // 下次判断该数有没有被占用，则可以确定该位置能不能被攻击到
            // 感觉和组合，全排列的题目类似，都是回溯的思想，使用深度优先搜索。
            if (diag1.contains(row + i) || diag2.contains(row - i) || cols[i]) continue;
            cols[i] = true;
            diag1.add(row + i);
            diag2.add(row - i);
            board[row][i] = 'Q';
            dfs(n, row + 1, board, diag1, diag2, cols, res);
            board[row][i] = '.';
            diag2.remove(row - i);
            diag1.remove(row + i);
            cols[i] = false;
        }
    }
    private List<String> transfer(char[][] board, int n) {
        List<String> toAdd = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            toAdd.add(String.valueOf(board[i]));
        }
        return toAdd;
    }
}
// @lc code=end

