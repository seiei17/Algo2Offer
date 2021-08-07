package algo;

import org.junit.Test;

public class Solution {
    @Test
    public void test() {
        int[] nums = {10,14,49,22,7,6,25};
        int k = 2;
        System.out.println(minSpaceWastedKResizing(nums, k));
    }
    private int ans;
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        ans = Integer.MAX_VALUE;
        for (int num : nums) {
            cal(nums, k, n, num, 0, 0);
        }
        return ans;
    }

    private void cal(int[] nums, int k, int n, int capacity, int waste, int idx) {
        if (idx == n) {
            ans = Math.min(ans, waste);
            return;
        }
        if (nums[idx] == capacity) {
            cal(nums, k, n, capacity, waste, idx + 1);
        } else if (nums[idx] < capacity) {
            if (k > 0) cal(nums, k - 1, n, nums[idx], waste, idx + 1);
            cal(nums, k, n, capacity, waste - nums[idx] + capacity, idx + 1);
        } else {
            if (k > 0) cal(nums, k - 1, n, nums[idx], waste, idx + 1);
        }
    }
}
