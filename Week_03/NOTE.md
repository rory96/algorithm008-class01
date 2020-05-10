# 递归 (Recursion)

递归代码的模版：

1. 递归终结代码
2. 处理
3. 递归

```java
public void recursion(int level, int param) {
  // terminator 递归终止条件
  if (level > MAX_LEVEL) {
    // process result
    return;
  }
  // process current logic
  // 处理当前逻辑
  process(level, param);
  // drill down
  // 进入下一层递归
  recursion(level + 1, newParam);
  
  // restore current status
  // 清理当前状态
}
```

# 分治

## 分治代码模版

```python
def divide_conquer(problem, param1, param2, ...):
  # recursion terminator
	if problem is None:
  	print_result
  	return
	# prepare data
	data = prepare_data(problem)
	subproblems = split_problem(problem, data)
	# conquer subproblems
	subresult1 = self.divide_conquer(subproblems[0], p1, ...)
	subresult2 = self.divide_conquer(subproblems[1], p1, ...)
	subresult3 = self.divide_conquer(subproblems[2], p1, ...)
	...
	# process and generate the final result
	result = process_result(subresult1, subresult2, subresult3, …)
	
	# revert the current level states
```

# 回溯

