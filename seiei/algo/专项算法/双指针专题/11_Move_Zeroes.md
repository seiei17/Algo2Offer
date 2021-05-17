## Move Zeroes

给定一个数组 `nums`，编写一个函数将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。

**示例:**

```
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
```

[题目见LeetCode: https://leetcode-cn.com/problems/move-zeroes/](https://leetcode-cn.com/problems/move-zeroes/)。

## 分析

采用双指针的方法。

使用i和zero同时遍历数组。当遇到0时，zero就不再前进；当i遇到非零时，交换i和zero位置的元素，i和zero继续遍历。

## Java代码

```java
public class Solution {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int zero = 0;
        for (int i = 0; i < len; i ++) {
            if (nums[i] != 0) {
                // 只有当i和zero不指向同一个位置时才交换
                if (i != zero) {
                    int temp = nums[i];
                    nums[i] = nums[zero];
                    nums[zero] = temp;
                }
                // 只有nums[i] != 0时
                // zero指针才会移动
                zero ++;
            }
        }
    }
}
```

