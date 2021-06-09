## Edit Distance

[72. 编辑距离 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/edit-distance/)

## 分析

#### 二维DP

创建DP数组：int\[]\[] dp = new int\[m + 1][n + 1]。

dp\[i][j]表示word1中**第i个字符以前**的子字符串要组成word2中**第j个字符以前**的子字符串需要的最小操作次数。举例，如word1="horse"，word2="ros"，则dp\[3][2]表示word1中第3个字符'r'以前的子字符串""hor"要组成word2中第2个字符'o'以前的子字符串"ro"需要的最小操作次数。要构成子字符串，可知有三种情况：

1.  构成字符串以第i个字符结尾。先判断word1[i]是否等于word2[j]，如果不等，则操作次数需要增加一次替换。再加上word1第i-1个字符以前的字符串组成word2第j-1个字符以前的子字符串所需的最小操作次数：d1 = dp\[i -1][j - 1] + !(word1[i] == word2[j])。
2.  构成字符串不以第i个字符结尾。即word1第i-1个字符以前的字符串组成word2第j个字符以前的字符串所需的最小次数加上删除第i的字符的次数：d2 = dp\[i - 1][j] + 1。
3.  第i个字符构成了j-1以前的子字符串，再添加剩余的字符个数：d3 = dp\[i][j - 1] + 1。

则dp\[i][j] = min(d1, d2, d3)。最终，dp\[m][n]即为总的最小操作次数。

##### 代码

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j ++) dp[0][j] = j;
        for (int i = 1; i <= m; i ++) {
            dp[i][0] = i;
            for (int j = 1; j <= n; j ++) {
                int d1 = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1)? 0: 1);
                int d2 = dp[i - 1][j] + 1;
                int d3 = dp[i][j - 1] + 1;
                dp[i][j] = Math.min(d1, Math.min(d2, d3));
            }
        }
        return dp[m][n];
    }
}
```

