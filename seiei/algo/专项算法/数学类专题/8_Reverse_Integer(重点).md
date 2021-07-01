# Reverse Integer

[7. 整数反转 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/reverse-integer/)

## 分析

使用一个num储存翻转结果：num = num * 10 + x % 10。

但是不允许储存64位整数，且要求如果翻转结果num不在整型范围需要返回0。

所以我们需要判断num是否处于整型范围。

直接判断:

*   如果num * 10 / 10 != num，说明num溢出，返回0。
*   如果(num * 10  + x %10  - x % 10) / num != num，说明num溢出，返回0。

```java
class Solution {
    public int reverse(int x) {
        if (x == 0) return 0;
        boolean minus = x < 0 ? true : false;
        int num = 0;
        while (x != 0) {
            int remainder = x % 10;
            if (num * 10 / 10 != num) return 0;
            if ((num * 10 + remainder - remainder) / 10 != num) return 0;
            num = num * 10 + remainder;
            x /= 10;
        }
        return num;
    }
}
```

