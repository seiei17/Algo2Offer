package algo.leetcode.list.Q1AddTwoNumbers;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i ++) {
            // 说明不存在互补的值
            if (!map.containsKey(nums[i])) {
                map.put(target - nums[i], i);
            // 说明存在互补的值
            } else {
                return new int[] {map.get(nums[i]), i};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2,7,11,15};
        int target = 9;
        int[] res = twoSum(nums, target);
        assert res != null;
        for (int r: res) {
            System.out.print(r + " ");
        }
    }
}
