## Longest SubString Without Repeating Characters

[3. 无重复字符的最长子串 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

## 分析

使用滑动窗口+双指针。

假设子字符串从start开始，结束位置的下一位为end，则[start, end)为一个子字符串，且end与子字符串 中的字符重复，那么子字符串长度为len=end-start。

由于[start, end)中的字符是不重复的，那么[start+1, end)的字符也不重复。于是继续以start+1为子字符串起点，end继续右移直到又出现重复字符。这样，[start, end)组成了一个滑动窗口。

到查找结束，start和end指针分别遍历一次字符串，则时间复杂度为O(n)，空间复杂度为O(len)。

## Java代码

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int end = 0;
        int len = 0;
        for (int start = 0; start < n; start ++) {
            // 每进行一次循环，说明end找到了重复字符，需要start右移一位
            if (start != 0) set.remove(s.charAt(start - 1));
            // end右移，直到找到重复字符
            while (end < n && !set.contains(s.charAt(end))) set.add(s.charAt(end ++));
            len = Math.max(len, end - start);
        }
        return len;
    }
}
```

