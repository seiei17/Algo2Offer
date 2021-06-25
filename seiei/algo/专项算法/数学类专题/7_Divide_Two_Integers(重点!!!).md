# Divide Two Integers

[29. 两数相除 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/divide-two-integers/)

## 分析

除法不能使用乘、除、模。

除法的本质是被除数能被除数分为几倍，思考一种最朴素的方法实现除法。用quotient代表商，那么当dividend<divisor的情况下，dividend-=divisor能计算几次，quotient就能加1几次。那么最后得到的quotient就是商。

这种朴素做法的时间复杂度就非常高了，极端情况下当dividend=2^31 - 1，divisor=1时，需要循环减法2^31-1次。

#### 二进制移位或加法做除法

考虑一种情况。如dividend=11，divisor=3：

*   尝试比较3的二倍6是否小于11。如果小于，说明至少需要2个3，即商至少为2。
*   再尝试比较6的二倍12是否小于11。如果小于，说明至少需要4个3，即商至少为4。
*   但是6的二倍12是大于11的，说明商在[2,4)的范围内。
*   再考虑dividend=11-6=5，divisor=3的情况。累加商即可。

这是一个迭代的情况。转换成算法为，用quotient代表商：

*   让temp=divisor用于观察。
*   temp + temp < dividend ? temp += tmep : temp = temp，循环次数为x。
*   quotient += 2 \* x。
*   dividend -= temp。

直到dividend < divisor。

对于加法操作，也可以用二进制左移1位代替。

这样将循环次数降到了logn。

**注意**：

*   计算中可以提出dividend和divisor的符号，这样计算时候只需要考虑正数的情况。
*   要考虑溢出的情况。

```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0)
            return 0;
        int signal1 = dividend > 0 ? 1 : 0, signal2 = divisor > 0 ? 1 : 0;
        // signal=0表示为正数，1表示为负数
        int signal = signal1 ^ signal2;
        long dd = dividend > 0 ? dividend : 0L - dividend;
        long dr = divisor > 0 ? divisor : 0L - divisor;
        long quotient = 0;
        while (dd >= dr) {
            long q = 1;
            long temp = dr;
            while (temp << 1 < dd) {
                temp <<= 1;
                q <<= 1;
            }
            dd -= temp;
            quotient += q;
        }
        quotient = signal == 0? quotient : 0L - quotient;
        if (quotient > 0) return quotient > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) quotient;
        else return (int) quotient;
    }
}
```

