/*
 * @lc app=leetcode.cn id=213 lang=java
 *
 * [213] 打家劫舍 II
 *
 * https://leetcode-cn.com/problems/house-robber-ii/description/
 *
 * algorithms
 * Medium (38.03%)
 * Likes:    271
 * Dislikes: 0
 * Total Accepted:    36.3K
 * Total Submissions: 95.5K
 * Testcase Example:  '[2,3,2]'
 *
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * 
 * 示例 1:
 * 
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 
 * 
 * 示例 2:
 * 
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 */

// @lc code=start
class Solution {

    // 滚动数组
    public int rob(int[] nums) {
        // 状态转移方程和 house robber 一样。
        // 但是因为是环形，所以要考虑第一个和最后一个偷不偷的问题
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        // 考虑第一个房子不偷
        int dp1 = 0, dp2 = 0, max1 = 0;
        for (int i = 1; i < len; i++) {
            max1 = Math.max(dp1 + nums[i], dp2);
            dp1 = dp2;
            dp2 = max1;
        }

        //考虑偷第一个房子
        dp1 = 0; dp2 = 0;
        int max2 = 0;
        for (int i = 0; i < len - 1; i++) {
            max2 = Math.max(dp1 + nums[i], dp2);
            dp1 = dp2;
            dp2 = max2;
        }
        return Math.max(max1, max2);
    }
}
// @lc code=end

