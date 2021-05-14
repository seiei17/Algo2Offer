## Delete Node in a Linked List

[题目页面见LeetCode。](https://leetcode.com/problems/delete-node-in-a-linked-list/)

## 分析

题目只传入了待删除节点而没有传入head节点。于是无法直接删除节点。

需要将待删除节点替换为后一个节点，再删除后一个节点即可。

## Java代码

```java
public class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```

