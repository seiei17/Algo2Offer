## Merge Sorted Array

[88. 合并两个有序数组 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/merge-sorted-array/)

## 分析

#### 1. 暴力：添加+排序

直接把第二个数组添加到第一个数组末尾，然后排序。

时间复杂度O((m+n)log(m+n))，空间复杂度O(1)。

#### 2. 使用辅助数组

创建一个辅助数组int[] sorted = new int[m + n]。

每次遍历两个数组，比较当前遍历的数，将较小的先放进sorted数组，最后将sorted数组复制到nums1。

时间复杂度为O(m + n)，空间复杂度为O(m + n)。

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] sorted = new int[n + m];
        int i = 0, j = 0;
        int cursor = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) sorted[cursor ++] = nums1[i ++];
            else sorted[cursor ++] = nums2[j ++];
        }
        while (i < m) sorted[cursor ++] = nums1[i ++];
        while (j < n) sorted[cursor ++] = nums2[j ++];
        for (int k = 0; k < m + n; k ++) {
            nums1[k] = sorted[k];
        }
    }
}
```

#### 3. 逆向双指针

从尾到头考虑。

反向比较两个数组，将大的数放入数组尾部。

如果m < n，当数组1遍历完的时候，直接将数组2剩余数赋值到前端。

如果m >= n，当数组2遍历完的时候，数组1不需要再移动了。

时间复杂度为O(m + n)，空间复杂度为O(1)。

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int cursor = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[cursor --] = nums1[i] > nums2[j]? nums1[i --]: nums2[j --];
        }
        while (j >= 0) nums1[cursor --] = nums2[j --];
    }
}
```

简化代码：

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0) {
            nums1[m + n - 1] = m < 1 || nums2[n - 1] > nums1[m - 1]? nums2[-- n]: nums1[-- m];
        }
    }
}
```

