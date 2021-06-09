## Count and Say

[38. 外观数列 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/count-and-say/)

## 分析

递归算法。

对于第n层，通过递归，可以求出n-1层的字符串bottom=countAndSay(n-1)。分析bottom，我们可以得到当前层的输出。

使用last=bottom.charAt(0)记录上一个遍历的字符，使用cnt记录上一个字符出现的个数。使用cur表示当前层输出的字符串。如果当前遍历字符与last相同，则len++；否则，表示上一组字符已经记录完毕，需要转换成字符串，cur.append(cnt).append(last)，然后last=当前字符，cnt=1。

最后返回得到的字符串即可，cur.toString()。

```java
class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String bottom = countAndSay(n - 1);
        StringBuilder cur = new StringBuilder();
        int cnt = 0;
        int len = bottom.length();
        char last = bottom.charAt(0);
        for (int i = 0; i < len; i ++) {
            if (bottom.charAt(i) != last) {
                cur.append(cnt).append(last);
                last = bottom.charAt(i);
                cnt = 1;
            } else {
                cnt ++;
            }
        }
        cur.append(cnt).append(last);
        return cur.toString();
    }
}
```

