# Add Binary

[67. 二进制求和 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/add-binary/)

## 分析

考虑一个最朴素的算法，直接将a和b转化为十进制数，求和之后转化为二进制数。在Python和Java中自带高精度运算，满可以排除误差：

```java
class Solution {
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(
            Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }
}
```

这个方法的时间复杂度为O(m+n)，并且基于Python和Java自带的高精度运算，在其他语言不适用。并且，对于Java：

*   字符串超过33位，不能转化为Integer；
*   字符串超过65位，不能转化为Long；
*   字符串超过500000001位，不能转化为BigInteger。

所以，对于大数，这种直接的算法在**java**行不通。

#### 1. 模拟运算，反向遍历，依次相加

和十位数的加法一样，只是十位数加法缝10进1，二进制加法要逢2进1。用carry储存进位，从低位依次遍历两个数，如果当前位没有数字（一个字符串遍历完，另一个没有），则直接置为0，每次对应位相加再加上carry得到sum，sum % 2则为和的当前位上的值，carry = sum / 2。最后，判断carry是否大于0，是的话还要再加上carry位。

时间复杂度为O(n)。

```java
class Solution {
    public String addBinary(String a, String b) {
        int cur1 = a.length() - 1, cur2 = b.length() - 1;
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (cur1 >= 0 || cur2 >=0 ) {
            carry += (cur1 >= 0? a.charAt(cur1) - '0': 0) + (cur2 >= 0? b.charAt(cur2) - '0': 0);
            stringBuilder.append(carry % 2);
            carry /= 2;
            cur1--;
            cur2--;
        }
        if (carry > 0) stringBuilder.append(carry);
        return stringBuilder.reverse().toString();
    }
}
```

#### 2. 位运算

跟直接运算是一样的，只是不适用加减乘除。

将a和b转化为二进制数。

考虑到，异或是不带进位的加法，并是求进位的运算。于是：

* 把a、b转化为x、y（二进制数）。接下来，x储存和，y储存进位。当进位y不为零：
  * 计算两者无进位相加：sum = x ^ y。
  * 计算两者本次相加进位：carry = (x & y) \<< 1。
  * 赋值：x = sum, y = carry。

当y为0，即为结果。

```python
class Solution:
    def addBinary(self, a, b) -> str:
        x, y = int(a, 2), int(b, 2)
        while y:
            answer = x ^ y
            carry = (x & y) << 1
            x, y = answer, carry
        return bin(x)[2:]

```

同理，在java中是行不通的。