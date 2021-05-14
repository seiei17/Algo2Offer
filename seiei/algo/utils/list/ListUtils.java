package algo.utils.list;

import algo.utils.list.defination.ListNode;

public class ListUtils {

    //**********algo.utils.list.definition.ListNode*********//
    //----------create---------//
    public static ListNode createByNums(int[] nums) {
        if (nums.length <= 0) return null;
        int len = nums.length;
        ListNode head = new ListNode(nums[0]);
        ListNode pre = head;
        for (int i = 1; i < len; i ++) {
            pre.next = new ListNode(nums[i]);
            pre = pre.next;
        }
        pre.next = null;
        return head;
    }

    //----------traverse---------//
    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
