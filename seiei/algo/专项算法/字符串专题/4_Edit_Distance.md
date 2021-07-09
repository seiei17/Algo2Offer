## Edit Distance

[72. 编辑距离 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/edit-distance/)

## 分析

#### 二维DP

将字符串A修改为字符串B的次数就是编辑距离，那么对于字符串A有三种操作方式：

*   向A添加一个字符。
*   将A删除一个字符。
*   将A修改一个字符。

而将A删除一个字符等价于向B添加一个字符。如A = "abc"，B = "ab"，将A删除'c'和向B添加'c'是等价的。那么求编辑距离可以转化为：

*   向A添加一个字符。
*   向B添加一个字符。
*   将A修改一个字符。

举例：A = "horse"，B = "ros"：

*   如果"horse"到"ro"的编辑距离为a，说明只需要a步操作可以将"horse"转化为"ro"。那么我们只需要将"horse"转化为"ro"再在末尾添加一个"s"即可。编辑距离为a + 1。
*   如果"hors"到"ros"的编辑距离为b，说明只需要b步操作可以将"ros"转化为"hors"。那么我们只需要将"ros"转化为"hors"再在末尾添加一个"e"即可。编辑距离为b + 1。
*   如果"hors"到"ro"的编辑距离为c，说明只需要c步操作可以将"hors"转化为"ro"。那么我们只需要将剩下的"e"修改为"s"就可以从"horse"得到"ros"了。编辑距离为c + 1。
*   则A到B的编辑距离为：min(a + 1, b + 1, c + 1)。

那么总结成算法，可以得到动态规划的状态转移方程，dp\[i][j]表示A(0...i)和B(0...j)的编辑距离：

*   字符串A(0...i)到B(0...j-1)的编辑距离为a，那么只需要在A末尾添加一个字符就能得到A(0...i)到B(0...j)的编辑距离：a + 1。
*   字符串A(0...i-1)到B(0...j)的编辑距离为b，那么只需要在B末尾添加一个字符就能得到A(0...i)到B(0...j)的编辑距离：b + 1。
*   字符串A(0...i-1)到B(0...j-1)的编辑距离为c，此时需要考察A(i)和B(j)是否相等：
    *   如果相等，那么不需要做任何修改A(0...i)就能和B(0...j)相等，编辑距离为c。
    *   如果不等，说明需要将A(i)转化为B(i)，编辑距离为c + 1。
*   于是，A(0...i)到B(0...j)的编辑距离dp\[i][j] = min(a + 1, b + 1, c or c + 1)。

##### 代码

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= n; j++) {
                char w1 = word1.charAt(i - 1), w2 = word2.charAt(j - 1);
                int addA = dp[i][j - 1] + 1;
                int addB = dp[i - 1][j] + 1;
                int rplc = dp[i - 1][j - 1] + (w1 == w2 ? 0 : 1);
                dp[i][j] = Math.min(rplc, Math.min(addA, addB));
            }
        }
        return dp[m][n];
    }
}
```

