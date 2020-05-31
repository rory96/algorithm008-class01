# 动态规划 - Dynamic Programming

> “Simplifying a complicated problem by breaking it down into simpler sub-problems”

动态规划就是将一个复杂的问题分解成更简单的字问题，本质上与递归或者分支没有太大的区别。唯一的区别就是有无最优的字结构（有无存储当前的递归状态）。

# 不同路径

从顶向下的思想，计算从目的地到开始地能走的所有路径。

## 状态方程

> 状态方程：dp\[i\]\[j\] = dp\[i - 1\]\[j\] + dp\[i\]\[j - 1\]
>
> 其中 dp\[0\]\[0\] 是终点，计算往右走和往下走两种情况有多少路径。

Java Code：

```java
class Solution {
    // DP
    // 超简单的dp，不需要再解释了
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

## 不同路径 II（有障碍的情况）

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        if (obstacleGrid[0][0] == 0) dp[0][0] = 1; // 如果第一个位置不是障碍，则设为1；否则永远到不了终点。
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) {
                    if (obstacleGrid[i][j] == 0) dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    if (obstacleGrid[i][j] == 0) dp[i][j] = dp[i - 1][j];
                } else {
                    if (obstacleGrid[i][j] == 0) dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }
}
```

和不同路径的题解基本思想是一样的，但是要考虑障碍物。代码如上。

# 最长公共子串

![img](/Users/leiyongqi/github/algorithm008-class01/Week_06/longgestCommonSubstring1.png)

![image-20200530134041410](/Users/leiyongqi/github/algorithm008-class01/Week_06/longgestCommonSubstring2.png)

主要思想就是通过将两个字符串放在一个二维数组中，计算最短字符串开始的最长子串。

Java Code：

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }
}
```

如果 row 和 col 上的字符一样，则该位置的最长公共子串长度为 **dp\[i - 1\]\[j - 1\] + 1**；若不相同，则该位置的最长公共子序列的长度为 **dp\[i - 1\]\[j\]** 和 **dp\[i\]\[j - 1\]** 中的**最大值**。

# 爬楼梯变形

## 变形一

**如果一次能爬1，2，3阶，能有多少种爬法？**

这时候的方程则变为：`f(n) = f(n - 1) + f(n - 2) + f(n - 3)`

## 变形二

**变形一的基础上，连续两次爬楼梯的阶数不能相同，此时有多少种爬法？**

```java

```

# 三角形最小路径和

## 解法一：递归、分治

用最基础的递归：

```java
class Solution {

    // 递归、分治
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) return 0;
        int size = triangle.size();
        return helper(0, 0, size, triangle);
    }

    private int helper(int level, int i, int size, List<List<Integer>> triangle) {
        if (level == size - 1) return triangle.get(level).get(i);
        int num = triangle.get(level).get(i) + Math.min(helper(level + 1, i, size, triangle), helper(level + 1, i + 1, size, triangle));
        return num;
    }
}
```

这种解法在 Leetcode 上执行超时：

![image-20200530155544585](/Users/leiyongqi/github/algorithm008-class01/Week_06/triangle1.png)

所以需要优化该方法，将递归路径记录下来。

## 解法二：记忆化搜索（递归）

将递归的路径记录下来，减少递归回数。不过这样空间复杂度就不是O(n)了。

```java
class Solution {
    int[][] memo;
    // 递归，记忆化搜索
    public int minimumTotal(List<List<Integer>> triangle) {
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
```

这种解法比之前的效率高出很多：

![记忆化搜索（递归）](/Users/leiyongqi/github/algorithm008-class01/Week_06/triangle2.png)

## 解法三：DP

自底向上的DP：

```java
class Solution {

    // DP,自底向上
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
```

使用 `triangle` 从倒数第二层开始记录最短路径，但是这种效率依旧比较低，需要用数组来优化。

### DP 优化

```java
class Solution {
    // DP，优化解法
    public int minimumTotal(List<List<Integer>> triangle) {
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
}
```

效率提高很多，但是居然没有记忆化搜索强？？？？？？？

![DP优化](/Users/leiyongqi/github/algorithm008-class01/Week_06/triangle3.png)

# 总结

动态规划可以总结为一下几步：

1. 找到公共的字问题。
2. 写出每个字问题的状态方程
3. 解题。

最重要的就是DP中的状态方程，如果能找到字问题及状态方程，题就很好解了。