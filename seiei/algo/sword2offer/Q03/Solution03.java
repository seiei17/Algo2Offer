package algo.sword2offer.Q03;

public class Solution03 {
    // 题目1 解法3：
    public int findRepeatNumber(int[] nums) {
        int cur = 0, len = nums.length;
        int repeat = -1;
        while (cur < len) {
            if (nums[cur] != cur) {
                if (nums[nums[cur]] == nums[cur]) {
                    repeat = nums[cur];
                    break;
                }
                else {
                    int t = nums[cur];
                    nums[cur] = nums[nums[cur]];
                    nums[t] = t;
                }
            } else {
                cur ++;
            }
        }
        return repeat;
    }

    //题目2：
    public int findRepeatNumber2(int[] nums) {
        int len = nums.length;
        if (len == 0) return -1;
        int start = 1, end = len - 1;
        while (start <= end) {
            int mid = ((end - start) >> 1) + start;
            int count = countRange(nums, len, start, mid);
            if (end == start) {
                if (count > 1) return start;
                break;
            }
            if (count > (mid - start + 1)) end = mid;
            else start = mid + 1;
        }
        return -1;
    }
    int countRange(int[] nums, int length, int start, int end) {
        int count = 0;
        for (int i = 0; i < length; i ++) {
            if (nums[i] <= end && nums[i] >= start) count ++;
        }
        return count;
    }
}
