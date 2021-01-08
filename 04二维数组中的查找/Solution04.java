public class Solution04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) return false;
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        if (n == 0) return false;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (target == matrix[i][j]) return true;
            else if (target < matrix[i][j]) j --;
            else i ++;
        }
        return false;
    }
}
