# Single Number III

[260. 只出现一次的数字 III - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/single-number-iii/)

## 分析

#### 1. 遍历并储存出现次数，返回次数为1的

直接遍历整个数组并记录出现次数，最后返回次数为1的数即可。

```java
class Solution {
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[2];
        int p = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 1) res[p ++] = entry.getKey();
        }
        return res;
    }
}
```

#### 2. 位运算

考虑一个简化问题，如果数组中只有1个仅出现1次的数，其余数都出现2次，那么对整个数组进行异或操作就可以得到仅出现1次的数。

那么可以把原问题寻求某种方法转化为两个上述数组，每个数组包含一个答案，即：

1.  两个只出现1次的数在不同的子数组。
2.  相同的数都在同一个数组。

接下来就把问题转化为如何寻找划分的方法。

---

对整个数组进行异或操作，得到异或结果为XOR。

考虑XOR的每一位。如果当前位为0，表示整个数组的这个位置都是相同的；如果当前位为1，表示两个答案在这个位置上的值不同（其余出现2次的数这个位置对应位相同）。

由上可知，从低到高考察XOR第一个**不为0**的位。则可以通过遍历数组，将这位为0的数划为一组，为1的数划为另一组，即可满足条件：

*   两个相同的数每一位都是相同的，如果一个数被划到了一组，另一个相同的数也会被划到这一组。
*   两个不同的数在考察位是不同的，所以一定会被划在不同的组。

---

最后再分别对两个数组异或，结果就是两个答案。

时间复杂度为O(n)，空间复杂度为O(1)。

```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int lx = 0, rx = 0;
        int xor = 0;
        int div = 1;
        for (int num: nums) xor ^= num;
        while ((div & xor) == 0) div <<= 1;
        for (int num: nums) {
            if ((div & num) == 0) lx ^= num;
            else rx ^= num;
        }
        return new int[] {lx, rx};
    }
}
```

