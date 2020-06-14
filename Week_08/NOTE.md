# 排序算法

## 冒泡排序

```java
public static int[] bubbleSort(int[] array) {
    if (array.length == 0)
        return array;
    for (int i = 0; i < array.length; i++){  //外层循环一次为一趟排序
        /*设置标识，判断这趟排序是否发生了交换。
       如果未发生交换，则说明数组已经有序，不必再排序了*/
        boolean isSwap = false;
        for (int j = 0; j < array.length - 1 - i; j++) //内层循环一次为一次相邻比较
            if (array[j + 1] < array[j]) {
                int temp = array[j + 1];
                array[j + 1] = array[j];
                array[j] = temp;
                isSwap = true;
            }
        if(!isSwap)
            break;
    }
    return array;
}
```

时间复杂度：

最好 `O(n)`, 最坏 `O(n^2)`，平均 `O(n^2)`。

## 插入排序

```java
public static void sort(int[] numbers){
    for (int i = 1; i < numbers.length; i++) {
        int currentNumber = numbers[i];
        int j = i - 1;
        while (j >= 0 && numbers[j] > currentNumber) {
            numbers[j + 1] = numbers[j];
            j--;
        }
        numbers[j + 1] = currentNumber;
    }
}
```

最坏情况：`O(n^2)`，最好情况：`O(n)`。

## 快速排序

```java
public int partition(int[] arr, int left, int right) {
    int temp = arr[left];
    while (right > left) {
        // 先判断基准数和后面的数依次比较
        while (temp <= arr[right] && left < right) {
            --right;
        }
        // 当基准数大于了 arr[right]，则填坑
        if (left < right) {
            arr[left] = arr[right];
            ++left;
        }
        // 现在是 arr[right] 需要填坑了
        while (temp >= arr[left] && left < right) {
            ++left;
        }
        if (left < right) {
            arr[right] = arr[left];
            --right;
        }
    }
    arr[left] = temp;
    return left;
}

public void quickSort(int[] arr, int left, int right) {
    if (arr == null || left >= right || arr.length <= 1)
        return;
    int mid = partition(arr, left, right);
    quickSort(arr, left, mid);
    quickSort(arr, mid + 1, right);
}
```

平均时间复杂度：`O(nlogn)`

## 选择排序

```java
public static int[] selectionSort(int[] array) {
    if (array.length == 0)
         return array;
    for (int i = 0; i < array.length; i++) {
        int minIndex = i;
        for (int j = i; j < array.length; j++) {
            if (array[j] < array[minIndex]) //找到最小的数
                minIndex = j; //将最小数的索引保存
        }
        int temp = array[minIndex]; //将最小数和无序区的第一个数交换
        array[minIndex] = array[i];
        array[i] = temp;
    }
    return array;
}
```

## 希尔排序

```java
public static void shellSort(int[] arr) {
    int length = arr.length;
    int temp;
    for (int step = length / 2; step >= 1; step /= 2) {
        for (int i = step; i < length; i++) {
            temp = arr[i];
            int j = i - step;
            while (j >= 0 && arr[j] > temp) {
                arr[j + step] = arr[j];
                j -= step;
            }
            arr[j + step] = temp;
        }
    }
}
```

