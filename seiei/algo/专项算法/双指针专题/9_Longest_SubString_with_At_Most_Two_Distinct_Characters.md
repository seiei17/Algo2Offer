## Longest Substring With  At Most Two Distinct Characters

给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。

示例 1:

    输入: "eceba"
    输出: 3
    解释: t 是 "ece"，长度为3。

示例 2:

    输入: "ccaabbb"
    输出: 5
    解释: t 是 "aabbb"，长度为5。

## 分析

滑动窗口+双指针。

用一个Map<Character, Integer> map来储存字符和出现的次数，用i来表示左边指针处于的位置，用r来表示右边出现的第三个字符。

当map的大小小于3时，说明当前遍历的字符串只有不超过2个不同字符，继续增大r的值，将r+1位置的字符统计整合进map，直到map的大小等于3，此时字符串已经出现3个字符，停止增大r，此时的r位置为第三个字符首次出现的位置。

此时字符串长度r-i为当前只包含2个字符的字符串的长度。增大左指针i，将i-1位置的字符移出map，直到map中只包含不超过2个字符。

当右指针到达末尾时，可以结束遍历。近似于两个指针都遍历了一次字符串，时间复杂度为O(n)。

	注意：
		r到达末尾时有两种情况：
			1. 末尾元素是第三个字符，长度应计算为r - i。
			2. 末尾元素是第二个字符，长度应计算为r - i + 1。

## Java代码

```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) return n;
        int r = - 1;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        // 左指针i
        for (int i = 0; i < n; i ++) {
            // 字符串中包含第三个字符，i右移，直到只有2个字符
            if (map.size() > 2) {
                int v = map.get(s.charAt(i - 1)) - 1;
                if (v == 0) map.remove(s.charAt(i - 1));
                else map.put(s.charAt(i - 1), v);
            }
            // 当遍历字符串包含不超过2个字符的时候，r右移
            while (r + 1 < n && map.size() < 3) {
                // 字符串中字符重复出现，应增大出现次数的值
                if (map.containsKey(s.charAt(r + 1))) map.computeIfPresent(s.charAt(r + 1), (k, v) -> v + 1);
                // 字符第一次出现，直接纳入map中
                else map.put(s.charAt(r + 1), 1);
                r ++;
            }
            if (map.size() > 2) {
                // r元素为第三个字符，不算入长度中
                res = Math.max(res, r - i);
            } else {
                // r元素为第二个字符，应算入长度中，且可以结束循环了。
                res = Math.max(res, r - i + 1);
                break;
            }
        }
        return res;
    }
}
```

