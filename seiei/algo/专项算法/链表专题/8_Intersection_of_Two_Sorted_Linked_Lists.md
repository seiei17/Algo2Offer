## Intersection Of Two Sorted Linked Lists

Given two lists sorted in increasing order, create and return a new list representing the two lists' intersection. The new list should be made with its memory - the origin lists should not be changed.

For example:

```
Input:
 
First List: 1 —> 4 —> 7 —> 10 —> null
Second List: 2 —> 4 —> 6 —> 8 —> 10 —> null
 
Output: 4 —> 10 —> null
```

## 分析

依次比较两个链表的当前节点：

* 如果l1的值小于l2，则l1前进一步；
* 如果l1的值大于l2，则l2前进一步；
* 如果相等，则加入新链表，l1和l2均前进一步；
* 直到两者中有至少一个为null。

```java
public class Solution {
    public static ListNode intersection(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) l1 = l1.next;
            else if (l1.val > l2.val) l2 = l2.next;
            else {
                prev.next = new ListNode(l1.val);
                l1 = l1.next;
                l2 = l2.next;
                prev = prev.next;
            }
            prev.next = null;
        }
        return dummy.next;
    }
}
```

