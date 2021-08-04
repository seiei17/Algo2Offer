package algo;

import org.junit.Test;

import java.util.*;

public class Solution {
    @Test
    public void test() {
        System.out.println(nthUglyNumber(10));
    }

    public int nthUglyNumber(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            long d2 = dp[p2] * 2, d3 = dp[p3] * 3, d5 = dp[p5] * 5;
            dp[i] = Math.min(d2, Math.min(d5, d3));
            if (dp[i] == d2) p2++;
            else if (dp[i] == d3) p3++;
            else p5++;
        }
        return (int) dp[n];
    }
}