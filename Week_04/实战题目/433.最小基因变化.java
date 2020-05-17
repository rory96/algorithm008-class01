import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=433 lang=java
 *
 * [433] 最小基因变化
 *
 * https://leetcode-cn.com/problems/minimum-genetic-mutation/description/
 *
 * algorithms
 * Medium (49.65%)
 * Likes:    33
 * Dislikes: 0
 * Total Accepted:    4.4K
 * Total Submissions: 8.8K
 * Testcase Example:  '"AACCGGTT"\n"AACCGGTA"\n["AACCGGTA"]'
 *
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 
 * 现在给定3个参数 — start, end,
 * bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回
 * -1。
 * 
 * 注意:
 * 
 * 
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 * 
 * 
 * 示例 1:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 
 * 返回值: 1
 * 
 * 
 * 示例 2:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 
 * 返回值: 2
 * 
 * 
 * 示例 3:
 * 
 * 
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 
 * 返回值: 3
 * 
 * 
 */

// @lc code=start
class Solution {
    // BFS
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[bank.length];
        int res = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String tmp = queue.poll();
                if (end.equals(tmp)) return res;
                for (int j = 0; j < bank.length; j++) {
                    if (!visited[j] && isValid(tmp, bank[j])) {
                        visited[j] = true;
                        queue.add(bank[j]);
                    }
                }
            }
            res++;
        }
        return -1;
    }
    private boolean isValid(String tmp, String bank) {
        boolean diff = false;
        for (int i = 0;i < tmp.length(); i++) {
            if (tmp.charAt(i) != bank.charAt(i)) {
                if (diff) return false;
                diff = true;
            }
        }
        return true;
    }
}
// @lc code=end

