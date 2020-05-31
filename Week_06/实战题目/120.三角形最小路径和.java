import java.util.List;

/*
 * @lc app=leetcode.cn id=120 lang=java
 *
 * [120] 三角形最小路径和
 *
 * https://leetcode-cn.com/problems/triangle/description/
 *
 * algorithms
 * Medium (64.71%)
 * Likes:    399
 * Dislikes: 0
 * Total Accepted:    59.4K
 * Total Submissions: 91.8K
 * Testcase Example:  '[[2],[3,4],[6,5,7],[4,1,8,3]]'
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 
 * 
 * 
 * 例如，给定三角形：
 * 
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 * 
 * 
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 
 * 
 * 
 * 说明：
 * 
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * 
 */

// @lc code=start
class Solution {
    
    // DP，使用传进来的List
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    // DP优化，分配一个数组
    public int minimumTotal1(List<List<Integer>> triangle) {
        int size = triangle.size();
        if (size == 0) return 0;
        int[] dp = new int[size];
        // 初始化dp数组，使用最后一行数组
        for (int i = 0; i < size; i++) {
            dp[i] = triangle.get(size - 1).get(i);
        }
        // 自底向上dp
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    int[][] memo;
    // 递归，记忆化搜索
    public int minimumTotal2(List<List<Integer>> triangle) {
        int size = triangle.size();
        memo = new int[size][size];
        return help(0, 0, size, triangle);
    }

    private int help(int level, int i, int size, List<List<Integer>> triangle) {
        if (level == size - 1) return memo[level][i] = triangle.get(level).get(i);
        if (memo[level][i] == 0) {
            memo[level][i] = triangle.get(level).get(i) + Math.min(help(level + 1, i, size, triangle), help(level + 1, i + 1, size, triangle));
        }
        return memo[level][i];
    }
}
// @lc code=end

