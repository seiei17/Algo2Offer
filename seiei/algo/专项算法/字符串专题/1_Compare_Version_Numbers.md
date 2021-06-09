## Compare Version Numbers

[165. 比较版本号 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/compare-version-numbers/)

## 分析

#### 1. 分割后比较

使用split()方法将版本号依"."分开为数组，依次比较数组对应位置的值，如果长度不足，则应补零。

如使用i1表示version1对应位置的值，i2表示version2对应位置的值。如果version1在这个长度没有值，则直接置i1=0，再进行比较。

时间复杂度为O(M + N + max(M, N))，因为split的时候遍历了两个字符串，最后比较遍历了较长的一个数组。

空间复杂度为O(M + N)。

```java
class Solution {
  public int compareVersion(String version1, String version2) {
    String[] nums1 = version1.split("\\.");
    String[] nums2 = version2.split("\\.");
    int n1 = nums1.length, n2 = nums2.length;

    // compare versions
    int i1, i2;
    for (int i = 0; i < Math.max(n1, n2); ++i) {
      i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
      i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
      if (i1 != i2) {
        return i1 > i2 ? 1 : -1;
      }
    }
    // the versions are equal
    return 0;
  }
}
```

#### 2. 双指针遍历

使用双指针直接遍历，省略了split()的一步。

使用p1、p2表示指向两个版本号的指针。如果p1或者p2位置的字符为'.'，表示上一个小版本号已经结束，应该递增指向下一个小版本号的开头位置。

使用i1、i2表示当前小版本号代表的十进制数字，初始化为0。如果当前版本号没有这一位的小版本号，即p < len，则值不变（直接为0）；否则，计算当前小版本号的值：while(p < len && version.charAt(p) != '.') i = i \* 10 + version.charAt(p) - '0'。

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        int n1 = version1.length(), n2 = version2.length();
        int p1 = 0, p2 = 0;
        while (p1 < n1 || p2 < n2) {
            if (p1 < n1 && version1.charAt(p1) == '.') p1 ++;
            if (p2 < n2 && version2.charAt(p2) == '.') p2 ++;
            int cur1 = 0, cur2 = 0;
            while (p1 < n1 && version1.charAt(p1) != '.') cur1 = cur1 * 10 + version1.charAt(p1 ++) - '0';
            while (p2 < n2 && version2.charAt(p2) != '.') cur2 = cur2 * 10 + version2.charAt(p2 ++) - '0';
            if (cur1 != cur2) return Integer.compare(cur1, cur2);
        }
        return 0;
    }
}
```

