# Excel表列序号

[Leetcode - Excel表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/)

## 分析

实际上是一个从 1 开始的 26 进制的转换。

从高位开始向低位遍历，每次递乘 26 并加上下一位的值。

时间复杂度为 O(n) 。

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            ans = ans * 26 + (int) (c - 'A' + 1);
        }
        return ans;
    }
}
```