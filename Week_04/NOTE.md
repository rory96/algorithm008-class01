# 深度优先搜索 - DFS

## 代码模版

多叉树 / 图：

```python
visited = set()

def dfs(node, visited):
  if node in visited: # recursion terminator
    return
  visited.add(node);
  # process current node here
  # ...
  for next_node in node.children():
    if not next_node in visited:
      dfs(node, visited)
```

二叉树：

```java
Set<TreeNode> visited = new HashSet<>();

private void dfs(TreeNode node, Set<TreeNode> visited) {
  // recursion terminator
  if (set.contains(node)) return;
  
  visited.add(node);
  // process current node here
  // ...
  for (TreeNode next : node.children()) {
    if (!set.contains(next)) {
      dfs(next, visited);
    }
  }
}
```

非递归写法（手动维护栈）：

```python
def dfs(self, tree):
  if tree.root is none:
    reutrn []
  visited, stack = [], [tree.root]
  
  while stack:
    node = stack.pop()
    
    if not node in visited:
      process(node)
    
    visited.add(node)
    nodes = generate_related_nodes(node)
    stack.push(nodes)
  
  # other processing work
  # ...
    
```

Java：

```java

```



# 广度优先遍历 - BFS

## 代码模版

广度优先遍历需要用到队列来实现：

```python
visited = set()
def BFS(graph, start, end):
  queue = []
  queue.append([start])
  visited.add(start)
  while queue:
    node = queue.pop()
    visited.add(node)
    process(node)
    nodes = generate_related_nodes(node)
    queue.push(nodes)
  # other processing work
  # ...
```

Java：

```java

```

# 贪心算法 - Greedy

贪心算法是在**每一步**选择中都采取当前状态下**最好**或**最优**的选择，从而导致结果是全局最好或最优的算法。

> 不一定每一步选择最优解，最后的结果就是最优解，只有在特定的情况，你能证明每一步选最优解最后能得出最优解的时候，用贪心算法最合适。

## 适合的场景

最小路径，最小生成树，求哈夫曼编码等等。。。

# 二分查找 - Binary Search

## 二分查找的前提

1. 目标函数单调性（单调递增或单调递减）
2. 存在上下界
3. 能够通过索引访问（Index accessible）

## 代码模版

```python
# 假设数组升序排列
left, right = 0, len(array) - 1
while left <= right:
  mid = (left + right) / 2
  if array[mid] == target:
    # find the target
    break or return result
  elif array[mid] < target:
    left = mid + 1
  else:
    rigth = mid - 1
```

# 寻找半有序数组的中间无序的位置

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 `[0,1,2,4,5,6,7]` 可能变为 `[4,5,6,7,0,1,2]` )。

使用`二分查找法`查找出无序的地方，时间复杂度需要为`O(logn)`。

## 思路

