# Add Digits

[258. 各位相加 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/add-digits/)

## 分析

#### 1. 直接运算

每次循环计算num的各个位相加，直到结果为一位数。

*   每次循环内的加法，用sum表示各个位之和，则sum += num % 10，num /= 10，直到num < 0。
*   判断sum是否为一位数，否则继续循环加法。

时间复杂度为O(n^2)。

```java
class Solution {
    public int addDigits(int num) {
        while (num / 10 > 0) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
}
```

#### 2. 进阶算法：不使用循环或递归，且时间复杂度为O(1)

[详细通俗的思路分析，多解法 - 各位相加 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/add-digits/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-7/)

