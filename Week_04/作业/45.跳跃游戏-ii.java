/*
 * @lc app=leetcode.cn id=45 lang=java
 *
 * [45] 跳跃游戏 II
 *
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (33.85%)
 * Likes:    554
 * Dislikes: 0
 * Total Accepted:    60.2K
 * Total Submissions: 165.9K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 示例:
 * 
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 
 * 
 * 说明:
 * 
 * 假设你总是可以到达数组的最后一个位置。
 * 
 */

// @lc code=start
class Solution {
    // 自己解法，效率较低：302ms
    public int jump(int[] nums) {
        int goal = nums.length - 1;
        int count = 0;
        while (goal != 0) {
            for (int i = 0; i < goal; i++) {
                if (nums[i] + i >= goal) {
                    goal = i;
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    // 贪心算法
    public int jump1(int[] nums) {
        int len = nums.length;
        if (len <= 1) return 0;
        int count = 1;
        int index = 0;
        int max = nums[0];
        while (max < len - 1) {
            int curMax = 0;
            for (int i = index + 1; i <= max; i++) {
                if (nums[i] + i > curMax) {
                    index = i;
                    curMax = i + nums[i];
                }
            }
            max = index + nums[index];
            count++;
        }
        return count;
    }
}
// @lc code=end

