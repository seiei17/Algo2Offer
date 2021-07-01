# 传递信息

[LCP 07. 传递信息 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/chuan-di-xin-xi/)

## 分析

#### 动态规划

规定dp\[i][j]表示第i轮，传递到第j个人的路线次数。可知，此时的路线次数应该为上一轮中，传递到第j个人的**前置**的次数和。即状态转移方程为：

<img src="https://latex.codecogs.com/svg.image?dp[i][j]=\sum_{x}^{set}dp[i-1][x]" title="dp[i][j]=\sum_{x}^{set}dp[i-1][x]" />

其中，set为能够传递到j的人。最后返回dp\[k][n-1]即可。

时间复杂度为O(k * m)，m为relation的大小。

```java
class Solution {
    public int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int[] r: relation) {
                dp[i][r[1]] += dp[i - 1][r[0]];
            }
        }

        return dp[k][n - 1];
    }
}
```

