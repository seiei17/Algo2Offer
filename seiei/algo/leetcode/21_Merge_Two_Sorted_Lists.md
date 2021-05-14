## 题目

将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

## 分析

#### 递归

每次传入头节点，创建新的头节点root，如果l1的值小于l2，则root=l1，l1=l1.next；否则root=l2，l2=l2.next。

然后继续递归，使当前root指向下一个递归后的root，root.next = merge(l1, l2)。

```java
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode root;
        if (l1.val <= l2) {
            root = l1;
            l1 = l1.next;
        } else {
            root = l2;
            l2 = l2.next;
        }
        root.next = mergeTwoLists(l1, l2);
        return root;
    }
}
```

#### 非递归 （速度更快）

创建哑节点dummy指向排序后的head。创建temp指向上一个节点，temp1为l1的节点，temp2为l2的节点。

每次比较temp1和temp2的值，如果temp1<=temp2，则temp.next=temp1，temp1=temp1.next；否则temp.next=temp2，temp2=temp2.next。

最后，返回dummy.next。

```java
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy, temp1 = l1, temp2 = l2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        }
        if (temp2 != null) {
            temp.next = temp2;
        }
        return dummy.next;
    }
}
```