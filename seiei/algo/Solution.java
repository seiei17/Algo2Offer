package algo;

import java.util.Arrays;

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i ++) {
            if (nums[i] + nums[i + 1] + nums[i + 2] >= target) break;
            int low = i + 1, high = len - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum >= target) high --;
                else {
                    count += high - low;
                    low ++;
                }
            }
        }
        return count;
    }
}