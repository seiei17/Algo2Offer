# 包含min函数的栈

[剑指 Offer 30. 包含min函数的栈 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

## 分析

#### 双栈

使用两个栈，data栈保存数据，mins栈保存最小值：

*   每次入栈的时候，需要比较入栈值是否大于mins栈顶元素，如果不大于则需要入mins栈。
*   每次出栈的时候，需要比较出栈值是否等于mins栈顶元素，如果等于，需要出mins栈。

>   注意：Stack对象使用的包装类Integer，出栈比较的时候需要使用equals。

```java
class MinStack {
    Stack<Integer> data;
    Stack<Integer> mins;

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<>();
        mins = new Stack<>();
    }
    
    public void push(int x) {
        data.push(x);
        if (mins.isEmpty() || x <= mins.peek()) mins.push(x);
    }
    
    public void pop() {
        if (data.pop().equals(mins.peek())) mins.pop();
    }
    
    public int top() {
        return data.peek();
    }
    
    public int min() {
        return mins.peek();
    }
}
```

---

#### 双向链表实现栈

难点在于要在O(1)的时间复杂度得到栈的最小值，那么我们可以自定义双向链表来实现栈，每个节点储存当前栈中的最小值。

压栈、出栈、获取最小值的时间复杂度均为O(1)。

```java
class MinStack {
    private Node top;

    /** initialize your data structure here. */
    public MinStack() {
        top = null;
    }
    
    public void push(int x) {
        if (top == null) top = new Node(x, x);
        else {
            Node n = new Node(x, Math.min(x, top.min));
            top.next = n;
            n.prev = top;
            top = n;
        }
    }
    
    public void pop() {
        if (top != null) top = top.prev;
    }
    
    public int top() {
        return this.top.val;
    }
    
    public int min() {
        return this.top.min;
    }
}

class Node {
    int val;
    int min;
    Node next, prev;
    Node (int val) {
        this.val = val;
    }
    Node (int val, int min) {
        this.val = val;
        this.min = min;
    }
}
```

