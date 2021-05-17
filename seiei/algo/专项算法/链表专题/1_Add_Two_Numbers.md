## Add Two Numbers

[题目见LeetCode: https://leetcode-cn.com/problems/add-two-numbers/](https://leetcode-cn.com/problems/add-two-numbers/).

## 分析

输入的链表是逆序储存，直接遍历满足低位先加的要求。

同时遍历两个链表，逐位计算和并与上位的进位相加。即，当前位置节点的值为v1，v2，进位为carry，则和为v1 + v2 + carry。则新节点的值为(v1 + v2 +carry) % 10，新的进位为(v1 + v2 + carry) / 10。如果链表长度不同，则认为短链表后面不足的位置为0。

当遍历结束后，如果进位carry>0，则应该新建一个节点储存carry的值。

## Java代码

```java
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        // 利用哑节点储存头节点的位置
        ListNode dummy = new ListNode(-1), prev = dummy;
        while (l1 != null || l2 != null) {
            // 短链表用0补足
            int v1 = l1 != null? l1.val: 0;
            int v2 = l2 != null? l2.val: 0;
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            prev.next = new ListNode(sum % 10);
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            prev = prev.next;
        }
        // 如果还有进位，需要添加新节点
        if (carry > 0) {
            prev.next = new ListNode(carry, null);
        }
        return dummy.next;
    }
}
```

