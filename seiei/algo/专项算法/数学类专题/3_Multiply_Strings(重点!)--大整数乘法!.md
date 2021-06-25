# Multiply Strings

[43. Multiply Strings](https://leetcode-cn.com/problems/multiply-strings/)

## 分析

#### 1. 直接竖式相乘，再相加

将a拆分为a(m)\*10^m + a(m-1)\*10^(m-1) + ... + a(1) * 10 + a(0)，与b相乘。将每个数字交叉相乘，再把积相加即为最后结果。

与b相乘的时候，让a(i)与b(j)依次相乘，再加上进位add。每次将结果对10求余储存，add更新为结果除以10。

要记得，使用a(i)相乘之后，应该在最后的结果上补足m - i - 1个零。

最后，把每个a(i)相乘的结果相加即可。

乘积的循环是mn次；字符串相加的字符最长是m+n，字符串要操作n次，所以大概是mn+n\^2次。总的时间复杂度为O(mn + n^2)。

```java
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length(), n = num2.length();
        String res = "0";
        for (int i = m - 1; i >= 0; i--) {
            if (num1.charAt(i) == '0') continue;
            int x = num1.charAt(i) - '0';
            int add = 0;
            StringBuilder now = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                now.append((x * y + add) % 10);
                add = (x * y + add) / 10;
            }
            if (add > 0) now.append(add);
            now = now.reverse();
            for (int k = 0; k < m - i - 1; k++) now.append("0");
            res = plus(res, now.toString());
        }
        return res;
    }

    private String plus(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0) {
            int n1 = i >= 0? num1.charAt(i) - '0': 0;
            int n2 = j >= 0? num2.charAt(j) - '0': 0;
            sb.append((n1 + n2 + carry) % 10);
            carry = (n1 + n2 + carry) / 10;
            i--;
            j--;
        }
        if (carry > 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
```

#### 2. 做乘法

方法一的缺陷在于，在两个结果字符串相加的过程中，多加了用作补足的多余的0。

使用数组来储存结果，可以减少对补足0的操作次数。

假设num1和num2的长度分别为m和n，并且均不为0。那么他们乘积的长度范围一定在(m + n - 1, m + n)中。证明：

*   假设两者都取最小值，那么乘积也是最小值。即num1 = 1 \* 10 ^ (m - 1)，num2 = 1 \* 10 ^ (n - 1)，那么结果为1 \* 10 ^ (m + n - 2)。即长度为m + n - 1。
*   假设两者都取最大值，那么乘积也是最大值。即num1 = 10 ^ m - 1，num2 = 10 ^ n - 1，那么结果为10 ^ (m + n) - 10 ^ m - 10 ^ n - 1，即大小不超过10 ^ (m + n)，那么最大长度为m + n。

那么，通过对竖式计算乘法的模拟可以发现。在a中下标为i的数和在b中下标为j的数相乘的结果应该储存在i + j + 1中。即乘积结果mul = res[i + j + 1] + x \* y。其中，x代表a[i]，y代表b[j]，res[i + j + 1]表示之前计算储存在这个位置的值。

那么res[i + j + 1] = mul % 10，并且产生进位应该储存在上一位res[i + j] += mul / 10。

时间复杂度为O(mn)。

![img](https://assets.leetcode-cn.com/solution-static/43/13.png)

```java
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + n; i++) {
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
```