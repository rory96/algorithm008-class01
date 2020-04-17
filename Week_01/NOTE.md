# Array，LinkedList，SkipList

## Array 时间复杂度

- Prepend, Append：O(1)
- Insert, Delete：O(n)
- Lookup：O(1)

## 链表的时间复杂度

- Prepend, Append：O(1)
- Lookup：O(n)
- Insert, Delete：O(1)

## 跳表：SkipList

跳表是基于链表的数据结构优化之后的一种数据结构。

### 跳表是如何对链表进行优化的

在链表的基础上，跳表增加了多级索引，查找元素时，从最顶层索引一直向下找，直到查找到该元素。

![Skip List](skiplist1.png)

### 跳表时间复杂度分析

第一级索引的个数为 n/2，第二级索引的个数为 n/4，由此类推，第 k 级索引的格式就是 n/(2^k)。假设索引有 h 级，最高级的索引有 2 个结点。n/(2^h) = 2，从而求得 h = log2(n)-1。

以上可得知，索引的高度为 logn，每层最多遍历的节点个数为 3。所以在跳表中查询任意数据的时间复杂度就是 O(logn)。

### 跳表空间复杂度分析

原始链表大小为 n，每 2 个结点抽 1 个，每层索引的结点数：
$$
\frac{n}{2}, \frac{n}{4}, \frac{n}{8}, ... ,8, 4, 2
$$
原始链表大小为 n，每 3 个节点抽 1 个，每层索引的节点数：
$$
\frac{n}{3}, \frac{n}{9}, \frac{n}{27}, ... , 9, 3, 1
$$
每层索引的个数相加，无线接近于 n。所以空间复杂度是 O(n)。

## 总结

- 熟悉数组，链表，跳表是如何实现的
- 牢记三者的时间复杂度，空间复杂度
- 工程中的应用：LRU Cache(Linked List)，Redis(Skip List)
- 跳表：升维思想，用空间来换时间



# 实战项目

## Move Zeroes

记录 0 的位置，将非 0 元素与 0 元素互换。

### 题解

```java
class Solution {
    public void moveZeroes(int[] nums) {
        // assume it's 0's index
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // nums[i] != 0, change nums[i]'s value into nums[j] which the value is 0;
                // the case that nums[j] == nums[i] != 0 exists.
                nums[j] = nums[i];
                if (i != j) {
                    // So judge if i equals j or not
                    // i doesn't equals j, set 0 into nums[i]'s value
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
```

## Container with Most Water

最左和最右双指针方法。很简单的一道 Medium 难度的题，不需要说明了。

### 题解

```java
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j;) {
            int area = (j - i) * Math.min(height[i], height[j]);
            max = Math.max(max, area);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}
```

时间复杂度：`O(n)`

## Climbing Stairs

重复最近子问题。爬楼梯的问题相当于一个Fibonacci数列，直接用Fibonacci数列的思想来解决即可。

- Fibonacci 数列的几种解法

  - 递归：
    $$
    时间复杂度：O(2^n) \quad 空间复杂度：O(1)
    $$
  
- 数组： 
    $$
    时间复杂度：O(n) \quad 空间复杂度：O(n)
    $$
    
  - 缓存：
  
  $$
    时间复杂度：O(n) \quad 空间复杂度：O(1)
  $$

### 题解

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int x = 1, y = 2, z = 3;
        for (int i = 3; i <= n; i++) {
            z = x + y;
            x = y;
            y = z;
        }
        return z;
    }
}
```

时间复杂度：`O(n)`

## 3 Sum

目标是找出数组中三个元素满足 `a + b + c = 0`，可以遍历数组，并在循环中使用左右边界的双指针，三个元素加起来为 0，就将这三个元素加入到结果集中。详细可以通过代码来理解：

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++) {
            // k元素为目标数，且k大于0时，则不会有等于0的结果了。
            if (nums[k] > 0) return result;
            // 使用i，j左右边界双指针，i > k
            int i = k + 1, j = nums.length - 1;
            // 重复元素跳过
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            while (i < j) {
                // 若结果大于0，则将j往左移动
                if (nums[i] + nums[j] + nums[k] > 0) {
                    j--;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    // 结果小于0，将i向右移动
                    i++;
                } else {
                    // 找到结果集后，将i向右移，j向左移动，继续查找是否还有符合条件的结果。
                    result.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    while(i < j && nums[j] == nums[--j]);
                    while(i < j && nums[i] == nums[++i]);
                }
            }
            // 若该k元素与其他元素均没有符合条件的结果，将k移到下一个元素，继续查找。
        }
        return result;
    }
}
```

时间复杂度：
$$
O(n^2)
$$

# 栈和队列

- Stack：FILO；添加删除操作皆为O(1)，查询为O(n)
- Queue：FIFO；添加删除操作皆为O(1)，查询为O(n)

  ## 双端队列 Deque：Double-End Queue

双端队列是 Stack 和 Queue 的结合，可以从两端添加或者删除数据，添加删除皆为O(1)，查询为O(n)。

## Priority Queue 优先队列

Java 实现的优先级队列：[PriorityQueue\<E\>](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html)

> An unbounded priority [queue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html) based on a priority heap. The elements of the priority queue are ordered according to their [natural ordering](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html), or by a [`Comparator`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html) provided at queue construction time, depending on which constructor is used. A priority queue does not permit `null` elements. A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so may result in `ClassCastException`).
>
> **Note that this implementation is not synchronized.** Multiple threads should not access a `PriorityQueue` instance concurrently if any of the threads modifies the queue. Instead, use the thread-safe [`PriorityBlockingQueue`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/PriorityBlockingQueue.html) class.

基于优先级堆(priority heap)，默认情况下由元素的自然顺序排序。也可以在构造方法中传一个自定义的 `Comparator` 来排序。

`PriorityQueue` 不允许 `null` 元素，也不允许不可排序的元素 (可能会报错 `ClassCasrException`)。

`PriorityQueue` 不是线程安全的，如果有多线程并发操作的情况，推荐使用 `PriorityBlockingQueue`。

插入操作：O(1)

取出操作：O(logN) - 通过元素的优先级取出，底层数据结构比较复杂

数据结构：heap，BST，treap

 ## 作业：改写代码

