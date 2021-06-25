# Plus One

[66. 加一 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/plus-one/)

## 分析

对最低位加一，如果有进位，那么它的高一位也是加一处理。

* 于是从低到高，逐位加一，digits[i] = digits[i] + 1。
* 因为每个元素只能储存10以下的数，于是对当前位以10取余，digits[i] = (digits[i] + 1) % 10。
* 判断当前位是否为0。为0，表示有进位，继续对高一位进行计算；不为0，表示无进位，高一位不需要再加一，直接返回。
* 最后，如果循环结束都没有返回，说明产生了连续进位，即当前数组计算后每一位都为0，需要在最高位进一。直接新建一个长度为len + 1的数组，把最高位置1，返回。

时间复杂度为O(n)。

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}
```