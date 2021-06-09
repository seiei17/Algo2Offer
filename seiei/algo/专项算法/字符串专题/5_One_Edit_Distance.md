## One Edit Distance

给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。

注意：

满足编辑距离等于 1 有三种可能的情形：

1. 往 s 中插入一个字符得到 t
2. 从 s 中删除一个字符得到 t
3. 在 s 中替换一个字符得到 t

示例 1：

    输入: s = "ab", t = "acb"
    输出: true
    解释: 可以将 'c' 插入字符串 s 来得到 t。

示例 2:

    输入: s = "cab", t = "ad"
    输出: false
    解释: 无法通过 1 步操作使 s 变为 t。

示例 3:

    输入: s = "1203", t = "1213"
    输出: true
    解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。

## 分析

#### 1. 直接判断

令m = s.length(), n = t.length()。

判断m、n：

1.  m = 0, n = 0。返回false。

2.  \| m - n \| > 1。说明至少需要插入或删除2个字符，返回false。

3.  其余情况下：

    *   如果m = n，说明只需要替换1个字符就能满足条件。检查s和t，如果只有1个字符不同，返回true；否则，返回false。
    *   如果m != n，说明只需要插入或删除1个字符就能满足条件。检查s和t，只能有多余的一个字符不同，其余字符必须相同，返回true；否则，返回false。

    时间复杂度为O(max(m, n))，空间复杂度为O(1)。

```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m <= 0 && n <= 0) return false;
        if (Math.abs(m - n) > 1) return false;
        if (m == n) {
            int count = 0;
            for (int i = 0; i < m; i ++) {
                if (s.charAt(i) != t.charAt(i)) count ++;
                if (count > 1) return false;
            }
            return count == 1;
        }
        else {
            int p = 0, q = 0;
            int count  = 0;
            while (p < m && q < n) {
                if (s.charAt(p) != t.charAt(q)) {
                    if (m > n) p ++;
                    else q ++;
                    count ++;
                    if (count > 1) return false;
                }
                else {
                    p ++;
                    q ++;
                }
            }
            return true;
        }
    }
}
```



#### 2. 动态规划

使用DP算法计算出最终的编辑距离，如[4.Edit Distance](4_Edit_Distance.md)，再判断最终结果是否为1。

每次都需要遍历完整数组，时间复杂度为O(m + n)。

使用了额外DP数组，空间复杂度为O(m \* n)。

```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m <= 0 && m == n) return false;
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j ++) dp[0][j] = j;
        for (int i = 1; i <= m; i ++) {
            dp[i][0] = i;
            for (int j = 1; j<= n; j ++) {
                // 以自己结尾
                int d1 = dp[i - 1][j - 1] + (s.charAt(i - 1) == t.charAt(j - 1)? 0: 1);
                // 不含自己
                int d2 = dp[i - 1][j] + 1;
                // 自己在中部
                int d3 = dp[i][j - 1] + 1;
                dp[i][j] = Math.min(d1, Math.min(d2, d3));
            }
        }
        return dp[m][n] == 1;
    }
}
```

注：不能单纯判断dp\[i][j] > 1就跳出循环。
