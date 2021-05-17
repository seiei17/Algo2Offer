package algo;

import algo.utils.list.ListUtils;
import algo.utils.list.defination.ListNode;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i ++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = low + high >> 1;
                if (numbers[i] + numbers[mid] < target) low = mid + 1;
                else if (numbers[i] + numbers[mid] > target) high = mid - 1;
                else return new int[] {i + 1, mid + 1};
            }
        }
        return null;
    }
}