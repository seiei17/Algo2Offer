# Interleaving String

## 分析

对于**双指针**方法，属于一种贪心的策略，在某些情况下不适用。

采用**动态规划**的方法。

如果\|s1| + \|s2| != \|s3|，则直接返回false。

用dp\[i][j]表示s1的前i的字符和s2的前j个字符能否交叉组成s3的前i+j个字符：

*   如果s1[i] = s3[i + j]，说明新增加的字符来自s1。如果s1的前i-1个字符和s2的前j个字符能够组成s3的前i+j-1个字符，那么当前情况也可以，即dp\[i][j] = dp\[i - 1][j] && s1[i] == s3[i + j]。
*   如果s2[j] = s3[i + j]，说明新增加的字符来自s2。如果s1的前i个字符和s2的前j-1个字符能够组成s3的前i+j-1个字符，那么当前情况也可以，即dp\[i][j] = dp\[i][j - 1] && s2[j] == s3[i + j]。

那么状态转移方程为dp\[i][j] = (dp\[i - 1][j] && s1[i] == s3[i + j]) \|| ( dp\[i][j - 1] && s2[j] == s3[i + j])。

对于dp\[0][0]，表示s1的空字串和s2的空字串能否组成s3的空字串，显然为true。

对于i=0或者j=0的情况，表示只由s2或者s1能否组成s3的情况，也要纳入状态转移的过程中。

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), l = s3.length();
        if (l != m + n) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < m + 1; i ++) {
            for (int j = 0; j < n + 1; j ++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s3.charAt(p) == s1.charAt(i - 1)); 
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s3.charAt(p) == s2.charAt(j - 1));
                }
            }
        }
        return dp[m][n];
    }
}
```

## 一位数组优化

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), l = s3.length();
        if (l != m + n) return false;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i < m + 1; i ++) {
            for (int j = 0; j < n + 1; j ++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s3.charAt(p) == s1.charAt(i - 1);
                }
                if (j > 0) {
                    dp[j] = dp[j] || dp[j - 1] && s3.charAt(p) == s2.charAt(j - 1);
                }
            }
        }
        return dp[n];
    }
}

```

