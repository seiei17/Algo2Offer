## Linked List Cycle

[题目见LeetCode: https://leetcode-cn.com/problems/linked-list-cycle/](https://leetcode-cn.com/problems/linked-list-cycle/)

## 分析

通过快慢指针来判断是否有环。快指针的速度是慢指针的两倍，如果链表中有环，则快慢指针一定会相遇；否则，如果其中一个指针为null，说明链表无环。

## Java代码

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        // 如果fast指针为null
        // 则表示链表无环
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return false;
            }
            // 如果两个指针相遇
            // 则表示有环
            if (fast == slow) return true;
        }
        return false;        
    }
}
```

