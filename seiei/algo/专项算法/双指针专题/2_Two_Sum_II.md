## Two Sum II

[题目见: https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

## 分析

#### 1. 双指针

因为数组已经升序排序。使用双指针分别从起始和末尾位置遍历。

双指针low=0，high=len-1。如果num[low] + num[high] < target，说明和偏小，low右移；如果num[low] + num[high] > target，说明和偏大，high左移。直到和=target。

时间复杂度为O(n)，空间复杂度为O(1)。

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] + numbers[high] < target) low ++;
            else if (numbers[low] + numbers[high] > target) high --;
            else return new int[] {low + 1, high + 1};
        }
        return null;
    }
}
```

#### 2. 二分查找

每次选定一个数i，从[i+1,len]之间通过二分法查找。

时间复杂度为O(nlogn)，空间复杂度为O(1)。

```java
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i ++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = low + high >> 1;
                if (numbers[i] + numbers[mid] < target) low = mid + 1;
                else if (numbers[i] + numbers[mid] > target) high = mid - 1;
                else return new int[] {i + 1, mid + 1};
            }
        }
        return null;
    }
}
```

