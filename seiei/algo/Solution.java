package algo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    @Test
    public void test() {
        String s = "[[5,1,0,4,0,1,1,6,7,3,9,9,4,6,8,1],[9,1,0,6,4,2,8,0,1,6,0,2,7,9,0,4],[3,2,0,3,3,3,1,3,7,3,2,1,1,2,2,0],[5,2,8,2,7,6,2,0,5,3,2,4,4,4,8,9],[7,0,5,2,4,6,7,1,1,1,2,2,6,6,4,1],[0,3,5,9,1,8,0,6,3,4,0,9,9,0,9,8],[3,4,0,7,2,8,0,4,9,4,8,5,2,5,9,4],[0,4,4,1,4,6,0,7,0,2,7,1,3,8,9,8],[2,0,7,4,0,7,0,1,1,1,9,5,6,8,9,6],[4,3,9,9,1,9,8,4,2,7,5,7,5,5,5,9],[7,4,6,9,1,8,0,4,9,9,9,7,9,8,3,4],[4,3,5,7,4,5,1,8,3,7,7,0,4,4,2,3],[8,0,2,9,8,2,5,8,4,4,7,3,5,1,9,1],[6,4,8,2,2,2,1,7,1,8,7,5,5,1,0,3],[1,2,5,0,6,0,0,0,7,7,6,4,0,5,5,8],[2,5,1,4,9,4,1,0,2,0,5,7,4,7,3,5],[9,8,7,8,8,9,8,5,9,6,9,9,2,6,0,6],[4,1,2,3,5,5,4,9,5,1,9,9,9,2,7,0],[0,6,8,0,6,9,8,7,5,7,8,9,6,8,5,0]]";
        String ss = s.replaceAll("],", "#").replaceAll("\\[", "").replaceAll("]]", "");
        String[] sss = ss.split("#");
        List<List<Integer>> arrs = new ArrayList<>();
        for (String ssss: sss) {
            String[] nums = ssss.split(",");
            List<Integer> list = new ArrayList<>();
            for (String n: nums) list.add(Integer.valueOf(n));
            arrs.add(list);
        }
        int[][] nums = new int[arrs.size()][arrs.get(0).size()];
        for (int i = 0; i < arrs.size(); i++) {
            for (int j = 0; j < arrs.get(i).size(); j++) nums[i][j] = arrs.get(i).get(j);
        }
        int ans = maxValue(nums);
        System.out.println(map.size());
    }
    private Map<String, Integer> map = new HashMap<>();

    public int maxValue(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        return dfs(grid, m, n, 0, 0);
    }

    private int dfs(int[][] grid, int m, int n, int i, int j) {
        if (i == m || j == n) return 0;
        String key = "" + i + j;
        if (map.containsKey(key)) return map.get(key);
        else {
            int right = dfs(grid, m, n, i, j + 1);
            int down = dfs(grid, m, n, i + 1, j);
            int value = Math.max(right, down) + grid[i][j];
            map.put(key, value);
            return value;
        }
    }
}