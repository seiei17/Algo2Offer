## Sort List

[题目详见Leetcode](https://leetcode-cn.com/problems/sort-list/)。

## 分析

时间复杂度为O(nlngn)，那么可选排序方法为归并排序、堆排序、快速排序（最差情况下为O(n^2)）。归并排序比较适合链表。

#### 1. 自顶向下归并排序，空间复杂度为O(logn)。

1. 通过快慢指针找到链表的中点，将链表拆分为左右两个子链表。
2. 对两个子链表分别排序。
3. 将两个子链表按照排序合并。见\[[21. 合并两个有序列表](../../leetcode/21_Merge_Two_Sorted_Lists.md)]

通过递归实现，当链表节点小于等于1，即当链表为空或只包含一个节点的时候，直接返回。

```java
public class Solution {
    public ListNode sortList(ListNode head) {
        return mergeSort(head, null);
    }

    public ListNode mergeSort(ListNode head, ListNode tail) {
        // 链表为空
        if (head == null) return head;
        
        // 链表只有1个节点
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        // 找中间节点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode lh = mergeSort(head, slow);
        ListNode rh = mergeSort(slow, tail);
        return merge(lh, rh);
    }

    public ListNode merge(ListNode lh, ListNode rh) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy, temp1 = lh, temp2 = rh;
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
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummy.next;
    }
}
```

---

#### 2. 自底向上，没有递归，空间复杂度为O(1)

每次将链表拆分为长度为subLen的子链表，其中，每个子链表都是排序完毕的。再两两将子链表merge组合，直至subLen >= len。

1. 用subLen表示每次需要排序的子链表的长度，初始subLen=1。
2. 每次拆分出两个subLen长度的链表，head1和head2，合并两个列表，将上个合并链表的尾部指向当前合并列表的头部。合并后得到若干个长度为subLen \* 2的子链表。
3. 每次二倍增长：subLen <<= 1，重复操作，直至subLen >= len。

		数学归纳法证明：
		1. 初始时subLen=1，每个长度为1的子链表都是有序的。
		2. 如果每个长度为subLeng的子链表已经有序，合并两个长度为subLen的有序子链表，得到长度为subLeng×2的子链表，一定也是有序的。
		3. 当最后一个子链表的长度小于subLen时，该子链表也是有序的，合并两个有序子链表之后得到的子链表一定也是有序的。

```java
public class Solution {
	public ListNode sortList(ListNode head) {
		if (head == null) return null;
		int len = 0;
		ListNode node = head;
		while (node != null) {
		    node = node.next;
		    len ++;
		}
		ListNode dummy = new ListNode(-1, head);
		for (int subLen = 1; subLen < len; subLen <<= 1) {
		    ListNode prev = dummy, cur = dummy.next;
		    while (cur != null) {
		        // 得到第一个子链表头
		        ListNode head1 = cur;
		        // 找第二个子链表头
		        for (int i = 1; i < subLen && cur.next != null; i ++) cur = cur.next;
		        ListNode head2 = cur.next;
		        cur.next = null;
		        // 找下一组的第一个子链表头
				cur = head2;
				for (int i = 1; i < subLen && cur != null && cur.next != null; i ++) cur = cur.next;
				ListNode next = null;
				if (cur != null) {
				    next = cur.next;
				    cur.next = null;
				}
				// 合并两个链表
				prev.next = merge(head1, head2);
				while (prev.next != null) prev = prev.next;
				cur = next;
			}
		}
		return dummy.next;
	}

	public ListNode merge(ListNode l1, ListNode l2) {
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

