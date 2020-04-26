# 哈希表 (Hash Table)

哈希表又被叫做散列表，其中最重要的部分就是**散列函数(Hash Function，映射函数)**，哈希表通过散列函数将数据映射到表中的某个位置来存储(通过散列函数来确定数组在表中的位置)。

但是如果散列函数设计的不好或者需要存储的数据的 hash 值重复了，会发生 Hash 碰撞，这时候该位置就会有重复值。解决方法有很多种：

1. 存储到下一个为空的位置（如果有大量 Hash 碰撞的时候，这种方法效率很低）
2. 链表（Java 中的 `HashMap` 就是采用链表的方式来解决 Hash 碰撞）

## HashMap

### Overview

> An instance of HashMap has two parameters that affect its performance: initial capacity and load factor. The capacity is the number of buckets in the hash table, and the initial capacity is simply the capacity at the time the hash table is created. The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. When the number of entries in the hash table exceeds the product of the load factor and the current capacity, the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.

我们查看官方 api 的文档，其中有这样一句话，一个 HashMap 实例有两个可以影响其性能的参数：初始化容量 (Initial Capacity) 和加载因子 (Load Factor)。Initial capacity 是 HashMap 在创建的时候 bucket 的数量，load factor 是衡量在一个 HashMap 自动扩容前允许的容量 (意思是当 HashMap 中的数据个数超过了当前容量和 load factor 的乘积时，会扩容)。扩容时，HashMap 会进行 rehash，内部数据结构会重建，而容量会变为原来的两倍。

我这里主要说 ***Java 11*** 中的 `HashMap`。

### Parameters

```java
// 默认容量为 16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
// 默认的 load factor 是 0.75
static final float DEFAULT_LOAD_FACTOR = 0.75f;
// 当发生Hash碰撞时，元素会加在这个桶中的链表最后面，当链表长度大于等于8时，链表会转为红黑树
static final int TREEIFY_THRESHOLD = 8;
// 当链表中的容量小于等于6时，红黑树会自动转换为链表
static final int UNTREEIFY_THRESHOLD = 6;
```

HashMap 中维护了一个 Node 数组 - `table`，Node 是 HashMap 中的一个内部类。

```java
transient Node<K,V>[] table;
// static class Node<K,V> implements Map.Entry<K,V>
```

其他参数：

```java
transient Set<Map.Entry<K,V>> entrySet;

transient int size;

transient int modCount;

int threshold; // 下一次扩容时需要的容量 (capacity * load factor)
```



### Hash Function

HashMap 中使用的 Hash 函数：

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

### 添加元素 - put

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length; // 1
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null); // 2
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k)))) // 3
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value); // 4
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null); // 5
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash); // 6
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k)))) // 7
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

1. 我们使用 HashMap 时经常调用的 put 方法实际上是调用了 putVal，首先 HashMap 是会判断当前的 table 是不是空的，如果是空的，则先调用 resize 方法进行扩容。
2. 然后通过计算出的 hash 值找到元素应该存放的位置，如果当前位置还未存放任何元素，就将这个数据存放在这个位置。
3. 如果该节点的 key 与要插入的数据的 key 值相等，则直接替换。
4. 否则，如果该位置是一个 TreeNode，则调用 putTreeVal 方法将元素插入红黑树中。
5. 如果节点不为空且不是树形结构，说明该节点为链表，遍历链表，将该元素加入到链表最后。
6. 然后判断链表个数是否达到需要转换为红黑树的标准，如果达到了，则将链表转化为红黑树，完成后跳出循环，执行结束。
7. 遍历链表时，如果遍历到的节点的 key 值与要插入的数据的 key 值一样，则跳出循环。
8. 判断 `e != null` ，将新值替换旧值，返回旧值。

HashMap 中内部链表个数大于 8 时，转成了红黑树，等学到红黑树再添加这部分。

# 树，二叉树，二叉搜索树

## 树

树是一种特殊的链表，有树形的结构。

## 二叉树

二叉树每个节点只有两个子节点，也是树形结构。

## 二叉搜索树 (BST)

二叉搜索树又称为有序二叉树，所有节点都**大于**其**所有左子节点**，都**小于**其**所有右子节点**。而二叉搜索树的中序遍历就是按照从小到大遍历。

## 遍历方式 (BST)

- 前序遍历
  - 根，左，右
- 中序遍历
  - 左，根，右
- 后序遍历
  - 左，右，根

遍历主要有循环和递归两种方法，其中**递归**的代码较简洁，易看懂。

### 前序遍历

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }
    private void preorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
```

### 中序遍历

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
```

### 后序遍历

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }
    private void postorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }
}
```

# 堆和二叉堆、图

## 堆

堆是一种基于树形的特殊数据结构，且是一种 `complete tree` (完整的树：除了叶子节点，其他节点是满的)，所有的节点的值都大于其子节点的值。堆可以迅速找到一堆数中的最大或最小值：

- **大顶堆**：根节点最大
- **小顶堆**：根节点最小

## 堆常见的操作

- *find-max* (or *find-min*): find a maximum item of a max-heap, or a minimum item of a min-heap, respectively (a.k.a. *[peek](https://en.wikipedia.org/wiki/Peek_(data_type_operation))*)
- *insert*: adding a new key to the heap (a.k.a., *push*[[4]](https://en.wikipedia.org/wiki/Heap_(data_structure)#cite_note-4))
- *extract-max* (or *extract-min*): returns the node of maximum value from a max heap [or minimum value from a min heap] after removing it from the heap (a.k.a., *pop*[[5]](https://en.wikipedia.org/wiki/Heap_(data_structure)#cite_note-5))
- *delete-max* (or *delete-min*): removing the root node of a max heap (or min heap), respectively
- *replace*: pop root and push a new key. More efficient than pop followed by push, since only need to balance once, not twice, and appropriate for fixed-size heaps.[[6]](https://en.wikipedia.org/wiki/Heap_(data_structure)#cite_note-6)

> 摘自[维基百科](https://en.wikipedia.org/wiki/Heap_(data_structure))

## 二叉堆

通过二叉树来实现，是一种完全二叉树。Java 中的 PriorityQueue 是一种二叉堆的结构。

## 其他

- Fibonacci Heap
- Strict Fibonacci Heap
- 2 - 3 Heap