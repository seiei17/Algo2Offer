# Single Number II

[137. 只出现一次的数字 II - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/single-number-ii/)

## 分析

#### 1. 遍历并记录出现次数，返回次数为1的数

遍历数组，使用map记录出现次数，最后返回map中次数为1的数。

时间复杂度为O(2n)。

```java
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            if (!map.containsKey(num)) map.put(num, 1);
            else map.computeIfPresent(num, (k, v) -> v + 1);
        }
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return 0;
    }
}
```



#### 2. 位运算

把只出现一次的数记为ans。

数组的元素都是int类型，只有32位。依次计算ans的每一个二进制位是0还是1。

考虑ans的第i位（从低位到高位，从0开始编号），可能为0或者为1。对于数组中非ans的元素，每一个数都出现了3次，它们的第i位可能是0或者1，对应着整个数组中的数的第i位有3的倍数个0或者3的倍数个1。**于是将每个数第i位的数求和，再取余就是ans的第i位**。再把ans的第i位加到ans上即可。

时间复杂度为O(nlogC)，其中C代表数的范围。如果是int型，C=2^32。

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        // 从低位第0位开始求ans的每一位
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            // 把数组中第i位出现的0或者1求和
            for (int num : nums) sum += ((num >> i) & 1);
            // 把第i位的和对三取余则是ans的第i位
            ans += (sum % 3) << i;
        }
        return ans;
    }
}
```

#### 3. 数字电路设计 -> 优化

[只出现一次的数字 II - 只出现一次的数字 II - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/)。

