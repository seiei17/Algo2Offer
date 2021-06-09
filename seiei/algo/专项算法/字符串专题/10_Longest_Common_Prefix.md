# Longest Common Prefix

## 分析

#### 1. 用一个字符串和剩余字符串比较，删除不同的

选用第一个字符串直接作为prefix，依次和剩下的字符串作比较：

*   每次找到第一个不同的字符的位置，删除prefix从这个位置到结尾的字符串。
*   查看下一个字符串。

时间复杂度为O(m + m \* n)。

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) return "";
        String res = strs[0];
        for (int i = 1; i < len; i ++) {
            int uncommon = 0;
            int sb_len = res.length(), str_len = strs[i].length();
            while (uncommon < sb_len && uncommon < str_len && res.charAt(uncommon) == strs[i].charAt(uncommon)) uncommon ++;
            res = res.substring(0, uncommon);
        }
        return res;
    }
}
```

#### 2. 使用startsWith()

将上面方法的判断模式改为startsWith()。

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) return "";
        String res = strs[0];
        for (int i = 0; i < len; i ++) {
            while (!strs[i].startsWith(res)) res = res.substring(0, res.length() - 1);
        }
        return res;
    }
}
```

