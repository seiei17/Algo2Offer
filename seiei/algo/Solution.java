package algo;

import org.junit.Test;

import java.util.*;

public class Solution {
    private int ans = 0;
    private int m;
    private int n;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        m = students.length;
        n = students[0].length;
        int[] stu = new int[m], men = new int[m];
        for (int i = 0; i < m; i++) {
            stu[i] = cal(students[i], n);
            men[i] = cal(mentors[i], n);
        }
        get(stu, men, 0, 0);
        return ans;
    }

    private void get(int[] stu, int[] men, int stu_id, int point) {
        if (stu_id == m) {
            ans = Math.max(ans, point);
            return;
        }
        for (int i = 0; i < m; i++) {
            if (men[i] == -1) continue;
            int p = cov(stu[stu_id], men[i], n);
            int t = men[i];
            men[i] = -1;
            get(stu, men, stu_id + 1, point + p);
            men[i] = t;
        }
    }

    private int cov(int a, int b, int n) {
        int s = a ^ b;
        return n - Integer.bitCount(s);
    }

    private int cal(int[] p, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum * 10 + p[i];
        }
        return sum;
    }
    @Test
    public void test() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.getOrDefault(0, new ArrayList<>()).add(1);
        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}