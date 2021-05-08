package algo.sword2offer.Q06;

import algo.utils.list.defination.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Solution06 {

    // 解法1：遍历两遍。第一次计算长度，第二次放入数组。
    public int[] reversePrint1(ListNode head) {
        ListNode travel = head;
        int cnt = 0;
        while (travel != null) {
            cnt ++;
            travel = travel.next;
        }
        int[] res = new int[cnt --];
        while (head != null) {
            res[cnt --] = head.val;
            head = head.next;
        }
        return res;
    }

    // 解法2：用栈储存链表值。
    public int[] reversePrint2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int n = stack.size();
        int[] res = new int[n + 1];
        for (int i = 0; i <= n && !stack.isEmpty(); i ++) {
            res[i] = stack.pop();
        }
        return res;
    }

    // 解法3：用ArrayList。
    public int[] reversePrint3(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int n = list.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i ++) {
            res[i] = list.get(n - i - 1);
        }
        return res;
    }
}
