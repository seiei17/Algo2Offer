## Merge k Sorted Lists

[题目：https://leetcode-cn.com/problems/merge-k-sorted-lists/](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

## 分析

#### 分治归并

* 对每个链表两两合并，再将合并后的链表继续两两合并，重复至只有一个链表。

##### 1. 由底至上，非递归

* 定义subLen为子链表的长度，初始为1。
* 依次合并两个子链表，使得每个子链表长度为2\*subLen。
* subLen增加一倍，直到所有链表合并为一个。

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length <= 0) return null;
        int len = lists.length;
        // 从subLen = 1开始，直到sunLen超过len。
        for (int subLen = 1; subLen < len; subLen <<= 1) {
            // 每次合并的两个链表的下标为i和i+subLen。
            // 所以下一组合并的链表下标应该是i+2*subLen。
            for (int i = 0; i < len; i += subLen * 2) {
                // 如果一组内第二个链表不存在，即i+subLen>=Len，则应该合并一个null链表。
                lists[i] = merge(lists[i], i + subLen < len? lists[i + subLen]: null);
            }
        }
        return lists[0];
    }
	
    // Merge Two Lists.
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 != null? l1: l2;
        return dummy.next;
    }
}
```

##### 2. 由顶至底，递归

* 定义合并方法mergeLists(ListNode[] lists, int start, int end)。start为当前链表组的起始下标，end为当前链表组的结束下标。
* mid为分割链表组的中点，mid=start + end >> 1。

```java
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        mergeLists(lists, 0, lists.length - 1);
    }
    
    public ListNode mergeLists(ListNode lists, int start, int end) {
        if (start == end) return lists[start];
        if (start > end) return null;
        int mid = start + end >> 1;
        return merge(mergeLists(lists, start, mid), mergeLists(lists, mid + 1, end));
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
```

##### 3. 优先级队列

对于lists中的每个链表，因为每个链表都是排序的，所以起始节点一定是最小的。

维护一个由每个链表的起始节点组成的优先级队列，每次出列一个最小的组成新链表，再将next节点加入优先级队列，重复直至合并完成。优先级队列出列的时间复杂度为O(logn)，所以总体时间复杂度为O(nlogn)。

```java
public class Solution {
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((x,y)->x.val-y.val);
        for (ListNode list: lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (!queue.isEmpty()) {
            prev.next = queue.poll();
            prev = prev.next;
            if (prev.next != null) {
                queue.offer(prev.next);
            }
        }
        return dummy.next;
    }
}
```

