package algo;

import org.junit.Test;

import java.util.*;

public class Solution {
    @Test
    public void test() {
        int[] nums = {0,0,1,2,5};
        System.out.println(isStraight(nums));
    }

    public boolean isStraight(int[] nums) {
        int[] cnt = new int[14];
        for (int n: nums) cnt[n]++;
        boolean begin = false;
        int number = 0;
        for (int i = 1; i <= 13; i++) {
            if (begin) {
                if (cnt[i] == 0) {
                    if (cnt[0] > 0) {
                        cnt[0]--;
                    } else return false;
                }
                if (++number == 5) return true;
            } else if (nums[i] > 0) {
                begin = true;
                number++;
            }
        }
        return number == 5;
    }
}