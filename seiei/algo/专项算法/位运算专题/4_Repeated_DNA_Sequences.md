# Repeated DNA Sequences

[187. 重复的DNA序列 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/repeated-dna-sequences/)

## 分析

使用map来储存，直接比较map中是否存在，然后添加存在次数为2的即可。时间复杂度为O(10n)。

对于滑动窗口查找字符串，可以使用**Rabin-Karp算法**和**二进制掩码**的方法，可以减少时间复杂度。
