## Linked List Cycle II

[题目见LeetCode: https://leetcode-cn.com/problems/linked-list-cycle-ii/](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

## 分析

使用快慢指针同时遍历链表。如果链表存在环，则在slow**未走完第一个环**的时候，一定会和fast节点相遇。

```
极限情况下，入环点在第一个节点，那么当慢指针刚好走完一圈的时候会和快指针相遇。
当入环点延后，必定会在慢指针未走完一圈的时候相遇。
```

设头节点到入环点的距离为A，相遇时入环点到慢指针位置的距离为B，慢指针位置继续到入环点的距离为C。

于是相遇时，慢指针走过的距离为：<img src="https://latex.codecogs.com/svg.image?l_s=A&plus;B" title="l_s=A+B" />，快指针走过的距离为：<img src="https://latex.codecogs.com/svg.image?l_f=A&plus;(B&plus;C)&plus;B=A&plus;2B&plus;C" title="l_f=A+(B+C)+B=A+2B+C" />。又因为快指针走过的路程是慢指针的2倍，于是有：<img src="https://latex.codecogs.com/svg.image?2l_s=l_f" title="2l_s=l_f" />，即：<img src="https://latex.codecogs.com/svg.image?2(A&plus;B)=A&plus;2B&plus;C" title="2(A+B)=A+2B+C" />，得到：<img src="https://latex.codecogs.com/svg.image?A=C" title="A=C" />。即**慢指针剩下未走完一个环的距离等于头节点到入环点的距离**。

于是，在快慢节点相遇的时候，使用一个新的节点从头节点出发，和慢指针一起前进，当两者相遇的时候，新节点走过了A，慢指针走过了C，即两者会在入环点相遇。

## Java代码

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        // fast为null表示无环
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
            else return null;
            // ptr指针开始前进直至与slow相遇
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
```

