# Length of Last Word

[58. 最后一个单词的长度 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/length-of-last-word/)

## 分析

反向遍历。

从尾部向前遍历，得到的第一个单词的长度，就是字符串最后一个单词的长度。

时间复杂度为O(n)，但是只有在字符串少于一个单词的情况下才会达到n，其余时间都低于n。

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1, index = end;
        int res = 0;
        for (; index >= 0; index --) {
            if (res > 0) return res;
            if (s.charAt(index) == ' ') {
                res = end - index;
                end = index - 1;
            }
        }
        if (res == 0) res = end - index;
        return res;  
    }
}
```

