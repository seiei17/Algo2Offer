## Insertion Sort List

[题目页面见LeetCode。](https://leetcode-cn.com/problems/insertion-sort-list/)

## 分析

即插入排序的链表实现。

每次选择一个待排数，分别与前面已经排序的数比较，插入到合适的位置。时间复杂度为**O(n)**，可以通过减少比较次数进行优化：**对于一个待排数cur，即cur以前的序列是已经排过序的，如果cur的值大于它的前面一个数，则说明cur节点应该放在原地而不用再依次和前面的数比较了**。

因为会可能插入在头节点之前，引入dummy节点向链表的第一个节点。也可以方便比较的循环遍历。

1. 依次取出未排序的节点，先和前驱节点比较，如果大于等于前驱节点，则放在原地，检查下一个节点。
2. 如果比前驱节点小，则依次从dummy节点开始比较，找到合适的位置。

## Java代码

```java
public class Solution {
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1, head), pre;
        while (head != null && head.next != null) {
            if (head.next.val >= head.val) {
                head = head.next;
                continue;
            }
            pre = dummy;
            while (pre.next.val < head.next.val) pre = pre.next;
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
        }
        return dummy.next;
    }
}
```

