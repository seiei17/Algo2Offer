## Distinct Subsequences

[115. 不同的子序列 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/distinct-subsequences/)

## 分析

#### 1. 二维DP

使用二维数组的DP。

创建DP数组：int\[][] dp = new int \[s.length() + 1][t.length() + 1];

对于dp\[i][j]，i表示当前处理的是s中的第i个字符，j表示当前字符即之前对应的t中拆分子字符串的个数。

如s="babgbag"，t="bag"，做dp表。

|      | i    | 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| j    |      | b    | a    | b    | g    | b    | a    | g    |
| 1    | b    | 1    | 1    | 2    | 2    | 3    | 3    | 3    |
| 2    | ba   | 0    | 1    | 1    | 1    | 1    | 4    | 4    |
| 3    | bag  | 0    | 0    | 0    | 1    | 1    | 1    | 5    |

转移方程为：

1.  对于\[1,j)和(j,t.length()\范围内的j，dp\[i]\[j] = dp\[i - 1]\[j]。
2.  对于以自己结尾的子字符串，dp\[i][j] = dp\[i - 1]\[j - 1] + dp\[i - 1]\[j]。

举个例子，当i=6时，此时对应s的字符为'a'：

1.  当j=1，求此时前方字符串"b"的个数，因为s[i] = 'a'，不影响这个字符串，于是dp\[i][j] = dp\[i - 1][j] = 3。
2.  当j=2,求此时前方字符串"ba"的个数，个数应该是前方"b"与当前"a"组合的个数+前方"ba"的个数，所以等于前方"ba"的个数+前方"b"的个数，即dp\[i][j] = dp\[i - 1][j - 1] + dp\[i - 1][j]。
3.  当j=3，求此时前方字符串"bag"的个数，当前i位无法组成这个字符串，所以直接复制上一个值。

最后，dp\[s.length()][t.length()]即为所求值。

##### 代码

```java
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i ++) dp[i][0] = 1;
        for (int i = 1; i <= m; i ++) {
            int j = 1;
            while (j <= n) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j];
                j ++;
            }
        }
        return dp[m][n];
    }
}
```

遍历了两次数组，时间复杂度为O(m\*n + m) = O(m*n)，空间复杂度为O(m\*n)。

#### 2. 一维DP

对于动态方程，当j由大到小可以得到，j只跟比自己小的j有关系，即：

1.  dp[j] += dp[j]，if s[i] == t[j]。
2.  dp[j] = dp[j]。

即可以使用一个一维数组来优化空间。

##### 代码

```java
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i ++) {
            int j = n;
            while (j > 0) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[j] += dp[j - 1];
                j --;
            }
        }
        return dp[n];
    }
}
```

## 时间优化

通过构造字典来查找更改的j的值，而不是去遍历t字符串。

用int\[] map来储存字符的最后一个位置，用int\[] next来储存对应位置字符的下一个位置。例如"rabbit"，字符'b'对应的下标int c = 'b' - 'A' = 33，则'b'的起始位置为map\[33] = 4，即第二个b字符的位置，而next\[4]=3，即指向第一个b字符的位置。于是遍历相同字符，只需要查看next数组对应位置的值即可。

```java
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];

        int[] map = new int[58];
        Arrays.fill(map, 0);
        int[] next = new int[n + 1];
        for (int i = 0; i < n; i ++) {
            int c = t.charAt(i) - 'A';
            next[i + 1] = map[c];
            map[c] = i + 1;
        }

        for (int i = 1; i < m + 1; i ++) {
            int c = s.charAt(i - 1) - 'A';
            for (int j = map[c]; j > 0; j = next[j]) dp[j] += dp[j - 1];
        }
        return dp[n];
    }
}
```

